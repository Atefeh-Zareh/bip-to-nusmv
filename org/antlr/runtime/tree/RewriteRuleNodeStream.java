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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RewriteRuleNodeStream
/*    */   extends RewriteRuleElementStream
/*    */ {
/*    */   public RewriteRuleNodeStream(TreeAdaptor adaptor, String elementDescription) {
/* 38 */     super(adaptor, elementDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleNodeStream(TreeAdaptor adaptor, String elementDescription, Object oneElement) {
/* 46 */     super(adaptor, elementDescription, oneElement);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleNodeStream(TreeAdaptor adaptor, String elementDescription, List elements) {
/* 54 */     super(adaptor, elementDescription, elements);
/*    */   }
/*    */   
/*    */   public Object nextNode() {
/* 58 */     return _next();
/*    */   }
/*    */   
/*    */   protected Object toTree(Object el) {
/* 62 */     return this.adaptor.dupNode(el);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object dup(Object el) {
/* 68 */     throw new UnsupportedOperationException("dup can't be called for a node stream.");
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\RewriteRuleNodeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */