/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.RecognizerSharedState;
/*     */ import org.antlr.runtime.TokenStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeRewriter
/*     */   extends TreeParser
/*     */ {
/*     */   protected boolean showTransformations = false;
/*     */   protected TokenStream originalTokenStream;
/*     */   protected TreeAdaptor originalAdaptor;
/*     */   fptr topdown_fptr;
/*     */   fptr bottomup_ftpr;
/*     */   
/*     */   public TreeRewriter(TreeNodeStream input) {
/*  45 */     this(input, new RecognizerSharedState());
/*     */   }
/*     */   
/*  48 */   public TreeRewriter(TreeNodeStream input, RecognizerSharedState state) { super(input, state);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     this.topdown_fptr = new fptr() { private final TreeRewriter this$0; public Object rule() throws RecognitionException {
/* 108 */           return TreeRewriter.this.topdown();
/*     */         } }
/*     */       ;
/* 111 */     this.bottomup_ftpr = new fptr() { private final TreeRewriter this$0;
/* 112 */         public Object rule() throws RecognitionException { return TreeRewriter.this.bottomup(); } }; this.originalAdaptor = input.getTreeAdaptor(); this.originalTokenStream = input.getTokenStream(); }
/*     */   public Object applyOnce(Object t, fptr whichRule) { if (t == null)
/*     */       return null;  try { this.state = new RecognizerSharedState(); this.input = new CommonTreeNodeStream(this.originalAdaptor, t); ((CommonTreeNodeStream)this.input).setTokenStream(this.originalTokenStream); setBacktrackingLevel(1); TreeRuleReturnScope r = (TreeRuleReturnScope)whichRule.rule(); setBacktrackingLevel(0); if (failed())
/*     */         return t;  if (this.showTransformations && r != null && !t.equals(r.getTree()) && r.getTree() != null)
/*     */         reportTransformation(t, r.getTree());  if (r != null && r.getTree() != null)
/*     */         return r.getTree();  return t; } catch (RecognitionException e) { return t; }  }
/* 118 */   public Object applyRepeatedly(Object t, fptr whichRule) { boolean treeChanged = true; while (treeChanged) { Object u = applyOnce(t, whichRule); treeChanged = !t.equals(u); t = u; }  return t; } public Object topdown() throws RecognitionException { return null; } public Object downup(Object t) { return downup(t, false); } public Object downup(Object t, boolean showTransformations) { this.showTransformations = showTransformations; TreeVisitor v = new TreeVisitor(new CommonTreeAdaptor()); TreeVisitorAction actions = new TreeVisitorAction() { private final TreeRewriter this$0; public Object pre(Object t) { return TreeRewriter.this.applyOnce(t, TreeRewriter.this.topdown_fptr); } public Object post(Object t) { return TreeRewriter.this.applyRepeatedly(t, TreeRewriter.this.bottomup_ftpr); } }
/* 119 */       ; t = v.visit(t, actions); return t; } public void reportTransformation(Object oldTree, Object newTree) { System.out.println(((Tree)oldTree).toStringTree() + " -> " + ((Tree)newTree).toStringTree()); } public Object bottomup() throws RecognitionException { return null; }
/*     */ 
/*     */   
/*     */   public static interface fptr {
/*     */     Object rule() throws RecognitionException;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeRewriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */