/*    */ package org.antlr.runtime.debug;
/*    */ 
/*    */ import org.antlr.runtime.Token;
/*    */ import org.antlr.runtime.tree.TreeAdaptor;
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
/*    */ public class TraceDebugEventListener
/*    */   extends BlankDebugEventListener
/*    */ {
/*    */   TreeAdaptor adaptor;
/*    */   
/*    */   public TraceDebugEventListener(TreeAdaptor adaptor) {
/* 38 */     this.adaptor = adaptor;
/*    */   }
/*    */   
/* 41 */   public void enterRule(String ruleName) { System.out.println("enterRule " + ruleName); }
/* 42 */   public void exitRule(String ruleName) { System.out.println("exitRule " + ruleName); }
/* 43 */   public void enterSubRule(int decisionNumber) { System.out.println("enterSubRule"); }
/* 44 */   public void exitSubRule(int decisionNumber) { System.out.println("exitSubRule"); } public void location(int line, int pos) {
/* 45 */     System.out.println("location " + line + ":" + pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public void consumeNode(Object t) {
/* 50 */     int ID = this.adaptor.getUniqueID(t);
/* 51 */     String text = this.adaptor.getText(t);
/* 52 */     int type = this.adaptor.getType(t);
/* 53 */     System.out.println("consumeNode " + ID + " " + text + " " + type);
/*    */   }
/*    */   
/*    */   public void LT(int i, Object t) {
/* 57 */     int ID = this.adaptor.getUniqueID(t);
/* 58 */     String text = this.adaptor.getText(t);
/* 59 */     int type = this.adaptor.getType(t);
/* 60 */     System.out.println("LT " + i + " " + ID + " " + text + " " + type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void nilNode(Object t) {
/* 65 */     System.out.println("nilNode " + this.adaptor.getUniqueID(t));
/*    */   }
/*    */   public void createNode(Object t) {
/* 68 */     int ID = this.adaptor.getUniqueID(t);
/* 69 */     String text = this.adaptor.getText(t);
/* 70 */     int type = this.adaptor.getType(t);
/* 71 */     System.out.println("create " + ID + ": " + text + ", " + type);
/*    */   }
/*    */   
/*    */   public void createNode(Object node, Token token) {
/* 75 */     int ID = this.adaptor.getUniqueID(node);
/* 76 */     String text = this.adaptor.getText(node);
/* 77 */     int tokenIndex = token.getTokenIndex();
/* 78 */     System.out.println("create " + ID + ": " + tokenIndex);
/*    */   }
/*    */   
/*    */   public void becomeRoot(Object newRoot, Object oldRoot) {
/* 82 */     System.out.println("becomeRoot " + this.adaptor.getUniqueID(newRoot) + ", " + this.adaptor.getUniqueID(oldRoot));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addChild(Object root, Object child) {
/* 87 */     System.out.println("addChild " + this.adaptor.getUniqueID(root) + ", " + this.adaptor.getUniqueID(child));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
/* 92 */     System.out.println("setTokenBoundaries " + this.adaptor.getUniqueID(t) + ", " + tokenStartIndex + ", " + tokenStopIndex);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\TraceDebugEventListener.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */