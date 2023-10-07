package org.antlr.runtime;

public interface TokenStream extends IntStream {
  Token LT(int paramInt);
  
  Token get(int paramInt);
  
  TokenSource getTokenSource();
  
  String toString(int paramInt1, int paramInt2);
  
  String toString(Token paramToken1, Token paramToken2);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\TokenStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */