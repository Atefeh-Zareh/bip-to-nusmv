/*    */ package org.antlr.runtime.tree;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Tree
/*    */ {
/* 45 */   public static final Tree INVALID_NODE = new CommonTree(Token.INVALID_TOKEN);
/*    */   
/*    */   Tree getChild(int paramInt);
/*    */   
/*    */   int getChildCount();
/*    */   
/*    */   Tree getParent();
/*    */   
/*    */   void setParent(Tree paramTree);
/*    */   
/*    */   boolean hasAncestor(int paramInt);
/*    */   
/*    */   Tree getAncestor(int paramInt);
/*    */   
/*    */   List getAncestors();
/*    */   
/*    */   int getChildIndex();
/*    */   
/*    */   void setChildIndex(int paramInt);
/*    */   
/*    */   void freshenParentAndChildIndexes();
/*    */   
/*    */   void addChild(Tree paramTree);
/*    */   
/*    */   void setChild(int paramInt, Tree paramTree);
/*    */   
/*    */   Object deleteChild(int paramInt);
/*    */   
/*    */   void replaceChildren(int paramInt1, int paramInt2, Object paramObject);
/*    */   
/*    */   boolean isNil();
/*    */   
/*    */   int getTokenStartIndex();
/*    */   
/*    */   void setTokenStartIndex(int paramInt);
/*    */   
/*    */   int getTokenStopIndex();
/*    */   
/*    */   void setTokenStopIndex(int paramInt);
/*    */   
/*    */   Tree dupNode();
/*    */   
/*    */   int getType();
/*    */   
/*    */   String getText();
/*    */   
/*    */   int getLine();
/*    */   
/*    */   int getCharPositionInLine();
/*    */   
/*    */   String toStringTree();
/*    */   
/*    */   String toString();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\Tree.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */