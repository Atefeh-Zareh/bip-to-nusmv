/*     */ package org.eclipse.emf.ecore.xml.namespace;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.xml.namespace.impl.XMLNamespacePackageImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface XMLNamespacePackage
/*     */   extends EPackage
/*     */ {
/*     */   public static final String eNAME = "namespace";
/*     */   public static final String eNS_URI = "http://www.w3.org/XML/1998/namespace";
/*     */   public static final String eNS_PREFIX = "xml";
/*  75 */   public static final XMLNamespacePackage eINSTANCE = XMLNamespacePackageImpl.init();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__MIXED = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__BASE = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__ID = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__LANG = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT__SPACE = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int XML_NAMESPACE_DOCUMENT_ROOT_FEATURE_COUNT = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SPACE_TYPE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LANG_TYPE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LANG_TYPE_NULL = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SPACE_TYPE_OBJECT = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EClass getXMLNamespaceDocumentRoot();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLNamespaceDocumentRoot_Mixed();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getXMLNamespaceDocumentRoot_XMLNSPrefixMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EReference getXMLNamespaceDocumentRoot_XSISchemaLocation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLNamespaceDocumentRoot_Base();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLNamespaceDocumentRoot_Id();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLNamespaceDocumentRoot_Lang();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getXMLNamespaceDocumentRoot_Space();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EEnum getSpaceType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EDataType getLangType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EDataType getLangTypeNull();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EDataType getSpaceTypeObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   XMLNamespaceFactory getXMLNamespaceFactory();
/*     */ 
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
/* 364 */     public static final EClass XML_NAMESPACE_DOCUMENT_ROOT = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     public static final EAttribute XML_NAMESPACE_DOCUMENT_ROOT__MIXED = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_Mixed();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     public static final EReference XML_NAMESPACE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_XMLNSPrefixMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     public static final EReference XML_NAMESPACE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_XSISchemaLocation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     public static final EAttribute XML_NAMESPACE_DOCUMENT_ROOT__BASE = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_Base();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     public static final EAttribute XML_NAMESPACE_DOCUMENT_ROOT__ID = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_Id();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 412 */     public static final EAttribute XML_NAMESPACE_DOCUMENT_ROOT__LANG = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_Lang();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 420 */     public static final EAttribute XML_NAMESPACE_DOCUMENT_ROOT__SPACE = XMLNamespacePackage.eINSTANCE.getXMLNamespaceDocumentRoot_Space();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 430 */     public static final EEnum SPACE_TYPE = XMLNamespacePackage.eINSTANCE.getSpaceType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 440 */     public static final EDataType LANG_TYPE = XMLNamespacePackage.eINSTANCE.getLangType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 450 */     public static final EDataType LANG_TYPE_NULL = XMLNamespacePackage.eINSTANCE.getLangTypeNull();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 460 */     public static final EDataType SPACE_TYPE_OBJECT = XMLNamespacePackage.eINSTANCE.getSpaceTypeObject();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\XMLNamespacePackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */