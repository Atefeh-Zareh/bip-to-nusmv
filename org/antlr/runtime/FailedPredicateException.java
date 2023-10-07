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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FailedPredicateException
/*    */   extends RecognitionException
/*    */ {
/*    */   public String ruleName;
/*    */   public String predicateText;
/*    */   
/*    */   public FailedPredicateException() {}
/*    */   
/*    */   public FailedPredicateException(IntStream input, String ruleName, String predicateText) {
/* 46 */     super(input);
/* 47 */     this.ruleName = ruleName;
/* 48 */     this.predicateText = predicateText;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 52 */     return "FailedPredicateException(" + this.ruleName + ",{" + this.predicateText + "}?)";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\FailedPredicateException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */