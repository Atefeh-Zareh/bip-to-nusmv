/*    */ package org.antlr.runtime;
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
/*    */ public interface Token
/*    */ {
/*    */   public static final int EOR_TOKEN_TYPE = 1;
/*    */   public static final int DOWN = 2;
/*    */   public static final int UP = 3;
/*    */   public static final int MIN_TOKEN_TYPE = 4;
/*    */   public static final int EOF = -1;
/* 41 */   public static final Token EOF_TOKEN = new CommonToken(-1);
/*    */   
/*    */   public static final int INVALID_TOKEN_TYPE = 0;
/* 44 */   public static final Token INVALID_TOKEN = new CommonToken(0);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public static final Token SKIP_TOKEN = new CommonToken(0);
/*    */   public static final int DEFAULT_CHANNEL = 0;
/*    */   public static final int HIDDEN_CHANNEL = 99;
/*    */   
/*    */   String getText();
/*    */   
/*    */   void setText(String paramString);
/*    */   
/*    */   int getType();
/*    */   
/*    */   void setType(int paramInt);
/*    */   
/*    */   int getLine();
/*    */   
/*    */   void setLine(int paramInt);
/*    */   
/*    */   int getCharPositionInLine();
/*    */   
/*    */   void setCharPositionInLine(int paramInt);
/*    */   
/*    */   int getChannel();
/*    */   
/*    */   void setChannel(int paramInt);
/*    */   
/*    */   int getTokenIndex();
/*    */   
/*    */   void setTokenIndex(int paramInt);
/*    */   
/*    */   CharStream getInputStream();
/*    */   
/*    */   void setInputStream(CharStream paramCharStream);
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\Token.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */