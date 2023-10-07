/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.misc.IntArray;
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
/*     */ public class BufferedTreeNodeStream
/*     */   implements TreeNodeStream
/*     */ {
/*     */   public static final int DEFAULT_INITIAL_BUFFER_SIZE = 100;
/*     */   public static final int INITIAL_CALL_STACK_SIZE = 10;
/*     */   protected Object down;
/*     */   protected Object up;
/*     */   protected Object eof;
/*     */   protected List nodes;
/*     */   protected Object root;
/*     */   protected TokenStream tokens;
/*     */   TreeAdaptor adaptor;
/*     */   
/*     */   protected class StreamIterator
/*     */     implements Iterator
/*     */   {
/*  62 */     int i = 0;
/*     */     public boolean hasNext() {
/*  64 */       return (this.i < BufferedTreeNodeStream.this.nodes.size());
/*     */     }
/*     */     private final BufferedTreeNodeStream this$0;
/*     */     public Object next() {
/*  68 */       int current = this.i;
/*  69 */       this.i++;
/*  70 */       if (current < BufferedTreeNodeStream.this.nodes.size()) {
/*  71 */         return BufferedTreeNodeStream.this.nodes.get(current);
/*     */       }
/*  73 */       return BufferedTreeNodeStream.this.eof;
/*     */     }
/*     */     
/*     */     public void remove() {
/*  77 */       throw new RuntimeException("cannot remove nodes from stream");
/*     */     }
/*     */   }
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
/*     */   protected boolean uniqueNavigationNodes = false;
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
/* 114 */   protected int p = -1;
/*     */ 
/*     */   
/*     */   protected int lastMarker;
/*     */   
/*     */   protected IntArray calls;
/*     */ 
/*     */   
/*     */   public BufferedTreeNodeStream(Object tree) {
/* 123 */     this(new CommonTreeAdaptor(), tree);
/*     */   }
/*     */   
/*     */   public BufferedTreeNodeStream(TreeAdaptor adaptor, Object tree) {
/* 127 */     this(adaptor, tree, 100);
/*     */   }
/*     */   
/*     */   public BufferedTreeNodeStream(TreeAdaptor adaptor, Object tree, int initialBufferSize) {
/* 131 */     this.root = tree;
/* 132 */     this.adaptor = adaptor;
/* 133 */     this.nodes = new ArrayList(initialBufferSize);
/* 134 */     this.down = adaptor.create(2, "DOWN");
/* 135 */     this.up = adaptor.create(3, "UP");
/* 136 */     this.eof = adaptor.create(-1, "EOF");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillBuffer() {
/* 143 */     fillBuffer(this.root);
/*     */     
/* 145 */     this.p = 0;
/*     */   }
/*     */   
/*     */   public void fillBuffer(Object t) {
/* 149 */     boolean nil = this.adaptor.isNil(t);
/* 150 */     if (!nil) {
/* 151 */       this.nodes.add(t);
/*     */     }
/*     */     
/* 154 */     int n = this.adaptor.getChildCount(t);
/* 155 */     if (!nil && n > 0) {
/* 156 */       addNavigationNode(2);
/*     */     }
/*     */     
/* 159 */     for (int c = 0; c < n; c++) {
/* 160 */       Object child = this.adaptor.getChild(t, c);
/* 161 */       fillBuffer(child);
/*     */     } 
/*     */     
/* 164 */     if (!nil && n > 0) {
/* 165 */       addNavigationNode(3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNodeIndex(Object node) {
/* 173 */     if (this.p == -1) {
/* 174 */       fillBuffer();
/*     */     }
/* 176 */     for (int i = 0; i < this.nodes.size(); i++) {
/* 177 */       Object t = this.nodes.get(i);
/* 178 */       if (t == node) {
/* 179 */         return i;
/*     */       }
/*     */     } 
/* 182 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addNavigationNode(int ttype) {
/* 190 */     Object navNode = null;
/* 191 */     if (ttype == 2) {
/* 192 */       if (hasUniqueNavigationNodes()) {
/* 193 */         navNode = this.adaptor.create(2, "DOWN");
/*     */       } else {
/*     */         
/* 196 */         navNode = this.down;
/*     */       }
/*     */     
/*     */     }
/* 200 */     else if (hasUniqueNavigationNodes()) {
/* 201 */       navNode = this.adaptor.create(3, "UP");
/*     */     } else {
/*     */       
/* 204 */       navNode = this.up;
/*     */     } 
/*     */     
/* 207 */     this.nodes.add(navNode);
/*     */   }
/*     */   
/*     */   public Object get(int i) {
/* 211 */     if (this.p == -1) {
/* 212 */       fillBuffer();
/*     */     }
/* 214 */     return this.nodes.get(i);
/*     */   }
/*     */   
/*     */   public Object LT(int k) {
/* 218 */     if (this.p == -1) {
/* 219 */       fillBuffer();
/*     */     }
/* 221 */     if (k == 0) {
/* 222 */       return null;
/*     */     }
/* 224 */     if (k < 0) {
/* 225 */       return LB(-k);
/*     */     }
/*     */     
/* 228 */     if (this.p + k - 1 >= this.nodes.size()) {
/* 229 */       return this.eof;
/*     */     }
/* 231 */     return this.nodes.get(this.p + k - 1);
/*     */   }
/*     */   public Object getCurrentSymbol() {
/* 234 */     return LT(1);
/*     */   }
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
/*     */   protected Object LB(int k) {
/* 257 */     if (k == 0) {
/* 258 */       return null;
/*     */     }
/* 260 */     if (this.p - k < 0) {
/* 261 */       return null;
/*     */     }
/* 263 */     return this.nodes.get(this.p - k);
/*     */   }
/*     */   
/*     */   public Object getTreeSource() {
/* 267 */     return this.root;
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/* 271 */     return getTokenStream().getSourceName();
/*     */   }
/*     */   
/*     */   public TokenStream getTokenStream() {
/* 275 */     return this.tokens;
/*     */   }
/*     */   
/*     */   public void setTokenStream(TokenStream tokens) {
/* 279 */     this.tokens = tokens;
/*     */   }
/*     */   
/*     */   public TreeAdaptor getTreeAdaptor() {
/* 283 */     return this.adaptor;
/*     */   }
/*     */   
/*     */   public void setTreeAdaptor(TreeAdaptor adaptor) {
/* 287 */     this.adaptor = adaptor;
/*     */   }
/*     */   
/*     */   public boolean hasUniqueNavigationNodes() {
/* 291 */     return this.uniqueNavigationNodes;
/*     */   }
/*     */   
/*     */   public void setUniqueNavigationNodes(boolean uniqueNavigationNodes) {
/* 295 */     this.uniqueNavigationNodes = uniqueNavigationNodes;
/*     */   }
/*     */   
/*     */   public void consume() {
/* 299 */     if (this.p == -1) {
/* 300 */       fillBuffer();
/*     */     }
/* 302 */     this.p++;
/*     */   }
/*     */   
/*     */   public int LA(int i) {
/* 306 */     return this.adaptor.getType(LT(i));
/*     */   }
/*     */   
/*     */   public int mark() {
/* 310 */     if (this.p == -1) {
/* 311 */       fillBuffer();
/*     */     }
/* 313 */     this.lastMarker = index();
/* 314 */     return this.lastMarker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void release(int marker) {}
/*     */ 
/*     */   
/*     */   public int index() {
/* 322 */     return this.p;
/*     */   }
/*     */   
/*     */   public void rewind(int marker) {
/* 326 */     seek(marker);
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 330 */     seek(this.lastMarker);
/*     */   }
/*     */   
/*     */   public void seek(int index) {
/* 334 */     if (this.p == -1) {
/* 335 */       fillBuffer();
/*     */     }
/* 337 */     this.p = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(int index) {
/* 344 */     if (this.calls == null) {
/* 345 */       this.calls = new IntArray();
/*     */     }
/* 347 */     this.calls.push(this.p);
/* 348 */     seek(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int pop() {
/* 355 */     int ret = this.calls.pop();
/* 356 */     seek(ret);
/* 357 */     return ret;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 361 */     this.p = 0;
/* 362 */     this.lastMarker = 0;
/* 363 */     if (this.calls != null) {
/* 364 */       this.calls.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public int size() {
/* 369 */     if (this.p == -1) {
/* 370 */       fillBuffer();
/*     */     }
/* 372 */     return this.nodes.size();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 376 */     if (this.p == -1) {
/* 377 */       fillBuffer();
/*     */     }
/* 379 */     return new StreamIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
/* 385 */     if (parent != null) {
/* 386 */       this.adaptor.replaceChildren(parent, startChildIndex, stopChildIndex, t);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toTokenTypeString() {
/* 392 */     if (this.p == -1) {
/* 393 */       fillBuffer();
/*     */     }
/* 395 */     StringBuffer buf = new StringBuffer();
/* 396 */     for (int i = 0; i < this.nodes.size(); i++) {
/* 397 */       Object t = this.nodes.get(i);
/* 398 */       buf.append(" ");
/* 399 */       buf.append(this.adaptor.getType(t));
/*     */     } 
/* 401 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toTokenString(int start, int stop) {
/* 406 */     if (this.p == -1) {
/* 407 */       fillBuffer();
/*     */     }
/* 409 */     StringBuffer buf = new StringBuffer();
/* 410 */     for (int i = start; i < this.nodes.size() && i <= stop; i++) {
/* 411 */       Object t = this.nodes.get(i);
/* 412 */       buf.append(" ");
/* 413 */       buf.append(this.adaptor.getToken(t));
/*     */     } 
/* 415 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String toString(Object start, Object stop) {
/* 419 */     System.out.println("toString");
/* 420 */     if (start == null || stop == null) {
/* 421 */       return null;
/*     */     }
/* 423 */     if (this.p == -1) {
/* 424 */       fillBuffer();
/*     */     }
/*     */     
/* 427 */     if (start instanceof CommonTree) {
/* 428 */       System.out.print("toString: " + ((CommonTree)start).getToken() + ", ");
/*     */     } else {
/* 430 */       System.out.println(start);
/* 431 */     }  if (stop instanceof CommonTree) {
/* 432 */       System.out.println(((CommonTree)stop).getToken());
/*     */     } else {
/* 434 */       System.out.println(stop);
/*     */     } 
/* 436 */     if (this.tokens != null) {
/* 437 */       int beginTokenIndex = this.adaptor.getTokenStartIndex(start);
/* 438 */       int endTokenIndex = this.adaptor.getTokenStopIndex(stop);
/*     */ 
/*     */       
/* 441 */       if (this.adaptor.getType(stop) == 3) {
/* 442 */         endTokenIndex = this.adaptor.getTokenStopIndex(start);
/*     */       }
/* 444 */       else if (this.adaptor.getType(stop) == -1) {
/* 445 */         endTokenIndex = size() - 2;
/*     */       } 
/* 447 */       return this.tokens.toString(beginTokenIndex, endTokenIndex);
/*     */     } 
/*     */     
/* 450 */     Object t = null;
/* 451 */     int i = 0;
/* 452 */     for (; i < this.nodes.size(); i++) {
/* 453 */       t = this.nodes.get(i);
/* 454 */       if (t == start) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 459 */     StringBuffer buf = new StringBuffer();
/* 460 */     t = this.nodes.get(i);
/* 461 */     while (t != stop) {
/* 462 */       String str = this.adaptor.getText(t);
/* 463 */       if (str == null) {
/* 464 */         str = " " + String.valueOf(this.adaptor.getType(t));
/*     */       }
/* 466 */       buf.append(str);
/* 467 */       i++;
/* 468 */       t = this.nodes.get(i);
/*     */     } 
/*     */     
/* 471 */     String text = this.adaptor.getText(stop);
/* 472 */     if (text == null) {
/* 473 */       text = " " + String.valueOf(this.adaptor.getType(stop));
/*     */     }
/* 475 */     buf.append(text);
/* 476 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\BufferedTreeNodeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */