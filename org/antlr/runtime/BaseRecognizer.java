/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseRecognizer
/*     */ {
/*     */   public static final int MEMO_RULE_FAILED = -2;
/*     */   public static final int MEMO_RULE_UNKNOWN = -1;
/*     */   public static final int INITIAL_FOLLOW_STACK_SIZE = 100;
/*     */   public static final int DEFAULT_TOKEN_CHANNEL = 0;
/*     */   public static final int HIDDEN = 99;
/*     */   public static final String NEXT_TOKEN_RULE_NAME = "nextToken";
/*     */   protected RecognizerSharedState state;
/*     */   
/*     */   public BaseRecognizer() {
/*  60 */     this.state = new RecognizerSharedState();
/*     */   }
/*     */   
/*     */   public BaseRecognizer(RecognizerSharedState state) {
/*  64 */     if (state == null) {
/*  65 */       state = new RecognizerSharedState();
/*     */     }
/*  67 */     this.state = state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  73 */     if (this.state == null) {
/*     */       return;
/*     */     }
/*  76 */     this.state._fsp = -1;
/*  77 */     this.state.errorRecovery = false;
/*  78 */     this.state.lastErrorIndex = -1;
/*  79 */     this.state.failed = false;
/*  80 */     this.state.syntaxErrors = 0;
/*     */     
/*  82 */     this.state.backtracking = 0;
/*  83 */     for (int i = 0; this.state.ruleMemo != null && i < this.state.ruleMemo.length; i++) {
/*  84 */       this.state.ruleMemo[i] = null;
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
/*     */   public Object match(IntStream input, int ttype, BitSet follow) throws RecognitionException {
/* 104 */     Object matchedSymbol = getCurrentInputSymbol(input);
/* 105 */     if (input.LA(1) == ttype) {
/* 106 */       input.consume();
/* 107 */       this.state.errorRecovery = false;
/* 108 */       this.state.failed = false;
/* 109 */       return matchedSymbol;
/*     */     } 
/* 111 */     if (this.state.backtracking > 0) {
/* 112 */       this.state.failed = true;
/* 113 */       return matchedSymbol;
/*     */     } 
/* 115 */     matchedSymbol = recoverFromMismatchedToken(input, ttype, follow);
/* 116 */     return matchedSymbol;
/*     */   }
/*     */ 
/*     */   
/*     */   public void matchAny(IntStream input) {
/* 121 */     this.state.errorRecovery = false;
/* 122 */     this.state.failed = false;
/* 123 */     input.consume();
/*     */   }
/*     */   
/*     */   public boolean mismatchIsUnwantedToken(IntStream input, int ttype) {
/* 127 */     return (input.LA(2) == ttype);
/*     */   }
/*     */   
/*     */   public boolean mismatchIsMissingToken(IntStream input, BitSet follow) {
/* 131 */     if (follow == null)
/*     */     {
/*     */       
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     if (follow.member(1)) {
/* 138 */       BitSet viableTokensFollowingThisRule = computeContextSensitiveRuleFOLLOW();
/* 139 */       follow = follow.or(viableTokensFollowingThisRule);
/* 140 */       if (this.state._fsp >= 0) {
/* 141 */         follow.remove(1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (follow.member(input.LA(1)) || follow.member(1))
/*     */     {
/* 156 */       return true;
/*     */     }
/* 158 */     return false;
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
/*     */   public void reportError(RecognitionException e) {
/* 179 */     if (this.state.errorRecovery) {
/*     */       return;
/*     */     }
/*     */     
/* 183 */     this.state.syntaxErrors++;
/* 184 */     this.state.errorRecovery = true;
/*     */     
/* 186 */     displayRecognitionError(getTokenNames(), e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
/* 192 */     String hdr = getErrorHeader(e);
/* 193 */     String msg = getErrorMessage(e, tokenNames);
/* 194 */     emitErrorMessage(hdr + " " + msg);
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
/*     */   public String getErrorMessage(RecognitionException e, String[] tokenNames) {
/* 220 */     String msg = e.getMessage();
/* 221 */     if (e instanceof UnwantedTokenException) {
/* 222 */       UnwantedTokenException ute = (UnwantedTokenException)e;
/* 223 */       String tokenName = "<unknown>";
/* 224 */       if (ute.expecting == -1) {
/* 225 */         tokenName = "EOF";
/*     */       } else {
/*     */         
/* 228 */         tokenName = tokenNames[ute.expecting];
/*     */       } 
/* 230 */       msg = "extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken()) + " expecting " + tokenName;
/*     */     
/*     */     }
/* 233 */     else if (e instanceof MissingTokenException) {
/* 234 */       MissingTokenException mte = (MissingTokenException)e;
/* 235 */       String tokenName = "<unknown>";
/* 236 */       if (mte.expecting == -1) {
/* 237 */         tokenName = "EOF";
/*     */       } else {
/*     */         
/* 240 */         tokenName = tokenNames[mte.expecting];
/*     */       } 
/* 242 */       msg = "missing " + tokenName + " at " + getTokenErrorDisplay(e.token);
/*     */     }
/* 244 */     else if (e instanceof MismatchedTokenException) {
/* 245 */       MismatchedTokenException mte = (MismatchedTokenException)e;
/* 246 */       String tokenName = "<unknown>";
/* 247 */       if (mte.expecting == -1) {
/* 248 */         tokenName = "EOF";
/*     */       } else {
/*     */         
/* 251 */         tokenName = tokenNames[mte.expecting];
/*     */       } 
/* 253 */       msg = "mismatched input " + getTokenErrorDisplay(e.token) + " expecting " + tokenName;
/*     */     
/*     */     }
/* 256 */     else if (e instanceof MismatchedTreeNodeException) {
/* 257 */       MismatchedTreeNodeException mtne = (MismatchedTreeNodeException)e;
/* 258 */       String tokenName = "<unknown>";
/* 259 */       if (mtne.expecting == -1) {
/* 260 */         tokenName = "EOF";
/*     */       } else {
/*     */         
/* 263 */         tokenName = tokenNames[mtne.expecting];
/*     */       } 
/* 265 */       msg = "mismatched tree node: " + mtne.node + " expecting " + tokenName;
/*     */     
/*     */     }
/* 268 */     else if (e instanceof NoViableAltException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       msg = "no viable alternative at input " + getTokenErrorDisplay(e.token);
/*     */     }
/* 275 */     else if (e instanceof EarlyExitException) {
/*     */ 
/*     */       
/* 278 */       msg = "required (...)+ loop did not match anything at input " + getTokenErrorDisplay(e.token);
/*     */     
/*     */     }
/* 281 */     else if (e instanceof MismatchedSetException) {
/* 282 */       MismatchedSetException mse = (MismatchedSetException)e;
/* 283 */       msg = "mismatched input " + getTokenErrorDisplay(e.token) + " expecting set " + mse.expecting;
/*     */     
/*     */     }
/* 286 */     else if (e instanceof MismatchedNotSetException) {
/* 287 */       MismatchedNotSetException mse = (MismatchedNotSetException)e;
/* 288 */       msg = "mismatched input " + getTokenErrorDisplay(e.token) + " expecting set " + mse.expecting;
/*     */     
/*     */     }
/* 291 */     else if (e instanceof FailedPredicateException) {
/* 292 */       FailedPredicateException fpe = (FailedPredicateException)e;
/* 293 */       msg = "rule " + fpe.ruleName + " failed predicate: {" + fpe.predicateText + "}?";
/*     */     } 
/*     */     
/* 296 */     return msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSyntaxErrors() {
/* 307 */     return this.state.syntaxErrors;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getErrorHeader(RecognitionException e) {
/* 312 */     return "line " + e.line + ":" + e.charPositionInLine;
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
/*     */   public String getTokenErrorDisplay(Token t) {
/* 324 */     String s = t.getText();
/* 325 */     if (s == null) {
/* 326 */       if (t.getType() == -1) {
/* 327 */         s = "<EOF>";
/*     */       } else {
/*     */         
/* 330 */         s = "<" + t.getType() + ">";
/*     */       } 
/*     */     }
/* 333 */     s = s.replaceAll("\n", "\\\\n");
/* 334 */     s = s.replaceAll("\r", "\\\\r");
/* 335 */     s = s.replaceAll("\t", "\\\\t");
/* 336 */     return "'" + s + "'";
/*     */   }
/*     */ 
/*     */   
/*     */   public void emitErrorMessage(String msg) {
/* 341 */     System.err.println(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recover(IntStream input, RecognitionException re) {
/* 351 */     if (this.state.lastErrorIndex == input.index())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 356 */       input.consume();
/*     */     }
/* 358 */     this.state.lastErrorIndex = input.index();
/* 359 */     BitSet followSet = computeErrorRecoverySet();
/* 360 */     beginResync();
/* 361 */     consumeUntil(input, followSet);
/* 362 */     endResync();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginResync() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endResync() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BitSet computeErrorRecoverySet() {
/* 466 */     return combineFollows(false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BitSet computeContextSensitiveRuleFOLLOW() {
/* 522 */     return combineFollows(true);
/*     */   }
/*     */   
/*     */   protected BitSet combineFollows(boolean exact) {
/* 526 */     int top = this.state._fsp;
/* 527 */     BitSet followSet = new BitSet();
/* 528 */     for (int i = top; i >= 0; i--) {
/* 529 */       BitSet localFollowSet = this.state.following[i];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 534 */       followSet.orInPlace(localFollowSet);
/* 535 */       if (exact)
/*     */       {
/* 537 */         if (localFollowSet.member(1)) {
/*     */ 
/*     */           
/* 540 */           if (i > 0) {
/* 541 */             followSet.remove(1);
/*     */           }
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 549 */     return followSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
/* 584 */     RecognitionException e = null;
/*     */     
/* 586 */     if (mismatchIsUnwantedToken(input, ttype)) {
/* 587 */       e = new UnwantedTokenException(ttype, input);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 593 */       beginResync();
/* 594 */       input.consume();
/* 595 */       endResync();
/* 596 */       reportError(e);
/*     */       
/* 598 */       Object matchedSymbol = getCurrentInputSymbol(input);
/* 599 */       input.consume();
/* 600 */       return matchedSymbol;
/*     */     } 
/*     */     
/* 603 */     if (mismatchIsMissingToken(input, follow)) {
/* 604 */       Object inserted = getMissingSymbol(input, e, ttype, follow);
/* 605 */       e = new MissingTokenException(ttype, input, inserted);
/* 606 */       reportError(e);
/* 607 */       return inserted;
/*     */     } 
/*     */     
/* 610 */     e = new MismatchedTokenException(ttype, input);
/* 611 */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
/* 620 */     if (mismatchIsMissingToken(input, follow)) {
/*     */       
/* 622 */       reportError(e);
/*     */       
/* 624 */       return getMissingSymbol(input, e, 0, follow);
/*     */     } 
/*     */     
/* 627 */     throw e;
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
/*     */   protected Object getCurrentInputSymbol(IntStream input) {
/* 639 */     return null;
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
/*     */   protected Object getMissingSymbol(IntStream input, RecognitionException e, int expectedTokenType, BitSet follow) {
/* 665 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeUntil(IntStream input, int tokenType) {
/* 670 */     int ttype = input.LA(1);
/* 671 */     while (ttype != -1 && ttype != tokenType) {
/* 672 */       input.consume();
/* 673 */       ttype = input.LA(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeUntil(IntStream input, BitSet set) {
/* 680 */     int ttype = input.LA(1);
/* 681 */     while (ttype != -1 && !set.member(ttype)) {
/*     */       
/* 683 */       input.consume();
/* 684 */       ttype = input.LA(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void pushFollow(BitSet fset) {
/* 690 */     if (this.state._fsp + 1 >= this.state.following.length) {
/* 691 */       BitSet[] f = new BitSet[this.state.following.length * 2];
/* 692 */       System.arraycopy(this.state.following, 0, f, 0, this.state.following.length);
/* 693 */       this.state.following = f;
/*     */     } 
/* 695 */     this.state.following[++this.state._fsp] = fset;
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
/*     */   public List getRuleInvocationStack() {
/* 707 */     String parserClassName = getClass().getName();
/* 708 */     return getRuleInvocationStack(new Throwable(), parserClassName);
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
/*     */   public static List getRuleInvocationStack(Throwable e, String recognizerClassName) {
/* 721 */     List rules = new ArrayList();
/* 722 */     StackTraceElement[] stack = e.getStackTrace();
/* 723 */     int i = 0;
/* 724 */     for (i = stack.length - 1; i >= 0; i--) {
/* 725 */       StackTraceElement t = stack[i];
/* 726 */       if (!t.getClassName().startsWith("org.antlr.runtime."))
/*     */       {
/*     */         
/* 729 */         if (!t.getMethodName().equals("nextToken"))
/*     */         {
/*     */           
/* 732 */           if (t.getClassName().equals(recognizerClassName))
/*     */           {
/*     */             
/* 735 */             rules.add(t.getMethodName()); }  }  } 
/*     */     } 
/* 737 */     return rules;
/*     */   }
/*     */   public int getBacktrackingLevel() {
/* 740 */     return this.state.backtracking;
/*     */   } public void setBacktrackingLevel(int n) {
/* 742 */     this.state.backtracking = n;
/*     */   }
/*     */   public boolean failed() {
/* 745 */     return this.state.failed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getTokenNames() {
/* 752 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGrammarFileName() {
/* 759 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getSourceName();
/*     */ 
/*     */   
/*     */   public List toStrings(List tokens) {
/* 768 */     if (tokens == null) return null; 
/* 769 */     List strings = new ArrayList(tokens.size());
/* 770 */     for (int i = 0; i < tokens.size(); i++) {
/* 771 */       strings.add(((Token)tokens.get(i)).getText());
/*     */     }
/* 773 */     return strings;
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
/*     */   public int getRuleMemoization(int ruleIndex, int ruleStartIndex) {
/* 787 */     if (this.state.ruleMemo[ruleIndex] == null) {
/* 788 */       this.state.ruleMemo[ruleIndex] = new HashMap();
/*     */     }
/* 790 */     Integer stopIndexI = this.state.ruleMemo[ruleIndex].get(new Integer(ruleStartIndex));
/*     */     
/* 792 */     if (stopIndexI == null) {
/* 793 */       return -1;
/*     */     }
/* 795 */     return stopIndexI.intValue();
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
/*     */   public boolean alreadyParsedRule(IntStream input, int ruleIndex) {
/* 808 */     int stopIndex = getRuleMemoization(ruleIndex, input.index());
/* 809 */     if (stopIndex == -1) {
/* 810 */       return false;
/*     */     }
/* 812 */     if (stopIndex == -2) {
/*     */       
/* 814 */       this.state.failed = true;
/*     */     }
/*     */     else {
/*     */       
/* 818 */       input.seek(stopIndex + 1);
/*     */     } 
/* 820 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void memoize(IntStream input, int ruleIndex, int ruleStartIndex) {
/* 830 */     int stopTokenIndex = this.state.failed ? -2 : (input.index() - 1);
/* 831 */     if (this.state.ruleMemo == null) {
/* 832 */       System.err.println("!!!!!!!!! memo array is null for " + getGrammarFileName());
/*     */     }
/* 834 */     if (ruleIndex >= this.state.ruleMemo.length) {
/* 835 */       System.err.println("!!!!!!!!! memo size is " + this.state.ruleMemo.length + ", but rule index is " + ruleIndex);
/*     */     }
/* 837 */     if (this.state.ruleMemo[ruleIndex] != null) {
/* 838 */       this.state.ruleMemo[ruleIndex].put(new Integer(ruleStartIndex), new Integer(stopTokenIndex));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRuleMemoizationCacheSize() {
/* 848 */     int n = 0;
/* 849 */     for (int i = 0; this.state.ruleMemo != null && i < this.state.ruleMemo.length; i++) {
/* 850 */       Map ruleMap = this.state.ruleMemo[i];
/* 851 */       if (ruleMap != null) {
/* 852 */         n += ruleMap.size();
/*     */       }
/*     */     } 
/* 855 */     return n;
/*     */   }
/*     */   
/*     */   public void traceIn(String ruleName, int ruleIndex, Object inputSymbol) {
/* 859 */     System.out.print("enter " + ruleName + " " + inputSymbol);
/* 860 */     if (this.state.backtracking > 0) {
/* 861 */       System.out.print(" backtracking=" + this.state.backtracking);
/*     */     }
/* 863 */     System.out.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void traceOut(String ruleName, int ruleIndex, Object inputSymbol) {
/* 870 */     System.out.print("exit " + ruleName + " " + inputSymbol);
/* 871 */     if (this.state.backtracking > 0) {
/* 872 */       System.out.print(" backtracking=" + this.state.backtracking);
/* 873 */       if (this.state.failed) { System.out.print(" failed"); }
/* 874 */       else { System.out.print(" succeeded"); }
/*     */     
/* 876 */     }  System.out.println();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\BaseRecognizer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */