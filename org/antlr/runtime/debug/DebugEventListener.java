package org.antlr.runtime.debug;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public interface DebugEventListener {
  public static final String PROTOCOL_VERSION = "2";
  
  public static final int TRUE = 1;
  
  public static final int FALSE = 0;
  
  void enterRule(String paramString1, String paramString2);
  
  void enterAlt(int paramInt);
  
  void exitRule(String paramString1, String paramString2);
  
  void enterSubRule(int paramInt);
  
  void exitSubRule(int paramInt);
  
  void enterDecision(int paramInt);
  
  void exitDecision(int paramInt);
  
  void consumeToken(Token paramToken);
  
  void consumeHiddenToken(Token paramToken);
  
  void LT(int paramInt, Token paramToken);
  
  void mark(int paramInt);
  
  void rewind(int paramInt);
  
  void rewind();
  
  void beginBacktrack(int paramInt);
  
  void endBacktrack(int paramInt, boolean paramBoolean);
  
  void location(int paramInt1, int paramInt2);
  
  void recognitionException(RecognitionException paramRecognitionException);
  
  void beginResync();
  
  void endResync();
  
  void semanticPredicate(boolean paramBoolean, String paramString);
  
  void commence();
  
  void terminate();
  
  void consumeNode(Object paramObject);
  
  void LT(int paramInt, Object paramObject);
  
  void nilNode(Object paramObject);
  
  void errorNode(Object paramObject);
  
  void createNode(Object paramObject);
  
  void createNode(Object paramObject, Token paramToken);
  
  void becomeRoot(Object paramObject1, Object paramObject2);
  
  void addChild(Object paramObject1, Object paramObject2);
  
  void setTokenBoundaries(Object paramObject, int paramInt1, int paramInt2);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugEventListener.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */