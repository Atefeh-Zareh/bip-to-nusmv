/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ExtendedMetaData
/*     */ {
/*     */   public static final String ANNOTATION_URI = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
/*     */   public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
/*     */   public static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
/*     */   public static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";
/*     */   public static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";
/*     */   public static final String XMI_URI = "http://www.omg.org/XMI";
/*     */   public static final String XMLNS_PREFIX = "xmlns";
/*     */   public static final String XSI_PREFIX = "xsi";
/*     */   public static final int UNSPECIFIED_FEATURE = 0;
/*     */   public static final int SIMPLE_FEATURE = 1;
/*     */   public static final int ATTRIBUTE_FEATURE = 2;
/*     */   public static final int ATTRIBUTE_WILDCARD_FEATURE = 3;
/*     */   public static final int ELEMENT_FEATURE = 4;
/*     */   public static final int ELEMENT_WILDCARD_FEATURE = 5;
/*     */   public static final int GROUP_FEATURE = 6;
/* 340 */   public static final String[] FEATURE_KINDS = new String[] { "unspecified", "simple", "attribute", "attributeWildcard", "element", "elementWildcard", "group" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNSPECIFIED_CONTENT = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int EMPTY_CONTENT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SIMPLE_CONTENT = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MIXED_CONTENT = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ELEMENT_ONLY_CONTENT = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   public static final String[] CONTENT_KINDS = new String[] { "unspecified", "empty", "simple", "mixed", "elementOnly" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNSPECIFIED_DERIVATION = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int RESTRICTION_DERIVATION = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LIST_DERIVATION = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNION_DERIVATION = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public static final String[] DERIVATION_KINDS = new String[] { "unspecified", "restriction", "list", "union" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNSPECIFIED_PROCESSING = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int STRICT_PROCESSING = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LAX_PROCESSING = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SKIP_PROCESSING = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public static final String[] PROCESSING_KINDS = new String[] { "unspecified", "strict", "lax", "skip" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNSPECIFIED_WHITE_SPACE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int PRESERVE_WHITE_SPACE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int REPLACE_WHITE_SPACE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int COLLAPSE_WHITE_SPACE = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public static final String[] WHITE_SPACE_KINDS = new String[] { "unspecified", "preserve", "replace", "collapse" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 877 */   public static final ExtendedMetaData INSTANCE = new BasicExtendedMetaData();
/*     */   
/*     */   EPackage getPackage(String paramString);
/*     */   
/*     */   void putPackage(String paramString, EPackage paramEPackage);
/*     */   
/*     */   EClass getDocumentRoot(EPackage paramEPackage);
/*     */   
/*     */   void setDocumentRoot(EClass paramEClass);
/*     */   
/*     */   boolean isDocumentRoot(EClass paramEClass);
/*     */   
/*     */   EReference getXMLNSPrefixMapFeature(EClass paramEClass);
/*     */   
/*     */   EReference getXSISchemaLocationMapFeature(EClass paramEClass);
/*     */   
/*     */   boolean isQualified(EPackage paramEPackage);
/*     */   
/*     */   void setQualified(EPackage paramEPackage, boolean paramBoolean);
/*     */   
/*     */   String getNamespace(EPackage paramEPackage);
/*     */   
/*     */   String getNamespace(EClassifier paramEClassifier);
/*     */   
/*     */   String getNamespace(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setNamespace(EStructuralFeature paramEStructuralFeature, String paramString);
/*     */   
/*     */   String getName(EClassifier paramEClassifier);
/*     */   
/*     */   void setName(EClassifier paramEClassifier, String paramString);
/*     */   
/*     */   boolean isAnonymous(EClassifier paramEClassifier);
/*     */   
/*     */   String getName(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setName(EStructuralFeature paramEStructuralFeature, String paramString);
/*     */   
/*     */   EClassifier getType(String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature getAttribute(String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature getElement(String paramString1, String paramString2);
/*     */   
/*     */   EClassifier getType(EPackage paramEPackage, String paramString);
/*     */   
/*     */   EStructuralFeature getAttribute(EClass paramEClass, String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature getElement(EClass paramEClass, String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature getSimpleFeature(EClass paramEClass);
/*     */   
/*     */   EAttribute getMixedFeature(EClass paramEClass);
/*     */   
/*     */   int getFeatureKind(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setFeatureKind(EStructuralFeature paramEStructuralFeature, int paramInt);
/*     */   
/*     */   int getContentKind(EClass paramEClass);
/*     */   
/*     */   void setContentKind(EClass paramEClass, int paramInt);
/*     */   
/*     */   int getDerivationKind(EDataType paramEDataType);
/*     */   
/*     */   EDataType getBaseType(EDataType paramEDataType);
/*     */   
/*     */   void setBaseType(EDataType paramEDataType1, EDataType paramEDataType2);
/*     */   
/*     */   EDataType getItemType(EDataType paramEDataType);
/*     */   
/*     */   void setItemType(EDataType paramEDataType1, EDataType paramEDataType2);
/*     */   
/*     */   List<EDataType> getMemberTypes(EDataType paramEDataType);
/*     */   
/*     */   void setMemberTypes(EDataType paramEDataType, List<EDataType> paramList);
/*     */   
/*     */   List<EStructuralFeature> getAllAttributes(EClass paramEClass);
/*     */   
/*     */   List<EStructuralFeature> getAllElements(EClass paramEClass);
/*     */   
/*     */   List<EStructuralFeature> getAttributes(EClass paramEClass);
/*     */   
/*     */   List<EStructuralFeature> getElements(EClass paramEClass);
/*     */   
/*     */   boolean matches(List<String> paramList, String paramString);
/*     */   
/*     */   boolean matches(String paramString1, String paramString2);
/*     */   
/*     */   List<String> getWildcards(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setWildcards(EStructuralFeature paramEStructuralFeature, List<String> paramList);
/*     */   
/*     */   int getProcessingKind(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setProcessingKind(EStructuralFeature paramEStructuralFeature, int paramInt);
/*     */   
/*     */   EStructuralFeature getAffiliation(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setAffiliation(EStructuralFeature paramEStructuralFeature1, EStructuralFeature paramEStructuralFeature2);
/*     */   
/*     */   EStructuralFeature getGroup(EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   void setGroup(EStructuralFeature paramEStructuralFeature1, EStructuralFeature paramEStructuralFeature2);
/*     */   
/*     */   EStructuralFeature getAffiliation(EClass paramEClass, EStructuralFeature paramEStructuralFeature);
/*     */   
/*     */   EStructuralFeature getAttributeWildcardAffiliation(EClass paramEClass, String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature getElementWildcardAffiliation(EClass paramEClass, String paramString1, String paramString2);
/*     */   
/*     */   int getWhiteSpaceFacet(EDataType paramEDataType);
/*     */   
/*     */   void setWhiteSpaceFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   List<String> getEnumerationFacet(EDataType paramEDataType);
/*     */   
/*     */   void setEnumerationFacet(EDataType paramEDataType, List<String> paramList);
/*     */   
/*     */   List<String> getPatternFacet(EDataType paramEDataType);
/*     */   
/*     */   void setPatternFacet(EDataType paramEDataType, List<String> paramList);
/*     */   
/*     */   int getTotalDigitsFacet(EDataType paramEDataType);
/*     */   
/*     */   void setTotalDigitsFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   int getFractionDigitsFacet(EDataType paramEDataType);
/*     */   
/*     */   void setFractionDigitsFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   int getLengthFacet(EDataType paramEDataType);
/*     */   
/*     */   void setLengthFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   int getMinLengthFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMinLengthFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   int getMaxLengthFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMaxLengthFacet(EDataType paramEDataType, int paramInt);
/*     */   
/*     */   String getMinExclusiveFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMinExclusiveFacet(EDataType paramEDataType, String paramString);
/*     */   
/*     */   String getMaxExclusiveFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMaxExclusiveFacet(EDataType paramEDataType, String paramString);
/*     */   
/*     */   String getMinInclusiveFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMinInclusiveFacet(EDataType paramEDataType, String paramString);
/*     */   
/*     */   String getMaxInclusiveFacet(EDataType paramEDataType);
/*     */   
/*     */   void setMaxInclusiveFacet(EDataType paramEDataType, String paramString);
/*     */   
/*     */   EPackage demandPackage(String paramString);
/*     */   
/*     */   EClassifier demandType(String paramString1, String paramString2);
/*     */   
/*     */   EStructuralFeature demandFeature(String paramString1, String paramString2, boolean paramBoolean);
/*     */   
/*     */   EStructuralFeature demandFeature(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   Collection<EPackage> demandedPackages();
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\ExtendedMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */