/*    */ package org.antlr.runtime;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ANTLRFileStream
/*    */   extends ANTLRStringStream
/*    */ {
/*    */   protected String fileName;
/*    */   
/*    */   public ANTLRFileStream(String fileName) throws IOException {
/* 42 */     this(fileName, null);
/*    */   }
/*    */   
/*    */   public ANTLRFileStream(String fileName, String encoding) throws IOException {
/* 46 */     this.fileName = fileName;
/* 47 */     load(fileName, encoding);
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(String fileName, String encoding) throws IOException {
/*    */     InputStreamReader isr;
/* 53 */     if (fileName == null) {
/*    */       return;
/*    */     }
/* 56 */     File f = new File(fileName);
/* 57 */     int size = (int)f.length();
/*    */     
/* 59 */     FileInputStream fis = new FileInputStream(fileName);
/* 60 */     if (encoding != null) {
/* 61 */       isr = new InputStreamReader(fis, encoding);
/*    */     } else {
/*    */       
/* 64 */       isr = new InputStreamReader(fis);
/*    */     } 
/*    */     try {
/* 67 */       this.data = new char[size];
/* 68 */       this.n = isr.read(this.data);
/*    */     } finally {
/*    */       
/* 71 */       isr.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getSourceName() {
/* 76 */     return this.fileName;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\ANTLRFileStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */