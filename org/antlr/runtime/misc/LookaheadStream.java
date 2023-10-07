/*     */ package org.antlr.runtime.misc;
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
/*     */ public abstract class LookaheadStream<T>
/*     */   extends FastQueue
/*     */ {
/*     */   public static final int UNINITIALIZED_EOF_ELEMENT_INDEX = 2147483647;
/*  42 */   protected int eofElementIndex = Integer.MAX_VALUE;
/*     */ 
/*     */   
/*  45 */   public T eof = null;
/*     */ 
/*     */   
/*     */   protected int lastMarker;
/*     */ 
/*     */   
/*  51 */   protected int markDepth = 0;
/*     */   
/*     */   public LookaheadStream(T eof) {
/*  54 */     this.eof = eof;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  58 */     this.eofElementIndex = Integer.MAX_VALUE;
/*  59 */     super.reset();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract T nextElement();
/*     */ 
/*     */ 
/*     */   
/*     */   public T remove() {
/*  69 */     T o = get(0);
/*  70 */     this.p++;
/*     */     
/*  72 */     if (this.p == this.data.size() && this.markDepth == 0)
/*     */     {
/*  74 */       clear();
/*     */     }
/*  76 */     return o;
/*     */   }
/*     */   
/*     */   public void consume() {
/*  80 */     sync(1); remove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sync(int need) {
/*  87 */     int n = this.p + need - 1 - this.data.size() + 1;
/*  88 */     if (n > 0) fill(n);
/*     */   
/*     */   }
/*     */   
/*     */   public void fill(int n) {
/*  93 */     for (int i = 1; i <= n; i++) {
/*  94 */       T o = nextElement();
/*  95 */       if (o == this.eof) {
/*  96 */         this.data.add(this.eof);
/*  97 */         this.eofElementIndex = this.data.size() - 1;
/*     */       } else {
/*  99 */         this.data.add(o);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 106 */     throw new UnsupportedOperationException("streams are of unknown size");
/*     */   }
/*     */   public Object LT(int k) {
/* 109 */     if (k == 0) {
/* 110 */       return null;
/*     */     }
/* 112 */     if (k < 0) {
/* 113 */       return LB(-k);
/*     */     }
/*     */     
/* 116 */     if (this.p + k - 1 >= this.eofElementIndex) {
/* 117 */       return this.eof;
/*     */     }
/* 119 */     sync(k);
/* 120 */     return get(k - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object LB(int k) {
/* 125 */     if (k == 0) {
/* 126 */       return null;
/*     */     }
/* 128 */     if (this.p - k < 0) {
/* 129 */       return null;
/*     */     }
/* 131 */     return get(-k);
/*     */   }
/*     */   public Object getCurrentSymbol() {
/* 134 */     return LT(1);
/*     */   } public int index() {
/* 136 */     return this.p;
/*     */   }
/*     */   public int mark() {
/* 139 */     this.markDepth++;
/* 140 */     this.lastMarker = index();
/* 141 */     return this.lastMarker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void release(int marker) {}
/*     */ 
/*     */   
/*     */   public void rewind(int marker) {
/* 149 */     this.markDepth--;
/* 150 */     seek(marker);
/*     */   }
/*     */ 
/*     */   
/*     */   public void rewind() {
/* 155 */     seek(this.lastMarker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(int index) {
/* 162 */     this.p = index;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\misc\LookaheadStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */