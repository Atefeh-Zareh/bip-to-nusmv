/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.tree.TreeAdaptor;
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
/*     */ public class DebugTreeAdaptor
/*     */   implements TreeAdaptor
/*     */ {
/*     */   protected DebugEventListener dbg;
/*     */   protected TreeAdaptor adaptor;
/*     */   
/*     */   public DebugTreeAdaptor(DebugEventListener dbg, TreeAdaptor adaptor) {
/*  52 */     this.dbg = dbg;
/*  53 */     this.adaptor = adaptor;
/*     */   }
/*     */   
/*     */   public Object create(Token payload) {
/*  57 */     if (payload.getTokenIndex() < 0)
/*     */     {
/*  59 */       return create(payload.getType(), payload.getText());
/*     */     }
/*  61 */     Object node = this.adaptor.create(payload);
/*  62 */     this.dbg.createNode(node, payload);
/*  63 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
/*  69 */     Object node = this.adaptor.errorNode(input, start, stop, e);
/*  70 */     if (node != null) {
/*  71 */       this.dbg.errorNode(node);
/*     */     }
/*  73 */     return node;
/*     */   }
/*     */   
/*     */   public Object dupTree(Object tree) {
/*  77 */     Object t = this.adaptor.dupTree(tree);
/*     */ 
/*     */ 
/*     */     
/*  81 */     simulateTreeConstruction(t);
/*  82 */     return t;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void simulateTreeConstruction(Object t) {
/*  87 */     this.dbg.createNode(t);
/*  88 */     int n = this.adaptor.getChildCount(t);
/*  89 */     for (int i = 0; i < n; i++) {
/*  90 */       Object child = this.adaptor.getChild(t, i);
/*  91 */       simulateTreeConstruction(child);
/*  92 */       this.dbg.addChild(t, child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object dupNode(Object treeNode) {
/*  97 */     Object d = this.adaptor.dupNode(treeNode);
/*  98 */     this.dbg.createNode(d);
/*  99 */     return d;
/*     */   }
/*     */   
/*     */   public Object nil() {
/* 103 */     Object node = this.adaptor.nil();
/* 104 */     this.dbg.nilNode(node);
/* 105 */     return node;
/*     */   }
/*     */   
/*     */   public boolean isNil(Object tree) {
/* 109 */     return this.adaptor.isNil(tree);
/*     */   }
/*     */   
/*     */   public void addChild(Object t, Object child) {
/* 113 */     if (t == null || child == null) {
/*     */       return;
/*     */     }
/* 116 */     this.adaptor.addChild(t, child);
/* 117 */     this.dbg.addChild(t, child);
/*     */   }
/*     */   
/*     */   public Object becomeRoot(Object newRoot, Object oldRoot) {
/* 121 */     Object n = this.adaptor.becomeRoot(newRoot, oldRoot);
/* 122 */     this.dbg.becomeRoot(newRoot, oldRoot);
/* 123 */     return n;
/*     */   }
/*     */   
/*     */   public Object rulePostProcessing(Object root) {
/* 127 */     return this.adaptor.rulePostProcessing(root);
/*     */   }
/*     */   
/*     */   public void addChild(Object t, Token child) {
/* 131 */     Object n = create(child);
/* 132 */     addChild(t, n);
/*     */   }
/*     */   
/*     */   public Object becomeRoot(Token newRoot, Object oldRoot) {
/* 136 */     Object n = create(newRoot);
/* 137 */     this.adaptor.becomeRoot(n, oldRoot);
/* 138 */     this.dbg.becomeRoot(newRoot, oldRoot);
/* 139 */     return n;
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, Token fromToken) {
/* 143 */     Object node = this.adaptor.create(tokenType, fromToken);
/* 144 */     this.dbg.createNode(node);
/* 145 */     return node;
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, Token fromToken, String text) {
/* 149 */     Object node = this.adaptor.create(tokenType, fromToken, text);
/* 150 */     this.dbg.createNode(node);
/* 151 */     return node;
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, String text) {
/* 155 */     Object node = this.adaptor.create(tokenType, text);
/* 156 */     this.dbg.createNode(node);
/* 157 */     return node;
/*     */   }
/*     */   
/*     */   public int getType(Object t) {
/* 161 */     return this.adaptor.getType(t);
/*     */   }
/*     */   
/*     */   public void setType(Object t, int type) {
/* 165 */     this.adaptor.setType(t, type);
/*     */   }
/*     */   
/*     */   public String getText(Object t) {
/* 169 */     return this.adaptor.getText(t);
/*     */   }
/*     */   
/*     */   public void setText(Object t, String text) {
/* 173 */     this.adaptor.setText(t, text);
/*     */   }
/*     */   
/*     */   public Token getToken(Object t) {
/* 177 */     return this.adaptor.getToken(t);
/*     */   }
/*     */   
/*     */   public void setTokenBoundaries(Object t, Token startToken, Token stopToken) {
/* 181 */     this.adaptor.setTokenBoundaries(t, startToken, stopToken);
/* 182 */     if (t != null && startToken != null && stopToken != null) {
/* 183 */       this.dbg.setTokenBoundaries(t, startToken.getTokenIndex(), stopToken.getTokenIndex());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTokenStartIndex(Object t) {
/* 190 */     return this.adaptor.getTokenStartIndex(t);
/*     */   }
/*     */   
/*     */   public int getTokenStopIndex(Object t) {
/* 194 */     return this.adaptor.getTokenStopIndex(t);
/*     */   }
/*     */   
/*     */   public Object getChild(Object t, int i) {
/* 198 */     return this.adaptor.getChild(t, i);
/*     */   }
/*     */   
/*     */   public void setChild(Object t, int i, Object child) {
/* 202 */     this.adaptor.setChild(t, i, child);
/*     */   }
/*     */   
/*     */   public Object deleteChild(Object t, int i) {
/* 206 */     return deleteChild(t, i);
/*     */   }
/*     */   
/*     */   public int getChildCount(Object t) {
/* 210 */     return this.adaptor.getChildCount(t);
/*     */   }
/*     */   
/*     */   public int getUniqueID(Object node) {
/* 214 */     return this.adaptor.getUniqueID(node);
/*     */   }
/*     */   
/*     */   public Object getParent(Object t) {
/* 218 */     return this.adaptor.getParent(t);
/*     */   }
/*     */   
/*     */   public int getChildIndex(Object t) {
/* 222 */     return this.adaptor.getChildIndex(t);
/*     */   }
/*     */   
/*     */   public void setParent(Object t, Object parent) {
/* 226 */     this.adaptor.setParent(t, parent);
/*     */   }
/*     */   
/*     */   public void setChildIndex(Object t, int index) {
/* 230 */     this.adaptor.setChildIndex(t, index);
/*     */   }
/*     */   
/*     */   public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
/* 234 */     this.adaptor.replaceChildren(parent, startChildIndex, stopChildIndex, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DebugEventListener getDebugListener() {
/* 240 */     return this.dbg;
/*     */   }
/*     */   
/*     */   public void setDebugListener(DebugEventListener dbg) {
/* 244 */     this.dbg = dbg;
/*     */   }
/*     */   
/*     */   public TreeAdaptor getTreeAdaptor() {
/* 248 */     return this.adaptor;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugTreeAdaptor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */