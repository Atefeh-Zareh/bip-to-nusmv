/*     */ package org.eclipse.emf.ecore.xml.namespace.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.util.Enumerator;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EValidator;
/*     */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*     */ import org.eclipse.emf.ecore.xml.namespace.SpaceType;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceFactory;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*     */ import org.eclipse.emf.ecore.xml.namespace.util.XMLNamespaceValidator;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLNamespacePackageImpl
/*     */   extends EPackageImpl
/*     */   implements XMLNamespacePackage
/*     */ {
/*  49 */   private EClass xmlNamespaceDocumentRootEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private EEnum spaceTypeEEnum = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private EDataType langTypeEDataType = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private EDataType langTypeNullEDataType = null;
/*     */   
/*     */   private static boolean isInited = false;
/*     */   
/*     */   private boolean isCreated;
/*     */   
/*     */   private boolean isInitialized;
/*  77 */   private EDataType spaceTypeObjectEDataType = null;
/*     */   public static XMLNamespacePackage init() { if (isInited)
/*     */       return (XMLNamespacePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.w3.org/XML/1998/namespace"); 
/*     */     XMLNamespacePackageImpl theXMLNamespacePackage = (EPackage.Registry.INSTANCE.get("http://www.w3.org/XML/1998/namespace") instanceof XMLNamespacePackageImpl) ? (XMLNamespacePackageImpl)EPackage.Registry.INSTANCE.get("http://www.w3.org/XML/1998/namespace") : new XMLNamespacePackageImpl();
/*     */     isInited = true;
/*     */     XMLTypePackage.eINSTANCE.eClass();
/*     */     theXMLNamespacePackage.createPackageContents();
/*     */     theXMLNamespacePackage.initializePackageContents();
/*     */     EValidator.Registry.INSTANCE.put(theXMLNamespacePackage, new EValidator.Descriptor()
/*     */         {
/*     */           public EValidator getEValidator() { return (EValidator)XMLNamespaceValidator.INSTANCE; }
/*     */         });
/*     */     theXMLNamespacePackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http://www.w3.org/XML/1998/namespace", theXMLNamespacePackage);
/*     */     return theXMLNamespacePackage; } public EClass getXMLNamespaceDocumentRoot() { return this.xmlNamespaceDocumentRootEClass; } public EAttribute getXMLNamespaceDocumentRoot_Mixed() {
/*     */     return (EAttribute)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(0);
/*     */   } public EReference getXMLNamespaceDocumentRoot_XMLNSPrefixMap() {
/*     */     return (EReference)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(1);
/*     */   } private XMLNamespacePackageImpl() {
/*  96 */     super("http://www.w3.org/XML/1998/namespace", (EFactory)XMLNamespaceFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 329 */     this.isInitialized = false;
/*     */   }
/*     */   public EReference getXMLNamespaceDocumentRoot_XSISchemaLocation() {
/*     */     return (EReference)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EAttribute getXMLNamespaceDocumentRoot_Base() {
/*     */     return (EAttribute)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(3);
/*     */   }
/*     */   
/*     */   public void initializePackageContents() {
/* 340 */     if (this.isInitialized)
/* 341 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 344 */     setName("namespace");
/* 345 */     setNsPrefix("xml");
/* 346 */     setNsURI("http://www.w3.org/XML/1998/namespace");
/*     */ 
/*     */     
/* 349 */     XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2003/XMLType");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     initEClass(this.xmlNamespaceDocumentRootEClass, XMLNamespaceDocumentRoot.class, "XMLNamespaceDocumentRoot", false, false, true);
/* 359 */     initEAttribute(getXMLNamespaceDocumentRoot_Mixed(), (EClassifier)this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, false, false, true, false, false, false, false, true);
/* 360 */     initEReference(getXMLNamespaceDocumentRoot_XMLNSPrefixMap(), (EClassifier)this.ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, true, false, true, true, false, false, true, false, true);
/* 361 */     initEReference(getXMLNamespaceDocumentRoot_XSISchemaLocation(), (EClassifier)this.ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, true, false, true, true, false, false, true, false, true);
/* 362 */     initEAttribute(getXMLNamespaceDocumentRoot_Base(), (EClassifier)theXMLTypePackage.getAnyURI(), "base", null, 0, 1, null, false, false, true, false, false, true, false, true);
/* 363 */     initEAttribute(getXMLNamespaceDocumentRoot_Id(), (EClassifier)theXMLTypePackage.getID(), "id", null, 0, 1, null, false, false, true, false, true, true, false, true);
/* 364 */     initEAttribute(getXMLNamespaceDocumentRoot_Lang(), (EClassifier)getLangType(), "lang", null, 0, 1, null, false, false, true, false, false, true, false, true);
/* 365 */     initEAttribute(getXMLNamespaceDocumentRoot_Space(), (EClassifier)getSpaceType(), "space", null, 0, 1, null, false, false, true, true, false, true, false, true);
/*     */ 
/*     */     
/* 368 */     initEEnum(this.spaceTypeEEnum, SpaceType.class, "SpaceType");
/* 369 */     addEEnumLiteral(this.spaceTypeEEnum, (Enumerator)SpaceType.DEFAULT_LITERAL);
/* 370 */     addEEnumLiteral(this.spaceTypeEEnum, (Enumerator)SpaceType.PRESERVE_LITERAL);
/*     */ 
/*     */     
/* 373 */     initEDataType(this.langTypeEDataType, String.class, "LangType", true, false);
/* 374 */     initEDataType(this.langTypeNullEDataType, String.class, "LangTypeNull", true, false);
/* 375 */     initEDataType(this.spaceTypeObjectEDataType, SpaceType.class, "SpaceTypeObject", true, true);
/*     */ 
/*     */     
/* 378 */     createResource("http://www.w3.org/XML/1998/namespace");
/*     */ 
/*     */ 
/*     */     
/* 382 */     createNamespaceAnnotations();
/*     */     
/* 384 */     createExtendedMetaDataAnnotations();
/*     */   } public EAttribute getXMLNamespaceDocumentRoot_Id() {
/*     */     return (EAttribute)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(4);
/*     */   } public EAttribute getXMLNamespaceDocumentRoot_Lang() {
/*     */     return (EAttribute)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(5);
/*     */   } public EAttribute getXMLNamespaceDocumentRoot_Space() {
/*     */     return (EAttribute)this.xmlNamespaceDocumentRootEClass.getEStructuralFeatures().get(6);
/*     */   }
/*     */   public EEnum getSpaceType() {
/*     */     return this.spaceTypeEEnum;
/*     */   }
/* 395 */   protected void createNamespaceAnnotations() { String source = "http://www.w3.org/XML/1998/namespace";
/* 396 */     addAnnotation(
/* 397 */         (ENamedElement)this, 
/* 398 */         source, 
/*     */         
/* 400 */         new String[] {
/* 401 */           "lang", "en" }); }
/*     */ 
/*     */   
/*     */   public EDataType getLangType() {
/*     */     return this.langTypeEDataType;
/*     */   }
/*     */   
/*     */   public EDataType getLangTypeNull() {
/*     */     return this.langTypeNullEDataType;
/*     */   }
/*     */   
/*     */   protected void createExtendedMetaDataAnnotations() {
/* 413 */     String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
/* 414 */     addAnnotation(
/* 415 */         (ENamedElement)this.langTypeEDataType, 
/* 416 */         source, 
/*     */         
/* 418 */         new String[] {
/* 419 */           "name", "lang_._type", 
/* 420 */           "memberTypes", "http://www.eclipse.org/emf/2003/XMLType#language lang_._type_._member_._1"
/*     */         });
/* 422 */     addAnnotation(
/* 423 */         (ENamedElement)this.langTypeNullEDataType, 
/* 424 */         source, 
/*     */         
/* 426 */         new String[] {
/* 427 */           "name", "lang_._type_._member_._1", 
/* 428 */           "baseType", "http://www.eclipse.org/emf/2003/XMLType#string", 
/* 429 */           "enumeration", ""
/*     */         });
/* 431 */     addAnnotation(
/* 432 */         (ENamedElement)this.spaceTypeEEnum, 
/* 433 */         source, 
/*     */         
/* 435 */         new String[] {
/* 436 */           "name", "space_._type"
/*     */         });
/* 438 */     addAnnotation(
/* 439 */         (ENamedElement)this.spaceTypeObjectEDataType, 
/* 440 */         source, 
/*     */         
/* 442 */         new String[] {
/* 443 */           "name", "space_._type:Object", 
/* 444 */           "baseType", "space_._type"
/*     */         });
/* 446 */     addAnnotation(
/* 447 */         (ENamedElement)this.xmlNamespaceDocumentRootEClass, 
/* 448 */         source, 
/*     */         
/* 450 */         new String[] {
/* 451 */           "name", "", 
/* 452 */           "kind", "mixed"
/*     */         });
/* 454 */     addAnnotation(
/* 455 */         (ENamedElement)getXMLNamespaceDocumentRoot_Mixed(), 
/* 456 */         source, 
/*     */         
/* 458 */         new String[] {
/* 459 */           "kind", "elementWildcard", 
/* 460 */           "name", ":mixed"
/*     */         });
/* 462 */     addAnnotation(
/* 463 */         (ENamedElement)getXMLNamespaceDocumentRoot_XMLNSPrefixMap(), 
/* 464 */         source, 
/*     */         
/* 466 */         new String[] {
/* 467 */           "kind", "attribute", 
/* 468 */           "name", "xmlns:prefix"
/*     */         });
/* 470 */     addAnnotation(
/* 471 */         (ENamedElement)getXMLNamespaceDocumentRoot_XSISchemaLocation(), 
/* 472 */         source, 
/*     */         
/* 474 */         new String[] {
/* 475 */           "kind", "attribute", 
/* 476 */           "name", "xsi:schemaLocation"
/*     */         });
/* 478 */     addAnnotation(
/* 479 */         (ENamedElement)getXMLNamespaceDocumentRoot_Base(), 
/* 480 */         source, 
/*     */         
/* 482 */         new String[] {
/* 483 */           "kind", "attribute", 
/* 484 */           "name", "base", 
/* 485 */           "namespace", "##targetNamespace"
/*     */         });
/* 487 */     addAnnotation(
/* 488 */         (ENamedElement)getXMLNamespaceDocumentRoot_Id(), 
/* 489 */         source, 
/*     */         
/* 491 */         new String[] {
/* 492 */           "kind", "attribute", 
/* 493 */           "name", "id", 
/* 494 */           "namespace", "##targetNamespace"
/*     */         });
/* 496 */     addAnnotation(
/* 497 */         (ENamedElement)getXMLNamespaceDocumentRoot_Lang(), 
/* 498 */         source, 
/*     */         
/* 500 */         new String[] {
/* 501 */           "kind", "attribute", 
/* 502 */           "name", "lang", 
/* 503 */           "namespace", "##targetNamespace"
/*     */         });
/* 505 */     addAnnotation(
/* 506 */         (ENamedElement)getXMLNamespaceDocumentRoot_Space(), 
/* 507 */         source, 
/*     */         
/* 509 */         new String[] {
/* 510 */           "kind", "attribute", 
/* 511 */           "name", "space", 
/* 512 */           "namespace", "##targetNamespace" });
/*     */   }
/*     */   
/*     */   public EDataType getSpaceTypeObject() {
/*     */     return this.spaceTypeObjectEDataType;
/*     */   }
/*     */   
/*     */   public XMLNamespaceFactory getXMLNamespaceFactory() {
/*     */     return (XMLNamespaceFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.xmlNamespaceDocumentRootEClass = createEClass(0);
/*     */     createEAttribute(this.xmlNamespaceDocumentRootEClass, 0);
/*     */     createEReference(this.xmlNamespaceDocumentRootEClass, 1);
/*     */     createEReference(this.xmlNamespaceDocumentRootEClass, 2);
/*     */     createEAttribute(this.xmlNamespaceDocumentRootEClass, 3);
/*     */     createEAttribute(this.xmlNamespaceDocumentRootEClass, 4);
/*     */     createEAttribute(this.xmlNamespaceDocumentRootEClass, 5);
/*     */     createEAttribute(this.xmlNamespaceDocumentRootEClass, 6);
/*     */     this.spaceTypeEEnum = createEEnum(1);
/*     */     this.langTypeEDataType = createEDataType(2);
/*     */     this.langTypeNullEDataType = createEDataType(3);
/*     */     this.spaceTypeObjectEDataType = createEDataType(4);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\impl\XMLNamespacePackageImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */