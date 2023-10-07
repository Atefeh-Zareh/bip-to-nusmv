package org.antlr.runtime;

public interface CharStream extends IntStream {
  public static final int EOF = -1;
  
  String substring(int paramInt1, int paramInt2);
  
  int LT(int paramInt);
  
  int getLine();
  
  void setLine(int paramInt);
  
  void setCharPositionInLine(int paramInt);
  
  int getCharPositionInLine();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\CharStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */