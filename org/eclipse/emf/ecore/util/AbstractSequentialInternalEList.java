/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.AbstractSequentialList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSequentialInternalEList<E>
/*     */   extends AbstractSequentialList<E>
/*     */   implements InternalEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public boolean addAllUnique(Collection<? extends E> collection) {
/*  45 */     return addAllUnique(size(), collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAllUnique(int index, Collection<? extends E> collection) {
/*  50 */     return addAll(index, collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addUnique(E object) {
/*  55 */     addUnique(size(), object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addUnique(int index, E object) {
/*  60 */     add(index, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public NotificationChain basicAdd(E object, NotificationChain notifications) {
/*  65 */     addUnique(object);
/*  66 */     return notifications;
/*     */   }
/*     */ 
/*     */   
/*     */   public E basicGet(int index) {
/*  71 */     return basicList().get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<E> basicIterator() {
/*  76 */     return basicListIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/*  81 */     return 
/*  82 */       new AbstractSequentialList<E>()
/*     */       {
/*     */         private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */         
/*     */         public ListIterator<E> listIterator(int index) {
/*  89 */           return AbstractSequentialInternalEList.this.basicListIterator(index);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int size() {
/*  95 */           return AbstractSequentialInternalEList.this.size();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator() {
/* 102 */     return basicListIterator(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator(int index) {
/* 107 */     return basicList().listIterator(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/* 112 */     remove(object);
/* 113 */     return notifications;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContains(Object object) {
/* 118 */     return basicList().contains(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean basicContainsAll(Collection<?> collection) {
/* 123 */     return basicList().containsAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicIndexOf(Object object) {
/* 128 */     return basicList().indexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public int basicLastIndexOf(Object object) {
/* 133 */     return basicList().lastIndexOf(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] basicToArray() {
/* 138 */     return basicList().toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] basicToArray(Object[] array) {
/* 143 */     return basicList().toArray((T[])array);
/*     */   }
/*     */ 
/*     */   
/*     */   public E setUnique(int index, E object) {
/* 148 */     return set(index, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(int newPosition, E object) {
/* 153 */     remove(object);
/* 154 */     add(newPosition, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public E move(int newPosition, int oldPosition) {
/* 159 */     E movedObject = remove(oldPosition);
/* 160 */     add(newPosition, movedObject);
/* 161 */     return movedObject;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\AbstractSequentialInternalEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */