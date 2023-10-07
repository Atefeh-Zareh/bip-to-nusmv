/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.RandomAccess;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BasicEList<E>
/*      */   extends AbstractEList<E>
/*      */   implements RandomAccess, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected int size;
/*      */   protected transient Object[] data;
/*      */   
/*      */   public BasicEList() {}
/*      */   
/*      */   public BasicEList(int initialCapacity) {
/*   65 */     if (initialCapacity < 0)
/*      */     {
/*   67 */       throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
/*      */     }
/*      */     
/*   70 */     this.data = newData(initialCapacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BasicEList(Collection<? extends E> collection) {
/*   79 */     this.size = collection.size();
/*      */ 
/*      */ 
/*      */     
/*   83 */     if (this.size > 0) {
/*      */ 
/*      */ 
/*      */       
/*   87 */       this.data = newData(this.size + this.size / 8 + 1);
/*   88 */       collection.toArray(this.data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BasicEList(int size, Object[] data) {
/*   99 */     this.size = size;
/*  100 */     this.data = data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] newData(int capacity) {
/*  111 */     return new Object[capacity];
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
/*      */   protected E assign(int index, E object) {
/*  124 */     this.data[index] = object;
/*  125 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  135 */     return this.size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  145 */     return (this.size == 0);
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
/*      */   public boolean contains(Object object) {
/*  158 */     if (useEquals() && object != null) {
/*      */       
/*  160 */       for (int i = 0; i < this.size; i++)
/*      */       {
/*  162 */         if (object.equals(this.data[i]))
/*      */         {
/*  164 */           return true;
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  170 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  172 */         if (this.data[i] == object)
/*      */         {
/*  174 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  179 */     return false;
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
/*      */   public int indexOf(Object object) {
/*  191 */     if (useEquals() && object != null) {
/*      */       
/*  193 */       for (int i = 0; i < this.size; i++)
/*      */       {
/*  195 */         if (object.equals(this.data[i]))
/*      */         {
/*  197 */           return i;
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  203 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  205 */         if (this.data[i] == object)
/*      */         {
/*  207 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/*  211 */     return -1;
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
/*      */   public int lastIndexOf(Object object) {
/*  223 */     if (useEquals() && object != null) {
/*      */       
/*  225 */       for (int i = this.size - 1; i >= 0; i--)
/*      */       {
/*  227 */         if (object.equals(this.data[i]))
/*      */         {
/*  229 */           return i;
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  235 */       for (int i = this.size - 1; i >= 0; i--) {
/*      */         
/*  237 */         if (this.data[i] == object)
/*      */         {
/*  239 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/*  243 */     return -1;
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
/*      */   public Object[] toArray() {
/*  255 */     Object[] result = newData(this.size);
/*      */ 
/*      */ 
/*      */     
/*  259 */     if (this.size > 0)
/*      */     {
/*  261 */       System.arraycopy(this.data, 0, result, 0, this.size);
/*      */     }
/*  263 */     return result;
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
/*      */   public <T> T[] toArray(Object[] array) {
/*  278 */     if (this.size > 0) {
/*      */       
/*  280 */       if (array.length < this.size) {
/*      */         
/*  282 */         Object[] newArray = (Object[])Array.newInstance(array.getClass().getComponentType(), this.size);
/*  283 */         array = newArray;
/*      */       } 
/*      */       
/*  286 */       System.arraycopy(this.data, 0, array, 0, this.size);
/*      */     } 
/*      */     
/*  289 */     if (array.length > this.size)
/*      */     {
/*  291 */       array[this.size] = null;
/*      */     }
/*      */     
/*  294 */     return (T[])array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] data() {
/*  305 */     return this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setData(int size, Object[] data) {
/*  315 */     this.size = size;
/*  316 */     this.data = data;
/*  317 */     this.modCount++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class BasicIndexOutOfBoundsException
/*      */     extends AbstractEList.BasicIndexOutOfBoundsException
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BasicIndexOutOfBoundsException(int index, int size) {
/*  333 */       super(index, size);
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
/*      */   
/*      */   public E get(int index) {
/*  351 */     if (index >= this.size) {
/*  352 */       throw new BasicIndexOutOfBoundsException(index, this.size);
/*      */     }
/*  354 */     return resolve(index, (E)this.data[index]);
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
/*      */   public E basicGet(int index) {
/*  368 */     if (index >= this.size) {
/*  369 */       throw new BasicIndexOutOfBoundsException(index, this.size);
/*      */     }
/*  371 */     return primitiveGet(index);
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
/*      */   protected E primitiveGet(int index) {
/*  386 */     return (E)this.data[index];
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
/*  402 */     E oldObject = (E)this.data[index];
/*  403 */     assign(index, validate(index, object));
/*  404 */     didSet(index, object, oldObject);
/*  405 */     didChange();
/*  406 */     return oldObject;
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
/*      */   public void addUnique(E object) {
/*  422 */     grow(this.size + 1);
/*      */     
/*  424 */     assign(this.size, validate(this.size, object));
/*  425 */     didAdd(this.size++, object);
/*  426 */     didChange();
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
/*      */   public void addUnique(int index, E object) {
/*  441 */     grow(this.size + 1);
/*      */     
/*  443 */     E validatedObject = validate(index, object);
/*  444 */     if (index != this.size)
/*      */     {
/*  446 */       System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
/*      */     }
/*  448 */     assign(index, validatedObject);
/*  449 */     this.size++;
/*  450 */     didAdd(index, object);
/*  451 */     didChange();
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
/*  464 */     int growth = collection.size();
/*      */ 
/*      */ 
/*      */     
/*  468 */     grow(this.size + growth);
/*      */     
/*  470 */     Iterator<? extends E> objects = collection.iterator();
/*  471 */     int oldSize = this.size;
/*  472 */     this.size += growth;
/*  473 */     for (int i = oldSize; i < this.size; i++) {
/*      */       
/*  475 */       E object = objects.next();
/*  476 */       assign(i, validate(i, object));
/*  477 */       didAdd(i, object);
/*  478 */       didChange();
/*      */     } 
/*      */     
/*  481 */     return (growth != 0);
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
/*      */   public boolean addAllUnique(int index, Collection<? extends E> collection) {
/*  497 */     int growth = collection.size();
/*      */ 
/*      */ 
/*      */     
/*  501 */     grow(this.size + growth);
/*      */     
/*  503 */     int shifted = this.size - index;
/*  504 */     if (shifted > 0)
/*      */     {
/*  506 */       System.arraycopy(this.data, index, this.data, index + growth, shifted);
/*      */     }
/*      */     
/*  509 */     Iterator<? extends E> objects = collection.iterator();
/*  510 */     this.size += growth;
/*  511 */     for (int i = 0; i < growth; i++) {
/*      */       
/*  513 */       E object = objects.next();
/*  514 */       assign(index, validate(index, object));
/*  515 */       didAdd(index, object);
/*  516 */       didChange();
/*  517 */       index++;
/*      */     } 
/*      */     
/*  520 */     return (growth != 0);
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
/*      */   public boolean addAllUnique(Object[] objects, int start, int end) {
/*  537 */     int growth = end - start;
/*      */ 
/*      */ 
/*      */     
/*  541 */     grow(this.size + growth);
/*      */     
/*  543 */     this.size += growth;
/*  544 */     int index = this.size;
/*  545 */     for (int i = start; i < end; i++) {
/*      */       
/*  547 */       E object = (E)objects[i];
/*  548 */       assign(index, validate(index, object));
/*  549 */       didAdd(index, object);
/*  550 */       didChange();
/*  551 */       index++;
/*      */     } 
/*      */     
/*  554 */     return (growth != 0);
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
/*      */   public boolean addAllUnique(int index, Object[] objects, int start, int end) {
/*  572 */     int growth = end - start;
/*      */ 
/*      */ 
/*      */     
/*  576 */     grow(this.size + growth);
/*      */     
/*  578 */     int shifted = this.size - index;
/*  579 */     if (shifted > 0)
/*      */     {
/*  581 */       System.arraycopy(this.data, index, this.data, index + growth, shifted);
/*      */     }
/*      */     
/*  584 */     this.size += growth;
/*  585 */     for (int i = start; i < end; i++) {
/*      */       
/*  587 */       E object = (E)objects[i];
/*  588 */       assign(index, validate(index, object));
/*  589 */       didAdd(index, object);
/*  590 */       didChange();
/*  591 */       index++;
/*      */     } 
/*      */     
/*  594 */     return (growth != 0);
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
/*  607 */     if (index >= this.size) {
/*  608 */       throw new BasicIndexOutOfBoundsException(index, this.size);
/*      */     }
/*  610 */     this.modCount++;
/*  611 */     E oldObject = (E)this.data[index];
/*      */     
/*  613 */     int shifted = this.size - index - 1;
/*  614 */     if (shifted > 0)
/*      */     {
/*  616 */       System.arraycopy(this.data, index + 1, this.data, index, shifted);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  621 */     this.data[--this.size] = null;
/*  622 */     didRemove(index, oldObject);
/*  623 */     didChange();
/*      */     
/*  625 */     return oldObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  636 */     this.modCount++;
/*      */     
/*  638 */     Object[] oldData = this.data;
/*  639 */     int oldSize = this.size;
/*      */ 
/*      */ 
/*      */     
/*  643 */     this.data = null;
/*  644 */     this.size = 0;
/*      */     
/*  646 */     didClear(oldSize, oldData);
/*  647 */     didChange();
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
/*      */   public E move(int targetIndex, int sourceIndex) {
/*  662 */     this.modCount++;
/*  663 */     if (targetIndex >= this.size) {
/*  664 */       throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + this.size);
/*      */     }
/*  666 */     if (sourceIndex >= this.size) {
/*  667 */       throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + this.size);
/*      */     }
/*  669 */     E object = (E)this.data[sourceIndex];
/*  670 */     if (targetIndex != sourceIndex) {
/*      */       
/*  672 */       if (targetIndex < sourceIndex) {
/*      */         
/*  674 */         System.arraycopy(this.data, targetIndex, this.data, targetIndex + 1, sourceIndex - targetIndex);
/*      */       }
/*      */       else {
/*      */         
/*  678 */         System.arraycopy(this.data, sourceIndex + 1, this.data, sourceIndex, targetIndex - sourceIndex);
/*      */       } 
/*  680 */       assign(targetIndex, object);
/*  681 */       didMove(targetIndex, object, sourceIndex);
/*  682 */       didChange();
/*      */     } 
/*  684 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shrink() {
/*  693 */     this.modCount++;
/*      */ 
/*      */ 
/*      */     
/*  697 */     if (this.size == 0) {
/*      */ 
/*      */ 
/*      */       
/*  701 */       this.data = null;
/*      */     }
/*  703 */     else if (this.size < this.data.length) {
/*      */       
/*  705 */       Object[] oldData = this.data;
/*  706 */       this.data = newData(this.size);
/*  707 */       System.arraycopy(oldData, 0, this.data, 0, this.size);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void grow(int minimumCapacity) {
/*  718 */     this.modCount++;
/*  719 */     int oldCapacity = (this.data == null) ? 0 : this.data.length;
/*  720 */     if (minimumCapacity > oldCapacity) {
/*      */       
/*  722 */       Object[] oldData = this.data;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  728 */       int newCapacity = oldCapacity + oldCapacity / 2 + 4;
/*  729 */       if (newCapacity < minimumCapacity)
/*      */       {
/*  731 */         newCapacity = minimumCapacity;
/*      */       }
/*  733 */       this.data = newData(newCapacity);
/*  734 */       if (oldData != null)
/*      */       {
/*  736 */         System.arraycopy(oldData, 0, this.data, 0, this.size);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
/*  743 */     objectOutputStream.defaultWriteObject();
/*  744 */     if (this.data == null) {
/*      */       
/*  746 */       objectOutputStream.writeInt(0);
/*      */     }
/*      */     else {
/*      */       
/*  750 */       objectOutputStream.writeInt(this.data.length);
/*  751 */       for (int i = 0; i < this.size; i++)
/*      */       {
/*  753 */         objectOutputStream.writeObject(this.data[i]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
/*  760 */     objectInputStream.defaultReadObject();
/*  761 */     int arrayLength = objectInputStream.readInt();
/*  762 */     if (arrayLength > 0) {
/*      */ 
/*      */       
/*      */       try {
/*  766 */         this.data = newData(arrayLength);
/*      */       }
/*  768 */       catch (Throwable exception) {
/*      */         
/*  770 */         this.data = new Object[arrayLength];
/*      */       } 
/*      */       
/*  773 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  775 */         E object = (E)objectInputStream.readObject();
/*  776 */         didAdd(i, assign(i, object));
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
/*      */   public Object clone() {
/*      */     try {
/*  790 */       BasicEList<E> clone = (BasicEList<E>)super.clone();
/*  791 */       if (this.size > 0) {
/*      */         
/*  793 */         clone.size = this.size;
/*  794 */         clone.data = newData(this.size);
/*  795 */         System.arraycopy(this.data, 0, clone.data, 0, this.size);
/*      */       } 
/*  797 */       return clone;
/*      */     }
/*  799 */     catch (CloneNotSupportedException exception) {
/*      */       
/*  801 */       throw new InternalError();
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
/*      */   protected class EIterator<E1>
/*      */     extends AbstractEList<E>.EIterator<E1> {}
/*      */ 
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
/*  849 */       super(index);
/*  850 */       this.cursor = index;
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
/*  876 */       super(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UnmodifiableEList<E>
/*      */     extends BasicEList<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public UnmodifiableEList(int size, Object[] data) {
/*  894 */       this.size = size;
/*  895 */       this.data = data;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E set(int index, E object) {
/*  905 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean add(E object) {
/*  915 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(int index, E object) {
/*  925 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection<? extends E> collection) {
/*  935 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(int index, Collection<? extends E> collection) {
/*  945 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean remove(Object object) {
/*  955 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E remove(int index) {
/*  965 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  975 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  985 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear() {
/*  995 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void move(int index, E object) {
/* 1005 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E move(int targetIndex, int sourceIndex) {
/* 1015 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void shrink() {
/* 1025 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void grow(int minimumCapacity) {
/* 1035 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<E> iterator() {
/* 1045 */       return basicIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> listIterator() {
/* 1055 */       return basicListIterator();
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
/* 1066 */       return basicListIterator(index);
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
/* 1077 */     if (this.size == 0)
/*      */     {
/* 1079 */       return ECollections.emptyEList();
/*      */     }
/*      */ 
/*      */     
/* 1083 */     return new UnmodifiableEList<E>(this.size, this.data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class FastCompare<E>
/*      */     extends BasicEList<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FastCompare() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FastCompare(int initialCapacity) {
/* 1109 */       super(initialCapacity);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FastCompare(Collection<? extends E> collection) {
/* 1118 */       super(collection.size());
/* 1119 */       addAll(collection);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean useEquals() {
/* 1129 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\BasicEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */