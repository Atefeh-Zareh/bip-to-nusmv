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
/*    */ public class NoViableAltException
/*    */   extends RecognitionException
/*    */ {
/*    */   public String grammarDecisionDescription;
/*    */   public int decisionNumber;
/*    */   public int stateNumber;
/*    */   
/*    */   public NoViableAltException() {}
/*    */   
/*    */   public NoViableAltException(String grammarDecisionDescription, int decisionNumber, int stateNumber, IntStream input) {
/* 43 */     super(input);
/* 44 */     this.grammarDecisionDescription = grammarDecisionDescription;
/* 45 */     this.decisionNumber = decisionNumber;
/* 46 */     this.stateNumber = stateNumber;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     if (this.input instanceof CharStream) {
/* 51 */       return "NoViableAltException('" + (char)getUnexpectedType() + "'@[" + this.grammarDecisionDescription + "])";
/*    */     }
/*    */     
/* 54 */     return "NoViableAltException(" + getUnexpectedType() + "@[" + this.grammarDecisionDescription + "])";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\NoViableAltException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */