/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.common.util.UniqueEList;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
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
/*     */ public class XMLMapImpl
/*     */   implements XMLResource.XMLMap
/*     */ {
/*     */   protected static final String XSD2ECORE = "http:///org/eclipse/emf/mapping/xsd2ecore/XSD2Ecore";
/*     */   protected Map<ENamedElement, XMLResource.XMLInfo> ecoreToXMLInfo;
/*     */   protected EPackage noNamespacePkg;
/*     */   protected String idAttributeName;
/*     */   protected Map<String, Map<String, EClassifier>> urisToNamesToClassifiers;
/*     */   protected Map<EClass, EList<EStructuralFeature>> eClassToFeatures;
/*     */   protected Set<EPackage> processedEPackages;
/*  55 */   protected EPackage.Registry packageRegistry = EPackage.Registry.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLMapImpl() {
/*  63 */     this.ecoreToXMLInfo = new HashMap<ENamedElement, XMLResource.XMLInfo>();
/*  64 */     this.processedEPackages = new HashSet<EPackage>();
/*  65 */     this.eClassToFeatures = new HashMap<EClass, EList<EStructuralFeature>>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(ENamedElement element, XMLResource.XMLInfo info) {
/*  74 */     this.ecoreToXMLInfo.put(element, info);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLResource.XMLInfo getInfo(ENamedElement element) {
/*  83 */     XMLResource.XMLInfo result = this.ecoreToXMLInfo.get(element);
/*  84 */     if (result == null) {
/*     */       
/*  86 */       for (EAnnotation eAnnotation : element.getEAnnotations()) {
/*     */         
/*  88 */         if ("http:///org/eclipse/emf/mapping/xsd2ecore/XSD2Ecore".equals(eAnnotation.getSource())) {
/*     */           
/*  90 */           result = new XMLInfoImpl();
/*  91 */           EMap<String, String> details = eAnnotation.getDetails();
/*  92 */           result.setName((String)details.get("name"));
/*  93 */           result.setTargetNamespace((String)details.get("targetNamespace"));
/*  94 */           String representation = (String)details.get("representation");
/*  95 */           if ("element".equals(representation)) {
/*     */             
/*  97 */             result.setXMLRepresentation(0); continue;
/*     */           } 
/*  99 */           if ("attribute".equals(representation)) {
/*     */             
/* 101 */             result.setXMLRepresentation(1); continue;
/*     */           } 
/* 103 */           if ("simple-content".equals(representation))
/*     */           {
/* 105 */             result.setXMLRepresentation(2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 110 */       if (result != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         this.ecoreToXMLInfo.put(element, result);
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoNamespacePackage(EPackage pkg) {
/* 126 */     this.noNamespacePkg = pkg;
/*     */   }
/*     */ 
/*     */   
/*     */   public EPackage getNoNamespacePackage() {
/* 131 */     return this.noNamespacePkg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIDAttributeName(String name) {
/* 136 */     this.idAttributeName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIDAttributeName() {
/* 141 */     return this.idAttributeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageRegistry(EPackage.Registry packageRegistry) {
/* 146 */     this.packageRegistry = packageRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   public EPackage.Registry getPackageRegistry() {
/* 151 */     return this.packageRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   public EClassifier getClassifier(String namespaceURI, String name) {
/* 156 */     EPackage ePackage = this.packageRegistry.getEPackage(namespaceURI);
/* 157 */     if (ePackage != null) {
/*     */       
/* 159 */       if (this.processedEPackages.add(ePackage)) {
/*     */         
/* 161 */         if (this.urisToNamesToClassifiers == null)
/*     */         {
/* 163 */           this.urisToNamesToClassifiers = new HashMap<String, Map<String, EClassifier>>();
/*     */         }
/*     */         
/* 166 */         getInfoForClassifiers(ePackage);
/*     */         
/* 168 */         for (Map.Entry<ENamedElement, XMLResource.XMLInfo> entry : this.ecoreToXMLInfo.entrySet()) {
/*     */           
/* 170 */           Object key = entry.getKey();
/*     */ 
/*     */ 
/*     */           
/* 174 */           if (key instanceof EClassifier) {
/*     */             
/* 176 */             EClassifier eClassifier = (EClassifier)key;
/* 177 */             if (eClassifier.getEPackage() == ePackage) {
/*     */               
/* 179 */               XMLResource.XMLInfo info = entry.getValue();
/* 180 */               String uri = info.getTargetNamespace();
/* 181 */               if (uri == null)
/*     */               {
/* 183 */                 uri = namespaceURI;
/*     */               }
/*     */               
/* 186 */               if (info.getName() != null) {
/*     */                 
/* 188 */                 Map<String, EClassifier> map = this.urisToNamesToClassifiers.get(uri);
/*     */                 
/* 190 */                 if (map == null) {
/*     */                   
/* 192 */                   map = new HashMap<String, EClassifier>();
/* 193 */                   this.urisToNamesToClassifiers.put(uri, map);
/*     */                 } 
/*     */                 
/* 196 */                 map.put(info.getName(), eClassifier);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 203 */       Map<String, EClassifier> namesToClassifiers = this.urisToNamesToClassifiers.get(namespaceURI);
/* 204 */       if (namesToClassifiers != null)
/*     */       {
/* 206 */         return namesToClassifiers.get(name);
/*     */       }
/*     */     } 
/*     */     
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getInfoForClassifiers(EPackage ePackage) {
/* 219 */     for (EClassifier eClassifier : ePackage.getEClassifiers())
/*     */     {
/* 221 */       getInfo((ENamedElement)eClassifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name) {
/* 227 */     for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*     */       
/* 229 */       EStructuralFeature feature = eClass.getEStructuralFeature(i);
/* 230 */       XMLResource.XMLInfo info = getInfo((ENamedElement)feature);
/*     */       
/* 232 */       if (info != null) {
/*     */         
/* 234 */         String infoURI = info.getTargetNamespace();
/* 235 */         String infoName = info.getName();
/* 236 */         if (namespaceURI == null) {
/*     */ 
/*     */ 
/*     */           
/* 240 */           if (infoURI == null && (name.equals(infoName) || (infoName == null && name.length() == 0)))
/*     */           {
/* 242 */             return feature;
/*     */           }
/*     */         }
/* 245 */         else if (namespaceURI.equals(infoURI) && name.equals(infoName)) {
/*     */           
/* 247 */           return feature;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     return null;
/*     */   }
/*     */   
/*     */   public List<EStructuralFeature> getFeatures(EClass eClass) {
/*     */     UniqueEList uniqueEList;
/* 257 */     EList<EStructuralFeature> result = this.eClassToFeatures.get(eClass);
/* 258 */     if (result == null) {
/*     */       
/* 260 */       uniqueEList = new UniqueEList();
/* 261 */       for (EClass eSuperType : eClass.getESuperTypes())
/*     */       {
/* 263 */         uniqueEList.addAll(getFeatures(eSuperType));
/*     */       }
/* 265 */       EList eList1 = eClass.getEAttributes();
/* 266 */       uniqueEList.addAll((Collection)eList1);
/* 267 */       EList eList2 = eClass.getEReferences();
/* 268 */       uniqueEList.addAll((Collection)eList2);
/*     */       
/* 270 */       EAnnotation eAnnotation = eClass.getEAnnotation("http:///org/eclipse/emf/mapping/xsd2ecore/XSD2Ecore");
/* 271 */       if (eAnnotation != null) {
/*     */         
/* 273 */         String featureOrder = (String)eAnnotation.getDetails().get("feature-order");
/* 274 */         if (featureOrder != null) {
/*     */           
/* 276 */           int size = uniqueEList.size();
/* 277 */           int index = size - eList2.size() - eList1.size();
/* 278 */           for (StringTokenizer stringTokenizer = new StringTokenizer(featureOrder); stringTokenizer.hasMoreTokens(); index++) {
/*     */             
/* 280 */             String featureName = stringTokenizer.nextToken();
/* 281 */             for (int i = index; i < size; i++) {
/*     */               
/* 283 */               EStructuralFeature eStructuralFeature = (EStructuralFeature)uniqueEList.get(i);
/* 284 */               if (featureName.equals(eStructuralFeature.getName())) {
/*     */                 
/* 286 */                 uniqueEList.move(index, eStructuralFeature);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 293 */       this.eClassToFeatures.put(eClass, uniqueEList);
/*     */     } 
/*     */     
/* 296 */     return (List<EStructuralFeature>)uniqueEList;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLMapImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */