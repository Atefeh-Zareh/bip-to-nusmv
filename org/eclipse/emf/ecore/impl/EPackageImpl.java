/*      */ package org.eclipse.emf.ecore.impl;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.CommonPlugin;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.Enumerator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EAnnotation;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EEnum;
/*      */ import org.eclipse.emf.ecore.EEnumLiteral;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EGenericType;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EParameter;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.ETypeParameter;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
/*      */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
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
/*      */ public class EPackageImpl
/*      */   extends ENamedElementImpl
/*      */   implements EPackage, BasicExtendedMetaData.EPackageExtendedMetaData.Holder
/*      */ {
/*   92 */   protected static final String NS_URI_EDEFAULT = null;
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
/*      */   protected EcoreFactory ecoreFactory;
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
/*      */   protected EcorePackage ecorePackage;
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
/*      */   protected Map<String, EClassifier> eNameToEClassifierMap;
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
/*      */   protected String nsURI;
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
/*      */   protected EPackageImpl()
/*      */   {
/*  262 */     this.nsURI = NS_URI_EDEFAULT;
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
/*  282 */     this.nsPrefix = NS_PREFIX_EDEFAULT; setEFactoryInstance(new EFactoryImpl()); this.ecorePackage = EcorePackage.eINSTANCE; this.ecoreFactory = EcoreFactory.eINSTANCE; } protected EPackageImpl(EFactory eFactory) { this.nsURI = NS_URI_EDEFAULT; this.nsPrefix = NS_PREFIX_EDEFAULT; setEFactoryInstance(eFactory); this.ecorePackage = EcorePackage.eINSTANCE; this.ecoreFactory = EcoreFactory.eINSTANCE; } protected EPackageImpl(String packageURI) { this(packageURI, new EFactoryImpl()); } protected EPackageImpl(String packageURI, final EFactory factory) { this.nsURI = NS_URI_EDEFAULT; this.nsPrefix = NS_PREFIX_EDEFAULT;
/*      */     Object registration = EPackage.Registry.INSTANCE.get(packageURI);
/*      */     if (registration instanceof EPackage.Descriptor) {
/*      */       final EPackage.Descriptor descriptor = (EPackage.Descriptor)registration;
/*      */       final long threadId = Thread.currentThread().getId();
/*      */       EPackage.Registry.INSTANCE.put(packageURI, new EPackage.Descriptor()
/*      */           {
/*      */             public EPackage getEPackage() { return (Thread.currentThread().getId() == threadId) ? EPackageImpl.this : descriptor.getEPackage(); } public EFactory getEFactory() { return (Thread.currentThread().getId() == threadId) ? factory : descriptor.getEFactory(); }
/*      */           });
/*      */     } else {
/*      */       EPackage.Registry.INSTANCE.put(packageURI, this);
/*      */     } 
/*      */     setEFactoryInstance(factory);
/*      */     if (factory == EcoreFactory.eINSTANCE) {
/*      */       this.ecorePackage = (EcorePackage)this;
/*      */       this.ecoreFactory = (EcoreFactory)factory;
/*      */     } else {
/*      */       this.ecorePackage = EcorePackage.eINSTANCE;
/*      */       this.ecoreFactory = EcoreFactory.eINSTANCE;
/*      */     }  }
/*      */    public void freeze() {
/*      */     if (this.eClassifiers != null)
/*      */       for (int i = 0, size = this.eClassifiers.size(); i < size; i++)
/*      */         freeze(this.eClassifiers.get(i));  
/*      */     if (this.eSubpackages != null)
/*      */       for (int i = 0, size = this.eSubpackages.size(); i < size; i++)
/*      */         freeze(this.eSubpackages.get(i));  
/*      */     super.freeze();
/*      */   } public void eSetProxyURI(URI uri) {
/*      */     if (uri != null && this.eClassifiers != null)
/*      */       for (EClassifier eClassifier : this.eClassifiers) {
/*      */         if (eClassifier instanceof EClassifierImpl)
/*      */           ((EClassifierImpl)eClassifier).ePackage = null; 
/*      */       }  
/*      */     super.eSetProxyURI(uri);
/*      */   } protected EClass eStaticClass() {
/*      */     return EcorePackage.Literals.EPACKAGE;
/*      */   } @Deprecated
/*      */   public void setNamespaceURI(String nsURI) {} protected static final String NS_PREFIX_EDEFAULT = null; protected String nsPrefix; public String getNsURI() {
/*  321 */     return this.nsURI;
/*      */   }
/*      */   protected EFactory eFactoryInstance; protected EList<EClassifier> eClassifiers; protected EList<EPackage> eSubpackages; private static Resource.Factory resourceFactory; protected static final boolean IS_ABSTRACT = true; protected static final boolean IS_INTERFACE = true; protected static final boolean IS_GENERATED_INSTANCE_CLASS = true; protected static final boolean IS_SERIALIZABLE = true; protected static final boolean IS_DERIVED = true; protected static final boolean IS_TRANSIENT = true; protected static final boolean IS_VOLATILE = true; protected static final boolean IS_CHANGEABLE = true; protected static final boolean IS_UNSETTABLE = true; protected static final boolean IS_UNIQUE = true; protected static final boolean IS_ID = true;
/*      */   protected static final boolean IS_ORDERED = true;
/*      */   protected static final boolean IS_COMPOSITE = true;
/*      */   protected static final boolean IS_RESOLVE_PROXIES = true;
/*      */   protected static final boolean IS_RESOLVABLE = true;
/*      */   protected BasicExtendedMetaData.EPackageExtendedMetaData ePackageExtendedMetaData;
/*      */   
/*      */   public void setNsURI(String newNsURI) {
/*  331 */     String oldNsURI = this.nsURI;
/*  332 */     this.nsURI = newNsURI;
/*  333 */     if (eNotificationRequired()) {
/*  334 */       eNotify((Notification)new ENotificationImpl(this, 1, 2, oldNsURI, this.nsURI));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNsPrefix() {
/*  344 */     return this.nsPrefix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNsPrefix(String newNsPrefix) {
/*  354 */     String oldNsPrefix = this.nsPrefix;
/*  355 */     this.nsPrefix = newNsPrefix;
/*  356 */     if (eNotificationRequired()) {
/*  357 */       eNotify((Notification)new ENotificationImpl(this, 1, 3, oldNsPrefix, this.nsPrefix));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EFactory getEFactoryInstance() {
/*  367 */     return this.eFactoryInstance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEFactoryInstance(EFactory newEFactoryInstance) {
/*  377 */     if (newEFactoryInstance != this.eFactoryInstance) {
/*      */       
/*  379 */       NotificationChain msgs = null;
/*  380 */       if (this.eFactoryInstance != null)
/*  381 */         msgs = ((InternalEObject)this.eFactoryInstance).eInverseRemove(this, 1, EFactory.class, msgs); 
/*  382 */       if (newEFactoryInstance != null)
/*  383 */         msgs = ((InternalEObject)newEFactoryInstance).eInverseAdd(this, 1, EFactory.class, msgs); 
/*  384 */       msgs = basicSetEFactoryInstance(newEFactoryInstance, msgs);
/*  385 */       if (msgs != null) msgs.dispatch();
/*      */     
/*  387 */     } else if (eNotificationRequired()) {
/*  388 */       eNotify((Notification)new ENotificationImpl(this, 1, 4, newEFactoryInstance, newEFactoryInstance));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain basicSetEFactoryInstance(EFactory newEFactoryInstance, NotificationChain msgs) {
/*      */     ENotificationImpl eNotificationImpl;
/*  398 */     EFactory oldEFactoryInstance = this.eFactoryInstance;
/*  399 */     this.eFactoryInstance = newEFactoryInstance;
/*  400 */     if (eNotificationRequired()) {
/*      */       
/*  402 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 4, oldEFactoryInstance, newEFactoryInstance);
/*  403 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*      */     
/*  405 */     }  return (NotificationChain)eNotificationImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EClassifier> getEClassifiers() {
/*  415 */     if (this.eClassifiers == null)
/*      */     {
/*  417 */       this.eClassifiers = 
/*  418 */         (EList<EClassifier>)new EObjectContainmentWithInverseEList.Resolving<EClassifier>(
/*  419 */           EClassifier.class, this, 5, 6)
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           protected void didChange() {
/*  426 */             EPackageImpl.this.eNameToEClassifierMap = null;
/*      */           }
/*      */         };
/*      */     }
/*  430 */     return this.eClassifiers;
/*      */   }
/*      */ 
/*      */   
/*      */   public EClassifier getEClassifier(String name) {
/*  435 */     return getEClassifierGen(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EClassifier getEClassifierGen(String name) {
/*  443 */     if (this.eNameToEClassifierMap == null) {
/*      */       
/*  445 */       EList<EClassifier> eList = getEClassifiers();
/*  446 */       Map<String, EClassifier> result = new HashMap<String, EClassifier>(eList.size());
/*  447 */       for (EClassifier eClassifier : eList)
/*      */       {
/*  449 */         result.put(eClassifier.getName(), eClassifier);
/*      */       }
/*  451 */       this.eNameToEClassifierMap = result;
/*      */     } 
/*      */     
/*  454 */     return this.eNameToEClassifierMap.get(name);
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
/*      */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  466 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  469 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*      */       case 4:
/*  471 */         if (this.eFactoryInstance != null)
/*  472 */           msgs = ((InternalEObject)this.eFactoryInstance).eInverseRemove(this, 1, EFactory.class, msgs); 
/*  473 */         return basicSetEFactoryInstance((EFactory)otherEnd, msgs);
/*      */       case 5:
/*  475 */         return ((InternalEList)getEClassifiers()).basicAdd(otherEnd, msgs);
/*      */       case 6:
/*  477 */         return ((InternalEList)getESubpackages()).basicAdd(otherEnd, msgs);
/*      */       case 7:
/*  479 */         if (eInternalContainer() != null)
/*  480 */           msgs = eBasicRemoveFromContainer(msgs); 
/*  481 */         return eBasicSetContainer(otherEnd, 7, msgs);
/*      */     } 
/*  483 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  494 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  497 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*      */       case 4:
/*  499 */         return basicSetEFactoryInstance((EFactory)null, msgs);
/*      */       case 5:
/*  501 */         return ((InternalEList)getEClassifiers()).basicRemove(otherEnd, msgs);
/*      */       case 6:
/*  503 */         return ((InternalEList)getESubpackages()).basicRemove(otherEnd, msgs);
/*      */       case 7:
/*  505 */         return eBasicSetContainer((InternalEObject)null, 7, msgs);
/*      */     } 
/*  507 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/*  518 */     switch (eContainerFeatureID()) {
/*      */       
/*      */       case 7:
/*  521 */         return eInternalContainer().eInverseRemove(this, 6, EPackage.class, msgs);
/*      */     } 
/*  523 */     return eDynamicBasicRemoveFromContainer(msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EPackage> getESubpackages() {
/*  533 */     if (this.eSubpackages == null)
/*      */     {
/*  535 */       this.eSubpackages = (EList<EPackage>)new EObjectContainmentWithInverseEList.Resolving(EPackage.class, this, 6, 7);
/*      */     }
/*  537 */     return this.eSubpackages;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EPackage getESuperPackage() {
/*  547 */     return (eContainerFeatureID() == 7) ? (EPackage)this.eContainer : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EPackage basicGetESuperPackage() {
/*  558 */     if (eContainerFeatureID() != 7) return null; 
/*  559 */     return (EPackage)eInternalContainer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/*  570 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  573 */         return getEAnnotations();
/*      */       case 1:
/*  575 */         return getName();
/*      */       case 2:
/*  577 */         return getNsURI();
/*      */       case 3:
/*  579 */         return getNsPrefix();
/*      */       case 4:
/*  581 */         return getEFactoryInstance();
/*      */       case 5:
/*  583 */         return getEClassifiers();
/*      */       case 6:
/*  585 */         return getESubpackages();
/*      */       case 7:
/*  587 */         if (resolve) return getESuperPackage(); 
/*  588 */         return basicGetESuperPackage();
/*      */     } 
/*  590 */     return eDynamicGet(featureID, resolve, coreType);
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
/*      */   public void eSet(int featureID, Object newValue) {
/*  602 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  605 */         getEAnnotations().clear();
/*  606 */         getEAnnotations().addAll((Collection)newValue);
/*      */         return;
/*      */       case 1:
/*  609 */         setName((String)newValue);
/*      */         return;
/*      */       case 2:
/*  612 */         setNsURI((String)newValue);
/*      */         return;
/*      */       case 3:
/*  615 */         setNsPrefix((String)newValue);
/*      */         return;
/*      */       case 4:
/*  618 */         setEFactoryInstance((EFactory)newValue);
/*      */         return;
/*      */       case 5:
/*  621 */         getEClassifiers().clear();
/*  622 */         getEClassifiers().addAll((Collection)newValue);
/*      */         return;
/*      */       case 6:
/*  625 */         getESubpackages().clear();
/*  626 */         getESubpackages().addAll((Collection)newValue);
/*      */         return;
/*      */     } 
/*  629 */     eDynamicSet(featureID, newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void eUnset(int featureID) {
/*  640 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  643 */         getEAnnotations().clear();
/*      */         return;
/*      */       case 1:
/*  646 */         setName(NAME_EDEFAULT);
/*      */         return;
/*      */       case 2:
/*  649 */         setNsURI(NS_URI_EDEFAULT);
/*      */         return;
/*      */       case 3:
/*  652 */         setNsPrefix(NS_PREFIX_EDEFAULT);
/*      */         return;
/*      */       case 4:
/*  655 */         setEFactoryInstance((EFactory)null);
/*      */         return;
/*      */       case 5:
/*  658 */         getEClassifiers().clear();
/*      */         return;
/*      */       case 6:
/*  661 */         getESubpackages().clear();
/*      */         return;
/*      */     } 
/*  664 */     eDynamicUnset(featureID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eIsSet(int featureID) {
/*  675 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  678 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*      */       case 1:
/*  680 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*      */       case 2:
/*  682 */         return (NS_URI_EDEFAULT == null) ? ((this.nsURI != null)) : (!NS_URI_EDEFAULT.equals(this.nsURI));
/*      */       case 3:
/*  684 */         return (NS_PREFIX_EDEFAULT == null) ? ((this.nsPrefix != null)) : (!NS_PREFIX_EDEFAULT.equals(this.nsPrefix));
/*      */       case 4:
/*  686 */         return (this.eFactoryInstance != null);
/*      */       case 5:
/*  688 */         return (this.eClassifiers != null && !this.eClassifiers.isEmpty());
/*      */       case 6:
/*  690 */         return (this.eSubpackages != null && !this.eSubpackages.isEmpty());
/*      */       case 7:
/*  692 */         return (basicGetESuperPackage() != null);
/*      */     } 
/*  694 */     return eDynamicIsSet(featureID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/*  705 */     switch (operationID) {
/*      */       
/*      */       case 0:
/*  708 */         return getEAnnotation((String)arguments.get(0));
/*      */       case 1:
/*  710 */         return getEClassifier((String)arguments.get(0));
/*      */     } 
/*  712 */     return eDynamicInvoke(operationID, arguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  723 */     if (eIsProxy()) return super.toString();
/*      */     
/*  725 */     StringBuffer result = new StringBuffer(super.toString());
/*  726 */     result.append(" (nsURI: ");
/*  727 */     result.append(this.nsURI);
/*  728 */     result.append(", nsPrefix: ");
/*  729 */     result.append(this.nsPrefix);
/*  730 */     result.append(')');
/*  731 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Resource createResource(String uri) {
/*  738 */     Resource resource = eResource();
/*  739 */     if (resource == null) {
/*      */       
/*  741 */       if (resourceFactory == null) {
/*      */         
/*      */         try {
/*      */           
/*  745 */           resourceFactory = CommonPlugin.loadClass("org.eclipse.emf.ecore.xmi", "org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl").newInstance();
/*      */         }
/*  747 */         catch (Throwable exception) {
/*      */           
/*  749 */           resourceFactory = (Resource.Factory)new ResourceFactoryImpl();
/*      */         } 
/*      */       }
/*  752 */       URI actualURI = URI.createURI(uri);
/*  753 */       resource = resourceFactory.createResource(actualURI);
/*  754 */       resource.getContents().add(this);
/*      */     } 
/*  756 */     return resource;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EClass createEClass(int id) {
/*  761 */     EClassImpl c = (EClassImpl)this.ecoreFactory.createEClass();
/*  762 */     c.setClassifierID(id);
/*  763 */     getEClassifiers().add(c);
/*  764 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EEnum createEEnum(int id) {
/*  769 */     EEnumImpl e = (EEnumImpl)this.ecoreFactory.createEEnum();
/*  770 */     e.setClassifierID(id);
/*  771 */     getEClassifiers().add(e);
/*  772 */     return e;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EDataType createEDataType(int id) {
/*  777 */     EDataTypeImpl d = (EDataTypeImpl)this.ecoreFactory.createEDataType();
/*  778 */     d.setClassifierID(id);
/*  779 */     getEClassifiers().add(d);
/*  780 */     return d;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createEAttribute(EClass owner, int id) {
/*  785 */     EAttributeImpl a = (EAttributeImpl)this.ecoreFactory.createEAttribute();
/*  786 */     a.setFeatureID(id);
/*  787 */     owner.getEStructuralFeatures().add(a);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createEReference(EClass owner, int id) {
/*  792 */     EReferenceImpl r = (EReferenceImpl)this.ecoreFactory.createEReference();
/*  793 */     r.setFeatureID(id);
/*  794 */     owner.getEStructuralFeatures().add(r);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createEOperation(EClass owner, int id) {
/*  802 */     EOperationImpl o = (EOperationImpl)this.ecoreFactory.createEOperation();
/*  803 */     o.setOperationID(id);
/*  804 */     owner.getEOperations().add(o);
/*      */   }
/*      */ 
/*      */   
/*      */   protected ETypeParameter addETypeParameter(EClassifier owner, String name) {
/*  809 */     ETypeParameter eTypeParameter = this.ecoreFactory.createETypeParameter();
/*  810 */     eTypeParameter.setName(name);
/*  811 */     owner.getETypeParameters().add(eTypeParameter);
/*  812 */     return eTypeParameter;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ETypeParameter addETypeParameter(EOperation owner, String name) {
/*  817 */     ETypeParameter eTypeParameter = this.ecoreFactory.createETypeParameter();
/*  818 */     eTypeParameter.setName(name);
/*  819 */     owner.getETypeParameters().add(eTypeParameter);
/*  820 */     return eTypeParameter;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EGenericType createEGenericType() {
/*  825 */     EGenericType eGenericType = this.ecoreFactory.createEGenericType();
/*  826 */     return eGenericType;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EGenericType createEGenericType(ETypeParameter eTypeParameter) {
/*  831 */     EGenericType eGenericType = this.ecoreFactory.createEGenericType();
/*  832 */     eGenericType.setETypeParameter(eTypeParameter);
/*  833 */     return eGenericType;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EGenericType createEGenericType(EClassifier eClassifier) {
/*  838 */     EGenericType eGenericType = this.ecoreFactory.createEGenericType();
/*  839 */     eGenericType.setEClassifier(eClassifier);
/*  840 */     return eGenericType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract, boolean isInterface) {
/*  849 */     initEClassifier((EClassifier)c, this.ecorePackage.getEClass(), instanceClass, name);
/*  850 */     c.setAbstract(isAbstract);
/*  851 */     c.setInterface(isInterface);
/*  852 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract, boolean isInterface, boolean isGenerated) {
/*  857 */     initEClassifier((EClassifier)c, this.ecorePackage.getEClass(), instanceClass, name, isGenerated);
/*  858 */     c.setAbstract(isAbstract);
/*  859 */     c.setInterface(isInterface);
/*  860 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract, boolean isInterface, boolean isGenerated, String instanceTypeName) {
/*  866 */     initEClass(c, instanceClass, name, isAbstract, isInterface, isGenerated);
/*  867 */     if (instanceTypeName != null)
/*      */     {
/*  869 */       setInstanceTypeName((EClassifier)c, instanceTypeName);
/*      */     }
/*  871 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EEnum initEEnum(EEnum e, Class<?> instanceClass, String name) {
/*  876 */     initEClassifier((EClassifier)e, this.ecorePackage.getEEnum(), instanceClass, name, true);
/*  877 */     return e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EDataType initEDataType(EDataType d, Class<?> instanceClass, String name, boolean isSerializable) {
/*  884 */     initEClassifier((EClassifier)d, this.ecorePackage.getEDataType(), instanceClass, name, false);
/*  885 */     d.setSerializable(isSerializable);
/*  886 */     return d;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EDataType initEDataType(EDataType d, Class<?> instanceClass, String name, boolean isSerializable, boolean isGenerated) {
/*  891 */     initEClassifier((EClassifier)d, this.ecorePackage.getEDataType(), instanceClass, name, isGenerated);
/*  892 */     d.setSerializable(isSerializable);
/*  893 */     return d;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EDataType initEDataType(EDataType d, Class<?> instanceClass, String name, boolean isSerializable, boolean isGenerated, String instanceTypeName) {
/*  899 */     initEDataType(d, instanceClass, name, isSerializable, isGenerated);
/*  900 */     if (instanceTypeName != null)
/*      */     {
/*  902 */       setInstanceTypeName((EClassifier)d, instanceTypeName);
/*      */     }
/*  904 */     return d;
/*      */   }
/*      */ 
/*      */   
/*      */   private void initEClassifier(EClassifier o, EClass metaObject, Class<?> instanceClass, String name) {
/*  909 */     o.setName(name);
/*  910 */     if (instanceClass != null)
/*      */     {
/*  912 */       o.setInstanceClass(instanceClass);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void initEClassifier(EClassifier o, EClass metaObject, Class<?> instanceClass, String name, boolean isGenerated) {
/*  918 */     o.setName(name);
/*  919 */     if (instanceClass != null)
/*      */     {
/*  921 */       o.setInstanceClass(instanceClass);
/*      */     }
/*  923 */     if (isGenerated)
/*      */     {
/*  925 */       setGeneratedClassName(o);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setGeneratedClassName(EClassifier eClassifier) {
/*  931 */     ((EClassifierImpl)eClassifier).setGeneratedInstanceClass(true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setInstanceTypeName(EClassifier eClassifier, String instanceTypeName) {
/*  936 */     ((EClassifierImpl)eClassifier).basicSetInstanceTypeName(instanceTypeName);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable) {
/*  964 */     return 
/*  965 */       initEAttribute(
/*  966 */         a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, false, true);
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
/*      */   @Deprecated
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID) {
/*  986 */     return 
/*  987 */       initEAttribute(
/*  988 */         a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, isID, true);
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
/*      */   @Deprecated
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique) {
/* 1009 */     return 
/* 1010 */       initEAttribute(
/* 1011 */         a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, isID, isUnique, false);
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
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived) {
/* 1029 */     return 
/* 1030 */       initEAttribute(
/* 1031 */         a, 
/* 1032 */         type, 
/* 1033 */         name, 
/* 1034 */         defaultValue, 
/* 1035 */         lowerBound, 
/* 1036 */         upperBound, 
/* 1037 */         isTransient, 
/* 1038 */         isVolatile, 
/* 1039 */         isChangeable, 
/* 1040 */         isUnsettable, 
/* 1041 */         isID, 
/* 1042 */         isUnique, 
/* 1043 */         isDerived, 
/* 1044 */         true);
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
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1063 */     initEAttribute(
/* 1064 */         a, 
/* 1065 */         type, 
/* 1066 */         name, 
/* 1067 */         defaultValue, 
/* 1068 */         lowerBound, 
/* 1069 */         upperBound, (
/* 1070 */         (EClassifier)a.eContainer()).getInstanceClass(), 
/* 1071 */         isTransient, 
/* 1072 */         isVolatile, 
/* 1073 */         isChangeable, 
/* 1074 */         isUnsettable, 
/* 1075 */         isID, 
/* 1076 */         isUnique, 
/* 1077 */         isDerived, 
/* 1078 */         isOrdered);
/* 1079 */     return a;
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
/*      */   protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1099 */     initEStructuralFeature(
/* 1100 */         (EStructuralFeature)a, 
/* 1101 */         type, 
/* 1102 */         name, 
/* 1103 */         defaultValue, 
/* 1104 */         lowerBound, 
/* 1105 */         upperBound, 
/* 1106 */         containerClass, 
/* 1107 */         isTransient, 
/* 1108 */         isVolatile, 
/* 1109 */         isChangeable, 
/* 1110 */         isUnsettable, 
/* 1111 */         isUnique, 
/* 1112 */         isDerived, 
/* 1113 */         isOrdered);
/* 1114 */     a.setID(isID);
/* 1115 */     return a;
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
/*      */   protected EAttribute initEAttribute(EAttribute a, EGenericType type, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1135 */     initEStructuralFeature(
/* 1136 */         (EStructuralFeature)a, 
/* 1137 */         type, 
/* 1138 */         name, 
/* 1139 */         defaultValue, 
/* 1140 */         lowerBound, 
/* 1141 */         upperBound, 
/* 1142 */         containerClass, 
/* 1143 */         isTransient, 
/* 1144 */         isVolatile, 
/* 1145 */         isChangeable, 
/* 1146 */         isUnsettable, 
/* 1147 */         isUnique, 
/* 1148 */         isDerived, 
/* 1149 */         isOrdered);
/* 1150 */     a.setID(isID);
/* 1151 */     return a;
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
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies) {
/* 1176 */     initEReference(
/* 1177 */         r, 
/* 1178 */         type, 
/* 1179 */         otherEnd, 
/* 1180 */         name, 
/* 1181 */         defaultValue, 
/* 1182 */         lowerBound, 
/* 1183 */         upperBound, 
/* 1184 */         isTransient, 
/* 1185 */         isVolatile, 
/* 1186 */         isChangeable, 
/* 1187 */         isContainment, 
/* 1188 */         isResolveProxies, 
/* 1189 */         false, 
/* 1190 */         true);
/* 1191 */     return r;
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
/*      */   @Deprecated
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable) {
/* 1213 */     initEReference(
/* 1214 */         r, 
/* 1215 */         type, 
/* 1216 */         otherEnd, 
/* 1217 */         name, 
/* 1218 */         defaultValue, 
/* 1219 */         lowerBound, 
/* 1220 */         upperBound, 
/* 1221 */         isTransient, 
/* 1222 */         isVolatile, 
/* 1223 */         isChangeable, 
/* 1224 */         isContainment, 
/* 1225 */         isResolveProxies, 
/* 1226 */         isUnsettable, 
/* 1227 */         true);
/* 1228 */     return r;
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
/*      */   @Deprecated
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable, boolean isUnique) {
/* 1251 */     initEReference(
/* 1252 */         r, 
/* 1253 */         type, 
/* 1254 */         otherEnd, 
/* 1255 */         name, 
/* 1256 */         defaultValue, 
/* 1257 */         lowerBound, 
/* 1258 */         upperBound, 
/* 1259 */         isTransient, 
/* 1260 */         isVolatile, 
/* 1261 */         isChangeable, 
/* 1262 */         isContainment, 
/* 1263 */         isResolveProxies, 
/* 1264 */         isUnsettable, 
/* 1265 */         isUnique, 
/* 1266 */         false);
/* 1267 */     return r;
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
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable, boolean isUnique, boolean isDerived) {
/* 1287 */     initEReference(
/* 1288 */         r, 
/* 1289 */         type, 
/* 1290 */         otherEnd, 
/* 1291 */         name, 
/* 1292 */         defaultValue, 
/* 1293 */         lowerBound, 
/* 1294 */         upperBound, 
/* 1295 */         isTransient, 
/* 1296 */         isVolatile, 
/* 1297 */         isChangeable, 
/* 1298 */         isContainment, 
/* 1299 */         isResolveProxies, 
/* 1300 */         isUnsettable, 
/* 1301 */         isUnique, 
/* 1302 */         isDerived, 
/* 1303 */         true);
/* 1304 */     return r;
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
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1325 */     initEReference(
/* 1326 */         r, 
/* 1327 */         type, 
/* 1328 */         otherEnd, 
/* 1329 */         name, 
/* 1330 */         defaultValue, 
/* 1331 */         lowerBound, 
/* 1332 */         upperBound, (
/* 1333 */         (EClassifier)r.eContainer()).getInstanceClass(), 
/* 1334 */         isTransient, 
/* 1335 */         isVolatile, 
/* 1336 */         isChangeable, 
/* 1337 */         isContainment, 
/* 1338 */         isResolveProxies, 
/* 1339 */         isUnsettable, 
/* 1340 */         isUnique, 
/* 1341 */         isDerived, 
/* 1342 */         isOrdered);
/* 1343 */     return r;
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
/*      */   protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1365 */     initEStructuralFeature(
/* 1366 */         (EStructuralFeature)r, 
/* 1367 */         type, 
/* 1368 */         name, 
/* 1369 */         defaultValue, 
/* 1370 */         lowerBound, 
/* 1371 */         upperBound, 
/* 1372 */         containerClass, 
/* 1373 */         isTransient, 
/* 1374 */         isVolatile, 
/* 1375 */         isChangeable, 
/* 1376 */         isUnsettable, 
/* 1377 */         isUnique, 
/* 1378 */         isDerived, 
/* 1379 */         isOrdered);
/* 1380 */     r.setContainment(isContainment);
/* 1381 */     if (otherEnd != null)
/*      */     {
/* 1383 */       r.setEOpposite(otherEnd);
/*      */     }
/* 1385 */     r.setResolveProxies(isResolveProxies);
/* 1386 */     return r;
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
/*      */   protected EReference initEReference(EReference r, EGenericType type, EReference otherEnd, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1408 */     initEStructuralFeature(
/* 1409 */         (EStructuralFeature)r, 
/* 1410 */         type, 
/* 1411 */         name, 
/* 1412 */         defaultValue, 
/* 1413 */         lowerBound, 
/* 1414 */         upperBound, 
/* 1415 */         containerClass, 
/* 1416 */         isTransient, 
/* 1417 */         isVolatile, 
/* 1418 */         isChangeable, 
/* 1419 */         isUnsettable, 
/* 1420 */         isUnique, 
/* 1421 */         isDerived, 
/* 1422 */         isOrdered);
/* 1423 */     r.setContainment(isContainment);
/* 1424 */     if (otherEnd != null)
/*      */     {
/* 1426 */       r.setEOpposite(otherEnd);
/*      */     }
/* 1428 */     r.setResolveProxies(isResolveProxies);
/* 1429 */     return r;
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
/*      */   private void initEStructuralFeature(EStructuralFeature s, EClassifier type, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1448 */     s.setName(name);
/* 1449 */     ((EStructuralFeatureImpl)s).setContainerClass(containerClass);
/* 1450 */     s.setTransient(isTransient);
/* 1451 */     s.setVolatile(isVolatile);
/* 1452 */     s.setChangeable(isChangeable);
/* 1453 */     s.setUnsettable(isUnsettable);
/* 1454 */     s.setUnique(isUnique);
/* 1455 */     s.setDerived(isDerived);
/* 1456 */     s.setOrdered(isOrdered);
/* 1457 */     s.setLowerBound(lowerBound);
/* 1458 */     s.setUpperBound(upperBound);
/* 1459 */     s.setEType(type);
/* 1460 */     if (defaultValue != null)
/*      */     {
/* 1462 */       s.setDefaultValueLiteral(defaultValue);
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
/*      */   private void initEStructuralFeature(EStructuralFeature s, EGenericType type, String name, String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile, boolean isChangeable, boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
/* 1482 */     s.setName(name);
/* 1483 */     ((EStructuralFeatureImpl)s).setContainerClass(containerClass);
/* 1484 */     s.setTransient(isTransient);
/* 1485 */     s.setVolatile(isVolatile);
/* 1486 */     s.setChangeable(isChangeable);
/* 1487 */     s.setUnsettable(isUnsettable);
/* 1488 */     s.setUnique(isUnique);
/* 1489 */     s.setDerived(isDerived);
/* 1490 */     s.setOrdered(isOrdered);
/* 1491 */     s.setLowerBound(lowerBound);
/* 1492 */     s.setUpperBound(upperBound);
/* 1493 */     s.setEGenericType(type);
/* 1494 */     if (defaultValue != null)
/*      */     {
/* 1496 */       s.setDefaultValueLiteral(defaultValue);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EOperation addEOperation(EClass owner, EClassifier type, String name) {
/* 1502 */     EOperation o = this.ecoreFactory.createEOperation();
/* 1503 */     initEOperation(o, type, name);
/* 1504 */     owner.getEOperations().add(o);
/* 1505 */     return o;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EOperation addEOperation(EClass owner, EClassifier type, String name, int lowerBound, int upperBound) {
/* 1510 */     EOperation o = this.ecoreFactory.createEOperation();
/* 1511 */     initEOperation(o, type, name, lowerBound, upperBound);
/* 1512 */     owner.getEOperations().add(o);
/* 1513 */     return o;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EOperation addEOperation(EClass owner, EClassifier type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered) {
/* 1518 */     EOperation o = this.ecoreFactory.createEOperation();
/* 1519 */     initEOperation(o, type, name, lowerBound, upperBound, isUnique, isOrdered);
/* 1520 */     owner.getEOperations().add(o);
/* 1521 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EOperation initEOperation(EOperation eOperation, EClassifier type, String name) {
/* 1529 */     eOperation.setEType(type);
/* 1530 */     eOperation.setName(name);
/* 1531 */     return eOperation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EOperation initEOperation(EOperation eOperation, EClassifier type, String name, int lowerBound, int upperBound) {
/* 1539 */     initEOperation(eOperation, type, name);
/* 1540 */     eOperation.setLowerBound(lowerBound);
/* 1541 */     eOperation.setUpperBound(upperBound);
/* 1542 */     return eOperation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EOperation initEOperation(EOperation eOperation, EClassifier type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered) {
/* 1550 */     initEOperation(eOperation, type, name, lowerBound, upperBound);
/* 1551 */     eOperation.setUnique(isUnique);
/* 1552 */     eOperation.setOrdered(isOrdered);
/* 1553 */     return eOperation;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initEOperation(EOperation eOperation, EGenericType eGenericType) {
/* 1558 */     eOperation.setEGenericType(eGenericType);
/*      */   }
/*      */ 
/*      */   
/*      */   private EParameter internalAddEParameter(EOperation owner, EClassifier type, String name) {
/* 1563 */     EParameter p = this.ecoreFactory.createEParameter();
/* 1564 */     p.setEType(type);
/* 1565 */     p.setName(name);
/* 1566 */     owner.getEParameters().add(p);
/* 1567 */     return p;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEParameter(EOperation owner, EClassifier type, String name) {
/* 1572 */     internalAddEParameter(owner, type, name);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEParameter(EOperation owner, EClassifier type, String name, int lowerBound, int upperBound) {
/* 1577 */     EParameter p = internalAddEParameter(owner, type, name);
/* 1578 */     p.setLowerBound(lowerBound);
/* 1579 */     p.setUpperBound(upperBound);
/*      */   }
/*      */ 
/*      */   
/*      */   protected EParameter addEParameter(EOperation owner, EClassifier type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered) {
/* 1584 */     EParameter p = internalAddEParameter(owner, type, name);
/* 1585 */     p.setLowerBound(lowerBound);
/* 1586 */     p.setUpperBound(upperBound);
/* 1587 */     p.setUnique(isUnique);
/* 1588 */     p.setOrdered(isOrdered);
/* 1589 */     return p;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected void addEParameter(EOperation owner, EGenericType type, String name, int lowerBound, int upperBound) {
/* 1595 */     EParameter p = this.ecoreFactory.createEParameter();
/* 1596 */     p.setEGenericType(type);
/* 1597 */     p.setName(name);
/* 1598 */     owner.getEParameters().add(p);
/* 1599 */     p.setLowerBound(lowerBound);
/* 1600 */     p.setUpperBound(upperBound);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEParameter(EOperation owner, EGenericType type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered) {
/* 1605 */     EParameter p = this.ecoreFactory.createEParameter();
/* 1606 */     p.setEGenericType(type);
/* 1607 */     p.setName(name);
/* 1608 */     owner.getEParameters().add(p);
/* 1609 */     p.setLowerBound(lowerBound);
/* 1610 */     p.setUpperBound(upperBound);
/* 1611 */     p.setUnique(isUnique);
/* 1612 */     p.setOrdered(isOrdered);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEException(EOperation owner, EClassifier exception) {
/* 1617 */     owner.getEExceptions().add(exception);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEException(EOperation owner, EGenericType exception) {
/* 1622 */     owner.getEGenericExceptions().add(exception);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addEEnumLiteral(EEnum owner, Enumerator e) {
/* 1627 */     EEnumLiteralImpl l = (EEnumLiteralImpl)this.ecoreFactory.createEEnumLiteral();
/* 1628 */     l.setInstance(e);
/* 1629 */     l.setGeneratedInstance(true);
/* 1630 */     owner.getELiterals().add(l);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addAnnotation(ENamedElement eNamedElement, String source, String[] details) {
/* 1635 */     addAnnotation(eNamedElement, source, details, (URI[])null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addAnnotation(ENamedElement eNamedElement, String source, String[] details, URI[] references) {
/* 1640 */     addAnnotation(eNamedElement, 0, source, details, references);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addAnnotation(ENamedElement eNamedElement, int depth, String source, String[] details) {
/* 1645 */     addAnnotation(eNamedElement, depth, source, details, (URI[])null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addAnnotation(ENamedElement eNamedElement, int depth, String source, String[] details, URI[] references) {
/* 1650 */     EAnnotation eAnnotation = this.ecoreFactory.createEAnnotation();
/* 1651 */     eAnnotation.setSource(source);
/* 1652 */     EMap<String, String> theDetails = eAnnotation.getDetails();
/* 1653 */     for (int i = 1; i < details.length; i += 2)
/*      */     {
/* 1655 */       theDetails.put(details[i - 1], details[i]);
/*      */     }
/* 1657 */     EList<EAnnotation> annotations = eNamedElement.getEAnnotations();
/* 1658 */     for (int j = 0; j < depth; j++) {
/*      */       
/* 1660 */       EList<EAnnotation> childAnnotations = (
/* 1661 */         (EAnnotation)annotations.get(annotations.size() - 1)).getContents();
/* 1662 */       annotations = childAnnotations;
/*      */     } 
/* 1664 */     annotations.add(eAnnotation);
/* 1665 */     if (references != null) {
/*      */       
/* 1667 */       InternalEList<EObject> eAnnotationReferences = (InternalEList<EObject>)eAnnotation.getReferences(); byte b; int k; URI[] arrayOfURI;
/* 1668 */       for (k = (arrayOfURI = references).length, b = 0; b < k; ) { URI reference = arrayOfURI[b];
/*      */         
/* 1670 */         InternalEObject internalEObject = (InternalEObject)this.ecoreFactory.createEObject();
/* 1671 */         internalEObject.eSetProxyURI(reference);
/* 1672 */         eAnnotationReferences.addUnique(internalEObject);
/*      */         b++; }
/*      */     
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initializeFromLoadedEPackage(EPackage target, EPackage source) {
/* 1679 */     target.setName(source.getName());
/* 1680 */     target.setNsPrefix(source.getNsPrefix());
/* 1681 */     target.setNsURI(source.getNsURI());
/*      */     
/* 1683 */     target.getEClassifiers().addAll((Collection)source.getEClassifiers());
/* 1684 */     target.getEAnnotations().addAll((Collection)source.getEAnnotations());
/*      */     
/* 1686 */     for (EPackage sourceSubpackage : source.getESubpackages()) {
/*      */       
/* 1688 */       EPackage targetSubpackage = EPackage.Registry.INSTANCE.getEPackage(sourceSubpackage.getNsURI());
/* 1689 */       initializeFromLoadedEPackage(targetSubpackage, sourceSubpackage);
/* 1690 */       target.getESubpackages().add(targetSubpackage);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fixEClassifiers() {
/* 1696 */     int id = 0;
/*      */     Iterator<EClassifier> i;
/* 1698 */     for (i = getEClassifiers().iterator(); i.hasNext(); ) {
/*      */       
/* 1700 */       EClassifierImpl eClassifier = (EClassifierImpl)i.next();
/* 1701 */       if (eClassifier instanceof EClass) {
/*      */         
/* 1703 */         eClassifier.setClassifierID(id++);
/* 1704 */         fixInstanceClass(eClassifier);
/* 1705 */         fixEStructuralFeatures((EClass)eClassifier);
/* 1706 */         fixEOperations((EClass)eClassifier);
/*      */       } 
/*      */     } 
/*      */     
/* 1710 */     for (i = getEClassifiers().iterator(); i.hasNext(); ) {
/*      */       
/* 1712 */       EClassifierImpl eClassifier = (EClassifierImpl)i.next();
/* 1713 */       if (eClassifier.metaObjectID == -1 && eClassifier instanceof EEnum) {
/*      */         
/* 1715 */         eClassifier.setClassifierID(id++);
/* 1716 */         fixInstanceClass(eClassifier);
/* 1717 */         fixEEnumLiterals((EEnum)eClassifier);
/*      */       } 
/*      */     } 
/*      */     
/* 1721 */     for (i = getEClassifiers().iterator(); i.hasNext(); ) {
/*      */       
/* 1723 */       EClassifierImpl eClassifier = (EClassifierImpl)i.next();
/* 1724 */       if (eClassifier.metaObjectID == -1 && eClassifier instanceof EDataType) {
/*      */         
/* 1726 */         eClassifier.setClassifierID(id++);
/* 1727 */         if (eClassifier.getInstanceClassName() == "org.eclipse.emf.common.util.AbstractEnumerator") {
/*      */           
/* 1729 */           EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType((EDataType)eClassifier);
/* 1730 */           if (baseType instanceof EEnum) {
/*      */             
/* 1732 */             eClassifier.setInstanceClass(baseType.getInstanceClass());
/* 1733 */             setGeneratedClassName(eClassifier);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fixInstanceClass(EClassifier eClassifier) {
/* 1742 */     if (eClassifier.getInstanceClassName() == null) {
/*      */       
/* 1744 */       String className = getClass().getName();
/* 1745 */       int i = className.lastIndexOf('.', className.lastIndexOf('.') - 1);
/* 1746 */       className = (i == -1) ? eClassifier.getName() : (String.valueOf(className.substring(0, i + 1)) + eClassifier.getName());
/* 1747 */       eClassifier.setInstanceClassName(className);
/* 1748 */       setGeneratedClassName(eClassifier);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fixEStructuralFeatures(EClass eClass) {
/* 1754 */     EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
/* 1755 */     if (!eList.isEmpty()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1762 */       Class<?> containerClass = (ExtendedMetaData.INSTANCE.getDocumentRoot(this) == eClass) ? null : eClass.getInstanceClass();
/*      */       
/* 1764 */       int id = eClass.getFeatureID(eList.get(0));
/*      */       
/* 1766 */       for (Iterator<EStructuralFeature> i = eList.iterator(); i.hasNext(); ) {
/*      */         
/* 1768 */         EStructuralFeatureImpl eStructuralFeature = (EStructuralFeatureImpl)i.next();
/* 1769 */         eStructuralFeature.setFeatureID(id++);
/* 1770 */         eStructuralFeature.setContainerClass(containerClass);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fixEOperations(EClass eClass) {
/* 1780 */     EList<EOperation> eList = eClass.getEOperations();
/* 1781 */     if (!eList.isEmpty()) {
/*      */       
/* 1783 */       int id = eClass.getOperationID(eList.get(0));
/*      */       
/* 1785 */       for (Iterator<EOperation> i = eList.iterator(); i.hasNext(); ) {
/*      */         
/* 1787 */         EOperationImpl eOperation = (EOperationImpl)i.next();
/* 1788 */         eOperation.setOperationID(id++);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fixEEnumLiterals(EEnum eEnum) {
/* 1795 */     Class<?> enumClass = eEnum.getInstanceClass();
/*      */ 
/*      */     
/*      */     try {
/* 1799 */       Method getter = enumClass.getMethod("get", new Class[] { int.class });
/*      */       
/* 1801 */       for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
/*      */       {
/* 1803 */         Enumerator instance = (Enumerator)getter.invoke(null, new Object[] { Integer.valueOf(eEnumLiteral.getValue()) });
/* 1804 */         eEnumLiteral.setInstance(instance);
/* 1805 */         ((EEnumLiteralImpl)eEnumLiteral).setGeneratedInstance(true);
/*      */       }
/*      */     
/* 1808 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BasicExtendedMetaData.EPackageExtendedMetaData getExtendedMetaData() {
/* 1818 */     return this.ePackageExtendedMetaData;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setExtendedMetaData(BasicExtendedMetaData.EPackageExtendedMetaData ePackageExtendedMetaData) {
/* 1823 */     this.ePackageExtendedMetaData = ePackageExtendedMetaData;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
/* 1829 */     EClassifier eClassifier = getEClassifierGen(uriFragmentSegment);
/* 1830 */     return (eClassifier != null) ? (EObject)eClassifier : super.eObjectForURIFragmentSegment(uriFragmentSegment);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EPackageImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */