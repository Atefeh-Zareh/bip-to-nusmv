/*     */ package org.eclipse.emf.mapping.ecore2xml;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Ecore2XMLPackage
/*     */   extends EPackage
/*     */ {
/*     */   public static final String eNAME = "ecore2xml";
/*     */   public static final String eNS_URI = "http://www.eclipse.org/emf/2005/Ecore2XML";
/*     */   public static final String eNS_PREFIX = "ecore2xml";
/*     */   public static final String eCONTENT_TYPE = "org.eclipse.emf.mapping.ecore2xml";
/*  80 */   public static final Ecore2XMLPackage eINSTANCE = Ecore2XMLPackageImpl.init();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_INFO = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_INFO__NAME = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_INFO__TARGET_NAMESPACE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_INFO__XML_REPRESENTATION = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_INFO_FEATURE_COUNT = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_MAP = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_MAP__ID_ATTRIBUTE_NAME = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_MAP__ECORE_TO_XML_INFO = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_MAP__NO_NAMESPACE_PACKAGE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_MAP_FEATURE_COUNT = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY_FEATURE_COUNT = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EClass getXMLInfo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLInfo_Name();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLInfo_TargetNamespace();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLInfo_XMLRepresentation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EClass getXMLMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLMap_IDAttributeName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getXMLMap_EcoreToXMLInfo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getXMLMap_NoNamespacePackage();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EClass getENamedElementToXMLInfoMapEntry();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getENamedElementToXMLInfoMapEntry_Key();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getENamedElementToXMLInfoMapEntry_Value();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Ecore2XMLFactory getEcore2XMLFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Literals
/*     */   {
/* 363 */     public static final EClass XML_INFO = Ecore2XMLPackage.eINSTANCE.getXMLInfo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 371 */     public static final EAttribute XML_INFO__NAME = Ecore2XMLPackage.eINSTANCE.getXMLInfo_Name();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 379 */     public static final EAttribute XML_INFO__TARGET_NAMESPACE = Ecore2XMLPackage.eINSTANCE.getXMLInfo_TargetNamespace();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 387 */     public static final EAttribute XML_INFO__XML_REPRESENTATION = Ecore2XMLPackage.eINSTANCE.getXMLInfo_XMLRepresentation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 397 */     public static final EClass XML_MAP = Ecore2XMLPackage.eINSTANCE.getXMLMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 405 */     public static final EAttribute XML_MAP__ID_ATTRIBUTE_NAME = Ecore2XMLPackage.eINSTANCE.getXMLMap_IDAttributeName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 413 */     public static final EReference XML_MAP__ECORE_TO_XML_INFO = Ecore2XMLPackage.eINSTANCE.getXMLMap_EcoreToXMLInfo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     public static final EReference XML_MAP__NO_NAMESPACE_PACKAGE = Ecore2XMLPackage.eINSTANCE.getXMLMap_NoNamespacePackage();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     public static final EClass ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY = Ecore2XMLPackage.eINSTANCE.getENamedElementToXMLInfoMapEntry();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     public static final EReference ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY = Ecore2XMLPackage.eINSTANCE.getENamedElementToXMLInfoMapEntry_Key();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 447 */     public static final EReference ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE = Ecore2XMLPackage.eINSTANCE.getENamedElementToXMLInfoMapEntry_Value();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\Ecore2XMLPackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */