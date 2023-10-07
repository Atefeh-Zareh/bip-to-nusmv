package org.antlr.runtime.tree;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public interface TreeAdaptor {
  Object create(Token paramToken);
  
  Object dupNode(Object paramObject);
  
  Object dupTree(Object paramObject);
  
  Object nil();
  
  Object errorNode(TokenStream paramTokenStream, Token paramToken1, Token paramToken2, RecognitionException paramRecognitionException);
  
  boolean isNil(Object paramObject);
  
  void addChild(Object paramObject1, Object paramObject2);
  
  Object becomeRoot(Object paramObject1, Object paramObject2);
  
  Object rulePostProcessing(Object paramObject);
  
  int getUniqueID(Object paramObject);
  
  Object becomeRoot(Token paramToken, Object paramObject);
  
  Object create(int paramInt, Token paramToken);
  
  Object create(int paramInt, Token paramToken, String paramString);
  
  Object create(int paramInt, String paramString);
  
  int getType(Object paramObject);
  
  void setType(Object paramObject, int paramInt);
  
  String getText(Object paramObject);
  
  void setText(Object paramObject, String paramString);
  
  Token getToken(Object paramObject);
  
  void setTokenBoundaries(Object paramObject, Token paramToken1, Token paramToken2);
  
  int getTokenStartIndex(Object paramObject);
  
  int getTokenStopIndex(Object paramObject);
  
  Object getChild(Object paramObject, int paramInt);
  
  void setChild(Object paramObject1, int paramInt, Object paramObject2);
  
  Object deleteChild(Object paramObject, int paramInt);
  
  int getChildCount(Object paramObject);
  
  Object getParent(Object paramObject);
  
  void setParent(Object paramObject1, Object paramObject2);
  
  int getChildIndex(Object paramObject);
  
  void setChildIndex(Object paramObject, int paramInt);
  
  void replaceChildren(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeAdaptor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */