/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NotifyingInternalEListImpl<E>
/*     */   extends NotifyingListImpl<E>
/*     */   implements InternalEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public NotifyingInternalEListImpl() {}
/*     */   
/*     */   public NotifyingInternalEListImpl(Collection<? extends E> collection) {
/*  41 */     super(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public NotifyingInternalEListImpl(int initialCapacity) {
/*  46 */     super(initialCapacity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContains(Object object) {
/*  51 */     return contains(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContainsAll(Collection<?> collection) {
/*  56 */     return containsAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicIndexOf(Object object) {
/*  61 */     return indexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicLastIndexOf(Object object) {
/*  66 */     return lastIndexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] basicToArray() {
/*  71 */     return toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] basicToArray(Object[] array) {
/*  76 */     return (T[])toArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/*  82 */     return super.basicList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> basicIterator() {
/*  88 */     return super.basicIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator() {
/*  94 */     return super.basicListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator(int index) {
/* 100 */     return super.basicListIterator(index);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\NotifyingInternalEListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */