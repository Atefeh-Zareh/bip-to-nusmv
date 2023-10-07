/*    */ package org.antlr.runtime.debug;
/*    */ 
/*    */ import org.antlr.runtime.RecognitionException;
/*    */ import org.antlr.runtime.Token;
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
/*    */ public class DebugEventRepeater
/*    */   implements DebugEventListener
/*    */ {
/*    */   protected DebugEventListener listener;
/*    */   
/*    */   public DebugEventRepeater(DebugEventListener listener) {
/* 46 */     this.listener = listener;
/*    */   }
/*    */   
/* 49 */   public void enterRule(String grammarFileName, String ruleName) { this.listener.enterRule(grammarFileName, ruleName); }
/* 50 */   public void exitRule(String grammarFileName, String ruleName) { this.listener.exitRule(grammarFileName, ruleName); }
/* 51 */   public void enterAlt(int alt) { this.listener.enterAlt(alt); }
/* 52 */   public void enterSubRule(int decisionNumber) { this.listener.enterSubRule(decisionNumber); }
/* 53 */   public void exitSubRule(int decisionNumber) { this.listener.exitSubRule(decisionNumber); }
/* 54 */   public void enterDecision(int decisionNumber) { this.listener.enterDecision(decisionNumber); }
/* 55 */   public void exitDecision(int decisionNumber) { this.listener.exitDecision(decisionNumber); }
/* 56 */   public void location(int line, int pos) { this.listener.location(line, pos); }
/* 57 */   public void consumeToken(Token token) { this.listener.consumeToken(token); }
/* 58 */   public void consumeHiddenToken(Token token) { this.listener.consumeHiddenToken(token); }
/* 59 */   public void LT(int i, Token t) { this.listener.LT(i, t); }
/* 60 */   public void mark(int i) { this.listener.mark(i); }
/* 61 */   public void rewind(int i) { this.listener.rewind(i); }
/* 62 */   public void rewind() { this.listener.rewind(); }
/* 63 */   public void beginBacktrack(int level) { this.listener.beginBacktrack(level); }
/* 64 */   public void endBacktrack(int level, boolean successful) { this.listener.endBacktrack(level, successful); }
/* 65 */   public void recognitionException(RecognitionException e) { this.listener.recognitionException(e); }
/* 66 */   public void beginResync() { this.listener.beginResync(); }
/* 67 */   public void endResync() { this.listener.endResync(); }
/* 68 */   public void semanticPredicate(boolean result, String predicate) { this.listener.semanticPredicate(result, predicate); }
/* 69 */   public void commence() { this.listener.commence(); } public void terminate() {
/* 70 */     this.listener.terminate();
/*    */   }
/*    */   
/*    */   public void consumeNode(Object t) {
/* 74 */     this.listener.consumeNode(t); } public void LT(int i, Object t) {
/* 75 */     this.listener.LT(i, t);
/*    */   }
/*    */   
/*    */   public void nilNode(Object t) {
/* 79 */     this.listener.nilNode(t);
/* 80 */   } public void errorNode(Object t) { this.listener.errorNode(t); }
/* 81 */   public void createNode(Object t) { this.listener.createNode(t); }
/* 82 */   public void createNode(Object node, Token token) { this.listener.createNode(node, token); }
/* 83 */   public void becomeRoot(Object newRoot, Object oldRoot) { this.listener.becomeRoot(newRoot, oldRoot); } public void addChild(Object root, Object child) {
/* 84 */     this.listener.addChild(root, child);
/*    */   } public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
/* 86 */     this.listener.setTokenBoundaries(t, tokenStartIndex, tokenStopIndex);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugEventRepeater.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */