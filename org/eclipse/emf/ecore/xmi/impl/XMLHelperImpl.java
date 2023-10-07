/*      */ package org.eclipse.emf.ecore.xmi.impl;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TreeMap;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.BasicEMap;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.common.util.UniqueEList;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ import org.eclipse.emf.ecore.xmi.DanglingHREFException;
/*      */ import org.eclipse.emf.ecore.xmi.IllegalValueException;
/*      */ import org.eclipse.emf.ecore.xmi.NameInfo;
/*      */ import org.eclipse.emf.ecore.xmi.XMIException;
/*      */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*      */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.QName;
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
/*      */ public class XMLHelperImpl
/*      */   implements XMLHelper
/*      */ {
/*   66 */   protected static final Integer INTEGER_DATATYPE_IS_MANY = Integer.valueOf(2);
/*   67 */   protected static final Integer INTEGER_DATATYPE_SINGLE = Integer.valueOf(1);
/*   68 */   protected static final Integer INTEGER_IS_MANY_ADD = Integer.valueOf(3);
/*   69 */   protected static final Integer INTEGER_IS_MANY_MOVE = Integer.valueOf(4);
/*   70 */   protected static final Integer INTEGER_OTHER = Integer.valueOf(5);
/*      */   
/*      */   protected EPackage noNamespacePackage;
/*      */   
/*      */   protected XMLResource.XMLMap xmlMap;
/*      */   protected ExtendedMetaData extendedMetaData;
/*      */   protected boolean laxFeatureProcessing;
/*      */   protected EPackage.Registry packageRegistry;
/*      */   protected XMLResource resource;
/*      */   protected URI resourceURI;
/*      */   protected boolean deresolve;
/*      */   protected Map<EPackage, String> packages;
/*      */   protected Map<EStructuralFeature, Integer> featuresToKinds;
/*      */   protected String processDanglingHREF;
/*      */   protected DanglingHREFException danglingHREFException;
/*      */   protected EMap<String, String> prefixesToURIs;
/*      */   protected Map<String, List<String>> urisToPrefixes;
/*      */   protected Map<String, String> anyPrefixesToURIs;
/*      */   protected NamespaceSupport namespaceSupport;
/*      */   protected EClass anySimpleType;
/*      */   protected boolean seenEmptyStringMapping;
/*   91 */   protected EPackage xmlSchemaTypePackage = (EPackage)XMLTypePackage.eINSTANCE;
/*      */   
/*      */   protected List<String> allPrefixToURI;
/*      */   
/*      */   protected boolean checkForDuplicates;
/*      */   protected boolean mustHavePrefix;
/*      */   protected XMLResource.URIHandler uriHandler;
/*      */   protected List<? extends EObject> roots;
/*      */   protected String[] fragmentPrefixes;
/*      */   private EPackage previousPackage;
/*      */   private String previousNS;
/*      */   
/*      */   public static String saveString(Map<?, ?> options, List<? extends EObject> contents, String encoding, XMLHelper helper) throws Exception {
/*  104 */     if (helper == null)
/*      */     {
/*  106 */       helper = new XMIHelperImpl();
/*      */     }
/*  108 */     if (!options.containsKey("DECLARE_XML")) {
/*      */       
/*  110 */       Map<Object, Object> modifiedOptions = new HashMap<Object, Object>(options);
/*  111 */       modifiedOptions.put("DECLARE_XML", Boolean.FALSE);
/*  112 */       options = modifiedOptions;
/*      */     } 
/*  114 */     XMLSaveImpl save = new XMISaveImpl(options, helper, encoding);
/*      */     
/*  116 */     if (Boolean.TRUE.equals(options.get("DEFER_IDREF_RESOLUTION")))
/*      */     {
/*  118 */       ((XMLHelperImpl)helper).checkForDuplicates = true;
/*      */     }
/*      */     
/*  121 */     ((XMLHelperImpl)helper).processDanglingHREF = (String)options.get("PROCESS_DANGLING_HREF");
/*  122 */     save.traverse(contents);
/*  123 */     if (save.useCache) {
/*      */       
/*  125 */       if (save.doc != null)
/*      */       {
/*  127 */         ConfigurationCache.INSTANCE.releasePrinter(save.doc);
/*      */       }
/*  129 */       if (save.escape != null)
/*      */       {
/*  131 */         ConfigurationCache.INSTANCE.releaseEscape(save.escape);
/*      */       }
/*      */     } 
/*  134 */     char[] chars = save.toChar();
/*  135 */     return new String(chars);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLHelperImpl() {
/*  141 */     this.packages = new HashMap<EPackage, String>();
/*  142 */     this.featuresToKinds = new HashMap<EStructuralFeature, Integer>();
/*  143 */     this.prefixesToURIs = 
/*  144 */       (EMap<String, String>)new BasicEMap<String, String>()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */         
/*      */         protected List<String> getPrefixes(String uri) {
/*  150 */           List<String> result = XMLHelperImpl.this.urisToPrefixes.get(uri);
/*  151 */           if (result == null)
/*      */           {
/*  153 */             XMLHelperImpl.this.urisToPrefixes.put(uri, result = new ArrayList<String>());
/*      */           }
/*  155 */           return result;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didAdd(BasicEMap.Entry<String, String> entry) {
/*  161 */           getPrefixes((String)entry.getValue()).add((String)entry.getKey());
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didClear(BasicEList[] oldEntryData) {
/*  167 */           XMLHelperImpl.this.urisToPrefixes.clear();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didModify(BasicEMap.Entry<String, String> entry, String oldValue) {
/*  173 */           String key = (String)entry.getKey();
/*  174 */           getPrefixes(oldValue).remove(key);
/*  175 */           getPrefixes((String)entry.getValue()).add(key);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void didRemove(BasicEMap.Entry<String, String> entry) {
/*  181 */           getPrefixes((String)entry.getValue()).add((String)entry.getKey());
/*      */         }
/*      */       };
/*      */     
/*  185 */     this.urisToPrefixes = new HashMap<String, List<String>>();
/*      */     
/*  187 */     this.anyPrefixesToURIs = new HashMap<String, String>();
/*  188 */     this.allPrefixToURI = new ArrayList<String>();
/*  189 */     this.namespaceSupport = new NamespaceSupport();
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLHelperImpl(XMLResource resource) {
/*  194 */     this();
/*  195 */     setResource(resource);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOptions(Map<?, ?> options) {
/*  200 */     this.laxFeatureProcessing = Boolean.TRUE.equals(options.get("LAX_FEATURE_PROCESSING"));
/*  201 */     this.uriHandler = (XMLResource.URIHandler)options.get("URI_HANDLER");
/*  202 */     if (this.uriHandler != null)
/*      */     {
/*  204 */       this.uriHandler.setBaseURI(this.resourceURI);
/*      */     }
/*      */     
/*  207 */     List<? extends EObject> roots = (List<? extends EObject>)options.get("ROOT_OBJECTS");
/*  208 */     if (roots != null) {
/*      */       
/*  210 */       this.roots = roots;
/*  211 */       this.fragmentPrefixes = new String[roots.size()];
/*  212 */       int count = 0;
/*  213 */       for (EObject root : roots) {
/*      */         
/*  215 */         InternalEObject internalEObject = (InternalEObject)root;
/*  216 */         List<String> uriFragmentPath = new ArrayList<String>();
/*  217 */         for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer()) {
/*      */           
/*  219 */           uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), (EObject)internalEObject));
/*  220 */           internalEObject = container;
/*  221 */           Resource.Internal internal = container.eDirectResource();
/*  222 */           if (internal != null) {
/*      */             
/*  224 */             int index = internal.getContents().indexOf(container);
/*  225 */             uriFragmentPath.add((index != 0) ? Integer.toString(index) : "");
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  230 */         StringBuilder result = new StringBuilder("/");
/*  231 */         for (int i = uriFragmentPath.size() - 1; i >= 1; i--) {
/*      */           
/*  233 */           result.append(uriFragmentPath.get(i));
/*  234 */           result.append('/');
/*      */         } 
/*  236 */         this.fragmentPrefixes[count++] = result.toString();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNoNamespacePackage(EPackage pkg) {
/*  243 */     this.noNamespacePackage = pkg;
/*      */   }
/*      */ 
/*      */   
/*      */   public EPackage getNoNamespacePackage() {
/*  248 */     return 
/*  249 */       (this.noNamespacePackage != null) ? 
/*  250 */       this.noNamespacePackage : (
/*  251 */       (this.extendedMetaData != null) ? 
/*  252 */       this.extendedMetaData.getPackage(null) : 
/*  253 */       null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setXMLMap(XMLResource.XMLMap map) {
/*  258 */     this.xmlMap = map;
/*  259 */     if (map != null && map.getNoNamespacePackage() != null)
/*      */     {
/*  261 */       setNoNamespacePackage(map.getNoNamespacePackage());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLResource.XMLMap getXMLMap() {
/*  267 */     return this.xmlMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setExtendedMetaData(ExtendedMetaData extendedMetaData) {
/*  272 */     this.extendedMetaData = extendedMetaData;
/*  273 */     if (extendedMetaData != null && extendedMetaData.getPackage(null) != null)
/*      */     {
/*  275 */       setNoNamespacePackage(extendedMetaData.getPackage(null));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ExtendedMetaData getExtendedMetaData() {
/*  281 */     return this.extendedMetaData;
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLResource getResource() {
/*  286 */     return this.resource;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResource(XMLResource resource) {
/*  291 */     this.resource = resource;
/*  292 */     if (resource == null) {
/*      */       
/*  294 */       this.resourceURI = null;
/*  295 */       this.deresolve = false;
/*  296 */       this.packageRegistry = EPackage.Registry.INSTANCE;
/*      */     }
/*      */     else {
/*      */       
/*  300 */       this.resourceURI = resource.getURI();
/*  301 */       this.deresolve = (this.resourceURI != null && !this.resourceURI.isRelative() && this.resourceURI.isHierarchical());
/*  302 */       this.packageRegistry = (resource.getResourceSet() == null) ? EPackage.Registry.INSTANCE : resource.getResourceSet().getPackageRegistry();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getValue(EObject obj, EStructuralFeature f) {
/*  308 */     return obj.eGet(f, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getQName(EClass c) {
/*  313 */     String name = getName((ENamedElement)c);
/*  314 */     if (this.xmlMap != null) {
/*      */       
/*  316 */       XMLResource.XMLInfo clsInfo = this.xmlMap.getInfo((ENamedElement)c);
/*      */       
/*  318 */       if (clsInfo != null) {
/*      */         
/*  320 */         String targetNamespace = clsInfo.getTargetNamespace();
/*  321 */         return getQName(targetNamespace, name);
/*      */       } 
/*      */     } 
/*      */     
/*  325 */     return getQName(c.getEPackage(), name);
/*      */   }
/*      */ 
/*      */   
/*      */   public void populateNameInfo(NameInfo nameInfo, EClass c) {
/*  330 */     String name = getName((ENamedElement)c);
/*  331 */     nameInfo.setLocalPart(name);
/*  332 */     if (this.xmlMap != null) {
/*      */       
/*  334 */       XMLResource.XMLInfo clsInfo = this.xmlMap.getInfo((ENamedElement)c);
/*      */       
/*  336 */       if (clsInfo != null) {
/*      */         
/*  338 */         String targetNamespace = clsInfo.getTargetNamespace();
/*  339 */         nameInfo.setNamespaceURI(targetNamespace);
/*  340 */         nameInfo.setQualifiedName(getQName(targetNamespace, name));
/*      */         return;
/*      */       } 
/*      */     } 
/*  344 */     getQName(nameInfo, c.getEPackage(), name);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getQName(EDataType c) {
/*  349 */     String name = getName((ENamedElement)c);
/*  350 */     if (this.xmlMap != null) {
/*      */       
/*  352 */       XMLResource.XMLInfo clsInfo = this.xmlMap.getInfo((ENamedElement)c);
/*      */       
/*  354 */       if (clsInfo != null) {
/*      */         
/*  356 */         String targetNamespace = clsInfo.getTargetNamespace();
/*  357 */         return getQName(targetNamespace, name);
/*      */       } 
/*      */     } 
/*      */     
/*  361 */     return getQName(c.getEPackage(), name);
/*      */   }
/*      */ 
/*      */   
/*      */   public void populateNameInfo(NameInfo nameInfo, EDataType eDataType) {
/*  366 */     String name = getName((ENamedElement)eDataType);
/*  367 */     nameInfo.setLocalPart(name);
/*  368 */     if (this.xmlMap != null) {
/*      */       
/*  370 */       XMLResource.XMLInfo clsInfo = this.xmlMap.getInfo((ENamedElement)eDataType);
/*      */       
/*  372 */       if (clsInfo != null) {
/*      */         
/*  374 */         String targetNamespace = clsInfo.getTargetNamespace();
/*  375 */         nameInfo.setNamespaceURI(targetNamespace);
/*  376 */         nameInfo.setQualifiedName(getQName(targetNamespace, name));
/*      */         return;
/*      */       } 
/*      */     } 
/*  380 */     getQName(nameInfo, eDataType.getEPackage(), name);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getQName(EStructuralFeature feature) {
/*  385 */     if (this.extendedMetaData != null) {
/*      */       
/*  387 */       String namespace = this.extendedMetaData.getNamespace(feature);
/*  388 */       String str1 = this.extendedMetaData.getName(feature);
/*  389 */       String result = str1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  394 */       if (namespace != null) {
/*      */         EPackage ePackage;
/*      */ 
/*      */ 
/*      */         
/*  399 */         if (namespace.equals(this.previousNS)) {
/*      */           
/*  401 */           ePackage = this.previousPackage;
/*      */         }
/*      */         else {
/*      */           
/*  405 */           ePackage = this.extendedMetaData.getPackage(namespace);
/*  406 */           if (ePackage == null)
/*      */           {
/*  408 */             ePackage = this.extendedMetaData.demandPackage(namespace);
/*      */           }
/*  410 */           this.previousPackage = ePackage;
/*  411 */           this.previousNS = namespace;
/*      */         } 
/*      */         
/*  414 */         result = getQName(ePackage, str1);
/*      */ 
/*      */ 
/*      */         
/*  418 */         if (result.length() == str1.length() && this.extendedMetaData.getFeatureKind(feature) == 2)
/*      */         {
/*  420 */           result = getQName(ePackage, str1, true);
/*      */         }
/*      */       } 
/*  423 */       return result;
/*      */     } 
/*      */     
/*  426 */     String name = getName((ENamedElement)feature);
/*  427 */     if (this.xmlMap != null) {
/*      */       
/*  429 */       XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)feature);
/*  430 */       if (info != null)
/*      */       {
/*  432 */         return getQName(info.getTargetNamespace(), name);
/*      */       }
/*      */     } 
/*      */     
/*  436 */     return name;
/*      */   }
/*      */ 
/*      */   
/*      */   public void populateNameInfo(NameInfo nameInfo, EStructuralFeature feature) {
/*  441 */     if (this.extendedMetaData != null) {
/*      */       
/*  443 */       String namespace = this.extendedMetaData.getNamespace(feature);
/*  444 */       String name = this.extendedMetaData.getName(feature);
/*  445 */       nameInfo.setNamespaceURI(namespace);
/*  446 */       nameInfo.setLocalPart(name);
/*  447 */       nameInfo.setQualifiedName(name);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  452 */       if (namespace != null)
/*      */       {
/*      */ 
/*      */         
/*  456 */         EPackage ePackage = this.extendedMetaData.getPackage(namespace);
/*  457 */         if (ePackage == null)
/*      */         {
/*  459 */           ePackage = this.extendedMetaData.demandPackage(namespace);
/*      */         }
/*      */         
/*  462 */         String result = getQName(nameInfo, ePackage, name);
/*      */ 
/*      */ 
/*      */         
/*  466 */         if (result.length() == name.length() && this.extendedMetaData.getFeatureKind(feature) == 2)
/*      */         {
/*  468 */           getQName(nameInfo, ePackage, name, true);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  474 */       String name = getName((ENamedElement)feature);
/*  475 */       nameInfo.setNamespaceURI(null);
/*  476 */       nameInfo.setLocalPart(name);
/*  477 */       if (this.xmlMap != null) {
/*      */         
/*  479 */         XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)feature);
/*  480 */         if (info != null) {
/*      */           
/*  482 */           String targetNamespace = info.getTargetNamespace();
/*  483 */           nameInfo.setNamespaceURI(targetNamespace);
/*  484 */           nameInfo.setQualifiedName(getQName(targetNamespace, name));
/*      */         } 
/*      */       } 
/*  487 */       nameInfo.setQualifiedName(name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQName(NameInfo nameInfo, EPackage ePackage, String name) {
/*  493 */     String qname = getQName(nameInfo, ePackage, name, this.mustHavePrefix);
/*  494 */     nameInfo.setQualifiedName(qname);
/*  495 */     return qname;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQName(NameInfo nameInfo, EPackage ePackage, String name, boolean mustHavePrefix) {
/*  500 */     String nsPrefix = getPrefix(ePackage, mustHavePrefix);
/*  501 */     nameInfo.setNamespaceURI(getNamespaceURI(nsPrefix));
/*  502 */     if ("".equals(nsPrefix))
/*      */     {
/*  504 */       return name;
/*      */     }
/*  506 */     if (name.length() == 0)
/*      */     {
/*  508 */       return nsPrefix;
/*      */     }
/*      */ 
/*      */     
/*  512 */     return String.valueOf(nsPrefix) + ":" + name;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getQName(EPackage ePackage, String name) {
/*  518 */     return getQName(ePackage, name, this.mustHavePrefix);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix) {
/*  523 */     String nsPrefix = getPrefix(ePackage, mustHavePrefix);
/*  524 */     if ("".equals(nsPrefix))
/*      */     {
/*  526 */       return name;
/*      */     }
/*  528 */     if (name.length() == 0)
/*      */     {
/*  530 */       return nsPrefix;
/*      */     }
/*      */ 
/*      */     
/*  534 */     return String.valueOf(nsPrefix) + ":" + name;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(EPackage ePackage) {
/*  540 */     return getPrefix(ePackage, this.mustHavePrefix);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(String prefix) {
/*  545 */     String namespaceURI = this.namespaceSupport.getURI(prefix);
/*  546 */     if (namespaceURI == null)
/*      */     {
/*  548 */       namespaceURI = (String)this.prefixesToURIs.get(prefix);
/*      */     }
/*  550 */     return namespaceURI;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getPrefix(EPackage ePackage, boolean mustHavePrefix) {
/*  555 */     String nsPrefix = this.packages.get(ePackage);
/*  556 */     if (nsPrefix == null || (mustHavePrefix && nsPrefix.length() == 0)) {
/*      */       
/*  558 */       String nsURI = 
/*  559 */         (this.xmlSchemaTypePackage == ePackage) ? 
/*  560 */         "http://www.w3.org/2001/XMLSchema" : (
/*  561 */         (this.extendedMetaData == null) ? 
/*  562 */         ePackage.getNsURI() : 
/*  563 */         this.extendedMetaData.getNamespace(ePackage));
/*      */       
/*  565 */       boolean found = false;
/*  566 */       List<String> prefixes = this.urisToPrefixes.get(nsURI);
/*  567 */       if (prefixes != null)
/*      */       {
/*  569 */         for (String prefix : prefixes) {
/*      */           
/*  571 */           nsPrefix = prefix;
/*  572 */           if (!mustHavePrefix || nsPrefix.length() > 0) {
/*      */             
/*  574 */             found = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  580 */       if (!found) {
/*      */ 
/*      */         
/*  583 */         nsPrefix = this.namespaceSupport.getPrefix(nsURI);
/*  584 */         if (nsPrefix != null)
/*      */         {
/*  586 */           return nsPrefix;
/*      */         }
/*      */         
/*  589 */         if (nsURI != null)
/*      */         {
/*  591 */           nsPrefix = (this.xmlSchemaTypePackage == ePackage) ? "xsd" : ePackage.getNsPrefix();
/*      */         }
/*  593 */         if (nsPrefix == null)
/*      */         {
/*  595 */           nsPrefix = mustHavePrefix ? "_" : "";
/*      */         }
/*      */         
/*  598 */         if (this.prefixesToURIs.containsKey(nsPrefix)) {
/*      */           
/*  600 */           String currentValue = (String)this.prefixesToURIs.get(nsPrefix);
/*  601 */           if ((currentValue == null) ? (nsURI != null) : !currentValue.equals(nsURI)) {
/*      */             
/*  603 */             int index = 1;
/*  604 */             while (this.prefixesToURIs.containsKey(String.valueOf(nsPrefix) + "_" + index))
/*      */             {
/*  606 */               index++;
/*      */             }
/*  608 */             nsPrefix = String.valueOf(nsPrefix) + "_" + index;
/*      */           } 
/*      */         } 
/*      */         
/*  612 */         this.prefixesToURIs.put(nsPrefix, nsURI);
/*      */       } 
/*      */       
/*  615 */       if (!this.packages.containsKey(ePackage))
/*      */       {
/*  617 */         this.packages.put(ePackage, nsPrefix);
/*      */       }
/*      */     } 
/*      */     
/*  621 */     return nsPrefix;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> getPrefixes(EPackage ePackage) {
/*  626 */     UniqueEList<String> uniqueEList = new UniqueEList();
/*  627 */     uniqueEList.add(getPrefix(ePackage));
/*  628 */     String namespace = (this.extendedMetaData == null) ? ePackage.getNsURI() : this.extendedMetaData.getNamespace(ePackage);
/*  629 */     List<String> prefixes = this.urisToPrefixes.get(namespace);
/*  630 */     if (prefixes != null)
/*      */     {
/*  632 */       uniqueEList.addAll(prefixes);
/*      */     }
/*  634 */     return (List<String>)uniqueEList;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQName(String uri, String name) {
/*  639 */     if (uri == null) {
/*      */       
/*  641 */       EPackage theNoNamespacePackage = getNoNamespacePackage();
/*  642 */       if (theNoNamespacePackage != null)
/*      */       {
/*  644 */         this.packages.put(theNoNamespacePackage, "");
/*      */       }
/*      */       
/*  647 */       return name;
/*      */     } 
/*      */     
/*  650 */     EPackage ePackage = 
/*  651 */       (this.extendedMetaData == null) ? 
/*  652 */       EPackage.Registry.INSTANCE.getEPackage(uri) : 
/*  653 */       this.extendedMetaData.getPackage(uri);
/*  654 */     if (ePackage == null) {
/*      */       
/*  656 */       if (this.extendedMetaData != null)
/*      */       {
/*  658 */         return getQName(this.extendedMetaData.demandPackage(uri), name);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  663 */       return name;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  668 */     return getQName(ePackage, name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName(ENamedElement obj) {
/*  674 */     if (this.extendedMetaData != null)
/*      */     {
/*  676 */       return 
/*  677 */         (obj instanceof EStructuralFeature) ? 
/*  678 */         this.extendedMetaData.getName((EStructuralFeature)obj) : 
/*  679 */         this.extendedMetaData.getName((EClassifier)obj);
/*      */     }
/*      */     
/*  682 */     if (this.xmlMap != null) {
/*      */       
/*  684 */       XMLResource.XMLInfo info = this.xmlMap.getInfo(obj);
/*  685 */       if (info != null) {
/*      */         
/*  687 */         String result = info.getName();
/*  688 */         if (result != null)
/*      */         {
/*  690 */           return result;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  695 */     return obj.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getID(EObject obj) {
/*  701 */     return (this.resource == null) ? null : this.resource.getID(obj);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getURIFragmentQuery(Resource containingResource, EObject object) {
/*  706 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getURIFragment(Resource containingResource, EObject object) {
/*  711 */     if (this.roots != null && containingResource == this.resource && !EcoreUtil.isAncestor(this.roots, object)) {
/*      */       
/*  713 */       URI uriResult = handleDanglingHREF(object);
/*  714 */       return (uriResult == null || !uriResult.hasFragment()) ? null : uriResult.fragment();
/*      */     } 
/*      */ 
/*      */     
/*  718 */     String result = containingResource.getURIFragment(object);
/*  719 */     if (result.length() > 0 && result.charAt(0) != '/') {
/*      */       
/*  721 */       String query = getURIFragmentQuery(containingResource, object);
/*  722 */       if (query != null)
/*      */       {
/*  724 */         result = String.valueOf(result) + "?" + query + "?";
/*      */       }
/*      */     }
/*  727 */     else if ("/-1".equals(result)) {
/*      */       
/*  729 */       if (object.eResource() != containingResource)
/*      */       {
/*  731 */         URI uriResult = handleDanglingHREF(object);
/*  732 */         return (uriResult == null || !uriResult.hasFragment()) ? null : uriResult.fragment();
/*      */       }
/*      */     
/*  735 */     } else if (this.fragmentPrefixes != null) {
/*      */       
/*  737 */       for (int i = 0; i < this.fragmentPrefixes.length; i++) {
/*      */         
/*  739 */         String fragmentPrefix = this.fragmentPrefixes[i];
/*  740 */         if (result.startsWith(fragmentPrefix)) {
/*      */           
/*  742 */           result = "/" + ((i == 0) ? "" : Integer.toString(i)) + result.substring(fragmentPrefix.length() - 1);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  747 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getIDREF(EObject obj) {
/*  753 */     return (this.resource == null) ? null : getURIFragment((Resource)this.resource, obj);
/*      */   }
/*      */ 
/*      */   
/*      */   protected URI handleDanglingHREF(EObject object) {
/*  758 */     if (!"DISCARD".equals(this.processDanglingHREF)) {
/*      */       
/*  760 */       DanglingHREFException exception = new DanglingHREFException(
/*  761 */           "The object '" + object + "' is not contained in a resource.", (
/*  762 */           this.resource == null || this.resource.getURI() == null) ? "unknown" : this.resource.getURI().toString(), 0, 0);
/*      */       
/*  764 */       if (this.danglingHREFException == null)
/*      */       {
/*  766 */         this.danglingHREFException = exception;
/*      */       }
/*      */       
/*  769 */       if (this.resource != null)
/*      */       {
/*  771 */         this.resource.getErrors().add(exception);
/*      */       }
/*      */     } 
/*      */     
/*  775 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHREF(EObject obj) {
/*  780 */     InternalEObject o = (InternalEObject)obj;
/*      */     
/*  782 */     URI objectURI = o.eProxyURI();
/*  783 */     if (objectURI == null) {
/*      */       
/*  785 */       Resource otherResource = obj.eResource();
/*  786 */       if (otherResource == null) {
/*      */         
/*  788 */         if (this.resource != null && this.resource.getID(obj) != null)
/*      */         {
/*  790 */           objectURI = getHREF((Resource)this.resource, obj);
/*      */         }
/*      */         else
/*      */         {
/*  794 */           objectURI = handleDanglingHREF(obj);
/*  795 */           if (objectURI == null)
/*      */           {
/*  797 */             return null;
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  803 */         objectURI = getHREF(otherResource, obj);
/*      */       } 
/*      */     } 
/*      */     
/*  807 */     objectURI = deresolve(objectURI);
/*      */     
/*  809 */     return objectURI.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   protected URI getHREF(Resource otherResource, EObject obj) {
/*  814 */     return otherResource.getURI().appendFragment(getURIFragment(otherResource, obj));
/*      */   }
/*      */ 
/*      */   
/*      */   public URI deresolve(URI uri) {
/*  819 */     if (this.uriHandler != null) {
/*      */       
/*  821 */       uri = this.uriHandler.deresolve(uri);
/*      */     }
/*  823 */     else if (this.deresolve && !uri.isRelative()) {
/*      */       
/*  825 */       URI deresolvedURI = uri.deresolve(this.resourceURI, true, true, false);
/*  826 */       if (deresolvedURI.hasRelativePath())
/*      */       {
/*  828 */         uri = deresolvedURI;
/*      */       }
/*      */     } 
/*      */     
/*  832 */     return uri;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFeatureKind(EStructuralFeature feature) {
/*  837 */     Integer kind = this.featuresToKinds.get(feature);
/*  838 */     if (kind != null)
/*      */     {
/*  840 */       return kind.intValue();
/*      */     }
/*      */ 
/*      */     
/*  844 */     computeFeatureKind(feature);
/*  845 */     kind = this.featuresToKinds.get(feature);
/*  846 */     if (kind != null)
/*      */     {
/*  848 */       return kind.intValue();
/*      */     }
/*      */ 
/*      */     
/*  852 */     this.featuresToKinds.put(feature, INTEGER_OTHER);
/*  853 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EObject createObject(EFactory eFactory, EClassifier type) {
/*  860 */     EObject newObject = null;
/*  861 */     if (eFactory != null)
/*      */     {
/*  863 */       if (this.extendedMetaData != null) {
/*      */         
/*  865 */         if (type == null)
/*      */         {
/*  867 */           return null;
/*      */         }
/*  869 */         if (type instanceof EClass)
/*      */         {
/*  871 */           EClass eClass = (EClass)type;
/*  872 */           if (!eClass.isAbstract())
/*      */           {
/*  874 */             newObject = eFactory.create((EClass)type);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  879 */           SimpleAnyType result = (SimpleAnyType)EcoreUtil.create(this.anySimpleType);
/*  880 */           result.setInstanceType((EDataType)type);
/*  881 */           SimpleAnyType simpleAnyType1 = result;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  886 */       else if (type != null) {
/*      */         
/*  888 */         EClass eClass = (EClass)type;
/*  889 */         if (!eClass.isAbstract())
/*      */         {
/*  891 */           newObject = eFactory.create((EClass)type);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  896 */     return newObject;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EClassifier getType(EFactory eFactory, String typeName) {
/*  902 */     if (eFactory != null) {
/*      */       
/*  904 */       EPackage ePackage = eFactory.getEPackage();
/*  905 */       if (this.extendedMetaData != null)
/*      */       {
/*  907 */         return this.extendedMetaData.getType(ePackage, typeName);
/*      */       }
/*      */ 
/*      */       
/*  911 */       EClass eClass = (EClass)ePackage.getEClassifier(typeName);
/*  912 */       if (eClass == null && this.xmlMap != null)
/*      */       {
/*  914 */         return this.xmlMap.getClassifier(ePackage.getNsURI(), typeName);
/*      */       }
/*  916 */       return (EClassifier)eClass;
/*      */     } 
/*      */     
/*  919 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public EObject createObject(EFactory eFactory, String classXMIName) {
/*  928 */     return createObject(eFactory, getType(eFactory, classXMIName));
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name) {
/*  933 */     EStructuralFeature feature = getFeatureWithoutMap(eClass, name);
/*  934 */     if (feature == null)
/*      */     {
/*  936 */       if (this.xmlMap != null) {
/*      */         
/*  938 */         feature = this.xmlMap.getFeature(eClass, namespaceURI, name);
/*  939 */         if (feature != null)
/*      */         {
/*  941 */           computeFeatureKind(feature);
/*      */         }
/*      */       }
/*  944 */       else if (this.laxFeatureProcessing && this.extendedMetaData != null) {
/*      */         
/*  946 */         EList<EStructuralFeature> eList = eClass.getEAllStructuralFeatures();
/*  947 */         for (int i = 0, size = eList.size(); i < size; i++) {
/*      */           
/*  949 */           EStructuralFeature eStructuralFeature = eList.get(i);
/*  950 */           if (name.equals(this.extendedMetaData.getName(eStructuralFeature)) && (
/*  951 */             (namespaceURI == null) ? (this.extendedMetaData.getNamespace(eStructuralFeature) == null) : namespaceURI.equals(this.extendedMetaData.getNamespace(eStructuralFeature))))
/*      */           {
/*  953 */             return eStructuralFeature;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  959 */     return feature;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name, boolean isElement) {
/*  964 */     if (this.extendedMetaData != null) {
/*      */ 
/*      */ 
/*      */       
/*  968 */       if (isElement && namespaceURI == null)
/*      */       {
/*  970 */         this.seenEmptyStringMapping = true;
/*      */       }
/*  972 */       EStructuralFeature eStructuralFeature = 
/*  973 */         isElement ? 
/*  974 */         this.extendedMetaData.getElement(eClass, namespaceURI, name) : 
/*  975 */         this.extendedMetaData.getAttribute(eClass, (namespaceURI == "") ? null : namespaceURI, name);
/*  976 */       if (eStructuralFeature != null) {
/*      */         
/*  978 */         computeFeatureKind(eStructuralFeature);
/*      */       }
/*      */       else {
/*      */         
/*  982 */         eStructuralFeature = getFeature(eClass, namespaceURI, name);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  988 */         if (!this.laxFeatureProcessing && eStructuralFeature != null && 
/*  989 */           this.extendedMetaData.getFeatureKind(eStructuralFeature) != 0)
/*      */         {
/*  991 */           eStructuralFeature = null;
/*      */         }
/*      */       } 
/*      */       
/*  995 */       return eStructuralFeature;
/*      */     } 
/*      */     
/*  998 */     return getFeature(eClass, namespaceURI, name);
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature getFeatureWithoutMap(EClass eClass, String name) {
/* 1003 */     EStructuralFeature feature = eClass.getEStructuralFeature(name);
/*      */     
/* 1005 */     if (feature != null) {
/* 1006 */       computeFeatureKind(feature);
/*      */     }
/* 1008 */     return feature;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void computeFeatureKind(EStructuralFeature feature) {
/* 1013 */     EClassifier eClassifier = feature.getEType();
/*      */     
/* 1015 */     if (eClassifier instanceof EDataType) {
/*      */       
/* 1017 */       if (feature.isMany())
/*      */       {
/* 1019 */         this.featuresToKinds.put(feature, INTEGER_DATATYPE_IS_MANY);
/*      */       }
/*      */       else
/*      */       {
/* 1023 */         this.featuresToKinds.put(feature, INTEGER_DATATYPE_SINGLE);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1028 */     else if (feature.isMany()) {
/*      */       
/* 1030 */       EReference reference = (EReference)feature;
/* 1031 */       EReference opposite = reference.getEOpposite();
/*      */       
/* 1033 */       if (opposite == null || opposite.isTransient() || !opposite.isMany()) {
/* 1034 */         this.featuresToKinds.put(feature, INTEGER_IS_MANY_ADD);
/*      */       } else {
/* 1036 */         this.featuresToKinds.put(feature, INTEGER_IS_MANY_MOVE);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getJavaEncoding(String xmlEncoding) {
/* 1043 */     return xmlEncoding;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getXMLEncoding(String javaEncoding) {
/* 1048 */     return javaEncoding;
/*      */   }
/*      */ 
/*      */   
/*      */   public EPackage[] packages() {
/* 1053 */     Map<String, EPackage> map = new TreeMap<String, EPackage>();
/*      */ 
/*      */ 
/*      */     
/* 1057 */     for (EPackage ePackage : this.packages.keySet()) {
/*      */       
/* 1059 */       String prefix = getPrefix(ePackage);
/* 1060 */       if (prefix == null)
/*      */       {
/* 1062 */         prefix = "";
/*      */       }
/* 1064 */       EPackage conflict = map.put(prefix, ePackage);
/* 1065 */       if (conflict != null && conflict.eResource() != null)
/*      */       {
/* 1067 */         map.put(prefix, conflict);
/*      */       }
/*      */     } 
/* 1070 */     EPackage[] result = new EPackage[map.size()];
/* 1071 */     map.values().toArray((Object[])result);
/* 1072 */     return result; } public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
/*      */     EClassifier eClassifier;
/*      */     InternalEList<Object> list;
/*      */     EDataType eDataType;
/*      */     EFactory eFactory;
/* 1077 */     if (this.extendedMetaData != null) {
/*      */       
/* 1079 */       EStructuralFeature targetFeature = this.extendedMetaData.getAffiliation(object.eClass(), feature);
/* 1080 */       if (targetFeature != null && targetFeature != feature) {
/*      */         
/* 1082 */         EStructuralFeature group = this.extendedMetaData.getGroup(targetFeature);
/* 1083 */         if (group != null)
/*      */         {
/* 1085 */           targetFeature = group;
/*      */         }
/* 1087 */         if (targetFeature.getEType() == EcorePackage.Literals.EFEATURE_MAP_ENTRY) {
/*      */           
/* 1089 */           FeatureMap featureMap = (FeatureMap)object.eGet(targetFeature);
/* 1090 */           EClassifier eClassifier1 = feature.getEType();
/* 1091 */           if (eClassifier1 instanceof EDataType) {
/*      */             
/* 1093 */             EDataType eDataType1 = (EDataType)eClassifier1;
/* 1094 */             EFactory eFactory1 = eDataType1.getEPackage().getEFactoryInstance();
/* 1095 */             value = createFromString(eFactory1, eDataType1, (String)value);
/*      */           } 
/* 1097 */           featureMap.add(feature, value);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 1104 */         EClassifier eType = feature.getEType();
/* 1105 */         if (eType instanceof EDataType && targetFeature instanceof EReference) {
/*      */ 
/*      */ 
/*      */           
/* 1109 */           SimpleAnyType simpleAnyType = (SimpleAnyType)EcoreUtil.create(this.anySimpleType);
/* 1110 */           simpleAnyType.setInstanceType((EDataType)eType);
/* 1111 */           simpleAnyType.setRawValue((String)value);
/* 1112 */           value = simpleAnyType;
/*      */         } 
/* 1114 */         feature = targetFeature;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1119 */     int kind = getFeatureKind(feature);
/* 1120 */     switch (kind) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/* 1125 */         eClassifier = feature.getEType();
/* 1126 */         eDataType = (EDataType)eClassifier;
/* 1127 */         eFactory = eDataType.getEPackage().getEFactoryInstance();
/*      */         
/* 1129 */         if (kind == 2) {
/*      */           
/* 1131 */           InternalEList<Object> internalEList = (InternalEList<Object>)object.eGet(feature);
/* 1132 */           if (position == -2)
/*      */           {
/* 1134 */             for (StringTokenizer stringTokenizer = new StringTokenizer((String)value, " "); stringTokenizer.hasMoreTokens(); ) {
/*      */               
/* 1136 */               String token = stringTokenizer.nextToken();
/* 1137 */               internalEList.addUnique(createFromString(eFactory, eDataType, token));
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1142 */             if (internalEList.isEmpty())
/*      */             {
/* 1144 */               internalEList.clear();
/*      */             }
/*      */           }
/* 1147 */           else if (value == null)
/*      */           {
/* 1149 */             internalEList.addUnique(null);
/*      */           }
/*      */           else
/*      */           {
/* 1153 */             internalEList.addUnique(createFromString(eFactory, eDataType, (String)value));
/*      */           }
/*      */         
/* 1156 */         } else if (value == null) {
/*      */           
/* 1158 */           object.eSet(feature, null);
/*      */         }
/*      */         else {
/*      */           
/* 1162 */           object.eSet(feature, createFromString(eFactory, eDataType, (String)value));
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 4:
/* 1169 */         list = (InternalEList<Object>)object.eGet(feature);
/*      */         
/* 1171 */         if (position == -1) {
/*      */           
/* 1173 */           if (object == value)
/*      */           {
/* 1175 */             list.add(value);
/*      */           }
/*      */           else
/*      */           {
/* 1179 */             list.addUnique(value);
/*      */           }
/*      */         
/* 1182 */         } else if (position == -2) {
/*      */           
/* 1184 */           list.clear();
/*      */         }
/* 1186 */         else if (this.checkForDuplicates || object == value) {
/*      */           
/* 1188 */           int index = list.basicIndexOf(value);
/* 1189 */           if (index == -1)
/*      */           {
/* 1191 */             list.addUnique(position, value);
/*      */           }
/*      */           else
/*      */           {
/* 1195 */             list.move(position, index);
/*      */           }
/*      */         
/* 1198 */         } else if (kind == 3) {
/*      */           
/* 1200 */           list.addUnique(position, value);
/*      */         }
/*      */         else {
/*      */           
/* 1204 */           list.move(position, value);
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/* 1210 */     object.eSet(feature, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<XMIException> setManyReference(XMLHelper.ManyReference reference, String location) {
/* 1218 */     EStructuralFeature feature = reference.getFeature();
/* 1219 */     int kind = getFeatureKind(feature);
/* 1220 */     EObject object = reference.getObject();
/* 1221 */     InternalEList<Object> list = (InternalEList<Object>)object.eGet(feature);
/* 1222 */     BasicEList<IllegalValueException> basicEList = new BasicEList();
/* 1223 */     Object[] values = reference.getValues();
/* 1224 */     int[] positions = reference.getPositions();
/*      */     
/* 1226 */     if (kind == 3) {
/*      */       
/* 1228 */       for (int i = 0, l = values.length; i < l; i++) {
/*      */         
/* 1230 */         Object value = values[i];
/* 1231 */         if (value != null)
/*      */         {
/* 1233 */           int position = positions[i];
/*      */           
/*      */           try {
/* 1236 */             if (this.checkForDuplicates || object == value) {
/*      */               
/* 1238 */               int index = list.basicIndexOf(value);
/* 1239 */               if (index == -1)
/*      */               {
/* 1241 */                 list.addUnique(position, value);
/*      */               }
/*      */               else
/*      */               {
/* 1245 */                 list.move(position, index);
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/* 1250 */               list.addUnique(position, value);
/*      */             }
/*      */           
/* 1253 */           } catch (RuntimeException e) {
/*      */             
/* 1255 */             basicEList.add(new IllegalValueException(
/* 1256 */                   object, 
/* 1257 */                   feature, 
/* 1258 */                   value, 
/* 1259 */                   e, 
/* 1260 */                   location, 
/* 1261 */                   reference.getLineNumber(), 
/* 1262 */                   reference.getColumnNumber()));
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1270 */       for (int i = 0, l = values.length; i < l; i++) {
/*      */         
/* 1272 */         Object value = values[i];
/* 1273 */         if (value != null) {
/*      */           
/*      */           try {
/*      */             
/* 1277 */             int sourcePosition = list.basicIndexOf(value);
/* 1278 */             if (sourcePosition != -1)
/*      */             {
/* 1280 */               list.move(positions[i], sourcePosition);
/*      */             }
/*      */             else
/*      */             {
/* 1284 */               list.addUnique(positions[i], value);
/*      */             }
/*      */           
/* 1287 */           } catch (RuntimeException e) {
/*      */             
/* 1289 */             basicEList.add(new IllegalValueException(
/* 1290 */                   object, 
/* 1291 */                   feature, 
/* 1292 */                   value, 
/* 1293 */                   e, 
/* 1294 */                   location, 
/* 1295 */                   reference.getLineNumber(), 
/* 1296 */                   reference.getColumnNumber()));
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1303 */     if (basicEList.isEmpty())
/*      */     {
/* 1305 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1309 */     return (List)basicEList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCheckForDuplicates(boolean checkForDuplicates) {
/* 1315 */     this.checkForDuplicates = checkForDuplicates;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProcessDanglingHREF(String value) {
/* 1320 */     this.processDanglingHREF = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public DanglingHREFException getDanglingHREFException() {
/* 1325 */     return this.danglingHREFException;
/*      */   }
/*      */ 
/*      */   
/*      */   public URI resolve(URI relative, URI base) {
/* 1330 */     return (this.uriHandler == null) ? relative.resolve(base) : this.uriHandler.resolve(relative);
/*      */   }
/*      */ 
/*      */   
/*      */   public void pushContext() {
/* 1335 */     this.namespaceSupport.pushContext();
/*      */   }
/*      */ 
/*      */   
/*      */   public void popContext() {
/* 1340 */     this.namespaceSupport.popContext();
/*      */   }
/*      */ 
/*      */   
/*      */   public void popContext(Map<String, EFactory> prefixesToFactories) {
/* 1345 */     this.namespaceSupport.popContext(prefixesToFactories);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addPrefix(String prefix, String uri) {
/* 1350 */     if (!"xml".equals(prefix) && !"xmlns".equals(prefix)) {
/*      */       
/* 1352 */       uri = (uri.length() == 0) ? null : uri;
/* 1353 */       this.namespaceSupport.declarePrefix(prefix, uri);
/* 1354 */       this.allPrefixToURI.add(prefix);
/* 1355 */       this.allPrefixToURI.add(uri);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPrefix(String namespaceURI) {
/* 1361 */     return this.namespaceSupport.getPrefix(namespaceURI);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, String> getAnyContentPrefixToURIMapping() {
/* 1366 */     this.anyPrefixesToURIs.clear();
/* 1367 */     int count = this.namespaceSupport.getDeclaredPrefixCount();
/* 1368 */     int size = this.allPrefixToURI.size();
/* 1369 */     while (count-- > 0) {
/*      */       
/* 1371 */       String uri = this.allPrefixToURI.remove(--size);
/* 1372 */       String prefix = this.allPrefixToURI.remove(--size);
/* 1373 */       this.anyPrefixesToURIs.put(prefix, uri);
/*      */     } 
/* 1375 */     return this.anyPrefixesToURIs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getURI(String prefix) {
/* 1381 */     return 
/* 1382 */       "xml".equals(prefix) ? 
/* 1383 */       "http://www.w3.org/XML/1998/namespace" : (
/* 1384 */       "xmlns".equals(prefix) ? 
/* 1385 */       "http://www.w3.org/2000/xmlns/" : 
/* 1386 */       this.namespaceSupport.getURI(prefix));
/*      */   }
/*      */ 
/*      */   
/*      */   public EMap<String, String> getPrefixToNamespaceMap() {
/* 1391 */     return this.prefixesToURIs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void recordPrefixToURIMapping() {
/* 1396 */     for (int i = 0, size = this.allPrefixToURI.size(); i < size; ) {
/*      */       
/* 1398 */       String prefix = this.allPrefixToURI.get(i++);
/* 1399 */       String uri = this.allPrefixToURI.get(i++);
/* 1400 */       String originalURI = (String)this.prefixesToURIs.get(prefix);
/* 1401 */       if (uri == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1408 */         this.seenEmptyStringMapping = true;
/* 1409 */         if (originalURI != null) {
/*      */ 
/*      */           
/* 1412 */           this.prefixesToURIs.removeKey(prefix);
/* 1413 */           addNSDeclaration(prefix, originalURI);
/*      */         } 
/*      */         continue;
/*      */       } 
/* 1417 */       if (this.seenEmptyStringMapping && prefix.length() == 0) {
/*      */ 
/*      */         
/* 1420 */         addNSDeclaration(prefix, uri); continue;
/*      */       } 
/* 1422 */       if (originalURI != null) {
/*      */         
/* 1424 */         if (!uri.equals(originalURI))
/*      */         {
/*      */           
/* 1427 */           addNSDeclaration(prefix, uri);
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 1433 */       this.prefixesToURIs.put(prefix, uri);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrefixToNamespaceMap(EMap<String, String> prefixToNamespaceMap) {
/* 1440 */     for (Map.Entry<String, String> entry : prefixToNamespaceMap) {
/*      */       
/* 1442 */       String prefix = entry.getKey();
/* 1443 */       String namespace = entry.getValue();
/* 1444 */       EPackage ePackage = null;
/* 1445 */       if (this.extendedMetaData == null) {
/*      */         
/* 1447 */         ePackage = this.packageRegistry.getEPackage(namespace);
/*      */       }
/*      */       else {
/*      */         
/* 1451 */         ePackage = this.extendedMetaData.getPackage(namespace);
/* 1452 */         if (ePackage == null)
/*      */         {
/* 1454 */           if ("http://www.w3.org/2001/XMLSchema".equals(namespace)) {
/*      */             
/* 1456 */             ePackage = this.xmlSchemaTypePackage;
/*      */           }
/*      */           else {
/*      */             
/* 1460 */             ePackage = this.extendedMetaData.demandPackage(namespace);
/*      */           } 
/*      */         }
/*      */       } 
/* 1464 */       if (ePackage != null && !this.packages.containsKey(ePackage))
/*      */       {
/* 1466 */         this.packages.put(ePackage, prefix);
/*      */       }
/* 1468 */       this.prefixesToURIs.put(prefix, namespace);
/*      */     } 
/*      */   }
/*      */   protected static class NamespaceSupport { protected String[] namespace; protected int namespaceSize;
/*      */     protected int[] context;
/*      */     protected int currentContext;
/*      */     protected String[] prefixes;
/*      */     
/*      */     protected NamespaceSupport() {
/* 1477 */       this.namespace = new String[32];
/*      */       
/* 1479 */       this.namespaceSize = 0;
/*      */       
/* 1481 */       this.context = new int[8];
/*      */       
/* 1483 */       this.currentContext = -1;
/*      */       
/* 1485 */       this.prefixes = new String[16];
/*      */     }
/*      */ 
/*      */     
/*      */     public void pushContext() {
/* 1490 */       if (this.currentContext + 1 == this.context.length) {
/*      */         
/* 1492 */         int[] contextarray = new int[this.context.length * 2];
/* 1493 */         System.arraycopy(this.context, 0, contextarray, 0, this.context.length);
/* 1494 */         this.context = contextarray;
/*      */       } 
/*      */ 
/*      */       
/* 1498 */       this.context[++this.currentContext] = this.namespaceSize;
/*      */     }
/*      */ 
/*      */     
/*      */     public void popContext() {
/* 1503 */       this.namespaceSize = this.context[this.currentContext--];
/*      */     }
/*      */ 
/*      */     
/*      */     public void popContext(Map<String, EFactory> prefixesToFactories) {
/* 1508 */       int oldNamespaceSize = this.namespaceSize;
/* 1509 */       for (int i = this.namespaceSize = this.context[this.currentContext--]; i < oldNamespaceSize; i += 2)
/*      */       {
/* 1511 */         prefixesToFactories.remove(this.namespace[i]);
/*      */       }
/*      */     }
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
/*      */     public boolean declarePrefix(String prefix, String uri) {
/* 1525 */       for (int i = this.namespaceSize; i > this.context[this.currentContext]; i -= 2) {
/*      */         
/* 1527 */         if (this.namespace[i - 2].equals(prefix)) {
/*      */           
/* 1529 */           this.namespace[i - 1] = uri;
/* 1530 */           return true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1535 */       if (this.namespaceSize == this.namespace.length) {
/*      */         
/* 1537 */         String[] namespacearray = new String[this.namespaceSize * 2];
/* 1538 */         System.arraycopy(this.namespace, 0, namespacearray, 0, this.namespaceSize);
/* 1539 */         this.namespace = namespacearray;
/*      */       } 
/*      */ 
/*      */       
/* 1543 */       this.namespace[this.namespaceSize++] = prefix;
/* 1544 */       this.namespace[this.namespaceSize++] = uri;
/* 1545 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getURI(String prefix) {
/* 1551 */       for (int i = this.namespaceSize; i > 0; i -= 2) {
/*      */         
/* 1553 */         if (this.namespace[i - 2].equals(prefix))
/*      */         {
/* 1555 */           return this.namespace[i - 1];
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1560 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPrefix(String uri) {
/* 1566 */       for (int i = this.namespaceSize; i > 0; i -= 2) {
/*      */         
/* 1568 */         String knownURI = this.namespace[i - 1];
/* 1569 */         if ((knownURI != null) ? knownURI.equals(uri) : (uri == knownURI)) {
/*      */           
/* 1571 */           knownURI = getURI(this.namespace[i - 2]);
/* 1572 */           if ((knownURI != null) ? knownURI.equals(uri) : (uri == knownURI)) {
/* 1573 */             return this.namespace[i - 2];
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1578 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getDeclaredPrefixCount() {
/* 1583 */       return (this.namespaceSize - this.context[this.currentContext]) / 2;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getDeclaredPrefixAt(int index) {
/* 1588 */       return this.namespace[this.context[this.currentContext] + index * 2];
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAnySimpleType(EClass type) {
/* 1595 */     this.anySimpleType = type;
/*      */   }
/*      */ 
/*      */   
/*      */   public String convertToString(EFactory factory, EDataType dataType, Object value) {
/* 1600 */     if (this.extendedMetaData != null) {
/*      */       
/* 1602 */       if (value instanceof List) {
/*      */         
/* 1604 */         List<?> list = (List)value;
/* 1605 */         for (Object item : list)
/*      */         {
/* 1607 */           updateQNamePrefix(factory, dataType, item, true);
/*      */         }
/* 1609 */         return factory.convertToString(dataType, value);
/*      */       } 
/*      */ 
/*      */       
/* 1613 */       return updateQNamePrefix(factory, dataType, value, false);
/*      */     } 
/*      */     
/* 1616 */     return factory.convertToString(dataType, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object createFromString(EFactory eFactory, EDataType eDataType, String value) {
/* 1621 */     Object obj = eFactory.createFromString(eDataType, value);
/* 1622 */     if (this.extendedMetaData != null)
/*      */     {
/* 1624 */       if (obj instanceof List) {
/*      */ 
/*      */         
/* 1627 */         List<Object> list = (List<Object>)obj;
/* 1628 */         for (int i = 0; i < list.size(); i++)
/*      */         {
/* 1630 */           Object item = list.get(i);
/* 1631 */           Object replacement = updateQNameURI(item);
/* 1632 */           if (replacement != item)
/*      */           {
/* 1634 */             list.set(i, replacement);
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1640 */         obj = updateQNameURI(obj);
/*      */       } 
/*      */     }
/* 1643 */     return obj;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object updateQNameURI(Object value) {
/* 1648 */     if (value instanceof QName) {
/*      */       
/* 1650 */       QName qName = (QName)value;
/* 1651 */       if (qName.getNamespaceURI().length() != 0)
/*      */       {
/* 1653 */         throw new IllegalArgumentException("Curly brace notation is not a syntactically valid serialized representation for the QName '" + qName.toString() + "'");
/*      */       }
/* 1655 */       String prefix = qName.getPrefix();
/* 1656 */       String namespace = getURI(prefix);
/* 1657 */       QName qName1 = new QName(namespace, qName.getLocalPart(), prefix);
/* 1658 */       if (qName1.getPrefix().length() > 0 && namespace == null)
/*      */       {
/* 1660 */         throw new IllegalArgumentException("The prefix '" + prefix + "' is not declared for the QName '" + qName1.toString() + "'");
/*      */       }
/* 1662 */       if (namespace == null) {
/*      */         
/* 1664 */         this.seenEmptyStringMapping = true;
/* 1665 */         String uri = (String)this.prefixesToURIs.get("");
/* 1666 */         if (uri != null) {
/*      */           
/* 1668 */           this.prefixesToURIs.put("", namespace);
/* 1669 */           addNSDeclaration("", uri);
/*      */         } 
/*      */       } 
/* 1672 */       return qName1;
/*      */     } 
/*      */ 
/*      */     
/* 1676 */     return value;
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
/*      */   protected String updateQNamePrefix(EFactory factory, EDataType dataType, Object value, boolean list) {
/* 1690 */     if (value instanceof QName) {
/*      */       
/* 1692 */       QName qName = (QName)value;
/* 1693 */       String namespace = qName.getNamespaceURI();
/* 1694 */       String localPart = qName.getLocalPart();
/* 1695 */       if (namespace.length() == 0) {
/*      */         
/* 1697 */         if (value instanceof QName) {
/*      */           
/* 1699 */           ((QName)qName).setPrefix("");
/*      */         }
/* 1701 */         else if (qName.getPrefix().length() != 0) {
/*      */           
/* 1703 */           throw new IllegalStateException("The null namespace cannot be bound to a non-null prefix '" + qName + "'");
/*      */         } 
/* 1705 */         return localPart;
/*      */       } 
/* 1707 */       String prefix = qName.getPrefix();
/* 1708 */       EPackage ePackage = this.extendedMetaData.getPackage(namespace);
/* 1709 */       if (ePackage == null) {
/*      */         
/* 1711 */         int size = this.extendedMetaData.demandedPackages().size();
/* 1712 */         ePackage = this.extendedMetaData.demandPackage(namespace);
/* 1713 */         if (prefix.length() != 0 && this.extendedMetaData.demandedPackages().size() > size)
/*      */         {
/* 1715 */           ePackage.setNsPrefix(prefix);
/*      */         }
/*      */       } 
/* 1718 */       if (!namespace.equals(getNamespaceURI(prefix))) {
/*      */         
/* 1720 */         prefix = getPrefix(ePackage, true);
/* 1721 */         if (value instanceof QName)
/*      */         {
/* 1723 */           ((QName)qName).setPrefix(prefix);
/*      */         }
/*      */       } 
/* 1726 */       return list ? null : ((prefix.length() == 0) ? localPart : (String.valueOf(prefix) + ':' + localPart));
/*      */     } 
/*      */     
/* 1729 */     return list ? null : factory.convertToString(dataType, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addNSDeclaration(String prefix, String uri) {
/* 1734 */     if (uri != null) {
/*      */       
/* 1736 */       List<String> existingPrefixes = this.urisToPrefixes.get(uri);
/* 1737 */       if (existingPrefixes == null) {
/*      */         
/* 1739 */         int lowerBound = 0;
/* 1740 */         int index = 1;
/*      */         String newPrefix;
/* 1742 */         while (this.prefixesToURIs.containsKey(newPrefix = String.valueOf(prefix) + "_" + index)) {
/*      */           
/* 1744 */           lowerBound = index;
/* 1745 */           index <<= 1;
/*      */         } 
/* 1747 */         if (lowerBound != 0) {
/*      */           
/* 1749 */           int upperBound = index;
/* 1750 */           while (lowerBound + 1 < upperBound) {
/*      */             
/* 1752 */             index = lowerBound + upperBound >> 1;
/* 1753 */             if (this.prefixesToURIs.containsKey(String.valueOf(prefix) + "_" + index)) {
/*      */               
/* 1755 */               lowerBound = index;
/*      */               
/*      */               continue;
/*      */             } 
/* 1759 */             upperBound = index;
/*      */           } 
/*      */           
/* 1762 */           newPrefix = String.valueOf(prefix) + "_" + (lowerBound + 1);
/*      */         } 
/* 1764 */         this.prefixesToURIs.put(newPrefix, uri);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMustHavePrefix(boolean mustHavePrefix) {
/* 1771 */     this.mustHavePrefix = mustHavePrefix;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLHelperImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */