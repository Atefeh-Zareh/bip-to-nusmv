/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.ETypedElement;
/*     */ import org.eclipse.emf.ecore.EcoreFactory;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ETypedElementImpl
/*     */   extends ENamedElementImpl
/*     */   implements ETypedElement
/*     */ {
/*     */   protected static final boolean ORDERED_EDEFAULT = true;
/*     */   protected static final int ORDERED_EFLAG = 256;
/*     */   protected static final boolean UNIQUE_EDEFAULT = true;
/*     */   protected static final int UNIQUE_EFLAG = 512;
/*     */   protected static final int LOWER_BOUND_EDEFAULT = 0;
/* 115 */   protected int lowerBound = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int UPPER_BOUND_EDEFAULT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   protected int upperBound = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean MANY_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean REQUIRED_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClassifier eType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EGenericType eGenericType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ETypedElementImpl() {
/* 185 */     this.eFlags |= 0x100;
/* 186 */     this.eFlags |= 0x200;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void freeze() {
/* 192 */     getEType();
/* 193 */     super.freeze();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 204 */     return EcorePackage.Literals.ETYPED_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOrdered() {
/* 214 */     return ((this.eFlags & 0x100) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrdered(boolean newOrdered) {
/* 224 */     boolean oldOrdered = ((this.eFlags & 0x100) != 0);
/* 225 */     if (newOrdered) { this.eFlags |= 0x100; } else { this.eFlags &= 0xFFFFFEFF; }
/* 226 */      if (eNotificationRequired()) {
/* 227 */       eNotify((Notification)new ENotificationImpl(this, 1, 2, oldOrdered, newOrdered));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnique() {
/* 237 */     return ((this.eFlags & 0x200) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnique(boolean newUnique) {
/* 247 */     boolean oldUnique = ((this.eFlags & 0x200) != 0);
/* 248 */     if (newUnique) { this.eFlags |= 0x200; } else { this.eFlags &= 0xFFFFFDFF; }
/* 249 */      if (eNotificationRequired()) {
/* 250 */       eNotify((Notification)new ENotificationImpl(this, 1, 3, oldUnique, newUnique));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLowerBound() {
/* 260 */     return this.lowerBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowerBound(int newLowerBound) {
/* 270 */     int oldLowerBound = this.lowerBound;
/* 271 */     this.lowerBound = newLowerBound;
/* 272 */     if (eNotificationRequired()) {
/* 273 */       eNotify((Notification)new ENotificationImpl(this, 1, 4, oldLowerBound, this.lowerBound));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUpperBound() {
/* 283 */     return this.upperBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperBound(int newUpperBound) {
/* 293 */     int oldUpperBound = this.upperBound;
/* 294 */     this.upperBound = newUpperBound;
/* 295 */     if (eNotificationRequired()) {
/* 296 */       eNotify((Notification)new ENotificationImpl(this, 1, 5, oldUpperBound, this.upperBound));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isMany() {
/* 301 */     int upper = getUpperBound();
/* 302 */     return !(upper <= 1 && upper != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRequired() {
/* 307 */     int lower = getLowerBound();
/* 308 */     return (lower >= 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getEType() {
/* 318 */     if (!isFrozen() && this.eType != null && this.eType.eIsProxy()) {
/*     */       
/* 320 */       InternalEObject oldEType = (InternalEObject)this.eType;
/* 321 */       this.eType = (EClassifier)eResolveProxy(oldEType);
/* 322 */       if (this.eType != oldEType)
/*     */       {
/* 324 */         if (eNotificationRequired())
/* 325 */           eNotify((Notification)new ENotificationImpl(this, 9, 8, oldEType, this.eType)); 
/*     */       }
/*     */     } 
/* 328 */     return this.eType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier basicGetEType() {
/* 338 */     return this.eType;
/*     */   }
/*     */   
/*     */   public NotificationChain setEType(EClassifier newEType, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 343 */     EClassifier oldEType = this.eType;
/* 344 */     this.eType = newEType;
/* 345 */     if (eNotificationRequired()) {
/*     */       
/* 347 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 8, oldEType, this.eType);
/* 348 */       if (msgs == null) {
/*     */         
/* 350 */         eNotificationImpl = notification;
/*     */       }
/*     */       else {
/*     */         
/* 354 */         eNotificationImpl.add((Notification)notification);
/*     */       } 
/*     */     } 
/* 357 */     return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEType(EClassifier newEType) {
/* 362 */     NotificationChain msgs = setEType(newEType, (NotificationChain)null);
/* 363 */     EGenericType newEGenericType = null;
/* 364 */     if (newEType != null) {
/*     */       
/* 366 */       newEGenericType = EcoreFactory.eINSTANCE.createEGenericType();
/* 367 */       newEGenericType.setEClassifier(this.eType);
/*     */     } 
/* 369 */     msgs = setEGenericType(newEGenericType, msgs);
/* 370 */     if (msgs != null)
/*     */     {
/* 372 */       msgs.dispatch();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetEType() {
/* 383 */     setEType((EClassifier)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSetEType() {
/* 394 */     if (this.eType != null && 
/* 395 */       this.eGenericType.getETypeParameter() == null && 
/* 396 */       this.eGenericType.getETypeArguments().isEmpty()) return true;
/*     */     
/*     */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EGenericType getEGenericType() {
/* 406 */     return this.eGenericType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetEGenericType(EGenericType newEGenericType, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*     */     NotificationChain notificationChain;
/* 416 */     EGenericType oldEGenericType = this.eGenericType;
/* 417 */     this.eGenericType = newEGenericType;
/* 418 */     if (eNotificationRequired()) {
/*     */       
/* 420 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 9, oldEGenericType, newEGenericType);
/* 421 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 423 */     }  if (newEGenericType == null) {
/*     */       
/* 425 */       if (this.eType != null)
/*     */       {
/* 427 */         notificationChain = setEType((EClassifier)null, (NotificationChain)eNotificationImpl);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 432 */       EClassifier newEType = ((EGenericTypeImpl)newEGenericType).basicGetERawType();
/* 433 */       if (newEType != this.eType)
/*     */       {
/* 435 */         notificationChain = setEType(newEType, notificationChain);
/*     */       }
/*     */     } 
/* 438 */     return notificationChain;
/*     */   }
/*     */   
/*     */   public NotificationChain setEGenericType(EGenericType newEGenericType, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 443 */     if (newEGenericType != this.eGenericType) {
/*     */       
/* 445 */       if (this.eGenericType != null)
/* 446 */         msgs = ((InternalEObject)this.eGenericType).eInverseRemove(this, -10, null, msgs); 
/* 447 */       if (newEGenericType != null)
/* 448 */         msgs = ((InternalEObject)newEGenericType).eInverseAdd(this, -10, null, msgs); 
/* 449 */       msgs = basicSetEGenericType(newEGenericType, msgs);
/*     */     }
/* 451 */     else if (eNotificationRequired()) {
/*     */       
/* 453 */       ENotificationImpl notification = 
/* 454 */         new ENotificationImpl(this, 1, 9, newEGenericType, newEGenericType);
/* 455 */       if (msgs == null) {
/*     */         
/* 457 */         eNotificationImpl = notification;
/*     */       }
/*     */       else {
/*     */         
/* 461 */         eNotificationImpl.add((Notification)notification);
/*     */       } 
/*     */     } 
/* 464 */     return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEGenericType(EGenericType newEGenericType) {
/* 474 */     NotificationChain msgs = setEGenericType(newEGenericType, (NotificationChain)null);
/* 475 */     if (msgs != null)
/*     */     {
/* 477 */       msgs.dispatch();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicUnsetEGenericType(NotificationChain msgs) {
/* 488 */     msgs = setEType((EClassifier)null, msgs);
/* 489 */     return basicSetEGenericType((EGenericType)null, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetEGenericType() {
/* 499 */     setEGenericType((EGenericType)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSetEGenericType() {
/* 509 */     return (this.eGenericType != null && !isSetEType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 520 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 523 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 9:
/* 525 */         return basicUnsetEGenericType(msgs);
/*     */     } 
/* 527 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 538 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 541 */         return getEAnnotations();
/*     */       case 1:
/* 543 */         return getName();
/*     */       case 2:
/* 545 */         return Boolean.valueOf(isOrdered());
/*     */       case 3:
/* 547 */         return Boolean.valueOf(isUnique());
/*     */       case 4:
/* 549 */         return Integer.valueOf(getLowerBound());
/*     */       case 5:
/* 551 */         return Integer.valueOf(getUpperBound());
/*     */       case 6:
/* 553 */         return Boolean.valueOf(isMany());
/*     */       case 7:
/* 555 */         return Boolean.valueOf(isRequired());
/*     */       case 8:
/* 557 */         if (resolve) return getEType(); 
/* 558 */         return basicGetEType();
/*     */       case 9:
/* 560 */         return getEGenericType();
/*     */     } 
/* 562 */     return eDynamicGet(featureID, resolve, coreType);
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
/*     */   public void eSet(int featureID, Object newValue) {
/* 574 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 577 */         getEAnnotations().clear();
/* 578 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 581 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 584 */         setOrdered(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 3:
/* 587 */         setUnique(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 4:
/* 590 */         setLowerBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 5:
/* 593 */         setUpperBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 8:
/* 596 */         setEType((EClassifier)newValue);
/*     */         return;
/*     */       case 9:
/* 599 */         setEGenericType((EGenericType)newValue);
/*     */         return;
/*     */     } 
/* 602 */     eDynamicSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 613 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 616 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 619 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 622 */         setOrdered(true);
/*     */         return;
/*     */       case 3:
/* 625 */         setUnique(true);
/*     */         return;
/*     */       case 4:
/* 628 */         setLowerBound(0);
/*     */         return;
/*     */       case 5:
/* 631 */         setUpperBound(1);
/*     */         return;
/*     */       case 8:
/* 634 */         unsetEType();
/*     */         return;
/*     */       case 9:
/* 637 */         unsetEGenericType();
/*     */         return;
/*     */     } 
/* 640 */     eDynamicUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 651 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 654 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 656 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 658 */         return !((this.eFlags & 0x100) != 0);
/*     */       case 3:
/* 660 */         return !((this.eFlags & 0x200) != 0);
/*     */       case 4:
/* 662 */         return (this.lowerBound != 0);
/*     */       case 5:
/* 664 */         return (this.upperBound != 1);
/*     */       case 6:
/* 666 */         return isMany();
/*     */       case 7:
/* 668 */         return isRequired();
/*     */       case 8:
/* 670 */         return isSetEType();
/*     */       case 9:
/* 672 */         return isSetEGenericType();
/*     */     } 
/* 674 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 685 */     if (eIsProxy()) return super.toString();
/*     */     
/* 687 */     StringBuffer result = new StringBuffer(super.toString());
/* 688 */     result.append(" (ordered: ");
/* 689 */     result.append(((this.eFlags & 0x100) != 0));
/* 690 */     result.append(", unique: ");
/* 691 */     result.append(((this.eFlags & 0x200) != 0));
/* 692 */     result.append(", lowerBound: ");
/* 693 */     result.append(this.lowerBound);
/* 694 */     result.append(", upperBound: ");
/* 695 */     result.append(this.upperBound);
/* 696 */     result.append(')');
/* 697 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ETypedElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */