/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.security.Key;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.CipherInputStream;
/*     */ import javax.crypto.CipherOutputStream;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.PBEParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AESCipherImpl
/*     */   implements URIConverter.Cipher
/*     */ {
/*     */   private static final String ENCRYPTION_ALGORITHM = "AES/CFB8/PKCS5Padding";
/*     */   private static final int ENCRYPTION_IV_LENGTH = 16;
/*     */   private static final String ENCRYPTION_KEY_ALGORITHM = "AES";
/*     */   private static final String PBE_ALGORITHM = "PBEWithMD5AndDES";
/*     */   private static final int PBE_IV_LENGTH = 8;
/*     */   private static final int PBE_ITERATIONS = 1000;
/*     */   private static KeyGenerator keygen;
/*     */   private static SecureRandom random;
/*     */   private String password;
/*     */   private Key key;
/*     */   
/*     */   private static Key generateKey(int keysize) {
/*  65 */     if (keygen == null) {
/*     */       
/*     */       try {
/*     */         
/*  69 */         keygen = KeyGenerator.getInstance("AES");
/*  70 */         keygen.init(keysize);
/*     */       }
/*  72 */       catch (Exception ex) {
/*     */ 
/*     */         
/*  75 */         throw new RuntimeException(ex);
/*     */       } 
/*     */     }
/*  78 */     return keygen.generateKey();
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] randomBytes(int length) {
/*  83 */     if (random == null)
/*     */     {
/*  85 */       random = new SecureRandom();
/*     */     }
/*     */     
/*  88 */     byte[] bytes = new byte[length];
/*  89 */     random.nextBytes(bytes);
/*     */     
/*  91 */     return bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] readBytes(int length, InputStream in) throws Exception {
/*  96 */     byte[] bytes = new byte[length];
/*  97 */     int read = in.read(bytes);
/*     */     
/*  99 */     if (read != length)
/*     */     {
/* 101 */       throw new Exception("expected length != actual length");
/*     */     }
/*     */     
/* 104 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] transformWithPassword(byte[] bytes, byte[] iv, String password, int mode) throws Exception {
/* 110 */     PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
/* 111 */     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
/* 112 */     SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
/* 113 */     PBEParameterSpec pbeParamSpec = new PBEParameterSpec(iv, 1000);
/*     */ 
/*     */     
/* 116 */     Cipher keyCipher = Cipher.getInstance("PBEWithMD5AndDES");
/* 117 */     keyCipher.init(mode, pbeKey, pbeParamSpec);
/* 118 */     return keyCipher.doFinal(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 123 */   private int keysize = 128;
/*     */   
/*     */   private byte[] encryptedKeyBytes;
/*     */   private byte[] pbeIV;
/*     */   private byte[] encryptionIV;
/*     */   
/*     */   public AESCipherImpl(String password) throws Exception {
/* 130 */     this.password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeysize(int keysize) {
/* 140 */     if (this.key == null)
/*     */     {
/* 142 */       this.keysize = keysize;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKeysize() {
/* 148 */     return this.keysize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream encrypt(OutputStream outputStream) throws Exception {
/* 156 */     if (this.key == null) {
/*     */ 
/*     */       
/* 159 */       this.key = generateKey(getKeysize());
/*     */ 
/*     */       
/* 162 */       this.pbeIV = randomBytes(8);
/*     */ 
/*     */       
/* 165 */       this.encryptionIV = randomBytes(16);
/*     */ 
/*     */       
/* 168 */       this.encryptedKeyBytes = transformWithPassword(this.key.getEncoded(), this.pbeIV, this.password, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     outputStream.write(this.pbeIV);
/* 175 */     outputStream.write(this.encryptionIV);
/* 176 */     outputStream.write(this.encryptedKeyBytes.length);
/* 177 */     outputStream.write(this.encryptedKeyBytes);
/*     */ 
/*     */     
/* 180 */     Cipher cipher = Cipher.getInstance("AES/CFB8/PKCS5Padding");
/* 181 */     cipher.init(1, this.key, new IvParameterSpec(this.encryptionIV));
/*     */ 
/*     */ 
/*     */     
/* 185 */     outputStream = new FilterOutputStream(outputStream)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     return new CipherOutputStream(outputStream, cipher);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish(OutputStream out) throws Exception {
/* 198 */     out.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream decrypt(InputStream in) throws Exception {
/* 204 */     byte[] pbeIV = readBytes(8, in);
/* 205 */     byte[] encryptionIV = readBytes(16, in);
/* 206 */     int keyLength = in.read();
/* 207 */     byte[] encryptedKeyBytes = readBytes(keyLength, in);
/*     */ 
/*     */     
/* 210 */     byte[] decryptedKeyBytes = transformWithPassword(encryptedKeyBytes, pbeIV, this.password, 2);
/*     */ 
/*     */     
/* 213 */     Key key = new SecretKeySpec(decryptedKeyBytes, "AES");
/*     */ 
/*     */     
/* 216 */     if (this.key == null) {
/*     */       
/* 218 */       this.pbeIV = pbeIV;
/* 219 */       this.encryptionIV = encryptionIV;
/* 220 */       this.encryptedKeyBytes = encryptedKeyBytes;
/* 221 */       this.key = key;
/*     */     } 
/*     */ 
/*     */     
/* 225 */     Cipher cipher = Cipher.getInstance("AES/CFB8/PKCS5Padding");
/* 226 */     cipher.init(2, key, new IvParameterSpec(encryptionIV));
/* 227 */     return new CipherInputStream(in, cipher);
/*     */   }
/*     */   
/*     */   public void finish(InputStream in) throws Exception {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\AESCipherImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */