/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import org.antlr.runtime.CommonToken;
/*     */ import org.antlr.runtime.Token;
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
/*     */ public class CommonTreeAdaptor
/*     */   extends BaseTreeAdaptor
/*     */ {
/*     */   public Object dupNode(Object t) {
/*  51 */     if (t == null) return null; 
/*  52 */     return ((Tree)t).dupNode();
/*     */   }
/*     */   
/*     */   public Object create(Token payload) {
/*  56 */     return new CommonTree(payload);
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
/*     */   public Token createToken(int tokenType, String text) {
/*  68 */     return (Token)new CommonToken(tokenType, text);
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
/*     */   public Token createToken(Token fromToken) {
/*  86 */     return (Token)new CommonToken(fromToken);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTokenBoundaries(Object t, Token startToken, Token stopToken) {
/*  95 */     if (t == null)
/*  96 */       return;  int start = 0;
/*  97 */     int stop = 0;
/*  98 */     if (startToken != null) start = startToken.getTokenIndex(); 
/*  99 */     if (stopToken != null) stop = stopToken.getTokenIndex(); 
/* 100 */     ((Tree)t).setTokenStartIndex(start);
/* 101 */     ((Tree)t).setTokenStopIndex(stop);
/*     */   }
/*     */   
/*     */   public int getTokenStartIndex(Object t) {
/* 105 */     if (t == null) return -1; 
/* 106 */     return ((Tree)t).getTokenStartIndex();
/*     */   }
/*     */   
/*     */   public int getTokenStopIndex(Object t) {
/* 110 */     if (t == null) return -1; 
/* 111 */     return ((Tree)t).getTokenStopIndex();
/*     */   }
/*     */   
/*     */   public String getText(Object t) {
/* 115 */     if (t == null) return null; 
/* 116 */     return ((Tree)t).getText();
/*     */   }
/*     */   
/*     */   public int getType(Object t) {
/* 120 */     if (t == null) return 0; 
/* 121 */     return ((Tree)t).getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token getToken(Object t) {
/* 129 */     if (t instanceof CommonTree) {
/* 130 */       return ((CommonTree)t).getToken();
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public Object getChild(Object t, int i) {
/* 136 */     if (t == null) return null; 
/* 137 */     return ((Tree)t).getChild(i);
/*     */   }
/*     */   
/*     */   public int getChildCount(Object t) {
/* 141 */     if (t == null) return 0; 
/* 142 */     return ((Tree)t).getChildCount();
/*     */   }
/*     */   
/*     */   public Object getParent(Object t) {
/* 146 */     if (t == null) return null; 
/* 147 */     return ((Tree)t).getParent();
/*     */   }
/*     */   
/*     */   public void setParent(Object t, Object parent) {
/* 151 */     if (t != null) ((Tree)t).setParent((Tree)parent); 
/*     */   }
/*     */   
/*     */   public int getChildIndex(Object t) {
/* 155 */     if (t == null) return 0; 
/* 156 */     return ((Tree)t).getChildIndex();
/*     */   }
/*     */   
/*     */   public void setChildIndex(Object t, int index) {
/* 160 */     if (t != null) ((Tree)t).setChildIndex(index); 
/*     */   }
/*     */   
/*     */   public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
/* 164 */     if (parent != null)
/* 165 */       ((Tree)parent).replaceChildren(startChildIndex, stopChildIndex, t); 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\CommonTreeAdaptor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */