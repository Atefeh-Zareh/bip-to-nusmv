/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicInternalEList<E>
/*     */   extends BasicEList<E>
/*     */   implements InternalEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final Class<? extends E> dataClass;
/*     */   
/*     */   public BasicInternalEList(Class<? extends E> dataClass) {
/*  42 */     this.dataClass = dataClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicInternalEList(Class<? extends E> dataClass, int initialCapacity) {
/*  48 */     this.dataClass = dataClass;
/*     */     
/*  50 */     if (initialCapacity < 0)
/*     */     {
/*  52 */       throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
/*     */     }
/*     */     
/*  55 */     this.data = newData(initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicInternalEList(Class<? extends E> dataClass, Collection<? extends E> collection) {
/*  61 */     this.dataClass = dataClass;
/*  62 */     this.size = collection.size();
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (this.size > 0) {
/*     */ 
/*     */ 
/*     */       
/*  70 */       this.data = newData(this.size + this.size / 8 + 1);
/*  71 */       collection.toArray(this.data);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicInternalEList(Class<? extends E> dataClass, int size, Object[] data) {
/*  77 */     super(size, data);
/*  78 */     this.dataClass = dataClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] newData(int capacity) {
/*  84 */     return (Object[])Array.newInstance(this.dataClass, capacity);
/*     */   }
/*     */ 
/*     */   
/*     */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/*  89 */     remove(object);
/*  90 */     return notifications;
/*     */   }
/*     */ 
/*     */   
/*     */   public NotificationChain basicAdd(E object, NotificationChain notifications) {
/*  95 */     add(object);
/*  96 */     return notifications;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> basicIterator() {
/* 102 */     return super.basicIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/* 108 */     return super.basicList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator() {
/* 114 */     return super.basicListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator(int index) {
/* 120 */     return super.basicListIterator(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContains(Object object) {
/* 125 */     return contains(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContainsAll(Collection<?> collection) {
/* 130 */     return containsAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicIndexOf(Object object) {
/* 135 */     return indexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicLastIndexOf(Object object) {
/* 140 */     return lastIndexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] basicToArray() {
/* 145 */     return toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] basicToArray(Object[] array) {
/* 150 */     return (T[])toArray(array);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\BasicInternalEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */