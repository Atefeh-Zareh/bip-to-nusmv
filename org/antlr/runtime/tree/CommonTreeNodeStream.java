/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.misc.IntArray;
/*     */ import org.antlr.runtime.misc.LookaheadStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonTreeNodeStream
/*     */   extends LookaheadStream
/*     */   implements TreeNodeStream
/*     */ {
/*     */   public static final int DEFAULT_INITIAL_BUFFER_SIZE = 100;
/*     */   public static final int INITIAL_CALL_STACK_SIZE = 10;
/*     */   protected Object root;
/*     */   protected TokenStream tokens;
/*     */   TreeAdaptor adaptor;
/*     */   protected TreeIterator it;
/*     */   protected IntArray calls;
/*     */   protected boolean hasNilRoot = false;
/*  60 */   protected int level = 0;
/*     */   
/*     */   public CommonTreeNodeStream(Object tree) {
/*  63 */     this(new CommonTreeAdaptor(), tree);
/*     */   }
/*     */   
/*     */   public CommonTreeNodeStream(TreeAdaptor adaptor, Object tree) {
/*  67 */     super(adaptor.create(-1, "EOF"));
/*  68 */     this.root = tree;
/*  69 */     this.adaptor = adaptor;
/*  70 */     this.it = new TreeIterator(this.root);
/*  71 */     this.it.eof = this.eof;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  75 */     super.reset();
/*  76 */     this.it.reset();
/*  77 */     this.hasNilRoot = false;
/*  78 */     this.level = 0;
/*  79 */     if (this.calls != null) this.calls.clear();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object nextElement() {
/*  86 */     Object t = this.it.next();
/*     */     
/*  88 */     if (t == this.it.up)
/*  89 */     { this.level--;
/*  90 */       if (this.level == 0 && this.hasNilRoot) return this.it.next();
/*     */        }
/*  92 */     else if (t == this.it.down) { this.level++; }
/*  93 */      if (this.level == 0 && this.adaptor.isNil(t)) {
/*  94 */       this.hasNilRoot = true;
/*  95 */       t = this.it.next();
/*  96 */       this.level++;
/*  97 */       t = this.it.next();
/*     */     } 
/*  99 */     return t;
/*     */   }
/*     */   public void setUniqueNavigationNodes(boolean uniqueNavigationNodes) {}
/*     */   
/*     */   public Object getTreeSource() {
/* 104 */     return this.root;
/*     */   } public String getSourceName() {
/* 106 */     return getTokenStream().getSourceName();
/*     */   } public TokenStream getTokenStream() {
/* 108 */     return this.tokens;
/*     */   } public void setTokenStream(TokenStream tokens) {
/* 110 */     this.tokens = tokens;
/*     */   } public TreeAdaptor getTreeAdaptor() {
/* 112 */     return this.adaptor;
/*     */   } public void setTreeAdaptor(TreeAdaptor adaptor) {
/* 114 */     this.adaptor = adaptor;
/*     */   } public int LA(int i) {
/* 116 */     return this.adaptor.getType(LT(i));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(int index) {
/* 122 */     if (this.calls == null) {
/* 123 */       this.calls = new IntArray();
/*     */     }
/* 125 */     this.calls.push(this.p);
/* 126 */     seek(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int pop() {
/* 133 */     int ret = this.calls.pop();
/* 134 */     seek(ret);
/* 135 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
/* 141 */     if (parent != null) {
/* 142 */       this.adaptor.replaceChildren(parent, startChildIndex, stopChildIndex, t);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Object start, Object stop) {
/* 149 */     return "n/a";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toTokenTypeString() {
/* 154 */     reset();
/* 155 */     StringBuffer buf = new StringBuffer();
/* 156 */     Object o = LT(1);
/* 157 */     int type = this.adaptor.getType(o);
/* 158 */     while (type != -1) {
/* 159 */       buf.append(" ");
/* 160 */       buf.append(type);
/* 161 */       consume();
/* 162 */       o = LT(1);
/* 163 */       type = this.adaptor.getType(o);
/*     */     } 
/* 165 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\CommonTreeNodeStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */