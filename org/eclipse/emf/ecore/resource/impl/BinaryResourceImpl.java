/*      */ package org.eclipse.emf.ecore.resource.impl;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.FeatureMapUtil;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
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
/*      */ 
/*      */ 
/*      */ public class BinaryResourceImpl
/*      */   extends ResourceImpl
/*      */ {
/*      */   public static final String OPTION_BUFFER_CAPACITY = "BUFFER_CAPACITY";
/*      */   public static final int DEFAULT_BUFFER_CAPACITY = 1024;
/*      */   
/*      */   protected static int getBufferCapacity(Map<?, ?> options) {
/*   81 */     if (options != null) {
/*      */       
/*   83 */       Integer capacity = (Integer)options.get("BUFFER_CAPACITY");
/*   84 */       if (capacity != null)
/*      */       {
/*   86 */         return capacity.intValue();
/*      */       }
/*      */     } 
/*   89 */     return 1024;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BinaryResourceImpl() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public BinaryResourceImpl(URI uri) {
/*   99 */     super(uri);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
/*  105 */     boolean buffer = !(outputStream instanceof BufferedOutputStream);
/*  106 */     if (buffer) {
/*      */       
/*  108 */       int bufferCapacity = getBufferCapacity(options);
/*  109 */       if (bufferCapacity > 0) {
/*      */         
/*  111 */         outputStream = new BufferedOutputStream(outputStream, bufferCapacity);
/*      */       }
/*      */       else {
/*      */         
/*  115 */         buffer = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  121 */       EObjectOutputStream eObjectOutputStream = new EObjectOutputStream(outputStream, options);
/*  122 */       eObjectOutputStream.saveResource(this);
/*      */     }
/*      */     finally {
/*      */       
/*  126 */       if (buffer)
/*      */       {
/*  128 */         outputStream.flush();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
/*  136 */     if (!(inputStream instanceof BufferedInputStream)) {
/*      */       
/*  138 */       int bufferCapacity = getBufferCapacity(options);
/*  139 */       if (bufferCapacity > 0)
/*      */       {
/*  141 */         inputStream = new BufferedInputStream(inputStream, bufferCapacity);
/*      */       }
/*      */     } 
/*      */     
/*  145 */     EObjectInputStream eObjectInputStream = new EObjectInputStream(inputStream, options);
/*  146 */     eObjectInputStream.loadResource(this);
/*      */   }
/*      */   
/*      */   public static class BinaryIO {
/*      */     protected Version version;
/*      */     protected Resource resource;
/*      */     protected URI baseURI;
/*      */     protected Map<?, ?> options;
/*      */     protected char[] characters;
/*      */     protected InternalEObject[][] internalEObjectDataArrayBuffer;
/*      */     protected int internalEObjectDataArrayBufferCount;
/*      */     protected FeatureMap.Entry.Internal[][] featureMapEntryDataArrayBuffer;
/*      */     protected int featureMapEntryDataArrayBufferCount;
/*      */     
/*      */     public BinaryIO() {
/*  161 */       this.internalEObjectDataArrayBuffer = new InternalEObject[50][];
/*  162 */       this.internalEObjectDataArrayBufferCount = -1;
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
/*      */ 
/*      */       
/*  207 */       this.featureMapEntryDataArrayBuffer = new FeatureMap.Entry.Internal[50][];
/*  208 */       this.featureMapEntryDataArrayBufferCount = -1;
/*      */     } public enum Version {
/*      */       VERSION_1_0; } protected URI resolve(URI uri) {
/*      */       return (this.baseURI != null && uri.isRelative() && uri.hasRelativePath()) ? uri.resolve(this.baseURI) : uri;
/*  212 */     } protected FeatureMap.Entry.Internal[] allocateFeatureMapEntryArray(int length) { if (this.featureMapEntryDataArrayBufferCount == -1)
/*      */       {
/*  214 */         return new FeatureMap.Entry.Internal[length];
/*      */       }
/*      */ 
/*      */       
/*  218 */       FeatureMap.Entry.Internal[] buffer = this.featureMapEntryDataArrayBuffer[this.featureMapEntryDataArrayBufferCount];
/*  219 */       this.featureMapEntryDataArrayBuffer[this.featureMapEntryDataArrayBufferCount--] = null;
/*  220 */       return (buffer.length >= length) ? buffer : new FeatureMap.Entry.Internal[length]; }
/*      */     protected URI deresolve(URI uri) { if (this.baseURI != null && !uri.isRelative()) {
/*      */         URI deresolvedURI = uri.deresolve(this.baseURI, true, true, false);
/*      */         if (deresolvedURI.hasRelativePath() && (!uri.isPlatform() || uri.segment(0).equals(this.baseURI.segment(0))))
/*      */           uri = deresolvedURI; 
/*      */       } 
/*  226 */       return uri; } protected void recycle(FeatureMap.Entry.Internal[] values) { if (++this.featureMapEntryDataArrayBufferCount >= this.featureMapEntryDataArrayBuffer.length) {
/*      */         
/*  228 */         FeatureMap.Entry.Internal[][] newFeatureMapEntryDataArrayBuffer = new FeatureMap.Entry.Internal[this.featureMapEntryDataArrayBufferCount * 2][];
/*  229 */         System.arraycopy(this.featureMapEntryDataArrayBuffer, 0, newFeatureMapEntryDataArrayBuffer, 0, this.featureMapEntryDataArrayBufferCount);
/*  230 */         this.featureMapEntryDataArrayBuffer = newFeatureMapEntryDataArrayBuffer;
/*      */       } 
/*  232 */       this.featureMapEntryDataArrayBuffer[this.featureMapEntryDataArrayBufferCount] = values; } protected InternalEObject[] allocateInternalEObjectArray(int length) { if (this.internalEObjectDataArrayBufferCount == -1)
/*      */         return new InternalEObject[length];  InternalEObject[] buffer = this.internalEObjectDataArrayBuffer[this.internalEObjectDataArrayBufferCount]; this.internalEObjectDataArrayBuffer[this.internalEObjectDataArrayBufferCount--] = null; return (buffer.length >= length) ? buffer : new InternalEObject[length]; } protected void recycle(InternalEObject[] values) { if (++this.internalEObjectDataArrayBufferCount >= this.internalEObjectDataArrayBuffer.length) {
/*      */         InternalEObject[][] newInternalEObjectDataArrayBuffer = new InternalEObject[this.internalEObjectDataArrayBufferCount * 2][]; System.arraycopy(this.internalEObjectDataArrayBuffer, 0, newInternalEObjectDataArrayBuffer, 0, this.internalEObjectDataArrayBufferCount); this.internalEObjectDataArrayBuffer = newInternalEObjectDataArrayBuffer;
/*      */       }  this.internalEObjectDataArrayBuffer[this.internalEObjectDataArrayBufferCount] = values; } protected enum FeatureKind
/*      */     {
/*  237 */       EOBJECT_CONTAINER,
/*  238 */       EOBJECT_CONTAINER_PROXY_RESOLVING,
/*      */       
/*  240 */       EOBJECT,
/*  241 */       EOBJECT_PROXY_RESOLVING,
/*      */       
/*  243 */       EOBJECT_LIST,
/*  244 */       EOBJECT_LIST_PROXY_RESOLVING,
/*      */       
/*  246 */       EOBJECT_CONTAINMENT,
/*  247 */       EOBJECT_CONTAINMENT_PROXY_RESOLVING,
/*      */       
/*  249 */       EOBJECT_CONTAINMENT_LIST,
/*  250 */       EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING,
/*      */       
/*  252 */       BOOLEAN,
/*  253 */       BYTE,
/*  254 */       CHAR,
/*  255 */       DOUBLE,
/*  256 */       FLOAT,
/*  257 */       INT,
/*  258 */       LONG,
/*  259 */       SHORT,
/*  260 */       STRING,
/*      */       
/*  262 */       DATA,
/*  263 */       DATA_LIST,
/*      */       
/*  265 */       FEATURE_MAP;
/*      */ 
/*      */       
/*      */       public static FeatureKind get(EStructuralFeature eStructuralFeature) {
/*  269 */         if (eStructuralFeature instanceof EReference) {
/*      */           
/*  271 */           EReference eReference = (EReference)eStructuralFeature;
/*  272 */           if (eReference.isContainment()) {
/*      */             
/*  274 */             if (eReference.isResolveProxies()) {
/*      */               
/*  276 */               if (eReference.isMany())
/*      */               {
/*  278 */                 return EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING;
/*      */               }
/*      */ 
/*      */               
/*  282 */               return EOBJECT_CONTAINMENT_PROXY_RESOLVING;
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*  287 */             if (eReference.isMany())
/*      */             {
/*  289 */               return EOBJECT_CONTAINMENT_LIST;
/*      */             }
/*      */ 
/*      */             
/*  293 */             return EOBJECT_CONTAINMENT;
/*      */           } 
/*      */ 
/*      */           
/*  297 */           if (eReference.isContainer()) {
/*      */             
/*  299 */             if (eReference.isResolveProxies())
/*      */             {
/*  301 */               return EOBJECT_CONTAINER_PROXY_RESOLVING;
/*      */             }
/*      */ 
/*      */             
/*  305 */             return EOBJECT_CONTAINER;
/*      */           } 
/*      */           
/*  308 */           if (eReference.isResolveProxies()) {
/*      */             
/*  310 */             if (eReference.isMany())
/*      */             {
/*  312 */               return EOBJECT_LIST_PROXY_RESOLVING;
/*      */             }
/*      */ 
/*      */             
/*  316 */             return EOBJECT_PROXY_RESOLVING;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  321 */           if (eReference.isMany())
/*      */           {
/*  323 */             return EOBJECT_LIST;
/*      */           }
/*      */ 
/*      */           
/*  327 */           return EOBJECT;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  333 */         EAttribute eAttribute = (EAttribute)eStructuralFeature;
/*  334 */         EDataType eDataType = eAttribute.getEAttributeType();
/*  335 */         String instanceClassName = eDataType.getInstanceClassName();
/*  336 */         if (instanceClassName == "org.eclipse.emf.ecore.util.FeatureMap$Entry")
/*      */         {
/*  338 */           return FEATURE_MAP;
/*      */         }
/*  340 */         if (eAttribute.isMany())
/*      */         {
/*  342 */           return DATA_LIST;
/*      */         }
/*  344 */         if (instanceClassName == "java.lang.String")
/*      */         {
/*  346 */           return STRING;
/*      */         }
/*  348 */         if (instanceClassName == "boolean")
/*      */         {
/*  350 */           return BOOLEAN;
/*      */         }
/*  352 */         if (instanceClassName == "byte")
/*      */         {
/*  354 */           return BYTE;
/*      */         }
/*  356 */         if (instanceClassName == "char")
/*      */         {
/*  358 */           return CHAR;
/*      */         }
/*  360 */         if (instanceClassName == "double")
/*      */         {
/*  362 */           return DOUBLE;
/*      */         }
/*  364 */         if (instanceClassName == "float")
/*      */         {
/*  366 */           return FLOAT;
/*      */         }
/*  368 */         if (instanceClassName == "int")
/*      */         {
/*  370 */           return INT;
/*      */         }
/*  372 */         if (instanceClassName == "long")
/*      */         {
/*  374 */           return LONG;
/*      */         }
/*  376 */         if (instanceClassName == "short")
/*      */         {
/*  378 */           return SHORT;
/*      */         }
/*      */ 
/*      */         
/*  382 */         return DATA;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class EObjectOutputStream
/*      */     extends BinaryIO
/*      */   {
/*      */     protected OutputStream outputStream;
/*      */     
/*      */     public enum Check {
/*  393 */       NOTHING,
/*  394 */       DIRECT_RESOURCE,
/*  395 */       RESOURCE,
/*  396 */       CONTAINER;
/*      */     }
/*      */ 
/*      */     
/*      */     protected static class EPackageData
/*      */     {
/*      */       public int id;
/*      */       public BinaryResourceImpl.EObjectOutputStream.EClassData[] eClassData;
/*      */       
/*      */       public final int allocateEClassID() {
/*  406 */         for (int i = 0, length = this.eClassData.length; i < length; i++) {
/*      */           
/*  408 */           BinaryResourceImpl.EObjectOutputStream.EClassData eClassData = this.eClassData[i];
/*  409 */           if (eClassData == null)
/*      */           {
/*  411 */             return i;
/*      */           }
/*      */         } 
/*  414 */         return -1;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected static class EClassData
/*      */     {
/*      */       public int ePackageID;
/*      */       
/*      */       public int id;
/*      */       public BinaryResourceImpl.EObjectOutputStream.EStructuralFeatureData[] eStructuralFeatureData;
/*      */     }
/*      */     
/*      */     protected static class EStructuralFeatureData
/*      */     {
/*      */       public String name;
/*      */       public boolean isTransient;
/*      */       public BinaryResourceImpl.BinaryIO.FeatureKind kind;
/*      */       public EFactory eFactory;
/*      */       public EDataType eDataType;
/*      */     }
/*  435 */     protected Map<EPackage, EPackageData> ePackageDataMap = new HashMap<EPackage, EPackageData>();
/*  436 */     protected Map<EClass, EClassData> eClassDataMap = new HashMap<EClass, EClassData>();
/*  437 */     protected Map<EObject, Integer> eObjectIDMap = new HashMap<EObject, Integer>();
/*  438 */     protected Map<URI, Integer> uriToIDMap = new HashMap<URI, Integer>();
/*      */ 
/*      */     
/*      */     public EObjectOutputStream(OutputStream outputStream, Map<?, ?> options) throws IOException {
/*  442 */       this(outputStream, options, BinaryResourceImpl.BinaryIO.Version.VERSION_1_0);
/*      */     }
/*      */ 
/*      */     
/*      */     public EObjectOutputStream(OutputStream outputStream, Map<?, ?> options, BinaryResourceImpl.BinaryIO.Version version) throws IOException {
/*  447 */       this.outputStream = outputStream;
/*  448 */       this.options = options;
/*  449 */       this.version = version;
/*  450 */       writeSignature();
/*  451 */       writeVersion();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void writeSignature() throws IOException {
/*  460 */       writeByte(137);
/*  461 */       writeByte(101);
/*  462 */       writeByte(109);
/*  463 */       writeByte(102);
/*  464 */       writeByte(10);
/*  465 */       writeByte(13);
/*  466 */       writeByte(26);
/*  467 */       writeByte(10);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeVersion() throws IOException {
/*  472 */       writeByte(this.version.ordinal());
/*      */     }
/*      */ 
/*      */     
/*      */     protected EPackageData writeEPackage(EPackage ePackage) throws IOException {
/*  477 */       EPackageData ePackageData = this.ePackageDataMap.get(ePackage);
/*  478 */       if (ePackageData == null) {
/*      */         
/*  480 */         ePackageData = new EPackageData();
/*  481 */         int id = this.ePackageDataMap.size();
/*  482 */         ePackageData.id = id;
/*  483 */         ePackageData.eClassData = new EClassData[ePackage.getEClassifiers().size()];
/*  484 */         writeCompressedInt(id);
/*  485 */         writeString(ePackage.getNsURI());
/*  486 */         writeURI(EcoreUtil.getURI((EObject)ePackage));
/*  487 */         this.ePackageDataMap.put(ePackage, ePackageData);
/*      */       }
/*      */       else {
/*      */         
/*  491 */         writeCompressedInt(ePackageData.id);
/*      */       } 
/*  493 */       return ePackageData;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EClassData writeEClass(EClass eClass) throws IOException {
/*  498 */       EClassData eClassData = this.eClassDataMap.get(eClass);
/*  499 */       if (eClassData == null) {
/*      */         
/*  501 */         eClassData = new EClassData();
/*  502 */         EPackageData ePackageData = writeEPackage(eClass.getEPackage());
/*  503 */         eClassData.ePackageID = ePackageData.id;
/*  504 */         writeCompressedInt(eClassData.id = ePackageData.allocateEClassID());
/*  505 */         writeString(eClass.getName());
/*  506 */         int featureCount = eClass.getFeatureCount();
/*  507 */         EStructuralFeatureData[] eStructuralFeaturesData = eClassData.eStructuralFeatureData = new EStructuralFeatureData[featureCount];
/*  508 */         for (int i = 0; i < featureCount; i++) {
/*      */           
/*  510 */           EStructuralFeatureData eStructuralFeatureData = eStructuralFeaturesData[i] = new EStructuralFeatureData();
/*  511 */           EStructuralFeature.Internal eStructuralFeature = (EStructuralFeature.Internal)eClass.getEStructuralFeature(i);
/*  512 */           eStructuralFeatureData.name = eStructuralFeature.getName();
/*  513 */           eStructuralFeatureData.isTransient = !(!eStructuralFeature.isTransient() && (!eStructuralFeature.isContainer() || eStructuralFeature.isResolveProxies()));
/*  514 */           eStructuralFeatureData.kind = BinaryResourceImpl.BinaryIO.FeatureKind.get((EStructuralFeature)eStructuralFeature);
/*  515 */           if (eStructuralFeature instanceof EAttribute) {
/*      */             
/*  517 */             EAttribute eAttribute = (EAttribute)eStructuralFeature;
/*  518 */             EDataType eDataType = eAttribute.getEAttributeType();
/*  519 */             eStructuralFeatureData.eDataType = eDataType;
/*  520 */             eStructuralFeatureData.eFactory = eDataType.getEPackage().getEFactoryInstance();
/*      */           } 
/*      */         } 
/*  523 */         ePackageData.eClassData[eClassData.id] = eClassData;
/*  524 */         this.eClassDataMap.put(eClass, eClassData);
/*      */       }
/*      */       else {
/*      */         
/*  528 */         writeCompressedInt(eClassData.ePackageID);
/*  529 */         writeCompressedInt(eClassData.id);
/*      */       } 
/*  531 */       return eClassData;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EStructuralFeatureData writeEStructuralFeature(EStructuralFeature eStructuralFeature) throws IOException {
/*  536 */       EClass eClass = eStructuralFeature.getEContainingClass();
/*  537 */       EClassData eClassData = writeEClass(eClass);
/*  538 */       int featureID = eClass.getFeatureID(eStructuralFeature);
/*  539 */       EStructuralFeatureData eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID];
/*  540 */       writeCompressedInt(featureID);
/*  541 */       if (eStructuralFeatureData.name != null) {
/*      */         
/*  543 */         writeString(eStructuralFeatureData.name);
/*  544 */         eStructuralFeatureData.name = null;
/*      */       } 
/*  546 */       return eStructuralFeatureData;
/*      */     }
/*      */ 
/*      */     
/*      */     public void saveResource(Resource resource) throws IOException {
/*  551 */       this.resource = resource;
/*  552 */       URI uri = resource.getURI();
/*  553 */       if (uri != null && uri.isHierarchical() && !uri.isRelative())
/*      */       {
/*  555 */         this.baseURI = uri;
/*      */       }
/*      */       
/*  558 */       InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>)resource.getContents();
/*  559 */       saveEObjects(internalEList, Check.CONTAINER);
/*      */     }
/*      */ 
/*      */     
/*      */     public void saveEObjects(InternalEList<? extends InternalEObject> internalEObjects, Check check) throws IOException {
/*  564 */       int size = internalEObjects.size();
/*  565 */       InternalEObject[] values = allocateInternalEObjectArray(size);
/*  566 */       internalEObjects.basicToArray((Object[])values);
/*  567 */       writeCompressedInt(size);
/*  568 */       for (int i = 0; i < size; i++) {
/*      */         
/*  570 */         InternalEObject internalEObject = values[i];
/*  571 */         saveEObject(internalEObject, check);
/*      */       } 
/*  573 */       recycle(values);
/*      */     }
/*      */ 
/*      */     
/*      */     public void saveFeatureMap(FeatureMap.Internal featureMap) throws IOException {
/*  578 */       int size = featureMap.size();
/*  579 */       FeatureMap.Entry.Internal[] values = allocateFeatureMapEntryArray(size);
/*  580 */       featureMap.toArray((Object[])values);
/*  581 */       writeCompressedInt(size);
/*  582 */       for (int i = 0; i < size; i++) {
/*      */         
/*  584 */         FeatureMap.Entry.Internal entry = values[i];
/*  585 */         saveFeatureMapEntry(entry);
/*      */       } 
/*  587 */       recycle(values);
/*      */     }
/*      */     
/*      */     public void saveFeatureMapEntry(FeatureMap.Entry.Internal entry) throws IOException {
/*      */       String literal;
/*  592 */       EStructuralFeatureData eStructuralFeatureData = writeEStructuralFeature(entry.getEStructuralFeature());
/*  593 */       Object value = entry.getValue();
/*  594 */       switch (eStructuralFeatureData.kind) {
/*      */ 
/*      */         
/*      */         case EOBJECT:
/*      */         case EOBJECT_LIST:
/*      */         case EOBJECT_CONTAINMENT:
/*      */         case EOBJECT_CONTAINMENT_LIST:
/*  601 */           saveEObject((InternalEObject)value, Check.NOTHING);
/*      */           return;
/*      */ 
/*      */         
/*      */         case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
/*      */         case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
/*  607 */           saveEObject((InternalEObject)value, Check.DIRECT_RESOURCE);
/*      */           return;
/*      */ 
/*      */         
/*      */         case EOBJECT_PROXY_RESOLVING:
/*      */         case EOBJECT_LIST_PROXY_RESOLVING:
/*  613 */           saveEObject((InternalEObject)value, Check.RESOURCE);
/*      */           return;
/*      */ 
/*      */         
/*      */         case null:
/*  618 */           writeBoolean(((Boolean)value).booleanValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case BYTE:
/*  623 */           writeByte(((Byte)value).byteValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case CHAR:
/*  628 */           writeChar(((Character)value).charValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case DOUBLE:
/*  633 */           writeDouble(((Double)value).doubleValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case FLOAT:
/*  638 */           writeFloat(((Float)value).floatValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case INT:
/*  643 */           writeInt(((Integer)value).intValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case LONG:
/*  648 */           writeLong(((Long)value).longValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case SHORT:
/*  653 */           writeShort(((Short)value).shortValue());
/*      */           return;
/*      */ 
/*      */         
/*      */         case STRING:
/*  658 */           writeString((String)value);
/*      */           return;
/*      */ 
/*      */         
/*      */         case DATA:
/*      */         case DATA_LIST:
/*  664 */           literal = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value);
/*  665 */           writeString(literal);
/*      */           return;
/*      */       } 
/*      */ 
/*      */       
/*  670 */       throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void saveEObject(InternalEObject internalEObject, Check check) throws IOException {
/*  677 */       if (internalEObject == null) {
/*      */         
/*  679 */         writeCompressedInt(-1);
/*      */       }
/*      */       else {
/*      */         
/*  683 */         Integer id = this.eObjectIDMap.get(internalEObject);
/*  684 */         if (id == null) {
/*      */           Resource.Internal internal; Resource resource;
/*  686 */           int idValue = this.eObjectIDMap.size();
/*  687 */           writeCompressedInt(idValue);
/*  688 */           this.eObjectIDMap.put(internalEObject, Integer.valueOf(idValue));
/*  689 */           EClass eClass = internalEObject.eClass();
/*  690 */           EClassData eClassData = writeEClass(eClass);
/*  691 */           switch (check) {
/*      */ 
/*      */             
/*      */             case DIRECT_RESOURCE:
/*  695 */               internal = internalEObject.eDirectResource();
/*  696 */               if (internal != null) {
/*      */                 
/*  698 */                 writeCompressedInt(-1);
/*  699 */                 writeURI(internal.getURI(), internal.getURIFragment((EObject)internalEObject));
/*      */                 return;
/*      */               } 
/*  702 */               if (internalEObject.eIsProxy()) {
/*      */                 
/*  704 */                 writeCompressedInt(-1);
/*  705 */                 writeURI(internalEObject.eProxyURI());
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case RESOURCE:
/*  712 */               resource = internalEObject.eResource();
/*  713 */               if (resource != this.resource && resource != null) {
/*      */                 
/*  715 */                 writeCompressedInt(-1);
/*  716 */                 writeURI(resource.getURI(), resource.getURIFragment((EObject)internalEObject));
/*      */                 return;
/*      */               } 
/*  719 */               if (internalEObject.eIsProxy()) {
/*      */                 
/*  721 */                 writeCompressedInt(-1);
/*  722 */                 writeURI(internalEObject.eProxyURI());
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  733 */           EStructuralFeatureData[] eStructuralFeatureData = eClassData.eStructuralFeatureData;
/*  734 */           for (int i = 0, length = eStructuralFeatureData.length; i < length; i++) {
/*      */             
/*  736 */             EStructuralFeatureData structuralFeatureData = eStructuralFeatureData[i];
/*  737 */             if (!structuralFeatureData.isTransient && (
/*  738 */               structuralFeatureData.kind != BinaryResourceImpl.BinaryIO.FeatureKind.EOBJECT_CONTAINER_PROXY_RESOLVING || check == Check.CONTAINER))
/*      */             {
/*  740 */               saveFeatureValue(internalEObject, i, structuralFeatureData);
/*      */             }
/*      */           } 
/*  743 */           writeCompressedInt(0);
/*      */         }
/*      */         else {
/*      */           
/*  747 */           writeCompressedInt(id.intValue());
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void saveFeatureValue(InternalEObject internalEObject, int featureID, EStructuralFeatureData eStructuralFeatureData) throws IOException {
/*  754 */       if (internalEObject.eIsSet(featureID)) {
/*      */         InternalEList<? extends InternalEObject> internalEList; FeatureMap.Internal featureMap; String literal; List<?> dataValues; int length, j;
/*  756 */         writeCompressedInt(featureID + 1);
/*  757 */         if (eStructuralFeatureData.name != null) {
/*      */           
/*  759 */           writeString(eStructuralFeatureData.name);
/*  760 */           eStructuralFeatureData.name = null;
/*      */         } 
/*  762 */         Object value = internalEObject.eGet(featureID, false, true);
/*  763 */         switch (eStructuralFeatureData.kind) {
/*      */ 
/*      */           
/*      */           case EOBJECT:
/*      */           case EOBJECT_CONTAINMENT:
/*  768 */             saveEObject((InternalEObject)value, Check.NOTHING);
/*      */             return;
/*      */ 
/*      */           
/*      */           case EOBJECT_CONTAINER_PROXY_RESOLVING:
/*  773 */             saveEObject((InternalEObject)value, Check.DIRECT_RESOURCE);
/*      */             return;
/*      */ 
/*      */           
/*      */           case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
/*  778 */             saveEObject((InternalEObject)value, Check.DIRECT_RESOURCE);
/*      */             return;
/*      */ 
/*      */           
/*      */           case EOBJECT_PROXY_RESOLVING:
/*  783 */             saveEObject((InternalEObject)value, Check.RESOURCE);
/*      */             return;
/*      */ 
/*      */ 
/*      */           
/*      */           case EOBJECT_LIST:
/*      */           case EOBJECT_CONTAINMENT_LIST:
/*  790 */             internalEList = (InternalEList<? extends InternalEObject>)value;
/*  791 */             saveEObjects(internalEList, Check.NOTHING);
/*      */             return;
/*      */ 
/*      */ 
/*      */           
/*      */           case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
/*  797 */             internalEList = (InternalEList<? extends InternalEObject>)value;
/*  798 */             saveEObjects(internalEList, Check.DIRECT_RESOURCE);
/*      */             return;
/*      */ 
/*      */ 
/*      */           
/*      */           case EOBJECT_LIST_PROXY_RESOLVING:
/*  804 */             internalEList = (InternalEList<? extends InternalEObject>)value;
/*  805 */             saveEObjects(internalEList, Check.RESOURCE);
/*      */             return;
/*      */ 
/*      */           
/*      */           case null:
/*  810 */             writeBoolean(((Boolean)value).booleanValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case BYTE:
/*  815 */             writeByte(((Byte)value).byteValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case CHAR:
/*  820 */             writeChar(((Character)value).charValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case DOUBLE:
/*  825 */             writeDouble(((Double)value).doubleValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case FLOAT:
/*  830 */             writeFloat(((Float)value).floatValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case INT:
/*  835 */             writeInt(((Integer)value).intValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case LONG:
/*  840 */             writeLong(((Long)value).longValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case SHORT:
/*  845 */             writeShort(((Short)value).shortValue());
/*      */             return;
/*      */ 
/*      */           
/*      */           case STRING:
/*  850 */             writeString((String)value);
/*      */             return;
/*      */ 
/*      */           
/*      */           case FEATURE_MAP:
/*  855 */             featureMap = (FeatureMap.Internal)value;
/*  856 */             saveFeatureMap(featureMap);
/*      */             return;
/*      */ 
/*      */           
/*      */           case DATA:
/*  861 */             literal = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value);
/*  862 */             writeString(literal);
/*      */             return;
/*      */ 
/*      */           
/*      */           case DATA_LIST:
/*  867 */             dataValues = (List)value;
/*  868 */             length = dataValues.size();
/*  869 */             writeCompressedInt(length);
/*  870 */             for (j = 0; j < length; j++) {
/*      */               
/*  872 */               String str = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, dataValues.get(j));
/*  873 */               writeString(str);
/*      */             } 
/*      */             return;
/*      */         } 
/*      */ 
/*      */         
/*  879 */         throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void writeByte(int value) throws IOException {
/*  887 */       this.outputStream.write(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeBoolean(boolean value) throws IOException {
/*  892 */       writeByte(value ? 1 : 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeChar(int value) throws IOException {
/*  897 */       writeByte((byte)(value >> 8 & 0xFF));
/*  898 */       writeByte((byte)(value & 0xFF));
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeShort(int value) throws IOException {
/*  903 */       writeByte((byte)(value >> 8 & 0xFF));
/*  904 */       writeByte((byte)(value & 0xFF));
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeInt(int value) throws IOException {
/*  909 */       writeByte((byte)(value >> 24 & 0xFF));
/*  910 */       writeByte((byte)(value >> 16 & 0xFF));
/*  911 */       writeByte((byte)(value >> 8 & 0xFF));
/*  912 */       writeByte((byte)(value & 0xFF));
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeLong(long value) throws IOException {
/*  917 */       writeInt((int)(value >> 32L));
/*  918 */       writeInt((int)value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeFloat(float value) throws IOException {
/*  923 */       writeInt(Float.floatToIntBits(value));
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeDouble(double value) throws IOException {
/*  928 */       writeLong(Double.doubleToLongBits(value));
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeCompressedInt(int value) throws IOException {
/*  933 */       value++;
/*  934 */       int firstByte = value >> 24 & 0xFF;
/*  935 */       int secondByte = value >> 16 & 0xFF;
/*  936 */       int thirdByte = value >> 8 & 0xFF;
/*  937 */       int fourthBtye = value & 0xFF;
/*  938 */       if (firstByte > 63) {
/*      */         
/*  940 */         handleInvalidValue(value);
/*      */       }
/*  942 */       else if (firstByte != 0 || secondByte > 63) {
/*      */         
/*  944 */         writeByte(firstByte | 0xC0);
/*  945 */         writeByte(secondByte);
/*  946 */         writeByte(thirdByte);
/*  947 */         writeByte(fourthBtye);
/*      */       }
/*  949 */       else if (secondByte != 0 || thirdByte > 63) {
/*      */         
/*  951 */         writeByte(secondByte | 0x80);
/*  952 */         writeByte(thirdByte);
/*  953 */         writeByte(fourthBtye);
/*      */       }
/*  955 */       else if (thirdByte != 0 || fourthBtye > 63) {
/*      */         
/*  957 */         writeByte(thirdByte | 0x40);
/*  958 */         writeByte(fourthBtye);
/*      */       }
/*      */       else {
/*      */         
/*  962 */         writeByte(fourthBtye);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private final void handleInvalidValue(int value) throws IOException {
/*  968 */       throw new IOException("Invalid value " + value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeString(String value) throws IOException {
/*  973 */       if (value == null) {
/*      */         
/*  975 */         writeCompressedInt(-1);
/*      */       }
/*      */       else {
/*      */         
/*  979 */         int length = value.length();
/*  980 */         writeCompressedInt(length);
/*  981 */         if (this.characters == null || this.characters.length < length)
/*      */         {
/*  983 */           this.characters = new char[length];
/*      */         }
/*  985 */         value.getChars(0, length, this.characters, 0);
/*      */         
/*  987 */         for (int i = 0; i < length; i++) {
/*      */           
/*  989 */           char character = this.characters[i];
/*  990 */           if (character == '\000' || character > 'ÿ') {
/*      */             
/*  992 */             writeByte(0);
/*  993 */             writeChar(character);
/*  994 */             while (++i < length)
/*      */             {
/*  996 */               writeChar(this.characters[i]);
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1002 */           writeByte((byte)character);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void writeURI(URI uri) throws IOException {
/* 1010 */       writeURI(uri.trimFragment(), uri.fragment());
/*      */     }
/*      */ 
/*      */     
/*      */     public void writeURI(URI uri, String fragment) throws IOException {
/* 1015 */       if (uri == null) {
/*      */         
/* 1017 */         writeCompressedInt(-1);
/*      */       }
/*      */       else {
/*      */         
/* 1021 */         assert uri.fragment() == null;
/* 1022 */         Integer id = this.uriToIDMap.get(uri);
/* 1023 */         if (id == null) {
/*      */           
/* 1025 */           int idValue = this.uriToIDMap.size();
/* 1026 */           this.uriToIDMap.put(uri, Integer.valueOf(idValue));
/* 1027 */           writeCompressedInt(idValue);
/* 1028 */           writeString(deresolve(uri).toString());
/*      */         }
/*      */         else {
/*      */           
/* 1032 */           writeCompressedInt(id.intValue());
/*      */         } 
/* 1034 */         writeString(fragment);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class EObjectInputStream extends BinaryIO {
/*      */     protected ResourceSet resourceSet;
/*      */     protected InputStream inputStream;
/*      */     
/*      */     protected static class EPackageData {
/*      */       public EPackage ePackage;
/*      */       public BinaryResourceImpl.EObjectInputStream.EClassData[] eClassData;
/*      */       
/*      */       public final int allocateEClassID() {
/* 1048 */         for (int i = 0, length = this.eClassData.length; i < length; i++) {
/*      */           
/* 1050 */           BinaryResourceImpl.EObjectInputStream.EClassData eClassData = this.eClassData[i];
/* 1051 */           if (eClassData == null)
/*      */           {
/* 1053 */             return i;
/*      */           }
/*      */         } 
/* 1056 */         return -1;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected static class EClassData
/*      */     {
/*      */       public EClass eClass;
/*      */       
/*      */       public EFactory eFactory;
/*      */       
/*      */       public BinaryResourceImpl.EObjectInputStream.EStructuralFeatureData[] eStructuralFeatureData;
/*      */     }
/*      */ 
/*      */     
/*      */     protected static class EStructuralFeatureData
/*      */     {
/*      */       public int featureID;
/*      */       public EStructuralFeature eStructuralFeature;
/*      */       public BinaryResourceImpl.BinaryIO.FeatureKind kind;
/*      */       public EFactory eFactory;
/*      */       public EDataType eDataType;
/*      */     }
/* 1079 */     protected List<EPackageData> ePackageDataList = new ArrayList<EPackageData>();
/* 1080 */     protected List<EClassData> eClassDataList = new ArrayList<EClassData>();
/* 1081 */     protected List<InternalEObject> eObjectList = new ArrayList<InternalEObject>();
/* 1082 */     protected List<URI> uriList = new ArrayList<URI>();
/*      */     
/* 1084 */     protected BasicEList<InternalEObject> internalEObjectList = new BasicEList();
/* 1085 */     protected BasicEList<Object> dataValueList = new BasicEList();
/*      */ 
/*      */ 
/*      */     
/*      */     protected int[][] intDataArrayBuffer;
/*      */ 
/*      */     
/*      */     protected int intDataArrayBufferCount;
/*      */ 
/*      */ 
/*      */     
/*      */     protected void readSignature() throws IOException {
/* 1097 */       if (readByte() != -119 || 
/* 1098 */         readByte() != 101 || 
/* 1099 */         readByte() != 109 || 
/* 1100 */         readByte() != 102 || 
/* 1101 */         readByte() != 10 || 
/* 1102 */         readByte() != 13 || 
/* 1103 */         readByte() != 26 || 
/* 1104 */         readByte() != 10)
/*      */       {
/* 1106 */         throw new IOException("Invalid signature for a binary EMF serialization");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readVersion() throws IOException {
/* 1112 */       this.version = BinaryResourceImpl.BinaryIO.Version.values()[readByte()];
/*      */     }
/*      */     
/* 1115 */     public EObjectInputStream(InputStream inputStream, Map<?, ?> options) throws IOException { this.intDataArrayBuffer = new int[50][];
/* 1116 */       this.intDataArrayBufferCount = -1;
/*      */       this.inputStream = inputStream;
/*      */       this.options = options;
/*      */       readSignature();
/* 1120 */       readVersion(); } protected int[] allocateIntArray(int length) { if (this.intDataArrayBufferCount == -1)
/*      */       {
/* 1122 */         return new int[length];
/*      */       }
/*      */ 
/*      */       
/* 1126 */       int[] buffer = this.intDataArrayBuffer[this.intDataArrayBufferCount];
/* 1127 */       this.intDataArrayBuffer[this.intDataArrayBufferCount--] = null;
/* 1128 */       return (buffer.length >= length) ? buffer : new int[length]; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void recycle(int[] values) {
/* 1134 */       if (++this.intDataArrayBufferCount >= this.intDataArrayBuffer.length) {
/*      */         
/* 1136 */         int[][] newIntDataArrayBuffer = new int[this.intDataArrayBufferCount * 2][];
/* 1137 */         System.arraycopy(this.intDataArrayBuffer, 0, newIntDataArrayBuffer, 0, this.intDataArrayBufferCount);
/* 1138 */         this.intDataArrayBuffer = newIntDataArrayBuffer;
/*      */       } 
/* 1140 */       this.intDataArrayBuffer[this.intDataArrayBufferCount] = values;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EPackageData readEPackage() throws IOException {
/* 1145 */       int id = readCompressedInt();
/* 1146 */       if (this.ePackageDataList.size() <= id) {
/*      */         
/* 1148 */         EPackageData ePackageData = new EPackageData();
/* 1149 */         String nsURI = readString();
/* 1150 */         URI uri = readURI();
/* 1151 */         if (this.resourceSet != null) {
/*      */           
/* 1153 */           ePackageData.ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
/* 1154 */           if (ePackageData.ePackage == null)
/*      */           {
/* 1156 */             ePackageData.ePackage = (EPackage)this.resourceSet.getEObject(uri, true);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 1161 */           ePackageData.ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
/*      */         } 
/* 1163 */         ePackageData.eClassData = new EClassData[ePackageData.ePackage.getEClassifiers().size()];
/* 1164 */         this.ePackageDataList.add(ePackageData);
/* 1165 */         return ePackageData;
/*      */       } 
/*      */ 
/*      */       
/* 1169 */       return this.ePackageDataList.get(id);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected EClassData readEClass() throws IOException {
/* 1175 */       EPackageData ePackageData = readEPackage();
/* 1176 */       int id = readCompressedInt();
/* 1177 */       EClassData eClassData = ePackageData.eClassData[id];
/* 1178 */       if (eClassData == null) {
/*      */         
/* 1180 */         eClassData = ePackageData.eClassData[id] = new EClassData();
/* 1181 */         String name = readString();
/* 1182 */         eClassData.eClass = (EClass)ePackageData.ePackage.getEClassifier(name);
/* 1183 */         eClassData.eFactory = ePackageData.ePackage.getEFactoryInstance();
/* 1184 */         eClassData.eStructuralFeatureData = new EStructuralFeatureData[eClassData.eClass.getFeatureCount()];
/*      */       } 
/* 1186 */       return eClassData;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EStructuralFeatureData readEStructuralFeature() throws IOException {
/* 1191 */       EClassData eClassData = readEClass();
/* 1192 */       int featureID = readCompressedInt();
/* 1193 */       return getEStructuralFeatureData(eClassData, featureID);
/*      */     }
/*      */ 
/*      */     
/*      */     protected EStructuralFeatureData getEStructuralFeatureData(EClassData eClassData, int featureID) throws IOException {
/* 1198 */       EStructuralFeatureData eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID];
/* 1199 */       if (eStructuralFeatureData == null) {
/*      */         
/* 1201 */         eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID] = new EStructuralFeatureData();
/* 1202 */         String name = readString();
/* 1203 */         eStructuralFeatureData.eStructuralFeature = eClassData.eClass.getEStructuralFeature(name);
/* 1204 */         eStructuralFeatureData.featureID = eClassData.eClass.getFeatureID(eStructuralFeatureData.eStructuralFeature);
/* 1205 */         eStructuralFeatureData.kind = BinaryResourceImpl.BinaryIO.FeatureKind.get(eStructuralFeatureData.eStructuralFeature);
/* 1206 */         if (eStructuralFeatureData.eStructuralFeature instanceof EAttribute) {
/*      */           
/* 1208 */           EAttribute eAttribute = (EAttribute)eStructuralFeatureData.eStructuralFeature;
/* 1209 */           eStructuralFeatureData.eDataType = eAttribute.getEAttributeType();
/* 1210 */           eStructuralFeatureData.eFactory = eStructuralFeatureData.eDataType.getEPackage().getEFactoryInstance();
/*      */         } 
/*      */       } 
/* 1213 */       return eStructuralFeatureData;
/*      */     }
/*      */ 
/*      */     
/*      */     public void loadResource(Resource resource) throws IOException {
/* 1218 */       this.resource = resource;
/* 1219 */       this.resourceSet = resource.getResourceSet();
/* 1220 */       URI uri = resource.getURI();
/* 1221 */       if (uri != null && uri.isHierarchical() && !uri.isRelative())
/*      */       {
/* 1223 */         this.baseURI = uri;
/*      */       }
/* 1225 */       int size = readCompressedInt();
/* 1226 */       InternalEObject[] values = allocateInternalEObjectArray(size);
/* 1227 */       for (int i = 0; i < size; i++)
/*      */       {
/* 1229 */         values[i] = loadEObject();
/*      */       }
/* 1231 */       this.internalEObjectList.setData(size, (Object[])values);
/*      */       
/* 1233 */       InternalEList<InternalEObject> internalEObjects = (InternalEList<InternalEObject>)resource.getContents();
/* 1234 */       internalEObjects.addAllUnique((Collection)this.internalEObjectList);
/* 1235 */       recycle(values);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void loadEObjects(InternalEList<InternalEObject> internalEObjects) throws IOException {
/* 1242 */       int size = readCompressedInt();
/* 1243 */       InternalEObject[] values = allocateInternalEObjectArray(size);
/* 1244 */       for (int i = 0; i < size; i++)
/*      */       {
/* 1246 */         values[i] = loadEObject();
/*      */       }
/* 1248 */       int existingSize = internalEObjects.size();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1253 */       if (existingSize == 0) {
/*      */         
/* 1255 */         this.internalEObjectList.setData(size, (Object[])values);
/* 1256 */         internalEObjects.addAllUnique((Collection)this.internalEObjectList);
/*      */       }
/*      */       else {
/*      */         
/* 1260 */         InternalEObject[] existingValues = allocateInternalEObjectArray(existingSize);
/* 1261 */         internalEObjects.basicToArray((Object[])existingValues);
/* 1262 */         int[] indices = allocateIntArray(existingSize);
/* 1263 */         int duplicateCount = 0;
/*      */         int j;
/* 1265 */         for (j = 0; j < size; j++) {
/*      */           
/* 1267 */           InternalEObject internalEObject = values[j];
/* 1268 */           int k = 0, count = 0; while (true) { if (k >= existingSize)
/*      */             
/*      */             { 
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
/* 1289 */               values[j - duplicateCount] = internalEObject; break; }  InternalEObject existingInternalEObject = existingValues[k]; if (existingInternalEObject == internalEObject) { if (duplicateCount != count)
/*      */                 internalEObjects.move(duplicateCount, count);  indices[duplicateCount] = j; count++; duplicateCount++; existingValues[k] = null; break; }  if (existingInternalEObject != null)
/*      */               count++;  k++; } 
/* 1292 */         }  size -= existingSize;
/* 1293 */         this.internalEObjectList.setData(size, (Object[])values);
/* 1294 */         internalEObjects.addAllUnique(0, (Collection)this.internalEObjectList);
/* 1295 */         for (j = 0; j < existingSize; j++) {
/*      */           
/* 1297 */           int newPosition = indices[j];
/* 1298 */           int oldPosition = size + j;
/* 1299 */           if (newPosition != oldPosition)
/*      */           {
/* 1301 */             internalEObjects.move(newPosition, oldPosition);
/*      */           }
/*      */         } 
/* 1304 */         recycle(existingValues);
/* 1305 */         recycle(indices);
/*      */       } 
/* 1307 */       recycle(values);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void loadFeatureMap(FeatureMap.Internal featureMap) throws IOException {
/* 1314 */       int size = readCompressedInt();
/* 1315 */       FeatureMap.Entry.Internal[] values = allocateFeatureMapEntryArray(size);
/* 1316 */       for (int i = 0; i < size; i++)
/*      */       {
/* 1318 */         values[i] = loadFeatureMapEntry();
/*      */       }
/* 1320 */       int existingSize = featureMap.size();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1325 */       if (existingSize == 0) {
/*      */         
/* 1327 */         featureMap.addAllUnique(values, 0, size);
/*      */       }
/*      */       else {
/*      */         
/* 1331 */         FeatureMap.Entry.Internal[] existingValues = allocateFeatureMapEntryArray(existingSize);
/* 1332 */         featureMap.basicToArray((Object[])existingValues);
/* 1333 */         int[] indices = allocateIntArray(existingSize);
/* 1334 */         int duplicateCount = 0;
/*      */         int j;
/* 1336 */         for (j = 0; j < size; j++) {
/*      */           
/* 1338 */           FeatureMap.Entry.Internal entry = values[j];
/* 1339 */           int k = 0, count = 0; while (true) { if (k >= existingSize)
/*      */             
/*      */             { 
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
/* 1360 */               values[j - duplicateCount] = entry; break; }  FeatureMap.Entry.Internal existingEntry = existingValues[k]; if (entry.equals(existingEntry)) { if (duplicateCount != count)
/*      */                 featureMap.move(duplicateCount, count);  indices[duplicateCount] = j; count++; duplicateCount++; existingValues[k] = null; break; }  if (existingEntry != null)
/*      */               count++;  k++; } 
/* 1363 */         }  size -= existingSize;
/* 1364 */         this.internalEObjectList.setData(size, (Object[])values);
/* 1365 */         featureMap.addAllUnique(0, values, 0, size);
/* 1366 */         for (j = 0; j < existingSize; j++) {
/*      */           
/* 1368 */           int newPosition = indices[j];
/* 1369 */           int oldPosition = size + j;
/* 1370 */           if (newPosition != oldPosition)
/*      */           {
/* 1372 */             featureMap.move(newPosition, oldPosition);
/*      */           }
/*      */         } 
/* 1375 */         recycle(existingValues);
/* 1376 */         recycle(indices);
/*      */       } 
/* 1378 */       recycle(values);
/*      */     }
/*      */     public FeatureMap.Entry.Internal loadFeatureMapEntry() throws IOException {
/*      */       Object value;
/*      */       String literal;
/* 1383 */       EStructuralFeatureData eStructuralFeatureData = readEStructuralFeature();
/*      */       
/* 1385 */       switch (eStructuralFeatureData.kind) {
/*      */ 
/*      */         
/*      */         case EOBJECT_CONTAINER:
/*      */         case EOBJECT_CONTAINER_PROXY_RESOLVING:
/*      */         case EOBJECT:
/*      */         case EOBJECT_PROXY_RESOLVING:
/*      */         case EOBJECT_LIST:
/*      */         case EOBJECT_LIST_PROXY_RESOLVING:
/*      */         case EOBJECT_CONTAINMENT:
/*      */         case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
/*      */         case EOBJECT_CONTAINMENT_LIST:
/*      */         case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
/* 1398 */           value = loadEObject();
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
/* 1457 */           return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case STRING: value = readString(); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case DATA: literal = readString(); value = eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, literal); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case null: value = Boolean.valueOf(readBoolean()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case BYTE: value = Byte.valueOf(readByte()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case CHAR: value = Character.valueOf(readChar()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case DOUBLE: value = Double.valueOf(readDouble()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case FLOAT: value = Float.valueOf(readFloat()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case INT: value = Integer.valueOf(readInt()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case LONG: value = Long.valueOf(readLong()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);case SHORT: value = Short.valueOf(readShort()); return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);
/*      */       } 
/*      */       throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
/*      */     }
/*      */     
/* 1462 */     public InternalEObject loadEObject() throws IOException { int id = readCompressedInt();
/* 1463 */       if (id == -1)
/*      */       {
/* 1465 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1469 */       if (this.eObjectList.size() <= id) {
/*      */         
/* 1471 */         EClassData eClassData = readEClass();
/* 1472 */         InternalEObject internalEObject = (InternalEObject)eClassData.eFactory.create(eClassData.eClass);
/* 1473 */         this.eObjectList.add(internalEObject);
/*      */         
/*      */         while (true) {
/* 1476 */           int featureID = readCompressedInt() - 1;
/* 1477 */           if (featureID == -1) {
/*      */             break;
/*      */           }
/*      */           
/* 1481 */           if (featureID == -2) {
/*      */             
/* 1483 */             internalEObject.eSetProxyURI(readURI());
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1488 */           EStructuralFeatureData eStructuralFeatureData = getEStructuralFeatureData(eClassData, featureID);
/* 1489 */           loadFeatureValue(internalEObject, eStructuralFeatureData);
/*      */         } 
/*      */         
/* 1492 */         return internalEObject;
/*      */       } 
/*      */ 
/*      */       
/* 1496 */       return this.eObjectList.get(id); } protected void loadFeatureValue(InternalEObject internalEObject, EStructuralFeatureData eStructuralFeatureData) throws IOException { InternalEList<InternalEObject> internalEList;
/*      */       FeatureMap.Internal featureMap;
/*      */       String literal;
/*      */       int size;
/*      */       Object[] dataValues;
/*      */       int i;
/*      */       List<Object> values;
/* 1503 */       switch (eStructuralFeatureData.kind) {
/*      */ 
/*      */         
/*      */         case EOBJECT_CONTAINER:
/*      */         case EOBJECT_CONTAINER_PROXY_RESOLVING:
/*      */         case EOBJECT:
/*      */         case EOBJECT_PROXY_RESOLVING:
/*      */         case EOBJECT_CONTAINMENT:
/*      */         case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
/* 1512 */           internalEObject.eSet(eStructuralFeatureData.featureID, loadEObject());
/*      */           return;
/*      */ 
/*      */ 
/*      */         
/*      */         case EOBJECT_LIST:
/*      */         case EOBJECT_LIST_PROXY_RESOLVING:
/*      */         case EOBJECT_CONTAINMENT_LIST:
/*      */         case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
/* 1521 */           internalEList = (InternalEList<InternalEObject>)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
/* 1522 */           loadEObjects(internalEList);
/*      */           return;
/*      */ 
/*      */         
/*      */         case STRING:
/* 1527 */           internalEObject.eSet(eStructuralFeatureData.featureID, readString());
/*      */           return;
/*      */ 
/*      */         
/*      */         case FEATURE_MAP:
/* 1532 */           featureMap = (FeatureMap.Internal)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
/* 1533 */           loadFeatureMap(featureMap);
/*      */           return;
/*      */ 
/*      */         
/*      */         case DATA:
/* 1538 */           literal = readString();
/* 1539 */           internalEObject.eSet(eStructuralFeatureData.featureID, eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, literal));
/*      */           return;
/*      */ 
/*      */         
/*      */         case DATA_LIST:
/* 1544 */           size = readCompressedInt();
/* 1545 */           this.dataValueList.grow(size);
/* 1546 */           dataValues = this.dataValueList.data();
/* 1547 */           for (i = 0; i < size; i++) {
/*      */             
/* 1549 */             String str = readString();
/* 1550 */             dataValues[i] = eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, str);
/*      */           } 
/* 1552 */           this.dataValueList.setData(size, dataValues);
/*      */           
/* 1554 */           values = (List<Object>)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
/* 1555 */           values.addAll((Collection<?>)this.dataValueList);
/*      */           return;
/*      */ 
/*      */         
/*      */         case null:
/* 1560 */           internalEObject.eSet(eStructuralFeatureData.featureID, Boolean.valueOf(readBoolean()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case BYTE:
/* 1565 */           internalEObject.eSet(eStructuralFeatureData.featureID, Byte.valueOf(readByte()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case CHAR:
/* 1570 */           internalEObject.eSet(eStructuralFeatureData.featureID, Character.valueOf(readChar()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case DOUBLE:
/* 1575 */           internalEObject.eSet(eStructuralFeatureData.featureID, Double.valueOf(readDouble()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case FLOAT:
/* 1580 */           internalEObject.eSet(eStructuralFeatureData.featureID, Float.valueOf(readFloat()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case INT:
/* 1585 */           internalEObject.eSet(eStructuralFeatureData.featureID, Integer.valueOf(readInt()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case LONG:
/* 1590 */           internalEObject.eSet(eStructuralFeatureData.featureID, Long.valueOf(readLong()));
/*      */           return;
/*      */ 
/*      */         
/*      */         case SHORT:
/* 1595 */           internalEObject.eSet(eStructuralFeatureData.featureID, Short.valueOf(readShort()));
/*      */           return;
/*      */       } 
/*      */ 
/*      */       
/* 1600 */       throw new IOException("Unhandled case " + eStructuralFeatureData.kind); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte readByte() throws IOException {
/* 1607 */       int result = this.inputStream.read();
/* 1608 */       if (result == -1)
/*      */       {
/* 1610 */         throw new IOException("Unexpected end of stream");
/*      */       }
/* 1612 */       return (byte)result;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean readBoolean() throws IOException {
/* 1617 */       return (readByte() != 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public char readChar() throws IOException {
/* 1622 */       return (char)(readByte() << 8 & 0xFF00 | readByte() & 0xFF);
/*      */     }
/*      */ 
/*      */     
/*      */     public short readShort() throws IOException {
/* 1627 */       return (short)(readByte() << 8 & 0xFF00 | readByte() & 0xFF);
/*      */     }
/*      */ 
/*      */     
/*      */     public int readInt() throws IOException {
/* 1632 */       return readByte() << 24 | readByte() << 16 & 0xFF0000 | readByte() << 8 & 0xFF00 | readByte() & 0xFF;
/*      */     }
/*      */ 
/*      */     
/*      */     public long readLong() throws IOException {
/* 1637 */       return readInt() << 32L | readInt() & 0xFFFFFFFFL;
/*      */     }
/*      */ 
/*      */     
/*      */     public float readFloat() throws IOException {
/* 1642 */       return Float.intBitsToFloat(readInt());
/*      */     }
/*      */ 
/*      */     
/*      */     public double readDouble() throws IOException {
/* 1647 */       return Double.longBitsToDouble(readLong());
/*      */     }
/*      */ 
/*      */     
/*      */     public int readCompressedInt() throws IOException {
/* 1652 */       byte initialByte = readByte();
/* 1653 */       int code = initialByte >> 6 & 0x3;
/* 1654 */       switch (code) {
/*      */ 
/*      */         
/*      */         case 0:
/* 1658 */           return initialByte - 1;
/*      */ 
/*      */         
/*      */         case 1:
/* 1662 */           return (initialByte << 8 & 0x3F00 | readByte() & 0xFF) - 1;
/*      */ 
/*      */         
/*      */         case 2:
/* 1666 */           return (initialByte << 16 & 0x3F0000 | readByte() << 8 & 0xFF00 | readByte() & 0xFF) - 1;
/*      */       } 
/*      */ 
/*      */       
/* 1670 */       return (initialByte << 24 & 0x3F000000 | readByte() << 16 & 0xFF0000 | readByte() << 8 & 0xFF00 | readByte() & 0xFF) - 1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String readString() throws IOException {
/* 1677 */       int length = readCompressedInt();
/* 1678 */       if (length == -1)
/*      */       {
/* 1680 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1684 */       if (this.characters == null || this.characters.length < length)
/*      */       {
/* 1686 */         this.characters = new char[length];
/*      */       }
/*      */       
/* 1689 */       for (int i = 0; i < length; i++) {
/*      */         
/* 1691 */         byte value = readByte();
/* 1692 */         if (value == 0) {
/*      */           
/*      */           do
/*      */           {
/* 1696 */             this.characters[i] = readChar();
/*      */           }
/* 1698 */           while (++i < length);
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 1703 */         this.characters[i] = (char)(value & 0xFF);
/*      */       } 
/*      */       
/* 1706 */       return new String(this.characters, 0, length);
/*      */     }
/*      */ 
/*      */     
/*      */     public URI readURI() throws IOException {
/*      */       URI uri;
/* 1712 */       int id = readCompressedInt();
/* 1713 */       if (id == -1)
/*      */       {
/* 1715 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1720 */       if (this.uriList.size() <= id) {
/*      */         
/* 1722 */         String value = readString();
/* 1723 */         uri = resolve(URI.createURI(value));
/* 1724 */         this.uriList.add(uri);
/*      */       }
/*      */       else {
/*      */         
/* 1728 */         uri = this.uriList.get(id);
/*      */       } 
/* 1730 */       String fragment = readString();
/* 1731 */       if (fragment != null)
/*      */       {
/* 1733 */         uri = uri.appendFragment(fragment);
/*      */       }
/* 1735 */       return uri;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\BinaryResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */