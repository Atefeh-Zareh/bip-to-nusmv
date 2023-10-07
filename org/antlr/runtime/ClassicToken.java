/*     */ package org.antlr.runtime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassicToken
/*     */   implements Token
/*     */ {
/*     */   protected String text;
/*     */   protected int type;
/*     */   protected int line;
/*     */   protected int charPositionInLine;
/*  42 */   protected int channel = 0;
/*     */   
/*     */   protected int index;
/*     */ 
/*     */   
/*     */   public ClassicToken(int type) {
/*  48 */     this.type = type;
/*     */   }
/*     */   
/*     */   public ClassicToken(Token oldToken) {
/*  52 */     this.text = oldToken.getText();
/*  53 */     this.type = oldToken.getType();
/*  54 */     this.line = oldToken.getLine();
/*  55 */     this.charPositionInLine = oldToken.getCharPositionInLine();
/*  56 */     this.channel = oldToken.getChannel();
/*     */   }
/*     */   
/*     */   public ClassicToken(int type, String text) {
/*  60 */     this.type = type;
/*  61 */     this.text = text;
/*     */   }
/*     */   
/*     */   public ClassicToken(int type, String text, int channel) {
/*  65 */     this.type = type;
/*  66 */     this.text = text;
/*  67 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  71 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setLine(int line) {
/*  75 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getText() {
/*  79 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/*  83 */     this.text = text;
/*     */   }
/*     */   
/*     */   public int getLine() {
/*  87 */     return this.line;
/*     */   }
/*     */   
/*     */   public int getCharPositionInLine() {
/*  91 */     return this.charPositionInLine;
/*     */   }
/*     */   
/*     */   public void setCharPositionInLine(int charPositionInLine) {
/*  95 */     this.charPositionInLine = charPositionInLine;
/*     */   }
/*     */   
/*     */   public int getChannel() {
/*  99 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/* 103 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 107 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getTokenIndex() {
/* 111 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setTokenIndex(int index) {
/* 115 */     this.index = index;
/*     */   }
/*     */   
/*     */   public CharStream getInputStream() {
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInputStream(CharStream input) {}
/*     */   
/*     */   public String toString() {
/* 126 */     String channelStr = "";
/* 127 */     if (this.channel > 0) {
/* 128 */       channelStr = ",channel=" + this.channel;
/*     */     }
/* 130 */     String txt = getText();
/* 131 */     if (txt != null) {
/* 132 */       txt = txt.replaceAll("\n", "\\\\n");
/* 133 */       txt = txt.replaceAll("\r", "\\\\r");
/* 134 */       txt = txt.replaceAll("\t", "\\\\t");
/*     */     } else {
/*     */       
/* 137 */       txt = "<no text>";
/*     */     } 
/* 139 */     return "[@" + getTokenIndex() + ",'" + txt + "',<" + this.type + ">" + channelStr + "," + this.line + ":" + getCharPositionInLine() + "]";
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\ClassicToken.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */