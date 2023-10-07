/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EcoreEList<E>
/*     */   extends NotifyingInternalEListImpl<E>
/*     */   implements InternalEList.Unsettable<E>, EStructuralFeature.Setting
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final Class<?> dataClass;
/*     */   protected final InternalEObject owner;
/*     */   
/*     */   public EcoreEList(Class<?> dataClass, InternalEObject owner) {
/*  56 */     this.dataClass = dataClass;
/*  57 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] newData(int capacity) {
/*  63 */     return (Object[])Array.newInstance(this.dataClass, capacity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected E validate(int index, E object) {
/*  69 */     super.validate(index, object);
/*  70 */     if (!hasInstanceClass() && object != null && !isInstance(object))
/*     */     {
/*  72 */       throw new ArrayStoreException();
/*     */     }
/*  74 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isInstance(Object object) {
/*  79 */     return this.dataClass.isInstance(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNotifier() {
/*  85 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFeature() {
/*  91 */     return getEStructuralFeature();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureID() {
/*  97 */     return this.owner.eClass().getFeatureID(getEStructuralFeature());
/*     */   }
/*     */ 
/*     */   
/*     */   public EStructuralFeature getEStructuralFeature() {
/* 102 */     return this.owner.eClass().getEStructuralFeature(getFeatureID());
/*     */   }
/*     */ 
/*     */   
/*     */   protected EClassifier getFeatureType() {
/* 107 */     return getEStructuralFeature().getEType();
/*     */   }
/*     */ 
/*     */   
/*     */   protected EReference getInverseEReference() {
/* 112 */     return ((EReference)getEStructuralFeature()).getEOpposite();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getInverseFeatureID() {
/* 117 */     return getInverseEReference().getFeatureID();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class<?> getInverseFeatureClass() {
/* 122 */     return ((EClass)getEStructuralFeature().getEType()).getInstanceClass();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasManyInverse() {
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasNavigableInverse() {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEObject() {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isContainment() {
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasProxies() {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasInstanceClass() {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected E resolve(int index, E object) {
/* 159 */     return 
/* 160 */       (isEObject() && hasProxies()) ? 
/* 161 */       (E)resolve(index, (EObject)object) : 
/* 162 */       object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EObject resolve(int index, EObject eObject) {
/* 167 */     EObject resolved = resolveProxy(eObject);
/* 168 */     if (resolved != eObject) {
/*     */       
/* 170 */       Object oldObject = this.data[index];
/* 171 */       EObject eObject1 = resolved;
/* 172 */       assign(index, validate(index, (E)eObject1));
/* 173 */       E oldElement = (E)oldObject;
/* 174 */       didSet(index, eObject1, oldElement);
/*     */       
/* 176 */       if (isContainment()) {
/*     */         
/* 178 */         EObject eObject2 = eObject;
/* 179 */         NotificationChain notificationChain = inverseRemove((E)eObject2, (NotificationChain)null);
/* 180 */         if (((InternalEObject)resolved).eInternalContainer() == null)
/*     */         {
/* 182 */           notificationChain = inverseAdd((E)eObject1, notificationChain);
/*     */         }
/* 184 */         if (notificationChain != null)
/*     */         {
/* 186 */           notificationChain.dispatch();
/*     */         }
/*     */       } 
/*     */       
/* 190 */       if (isNotificationRequired())
/*     */       {
/* 192 */         dispatchNotification((Notification)createNotification(9, eObject, resolved, index, false));
/*     */       }
/*     */       
/* 195 */       return resolved;
/*     */     } 
/*     */ 
/*     */     
/* 199 */     return eObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected E resolve(E object) {
/* 207 */     return isEObject() ? (E)resolveProxy((EObject)object) : object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EObject resolveProxy(EObject eObject) {
/* 212 */     return eObject.eIsProxy() ? this.owner.eResolveProxy((InternalEObject)eObject) : eObject;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 218 */     if (hasProxies())
/*     */     {
/* 220 */       for (int i = this.size - 1; i >= 0; i--)
/*     */       {
/* 222 */         get(i);
/*     */       }
/*     */     }
/* 225 */     return super.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(Object[] array) {
/* 231 */     if (hasProxies())
/*     */     {
/* 233 */       for (int i = this.size - 1; i >= 0; i--)
/*     */       {
/* 235 */         get(i);
/*     */       }
/*     */     }
/* 238 */     return (T[])super.toArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet) {
/* 244 */     return (NotificationImpl)new ENotificationImpl(this.owner, eventType, getFeatureID(), oldObject, newObject, index, wasSet);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NotificationImpl createNotification(int eventType, boolean oldValue, boolean newValue) {
/* 249 */     return (NotificationImpl)new ENotificationImpl(this.owner, eventType, getFeatureID(), oldValue, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchNotification(Notification notification) {
/* 255 */     this.owner.eNotify(notification);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNotificationRequired() {
/* 261 */     return this.owner.eNotificationRequired();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain inverseAdd(E object, NotificationChain notifications) {
/* 267 */     InternalEObject internalEObject = (InternalEObject)object;
/* 268 */     if (hasNavigableInverse()) {
/*     */       
/* 270 */       if (!hasInstanceClass())
/*     */       {
/* 272 */         return 
/* 273 */           internalEObject.eInverseAdd(
/* 274 */             this.owner, 
/* 275 */             internalEObject.eClass().getFeatureID((EStructuralFeature)getInverseEReference()), 
/* 276 */             null, 
/* 277 */             notifications);
/*     */       }
/*     */ 
/*     */       
/* 281 */       return 
/* 282 */         internalEObject.eInverseAdd(
/* 283 */           this.owner, 
/* 284 */           getInverseFeatureID(), 
/* 285 */           getInverseFeatureClass(), 
/* 286 */           notifications);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 291 */     return 
/* 292 */       internalEObject.eInverseAdd(
/* 293 */         this.owner, 
/* 294 */         -1 - getFeatureID(), 
/* 295 */         null, 
/* 296 */         notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain inverseRemove(E object, NotificationChain notifications) {
/* 303 */     InternalEObject internalEObject = (InternalEObject)object;
/* 304 */     if (hasNavigableInverse()) {
/*     */       
/* 306 */       if (!hasInstanceClass())
/*     */       {
/* 308 */         return 
/* 309 */           internalEObject.eInverseRemove(
/* 310 */             this.owner, 
/* 311 */             internalEObject.eClass().getFeatureID((EStructuralFeature)getInverseEReference()), 
/* 312 */             null, 
/* 313 */             notifications);
/*     */       }
/*     */ 
/*     */       
/* 317 */       return 
/* 318 */         internalEObject.eInverseRemove(
/* 319 */           this.owner, 
/* 320 */           getInverseFeatureID(), 
/* 321 */           getInverseFeatureClass(), 
/* 322 */           notifications);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 327 */     return 
/* 328 */       internalEObject.eInverseRemove(
/* 329 */         this.owner, 
/* 330 */         -1 - getFeatureID(), 
/* 331 */         null, 
/* 332 */         notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object object) {
/* 342 */     if (isEObject()) {
/*     */       
/* 344 */       if (this.size > 4) {
/*     */         
/* 346 */         if (!isInstance(object))
/*     */         {
/* 348 */           return false;
/*     */         }
/* 350 */         if (isContainment()) {
/*     */           
/* 352 */           InternalEObject eObject = (InternalEObject)object;
/* 353 */           EObject eContainer = eObject.eContainer();
/* 354 */           boolean bool = 
/* 355 */             (eContainer == this.owner && (
/* 356 */             hasNavigableInverse() ? (
/* 357 */             eObject.eBaseStructuralFeatureID(eObject.eContainerFeatureID(), this.dataClass) == getInverseFeatureID()) : (
/* 358 */             -1 - eObject.eContainerFeatureID() == getFeatureID())));
/* 359 */           if (hasProxies() && !bool && eContainer == null && eObject.eDirectResource() != null)
/*     */           {
/* 361 */             for (int i = 0; i < this.size; i++) {
/*     */               
/* 363 */               EObject containedEObject = resolveProxy((EObject)this.data[i]);
/* 364 */               if (containedEObject == object)
/*     */               {
/* 366 */                 return true;
/*     */               }
/*     */             } 
/*     */           }
/* 370 */           return bool;
/*     */         } 
/*     */ 
/*     */         
/* 374 */         if (hasNavigableInverse() && !hasManyInverse())
/*     */         {
/* 376 */           return (((EObject)object).eGet((EStructuralFeature)getInverseEReference()) == this.owner);
/*     */         }
/*     */       } 
/*     */       
/* 380 */       boolean result = super.contains(object);
/* 381 */       if (hasProxies() && !result)
/*     */       {
/* 383 */         for (int i = 0; i < this.size; i++) {
/*     */           
/* 385 */           EObject eObject = resolveProxy((EObject)this.data[i]);
/* 386 */           if (eObject == object)
/*     */           {
/* 388 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/* 392 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 396 */     return super.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object object) {
/* 403 */     int index = super.indexOf(object);
/* 404 */     if (index >= 0) {
/* 405 */       return index;
/*     */     }
/* 407 */     if (isEObject())
/*     */     {
/* 409 */       for (int i = 0; i < this.size; i++) {
/*     */         
/* 411 */         EObject eObject = resolveProxy((EObject)this.data[i]);
/* 412 */         if (eObject == object)
/*     */         {
/* 414 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 419 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 425 */     int result = super.lastIndexOf(object);
/* 426 */     if (isEObject() && result == -1)
/*     */     {
/* 428 */       for (int i = this.size - 1; i >= 0; i--) {
/*     */         
/* 430 */         EObject eObject = resolveProxy((EObject)this.data[i]);
/* 431 */         if (eObject == object)
/*     */         {
/* 433 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 438 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public EObject getEObject() {
/* 443 */     return (EObject)this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(boolean resolve) {
/* 448 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Object newValue) {
/* 454 */     clear();
/* 455 */     addAll((List)newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSet() {
/* 461 */     return !isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unset() {
/* 466 */     clear();
/*     */   }
/*     */   
/*     */   public static class UnmodifiableEList<E>
/*     */     extends BasicEList.UnmodifiableEList<E>
/*     */     implements InternalEList.Unsettable<E>, EStructuralFeature.Setting {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected final InternalEObject owner;
/*     */     protected final EStructuralFeature eStructuralFeature;
/*     */     
/*     */     public static class FastCompare<E>
/*     */       extends UnmodifiableEList<E> {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public FastCompare(InternalEObject owner, EStructuralFeature eStructuralFeature, int size, Object[] data) {
/* 481 */         super(owner, eStructuralFeature, size, data);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean useEquals() {
/* 487 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public UnmodifiableEList(InternalEObject owner, EStructuralFeature eStructuralFeature, int size, Object[] data) {
/* 496 */       super(size, data);
/* 497 */       this.owner = owner;
/* 498 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public List<E> basicList() {
/* 504 */       return super.basicList();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Iterator<E> basicIterator() {
/* 510 */       return super.basicIterator();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ListIterator<E> basicListIterator() {
/* 516 */       return super.basicListIterator();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ListIterator<E> basicListIterator(int index) {
/* 522 */       return super.basicListIterator(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean basicContains(Object object) {
/* 527 */       return contains(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean basicContainsAll(Collection<?> collection) {
/* 532 */       return containsAll(collection);
/*     */     }
/*     */ 
/*     */     
/*     */     public int basicIndexOf(Object object) {
/* 537 */       return indexOf(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public int basicLastIndexOf(Object object) {
/* 542 */       return lastIndexOf(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] basicToArray() {
/* 547 */       return toArray();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T[] basicToArray(Object[] array) {
/* 552 */       return (T[])toArray(array);
/*     */     }
/*     */ 
/*     */     
/*     */     public EObject getEObject() {
/* 557 */       return (EObject)this.owner;
/*     */     }
/*     */ 
/*     */     
/*     */     public EStructuralFeature getEStructuralFeature() {
/* 562 */       return this.eStructuralFeature;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object get(boolean resolve) {
/* 567 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(Object newValue) {
/* 572 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/* 577 */       return !isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public void unset() {
/* 582 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/* 587 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public NotificationChain basicAdd(E object, NotificationChain notifications) {
/* 592 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class Generic<E>
/*     */     extends EcoreEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public static final int IS_SET = 1;
/*     */     
/*     */     public static final int IS_UNSETTABLE = 2;
/*     */     
/*     */     public static final int HAS_INSTANCE_CLASS = 4;
/*     */     
/*     */     public static final int HAS_NAVIGABLE_INVERSE = 8;
/*     */     public static final int HAS_MANY_INVERSE = 16;
/*     */     public static final int IS_CONTAINMENT = 32;
/*     */     public static final int IS_CONTAINER = 64;
/*     */     public static final int IS_UNIQUE = 128;
/*     */     public static final int IS_PRIMITIVE = 256;
/*     */     public static final int IS_ENUM = 512;
/*     */     public static final int IS_EOBJECT = 1024;
/*     */     public static final int HAS_PROXIES = 2048;
/*     */     protected int kind;
/*     */     
/*     */     public static Class<?> wrapperClassFor(Class<?> javaClass) {
/* 620 */       if (javaClass == null)
/*     */       {
/* 622 */         return Object.class;
/*     */       }
/*     */ 
/*     */       
/* 626 */       return EcoreUtil.wrapperClassFor(javaClass);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static int kind(EStructuralFeature eStructuralFeature) {
/* 632 */       int result = 0;
/*     */       
/* 634 */       EClassifier eClassifier = eStructuralFeature.getEType();
/*     */       
/* 636 */       if (eClassifier.getInstanceClass() != null)
/*     */       {
/* 638 */         result |= 0x4;
/*     */       }
/*     */       
/* 641 */       if (eStructuralFeature.isUnsettable())
/*     */       {
/* 643 */         result |= 0x2;
/*     */       }
/*     */       
/* 646 */       if (eStructuralFeature instanceof EReference) {
/*     */         
/* 648 */         EReference eReference = (EReference)eStructuralFeature;
/* 649 */         EReference inverseEReference = eReference.getEOpposite();
/* 650 */         if (eReference.isContainment())
/*     */         {
/* 652 */           result |= 0x20;
/*     */         }
/*     */         
/* 655 */         if (inverseEReference != null) {
/*     */ 
/*     */ 
/*     */           
/* 659 */           inverseEReference.getEContainingClass().getFeatureCount();
/* 660 */           result |= 0x8;
/* 661 */           if (inverseEReference.isMany())
/*     */           {
/* 663 */             result |= 0x10;
/*     */           }
/* 665 */           if (inverseEReference.isContainment())
/*     */           {
/* 667 */             result |= 0x40;
/*     */           }
/*     */         } 
/*     */         
/* 671 */         if (eReference.isResolveProxies())
/*     */         {
/* 673 */           result |= 0x800;
/*     */         }
/*     */         
/* 676 */         result |= 0x400;
/*     */ 
/*     */       
/*     */       }
/* 680 */       else if (eClassifier instanceof org.eclipse.emf.ecore.EEnum) {
/*     */         
/* 682 */         result |= 0x200;
/*     */       }
/*     */       else {
/*     */         
/* 686 */         Class<?> instanceClass = eClassifier.getInstanceClass();
/* 687 */         if (instanceClass != null && instanceClass.isPrimitive())
/*     */         {
/* 689 */           result |= 0x100;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 694 */       if (eStructuralFeature.isUnique())
/*     */       {
/* 696 */         result |= 0x80;
/*     */       }
/*     */       
/* 699 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Generic(int kind, Class<?> dataClass, InternalEObject owner) {
/* 706 */       super(dataClass, owner);
/* 707 */       this.kind = kind;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useEquals() {
/* 715 */       return ((this.kind & 0x600) == 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canContainNull() {
/* 721 */       return ((this.kind & 0x700) == 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isUnique() {
/* 727 */       return ((this.kind & 0x80) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasInverse() {
/* 733 */       return ((this.kind & 0x28) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasManyInverse() {
/* 739 */       return ((this.kind & 0x10) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasNavigableInverse() {
/* 745 */       return ((this.kind & 0x8) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isEObject() {
/* 751 */       return ((this.kind & 0x400) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isContainment() {
/* 757 */       return ((this.kind & 0x20) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasProxies() {
/* 763 */       return ((this.kind & 0x800) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasInstanceClass() {
/* 769 */       return ((this.kind & 0x4) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isInstance(Object object) {
/* 775 */       return (this.dataClass == null) ? getFeatureType().isInstance(object) : super.isInstance(object);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isContainer() {
/* 780 */       return ((this.kind & 0x40) != 0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isUnsettable() {
/* 785 */       return ((this.kind & 0x2) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/* 791 */       return isUnsettable() ? (((this.kind & 0x1) != 0)) : (!isEmpty());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/* 797 */       super.unset();
/* 798 */       if (isUnsettable())
/*     */       {
/* 800 */         if (isNotificationRequired()) {
/*     */           
/* 802 */           boolean oldIsSet = ((this.kind & 0x1) != 0);
/* 803 */           this.kind &= 0xFFFFFFFE;
/* 804 */           dispatchNotification((Notification)createNotification(2, oldIsSet, false));
/*     */         }
/*     */         else {
/*     */           
/* 808 */           this.kind &= 0xFFFFFFFE;
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/* 816 */       this.kind |= 0x1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Dynamic<E>
/*     */     extends Generic<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected EStructuralFeature eStructuralFeature;
/*     */     
/*     */     public Dynamic(InternalEObject owner, EStructuralFeature eStructuralFeature) {
/* 828 */       super(kind(eStructuralFeature), wrapperClassFor(eStructuralFeature.getEType().getInstanceClass()), owner);
/* 829 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */     
/*     */     public Dynamic(int kind, InternalEObject owner, EStructuralFeature eStructuralFeature) {
/* 834 */       super(kind, wrapperClassFor(eStructuralFeature.getEType().getInstanceClass()), owner);
/* 835 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */     
/*     */     public Dynamic(int kind, Class<?> dataClass, InternalEObject owner, EStructuralFeature eStructuralFeature) {
/* 840 */       super(kind, dataClass, owner);
/* 841 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EStructuralFeature getEStructuralFeature() {
/* 847 */       return this.eStructuralFeature;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */