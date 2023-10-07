/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class ParseTree
/*     */   extends BaseTree
/*     */ {
/*     */   public Object payload;
/*     */   public List hiddenTokens;
/*     */   
/*     */   public ParseTree(Object label) {
/*  44 */     this.payload = label;
/*     */   }
/*     */   
/*     */   public Tree dupNode() {
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  52 */     return 0;
/*     */   }
/*     */   
/*     */   public String getText() {
/*  56 */     return toString();
/*     */   }
/*     */   
/*     */   public int getTokenStartIndex() {
/*  60 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTokenStartIndex(int index) {}
/*     */   
/*     */   public int getTokenStopIndex() {
/*  67 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTokenStopIndex(int index) {}
/*     */   
/*     */   public String toString() {
/*  74 */     if (this.payload instanceof Token) {
/*  75 */       Token t = (Token)this.payload;
/*  76 */       if (t.getType() == -1) {
/*  77 */         return "<EOF>";
/*     */       }
/*  79 */       return t.getText();
/*     */     } 
/*  81 */     return this.payload.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringWithHiddenTokens() {
/*  88 */     StringBuffer buf = new StringBuffer();
/*  89 */     if (this.hiddenTokens != null) {
/*  90 */       for (int i = 0; i < this.hiddenTokens.size(); i++) {
/*  91 */         Token hidden = this.hiddenTokens.get(i);
/*  92 */         buf.append(hidden.getText());
/*     */       } 
/*     */     }
/*  95 */     String nodeText = toString();
/*  96 */     if (!nodeText.equals("<EOF>")) buf.append(nodeText); 
/*  97 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toInputString() {
/* 104 */     StringBuffer buf = new StringBuffer();
/* 105 */     _toStringLeaves(buf);
/* 106 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void _toStringLeaves(StringBuffer buf) {
/* 110 */     if (this.payload instanceof Token) {
/* 111 */       buf.append(toStringWithHiddenTokens());
/*     */       return;
/*     */     } 
/* 114 */     for (int i = 0; this.children != null && i < this.children.size(); i++) {
/* 115 */       ParseTree t = this.children.get(i);
/* 116 */       t._toStringLeaves(buf);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\ParseTree.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */