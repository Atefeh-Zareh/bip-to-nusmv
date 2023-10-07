/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.CipherInputStream;
/*     */ import javax.crypto.CipherOutputStream;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DESCipherImpl
/*     */   implements URIConverter.Cipher
/*     */ {
/*     */   protected static final String ENCRYPTION_SCHEME = "DES";
/*     */   protected static final String UNICODE_FORMAT = "UTF-8";
/*     */   protected String stringKey;
/*     */   protected SecretKey key;
/*     */   
/*     */   public DESCipherImpl() {
/*  57 */     this(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DESCipherImpl(String key) {
/*  62 */     this.stringKey = key;
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream encrypt(OutputStream outputStream) throws Exception {
/*  67 */     Cipher cipher = Cipher.getInstance("DES");
/*  68 */     cipher.init(1, getKey());
/*     */ 
/*     */ 
/*     */     
/*  72 */     outputStream = new FilterOutputStream(outputStream)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     return new CipherOutputStream(outputStream, cipher);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish(OutputStream outputStream) throws Exception {
/*  85 */     outputStream.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream decrypt(InputStream inputStream) throws Exception {
/*  90 */     Cipher cipher = Cipher.getInstance("DES");
/*  91 */     cipher.init(2, getKey());
/*  92 */     return new CipherInputStream(inputStream, cipher);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish(InputStream inputStream) throws Exception {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected SecretKey getKey() throws Exception {
/* 102 */     if (this.key == null) {
/*     */       
/* 104 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 105 */       DESKeySpec keySpec = new DESKeySpec(this.stringKey.getBytes("UTF-8"));
/* 106 */       this.key = keyFactory.generateSecret(keySpec);
/*     */     } 
/* 108 */     return this.key;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\DESCipherImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */