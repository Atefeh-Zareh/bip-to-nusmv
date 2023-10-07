/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EMOFExtendedMetaData
/*     */   extends BasicExtendedMetaData
/*     */ {
/*     */   public static final String EMOF_PACKAGE_NS_PREFIX = "emof";
/*     */   public static final String EMOF_PACKAGE_NS_URI_2_0 = "http://schema.omg.org/spec/MOF/2.0/emof.xml";
/*     */   public static final String EMOF_PACKAGE_NS_URI = "http://schema.omg.org/spec/mof/2.0/emof.xmi";
/*     */   public static final String EXTENSION = "Extension";
/*     */   public static final String XMI_EXTENSION_ELEMENT = "xmi:Extension";
/*     */   public static final String XMI_EXTENDER_ATTRIBUTE = "extender";
/*     */   public static final String EMOF_XMI_EXTENDER = "http://www.eclipse.org/emf/2002/Ecore";
/*     */   public static final String ECORE_EDATATYPE_HREF_PREFIX = "http://www.eclipse.org/emf/2002/Ecore#//";
/*     */   public static final String UNMAPPED_EMOF_EDATATYPE_HREF_PREFIX = "http://www.eclipse.org/emf/2002/Ecore.emof#ecore.";
/*     */   public static final String MAPPED_EMOF_EDATATYPE_HREF_PREFIX = "http://schema.omg.org/spec/mof/2.0/emof.xmi#";
/*     */   public static final String MAPPED_EMOF_EDATATYPE_HREF_PREFIX_2_0 = "http://schema.omg.org/spec/MOF/2.0/emof.xml#";
/*  55 */   public static final String[] MAPPED_ECORE_EDATATYPES = new String[] { "EString", "EBoolean", "EInt", "EBigInteger" };
/*  56 */   public static final String[] MAPPED_EMOF_EDATATYPES = new String[] { "String", "Boolean", "Integer", "UnlimitedNatural" };
/*     */   
/*     */   public static final String TAG = "Tag";
/*     */   
/*     */   public static final String EMOF_TAG = "emof:Tag";
/*     */   
/*     */   public static final String EMOF_TAG_NAME = "name";
/*     */   
/*     */   public static final String EMOF_TAG_VALUE = "value";
/*     */   
/*     */   public static final String EMOF_TAG_ELEMENT = "element";
/*     */   
/*     */   public static final String EMOF_OWNED_COMMENT = "ownedComment";
/*     */   
/*     */   public static final String EMOF_COMMENT_BODY = "body";
/*     */   
/*     */   public static final String EMOF_COMMENT_ANNOTATION_SOURCE = "http://schema.omg.org/spec/MOF/2.0/emof.xml#Comment";
/*     */   
/*     */   public static final String EMOF_PROPERTY_CLASS_NAME = "Property";
/*     */   
/*     */   public static final String EMOF_PROPERTY_OPPOSITE_ROLE_NAME_ANNOTATION_SOURCE = "http://schema.omg.org/spec/MOF/2.0/emof.xml#Property.oppositeRoleName";
/*     */   
/*     */   public static final String CONTENT_TYPE = "org.eclipse.emf.emof";
/*     */   protected XMLResource.XMLMap xmlMap;
/*     */   
/*     */   public EMOFExtendedMetaData(XMLResource.XMLMap xmlMap) {
/*  82 */     this.extendedMetaDataHolderCache = new HashMap<Object, Object>();
/*  83 */     this.xmlMap = xmlMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespace(EPackage ePackage) {
/*  89 */     return (ePackage == EcorePackage.eINSTANCE) ? "http://schema.omg.org/spec/MOF/2.0/emof.xml" : super.getNamespace(ePackage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getPackage(String namespace) {
/*  95 */     return ("http://schema.omg.org/spec/mof/2.0/emof.xmi".equals(namespace) || "http://schema.omg.org/spec/MOF/2.0/emof.xml".equals(namespace)) ? (EPackage)EcorePackage.eINSTANCE : super.getPackage(namespace);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(EClassifier eClassifier) {
/* 101 */     XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)eClassifier);
/* 102 */     if (info != null) {
/*     */       
/* 104 */       String name = info.getName();
/* 105 */       if (name != null)
/*     */       {
/* 107 */         return info.getName();
/*     */       }
/*     */     } 
/* 110 */     return super.getName(eClassifier);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(EStructuralFeature eStructuralFeature) {
/* 116 */     XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)eStructuralFeature);
/* 117 */     if (info != null) {
/*     */       
/* 119 */       String name = info.getName();
/* 120 */       if (name != null)
/*     */       {
/* 122 */         return info.getName();
/*     */       }
/*     */     } 
/* 125 */     return super.getName(eStructuralFeature);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getType(EPackage ePackage, String name) {
/* 131 */     EClassifier eClassifier = super.getType(ePackage, name);
/* 132 */     if (eClassifier == null)
/*     */     {
/* 134 */       eClassifier = this.xmlMap.getClassifier(ePackage.getNsURI(), name);
/*     */     }
/* 136 */     return eClassifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureKind(EStructuralFeature feature) {
/* 142 */     XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)feature);
/* 143 */     if (info != null)
/*     */     {
/* 145 */       switch (info.getXMLRepresentation()) {
/*     */         
/*     */         case 0:
/* 148 */           return 4;
/*     */         case 1:
/* 150 */           return 2;
/*     */       } 
/*     */     }
/* 153 */     return super.getFeatureKind(feature);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicExtendedMetaData.EPackageExtendedMetaData createEPackageExtendedMetaData(EPackage ePackage) {
/* 159 */     return 
/* 160 */       (BasicExtendedMetaData.EPackageExtendedMetaData)new BasicExtendedMetaData.EPackageExtendedMetaDataImpl(this, ePackage)
/*     */       {
/*     */         
/*     */         public EClassifier getType(String name)
/*     */         {
/* 165 */           if (this.ePackage == EcorePackage.eINSTANCE) {
/*     */ 
/*     */ 
/*     */             
/* 169 */             if (this.nameToClassifierMap == null) {
/*     */               
/* 171 */               super.getType(name);
/* 172 */               this.nameToClassifierMap.put("Property", EcorePackage.Literals.EREFERENCE);
/*     */             } 
/* 174 */             return (EClassifier)this.nameToClassifierMap.get(name);
/*     */           } 
/*     */ 
/*     */           
/* 178 */           return super.getType(name);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFExtendedMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */