/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.antlr.runtime.CommonToken;
/*     */ import org.antlr.runtime.IntStream;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.misc.Stats;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Profiler
/*     */   extends BlankDebugEventListener
/*     */ {
/*     */   public static final String Version = "2";
/*     */   public static final String RUNTIME_STATS_FILENAME = "runtime.stats";
/*     */   public static final int NUM_RUNTIME_STATS = 29;
/*  47 */   public DebugParser parser = null;
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected int ruleLevel = 0;
/*  52 */   protected int decisionLevel = 0;
/*  53 */   protected int maxLookaheadInCurrentDecision = 0;
/*  54 */   protected CommonToken lastTokenConsumed = null;
/*     */   
/*  56 */   protected List lookaheadStack = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  60 */   public int numRuleInvocations = 0;
/*  61 */   public int numGuessingRuleInvocations = 0;
/*  62 */   public int maxRuleInvocationDepth = 0;
/*  63 */   public int numFixedDecisions = 0;
/*  64 */   public int numCyclicDecisions = 0;
/*  65 */   public int numBacktrackDecisions = 0;
/*  66 */   public int[] decisionMaxFixedLookaheads = new int[200];
/*  67 */   public int[] decisionMaxCyclicLookaheads = new int[200];
/*  68 */   public List decisionMaxSynPredLookaheads = new ArrayList();
/*  69 */   public int numHiddenTokens = 0;
/*  70 */   public int numCharsMatched = 0;
/*  71 */   public int numHiddenCharsMatched = 0;
/*  72 */   public int numSemanticPredicates = 0;
/*  73 */   public int numSyntacticPredicates = 0;
/*  74 */   protected int numberReportedErrors = 0;
/*  75 */   public int numMemoizationCacheMisses = 0;
/*  76 */   public int numMemoizationCacheHits = 0;
/*  77 */   public int numMemoizationCacheEntries = 0;
/*     */ 
/*     */   
/*     */   public Profiler() {}
/*     */   
/*     */   public Profiler(DebugParser parser) {
/*  83 */     this.parser = parser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enterRule(String grammarFileName, String ruleName) {
/*  88 */     this.ruleLevel++;
/*  89 */     this.numRuleInvocations++;
/*  90 */     if (this.ruleLevel > this.maxRuleInvocationDepth) {
/*  91 */       this.maxRuleInvocationDepth = this.ruleLevel;
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
/*     */   public void examineRuleMemoization(IntStream input, int ruleIndex, String ruleName) {
/* 105 */     int stopIndex = this.parser.getRuleMemoization(ruleIndex, input.index());
/* 106 */     if (stopIndex == -1) {
/*     */       
/* 108 */       this.numMemoizationCacheMisses++;
/* 109 */       this.numGuessingRuleInvocations++;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 114 */       this.numMemoizationCacheHits++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void memoize(IntStream input, int ruleIndex, int ruleStartIndex, String ruleName) {
/* 125 */     this.numMemoizationCacheEntries++;
/*     */   }
/*     */   
/*     */   public void exitRule(String grammarFileName, String ruleName) {
/* 129 */     this.ruleLevel--;
/*     */   }
/*     */   
/*     */   public void enterDecision(int decisionNumber) {
/* 133 */     this.decisionLevel++;
/* 134 */     int startingLookaheadIndex = this.parser.getTokenStream().index();
/*     */     
/* 136 */     this.lookaheadStack.add(new Integer(startingLookaheadIndex));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exitDecision(int decisionNumber) {
/* 143 */     if (this.parser.isCyclicDecision) {
/* 144 */       this.numCyclicDecisions++;
/*     */     } else {
/*     */       
/* 147 */       this.numFixedDecisions++;
/*     */     } 
/* 149 */     this.lookaheadStack.remove(this.lookaheadStack.size() - 1);
/* 150 */     this.decisionLevel--;
/* 151 */     if (this.parser.isCyclicDecision) {
/* 152 */       if (this.numCyclicDecisions >= this.decisionMaxCyclicLookaheads.length) {
/* 153 */         int[] bigger = new int[this.decisionMaxCyclicLookaheads.length * 2];
/* 154 */         System.arraycopy(this.decisionMaxCyclicLookaheads, 0, bigger, 0, this.decisionMaxCyclicLookaheads.length);
/* 155 */         this.decisionMaxCyclicLookaheads = bigger;
/*     */       } 
/* 157 */       this.decisionMaxCyclicLookaheads[this.numCyclicDecisions - 1] = this.maxLookaheadInCurrentDecision;
/*     */     } else {
/*     */       
/* 160 */       if (this.numFixedDecisions >= this.decisionMaxFixedLookaheads.length) {
/* 161 */         int[] bigger = new int[this.decisionMaxFixedLookaheads.length * 2];
/* 162 */         System.arraycopy(this.decisionMaxFixedLookaheads, 0, bigger, 0, this.decisionMaxFixedLookaheads.length);
/* 163 */         this.decisionMaxFixedLookaheads = bigger;
/*     */       } 
/* 165 */       this.decisionMaxFixedLookaheads[this.numFixedDecisions - 1] = this.maxLookaheadInCurrentDecision;
/*     */     } 
/* 167 */     this.parser.isCyclicDecision = false;
/* 168 */     this.maxLookaheadInCurrentDecision = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeToken(Token token) {
/* 173 */     this.lastTokenConsumed = (CommonToken)token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inDecision() {
/* 180 */     return (this.decisionLevel > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeHiddenToken(Token token) {
/* 185 */     this.lastTokenConsumed = (CommonToken)token;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void LT(int i, Token t) {
/* 191 */     if (inDecision()) {
/*     */       
/* 193 */       int stackTop = this.lookaheadStack.size() - 1;
/* 194 */       Integer startingIndex = this.lookaheadStack.get(stackTop);
/*     */       
/* 196 */       int thisRefIndex = this.parser.getTokenStream().index();
/* 197 */       int numHidden = getNumberOfHiddenTokens(startingIndex.intValue(), thisRefIndex);
/*     */       
/* 199 */       int depth = i + thisRefIndex - startingIndex.intValue() - numHidden;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 204 */       if (depth > this.maxLookaheadInCurrentDecision) {
/* 205 */         this.maxLookaheadInCurrentDecision = depth;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginBacktrack(int level) {
/* 227 */     this.numBacktrackDecisions++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endBacktrack(int level, boolean successful) {
/* 233 */     this.decisionMaxSynPredLookaheads.add(new Integer(this.maxLookaheadInCurrentDecision));
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
/*     */   public void recognitionException(RecognitionException e) {
/* 262 */     this.numberReportedErrors++;
/*     */   }
/*     */   
/*     */   public void semanticPredicate(boolean result, String predicate) {
/* 266 */     if (inDecision()) {
/* 267 */       this.numSemanticPredicates++;
/*     */     }
/*     */   }
/*     */   
/*     */   public void terminate() {
/* 272 */     String stats = toNotifyString();
/*     */     try {
/* 274 */       Stats.writeReport("runtime.stats", stats);
/*     */     }
/* 276 */     catch (IOException ioe) {
/* 277 */       System.err.println(ioe);
/* 278 */       ioe.printStackTrace(System.err);
/*     */     } 
/* 280 */     System.out.println(toString(stats));
/*     */   }
/*     */   
/*     */   public void setParser(DebugParser parser) {
/* 284 */     this.parser = parser;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toNotifyString() {
/* 290 */     TokenStream input = this.parser.getTokenStream();
/* 291 */     for (int i = 0; i < input.size() && this.lastTokenConsumed != null && i <= this.lastTokenConsumed.getTokenIndex(); i++) {
/* 292 */       Token t = input.get(i);
/* 293 */       if (t.getChannel() != 0) {
/* 294 */         this.numHiddenTokens++;
/* 295 */         this.numHiddenCharsMatched += t.getText().length();
/*     */       } 
/*     */     } 
/* 298 */     this.numCharsMatched = this.lastTokenConsumed.getStopIndex() + 1;
/* 299 */     this.decisionMaxFixedLookaheads = trim(this.decisionMaxFixedLookaheads, this.numFixedDecisions);
/* 300 */     this.decisionMaxCyclicLookaheads = trim(this.decisionMaxCyclicLookaheads, this.numCyclicDecisions);
/* 301 */     StringBuffer buf = new StringBuffer();
/* 302 */     buf.append("2");
/* 303 */     buf.append('\t');
/* 304 */     buf.append(this.parser.getClass().getName());
/* 305 */     buf.append('\t');
/* 306 */     buf.append(this.numRuleInvocations);
/* 307 */     buf.append('\t');
/* 308 */     buf.append(this.maxRuleInvocationDepth);
/* 309 */     buf.append('\t');
/* 310 */     buf.append(this.numFixedDecisions);
/* 311 */     buf.append('\t');
/* 312 */     buf.append(Stats.min(this.decisionMaxFixedLookaheads));
/* 313 */     buf.append('\t');
/* 314 */     buf.append(Stats.max(this.decisionMaxFixedLookaheads));
/* 315 */     buf.append('\t');
/* 316 */     buf.append(Stats.avg(this.decisionMaxFixedLookaheads));
/* 317 */     buf.append('\t');
/* 318 */     buf.append(Stats.stddev(this.decisionMaxFixedLookaheads));
/* 319 */     buf.append('\t');
/* 320 */     buf.append(this.numCyclicDecisions);
/* 321 */     buf.append('\t');
/* 322 */     buf.append(Stats.min(this.decisionMaxCyclicLookaheads));
/* 323 */     buf.append('\t');
/* 324 */     buf.append(Stats.max(this.decisionMaxCyclicLookaheads));
/* 325 */     buf.append('\t');
/* 326 */     buf.append(Stats.avg(this.decisionMaxCyclicLookaheads));
/* 327 */     buf.append('\t');
/* 328 */     buf.append(Stats.stddev(this.decisionMaxCyclicLookaheads));
/* 329 */     buf.append('\t');
/* 330 */     buf.append(this.numBacktrackDecisions);
/* 331 */     buf.append('\t');
/* 332 */     buf.append(Stats.min(toArray(this.decisionMaxSynPredLookaheads)));
/* 333 */     buf.append('\t');
/* 334 */     buf.append(Stats.max(toArray(this.decisionMaxSynPredLookaheads)));
/* 335 */     buf.append('\t');
/* 336 */     buf.append(Stats.avg(toArray(this.decisionMaxSynPredLookaheads)));
/* 337 */     buf.append('\t');
/* 338 */     buf.append(Stats.stddev(toArray(this.decisionMaxSynPredLookaheads)));
/* 339 */     buf.append('\t');
/* 340 */     buf.append(this.numSemanticPredicates);
/* 341 */     buf.append('\t');
/* 342 */     buf.append(this.parser.getTokenStream().size());
/* 343 */     buf.append('\t');
/* 344 */     buf.append(this.numHiddenTokens);
/* 345 */     buf.append('\t');
/* 346 */     buf.append(this.numCharsMatched);
/* 347 */     buf.append('\t');
/* 348 */     buf.append(this.numHiddenCharsMatched);
/* 349 */     buf.append('\t');
/* 350 */     buf.append(this.numberReportedErrors);
/* 351 */     buf.append('\t');
/* 352 */     buf.append(this.numMemoizationCacheHits);
/* 353 */     buf.append('\t');
/* 354 */     buf.append(this.numMemoizationCacheMisses);
/* 355 */     buf.append('\t');
/* 356 */     buf.append(this.numGuessingRuleInvocations);
/* 357 */     buf.append('\t');
/* 358 */     buf.append(this.numMemoizationCacheEntries);
/* 359 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 363 */     return toString(toNotifyString());
/*     */   }
/*     */   
/*     */   protected static String[] decodeReportData(String data) {
/* 367 */     String[] fields = new String[29];
/* 368 */     StringTokenizer st = new StringTokenizer(data, "\t");
/* 369 */     int i = 0;
/* 370 */     while (st.hasMoreTokens()) {
/* 371 */       fields[i] = st.nextToken();
/* 372 */       i++;
/*     */     } 
/* 374 */     if (i != 29) {
/* 375 */       return null;
/*     */     }
/* 377 */     return fields;
/*     */   }
/*     */   
/*     */   public static String toString(String notifyDataLine) {
/* 381 */     String[] fields = decodeReportData(notifyDataLine);
/* 382 */     if (fields == null) {
/* 383 */       return null;
/*     */     }
/* 385 */     StringBuffer buf = new StringBuffer();
/* 386 */     buf.append("ANTLR Runtime Report; Profile Version ");
/* 387 */     buf.append(fields[0]);
/* 388 */     buf.append('\n');
/* 389 */     buf.append("parser name ");
/* 390 */     buf.append(fields[1]);
/* 391 */     buf.append('\n');
/* 392 */     buf.append("Number of rule invocations ");
/* 393 */     buf.append(fields[2]);
/* 394 */     buf.append('\n');
/* 395 */     buf.append("Number of rule invocations in \"guessing\" mode ");
/* 396 */     buf.append(fields[27]);
/* 397 */     buf.append('\n');
/* 398 */     buf.append("max rule invocation nesting depth ");
/* 399 */     buf.append(fields[3]);
/* 400 */     buf.append('\n');
/* 401 */     buf.append("number of fixed lookahead decisions ");
/* 402 */     buf.append(fields[4]);
/* 403 */     buf.append('\n');
/* 404 */     buf.append("min lookahead used in a fixed lookahead decision ");
/* 405 */     buf.append(fields[5]);
/* 406 */     buf.append('\n');
/* 407 */     buf.append("max lookahead used in a fixed lookahead decision ");
/* 408 */     buf.append(fields[6]);
/* 409 */     buf.append('\n');
/* 410 */     buf.append("average lookahead depth used in fixed lookahead decisions ");
/* 411 */     buf.append(fields[7]);
/* 412 */     buf.append('\n');
/* 413 */     buf.append("standard deviation of depth used in fixed lookahead decisions ");
/* 414 */     buf.append(fields[8]);
/* 415 */     buf.append('\n');
/* 416 */     buf.append("number of arbitrary lookahead decisions ");
/* 417 */     buf.append(fields[9]);
/* 418 */     buf.append('\n');
/* 419 */     buf.append("min lookahead used in an arbitrary lookahead decision ");
/* 420 */     buf.append(fields[10]);
/* 421 */     buf.append('\n');
/* 422 */     buf.append("max lookahead used in an arbitrary lookahead decision ");
/* 423 */     buf.append(fields[11]);
/* 424 */     buf.append('\n');
/* 425 */     buf.append("average lookahead depth used in arbitrary lookahead decisions ");
/* 426 */     buf.append(fields[12]);
/* 427 */     buf.append('\n');
/* 428 */     buf.append("standard deviation of depth used in arbitrary lookahead decisions ");
/* 429 */     buf.append(fields[13]);
/* 430 */     buf.append('\n');
/* 431 */     buf.append("number of evaluated syntactic predicates ");
/* 432 */     buf.append(fields[14]);
/* 433 */     buf.append('\n');
/* 434 */     buf.append("min lookahead used in a syntactic predicate ");
/* 435 */     buf.append(fields[15]);
/* 436 */     buf.append('\n');
/* 437 */     buf.append("max lookahead used in a syntactic predicate ");
/* 438 */     buf.append(fields[16]);
/* 439 */     buf.append('\n');
/* 440 */     buf.append("average lookahead depth used in syntactic predicates ");
/* 441 */     buf.append(fields[17]);
/* 442 */     buf.append('\n');
/* 443 */     buf.append("standard deviation of depth used in syntactic predicates ");
/* 444 */     buf.append(fields[18]);
/* 445 */     buf.append('\n');
/* 446 */     buf.append("rule memoization cache size ");
/* 447 */     buf.append(fields[28]);
/* 448 */     buf.append('\n');
/* 449 */     buf.append("number of rule memoization cache hits ");
/* 450 */     buf.append(fields[25]);
/* 451 */     buf.append('\n');
/* 452 */     buf.append("number of rule memoization cache misses ");
/* 453 */     buf.append(fields[26]);
/* 454 */     buf.append('\n');
/* 455 */     buf.append("number of evaluated semantic predicates ");
/* 456 */     buf.append(fields[19]);
/* 457 */     buf.append('\n');
/* 458 */     buf.append("number of tokens ");
/* 459 */     buf.append(fields[20]);
/* 460 */     buf.append('\n');
/* 461 */     buf.append("number of hidden tokens ");
/* 462 */     buf.append(fields[21]);
/* 463 */     buf.append('\n');
/* 464 */     buf.append("number of char ");
/* 465 */     buf.append(fields[22]);
/* 466 */     buf.append('\n');
/* 467 */     buf.append("number of hidden char ");
/* 468 */     buf.append(fields[23]);
/* 469 */     buf.append('\n');
/* 470 */     buf.append("number of syntax errors ");
/* 471 */     buf.append(fields[24]);
/* 472 */     buf.append('\n');
/* 473 */     return buf.toString();
/*     */   }
/*     */   
/*     */   protected int[] trim(int[] X, int n) {
/* 477 */     if (n < X.length) {
/* 478 */       int[] trimmed = new int[n];
/* 479 */       System.arraycopy(X, 0, trimmed, 0, n);
/* 480 */       X = trimmed;
/*     */     } 
/* 482 */     return X;
/*     */   }
/*     */   
/*     */   protected int[] toArray(List a) {
/* 486 */     int[] x = new int[a.size()];
/* 487 */     for (int i = 0; i < a.size(); i++) {
/* 488 */       Integer I = a.get(i);
/* 489 */       x[i] = I.intValue();
/*     */     } 
/* 491 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumberOfHiddenTokens(int i, int j) {
/* 496 */     int n = 0;
/* 497 */     TokenStream input = this.parser.getTokenStream();
/* 498 */     for (int ti = i; ti < input.size() && ti <= j; ti++) {
/* 499 */       Token t = input.get(ti);
/* 500 */       if (t.getChannel() != 0) {
/* 501 */         n++;
/*     */       }
/*     */     } 
/* 504 */     return n;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\Profiler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */