/*     */ package org.antlr.runtime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Lexer
/*     */   extends BaseRecognizer
/*     */   implements TokenSource
/*     */ {
/*     */   protected CharStream input;
/*     */   
/*     */   public Lexer() {}
/*     */   
/*     */   public Lexer(CharStream input) {
/*  43 */     this.input = input;
/*     */   }
/*     */   
/*     */   public Lexer(CharStream input, RecognizerSharedState state) {
/*  47 */     super(state);
/*  48 */     this.input = input;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  52 */     super.reset();
/*     */     
/*  54 */     if (this.input != null) {
/*  55 */       this.input.seek(0);
/*     */     }
/*  57 */     if (this.state == null) {
/*     */       return;
/*     */     }
/*  60 */     this.state.token = null;
/*  61 */     this.state.type = 0;
/*  62 */     this.state.channel = 0;
/*  63 */     this.state.tokenStartCharIndex = -1;
/*  64 */     this.state.tokenStartCharPositionInLine = -1;
/*  65 */     this.state.tokenStartLine = -1;
/*  66 */     this.state.text = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token nextToken() {
/*     */     while (true) {
/*  74 */       this.state.token = null;
/*  75 */       this.state.channel = 0;
/*  76 */       this.state.tokenStartCharIndex = this.input.index();
/*  77 */       this.state.tokenStartCharPositionInLine = this.input.getCharPositionInLine();
/*  78 */       this.state.tokenStartLine = this.input.getLine();
/*  79 */       this.state.text = null;
/*  80 */       if (this.input.LA(1) == -1) {
/*  81 */         return Token.EOF_TOKEN;
/*     */       }
/*     */       try {
/*  84 */         mTokens();
/*  85 */         if (this.state.token == null) {
/*  86 */           emit();
/*     */         }
/*  88 */         else if (this.state.token == Token.SKIP_TOKEN) {
/*     */           continue;
/*     */         } 
/*  91 */         return this.state.token;
/*     */       }
/*  93 */       catch (NoViableAltException nva) {
/*  94 */         reportError(nva);
/*  95 */         recover(nva);
/*     */       }
/*  97 */       catch (RecognitionException re) {
/*  98 */         reportError(re);
/*     */       } 
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
/*     */   public void skip() {
/* 111 */     this.state.token = Token.SKIP_TOKEN;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void mTokens() throws RecognitionException;
/*     */ 
/*     */   
/*     */   public void setCharStream(CharStream input) {
/* 119 */     this.input = null;
/* 120 */     reset();
/* 121 */     this.input = input;
/*     */   }
/*     */   
/*     */   public CharStream getCharStream() {
/* 125 */     return this.input;
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/* 129 */     return this.input.getSourceName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void emit(Token token) {
/* 138 */     this.state.token = token;
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
/*     */   public Token emit() {
/* 151 */     Token t = new CommonToken(this.input, this.state.type, this.state.channel, this.state.tokenStartCharIndex, getCharIndex() - 1);
/* 152 */     t.setLine(this.state.tokenStartLine);
/* 153 */     t.setText(this.state.text);
/* 154 */     t.setCharPositionInLine(this.state.tokenStartCharPositionInLine);
/* 155 */     emit(t);
/* 156 */     return t;
/*     */   }
/*     */   
/*     */   public void match(String s) throws MismatchedTokenException {
/* 160 */     int i = 0;
/* 161 */     while (i < s.length()) {
/* 162 */       if (this.input.LA(1) != s.charAt(i)) {
/* 163 */         if (this.state.backtracking > 0) {
/* 164 */           this.state.failed = true;
/*     */           return;
/*     */         } 
/* 167 */         MismatchedTokenException mte = new MismatchedTokenException(s.charAt(i), this.input);
/*     */         
/* 169 */         recover(mte);
/* 170 */         throw mte;
/*     */       } 
/* 172 */       i++;
/* 173 */       this.input.consume();
/* 174 */       this.state.failed = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void matchAny() {
/* 179 */     this.input.consume();
/*     */   }
/*     */   
/*     */   public void match(int c) throws MismatchedTokenException {
/* 183 */     if (this.input.LA(1) != c) {
/* 184 */       if (this.state.backtracking > 0) {
/* 185 */         this.state.failed = true;
/*     */         return;
/*     */       } 
/* 188 */       MismatchedTokenException mte = new MismatchedTokenException(c, this.input);
/*     */       
/* 190 */       recover(mte);
/* 191 */       throw mte;
/*     */     } 
/* 193 */     this.input.consume();
/* 194 */     this.state.failed = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void matchRange(int a, int b) throws MismatchedRangeException {
/* 200 */     if (this.input.LA(1) < a || this.input.LA(1) > b) {
/* 201 */       if (this.state.backtracking > 0) {
/* 202 */         this.state.failed = true;
/*     */         return;
/*     */       } 
/* 205 */       MismatchedRangeException mre = new MismatchedRangeException(a, b, this.input);
/*     */       
/* 207 */       recover(mre);
/* 208 */       throw mre;
/*     */     } 
/* 210 */     this.input.consume();
/* 211 */     this.state.failed = false;
/*     */   }
/*     */   
/*     */   public int getLine() {
/* 215 */     return this.input.getLine();
/*     */   }
/*     */   
/*     */   public int getCharPositionInLine() {
/* 219 */     return this.input.getCharPositionInLine();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCharIndex() {
/* 224 */     return this.input.index();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 231 */     if (this.state.text != null) {
/* 232 */       return this.state.text;
/*     */     }
/* 234 */     return this.input.substring(this.state.tokenStartCharIndex, getCharIndex() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 241 */     this.state.text = text;
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
/*     */   public void reportError(RecognitionException e) {
/* 256 */     displayRecognitionError(getTokenNames(), e);
/*     */   }
/*     */   
/*     */   public String getErrorMessage(RecognitionException e, String[] tokenNames) {
/* 260 */     String msg = null;
/* 261 */     if (e instanceof MismatchedTokenException) {
/* 262 */       MismatchedTokenException mte = (MismatchedTokenException)e;
/* 263 */       msg = "mismatched character " + getCharErrorDisplay(e.c) + " expecting " + getCharErrorDisplay(mte.expecting);
/*     */     }
/* 265 */     else if (e instanceof NoViableAltException) {
/* 266 */       NoViableAltException nvae = (NoViableAltException)e;
/*     */ 
/*     */ 
/*     */       
/* 270 */       msg = "no viable alternative at character " + getCharErrorDisplay(e.c);
/*     */     }
/* 272 */     else if (e instanceof EarlyExitException) {
/* 273 */       EarlyExitException eee = (EarlyExitException)e;
/*     */       
/* 275 */       msg = "required (...)+ loop did not match anything at character " + getCharErrorDisplay(e.c);
/*     */     }
/* 277 */     else if (e instanceof MismatchedNotSetException) {
/* 278 */       MismatchedNotSetException mse = (MismatchedNotSetException)e;
/* 279 */       msg = "mismatched character " + getCharErrorDisplay(e.c) + " expecting set " + mse.expecting;
/*     */     }
/* 281 */     else if (e instanceof MismatchedSetException) {
/* 282 */       MismatchedSetException mse = (MismatchedSetException)e;
/* 283 */       msg = "mismatched character " + getCharErrorDisplay(e.c) + " expecting set " + mse.expecting;
/*     */     }
/* 285 */     else if (e instanceof MismatchedRangeException) {
/* 286 */       MismatchedRangeException mre = (MismatchedRangeException)e;
/* 287 */       msg = "mismatched character " + getCharErrorDisplay(e.c) + " expecting set " + getCharErrorDisplay(mre.a) + ".." + getCharErrorDisplay(mre.b);
/*     */     }
/*     */     else {
/*     */       
/* 291 */       msg = super.getErrorMessage(e, tokenNames);
/*     */     } 
/* 293 */     return msg;
/*     */   }
/*     */   
/*     */   public String getCharErrorDisplay(int c) {
/* 297 */     String s = String.valueOf((char)c);
/* 298 */     switch (c) {
/*     */       case -1:
/* 300 */         s = "<EOF>";
/*     */         break;
/*     */       case 10:
/* 303 */         s = "\\n";
/*     */         break;
/*     */       case 9:
/* 306 */         s = "\\t";
/*     */         break;
/*     */       case 13:
/* 309 */         s = "\\r";
/*     */         break;
/*     */     } 
/* 312 */     return "'" + s + "'";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recover(RecognitionException re) {
/* 323 */     this.input.consume();
/*     */   }
/*     */   
/*     */   public void traceIn(String ruleName, int ruleIndex) {
/* 327 */     String inputSymbol = (char)this.input.LT(1) + " line=" + getLine() + ":" + getCharPositionInLine();
/* 328 */     traceIn(ruleName, ruleIndex, inputSymbol);
/*     */   }
/*     */   
/*     */   public void traceOut(String ruleName, int ruleIndex) {
/* 332 */     String inputSymbol = (char)this.input.LT(1) + " line=" + getLine() + ":" + getCharPositionInLine();
/* 333 */     traceOut(ruleName, ruleIndex, inputSymbol);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\Lexer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */