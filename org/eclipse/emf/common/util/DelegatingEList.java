/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DelegatingEList<E>
/*      */   extends AbstractEList<E>
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public DelegatingEList() {}
/*      */   
/*      */   public DelegatingEList(Collection<? extends E> collection) {
/*   48 */     addAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract List<E> delegateList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*   64 */     return delegateSize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int delegateSize() {
/*   73 */     return delegateList().size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*   83 */     return delegateIsEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean delegateIsEmpty() {
/*   92 */     return delegateList().isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(Object object) {
/*  103 */     return delegateContains(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean delegateContains(Object object) {
/*  113 */     return delegateList().contains(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAll(Collection<?> collection) {
/*  125 */     return delegateContainsAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean delegateContainsAll(Collection<?> collection) {
/*  136 */     return delegateList().containsAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOf(Object object) {
/*  147 */     return delegateIndexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int delegateIndexOf(Object object) {
/*  157 */     return delegateList().indexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int lastIndexOf(Object object) {
/*  168 */     return delegateLastIndexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int delegateLastIndexOf(Object object) {
/*  178 */     return delegateList().lastIndexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray() {
/*  188 */     return delegateToArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] delegateToArray() {
/*  197 */     return delegateList().toArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T[] toArray(Object[] array) {
/*  209 */     return delegateToArray((T[])array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected <T> T[] delegateToArray(Object[] array) {
/*  220 */     return delegateList().toArray((T[])array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E get(int index) {
/*  236 */     return resolve(index, delegateGet(index));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E delegateGet(int index) {
/*  247 */     return delegateList().get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E basicGet(int index) {
/*  261 */     return delegateGet(index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected E primitiveGet(int index) {
/*  267 */     return delegateGet(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E setUnique(int index, E object) {
/*  283 */     E oldObject = delegateSet(index, validate(index, object));
/*  284 */     didSet(index, object, oldObject);
/*  285 */     didChange();
/*  286 */     return oldObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E delegateSet(int index, E object) {
/*  297 */     return delegateList().set(index, object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUnique(E object) {
/*  311 */     this.modCount++;
/*      */     
/*  313 */     int size = size();
/*  314 */     delegateAdd(validate(size, object));
/*  315 */     didAdd(size, object);
/*  316 */     didChange();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void delegateAdd(E object) {
/*  325 */     delegateList().add(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUnique(int index, E object) {
/*  338 */     this.modCount++;
/*      */     
/*  340 */     delegateAdd(index, validate(index, object));
/*  341 */     didAdd(index, object);
/*  342 */     didChange();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void delegateAdd(int index, E object) {
/*  351 */     delegateList().add(index, object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(Collection<? extends E> collection) {
/*  364 */     this.modCount++;
/*      */     
/*  366 */     if (collection.isEmpty())
/*      */     {
/*  368 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  372 */     int i = size();
/*  373 */     for (E object : collection) {
/*      */       
/*  375 */       delegateAdd(validate(i, object));
/*  376 */       didAdd(i, object);
/*  377 */       didChange();
/*  378 */       i++;
/*      */     } 
/*      */     
/*  381 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(int index, Collection<? extends E> collection) {
/*  398 */     this.modCount++;
/*      */     
/*  400 */     if (collection.isEmpty())
/*      */     {
/*  402 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  406 */     for (E object : collection) {
/*      */       
/*  408 */       delegateAdd(index, validate(index, object));
/*  409 */       didAdd(index, object);
/*  410 */       didChange();
/*  411 */       index++;
/*      */     } 
/*      */     
/*  414 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(Object[] objects, int start, int end) {
/*  432 */     int growth = end - start;
/*      */     
/*  434 */     this.modCount++;
/*      */     
/*  436 */     if (growth == 0)
/*      */     {
/*  438 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  442 */     int index = size();
/*  443 */     for (int i = start; i < end; i++, index++) {
/*      */       
/*  445 */       E object = (E)objects[i];
/*  446 */       delegateAdd(validate(index, object));
/*  447 */       didAdd(index, object);
/*  448 */       didChange();
/*      */     } 
/*      */     
/*  451 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(int index, Object[] objects, int start, int end) {
/*  470 */     int growth = end - start;
/*      */     
/*  472 */     this.modCount++;
/*      */     
/*  474 */     if (growth == 0)
/*      */     {
/*  476 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  480 */     for (int i = start; i < end; i++, index++) {
/*      */       
/*  482 */       E object = (E)objects[i];
/*  483 */       delegateAdd(validate(index, object));
/*  484 */       didAdd(index, object);
/*  485 */       didChange();
/*      */     } 
/*      */     
/*  488 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object object) {
/*  503 */     int index = indexOf(object);
/*  504 */     if (index >= 0) {
/*      */       
/*  506 */       remove(index);
/*  507 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  511 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeAll(Collection<?> collection) {
/*  523 */     boolean modified = false;
/*  524 */     for (ListIterator<?> i = listIterator(); i.hasNext();) {
/*      */       
/*  526 */       if (collection.contains(i.next())) {
/*      */         
/*  528 */         i.remove();
/*  529 */         modified = true;
/*      */       } 
/*      */     } 
/*      */     
/*  533 */     return modified;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E remove(int index) {
/*  546 */     this.modCount++;
/*      */     
/*  548 */     E oldObject = delegateRemove(index);
/*  549 */     didRemove(index, oldObject);
/*  550 */     didChange();
/*      */     
/*  552 */     return oldObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E delegateRemove(int index) {
/*  562 */     return delegateList().remove(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean retainAll(Collection<?> collection) {
/*  576 */     boolean modified = false;
/*  577 */     for (ListIterator<?> i = listIterator(); i.hasNext();) {
/*      */       
/*  579 */       if (!collection.contains(i.next())) {
/*      */         
/*  581 */         i.remove();
/*  582 */         modified = true;
/*      */       } 
/*      */     } 
/*  585 */     return modified;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  594 */     doClear(size(), delegateToArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doClear(int oldSize, Object[] oldData) {
/*  604 */     this.modCount++;
/*      */     
/*  606 */     delegateClear();
/*      */     
/*  608 */     didClear(oldSize, oldData);
/*  609 */     didChange();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void delegateClear() {
/*  617 */     delegateList().clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E move(int targetIndex, int sourceIndex) {
/*      */     E object;
/*  632 */     this.modCount++;
/*  633 */     int size = size();
/*  634 */     if (targetIndex >= size || targetIndex < 0) {
/*  635 */       throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + size);
/*      */     }
/*  637 */     if (sourceIndex >= size || sourceIndex < 0) {
/*  638 */       throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + size);
/*      */     }
/*      */     
/*  641 */     if (targetIndex != sourceIndex) {
/*      */       
/*  643 */       object = delegateMove(targetIndex, sourceIndex);
/*  644 */       didMove(targetIndex, object, sourceIndex);
/*  645 */       didChange();
/*      */     }
/*      */     else {
/*      */       
/*  649 */       object = delegateGet(sourceIndex);
/*      */     } 
/*  651 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E delegateMove(int targetIndex, int sourceIndex) {
/*  664 */     E result = delegateRemove(sourceIndex);
/*  665 */     delegateAdd(targetIndex, result);
/*  666 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object object) {
/*  678 */     return delegateEquals(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean delegateEquals(Object object) {
/*  687 */     return delegateList().equals(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  697 */     return delegateHashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int delegateHashCode() {
/*  706 */     return delegateList().hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  716 */     return delegateToString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String delegateToString() {
/*  725 */     return delegateList().toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Iterator<E> delegateIterator() {
/*  734 */     return delegateList().iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected class EIterator<E1>
/*      */     extends AbstractEList<E>.EIterator<E1> {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected class NonResolvingEIterator<E1>
/*      */     extends AbstractEList<E>.NonResolvingEIterator<E1> {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ListIterator<E> delegateListIterator() {
/*  765 */     return delegateList().listIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected class EListIterator<E1>
/*      */     extends AbstractEList<E>.EListIterator<E1>
/*      */   {
/*      */     public EListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EListIterator(int index) {
/*  790 */       super(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected class NonResolvingEListIterator<E1>
/*      */     extends AbstractEList<E>.NonResolvingEListIterator<E1>
/*      */   {
/*      */     public NonResolvingEListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NonResolvingEListIterator(int index) {
/*  816 */       super(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UnmodifiableEList<E>
/*      */     extends DelegatingEList<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<E> underlyingList;
/*      */ 
/*      */ 
/*      */     
/*      */     public UnmodifiableEList(List<E> underlyingList) {
/*  835 */       this.underlyingList = underlyingList;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<E> delegateList() {
/*  841 */       return this.underlyingList;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E set(int index, E object) {
/*  851 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean add(E object) {
/*  861 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(int index, E object) {
/*  871 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection<? extends E> collection) {
/*  881 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(int index, Collection<? extends E> collection) {
/*  891 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean remove(Object object) {
/*  901 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E remove(int index) {
/*  911 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  921 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  931 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear() {
/*  941 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void move(int index, E object) {
/*  951 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E move(int targetIndex, int sourceIndex) {
/*  961 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<E> iterator() {
/*  971 */       return basicIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> listIterator() {
/*  981 */       return basicListIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> listIterator(int index) {
/*  992 */       return basicListIterator(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<E> basicList() {
/* 1003 */     return delegateBasicList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<E> delegateBasicList() {
/* 1012 */     return delegateList();
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\DelegatingEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */