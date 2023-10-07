/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.tree.ParseTree;
/*     */ import org.antlr.runtime.tree.Tree;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParseTreeBuilder
/*     */   extends BlankDebugEventListener
/*     */ {
/*     */   public static final String EPSILON_PAYLOAD = "<epsilon>";
/*  44 */   Stack callStack = new Stack();
/*  45 */   List hiddenTokens = new ArrayList();
/*  46 */   int backtracking = 0;
/*     */   
/*     */   public ParseTreeBuilder(String grammarName) {
/*  49 */     ParseTree root = create("<grammar " + grammarName + ">");
/*  50 */     this.callStack.push(root);
/*     */   }
/*     */   
/*     */   public ParseTree getTree() {
/*  54 */     return this.callStack.elementAt(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParseTree create(Object payload) {
/*  61 */     return new ParseTree(payload);
/*     */   }
/*     */   
/*     */   public ParseTree epsilonNode() {
/*  65 */     return create("<epsilon>");
/*     */   }
/*     */   
/*     */   public void enterDecision(int d) {
/*  69 */     this.backtracking++; } public void exitDecision(int i) {
/*  70 */     this.backtracking--;
/*     */   }
/*     */   public void enterRule(String filename, String ruleName) {
/*  73 */     if (this.backtracking > 0)
/*  74 */       return;  ParseTree parentRuleNode = this.callStack.peek();
/*  75 */     ParseTree ruleNode = create(ruleName);
/*  76 */     parentRuleNode.addChild((Tree)ruleNode);
/*  77 */     this.callStack.push(ruleNode);
/*     */   }
/*     */   
/*     */   public void exitRule(String filename, String ruleName) {
/*  81 */     if (this.backtracking > 0)
/*  82 */       return;  ParseTree ruleNode = this.callStack.peek();
/*  83 */     if (ruleNode.getChildCount() == 0) {
/*  84 */       ruleNode.addChild((Tree)epsilonNode());
/*     */     }
/*  86 */     this.callStack.pop();
/*     */   }
/*     */   
/*     */   public void consumeToken(Token token) {
/*  90 */     if (this.backtracking > 0)
/*  91 */       return;  ParseTree ruleNode = this.callStack.peek();
/*  92 */     ParseTree elementNode = create(token);
/*  93 */     elementNode.hiddenTokens = this.hiddenTokens;
/*  94 */     this.hiddenTokens = new ArrayList();
/*  95 */     ruleNode.addChild((Tree)elementNode);
/*     */   }
/*     */   
/*     */   public void consumeHiddenToken(Token token) {
/*  99 */     if (this.backtracking > 0)
/* 100 */       return;  this.hiddenTokens.add(token);
/*     */   }
/*     */   
/*     */   public void recognitionException(RecognitionException e) {
/* 104 */     if (this.backtracking > 0)
/* 105 */       return;  ParseTree ruleNode = this.callStack.peek();
/* 106 */     ParseTree errorNode = create(e);
/* 107 */     ruleNode.addChild((Tree)errorNode);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\ParseTreeBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */