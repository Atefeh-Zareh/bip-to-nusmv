/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DelegatingNotifyingInternalEListImpl<E>
/*     */   extends DelegatingNotifyingListImpl<E>
/*     */   implements InternalEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public DelegatingNotifyingInternalEListImpl() {}
/*     */   
/*     */   public DelegatingNotifyingInternalEListImpl(Collection<? extends E> collection) {
/*  41 */     super(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean basicContains(Object object) {
/*  47 */     return contains(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContainsAll(Collection<?> collection) {
/*  52 */     return containsAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicIndexOf(Object object) {
/*  57 */     return indexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicLastIndexOf(Object object) {
/*  62 */     return lastIndexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] basicToArray() {
/*  67 */     return toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] basicToArray(Object[] array) {
/*  72 */     return (T[])toArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public E basicGet(int index) {
/*  78 */     return (E)super.basicGet(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/*  84 */     return super.basicList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> basicIterator() {
/*  90 */     return super.basicIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator() {
/*  96 */     return super.basicListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator(int index) {
/* 102 */     return super.basicListIterator(index);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\DelegatingNotifyingInternalEListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */