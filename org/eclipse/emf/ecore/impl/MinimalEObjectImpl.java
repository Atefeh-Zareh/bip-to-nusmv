/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
/*     */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*     */ import org.eclipse.emf.common.util.ArrayDelegatingEList;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.util.EContentsEList;
/*     */ import org.eclipse.emf.ecore.util.ECrossReferenceEList;
/*     */ 
/*     */ public class MinimalEObjectImpl
/*     */   extends BasicEObjectImpl
/*     */   implements EObject, EStructuralFeature.Internal.DynamicValueHolder
/*     */ {
/*     */   private static final int NO_DELIVER = 1;
/*     */   private static final int CONTAINER = 2;
/*     */   private static final int ADAPTER = 4;
/*     */   private static final int ADAPTER_LISTENER = 8;
/*     */   private static final int CLASS = 16;
/*     */   private static final int SETTING = 32;
/*     */   private static final int PROXY = 64;
/*     */   private static final int RESOURCE = 128;
/*     */   private static final int FIELD_MASK = 254;
/*     */   private int eFlags;
/*     */   private Object eStorage;
/*     */   
/*     */   public static class Container
/*     */     extends MinimalEObjectImpl
/*     */   {
/*     */     protected InternalEObject eContainer;
/*     */     
/*     */     public static class Dynamic
/*     */       extends Container {
/*     */       protected EClass eClass;
/*     */       protected Object[] eSettings;
/*     */       
/*     */       public static final class BasicEMapEntry<K, V>
/*     */         extends Dynamic
/*     */         implements BasicEMap.Entry<K, V> {
/*  51 */         protected int hash = -1;
/*     */ 
/*     */ 
/*     */         
/*     */         protected EStructuralFeature keyFeature;
/*     */ 
/*     */ 
/*     */         
/*     */         protected EStructuralFeature valueFeature;
/*     */ 
/*     */ 
/*     */         
/*     */         public BasicEMapEntry() {}
/*     */ 
/*     */ 
/*     */         
/*     */         public BasicEMapEntry(EClass eClass) {
/*  68 */           super(eClass);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public K getKey() {
/*  74 */           return (K)eGet(this.keyFeature);
/*     */         }
/*     */ 
/*     */         
/*     */         public void setKey(Object key) {
/*  79 */           eSet(this.keyFeature, key);
/*     */         }
/*     */ 
/*     */         
/*     */         public int getHash() {
/*  84 */           if (this.hash == -1) {
/*     */             
/*  86 */             Object theKey = getKey();
/*  87 */             this.hash = (theKey == null) ? 0 : theKey.hashCode();
/*     */           } 
/*  89 */           return this.hash;
/*     */         }
/*     */ 
/*     */         
/*     */         public void setHash(int hash) {
/*  94 */           this.hash = hash;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public V getValue() {
/* 100 */           return (V)eGet(this.valueFeature);
/*     */         }
/*     */ 
/*     */         
/*     */         public V setValue(V value) {
/* 105 */           V result = (V)eGet(this.valueFeature);
/* 106 */           eSet(this.valueFeature, value);
/* 107 */           return result;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void eSetClass(EClass eClass) {
/* 113 */           super.eSetClass(eClass);
/* 114 */           this.keyFeature = eClass.getEStructuralFeature("key");
/* 115 */           this.valueFeature = eClass.getEStructuralFeature("value");
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public Dynamic() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public Dynamic(EClass eClass) {
/* 130 */         eSetClass(eClass);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public EClass eClass() {
/* 136 */         return this.eClass;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected EClass eDynamicClass() {
/* 142 */         return eClass();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void eSetClass(EClass eClass) {
/* 148 */         this.eClass = eClass;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean eHasSettings() {
/* 154 */         return (this.eSettings != null);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected Object[] eBasicSettings() {
/* 160 */         return this.eSettings;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void eBasicSetSettings(Object[] settings) {
/* 166 */         this.eSettings = settings;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InternalEObject eInternalContainer() {
/* 180 */       return this.eContainer;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void eBasicSetContainer(InternalEObject newContainer) {
/* 186 */       this.eContainer = newContainer;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eProperties() {
/* 288 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eBasicProperties() {
/* 294 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEList<Adapter> eBasicAdapters() {
/* 300 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean hasField(int field) {
/* 305 */     return ((this.eFlags & field) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private final Object getField(int field) {
/* 310 */     if (hasField(field)) {
/*     */       
/* 312 */       int fieldIndex = fieldIndex(field);
/* 313 */       return (fieldIndex == -1) ? this.eStorage : ((Object[])this.eStorage)[fieldIndex];
/*     */     } 
/*     */ 
/*     */     
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void setField(int field, Object value) {
/* 323 */     if (hasField(field)) {
/*     */       
/* 325 */       if (value == null) {
/*     */         
/* 327 */         removeField(field);
/*     */       }
/*     */       else {
/*     */         
/* 331 */         int fieldIndex = fieldIndex(field);
/* 332 */         if (fieldIndex == -1)
/*     */         {
/* 334 */           this.eStorage = value;
/*     */         }
/*     */         else
/*     */         {
/* 338 */           ((Object[])this.eStorage)[fieldIndex] = value;
/*     */         }
/*     */       
/*     */       } 
/* 342 */     } else if (value != null) {
/*     */       
/* 344 */       addField(field, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final int fieldIndex(int field) {
/* 350 */     int result = 0; int bit;
/* 351 */     for (bit = 2; bit < field; bit <<= 1) {
/*     */       
/* 353 */       if ((this.eFlags & bit) != 0)
/*     */       {
/* 355 */         result++;
/*     */       }
/*     */     } 
/* 358 */     if (result == 0) {
/*     */       
/* 360 */       for (bit = field <<= 1; bit <= 128; bit <<= 1) {
/*     */         
/* 362 */         if ((this.eFlags & bit) != 0)
/*     */         {
/* 364 */           return 0;
/*     */         }
/*     */       } 
/* 367 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 371 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void addField(int field, Object value) {
/* 377 */     int fieldCount = Integer.bitCount(this.eFlags & 0xFE);
/* 378 */     if (fieldCount == 0) {
/*     */       
/* 380 */       this.eStorage = value;
/*     */     } else {
/*     */       Object[] result;
/*     */ 
/*     */       
/* 385 */       if (fieldCount == 1) {
/*     */         
/* 387 */         result = new Object[2];
/* 388 */         int fieldIndex = fieldIndex(field);
/* 389 */         if (fieldIndex == 0)
/*     */         {
/* 391 */           result[0] = value;
/* 392 */           result[1] = this.eStorage;
/*     */         }
/*     */         else
/*     */         {
/* 396 */           result[0] = this.eStorage;
/* 397 */           result[1] = value;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 402 */         result = new Object[fieldCount + 1];
/* 403 */         Object[] oldStorage = (Object[])this.eStorage;
/* 404 */         for (int bit = 2, sourceIndex = 0, targetIndex = 0; bit <= 128; bit <<= 1) {
/*     */           
/* 406 */           if (bit == field) {
/*     */             
/* 408 */             result[targetIndex++] = value;
/*     */           }
/* 410 */           else if ((this.eFlags & bit) != 0) {
/*     */             
/* 412 */             result[targetIndex++] = oldStorage[sourceIndex++];
/*     */           } 
/*     */         } 
/*     */       } 
/* 416 */       this.eStorage = result;
/*     */     } 
/* 418 */     this.eFlags |= field;
/*     */   }
/*     */ 
/*     */   
/*     */   private final void removeField(int field) {
/* 423 */     int fieldCount = Integer.bitCount(this.eFlags & 0xFE);
/* 424 */     if (fieldCount == 1) {
/*     */       
/* 426 */       this.eStorage = null;
/*     */     }
/*     */     else {
/*     */       
/* 430 */       Object[] oldStorage = (Object[])this.eStorage;
/* 431 */       if (fieldCount == 2) {
/*     */         
/* 433 */         int fieldIndex = fieldIndex(field);
/* 434 */         this.eStorage = oldStorage[(fieldIndex == 0) ? 1 : 0];
/*     */       }
/*     */       else {
/*     */         
/* 438 */         Object[] result = new Object[fieldCount - 1];
/* 439 */         for (int bit = 2, sourceIndex = 0, targetIndex = 0; bit <= 128; bit <<= 1) {
/*     */           
/* 441 */           if (bit == field) {
/*     */             
/* 443 */             sourceIndex++;
/*     */           }
/* 445 */           else if ((this.eFlags & bit) != 0) {
/*     */             
/* 447 */             result[targetIndex++] = oldStorage[sourceIndex++];
/*     */           } 
/*     */         } 
/* 450 */         this.eStorage = result;
/*     */       } 
/*     */     } 
/* 453 */     this.eFlags &= field ^ 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public EList<Adapter> eAdapters() {
/*     */     class ArrayDelegatingAdapterList
/*     */       extends ArrayDelegatingEList<Adapter>
/*     */       implements BasicNotifierImpl.EObservableAdapterList
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */ 
/*     */       
/*     */       protected Object[] newData(int capacity) {
/* 466 */         return (Object[])new Adapter[capacity];
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Object[] data() {
/* 472 */         return (Object[])MinimalEObjectImpl.this.eBasicAdapterArray();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void setData(Object[] data) {
/* 478 */         this.modCount++;
/* 479 */         InternalEObject eContainer = MinimalEObjectImpl.this.eInternalContainer();
/* 480 */         if (eContainer instanceof BasicEObjectImpl) {
/*     */           
/* 482 */           Adapter[] eContainerAdapterArray = MinimalEObjectImpl.this.eContainerAdapterArray();
/* 483 */           if (Arrays.equals(data, (Object[])eContainerAdapterArray)) {
/*     */             
/* 485 */             MinimalEObjectImpl.this.eBasicSetAdapterArray(eContainerAdapterArray);
/*     */             return;
/*     */           } 
/*     */         } 
/* 489 */         MinimalEObjectImpl.this.eBasicSetAdapterArray((Adapter[])data);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void didAdd(int index, Adapter newObject) {
/* 495 */         BasicNotifierImpl.EObservableAdapterList.Listener[] listeners = (BasicNotifierImpl.EObservableAdapterList.Listener[])MinimalEObjectImpl.this.getField(8);
/* 496 */         if (listeners != null) {
/*     */           byte b; int i; BasicNotifierImpl.EObservableAdapterList.Listener[] arrayOfListener;
/* 498 */           for (i = (arrayOfListener = listeners).length, b = 0; b < i; ) { BasicNotifierImpl.EObservableAdapterList.Listener listener = arrayOfListener[b];
/*     */             
/* 500 */             listener.added((Notifier)MinimalEObjectImpl.this, newObject); b++; }
/*     */         
/*     */         } 
/* 503 */         newObject.setTarget((Notifier)MinimalEObjectImpl.this);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void didRemove(int index, Adapter oldObject) {
/* 509 */         BasicNotifierImpl.EObservableAdapterList.Listener[] listeners = (BasicNotifierImpl.EObservableAdapterList.Listener[])MinimalEObjectImpl.this.getField(8);
/* 510 */         if (listeners != null) {
/*     */           byte b; int i; BasicNotifierImpl.EObservableAdapterList.Listener[] arrayOfListener;
/* 512 */           for (i = (arrayOfListener = listeners).length, b = 0; b < i; ) { BasicNotifierImpl.EObservableAdapterList.Listener listener = arrayOfListener[b];
/*     */             
/* 514 */             listener.removed((Notifier)MinimalEObjectImpl.this, oldObject); b++; }
/*     */         
/*     */         } 
/* 517 */         Adapter adapter = oldObject;
/* 518 */         if (MinimalEObjectImpl.this.eDeliver()) {
/*     */           
/* 520 */           NotificationImpl notificationImpl = 
/* 521 */             new NotificationImpl(8, oldObject, null, index)
/*     */             {
/*     */               
/*     */               public Object getNotifier()
/*     */               {
/* 526 */                 return MinimalEObjectImpl.ArrayDelegatingAdapterList.access$2(MinimalEObjectImpl.ArrayDelegatingAdapterList.this);
/*     */               }
/*     */             };
/* 529 */           adapter.notifyChanged((Notification)notificationImpl);
/*     */         } 
/* 531 */         if (adapter instanceof Adapter.Internal) {
/*     */           
/* 533 */           ((Adapter.Internal)adapter).unsetTarget((Notifier)MinimalEObjectImpl.this);
/*     */         }
/* 535 */         else if (adapter.getTarget() == MinimalEObjectImpl.this) {
/*     */           
/* 537 */           adapter.setTarget(null);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public void addListener(BasicNotifierImpl.EObservableAdapterList.Listener listener) {
/* 543 */         BasicNotifierImpl.EObservableAdapterList.Listener[] listeners = (BasicNotifierImpl.EObservableAdapterList.Listener[])MinimalEObjectImpl.this.getField(8);
/* 544 */         if (listeners == null) {
/*     */           
/* 546 */           listeners = new BasicNotifierImpl.EObservableAdapterList.Listener[] { listener };
/*     */         }
/*     */         else {
/*     */           
/* 550 */           BasicNotifierImpl.EObservableAdapterList.Listener[] newListeners = new BasicNotifierImpl.EObservableAdapterList.Listener[listeners.length + 1];
/* 551 */           System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
/* 552 */           newListeners[listeners.length] = listener;
/* 553 */           listeners = newListeners;
/*     */         } 
/* 555 */         MinimalEObjectImpl.this.setField(8, listeners);
/*     */       }
/*     */ 
/*     */       
/*     */       public void removeListener(BasicNotifierImpl.EObservableAdapterList.Listener listener) {
/* 560 */         BasicNotifierImpl.EObservableAdapterList.Listener[] listeners = (BasicNotifierImpl.EObservableAdapterList.Listener[])MinimalEObjectImpl.this.getField(8);
/* 561 */         if (listeners != null)
/*     */         {
/* 563 */           for (int i = 0; i < listeners.length; i++) {
/*     */             
/* 565 */             if (listeners[i] == listener) {
/*     */               
/* 567 */               if (listeners.length == 1) {
/*     */                 
/* 569 */                 listeners = (BasicNotifierImpl.EObservableAdapterList.Listener[])null;
/*     */               }
/*     */               else {
/*     */                 
/* 573 */                 BasicNotifierImpl.EObservableAdapterList.Listener[] newListeners = new BasicNotifierImpl.EObservableAdapterList.Listener[listeners.length - 1];
/* 574 */                 System.arraycopy(listeners, 0, newListeners, 0, i);
/* 575 */                 if (i != newListeners.length)
/*     */                 {
/* 577 */                   System.arraycopy(listeners, i + 1, newListeners, i, newListeners.length - i);
/*     */                 }
/* 579 */                 listeners = newListeners;
/*     */               } 
/* 581 */               MinimalEObjectImpl.this.setField(8, listeners);
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       }
/*     */     };
/* 588 */     return (EList<Adapter>)new ArrayDelegatingAdapterList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Adapter[] eBasicAdapterArray() {
/* 594 */     return (Adapter[])getField(4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eBasicSetAdapterArray(Adapter[] eAdapters) {
/* 599 */     setField(4, eAdapters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean eBasicHasAdapters() {
/* 605 */     return hasField(4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eDeliver() {
/* 611 */     return ((this.eFlags & 0x1) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetDeliver(boolean deliver) {
/* 617 */     if (deliver) {
/*     */       
/* 619 */       this.eFlags &= 0xFFFFFFFE;
/*     */     }
/*     */     else {
/*     */       
/* 623 */       this.eFlags |= 0x1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsProxy() {
/* 630 */     return hasField(64);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public URI eProxyURI() {
/* 636 */     return (URI)getField(64);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetProxyURI(URI uri) {
/* 642 */     setField(64, uri);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalEObject eInternalContainer() {
/* 648 */     return (InternalEObject)getField(2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eBasicSetContainer(InternalEObject newContainer) {
/* 653 */     setField(2, newContainer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eContainerFeatureID() {
/* 659 */     return this.eFlags >> 16;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eBasicSetContainerFeatureID(int newContainerFeatureID) {
/* 664 */     this.eFlags = newContainerFeatureID << 16 | this.eFlags & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
/* 670 */     eBasicSetContainerFeatureID(newContainerFeatureID);
/* 671 */     eBasicSetContainer(newContainer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eDynamicClass() {
/* 677 */     return (EClass)getField(16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EClass eClass() {
/* 683 */     EClass eClass = eDynamicClass();
/* 684 */     return (eClass == null) ? eStaticClass() : eClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetClass(EClass eClass) {
/* 690 */     setField(16, eClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean eHasSettings() {
/* 696 */     return hasField(32);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object[] eBasicSettings() {
/* 701 */     return (Object[])getField(32);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eBasicSetSettings(Object[] settings) {
/* 706 */     setField(32, settings);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EStructuralFeature.Internal.DynamicValueHolder eSettings() {
/* 712 */     if (!eHasSettings()) {
/*     */       
/* 714 */       int size = eClass().getFeatureCount() - eStaticFeatureCount();
/* 715 */       if (size != 0)
/*     */       {
/* 717 */         eBasicSetSettings((size == 0) ? BasicEObjectImpl.EPropertiesHolderBaseImpl.NO_SETTINGS : new Object[size]);
/*     */       }
/*     */     } 
/*     */     
/* 721 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource.Internal eDirectResource() {
/* 727 */     return (Resource.Internal)getField(128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eSetDirectResource(Resource.Internal resource) {
/* 733 */     setField(128, resource);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> eContents() {
/* 739 */     return (EList<EObject>)EContentsEList.createEContentsEList(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> eCrossReferences() {
/* 745 */     return (EList<EObject>)ECrossReferenceEList.createECrossReferenceEList(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private Object[] eDynamicSettings() {
/* 750 */     Object[] settings = eBasicSettings();
/* 751 */     if (settings == null) {
/*     */       
/* 753 */       eSettings();
/* 754 */       settings = eBasicSettings();
/*     */     } 
/* 756 */     return settings;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object dynamicGet(int dynamicFeatureID) {
/* 761 */     Object[] settings = eDynamicSettings();
/* 762 */     return settings[dynamicFeatureID];
/*     */   }
/*     */ 
/*     */   
/*     */   public void dynamicSet(int dynamicFeatureID, Object newValue) {
/* 767 */     Object[] settings = eDynamicSettings();
/* 768 */     settings[dynamicFeatureID] = newValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dynamicUnset(int dynamicFeatureID) {
/* 773 */     Object[] settings = eDynamicSettings();
/* 774 */     settings[dynamicFeatureID] = null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\MinimalEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */