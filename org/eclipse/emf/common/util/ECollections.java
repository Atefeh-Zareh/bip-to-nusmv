/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
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
/*     */ public class ECollections
/*     */ {
/*     */   private static class BasicEmptyUnmodifiableEList<E>
/*     */   {
/*     */     public int size() {
/* 578 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 583 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 589 */       return Collections.EMPTY_LIST.equals(o);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 595 */       return Collections.EMPTY_LIST.hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public E get(int index) {
/* 600 */       Collections.EMPTY_LIST.get(index);
/* 601 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean contains(Object o) {
/* 606 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int indexOf(Object o) {
/* 611 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int lastIndexOf(Object o) {
/* 616 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 620 */     ListIterator<E> listIterator = new ListIterator<E>()
/*     */       {
/*     */         public boolean hasNext()
/*     */         {
/* 624 */           return false;
/*     */         }
/*     */         
/*     */         public E next() {
/* 628 */           throw new NoSuchElementException();
/*     */         }
/*     */         
/*     */         public boolean hasPrevious() {
/* 632 */           return false;
/*     */         }
/*     */         
/*     */         public E previous() {
/* 636 */           throw new NoSuchElementException();
/*     */         }
/*     */         
/*     */         public int nextIndex() {
/* 640 */           return 0;
/*     */         }
/*     */         
/*     */         public int previousIndex() {
/* 644 */           return -1;
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 649 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void set(E o) {
/* 653 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void add(E o) {
/* 657 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*     */     public Iterator<E> iterator() {
/* 663 */       return this.listIterator;
/*     */     }
/*     */ 
/*     */     
/*     */     public ListIterator<E> listIterator() {
/* 668 */       return this.listIterator;
/*     */     }
/*     */ 
/*     */     
/*     */     public ListIterator<E> listIterator(int index) {
/* 673 */       return this.listIterator;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<E> subList(int fromIndex, int toIndex) {
/* 678 */       return Collections.<E>emptyList().subList(fromIndex, toIndex);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray() {
/* 683 */       return Collections.EMPTY_LIST.toArray();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T[] toArray(Object[] a) {
/* 688 */       return (T[])Collections.emptyList().toArray(a);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 694 */       return Collections.EMPTY_LIST.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean add(E o) {
/* 699 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean remove(Object o) {
/* 704 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsAll(Collection<?> coll) {
/* 709 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addAll(Collection<? extends E> coll) {
/* 714 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean removeAll(Collection<?> coll) {
/* 719 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean retainAll(Collection<?> coll) {
/* 724 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 729 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public E set(int index, E element) {
/* 734 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void add(int index, E element) {
/* 739 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public E remove(int index) {
/* 744 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addAll(int index, Collection<? extends E> collection) {
/* 749 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void move(int newPosition, E o) {
/* 754 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     private BasicEmptyUnmodifiableEList() {}
/*     */     
/* 759 */     public E move(int newPosition, int oldPosition) { throw new UnsupportedOperationException(); }
/*     */   } private static class UnmodifiableEList<E> implements EList<E> {
/*     */     protected EList<? extends E> list; public UnmodifiableEList(EList<? extends E> list) { this.list = list; } public int size() { return this.list.size(); } public boolean isEmpty() { return this.list.isEmpty(); } public boolean contains(Object o) { return this.list.contains(o); } public Object[] toArray() { return this.list.toArray(); } public <T> T[] toArray(Object[] a) { return (T[])this.list.toArray(a); } public String toString() { return this.list.toString(); } public Iterator<E> iterator() { return new Iterator<E>() { Iterator<? extends E> i = ECollections.UnmodifiableEList.this.list.iterator(); public boolean hasNext() { return this.i.hasNext(); } public E next() { return this.i.next(); } public void remove() { throw new UnsupportedOperationException(); } }
/*     */         ; } public boolean add(E o) { throw new UnsupportedOperationException(); } public boolean remove(Object o) { throw new UnsupportedOperationException(); } public boolean containsAll(Collection<?> coll) { return this.list.containsAll(coll); } public boolean addAll(Collection<? extends E> coll) { throw new UnsupportedOperationException(); } public boolean removeAll(Collection<?> coll) { throw new UnsupportedOperationException(); } public boolean retainAll(Collection<?> coll) { throw new UnsupportedOperationException(); } public void clear() { throw new UnsupportedOperationException(); } public boolean equals(Object o) { return this.list.equals(o); } public int hashCode() { return this.list.hashCode(); } public E get(int index) { return this.list.get(index); } public E set(int index, E element) { throw new UnsupportedOperationException(); } public void add(int index, Object element) { throw new UnsupportedOperationException(); } public E remove(int index) { throw new UnsupportedOperationException(); } public int indexOf(Object o) { return this.list.indexOf(o); } public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); } public boolean addAll(int index, Collection<? extends E> collection) { throw new UnsupportedOperationException(); } public ListIterator<E> listIterator() { return listIterator(0); } public ListIterator<E> listIterator(int index) { return new ListIterator<E>(index) {
/*     */           ListIterator<? extends E> i; public boolean hasNext() { return this.i.hasNext(); } public E next() { return this.i.next(); } public boolean hasPrevious() { return this.i.hasPrevious(); } public E previous() { return this.i.previous(); } public int nextIndex() { return this.i.nextIndex(); } public int previousIndex() { return this.i.previousIndex(); } public void remove() { throw new UnsupportedOperationException(); } public void set(E o) { throw new UnsupportedOperationException(); } public void add(E o) { throw new UnsupportedOperationException(); }
/*     */         }; } public List<E> subList(int fromIndex, int toIndex) { return new UnmodifiableEList(new BasicEList<E>(this.list.subList(fromIndex, toIndex))); } public void move(int newPosition, E o) { throw new UnsupportedOperationException(); } public E move(int newPosition, int oldPosition) { throw new UnsupportedOperationException(); }
/*     */   } public static <T> void move(List<T> list, int newPosition, T object) { if (list instanceof EList) { ((EList<T>)list).move(newPosition, object); } else { list.remove(object); list.add(newPosition, object); }  } public static <T> T move(List<T> list, int targetIndex, int sourceIndex) { if (list instanceof EList) return ((EList<T>)list).move(targetIndex, sourceIndex);  T object = list.remove(sourceIndex); list.add(targetIndex, object); return object; } public static void reverse(EList<?> list) { int last = list.size() - 1; for (int i = 0; i < last; i++) list.move(i, last);  } public static int indexOf(List<?> list, Object o, int fromIndex) { if (fromIndex < 0) fromIndex = 0;  int size = list.size(); for (int i = fromIndex; i < size; i++) { Object element = list.get(i); if (o == null) { if (element == null) return i;  } else if (o == element || o.equals(element)) { return i; }  }  return -1; } public static void sort(EList<?> list) { Object[] listAsArray = list.toArray(); Arrays.sort(listAsArray); for (int i = 0; i < listAsArray.length; i++) { int oldIndex = indexOf(list, listAsArray[i], i); if (i != oldIndex) list.move(i, oldIndex);  }  } public static <T> void sort(EList<T> list, Comparator<? super T> comparator) { Object[] listAsArray = list.toArray(); Comparator<Object> objectComparator = (Comparator)comparator; Arrays.sort(listAsArray, objectComparator); for (int i = 0; i < listAsArray.length; i++) { int oldIndex = indexOf(list, listAsArray[i], i); if (i != oldIndex) list.move(i, oldIndex);  }  } public static <T> void setEList(EList<T> eList, List<? extends T> prototypeList) { int index = 0; for (T prototypeObject : prototypeList) { if (eList.size() <= index) { eList.add(prototypeObject); } else { boolean done; do { done = true; Object targetObject = eList.get(index); if ((targetObject == null) ? (prototypeObject != null) : !targetObject.equals(prototypeObject)) continue;  int position = indexOf(eList, prototypeObject, index); if (position != -1) { int targetIndex = indexOf(prototypeList, targetObject, index); if (targetIndex == -1) { eList.remove(index); done = false; } else if (targetIndex > position) { if (eList.size() <= targetIndex) targetIndex = eList.size() - 1;  eList.move(targetIndex, index); done = false; } else { eList.move(index, position); }  } else { eList.add(index, prototypeObject); }  } while (!done); }  index++; }  for (int i = eList.size(); i > index;) eList.remove(--i);  } public static <T> EList<T> unmodifiableEList(EList<? extends T> list) { return new UnmodifiableEList<T>(list); } public static <K, V> EMap<K, V> unmodifiableEMap(EMap<? extends K, ? extends V> map) { return new UnmodifiableEMap<K, V>(map); } public static final EList<?> EMPTY_ELIST = new EmptyUnmodifiableEList(null); public static <T> EList<T> emptyEList() { return (EList)EMPTY_ELIST; } public static final EMap<?, ?> EMPTY_EMAP = new EmptyUnmodifiableEMap(null); public static <K, V> EMap<K, V> emptyEMap() { return (EMap)EMPTY_EMAP; } private static class UnmodifiableEMap<K, V> extends UnmodifiableEList<Map.Entry<K, V>> implements EMap<K, V> {
/*     */     protected EMap<? extends K, ? extends V> eMap; public UnmodifiableEMap(EMap<? extends K, ? extends V> eMap) { super((EList)eMap); this.eMap = eMap; } public boolean containsKey(Object key) { return this.eMap.containsKey(key); } public boolean containsValue(Object value) { return this.eMap.containsValue(value); } public Set<Map.Entry<K, V>> entrySet() { return Collections.unmodifiableSet((Set)this.eMap.entrySet()); } public V get(Object key) { return this.eMap.get(key); } public int indexOfKey(Object key) { return this.eMap.indexOf(key); } public Set<K> keySet() { return Collections.unmodifiableSet(this.eMap.keySet()); } public Map<K, V> map() { return Collections.unmodifiableMap(this.eMap.map()); } public Collection<V> values() { return Collections.unmodifiableCollection(this.eMap.values()); } public V put(K key, V value) { throw new UnsupportedOperationException(); } public void putAll(EMap<? extends K, ? extends V> map) { throw new UnsupportedOperationException(); } public void putAll(Map<? extends K, ? extends V> map) { throw new UnsupportedOperationException(); } public V removeKey(Object key) { throw new UnsupportedOperationException(); }
/* 767 */   } private static class EmptyUnmodifiableEList extends BasicEmptyUnmodifiableEList<Object> implements EList<Object> { private EmptyUnmodifiableEList() { super(null); }
/*     */      }
/*     */   
/*     */   private static class EmptyUnmodifiableEMap extends BasicEmptyUnmodifiableEList<Map.Entry<Object, Object>> implements EMap<Object, Object> { private EmptyUnmodifiableEMap() {
/* 771 */       super(null);
/*     */     }
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 775 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 780 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<Object, Object>> entrySet() {
/* 785 */       return Collections.emptySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object get(Object key) {
/* 790 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int indexOfKey(Object key) {
/* 795 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Object> keySet() {
/* 800 */       return Collections.emptySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<Object, Object> map() {
/* 805 */       return Collections.emptyMap();
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<Object> values() {
/* 810 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object put(Object key, Object value) {
/* 815 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putAll(EMap<? extends Object, ? extends Object> map) {
/* 820 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putAll(Map<? extends Object, ? extends Object> map) {
/* 825 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object removeKey(Object key) {
/* 830 */       throw new UnsupportedOperationException();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\ECollections.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */