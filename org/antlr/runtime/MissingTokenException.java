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
/*    */ public class MissingTokenException
/*    */   extends MismatchedTokenException
/*    */ {
/*    */   public Object inserted;
/*    */   
/*    */   public MissingTokenException() {}
/*    */   
/*    */   public MissingTokenException(int expecting, IntStream input, Object inserted) {
/* 39 */     super(expecting, input);
/* 40 */     this.inserted = inserted;
/*    */   }
/*    */   
/*    */   public int getMissingType() {
/* 44 */     return this.expecting;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     if (this.inserted != null && this.token != null) {
/* 49 */       return "MissingTokenException(inserted " + this.inserted + " at " + this.token.getText() + ")";
/*    */     }
/* 51 */     if (this.token != null) {
/* 52 */       return "MissingTokenException(at " + this.token.getText() + ")";
/*    */     }
/* 54 */     return "MissingTokenException";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MissingTokenException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */