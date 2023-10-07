/*    */ package org.antlr.runtime;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public class ANTLRInputStream
/*    */   extends ANTLRReaderStream
/*    */ {
/*    */   public ANTLRInputStream() {}
/*    */   
/*    */   public ANTLRInputStream(InputStream input) throws IOException {
/* 40 */     this(input, (String)null);
/*    */   }
/*    */   
/*    */   public ANTLRInputStream(InputStream input, int size) throws IOException {
/* 44 */     this(input, size, null);
/*    */   }
/*    */   
/*    */   public ANTLRInputStream(InputStream input, String encoding) throws IOException {
/* 48 */     this(input, 1024, encoding);
/*    */   }
/*    */   
/*    */   public ANTLRInputStream(InputStream input, int size, String encoding) throws IOException {
/* 52 */     this(input, size, 1024, encoding);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ANTLRInputStream(InputStream input, int size, int readBufferSize, String encoding) throws IOException {
/*    */     InputStreamReader isr;
/* 62 */     if (encoding != null) {
/* 63 */       isr = new InputStreamReader(input, encoding);
/*    */     } else {
/*    */       
/* 66 */       isr = new InputStreamReader(input);
/*    */     } 
/* 68 */     load(isr, size, readBufferSize);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\ANTLRInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */