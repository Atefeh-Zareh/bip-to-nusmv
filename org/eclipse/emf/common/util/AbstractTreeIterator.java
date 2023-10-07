/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTreeIterator<E>
/*     */   extends BasicEList<Iterator<? extends E>>
/*     */   implements TreeIterator<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected boolean includeRoot;
/*     */   protected Object object;
/*     */   protected Iterator<? extends E> nextPruneIterator;
/*     */   protected Iterator<? extends E> nextRemoveIterator;
/*     */   
/*     */   public AbstractTreeIterator(E object) {
/*  60 */     this.object = object;
/*  61 */     this.includeRoot = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTreeIterator(Object object, boolean includeRoot) {
/*  72 */     this.object = object;
/*  73 */     this.includeRoot = includeRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Iterator<? extends E> getChildren(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/*  89 */     if (this.data == null && !this.includeRoot)
/*     */     {
/*  91 */       return hasAnyChildren();
/*     */     }
/*     */ 
/*     */     
/*  95 */     return hasMoreChildren();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasAnyChildren() {
/* 101 */     Iterator<? extends E> nextPruneIterator = this.nextPruneIterator;
/*     */     
/* 103 */     nextPruneIterator = getChildren(this.object);
/* 104 */     add(nextPruneIterator);
/* 105 */     return nextPruneIterator.hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasMoreChildren() {
/* 112 */     return !(this.data != null && (isEmpty() || !((Iterator)this.data[this.size - 1]).hasNext()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E next() {
/* 123 */     if (this.data == null) {
/*     */ 
/*     */ 
/*     */       
/* 127 */       this.nextPruneIterator = getChildren(this.object);
/* 128 */       add(this.nextPruneIterator);
/* 129 */       if (this.includeRoot) {
/*     */         
/* 131 */         E e = (E)this.object;
/* 132 */         return e;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 138 */     Iterator<? extends E> currentIterator = (Iterator<? extends E>)this.data[this.size - 1];
/* 139 */     E result = currentIterator.next();
/* 140 */     this.nextRemoveIterator = currentIterator;
/*     */ 
/*     */ 
/*     */     
/* 144 */     Iterator<? extends E> iterator = getChildren(result);
/* 145 */     if (iterator.hasNext()) {
/*     */ 
/*     */ 
/*     */       
/* 149 */       this.nextPruneIterator = iterator;
/* 150 */       add(iterator);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 156 */       this.nextPruneIterator = null;
/*     */ 
/*     */ 
/*     */       
/* 160 */       while (!currentIterator.hasNext()) {
/*     */ 
/*     */ 
/*     */         
/* 164 */         this.data[--this.size] = null;
/*     */ 
/*     */ 
/*     */         
/* 168 */         if (isEmpty()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         Iterator<? extends E> nextIterator = (Iterator<? extends E>)this.data[this.size - 1];
/* 176 */         currentIterator = nextIterator;
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     return result;
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
/*     */   public void remove() {
/* 193 */     if (this.nextRemoveIterator == null)
/*     */     {
/* 195 */       throw new IllegalStateException("There is no valid object to remove.");
/*     */     }
/* 197 */     this.nextRemoveIterator.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void prune() {
/* 207 */     if (this.nextPruneIterator != null) {
/*     */ 
/*     */ 
/*     */       
/* 211 */       if (!isEmpty() && this.data[this.size - 1] == this.nextPruneIterator) {
/*     */ 
/*     */ 
/*     */         
/* 215 */         this.data[--this.size] = null;
/*     */ 
/*     */ 
/*     */         
/* 219 */         while (!isEmpty() && !((Iterator)this.data[this.size - 1]).hasNext())
/*     */         {
/* 221 */           this.data[--this.size] = null;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 227 */       this.nextPruneIterator = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\AbstractTreeIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */