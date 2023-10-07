/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecognizerSharedState
/*     */ {
/*  43 */   public BitSet[] following = new BitSet[100];
/*  44 */   public int _fsp = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean errorRecovery = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public int lastErrorIndex = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean failed = false;
/*     */ 
/*     */ 
/*     */   
/*  66 */   public int syntaxErrors = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public int backtracking = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map[] ruleMemo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token token;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public int tokenStartCharIndex = -1;
/*     */ 
/*     */   
/*     */   public int tokenStartLine;
/*     */ 
/*     */   
/*     */   public int tokenStartCharPositionInLine;
/*     */ 
/*     */   
/*     */   public int channel;
/*     */ 
/*     */   
/*     */   public int type;
/*     */ 
/*     */   
/*     */   public String text;
/*     */ 
/*     */   
/*     */   public RecognizerSharedState() {}
/*     */ 
/*     */   
/*     */   public RecognizerSharedState(RecognizerSharedState state) {
/* 123 */     if (this.following.length < state.following.length) {
/* 124 */       this.following = new BitSet[state.following.length];
/*     */     }
/* 126 */     System.arraycopy(state.following, 0, this.following, 0, state.following.length);
/* 127 */     this._fsp = state._fsp;
/* 128 */     this.errorRecovery = state.errorRecovery;
/* 129 */     this.lastErrorIndex = state.lastErrorIndex;
/* 130 */     this.failed = state.failed;
/* 131 */     this.syntaxErrors = state.syntaxErrors;
/* 132 */     this.backtracking = state.backtracking;
/* 133 */     if (state.ruleMemo != null) {
/* 134 */       this.ruleMemo = new Map[state.ruleMemo.length];
/* 135 */       System.arraycopy(state.ruleMemo, 0, this.ruleMemo, 0, state.ruleMemo.length);
/*     */     } 
/* 137 */     this.token = state.token;
/* 138 */     this.tokenStartCharIndex = state.tokenStartCharIndex;
/* 139 */     this.tokenStartCharPositionInLine = state.tokenStartCharPositionInLine;
/* 140 */     this.channel = state.channel;
/* 141 */     this.type = state.type;
/* 142 */     this.text = state.text;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\RecognizerSharedState.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */