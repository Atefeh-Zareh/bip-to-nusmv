/*    */ package org.antlr.runtime.debug;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.antlr.runtime.Parser;
/*    */ import org.antlr.runtime.RecognitionException;
/*    */ import org.antlr.runtime.RecognizerSharedState;
/*    */ import org.antlr.runtime.TokenStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DebugParser
/*    */   extends Parser
/*    */ {
/* 36 */   protected DebugEventListener dbg = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCyclicDecision = false;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DebugParser(TokenStream input, DebugEventListener dbg, RecognizerSharedState state) {
/* 47 */     super((input instanceof DebugTokenStream) ? input : new DebugTokenStream(input, dbg), state);
/* 48 */     setDebugListener(dbg);
/*    */   }
/*    */   
/*    */   public DebugParser(TokenStream input, RecognizerSharedState state) {
/* 52 */     super((input instanceof DebugTokenStream) ? input : new DebugTokenStream(input, null), state);
/*    */   }
/*    */   
/*    */   public DebugParser(TokenStream input, DebugEventListener dbg) {
/* 56 */     this((input instanceof DebugTokenStream) ? input : new DebugTokenStream(input, dbg), dbg, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDebugListener(DebugEventListener dbg) {
/* 63 */     if (this.input instanceof DebugTokenStream) {
/* 64 */       ((DebugTokenStream)this.input).setDebugListener(dbg);
/*    */     }
/* 66 */     this.dbg = dbg;
/*    */   }
/*    */   
/*    */   public DebugEventListener getDebugListener() {
/* 70 */     return this.dbg;
/*    */   }
/*    */   
/*    */   public void reportError(IOException e) {
/* 74 */     System.err.println(e);
/* 75 */     e.printStackTrace(System.err);
/*    */   }
/*    */   
/*    */   public void beginResync() {
/* 79 */     this.dbg.beginResync();
/*    */   }
/*    */   
/*    */   public void endResync() {
/* 83 */     this.dbg.endResync();
/*    */   }
/*    */   
/*    */   public void beginBacktrack(int level) {
/* 87 */     this.dbg.beginBacktrack(level);
/*    */   }
/*    */   
/*    */   public void endBacktrack(int level, boolean successful) {
/* 91 */     this.dbg.endBacktrack(level, successful);
/*    */   }
/*    */   
/*    */   public void reportError(RecognitionException e) {
/* 95 */     this.dbg.recognitionException(e);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */