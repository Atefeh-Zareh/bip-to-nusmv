/*    */ package org.antlr.runtime.tree;
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
/*    */ public class RewriteCardinalityException
/*    */   extends RuntimeException
/*    */ {
/*    */   public String elementDescription;
/*    */   
/*    */   public RewriteCardinalityException(String elementDescription) {
/* 38 */     this.elementDescription = elementDescription;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 42 */     if (this.elementDescription != null) {
/* 43 */       return this.elementDescription;
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\RewriteCardinalityException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */