package org.antlr.runtime;

public interface IntStream {
  void consume();
  
  int LA(int paramInt);
  
  int mark();
  
  int index();
  
  void rewind(int paramInt);
  
  void rewind();
  
  void release(int paramInt);
  
  void seek(int paramInt);
  
  int size();
  
  String getSourceName();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\IntStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */