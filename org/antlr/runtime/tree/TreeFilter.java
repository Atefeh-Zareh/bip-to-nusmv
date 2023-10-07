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
/*     */ public class TreeFilter
/*     */   extends TreeParser
/*     */ {
/*     */   protected TokenStream originalTokenStream;
/*     */   protected TreeAdaptor originalAdaptor;
/*     */   fptr topdown_fptr;
/*     */   fptr bottomup_fptr;
/*     */   
/*     */   public TreeFilter(TreeNodeStream input) {
/*  87 */     this(input, new RecognizerSharedState());
/*     */   }
/*     */   public TreeFilter(TreeNodeStream input, RecognizerSharedState state) {
/*  90 */     super(input, state);
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
/* 118 */     this.topdown_fptr = new fptr() {
/*     */         public void rule() throws RecognitionException {
/* 120 */           TreeFilter.this.topdown();
/*     */         }
/*     */         private final TreeFilter this$0;
/*     */       };
/* 124 */     this.bottomup_fptr = new fptr() {
/*     */         public void rule() throws RecognitionException {
/* 126 */           TreeFilter.this.bottomup();
/*     */         }
/*     */         
/*     */         private final TreeFilter this$0;
/*     */       };
/*     */     this.originalAdaptor = input.getTreeAdaptor();
/*     */     this.originalTokenStream = input.getTokenStream();
/*     */   }
/*     */   
/*     */   public void applyOnce(Object t, fptr whichRule) {
/*     */     if (t == null)
/*     */       return; 
/*     */     try {
/*     */       this.state = new RecognizerSharedState();
/*     */       this.input = new CommonTreeNodeStream(this.originalAdaptor, t);
/*     */       ((CommonTreeNodeStream)this.input).setTokenStream(this.originalTokenStream);
/*     */       setBacktrackingLevel(1);
/*     */       whichRule.rule();
/*     */       setBacktrackingLevel(0);
/*     */     } catch (RecognitionException e) {}
/*     */   }
/*     */   
/*     */   public void downup(Object t) {
/*     */     TreeVisitor v = new TreeVisitor(new CommonTreeAdaptor());
/*     */     TreeVisitorAction actions = new TreeVisitorAction() {
/*     */         private final TreeFilter this$0;
/*     */         
/*     */         public Object pre(Object t) {
/*     */           TreeFilter.this.applyOnce(t, TreeFilter.this.topdown_fptr);
/*     */           return t;
/*     */         }
/*     */         
/*     */         public Object post(Object t) {
/*     */           TreeFilter.this.applyOnce(t, TreeFilter.this.bottomup_fptr);
/*     */           return t;
/*     */         }
/*     */       };
/*     */     v.visit(t, actions);
/*     */   }
/*     */   
/*     */   public void topdown() throws RecognitionException {}
/*     */   
/*     */   public void bottomup() throws RecognitionException {}
/*     */   
/*     */   public static interface fptr {
/*     */     void rule() throws RecognitionException;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeFilter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */