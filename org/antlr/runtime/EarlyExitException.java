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
/*    */ 
/*    */ public class EarlyExitException
/*    */   extends RecognitionException
/*    */ {
/*    */   public int decisionNumber;
/*    */   
/*    */   public EarlyExitException() {}
/*    */   
/*    */   public EarlyExitException(int decisionNumber, IntStream input) {
/* 38 */     super(input);
/* 39 */     this.decisionNumber = decisionNumber;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\EarlyExitException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */