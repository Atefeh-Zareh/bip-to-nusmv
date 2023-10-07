/*     */ package org.eclipse.emf.ecore.xmi.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
/*     */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.DOMHandler;
/*     */ import org.eclipse.emf.ecore.xmi.EcoreBuilder;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
/*     */ import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLProcessor
/*     */ {
/*     */   protected EcoreBuilder ecoreBuilder;
/*     */   protected static final String XML_EXTENSION = "xml";
/*     */   protected static final String STAR_EXTENSION = "*";
/*  66 */   protected static final URI XML_URI = URI.createFileURI("xml");
/*     */   
/*     */   protected Map<String, Resource.Factory> registrations;
/*     */   
/*  70 */   protected Map<Object, Object> loadOptions = new HashMap<Object, Object>();
/*     */   
/*  72 */   protected Map<Object, Object> saveOptions = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*     */   protected ExtendedMetaData extendedMetaData;
/*     */ 
/*     */   
/*     */   protected EPackage.Registry registry;
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLProcessor(EPackage.Registry registry) {
/*  84 */     this.registry = registry;
/*  85 */     this.extendedMetaData = createExtendedMetaData();
/*  86 */     this.ecoreBuilder = createEcoreBuilder();
/*  87 */     this.loadOptions.put("EXTENDED_META_DATA", this.extendedMetaData);
/*  88 */     this.loadOptions.put("USE_PARSER_POOL", new XMLParserPoolImpl(true));
/*  89 */     this.loadOptions.put("USE_XML_NAME_TO_FEATURE_MAP", new HashMap<Object, Object>());
/*  90 */     this.loadOptions.put("USE_DEPRECATED_METHODS", Boolean.FALSE);
/*  91 */     this.loadOptions.put("CONFIGURATION_CACHE", Boolean.TRUE);
/*  92 */     this.saveOptions.put("EXTENDED_META_DATA", this.extendedMetaData);
/*  93 */     this.saveOptions.put("USE_CACHED_LOOKUP_TABLE", new ArrayList());
/*  94 */     this.saveOptions.put("CONFIGURATION_CACHE", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLProcessor() {
/* 104 */     this.extendedMetaData = createExtendedMetaData();
/* 105 */     this.ecoreBuilder = createEcoreBuilder();
/*     */     
/* 107 */     this.loadOptions.put("EXTENDED_META_DATA", this.extendedMetaData);
/* 108 */     this.loadOptions.put("USE_PARSER_POOL", new XMLParserPoolImpl(true));
/* 109 */     this.loadOptions.put("USE_DEPRECATED_METHODS", Boolean.FALSE);
/* 110 */     this.loadOptions.put("CONFIGURATION_CACHE", Boolean.TRUE);
/* 111 */     this.saveOptions.put("EXTENDED_META_DATA", this.extendedMetaData);
/* 112 */     this.saveOptions.put("CONFIGURATION_CACHE", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLProcessor(URI schemaURI) throws SAXException {
/* 121 */     this(Collections.singleton(schemaURI));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLProcessor(Collection<URI> schemaURIs) throws SAXException {
/* 131 */     this((EPackage.Registry)new EPackageRegistryImpl());
/* 132 */     this.loadOptions.put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/* 133 */     this.loadOptions.put("USE_LEXICAL_HANDLER", Boolean.TRUE);
/* 134 */     this.saveOptions.put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/* 135 */     this.saveOptions.put("SCHEMA_LOCATION", Boolean.TRUE);
/*     */     
/*     */     try {
/* 138 */       for (Resource resource : this.ecoreBuilder.generate(schemaURIs))
/*     */       {
/* 140 */         for (EPackage ePackage : EcoreUtil.getObjectsByType((Collection)resource.getContents(), (EClassifier)EcorePackage.Literals.EPACKAGE))
/*     */         {
/* 142 */           EcoreUtil.freeze(ePackage);
/*     */         }
/*     */       }
/*     */     
/* 146 */     } catch (InvocationTargetException ie) {
/*     */       
/* 148 */       throw new SAXException((Exception)ie.getTargetException());
/*     */     }
/* 150 */     catch (Exception e) {
/*     */       
/* 152 */       throw new SAXException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Map<String, Resource.Factory> getRegistrations() {
/* 158 */     if (this.registrations == null) {
/*     */       
/* 160 */       Map<String, Resource.Factory> result = new HashMap<String, Resource.Factory>();
/* 161 */       result.put("*", new XMLResourceFactoryImpl());
/* 162 */       this.registrations = result;
/*     */     } 
/*     */     
/* 165 */     return this.registrations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage.Registry getEPackageRegistry() {
/* 174 */     return this.registry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedMetaData getExtendedMetaData() {
/* 184 */     return this.extendedMetaData;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource load(String systemId, Map<?, ?> options) throws IOException {
/* 200 */     ResourceSet resourceSet = createResourceSet();
/* 201 */     XMLResource resource = (XMLResource)resourceSet.createResource(URI.createURI(systemId));
/* 202 */     InputSource inputSource = new InputSource();
/* 203 */     inputSource.setSystemId(systemId);
/* 204 */     if (options != null) {
/*     */       
/* 206 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.loadOptions);
/* 207 */       mergedOptions.putAll(options);
/* 208 */       resource.load(inputSource, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 212 */       resource.load(inputSource, this.loadOptions);
/*     */     } 
/* 214 */     resourceSet.getPackageRegistry().putAll((Map)this.registry);
/* 215 */     return (Resource)resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public Resource load(InputStream is, Map<?, ?> options) throws IOException {
/* 220 */     ResourceSet resourceSet = createResourceSet();
/* 221 */     Resource resource = resourceSet.createResource(XML_URI);
/* 222 */     if (options != null) {
/*     */       
/* 224 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.loadOptions);
/* 225 */       mergedOptions.putAll(options);
/* 226 */       resource.load(is, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 230 */       resource.load(is, this.loadOptions);
/*     */     } 
/* 232 */     resourceSet.getPackageRegistry().putAll((Map)this.registry);
/* 233 */     return resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public Resource load(InputSource inputSource, Map<?, ?> options) throws IOException {
/* 238 */     ResourceSet resourceSet = createResourceSet();
/* 239 */     XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
/* 240 */     if (options != null) {
/*     */       
/* 242 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.loadOptions);
/* 243 */       mergedOptions.putAll(options);
/* 244 */       resource.load(inputSource, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 248 */       resource.load(inputSource, this.loadOptions);
/*     */     } 
/* 250 */     resourceSet.getPackageRegistry().putAll((Map)this.registry);
/* 251 */     return (Resource)resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public Resource load(Node node, Map<?, ?> options) throws IOException {
/* 256 */     ResourceSet resourceSet = createResourceSet();
/* 257 */     XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
/* 258 */     if (options != null) {
/*     */       
/* 260 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.loadOptions);
/* 261 */       mergedOptions.putAll(options);
/* 262 */       resource.load(node, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 266 */       resource.load(node, this.loadOptions);
/*     */     } 
/* 268 */     resourceSet.getPackageRegistry().putAll((Map)this.registry);
/* 269 */     return (Resource)resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void save(OutputStream outputStream, Resource resource, Map<?, ?> options) throws IOException {
/* 274 */     if (options != null) {
/*     */       
/* 276 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.saveOptions);
/* 277 */       mergedOptions.putAll(options);
/* 278 */       resource.save(outputStream, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 282 */       resource.save(outputStream, this.saveOptions);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save(Writer writer, Resource resource, Map<?, ?> options) throws IOException {
/* 288 */     if (options != null) {
/*     */       
/* 290 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.saveOptions);
/* 291 */       mergedOptions.putAll(options);
/* 292 */       ((XMLResource)resource).save(writer, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 296 */       ((XMLResource)resource).save(writer, this.saveOptions);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save(Document document, Resource resource, DOMHandler handler, Map<?, ?> options) throws IOException {
/* 302 */     if (options != null) {
/*     */       
/* 304 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.saveOptions);
/* 305 */       mergedOptions.putAll(options);
/* 306 */       ((XMLResource)resource).save(document, mergedOptions, handler);
/*     */     }
/*     */     else {
/*     */       
/* 310 */       ((XMLResource)resource).save(document, this.saveOptions, handler);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String saveToString(Resource resource, Map<?, ?> options) throws IOException {
/* 316 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 317 */     if (options != null) {
/*     */       
/* 319 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.saveOptions);
/* 320 */       mergedOptions.putAll(options);
/*     */       
/* 322 */       ((XMLResource)resource).save(os, mergedOptions);
/*     */     }
/*     */     else {
/*     */       
/* 326 */       ((XMLResource)resource).save(os, this.saveOptions);
/*     */     } 
/* 328 */     return os.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceSet createResourceSet() {
/* 333 */     ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
/* 334 */     resourceSetImpl.getResourceFactoryRegistry().getExtensionToFactoryMap().putAll(getRegistrations());
/* 335 */     return (ResourceSet)resourceSetImpl;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EcoreBuilder createEcoreBuilder() {
/* 340 */     return new DefaultEcoreBuilder(this.extendedMetaData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ExtendedMetaData createExtendedMetaData() {
/* 351 */     if (this.registry == null)
/*     */     {
/* 353 */       this.registry = (EPackage.Registry)new EPackageRegistryImpl();
/*     */     }
/* 355 */     return (ExtendedMetaData)new BasicExtendedMetaData(this.registry);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xm\\util\XMLProcessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */