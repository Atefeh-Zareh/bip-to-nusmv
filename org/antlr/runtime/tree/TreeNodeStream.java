package org.antlr.runtime.tree;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.TokenStream;

public interface TreeNodeStream extends IntStream {
  Object get(int paramInt);
  
  Object LT(int paramInt);
  
  Object getTreeSource();
  
  TokenStream getTokenStream();
  
  TreeAdaptor getTreeAdaptor();
  
  void setUniqueNavigationNodes(boolean paramBoolean);
  
  void reset();
  
  String toString(Object paramObject1, Object paramObject2);
  
  void replaceChildren(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeNodeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */