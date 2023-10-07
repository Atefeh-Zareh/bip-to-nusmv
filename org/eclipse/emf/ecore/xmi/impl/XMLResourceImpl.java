/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.WeakHashMap;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.xmi.DOMHandler;
/*     */ import org.eclipse.emf.ecore.xmi.DOMHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.ecore.xmi.XMLSave;
/*     */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLResourceImpl
/*     */   extends ResourceImpl
/*     */   implements XMLResource
/*     */ {
/*     */   protected Map<String, EObject> idToEObjectMap;
/*     */   protected Map<EObject, String> eObjectToIDMap;
/*     */   protected Map<EObject, AnyType> eObjectToExtensionMap;
/*     */   protected String encoding;
/*     */   protected String xmlVersion;
/*     */   protected boolean useZip;
/*     */   protected String publicId;
/*     */   protected String systemId;
/*     */   protected DOMHandler domHandler;
/*  83 */   protected static final Map<EObject, String> DETACHED_EOBJECT_TO_ID_MAP = Collections.synchronizedMap(new WeakHashMap<EObject, String>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLResourceImpl() {
/*  91 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLResourceImpl(URI uri) {
/* 100 */     super(uri);
/* 101 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void init() {
/* 106 */     this.encoding = "ASCII";
/* 107 */     this.xmlVersion = "1.0";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean useIDs() {
/* 112 */     return !(this.eObjectToIDMap == null && this.idToEObjectMap == null && !useUUIDs());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean useIDAttributes() {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean useUUIDs() {
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean assignIDsWhileLoading() {
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Object, Object> getDefaultSaveOptions() {
/* 132 */     if (this.defaultSaveOptions == null)
/*     */     {
/* 134 */       this.defaultSaveOptions = new HashMap<Object, Object>();
/*     */     }
/* 136 */     return this.defaultSaveOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Object, Object> getDefaultLoadOptions() {
/* 141 */     if (this.defaultLoadOptions == null)
/*     */     {
/* 143 */       this.defaultLoadOptions = new HashMap<Object, Object>();
/*     */     }
/* 145 */     return this.defaultLoadOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLHelper createXMLHelper() {
/* 150 */     return new XMLHelperImpl(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLLoad createXMLLoad() {
/* 155 */     return new XMLLoadImpl(createXMLHelper());
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLSave createXMLSave() {
/* 160 */     return new XMLSaveImpl(createXMLHelper());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
/* 166 */     XMLLoad xmlLoad = createXMLLoad();
/*     */     
/* 168 */     if (options == null)
/*     */     {
/* 170 */       options = Collections.EMPTY_MAP;
/*     */     }
/*     */     
/* 173 */     XMLResource.ResourceHandler handler = (XMLResource.ResourceHandler)options.get("RESOURCE_HANDLER");
/*     */     
/* 175 */     if (handler != null)
/*     */     {
/* 177 */       handler.preLoad(this, inputStream, options);
/*     */     }
/*     */     
/* 180 */     xmlLoad.load(this, inputStream, options);
/* 181 */     xmlLoad = null;
/*     */     
/* 183 */     if (handler != null)
/*     */     {
/* 185 */       handler.postLoad(this, inputStream, options);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
/* 192 */     XMLSave xmlSave = createXMLSave();
/*     */     
/* 194 */     if (options == null)
/*     */     {
/* 196 */       options = Collections.EMPTY_MAP;
/*     */     }
/*     */     
/* 199 */     XMLResource.ResourceHandler handler = (XMLResource.ResourceHandler)options.get("RESOURCE_HANDLER");
/*     */     
/* 201 */     if (handler != null)
/*     */     {
/* 203 */       handler.preSave(this, outputStream, options);
/*     */     }
/*     */     
/* 206 */     xmlSave.save(this, outputStream, options);
/*     */ 
/*     */     
/* 209 */     if (handler != null)
/*     */     {
/* 211 */       handler.postSave(this, outputStream, options);
/*     */     }
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
/*     */   public final void save(Writer writer, Map<?, ?> options) throws IOException {
/* 227 */     if (this.defaultSaveOptions == null || this.defaultSaveOptions.isEmpty()) {
/*     */       
/* 229 */       doSave(writer, options);
/*     */     }
/* 231 */     else if (options == null) {
/*     */       
/* 233 */       doSave(writer, this.defaultSaveOptions);
/*     */     }
/*     */     else {
/*     */       
/* 237 */       Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.defaultSaveOptions);
/* 238 */       mergedOptions.putAll(options);
/* 239 */       doSave(writer, mergedOptions);
/*     */     } 
/*     */     
/* 242 */     setModified(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSave(Writer writer, Map<?, ?> options) throws IOException {
/* 247 */     XMLSave xmlSave = createXMLSave();
/*     */     
/* 249 */     if (options == null)
/*     */     {
/* 251 */       options = Collections.EMPTY_MAP;
/*     */     }
/*     */     
/* 254 */     xmlSave.save(this, writer, options);
/*     */   }
/*     */ 
/*     */   
/*     */   public Document save(Document doc, Map<?, ?> options, DOMHandler handler) {
/* 259 */     XMLSave xmlSave = createXMLSave();
/* 260 */     this.domHandler = handler;
/* 261 */     if (this.domHandler == null)
/*     */     {
/* 263 */       this.domHandler = new DefaultDOMHandlerImpl();
/*     */     }
/* 265 */     Document document = doc;
/* 266 */     if (document == null) {
/*     */       
/*     */       try {
/*     */         
/* 270 */         document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
/*     */       }
/* 272 */       catch (Exception e) {
/*     */         
/* 274 */         throw new RuntimeException(e.getMessage());
/*     */       } 
/*     */     }
/* 277 */     if (this.defaultSaveOptions == null || this.defaultSaveOptions.isEmpty())
/*     */     {
/* 279 */       return xmlSave.save(this, document, (options == null) ? Collections.EMPTY_MAP : options, this.domHandler);
/*     */     }
/* 281 */     if (options == null)
/*     */     {
/* 283 */       return xmlSave.save(this, document, this.defaultSaveOptions, this.domHandler);
/*     */     }
/*     */ 
/*     */     
/* 287 */     Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.defaultSaveOptions);
/* 288 */     mergedOptions.putAll(options);
/* 289 */     return xmlSave.save(this, document, mergedOptions, this.domHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DOMHelper getDOMHelper() {
/* 295 */     return this.domHandler.getDOMHelper();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useZip() {
/* 301 */     return this.useZip;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUseZip(boolean useZip) {
/* 306 */     this.useZip = useZip;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/* 311 */     return this.publicId;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/* 315 */     return this.systemId;
/*     */   }
/*     */   
/*     */   public void setDoctypeInfo(String publicId, String systemId) {
/* 319 */     this.publicId = publicId;
/* 320 */     this.systemId = systemId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncoding() {
/* 325 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEncoding(String encoding) {
/* 330 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXMLVersion() {
/* 335 */     return this.xmlVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXMLVersion(String version) {
/* 340 */     this.xmlVersion = version;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, EObject> getIDToEObjectMap() {
/* 345 */     if (this.idToEObjectMap == null)
/*     */     {
/* 347 */       this.idToEObjectMap = new HashMap<String, EObject>();
/*     */     }
/*     */     
/* 350 */     return this.idToEObjectMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<EObject, String> getEObjectToIDMap() {
/* 355 */     if (this.eObjectToIDMap == null)
/*     */     {
/* 357 */       this.eObjectToIDMap = new HashMap<EObject, String>();
/*     */     }
/*     */     
/* 360 */     return this.eObjectToIDMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<EObject, AnyType> getEObjectToExtensionMap() {
/* 365 */     if (this.eObjectToExtensionMap == null)
/*     */     {
/* 367 */       this.eObjectToExtensionMap = new HashMap<EObject, AnyType>();
/*     */     }
/* 369 */     return this.eObjectToExtensionMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getID(EObject eObject) {
/* 377 */     if (this.eObjectToIDMap == null)
/*     */     {
/* 379 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 383 */     return this.eObjectToIDMap.get(eObject);
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
/*     */   public void setID(EObject eObject, String id) {
/* 396 */     Object oldID = (id != null) ? getEObjectToIDMap().put(eObject, id) : getEObjectToIDMap().remove(eObject);
/*     */     
/* 398 */     if (oldID != null)
/*     */     {
/* 400 */       getIDToEObjectMap().remove(oldID);
/*     */     }
/*     */     
/* 403 */     if (id != null)
/*     */     {
/* 405 */       getIDToEObjectMap().put(id, eObject);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURIFragment(EObject eObject) {
/* 415 */     String id = getID(eObject);
/*     */     
/* 417 */     if (id != null)
/*     */     {
/* 419 */       return id;
/*     */     }
/*     */ 
/*     */     
/* 423 */     return super.getURIFragment(eObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EObject getEObjectByID(String id) {
/* 430 */     if (this.idToEObjectMap != null) {
/*     */       
/* 432 */       EObject eObject = this.idToEObjectMap.get(id);
/* 433 */       if (eObject != null)
/*     */       {
/* 435 */         return eObject;
/*     */       }
/*     */     } 
/*     */     
/* 439 */     return useIDAttributes() ? super.getEObjectByID(id) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isPath(String uriFragment) {
/* 444 */     return uriFragment.startsWith("/");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAttachedDetachedHelperRequired() {
/* 450 */     return !(!useIDs() && !super.isAttachedDetachedHelperRequired());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attachedHelper(EObject eObject) {
/* 456 */     super.attachedHelper(eObject);
/*     */     
/* 458 */     if (useIDs()) {
/*     */       
/* 460 */       String id = getID(eObject);
/* 461 */       if (useUUIDs() && id == null) {
/*     */         
/* 463 */         if (assignIDsWhileLoading() || !isLoading())
/*     */         {
/* 465 */           id = DETACHED_EOBJECT_TO_ID_MAP.remove(eObject);
/* 466 */           if (id == null)
/*     */           {
/* 468 */             id = EcoreUtil.generateUUID();
/*     */           }
/* 470 */           setID(eObject, id);
/*     */         }
/*     */       
/* 473 */       } else if (id != null) {
/*     */         
/* 475 */         getIDToEObjectMap().put(id, eObject);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void detachedHelper(EObject eObject) {
/* 483 */     if (useIDs()) {
/*     */       
/* 485 */       if (useUUIDs())
/*     */       {
/* 487 */         DETACHED_EOBJECT_TO_ID_MAP.put(eObject, getID(eObject));
/*     */       }
/*     */       
/* 490 */       if (this.idToEObjectMap != null && this.eObjectToIDMap != null)
/*     */       {
/* 492 */         setID(eObject, (String)null);
/*     */       }
/*     */     } 
/*     */     
/* 496 */     super.detachedHelper(eObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doUnload() {
/* 506 */     super.doUnload();
/*     */     
/* 508 */     if (this.idToEObjectMap != null)
/*     */     {
/* 510 */       this.idToEObjectMap.clear();
/*     */     }
/*     */     
/* 513 */     if (this.eObjectToIDMap != null)
/*     */     {
/* 515 */       this.eObjectToIDMap.clear();
/*     */     }
/*     */     
/* 518 */     if (this.eObjectToExtensionMap != null)
/*     */     {
/* 520 */       this.eObjectToExtensionMap.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toKeyString() {
/* 531 */     StringBuffer result = new StringBuffer("Key type: ");
/* 532 */     result.append(getClass().toString());
/* 533 */     if (this.idToEObjectMap != null) {
/*     */       
/* 535 */       TreeMap<String, String> tree = new TreeMap<String, String>();
/* 536 */       for (String key : this.idToEObjectMap.keySet()) {
/*     */         
/* 538 */         if (key != null)
/*     */         {
/* 540 */           tree.put(key, key);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 545 */       for (String key : tree.values()) {
/*     */         
/* 547 */         Object value = this.idToEObjectMap.get(key);
/* 548 */         result.append("\r\n\t[Key=" + key + ", Value=" + value + "]");
/*     */       } 
/*     */     } 
/* 551 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void load(Node node, Map<?, ?> options) throws IOException {
/* 556 */     if (!this.isLoaded) {
/*     */       
/* 558 */       Notification notification = setLoaded(true);
/* 559 */       this.isLoading = true;
/*     */       
/* 561 */       if (this.errors != null)
/*     */       {
/* 563 */         this.errors.clear();
/*     */       }
/*     */       
/* 566 */       if (this.warnings != null)
/*     */       {
/* 568 */         this.warnings.clear();
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 573 */         if (this.defaultLoadOptions == null || this.defaultLoadOptions.isEmpty())
/*     */         {
/* 575 */           doLoad(node, options);
/*     */         }
/* 577 */         else if (options == null)
/*     */         {
/* 579 */           doLoad(node, this.defaultLoadOptions);
/*     */         }
/*     */         else
/*     */         {
/* 583 */           Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.defaultLoadOptions);
/* 584 */           mergedOptions.putAll(options);
/*     */           
/* 586 */           doLoad(node, mergedOptions);
/*     */         }
/*     */       
/*     */       } finally {
/*     */         
/* 591 */         this.isLoading = false;
/*     */         
/* 593 */         if (notification != null)
/*     */         {
/* 595 */           eNotify(notification);
/*     */         }
/*     */         
/* 598 */         setModified(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doLoad(Node node, Map<?, ?> options) throws IOException {
/* 608 */     XMLLoad xmlLoad = createXMLLoad();
/*     */     
/* 610 */     if (options == null)
/*     */     {
/* 612 */       options = Collections.EMPTY_MAP;
/*     */     }
/*     */     
/* 615 */     xmlLoad.load(this, node, options);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void load(InputSource inputSource, Map<?, ?> options) throws IOException {
/* 620 */     if (!this.isLoaded) {
/*     */       
/* 622 */       Notification notification = setLoaded(true);
/* 623 */       this.isLoading = true;
/*     */       
/* 625 */       if (this.errors != null)
/*     */       {
/* 627 */         this.errors.clear();
/*     */       }
/*     */       
/* 630 */       if (this.warnings != null)
/*     */       {
/* 632 */         this.warnings.clear();
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 637 */         if (this.defaultLoadOptions == null || this.defaultLoadOptions.isEmpty())
/*     */         {
/* 639 */           doLoad(inputSource, options);
/*     */         }
/* 641 */         else if (options == null)
/*     */         {
/* 643 */           doLoad(inputSource, this.defaultLoadOptions);
/*     */         }
/*     */         else
/*     */         {
/* 647 */           Map<Object, Object> mergedOptions = new HashMap<Object, Object>(this.defaultLoadOptions);
/* 648 */           mergedOptions.putAll(options);
/*     */           
/* 650 */           doLoad(inputSource, mergedOptions);
/*     */         }
/*     */       
/*     */       } finally {
/*     */         
/* 655 */         this.isLoading = false;
/*     */         
/* 657 */         if (notification != null)
/*     */         {
/* 659 */           eNotify(notification);
/*     */         }
/*     */         
/* 662 */         setModified(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLoad(InputSource inputSource, Map<?, ?> options) throws IOException {
/* 669 */     XMLLoad xmlLoad = createXMLLoad();
/*     */     
/* 671 */     if (options == null)
/*     */     {
/* 673 */       options = Collections.EMPTY_MAP;
/*     */     }
/* 675 */     xmlLoad.load(this, inputSource, options);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */