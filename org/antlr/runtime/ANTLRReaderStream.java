/*    */ package org.antlr.runtime;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ 
/*    */ 
/*    */ public class ANTLRReaderStream
/*    */   extends ANTLRStringStream
/*    */ {
/*    */   public static final int READ_BUFFER_SIZE = 1024;
/*    */   public static final int INITIAL_BUFFER_SIZE = 1024;
/*    */   
/*    */   public ANTLRReaderStream() {}
/*    */   
/*    */   public ANTLRReaderStream(Reader r) throws IOException {
/* 45 */     this(r, 1024, 1024);
/*    */   }
/*    */   
/*    */   public ANTLRReaderStream(Reader r, int size) throws IOException {
/* 49 */     this(r, size, 1024);
/*    */   }
/*    */   
/*    */   public ANTLRReaderStream(Reader r, int size, int readChunkSize) throws IOException {
/* 53 */     load(r, size, readChunkSize);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void load(Reader r, int size, int readChunkSize) throws IOException {
/* 59 */     if (r == null) {
/*    */       return;
/*    */     }
/* 62 */     if (size <= 0) {
/* 63 */       size = 1024;
/*    */     }
/* 65 */     if (readChunkSize <= 0) {
/* 66 */       readChunkSize = 1024;
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 71 */       this.data = new char[size];
/*    */       
/* 73 */       int numRead = 0;
/* 74 */       int p = 0;
/*    */       do {
/* 76 */         if (p + readChunkSize > this.data.length) {
/*    */           
/* 78 */           char[] newdata = new char[this.data.length * 2];
/* 79 */           System.arraycopy(this.data, 0, newdata, 0, this.data.length);
/* 80 */           this.data = newdata;
/*    */         } 
/* 82 */         numRead = r.read(this.data, p, readChunkSize);
/*    */         
/* 84 */         p += numRead;
/* 85 */       } while (numRead != -1);
/*    */ 
/*    */       
/* 88 */       this.n = p + 1;
/*    */     }
/*    */     finally {
/*    */       
/* 92 */       r.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\ANTLRReaderStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */