/*     */ package org.antlr.runtime.tree;
/*     */ 
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
/*     */ public class CommonTree
/*     */   extends BaseTree
/*     */ {
/*     */   public Token token;
/*  45 */   protected int startIndex = -1; protected int stopIndex = -1;
/*     */ 
/*     */   
/*     */   public CommonTree parent;
/*     */ 
/*     */   
/*  51 */   public int childIndex = -1;
/*     */   
/*     */   public CommonTree() {}
/*     */   
/*     */   public CommonTree(CommonTree node) {
/*  56 */     super(node);
/*  57 */     this.token = node.token;
/*  58 */     this.startIndex = node.startIndex;
/*  59 */     this.stopIndex = node.stopIndex;
/*     */   }
/*     */   
/*     */   public CommonTree(Token t) {
/*  63 */     this.token = t;
/*     */   }
/*     */   
/*     */   public Token getToken() {
/*  67 */     return this.token;
/*     */   }
/*     */   
/*     */   public Tree dupNode() {
/*  71 */     return new CommonTree(this);
/*     */   }
/*     */   
/*     */   public boolean isNil() {
/*  75 */     return (this.token == null);
/*     */   }
/*     */   
/*     */   public int getType() {
/*  79 */     if (this.token == null) {
/*  80 */       return 0;
/*     */     }
/*  82 */     return this.token.getType();
/*     */   }
/*     */   
/*     */   public String getText() {
/*  86 */     if (this.token == null) {
/*  87 */       return null;
/*     */     }
/*  89 */     return this.token.getText();
/*     */   }
/*     */   
/*     */   public int getLine() {
/*  93 */     if (this.token == null || this.token.getLine() == 0) {
/*  94 */       if (getChildCount() > 0) {
/*  95 */         return getChild(0).getLine();
/*     */       }
/*  97 */       return 0;
/*     */     } 
/*  99 */     return this.token.getLine();
/*     */   }
/*     */   
/*     */   public int getCharPositionInLine() {
/* 103 */     if (this.token == null || this.token.getCharPositionInLine() == -1) {
/* 104 */       if (getChildCount() > 0) {
/* 105 */         return getChild(0).getCharPositionInLine();
/*     */       }
/* 107 */       return 0;
/*     */     } 
/* 109 */     return this.token.getCharPositionInLine();
/*     */   }
/*     */   
/*     */   public int getTokenStartIndex() {
/* 113 */     if (this.startIndex == -1 && this.token != null) {
/* 114 */       return this.token.getTokenIndex();
/*     */     }
/* 116 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public void setTokenStartIndex(int index) {
/* 120 */     this.startIndex = index;
/*     */   }
/*     */   
/*     */   public int getTokenStopIndex() {
/* 124 */     if (this.stopIndex == -1 && this.token != null) {
/* 125 */       return this.token.getTokenIndex();
/*     */     }
/* 127 */     return this.stopIndex;
/*     */   }
/*     */   
/*     */   public void setTokenStopIndex(int index) {
/* 131 */     this.stopIndex = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnknownTokenBoundaries() {
/* 139 */     if (this.children == null) {
/* 140 */       if (this.startIndex < 0 || this.stopIndex < 0) {
/* 141 */         this.startIndex = this.stopIndex = this.token.getTokenIndex();
/*     */       }
/*     */       return;
/*     */     } 
/* 145 */     for (int i = 0; i < this.children.size(); i++) {
/* 146 */       ((CommonTree)this.children.get(i)).setUnknownTokenBoundaries();
/*     */     }
/* 148 */     if (this.startIndex >= 0 && this.stopIndex >= 0)
/* 149 */       return;  if (this.children.size() > 0) {
/* 150 */       CommonTree firstChild = this.children.get(0);
/* 151 */       CommonTree lastChild = this.children.get(this.children.size() - 1);
/* 152 */       this.startIndex = firstChild.getTokenStartIndex();
/* 153 */       this.stopIndex = lastChild.getTokenStopIndex();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getChildIndex() {
/* 158 */     return this.childIndex;
/*     */   }
/*     */   
/*     */   public Tree getParent() {
/* 162 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParent(Tree t) {
/* 166 */     this.parent = (CommonTree)t;
/*     */   }
/*     */   
/*     */   public void setChildIndex(int index) {
/* 170 */     this.childIndex = index;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 174 */     if (isNil()) {
/* 175 */       return "nil";
/*     */     }
/* 177 */     if (getType() == 0) {
/* 178 */       return "<errornode>";
/*     */     }
/* 180 */     if (this.token == null) {
/* 181 */       return null;
/*     */     }
/* 183 */     return this.token.getText();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\CommonTree.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */