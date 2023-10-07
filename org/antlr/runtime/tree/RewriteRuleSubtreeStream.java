/*    */ package org.antlr.runtime.tree;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class RewriteRuleSubtreeStream
/*    */   extends RewriteRuleElementStream
/*    */ {
/*    */   public RewriteRuleSubtreeStream(TreeAdaptor adaptor, String elementDescription) {
/* 35 */     super(adaptor, elementDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleSubtreeStream(TreeAdaptor adaptor, String elementDescription, Object oneElement) {
/* 43 */     super(adaptor, elementDescription, oneElement);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleSubtreeStream(TreeAdaptor adaptor, String elementDescription, List elements) {
/* 51 */     super(adaptor, elementDescription, elements);
/*    */   }
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
/*    */   public Object nextNode() {
/* 68 */     int n = size();
/* 69 */     if (this.dirty || (this.cursor >= n && n == 1)) {
/*    */ 
/*    */       
/* 72 */       Object object = _next();
/* 73 */       return this.adaptor.dupNode(object);
/*    */     } 
/*    */     
/* 76 */     Object el = _next();
/* 77 */     return el;
/*    */   }
/*    */   
/*    */   protected Object dup(Object el) {
/* 81 */     return this.adaptor.dupTree(el);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\RewriteRuleSubtreeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */