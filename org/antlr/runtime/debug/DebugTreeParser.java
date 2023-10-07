/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.antlr.runtime.BitSet;
/*     */ import org.antlr.runtime.IntStream;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.RecognizerSharedState;
/*     */ import org.antlr.runtime.tree.TreeNodeStream;
/*     */ import org.antlr.runtime.tree.TreeParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebugTreeParser
/*     */   extends TreeParser
/*     */ {
/*  38 */   protected DebugEventListener dbg = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCyclicDecision = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DebugTreeParser(TreeNodeStream input, DebugEventListener dbg, RecognizerSharedState state) {
/*  49 */     super((input instanceof DebugTreeNodeStream) ? input : new DebugTreeNodeStream(input, dbg), state);
/*  50 */     setDebugListener(dbg);
/*     */   }
/*     */   
/*     */   public DebugTreeParser(TreeNodeStream input, RecognizerSharedState state) {
/*  54 */     super((input instanceof DebugTreeNodeStream) ? input : new DebugTreeNodeStream(input, null), state);
/*     */   }
/*     */   
/*     */   public DebugTreeParser(TreeNodeStream input, DebugEventListener dbg) {
/*  58 */     this((input instanceof DebugTreeNodeStream) ? input : new DebugTreeNodeStream(input, dbg), dbg, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDebugListener(DebugEventListener dbg) {
/*  65 */     if (this.input instanceof DebugTreeNodeStream) {
/*  66 */       ((DebugTreeNodeStream)this.input).setDebugListener(dbg);
/*     */     }
/*  68 */     this.dbg = dbg;
/*     */   }
/*     */   
/*     */   public DebugEventListener getDebugListener() {
/*  72 */     return this.dbg;
/*     */   }
/*     */   
/*     */   public void reportError(IOException e) {
/*  76 */     System.err.println(e);
/*  77 */     e.printStackTrace(System.err);
/*     */   }
/*     */   
/*     */   public void reportError(RecognitionException e) {
/*  81 */     this.dbg.recognitionException(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getMissingSymbol(IntStream input, RecognitionException e, int expectedTokenType, BitSet follow) {
/*  89 */     Object o = super.getMissingSymbol(input, e, expectedTokenType, follow);
/*  90 */     this.dbg.consumeNode(o);
/*  91 */     return o;
/*     */   }
/*     */   
/*     */   public void beginResync() {
/*  95 */     this.dbg.beginResync();
/*     */   }
/*     */   
/*     */   public void endResync() {
/*  99 */     this.dbg.endResync();
/*     */   }
/*     */   
/*     */   public void beginBacktrack(int level) {
/* 103 */     this.dbg.beginBacktrack(level);
/*     */   }
/*     */   
/*     */   public void endBacktrack(int level, boolean successful) {
/* 107 */     this.dbg.endBacktrack(level, successful);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugTreeParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */