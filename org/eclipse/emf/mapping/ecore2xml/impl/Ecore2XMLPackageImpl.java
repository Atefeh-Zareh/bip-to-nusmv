/*     */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ecore2XMLPackageImpl
/*     */   extends EPackageImpl
/*     */   implements Ecore2XMLPackage
/*     */ {
/*  47 */   private EClass xmlInfoEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private EClass xmlMapEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private EClass eNamedElementToXMLInfoMapEntryEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Ecore2XMLPackageImpl() {
/*  80 */     super("http://www.eclipse.org/emf/2005/Ecore2XML", (EFactory)Ecore2XMLFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     this.isInitialized = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isInited = false;
/*     */   
/*     */   private boolean isCreated;
/*     */ 
/*     */   
/*     */   public void initializePackageContents() {
/* 300 */     if (this.isInitialized)
/* 301 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 304 */     setName("ecore2xml");
/* 305 */     setNsPrefix("ecore2xml");
/* 306 */     setNsURI("http://www.eclipse.org/emf/2005/Ecore2XML");
/*     */ 
/*     */     
/* 309 */     EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2002/Ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     initEClass(this.xmlInfoEClass, XMLInfo.class, "XMLInfo", false, false, true);
/* 319 */     initEAttribute(getXMLInfo_Name(), (EClassifier)theEcorePackage.getEString(), "name", null, 0, 1, XMLInfo.class, false, true, true, false, false, true, false, true);
/* 320 */     initEAttribute(getXMLInfo_TargetNamespace(), (EClassifier)theEcorePackage.getEString(), "targetNamespace", null, 0, 1, XMLInfo.class, false, true, true, false, false, true, false, true);
/* 321 */     initEAttribute(getXMLInfo_XMLRepresentation(), (EClassifier)theEcorePackage.getEInt(), "xMLRepresentation", "-1", 0, 1, XMLInfo.class, false, true, true, false, false, true, false, true);
/*     */     
/* 323 */     initEClass(this.xmlMapEClass, XMLMap.class, "XMLMap", false, false, true);
/* 324 */     initEAttribute(getXMLMap_IDAttributeName(), (EClassifier)theEcorePackage.getEString(), "iDAttributeName", null, 0, 1, XMLMap.class, false, true, true, false, false, true, false, true);
/* 325 */     initEReference(getXMLMap_EcoreToXMLInfo(), (EClassifier)getENamedElementToXMLInfoMapEntry(), null, "ecoreToXMLInfo", null, 0, -1, XMLMap.class, false, false, true, true, false, false, true, false, true);
/* 326 */     initEReference(getXMLMap_NoNamespacePackage(), (EClassifier)theEcorePackage.getEPackage(), null, "noNamespacePackage", null, 0, 1, XMLMap.class, false, true, true, false, true, false, true, false, true);
/*     */     
/* 328 */     initEClass(this.eNamedElementToXMLInfoMapEntryEClass, Map.Entry.class, "ENamedElementToXMLInfoMapEntry", false, false, false);
/* 329 */     initEReference(getENamedElementToXMLInfoMapEntry_Key(), (EClassifier)theEcorePackage.getENamedElement(), null, "key", null, 1, 1, Map.Entry.class, false, false, true, false, true, false, true, false, true);
/* 330 */     initEReference(getENamedElementToXMLInfoMapEntry_Value(), (EClassifier)getXMLInfo(), null, "value", null, 1, 1, Map.Entry.class, false, false, true, true, false, false, true, false, true);
/*     */ 
/*     */     
/* 333 */     createResource("http://www.eclipse.org/emf/2005/Ecore2XML");
/*     */   }
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public static Ecore2XMLPackage init() {
/*     */     if (isInited)
/*     */       return (Ecore2XMLPackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2005/Ecore2XML"); 
/*     */     Ecore2XMLPackageImpl theEcore2XMLPackage = (EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2005/Ecore2XML") instanceof Ecore2XMLPackageImpl) ? (Ecore2XMLPackageImpl)EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2005/Ecore2XML") : new Ecore2XMLPackageImpl();
/*     */     isInited = true;
/*     */     EcorePackage.eINSTANCE.eClass();
/*     */     theEcore2XMLPackage.createPackageContents();
/*     */     theEcore2XMLPackage.initializePackageContents();
/*     */     theEcore2XMLPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http://www.eclipse.org/emf/2005/Ecore2XML", theEcore2XMLPackage);
/*     */     return theEcore2XMLPackage;
/*     */   }
/*     */   
/*     */   public EClass getXMLInfo() {
/*     */     return this.xmlInfoEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getXMLInfo_Name() {
/*     */     return (EAttribute)this.xmlInfoEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EAttribute getXMLInfo_TargetNamespace() {
/*     */     return (EAttribute)this.xmlInfoEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EAttribute getXMLInfo_XMLRepresentation() {
/*     */     return (EAttribute)this.xmlInfoEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getXMLMap() {
/*     */     return this.xmlMapEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getXMLMap_IDAttributeName() {
/*     */     return (EAttribute)this.xmlMapEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getXMLMap_EcoreToXMLInfo() {
/*     */     return (EReference)this.xmlMapEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getXMLMap_NoNamespacePackage() {
/*     */     return (EReference)this.xmlMapEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getENamedElementToXMLInfoMapEntry() {
/*     */     return this.eNamedElementToXMLInfoMapEntryEClass;
/*     */   }
/*     */   
/*     */   public EReference getENamedElementToXMLInfoMapEntry_Key() {
/*     */     return (EReference)this.eNamedElementToXMLInfoMapEntryEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getENamedElementToXMLInfoMapEntry_Value() {
/*     */     return (EReference)this.eNamedElementToXMLInfoMapEntryEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public Ecore2XMLFactory getEcore2XMLFactory() {
/*     */     return (Ecore2XMLFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.xmlInfoEClass = createEClass(0);
/*     */     createEAttribute(this.xmlInfoEClass, 0);
/*     */     createEAttribute(this.xmlInfoEClass, 1);
/*     */     createEAttribute(this.xmlInfoEClass, 2);
/*     */     this.xmlMapEClass = createEClass(1);
/*     */     createEAttribute(this.xmlMapEClass, 0);
/*     */     createEReference(this.xmlMapEClass, 1);
/*     */     createEReference(this.xmlMapEClass, 2);
/*     */     this.eNamedElementToXMLInfoMapEntryEClass = createEClass(2);
/*     */     createEReference(this.eNamedElementToXMLInfoMapEntryEClass, 0);
/*     */     createEReference(this.eNamedElementToXMLInfoMapEntryEClass, 1);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\Ecore2XMLPackageImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */