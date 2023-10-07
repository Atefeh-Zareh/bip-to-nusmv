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
/*    */ 
/*    */ public class MismatchedTokenException
/*    */   extends RecognitionException
/*    */ {
/* 32 */   public int expecting = 0;
/*    */ 
/*    */   
/*    */   public MismatchedTokenException() {}
/*    */   
/*    */   public MismatchedTokenException(int expecting, IntStream input) {
/* 38 */     super(input);
/* 39 */     this.expecting = expecting;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return "MismatchedTokenException(" + getUnexpectedType() + "!=" + this.expecting + ")";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MismatchedTokenException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */