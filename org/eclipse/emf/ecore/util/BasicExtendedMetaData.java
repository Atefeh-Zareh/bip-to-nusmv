/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.UniqueEList;
/*      */ import org.eclipse.emf.ecore.EAnnotation;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EModelElement;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BasicExtendedMetaData
/*      */   implements ExtendedMetaData
/*      */ {
/*      */   protected String annotationURI;
/*      */   protected EPackage.Registry registry;
/*      */   protected EPackage.Registry demandRegistry;
/*      */   protected Map<EModelElement, Object> extendedMetaDataHolderCache;
/*      */   protected Map<EModelElement, EAnnotation> annotationMap;
/*      */   protected static final String UNINITIALIZED_STRING = "uninitialized";
/*      */   protected static final int UNINITIALIZED_INT = -2;
/*      */   
/*      */   public BasicExtendedMetaData() {
/*   60 */     this("http:///org/eclipse/emf/ecore/util/ExtendedMetaData", EPackage.Registry.INSTANCE);
/*      */   }
/*      */ 
/*      */   
/*      */   public BasicExtendedMetaData(EPackage.Registry registry) {
/*   65 */     this("http:///org/eclipse/emf/ecore/util/ExtendedMetaData", registry);
/*      */   }
/*      */ 
/*      */   
/*      */   public BasicExtendedMetaData(String annotationURI, EPackage.Registry registry) {
/*   70 */     this(annotationURI, registry, (Map<EModelElement, EAnnotation>)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public BasicExtendedMetaData(String annotationURI, EPackage.Registry registry, Map<EModelElement, EAnnotation> annotationMap) {
/*   75 */     this.annotationURI = annotationURI.intern();
/*   76 */     this.registry = registry;
/*   77 */     this.demandRegistry = (EPackage.Registry)new EPackageRegistryImpl();
/*   78 */     this.annotationMap = annotationMap;
/*      */     
/*   80 */     if (annotationURI != "http:///org/eclipse/emf/ecore/util/ExtendedMetaData")
/*      */     {
/*   82 */       this.extendedMetaDataHolderCache = new HashMap<EModelElement, Object>();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EAnnotation getAnnotation(EModelElement eModelElement, boolean demandCreate) {
/*   88 */     if (this.annotationMap != null) {
/*      */       
/*   90 */       EAnnotation eAnnotation = this.annotationMap.get(eModelElement);
/*   91 */       if (eAnnotation == null && demandCreate) {
/*      */         
/*   93 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/*   94 */         eAnnotation.setSource(this.annotationURI);
/*   95 */         this.annotationMap.put(eModelElement, eAnnotation);
/*      */       } 
/*   97 */       return eAnnotation;
/*      */     } 
/*      */ 
/*      */     
/*  101 */     EAnnotation result = eModelElement.getEAnnotation(this.annotationURI);
/*  102 */     if (result == null && demandCreate) {
/*      */       
/*  104 */       result = EcoreFactory.eINSTANCE.createEAnnotation();
/*  105 */       result.setSource(this.annotationURI);
/*  106 */       eModelElement.getEAnnotations().add(result);
/*      */     } 
/*  108 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EClassifier getType(EPackage ePackage, String name) {
/*  114 */     return getExtendedMetaData(ePackage).getType(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public EPackage getPackage(String namespace) {
/*  119 */     EPackage ePackage = this.registry.getEPackage(namespace);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  126 */     return ePackage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void putPackage(String namespace, EPackage ePackage) {
/*  131 */     this.registry.put(namespace, ePackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public EClass getDocumentRoot(EPackage ePackage) {
/*  136 */     return (EClass)getType(ePackage, "");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDocumentRoot(EClass eClass) {
/*  141 */     setName((EClassifier)eClass, "");
/*  142 */     setContentKind(eClass, 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDocumentRoot(EClass eClass) {
/*  147 */     return "".equals(getName((EClassifier)eClass));
/*      */   }
/*      */ 
/*      */   
/*      */   public EReference getXMLNSPrefixMapFeature(EClass eClass) {
/*  152 */     if (getContentKind(eClass) == 3) {
/*      */       
/*  154 */       EList<EReference> eList = eClass.getEAllReferences();
/*  155 */       for (int i = 0, size = eList.size(); i < size; i++) {
/*      */         
/*  157 */         EReference eReference = eList.get(i);
/*  158 */         if ("xmlns:prefix".equals(getName((EStructuralFeature)eReference)))
/*      */         {
/*  160 */           return eReference;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  165 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EReference getXSISchemaLocationMapFeature(EClass eClass) {
/*  170 */     if (getContentKind(eClass) == 3) {
/*      */       
/*  172 */       EList<EReference> eList = eClass.getEAllReferences();
/*  173 */       for (int i = 0, size = eList.size(); i < size; i++) {
/*      */         
/*  175 */         EReference eReference = eList.get(i);
/*  176 */         if ("xsi:schemaLocation".equals(getName((EStructuralFeature)eReference)))
/*      */         {
/*  178 */           return eReference;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  183 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isQualified(EPackage ePackage) {
/*  188 */     return getExtendedMetaData(ePackage).isQualified();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean basicIsQualified(EPackage ePackage) {
/*  193 */     EAnnotation eAnnotation = getAnnotation((EModelElement)ePackage, false);
/*  194 */     return !(eAnnotation != null && "false".equals(eAnnotation.getDetails().get("qualified")));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQualified(EPackage ePackage, boolean isQualified) {
/*  199 */     if (!isQualified) {
/*      */       
/*  201 */       EAnnotation eAnnotation = getAnnotation((EModelElement)ePackage, true);
/*  202 */       eAnnotation.getDetails().put("qualified", "false");
/*      */     }
/*      */     else {
/*      */       
/*  206 */       EAnnotation eAnnotation = getAnnotation((EModelElement)ePackage, false);
/*  207 */       if (eAnnotation != null)
/*      */       {
/*  209 */         eAnnotation.getDetails().remove("qualified");
/*      */       }
/*      */     } 
/*  212 */     getExtendedMetaData(ePackage).setQualified(isQualified);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespace(EPackage ePackage) {
/*  217 */     if (isQualified(ePackage))
/*      */     {
/*  219 */       return ePackage.getNsURI();
/*      */     }
/*      */ 
/*      */     
/*  223 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespace(EClassifier eClassifier) {
/*  229 */     return getNamespace(eClassifier.getEPackage());
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespace(EStructuralFeature eStructuralFeature) {
/*  234 */     return getExtendedMetaData(eStructuralFeature).getNamespace();
/*      */   }
/*      */ 
/*      */   
/*      */   public String basicGetNamespace(EStructuralFeature eStructuralFeature) {
/*  239 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/*  240 */     if (eAnnotation == null)
/*      */     {
/*  242 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  246 */     String result = (String)eAnnotation.getDetails().get("namespace");
/*  247 */     if ("##targetNamespace".equals(result))
/*      */     {
/*  249 */       return getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
/*      */     }
/*      */ 
/*      */     
/*  253 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNamespace(EStructuralFeature eStructuralFeature, String namespace) {
/*  260 */     String packageNamespace = getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
/*  261 */     String convertedNamespace = namespace;
/*  262 */     if ((namespace == null) ? (packageNamespace == null) : namespace.equals(packageNamespace))
/*      */     {
/*  264 */       convertedNamespace = "##targetNamespace";
/*      */     }
/*      */     
/*  267 */     if (convertedNamespace != null) {
/*      */       
/*  269 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/*  270 */       eAnnotation.getDetails().put("namespace", convertedNamespace);
/*      */     }
/*      */     else {
/*      */       
/*  274 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/*  275 */       if (eAnnotation != null)
/*      */       {
/*  277 */         eAnnotation.getDetails().remove("namespace");
/*      */       }
/*      */     } 
/*  280 */     getExtendedMetaData(eStructuralFeature).setNamespace(namespace);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getName(EClassifier eClassifier) {
/*  285 */     return getExtendedMetaData(eClassifier).getName();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetName(EClassifier eClassifier) {
/*  290 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eClassifier, false);
/*  291 */     if (eAnnotation != null) {
/*      */       
/*  293 */       String result = (String)eAnnotation.getDetails().get("name");
/*  294 */       if (result != null)
/*      */       {
/*  296 */         return result;
/*      */       }
/*      */     } 
/*  299 */     return eClassifier.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setName(EClassifier eClassifier, String name) {
/*  304 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eClassifier, true);
/*  305 */     eAnnotation.getDetails().put("name", name);
/*  306 */     getExtendedMetaData(eClassifier).setName(name);
/*  307 */     EPackage ePackage = eClassifier.getEPackage();
/*  308 */     if (ePackage != null)
/*      */     {
/*  310 */       getExtendedMetaData(ePackage).rename(eClassifier, name);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAnonymous(EClassifier eClassifier) {
/*  316 */     String name = getExtendedMetaData(eClassifier).getName();
/*  317 */     return !(name.length() != 0 && name.indexOf("_._") == -1);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getName(EStructuralFeature eStructuralFeature) {
/*  322 */     return getExtendedMetaData(eStructuralFeature).getName();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetName(EStructuralFeature eStructuralFeature) {
/*  327 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/*  328 */     if (eAnnotation != null) {
/*      */       
/*  330 */       String result = (String)eAnnotation.getDetails().get("name");
/*  331 */       if (result != null)
/*      */       {
/*  333 */         return result;
/*      */       }
/*      */     } 
/*  336 */     return eStructuralFeature.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setName(EStructuralFeature eStructuralFeature, String name) {
/*  341 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/*  342 */     eAnnotation.getDetails().put("name", name);
/*  343 */     getExtendedMetaData(eStructuralFeature).setName(name);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQualifiedName(String defaultNamespace, EClassifier eClassifier) {
/*  348 */     String namespace = getNamespace(eClassifier);
/*  349 */     String name = getName(eClassifier);
/*  350 */     if (namespace == null)
/*      */     {
/*  352 */       return (namespace == defaultNamespace) ? name : ("#" + name);
/*      */     }
/*      */ 
/*      */     
/*  356 */     return namespace.equals(defaultNamespace) ? name : (String.valueOf(namespace) + "#" + name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getQualifiedName(String defaultNamespace, EStructuralFeature eStructuralFeature) {
/*  362 */     String namespace = getNamespace(eStructuralFeature);
/*  363 */     String name = getName(eStructuralFeature);
/*  364 */     if (namespace == null)
/*      */     {
/*  366 */       return (namespace == defaultNamespace) ? name : ("#" + name);
/*      */     }
/*      */ 
/*      */     
/*  370 */     return namespace.equals(defaultNamespace) ? name : (String.valueOf(namespace) + "#" + name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EClassifier getType(String namespace, String name) {
/*  376 */     EPackage ePackage = getPackage(namespace);
/*  377 */     return (ePackage == null) ? null : getType(ePackage, name);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getAttribute(String namespace, String name) {
/*  382 */     EPackage ePackage = getPackage(namespace);
/*  383 */     if (ePackage != null) {
/*      */       
/*  385 */       EClass documentRoot = getDocumentRoot(ePackage);
/*  386 */       if (documentRoot != null)
/*      */       {
/*  388 */         return getLocalAttribute(documentRoot, namespace, name);
/*      */       }
/*      */     } 
/*      */     
/*  392 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getElement(String namespace, String name) {
/*  397 */     EPackage ePackage = getPackage(namespace);
/*  398 */     if (ePackage != null) {
/*      */       
/*  400 */       EClass documentRoot = getDocumentRoot(ePackage);
/*  401 */       if (documentRoot != null)
/*      */       {
/*  403 */         return getLocalElement(documentRoot, namespace, name);
/*      */       }
/*      */     } 
/*      */     
/*  407 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFeatureKind(EStructuralFeature eStructuralFeature) {
/*  412 */     return getExtendedMetaData(eStructuralFeature).getFeatureKind();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetFeatureKind(EStructuralFeature eStructuralFeature) {
/*  417 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/*  418 */     if (eAnnotation != null) {
/*      */       
/*  420 */       Object kind = eAnnotation.getDetails().get("kind");
/*  421 */       if (kind != null)
/*      */       {
/*  423 */         for (int i = 1; i < FEATURE_KINDS.length; i++) {
/*      */           
/*  425 */           if (FEATURE_KINDS[i].equals(kind))
/*      */           {
/*  427 */             return i;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  433 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFeatureKind(EStructuralFeature eStructuralFeature, int kind) {
/*  438 */     if (kind > 0 && kind < FEATURE_KINDS.length) {
/*      */       
/*  440 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/*  441 */       eAnnotation.getDetails().put("kind", FEATURE_KINDS[kind]);
/*      */     }
/*      */     else {
/*      */       
/*  445 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/*  446 */       if (eAnnotation != null)
/*      */       {
/*  448 */         eAnnotation.getDetails().remove("kind");
/*      */       }
/*      */     } 
/*  451 */     getExtendedMetaData(eStructuralFeature).setFeatureKind(kind);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getContentKind(EClass eClass) {
/*  456 */     return getExtendedMetaData((EClassifier)eClass).getContentKind();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetContentKind(EClass eClass) {
/*  461 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eClass, false);
/*  462 */     if (eAnnotation != null) {
/*      */       
/*  464 */       Object kind = eAnnotation.getDetails().get("kind");
/*  465 */       if (kind != null)
/*      */       {
/*  467 */         for (int i = 1; i < CONTENT_KINDS.length; i++) {
/*      */           
/*  469 */           if (CONTENT_KINDS[i].equals(kind))
/*      */           {
/*  471 */             return i;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  477 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setContentKind(EClass eClass, int kind) {
/*  482 */     if (kind > 0 && kind < CONTENT_KINDS.length) {
/*      */       
/*  484 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eClass, true);
/*  485 */       eAnnotation.getDetails().put("kind", CONTENT_KINDS[kind]);
/*      */     }
/*      */     else {
/*      */       
/*  489 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eClass, false);
/*  490 */       if (eAnnotation != null)
/*      */       {
/*  492 */         eAnnotation.getDetails().remove("kind");
/*      */       }
/*      */     } 
/*  495 */     getExtendedMetaData((EClassifier)eClass).setContentKind(kind);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDerivationKind(EDataType eDataType) {
/*  500 */     return getExtendedMetaData((EClassifier)eDataType).getDerivationKind();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetDerivationKind(EClassifier eClassifier) {
/*  505 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eClassifier, false);
/*  506 */     if (eAnnotation != null) {
/*      */       
/*  508 */       EMap<String, String> details = eAnnotation.getDetails();
/*  509 */       Object kind = details.get("restriction");
/*  510 */       if (kind != null)
/*      */       {
/*  512 */         return 1;
/*      */       }
/*  514 */       kind = details.get("list");
/*  515 */       if (kind != null)
/*      */       {
/*  517 */         return 2;
/*      */       }
/*  519 */       kind = details.get("union");
/*  520 */       if (kind != null)
/*      */       {
/*  522 */         return 3;
/*      */       }
/*      */     } 
/*      */     
/*  526 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public EDataType getBaseType(EDataType eDataType) {
/*  531 */     return getExtendedMetaData((EClassifier)eDataType).getBaseType();
/*      */   }
/*      */ 
/*      */   
/*      */   public EDataType basicGetBaseType(EDataType eDataType) {
/*  536 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  537 */     if (eAnnotation != null) {
/*      */       
/*  539 */       EMap<String, String> details = eAnnotation.getDetails();
/*  540 */       String baseType = (String)details.get("baseType");
/*  541 */       if (baseType != null) {
/*      */         
/*  543 */         int index = baseType.lastIndexOf("#");
/*  544 */         EClassifier type = 
/*  545 */           (index == -1) ? 
/*  546 */           getType(eDataType.getEPackage(), baseType) : (
/*  547 */           (index == 0) ? 
/*  548 */           getType((String)null, baseType.substring(1)) : 
/*  549 */           getType(baseType.substring(0, index), baseType.substring(index + 1)));
/*  550 */         if (type instanceof EDataType)
/*      */         {
/*  552 */           return (EDataType)type;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  557 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBaseType(EDataType eDataType, EDataType baseType) {
/*  562 */     if (baseType == null) {
/*      */       
/*  564 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  565 */       if (eAnnotation != null)
/*      */       {
/*  567 */         eAnnotation.getDetails().remove("baseType");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  572 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/*  573 */       eAnnotation.getDetails().put("baseType", getQualifiedName(getNamespace((EClassifier)eDataType), (EClassifier)baseType));
/*      */     } 
/*  575 */     getExtendedMetaData((EClassifier)eDataType).setBaseType(baseType);
/*      */   }
/*      */ 
/*      */   
/*      */   public EDataType getItemType(EDataType eDataType) {
/*  580 */     return getExtendedMetaData((EClassifier)eDataType).getItemType();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EDataType basicGetItemType(EDataType eDataType) {
/*  585 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  586 */     if (eAnnotation != null) {
/*      */       
/*  588 */       EMap<String, String> details = eAnnotation.getDetails();
/*  589 */       String itemType = (String)details.get("itemType");
/*  590 */       if (itemType != null) {
/*      */         
/*  592 */         int index = itemType.lastIndexOf("#");
/*  593 */         EClassifier type = 
/*  594 */           (index == -1) ? 
/*  595 */           getType(eDataType.getEPackage(), itemType) : (
/*  596 */           (index == 0) ? 
/*  597 */           getType((String)null, itemType.substring(1)) : 
/*  598 */           getType(itemType.substring(0, index), itemType.substring(index + 1)));
/*  599 */         if (type instanceof EDataType)
/*      */         {
/*  601 */           return (EDataType)type;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  606 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setItemType(EDataType eDataType, EDataType itemType) {
/*  611 */     if (itemType == null) {
/*      */       
/*  613 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  614 */       if (eAnnotation != null)
/*      */       {
/*  616 */         eAnnotation.getDetails().remove("itemType");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  621 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/*  622 */       eAnnotation.getDetails().put("itemType", getQualifiedName(getNamespace((EClassifier)eDataType), (EClassifier)itemType));
/*      */     } 
/*  624 */     getExtendedMetaData((EClassifier)eDataType).setItemType(itemType);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<EDataType> getMemberTypes(EDataType eDataType) {
/*  629 */     return getExtendedMetaData((EClassifier)eDataType).getMemberTypes();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<EDataType> basicGetMemberTypes(EDataType eDataType) {
/*  634 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  635 */     if (eAnnotation != null) {
/*      */       
/*  637 */       String memberTypes = (String)eAnnotation.getDetails().get("memberTypes");
/*  638 */       if (memberTypes != null) {
/*      */         
/*  640 */         List<EDataType> result = new ArrayList<EDataType>();
/*  641 */         for (StringTokenizer stringTokenizer = new StringTokenizer(memberTypes); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/*  643 */           String member = stringTokenizer.nextToken();
/*  644 */           int index = member.lastIndexOf("#");
/*  645 */           EClassifier type = 
/*  646 */             (index == -1) ? 
/*  647 */             getType(eDataType.getEPackage(), member) : (
/*  648 */             (index == 0) ? 
/*  649 */             getType((String)null, member.substring(1)) : 
/*  650 */             getType(member.substring(0, index), member.substring(index + 1)));
/*  651 */           if (type instanceof EDataType)
/*      */           {
/*  653 */             result.add((EDataType)type);
/*      */           }
/*      */         } 
/*  656 */         return result;
/*      */       } 
/*      */     } 
/*      */     
/*  660 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMemberTypes(EDataType eDataType, List<EDataType> memberTypes) {
/*  665 */     if (memberTypes.isEmpty()) {
/*      */       
/*  667 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/*  668 */       if (eAnnotation != null)
/*      */       {
/*  670 */         eAnnotation.getDetails().remove("memberTypes");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  675 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/*  676 */       String namespace = getNamespace((EClassifier)eDataType);
/*  677 */       StringBuffer result = new StringBuffer();
/*  678 */       for (int i = 0, size = memberTypes.size(); i < size; i++) {
/*      */         
/*  680 */         result.append(getQualifiedName(namespace, (EClassifier)memberTypes.get(i)));
/*  681 */         result.append(' ');
/*      */       } 
/*  683 */       eAnnotation.getDetails().put("memberTypes", result.substring(0, result.length() - 1));
/*      */     } 
/*  685 */     getExtendedMetaData((EClassifier)eDataType).setMemberTypes(memberTypes);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isFeatureKindSpecific() {
/*  690 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isFeatureNamespaceMatchingLax() {
/*  695 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getLocalAttribute(EClass eClass, String namespace, String name) {
/*  700 */     EStructuralFeature result = null;
/*  701 */     if (isFeatureKindSpecific()) {
/*      */       
/*  703 */       List<EStructuralFeature> allAttributes = getAllAttributes(eClass);
/*  704 */       for (int i = 0, size = allAttributes.size(); i < size; i++) {
/*      */         
/*  706 */         EStructuralFeature eStructuralFeature = allAttributes.get(i);
/*  707 */         if (name.equals(getName(eStructuralFeature))) {
/*      */           
/*  709 */           String featureNamespace = getNamespace(eStructuralFeature);
/*  710 */           if (namespace == null) {
/*      */             
/*  712 */             if (featureNamespace == null)
/*      */             {
/*  714 */               return eStructuralFeature;
/*      */             }
/*  716 */             if (result == null)
/*      */             {
/*  718 */               result = eStructuralFeature;
/*      */             }
/*      */           } else {
/*  721 */             if (namespace.equals(featureNamespace))
/*      */             {
/*  723 */               return eStructuralFeature;
/*      */             }
/*  725 */             if (featureNamespace == null && result == null)
/*      */             {
/*  727 */               result = eStructuralFeature;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  734 */       for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */         
/*  736 */         EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/*  737 */         switch (getFeatureKind(eStructuralFeature)) {
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 2:
/*  742 */             if (name.equals(getName(eStructuralFeature))) {
/*      */               
/*  744 */               String featureNamespace = getNamespace(eStructuralFeature);
/*  745 */               if (namespace == null) {
/*      */                 
/*  747 */                 if (featureNamespace == null)
/*      */                 {
/*  749 */                   return eStructuralFeature;
/*      */                 }
/*  751 */                 if (result == null)
/*      */                 {
/*  753 */                   result = eStructuralFeature; } 
/*      */                 break;
/*      */               } 
/*  756 */               if (namespace.equals(featureNamespace))
/*      */               {
/*  758 */                 return eStructuralFeature;
/*      */               }
/*  760 */               if (featureNamespace == null && result == null)
/*      */               {
/*  762 */                 result = eStructuralFeature;
/*      */               }
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/*  771 */     return isFeatureNamespaceMatchingLax() ? result : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getAttribute(EClass eClass, String namespace, String name) {
/*  776 */     EStructuralFeature result = getLocalAttribute(eClass, namespace, name);
/*  777 */     if (result == null) {
/*      */       
/*  779 */       result = getAttribute(namespace, name);
/*  780 */       if (result != null && getAffiliation(eClass, result) == null)
/*      */       {
/*  782 */         return null;
/*      */       }
/*      */     } 
/*  785 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature getLocalElement(EClass eClass, String namespace, String name) {
/*  790 */     EStructuralFeature result = null;
/*  791 */     if (isFeatureKindSpecific()) {
/*      */       
/*  793 */       List<EStructuralFeature> allElements = getAllElements(eClass);
/*  794 */       for (int i = 0, size = allElements.size(); i < size; i++) {
/*      */         
/*  796 */         EStructuralFeature eStructuralFeature = allElements.get(i);
/*  797 */         if (name.equals(getName(eStructuralFeature))) {
/*      */           
/*  799 */           String featureNamespace = getNamespace(eStructuralFeature);
/*  800 */           if (namespace == null) {
/*      */             
/*  802 */             if (featureNamespace == null)
/*      */             {
/*  804 */               return eStructuralFeature;
/*      */             }
/*  806 */             if (result == null)
/*      */             {
/*  808 */               result = eStructuralFeature;
/*      */             }
/*      */           } else {
/*  811 */             if (namespace.equals(featureNamespace))
/*      */             {
/*  813 */               return eStructuralFeature;
/*      */             }
/*  815 */             if (featureNamespace == null && result == null)
/*      */             {
/*  817 */               result = eStructuralFeature;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  824 */       for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */         
/*  826 */         EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/*  827 */         switch (getFeatureKind(eStructuralFeature)) {
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 4:
/*  832 */             if (name.equals(getName(eStructuralFeature))) {
/*      */               
/*  834 */               String featureNamespace = getNamespace(eStructuralFeature);
/*  835 */               if (namespace == null) {
/*      */                 
/*  837 */                 if (featureNamespace == null)
/*      */                 {
/*  839 */                   return eStructuralFeature;
/*      */                 }
/*  841 */                 if (result == null)
/*      */                 {
/*  843 */                   result = eStructuralFeature; } 
/*      */                 break;
/*      */               } 
/*  846 */               if (namespace.equals(featureNamespace))
/*      */               {
/*  848 */                 return eStructuralFeature;
/*      */               }
/*  850 */               if (featureNamespace == null && result == null)
/*      */               {
/*  852 */                 result = eStructuralFeature;
/*      */               }
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/*  861 */     return isFeatureNamespaceMatchingLax() ? result : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getElement(EClass eClass, String namespace, String name) {
/*  866 */     EStructuralFeature result = getLocalElement(eClass, namespace, name);
/*  867 */     if (result == null) {
/*      */       
/*  869 */       result = getElement(namespace, name);
/*  870 */       if (result != null && getAffiliation(eClass, result) == null)
/*      */       {
/*  872 */         return null;
/*      */       }
/*      */     } 
/*  875 */     return result;
/*      */   }
/*      */   
/*      */   public List<EStructuralFeature> getAllAttributes(EClass eClass) {
/*      */     UniqueEList<EStructuralFeature> uniqueEList;
/*  880 */     EList<EClass> eList = eClass.getESuperTypes();
/*  881 */     List<EStructuralFeature> result = null;
/*  882 */     boolean changeable = false;
/*  883 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*      */       
/*  885 */       EClass eSuperType = eList.get(i);
/*  886 */       List<EStructuralFeature> allAttributes = getAllAttributes(eSuperType);
/*  887 */       if (!allAttributes.isEmpty())
/*      */       {
/*  889 */         if (result == null) {
/*      */           
/*  891 */           result = allAttributes;
/*      */         }
/*      */         else {
/*      */           
/*  895 */           if (!changeable) {
/*      */             
/*  897 */             changeable = true;
/*  898 */             uniqueEList = new UniqueEList(result);
/*      */           } 
/*  900 */           uniqueEList.addAll(allAttributes);
/*      */         } 
/*      */       }
/*      */     } 
/*  904 */     List<EStructuralFeature> attributes = getAttributes(eClass);
/*  905 */     if (!attributes.isEmpty()) {
/*      */       
/*  907 */       if (uniqueEList == null)
/*      */       {
/*  909 */         return attributes;
/*      */       }
/*      */ 
/*      */       
/*  913 */       if (!changeable)
/*      */       {
/*  915 */         uniqueEList = new UniqueEList((Collection)uniqueEList);
/*      */       }
/*  917 */       uniqueEList.addAll(attributes);
/*  918 */       return (List<EStructuralFeature>)uniqueEList;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  923 */     return (uniqueEList == null) ? Collections.<EStructuralFeature>emptyList() : (List<EStructuralFeature>)uniqueEList;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<EStructuralFeature> getAllElements(EClass eClass) {
/*      */     UniqueEList<EStructuralFeature> uniqueEList;
/*  929 */     EList<EClass> eList = eClass.getESuperTypes();
/*  930 */     List<EStructuralFeature> result = null;
/*  931 */     boolean changeable = false;
/*  932 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*      */       
/*  934 */       EClass eSuperType = eList.get(i);
/*  935 */       List<EStructuralFeature> allElements = getAllElements(eSuperType);
/*  936 */       if (!allElements.isEmpty())
/*      */       {
/*  938 */         if (result == null) {
/*      */           
/*  940 */           result = allElements;
/*      */         }
/*      */         else {
/*      */           
/*  944 */           if (!changeable) {
/*      */             
/*  946 */             changeable = true;
/*  947 */             uniqueEList = new UniqueEList(result);
/*      */           } 
/*  949 */           uniqueEList.addAll(allElements);
/*      */         } 
/*      */       }
/*      */     } 
/*  953 */     List<EStructuralFeature> elements = getElements(eClass);
/*  954 */     if (!elements.isEmpty()) {
/*      */       
/*  956 */       if (uniqueEList == null)
/*      */       {
/*  958 */         return elements;
/*      */       }
/*      */ 
/*      */       
/*  962 */       if (!changeable)
/*      */       {
/*  964 */         uniqueEList = new UniqueEList((Collection)uniqueEList);
/*      */       }
/*  966 */       uniqueEList.addAll(elements);
/*  967 */       return (List<EStructuralFeature>)uniqueEList;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  972 */     return (uniqueEList == null) ? Collections.<EStructuralFeature>emptyList() : (List<EStructuralFeature>)uniqueEList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<EStructuralFeature> getAttributes(EClass eClass) {
/*  978 */     EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
/*  979 */     List<EStructuralFeature> result = null;
/*  980 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*      */       
/*  982 */       EStructuralFeature eStructuralFeature = eList.get(i);
/*  983 */       switch (getFeatureKind(eStructuralFeature)) {
/*      */ 
/*      */         
/*      */         case 2:
/*      */         case 3:
/*  988 */           if (result == null)
/*      */           {
/*  990 */             result = new ArrayList<EStructuralFeature>();
/*      */           }
/*  992 */           result.add(eStructuralFeature);
/*      */           break;
/*      */       } 
/*      */     } 
/*  996 */     return (result == null) ? Collections.<EStructuralFeature>emptyList() : result;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<EStructuralFeature> getElements(EClass eClass) {
/* 1001 */     EList<EStructuralFeature> eList = eClass.getEStructuralFeatures();
/* 1002 */     List<EStructuralFeature> result = null;
/* 1003 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*      */       
/* 1005 */       EStructuralFeature eStructuralFeature = eList.get(i);
/* 1006 */       switch (getFeatureKind(eStructuralFeature)) {
/*      */ 
/*      */         
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/* 1012 */           if (result == null)
/*      */           {
/* 1014 */             result = new ArrayList<EStructuralFeature>();
/*      */           }
/* 1016 */           result.add(eStructuralFeature);
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1022 */     return (result == null) ? Collections.<EStructuralFeature>emptyList() : result;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getSimpleFeature(EClass eClass) {
/* 1027 */     if (getContentKind(eClass) == 2)
/*      */     {
/* 1029 */       for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */         
/* 1031 */         EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/* 1032 */         if (getFeatureKind(eStructuralFeature) == 1)
/*      */         {
/* 1034 */           return eStructuralFeature;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1039 */     return null;
/*      */   } public EAttribute getMixedFeature(EClass eClass) {
/*      */     EList<EAttribute> eList;
/*      */     int i;
/*      */     int size;
/* 1044 */     switch (getContentKind(eClass)) {
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/* 1049 */         eList = eClass.getEAllAttributes();
/* 1050 */         for (i = 0, size = eList.size(); i < size; i++) {
/*      */           
/* 1052 */           EAttribute eAttribute = eList.get(i);
/* 1053 */           if (getFeatureKind((EStructuralFeature)eAttribute) == 5)
/*      */           {
/* 1055 */             return eAttribute;
/*      */           }
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1062 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> getWildcards(EStructuralFeature eStructuralFeature) {
/* 1067 */     return getExtendedMetaData(eStructuralFeature).getWildcards();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<String> basicGetWildcards(EStructuralFeature eStructuralFeature) {
/* 1072 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1073 */     if (eAnnotation != null) {
/*      */       
/* 1075 */       String wildcards = (String)eAnnotation.getDetails().get("wildcards");
/* 1076 */       if (wildcards != null) {
/*      */         
/* 1078 */         List<String> result = new ArrayList<String>();
/* 1079 */         for (StringTokenizer stringTokenizer = new StringTokenizer(wildcards); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 1081 */           String wildcard = stringTokenizer.nextToken();
/* 1082 */           if (wildcard.equals("##other")) {
/*      */             
/* 1084 */             result.add("!##" + getNamespace(eStructuralFeature.getEContainingClass().getEPackage())); continue;
/*      */           } 
/* 1086 */           if (wildcard.equals("##local")) {
/*      */             
/* 1088 */             result.add(null); continue;
/*      */           } 
/* 1090 */           if (wildcard.equals("##targetNamespace")) {
/*      */             
/* 1092 */             result.add(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
/*      */             
/*      */             continue;
/*      */           } 
/* 1096 */           result.add(wildcard);
/*      */         } 
/*      */         
/* 1099 */         return result;
/*      */       } 
/*      */     } 
/*      */     
/* 1103 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWildcards(EStructuralFeature eStructuralFeature, List<String> wildcards) {
/* 1108 */     if (wildcards.isEmpty()) {
/*      */       
/* 1110 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1111 */       if (eAnnotation != null)
/*      */       {
/* 1113 */         eAnnotation.getDetails().remove("wildcards");
/* 1114 */         eAnnotation.getDetails().remove("name");
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1119 */       String namespace = getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
/* 1120 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/* 1121 */       eAnnotation.getDetails().put("wildcards", getEncodedWildcards(namespace, wildcards));
/* 1122 */       eAnnotation.getDetails().put("name", "");
/*      */     } 
/* 1124 */     getExtendedMetaData(eStructuralFeature).setWildcards(wildcards);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getEncodedWildcards(String namespace, List<String> wildcards) {
/* 1129 */     if (wildcards.isEmpty())
/*      */     {
/* 1131 */       return "";
/*      */     }
/*      */ 
/*      */     
/* 1135 */     StringBuffer value = new StringBuffer();
/* 1136 */     for (int i = 0, size = wildcards.size(); i < size; ) {
/*      */       
/* 1138 */       String wildcard = wildcards.get(i);
/* 1139 */       if (wildcard == null) {
/*      */         
/* 1141 */         if (namespace == null)
/*      */         {
/* 1143 */           value.append("##targetNamespace");
/*      */         }
/*      */         else
/*      */         {
/* 1147 */           value.append("##local");
/*      */         }
/*      */       
/* 1150 */       } else if (wildcard.startsWith("!##")) {
/*      */         
/* 1152 */         if ((namespace == null) ? (
/* 1153 */           wildcard.length() == 3) : (
/* 1154 */           wildcard.endsWith(namespace) && wildcard.length() == namespace.length() + 3))
/*      */         {
/* 1156 */           value.append("##other");
/*      */         }
/*      */         else
/*      */         {
/* 1160 */           value.append(wildcard);
/*      */         }
/*      */       
/* 1163 */       } else if (wildcard.equals(namespace)) {
/*      */         
/* 1165 */         value.append("##targetNamespace");
/*      */       }
/*      */       else {
/*      */         
/* 1169 */         value.append(wildcard);
/*      */       } 
/*      */       
/* 1172 */       if (++i < size)
/*      */       {
/* 1174 */         value.append(' ');
/*      */       }
/*      */     } 
/* 1177 */     return value.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getProcessingKind(EStructuralFeature eStructuralFeature) {
/* 1183 */     return getExtendedMetaData(eStructuralFeature).getProcessingKind();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetProcessingKind(EStructuralFeature eStructuralFeature) {
/* 1188 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1189 */     if (eAnnotation != null) {
/*      */       
/* 1191 */       Object kind = eAnnotation.getDetails().get("processing");
/* 1192 */       if (kind != null)
/*      */       {
/* 1194 */         for (int i = 1; i < PROCESSING_KINDS.length; i++) {
/*      */           
/* 1196 */           if (PROCESSING_KINDS[i].equals(kind))
/*      */           {
/* 1198 */             return i;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1204 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProcessingKind(EStructuralFeature eStructuralFeature, int kind) {
/* 1209 */     if (kind > 0 && kind < PROCESSING_KINDS.length) {
/*      */       
/* 1211 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/* 1212 */       eAnnotation.getDetails().put("processing", PROCESSING_KINDS[kind]);
/*      */     }
/*      */     else {
/*      */       
/* 1216 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1217 */       if (eAnnotation != null)
/*      */       {
/* 1219 */         eAnnotation.getDetails().remove("processing");
/*      */       }
/*      */     } 
/* 1222 */     getExtendedMetaData(eStructuralFeature).setProcessingKind(kind);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getGroup(EStructuralFeature eStructuralFeature) {
/* 1227 */     return getExtendedMetaData(eStructuralFeature).getGroup();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature basicGetGroup(EStructuralFeature eStructuralFeature) {
/* 1232 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1233 */     if (eAnnotation != null) {
/*      */       
/* 1235 */       String qualifiedName = (String)eAnnotation.getDetails().get("group");
/* 1236 */       if (qualifiedName != null) {
/*      */         
/* 1238 */         int fragmentIndex = qualifiedName.lastIndexOf('#');
/* 1239 */         if (fragmentIndex == -1)
/*      */         {
/* 1241 */           return 
/* 1242 */             getElement(
/* 1243 */               eStructuralFeature.getEContainingClass(), 
/* 1244 */               getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), 
/* 1245 */               qualifiedName);
/*      */         }
/* 1247 */         if (fragmentIndex == 0)
/*      */         {
/* 1249 */           return 
/* 1250 */             getElement(
/* 1251 */               eStructuralFeature.getEContainingClass(), 
/* 1252 */               (String)null, 
/* 1253 */               qualifiedName.substring(1));
/*      */         }
/*      */ 
/*      */         
/* 1257 */         return 
/* 1258 */           getElement(
/* 1259 */             eStructuralFeature.getEContainingClass(), 
/* 1260 */             qualifiedName.substring(0, fragmentIndex), 
/* 1261 */             qualifiedName.substring(fragmentIndex + 1));
/*      */       } 
/*      */     } 
/*      */     
/* 1265 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGroup(EStructuralFeature eStructuralFeature, EStructuralFeature group) {
/* 1270 */     if (group == null) {
/*      */       
/* 1272 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1273 */       if (eAnnotation != null)
/*      */       {
/* 1275 */         eAnnotation.getDetails().remove("group");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1280 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/* 1281 */       eAnnotation.getDetails().put(
/* 1282 */           "group", getQualifiedName(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), group));
/*      */     } 
/* 1284 */     getExtendedMetaData(eStructuralFeature).setGroup(group);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getAffiliation(EStructuralFeature eStructuralFeature) {
/* 1289 */     return getExtendedMetaData(eStructuralFeature).getAffiliation();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature basicGetAffiliation(EStructuralFeature eStructuralFeature) {
/* 1294 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1295 */     if (eAnnotation != null) {
/*      */       
/* 1297 */       String qualifiedName = (String)eAnnotation.getDetails().get("affiliation");
/* 1298 */       if (qualifiedName != null) {
/*      */         
/* 1300 */         int fragmentIndex = qualifiedName.lastIndexOf('#');
/* 1301 */         if (fragmentIndex == -1)
/*      */         {
/* 1303 */           return getElement(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), qualifiedName);
/*      */         }
/* 1305 */         if (fragmentIndex == 0)
/*      */         {
/* 1307 */           return getElement((String)null, qualifiedName.substring(1));
/*      */         }
/*      */ 
/*      */         
/* 1311 */         return getElement(qualifiedName.substring(0, fragmentIndex), qualifiedName.substring(fragmentIndex + 1));
/*      */       } 
/*      */     } 
/*      */     
/* 1315 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAffiliation(EStructuralFeature eStructuralFeature, EStructuralFeature affiliation) {
/* 1320 */     if (affiliation == null) {
/*      */       
/* 1322 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, false);
/* 1323 */       if (eAnnotation != null)
/*      */       {
/* 1325 */         eAnnotation.getDetails().remove("affiliation");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1330 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eStructuralFeature, true);
/* 1331 */       eAnnotation.getDetails().put(
/* 1332 */           "affiliation", getQualifiedName(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), affiliation));
/*      */     } 
/* 1334 */     getExtendedMetaData(eStructuralFeature).setAffiliation(affiliation);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature) {
/* 1339 */     if (eClass.getFeatureID(eStructuralFeature) >= 0)
/*      */     {
/* 1341 */       return eStructuralFeature;
/*      */     }
/*      */     
/* 1344 */     switch (getFeatureKind(eStructuralFeature)) {
/*      */ 
/*      */       
/*      */       case 2:
/* 1348 */         if (isDocumentRoot(eStructuralFeature.getEContainingClass())) {
/*      */           
/* 1350 */           String namespace = getNamespace(eStructuralFeature);
/* 1351 */           String name = getName(eStructuralFeature);
/* 1352 */           EStructuralFeature result = getLocalAttribute(eClass, namespace, name);
/* 1353 */           if (result != null)
/*      */           {
/* 1355 */             return result;
/*      */           }
/*      */           
/* 1358 */           List<EStructuralFeature> allAttributes = getAllAttributes(eClass);
/* 1359 */           for (int i = 0, size = allAttributes.size(); i < size; i++) {
/*      */             
/* 1361 */             result = allAttributes.get(i);
/* 1362 */             if (matches(getWildcards(result), namespace))
/*      */             {
/* 1364 */               return result;
/*      */             }
/*      */           } 
/*      */         } 
/* 1368 */         return null;
/*      */ 
/*      */       
/*      */       case 4:
/* 1372 */         if (isDocumentRoot(eStructuralFeature.getEContainingClass())) {
/*      */           
/* 1374 */           for (EStructuralFeature affiliation = eStructuralFeature; affiliation != null; affiliation = getAffiliation(affiliation)) {
/*      */             
/* 1376 */             String str1 = getNamespace(affiliation);
/* 1377 */             String name = getName(affiliation);
/* 1378 */             EStructuralFeature result = getLocalElement(eClass, str1, name);
/* 1379 */             if (result != null)
/*      */             {
/* 1381 */               return result;
/*      */             }
/*      */           } 
/*      */           
/* 1385 */           String namespace = getNamespace(eStructuralFeature);
/* 1386 */           if ("http://www.eclipse.org/emf/2003/XMLType".equals(namespace))
/*      */           {
/* 1388 */             return (EStructuralFeature)getMixedFeature(eClass);
/*      */           }
/*      */ 
/*      */           
/* 1392 */           List<EStructuralFeature> allElements = getAllElements(eClass);
/* 1393 */           for (int i = 0, size = allElements.size(); i < size; i++) {
/*      */             
/* 1395 */             EStructuralFeature result = allElements.get(i);
/* 1396 */             if (matches(getWildcards(result), namespace))
/*      */             {
/* 1398 */               return result;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1403 */         return null;
/*      */     } 
/*      */ 
/*      */     
/* 1407 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EStructuralFeature getAttributeWildcardAffiliation(EClass eClass, String namespace, String name) {
/* 1414 */     List<EStructuralFeature> allAttributes = getAllAttributes(eClass);
/* 1415 */     for (int i = 0, size = allAttributes.size(); i < size; i++) {
/*      */       
/* 1417 */       EStructuralFeature result = allAttributes.get(i);
/* 1418 */       if (matches(getWildcards(result), namespace))
/*      */       {
/* 1420 */         return result;
/*      */       }
/*      */     } 
/*      */     
/* 1424 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getElementWildcardAffiliation(EClass eClass, String namespace, String name) {
/* 1429 */     List<EStructuralFeature> allElements = getAllElements(eClass);
/* 1430 */     for (int i = 0, size = allElements.size(); i < size; i++) {
/*      */       
/* 1432 */       EStructuralFeature result = allElements.get(i);
/* 1433 */       if (matches(getWildcards(result), namespace))
/*      */       {
/* 1435 */         return result;
/*      */       }
/*      */     } 
/*      */     
/* 1439 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean matches(List<String> wildcards, String namespace) {
/* 1444 */     if (!wildcards.isEmpty())
/*      */     {
/* 1446 */       for (int i = 0, size = wildcards.size(); i < size; i++) {
/*      */         
/* 1448 */         String wildcard = wildcards.get(i);
/* 1449 */         if (matches(wildcard, namespace))
/*      */         {
/* 1451 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1456 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean matches(String wildcard, String namespace) {
/* 1461 */     return 
/* 1462 */       (wildcard == null) ? (
/* 1463 */       (namespace == null)) : (
/* 1464 */       wildcard.startsWith("!##") ? (
/* 1465 */       (namespace != null && (
/* 1466 */       !wildcard.endsWith(namespace) || wildcard.length() != namespace.length() + 3) && 
/* 1467 */       !"http://www.eclipse.org/emf/2003/XMLType".equals(namespace))) : (
/* 1468 */       !((!wildcard.equals("##any") || "http://www.eclipse.org/emf/2003/XMLType".equals(namespace)) && !wildcard.equals(namespace))));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWhiteSpaceFacet(EDataType eDataType) {
/* 1473 */     return getExtendedMetaData((EClassifier)eDataType).getWhiteSpaceFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetWhiteSpaceFacet(EDataType eDataType) {
/* 1478 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1479 */     if (eAnnotation != null) {
/*      */       
/* 1481 */       String whiteSpaceLiteral = (String)eAnnotation.getDetails().get("whiteSpace");
/* 1482 */       for (int i = 1; i < WHITE_SPACE_KINDS.length; i++) {
/*      */         
/* 1484 */         if (WHITE_SPACE_KINDS[i].equals(whiteSpaceLiteral))
/*      */         {
/* 1486 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/* 1490 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWhiteSpaceFacet(EDataType eDataType, int whiteSpace) {
/* 1495 */     if (whiteSpace == 0) {
/*      */       
/* 1497 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1498 */       if (eAnnotation != null)
/*      */       {
/* 1500 */         eAnnotation.getDetails().remove("whiteSpace");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1505 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1506 */       eAnnotation.getDetails().put("whiteSpace", WHITE_SPACE_KINDS[whiteSpace]);
/*      */     } 
/* 1508 */     getExtendedMetaData((EClassifier)eDataType).setWhiteSpaceFacet(whiteSpace);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> getEnumerationFacet(EDataType eDataType) {
/* 1513 */     return getExtendedMetaData((EClassifier)eDataType).getEnumerationFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<String> basicGetEnumerationFacet(EDataType eDataType) {
/* 1518 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1519 */     if (eAnnotation != null) {
/*      */       
/* 1521 */       String enumerationLiteral = (String)eAnnotation.getDetails().get("enumeration");
/* 1522 */       if (enumerationLiteral != null) {
/*      */         
/* 1524 */         List<String> result = new ArrayList<String>();
/* 1525 */         for (StringTokenizer stringTokenizer = new StringTokenizer(enumerationLiteral, " "); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 1527 */           String enumeration = replace(replace(stringTokenizer.nextToken(), "%20", " "), "%25", "%");
/* 1528 */           result.add(enumeration);
/*      */         } 
/* 1530 */         return result;
/*      */       } 
/*      */     } 
/* 1533 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEnumerationFacet(EDataType eDataType, List<String> literals) {
/* 1538 */     if (literals.isEmpty()) {
/*      */       
/* 1540 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1541 */       if (eAnnotation != null)
/*      */       {
/* 1543 */         eAnnotation.getDetails().remove("enumeration");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1548 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1549 */       StringBuffer result = new StringBuffer();
/* 1550 */       for (int i = 0, size = literals.size(); i < size; i++) {
/*      */         
/* 1552 */         result.append(replace(replace(literals.get(i), "%", "%25"), " ", "%20"));
/* 1553 */         result.append(' ');
/*      */       } 
/* 1555 */       eAnnotation.getDetails().put("enumeration", result.substring(0, result.length() - 1));
/*      */     } 
/* 1557 */     getExtendedMetaData((EClassifier)eDataType).setEnumerationFacet(literals);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> getPatternFacet(EDataType eDataType) {
/* 1562 */     return getExtendedMetaData((EClassifier)eDataType).getPatternFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<String> basicGetPatternFacet(EDataType eDataType) {
/* 1567 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1568 */     if (eAnnotation != null) {
/*      */       
/* 1570 */       String patternLiteral = (String)eAnnotation.getDetails().get("pattern");
/* 1571 */       if (patternLiteral != null) {
/*      */         
/* 1573 */         List<String> result = new ArrayList<String>();
/* 1574 */         for (StringTokenizer stringTokenizer = new StringTokenizer(patternLiteral, " "); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 1576 */           String pattern = replace(replace(stringTokenizer.nextToken(), "%20", " "), "%25", "%");
/* 1577 */           result.add(pattern);
/*      */         } 
/* 1579 */         return result;
/*      */       } 
/*      */     } 
/* 1582 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPatternFacet(EDataType eDataType, List<String> pattern) {
/* 1587 */     if (pattern.isEmpty()) {
/*      */       
/* 1589 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1590 */       if (eAnnotation != null)
/*      */       {
/* 1592 */         eAnnotation.getDetails().remove("pattern");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1597 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1598 */       StringBuffer result = new StringBuffer();
/* 1599 */       for (int i = 0, size = pattern.size(); i < size; i++) {
/*      */         
/* 1601 */         result.append(replace(replace(pattern.get(i), "%", "%25"), " ", "%20"));
/* 1602 */         result.append(' ');
/*      */       } 
/* 1604 */       eAnnotation.getDetails().put("pattern", result.substring(0, result.length() - 1));
/*      */     } 
/* 1606 */     getExtendedMetaData((EClassifier)eDataType).setPatternFacet(pattern);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTotalDigitsFacet(EDataType eDataType) {
/* 1611 */     return getExtendedMetaData((EClassifier)eDataType).getTotalDigitsFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetTotalDigitsFacet(EDataType eDataType) {
/* 1616 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1617 */     if (eAnnotation != null) {
/*      */       
/* 1619 */       String totalDigitsLiteral = (String)eAnnotation.getDetails().get("totalDigits");
/* 1620 */       if (totalDigitsLiteral != null)
/*      */       {
/* 1622 */         return Integer.parseInt(totalDigitsLiteral);
/*      */       }
/*      */     } 
/* 1625 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTotalDigitsFacet(EDataType eDataType, int digits) {
/* 1630 */     if (digits == -1) {
/*      */       
/* 1632 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1633 */       if (eAnnotation != null)
/*      */       {
/* 1635 */         eAnnotation.getDetails().remove("totalDigits");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1640 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1641 */       eAnnotation.getDetails().put("totalDigits", Integer.toString(digits));
/*      */     } 
/* 1643 */     getExtendedMetaData((EClassifier)eDataType).setTotalDigitsFacet(digits);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFractionDigitsFacet(EDataType eDataType) {
/* 1648 */     return getExtendedMetaData((EClassifier)eDataType).getFractionDigitsFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetFractionDigitsFacet(EDataType eDataType) {
/* 1653 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1654 */     if (eAnnotation != null) {
/*      */       
/* 1656 */       String fractionDigitsLiteral = (String)eAnnotation.getDetails().get("fractionDigits");
/* 1657 */       if (fractionDigitsLiteral != null)
/*      */       {
/* 1659 */         return Integer.parseInt(fractionDigitsLiteral);
/*      */       }
/*      */     } 
/* 1662 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFractionDigitsFacet(EDataType eDataType, int digits) {
/* 1667 */     if (digits == -1) {
/*      */       
/* 1669 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1670 */       if (eAnnotation != null)
/*      */       {
/* 1672 */         eAnnotation.getDetails().remove("fractionDigits");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1677 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1678 */       eAnnotation.getDetails().put("fractionDigits", Integer.toString(digits));
/*      */     } 
/* 1680 */     getExtendedMetaData((EClassifier)eDataType).setFractionDigitsFacet(digits);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLengthFacet(EDataType eDataType) {
/* 1685 */     return getExtendedMetaData((EClassifier)eDataType).getLengthFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetLengthFacet(EDataType eDataType) {
/* 1690 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1691 */     if (eAnnotation != null) {
/*      */       
/* 1693 */       String lengthLiteral = (String)eAnnotation.getDetails().get("length");
/* 1694 */       if (lengthLiteral != null)
/*      */       {
/* 1696 */         return Integer.parseInt(lengthLiteral);
/*      */       }
/*      */     } 
/* 1699 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLengthFacet(EDataType eDataType, int length) {
/* 1704 */     if (length == -1) {
/*      */       
/* 1706 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1707 */       if (eAnnotation != null)
/*      */       {
/* 1709 */         eAnnotation.getDetails().remove("length");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1714 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1715 */       eAnnotation.getDetails().put("length", Integer.toString(length));
/*      */     } 
/* 1717 */     getExtendedMetaData((EClassifier)eDataType).setLengthFacet(length);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinLengthFacet(EDataType eDataType) {
/* 1722 */     return getExtendedMetaData((EClassifier)eDataType).getMinLengthFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetMinLengthFacet(EDataType eDataType) {
/* 1727 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1728 */     if (eAnnotation != null) {
/*      */       
/* 1730 */       String minLengthLiteral = (String)eAnnotation.getDetails().get("minLength");
/* 1731 */       if (minLengthLiteral != null)
/*      */       {
/* 1733 */         return Integer.parseInt(minLengthLiteral);
/*      */       }
/*      */     } 
/* 1736 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinLengthFacet(EDataType eDataType, int length) {
/* 1741 */     if (length == -1) {
/*      */       
/* 1743 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1744 */       if (eAnnotation != null)
/*      */       {
/* 1746 */         eAnnotation.getDetails().remove("minLength");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1751 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1752 */       eAnnotation.getDetails().put("minLength", Integer.toString(length));
/*      */     } 
/* 1754 */     getExtendedMetaData((EClassifier)eDataType).setMinLengthFacet(length);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxLengthFacet(EDataType eDataType) {
/* 1759 */     return getExtendedMetaData((EClassifier)eDataType).getMaxLengthFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int basicGetMaxLengthFacet(EDataType eDataType) {
/* 1764 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1765 */     if (eAnnotation != null) {
/*      */       
/* 1767 */       String maxLengthLiteral = (String)eAnnotation.getDetails().get("maxLength");
/* 1768 */       if (maxLengthLiteral != null)
/*      */       {
/* 1770 */         return Integer.parseInt(maxLengthLiteral);
/*      */       }
/*      */     } 
/* 1773 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxLengthFacet(EDataType eDataType, int length) {
/* 1778 */     if (length == -1) {
/*      */       
/* 1780 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1781 */       if (eAnnotation != null)
/*      */       {
/* 1783 */         eAnnotation.getDetails().remove("maxLength");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1788 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1789 */       eAnnotation.getDetails().put("maxLength", Integer.toString(length));
/*      */     } 
/* 1791 */     getExtendedMetaData((EClassifier)eDataType).setMaxLengthFacet(length);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMinExclusiveFacet(EDataType eDataType) {
/* 1796 */     return getExtendedMetaData((EClassifier)eDataType).getMinExclusiveFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetMinExclusiveFacet(EDataType eDataType) {
/* 1801 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1802 */     return 
/* 1803 */       (eAnnotation == null) ? 
/* 1804 */       null : 
/* 1805 */       (String)eAnnotation.getDetails().get("minExclusive");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinExclusiveFacet(EDataType eDataType, String literal) {
/* 1810 */     if (literal == null) {
/*      */       
/* 1812 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1813 */       if (eAnnotation != null)
/*      */       {
/* 1815 */         eAnnotation.getDetails().remove("minExclusive");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1820 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1821 */       eAnnotation.getDetails().put("minExclusive", literal);
/*      */     } 
/* 1823 */     getExtendedMetaData((EClassifier)eDataType).setMinExclusiveFacet(literal);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMaxExclusiveFacet(EDataType eDataType) {
/* 1828 */     return getExtendedMetaData((EClassifier)eDataType).getMaxExclusiveFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetMaxExclusiveFacet(EDataType eDataType) {
/* 1833 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1834 */     return 
/* 1835 */       (eAnnotation == null) ? 
/* 1836 */       null : 
/* 1837 */       (String)eAnnotation.getDetails().get("maxExclusive");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxExclusiveFacet(EDataType eDataType, String literal) {
/* 1842 */     if (literal == null) {
/*      */       
/* 1844 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1845 */       if (eAnnotation != null)
/*      */       {
/* 1847 */         eAnnotation.getDetails().remove("maxExclusive");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1852 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1853 */       eAnnotation.getDetails().put("maxExclusive", literal);
/*      */     } 
/* 1855 */     getExtendedMetaData((EClassifier)eDataType).setMaxExclusiveFacet(literal);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMinInclusiveFacet(EDataType eDataType) {
/* 1860 */     return getExtendedMetaData((EClassifier)eDataType).getMinInclusiveFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetMinInclusiveFacet(EDataType eDataType) {
/* 1865 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1866 */     return 
/* 1867 */       (eAnnotation == null) ? 
/* 1868 */       null : 
/* 1869 */       (String)eAnnotation.getDetails().get("minInclusive");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinInclusiveFacet(EDataType eDataType, String literal) {
/* 1874 */     if (literal == null) {
/*      */       
/* 1876 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1877 */       if (eAnnotation != null)
/*      */       {
/* 1879 */         eAnnotation.getDetails().remove("minInclusive");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1884 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1885 */       eAnnotation.getDetails().put("minInclusive", literal);
/*      */     } 
/* 1887 */     getExtendedMetaData((EClassifier)eDataType).setMinInclusiveFacet(literal);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMaxInclusiveFacet(EDataType eDataType) {
/* 1892 */     return getExtendedMetaData((EClassifier)eDataType).getMaxInclusiveFacet();
/*      */   }
/*      */ 
/*      */   
/*      */   protected String basicGetMaxInclusiveFacet(EDataType eDataType) {
/* 1897 */     EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1898 */     return 
/* 1899 */       (eAnnotation == null) ? 
/* 1900 */       null : 
/* 1901 */       (String)eAnnotation.getDetails().get("maxInclusive");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxInclusiveFacet(EDataType eDataType, String literal) {
/* 1906 */     if (literal == null) {
/*      */       
/* 1908 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, false);
/* 1909 */       if (eAnnotation != null)
/*      */       {
/* 1911 */         eAnnotation.getDetails().remove("maxInclusive");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1916 */       EAnnotation eAnnotation = getAnnotation((EModelElement)eDataType, true);
/* 1917 */       eAnnotation.getDetails().put("maxInclusive", literal);
/*      */     } 
/* 1919 */     getExtendedMetaData((EClassifier)eDataType).setMaxInclusiveFacet(literal);
/*      */   }
/*      */ 
/*      */   
/*      */   public EPackage demandPackage(String namespace) {
/* 1924 */     EPackage ePackage = this.demandRegistry.getEPackage(namespace);
/* 1925 */     if (ePackage == null) {
/*      */       
/* 1927 */       ePackage = EcoreFactory.eINSTANCE.createEPackage();
/* 1928 */       ePackage.setNsURI(namespace);
/* 1929 */       setQualified(ePackage, (namespace != null));
/* 1930 */       if (namespace != null)
/*      */       {
/* 1932 */         ePackage.setNsPrefix(
/* 1933 */             namespace.equals("http://www.w3.org/2000/xmlns/") ? (
/* 1934 */             namespace.equals("http://www.w3.org/XML/1998/namespace") ? 
/* 1935 */             "xml" : 
/* 1936 */             "xmlns") : 
/* 1937 */             computePrefix(namespace));
/*      */       }
/* 1939 */       this.demandRegistry.put(namespace, ePackage);
/*      */ 
/*      */ 
/*      */       
/* 1943 */       EClass documentRootEClass = EcoreFactory.eINSTANCE.createEClass();
/* 1944 */       documentRootEClass.getESuperTypes().add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot());
/* 1945 */       documentRootEClass.setName("DocumentRoot");
/* 1946 */       ePackage.getEClassifiers().add(documentRootEClass);
/* 1947 */       setDocumentRoot(documentRootEClass);
/*      */     } 
/* 1949 */     return ePackage;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String computePrefix(String namespace) {
/* 1954 */     int index = namespace.length();
/* 1955 */     boolean containsLetter = false;
/* 1956 */     StringBuffer prefix = new StringBuffer(index);
/* 1957 */     while (--index >= 0) {
/*      */       
/* 1959 */       char character = namespace.charAt(index);
/* 1960 */       if (XMLTypeUtil.isNCNamePart(character)) {
/*      */         
/* 1962 */         prefix.append(character);
/* 1963 */         containsLetter = Character.isLetter(character);
/*      */         break;
/*      */       } 
/*      */     } 
/* 1967 */     while (--index >= 0) {
/*      */       
/* 1969 */       char character = namespace.charAt(index);
/* 1970 */       if (XMLTypeUtil.isNCNamePart(character)) {
/*      */         
/* 1972 */         prefix.append(character);
/* 1973 */         if (!containsLetter)
/*      */         {
/* 1975 */           containsLetter = Character.isLetter(character); } 
/*      */         continue;
/*      */       } 
/* 1978 */       if (!containsLetter) {
/*      */         
/* 1980 */         prefix.append('_');
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/* 1988 */     int length = prefix.length();
/* 1989 */     if (length == 0 || !XMLTypeUtil.isNCNameStart(prefix.charAt(length - 1)))
/*      */     {
/* 1991 */       prefix.append('_');
/*      */     }
/* 1993 */     return prefix.reverse().toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public EClassifier demandType(String namespace, String name) {
/* 1998 */     EPackage ePackage = demandPackage(namespace);
/* 1999 */     EClassifier eClassifier = getType(ePackage, name);
/* 2000 */     if (eClassifier != null)
/*      */     {
/* 2002 */       return eClassifier;
/*      */     }
/*      */ 
/*      */     
/* 2006 */     EClass eClass = EcoreFactory.eINSTANCE.createEClass();
/* 2007 */     eClass.setName(name);
/* 2008 */     eClass.getESuperTypes().add(XMLTypePackage.eINSTANCE.getAnyType());
/* 2009 */     setContentKind(eClass, 3);
/* 2010 */     ePackage.getEClassifiers().add(eClass);
/* 2011 */     return (EClassifier)eClass;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EStructuralFeature demandFeature(String namespace, String name, boolean isElement) {
/* 2017 */     return demandFeature(namespace, name, isElement, isElement);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature demandFeature(String namespace, String name, boolean isElement, boolean isReference) {
/* 2022 */     EPackage ePackage = demandPackage(namespace);
/* 2023 */     EClass documentRootEClass = getDocumentRoot(ePackage);
/* 2024 */     EStructuralFeature eStructuralFeature = 
/* 2025 */       isElement ? 
/* 2026 */       getLocalElement(documentRootEClass, namespace, name) : 
/* 2027 */       getLocalAttribute(documentRootEClass, namespace, name);
/* 2028 */     if (eStructuralFeature != null)
/*      */     {
/* 2030 */       return eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/* 2034 */     if (isReference) {
/*      */       
/* 2036 */       EReference eReference = EcoreFactory.eINSTANCE.createEReference();
/* 2037 */       if (isElement) {
/*      */         
/* 2039 */         eReference.setContainment(true);
/* 2040 */         eReference.setResolveProxies(false);
/*      */       } 
/* 2042 */       eReference.setEType((EClassifier)EcorePackage.Literals.EOBJECT);
/* 2043 */       eReference.setName(name);
/* 2044 */       eReference.setDerived(true);
/* 2045 */       eReference.setTransient(true);
/* 2046 */       eReference.setVolatile(true);
/* 2047 */       documentRootEClass.getEStructuralFeatures().add(eReference);
/*      */       
/* 2049 */       setFeatureKind((EStructuralFeature)eReference, isElement ? 4 : 2);
/* 2050 */       setNamespace((EStructuralFeature)eReference, namespace);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2055 */       if (isElement)
/*      */       {
/* 2057 */         eReference.setUpperBound(-2);
/*      */       }
/*      */       
/* 2060 */       return (EStructuralFeature)eReference;
/*      */     } 
/*      */ 
/*      */     
/* 2064 */     EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
/* 2065 */     eAttribute.setName(name);
/* 2066 */     eAttribute.setEType((EClassifier)XMLTypePackage.eINSTANCE.getAnySimpleType());
/* 2067 */     eAttribute.setDerived(true);
/* 2068 */     eAttribute.setTransient(true);
/* 2069 */     eAttribute.setVolatile(true);
/* 2070 */     documentRootEClass.getEStructuralFeatures().add(eAttribute);
/*      */     
/* 2072 */     setFeatureKind((EStructuralFeature)eAttribute, isElement ? 4 : 2);
/* 2073 */     setNamespace((EStructuralFeature)eAttribute, namespace);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2078 */     if (isElement)
/*      */     {
/* 2080 */       eAttribute.setUpperBound(-2);
/*      */     }
/*      */     
/* 2083 */     return (EStructuralFeature)eAttribute;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<EPackage> demandedPackages() {
/* 2091 */     return this.demandRegistry.values();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class EPackageExtendedMetaDataImpl
/*      */     implements EPackageExtendedMetaData
/*      */   {
/*      */     protected EPackage ePackage;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isInitialized;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isQualified;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<String, EClassifier> nameToClassifierMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EPackageExtendedMetaDataImpl(EPackage ePackage) {
/* 2123 */       this.ePackage = ePackage;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isQualified() {
/* 2128 */       if (!this.isInitialized)
/*      */       {
/* 2130 */         setQualified(BasicExtendedMetaData.this.basicIsQualified(this.ePackage));
/*      */       }
/* 2132 */       return this.isQualified;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setQualified(boolean isQualified) {
/* 2137 */       this.isQualified = isQualified;
/* 2138 */       this.isInitialized = true;
/*      */     }
/*      */ 
/*      */     
/*      */     public EClassifier getType(String name) {
/* 2143 */       EClassifier result = null;
/* 2144 */       if (this.nameToClassifierMap != null)
/*      */       {
/* 2146 */         result = this.nameToClassifierMap.get(name);
/*      */       }
/* 2148 */       if (result == null) {
/*      */         
/* 2150 */         EList<EClassifier> eList = this.ePackage.getEClassifiers();
/* 2151 */         int size = eList.size();
/* 2152 */         if (this.nameToClassifierMap == null || this.nameToClassifierMap.size() != size) {
/*      */           
/* 2154 */           Map<String, EClassifier> nameToClassifierMap = new HashMap<String, EClassifier>();
/* 2155 */           if (this.nameToClassifierMap != null)
/*      */           {
/* 2157 */             nameToClassifierMap.putAll(this.nameToClassifierMap);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2163 */           int originalMapSize = nameToClassifierMap.size(); int i;
/* 2164 */           for (i = originalMapSize; i < size; i++) {
/*      */             
/* 2166 */             EClassifier eClassifier = eList.get(i);
/* 2167 */             String eClassifierName = BasicExtendedMetaData.this.getName(eClassifier);
/* 2168 */             EClassifier conflictingEClassifier = nameToClassifierMap.put(eClassifierName, eClassifier);
/* 2169 */             if (conflictingEClassifier != null && conflictingEClassifier != eClassifier)
/*      */             {
/* 2171 */               nameToClassifierMap.put(eClassifierName, conflictingEClassifier);
/*      */             }
/*      */           } 
/*      */           
/* 2175 */           if (nameToClassifierMap.size() != size)
/*      */           {
/* 2177 */             for (i = 0; i < originalMapSize; i++) {
/*      */               
/* 2179 */               EClassifier eClassifier = eList.get(i);
/* 2180 */               String eClassifierName = BasicExtendedMetaData.this.getName(eClassifier);
/* 2181 */               EClassifier conflictingEClassifier = nameToClassifierMap.put(eClassifierName, eClassifier);
/* 2182 */               if (conflictingEClassifier != null && conflictingEClassifier != eClassifier)
/*      */               {
/* 2184 */                 nameToClassifierMap.put(eClassifierName, conflictingEClassifier);
/*      */               }
/*      */             } 
/*      */           }
/* 2188 */           result = nameToClassifierMap.get(name);
/* 2189 */           this.nameToClassifierMap = nameToClassifierMap;
/*      */         } 
/*      */       } 
/*      */       
/* 2193 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public void rename(EClassifier eClassifier, String newName) {
/* 2198 */       if (this.nameToClassifierMap != null) {
/*      */         
/* 2200 */         this.nameToClassifierMap.values().remove(eClassifier);
/* 2201 */         this.nameToClassifierMap.put(newName, eClassifier);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EPackageExtendedMetaData getExtendedMetaData(EPackage ePackage) {
/* 2208 */     if (this.extendedMetaDataHolderCache != null) {
/*      */       
/* 2210 */       EPackageExtendedMetaData ePackageExtendedMetaData = (EPackageExtendedMetaData)this.extendedMetaDataHolderCache.get(ePackage);
/* 2211 */       if (ePackageExtendedMetaData == null)
/*      */       {
/* 2213 */         this.extendedMetaDataHolderCache.put(ePackage, ePackageExtendedMetaData = createEPackageExtendedMetaData(ePackage));
/*      */       }
/* 2215 */       return ePackageExtendedMetaData;
/*      */     } 
/*      */ 
/*      */     
/* 2219 */     EPackageExtendedMetaData.Holder holder = (EPackageExtendedMetaData.Holder)ePackage;
/* 2220 */     EPackageExtendedMetaData result = holder.getExtendedMetaData();
/* 2221 */     if (result == null)
/*      */     {
/* 2223 */       holder.setExtendedMetaData(result = createEPackageExtendedMetaData(ePackage));
/*      */     }
/* 2225 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPackageExtendedMetaData createEPackageExtendedMetaData(EPackage ePackage) {
/* 2231 */     return new EPackageExtendedMetaDataImpl(ePackage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2237 */   protected static final EDataType UNINITIALIZED_EDATA_TYPE = EcoreFactory.eINSTANCE.createEDataType();
/* 2238 */   protected static final EStructuralFeature UNINITIALIZED_ESTRUCTURAL_FEATURE = (EStructuralFeature)EcoreFactory.eINSTANCE.createEAttribute();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class EClassExtendedMetaDataImpl
/*      */     implements EClassifierExtendedMetaData
/*      */   {
/*      */     protected EClass eClass;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2305 */     protected String name = "uninitialized";
/* 2306 */     protected int contentKind = -2;
/*      */ 
/*      */     
/*      */     public EClassExtendedMetaDataImpl(EClass eClass) {
/* 2310 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2315 */       if (this.name == "uninitialized")
/*      */       {
/* 2317 */         setName(BasicExtendedMetaData.this.basicGetName((EClassifier)this.eClass));
/*      */       }
/* 2319 */       return this.name;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setName(String name) {
/* 2324 */       this.name = name;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getContentKind() {
/* 2329 */       if (this.contentKind == -2)
/*      */       {
/* 2331 */         setContentKind(BasicExtendedMetaData.this.basicGetContentKind(this.eClass));
/*      */       }
/* 2333 */       return this.contentKind;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setContentKind(int kind) {
/* 2338 */       this.contentKind = kind;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getDerivationKind() {
/* 2343 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public EDataType getBaseType() {
/* 2348 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setBaseType(EDataType baseType) {
/* 2353 */       throw new UnsupportedOperationException("Can't set the base type of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public EDataType getItemType() {
/* 2358 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setItemType(EDataType itemType) {
/* 2363 */       throw new UnsupportedOperationException("Can't set the item type of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public List<EDataType> getMemberTypes() {
/* 2368 */       return Collections.emptyList();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMemberTypes(List<EDataType> memberTypes) {
/* 2373 */       throw new UnsupportedOperationException("Can't set the member types of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getWhiteSpaceFacet() {
/* 2378 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setWhiteSpaceFacet(int whiteSpace) {
/* 2383 */       throw new UnsupportedOperationException("Can't set the white space of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public List<String> getEnumerationFacet() {
/* 2388 */       return Collections.emptyList();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEnumerationFacet(List<String> literals) {
/* 2393 */       throw new UnsupportedOperationException("Can't set the enumeration of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public List<String> getPatternFacet() {
/* 2398 */       return Collections.emptyList();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setPatternFacet(List<String> pattern) {
/* 2403 */       throw new UnsupportedOperationException("Can't set the pattern of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalDigitsFacet() {
/* 2408 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setTotalDigitsFacet(int digits) {
/* 2413 */       throw new UnsupportedOperationException("Can't set the total digits of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getFractionDigitsFacet() {
/* 2418 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFractionDigitsFacet(int digits) {
/* 2423 */       throw new UnsupportedOperationException("Can't set the fraction digits of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLengthFacet() {
/* 2428 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setLengthFacet(int length) {
/* 2433 */       throw new UnsupportedOperationException("Can't set the length of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMinLengthFacet() {
/* 2438 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinLengthFacet(int minLength) {
/* 2443 */       throw new UnsupportedOperationException("Can't set the min length of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMaxLengthFacet() {
/* 2448 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxLengthFacet(int maxLength) {
/* 2453 */       throw new UnsupportedOperationException("Can't set the max length of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMinExclusiveFacet() {
/* 2458 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinExclusiveFacet(String literal) {
/* 2463 */       throw new UnsupportedOperationException("Can't set the min exclusive of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMaxExclusiveFacet() {
/* 2468 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxExclusiveFacet(String literal) {
/* 2473 */       throw new UnsupportedOperationException("Can't set the max exclusive of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMinInclusiveFacet() {
/* 2478 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinInclusiveFacet(String literal) {
/* 2483 */       throw new UnsupportedOperationException("Can't set the min inclusive of an EClass");
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMaxInclusiveFacet() {
/* 2488 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxInclusiveFacet(String literal) {
/* 2493 */       throw new UnsupportedOperationException("Can't set the max inclusive of an EClass");
/*      */     }
/*      */   }
/*      */   
/*      */   public class EDataTypeExtendedMetaDataImpl
/*      */     implements EClassifierExtendedMetaData {
/*      */     protected EDataType eDataType;
/* 2500 */     protected String name = "uninitialized";
/* 2501 */     protected EDataType baseType = BasicExtendedMetaData.UNINITIALIZED_EDATA_TYPE;
/* 2502 */     protected EDataType itemType = BasicExtendedMetaData.UNINITIALIZED_EDATA_TYPE;
/*      */     protected List<EDataType> memberTypes;
/* 2504 */     protected int derivationKind = -2;
/* 2505 */     protected int whiteSpace = -2;
/*      */     protected List<String> enumerationLiterals;
/*      */     protected List<String> pattern;
/* 2508 */     int totalDigits = -2;
/* 2509 */     int fractionDigits = -2;
/* 2510 */     int length = -2;
/* 2511 */     int minLength = -2;
/* 2512 */     int maxLength = -2;
/* 2513 */     String minExclusive = "uninitialized";
/* 2514 */     String maxExclusive = "uninitialized";
/* 2515 */     String minInclusive = "uninitialized";
/* 2516 */     String maxInclusive = "uninitialized";
/*      */ 
/*      */     
/*      */     public EDataTypeExtendedMetaDataImpl(EDataType eDataType) {
/* 2520 */       this.eDataType = eDataType;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2525 */       if (this.name == "uninitialized")
/*      */       {
/* 2527 */         setName(BasicExtendedMetaData.this.basicGetName((EClassifier)this.eDataType));
/*      */       }
/* 2529 */       return this.name;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setName(String name) {
/* 2534 */       this.name = name;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getContentKind() {
/* 2539 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setContentKind(int kind) {
/* 2544 */       throw new UnsupportedOperationException("Can't set the content kind of an EDataType");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getDerivationKind() {
/* 2549 */       if (this.derivationKind == -2)
/*      */       {
/* 2551 */         if (getBaseType() != null) {
/*      */           
/* 2553 */           this.derivationKind = 1;
/*      */         }
/* 2555 */         else if (getItemType() != null) {
/*      */           
/* 2557 */           this.derivationKind = 2;
/*      */         }
/* 2559 */         else if (!getMemberTypes().isEmpty()) {
/*      */           
/* 2561 */           this.derivationKind = 3;
/*      */         }
/*      */         else {
/*      */           
/* 2565 */           this.derivationKind = 0;
/*      */         } 
/*      */       }
/* 2568 */       return this.derivationKind;
/*      */     }
/*      */ 
/*      */     
/*      */     public EDataType getBaseType() {
/* 2573 */       if (this.baseType == BasicExtendedMetaData.UNINITIALIZED_EDATA_TYPE)
/*      */       {
/* 2575 */         setBaseType(BasicExtendedMetaData.this.basicGetBaseType(this.eDataType));
/*      */       }
/* 2577 */       return this.baseType;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setBaseType(EDataType baseType) {
/* 2582 */       this.baseType = baseType;
/* 2583 */       this.derivationKind = -2;
/*      */     }
/*      */ 
/*      */     
/*      */     public EDataType getItemType() {
/* 2588 */       if (this.itemType == BasicExtendedMetaData.UNINITIALIZED_EDATA_TYPE)
/*      */       {
/* 2590 */         setItemType(BasicExtendedMetaData.this.basicGetItemType(this.eDataType));
/*      */       }
/* 2592 */       return this.itemType;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setItemType(EDataType itemType) {
/* 2597 */       this.itemType = itemType;
/* 2598 */       this.derivationKind = -2;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<EDataType> getMemberTypes() {
/* 2603 */       if (this.memberTypes == null)
/*      */       {
/* 2605 */         setMemberTypes(BasicExtendedMetaData.this.basicGetMemberTypes(this.eDataType));
/*      */       }
/* 2607 */       return this.memberTypes;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMemberTypes(List<EDataType> memberTypes) {
/* 2612 */       this.memberTypes = memberTypes;
/* 2613 */       this.derivationKind = -2;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getWhiteSpaceFacet() {
/* 2618 */       if (this.whiteSpace == -2)
/*      */       {
/* 2620 */         setWhiteSpaceFacet(BasicExtendedMetaData.this.basicGetWhiteSpaceFacet(this.eDataType));
/*      */       }
/* 2622 */       return this.whiteSpace;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setWhiteSpaceFacet(int whiteSpace) {
/* 2627 */       this.whiteSpace = whiteSpace;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<String> getEnumerationFacet() {
/* 2632 */       if (this.enumerationLiterals == null)
/*      */       {
/* 2634 */         setEnumerationFacet(BasicExtendedMetaData.this.basicGetEnumerationFacet(this.eDataType));
/*      */       }
/* 2636 */       return this.enumerationLiterals;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEnumerationFacet(List<String> literals) {
/* 2641 */       this.enumerationLiterals = literals;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<String> getPatternFacet() {
/* 2646 */       if (this.pattern == null)
/*      */       {
/* 2648 */         setPatternFacet(BasicExtendedMetaData.this.basicGetPatternFacet(this.eDataType));
/*      */       }
/* 2650 */       return this.pattern;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setPatternFacet(List<String> pattern) {
/* 2655 */       this.pattern = pattern;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalDigitsFacet() {
/* 2660 */       if (this.totalDigits == -2)
/*      */       {
/* 2662 */         setTotalDigitsFacet(BasicExtendedMetaData.this.basicGetTotalDigitsFacet(this.eDataType));
/*      */       }
/* 2664 */       return this.totalDigits;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setTotalDigitsFacet(int digits) {
/* 2669 */       this.totalDigits = digits;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getFractionDigitsFacet() {
/* 2674 */       if (this.fractionDigits == -2)
/*      */       {
/* 2676 */         setFractionDigitsFacet(BasicExtendedMetaData.this.basicGetFractionDigitsFacet(this.eDataType));
/*      */       }
/* 2678 */       return this.fractionDigits;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFractionDigitsFacet(int digits) {
/* 2683 */       this.fractionDigits = digits;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLengthFacet() {
/* 2688 */       if (this.length == -2)
/*      */       {
/* 2690 */         setLengthFacet(BasicExtendedMetaData.this.basicGetLengthFacet(this.eDataType));
/*      */       }
/* 2692 */       return this.length;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setLengthFacet(int length) {
/* 2697 */       this.length = length;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMinLengthFacet() {
/* 2702 */       if (this.minLength == -2)
/*      */       {
/* 2704 */         setMinLengthFacet(BasicExtendedMetaData.this.basicGetMinLengthFacet(this.eDataType));
/*      */       }
/* 2706 */       return this.minLength;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinLengthFacet(int minLength) {
/* 2711 */       this.minLength = minLength;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMaxLengthFacet() {
/* 2716 */       if (this.maxLength == -2)
/*      */       {
/* 2718 */         setMaxLengthFacet(BasicExtendedMetaData.this.basicGetMaxLengthFacet(this.eDataType));
/*      */       }
/* 2720 */       return this.maxLength;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxLengthFacet(int maxLength) {
/* 2725 */       this.maxLength = maxLength;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMinExclusiveFacet() {
/* 2730 */       if (this.minExclusive == "uninitialized")
/*      */       {
/* 2732 */         setMinExclusiveFacet(BasicExtendedMetaData.this.basicGetMinExclusiveFacet(this.eDataType));
/*      */       }
/* 2734 */       return this.minExclusive;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinExclusiveFacet(String literal) {
/* 2739 */       this.minExclusive = literal;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMaxExclusiveFacet() {
/* 2744 */       if (this.maxExclusive == "uninitialized")
/*      */       {
/* 2746 */         setMaxExclusiveFacet(BasicExtendedMetaData.this.basicGetMaxExclusiveFacet(this.eDataType));
/*      */       }
/* 2748 */       return this.maxExclusive;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxExclusiveFacet(String literal) {
/* 2753 */       this.maxExclusive = literal;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMinInclusiveFacet() {
/* 2758 */       if (this.minInclusive == "uninitialized")
/*      */       {
/* 2760 */         setMinInclusiveFacet(BasicExtendedMetaData.this.basicGetMinInclusiveFacet(this.eDataType));
/*      */       }
/* 2762 */       return this.minInclusive;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMinInclusiveFacet(String literal) {
/* 2767 */       this.minInclusive = literal;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getMaxInclusiveFacet() {
/* 2772 */       if (this.maxInclusive == "uninitialized")
/*      */       {
/* 2774 */         setMaxInclusiveFacet(BasicExtendedMetaData.this.basicGetMaxInclusiveFacet(this.eDataType));
/*      */       }
/* 2776 */       return this.maxInclusive;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMaxInclusiveFacet(String literal) {
/* 2781 */       this.maxInclusive = literal;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EClassifierExtendedMetaData getExtendedMetaData(EClassifier eClassifier) {
/* 2787 */     if (this.extendedMetaDataHolderCache != null) {
/*      */       
/* 2789 */       EClassifierExtendedMetaData eClassifierExtendedMetaData = (EClassifierExtendedMetaData)this.extendedMetaDataHolderCache.get(eClassifier);
/* 2790 */       if (eClassifierExtendedMetaData == null)
/*      */       {
/* 2792 */         this.extendedMetaDataHolderCache.put(eClassifier, eClassifierExtendedMetaData = createEClassifierExtendedMetaData(eClassifier));
/*      */       }
/* 2794 */       return eClassifierExtendedMetaData;
/*      */     } 
/*      */ 
/*      */     
/* 2798 */     EClassifierExtendedMetaData.Holder holder = (EClassifierExtendedMetaData.Holder)eClassifier;
/* 2799 */     EClassifierExtendedMetaData result = holder.getExtendedMetaData();
/* 2800 */     if (result == null)
/*      */     {
/* 2802 */       holder.setExtendedMetaData(result = createEClassifierExtendedMetaData(eClassifier));
/*      */     }
/* 2804 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EClassifierExtendedMetaData createEClassifierExtendedMetaData(EClassifier eClassifier) {
/* 2810 */     if (eClassifier instanceof EClass)
/*      */     {
/* 2812 */       return new EClassExtendedMetaDataImpl((EClass)eClassifier);
/*      */     }
/*      */ 
/*      */     
/* 2816 */     return new EDataTypeExtendedMetaDataImpl((EDataType)eClassifier);
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
/*      */   public class EStructuralFeatureExtendedMetaDataImpl
/*      */     implements EStructuralFeatureExtendedMetaData
/*      */   {
/*      */     protected EStructuralFeature eStructuralFeature;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2855 */     protected String name = "uninitialized";
/* 2856 */     protected String namespace = "uninitialized";
/* 2857 */     protected int featureKind = -2;
/*      */     protected List<String> wildcards;
/* 2859 */     protected int processingKind = -2;
/* 2860 */     protected EStructuralFeature group = BasicExtendedMetaData.UNINITIALIZED_ESTRUCTURAL_FEATURE;
/* 2861 */     protected EStructuralFeature affiliation = BasicExtendedMetaData.UNINITIALIZED_ESTRUCTURAL_FEATURE;
/*      */     
/*      */     protected Map<EClass, FeatureMapUtil.Validator> validatorMap;
/*      */     
/*      */     public EStructuralFeatureExtendedMetaDataImpl(EStructuralFeature eStructuralFeature) {
/* 2866 */       this.eStructuralFeature = eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     public Map<EClass, FeatureMapUtil.Validator> getValidatorMap() {
/* 2871 */       if (this.validatorMap == null)
/*      */       {
/* 2873 */         this.validatorMap = new Hashtable<EClass, FeatureMapUtil.Validator>();
/*      */       }
/* 2875 */       return this.validatorMap;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2880 */       if (this.name == "uninitialized")
/*      */       {
/* 2882 */         setName(BasicExtendedMetaData.this.basicGetName(this.eStructuralFeature));
/*      */       }
/* 2884 */       return this.name;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setName(String name) {
/* 2889 */       this.name = name;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getNamespace() {
/* 2894 */       if (this.namespace == "uninitialized")
/*      */       {
/* 2896 */         setNamespace(BasicExtendedMetaData.this.basicGetNamespace(this.eStructuralFeature));
/*      */       }
/* 2898 */       return this.namespace;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setNamespace(String namespace) {
/* 2903 */       this.namespace = namespace;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getFeatureKind() {
/* 2908 */       if (this.featureKind == -2)
/*      */       {
/* 2910 */         setFeatureKind(BasicExtendedMetaData.this.basicGetFeatureKind(this.eStructuralFeature));
/*      */       }
/* 2912 */       return this.featureKind;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFeatureKind(int kind) {
/* 2917 */       this.featureKind = kind;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<String> getWildcards() {
/* 2922 */       if (this.wildcards == null)
/*      */       {
/* 2924 */         setWildcards(BasicExtendedMetaData.this.basicGetWildcards(this.eStructuralFeature));
/*      */       }
/* 2926 */       return this.wildcards;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setWildcards(List<String> wildcards) {
/* 2931 */       this.wildcards = wildcards;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getProcessingKind() {
/* 2936 */       if (this.processingKind == -2)
/*      */       {
/* 2938 */         setProcessingKind(BasicExtendedMetaData.this.basicGetProcessingKind(this.eStructuralFeature));
/*      */       }
/* 2940 */       return this.processingKind;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setProcessingKind(int kind) {
/* 2945 */       this.processingKind = kind;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getGroup() {
/* 2950 */       if (this.group == BasicExtendedMetaData.UNINITIALIZED_ESTRUCTURAL_FEATURE)
/*      */       {
/* 2952 */         setGroup(BasicExtendedMetaData.this.basicGetGroup(this.eStructuralFeature));
/*      */       }
/* 2954 */       return this.group;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setGroup(EStructuralFeature group) {
/* 2959 */       this.group = group;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getAffiliation() {
/* 2964 */       if (this.affiliation == BasicExtendedMetaData.UNINITIALIZED_ESTRUCTURAL_FEATURE)
/*      */       {
/* 2966 */         setAffiliation(BasicExtendedMetaData.this.basicGetAffiliation(this.eStructuralFeature));
/*      */       }
/* 2968 */       return this.affiliation;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setAffiliation(EStructuralFeature affiliation) {
/* 2973 */       this.affiliation = affiliation;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeatureExtendedMetaData getExtendedMetaData(EStructuralFeature eStructuralFeature) {
/* 2979 */     if (this.extendedMetaDataHolderCache != null) {
/*      */       
/* 2981 */       EStructuralFeatureExtendedMetaData eStructuralFeatureExtendedMetaData = (EStructuralFeatureExtendedMetaData)this.extendedMetaDataHolderCache.get(eStructuralFeature);
/* 2982 */       if (eStructuralFeatureExtendedMetaData == null)
/*      */       {
/* 2984 */         this.extendedMetaDataHolderCache.put(eStructuralFeature, eStructuralFeatureExtendedMetaData = createEStructuralFeatureExtendedMetaData(eStructuralFeature));
/*      */       }
/* 2986 */       return eStructuralFeatureExtendedMetaData;
/*      */     } 
/*      */ 
/*      */     
/* 2990 */     EStructuralFeatureExtendedMetaData.Holder holder = (EStructuralFeatureExtendedMetaData.Holder)eStructuralFeature;
/* 2991 */     EStructuralFeatureExtendedMetaData result = holder.getExtendedMetaData();
/* 2992 */     if (result == null)
/*      */     {
/* 2994 */       holder.setExtendedMetaData(result = createEStructuralFeatureExtendedMetaData(eStructuralFeature));
/*      */     }
/* 2996 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EStructuralFeatureExtendedMetaData createEStructuralFeatureExtendedMetaData(EStructuralFeature eStructuralFeature) {
/* 3002 */     return new EStructuralFeatureExtendedMetaDataImpl(eStructuralFeature);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String replace(String in, String oldString, String newString) {
/* 3007 */     if (in == null || oldString == null)
/*      */     {
/* 3009 */       return in;
/*      */     }
/*      */     
/* 3012 */     int oldStringLength = oldString.length();
/* 3013 */     if (oldStringLength == 0)
/*      */     {
/* 3015 */       return in;
/*      */     }
/*      */     
/* 3018 */     if (newString == null)
/*      */     {
/* 3020 */       newString = "";
/*      */     }
/* 3022 */     int newStringLength = newString.length();
/*      */     
/* 3024 */     int index = -newStringLength;
/* 3025 */     StringBuffer result = new StringBuffer(in);
/* 3026 */     while ((index = indexOf(result, oldString, index + newStringLength)) >= 0)
/*      */     {
/* 3028 */       result.replace(index, index + oldStringLength, newString);
/*      */     }
/*      */     
/* 3031 */     return result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static int indexOf(StringBuffer in, String str, int fromIndex) {
/* 3036 */     if (in == null)
/*      */     {
/* 3038 */       return -1;
/*      */     }
/*      */     
/* 3041 */     if (str == null)
/*      */     {
/* 3043 */       str = "";
/*      */     }
/*      */     
/* 3046 */     int lengthIn = in.length();
/* 3047 */     int lengthStr = str.length();
/*      */     
/* 3049 */     if (lengthIn < lengthStr)
/*      */     {
/* 3051 */       return -1;
/*      */     }
/*      */     
/* 3054 */     if (fromIndex > lengthIn) {
/*      */       
/* 3056 */       if (lengthIn == 0 && fromIndex == 0 && lengthStr == 0)
/*      */       {
/* 3058 */         return 0;
/*      */       }
/* 3060 */       return -1;
/*      */     } 
/*      */     
/* 3063 */     if (fromIndex < 0)
/*      */     {
/* 3065 */       fromIndex = 0;
/*      */     }
/*      */     
/* 3068 */     if (lengthStr == 0)
/*      */     {
/* 3070 */       return fromIndex;
/*      */     }
/*      */     
/* 3073 */     int strPos = 0;
/* 3074 */     for (int i = fromIndex; i < lengthIn; i++) {
/*      */       
/* 3076 */       if (in.charAt(i) == str.charAt(strPos)) {
/*      */         
/* 3078 */         strPos++;
/* 3079 */         if (strPos == lengthStr)
/*      */         {
/* 3081 */           return i - lengthStr + 1;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 3086 */         strPos = 0;
/*      */       } 
/*      */     } 
/*      */     
/* 3090 */     return -1;
/*      */   }
/*      */   
/*      */   public static interface EClassifierExtendedMetaData {
/*      */     String getName();
/*      */     
/*      */     void setName(String param1String);
/*      */     
/*      */     int getContentKind();
/*      */     
/*      */     void setContentKind(int param1Int);
/*      */     
/*      */     int getDerivationKind();
/*      */     
/*      */     EDataType getBaseType();
/*      */     
/*      */     void setBaseType(EDataType param1EDataType);
/*      */     
/*      */     EDataType getItemType();
/*      */     
/*      */     void setItemType(EDataType param1EDataType);
/*      */     
/*      */     List<EDataType> getMemberTypes();
/*      */     
/*      */     void setMemberTypes(List<EDataType> param1List);
/*      */     
/*      */     int getWhiteSpaceFacet();
/*      */     
/*      */     void setWhiteSpaceFacet(int param1Int);
/*      */     
/*      */     List<String> getEnumerationFacet();
/*      */     
/*      */     void setEnumerationFacet(List<String> param1List);
/*      */     
/*      */     List<String> getPatternFacet();
/*      */     
/*      */     void setPatternFacet(List<String> param1List);
/*      */     
/*      */     int getTotalDigitsFacet();
/*      */     
/*      */     void setTotalDigitsFacet(int param1Int);
/*      */     
/*      */     int getFractionDigitsFacet();
/*      */     
/*      */     void setFractionDigitsFacet(int param1Int);
/*      */     
/*      */     int getLengthFacet();
/*      */     
/*      */     void setLengthFacet(int param1Int);
/*      */     
/*      */     int getMinLengthFacet();
/*      */     
/*      */     void setMinLengthFacet(int param1Int);
/*      */     
/*      */     int getMaxLengthFacet();
/*      */     
/*      */     void setMaxLengthFacet(int param1Int);
/*      */     
/*      */     String getMinExclusiveFacet();
/*      */     
/*      */     void setMinExclusiveFacet(String param1String);
/*      */     
/*      */     String getMaxExclusiveFacet();
/*      */     
/*      */     void setMaxExclusiveFacet(String param1String);
/*      */     
/*      */     String getMinInclusiveFacet();
/*      */     
/*      */     void setMinInclusiveFacet(String param1String);
/*      */     
/*      */     String getMaxInclusiveFacet();
/*      */     
/*      */     void setMaxInclusiveFacet(String param1String);
/*      */     
/*      */     public static interface Holder {
/*      */       BasicExtendedMetaData.EClassifierExtendedMetaData getExtendedMetaData();
/*      */       
/*      */       void setExtendedMetaData(BasicExtendedMetaData.EClassifierExtendedMetaData param2EClassifierExtendedMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface Holder {
/*      */     BasicExtendedMetaData.EClassifierExtendedMetaData getExtendedMetaData();
/*      */     
/*      */     void setExtendedMetaData(BasicExtendedMetaData.EClassifierExtendedMetaData param1EClassifierExtendedMetaData);
/*      */   }
/*      */   
/*      */   public static interface EPackageExtendedMetaData {
/*      */     boolean isQualified();
/*      */     
/*      */     void setQualified(boolean param1Boolean);
/*      */     
/*      */     EClassifier getType(String param1String);
/*      */     
/*      */     void rename(EClassifier param1EClassifier, String param1String);
/*      */     
/*      */     public static interface Holder {
/*      */       BasicExtendedMetaData.EPackageExtendedMetaData getExtendedMetaData();
/*      */       
/*      */       void setExtendedMetaData(BasicExtendedMetaData.EPackageExtendedMetaData param2EPackageExtendedMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface Holder {
/*      */     BasicExtendedMetaData.EPackageExtendedMetaData getExtendedMetaData();
/*      */     
/*      */     void setExtendedMetaData(BasicExtendedMetaData.EPackageExtendedMetaData param1EPackageExtendedMetaData);
/*      */   }
/*      */   
/*      */   public static interface EStructuralFeatureExtendedMetaData {
/*      */     String getName();
/*      */     
/*      */     void setName(String param1String);
/*      */     
/*      */     String getNamespace();
/*      */     
/*      */     void setNamespace(String param1String);
/*      */     
/*      */     int getFeatureKind();
/*      */     
/*      */     void setFeatureKind(int param1Int);
/*      */     
/*      */     List<String> getWildcards();
/*      */     
/*      */     void setWildcards(List<String> param1List);
/*      */     
/*      */     int getProcessingKind();
/*      */     
/*      */     void setProcessingKind(int param1Int);
/*      */     
/*      */     EStructuralFeature getGroup();
/*      */     
/*      */     void setGroup(EStructuralFeature param1EStructuralFeature);
/*      */     
/*      */     EStructuralFeature getAffiliation();
/*      */     
/*      */     void setAffiliation(EStructuralFeature param1EStructuralFeature);
/*      */     
/*      */     Map<EClass, FeatureMapUtil.Validator> getValidatorMap();
/*      */     
/*      */     public static interface Holder {
/*      */       BasicExtendedMetaData.EStructuralFeatureExtendedMetaData getExtendedMetaData();
/*      */       
/*      */       void setExtendedMetaData(BasicExtendedMetaData.EStructuralFeatureExtendedMetaData param2EStructuralFeatureExtendedMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface Holder {
/*      */     BasicExtendedMetaData.EStructuralFeatureExtendedMetaData getExtendedMetaData();
/*      */     
/*      */     void setExtendedMetaData(BasicExtendedMetaData.EStructuralFeatureExtendedMetaData param1EStructuralFeatureExtendedMetaData);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\BasicExtendedMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */