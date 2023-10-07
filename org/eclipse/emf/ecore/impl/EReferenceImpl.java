/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EReferenceImpl
/*     */   extends EStructuralFeatureImpl
/*     */   implements EReference
/*     */ {
/*     */   protected static final boolean CONTAINMENT_EDEFAULT = false;
/*     */   protected static final int CONTAINMENT_EFLAG = 32768;
/*     */   protected static final boolean CONTAINER_EDEFAULT = false;
/*     */   protected static final boolean RESOLVE_PROXIES_EDEFAULT = true;
/*     */   protected static final int RESOLVE_PROXIES_EFLAG = 65536;
/*     */   protected EReference eOpposite;
/*     */   protected EList<EAttribute> eKeys;
/*     */   protected EClass eReferenceType;
/*     */   
/*     */   protected EReferenceImpl() {
/* 134 */     this.eFlags |= 0x10000;
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
/* 145 */     return EcorePackage.Literals.EREFERENCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBidirectional() {
/* 150 */     return (getEOpposite() != null);
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
/*     */   public boolean isContainment() {
/* 162 */     return ((this.eFlags & 0x8000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContainment(boolean newContainment) {
/* 172 */     boolean oldContainment = ((this.eFlags & 0x8000) != 0);
/* 173 */     if (newContainment) { this.eFlags |= 0x8000; } else { this.eFlags &= 0xFFFF7FFF; }
/* 174 */      if (eNotificationRequired()) {
/* 175 */       eNotify((Notification)new ENotificationImpl(this, 1, 18, oldContainment, newContainment));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isContainer() {
/* 186 */     EReference theOpposite = getEOpposite();
/* 187 */     return (theOpposite != null && theOpposite.isContainment());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResolveProxies() {
/* 198 */     return ((this.eFlags & 0x10000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolveProxies(boolean newResolveProxies) {
/* 208 */     boolean oldResolveProxies = ((this.eFlags & 0x10000) != 0);
/* 209 */     if (newResolveProxies) { this.eFlags |= 0x10000; } else { this.eFlags &= 0xFFFEFFFF; }
/* 210 */      if (eNotificationRequired()) {
/* 211 */       eNotify((Notification)new ENotificationImpl(this, 1, 20, oldResolveProxies, newResolveProxies));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EReference getEOpposite() {
/* 222 */     if (this.eOpposite != null && this.eOpposite.eIsProxy()) {
/*     */       
/* 224 */       InternalEObject oldEOpposite = (InternalEObject)this.eOpposite;
/* 225 */       this.eOpposite = (EReference)eResolveProxy(oldEOpposite);
/* 226 */       if (this.eOpposite != oldEOpposite)
/*     */       {
/* 228 */         if (eNotificationRequired())
/* 229 */           eNotify((Notification)new ENotificationImpl(this, 9, 21, oldEOpposite, this.eOpposite)); 
/*     */       }
/*     */     } 
/* 232 */     return this.eOpposite;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EReference basicGetEOpposite() {
/* 242 */     return this.eOpposite;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEOpposite(EReference newEOpposite) {
/* 252 */     EReference oldEOpposite = this.eOpposite;
/* 253 */     this.eOpposite = newEOpposite;
/* 254 */     if (eNotificationRequired()) {
/* 255 */       eNotify((Notification)new ENotificationImpl(this, 1, 21, oldEOpposite, this.eOpposite));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NotificationChain setEType(EClassifier newEType, NotificationChain msgs) {
/* 261 */     this.eReferenceType = null;
/* 262 */     return super.setEType(newEType, msgs);
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
/*     */   public EClass getEReferenceType() {
/* 274 */     if (this.eReferenceType == null || (!isFrozen() && this.eReferenceType.eIsProxy())) {
/*     */       
/* 276 */       EClassifier eType = getEType();
/* 277 */       if (eType instanceof EClass)
/*     */       {
/* 279 */         this.eReferenceType = (EClass)eType;
/*     */       }
/*     */     } 
/* 282 */     return this.eReferenceType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClass basicGetEReferenceType() {
/* 292 */     if (this.eReferenceType == null) {
/*     */       
/* 294 */       EClassifier eType = basicGetEType();
/* 295 */       if (eType instanceof EClass)
/*     */       {
/* 297 */         this.eReferenceType = (EClass)eType;
/*     */       }
/*     */     } 
/* 300 */     return this.eReferenceType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void freeze() {
/* 306 */     getEReferenceType();
/* 307 */     super.freeze();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EAttribute> getEKeys() {
/* 317 */     if (this.eKeys == null)
/*     */     {
/* 319 */       this.eKeys = (EList<EAttribute>)new EObjectResolvingEList(EAttribute.class, this, 23);
/*     */     }
/* 321 */     return this.eKeys;
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
/* 332 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 335 */         return getEAnnotations();
/*     */       case 1:
/* 337 */         return getName();
/*     */       case 2:
/* 339 */         return Boolean.valueOf(isOrdered());
/*     */       case 3:
/* 341 */         return Boolean.valueOf(isUnique());
/*     */       case 4:
/* 343 */         return Integer.valueOf(getLowerBound());
/*     */       case 5:
/* 345 */         return Integer.valueOf(getUpperBound());
/*     */       case 6:
/* 347 */         return Boolean.valueOf(isMany());
/*     */       case 7:
/* 349 */         return Boolean.valueOf(isRequired());
/*     */       case 8:
/* 351 */         if (resolve) return getEType(); 
/* 352 */         return basicGetEType();
/*     */       case 9:
/* 354 */         return getEGenericType();
/*     */       case 10:
/* 356 */         return Boolean.valueOf(isChangeable());
/*     */       case 11:
/* 358 */         return Boolean.valueOf(isVolatile());
/*     */       case 12:
/* 360 */         return Boolean.valueOf(isTransient());
/*     */       case 13:
/* 362 */         return getDefaultValueLiteral();
/*     */       case 14:
/* 364 */         return getDefaultValue();
/*     */       case 15:
/* 366 */         return Boolean.valueOf(isUnsettable());
/*     */       case 16:
/* 368 */         return Boolean.valueOf(isDerived());
/*     */       case 17:
/* 370 */         return getEContainingClass();
/*     */       case 18:
/* 372 */         return Boolean.valueOf(isContainment());
/*     */       case 19:
/* 374 */         return Boolean.valueOf(isContainer());
/*     */       case 20:
/* 376 */         return Boolean.valueOf(isResolveProxies());
/*     */       case 21:
/* 378 */         if (resolve) return getEOpposite(); 
/* 379 */         return basicGetEOpposite();
/*     */       case 22:
/* 381 */         if (resolve) return getEReferenceType(); 
/* 382 */         return basicGetEReferenceType();
/*     */       case 23:
/* 384 */         return getEKeys();
/*     */     } 
/* 386 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 398 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 401 */         getEAnnotations().clear();
/* 402 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 405 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 408 */         setOrdered(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 3:
/* 411 */         setUnique(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 4:
/* 414 */         setLowerBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 5:
/* 417 */         setUpperBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 8:
/* 420 */         setEType((EClassifier)newValue);
/*     */         return;
/*     */       case 9:
/* 423 */         setEGenericType((EGenericType)newValue);
/*     */         return;
/*     */       case 10:
/* 426 */         setChangeable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 11:
/* 429 */         setVolatile(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 12:
/* 432 */         setTransient(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 13:
/* 435 */         setDefaultValueLiteral((String)newValue);
/*     */         return;
/*     */       case 15:
/* 438 */         setUnsettable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 16:
/* 441 */         setDerived(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 18:
/* 444 */         setContainment(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 20:
/* 447 */         setResolveProxies(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 21:
/* 450 */         setEOpposite((EReference)newValue);
/*     */         return;
/*     */       case 23:
/* 453 */         getEKeys().clear();
/* 454 */         getEKeys().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 457 */     eDynamicSet(featureID, newValue);
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
/* 468 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 471 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 474 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 477 */         setOrdered(true);
/*     */         return;
/*     */       case 3:
/* 480 */         setUnique(true);
/*     */         return;
/*     */       case 4:
/* 483 */         setLowerBound(0);
/*     */         return;
/*     */       case 5:
/* 486 */         setUpperBound(1);
/*     */         return;
/*     */       case 8:
/* 489 */         unsetEType();
/*     */         return;
/*     */       case 9:
/* 492 */         unsetEGenericType();
/*     */         return;
/*     */       case 10:
/* 495 */         setChangeable(true);
/*     */         return;
/*     */       case 11:
/* 498 */         setVolatile(false);
/*     */         return;
/*     */       case 12:
/* 501 */         setTransient(false);
/*     */         return;
/*     */       case 13:
/* 504 */         setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
/*     */         return;
/*     */       case 15:
/* 507 */         setUnsettable(false);
/*     */         return;
/*     */       case 16:
/* 510 */         setDerived(false);
/*     */         return;
/*     */       case 18:
/* 513 */         setContainment(false);
/*     */         return;
/*     */       case 20:
/* 516 */         setResolveProxies(true);
/*     */         return;
/*     */       case 21:
/* 519 */         setEOpposite((EReference)null);
/*     */         return;
/*     */       case 23:
/* 522 */         getEKeys().clear();
/*     */         return;
/*     */     } 
/* 525 */     eDynamicUnset(featureID);
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
/* 536 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 539 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 541 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 543 */         return !((this.eFlags & 0x100) != 0);
/*     */       case 3:
/* 545 */         return !((this.eFlags & 0x200) != 0);
/*     */       case 4:
/* 547 */         return (this.lowerBound != 0);
/*     */       case 5:
/* 549 */         return (this.upperBound != 1);
/*     */       case 6:
/* 551 */         return isMany();
/*     */       case 7:
/* 553 */         return isRequired();
/*     */       case 8:
/* 555 */         return isSetEType();
/*     */       case 9:
/* 557 */         return isSetEGenericType();
/*     */       case 10:
/* 559 */         return !((this.eFlags & 0x400) != 0);
/*     */       case 11:
/* 561 */         return ((this.eFlags & 0x800) != 0);
/*     */       case 12:
/* 563 */         return ((this.eFlags & 0x1000) != 0);
/*     */       case 13:
/* 565 */         return (DEFAULT_VALUE_LITERAL_EDEFAULT == null) ? ((this.defaultValueLiteral != null)) : (!DEFAULT_VALUE_LITERAL_EDEFAULT.equals(this.defaultValueLiteral));
/*     */       case 14:
/* 567 */         return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));
/*     */       case 15:
/* 569 */         return ((this.eFlags & 0x2000) != 0);
/*     */       case 16:
/* 571 */         return ((this.eFlags & 0x4000) != 0);
/*     */       case 17:
/* 573 */         return (getEContainingClass() != null);
/*     */       case 18:
/* 575 */         return ((this.eFlags & 0x8000) != 0);
/*     */       case 19:
/* 577 */         return isContainer();
/*     */       case 20:
/* 579 */         return !((this.eFlags & 0x10000) != 0);
/*     */       case 21:
/* 581 */         return (this.eOpposite != null);
/*     */       case 22:
/* 583 */         return (basicGetEReferenceType() != null);
/*     */       case 23:
/* 585 */         return (this.eKeys != null && !this.eKeys.isEmpty());
/*     */     } 
/* 587 */     return eDynamicIsSet(featureID);
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
/* 598 */     if (eIsProxy()) return super.toString();
/*     */     
/* 600 */     StringBuffer result = new StringBuffer(super.toString());
/* 601 */     result.append(" (containment: ");
/* 602 */     result.append(((this.eFlags & 0x8000) != 0));
/* 603 */     result.append(", resolveProxies: ");
/* 604 */     result.append(((this.eFlags & 0x10000) != 0));
/* 605 */     result.append(')');
/* 606 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EReferenceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */