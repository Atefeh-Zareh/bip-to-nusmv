/*     */ package org.eclipse.emf.common.archive;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
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
/*     */ public class Handler
/*     */   extends URLStreamHandler
/*     */ {
/*     */   public static void register() {
/*  42 */     String javaProtocolHandlerPkgs = System.getProperty("java.protocol.handler.pkgs");
/*  43 */     if (javaProtocolHandlerPkgs == null || javaProtocolHandlerPkgs.length() == 0) {
/*     */       
/*  45 */       javaProtocolHandlerPkgs = "org.eclipse.emf.common";
/*     */     }
/*     */     else {
/*     */       
/*  49 */       javaProtocolHandlerPkgs = String.valueOf(javaProtocolHandlerPkgs) + "|org.eclipse.emf.common";
/*     */     } 
/*  51 */     System.setProperty("java.protocol.handler.pkgs", javaProtocolHandlerPkgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/*  61 */     register();
/*     */     
/*  63 */     for (int i = 0; i < args.length; i++) {
/*     */       
/*  65 */       InputStream inputStream = (new URL(args[i])).openStream();
/*  66 */       byte[] bytes = new byte[4048]; int size;
/*  67 */       while ((size = inputStream.read(bytes, 0, bytes.length)) > -1)
/*     */       {
/*  69 */         System.out.write(bytes, 0, size);
/*     */       }
/*     */     } 
/*     */   }
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
/*     */   protected void parseURL(URL url, String specification, int start, int limit) {
/*  89 */     super.parseURL(url, specification, start, limit);
/*     */ 
/*     */ 
/*     */     
/*  93 */     if (start > limit || specification.charAt(start) == '/')
/*     */     {
/*     */       
/*  96 */       throw new IllegalArgumentException(
/*  97 */           "archive protocol must be immediately followed by another URL protocol " + specification);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     int archiveSeparator = specification.indexOf("!/", start);
/* 103 */     if (archiveSeparator < 0)
/*     */     {
/* 105 */       throw new IllegalArgumentException("missing archive separators " + specification.substring(start, limit));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     for (int i = start, end = specification.indexOf('/', start) - 1; (i = specification.indexOf(':', i)) < end; i++) {
/*     */ 
/*     */ 
/*     */       
/* 114 */       archiveSeparator = specification.indexOf("!/", archiveSeparator + 2);
/* 115 */       if (archiveSeparator < 0)
/*     */       {
/* 117 */         throw new IllegalArgumentException("too few archive separators " + specification);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URLConnection openConnection(URL url) throws IOException {
/* 128 */     return new ArchiveURLConnection(url);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\archive\Handler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */