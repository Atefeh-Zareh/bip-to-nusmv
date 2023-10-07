/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EcoreEMap<K, V>
/*     */   extends BasicEMap<K, V>
/*     */   implements InternalEList.Unsettable<Map.Entry<K, V>>, EStructuralFeature.Setting
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected EClass entryEClass;
/*     */   protected Class<?> entryClass;
/*     */   
/*     */   public static class Unsettable<K, V>
/*     */     extends EcoreEMap<K, V>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Unsettable(EClass entryEClass, Class<?> entryClass, InternalEObject owner, int featureID) {
/*  48 */       super(entryEClass, entryClass, (EList<BasicEMap.Entry<K, V>>)null);
/*  49 */       this.delegateEList = new UnsettableDelegateEObjectContainmentEList<BasicEMap.Entry<K, V>>(entryClass, owner, featureID);
/*     */     }
/*     */ 
/*     */     
/*     */     protected class UnsettableDelegateEObjectContainmentEList<E extends BasicEMap.Entry<K, V>>
/*     */       extends EcoreEMap<K, V>.DelegateEObjectContainmentEList<E>
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */       protected boolean isSet;
/*     */       
/*     */       public UnsettableDelegateEObjectContainmentEList(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  60 */         super(dataClass, owner, featureID);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void didChange() {
/*  66 */         this.isSet = true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean isSet() {
/*  72 */         return this.isSet;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void unset() {
/*  78 */         super.unset();
/*  79 */         if (isNotificationRequired()) {
/*     */           
/*  81 */           boolean oldIsSet = this.isSet;
/*  82 */           this.isSet = false;
/*  83 */           this.owner.eNotify((Notification)createNotification(2, oldIsSet, false));
/*     */         }
/*     */         else {
/*     */           
/*  87 */           this.isSet = false;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EcoreEMap(EClass entryEClass, Class<?> entryClass, InternalEObject owner, int featureID) {
/*  98 */     this.entryClass = entryClass;
/*  99 */     assert entryClass != null && BasicEMap.Entry.class.isAssignableFrom(entryClass) : "A class derived from org.eclipse.emf.common.util.BasicEMap.Entry is required: " + entryClass;
/* 100 */     this.entryEClass = entryEClass;
/* 101 */     this.delegateEList = new DelegateEObjectContainmentEList<BasicEMap.Entry<K, V>>(entryClass, owner, featureID);
/*     */   }
/*     */ 
/*     */   
/*     */   public EcoreEMap(EClass entryEClass, Class<?> entryClass, EList<BasicEMap.Entry<K, V>> delegateEList) {
/* 106 */     this.entryClass = entryClass;
/* 107 */     assert entryClass != null && BasicEMap.Entry.class.isAssignableFrom(entryClass) : "A class derived from org.eclipse.emf.common.util.BasicEMap.Entry is required: " + entryClass;
/* 108 */     this.entryEClass = entryEClass;
/* 109 */     this.delegateEList = delegateEList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeDelegateEList() {}
/*     */ 
/*     */   
/*     */   protected class DelegateEObjectContainmentEList<E extends BasicEMap.Entry<K, V>>
/*     */     extends EObjectContainmentEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */     
/*     */     public DelegateEObjectContainmentEList(Class<?> entryClass, InternalEObject owner, int featureID) {
/* 124 */       super(entryClass, owner, featureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didAdd(int index, E newObject) {
/* 130 */       EcoreEMap.this.doPut((BasicEMap.Entry)newObject);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didSet(int index, E newObject, E oldObject) {
/* 136 */       didRemove(index, oldObject);
/* 137 */       if (oldObject == newObject)
/*     */       {
/* 139 */         ((BasicEMap.Entry)oldObject).setHash(EcoreEMap.this.hashOf(((BasicEMap.Entry)newObject).getKey()));
/*     */       }
/* 141 */       didAdd(index, newObject);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didRemove(int index, E oldObject) {
/* 147 */       EcoreEMap.this.doRemove((BasicEMap.Entry)oldObject);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didClear(int size, Object[] oldObjects) {
/* 153 */       EcoreEMap.this.doClear();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didMove(int index, E movedObject, int oldIndex) {
/* 159 */       EcoreEMap.this.doMove((BasicEMap.Entry)movedObject);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEList<BasicEMap.Entry<K, V>> newList() {
/* 166 */     return 
/* 167 */       new BasicEList<BasicEMap.Entry<K, V>>()
/*     */       {
/*     */         private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */         
/*     */         public Object[] newData(int listCapacity) {
/* 174 */           return (Object[])Array.newInstance(EcoreEMap.this.entryClass, listCapacity);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEMap.Entry<K, V> newEntry(int hash, K key, V value) {
/* 182 */     BasicEMap.Entry<K, V> entry = (BasicEMap.Entry<K, V>)this.entryEClass.getEPackage().getEFactoryInstance().create(this.entryEClass);
/* 183 */     entry.setHash(hash);
/* 184 */     entry.setKey(key);
/* 185 */     entry.setValue(value);
/* 186 */     return entry;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicEMap.Entry<K, V> basicGet(int index) {
/* 191 */     return ((InternalEList<BasicEMap.Entry<K, V>>)this.delegateEList).basicGet(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map.Entry<K, V>> basicList() {
/* 197 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).basicList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Map.Entry<K, V>> basicIterator() {
/* 206 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).basicIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<Map.Entry<K, V>> basicListIterator() {
/* 215 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).basicListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<Map.Entry<K, V>> basicListIterator(int index) {
/* 224 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).basicListIterator(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean basicContains(Object object) {
/* 230 */     return ((InternalEList)this.delegateEList).basicContains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean basicContainsAll(Collection<?> collection) {
/* 236 */     return ((InternalEList)this.delegateEList).basicContainsAll(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int basicIndexOf(Object object) {
/* 242 */     return ((InternalEList)this.delegateEList).basicIndexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int basicLastIndexOf(Object object) {
/* 248 */     return ((InternalEList)this.delegateEList).basicLastIndexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] basicToArray() {
/* 257 */     return ((InternalEList)this.delegateEList).basicToArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T[] basicToArray(Object[] array) {
/* 266 */     return (T[])((InternalEList)this.delegateEList).basicToArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/* 274 */     return ((InternalEList)this.delegateEList).basicRemove(object, notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicAdd(Map.Entry<K, V> object, NotificationChain notifications) {
/* 283 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).basicAdd(object, notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnique(Map.Entry<K, V> object) {
/* 292 */     ((InternalEList<Map.Entry<K, V>>)this.delegateEList).addUnique(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnique(int index, Map.Entry<K, V> object) {
/* 301 */     ((InternalEList<Map.Entry<K, V>>)this.delegateEList).addUnique(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAllUnique(Collection<? extends Map.Entry<K, V>> collection) {
/* 310 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).addAllUnique(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAllUnique(int index, Collection<? extends Map.Entry<K, V>> collection) {
/* 319 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).addAllUnique(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map.Entry<K, V> setUnique(int index, Map.Entry<K, V> object) {
/* 328 */     return ((InternalEList<Map.Entry<K, V>>)this.delegateEList).setUnique(index, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public EObject getEObject() {
/* 333 */     return ((EStructuralFeature.Setting)this.delegateEList).getEObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public EStructuralFeature getEStructuralFeature() {
/* 338 */     return ((EStructuralFeature.Setting)this.delegateEList).getEStructuralFeature();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(boolean resolve) {
/* 343 */     return ((EStructuralFeature.Setting)this.delegateEList).get(resolve);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Object value) {
/* 348 */     if (value instanceof Map) {
/*     */       
/* 350 */       ((EStructuralFeature.Setting)this.delegateEList).unset();
/* 351 */       Map<? extends K, ? extends V> mapValue = (Map<? extends K, ? extends V>)value;
/* 352 */       putAll(mapValue);
/*     */     }
/*     */     else {
/*     */       
/* 356 */       ((EStructuralFeature.Setting)this.delegateEList).set(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSet() {
/* 362 */     return ((EStructuralFeature.Setting)this.delegateEList).isSet();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unset() {
/* 367 */     ((EStructuralFeature.Setting)this.delegateEList).unset();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreEMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */