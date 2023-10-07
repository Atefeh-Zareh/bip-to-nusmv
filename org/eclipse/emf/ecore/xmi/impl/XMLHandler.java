/*      */ package org.eclipse.emf.ecore.xmi.impl;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.TreeIterator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*      */ import org.eclipse.emf.ecore.resource.URIConverter;
/*      */ import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ import org.eclipse.emf.ecore.xmi.ClassNotFoundException;
/*      */ import org.eclipse.emf.ecore.xmi.EcoreBuilder;
/*      */ import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
/*      */ import org.eclipse.emf.ecore.xmi.IllegalValueException;
/*      */ import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
/*      */ import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
/*      */ import org.eclipse.emf.ecore.xmi.XMIException;
/*      */ import org.eclipse.emf.ecore.xmi.XMIPlugin;
/*      */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*      */ import org.eclipse.emf.ecore.xmi.XMLOptions;
/*      */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*      */ import org.eclipse.emf.ecore.xmi.util.DefaultEcoreBuilder;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ public abstract class XMLHandler extends DefaultHandler implements XMLDefaultHandler {
/*      */   protected static final String ERROR_TYPE = "error";
/*      */   protected static final String OBJECT_TYPE = "object";
/*      */   protected static final String UNKNOWN_FEATURE_TYPE = "unknownFeature";
/*      */   protected static final String DOCUMENT_ROOT_TYPE = "documentRoot";
/*      */   protected static final String TYPE_ATTRIB = "xsi:type";
/*      */   protected static final String NIL_ATTRIB = "xsi:nil";
/*      */   protected static final String SCHEMA_LOCATION_ATTRIB = "xsi:schemaLocation";
/*      */   protected static final String NO_NAMESPACE_SCHEMA_LOCATION_ATTRIB = "xsi:noNamespaceSchemaLocation";
/*      */   protected static final boolean DEBUG_DEMANDED_PACKAGES = false;
/*      */   protected XMLResource xmlResource;
/*      */   protected XMLHelper helper;
/*      */   protected MyStack<String> elements;
/*      */   protected MyEObjectStack objects;
/*      */   protected MyStack<Object> types;
/*      */   protected MyStack<FeatureMap> mixedTargets;
/*      */   protected Map<String, EFactory> prefixesToFactories;
/*      */   protected Map<String, URI> urisToLocations;
/*      */   protected Map<String, URI> externalURIToLocations;
/*      */   protected boolean processSchemaLocations;
/*      */   protected InternalEList<EObject> extent;
/*      */   protected List<EObject> deferredExtent;
/*      */   protected ResourceSet resourceSet;
/*      */   protected EPackage.Registry packageRegistry;
/*      */   protected URI resourceURI;
/*      */   protected boolean resolve;
/*      */   protected boolean oldStyleProxyURIs;
/*      */   protected boolean disableNotify;
/*      */   protected StringBuffer text;
/*      */   protected boolean isIDREF;
/*      */   protected boolean isSimpleFeature;
/*      */   protected List<InternalEObject> sameDocumentProxies;
/*      */   protected List<SingleReference> forwardSingleReferences;
/*      */   protected List<ManyReference> forwardManyReferences;
/*      */   protected Object[] identifiers;
/*      */   protected int[] positions;
/*      */   protected static final int ARRAY_SIZE = 64;
/*      */   protected static final int REFERENCE_THRESHOLD = 5;
/*      */   protected int capacity;
/*      */   protected Set<String> notFeatures;
/*      */   protected String idAttribute;
/*      */   protected String hrefAttribute;
/*      */   protected XMLResource.XMLMap xmlMap;
/*      */   protected ExtendedMetaData extendedMetaData;
/*      */   protected EClass anyType;
/*      */   protected EClass anySimpleType;
/*      */   protected boolean recordUnknownFeature;
/*      */   protected boolean useNewMethods;
/*      */   protected boolean recordAnyTypeNSDecls;
/*      */   protected Map<EObject, AnyType> eObjectToExtensionMap;
/*      */   protected EStructuralFeature contextFeature;
/*      */   
/*      */   protected static class MyStack<E> extends BasicEList<E> {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     public final E peek() {
/*  117 */       return (this.size == 0) ? null : (E)this.data[this.size - 1];
/*      */     }
/*      */ 
/*      */     
/*      */     public final void push(E o) {
/*  122 */       grow(this.size + 1);
/*  123 */       this.data[this.size++] = o;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final E pop() {
/*  129 */       return (this.size == 0) ? null : (E)this.data[--this.size];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MyEObjectStack
/*      */     extends MyStack<EObject>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     
/*      */     protected EObject[] eObjectData;
/*      */ 
/*      */ 
/*      */     
/*      */     protected final Object[] newData(int capacity) {
/*  147 */       return (Object[])(this.eObjectData = new EObject[capacity]);
/*      */     }
/*      */ 
/*      */     
/*      */     public final EObject peekEObject() {
/*  152 */       return (this.size == 0) ? null : this.eObjectData[this.size - 1];
/*      */     }
/*      */ 
/*      */     
/*      */     public final EObject popEObject() {
/*  157 */       return (this.size == 0) ? null : this.eObjectData[--this.size];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear() {
/*  163 */       this.eObjectData = null;
/*  164 */       super.clear();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class SingleReference
/*      */   {
/*      */     private EObject object;
/*      */ 
/*      */     
/*      */     private EStructuralFeature feature;
/*      */ 
/*      */     
/*      */     private Object value;
/*      */ 
/*      */     
/*      */     private int position;
/*      */ 
/*      */     
/*      */     private int lineNumber;
/*      */     
/*      */     private int columnNumber;
/*      */ 
/*      */     
/*      */     public SingleReference(EObject object, EStructuralFeature feature, Object value, int position, int lineNumber, int columnNumber) {
/*  190 */       this.object = object;
/*  191 */       this.feature = feature;
/*  192 */       this.value = value;
/*  193 */       this.position = position;
/*  194 */       this.lineNumber = lineNumber;
/*  195 */       this.columnNumber = columnNumber;
/*      */     }
/*      */ 
/*      */     
/*      */     public EObject getObject() {
/*  200 */       return this.object;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getFeature() {
/*  205 */       return this.feature;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue() {
/*  210 */       return this.value;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getPosition() {
/*  215 */       return this.position;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLineNumber() {
/*  220 */       return this.lineNumber;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getColumnNumber() {
/*  225 */       return this.columnNumber;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class ManyReference
/*      */     implements XMLHelper.ManyReference
/*      */   {
/*      */     private EObject object;
/*      */     
/*      */     private EStructuralFeature feature;
/*      */     
/*      */     private Object[] values;
/*      */     
/*      */     private int[] positions;
/*      */     
/*      */     private int lineNumber;
/*      */     private int columnNumber;
/*      */     
/*      */     public ManyReference(EObject object, EStructuralFeature feature, Object[] values, int[] positions, int lineNumber, int columnNumber) {
/*  245 */       this.object = object;
/*  246 */       this.feature = feature;
/*  247 */       this.values = values;
/*  248 */       this.positions = positions;
/*  249 */       this.lineNumber = lineNumber;
/*  250 */       this.columnNumber = columnNumber;
/*      */     }
/*      */ 
/*      */     
/*      */     public EObject getObject() {
/*  255 */       return this.object;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getFeature() {
/*  260 */       return this.feature;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] getValues() {
/*  265 */       return this.values;
/*      */     }
/*      */ 
/*      */     
/*      */     public int[] getPositions() {
/*  270 */       return this.positions;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLineNumber() {
/*  275 */       return this.lineNumber;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getColumnNumber() {
/*  280 */       return this.columnNumber;
/*      */     }
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
/*  325 */   protected EPackage xmlSchemaTypePackage = (EPackage)XMLTypePackage.eINSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean deferIDREFResolution;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean processAnyXML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EcoreBuilder ecoreBuilder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isRoot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Locator locator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Attributes attribs;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map<EStructuralFeature, Integer> featuresToKinds;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean useConfigurationCache;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean needsPushContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLResource.ResourceEntityHandler resourceEntityHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLResource.URIHandler uriHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject documentRoot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean usedNullNamespacePackage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isNamespaceAware;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean suppressDocumentRoot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean laxWildcardProcessing;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Map<EClassFeatureNamePair, EStructuralFeature> eClassFeatureNamePairToEStructuralFeatureMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isOptionUseXMLNameToFeatureSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClassFeatureNamePair eClassFeatureNamePair;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setExtendedMetaDataOption(Object extendedMetaDataOption) {
/*  508 */     if (extendedMetaDataOption instanceof Boolean) {
/*      */       
/*  510 */       if (extendedMetaDataOption.equals(Boolean.TRUE))
/*      */       {
/*  512 */         this.extendedMetaData = 
/*  513 */           (this.resourceSet == null) ? 
/*  514 */           ExtendedMetaData.INSTANCE : 
/*  515 */           (ExtendedMetaData)new BasicExtendedMetaData(this.resourceSet.getPackageRegistry());
/*  516 */         if (this.xmlResource != null)
/*      */         {
/*  518 */           this.xmlResource.getDefaultSaveOptions().put("EXTENDED_META_DATA", this.extendedMetaData);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  523 */         this.extendedMetaData = null;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  528 */       this.extendedMetaData = (ExtendedMetaData)extendedMetaDataOption;
/*      */     } 
/*      */     
/*  531 */     this.helper.setExtendedMetaData(this.extendedMetaData);
/*      */   }
/*      */ 
/*      */   
/*      */   public void prepare(XMLResource resource, XMLHelper helper, Map<?, ?> options) {
/*  536 */     this.xmlResource = resource;
/*  537 */     this.helper = helper;
/*  538 */     this.resourceSet = this.xmlResource.getResourceSet();
/*  539 */     this.packageRegistry = (this.resourceSet == null) ? EPackage.Registry.INSTANCE : this.resourceSet.getPackageRegistry();
/*  540 */     this.resourceURI = this.xmlResource.getURI();
/*  541 */     this.extent = (InternalEList<EObject>)this.xmlResource.getContents();
/*  542 */     if (Boolean.TRUE.equals(options.get("DEFER_ATTACHMENT")))
/*      */     {
/*  544 */       this.deferredExtent = new ArrayList<EObject>();
/*      */     }
/*  546 */     this.resolve = (this.resourceURI != null && this.resourceURI.isHierarchical() && !this.resourceURI.isRelative());
/*  547 */     this.eObjectToExtensionMap = this.xmlResource.getEObjectToExtensionMap();
/*  548 */     this.eObjectToExtensionMap.clear();
/*  549 */     setExtendedMetaDataOption(options.get("EXTENDED_META_DATA"));
/*  550 */     helper.setOptions(options);
/*  551 */     if (this.extendedMetaData != null) {
/*      */       
/*  553 */       if (this.ecoreBuilder != null)
/*      */       {
/*  555 */         this.ecoreBuilder.setExtendedMetaData(this.extendedMetaData);
/*      */       }
/*  557 */       AnyType anyType = XMLTypeFactory.eINSTANCE.createAnyType();
/*  558 */       this.mixedTargets.push(anyType.getMixed());
/*  559 */       this.text = new StringBuffer();
/*      */     } 
/*      */ 
/*      */     
/*  563 */     Map<EClassFeatureNamePair, EStructuralFeature> newEClassFeatureNamePairToEStructuralFeatureMap = 
/*  564 */       (Map<EClassFeatureNamePair, EStructuralFeature>)options.get("USE_XML_NAME_TO_FEATURE_MAP");
/*  565 */     this.eClassFeatureNamePairToEStructuralFeatureMap = 
/*  566 */       newEClassFeatureNamePairToEStructuralFeatureMap;
/*  567 */     if (this.eClassFeatureNamePairToEStructuralFeatureMap == null) {
/*      */       
/*  569 */       this.eClassFeatureNamePairToEStructuralFeatureMap = new HashMap<EClassFeatureNamePair, EStructuralFeature>();
/*  570 */       this.isOptionUseXMLNameToFeatureSet = false;
/*      */     }
/*      */     else {
/*      */       
/*  574 */       this.isOptionUseXMLNameToFeatureSet = true;
/*  575 */       if (helper instanceof XMLHelperImpl && this.featuresToKinds != null)
/*      */       {
/*  577 */         ((XMLHelperImpl)helper).featuresToKinds = this.featuresToKinds;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  584 */     this.uriHandler = (XMLResource.URIHandler)options.get("URI_HANDLER");
/*  585 */     this.resourceEntityHandler = (XMLResource.ResourceEntityHandler)options.get("RESOURCE_ENTITY_HANDLER");
/*  586 */     if (this.resourceEntityHandler != null) {
/*      */       
/*  588 */       this.resourceEntityHandler.reset();
/*  589 */       if (this.uriHandler == null && this.resourceEntityHandler instanceof XMLResource.URIHandler) {
/*      */         
/*  591 */         this.uriHandler = (XMLResource.URIHandler)this.resourceEntityHandler;
/*  592 */         this.uriHandler.setBaseURI(this.resourceURI);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void reset() {
/*  599 */     this.xmlResource = null;
/*  600 */     this.extendedMetaData = null;
/*      */     
/*  602 */     this.eClassFeatureNamePair.eClass = null;
/*  603 */     this.eClassFeatureNamePairToEStructuralFeatureMap = null;
/*  604 */     if (this.isOptionUseXMLNameToFeatureSet && this.helper instanceof XMLHelperImpl) {
/*      */       
/*  606 */       this.featuresToKinds = ((XMLHelperImpl)this.helper).featuresToKinds;
/*      */     }
/*      */     else {
/*      */       
/*  610 */       this.featuresToKinds = null;
/*      */     } 
/*      */     
/*  613 */     if (this.ecoreBuilder != null)
/*      */     {
/*  615 */       this.ecoreBuilder.setExtendedMetaData(null);
/*      */     }
/*  617 */     this.helper = null;
/*  618 */     this.elements.clear();
/*  619 */     this.objects.clear();
/*  620 */     this.mixedTargets.clear();
/*  621 */     this.contextFeature = null;
/*  622 */     this.eObjectToExtensionMap = null;
/*      */ 
/*      */     
/*  625 */     this.externalURIToLocations = null;
/*      */     
/*  627 */     this.types.clear();
/*  628 */     this.prefixesToFactories.clear();
/*  629 */     this.forwardSingleReferences.clear();
/*  630 */     this.forwardManyReferences.clear();
/*  631 */     this.sameDocumentProxies.clear(); int i;
/*  632 */     for (i = 0; i < this.identifiers.length; i++)
/*      */     {
/*  634 */       this.identifiers[i] = null;
/*      */     }
/*  636 */     for (i = 0; i < this.positions.length; i++)
/*      */     {
/*  638 */       this.positions[i] = 0;
/*      */     }
/*  640 */     this.capacity = 64;
/*  641 */     this.resourceSet = null;
/*  642 */     this.packageRegistry = null;
/*  643 */     this.resourceURI = null;
/*  644 */     this.extent = null;
/*  645 */     this.deferredExtent = null;
/*  646 */     this.attribs = null;
/*  647 */     this.locator = null;
/*  648 */     this.urisToLocations = null;
/*  649 */     this.resourceEntityHandler = null;
/*  650 */     this.uriHandler = null;
/*  651 */     this.documentRoot = null;
/*  652 */     this.usedNullNamespacePackage = false;
/*  653 */     this.isNamespaceAware = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void skippedEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMIException toXMIException(SAXParseException e) {
/*  674 */     XMIException xmiException = 
/*  675 */       new XMIException(
/*  676 */         (e.getException() == null) ? e : e.getException(), 
/*  677 */         (e.getSystemId() == null) ? getLocation() : e.getSystemId(), 
/*  678 */         e.getLineNumber(), 
/*  679 */         e.getColumnNumber());
/*  680 */     return xmiException;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void warning(SAXParseException e) throws SAXException {
/*  686 */     warning(toXMIException(e));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void error(SAXParseException e) throws SAXException {
/*  692 */     error(toXMIException(e));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void fatalError(SAXParseException e) throws SAXException {
/*  698 */     fatalError(toXMIException(e));
/*  699 */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/*  705 */     setLocator(locator);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  711 */     setAttributes(attributes);
/*  712 */     startElement(uri, localName, qName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startEntity(String name) {
/*  721 */     if (this.resourceEntityHandler != null)
/*      */     {
/*  723 */       this.text = new StringBuffer();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void endEntity(String name) {
/*  729 */     if (this.resourceEntityHandler != null)
/*      */     {
/*  731 */       this.resourceEntityHandler.handleEntity(name, this.text.toString());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void comment(char[] ch, int start, int length) {
/*  737 */     if (this.mixedTargets.peek() != null) {
/*      */       
/*  739 */       if (this.text != null)
/*      */       {
/*  741 */         handleMixedText();
/*      */       }
/*      */       
/*  744 */       handleComment(new String(ch, start, length));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void startCDATA() {
/*  750 */     if (this.mixedTargets.peek() != null) {
/*      */       
/*  752 */       if (this.text != null)
/*      */       {
/*  754 */         handleMixedText();
/*      */       }
/*  756 */       this.text = new StringBuffer();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void endCDATA() {
/*  762 */     if (this.mixedTargets.peek() != null && this.text != null)
/*      */     {
/*  764 */       handleCDATA();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) {
/*  773 */     this.xmlResource.setDoctypeInfo(publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/*      */     try {
/*  801 */       Map<Object, Object> options = new HashMap<Object, Object>();
/*  802 */       options.put("publicId", publicId);
/*  803 */       options.put("systemId", systemId);
/*  804 */       options.put("baseLocation", (this.resourceURI == null) ? null : this.resourceURI.toString());
/*  805 */       URI uri = URI.createURI(systemId);
/*  806 */       if (this.resolve && uri.isRelative() && uri.hasRelativePath())
/*      */       {
/*  808 */         uri = this.helper.resolve(uri, this.resourceURI);
/*      */       }
/*  810 */       InputStream inputStream = getURIConverter().createInputStream(uri, options);
/*  811 */       InputSource result = new InputSource(inputStream);
/*  812 */       result.setPublicId(publicId);
/*  813 */       result.setSystemId(systemId);
/*  814 */       return result;
/*      */     }
/*  816 */     catch (IOException exception) {
/*      */       
/*  818 */       throw new SAXException(exception);
/*      */     } 
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
/*      */   @Deprecated
/*      */   protected void handleNamespaceAttribs() {
/*  839 */     for (int i = 0, size = this.attribs.getLength(); i < size; i++) {
/*      */       
/*  841 */       String attrib = this.attribs.getQName(i);
/*  842 */       if (attrib.startsWith("xmlns")) {
/*      */         
/*  844 */         handleXMLNSAttribute(attrib, this.attribs.getValue(i));
/*      */       }
/*  846 */       else if ("xsi:schemaLocation".equals(attrib)) {
/*      */         
/*  848 */         handleXSISchemaLocation(this.attribs.getValue(i));
/*      */       }
/*  850 */       else if ("xsi:noNamespaceSchemaLocation".equals(attrib)) {
/*      */         
/*  852 */         handleXSINoNamespaceSchemaLocation(this.attribs.getValue(i));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleSchemaLocation() {
/*  859 */     String xsiSchemLocation = this.attribs.getValue("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation");
/*  860 */     if (xsiSchemLocation != null)
/*      */     {
/*  862 */       handleXSISchemaLocation(xsiSchemLocation);
/*      */     }
/*      */     
/*  865 */     String xsiNoNamespaceSchemLocation = this.attribs.getValue("http://www.w3.org/2001/XMLSchema-instance", "noNamespaceSchemaLocation");
/*  866 */     if (xsiNoNamespaceSchemLocation != null)
/*      */     {
/*  868 */       handleXSINoNamespaceSchemaLocation(xsiNoNamespaceSchemLocation);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isNull() {
/*  877 */     String value = this.isNamespaceAware ? this.attribs.getValue("http://www.w3.org/2001/XMLSchema-instance", "nil") : this.attribs.getValue("xsi:nil");
/*  878 */     if (value != null) {
/*      */       
/*      */       try {
/*      */         
/*  882 */         return XMLTypeFactory.eINSTANCE.createBoolean(value);
/*      */       }
/*  884 */       catch (RuntimeException exception) {
/*      */         
/*  886 */         error(new XMIException(exception));
/*      */       } 
/*      */     }
/*  889 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object setAttributes(Object attributes) {
/*  897 */     Object oldAttribs = this.attribs;
/*  898 */     this.attribs = (Attributes)attributes;
/*  899 */     return oldAttribs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setLocator(Object locator) {
/*  908 */     this.locator = (Locator)locator;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void recordHeaderInformation() {
/*  913 */     if (this.locator != null) {
/*      */       
/*  915 */       Class<?> locatorClass = this.locator.getClass();
/*      */       
/*      */       try {
/*  918 */         Method encodingMethod = locatorClass.getMethod("getEncoding", new Class[0]);
/*  919 */         String encoding = (String)encodingMethod.invoke(this.locator, new Object[0]);
/*  920 */         if (encoding != null)
/*      */         {
/*  922 */           this.xmlResource.setEncoding(encoding);
/*      */         }
/*      */         
/*  925 */         Method versionMethod = locatorClass.getMethod("getXMLVersion", new Class[0]);
/*  926 */         String version = (String)versionMethod.invoke(this.locator, new Object[0]);
/*  927 */         if (version != null)
/*      */         {
/*  929 */           this.xmlResource.setXMLVersion(version);
/*      */         }
/*      */       }
/*  932 */       catch (NoSuchMethodException noSuchMethodException) {
/*      */ 
/*      */       
/*      */       }
/*  936 */       catch (IllegalAccessException illegalAccessException) {
/*      */ 
/*      */       
/*      */       }
/*  940 */       catch (InvocationTargetException invocationTargetException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() {
/*  950 */     this.isRoot = true;
/*  951 */     this.helper.pushContext();
/*  952 */     this.needsPushContext = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String name) {
/*  961 */     if (this.needsPushContext)
/*      */     {
/*  963 */       this.helper.pushContext();
/*      */     }
/*  965 */     this.needsPushContext = true;
/*  966 */     if (this.text != null && this.text.length() > 0)
/*      */     {
/*  968 */       if (this.mixedTargets.peek() != null) {
/*      */         
/*  970 */         handleMixedText();
/*      */       }
/*      */       else {
/*      */         
/*  974 */         this.text = null;
/*      */       } 
/*      */     }
/*      */     
/*  978 */     this.elements.push(name);
/*  979 */     String prefix = "";
/*      */     
/*  981 */     if (this.useNewMethods) {
/*      */       
/*  983 */       if (this.isRoot)
/*      */       {
/*  985 */         handleSchemaLocation();
/*      */       }
/*  987 */       prefix = this.helper.getPrefix((uri.length() == 0) ? null : uri);
/*  988 */       prefix = (prefix == null) ? "" : prefix;
/*      */     }
/*      */     else {
/*      */       
/*  992 */       handleNamespaceAttribs();
/*  993 */       int index = name.indexOf(':', 0);
/*  994 */       localName = name;
/*  995 */       if (index != -1) {
/*      */         
/*  997 */         prefix = name.substring(0, index);
/*  998 */         localName = name.substring(index + 1);
/*      */       } 
/*      */     } 
/* 1001 */     processElement(name, prefix, localName);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void processElement(String name, String prefix, String localName) {
/* 1006 */     if (this.isRoot) {
/*      */       
/* 1008 */       this.isRoot = false;
/* 1009 */       recordHeaderInformation();
/*      */     } 
/* 1011 */     if (isError()) {
/*      */       
/* 1013 */       this.types.push("error");
/*      */ 
/*      */     
/*      */     }
/* 1017 */     else if (this.objects.isEmpty()) {
/*      */       
/* 1019 */       createTopObject(prefix, localName);
/*      */     }
/*      */     else {
/*      */       
/* 1023 */       handleFeature(prefix, localName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleForwardReferences() {
/* 1030 */     handleForwardReferences(false);
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
/*      */   protected void handleForwardReferences(boolean isEndDocument) {
/* 1046 */     for (Iterator<InternalEObject> iterator1 = this.sameDocumentProxies.iterator(); iterator1.hasNext(); ) {
/*      */       
/* 1048 */       InternalEObject proxy = iterator1.next();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1053 */       for (EReference eReference : proxy.eClass().getEAllReferences()) {
/*      */ 
/*      */ 
/*      */         
/* 1057 */         EReference oppositeEReference = eReference.getEOpposite();
/* 1058 */         if (oppositeEReference != null && oppositeEReference.isChangeable() && proxy.eIsSet((EStructuralFeature)eReference)) {
/*      */ 
/*      */ 
/*      */           
/* 1062 */           EObject resolvedEObject = this.xmlResource.getEObject(proxy.eProxyURI().fragment());
/* 1063 */           if (resolvedEObject != null) {
/*      */ 
/*      */ 
/*      */             
/* 1067 */             if (!isEndDocument)
/*      */             {
/* 1069 */               iterator1.remove();
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1074 */             EObject proxyHolder = eReference.isMany() ? ((List<EObject>)proxy.eGet((EStructuralFeature)eReference)).get(0) : (EObject)proxy.eGet((EStructuralFeature)eReference);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1080 */             if (oppositeEReference.isMany()) {
/*      */ 
/*      */ 
/*      */               
/* 1084 */               InternalEList<?> holderContents = (InternalEList)proxyHolder.eGet((EStructuralFeature)oppositeEReference);
/* 1085 */               int resolvedEObjectIndex = holderContents.basicIndexOf(resolvedEObject);
/* 1086 */               if (resolvedEObjectIndex != -1) {
/*      */ 
/*      */ 
/*      */                 
/* 1090 */                 int proxyIndex = holderContents.basicIndexOf(proxy);
/* 1091 */                 holderContents.move(proxyIndex, resolvedEObjectIndex);
/* 1092 */                 holderContents.remove((proxyIndex > resolvedEObjectIndex) ? (proxyIndex - 1) : (proxyIndex + 1));
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1097 */             if (!oppositeEReference.getEType().isInstance(resolvedEObject)) {
/*      */               
/* 1099 */               error(
/* 1100 */                   (XMIException)new IllegalValueException(
/* 1101 */                     proxyHolder, 
/* 1102 */                     (EStructuralFeature)oppositeEReference, 
/* 1103 */                     resolvedEObject, 
/* 1104 */                     null, 
/* 1105 */                     getLocation(), 
/* 1106 */                     getLineNumber(), 
/* 1107 */                     getColumnNumber()));
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 1113 */             if (eReference.isMany() ? 
/* 1114 */               !((InternalEList)resolvedEObject.eGet((EStructuralFeature)eReference)).basicContains(proxyHolder) : (
/* 1115 */               resolvedEObject.eGet((EStructuralFeature)eReference) != proxyHolder)) {
/*      */ 
/*      */ 
/*      */               
/* 1119 */               if (oppositeEReference.isMany()) {
/*      */                 
/* 1121 */                 InternalEList<EObject> proxyHolderList = (InternalEList<EObject>)proxyHolder.eGet((EStructuralFeature)oppositeEReference);
/* 1122 */                 proxyHolderList.setUnique(proxyHolderList.basicIndexOf(proxy), resolvedEObject);
/*      */                 
/*      */                 break;
/*      */               } 
/* 1126 */               proxyHolder.eSet((EStructuralFeature)oppositeEReference, resolvedEObject);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1136 */     for (Iterator<SingleReference> iterator = this.forwardSingleReferences.iterator(); iterator.hasNext(); ) {
/*      */       
/* 1138 */       SingleReference ref = iterator.next();
/* 1139 */       EObject obj = this.xmlResource.getEObject((String)ref.getValue());
/*      */       
/* 1141 */       if (obj != null) {
/*      */ 
/*      */         
/* 1144 */         if (!isEndDocument)
/*      */         {
/* 1146 */           iterator.remove();
/*      */         }
/* 1148 */         EStructuralFeature feature = ref.getFeature();
/* 1149 */         setFeatureValue(ref.getObject(), feature, obj, ref.getPosition()); continue;
/*      */       } 
/* 1151 */       if (isEndDocument)
/*      */       {
/* 1153 */         error(
/* 1154 */             (XMIException)new UnresolvedReferenceException(
/* 1155 */               (String)ref.getValue(), 
/* 1156 */               getLocation(), 
/* 1157 */               ref.getLineNumber(), 
/* 1158 */               ref.getColumnNumber()));
/*      */       }
/*      */     } 
/*      */     
/* 1162 */     for (Iterator<ManyReference> i = this.forwardManyReferences.iterator(); i.hasNext(); ) {
/*      */       
/* 1164 */       ManyReference ref = i.next();
/* 1165 */       Object[] values = ref.getValues();
/*      */       
/* 1167 */       boolean failure = false;
/* 1168 */       for (int j = 0, l = values.length; j < l; j++) {
/*      */         
/* 1170 */         String id = (String)values[j];
/* 1171 */         EObject obj = this.xmlResource.getEObject(id);
/* 1172 */         values[j] = obj;
/*      */         
/* 1174 */         if (obj == null) {
/*      */           
/* 1176 */           failure = true;
/* 1177 */           if (isEndDocument)
/*      */           {
/* 1179 */             error(
/* 1180 */                 (XMIException)new UnresolvedReferenceException(
/* 1181 */                   id, 
/* 1182 */                   getLocation(), 
/* 1183 */                   ref.getLineNumber(), 
/* 1184 */                   ref.getColumnNumber()));
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1189 */       if (!failure) {
/*      */         
/* 1191 */         if (!isEndDocument)
/*      */         {
/* 1193 */           i.remove();
/*      */         }
/* 1195 */         setFeatureValues(ref); continue;
/*      */       } 
/* 1197 */       if (isEndDocument)
/*      */       {
/*      */ 
/*      */         
/* 1201 */         setFeatureValues(ref);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() {
/* 1214 */     if (this.deferredExtent != null)
/*      */     {
/* 1216 */       this.extent.addAll(this.deferredExtent);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1222 */     if (this.usedNullNamespacePackage)
/*      */     {
/* 1224 */       this.helper.addPrefix("", "");
/*      */     }
/* 1226 */     this.helper.recordPrefixToURIMapping();
/* 1227 */     this.helper.popContext();
/* 1228 */     handleForwardReferences(true);
/*      */     
/* 1230 */     if (this.disableNotify)
/*      */     {
/* 1232 */       for (TreeIterator<EObject> treeIterator = EcoreUtil.getAllContents((Collection)this.xmlResource.getContents(), false); treeIterator.hasNext(); ) {
/*      */         
/* 1234 */         EObject eObject = treeIterator.next();
/* 1235 */         eObject.eSetDeliver(true);
/*      */       } 
/*      */     }
/*      */     
/* 1239 */     if (this.extendedMetaData != null)
/*      */     {
/* 1241 */       if (this.extent.size() == 1) {
/*      */         
/* 1243 */         EObject root = (EObject)this.extent.get(0);
/* 1244 */         recordNamespacesSchemaLocations(root);
/*      */       } 
/*      */     }
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
/*      */   protected EMap<String, String> recordNamespacesSchemaLocations(EObject root) {
/* 1263 */     EClass eClass = root.eClass();
/* 1264 */     EReference xmlnsPrefixMapFeature = this.extendedMetaData.getXMLNSPrefixMapFeature(eClass);
/* 1265 */     EMap<String, String> xmlnsPrefixMap = null;
/* 1266 */     if (xmlnsPrefixMapFeature != null) {
/*      */       
/* 1268 */       EMap<String, String> newXMLNSPrefixMap = (EMap<String, String>)root.eGet((EStructuralFeature)xmlnsPrefixMapFeature);
/* 1269 */       xmlnsPrefixMap = newXMLNSPrefixMap;
/* 1270 */       xmlnsPrefixMap.putAll(this.helper.getPrefixToNamespaceMap());
/*      */     } 
/*      */     
/* 1273 */     if (this.urisToLocations != null) {
/*      */       
/* 1275 */       EReference xsiSchemaLocationMapFeature = this.extendedMetaData.getXSISchemaLocationMapFeature(eClass);
/* 1276 */       if (xsiSchemaLocationMapFeature != null) {
/*      */         
/* 1278 */         EMap<String, String> newXSISchemaLocationMap = (EMap<String, String>)root.eGet((EStructuralFeature)xsiSchemaLocationMapFeature);
/* 1279 */         EMap<String, String> xsiSchemaLocationMap = newXSISchemaLocationMap;
/* 1280 */         for (Map.Entry<String, URI> entry : this.urisToLocations.entrySet())
/*      */         {
/* 1282 */           xsiSchemaLocationMap.put(entry.getKey(), ((URI)entry.getValue()).toString());
/*      */         }
/*      */       } 
/*      */     } 
/* 1286 */     return xmlnsPrefixMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject createObjectByType(String prefix, String name, boolean top) {
/* 1294 */     if (top)
/*      */     {
/* 1296 */       handleTopLocations(prefix, name);
/*      */     }
/*      */     
/* 1299 */     EFactory eFactory = getFactoryForPrefix(prefix);
/* 1300 */     String uri = this.helper.getURI(prefix);
/* 1301 */     if (eFactory == null && prefix.equals("") && uri == null) {
/*      */       
/* 1303 */       EPackage ePackage = handleMissingPackage(null);
/* 1304 */       if (ePackage == null) {
/*      */         
/* 1306 */         error(
/* 1307 */             (XMIException)new PackageNotFoundException(
/* 1308 */               null, 
/* 1309 */               getLocation(), 
/* 1310 */               getLineNumber(), 
/* 1311 */               getColumnNumber()));
/*      */       }
/*      */       else {
/*      */         
/* 1315 */         eFactory = ePackage.getEFactoryInstance();
/*      */       } 
/*      */     } 
/*      */     
/* 1319 */     this.documentRoot = createDocumentRoot(prefix, uri, name, eFactory, top);
/*      */     
/* 1321 */     if (this.documentRoot != null) return this.documentRoot;
/*      */     
/* 1323 */     EObject newObject = null;
/* 1324 */     if (this.useNewMethods) {
/*      */       
/* 1326 */       newObject = createObject(eFactory, this.helper.getType(eFactory, name), false);
/*      */     }
/*      */     else {
/*      */       
/* 1330 */       newObject = createObjectFromFactory(eFactory, name);
/*      */     } 
/* 1332 */     newObject = validateCreateObjectFromFactory(eFactory, name, newObject, top);
/*      */     
/* 1334 */     if (top) {
/*      */       
/* 1336 */       processTopObject(newObject);
/*      */       
/* 1338 */       if (this.extendedMetaData != null && newObject != null) {
/*      */         
/* 1340 */         EStructuralFeature simpleFeature = this.extendedMetaData.getSimpleFeature(newObject.eClass());
/* 1341 */         if (simpleFeature != null) {
/*      */           
/* 1343 */           this.isSimpleFeature = true;
/* 1344 */           this.isIDREF = simpleFeature instanceof EReference;
/* 1345 */           this.objects.push((EObject)null);
/* 1346 */           this.mixedTargets.push((FeatureMap)null);
/* 1347 */           this.types.push(simpleFeature);
/* 1348 */           this.text = new StringBuffer();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1352 */     return newObject;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EObject createDocumentRoot(String prefix, String uri, String name, EFactory eFactory, boolean top) {
/* 1357 */     if (this.extendedMetaData != null && eFactory != null) {
/*      */       
/* 1359 */       EPackage ePackage = eFactory.getEPackage();
/* 1360 */       EClass eClass = null;
/* 1361 */       if (this.useConfigurationCache) {
/*      */         
/* 1363 */         eClass = ConfigurationCache.INSTANCE.getDocumentRoot(ePackage);
/* 1364 */         if (eClass == null)
/*      */         {
/* 1366 */           eClass = this.extendedMetaData.getDocumentRoot(ePackage);
/* 1367 */           ConfigurationCache.INSTANCE.putDocumentRoot(ePackage, eClass);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1372 */         eClass = this.extendedMetaData.getDocumentRoot(ePackage);
/*      */       } 
/* 1374 */       if (eClass != null) {
/*      */         SimpleAnyType simpleAnyType;
/*      */         
/* 1377 */         String typeName = this.extendedMetaData.getName((EClassifier)eClass);
/* 1378 */         EObject newObject = 
/* 1379 */           this.useNewMethods ? 
/* 1380 */           createObject(eFactory, (EClassifier)eClass, true) : 
/* 1381 */           this.helper.createObject(eFactory, typeName);
/* 1382 */         validateCreateObjectFromFactory(eFactory, typeName, newObject);
/* 1383 */         if (top)
/*      */         {
/* 1385 */           if (this.suppressDocumentRoot) {
/*      */             SimpleAnyType simpleAnyType1;
/*      */ 
/*      */             
/* 1389 */             List<EObject> oldDeferredExtent = this.deferredExtent;
/*      */             
/*      */             try {
/* 1392 */               this.deferredExtent = new ArrayList<EObject>();
/* 1393 */               processTopObject(newObject);
/*      */             }
/*      */             finally {
/*      */               
/* 1397 */               this.deferredExtent = oldDeferredExtent;
/*      */             } 
/* 1399 */             handleFeature(prefix, name);
/*      */ 
/*      */ 
/*      */             
/* 1403 */             this.objects.remove(0);
/* 1404 */             this.mixedTargets.remove(0);
/* 1405 */             this.types.remove(0);
/*      */ 
/*      */ 
/*      */             
/* 1409 */             EObject peekObject = this.objects.peek();
/* 1410 */             if (peekObject == null) {
/*      */ 
/*      */ 
/*      */               
/* 1414 */               if (this.objects.size() > 1)
/*      */               {
/*      */ 
/*      */                 
/* 1418 */                 EcoreUtil.remove(peekObject = (EObject)this.objects.get(0));
/*      */ 
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*      */                 
/* 1425 */                 SimpleAnyType simpleAnyType2 = (SimpleAnyType)EcoreUtil.create(this.anySimpleType);
/* 1426 */                 simpleAnyType2.setInstanceType(((EAttribute)this.types.peek()).getEAttributeType());
/* 1427 */                 this.objects.set(0, simpleAnyType2);
/* 1428 */                 this.types.set(0, XMLTypePackage.Literals.SIMPLE_ANY_TYPE__RAW_VALUE);
/* 1429 */                 this.mixedTargets.set(0, simpleAnyType2.getMixed());
/* 1430 */                 simpleAnyType1 = simpleAnyType2;
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1437 */               EcoreUtil.remove((EObject)simpleAnyType1);
/*      */             } 
/*      */ 
/*      */             
/* 1441 */             if (this.deferredExtent != null) {
/*      */               
/* 1443 */               this.deferredExtent.add(simpleAnyType1);
/*      */             }
/*      */             else {
/*      */               
/* 1447 */               this.extent.addUnique(simpleAnyType1);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1452 */             simpleAnyType = simpleAnyType1;
/*      */           }
/*      */           else {
/*      */             
/* 1456 */             processTopObject((EObject)simpleAnyType);
/* 1457 */             handleFeature(prefix, name);
/*      */           } 
/*      */         }
/* 1460 */         return (EObject)simpleAnyType;
/*      */       } 
/*      */     } 
/* 1463 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createTopObject(String prefix, String name) {
/* 1468 */     createObjectByType(prefix, name, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void processTopObject(EObject object) {
/* 1476 */     if (object != null) {
/*      */       
/* 1478 */       if (this.deferredExtent != null) {
/*      */         
/* 1480 */         this.deferredExtent.add(object);
/*      */       }
/*      */       else {
/*      */         
/* 1484 */         this.extent.addUnique(object);
/*      */       } 
/*      */       
/* 1487 */       if (this.extendedMetaData != null && !this.mixedTargets.isEmpty()) {
/*      */         
/* 1489 */         FeatureMap featureMap = this.mixedTargets.pop();
/* 1490 */         EAttribute eAttribute = this.extendedMetaData.getMixedFeature(object.eClass());
/* 1491 */         if (eAttribute != null) {
/*      */           
/* 1493 */           FeatureMap otherFeatureMap = (FeatureMap)object.eGet((EStructuralFeature)eAttribute);
/* 1494 */           for (FeatureMap.Entry entry : new ArrayList((Collection<?>)featureMap)) {
/*      */ 
/*      */ 
/*      */             
/* 1498 */             if (entry.getEStructuralFeature() != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT || 
/* 1499 */               !"".equals(XMLTypeUtil.normalize(entry.getValue().toString(), true)))
/*      */             {
/* 1501 */               otherFeatureMap.add(entry.getEStructuralFeature(), entry.getValue());
/*      */             }
/*      */           } 
/*      */         } 
/* 1505 */         this.text = null;
/*      */       } 
/*      */     } 
/*      */     
/* 1509 */     processObject(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String uri, String localName, String name) {
/* 1519 */     this.elements.pop();
/* 1520 */     Object type = this.types.pop();
/* 1521 */     if (type == "object") {
/*      */       
/* 1523 */       if (this.text == null) {
/*      */         
/* 1525 */         this.objects.pop();
/* 1526 */         this.mixedTargets.pop();
/*      */       }
/*      */       else {
/*      */         
/* 1530 */         EObject object = this.objects.popEObject();
/* 1531 */         if (this.mixedTargets.peek() != null && (
/* 1532 */           object.eContainer() != null || 
/* 1533 */           this.suppressDocumentRoot || (
/* 1534 */           this.recordUnknownFeature && (
/* 1535 */           this.eObjectToExtensionMap.containsValue(object) || ((InternalEObject)object).eDirectResource() != null))))
/*      */         {
/* 1537 */           handleMixedText();
/* 1538 */           this.mixedTargets.pop();
/*      */         }
/*      */         else
/*      */         {
/* 1542 */           if (this.text.length() != 0)
/*      */           {
/* 1544 */             handleProxy((InternalEObject)object, this.text.toString().trim());
/*      */           }
/* 1546 */           this.mixedTargets.pop();
/* 1547 */           this.text = null;
/*      */         }
/*      */       
/*      */       } 
/* 1551 */     } else if (this.isIDREF) {
/*      */       
/* 1553 */       this.objects.pop();
/* 1554 */       this.mixedTargets.pop();
/* 1555 */       if (this.text != null) {
/*      */         
/* 1557 */         setValueFromId(this.objects.peekEObject(), (EReference)type, this.text.toString());
/* 1558 */         this.text = null;
/*      */       } 
/* 1560 */       this.isIDREF = false;
/*      */     }
/* 1562 */     else if (isTextFeatureValue(type)) {
/*      */       
/* 1564 */       EObject eObject = this.objects.popEObject();
/* 1565 */       this.mixedTargets.pop();
/* 1566 */       if (eObject == null)
/*      */       {
/* 1568 */         eObject = this.objects.peekEObject();
/*      */       }
/* 1570 */       setFeatureValue(eObject, (EStructuralFeature)type, (this.text == null) ? null : this.text.toString());
/* 1571 */       this.text = null;
/*      */     } 
/*      */     
/* 1574 */     if (this.isSimpleFeature) {
/*      */       
/* 1576 */       this.types.pop();
/* 1577 */       this.objects.pop();
/* 1578 */       this.mixedTargets.pop();
/* 1579 */       this.isSimpleFeature = false;
/*      */     } 
/* 1581 */     this.helper.popContext(this.prefixesToFactories);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isTextFeatureValue(Object type) {
/* 1586 */     return (type != "error");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) {
/* 1592 */     this.isNamespaceAware = true;
/*      */     
/* 1594 */     if (this.needsPushContext) {
/*      */       
/* 1596 */       this.helper.pushContext();
/* 1597 */       this.needsPushContext = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1602 */     this.helper.addPrefix(prefix, uri);
/* 1603 */     this.prefixesToFactories.remove(prefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPrefixMapping(String prefix) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int start, int length) {
/* 1616 */     if (this.text == null && this.mixedTargets.peek() != null)
/*      */     {
/* 1618 */       this.text = new StringBuffer();
/*      */     }
/*      */     
/* 1621 */     if (this.text != null)
/*      */     {
/* 1623 */       this.text.append(ch, start, length);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, String data) {
/* 1630 */     if (this.mixedTargets.peek() != null) {
/*      */       
/* 1632 */       if (this.text != null)
/*      */       {
/* 1634 */         handleMixedText();
/*      */       }
/*      */       
/* 1637 */       handleProcessingInstruction(target, data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleXMLNSAttribute(String attrib, String value) {
/* 1644 */     int index = attrib.indexOf(':', 0);
/* 1645 */     String prefix = (index == -1) ? "" : attrib.substring(index + 1);
/* 1646 */     this.helper.addPrefix(prefix, value);
/* 1647 */     this.prefixesToFactories.remove(prefix);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleXSISchemaLocation(String schemaLocations) {
/* 1652 */     if (this.urisToLocations == null) {
/*      */       
/* 1654 */       this.urisToLocations = new HashMap<String, URI>();
/* 1655 */       this.xmlResource.getDefaultSaveOptions().put("SCHEMA_LOCATION", Boolean.TRUE);
/*      */     } 
/*      */     
/* 1658 */     for (StringTokenizer stringTokenizer = new StringTokenizer(schemaLocations, " "); stringTokenizer.hasMoreTokens(); ) {
/*      */       
/* 1660 */       String key = stringTokenizer.nextToken();
/* 1661 */       if (stringTokenizer.hasMoreTokens()) {
/*      */         
/* 1663 */         String value = stringTokenizer.nextToken();
/* 1664 */         URI uri = URI.createURI(value);
/* 1665 */         if (this.uriHandler != null) {
/*      */           
/* 1667 */           uri = this.uriHandler.resolve(uri);
/*      */         }
/* 1669 */         else if (this.resolve && uri.isRelative() && uri.hasRelativePath()) {
/*      */           
/* 1671 */           uri = this.helper.resolve(uri, this.resourceURI);
/*      */         } 
/* 1673 */         this.urisToLocations.put(key, uri);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleXSINoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
/* 1680 */     if (this.urisToLocations == null) {
/*      */       
/* 1682 */       this.urisToLocations = new HashMap<String, URI>();
/* 1683 */       this.xmlResource.getDefaultSaveOptions().put("SCHEMA_LOCATION", Boolean.TRUE);
/*      */     } 
/*      */     
/* 1686 */     URI uri = URI.createURI(noNamespaceSchemaLocation);
/* 1687 */     if (this.uriHandler != null) {
/*      */       
/* 1689 */       uri = this.uriHandler.resolve(uri);
/*      */     }
/* 1691 */     else if (this.resolve && uri.isRelative() && uri.hasRelativePath()) {
/*      */       
/* 1693 */       uri = this.helper.resolve(uri, this.resourceURI);
/*      */     } 
/* 1695 */     this.urisToLocations.put(null, uri);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void processSchemaLocations(String prefix, String name) {
/* 1700 */     if (this.urisToLocations != null) {
/*      */ 
/*      */       
/* 1703 */       if (this.processSchemaLocations) {
/*      */         
/*      */         try {
/*      */           
/* 1707 */           this.ecoreBuilder.generate(this.urisToLocations);
/*      */         }
/* 1709 */         catch (Exception exception) {
/*      */           
/* 1711 */           XMIPlugin.INSTANCE.log(exception);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1717 */         if (this.externalURIToLocations != null)
/*      */         {
/* 1719 */           this.ecoreBuilder.generate(this.externalURIToLocations);
/*      */         }
/*      */       }
/* 1722 */       catch (Exception exception) {
/*      */         
/* 1724 */         XMIPlugin.INSTANCE.log(exception);
/*      */       } 
/*      */       
/* 1727 */       URI locationForNull = this.urisToLocations.get(null);
/* 1728 */       if (locationForNull != null && this.helper.getNoNamespacePackage() == null)
/*      */       {
/* 1730 */         this.helper.setNoNamespacePackage(getPackageForURI(locationForNull.toString()));
/*      */       }
/*      */     }
/* 1733 */     else if (this.externalURIToLocations != null) {
/*      */ 
/*      */       
/*      */       try {
/* 1737 */         this.ecoreBuilder.generate(this.externalURIToLocations);
/*      */       }
/* 1739 */       catch (Exception exception) {
/*      */         
/* 1741 */         XMIPlugin.INSTANCE.log(exception);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleTopLocations(String prefix, String name) {
/* 1748 */     processSchemaLocations(prefix, name);
/* 1749 */     if (this.processAnyXML) {
/*      */ 
/*      */ 
/*      */       
/* 1753 */       String uri = this.helper.getURI(prefix);
/* 1754 */       if (this.extendedMetaData.getPackage(uri) == null)
/*      */       {
/* 1756 */         this.extendedMetaData.demandFeature(uri, name, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleFeature(String prefix, String name) {
/* 1769 */     EObject peekObject = this.objects.peekEObject();
/*      */ 
/*      */ 
/*      */     
/* 1773 */     if (peekObject == null) {
/*      */       
/* 1775 */       this.types.push("error");
/* 1776 */       error(
/* 1777 */           (XMIException)new FeatureNotFoundException(
/* 1778 */             name, 
/* 1779 */             null, 
/* 1780 */             getLocation(), 
/* 1781 */             getLineNumber(), 
/* 1782 */             getColumnNumber()));
/*      */       
/*      */       return;
/*      */     } 
/* 1786 */     EStructuralFeature feature = getFeature(peekObject, prefix, name, true);
/* 1787 */     if (feature != null) {
/*      */       
/* 1789 */       int kind = this.helper.getFeatureKind(feature);
/* 1790 */       if (kind == 1 || kind == 2) {
/*      */         
/* 1792 */         this.objects.push((EObject)null);
/* 1793 */         this.mixedTargets.push((FeatureMap)null);
/* 1794 */         this.types.push(feature);
/* 1795 */         if (!isNull())
/*      */         {
/* 1797 */           this.text = new StringBuffer();
/*      */         }
/*      */       }
/* 1800 */       else if (this.extendedMetaData != null) {
/*      */         
/* 1802 */         EReference eReference = (EReference)feature;
/* 1803 */         boolean isContainment = eReference.isContainment();
/* 1804 */         if (!isContainment && !eReference.isResolveProxies() && this.extendedMetaData.getFeatureKind(feature) != 0) {
/*      */           
/* 1806 */           this.isIDREF = true;
/* 1807 */           this.objects.push((EObject)null);
/* 1808 */           this.mixedTargets.push((FeatureMap)null);
/* 1809 */           this.types.push(feature);
/* 1810 */           this.text = new StringBuffer();
/*      */         }
/*      */         else {
/*      */           
/* 1814 */           createObject(peekObject, feature);
/* 1815 */           EObject childObject = this.objects.peekEObject();
/* 1816 */           if (childObject != null)
/*      */           {
/* 1818 */             if (isContainment) {
/*      */               
/* 1820 */               EStructuralFeature simpleFeature = this.extendedMetaData.getSimpleFeature(childObject.eClass());
/* 1821 */               if (simpleFeature != null)
/*      */               {
/* 1823 */                 this.isSimpleFeature = true;
/* 1824 */                 this.isIDREF = simpleFeature instanceof EReference;
/* 1825 */                 this.objects.push((EObject)null);
/* 1826 */                 this.mixedTargets.push((FeatureMap)null);
/* 1827 */                 this.types.push(simpleFeature);
/* 1828 */                 this.text = new StringBuffer();
/*      */               }
/*      */             
/* 1831 */             } else if (!childObject.eIsProxy()) {
/*      */               
/* 1833 */               this.text = new StringBuffer();
/*      */             }
/*      */           
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/* 1840 */         createObject(peekObject, feature);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1848 */     else if (this.xmlMap != null && (feature = getFeature(peekObject, null, "", true)) != null) {
/*      */ 
/*      */       
/* 1851 */       EFactory eFactory = getFactoryForPrefix(prefix);
/*      */ 
/*      */ 
/*      */       
/* 1855 */       if (eFactory == null)
/*      */       {
/* 1857 */         eFactory = feature.getEContainingClass().getEPackage().getEFactoryInstance();
/*      */       }
/*      */       
/* 1860 */       EObject newObject = null;
/* 1861 */       if (this.useNewMethods) {
/*      */         
/* 1863 */         newObject = createObject(eFactory, this.helper.getType(eFactory, name), false);
/*      */       }
/*      */       else {
/*      */         
/* 1867 */         newObject = createObjectFromFactory(eFactory, name);
/*      */       } 
/* 1869 */       newObject = validateCreateObjectFromFactory(eFactory, name, newObject, feature);
/* 1870 */       if (newObject != null)
/*      */       {
/* 1872 */         setFeatureValue(peekObject, feature, newObject);
/*      */       }
/* 1874 */       processObject(newObject);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1880 */       if (this.xmlMap != null) {
/*      */         
/* 1882 */         EFactory eFactory = getFactoryForPrefix(prefix);
/* 1883 */         EObject newObject = createObjectFromFactory(eFactory, name);
/* 1884 */         validateCreateObjectFromFactory(eFactory, name, newObject);
/* 1885 */         if (newObject != null)
/*      */         {
/* 1887 */           for (EReference eReference : peekObject.eClass().getEAllReferences()) {
/*      */             
/* 1889 */             if (eReference.getEType().isInstance(newObject)) {
/*      */               
/* 1891 */               setFeatureValue(peekObject, (EStructuralFeature)eReference, newObject);
/* 1892 */               processObject(newObject);
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/* 1899 */       handleUnknownFeature(prefix, name, true, peekObject, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getLineNumber() {
/* 1906 */     if (this.locator != null)
/*      */     {
/* 1908 */       return this.locator.getLineNumber();
/*      */     }
/*      */ 
/*      */     
/* 1912 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getColumnNumber() {
/* 1918 */     if (this.locator != null)
/*      */     {
/* 1920 */       return this.locator.getColumnNumber();
/*      */     }
/*      */ 
/*      */     
/* 1924 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLocation() {
/* 1930 */     return 
/* 1931 */       (this.locator != null && this.locator.getSystemId() != null) ? 
/* 1932 */       this.locator.getSystemId() : (
/* 1933 */       (this.resourceURI == null) ? "" : this.resourceURI.toString());
/*      */   }
/*      */ 
/*      */   
/*      */   protected AnyType getExtension(EObject peekObject) {
/* 1938 */     AnyType anyType = this.eObjectToExtensionMap.get(peekObject);
/* 1939 */     if (anyType == null) {
/*      */       
/* 1941 */       anyType = XMLTypeFactory.eINSTANCE.createAnyType();
/* 1942 */       this.eObjectToExtensionMap.put(peekObject, anyType);
/*      */     } 
/* 1944 */     return anyType;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value) {
/* 1949 */     if (this.recordUnknownFeature) {
/*      */       
/* 1951 */       recordUnknownFeature(prefix, name, isElement, peekObject, value);
/*      */     }
/*      */     else {
/*      */       
/* 1955 */       reportUnknownFeature(prefix, name, isElement, peekObject, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void recordUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value) {
/* 1961 */     if (isElement) {
/*      */       
/* 1963 */       AnyType anyType = getExtension(peekObject);
/* 1964 */       int objectsIndex = this.objects.size();
/* 1965 */       this.objects.push((EObject)anyType);
/* 1966 */       int mixedTargetsIndex = this.mixedTargets.size();
/* 1967 */       this.mixedTargets.push(anyType.getAny());
/* 1968 */       int typesIndex = this.types.size();
/* 1969 */       this.types.push("unknownFeature");
/*      */       
/* 1971 */       handleFeature(prefix, name);
/*      */       
/* 1973 */       this.objects.remove(objectsIndex);
/* 1974 */       this.mixedTargets.remove(mixedTargetsIndex);
/* 1975 */       this.types.remove(typesIndex);
/*      */     }
/*      */     else {
/*      */       
/* 1979 */       AnyType anyType = getExtension(peekObject);
/* 1980 */       setAttribValue((EObject)anyType, (prefix == null) ? name : (String.valueOf(prefix) + ":" + name), value);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void reportUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value) {
/* 1986 */     if (isElement)
/*      */     {
/* 1988 */       this.types.push("error");
/*      */     }
/* 1990 */     error(
/* 1991 */         (XMIException)new FeatureNotFoundException(
/* 1992 */           name, 
/* 1993 */           peekObject, 
/* 1994 */           getLocation(), 
/* 1995 */           getLineNumber(), 
/* 1996 */           getColumnNumber()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void error(XMIException e) {
/* 2001 */     this.xmlResource.getErrors().add(e);
/*      */   }
/*      */ 
/*      */   
/*      */   public void warning(XMIException e) {
/* 2006 */     this.xmlResource.getWarnings().add(e);
/*      */   }
/*      */ 
/*      */   
/*      */   public void fatalError(XMIException e) {
/* 2011 */     this.xmlResource.getErrors().add(e);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createObject(EObject peekObject, EStructuralFeature feature) {
/* 2019 */     if (isNull()) {
/*      */       
/* 2021 */       setFeatureValue(peekObject, feature, null);
/* 2022 */       this.objects.push((EObject)null);
/* 2023 */       this.mixedTargets.push((FeatureMap)null);
/* 2024 */       this.types.push("object");
/*      */     }
/*      */     else {
/*      */       
/* 2028 */       String xsiType = getXSIType();
/* 2029 */       if (xsiType != null) {
/*      */         
/* 2031 */         createObjectFromTypeName(peekObject, xsiType, feature);
/*      */       }
/*      */       else {
/*      */         
/* 2035 */         createObjectFromFeatureType(peekObject, feature);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2041 */         if (this.xmlMap != null && !((EReference)feature).isContainment()) {
/*      */           
/* 2043 */           XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)feature);
/* 2044 */           if (info != null && info.getXMLRepresentation() == 0)
/*      */           {
/* 2046 */             this.text = new StringBuffer();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject createObjectFromTypeName(EObject peekObject, String typeQName, EStructuralFeature feature) {
/* 2058 */     String typeName = null;
/* 2059 */     String prefix = "";
/* 2060 */     int index = typeQName.indexOf(':', 0);
/* 2061 */     if (index > 0) {
/*      */       
/* 2063 */       prefix = typeQName.substring(0, index);
/* 2064 */       typeName = typeQName.substring(index + 1);
/*      */     }
/*      */     else {
/*      */       
/* 2068 */       typeName = typeQName;
/*      */     } 
/*      */     
/* 2071 */     this.contextFeature = feature;
/* 2072 */     EFactory eFactory = getFactoryForPrefix(prefix);
/* 2073 */     this.contextFeature = null;
/*      */     
/* 2075 */     if (eFactory == null && prefix.equals("") && this.helper.getURI(prefix) == null) {
/*      */       
/* 2077 */       this.contextFeature = feature;
/* 2078 */       EPackage ePackage = handleMissingPackage(null);
/* 2079 */       this.contextFeature = null;
/* 2080 */       if (ePackage == null) {
/*      */         
/* 2082 */         error((XMIException)new PackageNotFoundException(null, getLocation(), getLineNumber(), getColumnNumber()));
/*      */       }
/*      */       else {
/*      */         
/* 2086 */         eFactory = ePackage.getEFactoryInstance();
/*      */       } 
/*      */     } 
/* 2089 */     EObject obj = null;
/* 2090 */     if (this.useNewMethods) {
/*      */       
/* 2092 */       obj = createObject(eFactory, this.helper.getType(eFactory, typeName), false);
/*      */     }
/*      */     else {
/*      */       
/* 2096 */       obj = createObjectFromFactory(eFactory, typeName);
/*      */     } 
/*      */     
/* 2099 */     obj = validateCreateObjectFromFactory(eFactory, typeName, obj, feature);
/*      */     
/* 2101 */     if (obj != null)
/*      */     {
/* 2103 */       if (this.contextFeature == null) {
/*      */         
/* 2105 */         setFeatureValue(peekObject, feature, obj);
/*      */       }
/*      */       else {
/*      */         
/* 2109 */         this.contextFeature = null;
/*      */       } 
/*      */     }
/*      */     
/* 2113 */     processObject(obj);
/*      */     
/* 2115 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject createObjectFromFeatureType(EObject peekObject, EStructuralFeature feature) {
/* 2123 */     String typeName = null;
/* 2124 */     EFactory factory = null;
/* 2125 */     EClassifier eType = null;
/* 2126 */     EObject obj = null;
/*      */     
/* 2128 */     if (feature != null && (eType = feature.getEType()) != null) {
/*      */       EClass eClass;
/* 2130 */       if (this.useNewMethods) {
/*      */         
/* 2132 */         if (this.extendedMetaData != null && eType == EcorePackage.Literals.EOBJECT && this.extendedMetaData.getFeatureKind(feature) != 0) {
/*      */           
/* 2134 */           eClass = this.anyType;
/* 2135 */           typeName = this.extendedMetaData.getName((EClassifier)this.anyType);
/* 2136 */           factory = this.anyType.getEPackage().getEFactoryInstance();
/*      */         }
/*      */         else {
/*      */           
/* 2140 */           factory = eClass.getEPackage().getEFactoryInstance();
/* 2141 */           typeName = (this.extendedMetaData == null) ? eClass.getName() : this.extendedMetaData.getName((EClassifier)eClass);
/*      */         } 
/* 2143 */         obj = createObject(factory, (EClassifier)eClass, false);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2148 */         if (this.extendedMetaData != null && eClass == EcorePackage.Literals.EOBJECT && this.extendedMetaData.getFeatureKind(feature) != 0) {
/*      */           
/* 2150 */           typeName = this.extendedMetaData.getName((EClassifier)this.anyType);
/* 2151 */           factory = this.anyType.getEPackage().getEFactoryInstance();
/*      */         }
/*      */         else {
/*      */           
/* 2155 */           EClass eClass1 = eClass;
/* 2156 */           typeName = (this.extendedMetaData == null) ? eClass1.getName() : this.extendedMetaData.getName((EClassifier)eClass1);
/* 2157 */           factory = eClass1.getEPackage().getEFactoryInstance();
/*      */         } 
/* 2159 */         obj = createObjectFromFactory(factory, typeName);
/*      */       } 
/*      */     } 
/*      */     
/* 2163 */     obj = validateCreateObjectFromFactory(factory, typeName, obj, feature);
/*      */     
/* 2165 */     if (obj != null)
/*      */     {
/* 2167 */       setFeatureValue(peekObject, feature, obj);
/*      */     }
/*      */     
/* 2170 */     processObject(obj);
/* 2171 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected EObject createObjectFromFactory(EFactory factory, String typeName) {
/* 2182 */     EObject newObject = null;
/*      */     
/* 2184 */     if (factory != null) {
/*      */       
/* 2186 */       newObject = this.helper.createObject(factory, typeName);
/*      */       
/* 2188 */       if (newObject != null) {
/*      */         
/* 2190 */         if (this.disableNotify) {
/* 2191 */           newObject.eSetDeliver(false);
/*      */         }
/* 2193 */         handleObjectAttribs(newObject);
/*      */       } 
/*      */     } 
/*      */     
/* 2197 */     return newObject;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EObject createObject(EFactory eFactory, EClassifier type, boolean documentRoot) {
/* 2202 */     EObject newObject = this.helper.createObject(eFactory, type);
/* 2203 */     if (newObject != null && !documentRoot) {
/*      */       
/* 2205 */       if (this.disableNotify)
/*      */       {
/* 2207 */         newObject.eSetDeliver(false);
/*      */       }
/* 2209 */       handleObjectAttribs(newObject);
/*      */     } 
/* 2211 */     return newObject;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EObject validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject, boolean top) {
/* 2216 */     if (newObject == null && top && (this.recordUnknownFeature || this.processAnyXML) && factory != null && this.extendedMetaData != null) {
/*      */       
/* 2218 */       String namespace = this.extendedMetaData.getNamespace(factory.getEPackage());
/* 2219 */       if (namespace == null)
/*      */       {
/* 2221 */         this.usedNullNamespacePackage = true;
/*      */       }
/* 2223 */       if (this.useNewMethods) {
/*      */         
/* 2225 */         EClassifier type = this.extendedMetaData.demandType(namespace, typeName);
/* 2226 */         newObject = createObject(type.getEPackage().getEFactoryInstance(), type, false);
/*      */       }
/*      */       else {
/*      */         
/* 2230 */         factory = this.extendedMetaData.demandType(namespace, typeName).getEPackage().getEFactoryInstance();
/* 2231 */         newObject = createObjectFromFactory(factory, typeName);
/*      */       } 
/*      */     } 
/*      */     
/* 2235 */     validateCreateObjectFromFactory(factory, typeName, newObject);
/* 2236 */     return newObject;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject) {
/* 2241 */     if (newObject == null)
/*      */     {
/* 2243 */       error(
/* 2244 */           (XMIException)new ClassNotFoundException(
/* 2245 */             typeName, 
/* 2246 */             factory, 
/* 2247 */             getLocation(), 
/* 2248 */             getLineNumber(), 
/* 2249 */             getColumnNumber()));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected EObject validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject, EStructuralFeature feature) {
/* 2255 */     if (newObject != null) {
/*      */       
/* 2257 */       if (this.extendedMetaData != null) {
/*      */         
/* 2259 */         Collection<EPackage> demandedPackages = this.extendedMetaData.demandedPackages();
/* 2260 */         if (!demandedPackages.isEmpty() && demandedPackages.contains(newObject.eClass().getEPackage()))
/*      */         {
/* 2262 */           if (this.recordUnknownFeature) {
/*      */             
/* 2264 */             EObject peekObject = this.objects.peekEObject();
/* 2265 */             if (!(peekObject instanceof AnyType)) {
/*      */               
/* 2267 */               AnyType anyType = getExtension(this.objects.peekEObject());
/* 2268 */               EStructuralFeature entryFeature = 
/* 2269 */                 this.extendedMetaData.demandFeature(this.extendedMetaData.getNamespace(feature), this.extendedMetaData.getName(feature), true);
/* 2270 */               anyType.getAny().add(entryFeature, newObject);
/* 2271 */               this.contextFeature = entryFeature;
/*      */             } 
/* 2273 */             return newObject;
/*      */           } 
/*      */ 
/*      */           
/* 2277 */           String namespace = this.extendedMetaData.getNamespace(feature);
/* 2278 */           String name = this.extendedMetaData.getName(feature);
/* 2279 */           EStructuralFeature wildcardFeature = 
/* 2280 */             this.extendedMetaData.getElementWildcardAffiliation(this.objects.peekEObject().eClass(), namespace, name);
/* 2281 */           if (wildcardFeature != null) {
/*      */             
/* 2283 */             int processingKind = this.laxWildcardProcessing ? 2 : this.extendedMetaData.getProcessingKind(wildcardFeature);
/* 2284 */             switch (processingKind) {
/*      */ 
/*      */               
/*      */               case 2:
/*      */               case 3:
/* 2289 */                 return newObject;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/* 2295 */           newObject = null;
/*      */         }
/*      */       
/*      */       } 
/* 2299 */     } else if (feature != null && factory != null && this.extendedMetaData != null) {
/*      */ 
/*      */       
/* 2302 */       if (this.recordUnknownFeature || this.processAnyXML) {
/*      */ 
/*      */         
/* 2305 */         EObject result = null;
/* 2306 */         String str = this.extendedMetaData.getNamespace(factory.getEPackage());
/* 2307 */         if (str == null)
/*      */         {
/* 2309 */           this.usedNullNamespacePackage = true;
/*      */         }
/* 2311 */         if (this.useNewMethods) {
/*      */           
/* 2313 */           EClassifier type = this.extendedMetaData.demandType(str, typeName);
/* 2314 */           result = createObject(type.getEPackage().getEFactoryInstance(), type, false);
/*      */         }
/*      */         else {
/*      */           
/* 2318 */           factory = this.extendedMetaData.demandType(str, typeName).getEPackage().getEFactoryInstance();
/* 2319 */           result = createObjectFromFactory(factory, typeName);
/*      */         } 
/* 2321 */         EObject peekObject = this.objects.peekEObject();
/* 2322 */         if (!(peekObject instanceof AnyType)) {
/*      */           
/* 2324 */           AnyType anyType = getExtension(peekObject);
/* 2325 */           EStructuralFeature entryFeature = 
/* 2326 */             this.extendedMetaData.demandFeature(this.extendedMetaData.getNamespace(feature), this.extendedMetaData.getName(feature), true);
/* 2327 */           anyType.getAny().add(entryFeature, result);
/* 2328 */           this.contextFeature = entryFeature;
/*      */         } 
/* 2330 */         return result;
/*      */       } 
/*      */ 
/*      */       
/* 2334 */       String namespace = this.extendedMetaData.getNamespace(feature);
/* 2335 */       String name = this.extendedMetaData.getName(feature);
/* 2336 */       EStructuralFeature wildcardFeature = 
/* 2337 */         this.extendedMetaData.getElementWildcardAffiliation(this.objects.peekEObject().eClass(), namespace, name);
/* 2338 */       if (wildcardFeature != null) {
/*      */         String factoryNamespace;
/* 2340 */         int processingKind = this.laxWildcardProcessing ? 2 : this.extendedMetaData.getProcessingKind(wildcardFeature);
/* 2341 */         switch (processingKind) {
/*      */ 
/*      */ 
/*      */           
/*      */           case 2:
/*      */           case 3:
/* 2347 */             factoryNamespace = this.extendedMetaData.getNamespace(factory.getEPackage());
/* 2348 */             if (factoryNamespace == null)
/*      */             {
/* 2350 */               this.usedNullNamespacePackage = true;
/*      */             }
/* 2352 */             if (this.useNewMethods) {
/*      */               
/* 2354 */               EClassifier type = this.extendedMetaData.demandType(factoryNamespace, typeName);
/* 2355 */               return createObject(type.getEPackage().getEFactoryInstance(), type, false);
/*      */             } 
/*      */ 
/*      */             
/* 2359 */             factory = this.extendedMetaData.demandType(factoryNamespace, typeName).getEPackage().getEFactoryInstance();
/* 2360 */             return createObjectFromFactory(factory, typeName);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/* 2368 */     validateCreateObjectFromFactory(factory, typeName, newObject);
/*      */     
/* 2370 */     return newObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void processObject(EObject object) {
/* 2378 */     if (this.recordAnyTypeNSDecls && object instanceof AnyType) {
/*      */       
/* 2380 */       FeatureMap featureMap = ((AnyType)object).getAnyAttribute();
/* 2381 */       for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)this.helper.getAnyContentPrefixToURIMapping().entrySet()) {
/*      */         
/* 2383 */         Object uri = entry.getValue();
/* 2384 */         featureMap.add(this.extendedMetaData.demandFeature("http://www.w3.org/2000/xmlns/", entry.getKey(), false), (uri == null) ? "" : uri);
/*      */       } 
/*      */     } 
/*      */     
/* 2388 */     if (object != null) {
/*      */       
/* 2390 */       this.objects.push(object);
/* 2391 */       this.types.push("object");
/*      */       
/* 2393 */       if (this.extendedMetaData != null) {
/*      */         
/* 2395 */         EAttribute eAttribute = this.extendedMetaData.getMixedFeature(object.eClass());
/* 2396 */         if (eAttribute != null)
/*      */         {
/* 2398 */           this.mixedTargets.push((FeatureMap)object.eGet((EStructuralFeature)eAttribute));
/*      */         }
/*      */         else
/*      */         {
/* 2402 */           this.mixedTargets.push((FeatureMap)null);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2407 */         this.mixedTargets.push((FeatureMap)null);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2412 */       this.types.push("error");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected EFactory getFactoryForPrefix(String prefix) {
/* 2418 */     EFactory factory = this.prefixesToFactories.get(prefix);
/* 2419 */     if (factory == null) {
/*      */       
/* 2421 */       String uri = this.helper.getURI(prefix);
/* 2422 */       EPackage ePackage = getPackageForURI(uri);
/*      */       
/* 2424 */       if (ePackage == null && uri == null && prefix.equals(""))
/*      */       {
/* 2426 */         ePackage = this.helper.getNoNamespacePackage();
/*      */       }
/*      */ 
/*      */       
/* 2430 */       if (ePackage != null) {
/*      */         
/* 2432 */         factory = ePackage.getEFactoryInstance();
/* 2433 */         this.prefixesToFactories.put(prefix, factory);
/* 2434 */         if (uri == null)
/*      */         {
/* 2436 */           this.usedNullNamespacePackage = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2441 */     return factory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPackage getPackageForURI(String uriString) {
/* 2450 */     if (uriString == null)
/*      */     {
/* 2452 */       return null;
/*      */     }
/*      */     
/* 2455 */     EPackage ePackage = 
/* 2456 */       (this.extendedMetaData == null) ? 
/* 2457 */       this.packageRegistry.getEPackage(uriString) : 
/* 2458 */       this.extendedMetaData.getPackage(uriString);
/*      */     
/* 2460 */     if (ePackage != null && ePackage.eIsProxy())
/*      */     {
/* 2462 */       ePackage = null;
/*      */     }
/*      */     
/* 2465 */     if (ePackage == null) {
/*      */       
/* 2467 */       URI uri = URI.createURI(uriString);
/* 2468 */       if (uri.scheme() == null)
/*      */       {
/*      */         
/* 2471 */         for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)this.packageRegistry.entrySet()) {
/*      */           
/* 2473 */           String nsURI = entry.getKey();
/* 2474 */           if (nsURI != null && 
/* 2475 */             nsURI.length() > uriString.length() && 
/* 2476 */             nsURI.endsWith(uriString) && 
/* 2477 */             nsURI.charAt(nsURI.length() - uriString.length() - 1) == '/') {
/*      */             
/* 2479 */             this.oldStyleProxyURIs = true;
/* 2480 */             return (EPackage)entry.getValue();
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 2485 */       if (this.urisToLocations != null) {
/*      */         
/* 2487 */         URI locationURI = this.urisToLocations.get(uriString);
/* 2488 */         if (locationURI != null)
/*      */         {
/* 2490 */           uri = locationURI;
/*      */         }
/*      */       } 
/*      */       
/* 2494 */       String fragment = uri.fragment();
/* 2495 */       Resource resource = null;
/*      */       
/* 2497 */       if ("java".equalsIgnoreCase(uri.scheme()) && uri.authority() != null) {
/*      */         
/*      */         try {
/*      */           
/* 2501 */           String className = uri.authority();
/* 2502 */           Class<?> javaClass = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
/* 2503 */           Field field = javaClass.getField("eINSTANCE");
/* 2504 */           resource = ((EPackage)field.get(null)).eResource();
/*      */         }
/* 2506 */         catch (Exception exception) {}
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2512 */       if (resource == null && this.resourceSet != null) {
/*      */         
/* 2514 */         URI trimmedURI = uri.trimFragment();
/* 2515 */         resource = this.resourceSet.getResource(trimmedURI, false);
/* 2516 */         if (resource != null) {
/*      */           
/* 2518 */           if (!resource.isLoaded()) {
/*      */             
/*      */             try {
/*      */               
/* 2522 */               resource.load(this.resourceSet.getLoadOptions());
/*      */             }
/* 2524 */             catch (IOException iOException) {}
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2530 */         else if (!"http://www.w3.org/2001/XMLSchema".equals(uriString)) {
/*      */ 
/*      */           
/*      */           try {
/* 2534 */             InputStream inputStream = getURIConverter().createInputStream(trimmedURI, null);
/* 2535 */             resource = this.resourceSet.createResource(trimmedURI);
/* 2536 */             if (resource == null)
/*      */             {
/* 2538 */               inputStream.close();
/*      */             }
/*      */             else
/*      */             {
/* 2542 */               resource.load(inputStream, this.resourceSet.getLoadOptions());
/*      */             }
/*      */           
/* 2545 */           } catch (IOException iOException) {}
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2552 */       if (resource != null) {
/*      */         
/* 2554 */         Object content = null;
/* 2555 */         if (fragment != null) {
/*      */           
/* 2557 */           content = resource.getEObject(fragment);
/*      */         }
/*      */         else {
/*      */           
/* 2561 */           EList eList = resource.getContents();
/* 2562 */           if (!eList.isEmpty())
/*      */           {
/* 2564 */             content = eList.get(0);
/*      */           }
/*      */         } 
/*      */         
/* 2568 */         if (content instanceof EPackage) {
/*      */           
/* 2570 */           ePackage = (EPackage)content;
/* 2571 */           if (this.extendedMetaData != null) {
/*      */             
/* 2573 */             this.extendedMetaData.putPackage(this.extendedMetaData.getNamespace(ePackage), ePackage);
/*      */           }
/*      */           else {
/*      */             
/* 2577 */             this.packageRegistry.put(ePackage.getNsURI(), ePackage);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2583 */     if (ePackage == null)
/*      */     {
/* 2585 */       ePackage = handleMissingPackage(uriString);
/*      */     }
/*      */     
/* 2588 */     if (ePackage == null)
/*      */     {
/* 2590 */       error(
/* 2591 */           (XMIException)new PackageNotFoundException(
/* 2592 */             uriString, 
/* 2593 */             getLocation(), 
/* 2594 */             getLineNumber(), 
/* 2595 */             getColumnNumber()));
/*      */     }
/*      */     
/* 2598 */     return ePackage;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EPackage handleMissingPackage(String uriString) {
/* 2603 */     if ("http://www.w3.org/2001/XMLSchema".equals(uriString))
/*      */     {
/* 2605 */       return this.xmlSchemaTypePackage;
/*      */     }
/* 2607 */     if (this.extendedMetaData != null) {
/*      */       
/* 2609 */       if (this.recordUnknownFeature)
/*      */       {
/* 2611 */         return this.extendedMetaData.demandPackage(uriString);
/*      */       }
/* 2613 */       if (this.processAnyXML && this.objects.isEmpty())
/*      */       {
/* 2615 */         return this.extendedMetaData.demandPackage(uriString);
/*      */       }
/* 2617 */       if (this.contextFeature != null) {
/*      */         
/* 2619 */         String namespace = this.extendedMetaData.getNamespace(this.contextFeature);
/* 2620 */         String name = this.extendedMetaData.getName(this.contextFeature);
/* 2621 */         EStructuralFeature wildcardFeature = 
/* 2622 */           this.extendedMetaData.getElementWildcardAffiliation(this.objects.peekEObject().eClass(), namespace, name);
/* 2623 */         if (wildcardFeature != null) {
/*      */           
/* 2625 */           int processingKind = this.laxWildcardProcessing ? 2 : this.extendedMetaData.getProcessingKind(wildcardFeature);
/* 2626 */           switch (processingKind) {
/*      */ 
/*      */             
/*      */             case 2:
/*      */             case 3:
/* 2631 */               return this.extendedMetaData.demandPackage(uriString);
/*      */           } 
/*      */ 
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/* 2638 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected URIConverter getURIConverter() {
/* 2643 */     return (this.resourceSet != null) ? this.resourceSet.getURIConverter() : (URIConverter)new ExtensibleURIConverterImpl();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setFeatureValue(EObject object, EStructuralFeature feature, Object value) {
/* 2648 */     setFeatureValue(object, feature, value, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setFeatureValue(EObject object, EStructuralFeature feature, Object value, int position) {
/*      */     try {
/* 2658 */       this.helper.setValue(object, feature, value, position);
/*      */     }
/* 2660 */     catch (RuntimeException e) {
/*      */       
/* 2662 */       error(
/* 2663 */           (XMIException)new IllegalValueException(
/* 2664 */             object, 
/* 2665 */             feature, 
/* 2666 */             value, 
/* 2667 */             e, 
/* 2668 */             getLocation(), 
/* 2669 */             getLineNumber(), 
/* 2670 */             getColumnNumber()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setFeatureValues(ManyReference reference) {
/* 2679 */     List<XMIException> xmiExceptions = this.helper.setManyReference(reference, getLocation());
/*      */     
/* 2681 */     if (xmiExceptions != null)
/*      */     {
/* 2683 */       for (XMIException exception : xmiExceptions)
/*      */       {
/* 2685 */         error(exception);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setAttribValue(EObject object, String name, String value) {
/* 2696 */     int index = name.indexOf(':', 0);
/*      */ 
/*      */     
/* 2699 */     String prefix = null;
/* 2700 */     String localName = name;
/* 2701 */     if (index != -1) {
/*      */       
/* 2703 */       prefix = name.substring(0, index);
/* 2704 */       localName = name.substring(index + 1);
/*      */     } 
/* 2706 */     EStructuralFeature feature = getFeature(object, prefix, localName, false);
/* 2707 */     if (feature == null) {
/*      */       
/* 2709 */       handleUnknownFeature(prefix, localName, false, object, value);
/*      */     }
/*      */     else {
/*      */       
/* 2713 */       int kind = this.helper.getFeatureKind(feature);
/*      */       
/* 2715 */       if (kind == 1 || kind == 2) {
/*      */         
/* 2717 */         setFeatureValue(object, feature, value, -2);
/*      */       }
/*      */       else {
/*      */         
/* 2721 */         setValueFromId(object, (EReference)feature, value);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setValueFromId(EObject object, EReference eReference, String ids) {
/* 2732 */     StringTokenizer st = new StringTokenizer(ids);
/*      */     
/* 2734 */     boolean isFirstID = true;
/* 2735 */     boolean mustAdd = this.deferIDREFResolution;
/* 2736 */     boolean mustAddOrNotOppositeIsMany = false;
/*      */     
/* 2738 */     int size = 0;
/* 2739 */     String qName = null;
/* 2740 */     int position = 0;
/* 2741 */     while (st.hasMoreTokens()) {
/*      */       
/* 2743 */       String id = st.nextToken();
/* 2744 */       int index = id.indexOf('#', 0);
/* 2745 */       if (index != -1) {
/*      */         
/* 2747 */         if (index == 0) {
/*      */           
/* 2749 */           id = id.substring(1);
/*      */         }
/*      */         else {
/*      */           
/* 2753 */           Object oldAttributes = setAttributes(null);
/*      */ 
/*      */           
/* 2756 */           InternalEObject proxy = 
/*      */             
/* 2758 */             (qName == null) ? 
/* 2759 */             (InternalEObject)createObjectFromFeatureType(object, (EStructuralFeature)eReference) : 
/* 2760 */             (InternalEObject)createObjectFromTypeName(object, qName, (EStructuralFeature)eReference);
/* 2761 */           setAttributes(oldAttributes);
/* 2762 */           if (proxy != null)
/*      */           {
/* 2764 */             handleProxy(proxy, id);
/*      */           }
/* 2766 */           this.objects.pop();
/* 2767 */           this.types.pop();
/* 2768 */           this.mixedTargets.pop();
/*      */           
/* 2770 */           qName = null;
/* 2771 */           position++;
/*      */           
/*      */           continue;
/*      */         } 
/* 2775 */       } else if (id.indexOf(':', 0) != -1) {
/*      */         
/* 2777 */         qName = id;
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 2783 */       if (isFirstID && this.extendedMetaData != null && eReference.isMany() && this.extendedMetaData.getFeatureKind((EStructuralFeature)eReference) == 4) {
/*      */         
/* 2785 */         SingleReference ref = new SingleReference(
/* 2786 */             object, 
/* 2787 */             (EStructuralFeature)eReference, 
/* 2788 */             id, 
/* 2789 */             -1, 
/* 2790 */             getLineNumber(), 
/* 2791 */             getColumnNumber());
/* 2792 */         this.forwardSingleReferences.add(ref);
/*      */         
/*      */         return;
/*      */       } 
/* 2796 */       if (!this.deferIDREFResolution) {
/*      */         
/* 2798 */         if (isFirstID) {
/*      */           
/* 2800 */           EReference eOpposite = eReference.getEOpposite();
/* 2801 */           if (eOpposite == null) {
/*      */             
/* 2803 */             mustAdd = true;
/* 2804 */             mustAddOrNotOppositeIsMany = true;
/*      */           }
/*      */           else {
/*      */             
/* 2808 */             mustAdd = !(!eOpposite.isTransient() && !eReference.isMany());
/* 2809 */             mustAddOrNotOppositeIsMany = !(!mustAdd && eOpposite.isMany());
/*      */           } 
/* 2811 */           isFirstID = false;
/*      */         } 
/*      */         
/* 2814 */         if (mustAddOrNotOppositeIsMany) {
/*      */           
/* 2816 */           EObject resolvedEObject = this.xmlResource.getEObject(id);
/* 2817 */           if (resolvedEObject != null) {
/*      */             
/* 2819 */             setFeatureValue(object, (EStructuralFeature)eReference, resolvedEObject);
/* 2820 */             qName = null;
/* 2821 */             position++;
/*      */             
/*      */             continue;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2827 */       if (mustAdd) {
/*      */         
/* 2829 */         if (size == this.capacity) {
/* 2830 */           growArrays();
/*      */         }
/* 2832 */         this.identifiers[size] = id;
/* 2833 */         this.positions[size] = position;
/* 2834 */         size++;
/*      */       } 
/* 2836 */       qName = null;
/* 2837 */       position++;
/*      */     } 
/*      */     
/* 2840 */     if (position == 0) {
/*      */       
/* 2842 */       setFeatureValue(object, (EStructuralFeature)eReference, null, -2);
/*      */     }
/* 2844 */     else if (size <= 5) {
/*      */       
/* 2846 */       for (int i = 0; i < size; i++)
/*      */       {
/* 2848 */         SingleReference ref = new SingleReference(
/* 2849 */             object, 
/* 2850 */             (EStructuralFeature)eReference, 
/* 2851 */             this.identifiers[i], 
/* 2852 */             this.positions[i], 
/* 2853 */             getLineNumber(), 
/* 2854 */             getColumnNumber());
/* 2855 */         this.forwardSingleReferences.add(ref);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2860 */       Object[] values = new Object[size];
/* 2861 */       int[] currentPositions = new int[size];
/* 2862 */       System.arraycopy(this.identifiers, 0, values, 0, size);
/* 2863 */       System.arraycopy(this.positions, 0, currentPositions, 0, size);
/*      */       
/* 2865 */       ManyReference ref = new ManyReference(
/* 2866 */           object, 
/* 2867 */           (EStructuralFeature)eReference, 
/* 2868 */           values, 
/* 2869 */           currentPositions, 
/* 2870 */           getLineNumber(), 
/* 2871 */           getColumnNumber());
/* 2872 */       this.forwardManyReferences.add(ref);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleProxy(InternalEObject proxy, String uriLiteral) {
/*      */     URI proxyURI;
/* 2879 */     if (this.oldStyleProxyURIs) {
/*      */       
/* 2881 */       proxy.eSetProxyURI(proxyURI = URI.createURI(uriLiteral.startsWith("/") ? uriLiteral : ("/" + uriLiteral)));
/*      */     }
/*      */     else {
/*      */       
/* 2885 */       URI uri = URI.createURI(uriLiteral);
/* 2886 */       if (this.uriHandler != null) {
/*      */         
/* 2888 */         uri = this.uriHandler.resolve(uri);
/*      */       }
/* 2890 */       else if (this.resolve && 
/* 2891 */         uri.isRelative() && 
/* 2892 */         uri.hasRelativePath() && (
/* 2893 */         (this.extendedMetaData == null) ? 
/* 2894 */         !this.packageRegistry.containsKey(uri.trimFragment().toString()) : (
/* 2895 */         this.extendedMetaData.getPackage(uri.trimFragment().toString()) == null))) {
/*      */         
/* 2897 */         uri = this.helper.resolve(uri, this.resourceURI);
/*      */       } 
/* 2899 */       proxy.eSetProxyURI(proxyURI = uri);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2904 */     if (proxyURI.trimFragment().equals(this.resourceURI))
/*      */     {
/* 2906 */       this.sameDocumentProxies.add(proxy);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void growArrays() {
/* 2911 */     int oldCapacity = this.capacity;
/* 2912 */     this.capacity *= 2;
/* 2913 */     Object[] newIdentifiers = new Object[this.capacity];
/* 2914 */     int[] newPositions = new int[this.capacity];
/* 2915 */     System.arraycopy(this.identifiers, 0, newIdentifiers, 0, oldCapacity);
/* 2916 */     System.arraycopy(this.positions, 0, newPositions, 0, oldCapacity);
/* 2917 */     this.identifiers = newIdentifiers;
/* 2918 */     this.positions = newPositions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isError() {
/* 2926 */     return (this.types.peek() == "error");
/*      */   }
/*      */ 
/*      */   
/*      */   static class EClassFeatureNamePair
/*      */   {
/*      */     public EClass eClass;
/*      */     
/*      */     public String featureName;
/*      */     public String namespaceURI;
/*      */     public boolean isElement;
/*      */     
/*      */     public boolean equals(Object that) {
/* 2939 */       EClassFeatureNamePair typedThat = (EClassFeatureNamePair)that;
/*      */       
/* 2941 */       if (typedThat.eClass == this.eClass && 
/* 2942 */         typedThat.isElement == this.isElement && 
/* 2943 */         typedThat.featureName.equals(this.featureName) && (
/* 2944 */         (typedThat.namespaceURI != null) ? typedThat.namespaceURI.equals(this.namespaceURI) : (this.namespaceURI == null))) return true;
/*      */       
/*      */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2950 */       return this.eClass.hashCode() ^ this.featureName.hashCode() ^ ((this.namespaceURI == null) ? 0 : this.namespaceURI.hashCode()) + (this.isElement ? 0 : 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLHandler(XMLResource xmlResource, XMLHelper helper, Map<?, ?> options) {
/* 2956 */     this.eClassFeatureNamePair = new EClassFeatureNamePair(); this.xmlResource = xmlResource; this.helper = helper; this.elements = new MyStack<String>(); this.objects = new MyEObjectStack(); this.mixedTargets = new MyStack<FeatureMap>(); this.types = new MyStack(); this.prefixesToFactories = new HashMap<String, EFactory>(); this.forwardSingleReferences = new ArrayList<SingleReference>(); this.forwardManyReferences = new ArrayList<ManyReference>(); this.sameDocumentProxies = new ArrayList<InternalEObject>(); this.identifiers = new Object[64]; this.positions = new int[64]; this.capacity = 64; this.resourceSet = xmlResource.getResourceSet(); this.packageRegistry = (this.resourceSet == null) ? EPackage.Registry.INSTANCE : this.resourceSet.getPackageRegistry(); this.resourceURI = xmlResource.getURI(); this.extent = (InternalEList<EObject>)xmlResource.getContents(); if (Boolean.TRUE.equals(options.get("DEFER_ATTACHMENT"))) this.deferredExtent = new ArrayList<EObject>();  this.resolve = (this.resourceURI != null && this.resourceURI.isHierarchical() && !this.resourceURI.isRelative()); this.eObjectToExtensionMap = xmlResource.getEObjectToExtensionMap(); this.eObjectToExtensionMap.clear(); helper.setOptions(options); if (Boolean.TRUE.equals(options.get("DISABLE_NOTIFY"))) this.disableNotify = true;  this.notFeatures = new HashSet<String>(); this.notFeatures.add("xsi:type"); this.notFeatures.add("xsi:schemaLocation"); this.notFeatures.add("xsi:noNamespaceSchemaLocation"); this.xmlMap = (XMLResource.XMLMap)options.get("XML_MAP"); helper.setXMLMap(this.xmlMap); if (this.xmlMap != null) this.idAttribute = this.xmlMap.getIDAttributeName();  Object extendedMetaDataOption = options.get("EXTENDED_META_DATA"); setExtendedMetaDataOption(extendedMetaDataOption); this.recordUnknownFeature = Boolean.TRUE.equals(options.get("RECORD_UNKNOWN_FEATURE")); if (this.recordUnknownFeature && this.extendedMetaData == null)
/*      */       setExtendedMetaDataOption(Boolean.TRUE);  this.useNewMethods = Boolean.FALSE.equals(options.get("USE_DEPRECATED_METHODS")); XMLOptions xmlOptions = (XMLOptions)options.get("XML_OPTIONS"); if (xmlOptions != null) { this.processSchemaLocations = xmlOptions.isProcessSchemaLocations(); this.externalURIToLocations = xmlOptions.getExternalSchemaLocations(); if (this.processSchemaLocations || this.externalURIToLocations != null) { if (this.extendedMetaData == null)
/*      */           setExtendedMetaDataOption(Boolean.TRUE);  this.ecoreBuilder = xmlOptions.getEcoreBuilder(); if (this.ecoreBuilder == null) { this.ecoreBuilder = createEcoreBuilder(options, this.extendedMetaData); } else { this.ecoreBuilder.setExtendedMetaData(this.extendedMetaData); }  }  this.processAnyXML = xmlOptions.isProcessAnyXML(); if (this.processAnyXML && this.extendedMetaData == null)
/*      */         setExtendedMetaDataOption(Boolean.TRUE);  }  if (this.extendedMetaData != null) { AnyType anyType = XMLTypeFactory.eINSTANCE.createAnyType(); this.mixedTargets.push(anyType.getMixed()); this.text = new StringBuffer(); }  this.anyType = (EClass)options.get("ANY_TYPE"); this.anySimpleType = (EClass)options.get("ANY_SIMPLE_TYPE"); if (this.anyType == null) { this.anyType = XMLTypePackage.eINSTANCE.getAnyType(); this.anySimpleType = XMLTypePackage.eINSTANCE.getSimpleAnyType(); }  helper.setAnySimpleType(this.anySimpleType); Map<EClassFeatureNamePair, EStructuralFeature> newEClassFeatureNamePairToEStructuralFeatureMap = (Map<EClassFeatureNamePair, EStructuralFeature>)options.get("USE_XML_NAME_TO_FEATURE_MAP"); this.eClassFeatureNamePairToEStructuralFeatureMap = newEClassFeatureNamePairToEStructuralFeatureMap; if (this.eClassFeatureNamePairToEStructuralFeatureMap == null) { this.eClassFeatureNamePairToEStructuralFeatureMap = new HashMap<EClassFeatureNamePair, EStructuralFeature>(); } else { this.isOptionUseXMLNameToFeatureSet = true; }  this.recordAnyTypeNSDecls = Boolean.TRUE.equals(options.get("RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS")); this.hrefAttribute = "href"; if (Boolean.TRUE.equals(options.get("USE_ENCODED_ATTRIBUTE_STYLE")))
/*      */       this.hrefAttribute = null;  if (Boolean.TRUE.equals(options.get("DEFER_IDREF_RESOLUTION")))
/*      */       helper.setCheckForDuplicates(this.deferIDREFResolution = true);  if (Boolean.TRUE.equals(options.get("CONFIGURATION_CACHE")))
/*      */       this.useConfigurationCache = true;  this.uriHandler = (XMLResource.URIHandler)options.get("URI_HANDLER"); this.resourceEntityHandler = (XMLResource.ResourceEntityHandler)options.get("RESOURCE_ENTITY_HANDLER"); if (this.resourceEntityHandler != null) { this.resourceEntityHandler.reset(); if (this.uriHandler == null && this.resourceEntityHandler instanceof XMLResource.URIHandler) { this.uriHandler = (XMLResource.URIHandler)this.resourceEntityHandler; this.uriHandler.setBaseURI(this.resourceURI); }  }  if (Boolean.TRUE.equals(options.get("SUPPRESS_DOCUMENT_ROOT")))
/*      */       this.suppressDocumentRoot = true;  if (Boolean.TRUE.equals(options.get("LAX_WILDCARD_PROCESSING")))
/* 2964 */       this.laxWildcardProcessing = true;  } @Deprecated protected EStructuralFeature getFeature(EObject object, String prefix, String name) { EClass eClass = object.eClass();
/* 2965 */     String uri = this.helper.getURI(prefix);
/* 2966 */     EStructuralFeature result = this.helper.getFeature(eClass, uri, name, true);
/* 2967 */     if (result == null)
/*      */     {
/* 2969 */       this.helper.getFeature(eClass, uri, name, false);
/*      */     }
/* 2971 */     return result; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
/* 2980 */     String uri = this.helper.getURI(prefix);
/* 2981 */     EClass eClass = object.eClass();
/* 2982 */     this.eClassFeatureNamePair.eClass = eClass;
/* 2983 */     this.eClassFeatureNamePair.featureName = name;
/* 2984 */     this.eClassFeatureNamePair.namespaceURI = uri;
/* 2985 */     this.eClassFeatureNamePair.isElement = isElement;
/* 2986 */     EStructuralFeature result = this.eClassFeatureNamePairToEStructuralFeatureMap.get(this.eClassFeatureNamePair);
/* 2987 */     if (result == null) {
/*      */       
/* 2989 */       result = this.helper.getFeature(eClass, uri, name, isElement);
/*      */       
/* 2991 */       if (result == null)
/*      */       {
/* 2993 */         if (this.extendedMetaData != null) {
/*      */           
/* 2995 */           EStructuralFeature wildcardFeature = 
/* 2996 */             isElement ? 
/* 2997 */             this.extendedMetaData.getElementWildcardAffiliation(eClass, uri, name) : 
/* 2998 */             this.extendedMetaData.getAttributeWildcardAffiliation(eClass, uri, name);
/* 2999 */           if (wildcardFeature != null) {
/*      */             
/* 3001 */             int processingKind = this.laxWildcardProcessing ? 2 : this.extendedMetaData.getProcessingKind(wildcardFeature);
/* 3002 */             switch (processingKind) {
/*      */ 
/*      */ 
/*      */               
/*      */               case 2:
/*      */               case 3:
/* 3008 */                 result = this.extendedMetaData.demandFeature(uri, name, isElement);
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/*      */         } else {
/* 3018 */           result = getFeature(object, prefix, name);
/*      */         } 
/*      */       }
/*      */       
/* 3022 */       EClassFeatureNamePair entry = new EClassFeatureNamePair();
/* 3023 */       entry.eClass = eClass;
/* 3024 */       entry.featureName = name;
/* 3025 */       entry.namespaceURI = uri;
/* 3026 */       entry.isElement = isElement;
/* 3027 */       this.eClassFeatureNamePairToEStructuralFeatureMap.put(entry, result);
/*      */     } 
/*      */     
/* 3030 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getXMLEncoding(byte[] bytes) {
/*      */     int lastQuoteIndex;
/* 3039 */     String javaEncoding = null;
/*      */     
/* 3041 */     if (bytes.length >= 4)
/*      */     {
/* 3043 */       if ((bytes[0] == -2 && bytes[1] == -1) || (
/* 3044 */         bytes[0] == 0 && bytes[1] == 60)) {
/* 3045 */         javaEncoding = "UnicodeBig";
/* 3046 */       } else if ((bytes[0] == -1 && bytes[1] == -2) || (
/* 3047 */         bytes[0] == 60 && bytes[1] == 0)) {
/* 3048 */         javaEncoding = "UnicodeLittle";
/* 3049 */       } else if (bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
/* 3050 */         javaEncoding = "UTF8";
/*      */       } 
/*      */     }
/* 3053 */     String header = null;
/*      */ 
/*      */     
/*      */     try {
/* 3057 */       if (javaEncoding != null) {
/* 3058 */         header = new String(bytes, 0, bytes.length, javaEncoding);
/*      */       } else {
/* 3060 */         header = new String(bytes, 0, bytes.length);
/*      */       } 
/* 3062 */     } catch (UnsupportedEncodingException e) {
/*      */       
/* 3064 */       return null;
/*      */     } 
/*      */     
/* 3067 */     if (!header.startsWith("<?xml")) {
/* 3068 */       return "UTF-8";
/*      */     }
/* 3070 */     int endOfXMLPI = header.indexOf("?>");
/* 3071 */     int encodingIndex = header.indexOf("encoding", 6);
/*      */     
/* 3073 */     if (encodingIndex == -1 || encodingIndex > endOfXMLPI) {
/* 3074 */       return "UTF-8";
/*      */     }
/* 3076 */     int firstQuoteIndex = header.indexOf('"', encodingIndex);
/*      */ 
/*      */     
/* 3079 */     if (firstQuoteIndex == -1 || firstQuoteIndex > endOfXMLPI) {
/*      */       
/* 3081 */       firstQuoteIndex = header.indexOf('\'', encodingIndex);
/* 3082 */       lastQuoteIndex = header.indexOf('\'', firstQuoteIndex + 1);
/*      */     } else {
/*      */       
/* 3085 */       lastQuoteIndex = header.indexOf('"', firstQuoteIndex + 1);
/*      */     } 
/* 3087 */     return header.substring(firstQuoteIndex + 1, lastQuoteIndex);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleComment(String comment) {
/* 3092 */     FeatureMap featureMap = this.mixedTargets.peek();
/* 3093 */     featureMap.add((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT, comment);
/* 3094 */     this.text = null;
/*      */   }
/*      */   
/*      */   protected void handleMixedText() {
/* 3098 */     FeatureMap featureMap = this.mixedTargets.peek();
/* 3099 */     featureMap.add((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, this.text.toString());
/* 3100 */     this.text = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleCDATA() {
/* 3105 */     FeatureMap featureMap = this.mixedTargets.peek();
/* 3106 */     featureMap.add((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA, this.text.toString());
/* 3107 */     this.text = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleProcessingInstruction(String target, String data) {
/* 3112 */     FeatureMap featureMap = this.mixedTargets.peek();
/* 3113 */     FeatureMapUtil.addProcessingInstruction(featureMap, target, data);
/* 3114 */     this.text = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EcoreBuilder createEcoreBuilder(Map<?, ?> options, ExtendedMetaData extendedMetaData) {
/* 3119 */     return (EcoreBuilder)new DefaultEcoreBuilder(extendedMetaData);
/*      */   }
/*      */   
/*      */   protected abstract String getXSIType();
/*      */   
/*      */   protected abstract void handleObjectAttribs(EObject paramEObject);
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */