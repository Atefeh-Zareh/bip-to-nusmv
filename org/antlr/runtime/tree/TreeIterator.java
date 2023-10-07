/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.antlr.runtime.misc.FastQueue;
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
/*     */ public class TreeIterator
/*     */   implements Iterator
/*     */ {
/*     */   protected TreeAdaptor adaptor;
/*     */   protected Object root;
/*     */   protected Object tree;
/*     */   protected boolean firstTime = true;
/*     */   public Object up;
/*     */   public Object down;
/*     */   public Object eof;
/*     */   protected FastQueue nodes;
/*     */   
/*     */   public TreeIterator(Object tree) {
/*  57 */     this(new CommonTreeAdaptor(), tree);
/*     */   }
/*     */   
/*     */   public TreeIterator(TreeAdaptor adaptor, Object tree) {
/*  61 */     this.adaptor = adaptor;
/*  62 */     this.tree = tree;
/*  63 */     this.root = tree;
/*  64 */     this.nodes = new FastQueue();
/*  65 */     this.down = adaptor.create(2, "DOWN");
/*  66 */     this.up = adaptor.create(3, "UP");
/*  67 */     this.eof = adaptor.create(-1, "EOF");
/*     */   }
/*     */   
/*     */   public void reset() {
/*  71 */     this.firstTime = true;
/*  72 */     this.tree = this.root;
/*  73 */     this.nodes.clear();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  77 */     if (this.firstTime) return (this.root != null); 
/*  78 */     if (this.nodes != null && this.nodes.size() > 0) return true; 
/*  79 */     if (this.tree == null) return false; 
/*  80 */     if (this.adaptor.getChildCount(this.tree) > 0) return true; 
/*  81 */     return (this.adaptor.getParent(this.tree) != null);
/*     */   }
/*     */   
/*     */   public Object next() {
/*  85 */     if (this.firstTime) {
/*  86 */       this.firstTime = false;
/*  87 */       if (this.adaptor.getChildCount(this.tree) == 0) {
/*  88 */         this.nodes.add(this.eof);
/*  89 */         return this.tree;
/*     */       } 
/*  91 */       return this.tree;
/*     */     } 
/*     */     
/*  94 */     if (this.nodes != null && this.nodes.size() > 0) return this.nodes.remove();
/*     */ 
/*     */     
/*  97 */     if (this.tree == null) return this.eof;
/*     */ 
/*     */     
/* 100 */     if (this.adaptor.getChildCount(this.tree) > 0) {
/* 101 */       this.tree = this.adaptor.getChild(this.tree, 0);
/* 102 */       this.nodes.add(this.tree);
/* 103 */       return this.down;
/*     */     } 
/*     */     
/* 106 */     Object parent = this.adaptor.getParent(this.tree);
/*     */ 
/*     */     
/* 109 */     while (parent != null && this.adaptor.getChildIndex(this.tree) + 1 >= this.adaptor.getChildCount(parent)) {
/*     */       
/* 111 */       this.nodes.add(this.up);
/* 112 */       this.tree = parent;
/* 113 */       parent = this.adaptor.getParent(this.tree);
/*     */     } 
/*     */     
/* 116 */     if (parent == null) {
/* 117 */       this.tree = null;
/* 118 */       this.nodes.add(this.eof);
/* 119 */       return this.nodes.remove();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 124 */     int nextSiblingIndex = this.adaptor.getChildIndex(this.tree) + 1;
/* 125 */     this.tree = this.adaptor.getChild(parent, nextSiblingIndex);
/* 126 */     this.nodes.add(this.tree);
/* 127 */     return this.nodes.remove();
/*     */   }
/*     */   public void remove() {
/* 130 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeIterator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */