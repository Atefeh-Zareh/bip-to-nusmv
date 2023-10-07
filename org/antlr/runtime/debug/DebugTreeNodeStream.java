/*     */ package org.antlr.runtime.debug;
/*     */ 
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.tree.TreeAdaptor;
/*     */ import org.antlr.runtime.tree.TreeNodeStream;
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
/*     */ public class DebugTreeNodeStream
/*     */   implements TreeNodeStream
/*     */ {
/*     */   protected DebugEventListener dbg;
/*     */   protected TreeAdaptor adaptor;
/*     */   protected TreeNodeStream input;
/*     */   protected boolean initialStreamState = true;
/*     */   protected int lastMarker;
/*     */   
/*     */   public DebugTreeNodeStream(TreeNodeStream input, DebugEventListener dbg) {
/*  50 */     this.input = input;
/*  51 */     this.adaptor = input.getTreeAdaptor();
/*  52 */     this.input.setUniqueNavigationNodes(true);
/*  53 */     setDebugListener(dbg);
/*     */   }
/*     */   
/*     */   public void setDebugListener(DebugEventListener dbg) {
/*  57 */     this.dbg = dbg;
/*     */   }
/*     */   
/*     */   public TreeAdaptor getTreeAdaptor() {
/*  61 */     return this.adaptor;
/*     */   }
/*     */   
/*     */   public void consume() {
/*  65 */     Object node = this.input.LT(1);
/*  66 */     this.input.consume();
/*  67 */     this.dbg.consumeNode(node);
/*     */   }
/*     */   
/*     */   public Object get(int i) {
/*  71 */     return this.input.get(i);
/*     */   }
/*     */   
/*     */   public Object LT(int i) {
/*  75 */     Object node = this.input.LT(i);
/*  76 */     int ID = this.adaptor.getUniqueID(node);
/*  77 */     String text = this.adaptor.getText(node);
/*  78 */     int type = this.adaptor.getType(node);
/*  79 */     this.dbg.LT(i, node);
/*  80 */     return node;
/*     */   }
/*     */   
/*     */   public int LA(int i) {
/*  84 */     Object node = this.input.LT(i);
/*  85 */     int ID = this.adaptor.getUniqueID(node);
/*  86 */     String text = this.adaptor.getText(node);
/*  87 */     int type = this.adaptor.getType(node);
/*  88 */     this.dbg.LT(i, node);
/*  89 */     return type;
/*     */   }
/*     */   
/*     */   public int mark() {
/*  93 */     this.lastMarker = this.input.mark();
/*  94 */     this.dbg.mark(this.lastMarker);
/*  95 */     return this.lastMarker;
/*     */   }
/*     */   
/*     */   public int index() {
/*  99 */     return this.input.index();
/*     */   }
/*     */   
/*     */   public void rewind(int marker) {
/* 103 */     this.dbg.rewind(marker);
/* 104 */     this.input.rewind(marker);
/*     */   }
/*     */   
/*     */   public void rewind() {
/* 108 */     this.dbg.rewind();
/* 109 */     this.input.rewind(this.lastMarker);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void release(int marker) {}
/*     */ 
/*     */   
/*     */   public void seek(int index) {
/* 118 */     this.input.seek(index);
/*     */   }
/*     */   
/*     */   public int size() {
/* 122 */     return this.input.size();
/*     */   }
/*     */   
/*     */   public void reset() {}
/*     */   
/*     */   public Object getTreeSource() {
/* 128 */     return this.input;
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/* 132 */     return getTokenStream().getSourceName();
/*     */   }
/*     */   
/*     */   public TokenStream getTokenStream() {
/* 136 */     return this.input.getTokenStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUniqueNavigationNodes(boolean uniqueNavigationNodes) {
/* 145 */     this.input.setUniqueNavigationNodes(uniqueNavigationNodes);
/*     */   }
/*     */   
/*     */   public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
/* 149 */     this.input.replaceChildren(parent, startChildIndex, stopChildIndex, t);
/*     */   }
/*     */   
/*     */   public String toString(Object start, Object stop) {
/* 153 */     return this.input.toString(start, stop);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\DebugTreeNodeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */