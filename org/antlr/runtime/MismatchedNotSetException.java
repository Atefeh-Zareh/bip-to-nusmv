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
/*    */ public class MismatchedNotSetException
/*    */   extends MismatchedSetException
/*    */ {
/*    */   public MismatchedNotSetException() {}
/*    */   
/*    */   public MismatchedNotSetException(BitSet expecting, IntStream input) {
/* 35 */     super(expecting, input);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     return "MismatchedNotSetException(" + getUnexpectedType() + "!=" + this.expecting + ")";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MismatchedNotSetException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */