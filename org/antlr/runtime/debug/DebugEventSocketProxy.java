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
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import org.antlr.runtime.BaseRecognizer;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.Token;
/*     */ import org.antlr.runtime.tree.TreeAdaptor;
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
/*     */ public class DebugEventSocketProxy
/*     */   extends BlankDebugEventListener
/*     */ {
/*     */   public static final int DEFAULT_DEBUGGER_PORT = 49100;
/*  47 */   protected int port = 49100;
/*     */   
/*     */   protected ServerSocket serverSocket;
/*     */   
/*     */   protected Socket socket;
/*     */   
/*     */   protected String grammarFileName;
/*     */   
/*     */   protected PrintWriter out;
/*     */   
/*     */   protected BufferedReader in;
/*     */   
/*     */   protected BaseRecognizer recognizer;
/*     */   
/*     */   protected TreeAdaptor adaptor;
/*     */   
/*     */   public DebugEventSocketProxy(BaseRecognizer recognizer, TreeAdaptor adaptor) {
/*  64 */     this(recognizer, 49100, adaptor);
/*     */   }
/*     */   
/*     */   public DebugEventSocketProxy(BaseRecognizer recognizer, int port, TreeAdaptor adaptor) {
/*  68 */     this.grammarFileName = recognizer.getGrammarFileName();
/*  69 */     this.adaptor = adaptor;
/*  70 */     this.port = port;
/*     */   }
/*     */   
/*     */   public void handshake() throws IOException {
/*  74 */     if (this.serverSocket == null) {
/*  75 */       this.serverSocket = new ServerSocket(this.port);
/*  76 */       this.socket = this.serverSocket.accept();
/*  77 */       this.socket.setTcpNoDelay(true);
/*  78 */       OutputStream os = this.socket.getOutputStream();
/*  79 */       OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
/*  80 */       this.out = new PrintWriter(new BufferedWriter(osw));
/*  81 */       InputStream is = this.socket.getInputStream();
/*  82 */       InputStreamReader isr = new InputStreamReader(is, "UTF8");
/*  83 */       this.in = new BufferedReader(isr);
/*  84 */       this.out.println("ANTLR 2");
/*  85 */       this.out.println("grammar \"" + this.grammarFileName);
/*  86 */       this.out.flush();
/*  87 */       ack();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void commence() {}
/*     */ 
/*     */   
/*     */   public void terminate() {
/*  96 */     transmit("terminate");
/*  97 */     this.out.close();
/*     */     try {
/*  99 */       this.socket.close();
/*     */     }
/* 101 */     catch (IOException ioe) {
/* 102 */       ioe.printStackTrace(System.err);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ack() {
/*     */     try {
/* 108 */       this.in.readLine();
/*     */     }
/* 110 */     catch (IOException ioe) {
/* 111 */       ioe.printStackTrace(System.err);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void transmit(String event) {
/* 116 */     this.out.println(event);
/* 117 */     this.out.flush();
/* 118 */     ack();
/*     */   }
/*     */   
/*     */   public void enterRule(String grammarFileName, String ruleName) {
/* 122 */     transmit("enterRule\t" + grammarFileName + "\t" + ruleName);
/*     */   }
/*     */   
/*     */   public void enterAlt(int alt) {
/* 126 */     transmit("enterAlt\t" + alt);
/*     */   }
/*     */   
/*     */   public void exitRule(String grammarFileName, String ruleName) {
/* 130 */     transmit("exitRule\t" + grammarFileName + "\t" + ruleName);
/*     */   }
/*     */   
/*     */   public void enterSubRule(int decisionNumber) {
/* 134 */     transmit("enterSubRule\t" + decisionNumber);
/*     */   }
/*     */   
/*     */   public void exitSubRule(int decisionNumber) {
/* 138 */     transmit("exitSubRule\t" + decisionNumber);
/*     */   }
/*     */   
/*     */   public void enterDecision(int decisionNumber) {
/* 142 */     transmit("enterDecision\t" + decisionNumber);
/*     */   }
/*     */   
/*     */   public void exitDecision(int decisionNumber) {
/* 146 */     transmit("exitDecision\t" + decisionNumber);
/*     */   }
/*     */   
/*     */   public void consumeToken(Token t) {
/* 150 */     String buf = serializeToken(t);
/* 151 */     transmit("consumeToken\t" + buf);
/*     */   }
/*     */   
/*     */   public void consumeHiddenToken(Token t) {
/* 155 */     String buf = serializeToken(t);
/* 156 */     transmit("consumeHiddenToken\t" + buf);
/*     */   }
/*     */   
/*     */   public void LT(int i, Token t) {
/* 160 */     if (t != null)
/* 161 */       transmit("LT\t" + i + "\t" + serializeToken(t)); 
/*     */   }
/*     */   
/*     */   public void mark(int i) {
/* 165 */     transmit("mark\t" + i);
/*     */   }
/*     */   
/*     */   public void rewind(int i) {
/* 169 */     transmit("rewind\t" + i);
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 173 */     transmit("rewind");
/*     */   }
/*     */   
/*     */   public void beginBacktrack(int level) {
/* 177 */     transmit("beginBacktrack\t" + level);
/*     */   }
/*     */   
/*     */   public void endBacktrack(int level, boolean successful) {
/* 181 */     transmit("endBacktrack\t" + level + "\t" + (successful ? 1 : 0));
/*     */   }
/*     */   
/*     */   public void location(int line, int pos) {
/* 185 */     transmit("location\t" + line + "\t" + pos);
/*     */   }
/*     */   
/*     */   public void recognitionException(RecognitionException e) {
/* 189 */     StringBuffer buf = new StringBuffer(50);
/* 190 */     buf.append("exception\t");
/* 191 */     buf.append(e.getClass().getName());
/*     */     
/* 193 */     buf.append("\t");
/* 194 */     buf.append(e.index);
/* 195 */     buf.append("\t");
/* 196 */     buf.append(e.line);
/* 197 */     buf.append("\t");
/* 198 */     buf.append(e.charPositionInLine);
/* 199 */     transmit(buf.toString());
/*     */   }
/*     */   
/*     */   public void beginResync() {
/* 203 */     transmit("beginResync");
/*     */   }
/*     */   
/*     */   public void endResync() {
/* 207 */     transmit("endResync");
/*     */   }
/*     */   
/*     */   public void semanticPredicate(boolean result, String predicate) {
/* 211 */     StringBuffer buf = new StringBuffer(50);
/* 212 */     buf.append("semanticPredicate\t");
/* 213 */     buf.append(result);
/* 214 */     serializeText(buf, predicate);
/* 215 */     transmit(buf.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeNode(Object t) {
/* 221 */     StringBuffer buf = new StringBuffer(50);
/* 222 */     buf.append("consumeNode");
/* 223 */     serializeNode(buf, t);
/* 224 */     transmit(buf.toString());
/*     */   }
/*     */   
/*     */   public void LT(int i, Object t) {
/* 228 */     int ID = this.adaptor.getUniqueID(t);
/* 229 */     String text = this.adaptor.getText(t);
/* 230 */     int type = this.adaptor.getType(t);
/* 231 */     StringBuffer buf = new StringBuffer(50);
/* 232 */     buf.append("LN\t");
/* 233 */     buf.append(i);
/* 234 */     serializeNode(buf, t);
/* 235 */     transmit(buf.toString());
/*     */   }
/*     */   
/*     */   protected void serializeNode(StringBuffer buf, Object t) {
/* 239 */     int ID = this.adaptor.getUniqueID(t);
/* 240 */     String text = this.adaptor.getText(t);
/* 241 */     int type = this.adaptor.getType(t);
/* 242 */     buf.append("\t");
/* 243 */     buf.append(ID);
/* 244 */     buf.append("\t");
/* 245 */     buf.append(type);
/* 246 */     Token token = this.adaptor.getToken(t);
/* 247 */     int line = -1;
/* 248 */     int pos = -1;
/* 249 */     if (token != null) {
/* 250 */       line = token.getLine();
/* 251 */       pos = token.getCharPositionInLine();
/*     */     } 
/* 253 */     buf.append("\t");
/* 254 */     buf.append(line);
/* 255 */     buf.append("\t");
/* 256 */     buf.append(pos);
/* 257 */     int tokenIndex = this.adaptor.getTokenStartIndex(t);
/* 258 */     buf.append("\t");
/* 259 */     buf.append(tokenIndex);
/* 260 */     serializeText(buf, text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nilNode(Object t) {
/* 267 */     int ID = this.adaptor.getUniqueID(t);
/* 268 */     transmit("nilNode\t" + ID);
/*     */   }
/*     */   
/*     */   public void errorNode(Object t) {
/* 272 */     int ID = this.adaptor.getUniqueID(t);
/* 273 */     String text = t.toString();
/* 274 */     StringBuffer buf = new StringBuffer(50);
/* 275 */     buf.append("errorNode\t");
/* 276 */     buf.append(ID);
/* 277 */     buf.append("\t");
/* 278 */     buf.append(0);
/* 279 */     serializeText(buf, text);
/* 280 */     transmit(buf.toString());
/*     */   }
/*     */   
/*     */   public void createNode(Object t) {
/* 284 */     int ID = this.adaptor.getUniqueID(t);
/* 285 */     String text = this.adaptor.getText(t);
/* 286 */     int type = this.adaptor.getType(t);
/* 287 */     StringBuffer buf = new StringBuffer(50);
/* 288 */     buf.append("createNodeFromTokenElements\t");
/* 289 */     buf.append(ID);
/* 290 */     buf.append("\t");
/* 291 */     buf.append(type);
/* 292 */     serializeText(buf, text);
/* 293 */     transmit(buf.toString());
/*     */   }
/*     */   
/*     */   public void createNode(Object node, Token token) {
/* 297 */     int ID = this.adaptor.getUniqueID(node);
/* 298 */     int tokenIndex = token.getTokenIndex();
/* 299 */     transmit("createNode\t" + ID + "\t" + tokenIndex);
/*     */   }
/*     */   
/*     */   public void becomeRoot(Object newRoot, Object oldRoot) {
/* 303 */     int newRootID = this.adaptor.getUniqueID(newRoot);
/* 304 */     int oldRootID = this.adaptor.getUniqueID(oldRoot);
/* 305 */     transmit("becomeRoot\t" + newRootID + "\t" + oldRootID);
/*     */   }
/*     */   
/*     */   public void addChild(Object root, Object child) {
/* 309 */     int rootID = this.adaptor.getUniqueID(root);
/* 310 */     int childID = this.adaptor.getUniqueID(child);
/* 311 */     transmit("addChild\t" + rootID + "\t" + childID);
/*     */   }
/*     */   
/*     */   public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
/* 315 */     int ID = this.adaptor.getUniqueID(t);
/* 316 */     transmit("setTokenBoundaries\t" + ID + "\t" + tokenStartIndex + "\t" + tokenStopIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTreeAdaptor(TreeAdaptor adaptor) {
/* 322 */     this.adaptor = adaptor; } public TreeAdaptor getTreeAdaptor() {
/* 323 */     return this.adaptor;
/*     */   }
/*     */   protected String serializeToken(Token t) {
/* 326 */     StringBuffer buf = new StringBuffer(50);
/* 327 */     buf.append(t.getTokenIndex()); buf.append('\t');
/* 328 */     buf.append(t.getType()); buf.append('\t');
/* 329 */     buf.append(t.getChannel()); buf.append('\t');
/* 330 */     buf.append(t.getLine()); buf.append('\t');
/* 331 */     buf.append(t.getCharPositionInLine());
/* 332 */     serializeText(buf, t.getText());
/* 333 */     return buf.toString();
/*     */   }
/*     */   
/*     */   protected void serializeText(StringBuffer buf, String text) {
/* 337 */     buf.append("\t\"");
/* 338 */     if (text == null) {
/* 339 */       text = "";
/*     */     }
/*     */ 
/*     */     
/* 343 */     text = escapeNewlines(text);
/* 344 */     buf.append(text);
/*     */   }
/*     */   
/*     */   protected String escapeNewlines(String txt) {
/* 348 */     txt = txt.replaceAll("%", "%25");
/* 349 */     txt = txt.replaceAll("\n", "%0A");
/* 350 */     txt = txt.replaceAll("\r", "%0D");
/* 351 */     return txt;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugEventSocketProxy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */