/*    */ package org.antlr.runtime.tree;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeVisitor
/*    */ {
/*    */   protected TreeAdaptor adaptor;
/*    */   
/*    */   public TreeVisitor(TreeAdaptor adaptor) {
/* 10 */     this.adaptor = adaptor;
/*    */   } public TreeVisitor() {
/* 12 */     this(new CommonTreeAdaptor());
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
/*    */   public Object visit(Object t, TreeVisitorAction action) {
/* 26 */     boolean isNil = this.adaptor.isNil(t);
/* 27 */     if (action != null && !isNil) {
/* 28 */       t = action.pre(t);
/*    */     }
/* 30 */     int n = this.adaptor.getChildCount(t);
/* 31 */     for (int i = 0; i < n; i++) {
/* 32 */       Object child = this.adaptor.getChild(t, i);
/* 33 */       Object visitResult = visit(child, action);
/* 34 */       Object childAfterVisit = this.adaptor.getChild(t, i);
/* 35 */       if (visitResult != childAfterVisit) {
/* 36 */         this.adaptor.setChild(t, i, visitResult);
/*    */       }
/*    */     } 
/* 39 */     if (action != null && !isNil) t = action.post(t); 
/* 40 */     return t;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeVisitor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */