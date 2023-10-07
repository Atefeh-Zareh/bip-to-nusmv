/*    */ package org.antlr.runtime;
/*    */ 
/*    */ import org.antlr.runtime.tree.TreeNodeStream;
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
/*    */ public class MismatchedTreeNodeException
/*    */   extends RecognitionException
/*    */ {
/*    */   public int expecting;
/*    */   
/*    */   public MismatchedTreeNodeException() {}
/*    */   
/*    */   public MismatchedTreeNodeException(int expecting, TreeNodeStream input) {
/* 42 */     super((IntStream)input);
/* 43 */     this.expecting = expecting;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return "MismatchedTreeNodeException(" + getUnexpectedType() + "!=" + this.expecting + ")";
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\MismatchedTreeNodeException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */