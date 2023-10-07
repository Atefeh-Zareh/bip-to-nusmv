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
/*    */ public class MismatchedSetException
/*    */   extends RecognitionException
/*    */ {
/*    */   public BitSet expecting;
/*    */   
/*    */   public MismatchedSetException() {}
/*    */   
/*    */   public MismatchedSetException(BitSet expecting, IntStream input) {
/* 37 */     super(input);
/* 38 */     this.expecting = expecting;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     return "MismatchedSetException(" + getUnexpectedType() + "!=" + this.expecting + ")";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MismatchedSetException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */