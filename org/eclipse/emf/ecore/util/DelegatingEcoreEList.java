/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*     */ import org.eclipse.emf.common.util.DelegatingEList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DelegatingEcoreEList<E>
/*     */   extends DelegatingNotifyingInternalEListImpl<E>
/*     */   implements InternalEList.Unsettable<E>, EStructuralFeature.Setting
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final InternalEObject owner;
/*     */   
/*     */   public static abstract class Unsettable<E>
/*     */     extends DelegatingEcoreEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected boolean isSet;
/*     */     
/*     */     public Unsettable(InternalEObject owner) {
/*  65 */       super(owner);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/*  71 */       this.isSet = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/*  77 */       return this.isSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/*  83 */       super.unset();
/*  84 */       if (isNotificationRequired()) {
/*     */         
/*  86 */         boolean oldIsSet = this.isSet;
/*  87 */         this.isSet = false;
/*  88 */         dispatchNotification((Notification)createNotification(2, oldIsSet, false));
/*     */       }
/*     */       else {
/*     */         
/*  92 */         this.isSet = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DelegatingEcoreEList(InternalEObject owner) {
/* 102 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canContainNull() {
/* 108 */     EClassifier eClassifier = getFeatureType();
/* 109 */     if (eClassifier instanceof org.eclipse.emf.ecore.EDataType) {
/*     */       
/* 111 */       if (eClassifier instanceof org.eclipse.emf.ecore.EEnum)
/*     */       {
/* 113 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 117 */       return !eClassifier.getInstanceClass().isPrimitive();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isUnique() {
/* 129 */     return getEStructuralFeature().isUnique();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasInverse() {
/* 135 */     EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 136 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 138 */       EReference eReference = (EReference)eStructuralFeature;
/* 139 */       return !(!eReference.isContainment() && ((EReference)eStructuralFeature).getEOpposite() == null);
/*     */     } 
/*     */ 
/*     */     
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected E validate(int index, E object) {
/* 150 */     super.validate(index, object);
/* 151 */     if (object != null && !isInstance(object))
/*     */     {
/* 153 */       throw new ArrayStoreException();
/*     */     }
/* 155 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isInstance(Object object) {
/* 160 */     return getFeatureType().isInstance(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNotifier() {
/* 166 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFeature() {
/* 172 */     return getEStructuralFeature();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureID() {
/* 178 */     return this.owner.eClass().getFeatureID(getEStructuralFeature());
/*     */   }
/*     */ 
/*     */   
/*     */   public EStructuralFeature getEStructuralFeature() {
/* 183 */     return this.owner.eClass().getEStructuralFeature(getFeatureID());
/*     */   }
/*     */ 
/*     */   
/*     */   protected EClassifier getFeatureType() {
/* 188 */     return getEStructuralFeature().getEType();
/*     */   }
/*     */ 
/*     */   
/*     */   protected EReference getInverseEReference() {
/* 193 */     return ((EReference)getEStructuralFeature()).getEOpposite();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getInverseFeatureID() {
/* 198 */     return getInverseEReference().getFeatureID();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class<?> getInverseFeatureClass() {
/* 203 */     return ((EClass)getEStructuralFeature().getEType()).getInstanceClass();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasManyInverse() {
/* 208 */     EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 209 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 211 */       EReference eReference = (EReference)eStructuralFeature;
/* 212 */       EReference oppositeEReference = eReference.getEOpposite();
/* 213 */       return (oppositeEReference != null && oppositeEReference.isMany());
/*     */     } 
/*     */ 
/*     */     
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasNavigableInverse() {
/* 223 */     EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 224 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 226 */       EReference eReference = (EReference)eStructuralFeature;
/* 227 */       EReference oppositeEReference = eReference.getEOpposite();
/* 228 */       return (oppositeEReference != null);
/*     */     } 
/*     */ 
/*     */     
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEObject() {
/* 238 */     return getFeatureType() instanceof EClass;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isContainment() {
/* 243 */     EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 244 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 246 */       EReference eReference = (EReference)eStructuralFeature;
/* 247 */       return eReference.isContainment();
/*     */     } 
/*     */ 
/*     */     
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasProxies() {
/* 257 */     EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 258 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 260 */       EReference eReference = (EReference)eStructuralFeature;
/* 261 */       return eReference.isResolveProxies();
/*     */     } 
/*     */ 
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasInstanceClass() {
/* 271 */     return (getFeatureType().getInstanceClass() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected E resolve(int index, E object) {
/* 277 */     if (isEObject() && hasProxies()) {
/*     */       
/* 279 */       EObject eObject = resolveProxy((EObject)object);
/* 280 */       if (eObject != object) {
/*     */         
/* 282 */         E oldObject = (E)delegateGet(index);
/* 283 */         delegateSet(index, validate(index, (E)eObject));
/* 284 */         didSet(index, eObject, oldObject);
/*     */         
/* 286 */         if (isContainment()) {
/*     */           
/* 288 */           NotificationChain notificationChain = inverseRemove(object, (NotificationChain)null);
/* 289 */           if (((InternalEObject)eObject).eInternalContainer() == null)
/*     */           {
/* 291 */             notificationChain = inverseAdd((E)eObject, notificationChain);
/*     */           }
/* 293 */           if (notificationChain != null)
/*     */           {
/* 295 */             notificationChain.dispatch();
/*     */           }
/*     */         } 
/* 298 */         if (isNotificationRequired())
/*     */         {
/* 300 */           dispatchNotification((Notification)createNotification(9, object, eObject, index, false));
/*     */         }
/*     */         
/* 303 */         return (E)eObject;
/*     */       } 
/*     */     } 
/* 306 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected E resolve(E object) {
/* 313 */     return isEObject() ? (E)resolveProxy((EObject)object) : object;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EObject resolveProxy(EObject eObject) {
/* 318 */     return eObject.eIsProxy() ? this.owner.eResolveProxy((InternalEObject)eObject) : eObject;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 324 */     if (hasProxies())
/*     */     {
/* 326 */       for (int i = size() - 1; i >= 0; i--)
/*     */       {
/* 328 */         get(i);
/*     */       }
/*     */     }
/* 331 */     return super.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(Object[] array) {
/* 337 */     if (hasProxies())
/*     */     {
/* 339 */       for (int i = size() - 1; i >= 0; i--)
/*     */       {
/* 341 */         get(i);
/*     */       }
/*     */     }
/* 344 */     return (T[])super.toArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet) {
/* 350 */     return (NotificationImpl)new ENotificationImpl(this.owner, eventType, getFeatureID(), oldObject, newObject, index, wasSet);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NotificationImpl createNotification(int eventType, boolean oldValue, boolean newValue) {
/* 355 */     return (NotificationImpl)new ENotificationImpl(this.owner, eventType, getFeatureID(), oldValue, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchNotification(Notification notification) {
/* 361 */     this.owner.eNotify(notification);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNotificationRequired() {
/* 367 */     return this.owner.eNotificationRequired();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain inverseAdd(E object, NotificationChain notifications) {
/* 373 */     InternalEObject internalEObject = (InternalEObject)object;
/* 374 */     if (hasNavigableInverse()) {
/*     */       
/* 376 */       if (!hasInstanceClass())
/*     */       {
/* 378 */         return 
/* 379 */           internalEObject.eInverseAdd(
/* 380 */             this.owner, 
/* 381 */             internalEObject.eClass().getFeatureID((EStructuralFeature)getInverseEReference()), 
/* 382 */             null, 
/* 383 */             notifications);
/*     */       }
/*     */ 
/*     */       
/* 387 */       return 
/* 388 */         internalEObject.eInverseAdd(
/* 389 */           this.owner, 
/* 390 */           getInverseFeatureID(), 
/* 391 */           getInverseFeatureClass(), 
/* 392 */           notifications);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 397 */     return 
/* 398 */       internalEObject.eInverseAdd(
/* 399 */         this.owner, 
/* 400 */         -1 - getFeatureID(), 
/* 401 */         null, 
/* 402 */         notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain inverseRemove(E object, NotificationChain notifications) {
/* 409 */     InternalEObject internalEObject = (InternalEObject)object;
/* 410 */     if (hasNavigableInverse()) {
/*     */       
/* 412 */       if (!hasInstanceClass())
/*     */       {
/* 414 */         return 
/* 415 */           internalEObject.eInverseRemove(
/* 416 */             this.owner, 
/* 417 */             internalEObject.eClass().getFeatureID((EStructuralFeature)getInverseEReference()), 
/* 418 */             null, 
/* 419 */             notifications);
/*     */       }
/*     */ 
/*     */       
/* 423 */       return 
/* 424 */         internalEObject.eInverseRemove(
/* 425 */           this.owner, 
/* 426 */           getInverseFeatureID(), 
/* 427 */           getInverseFeatureClass(), 
/* 428 */           notifications);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 433 */     return 
/* 434 */       internalEObject.eInverseRemove(
/* 435 */         this.owner, 
/* 436 */         -1 - getFeatureID(), 
/* 437 */         null, 
/* 438 */         notifications);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object object) {
/* 448 */     if (isEObject()) {
/*     */       
/* 450 */       int size = size();
/* 451 */       if (size > 4) {
/*     */         
/* 453 */         if (!isInstance(object))
/*     */         {
/* 455 */           return false;
/*     */         }
/* 457 */         if (isContainment()) {
/*     */           
/* 459 */           InternalEObject eObject = (InternalEObject)object;
/* 460 */           EObject eContainer = eObject.eContainer();
/* 461 */           boolean bool = 
/* 462 */             (eContainer == this.owner && (
/* 463 */             hasNavigableInverse() ? (
/* 464 */             eObject.eContainerFeatureID() == getInverseFeatureID()) : (
/* 465 */             -1 - eObject.eContainerFeatureID() == getFeatureID())));
/* 466 */           if (hasProxies() && !bool && eContainer == null && eObject.eDirectResource() != null)
/*     */           {
/* 468 */             for (int i = 0; i < size; i++) {
/*     */               
/* 470 */               EObject containedEObject = resolveProxy((EObject)delegateGet(i));
/* 471 */               if (containedEObject == object)
/*     */               {
/* 473 */                 return true;
/*     */               }
/*     */             } 
/*     */           }
/* 477 */           return bool;
/*     */         } 
/*     */ 
/*     */         
/* 481 */         if (hasNavigableInverse() && !hasManyInverse())
/*     */         {
/* 483 */           return (((EObject)object).eGet((EStructuralFeature)getInverseEReference()) == this.owner);
/*     */         }
/*     */       } 
/*     */       
/* 487 */       boolean result = super.contains(object);
/* 488 */       if (hasProxies() && !result)
/*     */       {
/* 490 */         for (int i = 0; i < size; i++) {
/*     */           
/* 492 */           EObject eObject = resolveProxy((EObject)delegateGet(i));
/* 493 */           if (eObject == object)
/*     */           {
/* 495 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/* 499 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 503 */     return super.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object object) {
/* 510 */     int index = super.indexOf(object);
/* 511 */     if (index >= 0) {
/* 512 */       return index;
/*     */     }
/*     */ 
/*     */     
/* 516 */     if (isEObject())
/*     */     {
/* 518 */       for (int i = 0, size = size(); i < size; i++) {
/*     */         
/* 520 */         EObject eObject = resolveProxy((EObject)delegateGet(i));
/* 521 */         if (eObject == object)
/*     */         {
/* 523 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 528 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 534 */     int result = super.lastIndexOf(object);
/* 535 */     if (isEObject() && result == -1)
/*     */     {
/* 537 */       for (int i = size() - 1; i >= 0; i--) {
/*     */         
/* 539 */         EObject eObject = resolveProxy((EObject)delegateGet(i));
/* 540 */         if (eObject == object)
/*     */         {
/* 542 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 547 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public EObject getEObject() {
/* 552 */     return (EObject)this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(boolean resolve) {
/* 557 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Object newValue) {
/* 563 */     clear();
/* 564 */     addAll((List)newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSet() {
/* 570 */     return !isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unset() {
/* 575 */     clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class UnmodifiableEList<E>
/*     */     extends DelegatingEList.UnmodifiableEList<E>
/*     */     implements InternalEList.Unsettable<E>, EStructuralFeature.Setting
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     protected final InternalEObject owner;
/*     */     protected final EStructuralFeature eStructuralFeature;
/*     */     
/*     */     public UnmodifiableEList(InternalEObject owner, EStructuralFeature eStructuralFeature, List<E> underlyingList) {
/* 589 */       super(underlyingList);
/* 590 */       this.owner = owner;
/* 591 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E basicGet(int index) {
/* 597 */       return (E)super.basicGet(index);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public List<E> basicList() {
/* 603 */       return super.basicList();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Iterator<E> basicIterator() {
/* 609 */       return super.basicIterator();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ListIterator<E> basicListIterator() {
/* 615 */       return super.basicListIterator();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ListIterator<E> basicListIterator(int index) {
/* 621 */       return super.basicListIterator(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] basicToArray() {
/* 626 */       return toArray();
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> T[] basicToArray(Object[] array) {
/* 631 */       return (T[])toArray(array);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean basicContains(Object object) {
/* 636 */       return contains(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean basicContainsAll(Collection<?> collection) {
/* 641 */       return containsAll(collection);
/*     */     }
/*     */ 
/*     */     
/*     */     public int basicIndexOf(Object object) {
/* 646 */       return indexOf(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public int basicLastIndexOf(Object object) {
/* 651 */       return lastIndexOf(object);
/*     */     }
/*     */ 
/*     */     
/*     */     public EObject getEObject() {
/* 656 */       return (EObject)this.owner;
/*     */     }
/*     */ 
/*     */     
/*     */     public EStructuralFeature getEStructuralFeature() {
/* 661 */       return this.eStructuralFeature;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object get(boolean resolve) {
/* 666 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(Object newValue) {
/* 671 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/* 676 */       return !isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public void unset() {
/* 681 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/* 686 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public NotificationChain basicAdd(E object, NotificationChain notifications) {
/* 691 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class Generic<E>
/*     */     extends DelegatingEcoreEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     public static final int IS_SET = 1;
/*     */     public static final int IS_UNSETTABLE = 2;
/*     */     public static final int HAS_INSTANCE_CLASS = 4;
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
/*     */     public static int kind(EStructuralFeature eStructuralFeature) {
/* 714 */       return EcoreEList.Generic.kind(eStructuralFeature);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Generic(int kind, InternalEObject owner) {
/* 721 */       super(owner);
/* 722 */       this.kind = kind;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useEquals() {
/* 730 */       return ((this.kind & 0x600) == 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canContainNull() {
/* 736 */       return ((this.kind & 0x700) == 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isUnique() {
/* 742 */       return ((this.kind & 0x80) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasInverse() {
/* 748 */       return ((this.kind & 0x28) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasManyInverse() {
/* 754 */       return ((this.kind & 0x10) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasNavigableInverse() {
/* 760 */       return ((this.kind & 0x8) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isEObject() {
/* 766 */       return ((this.kind & 0x400) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isContainment() {
/* 772 */       return ((this.kind & 0x20) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasProxies() {
/* 778 */       return ((this.kind & 0x800) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasInstanceClass() {
/* 784 */       return ((this.kind & 0x4) != 0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isContainer() {
/* 789 */       return ((this.kind & 0x40) != 0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isUnsettable() {
/* 794 */       return ((this.kind & 0x2) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/* 800 */       return isUnsettable() ? (((this.kind & 0x1) != 0)) : (!isEmpty());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/* 806 */       super.unset();
/* 807 */       if (isUnsettable())
/*     */       {
/* 809 */         if (isNotificationRequired()) {
/*     */           
/* 811 */           boolean oldIsSet = ((this.kind & 0x1) != 0);
/* 812 */           this.kind &= 0xFFFFFFFE;
/* 813 */           dispatchNotification((Notification)createNotification(2, oldIsSet, false));
/*     */         }
/*     */         else {
/*     */           
/* 817 */           this.kind &= 0xFFFFFFFE;
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/* 825 */       this.kind |= 0x1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class Dynamic<E>
/*     */     extends Generic<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected EStructuralFeature eStructuralFeature;
/*     */     
/*     */     public Dynamic(InternalEObject owner, EStructuralFeature eStructuralFeature) {
/* 837 */       super(kind(eStructuralFeature), owner);
/* 838 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */     
/*     */     public Dynamic(int kind, InternalEObject owner, EStructuralFeature eStructuralFeature) {
/* 843 */       super(kind, owner);
/* 844 */       this.eStructuralFeature = eStructuralFeature;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EStructuralFeature getEStructuralFeature() {
/* 850 */       return this.eStructuralFeature;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\DelegatingEcoreEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */