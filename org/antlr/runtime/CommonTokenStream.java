/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonTokenStream
/*     */   implements TokenStream
/*     */ {
/*     */   protected TokenSource tokenSource;
/*     */   protected List tokens;
/*     */   protected Map channelOverrideMap;
/*     */   protected Set discardSet;
/*  54 */   protected int channel = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean discardOffChannelTokens = false;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int lastMarker;
/*     */ 
/*     */   
/*  65 */   protected int p = -1;
/*     */   
/*     */   public CommonTokenStream() {
/*  68 */     this.tokens = new ArrayList(500);
/*     */   }
/*     */   
/*     */   public CommonTokenStream(TokenSource tokenSource) {
/*  72 */     this();
/*  73 */     this.tokenSource = tokenSource;
/*     */   }
/*     */   
/*     */   public CommonTokenStream(TokenSource tokenSource, int channel) {
/*  77 */     this(tokenSource);
/*  78 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTokenSource(TokenSource tokenSource) {
/*  83 */     this.tokenSource = tokenSource;
/*  84 */     this.tokens.clear();
/*  85 */     this.p = -1;
/*  86 */     this.channel = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillBuffer() {
/*  94 */     int index = 0;
/*  95 */     Token t = this.tokenSource.nextToken();
/*  96 */     while (t != null && t.getType() != -1) {
/*  97 */       boolean discard = false;
/*     */       
/*  99 */       if (this.channelOverrideMap != null) {
/* 100 */         Integer channelI = (Integer)this.channelOverrideMap.get(new Integer(t.getType()));
/*     */         
/* 102 */         if (channelI != null) {
/* 103 */           t.setChannel(channelI.intValue());
/*     */         }
/*     */       } 
/* 106 */       if (this.discardSet != null && this.discardSet.contains(new Integer(t.getType()))) {
/*     */ 
/*     */         
/* 109 */         discard = true;
/*     */       }
/* 111 */       else if (this.discardOffChannelTokens && t.getChannel() != this.channel) {
/* 112 */         discard = true;
/*     */       } 
/* 114 */       if (!discard) {
/* 115 */         t.setTokenIndex(index);
/* 116 */         this.tokens.add(t);
/* 117 */         index++;
/*     */       } 
/* 119 */       t = this.tokenSource.nextToken();
/*     */     } 
/*     */     
/* 122 */     this.p = 0;
/* 123 */     this.p = skipOffTokenChannels(this.p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void consume() {
/* 134 */     if (this.p < this.tokens.size()) {
/* 135 */       this.p++;
/* 136 */       this.p = skipOffTokenChannels(this.p);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int skipOffTokenChannels(int i) {
/* 144 */     int n = this.tokens.size();
/* 145 */     while (i < n && ((Token)this.tokens.get(i)).getChannel() != this.channel) {
/* 146 */       i++;
/*     */     }
/* 148 */     return i;
/*     */   }
/*     */   
/*     */   protected int skipOffTokenChannelsReverse(int i) {
/* 152 */     while (i >= 0 && ((Token)this.tokens.get(i)).getChannel() != this.channel) {
/* 153 */       i--;
/*     */     }
/* 155 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTokenTypeChannel(int ttype, int channel) {
/* 165 */     if (this.channelOverrideMap == null) {
/* 166 */       this.channelOverrideMap = new HashMap();
/*     */     }
/* 168 */     this.channelOverrideMap.put(new Integer(ttype), new Integer(channel));
/*     */   }
/*     */   
/*     */   public void discardTokenType(int ttype) {
/* 172 */     if (this.discardSet == null) {
/* 173 */       this.discardSet = new HashSet();
/*     */     }
/* 175 */     this.discardSet.add(new Integer(ttype));
/*     */   }
/*     */   
/*     */   public void discardOffChannelTokens(boolean discardOffChannelTokens) {
/* 179 */     this.discardOffChannelTokens = discardOffChannelTokens;
/*     */   }
/*     */   
/*     */   public List getTokens() {
/* 183 */     if (this.p == -1) {
/* 184 */       fillBuffer();
/*     */     }
/* 186 */     return this.tokens;
/*     */   }
/*     */   
/*     */   public List getTokens(int start, int stop) {
/* 190 */     return getTokens(start, stop, (BitSet)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getTokens(int start, int stop, BitSet types) {
/* 198 */     if (this.p == -1) {
/* 199 */       fillBuffer();
/*     */     }
/* 201 */     if (stop >= this.tokens.size()) {
/* 202 */       stop = this.tokens.size() - 1;
/*     */     }
/* 204 */     if (start < 0) {
/* 205 */       start = 0;
/*     */     }
/* 207 */     if (start > stop) {
/* 208 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 212 */     List filteredTokens = new ArrayList();
/* 213 */     for (int i = start; i <= stop; i++) {
/* 214 */       Token t = this.tokens.get(i);
/* 215 */       if (types == null || types.member(t.getType())) {
/* 216 */         filteredTokens.add(t);
/*     */       }
/*     */     } 
/* 219 */     if (filteredTokens.size() == 0) {
/* 220 */       filteredTokens = null;
/*     */     }
/* 222 */     return filteredTokens;
/*     */   }
/*     */   
/*     */   public List getTokens(int start, int stop, List types) {
/* 226 */     return getTokens(start, stop, new BitSet(types));
/*     */   }
/*     */   
/*     */   public List getTokens(int start, int stop, int ttype) {
/* 230 */     return getTokens(start, stop, BitSet.of(ttype));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token LT(int k) {
/* 237 */     if (this.p == -1) {
/* 238 */       fillBuffer();
/*     */     }
/* 240 */     if (k == 0) {
/* 241 */       return null;
/*     */     }
/* 243 */     if (k < 0) {
/* 244 */       return LB(-k);
/*     */     }
/*     */     
/* 247 */     if (this.p + k - 1 >= this.tokens.size()) {
/* 248 */       return Token.EOF_TOKEN;
/*     */     }
/*     */     
/* 251 */     int i = this.p;
/* 252 */     int n = 1;
/*     */     
/* 254 */     while (n < k) {
/*     */       
/* 256 */       i = skipOffTokenChannels(i + 1);
/* 257 */       n++;
/*     */     } 
/* 259 */     if (i >= this.tokens.size()) {
/* 260 */       return Token.EOF_TOKEN;
/*     */     }
/* 262 */     return this.tokens.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token LB(int k) {
/* 268 */     if (this.p == -1) {
/* 269 */       fillBuffer();
/*     */     }
/* 271 */     if (k == 0) {
/* 272 */       return null;
/*     */     }
/* 274 */     if (this.p - k < 0) {
/* 275 */       return null;
/*     */     }
/*     */     
/* 278 */     int i = this.p;
/* 279 */     int n = 1;
/*     */     
/* 281 */     while (n <= k) {
/*     */       
/* 283 */       i = skipOffTokenChannelsReverse(i - 1);
/* 284 */       n++;
/*     */     } 
/* 286 */     if (i < 0) {
/* 287 */       return null;
/*     */     }
/* 289 */     return this.tokens.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token get(int i) {
/* 296 */     return this.tokens.get(i);
/*     */   }
/*     */   
/*     */   public int LA(int i) {
/* 300 */     return LT(i).getType();
/*     */   }
/*     */   
/*     */   public int mark() {
/* 304 */     if (this.p == -1) {
/* 305 */       fillBuffer();
/*     */     }
/* 307 */     this.lastMarker = index();
/* 308 */     return this.lastMarker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void release(int marker) {}
/*     */ 
/*     */   
/*     */   public int size() {
/* 316 */     return this.tokens.size();
/*     */   }
/*     */   
/*     */   public int index() {
/* 320 */     return this.p;
/*     */   }
/*     */   
/*     */   public void rewind(int marker) {
/* 324 */     seek(marker);
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 328 */     seek(this.lastMarker);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 332 */     this.p = 0;
/* 333 */     this.lastMarker = 0;
/*     */   }
/*     */   
/*     */   public void seek(int index) {
/* 337 */     this.p = index;
/*     */   }
/*     */   
/*     */   public TokenSource getTokenSource() {
/* 341 */     return this.tokenSource;
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/* 345 */     return getTokenSource().getSourceName();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 349 */     if (this.p == -1) {
/* 350 */       fillBuffer();
/*     */     }
/* 352 */     return toString(0, this.tokens.size() - 1);
/*     */   }
/*     */   
/*     */   public String toString(int start, int stop) {
/* 356 */     if (start < 0 || stop < 0) {
/* 357 */       return null;
/*     */     }
/* 359 */     if (this.p == -1) {
/* 360 */       fillBuffer();
/*     */     }
/* 362 */     if (stop >= this.tokens.size()) {
/* 363 */       stop = this.tokens.size() - 1;
/*     */     }
/* 365 */     StringBuffer buf = new StringBuffer();
/* 366 */     for (int i = start; i <= stop; i++) {
/* 367 */       Token t = this.tokens.get(i);
/* 368 */       buf.append(t.getText());
/*     */     } 
/* 370 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String toString(Token start, Token stop) {
/* 374 */     if (start != null && stop != null) {
/* 375 */       return toString(start.getTokenIndex(), stop.getTokenIndex());
/*     */     }
/* 377 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\CommonTokenStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */