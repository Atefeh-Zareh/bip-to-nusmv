/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
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
/*      */ public abstract class ArrayDelegatingEList<E>
/*      */   extends AbstractEList<E>
/*      */   implements RandomAccess, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public ArrayDelegatingEList() {}
/*      */   
/*      */   public ArrayDelegatingEList(Collection<? extends E> collection) {
/*   58 */     int size = collection.size();
/*   59 */     if (size > 0) {
/*      */       
/*   61 */       Object[] data = newData(size);
/*   62 */       collection.toArray(data);
/*   63 */       setData(data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ArrayDelegatingEList(Object[] data) {
/*   73 */     setData(data);
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
/*   84 */     return new Object[capacity];
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
/*      */   protected E assign(Object[] data, int index, E object) {
/*   97 */     data[index] = object;
/*   98 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int size() {
/*  108 */     Object[] data = data();
/*  109 */     return (data == null) ? 0 : data.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  119 */     return (data() == null);
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
/*  132 */     Object[] data = data();
/*  133 */     if (data != null)
/*      */     {
/*  135 */       if (useEquals() && object != null) {
/*      */         byte b; int i; Object[] arrayOfObject;
/*  137 */         for (i = (arrayOfObject = data).length, b = 0; b < i; ) { Object datum = arrayOfObject[b];
/*      */           
/*  139 */           if (object.equals(datum))
/*      */           {
/*  141 */             return true; }  b++; }
/*      */       
/*      */       } else {
/*      */         byte b;
/*      */         int i;
/*      */         Object[] arrayOfObject;
/*  147 */         for (i = (arrayOfObject = data).length, b = 0; b < i; ) { Object datum = arrayOfObject[b];
/*      */           
/*  149 */           if (datum == object)
/*      */           {
/*  151 */             return true;
/*      */           }
/*      */           b++; }
/*      */       
/*      */       } 
/*      */     }
/*  157 */     return false;
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
/*  169 */     Object[] data = data();
/*  170 */     if (data != null)
/*      */     {
/*  172 */       if (useEquals() && object != null) {
/*      */         
/*  174 */         for (int i = 0, size = data.length; i < size; i++)
/*      */         {
/*  176 */           if (object.equals(data[i]))
/*      */           {
/*  178 */             return i;
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  184 */         for (int i = 0, size = data.length; i < size; i++) {
/*      */           
/*  186 */           if (data[i] == object)
/*      */           {
/*  188 */             return i;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  193 */     return -1;
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
/*  205 */     Object[] data = data();
/*  206 */     if (data != null)
/*      */     {
/*  208 */       if (useEquals() && object != null) {
/*      */         
/*  210 */         for (int i = data.length - 1; i >= 0; i--)
/*      */         {
/*  212 */           if (object.equals(data[i]))
/*      */           {
/*  214 */             return i;
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  220 */         for (int i = data.length - 1; i >= 0; i--) {
/*      */           
/*  222 */           if (data[i] == object)
/*      */           {
/*  224 */             return i;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  229 */     return -1;
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
/*  241 */     Object[] data = data();
/*  242 */     int size = (data == null) ? 0 : data.length;
/*  243 */     Object[] result = newData(size);
/*      */ 
/*      */ 
/*      */     
/*  247 */     if (size > 0)
/*      */     {
/*  249 */       System.arraycopy(data, 0, result, 0, size);
/*      */     }
/*  251 */     return result;
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
/*  266 */     Object[] data = data();
/*  267 */     int size = (data == null) ? 0 : data.length;
/*  268 */     if (size > 0) {
/*      */       
/*  270 */       if (array.length < size) {
/*      */         
/*  272 */         Object[] newArray = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*  273 */         array = newArray;
/*      */       } 
/*      */       
/*  276 */       System.arraycopy(data, 0, array, 0, size);
/*      */     } 
/*      */     
/*  279 */     if (array.length > size)
/*      */     {
/*  281 */       array[size] = null;
/*      */     }
/*      */     
/*  284 */     return (T[])array;
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
/*      */   public void setData(Object[] data) {
/*  306 */     this.modCount++;
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
/*  323 */     Object[] data = data();
/*  324 */     int size = (data == null) ? 0 : data.length;
/*  325 */     if (index >= size) {
/*  326 */       throw new AbstractEList.BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  328 */     return resolve(index, (E)data[index]);
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
/*      */   public E basicGet(int index) {
/*  343 */     Object[] data = data();
/*  344 */     int size = (data == null) ? 0 : data.length;
/*  345 */     if (index >= size) {
/*  346 */       throw new AbstractEList.BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  348 */     return (E)data[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E primitiveGet(int index) {
/*  355 */     return (E)data()[index];
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
/*  371 */     Object[] data = copy();
/*  372 */     E oldObject = (E)data[index];
/*  373 */     assign(data, index, validate(index, object));
/*  374 */     setData(data);
/*  375 */     didSet(index, object, oldObject);
/*  376 */     didChange();
/*  377 */     return oldObject;
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
/*  391 */     int size = size();
/*  392 */     Object[] data = grow(size + 1);
/*  393 */     assign(data, size, validate(size, object));
/*  394 */     setData(data);
/*  395 */     didAdd(size, object);
/*  396 */     didChange();
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
/*  409 */     Object[] oldData = data();
/*  410 */     int size = (oldData == null) ? 0 : oldData.length;
/*  411 */     Object[] data = grow(size + 1);
/*  412 */     E validatedObject = validate(index, object);
/*  413 */     if (index != size)
/*      */     {
/*  415 */       System.arraycopy(oldData, index, data, index + 1, size - index);
/*      */     }
/*  417 */     assign(data, index, validatedObject);
/*  418 */     setData(data);
/*  419 */     didAdd(index, object);
/*  420 */     didChange();
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
/*  433 */     int growth = collection.size();
/*  434 */     if (growth != 0) {
/*      */       
/*  436 */       int oldSize = size();
/*  437 */       int size = oldSize + growth;
/*  438 */       Object[] data = grow(size);
/*      */       
/*  440 */       Iterator<? extends E> objects = collection.iterator(); int i;
/*  441 */       for (i = oldSize; i < size; i++) {
/*      */         
/*  443 */         E object = objects.next();
/*  444 */         assign(data, i, validate(i, object));
/*      */       } 
/*  446 */       setData(data);
/*  447 */       for (i = oldSize; i < size; i++) {
/*      */         
/*  449 */         E object = (E)data[i];
/*  450 */         didAdd(i, object);
/*  451 */         didChange();
/*      */       } 
/*  453 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  457 */     this.modCount++;
/*  458 */     return false;
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
/*  475 */     int growth = collection.size();
/*  476 */     if (growth != 0) {
/*      */       
/*  478 */       Object[] oldData = data();
/*  479 */       int oldSize = (oldData == null) ? 0 : oldData.length;
/*  480 */       int size = oldSize + growth;
/*  481 */       Object[] data = grow(size);
/*      */       
/*  483 */       int shifted = oldSize - index;
/*  484 */       if (shifted > 0)
/*      */       {
/*  486 */         System.arraycopy(oldData, index, data, index + growth, shifted);
/*      */       }
/*      */       
/*  489 */       Iterator<? extends E> objects = collection.iterator(); int i;
/*  490 */       for (i = 0; i < growth; i++) {
/*      */         
/*  492 */         E object = objects.next();
/*  493 */         int currentIndex = index + i;
/*  494 */         assign(data, currentIndex, validate(currentIndex, object));
/*      */       } 
/*  496 */       setData(data);
/*  497 */       for (i = 0; i < growth; i++) {
/*      */         
/*  499 */         E object = (E)data[index];
/*  500 */         didAdd(index, object);
/*  501 */         didChange();
/*  502 */         index++;
/*      */       } 
/*  504 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  508 */     this.modCount++;
/*  509 */     return false;
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
/*  527 */     int growth = end - start;
/*  528 */     if (growth > 0) {
/*      */       
/*  530 */       int oldSize = size();
/*  531 */       int size = oldSize + growth;
/*  532 */       Object[] data = grow(size);
/*      */       int i, index;
/*  534 */       for (i = start, index = size; i < end; i++, index++) {
/*      */         
/*  536 */         E object = (E)objects[i];
/*  537 */         assign(data, index, validate(index, object));
/*      */       } 
/*  539 */       setData(data);
/*  540 */       for (i = start, index = size; i < end; i++, index++) {
/*      */         
/*  542 */         E object = (E)objects[i];
/*  543 */         didAdd(index, object);
/*  544 */         didChange();
/*      */       } 
/*  546 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  550 */     this.modCount++;
/*  551 */     return false;
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
/*  570 */     int growth = end - start;
/*  571 */     if (growth > 0) {
/*      */       
/*  573 */       Object[] oldData = data();
/*  574 */       int oldSize = (oldData == null) ? 0 : oldData.length;
/*  575 */       int size = oldSize + growth;
/*  576 */       Object[] data = grow(size);
/*      */       
/*  578 */       int shifted = oldSize - index;
/*  579 */       if (shifted > 0)
/*      */       {
/*  581 */         System.arraycopy(oldData, index, data, index + growth, shifted);
/*      */       }
/*      */       int i, currentIndex;
/*  584 */       for (i = start, currentIndex = index; i < end; i++, currentIndex++) {
/*      */         
/*  586 */         E object = (E)objects[i];
/*  587 */         assign(data, currentIndex, validate(currentIndex, object));
/*      */       } 
/*  589 */       setData(data);
/*  590 */       for (i = start, currentIndex = index; i < end; i++, currentIndex++) {
/*      */         
/*  592 */         E object = (E)objects[i];
/*  593 */         didAdd(currentIndex, object);
/*  594 */         didChange();
/*      */       } 
/*  596 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  600 */     this.modCount++;
/*  601 */     return false;
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
/*      */   public boolean removeAll(Collection<?> collection) {
/*  614 */     Object[] data = data();
/*  615 */     int size = (data == null) ? 0 : data.length;
/*  616 */     boolean modified = false;
/*  617 */     for (int i = size; --i >= 0;) {
/*      */       
/*  619 */       if (collection.contains(data[i])) {
/*      */         
/*  621 */         remove(i);
/*  622 */         modified = true;
/*      */       } 
/*      */     } 
/*      */     
/*  626 */     return modified;
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
/*      */   public E remove(int index) {
/*  640 */     Object[] data = data();
/*  641 */     int size = (data == null) ? 0 : data.length;
/*  642 */     if (index >= size) {
/*  643 */       throw new AbstractEList.BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  645 */     E oldObject = (E)data[index];
/*      */     
/*  647 */     Object[] newData = newData(size - 1);
/*  648 */     System.arraycopy(data, 0, newData, 0, index);
/*  649 */     int shifted = size - index - 1;
/*  650 */     if (shifted > 0)
/*      */     {
/*  652 */       System.arraycopy(data, index + 1, newData, index, shifted);
/*      */     }
/*      */     
/*  655 */     setData(newData);
/*  656 */     didRemove(index, oldObject);
/*  657 */     didChange();
/*      */     
/*  659 */     return oldObject;
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
/*      */   public boolean retainAll(Collection<?> collection) {
/*  674 */     boolean modified = false;
/*  675 */     Object[] data = data();
/*  676 */     int size = (data == null) ? 0 : data.length;
/*  677 */     for (int i = size; --i >= 0;) {
/*      */       
/*  679 */       if (!collection.contains(data[i])) {
/*      */         
/*  681 */         remove(i);
/*  682 */         modified = true;
/*      */       } 
/*      */     } 
/*  685 */     return modified;
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
/*  696 */     this.modCount++;
/*      */     
/*  698 */     Object[] oldData = data();
/*  699 */     int oldSize = (oldData == null) ? 0 : oldData.length;
/*      */ 
/*      */ 
/*      */     
/*  703 */     setData((Object[])null);
/*      */     
/*  705 */     didClear(oldSize, oldData);
/*  706 */     didChange();
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
/*      */   public E move(int targetIndex, int sourceIndex) {
/*  722 */     Object[] data = copy();
/*  723 */     int size = (data == null) ? 0 : data.length;
/*  724 */     if (targetIndex >= size) {
/*  725 */       throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + size);
/*      */     }
/*  727 */     if (sourceIndex >= size) {
/*  728 */       throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + size);
/*      */     }
/*  730 */     E object = (E)data[sourceIndex];
/*  731 */     if (targetIndex != sourceIndex) {
/*      */       
/*  733 */       if (targetIndex < sourceIndex) {
/*      */         
/*  735 */         System.arraycopy(data, targetIndex, data, targetIndex + 1, sourceIndex - targetIndex);
/*      */       }
/*      */       else {
/*      */         
/*  739 */         System.arraycopy(data, sourceIndex + 1, data, sourceIndex, targetIndex - sourceIndex);
/*      */       } 
/*  741 */       assign(data, targetIndex, object);
/*  742 */       setData(data);
/*  743 */       didMove(targetIndex, object, sourceIndex);
/*  744 */       didChange();
/*      */     } 
/*  746 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] grow(int size) {
/*  754 */     Object[] oldData = data();
/*  755 */     Object[] data = newData(size);
/*  756 */     if (oldData != null)
/*      */     {
/*  758 */       System.arraycopy(oldData, 0, data, 0, oldData.length);
/*      */     }
/*  760 */     return data;
/*      */   }
/*      */   
/*  763 */   private static Object[] EMPTY_ARRAY = new Object[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] copy() {
/*  770 */     Object[] data = data();
/*  771 */     if (data != null) {
/*      */       
/*  773 */       Object[] newData = newData(data.length);
/*  774 */       System.arraycopy(data, 0, newData, 0, data.length);
/*  775 */       return newData;
/*      */     } 
/*      */ 
/*      */     
/*  779 */     return EMPTY_ARRAY;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
/*  785 */     objectOutputStream.defaultWriteObject();
/*  786 */     Object[] data = data();
/*  787 */     int size = (data == null) ? 0 : data.length;
/*  788 */     if (data == null) {
/*      */       
/*  790 */       objectOutputStream.writeInt(0);
/*      */     }
/*      */     else {
/*      */       
/*  794 */       objectOutputStream.writeInt(size);
/*  795 */       for (int i = 0; i < size; i++)
/*      */       {
/*  797 */         objectOutputStream.writeObject(data[i]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
/*  804 */     objectInputStream.defaultReadObject();
/*  805 */     int size = objectInputStream.readInt();
/*      */     
/*  807 */     if (size > 0) {
/*      */       Object[] data;
/*      */       
/*      */       try {
/*  811 */         data = newData(size);
/*      */       }
/*  813 */       catch (Throwable exception) {
/*      */         
/*  815 */         data = new Object[size];
/*      */       } 
/*      */       
/*  818 */       setData(data);
/*      */       
/*  820 */       for (int i = 0; i < size; i++) {
/*      */         
/*  822 */         E object = (E)objectInputStream.readObject();
/*  823 */         didAdd(i, assign(data, i, object));
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
/*  837 */       ArrayDelegatingEList<E> clone = (ArrayDelegatingEList<E>)super.clone();
/*  838 */       Object[] data = data();
/*  839 */       int size = (data == null) ? 0 : data.length;
/*  840 */       if (size > 0) {
/*      */         
/*  842 */         Object[] newData = newData(size);
/*  843 */         clone.setData(newData);
/*  844 */         System.arraycopy(data, 0, newData, 0, size);
/*      */       } 
/*  846 */       return clone;
/*      */     }
/*  848 */     catch (CloneNotSupportedException exception) {
/*      */       
/*  850 */       throw new InternalError();
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
/*      */   public Iterator<E> iterator() {
/*  863 */     return new EIterator<E>();
/*      */   }
/*      */ 
/*      */   
/*      */   protected class EIterator<E1>
/*      */     extends AbstractEList<E>.EIterator<E1>
/*      */   {
/*      */     protected Object[] expectedData;
/*      */ 
/*      */     
/*      */     protected EIterator() {
/*  874 */       this.expectedData = ArrayDelegatingEList.this.data();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/*  883 */       if (ArrayDelegatingEList.this.modCount != this.expectedModCount || ArrayDelegatingEList.this.data() != this.expectedData)
/*      */       {
/*  885 */         throw new ConcurrentModificationException();
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
/*      */   protected Iterator<E> basicIterator() {
/*  898 */     return new NonResolvingEIterator<E>();
/*      */   }
/*      */ 
/*      */   
/*      */   protected class NonResolvingEIterator<E1>
/*      */     extends AbstractEList<E>.NonResolvingEIterator<E1>
/*      */   {
/*      */     protected Object[] expectedData;
/*      */ 
/*      */     
/*      */     protected NonResolvingEIterator() {
/*  909 */       this.expectedData = ArrayDelegatingEList.this.data();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/*  918 */       if (ArrayDelegatingEList.this.modCount != this.expectedModCount || ArrayDelegatingEList.this.data() != this.expectedData)
/*      */       {
/*  920 */         throw new ConcurrentModificationException();
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
/*      */   
/*      */   public ListIterator<E> listIterator() {
/*  934 */     return new EListIterator<E>();
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
/*  948 */     int size = size();
/*  949 */     if (index < 0 || index > size) {
/*  950 */       throw new AbstractEList.BasicIndexOutOfBoundsException(index, size);
/*      */     }
/*  952 */     return new EListIterator<E>(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class EListIterator<E1>
/*      */     extends AbstractEList<E>.EListIterator<E1>
/*      */   {
/*  963 */     protected Object[] expectedData = ArrayDelegatingEList.this.data();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EListIterator(int index) {
/*  979 */       super(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/*  989 */       if (ArrayDelegatingEList.this.modCount != this.expectedModCount || ArrayDelegatingEList.this.data() != this.expectedData)
/*      */       {
/*  991 */         throw new ConcurrentModificationException();
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
/*      */   protected ListIterator<E> basicListIterator() {
/* 1004 */     return new NonResolvingEListIterator<E>();
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
/*      */   protected ListIterator<E> basicListIterator(int index) {
/* 1017 */     int size = size();
/* 1018 */     if (index < 0 || index > size) {
/* 1019 */       throw new AbstractEList.BasicIndexOutOfBoundsException(index, size);
/*      */     }
/* 1021 */     return new NonResolvingEListIterator<E>(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class NonResolvingEListIterator<E1>
/*      */     extends AbstractEList<E>.NonResolvingEListIterator<E1>
/*      */   {
/* 1032 */     protected Object[] expectedData = ArrayDelegatingEList.this.data();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NonResolvingEListIterator() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NonResolvingEListIterator(int index) {
/* 1048 */       super(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/* 1058 */       if (ArrayDelegatingEList.this.modCount != this.expectedModCount || ArrayDelegatingEList.this.data() != this.expectedData)
/*      */       {
/* 1060 */         throw new ConcurrentModificationException();
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
/*      */   protected List<E> basicList() {
/* 1072 */     Object[] data = data();
/* 1073 */     int size = (data == null) ? 0 : data.length;
/* 1074 */     if (size == 0)
/*      */     {
/* 1076 */       return ECollections.emptyEList();
/*      */     }
/*      */ 
/*      */     
/* 1080 */     return new BasicEList.UnmodifiableEList<E>(size, data);
/*      */   }
/*      */   
/*      */   public abstract Object[] data();
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\ArrayDelegatingEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */