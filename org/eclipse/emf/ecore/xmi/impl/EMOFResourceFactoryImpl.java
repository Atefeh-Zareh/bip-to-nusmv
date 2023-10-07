/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EMOFResourceFactoryImpl
/*     */   extends ResourceFactoryImpl
/*     */ {
/*     */   protected EMOFExtendedMetaData extendedMetaData;
/*     */   
/*     */   public EMOFResourceFactoryImpl() {
/*  36 */     XMLResource.XMLMap xmlMap = new XMLMapImpl();
/*     */     
/*  38 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EFACTORY, createXMLInfo("Factory"));
/*  39 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE, createXMLInfo("Package"));
/*  40 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EATTRIBUTE, createXMLInfo("Property"));
/*  41 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EREFERENCE, createXMLInfo("Property"));
/*  42 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPARAMETER, createXMLInfo("Parameter"));
/*  43 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EOPERATION, createXMLInfo("Operation"));
/*  44 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EENUM_LITERAL, createXMLInfo("EnumerationLiteral"));
/*  45 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EDATA_TYPE, createXMLInfo("PrimitiveType"));
/*  46 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EENUM, createXMLInfo("Enumeration"));
/*  47 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS, createXMLInfo("Class"));
/*     */     
/*  49 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EFACTORY__EPACKAGE, createXMLInfo("package"));
/*     */     
/*  51 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE__NS_URI, createXMLInfo("uri"));
/*  52 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, createXMLInfo("nestedPackage"));
/*  53 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE, createXMLInfo("nestingPackage"));
/*  54 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, createXMLInfo("ownedType"));
/*  55 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EPACKAGE__NS_PREFIX, createXMLInfo());
/*     */     
/*  57 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ETYPED_ELEMENT__ETYPE, createXMLInfo("type"));
/*  58 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ETYPED_ELEMENT__ORDERED, createXMLInfo("isOrdered"));
/*  59 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ETYPED_ELEMENT__UNIQUE, createXMLInfo("isUnique"));
/*  60 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND, createXMLInfo("lower"));
/*  61 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND, createXMLInfo("upper"));
/*     */     
/*  63 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE, createXMLInfo("isReadOnly"));
/*  64 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL, createXMLInfo("default"));
/*  65 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE, createXMLInfo());
/*  66 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE, createXMLInfo());
/*  67 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT, createXMLInfo());
/*  68 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED, createXMLInfo("isDerived"));
/*     */     
/*  70 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EATTRIBUTE__ID, createXMLInfo("isID"));
/*     */     
/*  72 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EREFERENCE__CONTAINMENT, createXMLInfo("isComposite"));
/*  73 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EREFERENCE__EOPPOSITE, createXMLInfo("opposite"));
/*  74 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES, createXMLInfo());
/*     */     
/*  76 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EENUM_LITERAL__VALUE, createXMLInfo());
/*     */     
/*  78 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EOPERATION__EPARAMETERS, createXMLInfo("ownedParameter"));
/*  79 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EOPERATION__EEXCEPTIONS, createXMLInfo("raisedException"));
/*     */     
/*  81 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME, createXMLInfo());
/*  82 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME, createXMLInfo());
/*     */     
/*  84 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE, createXMLInfo());
/*     */     
/*  86 */     xmlMap.add((ENamedElement)EcorePackage.Literals.EENUM__ELITERALS, createXMLInfo("ownedLiteral"));
/*     */     
/*  88 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS__ABSTRACT, createXMLInfo("isAbstract"));
/*  89 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, createXMLInfo("ownedAttribute"));
/*  90 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS__EOPERATIONS, createXMLInfo("ownedOperation"));
/*  91 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS__ESUPER_TYPES, createXMLInfo("superClass"));
/*  92 */     xmlMap.add((ENamedElement)EcorePackage.Literals.ECLASS__INTERFACE, createXMLInfo());
/*     */     
/*  94 */     this.extendedMetaData = new EMOFExtendedMetaData(xmlMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource createResource(URI uri) {
/* 100 */     EMOFResourceImpl result = new EMOFResourceImpl(uri);
/*     */     
/* 102 */     result.setEncoding("UTF-8");
/*     */     
/* 104 */     result.getDefaultLoadOptions().put("EXTENDED_META_DATA", this.extendedMetaData);
/* 105 */     result.getDefaultSaveOptions().put("EXTENDED_META_DATA", this.extendedMetaData);
/*     */     
/* 107 */     result.getDefaultSaveOptions().put("LINE_WIDTH", Integer.valueOf(80));
/* 108 */     result.getDefaultSaveOptions().put("USE_XMI_TYPE", Boolean.TRUE);
/*     */     
/* 110 */     return (Resource)result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLResource.XMLInfo createXMLInfo(String name) {
/* 115 */     XMLResource.XMLInfo info = new XMLInfoImpl();
/* 116 */     info.setName(name);
/* 117 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLResource.XMLInfo createXMLInfo() {
/* 122 */     XMLResource.XMLInfo info = new XMLInfoImpl();
/* 123 */     info.setXMLRepresentation(0);
/* 124 */     return info;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFResourceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */