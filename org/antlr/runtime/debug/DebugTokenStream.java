/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.TokenSource;
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
/*     */ public class DebugTokenStream
/*     */   implements TokenStream
/*     */ {
/*     */   protected DebugEventListener dbg;
/*     */   public TokenStream input;
/*     */   protected boolean initialStreamState = true;
/*     */   protected int lastMarker;
/*     */   
/*     */   public DebugTokenStream(TokenStream input, DebugEventListener dbg) {
/*  41 */     this.input = input;
/*  42 */     setDebugListener(dbg);
/*     */ 
/*     */     
/*  45 */     input.LT(1);
/*     */   }
/*     */   
/*     */   public void setDebugListener(DebugEventListener dbg) {
/*  49 */     this.dbg = dbg;
/*     */   }
/*     */   
/*     */   public void consume() {
/*  53 */     if (this.initialStreamState) {
/*  54 */       consumeInitialHiddenTokens();
/*     */     }
/*  56 */     int a = this.input.index();
/*  57 */     Token t = this.input.LT(1);
/*  58 */     this.input.consume();
/*  59 */     int b = this.input.index();
/*  60 */     this.dbg.consumeToken(t);
/*  61 */     if (b > a + 1)
/*     */     {
/*  63 */       for (int i = a + 1; i < b; i++) {
/*  64 */         this.dbg.consumeHiddenToken(this.input.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void consumeInitialHiddenTokens() {
/*  71 */     int firstOnChannelTokenIndex = this.input.index();
/*  72 */     for (int i = 0; i < firstOnChannelTokenIndex; i++) {
/*  73 */       this.dbg.consumeHiddenToken(this.input.get(i));
/*     */     }
/*  75 */     this.initialStreamState = false;
/*     */   }
/*     */   
/*     */   public Token LT(int i) {
/*  79 */     if (this.initialStreamState) {
/*  80 */       consumeInitialHiddenTokens();
/*     */     }
/*  82 */     this.dbg.LT(i, this.input.LT(i));
/*  83 */     return this.input.LT(i);
/*     */   }
/*     */   
/*     */   public int LA(int i) {
/*  87 */     if (this.initialStreamState) {
/*  88 */       consumeInitialHiddenTokens();
/*     */     }
/*  90 */     this.dbg.LT(i, this.input.LT(i));
/*  91 */     return this.input.LA(i);
/*     */   }
/*     */   
/*     */   public Token get(int i) {
/*  95 */     return this.input.get(i);
/*     */   }
/*     */   
/*     */   public int mark() {
/*  99 */     this.lastMarker = this.input.mark();
/* 100 */     this.dbg.mark(this.lastMarker);
/* 101 */     return this.lastMarker;
/*     */   }
/*     */   
/*     */   public int index() {
/* 105 */     return this.input.index();
/*     */   }
/*     */   
/*     */   public void rewind(int marker) {
/* 109 */     this.dbg.rewind(marker);
/* 110 */     this.input.rewind(marker);
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 114 */     this.dbg.rewind();
/* 115 */     this.input.rewind(this.lastMarker);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void release(int marker) {}
/*     */ 
/*     */   
/*     */   public void seek(int index) {
/* 124 */     this.input.seek(index);
/*     */   }
/*     */   
/*     */   public int size() {
/* 128 */     return this.input.size();
/*     */   }
/*     */   
/*     */   public TokenSource getTokenSource() {
/* 132 */     return this.input.getTokenSource();
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/* 136 */     return getTokenSource().getSourceName();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 140 */     return this.input.toString();
/*     */   }
/*     */   
/*     */   public String toString(int start, int stop) {
/* 144 */     return this.input.toString(start, stop);
/*     */   }
/*     */   
/*     */   public String toString(Token start, Token stop) {
/* 148 */     return this.input.toString(start, stop);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugTokenStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */