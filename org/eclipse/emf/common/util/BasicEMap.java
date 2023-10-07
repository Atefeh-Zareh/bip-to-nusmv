/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BasicEMap<K, V>
/*      */   implements EMap<K, V>, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected transient EList<Entry<K, V>> delegateEList;
/*      */   protected int size;
/*      */   protected transient BasicEList<Entry<K, V>>[] entryData;
/*      */   protected transient int modCount;
/*      */   protected transient View<K, V> view;
/*      */   
/*      */   protected static class View<K, V>
/*      */   {
/*      */     public transient Map<K, V> map;
/*      */     public transient Set<K> keySet;
/*      */     public transient Set<Map.Entry<K, V>> entrySet;
/*      */     public transient Collection<V> values;
/*      */   }
/*      */   
/*      */   public BasicEMap() {
/*  138 */     initializeDelegateEList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeDelegateEList() {
/*  148 */     this.delegateEList = 
/*  149 */       new BasicEList<Entry<K, V>>()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didAdd(int index, BasicEMap.Entry<K, V> newObject) {
/*  156 */           BasicEMap.this.doPut(newObject);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didSet(int index, BasicEMap.Entry<K, V> newObject, BasicEMap.Entry<K, V> oldObject) {
/*  162 */           didRemove(index, oldObject);
/*  163 */           didAdd(index, newObject);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didRemove(int index, BasicEMap.Entry<K, V> oldObject) {
/*  169 */           BasicEMap.this.doRemove(oldObject);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didClear(int size, Object[] oldObjects) {
/*  175 */           BasicEMap.this.doClear();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didMove(int index, BasicEMap.Entry<K, V> movedObject, int oldIndex) {
/*  181 */           BasicEMap.this.doMove(movedObject);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BasicEMap(int initialCapacity) {
/*  193 */     this();
/*      */     
/*  195 */     if (initialCapacity < 0)
/*      */     {
/*  197 */       throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
/*      */     }
/*      */     
/*  200 */     this.entryData = newEntryData(initialCapacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BasicEMap(Map<? extends K, ? extends V> map) {
/*  209 */     this();
/*  210 */     int mapSize = map.size();
/*  211 */     if (mapSize > 0) {
/*      */       
/*  213 */       this.entryData = newEntryData(2 * mapSize);
/*  214 */       putAll(map);
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
/*      */   protected BasicEList<Entry<K, V>>[] newEntryData(int capacity) {
/*  228 */     return (BasicEList<Entry<K, V>>[])new BasicEList[capacity];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureEntryDataExists() {
/*  237 */     if (this.entryData == null) {
/*      */       
/*  239 */       this.entryData = newEntryData(2 * this.size + 1);
/*      */ 
/*      */ 
/*      */       
/*  243 */       int oldModCount = this.modCount;
/*  244 */       this.size = 0;
/*  245 */       for (Entry<K, V> entry : this.delegateEList)
/*      */       {
/*  247 */         doPut(entry);
/*      */       }
/*  249 */       this.modCount = oldModCount;
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
/*      */   protected BasicEList<Entry<K, V>> newList() {
/*  263 */     return 
/*  264 */       new BasicEList<Entry<K, V>>()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Object[] newData(int listCapacity) {
/*  271 */           return (Object[])new BasicEMap.EntryImpl[listCapacity];
/*      */         }
/*      */       };
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
/*      */   protected Entry<K, V> newEntry(int hash, K key, V value) {
/*  289 */     validateKey(key);
/*  290 */     validateValue(value);
/*  291 */     return new EntryImpl(hash, key, value);
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
/*      */   protected V putEntry(Entry<K, V> entry, V value) {
/*  303 */     return entry.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean useEqualsForKey() {
/*  314 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean useEqualsForValue() {
/*  325 */     return true;
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
/*      */   protected V resolve(K key, V value) {
/*  338 */     return value;
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
/*      */   protected void validateKey(K key) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void validateValue(V value) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void didAdd(Entry<K, V> entry) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void didModify(Entry<K, V> entry, V oldValue) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void didRemove(Entry<K, V> entry) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void didClear(BasicEList[] oldEntryData) {
/*  408 */     if (oldEntryData != null)
/*      */     {
/*  410 */       for (int i = 0; i < oldEntryData.length; i++) {
/*      */         
/*  412 */         BasicEList<Entry<K, V>> eList = oldEntryData[i];
/*  413 */         if (eList != null) {
/*      */           
/*  415 */           Entry[] entries = (Entry[])eList.data;
/*  416 */           int size = eList.size;
/*  417 */           for (int j = 0; j < size; j++) {
/*      */             
/*  419 */             Entry<K, V> entry = entries[j];
/*  420 */             didRemove(entry);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  433 */     return this.size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  442 */     return (this.size == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOfKey(Object key) {
/*  450 */     if (useEqualsForKey() && key != null) {
/*      */       
/*  452 */       for (int i = 0, size = this.delegateEList.size(); i < size; i++)
/*      */       {
/*  454 */         Entry<K, V> entry = this.delegateEList.get(i);
/*  455 */         if (key.equals(entry.getKey()))
/*      */         {
/*  457 */           return i;
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  463 */       for (int i = 0, size = this.delegateEList.size(); i < size; i++) {
/*      */         
/*  465 */         Entry<K, V> entry = this.delegateEList.get(i);
/*  466 */         if (key == entry.getKey())
/*      */         {
/*  468 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  473 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  481 */     if (this.size > 0) {
/*      */       
/*  483 */       ensureEntryDataExists();
/*  484 */       int hash = hashOf(key);
/*  485 */       int index = indexOf(hash);
/*  486 */       int entryIndex = entryIndexForKey(index, hash, key);
/*  487 */       return (entryIndex != -1);
/*      */     } 
/*      */ 
/*      */     
/*  491 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  500 */     if (this.size > 0) {
/*      */       
/*  502 */       ensureEntryDataExists();
/*      */       
/*  504 */       if (useEqualsForValue() && value != null) {
/*      */         
/*  506 */         for (int i = 0; i < this.entryData.length; i++) {
/*      */           
/*  508 */           BasicEList<Entry<K, V>> eList = this.entryData[i];
/*  509 */           if (eList != null) {
/*      */             
/*  511 */             Entry[] entries = (Entry[])eList.data;
/*  512 */             int size = eList.size;
/*  513 */             for (int j = 0; j < size; j++)
/*      */             {
/*  515 */               Entry<K, V> entry = entries[j];
/*  516 */               if (value.equals(entry.getValue()))
/*      */               {
/*  518 */                 return true;
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  526 */         for (int i = 0; i < this.entryData.length; i++) {
/*      */           
/*  528 */           BasicEList<Entry<K, V>> eList = this.entryData[i];
/*  529 */           if (eList != null) {
/*      */             
/*  531 */             Entry[] entries = (Entry[])eList.data;
/*  532 */             int size = eList.size;
/*  533 */             for (int j = 0; j < size; j++) {
/*      */               
/*  535 */               Entry<K, V> entry = entries[j];
/*  536 */               if (value == entry.getValue())
/*      */               {
/*  538 */                 return true;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  546 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V get(Object key) {
/*  554 */     if (this.size > 0) {
/*      */       
/*  556 */       ensureEntryDataExists();
/*  557 */       int hash = hashOf(key);
/*  558 */       int index = indexOf(hash);
/*  559 */       Entry<K, V> entry = entryForKey(index, hash, key);
/*  560 */       if (entry != null) {
/*      */         
/*  562 */         K object = (K)key;
/*  563 */         return resolve(object, entry.getValue());
/*      */       } 
/*      */     } 
/*      */     
/*  567 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V put(K key, V value) {
/*  575 */     ensureEntryDataExists();
/*      */     
/*  577 */     int hash = hashOf(key);
/*  578 */     if (this.size > 0) {
/*      */       
/*  580 */       int index = indexOf(hash);
/*  581 */       Entry<K, V> entry1 = entryForKey(index, hash, key);
/*  582 */       if (entry1 != null) {
/*      */         
/*  584 */         V result = putEntry(entry1, value);
/*  585 */         didModify(entry1, result);
/*  586 */         return result;
/*      */       } 
/*      */     } 
/*      */     
/*  590 */     Entry<K, V> entry = newEntry(hash, key, value);
/*  591 */     this.delegateEList.add(entry);
/*  592 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doPut(Entry<K, V> entry) {
/*  601 */     if (this.entryData == null) {
/*      */       
/*  603 */       this.modCount++;
/*  604 */       this.size++;
/*      */     }
/*      */     else {
/*      */       
/*  608 */       int hash = entry.getHash();
/*  609 */       grow(this.size + 1);
/*  610 */       int index = indexOf(hash);
/*  611 */       BasicEList<Entry<K, V>> eList = this.entryData[index];
/*  612 */       if (eList == null)
/*      */       {
/*  614 */         eList = this.entryData[index] = newList();
/*      */       }
/*  616 */       eList.add(entry);
/*  617 */       this.size++;
/*  618 */       didAdd(entry);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V removeKey(Object key) {
/*  627 */     ensureEntryDataExists();
/*      */     
/*  629 */     int hash = hashOf(key);
/*  630 */     int index = indexOf(hash);
/*  631 */     Entry<K, V> entry = entryForKey(index, hash, key);
/*  632 */     if (entry != null) {
/*      */       
/*  634 */       remove(entry);
/*  635 */       return entry.getValue();
/*      */     } 
/*      */ 
/*      */     
/*  639 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doRemove(Entry<K, V> entry) {
/*  649 */     if (this.entryData == null) {
/*      */       
/*  651 */       this.modCount++;
/*  652 */       this.size--;
/*      */     }
/*      */     else {
/*      */       
/*  656 */       Object key = entry.getKey();
/*  657 */       int hash = entry.getHash();
/*  658 */       int index = indexOf(hash);
/*  659 */       removeEntry(index, entryIndexForKey(index, hash, key));
/*  660 */       didRemove(entry);
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
/*      */   protected V removeEntry(int index, int entryIndex) {
/*  672 */     this.modCount++;
/*  673 */     this.size--;
/*      */     
/*  675 */     Entry<K, V> entry = this.entryData[index].remove(entryIndex);
/*  676 */     return entry.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends K, ? extends V> map) {
/*  684 */     for (Map.Entry<? extends K, ? extends V> entry : map.entrySet())
/*      */     {
/*  686 */       put(entry.getKey(), entry.getValue());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(EMap<? extends K, ? extends V> map) {
/*  695 */     for (Map.Entry<? extends K, ? extends V> entry : map)
/*      */     {
/*  697 */       put(entry.getKey(), entry.getValue());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doClear() {
/*  706 */     if (this.entryData == null) {
/*      */       
/*  708 */       this.modCount++;
/*  709 */       this.size = 0;
/*  710 */       didClear(null);
/*      */     }
/*      */     else {
/*      */       
/*  714 */       this.modCount++;
/*  715 */       BasicEList<Entry<K, V>>[] arrayOfBasicEList = this.entryData;
/*  716 */       this.entryData = null;
/*  717 */       this.size = 0;
/*  718 */       didClear(arrayOfBasicEList);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doMove(Entry<K, V> entry) {
/*  727 */     this.modCount++;
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
/*  739 */       BasicEMap<K, V> result = (BasicEMap<K, V>)super.clone();
/*  740 */       if (this.entryData != null) {
/*      */         
/*  742 */         result.entryData = newEntryData(this.entryData.length);
/*  743 */         for (int i = 0; i < this.entryData.length; i++) {
/*      */ 
/*      */           
/*  746 */           BasicEList<Entry<K, V>> basicEList = (this.entryData[i] == null) ? null : (BasicEList<Entry<K, V>>)this.entryData[i].clone();
/*  747 */           result.entryData[i] = basicEList;
/*      */         } 
/*      */       } 
/*  750 */       result.view = null;
/*  751 */       result.modCount = 0;
/*  752 */       return result;
/*      */     }
/*  754 */     catch (CloneNotSupportedException exception) {
/*      */       
/*  756 */       throw new InternalError();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DelegatingMap
/*      */     implements EMap.InternalMapView<K, V>
/*      */   {
/*      */     public EMap<K, V> eMap() {
/*  769 */       return BasicEMap.this;
/*      */     }
/*      */ 
/*      */     
/*      */     public int size() {
/*  774 */       return BasicEMap.this.size();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isEmpty() {
/*  779 */       return BasicEMap.this.isEmpty();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean containsKey(Object key) {
/*  784 */       return BasicEMap.this.containsKey(key);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean containsValue(Object value) {
/*  789 */       return BasicEMap.this.containsValue(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public V get(Object key) {
/*  794 */       return (V)BasicEMap.this.get(key);
/*      */     }
/*      */ 
/*      */     
/*      */     public V put(K key, V value) {
/*  799 */       return BasicEMap.this.put(key, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public V remove(Object key) {
/*  804 */       return (V)BasicEMap.this.removeKey(key);
/*      */     }
/*      */ 
/*      */     
/*      */     public void putAll(Map<? extends K, ? extends V> map) {
/*  809 */       BasicEMap.this.putAll(map);
/*      */     }
/*      */ 
/*      */     
/*      */     public void clear() {
/*  814 */       BasicEMap.this.clear();
/*      */     }
/*      */ 
/*      */     
/*      */     public Set<K> keySet() {
/*  819 */       return BasicEMap.this.keySet();
/*      */     }
/*      */ 
/*      */     
/*      */     public Collection<V> values() {
/*  824 */       return BasicEMap.this.values();
/*      */     }
/*      */ 
/*      */     
/*      */     public Set<Map.Entry<K, V>> entrySet() {
/*  829 */       return BasicEMap.this.entrySet();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object object) {
/*  835 */       return BasicEMap.this.equals(object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  841 */       return BasicEMap.this.hashCode();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<K, V> map() {
/*  850 */     if (this.view == null)
/*      */     {
/*  852 */       this.view = new View<K, V>();
/*      */     }
/*  854 */     if (this.view.map == null)
/*      */     {
/*  856 */       this.view.map = new DelegatingMap();
/*      */     }
/*      */     
/*  859 */     return this.view.map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<K> keySet() {
/*  867 */     if (this.view == null)
/*      */     {
/*  869 */       this.view = new View<K, V>();
/*      */     }
/*      */     
/*  872 */     if (this.view.keySet == null)
/*      */     {
/*  874 */       this.view.keySet = 
/*  875 */         new AbstractSet<K>()
/*      */         {
/*      */           
/*      */           public Iterator<K> iterator()
/*      */           {
/*  880 */             return (BasicEMap.this.size == 0) ? ECollections.<K>emptyEList().iterator() : new BasicEMap.BasicEMapKeyIterator();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/*  886 */             return BasicEMap.this.size;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean contains(Object key) {
/*  892 */             return BasicEMap.this.containsKey(key);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean remove(Object key) {
/*  898 */             int oldSize = BasicEMap.this.size;
/*  899 */             BasicEMap.this.removeKey(key);
/*  900 */             return (BasicEMap.this.size != oldSize);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void clear() {
/*  906 */             BasicEMap.this.clear();
/*      */           }
/*      */         };
/*      */     }
/*  910 */     return this.view.keySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<V> values() {
/*  918 */     if (this.view == null)
/*      */     {
/*  920 */       this.view = new View<K, V>();
/*      */     }
/*  922 */     if (this.view.values == null)
/*      */     {
/*  924 */       this.view.values = 
/*  925 */         new AbstractCollection<V>()
/*      */         {
/*      */           
/*      */           public Iterator<V> iterator()
/*      */           {
/*  930 */             return (BasicEMap.this.size == 0) ? ECollections.<V>emptyEList().iterator() : new BasicEMap.BasicEMapValueIterator();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/*  936 */             return BasicEMap.this.size;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean contains(Object value) {
/*  942 */             return BasicEMap.this.containsValue(value);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void clear() {
/*  948 */             BasicEMap.this.clear();
/*      */           }
/*      */         };
/*      */     }
/*  952 */     return this.view.values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<K, V>> entrySet() {
/*  960 */     if (this.view == null)
/*      */     {
/*  962 */       this.view = new View<K, V>();
/*      */     }
/*  964 */     if (this.view.entrySet == null)
/*      */     {
/*  966 */       this.view.entrySet = new AbstractSet<Map.Entry<K, V>>()
/*      */         {
/*      */           
/*      */           public int size()
/*      */           {
/*  971 */             return BasicEMap.this.size;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean contains(Object object) {
/*  977 */             if (BasicEMap.this.size > 0 && object instanceof Map.Entry) {
/*      */               
/*  979 */               BasicEMap.this.ensureEntryDataExists();
/*  980 */               Map.Entry<K, V> otherEntry = (Map.Entry<K, V>)object;
/*  981 */               Object key = otherEntry.getKey();
/*      */               
/*  983 */               int hash = (key == null) ? 0 : key.hashCode();
/*  984 */               int index = BasicEMap.this.indexOf(hash);
/*  985 */               BasicEList<BasicEMap.Entry<K, V>> eList = BasicEMap.this.entryData[index];
/*  986 */               if (eList != null) {
/*      */                 
/*  988 */                 BasicEMap.Entry[] entries = (BasicEMap.Entry[])eList.data;
/*  989 */                 int size = eList.size;
/*  990 */                 for (int j = 0; j < size; j++) {
/*      */                   
/*  992 */                   BasicEMap.Entry<K, V> entry = entries[j];
/*  993 */                   if (entry.getHash() == hash && entry.equals(otherEntry))
/*      */                   {
/*  995 */                     return true;
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1000 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean remove(Object object) {
/* 1006 */             if (BasicEMap.this.size > 0 && object instanceof Map.Entry) {
/*      */               
/* 1008 */               BasicEMap.this.ensureEntryDataExists();
/* 1009 */               Map.Entry<K, V> otherEntry = (Map.Entry<K, V>)object;
/* 1010 */               Object key = otherEntry.getKey();
/* 1011 */               int hash = (key == null) ? 0 : key.hashCode();
/* 1012 */               int index = BasicEMap.this.indexOf(hash);
/* 1013 */               BasicEList<BasicEMap.Entry<K, V>> eList = BasicEMap.this.entryData[index];
/* 1014 */               if (eList != null) {
/*      */                 
/* 1016 */                 BasicEMap.Entry[] entries = (BasicEMap.Entry[])eList.data;
/* 1017 */                 int size = eList.size;
/* 1018 */                 for (int j = 0; j < size; j++) {
/*      */                   
/* 1020 */                   BasicEMap.Entry<K, V> entry = entries[j];
/* 1021 */                   if (entry.getHash() == hash && entry.equals(otherEntry)) {
/*      */ 
/*      */                     
/* 1024 */                     remove(otherEntry);
/* 1025 */                     return true;
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1030 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void clear() {
/* 1036 */             BasicEMap.this.clear();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public Iterator<Map.Entry<K, V>> iterator() {
/* 1042 */             return (BasicEMap.this.size == 0) ? ECollections.<Map.Entry<K, V>>emptyEList().iterator() : new BasicEMap.BasicEMapIterator<Map.Entry<K, V>>();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/* 1047 */     return this.view.entrySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class EntryImpl
/*      */     implements Entry<K, V>
/*      */   {
/*      */     protected int hash;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected K key;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected V value;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EntryImpl(int hash, K key, V value) {
/* 1078 */       this.hash = hash;
/* 1079 */       this.key = key;
/* 1080 */       this.value = value;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object clone() {
/* 1090 */       return BasicEMap.this.newEntry(this.hash, this.key, this.value);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getHash() {
/* 1095 */       return this.hash;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setHash(int hash) {
/* 1100 */       this.hash = hash;
/*      */     }
/*      */ 
/*      */     
/*      */     public K getKey() {
/* 1105 */       return this.key;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setKey(K key) {
/* 1110 */       throw new RuntimeException();
/*      */     }
/*      */ 
/*      */     
/*      */     public V getValue() {
/* 1115 */       return this.value;
/*      */     }
/*      */ 
/*      */     
/*      */     public V setValue(V value) {
/* 1120 */       BasicEMap.this.validateValue(value);
/*      */       
/* 1122 */       V oldValue = this.value;
/* 1123 */       this.value = value;
/* 1124 */       return oldValue;
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
/*      */     public boolean equals(Object object) {
/*      */       // Byte code:
/*      */       //   0: aload_1
/*      */       //   1: instanceof java/util/Map$Entry
/*      */       //   4: ifeq -> 114
/*      */       //   7: aload_1
/*      */       //   8: checkcast java/util/Map$Entry
/*      */       //   11: astore_2
/*      */       //   12: aload_0
/*      */       //   13: getfield this$0 : Lorg/eclipse/emf/common/util/BasicEMap;
/*      */       //   16: invokevirtual useEqualsForKey : ()Z
/*      */       //   19: ifeq -> 48
/*      */       //   22: aload_0
/*      */       //   23: getfield key : Ljava/lang/Object;
/*      */       //   26: ifnull -> 48
/*      */       //   29: aload_0
/*      */       //   30: getfield key : Ljava/lang/Object;
/*      */       //   33: aload_2
/*      */       //   34: invokeinterface getKey : ()Ljava/lang/Object;
/*      */       //   39: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */       //   42: ifeq -> 112
/*      */       //   45: goto -> 61
/*      */       //   48: aload_0
/*      */       //   49: getfield key : Ljava/lang/Object;
/*      */       //   52: aload_2
/*      */       //   53: invokeinterface getKey : ()Ljava/lang/Object;
/*      */       //   58: if_acmpne -> 112
/*      */       //   61: aload_0
/*      */       //   62: getfield this$0 : Lorg/eclipse/emf/common/util/BasicEMap;
/*      */       //   65: invokevirtual useEqualsForValue : ()Z
/*      */       //   68: ifeq -> 97
/*      */       //   71: aload_0
/*      */       //   72: getfield value : Ljava/lang/Object;
/*      */       //   75: ifnull -> 97
/*      */       //   78: aload_0
/*      */       //   79: getfield value : Ljava/lang/Object;
/*      */       //   82: aload_2
/*      */       //   83: invokeinterface getValue : ()Ljava/lang/Object;
/*      */       //   88: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */       //   91: ifeq -> 112
/*      */       //   94: goto -> 110
/*      */       //   97: aload_0
/*      */       //   98: getfield value : Ljava/lang/Object;
/*      */       //   101: aload_2
/*      */       //   102: invokeinterface getValue : ()Ljava/lang/Object;
/*      */       //   107: if_acmpne -> 112
/*      */       //   110: iconst_1
/*      */       //   111: ireturn
/*      */       //   112: iconst_0
/*      */       //   113: ireturn
/*      */       //   114: iconst_0
/*      */       //   115: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1130	-> 0
/*      */       //   #1132	-> 7
/*      */       //   #1135	-> 12
/*      */       //   #1136	-> 61
/*      */       //   #1134	-> 113
/*      */       //   #1140	-> 114
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	116	0	this	Lorg/eclipse/emf/common/util/BasicEMap$EntryImpl;
/*      */       //   0	116	1	object	Ljava/lang/Object;
/*      */       //   12	102	2	entry	Ljava/util/Map$Entry;
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/*      */       //   12	102	2	entry	Ljava/util/Map$Entry<TK;TV;>;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1147 */       return this.hash ^ ((this.value == null) ? 0 : this.value.hashCode());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1153 */       return (new StringBuilder()).append(this.key).append("->").append(this.value).toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class BasicEMapIterator<U>
/*      */     implements Iterator<U>
/*      */   {
/*      */     protected int cursor;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1170 */     protected int entryCursor = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int lastCursor;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int lastEntryCursor;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1185 */     protected int expectedModCount = BasicEMap.this.modCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     BasicEMapIterator() {
/* 1192 */       if (BasicEMap.this.size > 0)
/*      */       {
/* 1194 */         scan();
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
/*      */     protected U yield(BasicEMap.Entry<K, V> entry) {
/* 1207 */       return (U)entry;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void scan() {
/* 1215 */       BasicEMap.this.ensureEntryDataExists();
/* 1216 */       if (this.entryCursor != -1) {
/*      */         
/* 1218 */         this.entryCursor++;
/* 1219 */         BasicEList<BasicEMap.Entry<K, V>> eList = BasicEMap.this.entryData[this.cursor];
/* 1220 */         if (this.entryCursor < eList.size) {
/*      */           return;
/*      */         }
/*      */         
/* 1224 */         this.cursor++;
/*      */       } 
/*      */       
/* 1227 */       for (; this.cursor < BasicEMap.this.entryData.length; this.cursor++) {
/*      */         
/* 1229 */         BasicEList<BasicEMap.Entry<K, V>> eList = BasicEMap.this.entryData[this.cursor];
/* 1230 */         if (eList != null && !eList.isEmpty()) {
/*      */           
/* 1232 */           this.entryCursor = 0;
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 1237 */       this.entryCursor = -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 1246 */       return (this.entryCursor != -1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public U next() {
/* 1256 */       if (BasicEMap.this.modCount != this.expectedModCount)
/*      */       {
/* 1258 */         throw new ConcurrentModificationException();
/*      */       }
/*      */       
/* 1261 */       if (this.entryCursor == -1)
/*      */       {
/* 1263 */         throw new NoSuchElementException();
/*      */       }
/*      */       
/* 1266 */       this.lastCursor = this.cursor;
/* 1267 */       this.lastEntryCursor = this.entryCursor;
/*      */       
/* 1269 */       scan();
/* 1270 */       BasicEMap.Entry<K, V> result = (BasicEMap.Entry<K, V>)(BasicEMap.this.entryData[this.lastCursor]).data[this.lastEntryCursor];
/* 1271 */       return yield(result);
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
/*      */     public void remove() {
/* 1283 */       if (BasicEMap.this.modCount != this.expectedModCount)
/*      */       {
/* 1285 */         throw new ConcurrentModificationException();
/*      */       }
/*      */       
/* 1288 */       if (this.lastEntryCursor == -1)
/*      */       {
/* 1290 */         throw new IllegalStateException();
/*      */       }
/*      */       
/* 1293 */       BasicEMap.this.delegateEList.remove(BasicEMap.this.entryData[this.lastCursor].get(this.lastEntryCursor));
/*      */       
/* 1295 */       this.expectedModCount = BasicEMap.this.modCount;
/* 1296 */       this.lastEntryCursor = -1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class BasicEMapKeyIterator
/*      */     extends BasicEMapIterator<K>
/*      */   {
/*      */     protected K yield(BasicEMap.Entry<K, V> entry) {
/* 1322 */       return entry.getKey();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class BasicEMapValueIterator
/*      */     extends BasicEMapIterator<V>
/*      */   {
/*      */     protected V yield(BasicEMap.Entry<K, V> entry) {
/* 1348 */       return entry.getValue();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int hashOf(Object key) {
/* 1359 */     return (key == null) ? 0 : key.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int indexOf(int hash) {
/* 1369 */     return (hash & Integer.MAX_VALUE) % this.entryData.length;
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
/*      */   protected Entry<K, V> entryForKey(int index, int hash, Object key) {
/* 1381 */     BasicEList<Entry<K, V>> eList = this.entryData[index];
/* 1382 */     if (eList != null) {
/*      */       
/* 1384 */       Object[] entries = eList.data;
/* 1385 */       int size = eList.size;
/* 1386 */       if (useEqualsForKey() && key != null) {
/*      */         
/* 1388 */         for (int j = 0; j < size; j++)
/*      */         {
/* 1390 */           Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1391 */           if (entry.getHash() == hash && key.equals(entry.getKey()))
/*      */           {
/* 1393 */             return entry;
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1399 */         for (int j = 0; j < size; j++) {
/*      */           
/* 1401 */           Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1402 */           if (entry.getKey() == key)
/*      */           {
/* 1404 */             return entry;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1410 */     return null;
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
/*      */   protected int entryIndexForKey(int index, int hash, Object key) {
/* 1422 */     if (useEqualsForKey() && key != null) {
/*      */       
/* 1424 */       BasicEList<Entry<K, V>> eList = this.entryData[index];
/* 1425 */       if (eList != null) {
/*      */         
/* 1427 */         Object[] entries = eList.data;
/* 1428 */         int size = eList.size;
/* 1429 */         for (int j = 0; j < size; j++)
/*      */         {
/* 1431 */           Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1432 */           if (entry.getHash() == hash && key.equals(entry.getKey()))
/*      */           {
/* 1434 */             return j;
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1441 */       BasicEList<Entry<K, V>> eList = this.entryData[index];
/* 1442 */       if (eList != null) {
/*      */         
/* 1444 */         Object[] entries = eList.data;
/* 1445 */         int size = eList.size;
/* 1446 */         for (int j = 0; j < size; j++) {
/*      */           
/* 1448 */           Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1449 */           if (entry.getKey() == key)
/*      */           {
/* 1451 */             return j;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1457 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean grow(int minimumCapacity) {
/* 1466 */     this.modCount++;
/* 1467 */     int oldCapacity = (this.entryData == null) ? 0 : this.entryData.length;
/* 1468 */     if (minimumCapacity > oldCapacity) {
/*      */       
/* 1470 */       BasicEList<Entry<K, V>>[] arrayOfBasicEList = this.entryData;
/* 1471 */       this.entryData = newEntryData(2 * oldCapacity + 4);
/*      */       
/* 1473 */       for (int i = 0; i < oldCapacity; i++) {
/*      */         
/* 1475 */         BasicEList<Entry<K, V>> oldEList = arrayOfBasicEList[i];
/* 1476 */         if (oldEList != null) {
/*      */           
/* 1478 */           Object[] entries = oldEList.data;
/* 1479 */           int size = oldEList.size;
/* 1480 */           for (int j = 0; j < size; j++) {
/*      */             
/* 1482 */             Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1483 */             int index = indexOf(entry.getHash());
/* 1484 */             BasicEList<Entry<K, V>> eList = this.entryData[index];
/* 1485 */             if (eList == null)
/*      */             {
/* 1487 */               eList = this.entryData[index] = newList();
/*      */             }
/* 1489 */             eList.add(entry);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1494 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1498 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
/* 1504 */     objectOutputStream.defaultWriteObject();
/*      */     
/* 1506 */     if (this.entryData == null) {
/*      */       
/* 1508 */       objectOutputStream.writeInt(0);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1514 */       objectOutputStream.writeInt(this.entryData.length);
/*      */ 
/*      */ 
/*      */       
/* 1518 */       for (int i = 0; i < this.entryData.length; i++) {
/*      */         
/* 1520 */         BasicEList<Entry<K, V>> eList = this.entryData[i];
/* 1521 */         if (eList != null) {
/*      */           
/* 1523 */           Object[] entries = eList.data;
/* 1524 */           int size = eList.size;
/* 1525 */           for (int j = 0; j < size; j++) {
/*      */             
/* 1527 */             Entry<K, V> entry = (Entry<K, V>)entries[j];
/* 1528 */             objectOutputStream.writeObject(entry.getKey());
/* 1529 */             objectOutputStream.writeObject(entry.getValue());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
/* 1538 */     objectInputStream.defaultReadObject();
/*      */ 
/*      */ 
/*      */     
/* 1542 */     int capacity = objectInputStream.readInt();
/* 1543 */     if (capacity > 0) {
/*      */       
/* 1545 */       this.entryData = newEntryData(capacity);
/*      */ 
/*      */ 
/*      */       
/* 1549 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1551 */         K key = (K)objectInputStream.readObject();
/* 1552 */         V value = (V)objectInputStream.readObject();
/* 1553 */         put(key, value);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(Object object) {
/* 1563 */     return this.delegateEList.contains(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAll(Collection<?> collection) {
/* 1571 */     return this.delegateEList.containsAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOf(Object object) {
/* 1579 */     return this.delegateEList.indexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int lastIndexOf(Object object) {
/* 1587 */     return this.delegateEList.lastIndexOf(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray() {
/* 1595 */     return this.delegateEList.toArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T[] toArray(Object[] array) {
/* 1603 */     return (T[])this.delegateEList.toArray(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entry<K, V> get(int index) {
/* 1611 */     return this.delegateEList.get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map.Entry<K, V> set(int index, Map.Entry<K, V> object) {
/* 1619 */     return this.delegateEList.set(index, object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean add(Map.Entry<K, V> object) {
/* 1627 */     return this.delegateEList.add((Entry<K, V>)object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, Map.Entry<K, V> object) {
/* 1635 */     this.delegateEList.add(index, (Entry<K, V>)object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
/* 1644 */     return this.delegateEList.addAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, Collection<? extends Map.Entry<K, V>> collection) {
/* 1653 */     return this.delegateEList.addAll(index, collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object object) {
/* 1661 */     if (object instanceof Map.Entry)
/*      */     {
/* 1663 */       return this.delegateEList.remove(object);
/*      */     }
/*      */ 
/*      */     
/* 1667 */     boolean result = containsKey(object);
/* 1668 */     removeKey(object);
/* 1669 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeAll(Collection<?> collection) {
/* 1678 */     return this.delegateEList.removeAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map.Entry<K, V> remove(int index) {
/* 1686 */     return this.delegateEList.remove(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean retainAll(Collection<?> collection) {
/* 1694 */     return this.delegateEList.retainAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1702 */     this.delegateEList.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void move(int index, Map.Entry<K, V> object) {
/* 1710 */     this.delegateEList.move(index, (Entry<K, V>)object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map.Entry<K, V> move(int targetIndex, int sourceIndex) {
/* 1718 */     return this.delegateEList.move(targetIndex, sourceIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator<Map.Entry<K, V>> iterator() {
/* 1727 */     return this.delegateEList.iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator<Map.Entry<K, V>> listIterator() {
/* 1736 */     return this.delegateEList.listIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator<Map.Entry<K, V>> listIterator(int index) {
/* 1745 */     return this.delegateEList.listIterator(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Map.Entry<K, V>> subList(int start, int end) {
/* 1754 */     return this.delegateEList.subList(start, end);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1760 */     return this.delegateEList.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object object) {
/* 1766 */     if (object instanceof EMap)
/*      */     {
/* 1768 */       return this.delegateEList.equals(object);
/*      */     }
/*      */ 
/*      */     
/* 1772 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1782 */     return this.delegateEList.toString();
/*      */   }
/*      */   
/*      */   public static interface Entry<K, V> extends Map.Entry<K, V> {
/*      */     void setKey(K param1K);
/*      */     
/*      */     int getHash();
/*      */     
/*      */     void setHash(int param1Int);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\BasicEMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */