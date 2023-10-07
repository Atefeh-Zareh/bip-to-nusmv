/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.util.AbstractList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractEList<E>
/*      */   extends AbstractList<E>
/*      */   implements EList<E>
/*      */ {
/*      */   protected boolean useEquals() {
/*   51 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean equalObjects(Object firstObject, Object secondObject) {
/*   60 */     return 
/*   61 */       (useEquals() && firstObject != null) ? 
/*   62 */       firstObject.equals(secondObject) : (
/*   63 */       (firstObject == secondObject));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canContainNull() {
/*   73 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isUnique() {
/*   84 */     return false;
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
/*      */   protected E validate(int index, E object) {
/*   99 */     if (!canContainNull() && object == null)
/*      */     {
/*  101 */       throw new IllegalArgumentException("The 'no null' constraint is violated");
/*      */     }
/*      */     
/*  104 */     return object;
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
/*      */   protected E resolve(int index, E object) {
/*  117 */     return object;
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
/*      */   protected void didSet(int index, E newObject, E oldObject) {}
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
/*      */   protected void didAdd(int index, E newObject) {}
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
/*      */   protected void didRemove(int index, E oldObject) {}
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
/*      */   protected void didClear(int size, Object[] oldObjects) {
/*  167 */     if (oldObjects != null)
/*      */     {
/*  169 */       for (int i = 0; i < size; i++) {
/*      */         
/*  171 */         E object = (E)oldObjects[i];
/*  172 */         didRemove(i, object);
/*      */       } 
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
/*      */   protected void didMove(int index, E movedObject, int oldIndex) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void didChange() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class BasicIndexOutOfBoundsException
/*      */     extends IndexOutOfBoundsException
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BasicIndexOutOfBoundsException(int index, int size) {
/*  213 */       super("index=" + index + ", size=" + size);
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
/*      */   
/*      */   protected E basicGet(int index) {
/*  227 */     int size = size();
/*  228 */     if (index >= size) {
/*  229 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  231 */     return primitiveGet(index);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public E set(int index, E object) {
/*  259 */     int size = size();
/*  260 */     if (index >= size) {
/*  261 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  263 */     if (isUnique()) {
/*      */       
/*  265 */       int currentIndex = indexOf(object);
/*  266 */       if (currentIndex >= 0 && currentIndex != index)
/*      */       {
/*  268 */         throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */       }
/*      */     } 
/*      */     
/*  272 */     return setUnique(index, object);
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
/*      */   public boolean add(E object) {
/*  301 */     if (isUnique() && contains(object))
/*      */     {
/*  303 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  307 */     addUnique(object);
/*  308 */     return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, E object) {
/*  336 */     int size = size();
/*  337 */     if (index > size) {
/*  338 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  340 */     if (isUnique() && contains(object))
/*      */     {
/*  342 */       throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */     }
/*      */     
/*  345 */     addUnique(index, object);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(Collection<? extends E> collection) {
/*  370 */     if (isUnique())
/*      */     {
/*  372 */       collection = getNonDuplicates(collection);
/*      */     }
/*  374 */     return addAllUnique(collection);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, Collection<? extends E> collection) {
/*  402 */     int size = size();
/*  403 */     if (index > size) {
/*  404 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  406 */     if (isUnique())
/*      */     {
/*  408 */       collection = getNonDuplicates(collection);
/*      */     }
/*  410 */     return addAllUnique(index, collection);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object object) {
/*  463 */     int index = indexOf(object);
/*  464 */     if (index >= 0) {
/*      */       
/*  466 */       remove(index);
/*  467 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  471 */     return false;
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
/*  483 */     boolean modified = false;
/*  484 */     for (int i = size(); --i >= 0;) {
/*      */       
/*  486 */       if (collection.contains(primitiveGet(i))) {
/*      */         
/*  488 */         remove(i);
/*  489 */         modified = true;
/*      */       } 
/*      */     } 
/*      */     
/*  493 */     return modified;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean retainAll(Collection<?> collection) {
/*  517 */     boolean modified = false;
/*  518 */     for (int i = size(); --i >= 0;) {
/*      */       
/*  520 */       if (!collection.contains(primitiveGet(i))) {
/*      */         
/*  522 */         remove(i);
/*  523 */         modified = true;
/*      */       } 
/*      */     } 
/*  526 */     return modified;
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
/*      */   public void move(int index, E object) {
/*  539 */     move(index, indexOf(object));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object object) {
/*  563 */     if (object == this)
/*      */     {
/*  565 */       return true;
/*      */     }
/*      */     
/*  568 */     if (!(object instanceof List))
/*      */     {
/*  570 */       return false;
/*      */     }
/*      */     
/*  573 */     List<?> list = (List)object;
/*  574 */     int size = size();
/*  575 */     if (list.size() != size)
/*      */     {
/*  577 */       return false;
/*      */     }
/*      */     
/*  580 */     Iterator<?> objects = list.iterator();
/*  581 */     if (useEquals()) {
/*      */       
/*  583 */       for (int i = 0; i < size; i++)
/*      */       {
/*  585 */         Object o1 = primitiveGet(i);
/*  586 */         Object o2 = objects.next();
/*  587 */         if ((o1 == null) ? (o2 != null) : !o1.equals(o2))
/*      */         {
/*  589 */           return false;
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  595 */       for (int i = 0; i < size; i++) {
/*      */         
/*  597 */         Object o1 = primitiveGet(i);
/*  598 */         Object o2 = objects.next();
/*  599 */         if (o1 != o2)
/*      */         {
/*  601 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  606 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  616 */     int hashCode = 1;
/*  617 */     for (int i = 0, size = size(); i < size; i++) {
/*      */       
/*  619 */       Object object = primitiveGet(i);
/*  620 */       hashCode = 31 * hashCode + ((object == null) ? 0 : object.hashCode());
/*      */     } 
/*  622 */     return hashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  632 */     StringBuffer stringBuffer = new StringBuffer();
/*  633 */     stringBuffer.append("[");
/*  634 */     for (int i = 0, size = size(); i < size; ) {
/*      */       
/*  636 */       stringBuffer.append(String.valueOf(primitiveGet(i)));
/*  637 */       if (++i < size)
/*      */       {
/*  639 */         stringBuffer.append(", ");
/*      */       }
/*      */     } 
/*  642 */     stringBuffer.append("]");
/*  643 */     return stringBuffer.toString();
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
/*      */   public Iterator<E> iterator() {
/*  655 */     return new EIterator<E>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class EIterator<E1>
/*      */     implements Iterator<E1>
/*      */   {
/*  666 */     protected int cursor = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  671 */     protected int lastCursor = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  676 */     protected int expectedModCount = AbstractEList.this.modCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/*  684 */       return (this.cursor != AbstractEList.this.size());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E1 next() {
/*  696 */       return (E1)doNext();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E doNext() {
/*      */       try {
/*  709 */         E next = AbstractEList.this.get(this.cursor);
/*  710 */         checkModCount();
/*  711 */         this.lastCursor = this.cursor++;
/*  712 */         return next;
/*      */       }
/*  714 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  716 */         checkModCount();
/*  717 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
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
/*      */     public void remove() {
/*  732 */       if (this.lastCursor == -1)
/*      */       {
/*  734 */         throw new IllegalStateException();
/*      */       }
/*  736 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  740 */         AbstractEList.this.remove(this.lastCursor);
/*  741 */         this.expectedModCount = AbstractEList.this.modCount;
/*  742 */         if (this.lastCursor < this.cursor)
/*      */         {
/*  744 */           this.cursor--;
/*      */         }
/*  746 */         this.lastCursor = -1;
/*      */       }
/*  748 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  750 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/*  760 */       if (AbstractEList.this.modCount != this.expectedModCount)
/*      */       {
/*  762 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Iterator<E> basicIterator() {
/*  774 */     return new NonResolvingEIterator<E>();
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
/*      */   protected class NonResolvingEIterator<E1>
/*      */     extends EIterator<E1>
/*      */   {
/*      */     protected E doNext() {
/*      */       try {
/*  793 */         E next = AbstractEList.this.primitiveGet(this.cursor);
/*  794 */         checkModCount();
/*  795 */         this.lastCursor = this.cursor++;
/*  796 */         return next;
/*      */       }
/*  798 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  800 */         checkModCount();
/*  801 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {
/*  812 */       throw new UnsupportedOperationException();
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
/*      */   public ListIterator<E> listIterator() {
/*  825 */     return new EListIterator<E>();
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
/*      */   public ListIterator<E> listIterator(int index) {
/*  839 */     int size = size();
/*  840 */     if (index < 0 || index > size) {
/*  841 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  843 */     return new EListIterator<E>(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class EListIterator<E1>
/*      */     extends EIterator<E1>
/*      */     implements ListIterator<E1>
/*      */   {
/*      */     public EListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EListIterator(int index) {
/*  865 */       this.cursor = index;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasPrevious() {
/*  874 */       return (this.cursor != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E1 previous() {
/*  886 */       return (E1)doPrevious();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E doPrevious() {
/*      */       try {
/*  899 */         E previous = AbstractEList.this.get(--this.cursor);
/*  900 */         checkModCount();
/*  901 */         this.lastCursor = this.cursor;
/*  902 */         return previous;
/*      */       }
/*  904 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  906 */         checkModCount();
/*  907 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int nextIndex() {
/*  917 */       return this.cursor;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int previousIndex() {
/*  926 */       return this.cursor - 1;
/*      */     }
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
/*      */     public void set(E1 object) {
/*  941 */       doSet((E)object);
/*      */     }
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
/*      */     protected void doSet(E object) {
/*  955 */       if (this.lastCursor == -1)
/*      */       {
/*  957 */         throw new IllegalStateException();
/*      */       }
/*  959 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  963 */         AbstractEList.this.set(this.lastCursor, object);
/*      */       }
/*  965 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  967 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(E1 object) {
/*  979 */       doAdd((E)object);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void doAdd(E object) {
/*  989 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  993 */         AbstractEList.this.add(this.cursor++, object);
/*  994 */         this.expectedModCount = AbstractEList.this.modCount;
/*  995 */         this.lastCursor = -1;
/*      */       }
/*  997 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  999 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ListIterator<E> basicListIterator() {
/* 1011 */     return new NonResolvingEListIterator<E>();
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
/*      */   protected ListIterator<E> basicListIterator(int index) {
/* 1023 */     int size = size();
/* 1024 */     if (index < 0 || index > size) {
/* 1025 */       throw new BasicIndexOutOfBoundsException(index, size);
/*      */     }
/* 1027 */     return new NonResolvingEListIterator<E>(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class NonResolvingEListIterator<E1>
/*      */     extends EListIterator<E1>
/*      */   {
/*      */     public NonResolvingEListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NonResolvingEListIterator(int index) {
/* 1049 */       super(index);
/*      */     }
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
/*      */     protected E doNext() {
/*      */       try {
/* 1063 */         E next = AbstractEList.this.primitiveGet(this.cursor);
/* 1064 */         checkModCount();
/* 1065 */         this.lastCursor = this.cursor++;
/* 1066 */         return next;
/*      */       }
/* 1068 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/* 1070 */         checkModCount();
/* 1071 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
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
/*      */     protected E doPrevious() {
/*      */       try {
/* 1086 */         E previous = AbstractEList.this.primitiveGet(--this.cursor);
/* 1087 */         checkModCount();
/* 1088 */         this.lastCursor = this.cursor;
/* 1089 */         return previous;
/*      */       }
/* 1091 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/* 1093 */         checkModCount();
/* 1094 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {
/* 1105 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void set(E1 object) {
/* 1115 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(E1 object) {
/* 1125 */       throw new UnsupportedOperationException();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Collection<E> getDuplicates(Collection<?> collection) {
/* 1142 */     if (collection.isEmpty())
/*      */     {
/* 1144 */       return ECollections.emptyEList();
/*      */     }
/*      */ 
/*      */     
/* 1148 */     Collection<E> filteredResult = useEquals() ? new BasicEList<E>(collection.size()) : new BasicEList.FastCompare<E>(collection.size());
/* 1149 */     for (E object : this) {
/*      */       
/* 1151 */       if (collection.contains(object))
/*      */       {
/* 1153 */         filteredResult.add(object);
/*      */       }
/*      */     } 
/* 1156 */     return filteredResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Collection<E> getNonDuplicates(Collection<? extends E> collection) {
/* 1167 */     Collection<E> result = useEquals() ? new UniqueEList<E>(collection.size()) : new UniqueEList.FastCompare<E>(collection.size());
/* 1168 */     for (E object : collection) {
/*      */       
/* 1170 */       if (!contains(object))
/*      */       {
/* 1172 */         result.add(object);
/*      */       }
/*      */     } 
/* 1175 */     return result;
/*      */   }
/*      */   
/*      */   protected abstract E primitiveGet(int paramInt);
/*      */   
/*      */   public abstract E setUnique(int paramInt, E paramE);
/*      */   
/*      */   public abstract void addUnique(E paramE);
/*      */   
/*      */   public abstract void addUnique(int paramInt, E paramE);
/*      */   
/*      */   public abstract boolean addAllUnique(Collection<? extends E> paramCollection);
/*      */   
/*      */   public abstract boolean addAllUnique(int paramInt, Collection<? extends E> paramCollection);
/*      */   
/*      */   public abstract boolean addAllUnique(Object[] paramArrayOfObject, int paramInt1, int paramInt2);
/*      */   
/*      */   public abstract boolean addAllUnique(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int paramInt3);
/*      */   
/*      */   public abstract E remove(int paramInt);
/*      */   
/*      */   public abstract E move(int paramInt1, int paramInt2);
/*      */   
/*      */   protected abstract List<E> basicList();
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\AbstractEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */