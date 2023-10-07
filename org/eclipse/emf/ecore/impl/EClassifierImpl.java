/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.ETypeParameter;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
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
/*     */ public abstract class EClassifierImpl
/*     */   extends ENamedElementImpl
/*     */   implements EClassifier, BasicExtendedMetaData.EClassifierExtendedMetaData.Holder
/*     */ {
/*  60 */   protected int metaObjectID = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  80 */     return EcorePackage.Literals.ECLASSIFIER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClassifierID() {
/*  90 */     if (this.metaObjectID == -1)
/*     */     {
/*  92 */       this.metaObjectID = computeClassifierID();
/*     */     }
/*  94 */     return this.metaObjectID;
/*     */   }
/*     */ 
/*     */   
/*     */   private final int computeClassifierID() {
/*  99 */     EPackage ePackage = getEPackage();
/* 100 */     return 
/*     */       
/* 102 */       (ePackage != null) ? 
/* 103 */       ePackage.getEClassifiers().indexOf(this) : 
/* 104 */       -1;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 116 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 119 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 6:
/* 121 */         if (eInternalContainer() != null)
/* 122 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 123 */         return eBasicSetContainer(otherEnd, 6, msgs);
/*     */     } 
/* 125 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
/* 136 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 139 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 6:
/* 141 */         return eBasicSetContainer((InternalEObject)null, 6, msgs);
/*     */       case 7:
/* 143 */         return ((InternalEList)getETypeParameters()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 145 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 156 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 6:
/* 159 */         return eInternalContainer().eInverseRemove(this, 5, EPackage.class, msgs);
/*     */     } 
/* 161 */     return eDynamicBasicRemoveFromContainer(msgs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClassifierID(int id) {
/* 166 */     this.metaObjectID = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstance(Object object) {
/* 177 */     if (object != null) {
/*     */       
/* 179 */       Class<?> instanceClass = getInstanceClass();
/* 180 */       if (instanceClass != null) {
/*     */         
/* 182 */         if (instanceClass.isPrimitive())
/*     */         {
/* 184 */           if (instanceClass == boolean.class)
/*     */           {
/* 186 */             return object instanceof Boolean;
/*     */           }
/* 188 */           if (instanceClass == int.class)
/*     */           {
/* 190 */             return object instanceof Integer;
/*     */           }
/* 192 */           if (instanceClass == float.class)
/*     */           {
/* 194 */             return object instanceof Float;
/*     */           }
/* 196 */           if (instanceClass == byte.class)
/*     */           {
/* 198 */             return object instanceof Byte;
/*     */           }
/* 200 */           if (instanceClass == char.class)
/*     */           {
/* 202 */             return object instanceof Character;
/*     */           }
/* 204 */           if (instanceClass == double.class)
/*     */           {
/* 206 */             return object instanceof Double;
/*     */           }
/* 208 */           if (instanceClass == short.class)
/*     */           {
/* 210 */             return object instanceof Short;
/*     */           }
/* 212 */           if (instanceClass == long.class)
/*     */           {
/* 214 */             return object instanceof Long;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 219 */           return instanceClass.isInstance(object);
/*     */         }
/*     */       
/* 222 */       } else if (object instanceof EObject) {
/*     */         
/* 224 */         return dynamicIsInstance((EObject)object);
/*     */       } 
/*     */     } 
/*     */     
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean dynamicIsInstance(EObject eObject) {
/* 233 */     return (eObject.eClass() == this);
/*     */   }
/*     */   
/*     */   protected String instanceClassName;
/*     */   protected String generatedInstanceClassName;
/*     */   
/*     */   @Deprecated
/*     */   public String getInstanceClassNameGen() {
/*     */     return this.instanceClassName;
/*     */   }
/*     */   
/* 244 */   protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null; public String getInstanceClassName() { return (this.instanceClassName != null) ? this.instanceClassName : this.generatedInstanceClassName; }
/*     */   public void setGeneratedInstanceClass(boolean isGenerated) { if (isGenerated) { if (this.generatedInstanceClassName == null) { this.generatedInstanceClassName = this.instanceClassName; this.instanceClassName = null; }
/*     */        }
/*     */     else if (this.generatedInstanceClassName != null)
/*     */     { this.instanceClassName = this.generatedInstanceClassName; this.generatedInstanceClassName = null; }
/*     */      }
/*     */   protected void basicSetInstanceClassName(String value) { if (this.instanceClassName == null && this.generatedInstanceClassName != null) {
/*     */       this.instanceClassName = this.generatedInstanceClassName; this.generatedInstanceClassName = null;
/*     */     }  setInstanceClassNameGen((value == null) ? null : value.intern()); if (this.instanceClass != null)
/*     */       setInstanceClassGen((Class<?>)null);  }
/* 254 */   protected EClassifierImpl() { this.instanceClassName = INSTANCE_CLASS_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     this.instanceClass = INSTANCE_CLASS_EDEFAULT; }
/*     */   public void setInstanceClassName(String value) { basicSetInstanceClassName(value); basicSetInstanceTypeName(this.instanceClassName); }
/*     */   public void setInstanceClassNameGen(String newInstanceClassName) { String oldInstanceClassName = this.instanceClassName; this.instanceClassName = newInstanceClassName;
/*     */     if (eNotificationRequired())
/* 402 */       eNotify((Notification)new ENotificationImpl(this, 1, 2, oldInstanceClassName, this.instanceClassName));  } public Class<?> getInstanceClass() { if (this.instanceClass == null && (this.instanceClassName != null || this.generatedInstanceClassName != null)) {
/*     */       
/* 404 */       Class<?> primitiveClass = getPrimitiveOrArrayClass();
/* 405 */       if (primitiveClass != null) {
/*     */         
/* 407 */         setInstanceClassGen(primitiveClass);
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 413 */           setInstanceClassGen(getClassForName(getInstanceClassName()));
/*     */         }
/* 415 */         catch (ClassNotFoundException classNotFoundException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 421 */     return getInstanceClassGen(); }
/*     */   
/*     */   public void unsetInstanceClassName() {
/*     */     setInstanceClassName((String)null);
/*     */   }
/*     */   public boolean isSetInstanceClassName() {
/*     */     return (this.instanceClassName != null && this.instanceClassName == this.instanceTypeName);
/*     */   }
/*     */   protected static final Class<?> INSTANCE_CLASS_EDEFAULT = null; protected static final Object DEFAULT_VALUE_EDEFAULT = null; private static final String INSTANCE_TYPE_NAME_EDEFAULT = null; protected String instanceTypeName; protected EList<ETypeParameter> eTypeParameters; protected Class<?> instanceClass; EPackage ePackage; protected BasicExtendedMetaData.EClassifierExtendedMetaData eClassifierExtendedMetaData;
/*     */   
/*     */   protected Class<?> getClassForName(String name) throws ClassNotFoundException {
/* 432 */     EPackage p = getEPackage();
/* 433 */     return (p != null) ? Class.forName(name, true, p.getClass().getClassLoader()) : Class.forName(name);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class<?> getPrimitiveOrArrayClass() {
/* 438 */     String className = getInstanceClassName();
/* 439 */     int arrayIndex = className.indexOf('[');
/* 440 */     if (arrayIndex != -1) {
/*     */       
/* 442 */       String componentClassName = className.substring(0, arrayIndex);
/* 443 */       StringBuffer result = new StringBuffer(); 
/* 444 */       do { result.append('['); } while ((arrayIndex = className.indexOf('[', ++arrayIndex)) != -1);
/* 445 */       if (componentClassName.equals("boolean")) {
/* 446 */         result.append('Z');
/* 447 */       } else if (componentClassName.equals("byte")) {
/* 448 */         result.append('B');
/* 449 */       } else if (componentClassName.equals("char")) {
/* 450 */         result.append('C');
/* 451 */       } else if (componentClassName.equals("double")) {
/* 452 */         result.append('D');
/* 453 */       } else if (componentClassName.equals("float")) {
/* 454 */         result.append('F');
/* 455 */       } else if (componentClassName.equals("int")) {
/* 456 */         result.append('I');
/* 457 */       } else if (componentClassName.equals("long")) {
/* 458 */         result.append('J');
/* 459 */       } else if (componentClassName.equals("short")) {
/* 460 */         result.append('S');
/*     */       } else {
/* 462 */         result.append('L');
/* 463 */         result.append(componentClassName);
/* 464 */         result.append(';');
/*     */       } 
/*     */       
/*     */       try {
/* 468 */         return getClassForName(result.toString());
/*     */       }
/* 470 */       catch (ClassNotFoundException classNotFoundException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 475 */     else if (className.indexOf('.') == -1) {
/*     */       
/* 477 */       if (className.equals("boolean"))
/* 478 */         return boolean.class; 
/* 479 */       if (className.equals("byte"))
/* 480 */         return byte.class; 
/* 481 */       if (className.equals("char"))
/* 482 */         return char.class; 
/* 483 */       if (className.equals("double"))
/* 484 */         return double.class; 
/* 485 */       if (className.equals("float"))
/* 486 */         return float.class; 
/* 487 */       if (className.equals("int"))
/* 488 */         return int.class; 
/* 489 */       if (className.equals("long"))
/* 490 */         return long.class; 
/* 491 */       if (className.equals("short"))
/* 492 */         return short.class; 
/*     */     } 
/* 494 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getInstanceClassGen() {
/* 501 */     return this.instanceClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInstanceClass(Class<?> value) {
/* 506 */     if (value == null) {
/*     */       
/* 508 */       setInstanceClassNameGen((String)null);
/* 509 */       basicSetInstanceTypeName((String)null);
/*     */     }
/* 511 */     else if (value.isArray()) {
/*     */       
/* 513 */       String indices = "[]";
/* 514 */       for (Class<?> component = value.getComponentType();; component = component.getComponentType())
/*     */       {
/* 516 */         if (!component.isArray()) {
/*     */           
/* 518 */           String name = (String.valueOf(component.getName()) + indices).intern();
/* 519 */           setInstanceClassNameGen(name);
/* 520 */           basicSetInstanceTypeName(name);
/*     */           break;
/*     */         } 
/* 523 */         indices = String.valueOf(indices) + "[]";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 528 */       String name = value.getName().intern();
/* 529 */       setInstanceClassNameGen(name);
/* 530 */       basicSetInstanceTypeName(name);
/*     */     } 
/*     */     
/* 533 */     setInstanceClassGen(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstanceClassGen(Class<?> newInstanceClass) {
/* 540 */     this.instanceClass = newInstanceClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDefaultValue() {
/* 547 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceTypeName() {
/* 557 */     return this.instanceTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstanceTypeName(String newInstanceTypeName) {
/* 567 */     String oldInstanceTypeName = this.instanceTypeName;
/* 568 */     if (newInstanceTypeName == null) {
/*     */       
/* 570 */       this.instanceTypeName = null;
/* 571 */       basicSetInstanceClassName((String)null);
/*     */     }
/*     */     else {
/*     */       
/* 575 */       this.instanceTypeName = newInstanceTypeName.intern();
/*     */ 
/*     */       
/* 578 */       int index = newInstanceTypeName.indexOf('<');
/* 579 */       if (index != -1) {
/*     */ 
/*     */ 
/*     */         
/* 583 */         String newInstanceClassName = newInstanceTypeName.substring(0, index);
/*     */ 
/*     */ 
/*     */         
/* 587 */         if (newInstanceTypeName.indexOf('.') == -1 && 
/* 588 */           !newInstanceClassName.equals("boolean") && 
/* 589 */           !newInstanceClassName.equals("byte") && 
/* 590 */           !newInstanceClassName.equals("char") && 
/* 591 */           !newInstanceClassName.equals("double") && 
/* 592 */           !newInstanceClassName.equals("float") && 
/* 593 */           !newInstanceClassName.equals("int") && 
/* 594 */           !newInstanceClassName.equals("long") && 
/* 595 */           !newInstanceClassName.equals("short"))
/*     */         {
/* 597 */           newInstanceClassName = "java.lang.Object";
/*     */         }
/*     */         
/* 600 */         int end = newInstanceTypeName.lastIndexOf('>');
/* 601 */         if (end != -1)
/*     */         {
/*     */ 
/*     */           
/* 605 */           newInstanceClassName = String.valueOf(newInstanceClassName) + newInstanceTypeName.substring(end + 1);
/*     */         }
/* 607 */         basicSetInstanceClassName(newInstanceClassName);
/*     */       }
/*     */       else {
/*     */         
/* 611 */         String newInstanceClassName = newInstanceTypeName;
/* 612 */         if (newInstanceTypeName.indexOf('.') == -1) {
/*     */           
/* 614 */           index = newInstanceTypeName.indexOf('[');
/* 615 */           if (index != -1)
/*     */           {
/* 617 */             newInstanceClassName = newInstanceTypeName.substring(0, index);
/*     */           }
/* 619 */           if (!newInstanceClassName.equals("boolean") && 
/* 620 */             !newInstanceClassName.equals("byte") && 
/* 621 */             !newInstanceClassName.equals("char") && 
/* 622 */             !newInstanceClassName.equals("double") && 
/* 623 */             !newInstanceClassName.equals("float") && 
/* 624 */             !newInstanceClassName.equals("int") && 
/* 625 */             !newInstanceClassName.equals("long") && 
/* 626 */             !newInstanceClassName.equals("short")) {
/*     */             
/* 628 */             newInstanceClassName = "java.lang.Object";
/* 629 */             if (index != -1)
/*     */             {
/* 631 */               newInstanceClassName = String.valueOf(newInstanceClassName) + newInstanceTypeName.substring(index);
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 636 */             newInstanceClassName = newInstanceTypeName;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 643 */         basicSetInstanceClassName(newInstanceClassName);
/* 644 */         if (newInstanceClassName == newInstanceTypeName)
/*     */         {
/* 646 */           this.instanceTypeName = this.instanceClassName;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 651 */     if (eNotificationRequired())
/*     */     {
/* 653 */       eNotify(
/* 654 */           (Notification)new ENotificationImpl(this, 1, 5, oldInstanceTypeName, newInstanceTypeName));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void basicSetInstanceTypeName(String newInstanceTypeName) {
/* 660 */     String oldInstanceTypeName = this.instanceTypeName;
/* 661 */     this.instanceTypeName = newInstanceTypeName;
/* 662 */     if (eNotificationRequired())
/*     */     {
/* 664 */       eNotify(
/* 665 */           (Notification)new ENotificationImpl(this, 1, 5, oldInstanceTypeName, newInstanceTypeName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetInstanceTypeName() {
/* 676 */     setInstanceTypeName((String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSetInstanceTypeName() {
/* 686 */     return (this.instanceTypeName != null && this.instanceTypeName != this.instanceClassName && this.instanceTypeName != this.generatedInstanceClassName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getEPackageGen() {
/* 696 */     if (eContainerFeatureID() != 6) return null; 
/* 697 */     return (EPackage)eContainer();
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
/*     */   public EPackage getEPackage() {
/* 713 */     if (this.ePackage != null)
/*     */     {
/* 715 */       return this.ePackage;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 721 */     EPackage result = getEPackageGen();
/* 722 */     if (result != null && !result.eIsProxy())
/*     */     {
/* 724 */       this.ePackage = result;
/*     */     }
/* 726 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
/* 734 */     this.ePackage = null;
/* 735 */     super.eBasicSetContainer(newContainer, newContainerFeatureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage basicGetEPackage() {
/* 745 */     if (eContainerFeatureID() != 6) return null; 
/* 746 */     return (EPackage)eInternalContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<ETypeParameter> getETypeParameters() {
/* 756 */     if (this.eTypeParameters == null)
/*     */     {
/* 758 */       this.eTypeParameters = (EList<ETypeParameter>)new EObjectContainmentEList.Resolving(ETypeParameter.class, this, 7);
/*     */     }
/* 760 */     return this.eTypeParameters;
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
/*     */   public String toString() {
/* 773 */     if (eIsProxy()) return super.toString();
/*     */     
/* 775 */     StringBuffer result = new StringBuffer(super.toString());
/* 776 */     result.append(" (instanceClassName: ");
/* 777 */     result.append(this.instanceClassName);
/* 778 */     result.append(')');
/* 779 */     return result.toString();
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
/* 790 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 793 */         return getEAnnotations();
/*     */       case 1:
/* 795 */         return getName();
/*     */       case 2:
/* 797 */         return getInstanceClassName();
/*     */       case 3:
/* 799 */         return getInstanceClass();
/*     */       case 4:
/* 801 */         return getDefaultValue();
/*     */       case 5:
/* 803 */         return getInstanceTypeName();
/*     */       case 6:
/* 805 */         if (resolve) return getEPackage(); 
/* 806 */         return basicGetEPackage();
/*     */       case 7:
/* 808 */         return getETypeParameters();
/*     */     } 
/* 810 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 822 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 825 */         getEAnnotations().clear();
/* 826 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 829 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 832 */         setInstanceClassName((String)newValue);
/*     */         return;
/*     */       case 5:
/* 835 */         setInstanceTypeName((String)newValue);
/*     */         return;
/*     */       case 7:
/* 838 */         getETypeParameters().clear();
/* 839 */         getETypeParameters().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 842 */     eDynamicSet(featureID, newValue);
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
/* 853 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 856 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 859 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 862 */         unsetInstanceClassName();
/*     */         return;
/*     */       case 5:
/* 865 */         unsetInstanceTypeName();
/*     */         return;
/*     */       case 7:
/* 868 */         getETypeParameters().clear();
/*     */         return;
/*     */     } 
/* 871 */     eDynamicUnset(featureID);
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
/* 882 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 885 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 887 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 889 */         return isSetInstanceClassName();
/*     */       case 3:
/* 891 */         return (getInstanceClass() != null);
/*     */       case 4:
/* 893 */         return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));
/*     */       case 5:
/* 895 */         return isSetInstanceTypeName();
/*     */       case 6:
/* 897 */         return (basicGetEPackage() != null);
/*     */       case 7:
/* 899 */         return (this.eTypeParameters != null && !this.eTypeParameters.isEmpty());
/*     */     } 
/* 901 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 912 */     switch (operationID) {
/*     */       
/*     */       case 0:
/* 915 */         return getEAnnotation((String)arguments.get(0));
/*     */       case 1:
/* 917 */         return Boolean.valueOf(isInstance(arguments.get(0)));
/*     */       case 2:
/* 919 */         return Integer.valueOf(getClassifierID());
/*     */     } 
/* 921 */     return eDynamicInvoke(operationID, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicExtendedMetaData.EClassifierExtendedMetaData getExtendedMetaData() {
/* 928 */     return this.eClassifierExtendedMetaData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtendedMetaData(BasicExtendedMetaData.EClassifierExtendedMetaData eClassifierExtendedMetaData) {
/* 933 */     this.eClassifierExtendedMetaData = eClassifierExtendedMetaData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 939 */     if (this.eContainer instanceof EPackageImpl)
/*     */     {
/* 941 */       ((EPackageImpl)this.eContainer).eNameToEClassifierMap = null;
/*     */     }
/* 943 */     super.setName(newName);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EClassifierImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */