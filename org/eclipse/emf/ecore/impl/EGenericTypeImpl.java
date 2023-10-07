/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.ETypeParameter;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
/*     */ public class EGenericTypeImpl
/*     */   extends MinimalEObjectImpl.Container
/*     */   implements EGenericType
/*     */ {
/*     */   protected EGenericType eUpperBound;
/*     */   protected EList<EGenericType> eTypeArguments;
/*     */   static EDataType eJavaObject;
/* 101 */   protected EClassifier eRawType = (EClassifier)eJavaObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EGenericType eLowerBound;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ETypeParameter eTypeParameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClassifier eClassifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 151 */     return EcorePackage.Literals.EGENERIC_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EGenericType getEUpperBound() {
/* 161 */     return this.eUpperBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetEUpperBound(EGenericType newEUpperBound, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 171 */     EGenericType oldEUpperBound = this.eUpperBound;
/* 172 */     this.eUpperBound = newEUpperBound;
/* 173 */     if (eNotificationRequired()) {
/*     */       
/* 175 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 0, oldEUpperBound, newEUpperBound);
/* 176 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 178 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEUpperBound(EGenericType newEUpperBound) {
/* 188 */     if (newEUpperBound != this.eUpperBound) {
/*     */       
/* 190 */       NotificationChain msgs = null;
/* 191 */       if (this.eUpperBound != null)
/* 192 */         msgs = ((InternalEObject)this.eUpperBound).eInverseRemove(this, -1, null, msgs); 
/* 193 */       if (newEUpperBound != null)
/* 194 */         msgs = ((InternalEObject)newEUpperBound).eInverseAdd(this, -1, null, msgs); 
/* 195 */       msgs = basicSetEUpperBound(newEUpperBound, msgs);
/* 196 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 198 */     } else if (eNotificationRequired()) {
/* 199 */       eNotify((Notification)new ENotificationImpl(this, 1, 0, newEUpperBound, newEUpperBound));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EGenericType> getETypeArguments() {
/* 209 */     if (this.eTypeArguments == null)
/*     */     {
/* 211 */       this.eTypeArguments = (EList<EGenericType>)new EObjectContainmentEList(EGenericType.class, this, 1);
/*     */     }
/* 213 */     return this.eTypeArguments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getERawType() {
/* 223 */     if (this.eRawType != null && this.eRawType.eIsProxy()) {
/*     */       
/* 225 */       InternalEObject oldERawType = (InternalEObject)this.eRawType;
/* 226 */       this.eRawType = (EClassifier)eResolveProxy(oldERawType);
/* 227 */       if (this.eRawType != oldERawType) {
/*     */         
/* 229 */         if (eNotificationRequired()) {
/* 230 */           eNotify((Notification)new ENotificationImpl(this, 9, 2, oldERawType, this.eRawType));
/*     */         }
/* 232 */         if (this.eContainer instanceof EOperation) {
/*     */           
/* 234 */           if (eContainerFeatureID() == -15 && this.eContainer.eNotificationRequired())
/*     */           {
/* 236 */             (new ENotificationImpl(
/* 237 */                 this.eContainer, 
/* 238 */                 9, 
/* 239 */                 13, 
/* 240 */                 oldERawType, 
/* 241 */                 this.eRawType, (
/* 242 */                 (EOperation)this.eContainer).getEGenericExceptions().indexOf(this))).dispatch();
/*     */           }
/*     */         }
/* 245 */         else if (this.eContainer instanceof EClass) {
/*     */           
/* 247 */           if (eContainerFeatureID() == -23 && this.eContainer.eNotificationRequired()) {
/*     */             EClass eClass;
/* 249 */             EClassifier newERawType = this.eRawType;
/* 250 */             if (!(newERawType instanceof EClass))
/*     */             {
/* 252 */               eClass = EcorePackage.Literals.EOBJECT;
/*     */             }
/* 254 */             if (!(oldERawType instanceof EClass))
/*     */             {
/* 256 */               oldERawType = (InternalEObject)EcorePackage.Literals.EOBJECT;
/*     */             }
/*     */             
/* 259 */             (new ENotificationImpl(
/* 260 */                 this.eContainer, 
/* 261 */                 9, 
/* 262 */                 10, 
/* 263 */                 oldERawType, 
/* 264 */                 eClass, (
/* 265 */                 (EClass)this.eContainer).getEGenericSuperTypes().indexOf(this))).dispatch();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 270 */     return this.eRawType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier basicGetERawType() {
/* 280 */     return this.eRawType;
/*     */   } public NotificationChain setERawType(EClassifier newERawType, NotificationChain msgs) {
/*     */     EDataType eDataType;
/*     */     ENotificationImpl eNotificationImpl;
/*     */     NotificationChain notificationChain;
/* 285 */     EClassifier oldERawType = this.eRawType;
/* 286 */     if (newERawType == null)
/*     */     {
/* 288 */       eDataType = eJavaObject;
/*     */     }
/* 290 */     this.eRawType = (EClassifier)eDataType;
/* 291 */     if (eNotificationRequired()) {
/*     */       
/* 293 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 2, oldERawType, this.eRawType);
/* 294 */       if (msgs == null) {
/*     */         
/* 296 */         eNotificationImpl = notification;
/*     */       }
/*     */       else {
/*     */         
/* 300 */         eNotificationImpl.add((Notification)notification);
/*     */       } 
/*     */     } 
/*     */     
/* 304 */     if (oldERawType != eDataType) {
/*     */       ENotificationImpl eNotificationImpl1; EDataType eDataType1;
/* 306 */       if (this.eContainer instanceof ETypedElementImpl) {
/*     */         
/* 308 */         if (eContainerFeatureID() == -10) {
/*     */           
/* 310 */           notificationChain = ((ETypedElementImpl)this.eContainer).setEType((EClassifier)eDataType, (NotificationChain)eNotificationImpl);
/*     */         }
/* 312 */         else if (eContainerFeatureID() == -15) {
/*     */           
/* 314 */           if (eDataType == null)
/*     */           {
/* 316 */             eDataType = EcorePackage.Literals.EJAVA_OBJECT;
/*     */           }
/* 318 */           if (oldERawType == null)
/*     */           {
/* 320 */             eDataType1 = EcorePackage.Literals.EJAVA_OBJECT;
/*     */           }
/*     */           
/* 323 */           if (this.eContainer.eNotificationRequired()) {
/*     */             
/* 325 */             ENotificationImpl notification = 
/* 326 */               new ENotificationImpl(
/* 327 */                 this.eContainer, 
/* 328 */                 1, 
/* 329 */                 13, 
/* 330 */                 eDataType1, 
/* 331 */                 eDataType, (
/* 332 */                 (EOperation)this.eContainer).getEGenericExceptions().indexOf(this), 
/* 333 */                 false);
/*     */             
/* 335 */             if (notificationChain == null)
/*     */             {
/* 337 */               eNotificationImpl1 = notification;
/*     */             }
/*     */             else
/*     */             {
/* 341 */               eNotificationImpl1.add((Notification)notification);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 346 */       } else if (this.eContainer instanceof EClass) {
/*     */         
/* 348 */         if (eContainerFeatureID() == -23) {
/*     */           EClass eClass1; EClass eClass2;
/* 350 */           if (!(eDataType instanceof EClass))
/*     */           {
/* 352 */             eClass1 = EcorePackage.Literals.EOBJECT;
/*     */           }
/* 354 */           if (!(eDataType1 instanceof EClass))
/*     */           {
/* 356 */             eClass2 = EcorePackage.Literals.EOBJECT;
/*     */           }
/*     */           
/* 359 */           if (this.eContainer.eNotificationRequired()) {
/*     */             
/* 361 */             ENotificationImpl notification = 
/* 362 */               new ENotificationImpl(
/* 363 */                 this.eContainer, 
/* 364 */                 1, 
/* 365 */                 10, 
/* 366 */                 eClass2, 
/* 367 */                 eClass1, (
/* 368 */                 (EClass)this.eContainer).getEGenericSuperTypes().indexOf(this), 
/* 369 */                 false);
/*     */             
/* 371 */             if (eNotificationImpl1 == null)
/*     */             {
/* 373 */               eNotificationImpl1 = notification;
/*     */             }
/*     */             else
/*     */             {
/* 377 */               eNotificationImpl1.add((Notification)notification);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 382 */       } else if (this.eContainer instanceof ETypeParameter) {
/*     */         
/* 384 */         ETypeParameter eTypeParameter = (ETypeParameter)this.eContainer;
/* 385 */         synchronized (eTypeParameter) {
/*     */ 
/*     */           
/* 388 */           Set<EGenericTypeImpl> eGenericTypes = (Set)((ETypeParameterImpl)eTypeParameter).getEGenericTypes();
/* 389 */           for (EGenericTypeImpl eGenericType : eGenericTypes)
/*     */           {
/* 391 */             notificationChain = eGenericType.setERawType(eGenericType.getErasure(eTypeParameter), (NotificationChain)eNotificationImpl1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 396 */     return notificationChain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs) {
/* 403 */     msgs = super.eBasicSetContainer(newContainer, newContainerFeatureID, msgs);
/* 404 */     if (this.eTypeParameter != null && newContainer instanceof org.eclipse.emf.ecore.EStructuralFeature) {
/*     */ 
/*     */ 
/*     */       
/* 408 */       EClassifier newERawType = getErasure(this.eTypeParameter);
/* 409 */       if (newERawType != this.eRawType)
/*     */       {
/* 411 */         msgs = setERawType(newERawType, msgs);
/*     */       }
/*     */     } 
/* 414 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EGenericType getELowerBound() {
/* 424 */     return this.eLowerBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetELowerBound(EGenericType newELowerBound, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 434 */     EGenericType oldELowerBound = this.eLowerBound;
/* 435 */     this.eLowerBound = newELowerBound;
/* 436 */     if (eNotificationRequired()) {
/*     */       
/* 438 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 3, oldELowerBound, newELowerBound);
/* 439 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 441 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setELowerBound(EGenericType newELowerBound) {
/* 451 */     if (newELowerBound != this.eLowerBound) {
/*     */       
/* 453 */       NotificationChain msgs = null;
/* 454 */       if (this.eLowerBound != null)
/* 455 */         msgs = ((InternalEObject)this.eLowerBound).eInverseRemove(this, -4, null, msgs); 
/* 456 */       if (newELowerBound != null)
/* 457 */         msgs = ((InternalEObject)newELowerBound).eInverseAdd(this, -4, null, msgs); 
/* 458 */       msgs = basicSetELowerBound(newELowerBound, msgs);
/* 459 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 461 */     } else if (eNotificationRequired()) {
/* 462 */       eNotify((Notification)new ENotificationImpl(this, 1, 3, newELowerBound, newELowerBound));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ETypeParameter getETypeParameter() {
/* 472 */     return this.eTypeParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetETypeParameter(ETypeParameter newETypeParameter, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*     */     NotificationChain notificationChain;
/* 482 */     ETypeParameter oldETypeParameter = this.eTypeParameter;
/* 483 */     this.eTypeParameter = newETypeParameter;
/* 484 */     if (eNotificationRequired()) {
/*     */       
/* 486 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 4, oldETypeParameter, newETypeParameter);
/* 487 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 489 */     }  if (oldETypeParameter != newETypeParameter)
/*     */     {
/* 491 */       if (newETypeParameter != null) {
/*     */         
/* 493 */         notificationChain = setERawType(getErasure(newETypeParameter), (NotificationChain)eNotificationImpl);
/*     */       }
/*     */       else {
/*     */         
/* 497 */         notificationChain = setERawType(this.eClassifier, notificationChain);
/*     */       } 
/*     */     }
/* 500 */     return notificationChain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setETypeParameter(ETypeParameter newETypeParameter) {
/* 510 */     if (newETypeParameter != this.eTypeParameter) {
/*     */ 
/*     */ 
/*     */       
/* 514 */       if (this.eTypeParameter != null)
/*     */       {
/* 516 */         synchronized (this.eTypeParameter) {
/*     */           
/* 518 */           ((ETypeParameterImpl)this.eTypeParameter).getEGenericTypes().remove(this);
/*     */         } 
/*     */       }
/* 521 */       if (newETypeParameter != null)
/*     */       {
/* 523 */         synchronized (newETypeParameter) {
/*     */           
/* 525 */           ((ETypeParameterImpl)newETypeParameter).getEGenericTypes().add(this);
/*     */         } 
/*     */       }
/* 528 */       NotificationChain msgs = basicSetETypeParameter(newETypeParameter, (NotificationChain)null);
/* 529 */       if (msgs != null)
/*     */       {
/* 531 */         msgs.dispatch();
/*     */       }
/*     */     }
/* 534 */     else if (eNotificationRequired()) {
/*     */       
/* 536 */       eNotify((Notification)new ENotificationImpl(this, 1, 4, newETypeParameter, newETypeParameter));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected EClassifier getErasure(ETypeParameter eTypeParameter) {
/* 542 */     if (eTypeParameter == null)
/*     */     {
/* 544 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 548 */     boolean needEClass = !(!(this.eContainer instanceof EClass) && !(this.eContainer instanceof org.eclipse.emf.ecore.EReference));
/* 549 */     boolean needEDataType = (!needEClass && this.eContainer instanceof org.eclipse.emf.ecore.EAttribute);
/* 550 */     for (EGenericType eBound : eTypeParameter.getEBounds()) {
/*     */       
/* 552 */       EClassifier eRawType = eBound.getERawType();
/* 553 */       if (needEClass ? (eRawType instanceof EClass) : (needEDataType ? (eRawType instanceof EDataType) : (eRawType != null)))
/*     */       {
/* 555 */         return eRawType;
/*     */       }
/*     */     } 
/* 558 */     return needEClass ? (EClassifier)EcorePackage.Literals.EOBJECT : (EClassifier)EcorePackage.Literals.EJAVA_OBJECT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getEClassifier() {
/* 569 */     if (this.eClassifier != null && this.eClassifier.eIsProxy()) {
/*     */       
/* 571 */       InternalEObject oldEClassifier = (InternalEObject)this.eClassifier;
/* 572 */       this.eClassifier = (EClassifier)eResolveProxy(oldEClassifier);
/* 573 */       if (this.eClassifier != oldEClassifier)
/*     */       {
/* 575 */         if (eNotificationRequired())
/* 576 */           eNotify((Notification)new ENotificationImpl(this, 9, 5, oldEClassifier, this.eClassifier)); 
/*     */       }
/*     */     } 
/* 579 */     return this.eClassifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier basicGetEClassifier() {
/* 589 */     return this.eClassifier;
/*     */   }
/*     */   
/*     */   public NotificationChain setEClassifier(EClassifier newEClassifier, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 594 */     EClassifier oldEClassifier = this.eClassifier;
/* 595 */     this.eClassifier = newEClassifier;
/* 596 */     if (eNotificationRequired()) {
/*     */       
/* 598 */       ENotificationImpl notification = 
/* 599 */         new ENotificationImpl(this, 1, 5, oldEClassifier, this.eClassifier);
/* 600 */       if (msgs == null) {
/*     */         
/* 602 */         eNotificationImpl = notification;
/*     */       }
/*     */       else {
/*     */         
/* 606 */         eNotificationImpl.add((Notification)notification);
/*     */       } 
/*     */     } 
/* 609 */     return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEClassifier(EClassifier newEClassifier) {
/* 619 */     EClassifier oldEClassifier = this.eClassifier;
/* 620 */     NotificationChain msgs = setEClassifier(newEClassifier, (NotificationChain)null);
/* 621 */     if (oldEClassifier != newEClassifier && this.eTypeParameter == null)
/*     */     {
/* 623 */       msgs = setERawType(newEClassifier, msgs);
/*     */     }
/* 625 */     if (msgs != null)
/*     */     {
/* 627 */       msgs.dispatch();
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
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 639 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 642 */         return basicSetEUpperBound((EGenericType)null, msgs);
/*     */       case 1:
/* 644 */         return ((InternalEList)getETypeArguments()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 646 */         return basicSetELowerBound((EGenericType)null, msgs);
/*     */     } 
/* 648 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
/* 659 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 662 */         return getEUpperBound();
/*     */       case 1:
/* 664 */         return getETypeArguments();
/*     */       case 2:
/* 666 */         if (resolve) return getERawType(); 
/* 667 */         return basicGetERawType();
/*     */       case 3:
/* 669 */         return getELowerBound();
/*     */       case 4:
/* 671 */         return getETypeParameter();
/*     */       case 5:
/* 673 */         if (resolve) return getEClassifier(); 
/* 674 */         return basicGetEClassifier();
/*     */     } 
/* 676 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 688 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 691 */         setEUpperBound((EGenericType)newValue);
/*     */         return;
/*     */       case 1:
/* 694 */         getETypeArguments().clear();
/* 695 */         getETypeArguments().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 698 */         setELowerBound((EGenericType)newValue);
/*     */         return;
/*     */       case 4:
/* 701 */         setETypeParameter((ETypeParameter)newValue);
/*     */         return;
/*     */       case 5:
/* 704 */         setEClassifier((EClassifier)newValue);
/*     */         return;
/*     */     } 
/* 707 */     eDynamicSet(featureID, newValue);
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
/* 718 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 721 */         setEUpperBound((EGenericType)null);
/*     */         return;
/*     */       case 1:
/* 724 */         getETypeArguments().clear();
/*     */         return;
/*     */       case 3:
/* 727 */         setELowerBound((EGenericType)null);
/*     */         return;
/*     */       case 4:
/* 730 */         setETypeParameter((ETypeParameter)null);
/*     */         return;
/*     */       case 5:
/* 733 */         setEClassifier((EClassifier)null);
/*     */         return;
/*     */     } 
/* 736 */     eDynamicUnset(featureID);
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
/* 747 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 750 */         return (this.eUpperBound != null);
/*     */       case 1:
/* 752 */         return (this.eTypeArguments != null && !this.eTypeArguments.isEmpty());
/*     */       case 2:
/* 754 */         return (this.eRawType != null);
/*     */       case 3:
/* 756 */         return (this.eLowerBound != null);
/*     */       case 4:
/* 758 */         return (this.eTypeParameter != null);
/*     */       case 5:
/* 760 */         return (this.eClassifier != null);
/*     */     } 
/* 762 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 768 */     StringBuilder result = new StringBuilder(super.toString());
/* 769 */     result.append(" (expression: ");
/* 770 */     toString(result);
/* 771 */     result.append(')');
/* 772 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void toString(StringBuilder result) {
/* 777 */     if (this.eClassifier != null) {
/*     */       
/* 779 */       String label = this.eClassifier.getName();
/* 780 */       String tail = null;
/* 781 */       if (label != null) {
/*     */         
/* 783 */         result.append(label);
/*     */       }
/*     */       else {
/*     */         
/* 787 */         String instanceTypeName = this.eClassifier.getInstanceTypeName();
/* 788 */         if (instanceTypeName != null) {
/*     */           
/* 790 */           int index = instanceTypeName.indexOf('[');
/* 791 */           if (index != -1) {
/*     */             
/* 793 */             tail = instanceTypeName.substring(index);
/* 794 */             result.append(instanceTypeName, 0, index);
/*     */           }
/*     */           else {
/*     */             
/* 798 */             result.append(instanceTypeName);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 803 */       if (this.eTypeArguments != null && !this.eTypeArguments.isEmpty()) {
/*     */         
/* 805 */         boolean first = true;
/* 806 */         result.append('<');
/* 807 */         for (EGenericType eTypeArgument : this.eTypeArguments) {
/*     */           
/* 809 */           if (first) {
/*     */             
/* 811 */             first = false;
/*     */           }
/*     */           else {
/*     */             
/* 815 */             result.append(", ");
/*     */           } 
/* 817 */           ((EGenericTypeImpl)eTypeArgument).toString(result);
/*     */         } 
/* 819 */         result.append('>');
/*     */       } 
/*     */       
/* 822 */       if (tail != null)
/*     */       {
/* 824 */         result.append(tail);
/*     */       }
/*     */     }
/* 827 */     else if (this.eTypeParameter != null) {
/*     */       
/* 829 */       String label = this.eTypeParameter.getName();
/* 830 */       if (label != null)
/*     */       {
/* 832 */         result.append(label);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 837 */       result.append('?');
/* 838 */       if (this.eLowerBound != null) {
/*     */         
/* 840 */         result.append(" super ");
/* 841 */         ((EGenericTypeImpl)this.eLowerBound).toString(result);
/*     */ 
/*     */       
/*     */       }
/* 845 */       else if (this.eUpperBound != null) {
/*     */         
/* 847 */         result.append(" extends ");
/* 848 */         ((EGenericTypeImpl)this.eUpperBound).toString(result);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EGenericTypeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */