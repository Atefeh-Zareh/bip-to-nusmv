/*     */ package org.eclipse.emf.mapping.ecore2xml.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ecore2XMLExtendedMetaData
/*     */   extends BasicExtendedMetaData
/*     */ {
/*     */   protected final Ecore2XMLRegistry ecore2xmlRegistry;
/*  40 */   protected final Map<String, XMLResource.XMLMap> xmlMaps = new HashMap<String, XMLResource.XMLMap>();
/*     */ 
/*     */   
/*     */   public Ecore2XMLExtendedMetaData() {
/*  44 */     this(Ecore2XMLRegistry.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public Ecore2XMLExtendedMetaData(Ecore2XMLRegistry ecore2xmlRegistry) {
/*  49 */     this(EPackage.Registry.INSTANCE, ecore2xmlRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public Ecore2XMLExtendedMetaData(EPackage.Registry ePackageRegistry, Ecore2XMLRegistry ecore2xmlRegistry) {
/*  54 */     this("http:///org/eclipse/emf/ecore/util/ExtendedMetaData", ePackageRegistry, ecore2xmlRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public Ecore2XMLExtendedMetaData(String annotationURI, EPackage.Registry ePackageRegistry, Ecore2XMLRegistry ecore2xmlRegistry) {
/*  59 */     super(annotationURI, ePackageRegistry);
/*     */     
/*  61 */     this.extendedMetaDataHolderCache = new HashMap<Object, Object>();
/*     */     
/*  63 */     this.ecore2xmlRegistry = ecore2xmlRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLResource.XMLInfo getInfo(ENamedElement element) {
/*  68 */     XMLResource.XMLInfo xmlInfo = null;
/*     */     
/*  70 */     for (XMLResource.XMLMap xmlMap : this.xmlMaps.values()) {
/*     */       
/*  72 */       xmlInfo = xmlMap.getInfo(element);
/*  73 */       if (xmlInfo != null) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return xmlInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EClassifier getClassifier(String namespaceURI, String name) {
/*  84 */     EClassifier classifier = null;
/*     */     
/*  86 */     for (XMLResource.XMLMap xmlMap : this.xmlMaps.values()) {
/*     */       
/*  88 */       classifier = xmlMap.getClassifier(namespaceURI, name);
/*  89 */       if (classifier != null) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return classifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getPackage(String namespace) {
/* 106 */     XMLResource.XMLMap xmlMap = this.ecore2xmlRegistry.getXMLMap(namespace);
/*     */     
/* 108 */     if (xmlMap != null)
/*     */     {
/* 110 */       this.xmlMaps.put(namespace, xmlMap);
/*     */     }
/*     */     
/* 113 */     return super.getPackage(namespace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(EClassifier eClassifier) {
/* 124 */     XMLResource.XMLInfo xmlInfo = getInfo((ENamedElement)eClassifier);
/*     */     
/* 126 */     if (xmlInfo != null)
/*     */     {
/* 128 */       return xmlInfo.getName();
/*     */     }
/*     */     
/* 131 */     return super.getName(eClassifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(EStructuralFeature eStructuralFeature) {
/* 142 */     XMLResource.XMLInfo xmlInfo = getInfo((ENamedElement)eStructuralFeature);
/*     */     
/* 144 */     if (xmlInfo != null)
/*     */     {
/* 146 */       return xmlInfo.getName();
/*     */     }
/*     */     
/* 149 */     return super.getName(eStructuralFeature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespace(EPackage ePackage) {
/* 160 */     XMLResource.XMLInfo xmlInfo = getInfo((ENamedElement)ePackage);
/*     */     
/* 162 */     if (xmlInfo != null)
/*     */     {
/* 164 */       return xmlInfo.getTargetNamespace();
/*     */     }
/*     */     
/* 167 */     return super.getNamespace(ePackage);
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
/*     */   public EClassifier getType(EPackage ePackage, String name) {
/* 179 */     EClassifier type = super.getType(ePackage, name);
/*     */     
/* 181 */     if (type == null)
/*     */     {
/* 183 */       type = getClassifier(ePackage.getNsURI(), name);
/*     */     }
/*     */     
/* 186 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureKind(EStructuralFeature eStructuralFeature) {
/* 197 */     XMLResource.XMLInfo xmlInfo = getInfo((ENamedElement)eStructuralFeature);
/*     */     
/* 199 */     if (xmlInfo != null)
/*     */     {
/* 201 */       switch (xmlInfo.getXMLRepresentation()) {
/*     */         
/*     */         case 0:
/* 204 */           return 4;
/*     */         case 1:
/* 206 */           return 2;
/*     */       } 
/*     */     
/*     */     }
/* 210 */     return super.getFeatureKind(eStructuralFeature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureKindSpecific() {
/* 221 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xm\\util\Ecore2XMLExtendedMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */