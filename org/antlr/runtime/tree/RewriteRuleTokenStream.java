/*    */ package org.antlr.runtime.tree;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.antlr.runtime.Token;
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
/*    */ public class RewriteRuleTokenStream
/*    */   extends RewriteRuleElementStream
/*    */ {
/*    */   public RewriteRuleTokenStream(TreeAdaptor adaptor, String elementDescription) {
/* 37 */     super(adaptor, elementDescription);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleTokenStream(TreeAdaptor adaptor, String elementDescription, Object oneElement) {
/* 45 */     super(adaptor, elementDescription, oneElement);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RewriteRuleTokenStream(TreeAdaptor adaptor, String elementDescription, List elements) {
/* 53 */     super(adaptor, elementDescription, elements);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object nextNode() {
/* 58 */     Token t = (Token)_next();
/* 59 */     return this.adaptor.create(t);
/*    */   }
/*    */   
/*    */   public Token nextToken() {
/* 63 */     return (Token)_next();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object toTree(Object el) {
/* 70 */     return el;
/*    */   }
/*    */   
/*    */   protected Object dup(Object el) {
/* 74 */     throw new UnsupportedOperationException("dup can't be called for a token stream.");
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\RewriteRuleTokenStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */