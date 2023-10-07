/*      */ package org.eclipse.emf.ecore.xmi.impl;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.URIConverter;
/*      */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ import org.eclipse.emf.ecore.xmi.DOMHandler;
/*      */ import org.eclipse.emf.ecore.xmi.DanglingHREFException;
/*      */ import org.eclipse.emf.ecore.xmi.NameInfo;
/*      */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*      */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*      */ import org.eclipse.emf.ecore.xmi.XMLSave;
/*      */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.DataValue;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
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
/*      */ public class XMLSaveImpl
/*      */   implements XMLSave
/*      */ {
/*      */   private static final int MAX_UTF_MAPPABLE_CODEPOINT = 1114111;
/*      */   private static final int MAX_LATIN1_MAPPABLE_CODEPOINT = 255;
/*      */   private static final int MAX_ASCII_MAPPABLE_CODEPOINT = 127;
/*      */   protected static final int INDEX_LOOKUP = 0;
/*   85 */   final StringBuffer buffer = new StringBuffer();
/*      */   
/*      */   protected XMLHelper helper;
/*      */   protected XMLString doc;
/*      */   protected boolean declareXSI;
/*      */   protected boolean useEncodedAttributeStyle;
/*      */   protected boolean declareXML;
/*      */   protected boolean saveTypeInfo;
/*      */   protected XMLSave.XMLTypeInfo xmlTypeInfo;
/*      */   protected boolean keepDefaults;
/*      */   protected Escape escape;
/*      */   protected Escape escapeURI;
/*      */   protected XMLResource.ResourceEntityHandler resourceEntityHandler;
/*      */   protected Lookup featureTable;
/*      */   protected String encoding;
/*      */   protected String xmlVersion;
/*  101 */   protected String idAttributeName = "id";
/*  102 */   protected String idAttributeNS = null;
/*      */   protected String processDanglingHREF;
/*      */   protected boolean declareSchemaLocation;
/*      */   protected boolean declareSchemaLocationImplementation;
/*      */   protected XMLResource.XMLMap map;
/*      */   protected ExtendedMetaData extendedMetaData;
/*      */   protected EClass anySimpleType;
/*      */   protected EClass anyType;
/*      */   protected Map<EObject, AnyType> eObjectToExtensionMap;
/*  111 */   protected EPackage xmlSchemaTypePackage = (EPackage)XMLTypePackage.eINSTANCE;
/*  112 */   protected int flushThreshold = Integer.MAX_VALUE;
/*      */   
/*      */   protected boolean toDOM;
/*      */   
/*      */   protected DOMHandler handler;
/*      */   
/*      */   protected Document document;
/*      */   
/*      */   protected Node currentNode;
/*      */   
/*      */   protected NameInfo nameInfo;
/*      */   
/*      */   protected boolean useCache;
/*      */   
/*      */   protected EObject root;
/*      */   protected XMLResource xmlResource;
/*      */   protected List<? extends EObject> roots;
/*      */   protected XMLResource.ElementHandler elementHandler;
/*      */   protected static final int SKIP = 0;
/*      */   protected static final int SAME_DOC = 1;
/*      */   protected static final int CROSS_DOC = 2;
/*      */   protected static final int TRANSIENT = 0;
/*      */   protected static final int DATATYPE_SINGLE = 1;
/*      */   protected static final int DATATYPE_ELEMENT_SINGLE = 2;
/*      */   protected static final int DATATYPE_CONTENT_SINGLE = 3;
/*      */   protected static final int DATATYPE_SINGLE_NILLABLE = 4;
/*      */   protected static final int DATATYPE_MANY = 5;
/*      */   protected static final int OBJECT_CONTAIN_SINGLE = 6;
/*      */   protected static final int OBJECT_CONTAIN_MANY = 7;
/*      */   protected static final int OBJECT_HREF_SINGLE = 8;
/*      */   protected static final int OBJECT_HREF_MANY = 9;
/*      */   protected static final int OBJECT_CONTAIN_SINGLE_UNSETTABLE = 10;
/*      */   protected static final int OBJECT_CONTAIN_MANY_UNSETTABLE = 11;
/*      */   protected static final int OBJECT_HREF_SINGLE_UNSETTABLE = 12;
/*      */   protected static final int OBJECT_HREF_MANY_UNSETTABLE = 13;
/*      */   protected static final int OBJECT_ELEMENT_SINGLE = 14;
/*      */   protected static final int OBJECT_ELEMENT_SINGLE_UNSETTABLE = 15;
/*      */   protected static final int OBJECT_ELEMENT_MANY = 16;
/*      */   protected static final int OBJECT_ELEMENT_IDREF_SINGLE = 17;
/*      */   protected static final int OBJECT_ELEMENT_IDREF_SINGLE_UNSETTABLE = 18;
/*      */   protected static final int OBJECT_ELEMENT_IDREF_MANY = 19;
/*      */   protected static final int ATTRIBUTE_FEATURE_MAP = 20;
/*      */   protected static final int ELEMENT_FEATURE_MAP = 21;
/*      */   protected static final int OBJECT_ATTRIBUTE_SINGLE = 22;
/*      */   protected static final int OBJECT_ATTRIBUTE_MANY = 23;
/*      */   protected static final int OBJECT_ATTRIBUTE_IDREF_SINGLE = 24;
/*      */   protected static final int OBJECT_ATTRIBUTE_IDREF_MANY = 25;
/*      */   protected static final int DATATYPE_ATTRIBUTE_MANY = 26;
/*      */   protected static final String XML_VERSION = "1.0";
/*      */   protected static final String XSI_NIL = "xsi:nil";
/*      */   protected static final String XSI_TYPE_NS = "xsi:type";
/*      */   protected static final String XSI_XMLNS = "xmlns:xsi";
/*      */   protected static final String XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
/*      */   protected static final String XSI_NO_NAMESPACE_SCHEMA_LOCATION = "xsi:noNamespaceSchemaLocation";
/*      */   protected static final int EMPTY_ELEMENT = 1;
/*      */   protected static final int CONTENT_ELEMENT = 2;
/*      */   
/*      */   public XMLSaveImpl(XMLHelper helper) {
/*  170 */     this.helper = helper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLSaveImpl(Map<?, ?> options, XMLHelper helper, String encoding) {
/*  181 */     this(options, helper, encoding, "1.0");
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLSaveImpl(Map<?, ?> options, XMLHelper helper, String encoding, String xmlVersion) {
/*  186 */     this.helper = helper;
/*  187 */     init(helper.getResource(), options);
/*  188 */     this.encoding = encoding;
/*  189 */     this.xmlVersion = xmlVersion;
/*      */   }
/*      */   
/*      */   public Document save(XMLResource resource, Document doc, Map<?, ?> options, DOMHandler handler) {
/*      */     EList eList;
/*  194 */     this.toDOM = true;
/*  195 */     this.document = doc;
/*  196 */     this.handler = handler;
/*  197 */     this.xmlResource = resource;
/*      */     
/*  199 */     init(resource, options);
/*      */     
/*  201 */     List<? extends EObject> contents = this.roots = (List<? extends EObject>)options.get("ROOT_OBJECTS");
/*  202 */     if (contents == null)
/*      */     {
/*  204 */       eList = resource.getContents();
/*      */     }
/*  206 */     traverse((List<? extends EObject>)eList);
/*      */ 
/*      */     
/*      */     try {
/*  210 */       endSave((List<? extends EObject>)eList);
/*      */     }
/*  212 */     catch (Exception e) {
/*      */       
/*  214 */       e.printStackTrace();
/*      */     } 
/*  216 */     this.xmlResource = null;
/*  217 */     return this.document;
/*      */   }
/*      */   
/*      */   public void save(XMLResource resource, Writer writer, Map<?, ?> options) throws IOException {
/*      */     EList eList;
/*  222 */     this.xmlResource = resource;
/*  223 */     init(resource, options);
/*      */     
/*  225 */     List<? extends EObject> contents = this.roots = (List<? extends EObject>)options.get("ROOT_OBJECTS");
/*  226 */     if (contents == null)
/*      */     {
/*  228 */       eList = resource.getContents();
/*      */     }
/*  230 */     traverse((List<? extends EObject>)eList);
/*      */     
/*  232 */     write(writer);
/*  233 */     writer.flush();
/*      */     
/*  235 */     endSave((List<? extends EObject>)eList);
/*  236 */     this.xmlResource = null;
/*      */   }
/*      */   
/*      */   public void save(XMLResource resource, OutputStream outputStream, Map<?, ?> options) throws IOException {
/*      */     EList eList;
/*  241 */     if (outputStream instanceof URIConverter.Writeable) {
/*      */       
/*  243 */       URIConverter.Writeable writeable = (URIConverter.Writeable)outputStream;
/*  244 */       resource.setEncoding(writeable.getEncoding());
/*  245 */       save(resource, writeable.asWriter(), options);
/*      */       return;
/*      */     } 
/*  248 */     this.xmlResource = resource;
/*  249 */     init(resource, options);
/*      */     
/*  251 */     List<? extends EObject> contents = this.roots = (List<? extends EObject>)options.get("ROOT_OBJECTS");
/*  252 */     if (contents == null)
/*      */     {
/*  254 */       eList = resource.getContents();
/*      */     }
/*  256 */     traverse((List<? extends EObject>)eList);
/*      */     
/*  258 */     if ("US-ASCII".equals(this.encoding) || "ASCII".equals(this.encoding)) {
/*      */       
/*  260 */       writeAscii(outputStream);
/*  261 */       outputStream.flush();
/*      */     }
/*      */     else {
/*      */       
/*  265 */       OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, this.helper.getJavaEncoding(this.encoding));
/*  266 */       write(outputStreamWriter);
/*  267 */       outputStreamWriter.flush();
/*      */     } 
/*      */     
/*  270 */     endSave((List<? extends EObject>)eList);
/*  271 */     this.xmlResource = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void endSave(List<? extends EObject> contents) throws IOException {
/*  276 */     if (this.extendedMetaData != null && contents.size() >= 1) {
/*      */       
/*  278 */       EObject root = contents.get(0);
/*  279 */       EClass eClass = root.eClass();
/*      */       
/*  281 */       EReference xmlnsPrefixMapFeature = this.extendedMetaData.getXMLNSPrefixMapFeature(eClass);
/*  282 */       if (xmlnsPrefixMapFeature != null) {
/*      */         
/*  284 */         EMap<String, String> xmlnsPrefixMap = (EMap<String, String>)root.eGet((EStructuralFeature)xmlnsPrefixMapFeature);
/*  285 */         for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)this.helper.getPrefixToNamespaceMap()) {
/*      */           
/*  287 */           String key = entry.getKey();
/*  288 */           String value = entry.getValue();
/*  289 */           String currentValue = (String)xmlnsPrefixMap.get(key);
/*  290 */           if ((currentValue == null) ? (value != null) : !currentValue.equals(value))
/*      */           {
/*  292 */             xmlnsPrefixMap.put(key, value);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  298 */     if (this.processDanglingHREF == null || 
/*  299 */       "THROW".equals(this.processDanglingHREF)) {
/*      */       
/*  301 */       DanglingHREFException exception = this.helper.getDanglingHREFException();
/*      */       
/*  303 */       if (exception != null) {
/*      */         
/*  305 */         this.helper = null;
/*  306 */         throw new Resource.IOWrappedException(exception);
/*      */       } 
/*      */     } 
/*      */     
/*  310 */     if (this.useCache) {
/*      */       
/*  312 */       if (this.doc != null)
/*      */       {
/*  314 */         ConfigurationCache.INSTANCE.releasePrinter(this.doc);
/*      */       }
/*  316 */       if (this.escape != null)
/*      */       {
/*  318 */         ConfigurationCache.INSTANCE.releaseEscape(this.escape);
/*      */       }
/*      */     } 
/*  321 */     this.featureTable = null;
/*  322 */     this.doc = null;
/*  323 */     this.helper = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void init(XMLResource resource, Map<?, ?> options) {
/*  328 */     this.useCache = Boolean.TRUE.equals(options.get("CONFIGURATION_CACHE"));
/*      */     
/*  330 */     this.nameInfo = new NameInfoImpl();
/*  331 */     this.declareXSI = false;
/*  332 */     this.keepDefaults = Boolean.TRUE.equals(options.get("KEEP_DEFAULT_CONTENT"));
/*  333 */     this.useEncodedAttributeStyle = Boolean.TRUE.equals(options.get("USE_ENCODED_ATTRIBUTE_STYLE"));
/*  334 */     this.declareSchemaLocationImplementation = Boolean.TRUE.equals(options.get("SCHEMA_LOCATION_IMPLEMENTATION"));
/*  335 */     this.declareSchemaLocation = !(!this.declareSchemaLocationImplementation && !Boolean.TRUE.equals(options.get("SCHEMA_LOCATION")));
/*      */     
/*  337 */     Object saveTypeInfoOption = options.get("SAVE_TYPE_INFORMATION");
/*  338 */     if (saveTypeInfoOption instanceof Boolean) {
/*      */       
/*  340 */       this.saveTypeInfo = saveTypeInfoOption.equals(Boolean.TRUE);
/*  341 */       if (this.saveTypeInfo)
/*      */       {
/*  343 */         this.xmlTypeInfo = 
/*  344 */           new XMLSave.XMLTypeInfo()
/*      */           {
/*      */             public boolean shouldSaveType(EClass objectType, EClassifier featureType, EStructuralFeature feature)
/*      */             {
/*  348 */               return (objectType != XMLSaveImpl.this.anyType);
/*      */             }
/*      */ 
/*      */             
/*      */             public boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature) {
/*  353 */               return true;
/*      */             }
/*      */           };
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  360 */       this.saveTypeInfo = (saveTypeInfoOption != null);
/*  361 */       if (this.saveTypeInfo)
/*      */       {
/*  363 */         this.xmlTypeInfo = (XMLSave.XMLTypeInfo)saveTypeInfoOption;
/*      */       }
/*      */     } 
/*      */     
/*  367 */     this.anyType = (EClass)options.get("ANY_TYPE");
/*  368 */     this.anySimpleType = (EClass)options.get("ANY_SIMPLE_TYPE");
/*  369 */     if (this.anyType == null) {
/*      */       
/*  371 */       this.anyType = XMLTypePackage.eINSTANCE.getAnyType();
/*  372 */       this.anySimpleType = XMLTypePackage.eINSTANCE.getSimpleAnyType();
/*      */     } 
/*      */     
/*  375 */     Object extendedMetaDataOption = options.get("EXTENDED_META_DATA");
/*  376 */     if (extendedMetaDataOption instanceof Boolean) {
/*      */       
/*  378 */       if (extendedMetaDataOption.equals(Boolean.TRUE))
/*      */       {
/*  380 */         this.extendedMetaData = (
/*  381 */           resource == null || resource.getResourceSet() == null) ? 
/*  382 */           ExtendedMetaData.INSTANCE : 
/*  383 */           (ExtendedMetaData)new BasicExtendedMetaData(resource.getResourceSet().getPackageRegistry());
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  388 */       this.extendedMetaData = (ExtendedMetaData)options.get("EXTENDED_META_DATA");
/*      */     } 
/*      */ 
/*      */     
/*  392 */     if (!this.toDOM) {
/*      */       
/*  394 */       this.declareXML = !Boolean.FALSE.equals(options.get("DECLARE_XML"));
/*      */       
/*  396 */       if (options.get("FLUSH_THRESHOLD") instanceof Integer)
/*      */       {
/*  398 */         this.flushThreshold = ((Integer)options.get("FLUSH_THRESHOLD")).intValue();
/*      */       }
/*      */       
/*  401 */       String temporaryFileName = null;
/*  402 */       if (Boolean.TRUE.equals(options.get("USE_FILE_BUFFER"))) {
/*      */         
/*      */         try {
/*      */           
/*  406 */           temporaryFileName = File.createTempFile("XMLSave", null).getPath();
/*      */         }
/*  408 */         catch (IOException iOException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  414 */       Integer lineWidth = (Integer)options.get("LINE_WIDTH");
/*  415 */       int effectiveLineWidth = (lineWidth == null) ? Integer.MAX_VALUE : lineWidth.intValue();
/*  416 */       String publicId = null, systemId = null;
/*  417 */       if (resource != null && Boolean.TRUE.equals(options.get("SAVE_DOCTYPE"))) {
/*      */         
/*  419 */         publicId = resource.getPublicId();
/*  420 */         systemId = resource.getSystemId();
/*      */       } 
/*  422 */       if (this.useCache) {
/*      */         
/*  424 */         this.doc = ConfigurationCache.INSTANCE.getPrinter();
/*  425 */         this.doc.reset(publicId, systemId, effectiveLineWidth, temporaryFileName);
/*  426 */         this.escape = Boolean.TRUE.equals(options.get("SKIP_ESCAPE")) ? null : ConfigurationCache.INSTANCE.getEscape();
/*      */       }
/*      */       else {
/*      */         
/*  430 */         this.doc = new XMLString(effectiveLineWidth, publicId, systemId, temporaryFileName);
/*  431 */         this.escape = Boolean.TRUE.equals(options.get("SKIP_ESCAPE")) ? null : new Escape();
/*      */       } 
/*      */       
/*  434 */       if (Boolean.FALSE.equals(options.get("FORMATTED")))
/*      */       {
/*  436 */         this.doc.setUnformatted(true);
/*      */       }
/*      */ 
/*      */       
/*  440 */       this.escapeURI = Boolean.FALSE.equals(options.get("SKIP_ESCAPE_URI")) ? this.escape : null;
/*      */       
/*  442 */       if (options.containsKey("ENCODING")) {
/*      */         
/*  444 */         this.encoding = (String)options.get("ENCODING");
/*      */       }
/*  446 */       else if (resource != null) {
/*      */         
/*  448 */         this.encoding = resource.getEncoding();
/*      */       } 
/*      */       
/*  451 */       if (options.containsKey("XML_VERSION")) {
/*      */         
/*  453 */         this.xmlVersion = (String)options.get("XML_VERSION");
/*      */       }
/*  455 */       else if (resource != null) {
/*      */         
/*  457 */         this.xmlVersion = resource.getXMLVersion();
/*      */       } 
/*      */       
/*  460 */       if (this.escape != null) {
/*      */         
/*  462 */         int maxSafeChar = 1114111;
/*  463 */         if (this.encoding != null)
/*      */         {
/*  465 */           if (this.encoding.equalsIgnoreCase("ASCII") || this.encoding.equalsIgnoreCase("US-ASCII")) {
/*      */             
/*  467 */             maxSafeChar = 127;
/*      */           }
/*  469 */           else if (this.encoding.equalsIgnoreCase("ISO-8859-1")) {
/*      */             
/*  471 */             maxSafeChar = 255;
/*      */           } 
/*      */         }
/*      */         
/*  475 */         this.escape.setMappingLimit(maxSafeChar);
/*  476 */         if (!"1.0".equals(this.xmlVersion))
/*      */         {
/*  478 */           this.escape.setAllowControlCharacters(true);
/*      */         }
/*      */         
/*  481 */         this.escape.setUseCDATA(Boolean.TRUE.equals(options.get("ESCAPE_USING_CDATA")));
/*      */       } 
/*      */       
/*  484 */       this.resourceEntityHandler = (XMLResource.ResourceEntityHandler)options.get("RESOURCE_ENTITY_HANDLER");
/*  485 */       if (this.resourceEntityHandler instanceof XMLResource.URIHandler && !options.containsKey("URI_HANDLER"))
/*      */       {
/*  487 */         Map<Object, Object> newOptions = new LinkedHashMap<Object, Object>(options);
/*  488 */         newOptions.put("URI_HANDLER", this.resourceEntityHandler);
/*  489 */         options = newOptions;
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  495 */     else if (this.handler instanceof DefaultDOMHandlerImpl) {
/*      */       
/*  497 */       ((DefaultDOMHandlerImpl)this.handler).setExtendedMetaData(this.extendedMetaData);
/*      */     } 
/*      */     
/*  500 */     this.processDanglingHREF = (String)options.get("PROCESS_DANGLING_HREF");
/*  501 */     this.helper.setProcessDanglingHREF(this.processDanglingHREF);
/*      */     
/*  503 */     this.map = (XMLResource.XMLMap)options.get("XML_MAP");
/*  504 */     if (this.map != null) {
/*      */       
/*  506 */       this.helper.setXMLMap(this.map);
/*      */       
/*  508 */       if (this.map.getIDAttributeName() != null)
/*      */       {
/*  510 */         this.idAttributeName = this.map.getIDAttributeName();
/*      */       }
/*      */     } 
/*      */     
/*  514 */     if (resource != null) {
/*      */       
/*  516 */       this.eObjectToExtensionMap = resource.getEObjectToExtensionMap();
/*  517 */       if (this.eObjectToExtensionMap.isEmpty()) {
/*      */         
/*  519 */         this.eObjectToExtensionMap = null;
/*      */       }
/*  521 */       else if (this.extendedMetaData == null) {
/*      */         
/*  523 */         this.extendedMetaData = 
/*  524 */           (resource.getResourceSet() == null) ? 
/*  525 */           ExtendedMetaData.INSTANCE : 
/*  526 */           (ExtendedMetaData)new BasicExtendedMetaData(resource.getResourceSet().getPackageRegistry());
/*      */       } 
/*      */     } 
/*      */     
/*  530 */     if (this.extendedMetaData != null) {
/*      */       
/*  532 */       this.helper.setExtendedMetaData(this.extendedMetaData);
/*  533 */       if (resource != null && resource.getContents().size() >= 1) {
/*      */         
/*  535 */         EObject root = (EObject)resource.getContents().get(0);
/*  536 */         EClass eClass = root.eClass();
/*      */         
/*  538 */         EReference xmlnsPrefixMapFeature = this.extendedMetaData.getXMLNSPrefixMapFeature(eClass);
/*  539 */         if (xmlnsPrefixMapFeature != null) {
/*      */           
/*  541 */           EMap<String, String> xmlnsPrefixMap = (EMap<String, String>)root.eGet((EStructuralFeature)xmlnsPrefixMapFeature);
/*  542 */           this.helper.setPrefixToNamespaceMap(xmlnsPrefixMap);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  547 */     this.elementHandler = (XMLResource.ElementHandler)options.get("ELEMENT_HANDLER");
/*      */     
/*  549 */     List<Object> lookup = (List<Object>)options.get("USE_CACHED_LOOKUP_TABLE");
/*  550 */     if (lookup != null) {
/*      */ 
/*      */       
/*  553 */       if (lookup.isEmpty())
/*      */       {
/*  555 */         this.featureTable = new Lookup(this.map, this.extendedMetaData, this.elementHandler);
/*  556 */         lookup.add(this.featureTable);
/*      */       }
/*      */       else
/*      */       {
/*  560 */         this.featureTable = (Lookup)lookup.get(0);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  566 */       this.featureTable = new Lookup(this.map, this.extendedMetaData, this.elementHandler);
/*      */     } 
/*      */     
/*  569 */     this.helper.setOptions(options);
/*      */   }
/*      */   
/*      */   public void traverse(List<? extends EObject> contents) {
/*      */     Object mark;
/*  574 */     if (!this.toDOM && this.declareXML) {
/*      */       
/*  576 */       this.doc.add("<?xml version=\"" + this.xmlVersion + "\" encoding=\"" + this.encoding + "\"?>");
/*  577 */       this.doc.addLine();
/*      */     } 
/*      */     
/*  580 */     int size = contents.size();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  586 */     if (size == 1) {
/*      */       
/*  588 */       mark = writeTopObject(contents.get(0));
/*      */     }
/*      */     else {
/*      */       
/*  592 */       mark = writeTopObjects(contents);
/*      */     } 
/*  594 */     if (!this.toDOM) {
/*      */ 
/*      */ 
/*      */       
/*  598 */       this.doc.resetToMark(mark);
/*      */     }
/*      */     else {
/*      */       
/*  602 */       this.currentNode = this.document.getDocumentElement();
/*      */     } 
/*  604 */     addNamespaceDeclarations();
/*  605 */     addDoctypeInformation();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeTopAttributes(EObject top) {
/*  612 */     if (this.useEncodedAttributeStyle) {
/*      */       
/*  614 */       InternalEObject container = ((InternalEObject)top).eInternalContainer();
/*  615 */       if (container != null) {
/*      */         
/*  617 */         EReference containmentReference = top.eContainmentFeature();
/*  618 */         EReference containerReference = containmentReference.getEOpposite();
/*  619 */         if (containerReference != null && !containerReference.isTransient())
/*      */         {
/*  621 */           saveEObjectSingle(top, (EStructuralFeature)containerReference);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean writeTopElements(EObject top) {
/*  629 */     if (!this.useEncodedAttributeStyle) {
/*      */       
/*  631 */       InternalEObject container = ((InternalEObject)top).eInternalContainer();
/*  632 */       if (container != null) {
/*      */         
/*  634 */         EReference containmentReference = top.eContainmentFeature();
/*  635 */         EReference containerReference = containmentReference.getEOpposite();
/*  636 */         if (containerReference != null && !containerReference.isTransient()) {
/*      */           
/*  638 */           saveHref((EObject)container, (EStructuralFeature)containerReference);
/*  639 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*  643 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object writeTopObject(EObject top) {
/*  648 */     EClass eClass = top.eClass();
/*  649 */     if (!this.toDOM) {
/*      */       
/*  651 */       if (this.extendedMetaData == null || this.featureTable.getDocumentRoot(eClass.getEPackage()) != eClass) {
/*      */         
/*  653 */         EStructuralFeature rootFeature = null;
/*  654 */         boolean shouldSaveType = false;
/*  655 */         if (this.elementHandler != null) {
/*      */           
/*  657 */           EClassifier eClassifier = 
/*  658 */             (eClass == this.anySimpleType) ? 
/*  659 */             (EClassifier)((SimpleAnyType)top).getInstanceType() : 
/*  660 */             (EClassifier)eClass;
/*  661 */           rootFeature = this.featureTable.getRoot(eClassifier);
/*  662 */           if (rootFeature != null && rootFeature.getEType() != eClassifier)
/*      */           {
/*  664 */             shouldSaveType = true;
/*      */           }
/*      */         } 
/*  667 */         String name = 
/*  668 */           (rootFeature != null) ? 
/*  669 */           this.helper.getQName(rootFeature) : (
/*  670 */           (this.extendedMetaData != null && this.roots != null && top.eContainmentFeature() != null) ? 
/*  671 */           this.helper.getQName((EStructuralFeature)top.eContainmentFeature()) : 
/*  672 */           this.helper.getQName(eClass));
/*  673 */         this.doc.startElement(name);
/*  674 */         Object mark = this.doc.mark();
/*  675 */         this.root = top;
/*  676 */         if (shouldSaveType)
/*      */         {
/*  678 */           saveTypeAttribute(eClass);
/*      */         }
/*  680 */         saveElementID(top);
/*  681 */         return mark;
/*      */       } 
/*      */ 
/*      */       
/*  685 */       this.doc.startElement((String)null);
/*  686 */       this.root = top;
/*  687 */       saveFeatures(top);
/*  688 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  693 */     if (this.extendedMetaData == null || this.featureTable.getDocumentRoot(eClass.getEPackage()) != eClass) {
/*      */       
/*  695 */       EStructuralFeature rootFeature = null;
/*  696 */       boolean shouldSaveType = false;
/*  697 */       if (this.elementHandler != null) {
/*      */         
/*  699 */         EClassifier eClassifier = 
/*  700 */           (eClass == this.anySimpleType) ? 
/*  701 */           (EClassifier)((SimpleAnyType)top).getInstanceType() : 
/*  702 */           (EClassifier)eClass;
/*  703 */         rootFeature = this.featureTable.getRoot(eClassifier);
/*  704 */         if (rootFeature != null && rootFeature.getEType() != eClassifier)
/*      */         {
/*  706 */           shouldSaveType = true;
/*      */         }
/*      */       } 
/*  709 */       if (rootFeature != null) {
/*      */         
/*  711 */         this.helper.populateNameInfo(this.nameInfo, rootFeature);
/*      */       }
/*  713 */       else if (this.extendedMetaData != null && this.roots != null && top.eContainmentFeature() != null) {
/*      */         
/*  715 */         this.helper.populateNameInfo(this.nameInfo, (EStructuralFeature)top.eContainmentFeature());
/*      */       }
/*      */       else {
/*      */         
/*  719 */         this.helper.populateNameInfo(this.nameInfo, eClass);
/*      */       } 
/*  721 */       if (this.document.getLastChild() == null) {
/*      */         
/*  723 */         this.currentNode = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/*  724 */         this.currentNode = this.document.appendChild(this.currentNode);
/*      */       }
/*      */       else {
/*      */         
/*  728 */         this.currentNode = this.currentNode.appendChild(this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName()));
/*      */       } 
/*  730 */       this.handler.recordValues(this.currentNode, null, null, top);
/*  731 */       this.root = top;
/*  732 */       if (shouldSaveType)
/*      */       {
/*  734 */         saveTypeAttribute(eClass);
/*      */       }
/*  736 */       saveElementID(top);
/*  737 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  741 */     this.root = top;
/*  742 */     this.currentNode = this.document;
/*  743 */     saveFeatures(top);
/*  744 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object writeTopObjects(List<? extends EObject> contents) {
/*  751 */     return writeTopObject(contents.get(0));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addNamespaceDeclarations() {
/*  756 */     EPackage noNamespacePackage = this.helper.getNoNamespacePackage();
/*  757 */     EPackage[] packages = this.helper.packages();
/*  758 */     this.buffer.setLength(0);
/*  759 */     StringBuffer xsiSchemaLocation = this.buffer;
/*  760 */     String xsiNoNamespaceSchemaLocation = null;
/*  761 */     if (this.declareSchemaLocation) {
/*      */       
/*  763 */       Map<String, String> handledBySchemaLocationMap = Collections.emptyMap();
/*      */       
/*  765 */       if (this.extendedMetaData != null) {
/*      */         
/*  767 */         XMLResource xMLResource = this.helper.getResource();
/*  768 */         if (xMLResource != null && xMLResource.getContents().size() >= 1) {
/*      */           
/*  770 */           EObject root = getSchemaLocationRoot((EObject)xMLResource.getContents().get(0));
/*  771 */           EClass eClass = root.eClass();
/*      */           
/*  773 */           EReference xsiSchemaLocationMapFeature = this.extendedMetaData.getXSISchemaLocationMapFeature(eClass);
/*  774 */           if (xsiSchemaLocationMapFeature != null) {
/*      */             
/*  776 */             EMap<String, String> xsiSchemaLocationMap = (EMap<String, String>)root.eGet((EStructuralFeature)xsiSchemaLocationMapFeature);
/*  777 */             if (!xsiSchemaLocationMap.isEmpty()) {
/*      */               
/*  779 */               handledBySchemaLocationMap = xsiSchemaLocationMap.map();
/*  780 */               this.declareXSI = true;
/*  781 */               for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)xsiSchemaLocationMap.entrySet()) {
/*      */                 
/*  783 */                 String namespace = entry.getKey();
/*  784 */                 URI location = URI.createURI(entry.getValue());
/*  785 */                 if (namespace == null) {
/*      */                   
/*  787 */                   xsiNoNamespaceSchemaLocation = this.helper.deresolve(location).toString();
/*      */                   
/*      */                   continue;
/*      */                 } 
/*  791 */                 if (xsiSchemaLocation.length() > 0)
/*      */                 {
/*  793 */                   xsiSchemaLocation.append(' ');
/*      */                 }
/*  795 */                 xsiSchemaLocation.append(namespace);
/*  796 */                 xsiSchemaLocation.append(' ');
/*  797 */                 xsiSchemaLocation.append(this.helper.deresolve(location).toString());
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  805 */       for (int j = 0; j < packages.length; j++) {
/*      */         
/*  807 */         EPackage ePackage = packages[j];
/*      */         
/*  809 */         String javaImplementationLocation = null;
/*  810 */         if (this.declareSchemaLocationImplementation) {
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */             
/*  816 */             Field field = ePackage.getClass().getField("eINSTANCE");
/*  817 */             javaImplementationLocation = "java://" + field.getDeclaringClass().getName();
/*      */           }
/*  819 */           catch (Exception exception) {}
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  825 */         if (noNamespacePackage == ePackage) {
/*      */           
/*  827 */           if (ePackage.eResource() != null && !handledBySchemaLocationMap.containsKey(null)) {
/*      */             
/*  829 */             this.declareXSI = true;
/*  830 */             if (javaImplementationLocation != null)
/*      */             {
/*  832 */               xsiNoNamespaceSchemaLocation = javaImplementationLocation;
/*      */             }
/*      */             else
/*      */             {
/*  836 */               xsiNoNamespaceSchemaLocation = this.helper.getHREF((EObject)ePackage);
/*  837 */               if (xsiNoNamespaceSchemaLocation != null && xsiNoNamespaceSchemaLocation.endsWith("#/"))
/*      */               {
/*  839 */                 xsiNoNamespaceSchemaLocation = xsiNoNamespaceSchemaLocation.substring(0, xsiNoNamespaceSchemaLocation.length() - 2);
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/*  846 */           Resource resource = ePackage.eResource();
/*  847 */           if (resource != null) {
/*      */             
/*  849 */             String nsURI = (this.extendedMetaData == null) ? ePackage.getNsURI() : this.extendedMetaData.getNamespace(ePackage);
/*  850 */             if (!handledBySchemaLocationMap.containsKey(nsURI)) {
/*      */               
/*  852 */               URI uri = resource.getURI();
/*  853 */               if (javaImplementationLocation != null || ((uri == null) ? (nsURI != null) : !uri.toString().equals(nsURI))) {
/*      */                 
/*  855 */                 this.declareXSI = true;
/*  856 */                 if (xsiSchemaLocation.length() > 0)
/*      */                 {
/*  858 */                   xsiSchemaLocation.append(' ');
/*      */                 }
/*  860 */                 xsiSchemaLocation.append(nsURI);
/*  861 */                 xsiSchemaLocation.append(' ');
/*      */                 
/*  863 */                 String location = (javaImplementationLocation == null) ? this.helper.getHREF((EObject)ePackage) : javaImplementationLocation;
/*  864 */                 location = convertURI(location);
/*  865 */                 if (location.endsWith("#/")) {
/*      */                   
/*  867 */                   location = location.substring(0, location.length() - 2);
/*  868 */                   if (uri != null && uri.hasFragment())
/*      */                   {
/*  870 */                     location = String.valueOf(location) + "#" + uri.fragment();
/*      */                   }
/*      */                 } 
/*  873 */                 xsiSchemaLocation.append(location);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  881 */     if (this.declareXSI)
/*      */     {
/*  883 */       if (!this.toDOM) {
/*      */         
/*  885 */         this.doc.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
/*      */       }
/*      */       else {
/*      */         
/*  889 */         ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
/*      */       } 
/*      */     }
/*      */     
/*  893 */     for (int i = 0; i < packages.length; i++) {
/*      */       
/*  895 */       EPackage ePackage = packages[i];
/*  896 */       if (ePackage != noNamespacePackage && 
/*  897 */         ePackage != XMLNamespacePackage.eINSTANCE && 
/*  898 */         !"http://www.w3.org/2000/xmlns/".equals(ePackage.getNsURI())) {
/*      */         
/*  900 */         String nsURI = (this.extendedMetaData == null) ? ePackage.getNsURI() : this.extendedMetaData.getNamespace(ePackage);
/*  901 */         if (ePackage == this.xmlSchemaTypePackage)
/*      */         {
/*  903 */           nsURI = "http://www.w3.org/2001/XMLSchema";
/*      */         }
/*  905 */         if (nsURI != null && !isDuplicateURI(nsURI)) {
/*      */           
/*  907 */           List<String> nsPrefixes = this.helper.getPrefixes(ePackage);
/*  908 */           for (String nsPrefix : nsPrefixes) {
/*      */             
/*  910 */             if (!this.toDOM) {
/*      */               
/*  912 */               if (nsPrefix != null && nsPrefix.length() > 0) {
/*      */                 
/*  914 */                 if (!this.declareXSI || !"xsi".equals(nsPrefix))
/*      */                 {
/*  916 */                   this.doc.addAttributeNS("xmlns", nsPrefix, nsURI);
/*      */                 }
/*      */                 
/*      */                 continue;
/*      */               } 
/*  921 */               this.doc.addAttribute("xmlns", nsURI);
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  926 */             if (nsPrefix != null && nsPrefix.length() > 0) {
/*      */               
/*  928 */               if (!this.declareXSI || !"xsi".equals(nsPrefix))
/*      */               {
/*  930 */                 ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + nsPrefix, nsURI);
/*      */               }
/*      */               
/*      */               continue;
/*      */             } 
/*  935 */             ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", nsURI);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  943 */     if (xsiSchemaLocation.length() > 0)
/*      */     {
/*  945 */       if (!this.toDOM) {
/*      */         
/*  947 */         this.doc.addAttribute("xsi:schemaLocation", xsiSchemaLocation.toString());
/*      */       }
/*      */       else {
/*      */         
/*  951 */         ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", xsiSchemaLocation.toString());
/*      */       } 
/*      */     }
/*      */     
/*  955 */     if (xsiNoNamespaceSchemaLocation != null)
/*      */     {
/*  957 */       if (!this.toDOM) {
/*      */         
/*  959 */         this.doc.addAttribute("xsi:noNamespaceSchemaLocation", xsiNoNamespaceSchemaLocation);
/*      */       }
/*      */       else {
/*      */         
/*  963 */         ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation", xsiNoNamespaceSchemaLocation);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addDoctypeInformation() {
/*  970 */     if (this.resourceEntityHandler != null)
/*      */     {
/*  972 */       if (!this.toDOM)
/*      */       {
/*  974 */         for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)this.resourceEntityHandler.getNameToValueMap().entrySet())
/*      */         {
/*  976 */           this.doc.addEntity(entry.getKey(), entry.getValue());
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject getSchemaLocationRoot(EObject eObject) {
/*  988 */     return eObject;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDuplicateURI(String nsURI) {
/*  993 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void write(OutputStreamWriter os) throws IOException {
/* 1004 */     write(os);
/*      */   }
/*      */ 
/*      */   
/*      */   public void write(Writer os) throws IOException {
/* 1009 */     this.doc.write(os, this.flushThreshold);
/* 1010 */     os.flush();
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeAscii(OutputStream os) throws IOException {
/* 1015 */     this.doc.writeAscii(os, this.flushThreshold);
/* 1016 */     os.flush();
/*      */   }
/*      */ 
/*      */   
/*      */   public char[] toChar() {
/* 1021 */     int size = this.doc.getLength();
/* 1022 */     char[] output = new char[size];
/* 1023 */     this.doc.getChars(output, 0);
/* 1024 */     return output;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElement(InternalEObject o, EStructuralFeature f) {
/* 1029 */     if (o.eDirectResource() != null || o.eIsProxy()) {
/*      */       
/* 1031 */       saveHref((EObject)o, f);
/*      */     }
/*      */     else {
/*      */       
/* 1035 */       saveElement((EObject)o, f);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElement(EObject o, EStructuralFeature f) {
/* 1041 */     EClass eClass = o.eClass();
/* 1042 */     EClassifier eType = f.getEType();
/*      */     
/* 1044 */     if (this.extendedMetaData != null && eClass != eType) {
/*      */ 
/*      */ 
/*      */       
/* 1048 */       String name = this.extendedMetaData.getName((EClassifier)eClass);
/* 1049 */       if (name.endsWith("_._type")) {
/*      */         
/* 1051 */         String elementName = name.substring(0, name.indexOf("_._"));
/* 1052 */         String prefix = this.helper.getPrefix(eClass.getEPackage());
/* 1053 */         if (!"".equals(prefix))
/*      */         {
/* 1055 */           elementName = String.valueOf(prefix) + ":" + elementName;
/*      */         }
/* 1057 */         if (!this.toDOM) {
/*      */           
/* 1059 */           this.doc.startElement(elementName);
/*      */         }
/*      */         else {
/*      */           
/* 1063 */           this.currentNode = this.currentNode.appendChild(this.document.createElementNS(this.helper.getNamespaceURI(prefix), elementName));
/* 1064 */           this.handler.recordValues(this.currentNode, o.eContainer(), f, o);
/*      */         } 
/* 1066 */         saveElementID(o);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 1071 */     if (this.map != null) {
/*      */       
/* 1073 */       XMLResource.XMLInfo info = this.map.getInfo((ENamedElement)eClass);
/* 1074 */       if (info != null && info.getXMLRepresentation() == 0) {
/*      */         
/* 1076 */         if (!this.toDOM) {
/*      */           
/* 1078 */           String elementName = this.helper.getQName(eClass);
/* 1079 */           this.doc.startElement(elementName);
/*      */         }
/*      */         else {
/*      */           
/* 1083 */           this.helper.populateNameInfo(this.nameInfo, eClass);
/* 1084 */           if (this.currentNode == null) {
/*      */             
/* 1086 */             this.currentNode = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1087 */             this.document.appendChild(this.currentNode);
/* 1088 */             this.handler.recordValues(this.currentNode, o.eContainer(), f, o);
/*      */           }
/*      */           else {
/*      */             
/* 1092 */             this.currentNode = this.currentNode.appendChild(this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName()));
/* 1093 */             this.handler.recordValues(this.currentNode, o.eContainer(), f, o);
/*      */           } 
/*      */         } 
/* 1096 */         saveElementID(o);
/*      */         return;
/*      */       } 
/*      */     } 
/* 1100 */     boolean isAnyType = false;
/* 1101 */     if (o instanceof AnyType) {
/*      */       
/* 1103 */       isAnyType = true;
/* 1104 */       this.helper.pushContext();
/* 1105 */       for (FeatureMap.Entry entry : ((AnyType)o).getAnyAttribute()) {
/*      */         
/* 1107 */         if ("http://www.w3.org/2000/xmlns/".equals(this.extendedMetaData.getNamespace(entry.getEStructuralFeature()))) {
/*      */           
/* 1109 */           String uri = (String)entry.getValue();
/* 1110 */           this.helper.addPrefix(this.extendedMetaData.getName(entry.getEStructuralFeature()), (uri == null) ? "" : uri);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1114 */     boolean shouldSaveType = 
/* 1115 */       this.saveTypeInfo ? 
/* 1116 */       this.xmlTypeInfo.shouldSaveType(eClass, eType, f) : (
/* 1117 */       (eClass != eType && (
/* 1118 */       eClass != this.anyType || 
/* 1119 */       this.extendedMetaData == null || 
/* 1120 */       eType != EcorePackage.Literals.EOBJECT || 
/* 1121 */       this.extendedMetaData.getFeatureKind(f) == 0)));
/* 1122 */     EDataType eDataType = null;
/* 1123 */     if (shouldSaveType) {
/*      */       
/* 1125 */       EClassifier eClassifier = 
/* 1126 */         (eClass == this.anySimpleType) ? 
/* 1127 */         (EClassifier)(eDataType = ((SimpleAnyType)o).getInstanceType()) : 
/* 1128 */         (EClassifier)eClass;
/* 1129 */       if (this.elementHandler != null) {
/*      */         
/* 1131 */         EStructuralFeature substitutionGroup = this.featureTable.getSubstitutionGroup(f, eClassifier);
/* 1132 */         if (substitutionGroup != null) {
/*      */           
/* 1134 */           f = substitutionGroup;
/* 1135 */           shouldSaveType = (substitutionGroup.getEType() != eClassifier);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1140 */     if (!this.toDOM) {
/*      */       
/* 1142 */       String featureName = this.helper.getQName(f);
/* 1143 */       this.doc.startElement(featureName);
/*      */     }
/*      */     else {
/*      */       
/* 1147 */       this.helper.populateNameInfo(this.nameInfo, f);
/* 1148 */       if (this.currentNode == null) {
/*      */ 
/*      */         
/* 1151 */         this.currentNode = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1152 */         this.document.appendChild(this.currentNode);
/* 1153 */         this.handler.recordValues(this.currentNode, o.eContainer(), f, o);
/*      */       }
/*      */       else {
/*      */         
/* 1157 */         this.currentNode = this.currentNode.appendChild(this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName()));
/* 1158 */         this.handler.recordValues(this.currentNode, o.eContainer(), f, o);
/*      */       } 
/*      */     } 
/*      */     
/* 1162 */     if (shouldSaveType)
/*      */     {
/* 1164 */       if (eDataType != null) {
/*      */         
/* 1166 */         saveTypeAttribute(eDataType);
/*      */       }
/*      */       else {
/*      */         
/* 1170 */         saveTypeAttribute(eClass);
/*      */       } 
/*      */     }
/*      */     
/* 1174 */     saveElementID(o);
/* 1175 */     if (isAnyType)
/*      */     {
/* 1177 */       this.helper.popContext();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveTypeAttribute(EClass eClass) {
/* 1183 */     this.declareXSI = true;
/* 1184 */     if (!this.toDOM) {
/*      */       
/* 1186 */       this.doc.addAttribute("xsi:type", this.helper.getQName(eClass));
/*      */     }
/*      */     else {
/*      */       
/* 1190 */       this.helper.populateNameInfo(this.nameInfo, eClass);
/* 1191 */       ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type", this.nameInfo.getQualifiedName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveTypeAttribute(EDataType eDataType) {
/* 1198 */     this.declareXSI = true;
/* 1199 */     if (!this.toDOM) {
/*      */       
/* 1201 */       this.doc.addAttribute("xsi:type", this.helper.getQName(eDataType));
/*      */     }
/*      */     else {
/*      */       
/* 1205 */       this.helper.populateNameInfo(this.nameInfo, eDataType);
/* 1206 */       ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type", this.nameInfo.getQualifiedName());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
/* 1212 */     return !(!o.eIsSet(f) && (!this.keepDefaults || f.getDefaultValueLiteral() == null));
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean saveFeatures(EObject o) {
/* 1217 */     EClass eClass = o.eClass();
/* 1218 */     int contentKind = (this.extendedMetaData == null) ? 0 : this.extendedMetaData.getContentKind(eClass);
/* 1219 */     if (!this.toDOM)
/*      */     {
/* 1221 */       switch (contentKind) {
/*      */ 
/*      */         
/*      */         case 2:
/*      */         case 3:
/* 1226 */           this.doc.setMixed(true);
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     }
/* 1232 */     if (o == this.root)
/*      */     {
/* 1234 */       writeTopAttributes(this.root);
/*      */     }
/*      */     
/* 1237 */     EStructuralFeature[] features = this.featureTable.getFeatures(eClass);
/* 1238 */     int[] featureKinds = this.featureTable.getKinds(eClass, features);
/* 1239 */     int[] elementFeatures = (int[])null;
/* 1240 */     int elementCount = 0;
/*      */     
/* 1242 */     String content = null;
/*      */     
/*      */     int i;
/*      */     
/* 1246 */     for (i = 0; i < features.length; i++) {
/*      */       
/* 1248 */       int kind = featureKinds[i];
/* 1249 */       EStructuralFeature f = features[i];
/* 1250 */       if (kind != 0 && shouldSaveFeature(o, f))
/*      */       {
/* 1252 */         switch (kind) {
/*      */ 
/*      */           
/*      */           case 2:
/* 1256 */             if (contentKind == 2) {
/*      */               
/* 1258 */               content = getDataTypeElementSingleSimple(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1:
/* 1265 */             saveDataTypeSingle(o, f);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 4:
/* 1270 */             if (!isNil(o, f)) {
/*      */               
/* 1272 */               saveDataTypeSingle(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 22:
/* 1279 */             saveEObjectSingle(o, f);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 23:
/* 1284 */             saveEObjectMany(o, f);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 24:
/* 1289 */             saveIDRefSingle(o, f);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 25:
/* 1294 */             saveIDRefMany(o, f);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 12:
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/* 1307 */             if (this.useEncodedAttributeStyle) {
/*      */               
/* 1309 */               saveEObjectSingle(o, f);
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 1314 */             switch (sameDocSingle(o, f)) {
/*      */ 
/*      */               
/*      */               case 1:
/* 1318 */                 saveIDRefSingle(o, f);
/*      */                 break;
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               case 2:
/*      */               case 0:
/*      */                 break;
/*      */             } 
/*      */ 
/*      */           
/*      */           
/*      */ 
/*      */ 
/*      */           
/*      */           case 13:
/* 1335 */             if (isEmpty(o, f)) {
/*      */               
/* 1337 */               saveManyEmpty(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/* 1344 */             if (this.useEncodedAttributeStyle) {
/*      */               
/* 1346 */               saveEObjectMany(o, f);
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 1351 */             switch (sameDocMany(o, f)) {
/*      */ 
/*      */               
/*      */               case 1:
/* 1355 */                 saveIDRefMany(o, f);
/*      */                 break;
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               case 2:
/*      */               case 0:
/*      */                 break;
/*      */             } 
/*      */ 
/*      */           
/*      */           
/*      */ 
/*      */ 
/*      */           
/*      */           case 14:
/*      */           case 15:
/* 1373 */             if (contentKind == 2) {
/*      */               
/* 1375 */               content = getElementReferenceSingleSimple(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 16:
/* 1382 */             if (contentKind == 2) {
/*      */               
/* 1384 */               content = getElementReferenceManySimple(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 17:
/*      */           case 18:
/* 1392 */             if (contentKind == 2) {
/*      */               
/* 1394 */               content = getElementIDRefSingleSimple(o, f);
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case 19:
/* 1401 */             if (contentKind == 2) {
/*      */               
/* 1403 */               content = getElementIDRefManySimple(o, f);
/*      */               break;
/*      */             } 
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
/*      */ 
/*      */ 
/*      */           
/*      */           case 26:
/* 1442 */             if (elementFeatures == null)
/*      */             {
/* 1444 */               elementFeatures = new int[features.length];
/*      */             }
/* 1446 */             elementFeatures[elementCount++] = i; continue;case 5: case 11: if (isEmpty(o, f)) { saveManyEmpty(o, f); break; } case 6: case 7: case 10: case 21: if (elementFeatures == null) elementFeatures = new int[features.length];  elementFeatures[elementCount++] = i; continue;
/*      */           case 20:
/*      */             saveAttributeFeatureMap(o, f); break;
/*      */         }  }  continue;
/* 1450 */     }  processAttributeExtensions(o);
/*      */     
/* 1452 */     if (elementFeatures == null) {
/*      */       
/* 1454 */       if (content == null)
/*      */       {
/* 1456 */         content = getContent(o, features);
/*      */       }
/*      */       
/* 1459 */       if (content == null) {
/*      */         
/* 1461 */         if (o == this.root && writeTopElements(this.root)) {
/*      */           
/* 1463 */           endSaveFeatures(o, 0, null);
/* 1464 */           return true;
/*      */         } 
/*      */ 
/*      */         
/* 1468 */         endSaveFeatures(o, 1, null);
/* 1469 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1474 */       endSaveFeatures(o, 2, content);
/* 1475 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1479 */     if (o == this.root)
/*      */     {
/* 1481 */       writeTopElements(this.root);
/*      */     }
/*      */ 
/*      */     
/* 1485 */     for (i = 0; i < elementCount; i++) {
/*      */       
/* 1487 */       int kind = featureKinds[elementFeatures[i]];
/* 1488 */       EStructuralFeature f = features[elementFeatures[i]];
/* 1489 */       switch (kind) {
/*      */ 
/*      */         
/*      */         case 4:
/* 1493 */           saveNil(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 21:
/* 1498 */           saveElementFeatureMap(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 5:
/* 1503 */           saveDataTypeMany(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 26:
/* 1508 */           saveDataTypeAttributeMany(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/* 1513 */           saveDataTypeElementSingle(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 10:
/* 1518 */           if (isNil(o, f)) {
/*      */             
/* 1520 */             saveNil(o, f);
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 6:
/* 1527 */           saveContainedSingle(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 7:
/*      */         case 11:
/* 1533 */           saveContainedMany(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 12:
/* 1538 */           if (isNil(o, f)) {
/*      */             
/* 1540 */             saveNil(o, f);
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 8:
/* 1547 */           saveHRefSingle(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 9:
/*      */         case 13:
/* 1553 */           saveHRefMany(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 15:
/* 1558 */           if (isNil(o, f)) {
/*      */             
/* 1560 */             saveNil(o, f);
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 14:
/* 1567 */           saveElementReferenceSingle(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 16:
/* 1572 */           saveElementReferenceMany(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 18:
/* 1577 */           if (isNil(o, f)) {
/*      */             
/* 1579 */             saveNil(o, f);
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 17:
/* 1586 */           saveElementIDRefSingle(o, f);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 19:
/* 1591 */           saveElementIDRefMany(o, f);
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1596 */     endSaveFeatures(o, 0, null);
/* 1597 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void endSaveFeatures(EObject o, int elementType, String content) {
/* 1602 */     if (processElementExtensions(o)) {
/*      */       
/* 1604 */       if (!this.toDOM)
/*      */       {
/* 1606 */         this.doc.endElement();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1611 */       switch (elementType) {
/*      */ 
/*      */         
/*      */         case 1:
/* 1615 */           if (!this.toDOM)
/*      */           {
/* 1617 */             this.doc.endEmptyElement();
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/* 1623 */           if (!this.toDOM)
/*      */           {
/* 1625 */             this.doc.endContentElement(content);
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/* 1631 */           if (!this.toDOM)
/*      */           {
/* 1633 */             this.doc.endElement();
/*      */           }
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1639 */     if (this.toDOM)
/*      */     {
/* 1641 */       this.currentNode = this.currentNode.getParentNode();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean processElementExtensions(EObject object) {
/* 1650 */     if (this.eObjectToExtensionMap != null) {
/*      */       
/* 1652 */       AnyType anyType = this.eObjectToExtensionMap.get(object);
/* 1653 */       return (anyType != null && saveElementFeatureMap((EObject)anyType, (EStructuralFeature)XMLTypePackage.eINSTANCE.getAnyType_Mixed()));
/*      */     } 
/*      */ 
/*      */     
/* 1657 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void processAttributeExtensions(EObject object) {
/* 1665 */     if (this.eObjectToExtensionMap != null) {
/*      */       
/* 1667 */       AnyType anyType = this.eObjectToExtensionMap.get(object);
/* 1668 */       if (anyType != null)
/*      */       {
/* 1670 */         saveAttributeFeatureMap((EObject)anyType, (EStructuralFeature)XMLTypePackage.eINSTANCE.getAnyType_AnyAttribute());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveDataTypeSingle(EObject o, EStructuralFeature f) {
/* 1677 */     Object value = this.helper.getValue(o, f);
/* 1678 */     String svalue = getDatatypeValue(value, f, true);
/* 1679 */     if (svalue != null)
/*      */     {
/* 1681 */       if (!this.toDOM) {
/*      */         
/* 1683 */         this.doc.addAttribute(this.helper.getQName(f), svalue);
/*      */       }
/*      */       else {
/*      */         
/* 1687 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 1688 */         Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1689 */         attr.setNodeValue(svalue);
/* 1690 */         ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1691 */         this.handler.recordValues(attr, o, f, value);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isNil(EObject o, EStructuralFeature f) {
/* 1698 */     return (this.helper.getValue(o, f) == null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isEmpty(EObject o, EStructuralFeature f) {
/* 1703 */     return ((List)this.helper.getValue(o, f)).isEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveNil(EObject o, EStructuralFeature f) {
/* 1708 */     if (!this.toDOM) {
/*      */       
/* 1710 */       saveNil(f);
/*      */     }
/*      */     else {
/*      */       
/* 1714 */       this.declareXSI = true;
/* 1715 */       this.helper.populateNameInfo(this.nameInfo, f);
/* 1716 */       Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1717 */       elem.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:nil", "true");
/* 1718 */       this.currentNode.appendChild(elem);
/* 1719 */       this.handler.recordValues(this.currentNode.getLastChild(), o, f, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveNil(EStructuralFeature f) {
/* 1725 */     this.declareXSI = true;
/* 1726 */     this.doc.saveNilElement(this.helper.getQName(f));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveManyEmpty(EObject o, EStructuralFeature f) {
/* 1731 */     if (!this.toDOM) {
/*      */       
/* 1733 */       saveManyEmpty(f);
/*      */     }
/*      */     else {
/*      */       
/* 1737 */       this.helper.populateNameInfo(this.nameInfo, f);
/* 1738 */       Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1739 */       ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1740 */       this.handler.recordValues(attr, o, f, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveManyEmpty(EStructuralFeature f) {
/* 1746 */     this.doc.addAttribute(this.helper.getQName(f), "");
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveDataTypeMany(EObject o, EStructuralFeature f) {
/* 1751 */     List<?> values = (List)this.helper.getValue(o, f);
/* 1752 */     int size = values.size();
/* 1753 */     if (size > 0)
/*      */     {
/*      */       
/* 1756 */       if (!this.toDOM) {
/*      */         
/* 1758 */         EDataType d = (EDataType)f.getEType();
/* 1759 */         EPackage ePackage = d.getEPackage();
/* 1760 */         EFactory fac = ePackage.getEFactoryInstance();
/* 1761 */         String name = this.helper.getQName(f);
/* 1762 */         for (int i = 0; i < size; i++) {
/*      */           
/* 1764 */           Object value = values.get(i);
/* 1765 */           if (value == null)
/*      */           {
/* 1767 */             this.doc.startElement(name);
/* 1768 */             this.doc.addAttribute("xsi:nil", "true");
/* 1769 */             this.doc.endEmptyElement();
/* 1770 */             this.declareXSI = true;
/*      */           }
/*      */           else
/*      */           {
/* 1774 */             String svalue = this.helper.convertToString(fac, d, value);
/* 1775 */             if (this.escape != null)
/*      */             {
/* 1777 */               svalue = this.escape.convert(svalue);
/*      */             }
/* 1779 */             this.doc.saveDataValueElement(name, svalue);
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 1785 */         EDataType d = (EDataType)f.getEType();
/* 1786 */         EPackage ePackage = d.getEPackage();
/* 1787 */         EFactory fac = ePackage.getEFactoryInstance();
/* 1788 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 1789 */         for (int i = 0; i < size; i++) {
/*      */           
/* 1791 */           Object value = values.get(i);
/* 1792 */           if (value == null) {
/*      */             
/* 1794 */             Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1795 */             elem.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:nil", "true");
/* 1796 */             this.currentNode.appendChild(elem);
/* 1797 */             this.handler.recordValues(elem, o, f, null);
/* 1798 */             this.declareXSI = true;
/*      */           }
/*      */           else {
/*      */             
/* 1802 */             Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1803 */             Node text = this.document.createTextNode(this.helper.convertToString(fac, d, value));
/* 1804 */             elem.appendChild(text);
/* 1805 */             this.currentNode.appendChild(elem);
/* 1806 */             this.handler.recordValues(elem, o, f, value);
/* 1807 */             this.handler.recordValues(text, o, f, value);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveDataTypeAttributeMany(EObject o, EStructuralFeature f) {
/* 1816 */     List<?> values = (List)this.helper.getValue(o, f);
/* 1817 */     int size = values.size();
/* 1818 */     if (size > 0) {
/*      */       
/* 1820 */       EDataType d = (EDataType)f.getEType();
/* 1821 */       EPackage ePackage = d.getEPackage();
/* 1822 */       EFactory fac = ePackage.getEFactoryInstance();
/* 1823 */       StringBuffer stringValues = new StringBuffer();
/* 1824 */       for (int i = 0; i < size; i++) {
/*      */         
/* 1826 */         Object value = values.get(i);
/* 1827 */         if (value != null) {
/*      */           
/* 1829 */           String svalue = this.helper.convertToString(fac, d, value);
/* 1830 */           if (this.escape != null)
/*      */           {
/* 1832 */             svalue = this.escape.convert(svalue);
/*      */           }
/* 1834 */           if (i > 0)
/*      */           {
/* 1836 */             stringValues.append(' ');
/*      */           }
/* 1838 */           stringValues.append(svalue);
/*      */         } 
/*      */       } 
/* 1841 */       if (!this.toDOM) {
/*      */         
/* 1843 */         String name = this.helper.getQName(f);
/* 1844 */         this.doc.startAttribute(name);
/* 1845 */         this.doc.addAttributeContent(stringValues.toString());
/* 1846 */         this.doc.endAttribute();
/*      */       }
/*      */       else {
/*      */         
/* 1850 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 1851 */         Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1852 */         String value = stringValues.toString();
/* 1853 */         attr.setNodeValue(value);
/* 1854 */         ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1855 */         this.handler.recordValues(attr, o, f, value);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveEObjectSingle(EObject o, EStructuralFeature f) {
/* 1862 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 1863 */     if (value != null) {
/*      */       
/* 1865 */       String id = this.helper.getHREF(value);
/* 1866 */       if (id != null) {
/*      */         
/* 1868 */         id = convertURI(id);
/* 1869 */         this.buffer.setLength(0);
/* 1870 */         if (!id.startsWith("#")) {
/*      */           
/* 1872 */           EClass eClass = value.eClass();
/* 1873 */           EClass expectedType = (EClass)f.getEType();
/* 1874 */           if (this.saveTypeInfo ? this.xmlTypeInfo.shouldSaveType(eClass, expectedType, f) : (eClass != expectedType && (expectedType.isAbstract() || f.getEGenericType().getETypeParameter() != null))) {
/*      */             
/* 1876 */             this.buffer.append(this.helper.getQName(eClass));
/* 1877 */             this.buffer.append(' ');
/*      */           } 
/*      */         } 
/* 1880 */         this.buffer.append(id);
/* 1881 */         if (!this.toDOM) {
/*      */           
/* 1883 */           String name = this.helper.getQName(f);
/* 1884 */           this.doc.startAttribute(name);
/* 1885 */           this.doc.addAttributeContent(this.buffer.toString());
/* 1886 */           this.doc.endAttribute();
/*      */         }
/*      */         else {
/*      */           
/* 1890 */           this.helper.populateNameInfo(this.nameInfo, f);
/* 1891 */           Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1892 */           attr.setNodeValue(this.buffer.toString());
/* 1893 */           ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1894 */           this.handler.recordValues(attr, o, f, value);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveEObjectMany(EObject o, EStructuralFeature f) {
/* 1902 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/*      */     
/* 1904 */     if (!values.isEmpty()) {
/*      */       
/* 1906 */       this.buffer.setLength(0);
/* 1907 */       boolean failure = false;
/* 1908 */       Iterator<? extends EObject> i = values.basicIterator();
/*      */       while (true) {
/* 1910 */         EObject value = i.next();
/* 1911 */         String id = this.helper.getHREF(value);
/* 1912 */         if (id == null) {
/*      */           
/* 1914 */           failure = true;
/* 1915 */           if (!i.hasNext()) {
/*      */             break;
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 1922 */         id = convertURI(id);
/* 1923 */         if (!id.startsWith("#")) {
/*      */           
/* 1925 */           EClass eClass = value.eClass();
/* 1926 */           EClass expectedType = (EClass)f.getEType();
/* 1927 */           if (this.saveTypeInfo ? this.xmlTypeInfo.shouldSaveType(eClass, expectedType, f) : (eClass != expectedType && (expectedType.isAbstract() || f.getEGenericType().getETypeParameter() != null))) {
/*      */             
/* 1929 */             this.buffer.append(this.helper.getQName(eClass));
/* 1930 */             this.buffer.append(' ');
/*      */           } 
/*      */         } 
/* 1933 */         this.buffer.append(id);
/* 1934 */         if (i.hasNext()) {
/*      */           
/* 1936 */           this.buffer.append(' ');
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 1945 */       String string = this.buffer.toString();
/* 1946 */       if (!failure || (string = string.trim()).length() != 0)
/*      */       {
/* 1948 */         if (!this.toDOM) {
/*      */           
/* 1950 */           String name = this.helper.getQName(f);
/* 1951 */           this.doc.startAttribute(name);
/* 1952 */           this.doc.addAttributeContent(string);
/* 1953 */           this.doc.endAttribute();
/*      */         }
/*      */         else {
/*      */           
/* 1957 */           this.helper.populateNameInfo(this.nameInfo, f);
/* 1958 */           Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1959 */           attr.setNodeValue(string);
/* 1960 */           ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1961 */           this.handler.recordValues(attr, o, f, values);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveIDRefSingle(EObject o, EStructuralFeature f) {
/* 1969 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 1970 */     if (value != null) {
/*      */       
/* 1972 */       String id = this.helper.getIDREF(value);
/* 1973 */       if (id != null)
/*      */       {
/* 1975 */         if (!this.toDOM) {
/*      */           
/* 1977 */           String name = this.helper.getQName(f);
/* 1978 */           this.doc.addAttribute(name, id);
/*      */         }
/*      */         else {
/*      */           
/* 1982 */           this.helper.populateNameInfo(this.nameInfo, f);
/* 1983 */           Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 1984 */           attr.setNodeValue(id);
/* 1985 */           ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 1986 */           this.handler.recordValues(attr, o, f, value);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveIDRefMany(EObject o, EStructuralFeature f) {
/* 1994 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 1995 */     if (!values.isEmpty()) {
/*      */       
/* 1997 */       this.buffer.setLength(0);
/* 1998 */       StringBuffer ids = this.buffer;
/* 1999 */       boolean failure = false;
/* 2000 */       Iterator<? extends EObject> i = values.basicIterator();
/*      */       while (true) {
/* 2002 */         EObject value = i.next();
/* 2003 */         String id = this.helper.getIDREF(value);
/* 2004 */         if (id == null) {
/*      */           
/* 2006 */           failure = true;
/* 2007 */           if (!i.hasNext()) {
/*      */             break;
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 2014 */         ids.append(id);
/* 2015 */         if (i.hasNext()) {
/*      */           
/* 2017 */           ids.append(' ');
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 2025 */       String idsString = ids.toString();
/* 2026 */       if (!failure || (idsString = idsString.trim()).length() != 0)
/*      */       {
/* 2028 */         if (!this.toDOM) {
/*      */           
/* 2030 */           String name = this.helper.getQName(f);
/* 2031 */           this.doc.addAttribute(name, idsString);
/*      */         }
/*      */         else {
/*      */           
/* 2035 */           this.helper.populateNameInfo(this.nameInfo, f);
/* 2036 */           Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 2037 */           attr.setNodeValue(idsString);
/* 2038 */           ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 2039 */           this.handler.recordValues(attr, o, f, values);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementReference(EObject remote, EStructuralFeature f) {
/* 2047 */     String href = this.helper.getHREF(remote);
/* 2048 */     if (href != null) {
/*      */       
/* 2050 */       href = convertURI(href);
/* 2051 */       EClass eClass = remote.eClass();
/* 2052 */       EClass expectedType = (EClass)f.getEType();
/* 2053 */       boolean shouldSaveType = 
/* 2054 */         this.saveTypeInfo ? 
/* 2055 */         this.xmlTypeInfo.shouldSaveType(eClass, expectedType, f) : (
/* 2056 */         (eClass != expectedType && (expectedType.isAbstract() || f.getEGenericType().getETypeParameter() != null)));
/* 2057 */       if (this.elementHandler != null && shouldSaveType) {
/*      */         
/* 2059 */         EStructuralFeature substitutionGroup = this.featureTable.getSubstitutionGroup(f, (EClassifier)eClass);
/* 2060 */         if (substitutionGroup != null) {
/*      */           
/* 2062 */           f = substitutionGroup;
/* 2063 */           shouldSaveType = (substitutionGroup.getEType() != eClass);
/*      */         } 
/*      */       } 
/* 2066 */       if (!this.toDOM) {
/*      */         
/* 2068 */         this.doc.startElement(this.helper.getQName(f));
/*      */       }
/*      */       else {
/*      */         
/* 2072 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 2073 */         Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 2074 */         Node text = this.document.createTextNode(href);
/* 2075 */         elem.appendChild(text);
/* 2076 */         this.currentNode = this.currentNode.appendChild(elem);
/* 2077 */         this.handler.recordValues(elem, remote.eContainer(), f, remote);
/* 2078 */         this.handler.recordValues(text, remote.eContainer(), f, remote);
/*      */       } 
/* 2080 */       if (shouldSaveType)
/*      */       {
/* 2082 */         saveTypeAttribute(eClass);
/*      */       }
/* 2084 */       if (!this.toDOM) {
/*      */         
/* 2086 */         this.doc.endContentElement(href);
/*      */       }
/*      */       else {
/*      */         
/* 2090 */         this.currentNode = this.currentNode.getParentNode();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementReferenceSingle(EObject o, EStructuralFeature f) {
/* 2097 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 2098 */     if (value != null)
/*      */     {
/* 2100 */       saveElementReference(value, f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementReferenceMany(EObject o, EStructuralFeature f) {
/* 2106 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 2107 */     int size = values.size();
/* 2108 */     for (int i = 0; i < size; i++)
/*      */     {
/* 2110 */       saveElementReference((EObject)values.basicGet(i), f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getElementReferenceSingleSimple(EObject o, EStructuralFeature f) {
/* 2116 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 2117 */     String svalue = this.helper.getHREF(value);
/* 2118 */     if (svalue != null) {
/*      */       
/* 2120 */       svalue = convertURI(svalue);
/*      */       
/* 2122 */       if (this.toDOM) {
/*      */         
/* 2124 */         Node text = this.document.createTextNode(svalue);
/* 2125 */         this.currentNode.appendChild(text);
/* 2126 */         this.handler.recordValues(text, o, f, value);
/*      */       } 
/*      */     } 
/* 2129 */     return svalue;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getElementReferenceManySimple(EObject o, EStructuralFeature f) {
/* 2134 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 2135 */     this.buffer.setLength(0);
/* 2136 */     StringBuffer result = this.buffer;
/* 2137 */     int size = values.size();
/* 2138 */     String href = null;
/* 2139 */     boolean failure = false;
/* 2140 */     for (int i = 0; i < size; i++) {
/*      */       
/* 2142 */       href = this.helper.getHREF((EObject)values.basicGet(i));
/* 2143 */       if (href == null) {
/*      */         
/* 2145 */         failure = true;
/*      */       }
/*      */       else {
/*      */         
/* 2149 */         href = convertURI(href);
/* 2150 */         result.append(href);
/* 2151 */         result.append(' ');
/*      */       } 
/*      */     } 
/* 2154 */     String svalue = result.substring(0, result.length() - 1);
/* 2155 */     if (failure && (svalue = svalue.trim()).length() == 0)
/*      */     {
/* 2157 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2161 */     if (this.toDOM) {
/*      */       
/* 2163 */       Node text = this.document.createTextNode(svalue);
/* 2164 */       this.currentNode.appendChild(text);
/* 2165 */       this.handler.recordValues(text, o, f, values);
/*      */     } 
/* 2167 */     return svalue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveElementIDRef(EObject o, EObject target, EStructuralFeature f) {
/* 2173 */     if (!this.toDOM) {
/*      */       
/* 2175 */       saveElementIDRef(target, f);
/*      */     }
/*      */     else {
/*      */       
/* 2179 */       String id = this.helper.getIDREF(target);
/* 2180 */       if (id != null) {
/*      */         
/* 2182 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 2183 */         Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 2184 */         Node text = this.document.createTextNode(id);
/* 2185 */         elem.appendChild(text);
/* 2186 */         this.currentNode.appendChild(elem);
/* 2187 */         this.handler.recordValues(elem, o, f, target);
/* 2188 */         this.handler.recordValues(text, o, f, target);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementIDRef(EObject target, EStructuralFeature f) {
/* 2195 */     String name = this.helper.getQName(f);
/* 2196 */     String id = this.helper.getIDREF(target);
/* 2197 */     if (id != null)
/*      */     {
/* 2199 */       this.doc.saveDataValueElement(name, id);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementIDRefSingle(EObject o, EStructuralFeature f) {
/* 2205 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 2206 */     if (value != null)
/*      */     {
/* 2208 */       saveElementIDRef(o, value, f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementIDRefMany(EObject o, EStructuralFeature f) {
/* 2214 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 2215 */     int size = values.size();
/* 2216 */     for (int i = 0; i < size; i++)
/*      */     {
/* 2218 */       saveElementIDRef(o, (EObject)values.basicGet(i), f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getElementIDRefSingleSimple(EObject o, EStructuralFeature f) {
/* 2224 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 2225 */     String svalue = this.helper.getIDREF(value);
/* 2226 */     if (svalue == null)
/*      */     {
/* 2228 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2232 */     if (this.toDOM) {
/*      */       
/* 2234 */       Node text = this.document.createTextNode(svalue);
/* 2235 */       this.currentNode.appendChild(text);
/* 2236 */       this.handler.recordValues(text, o, f, value);
/*      */     } 
/* 2238 */     return svalue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getElementIDRefManySimple(EObject o, EStructuralFeature f) {
/* 2244 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 2245 */     this.buffer.setLength(0);
/* 2246 */     StringBuffer result = this.buffer;
/* 2247 */     boolean failure = false;
/* 2248 */     for (int i = 0, size = values.size(); i < size; i++) {
/*      */       
/* 2250 */       String idref = this.helper.getIDREF((EObject)values.basicGet(i));
/* 2251 */       if (idref == null) {
/*      */         
/* 2253 */         failure = true;
/*      */       }
/*      */       else {
/*      */         
/* 2257 */         result.append(idref);
/* 2258 */         result.append(' ');
/*      */       } 
/*      */     } 
/* 2261 */     String svalue = result.substring(0, result.length() - 1);
/* 2262 */     if (failure && (svalue = svalue.trim()).length() == 0)
/*      */     {
/* 2264 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2268 */     if (this.toDOM) {
/*      */       
/* 2270 */       Node text = this.document.createTextNode(svalue);
/* 2271 */       this.currentNode.appendChild(text);
/* 2272 */       this.handler.recordValues(text, o, f, values);
/*      */     } 
/* 2274 */     return svalue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveHref(EObject remote, EStructuralFeature f) {
/* 2280 */     String href = this.helper.getHREF(remote);
/* 2281 */     if (href != null) {
/*      */       
/* 2283 */       href = convertURI(href);
/* 2284 */       EClass eClass = remote.eClass();
/* 2285 */       EClass expectedType = (EClass)f.getEType();
/* 2286 */       boolean shouldSaveType = 
/* 2287 */         this.saveTypeInfo ? 
/* 2288 */         this.xmlTypeInfo.shouldSaveType(eClass, expectedType, f) : (
/* 2289 */         (eClass != expectedType && (expectedType.isAbstract() || f.getEGenericType().getETypeParameter() != null)));
/* 2290 */       if (this.elementHandler != null) {
/*      */         
/* 2292 */         EStructuralFeature substitutionGroup = this.featureTable.getSubstitutionGroup(f, (EClassifier)eClass);
/* 2293 */         if (substitutionGroup != null) {
/*      */           
/* 2295 */           f = substitutionGroup;
/* 2296 */           shouldSaveType = (substitutionGroup.getEType() != eClass);
/*      */         } 
/*      */       } 
/* 2299 */       if (!this.toDOM) {
/*      */         
/* 2301 */         String name = this.helper.getQName(f);
/* 2302 */         this.doc.startElement(name);
/*      */       }
/*      */       else {
/*      */         
/* 2306 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 2307 */         Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 2308 */         this.currentNode = this.currentNode.appendChild(elem);
/* 2309 */         this.handler.recordValues(elem, remote.eContainer(), f, remote);
/*      */       } 
/* 2311 */       if (shouldSaveType)
/*      */       {
/* 2313 */         saveTypeAttribute(eClass);
/*      */       }
/* 2315 */       if (!this.toDOM) {
/*      */         
/* 2317 */         this.doc.addAttribute("href", href);
/* 2318 */         if (this.eObjectToExtensionMap != null) {
/*      */           
/* 2320 */           processAttributeExtensions(remote);
/* 2321 */           if (processElementExtensions(remote))
/*      */           {
/* 2323 */             this.doc.endElement();
/*      */           }
/*      */           else
/*      */           {
/* 2327 */             this.doc.endEmptyElement();
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2332 */           this.doc.endEmptyElement();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2337 */         ((Element)this.currentNode).setAttributeNS((String)null, "href", href);
/* 2338 */         if (this.eObjectToExtensionMap != null) {
/*      */           
/* 2340 */           processAttributeExtensions(remote);
/* 2341 */           processElementExtensions(remote);
/*      */         } 
/* 2343 */         this.currentNode = this.currentNode.getParentNode();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveHRefSingle(EObject o, EStructuralFeature f) {
/* 2350 */     EObject value = (EObject)this.helper.getValue(o, f);
/* 2351 */     if (value != null)
/*      */     {
/* 2353 */       saveHref(value, f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveHRefMany(EObject o, EStructuralFeature f) {
/* 2359 */     InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)this.helper.getValue(o, f);
/* 2360 */     int size = values.size();
/* 2361 */     for (int i = 0; i < size; i++)
/*      */     {
/* 2363 */       saveHref((EObject)values.basicGet(i), f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveContainedSingle(EObject o, EStructuralFeature f) {
/* 2369 */     InternalEObject value = (InternalEObject)this.helper.getValue(o, f);
/* 2370 */     if (value != null)
/*      */     {
/* 2372 */       saveElement(value, f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveContainedMany(EObject o, EStructuralFeature f) {
/* 2378 */     List<? extends InternalEObject> values = (
/* 2379 */       (InternalEList)this.helper.getValue(o, f)).basicList();
/* 2380 */     int size = values.size();
/* 2381 */     for (int i = 0; i < size; i++) {
/*      */       
/* 2383 */       InternalEObject value = values.get(i);
/* 2384 */       if (value != null)
/*      */       {
/* 2386 */         saveElement(value, f);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveFeatureMapElementReference(EObject o, EReference f) {
/* 2393 */     saveElementReference(o, (EStructuralFeature)f);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean saveElementFeatureMap(EObject o, EStructuralFeature f) {
/* 2398 */     List<? extends FeatureMap.Entry> values = (List<? extends FeatureMap.Entry>)this.helper.getValue(o, f);
/* 2399 */     int size = values.size();
/* 2400 */     for (int i = 0; i < size; i++) {
/*      */       
/* 2402 */       FeatureMap.Entry entry = values.get(i);
/* 2403 */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 2404 */       Object value = entry.getValue();
/* 2405 */       if (entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION) {
/*      */         
/* 2407 */         ProcessingInstruction pi = (ProcessingInstruction)value;
/* 2408 */         String target = pi.getTarget();
/* 2409 */         String data = pi.getData();
/* 2410 */         if (this.escape != null && data != null)
/*      */         {
/* 2412 */           data = this.escape.convertLines(data);
/*      */         }
/* 2414 */         if (!this.toDOM)
/*      */         {
/* 2416 */           this.doc.addProcessingInstruction(target, data);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 2421 */           this.currentNode.appendChild(this.document.createProcessingInstruction(target, data));
/*      */         }
/*      */       
/* 2424 */       } else if (entryFeature instanceof EReference) {
/*      */         
/* 2426 */         if (value == null)
/*      */         {
/* 2428 */           saveNil(o, entryFeature);
/*      */         }
/*      */         else
/*      */         {
/* 2432 */           EReference referenceEntryFeature = (EReference)entryFeature;
/* 2433 */           if (referenceEntryFeature.isContainment())
/*      */           {
/* 2435 */             saveElement((InternalEObject)value, entryFeature);
/*      */           }
/* 2437 */           else if (referenceEntryFeature.isResolveProxies())
/*      */           {
/* 2439 */             saveFeatureMapElementReference((EObject)value, referenceEntryFeature);
/*      */           }
/*      */           else
/*      */           {
/* 2443 */             saveElementIDRef(o, (EObject)value, entryFeature);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2449 */       else if (entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT) {
/*      */         
/* 2451 */         String svalue = value.toString();
/* 2452 */         if (this.escape != null)
/*      */         {
/* 2454 */           svalue = this.escape.convertText(svalue);
/*      */         }
/* 2456 */         if (!this.toDOM)
/*      */         {
/* 2458 */           this.doc.addText(svalue);
/*      */         }
/*      */         else
/*      */         {
/* 2462 */           Node text = this.document.createTextNode(svalue);
/* 2463 */           this.currentNode.appendChild(text);
/* 2464 */           this.handler.recordValues(text, o, f, entry);
/*      */         }
/*      */       
/* 2467 */       } else if (entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA) {
/*      */         
/* 2469 */         String stringValue = value.toString();
/* 2470 */         if (this.escape != null)
/*      */         {
/* 2472 */           stringValue = this.escape.convertLines(stringValue);
/*      */         }
/* 2474 */         if (!this.toDOM)
/*      */         {
/* 2476 */           this.doc.addCDATA(stringValue);
/*      */         }
/*      */         else
/*      */         {
/* 2480 */           Node cdata = this.document.createCDATASection(stringValue);
/* 2481 */           this.currentNode.appendChild(cdata);
/* 2482 */           this.handler.recordValues(cdata, o, f, entry);
/*      */         }
/*      */       
/* 2485 */       } else if (entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT) {
/*      */         
/* 2487 */         String stringValue = value.toString();
/* 2488 */         if (this.escape != null)
/*      */         {
/* 2490 */           stringValue = this.escape.convertLines(stringValue);
/*      */         }
/* 2492 */         if (!this.toDOM)
/*      */         {
/* 2494 */           this.doc.addComment(stringValue);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 2499 */           this.currentNode.appendChild(this.document.createComment(stringValue));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2504 */         saveElement(o, value, entryFeature);
/*      */       } 
/*      */     } 
/*      */     
/* 2508 */     return (size > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveAttributeFeatureMap(EObject o, EStructuralFeature f) {
/* 2513 */     List<? extends FeatureMap.Entry> values = (List<? extends FeatureMap.Entry>)this.helper.getValue(o, f);
/* 2514 */     int size = values.size();
/* 2515 */     Set<EReference> repeats = null;
/* 2516 */     for (int i = 0; i < size; i++) {
/*      */       
/* 2518 */       FeatureMap.Entry entry = values.get(i);
/* 2519 */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 2520 */       if (entryFeature instanceof EReference) {
/*      */         
/* 2522 */         EReference referenceEntryFeature = (EReference)entryFeature;
/* 2523 */         if (referenceEntryFeature.isMany()) {
/*      */           
/* 2525 */           if (repeats == null) {
/*      */             
/* 2527 */             repeats = new HashSet<EReference>();
/*      */           }
/* 2529 */           else if (repeats.contains(referenceEntryFeature)) {
/*      */             continue;
/*      */           } 
/*      */ 
/*      */           
/* 2534 */           repeats.add(referenceEntryFeature);
/*      */           
/* 2536 */           if (referenceEntryFeature.isResolveProxies()) {
/*      */             
/* 2538 */             saveEObjectMany(o, entryFeature);
/*      */           }
/*      */           else {
/*      */             
/* 2542 */             saveIDRefMany(o, entryFeature);
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/* 2547 */         if (referenceEntryFeature.isResolveProxies()) {
/*      */           
/* 2549 */           saveEObjectSingle(o, entryFeature);
/*      */         }
/*      */         else {
/*      */           
/* 2553 */           saveIDRefSingle(o, entryFeature);
/*      */         } 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 2559 */       Object value = entry.getValue();
/* 2560 */       String svalue = getDatatypeValue(value, entryFeature, true);
/* 2561 */       if (!this.toDOM) {
/*      */         
/* 2563 */         this.doc.addAttribute(this.helper.getQName(entryFeature), svalue);
/*      */       }
/*      */       else {
/*      */         
/* 2567 */         this.helper.populateNameInfo(this.nameInfo, entryFeature);
/* 2568 */         Attr attr = this.document.createAttributeNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 2569 */         attr.setNodeValue(svalue);
/* 2570 */         ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 2571 */         this.handler.recordValues(attr, o, f, value);
/*      */       } 
/*      */       continue;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected int sameDocSingle(EObject o, EStructuralFeature f) {
/* 2579 */     InternalEObject value = (InternalEObject)this.helper.getValue(o, f);
/* 2580 */     if (value == null)
/*      */     {
/* 2582 */       return 0;
/*      */     }
/* 2584 */     if (value.eIsProxy())
/*      */     {
/* 2586 */       return 2;
/*      */     }
/*      */ 
/*      */     
/* 2590 */     Resource res = value.eResource();
/* 2591 */     return (res == this.helper.getResource() || res == null) ? 1 : 2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int sameDocMany(EObject o, EStructuralFeature f) {
/* 2597 */     InternalEList<? extends InternalEObject> values = (InternalEList<? extends InternalEObject>)this.helper.getValue(o, f);
/* 2598 */     if (values.isEmpty())
/*      */     {
/* 2600 */       return 0;
/*      */     }
/*      */     
/* 2603 */     for (Iterator<? extends InternalEObject> i = values.basicIterator(); i.hasNext(); ) {
/*      */       
/* 2605 */       InternalEObject value = i.next();
/* 2606 */       if (value.eIsProxy())
/*      */       {
/* 2608 */         return 2;
/*      */       }
/*      */ 
/*      */       
/* 2612 */       Resource resource = value.eResource();
/* 2613 */       if (resource != this.helper.getResource() && resource != null)
/*      */       {
/* 2615 */         return 2;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2620 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getContent(EObject o, EStructuralFeature[] features) {
/* 2625 */     if (this.map == null)
/*      */     {
/* 2627 */       return null;
/*      */     }
/*      */     
/* 2630 */     for (int i = 0; i < features.length; i++) {
/*      */       
/* 2632 */       EStructuralFeature feature = features[i];
/* 2633 */       XMLResource.XMLInfo info = this.map.getInfo((ENamedElement)feature);
/* 2634 */       if (info != null && info.getXMLRepresentation() == 2) {
/*      */         
/* 2636 */         Object value = this.helper.getValue(o, feature);
/* 2637 */         String svalue = getDatatypeValue(value, feature, false);
/* 2638 */         if (this.toDOM) {
/*      */           
/* 2640 */           Node text = this.document.createTextNode(svalue);
/* 2641 */           this.currentNode.appendChild(text);
/* 2642 */           this.handler.recordValues(text, o, feature, value);
/*      */         } 
/* 2644 */         return svalue;
/*      */       } 
/*      */     } 
/* 2647 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveDataTypeElementSingle(EObject o, EStructuralFeature f) {
/* 2652 */     saveElement(o, this.helper.getValue(o, f), f);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getDataTypeElementSingleSimple(EObject o, EStructuralFeature f) {
/* 2657 */     Object value = this.helper.getValue(o, f);
/* 2658 */     String svalue = getDatatypeValue(value, f, false);
/* 2659 */     if (this.toDOM) {
/*      */       
/* 2661 */       Node text = this.document.createTextNode(svalue);
/* 2662 */       this.currentNode.appendChild(text);
/* 2663 */       this.handler.recordValues(text, o, f, value);
/*      */     } 
/* 2665 */     return svalue;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElementID(EObject o) {
/* 2670 */     String id = this.helper.getID(o);
/* 2671 */     if (id != null)
/*      */     {
/* 2673 */       if (!this.toDOM) {
/*      */         
/* 2675 */         this.doc.addAttribute(this.idAttributeName, id);
/*      */       }
/*      */       else {
/*      */         
/* 2679 */         Attr attr = this.document.createAttributeNS(this.idAttributeNS, this.idAttributeName);
/* 2680 */         attr.setNodeValue(id);
/* 2681 */         ((Element)this.currentNode).setAttributeNodeNS(attr);
/* 2682 */         this.handler.recordValues(attr, o, null, o);
/*      */       } 
/*      */     }
/* 2685 */     saveFeatures(o);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class Lookup
/*      */   {
/*      */     protected static final int SHIFT = 10;
/*      */     protected static final int SIZE = 1024;
/*      */     protected static final int MASK = 1023;
/*      */     protected EClass[] classes;
/*      */     protected EStructuralFeature[][] features;
/*      */     protected int[][] featureKinds;
/*      */     protected XMLResource.XMLMap map;
/*      */     protected ExtendedMetaData extendedMetaData;
/* 2699 */     protected ArrayList<EObject> docRoots = new ArrayList<EObject>();
/*      */     protected XMLResource.ElementHandler elementHandler;
/*      */     protected FeatureClassifierPair featureClassifierPair;
/*      */     protected Map<FeatureClassifierPair, EStructuralFeature> substitutionGroupMap;
/*      */     
/*      */     protected static final class FeatureClassifierPair
/*      */     {
/*      */       EStructuralFeature eStructuralFeature;
/*      */       EClassifier eClassifier;
/*      */       
/*      */       public boolean equals(Object o) {
/* 2710 */         if (o == this)
/*      */         {
/* 2712 */           return true;
/*      */         }
/* 2714 */         if (o instanceof FeatureClassifierPair) {
/*      */           
/* 2716 */           FeatureClassifierPair featureClassifierPair = (FeatureClassifierPair)o;
/* 2717 */           return (this.eStructuralFeature == featureClassifierPair.eStructuralFeature && this.eClassifier == featureClassifierPair.eClassifier);
/*      */         } 
/*      */ 
/*      */         
/* 2721 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int hashCode() {
/* 2728 */         return this.eStructuralFeature.hashCode() ^ this.eClassifier.hashCode();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 2733 */     protected static final EStructuralFeature NULL_FEATURE = (EStructuralFeature)EcoreFactory.eINSTANCE.createEAttribute();
/*      */ 
/*      */     
/*      */     public Lookup(XMLResource.XMLMap map) {
/* 2737 */       this(map, null, null);
/*      */     }
/*      */ 
/*      */     
/*      */     public Lookup(XMLResource.XMLMap map, ExtendedMetaData extendedMetaData) {
/* 2742 */       this(map, extendedMetaData, null);
/*      */     }
/*      */ 
/*      */     
/*      */     public Lookup(XMLResource.XMLMap map, ExtendedMetaData extendedMetaData, XMLResource.ElementHandler elementHandler) {
/* 2747 */       this.map = map;
/* 2748 */       this.extendedMetaData = extendedMetaData;
/* 2749 */       this.elementHandler = elementHandler;
/* 2750 */       this.classes = new EClass[1024];
/* 2751 */       this.features = new EStructuralFeature[1024][];
/* 2752 */       this.featureKinds = new int[1024][];
/* 2753 */       if (elementHandler != null) {
/*      */         
/* 2755 */         this.featureClassifierPair = new FeatureClassifierPair();
/* 2756 */         this.substitutionGroupMap = new HashMap<FeatureClassifierPair, EStructuralFeature>();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public EClass getDocumentRoot(EPackage epackage) {
/* 2762 */       for (int i = 0; i < this.docRoots.size(); i += 2) {
/*      */         
/* 2764 */         if (this.docRoots.get(i) == epackage)
/*      */         {
/* 2766 */           return (EClass)this.docRoots.get(++i);
/*      */         }
/*      */       } 
/* 2769 */       this.docRoots.add(epackage);
/* 2770 */       EClass docRoot = this.extendedMetaData.getDocumentRoot(epackage);
/* 2771 */       this.docRoots.add(docRoot);
/* 2772 */       return docRoot;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature[] getFeatures(EClass cls) {
/* 2777 */       int index = getIndex(cls);
/* 2778 */       EClass c = this.classes[index];
/*      */       
/* 2780 */       if (c == cls)
/*      */       {
/* 2782 */         return this.features[index];
/*      */       }
/*      */       
/* 2785 */       EStructuralFeature[] featureList = listFeatures(cls);
/* 2786 */       if (c == null) {
/*      */         
/* 2788 */         this.classes[index] = cls;
/* 2789 */         this.features[index] = featureList;
/* 2790 */         this.featureKinds[index] = listKinds(featureList);
/*      */       } 
/* 2792 */       return featureList;
/*      */     }
/*      */ 
/*      */     
/*      */     public int[] getKinds(EClass cls, EStructuralFeature[] featureList) {
/* 2797 */       int index = getIndex(cls);
/* 2798 */       EClass c = this.classes[index];
/*      */       
/* 2800 */       if (c == cls)
/*      */       {
/* 2802 */         return this.featureKinds[index];
/*      */       }
/*      */       
/* 2805 */       int[] kindsList = listKinds(featureList);
/* 2806 */       if (c == null) {
/*      */         
/* 2808 */         this.classes[index] = cls;
/* 2809 */         this.features[index] = featureList;
/* 2810 */         this.featureKinds[index] = kindsList;
/*      */       } 
/* 2812 */       return kindsList;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getSubstitutionGroup(EStructuralFeature eStructuralFeature, EClassifier eClassifier) {
/* 2817 */       if (this.elementHandler == null)
/*      */       {
/* 2819 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 2823 */       this.featureClassifierPair.eStructuralFeature = eStructuralFeature;
/* 2824 */       this.featureClassifierPair.eClassifier = eClassifier;
/* 2825 */       EStructuralFeature result = this.substitutionGroupMap.get(this.featureClassifierPair);
/* 2826 */       if (result == NULL_FEATURE) {
/*      */         
/* 2828 */         result = null;
/*      */       }
/*      */       else {
/*      */         
/* 2832 */         result = this.elementHandler.getSubstitutionGroup(this.extendedMetaData, eStructuralFeature, eClassifier);
/* 2833 */         this.substitutionGroupMap.put(this.featureClassifierPair, (result == null) ? NULL_FEATURE : result);
/* 2834 */         this.featureClassifierPair = new FeatureClassifierPair();
/*      */       } 
/* 2836 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EStructuralFeature getRoot(EClassifier eClassifier) {
/* 2842 */       if (this.elementHandler == null)
/*      */       {
/* 2844 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 2848 */       this.featureClassifierPair.eStructuralFeature = NULL_FEATURE;
/* 2849 */       this.featureClassifierPair.eClassifier = eClassifier;
/* 2850 */       EStructuralFeature result = this.substitutionGroupMap.get(this.featureClassifierPair);
/* 2851 */       if (result == NULL_FEATURE) {
/*      */         
/* 2853 */         result = null;
/*      */       }
/*      */       else {
/*      */         
/* 2857 */         result = this.elementHandler.getRoot(this.extendedMetaData, eClassifier);
/* 2858 */         this.substitutionGroupMap.put(this.featureClassifierPair, (result == null) ? NULL_FEATURE : result);
/* 2859 */         this.featureClassifierPair = new FeatureClassifierPair();
/*      */       } 
/* 2861 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int getIndex(EClass cls) {
/* 2867 */       String name = cls.getInstanceClassName();
/* 2868 */       int index = 0;
/* 2869 */       if (name != null) {
/*      */         
/* 2871 */         index = name.hashCode() & 0x3FF;
/*      */       }
/*      */       else {
/*      */         
/* 2875 */         index = cls.hashCode() >> 10 & 0x3FF;
/*      */       } 
/* 2877 */       return index;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EStructuralFeature[] listFeatures(EClass cls) {
/* 2882 */       if (this.extendedMetaData != null) {
/*      */         
/* 2884 */         List<EStructuralFeature> list1 = new ArrayList<EStructuralFeature>();
/* 2885 */         list1.addAll((Collection<? extends EStructuralFeature>)cls.getEAllStructuralFeatures());
/* 2886 */         List<EStructuralFeature> orderedElements = this.extendedMetaData.getAllElements(cls);
/* 2887 */         list1.removeAll(orderedElements);
/* 2888 */         list1.addAll(orderedElements);
/* 2889 */         return list1.<EStructuralFeature>toArray(new EStructuralFeature[list1.size()]);
/*      */       } 
/*      */ 
/*      */       
/* 2893 */       List<EStructuralFeature> f = (this.map == null) ? (List<EStructuralFeature>)cls.getEAllStructuralFeatures() : this.map.getFeatures(cls);
/* 2894 */       return f.<EStructuralFeature>toArray(new EStructuralFeature[f.size()]);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int[] listKinds(EStructuralFeature[] featureList) {
/* 2900 */       int[] kinds = new int[featureList.length];
/* 2901 */       for (int i = featureList.length - 1; i >= 0; i--)
/*      */       {
/* 2903 */         kinds[i] = featureKind(featureList[i]);
/*      */       }
/*      */       
/* 2906 */       return kinds;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int featureKind(EStructuralFeature f) {
/* 2911 */       if (f.isTransient())
/*      */       {
/* 2913 */         return 0;
/*      */       }
/*      */       
/* 2916 */       boolean isMany = f.isMany();
/* 2917 */       boolean isUnsettable = f.isUnsettable();
/*      */       
/* 2919 */       if (f instanceof EReference) {
/*      */         
/* 2921 */         EReference r = (EReference)f;
/* 2922 */         if (r.isContainment())
/*      */         {
/* 2924 */           return 
/* 2925 */             isMany ? (
/* 2926 */             isUnsettable ? 11 : 7) : (
/* 2927 */             isUnsettable ? 10 : 6);
/*      */         }
/* 2929 */         EReference opposite = r.getEOpposite();
/* 2930 */         if (opposite != null && opposite.isContainment())
/*      */         {
/* 2932 */           return 0;
/*      */         }
/*      */         
/* 2935 */         if (this.map != null) {
/*      */           
/* 2937 */           XMLResource.XMLInfo xMLInfo = this.map.getInfo((ENamedElement)f);
/* 2938 */           if (xMLInfo != null && xMLInfo.getXMLRepresentation() == 0)
/*      */           {
/* 2940 */             return 
/* 2941 */               isMany ? 
/* 2942 */               16 : (
/* 2943 */               r.isUnsettable() ? 
/* 2944 */               15 : 
/* 2945 */               14);
/*      */           }
/*      */         } 
/*      */         
/* 2949 */         if (this.extendedMetaData != null)
/*      */         {
/* 2951 */           switch (this.extendedMetaData.getFeatureKind(f)) {
/*      */ 
/*      */             
/*      */             case 2:
/* 2955 */               return 
/* 2956 */                 r.isResolveProxies() ? (
/* 2957 */                 isMany ? 
/* 2958 */                 23 : 
/* 2959 */                 22) : (
/* 2960 */                 isMany ? 
/* 2961 */                 25 : 
/* 2962 */                 24);
/*      */ 
/*      */             
/*      */             case 1:
/* 2966 */               if (f == XMLTypePackage.Literals.SIMPLE_ANY_TYPE__INSTANCE_TYPE)
/*      */               {
/* 2968 */                 return 0;
/*      */               }
/*      */ 
/*      */             
/*      */             case 4:
/* 2973 */               return 
/* 2974 */                 r.isResolveProxies() ? (
/* 2975 */                 isMany ? 
/* 2976 */                 16 : (
/* 2977 */                 r.isUnsettable() ? 
/* 2978 */                 15 : 
/* 2979 */                 14)) : (
/* 2980 */                 isMany ? 
/* 2981 */                 19 : (
/* 2982 */                 r.isUnsettable() ? 
/* 2983 */                 18 : 
/* 2984 */                 17));
/*      */           } 
/*      */ 
/*      */         
/*      */         }
/* 2989 */         return 
/* 2990 */           isMany ? (
/* 2991 */           isUnsettable ? 13 : 9) : (
/* 2992 */           isUnsettable ? 12 : 8);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2997 */       EDataType d = (EDataType)f.getEType();
/* 2998 */       if (!d.isSerializable() && d != EcorePackage.Literals.EFEATURE_MAP_ENTRY)
/*      */       {
/* 3000 */         return 0;
/*      */       }
/*      */       
/* 3003 */       if (d.getInstanceClass() == FeatureMap.Entry.class)
/*      */       {
/* 3005 */         return 
/* 3006 */           (this.extendedMetaData != null && this.extendedMetaData.getFeatureKind(f) == 3) ? 
/* 3007 */           20 : 
/* 3008 */           21;
/*      */       }
/*      */       
/* 3011 */       if (this.extendedMetaData != null)
/*      */       {
/* 3013 */         switch (this.extendedMetaData.getFeatureKind(f)) {
/*      */ 
/*      */           
/*      */           case 1:
/* 3017 */             return 2;
/*      */ 
/*      */           
/*      */           case 4:
/* 3021 */             return f.isMany() ? 5 : 2;
/*      */ 
/*      */           
/*      */           case 2:
/* 3025 */             return f.isMany() ? 26 : 1;
/*      */         } 
/*      */ 
/*      */       
/*      */       }
/* 3030 */       if (isMany)
/*      */       {
/* 3032 */         return 5;
/*      */       }
/*      */       
/* 3035 */       if (isUnsettable && this.map == null)
/*      */       {
/* 3037 */         return 4;
/*      */       }
/*      */       
/* 3040 */       if (this.map == null)
/*      */       {
/* 3042 */         return 1;
/*      */       }
/*      */ 
/*      */       
/* 3046 */       XMLResource.XMLInfo info = this.map.getInfo((ENamedElement)f);
/*      */       
/* 3048 */       if (info != null && info.getXMLRepresentation() == 0)
/*      */       {
/* 3050 */         return 2;
/*      */       }
/* 3052 */       if (info != null && info.getXMLRepresentation() == 2)
/*      */       {
/* 3054 */         return 3;
/*      */       }
/*      */ 
/*      */       
/* 3058 */       if (isUnsettable) {
/* 3059 */         return 4;
/*      */       }
/* 3061 */       return 1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDatatypeValue(Object value, EStructuralFeature f, boolean isAttribute) {
/* 3070 */     if (value == null)
/*      */     {
/* 3072 */       return null;
/*      */     }
/* 3074 */     EDataType d = (EDataType)f.getEType();
/* 3075 */     EPackage ePackage = d.getEPackage();
/* 3076 */     EFactory fac = ePackage.getEFactoryInstance();
/* 3077 */     String svalue = this.helper.convertToString(fac, d, value);
/* 3078 */     if (this.escape != null)
/*      */     {
/* 3080 */       if (isAttribute) {
/*      */         
/* 3082 */         svalue = this.escape.convert(svalue);
/*      */       }
/*      */       else {
/*      */         
/* 3086 */         svalue = this.escape.convertText(svalue);
/*      */       } 
/*      */     }
/* 3089 */     return svalue;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveElement(EObject o, Object value, EStructuralFeature f) {
/* 3094 */     if (value == null) {
/*      */       
/* 3096 */       saveNil(o, f);
/*      */     }
/*      */     else {
/*      */       
/* 3100 */       String svalue = getDatatypeValue(value, f, false);
/* 3101 */       if (!this.toDOM) {
/*      */         
/* 3103 */         this.doc.saveDataValueElement(this.helper.getQName(f), svalue);
/*      */       }
/*      */       else {
/*      */         
/* 3107 */         this.helper.populateNameInfo(this.nameInfo, f);
/* 3108 */         Element elem = this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName());
/* 3109 */         Node text = this.document.createTextNode(svalue);
/* 3110 */         elem.appendChild(text);
/* 3111 */         this.currentNode.appendChild(elem);
/* 3112 */         this.handler.recordValues(elem, o, f, value);
/* 3113 */         this.handler.recordValues(text, o, f, value);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected String convertURI(String uri) {
/* 3120 */     if (this.resourceEntityHandler != null) {
/*      */       
/* 3122 */       int index = uri.indexOf('#');
/* 3123 */       if (index > 0) {
/*      */         
/* 3125 */         String baseURI = uri.substring(0, index);
/* 3126 */         String fragment = uri.substring(index + 1);
/* 3127 */         String entityName = this.resourceEntityHandler.getEntityName(baseURI);
/* 3128 */         if (entityName != null)
/*      */         {
/* 3130 */           return "&" + entityName + ";#" + ((this.escapeURI == null) ? fragment : this.escapeURI.convert(fragment));
/*      */         }
/*      */       } 
/*      */     } 
/* 3134 */     return (this.escapeURI != null) ? this.escapeURI.convert(uri) : uri;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class Escape
/*      */   {
/*      */     protected char[] value;
/*      */     protected int mappableLimit;
/*      */     protected boolean allowControlCharacters;
/*      */     protected boolean useCDATA;
/* 3144 */     protected final char[] NUL = new char[] { '&', '#', 'x', '0', ';' };
/* 3145 */     protected final char[] SOH = new char[] { '&', '#', 'x', '1', ';' };
/* 3146 */     protected final char[] STX = new char[] { '&', '#', 'x', '2', ';' };
/* 3147 */     protected final char[] ETX = new char[] { '&', '#', 'x', '3', ';' };
/* 3148 */     protected final char[] EOT = new char[] { '&', '#', 'x', '4', ';' };
/* 3149 */     protected final char[] ENQ = new char[] { '&', '#', 'x', '5', ';' };
/* 3150 */     protected final char[] ACK = new char[] { '&', '#', 'x', '6', ';' };
/* 3151 */     protected final char[] BEL = new char[] { '&', '#', 'x', '7', ';' };
/* 3152 */     protected final char[] BS = new char[] { '&', '#', 'x', '8', ';' };
/* 3153 */     protected final char[] TAB = new char[] { '&', '#', 'x', '9', ';' };
/* 3154 */     protected final char[] LF = new char[] { '&', '#', 'x', 'A', ';' };
/* 3155 */     protected final char[] VT = new char[] { '&', '#', 'x', 'B', ';' };
/* 3156 */     protected final char[] FF = new char[] { '&', '#', 'x', 'C', ';' };
/* 3157 */     protected final char[] CR = new char[] { '&', '#', 'x', 'D', ';' };
/* 3158 */     protected final char[] SO = new char[] { '&', '#', 'x', 'E', ';' };
/* 3159 */     protected final char[] SI = new char[] { '&', '#', 'x', 'F', ';' };
/* 3160 */     protected final char[] DLE = new char[] { '&', '#', 'x', '1', '0', ';' };
/* 3161 */     protected final char[] DC1 = new char[] { '&', '#', 'x', '1', '1', ';' };
/* 3162 */     protected final char[] DC2 = new char[] { '&', '#', 'x', '1', '2', ';' };
/* 3163 */     protected final char[] DC3 = new char[] { '&', '#', 'x', '1', '3', ';' };
/* 3164 */     protected final char[] DC4 = new char[] { '&', '#', 'x', '1', '4', ';' };
/* 3165 */     protected final char[] NAK = new char[] { '&', '#', 'x', '1', '5', ';' };
/* 3166 */     protected final char[] SYN = new char[] { '&', '#', 'x', '1', '6', ';' };
/* 3167 */     protected final char[] ETB = new char[] { '&', '#', 'x', '1', '7', ';' };
/* 3168 */     protected final char[] CAN = new char[] { '&', '#', 'x', '1', '8', ';' };
/* 3169 */     protected final char[] EM = new char[] { '&', '#', 'x', '1', '9', ';' };
/* 3170 */     protected final char[] SUB = new char[] { '&', '#', 'x', '1', 'A', ';' };
/* 3171 */     protected final char[] ESC = new char[] { '&', '#', 'x', '1', 'B', ';' };
/* 3172 */     protected final char[] FS = new char[] { '&', '#', 'x', '1', 'C', ';' };
/* 3173 */     protected final char[] GS = new char[] { '&', '#', 'x', '1', 'D', ';' };
/* 3174 */     protected final char[] RS = new char[] { '&', '#', 'x', '1', 'E', ';' };
/* 3175 */     protected final char[] US = new char[] { '&', '#', 'x', '1', 'F', ';' };
/*      */ 
/*      */ 
/*      */     
/* 3179 */     protected final char[][] CONTROL_CHARACTERS = new char[][] { 
/* 3180 */         this.NUL, 
/* 3181 */         this.SOH, 
/* 3182 */         this.STX, 
/* 3183 */         this.ETX, 
/* 3184 */         this.EOT, 
/* 3185 */         this.ENQ, 
/* 3186 */         this.ACK, 
/* 3187 */         this.BEL, 
/* 3188 */         this.BS, 
/* 3189 */         this.TAB, 
/* 3190 */         this.LF, 
/* 3191 */         this.VT, 
/* 3192 */         this.FF, 
/* 3193 */         this.CR, 
/* 3194 */         this.SO, 
/* 3195 */         this.SI, 
/* 3196 */         this.DLE, 
/* 3197 */         this.DC1, 
/* 3198 */         this.DC2, 
/* 3199 */         this.DC3, 
/* 3200 */         this.DC4, 
/* 3201 */         this.NAK, 
/* 3202 */         this.SYN, 
/* 3203 */         this.ETB, 
/* 3204 */         this.CAN, 
/* 3205 */         this.EM, 
/* 3206 */         this.SUB, 
/* 3207 */         this.ESC, 
/* 3208 */         this.FS, 
/* 3209 */         this.GS, 
/* 3210 */         this.RS, 
/* 3211 */         this.US };
/*      */ 
/*      */     
/* 3214 */     protected final char[] AMP = new char[] { '&', 'a', 'm', 'p', ';' };
/* 3215 */     protected final char[] LESS = new char[] { '&', 'l', 't', ';' };
/* 3216 */     protected final char[] GREATER = new char[] { '&', 'g', 't', ';' };
/* 3217 */     protected final char[] QUOTE = new char[] { '&', 'q', 'u', 'o', 't', ';' };
/* 3218 */     protected final char[] LINE_FEED = System.getProperty("line.separator").toCharArray();
/*      */ 
/*      */     
/*      */     public Escape() {
/* 3222 */       this.value = new char[100];
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMappingLimit(int mappingLimit) {
/* 3227 */       this.mappableLimit = mappingLimit;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setAllowControlCharacters(boolean allowControlCharacters) {
/* 3232 */       this.allowControlCharacters = allowControlCharacters;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setUseCDATA(boolean useCDATA) {
/* 3237 */       this.useCDATA = useCDATA;
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
/*      */     
/*      */     public String convert(String input) {
/* 3251 */       boolean changed = false;
/* 3252 */       int inputLength = input.length();
/* 3253 */       grow(inputLength);
/* 3254 */       int outputPos = 0;
/* 3255 */       int inputPos = 0;
/* 3256 */       char ch = Character.MIN_VALUE;
/* 3257 */       while (inputLength-- > 0) {
/*      */         
/* 3259 */         ch = input.charAt(inputPos++);
/* 3260 */         switch (ch) {
/*      */ 
/*      */           
/*      */           case '\001':
/*      */           case '\002':
/*      */           case '\003':
/*      */           case '\004':
/*      */           case '\005':
/*      */           case '\006':
/*      */           case '\007':
/*      */           case '\b':
/*      */           case '\013':
/*      */           case '\f':
/*      */           case '\016':
/*      */           case '\017':
/*      */           case '\020':
/*      */           case '\021':
/*      */           case '\022':
/*      */           case '\023':
/*      */           case '\024':
/*      */           case '\025':
/*      */           case '\026':
/*      */           case '\027':
/*      */           case '\030':
/*      */           case '\031':
/*      */           case '\032':
/*      */           case '\033':
/*      */           case '\034':
/*      */           case '\035':
/*      */           case '\036':
/*      */           case '\037':
/* 3291 */             if (this.allowControlCharacters) {
/*      */               
/* 3293 */               outputPos = replaceChars(outputPos, this.CONTROL_CHARACTERS[ch], inputLength);
/* 3294 */               changed = true;
/*      */               
/*      */               continue;
/*      */             } 
/* 3298 */             throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case '&':
/* 3304 */             outputPos = replaceChars(outputPos, this.AMP, inputLength);
/* 3305 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '<':
/* 3310 */             outputPos = replaceChars(outputPos, this.LESS, inputLength);
/* 3311 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '"':
/* 3316 */             outputPos = replaceChars(outputPos, this.QUOTE, inputLength);
/* 3317 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '\n':
/* 3322 */             outputPos = replaceChars(outputPos, this.LF, inputLength);
/* 3323 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '\r':
/* 3328 */             outputPos = replaceChars(outputPos, this.CR, inputLength);
/* 3329 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '\t':
/* 3334 */             outputPos = replaceChars(outputPos, this.TAB, inputLength);
/* 3335 */             changed = true;
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/* 3340 */         if (!DataValue.XMLChar.isValid(ch)) {
/*      */           
/* 3342 */           if (DataValue.XMLChar.isHighSurrogate(ch)) {
/*      */             
/* 3344 */             char high = ch;
/* 3345 */             if (inputLength-- > 0) {
/*      */               
/* 3347 */               ch = input.charAt(inputPos++);
/* 3348 */               if (DataValue.XMLChar.isLowSurrogate(ch)) {
/*      */                 
/* 3350 */                 if (this.mappableLimit == 1114111) {
/*      */ 
/*      */                   
/* 3353 */                   this.value[outputPos++] = high;
/* 3354 */                   this.value[outputPos++] = ch;
/*      */                   
/*      */                   continue;
/*      */                 } 
/*      */                 
/* 3359 */                 outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(DataValue.XMLChar.supplemental(high, ch)) + ";").toCharArray(), inputLength);
/* 3360 */                 changed = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/* 3364 */               throw new RuntimeException("An invalid low surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */             } 
/*      */ 
/*      */             
/* 3368 */             throw new RuntimeException("An unpaired high surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 3373 */           throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3379 */         if (ch <= this.mappableLimit) {
/*      */           
/* 3381 */           this.value[outputPos++] = ch;
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 3386 */         outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(ch) + ";").toCharArray(), inputLength);
/* 3387 */         changed = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3394 */       return changed ? new String(this.value, 0, outputPos) : input;
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
/*      */     public String convertText(String input) {
/* 3407 */       boolean changed = false;
/* 3408 */       boolean cdataCloseBracket = false;
/* 3409 */       int inputLength = input.length();
/* 3410 */       grow(inputLength);
/* 3411 */       int outputPos = 0;
/* 3412 */       int inputPos = 0;
/*      */       
/* 3414 */       while (inputLength-- > 0) {
/*      */         
/* 3416 */         char ch = input.charAt(inputPos++);
/* 3417 */         switch (ch) {
/*      */ 
/*      */           
/*      */           case '\001':
/*      */           case '\002':
/*      */           case '\003':
/*      */           case '\004':
/*      */           case '\005':
/*      */           case '\006':
/*      */           case '\007':
/*      */           case '\b':
/*      */           case '\013':
/*      */           case '\f':
/*      */           case '\016':
/*      */           case '\017':
/*      */           case '\020':
/*      */           case '\021':
/*      */           case '\022':
/*      */           case '\023':
/*      */           case '\024':
/*      */           case '\025':
/*      */           case '\026':
/*      */           case '\027':
/*      */           case '\030':
/*      */           case '\031':
/*      */           case '\032':
/*      */           case '\033':
/*      */           case '\034':
/*      */           case '\035':
/*      */           case '\036':
/*      */           case '\037':
/* 3448 */             if (this.allowControlCharacters) {
/*      */               
/* 3450 */               outputPos = replaceChars(outputPos, this.CONTROL_CHARACTERS[ch], inputLength);
/* 3451 */               changed = true;
/*      */               
/*      */               continue;
/*      */             } 
/* 3455 */             throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case '&':
/* 3461 */             outputPos = replaceChars(outputPos, this.AMP, inputLength);
/* 3462 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '<':
/* 3467 */             outputPos = replaceChars(outputPos, this.LESS, inputLength);
/* 3468 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '"':
/* 3473 */             outputPos = replaceChars(outputPos, this.QUOTE, inputLength);
/* 3474 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '\n':
/* 3479 */             outputPos = replaceChars(outputPos, this.LINE_FEED, inputLength);
/* 3480 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '\r':
/* 3485 */             outputPos = replaceChars(outputPos, this.CR, inputLength);
/* 3486 */             changed = true;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '>':
/* 3491 */             if (inputPos >= 3 && input.charAt(inputPos - 2) == ']' && input.charAt(inputPos - 3) == ']') {
/*      */               
/* 3493 */               outputPos = replaceChars(outputPos, this.GREATER, inputLength);
/* 3494 */               cdataCloseBracket = true;
/* 3495 */               changed = true;
/*      */               continue;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3503 */         if (!DataValue.XMLChar.isValid(ch)) {
/*      */           
/* 3505 */           if (DataValue.XMLChar.isHighSurrogate(ch)) {
/*      */             
/* 3507 */             char high = ch;
/* 3508 */             if (inputLength-- > 0) {
/*      */               
/* 3510 */               ch = input.charAt(inputPos++);
/* 3511 */               if (DataValue.XMLChar.isLowSurrogate(ch)) {
/*      */                 
/* 3513 */                 if (this.mappableLimit == 1114111) {
/*      */ 
/*      */                   
/* 3516 */                   this.value[outputPos++] = high;
/* 3517 */                   this.value[outputPos++] = ch;
/*      */                   
/*      */                   continue;
/*      */                 } 
/*      */                 
/* 3522 */                 outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(DataValue.XMLChar.supplemental(high, ch)) + ";").toCharArray(), inputLength);
/* 3523 */                 changed = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/* 3527 */               throw new RuntimeException("An invalid low surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */             } 
/*      */ 
/*      */             
/* 3531 */             throw new RuntimeException("An unpaired high surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 3536 */           throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3542 */         if (ch <= this.mappableLimit) {
/*      */           
/* 3544 */           this.value[outputPos++] = ch;
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 3549 */         outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(ch) + ";").toCharArray(), inputLength);
/* 3550 */         changed = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3557 */       return changed ? ((!this.useCDATA || cdataCloseBracket) ? new String(this.value, 0, outputPos) : ("<![CDATA[" + input + "]]>")) : input;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String convertLines(String input) {
/* 3566 */       boolean changed = false;
/* 3567 */       int inputLength = input.length();
/* 3568 */       grow(inputLength);
/* 3569 */       int outputPos = 0;
/* 3570 */       int inputPos = 0;
/*      */       
/* 3572 */       while (inputLength-- > 0) {
/*      */         
/* 3574 */         char ch = input.charAt(inputPos++);
/* 3575 */         switch (ch) {
/*      */ 
/*      */           
/*      */           case '\n':
/* 3579 */             outputPos = replaceChars(outputPos, this.LINE_FEED, inputLength);
/* 3580 */             changed = true;
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/* 3585 */         this.value[outputPos++] = ch;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3590 */       return changed ? new String(this.value, 0, outputPos) : input;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int replaceChars(int pos, char[] replacement, int inputLength) {
/* 3595 */       int rlen = replacement.length;
/* 3596 */       int newPos = pos + rlen;
/* 3597 */       grow(newPos + inputLength);
/* 3598 */       System.arraycopy(replacement, 0, this.value, pos, rlen);
/* 3599 */       return newPos;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     protected int replace(int pos, char[] replacement, int inputLength) {
/* 3609 */       int rlen = replacement.length;
/* 3610 */       int newPos = pos + rlen;
/* 3611 */       grow(newPos + inputLength);
/* 3612 */       System.arraycopy(this.value, pos + 1, this.value, newPos, inputLength);
/* 3613 */       System.arraycopy(replacement, 0, this.value, pos, rlen);
/* 3614 */       return newPos;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void grow(int newSize) {
/* 3619 */       int vlen = this.value.length;
/* 3620 */       if (vlen < newSize) {
/*      */         
/* 3622 */         char[] newValue = new char[newSize + newSize / 2];
/* 3623 */         System.arraycopy(this.value, 0, newValue, 0, vlen);
/* 3624 */         this.value = newValue;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class XMLTypeInfoImpl
/*      */     implements XMLSave.XMLTypeInfo
/*      */   {
/*      */     public boolean shouldSaveType(EClass objectType, EClassifier featureType, EStructuralFeature feature) {
/* 3637 */       return (objectType != featureType && objectType != XMLSaveImpl.this.anyType);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature) {
/* 3642 */       return (objectType != featureType && (featureType.isAbstract() || feature.getEGenericType().getETypeParameter() != null));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLSaveImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */