/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class CommonToken
/*     */   implements Token, Serializable
/*     */ {
/*     */   protected int type;
/*     */   protected int line;
/*  35 */   protected int charPositionInLine = -1;
/*  36 */   protected int channel = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected transient CharStream input;
/*     */ 
/*     */   
/*     */   protected String text;
/*     */ 
/*     */   
/*  46 */   protected int index = -1;
/*     */ 
/*     */   
/*     */   protected int start;
/*     */   
/*     */   protected int stop;
/*     */ 
/*     */   
/*     */   public CommonToken(int type) {
/*  55 */     this.type = type;
/*     */   }
/*     */   
/*     */   public CommonToken(CharStream input, int type, int channel, int start, int stop) {
/*  59 */     this.input = input;
/*  60 */     this.type = type;
/*  61 */     this.channel = channel;
/*  62 */     this.start = start;
/*  63 */     this.stop = stop;
/*     */   }
/*     */   
/*     */   public CommonToken(int type, String text) {
/*  67 */     this.type = type;
/*  68 */     this.channel = 0;
/*  69 */     this.text = text;
/*     */   }
/*     */   
/*     */   public CommonToken(Token oldToken) {
/*  73 */     this.text = oldToken.getText();
/*  74 */     this.type = oldToken.getType();
/*  75 */     this.line = oldToken.getLine();
/*  76 */     this.index = oldToken.getTokenIndex();
/*  77 */     this.charPositionInLine = oldToken.getCharPositionInLine();
/*  78 */     this.channel = oldToken.getChannel();
/*  79 */     if (oldToken instanceof CommonToken) {
/*  80 */       this.start = ((CommonToken)oldToken).start;
/*  81 */       this.stop = ((CommonToken)oldToken).stop;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getType() {
/*  86 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setLine(int line) {
/*  90 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getText() {
/*  94 */     if (this.text != null) {
/*  95 */       return this.text;
/*     */     }
/*  97 */     if (this.input == null) {
/*  98 */       return null;
/*     */     }
/* 100 */     this.text = this.input.substring(this.start, this.stop);
/* 101 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 110 */     this.text = text;
/*     */   }
/*     */   
/*     */   public int getLine() {
/* 114 */     return this.line;
/*     */   }
/*     */   
/*     */   public int getCharPositionInLine() {
/* 118 */     return this.charPositionInLine;
/*     */   }
/*     */   
/*     */   public void setCharPositionInLine(int charPositionInLine) {
/* 122 */     this.charPositionInLine = charPositionInLine;
/*     */   }
/*     */   
/*     */   public int getChannel() {
/* 126 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/* 130 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 134 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getStartIndex() {
/* 138 */     return this.start;
/*     */   }
/*     */   
/*     */   public void setStartIndex(int start) {
/* 142 */     this.start = start;
/*     */   }
/*     */   
/*     */   public int getStopIndex() {
/* 146 */     return this.stop;
/*     */   }
/*     */   
/*     */   public void setStopIndex(int stop) {
/* 150 */     this.stop = stop;
/*     */   }
/*     */   
/*     */   public int getTokenIndex() {
/* 154 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setTokenIndex(int index) {
/* 158 */     this.index = index;
/*     */   }
/*     */   
/*     */   public CharStream getInputStream() {
/* 162 */     return this.input;
/*     */   }
/*     */   
/*     */   public void setInputStream(CharStream input) {
/* 166 */     this.input = input;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 170 */     String channelStr = "";
/* 171 */     if (this.channel > 0) {
/* 172 */       channelStr = ",channel=" + this.channel;
/*     */     }
/* 174 */     String txt = getText();
/* 175 */     if (txt != null) {
/* 176 */       txt = txt.replaceAll("\n", "\\\\n");
/* 177 */       txt = txt.replaceAll("\r", "\\\\r");
/* 178 */       txt = txt.replaceAll("\t", "\\\\t");
/*     */     } else {
/*     */       
/* 181 */       txt = "<no text>";
/*     */     } 
/* 183 */     return "[@" + getTokenIndex() + "," + this.start + ":" + this.stop + "='" + txt + "',<" + this.type + ">" + channelStr + "," + this.line + ":" + getCharPositionInLine() + "]";
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\CommonToken.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */