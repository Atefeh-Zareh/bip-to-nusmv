/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.ConnectException;
/*     */ import java.net.Socket;
/*     */ import java.util.StringTokenizer;
/*     */ import org.antlr.runtime.CharStream;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.tree.BaseTree;
/*     */ import org.antlr.runtime.tree.Tree;
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
/*     */ public class RemoteDebugEventSocketListener
/*     */   implements Runnable
/*     */ {
/*     */   static final int MAX_EVENT_ELEMENTS = 8;
/*     */   DebugEventListener listener;
/*     */   String machine;
/*     */   int port;
/*  46 */   Socket channel = null;
/*     */   
/*     */   PrintWriter out;
/*     */   
/*     */   BufferedReader in;
/*     */   
/*     */   String event;
/*     */   
/*     */   public String version;
/*     */   public String grammarFileName;
/*  56 */   int previousTokenIndex = -1; boolean tokenIndexesInvalid = false;
/*     */   
/*     */   public static class ProxyToken implements Token { int index;
/*     */     int type;
/*     */     int channel;
/*     */     int line;
/*     */     int charPos;
/*     */     String text;
/*     */     
/*     */     public ProxyToken(int index) {
/*  66 */       this.index = index;
/*     */     }
/*     */     
/*     */     public ProxyToken(int index, int type, int channel, int line, int charPos, String text) {
/*  70 */       this.index = index;
/*  71 */       this.type = type;
/*  72 */       this.channel = channel;
/*  73 */       this.line = line;
/*  74 */       this.charPos = charPos;
/*  75 */       this.text = text;
/*     */     }
/*     */     public String getText() {
/*  78 */       return this.text;
/*     */     }
/*     */     public void setText(String text) {
/*  81 */       this.text = text;
/*     */     }
/*     */     public int getType() {
/*  84 */       return this.type;
/*     */     }
/*     */     public void setType(int ttype) {
/*  87 */       this.type = ttype;
/*     */     }
/*     */     public int getLine() {
/*  90 */       return this.line;
/*     */     }
/*     */     public void setLine(int line) {
/*  93 */       this.line = line;
/*     */     }
/*     */     public int getCharPositionInLine() {
/*  96 */       return this.charPos;
/*     */     }
/*     */     public void setCharPositionInLine(int pos) {
/*  99 */       this.charPos = pos;
/*     */     }
/*     */     public int getChannel() {
/* 102 */       return this.channel;
/*     */     }
/*     */     public void setChannel(int channel) {
/* 105 */       this.channel = channel;
/*     */     }
/*     */     public int getTokenIndex() {
/* 108 */       return this.index;
/*     */     }
/*     */     public void setTokenIndex(int index) {
/* 111 */       this.index = index;
/*     */     }
/*     */     public CharStream getInputStream() {
/* 114 */       return null;
/*     */     }
/*     */     public void setInputStream(CharStream input) {}
/*     */     
/*     */     public String toString() {
/* 119 */       String channelStr = "";
/* 120 */       if (this.channel != 0) {
/* 121 */         channelStr = ",channel=" + this.channel;
/*     */       }
/* 123 */       return "[" + getText() + "/<" + this.type + ">" + channelStr + "," + this.line + ":" + getCharPositionInLine() + ",@" + this.index + "]";
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class ProxyTree extends BaseTree {
/*     */     public int ID;
/*     */     public int type;
/* 130 */     public int line = 0;
/* 131 */     public int charPos = -1;
/* 132 */     public int tokenIndex = -1;
/*     */     public String text;
/*     */     
/*     */     public ProxyTree(int ID, int type, int line, int charPos, int tokenIndex, String text) {
/* 136 */       this.ID = ID;
/* 137 */       this.type = type;
/* 138 */       this.line = line;
/* 139 */       this.charPos = charPos;
/* 140 */       this.tokenIndex = tokenIndex;
/* 141 */       this.text = text;
/*     */     }
/*     */     public ProxyTree(int ID) {
/* 144 */       this.ID = ID;
/*     */     } public void setTokenStartIndex(int index) {} public int getTokenStartIndex() {
/* 146 */       return this.tokenIndex;
/*     */     } public int getTokenStopIndex() {
/* 148 */       return 0;
/*     */     } public void setTokenStopIndex(int index) {}
/* 150 */     public Tree dupNode() { return null; }
/* 151 */     public int getType() { return this.type; } public String getText() {
/* 152 */       return this.text;
/*     */     } public String toString() {
/* 154 */       return "fix this";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteDebugEventSocketListener(DebugEventListener listener, String machine, int port) throws IOException {
/* 162 */     this.listener = listener;
/* 163 */     this.machine = machine;
/* 164 */     this.port = port;
/*     */     
/* 166 */     if (!openConnection()) {
/* 167 */       throw new ConnectException();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void eventHandler() {
/*     */     try {
/* 173 */       handshake();
/* 174 */       this.event = this.in.readLine();
/* 175 */       while (this.event != null) {
/* 176 */         dispatch(this.event);
/* 177 */         ack();
/* 178 */         this.event = this.in.readLine();
/*     */       }
/*     */     
/* 181 */     } catch (Exception e) {
/* 182 */       System.err.println(e);
/* 183 */       e.printStackTrace(System.err);
/*     */     } finally {
/*     */       
/* 186 */       closeConnection();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean openConnection() {
/* 191 */     boolean success = false;
/*     */     try {
/* 193 */       this.channel = new Socket(this.machine, this.port);
/* 194 */       this.channel.setTcpNoDelay(true);
/* 195 */       OutputStream os = this.channel.getOutputStream();
/* 196 */       OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
/* 197 */       this.out = new PrintWriter(new BufferedWriter(osw));
/* 198 */       InputStream is = this.channel.getInputStream();
/* 199 */       InputStreamReader isr = new InputStreamReader(is, "UTF8");
/* 200 */       this.in = new BufferedReader(isr);
/* 201 */       success = true;
/* 202 */     } catch (Exception e) {
/* 203 */       System.err.println(e);
/*     */     } 
/* 205 */     return success;
/*     */   }
/*     */   
/*     */   protected void closeConnection() {
/*     */     try {
/* 210 */       this.in.close(); this.in = null;
/* 211 */       this.out.close(); this.out = null;
/* 212 */       this.channel.close(); this.channel = null;
/*     */     }
/* 214 */     catch (Exception e) {
/* 215 */       System.err.println(e);
/* 216 */       e.printStackTrace(System.err);
/*     */     } finally {
/*     */       
/* 219 */       if (this.in != null) {
/* 220 */         try { this.in.close(); } catch (IOException ioe)
/* 221 */         { System.err.println(ioe); }
/*     */       
/*     */       }
/* 224 */       if (this.out != null) {
/* 225 */         this.out.close();
/*     */       }
/* 227 */       if (this.channel != null) {
/* 228 */         try { this.channel.close(); } catch (IOException ioe)
/* 229 */         { System.err.println(ioe); }
/*     */       
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handshake() throws IOException {
/* 237 */     String antlrLine = this.in.readLine();
/* 238 */     String[] antlrElements = getEventElements(antlrLine);
/* 239 */     this.version = antlrElements[1];
/* 240 */     String grammarLine = this.in.readLine();
/* 241 */     String[] grammarElements = getEventElements(grammarLine);
/* 242 */     this.grammarFileName = grammarElements[1];
/* 243 */     ack();
/* 244 */     this.listener.commence();
/*     */   }
/*     */   
/*     */   protected void ack() {
/* 248 */     this.out.println("ack");
/* 249 */     this.out.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dispatch(String line) {
/* 254 */     String[] elements = getEventElements(line);
/* 255 */     if (elements == null || elements[0] == null) {
/* 256 */       System.err.println("unknown debug event: " + line);
/*     */       return;
/*     */     } 
/* 259 */     if (elements[0].equals("enterRule")) {
/* 260 */       this.listener.enterRule(elements[1], elements[2]);
/*     */     }
/* 262 */     else if (elements[0].equals("exitRule")) {
/* 263 */       this.listener.exitRule(elements[1], elements[2]);
/*     */     }
/* 265 */     else if (elements[0].equals("enterAlt")) {
/* 266 */       this.listener.enterAlt(Integer.parseInt(elements[1]));
/*     */     }
/* 268 */     else if (elements[0].equals("enterSubRule")) {
/* 269 */       this.listener.enterSubRule(Integer.parseInt(elements[1]));
/*     */     }
/* 271 */     else if (elements[0].equals("exitSubRule")) {
/* 272 */       this.listener.exitSubRule(Integer.parseInt(elements[1]));
/*     */     }
/* 274 */     else if (elements[0].equals("enterDecision")) {
/* 275 */       this.listener.enterDecision(Integer.parseInt(elements[1]));
/*     */     }
/* 277 */     else if (elements[0].equals("exitDecision")) {
/* 278 */       this.listener.exitDecision(Integer.parseInt(elements[1]));
/*     */     }
/* 280 */     else if (elements[0].equals("location")) {
/* 281 */       this.listener.location(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
/*     */     
/*     */     }
/* 284 */     else if (elements[0].equals("consumeToken")) {
/* 285 */       ProxyToken t = deserializeToken(elements, 1);
/* 286 */       if (t.getTokenIndex() == this.previousTokenIndex) {
/* 287 */         this.tokenIndexesInvalid = true;
/*     */       }
/* 289 */       this.previousTokenIndex = t.getTokenIndex();
/* 290 */       this.listener.consumeToken(t);
/*     */     }
/* 292 */     else if (elements[0].equals("consumeHiddenToken")) {
/* 293 */       ProxyToken t = deserializeToken(elements, 1);
/* 294 */       if (t.getTokenIndex() == this.previousTokenIndex) {
/* 295 */         this.tokenIndexesInvalid = true;
/*     */       }
/* 297 */       this.previousTokenIndex = t.getTokenIndex();
/* 298 */       this.listener.consumeHiddenToken(t);
/*     */     }
/* 300 */     else if (elements[0].equals("LT")) {
/* 301 */       Token t = deserializeToken(elements, 2);
/* 302 */       this.listener.LT(Integer.parseInt(elements[1]), t);
/*     */     }
/* 304 */     else if (elements[0].equals("mark")) {
/* 305 */       this.listener.mark(Integer.parseInt(elements[1]));
/*     */     }
/* 307 */     else if (elements[0].equals("rewind")) {
/* 308 */       if (elements[1] != null) {
/* 309 */         this.listener.rewind(Integer.parseInt(elements[1]));
/*     */       } else {
/*     */         
/* 312 */         this.listener.rewind();
/*     */       }
/*     */     
/* 315 */     } else if (elements[0].equals("beginBacktrack")) {
/* 316 */       this.listener.beginBacktrack(Integer.parseInt(elements[1]));
/*     */     }
/* 318 */     else if (elements[0].equals("endBacktrack")) {
/* 319 */       int level = Integer.parseInt(elements[1]);
/* 320 */       int successI = Integer.parseInt(elements[2]);
/* 321 */       this.listener.endBacktrack(level, (successI == 1));
/*     */     }
/* 323 */     else if (elements[0].equals("exception")) {
/* 324 */       String excName = elements[1];
/* 325 */       String indexS = elements[2];
/* 326 */       String lineS = elements[3];
/* 327 */       String posS = elements[4];
/* 328 */       Class excClass = null;
/*     */       try {
/* 330 */         excClass = Class.forName(excName);
/* 331 */         RecognitionException e = (RecognitionException)excClass.newInstance();
/*     */         
/* 333 */         e.index = Integer.parseInt(indexS);
/* 334 */         e.line = Integer.parseInt(lineS);
/* 335 */         e.charPositionInLine = Integer.parseInt(posS);
/* 336 */         this.listener.recognitionException(e);
/*     */       }
/* 338 */       catch (ClassNotFoundException cnfe) {
/* 339 */         System.err.println("can't find class " + cnfe);
/* 340 */         cnfe.printStackTrace(System.err);
/*     */       }
/* 342 */       catch (InstantiationException ie) {
/* 343 */         System.err.println("can't instantiate class " + ie);
/* 344 */         ie.printStackTrace(System.err);
/*     */       }
/* 346 */       catch (IllegalAccessException iae) {
/* 347 */         System.err.println("can't access class " + iae);
/* 348 */         iae.printStackTrace(System.err);
/*     */       }
/*     */     
/* 351 */     } else if (elements[0].equals("beginResync")) {
/* 352 */       this.listener.beginResync();
/*     */     }
/* 354 */     else if (elements[0].equals("endResync")) {
/* 355 */       this.listener.endResync();
/*     */     }
/* 357 */     else if (elements[0].equals("terminate")) {
/* 358 */       this.listener.terminate();
/*     */     }
/* 360 */     else if (elements[0].equals("semanticPredicate")) {
/* 361 */       Boolean result = Boolean.valueOf(elements[1]);
/* 362 */       String predicateText = elements[2];
/* 363 */       predicateText = unEscapeNewlines(predicateText);
/* 364 */       this.listener.semanticPredicate(result.booleanValue(), predicateText);
/*     */     
/*     */     }
/* 367 */     else if (elements[0].equals("consumeNode")) {
/* 368 */       ProxyTree node = deserializeNode(elements, 1);
/* 369 */       this.listener.consumeNode(node);
/*     */     }
/* 371 */     else if (elements[0].equals("LN")) {
/* 372 */       int i = Integer.parseInt(elements[1]);
/* 373 */       ProxyTree node = deserializeNode(elements, 2);
/* 374 */       this.listener.LT(i, node);
/*     */     }
/* 376 */     else if (elements[0].equals("createNodeFromTokenElements")) {
/* 377 */       int ID = Integer.parseInt(elements[1]);
/* 378 */       int type = Integer.parseInt(elements[2]);
/* 379 */       String text = elements[3];
/* 380 */       text = unEscapeNewlines(text);
/* 381 */       ProxyTree node = new ProxyTree(ID, type, -1, -1, -1, text);
/* 382 */       this.listener.createNode(node);
/*     */     }
/* 384 */     else if (elements[0].equals("createNode")) {
/* 385 */       int ID = Integer.parseInt(elements[1]);
/* 386 */       int tokenIndex = Integer.parseInt(elements[2]);
/*     */       
/* 388 */       ProxyTree node = new ProxyTree(ID);
/* 389 */       ProxyToken token = new ProxyToken(tokenIndex);
/* 390 */       this.listener.createNode(node, token);
/*     */     }
/* 392 */     else if (elements[0].equals("nilNode")) {
/* 393 */       int ID = Integer.parseInt(elements[1]);
/* 394 */       ProxyTree node = new ProxyTree(ID);
/* 395 */       this.listener.nilNode(node);
/*     */     }
/* 397 */     else if (elements[0].equals("errorNode")) {
/*     */       
/* 399 */       int ID = Integer.parseInt(elements[1]);
/* 400 */       int type = Integer.parseInt(elements[2]);
/* 401 */       String text = elements[3];
/* 402 */       text = unEscapeNewlines(text);
/* 403 */       ProxyTree node = new ProxyTree(ID, type, -1, -1, -1, text);
/* 404 */       this.listener.errorNode(node);
/*     */     }
/* 406 */     else if (elements[0].equals("becomeRoot")) {
/* 407 */       int newRootID = Integer.parseInt(elements[1]);
/* 408 */       int oldRootID = Integer.parseInt(elements[2]);
/* 409 */       ProxyTree newRoot = new ProxyTree(newRootID);
/* 410 */       ProxyTree oldRoot = new ProxyTree(oldRootID);
/* 411 */       this.listener.becomeRoot(newRoot, oldRoot);
/*     */     }
/* 413 */     else if (elements[0].equals("addChild")) {
/* 414 */       int rootID = Integer.parseInt(elements[1]);
/* 415 */       int childID = Integer.parseInt(elements[2]);
/* 416 */       ProxyTree root = new ProxyTree(rootID);
/* 417 */       ProxyTree child = new ProxyTree(childID);
/* 418 */       this.listener.addChild(root, child);
/*     */     }
/* 420 */     else if (elements[0].equals("setTokenBoundaries")) {
/* 421 */       int ID = Integer.parseInt(elements[1]);
/* 422 */       ProxyTree node = new ProxyTree(ID);
/* 423 */       this.listener.setTokenBoundaries(node, Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 429 */       System.err.println("unknown debug event: " + line);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ProxyTree deserializeNode(String[] elements, int offset) {
/* 434 */     int ID = Integer.parseInt(elements[offset + 0]);
/* 435 */     int type = Integer.parseInt(elements[offset + 1]);
/* 436 */     int tokenLine = Integer.parseInt(elements[offset + 2]);
/* 437 */     int charPositionInLine = Integer.parseInt(elements[offset + 3]);
/* 438 */     int tokenIndex = Integer.parseInt(elements[offset + 4]);
/* 439 */     String text = elements[offset + 5];
/* 440 */     text = unEscapeNewlines(text);
/* 441 */     return new ProxyTree(ID, type, tokenLine, charPositionInLine, tokenIndex, text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProxyToken deserializeToken(String[] elements, int offset) {
/* 447 */     String indexS = elements[offset + 0];
/* 448 */     String typeS = elements[offset + 1];
/* 449 */     String channelS = elements[offset + 2];
/* 450 */     String lineS = elements[offset + 3];
/* 451 */     String posS = elements[offset + 4];
/* 452 */     String text = elements[offset + 5];
/* 453 */     text = unEscapeNewlines(text);
/* 454 */     int index = Integer.parseInt(indexS);
/* 455 */     ProxyToken t = new ProxyToken(index, Integer.parseInt(typeS), Integer.parseInt(channelS), Integer.parseInt(lineS), Integer.parseInt(posS), text);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 462 */     return t;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/* 467 */     Thread t = new Thread(this);
/* 468 */     t.start();
/*     */   }
/*     */   
/*     */   public void run() {
/* 472 */     eventHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getEventElements(String event) {
/* 478 */     if (event == null) {
/* 479 */       return null;
/*     */     }
/* 481 */     String[] elements = new String[8];
/* 482 */     String str = null;
/*     */     try {
/* 484 */       int firstQuoteIndex = event.indexOf('"');
/* 485 */       if (firstQuoteIndex >= 0) {
/*     */ 
/*     */ 
/*     */         
/* 489 */         String eventWithoutString = event.substring(0, firstQuoteIndex);
/* 490 */         str = event.substring(firstQuoteIndex + 1, event.length());
/* 491 */         event = eventWithoutString;
/*     */       } 
/* 493 */       StringTokenizer st = new StringTokenizer(event, "\t", false);
/* 494 */       int i = 0;
/* 495 */       while (st.hasMoreTokens()) {
/* 496 */         if (i >= 8)
/*     */         {
/* 498 */           return elements;
/*     */         }
/* 500 */         elements[i] = st.nextToken();
/* 501 */         i++;
/*     */       } 
/* 503 */       if (str != null) {
/* 504 */         elements[i] = str;
/*     */       }
/*     */     }
/* 507 */     catch (Exception e) {
/* 508 */       e.printStackTrace(System.err);
/*     */     } 
/* 510 */     return elements;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String unEscapeNewlines(String txt) {
/* 515 */     txt = txt.replaceAll("%0A", "\n");
/* 516 */     txt = txt.replaceAll("%0D", "\r");
/* 517 */     txt = txt.replaceAll("%25", "%");
/* 518 */     return txt;
/*     */   }
/*     */   
/*     */   public boolean tokenIndexesAreInvalid() {
/* 522 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\RemoteDebugEventSocketListener.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */