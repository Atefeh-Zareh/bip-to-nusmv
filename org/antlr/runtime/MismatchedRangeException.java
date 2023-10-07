/*    */ package org.antlr.runtime;
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
/*    */ public class MismatchedRangeException
/*    */   extends RecognitionException
/*    */ {
/*    */   public int a;
/*    */   public int b;
/*    */   
/*    */   public MismatchedRangeException() {}
/*    */   
/*    */   public MismatchedRangeException(int a, int b, IntStream input) {
/* 37 */     super(input);
/* 38 */     this.a = a;
/* 39 */     this.b = b;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return "MismatchedNotSetException(" + getUnexpectedType() + " not in [" + this.a + "," + this.b + "])";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MismatchedRangeException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */