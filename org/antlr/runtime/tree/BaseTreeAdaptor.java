/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
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
/*     */ public abstract class BaseTreeAdaptor
/*     */   implements TreeAdaptor
/*     */ {
/*     */   protected Map treeToUniqueIDMap;
/*  44 */   protected int uniqueNodeID = 1;
/*     */   
/*     */   public Object nil() {
/*  47 */     return create(null);
/*     */   }
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
/*     */   public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
/*  64 */     CommonErrorNode t = new CommonErrorNode(input, start, stop, e);
/*     */     
/*  66 */     return t;
/*     */   }
/*     */   
/*     */   public boolean isNil(Object tree) {
/*  70 */     return ((Tree)tree).isNil();
/*     */   }
/*     */   
/*     */   public Object dupTree(Object tree) {
/*  74 */     return dupTree(tree, (Object)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object dupTree(Object t, Object parent) {
/*  82 */     if (t == null) {
/*  83 */       return null;
/*     */     }
/*  85 */     Object newTree = dupNode(t);
/*     */     
/*  87 */     setChildIndex(newTree, getChildIndex(t));
/*  88 */     setParent(newTree, parent);
/*  89 */     int n = getChildCount(t);
/*  90 */     for (int i = 0; i < n; i++) {
/*  91 */       Object child = getChild(t, i);
/*  92 */       Object newSubTree = dupTree(child, t);
/*  93 */       addChild(newTree, newSubTree);
/*     */     } 
/*  95 */     return newTree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChild(Object t, Object child) {
/* 106 */     if (t != null && child != null) {
/* 107 */       ((Tree)t).addChild((Tree)child);
/*     */     }
/*     */   }
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
/*     */   public Object becomeRoot(Object newRoot, Object oldRoot) {
/* 139 */     Tree newRootTree = (Tree)newRoot;
/* 140 */     Tree oldRootTree = (Tree)oldRoot;
/* 141 */     if (oldRoot == null) {
/* 142 */       return newRoot;
/*     */     }
/*     */     
/* 145 */     if (newRootTree.isNil()) {
/* 146 */       int nc = newRootTree.getChildCount();
/* 147 */       if (nc == 1) { newRootTree = newRootTree.getChild(0); }
/* 148 */       else if (nc > 1)
/*     */       
/* 150 */       { throw new RuntimeException("more than one node as root (TODO: make exception hierarchy)"); }
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     newRootTree.addChild(oldRootTree);
/* 157 */     return newRootTree;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object rulePostProcessing(Object root) {
/* 163 */     Tree r = (Tree)root;
/* 164 */     if (r != null && r.isNil()) {
/* 165 */       if (r.getChildCount() == 0) {
/* 166 */         r = null;
/*     */       }
/* 168 */       else if (r.getChildCount() == 1) {
/* 169 */         r = r.getChild(0);
/*     */         
/* 171 */         r.setParent(null);
/* 172 */         r.setChildIndex(-1);
/*     */       } 
/*     */     }
/* 175 */     return r;
/*     */   }
/*     */   
/*     */   public Object becomeRoot(Token newRoot, Object oldRoot) {
/* 179 */     return becomeRoot(create(newRoot), oldRoot);
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, Token fromToken) {
/* 183 */     fromToken = createToken(fromToken);
/*     */     
/* 185 */     fromToken.setType(tokenType);
/* 186 */     Tree t = (Tree)create(fromToken);
/* 187 */     return t;
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, Token fromToken, String text) {
/* 191 */     fromToken = createToken(fromToken);
/* 192 */     fromToken.setType(tokenType);
/* 193 */     fromToken.setText(text);
/* 194 */     Tree t = (Tree)create(fromToken);
/* 195 */     return t;
/*     */   }
/*     */   
/*     */   public Object create(int tokenType, String text) {
/* 199 */     Token fromToken = createToken(tokenType, text);
/* 200 */     Tree t = (Tree)create(fromToken);
/* 201 */     return t;
/*     */   }
/*     */   
/*     */   public int getType(Object t) {
/* 205 */     return ((Tree)t).getType();
/*     */   }
/*     */   
/*     */   public void setType(Object t, int type) {
/* 209 */     throw new NoSuchMethodError("don't know enough about Tree node");
/*     */   }
/*     */   
/*     */   public String getText(Object t) {
/* 213 */     return ((Tree)t).getText();
/*     */   }
/*     */   
/*     */   public void setText(Object t, String text) {
/* 217 */     throw new NoSuchMethodError("don't know enough about Tree node");
/*     */   }
/*     */   
/*     */   public Object getChild(Object t, int i) {
/* 221 */     return ((Tree)t).getChild(i);
/*     */   }
/*     */   
/*     */   public void setChild(Object t, int i, Object child) {
/* 225 */     ((Tree)t).setChild(i, (Tree)child);
/*     */   }
/*     */   
/*     */   public Object deleteChild(Object t, int i) {
/* 229 */     return ((Tree)t).deleteChild(i);
/*     */   }
/*     */   
/*     */   public int getChildCount(Object t) {
/* 233 */     return ((Tree)t).getChildCount();
/*     */   }
/*     */   
/*     */   public int getUniqueID(Object node) {
/* 237 */     if (this.treeToUniqueIDMap == null) {
/* 238 */       this.treeToUniqueIDMap = new HashMap();
/*     */     }
/* 240 */     Integer prevID = (Integer)this.treeToUniqueIDMap.get(node);
/* 241 */     if (prevID != null) {
/* 242 */       return prevID.intValue();
/*     */     }
/* 244 */     int ID = this.uniqueNodeID;
/* 245 */     this.treeToUniqueIDMap.put(node, new Integer(ID));
/* 246 */     this.uniqueNodeID++;
/* 247 */     return ID;
/*     */   }
/*     */   
/*     */   public abstract Token createToken(int paramInt, String paramString);
/*     */   
/*     */   public abstract Token createToken(Token paramToken);
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\BaseTreeAdaptor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */