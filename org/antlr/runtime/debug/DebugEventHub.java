/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.antlr.runtime.RecognitionException;
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
/*     */ public class DebugEventHub
/*     */   implements DebugEventListener
/*     */ {
/*  43 */   protected List listeners = new ArrayList();
/*     */   
/*     */   public DebugEventHub(DebugEventListener listener) {
/*  46 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public DebugEventHub(DebugEventListener a, DebugEventListener b) {
/*  50 */     this.listeners.add(a);
/*  51 */     this.listeners.add(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(DebugEventListener listener) {
/*  58 */     this.listeners.add(listener);
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
/*     */   public void enterRule(String grammarFileName, String ruleName) {
/*  71 */     for (int i = 0; i < this.listeners.size(); i++) {
/*  72 */       DebugEventListener listener = this.listeners.get(i);
/*  73 */       listener.enterRule(grammarFileName, ruleName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void exitRule(String grammarFileName, String ruleName) {
/*  78 */     for (int i = 0; i < this.listeners.size(); i++) {
/*  79 */       DebugEventListener listener = this.listeners.get(i);
/*  80 */       listener.exitRule(grammarFileName, ruleName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enterAlt(int alt) {
/*  85 */     for (int i = 0; i < this.listeners.size(); i++) {
/*  86 */       DebugEventListener listener = this.listeners.get(i);
/*  87 */       listener.enterAlt(alt);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enterSubRule(int decisionNumber) {
/*  92 */     for (int i = 0; i < this.listeners.size(); i++) {
/*  93 */       DebugEventListener listener = this.listeners.get(i);
/*  94 */       listener.enterSubRule(decisionNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void exitSubRule(int decisionNumber) {
/*  99 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 100 */       DebugEventListener listener = this.listeners.get(i);
/* 101 */       listener.exitSubRule(decisionNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enterDecision(int decisionNumber) {
/* 106 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 107 */       DebugEventListener listener = this.listeners.get(i);
/* 108 */       listener.enterDecision(decisionNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void exitDecision(int decisionNumber) {
/* 113 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 114 */       DebugEventListener listener = this.listeners.get(i);
/* 115 */       listener.exitDecision(decisionNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void location(int line, int pos) {
/* 120 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 121 */       DebugEventListener listener = this.listeners.get(i);
/* 122 */       listener.location(line, pos);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void consumeToken(Token token) {
/* 127 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 128 */       DebugEventListener listener = this.listeners.get(i);
/* 129 */       listener.consumeToken(token);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void consumeHiddenToken(Token token) {
/* 134 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 135 */       DebugEventListener listener = this.listeners.get(i);
/* 136 */       listener.consumeHiddenToken(token);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void LT(int index, Token t) {
/* 141 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 142 */       DebugEventListener listener = this.listeners.get(i);
/* 143 */       listener.LT(index, t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mark(int index) {
/* 148 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 149 */       DebugEventListener listener = this.listeners.get(i);
/* 150 */       listener.mark(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rewind(int index) {
/* 155 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 156 */       DebugEventListener listener = this.listeners.get(i);
/* 157 */       listener.rewind(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 162 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 163 */       DebugEventListener listener = this.listeners.get(i);
/* 164 */       listener.rewind();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void beginBacktrack(int level) {
/* 169 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 170 */       DebugEventListener listener = this.listeners.get(i);
/* 171 */       listener.beginBacktrack(level);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endBacktrack(int level, boolean successful) {
/* 176 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 177 */       DebugEventListener listener = this.listeners.get(i);
/* 178 */       listener.endBacktrack(level, successful);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void recognitionException(RecognitionException e) {
/* 183 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 184 */       DebugEventListener listener = this.listeners.get(i);
/* 185 */       listener.recognitionException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void beginResync() {
/* 190 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 191 */       DebugEventListener listener = this.listeners.get(i);
/* 192 */       listener.beginResync();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endResync() {
/* 197 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 198 */       DebugEventListener listener = this.listeners.get(i);
/* 199 */       listener.endResync();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void semanticPredicate(boolean result, String predicate) {
/* 204 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 205 */       DebugEventListener listener = this.listeners.get(i);
/* 206 */       listener.semanticPredicate(result, predicate);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void commence() {
/* 211 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 212 */       DebugEventListener listener = this.listeners.get(i);
/* 213 */       listener.commence();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void terminate() {
/* 218 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 219 */       DebugEventListener listener = this.listeners.get(i);
/* 220 */       listener.terminate();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeNode(Object t) {
/* 228 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 229 */       DebugEventListener listener = this.listeners.get(i);
/* 230 */       listener.consumeNode(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void LT(int index, Object t) {
/* 235 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 236 */       DebugEventListener listener = this.listeners.get(i);
/* 237 */       listener.LT(index, t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nilNode(Object t) {
/* 245 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 246 */       DebugEventListener listener = this.listeners.get(i);
/* 247 */       listener.nilNode(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void errorNode(Object t) {
/* 252 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 253 */       DebugEventListener listener = this.listeners.get(i);
/* 254 */       listener.errorNode(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createNode(Object t) {
/* 259 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 260 */       DebugEventListener listener = this.listeners.get(i);
/* 261 */       listener.createNode(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createNode(Object node, Token token) {
/* 266 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 267 */       DebugEventListener listener = this.listeners.get(i);
/* 268 */       listener.createNode(node, token);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void becomeRoot(Object newRoot, Object oldRoot) {
/* 273 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 274 */       DebugEventListener listener = this.listeners.get(i);
/* 275 */       listener.becomeRoot(newRoot, oldRoot);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addChild(Object root, Object child) {
/* 280 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 281 */       DebugEventListener listener = this.listeners.get(i);
/* 282 */       listener.addChild(root, child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
/* 287 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 288 */       DebugEventListener listener = this.listeners.get(i);
/* 289 */       listener.setTokenBoundaries(t, tokenStartIndex, tokenStopIndex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugEventHub.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */