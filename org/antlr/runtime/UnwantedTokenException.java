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
/*    */ public class UnwantedTokenException
/*    */   extends MismatchedTokenException
/*    */ {
/*    */   public UnwantedTokenException() {}
/*    */   
/*    */   public UnwantedTokenException(int expecting, IntStream input) {
/* 36 */     super(expecting, input);
/*    */   }
/*    */   
/*    */   public Token getUnexpectedToken() {
/* 40 */     return this.token;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     String exp = ", expected " + this.expecting;
/* 45 */     if (this.expecting == 0) {
/* 46 */       exp = "";
/*    */     }
/* 48 */     if (this.token == null) {
/* 49 */       return "UnwantedTokenException(found=" + null + exp + ")";
/*    */     }
/* 51 */     return "UnwantedTokenException(found=" + this.token.getText() + exp + ")";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\UnwantedTokenException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */