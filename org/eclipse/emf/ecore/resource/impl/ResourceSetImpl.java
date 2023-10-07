/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.AdapterFactory;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.NotifierImpl;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.TreeIterator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;
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
/*     */ 
/*     */ 
/*     */ public class ResourceSetImpl
/*     */   extends NotifierImpl
/*     */   implements ResourceSet
/*     */ {
/*     */   protected EList<Resource> resources;
/*     */   protected EList<AdapterFactory> adapterFactories;
/*     */   protected Map<Object, Object> loadOptions;
/*     */   protected Resource.Factory.Registry resourceFactoryRegistry;
/*     */   protected URIConverter uriConverter;
/*     */   protected EPackage.Registry packageRegistry;
/*     */   protected Map<URI, Resource> uriResourceMap;
/*     */   
/*     */   public Map<URI, Resource> getURIResourceMap() {
/* 125 */     return this.uriResourceMap;
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
/*     */   public void setURIResourceMap(Map<URI, Resource> uriResourceMap) {
/* 139 */     this.uriResourceMap = uriResourceMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Resource> getResources() {
/* 147 */     if (this.resources == null)
/*     */     {
/* 149 */       this.resources = (EList<Resource>)new ResourcesEList<Resource>();
/*     */     }
/* 151 */     return this.resources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeIterator<Notifier> getAllContents() {
/* 159 */     TreeIterator<Notifier> result = EcoreUtil.getAllContents(Collections.singleton(this));
/* 160 */     result.next();
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<AdapterFactory> getAdapterFactories() {
/* 169 */     if (this.adapterFactories == null)
/*     */     {
/* 171 */       this.adapterFactories = 
/* 172 */         (EList<AdapterFactory>)new BasicEList<AdapterFactory>()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected boolean useEquals() {
/* 179 */             return false;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected boolean isUnique() {
/* 185 */             return true;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected Object[] newData(int capacity) {
/* 191 */             return (Object[])new AdapterFactory[capacity];
/*     */           }
/*     */         };
/*     */     }
/* 195 */     return this.adapterFactories;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Object, Object> getLoadOptions() {
/* 203 */     if (this.loadOptions == null)
/*     */     {
/* 205 */       this.loadOptions = new HashMap<Object, Object>();
/*     */     }
/*     */     
/* 208 */     return this.loadOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject getEObject(URI uri, boolean loadOnDemand) {
/* 216 */     Resource resource = getResource(uri.trimFragment(), loadOnDemand);
/* 217 */     if (resource != null)
/*     */     {
/* 219 */       return resource.getEObject(uri.fragment());
/*     */     }
/*     */ 
/*     */     
/* 223 */     return null;
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
/*     */   protected Resource demandCreateResource(URI uri) {
/* 239 */     return createResource(uri, "");
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
/*     */   protected void demandLoad(Resource resource) throws IOException {
/* 255 */     resource.load(getLoadOptions());
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
/*     */   protected void demandLoadHelper(Resource resource) {
/*     */     try {
/* 270 */       demandLoad(resource);
/*     */     }
/* 272 */     catch (IOException exception) {
/*     */       
/* 274 */       handleDemandLoadException(resource, exception);
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
/*     */   protected void handleDemandLoadException(Resource resource, IOException exception) throws RuntimeException {
/* 288 */     final String location = (resource.getURI() == null) ? null : resource.getURI().toString();
/*     */     class DiagnosticWrappedException
/*     */       extends WrappedException
/*     */       implements Resource.Diagnostic {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public DiagnosticWrappedException(Exception exception) {
/* 295 */         super(exception);
/*     */       }
/*     */ 
/*     */       
/*     */       public String getLocation() {
/* 300 */         return location;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getColumn() {
/* 305 */         return 0;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getLine() {
/* 310 */         return 0;
/*     */       }
/*     */     };
/*     */     
/* 314 */     Exception cause = (exception instanceof Resource.IOWrappedException) ? (Exception)exception.getCause() : exception;
/* 315 */     DiagnosticWrappedException wrappedException = new DiagnosticWrappedException(cause);
/*     */     
/* 317 */     if (resource.getErrors().isEmpty())
/*     */     {
/* 319 */       resource.getErrors().add((exception instanceof Resource.Diagnostic) ? exception : wrappedException);
/*     */     }
/*     */     
/* 322 */     throw wrappedException;
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
/*     */   protected Resource delegatedGetResource(URI uri, boolean loadOnDemand) {
/* 337 */     EPackage ePackage = getPackageRegistry().getEPackage(uri.toString());
/* 338 */     return (ePackage == null) ? null : ePackage.eResource();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource getResource(URI uri, boolean loadOnDemand) {
/* 346 */     Map<URI, Resource> map = getURIResourceMap();
/* 347 */     if (map != null) {
/*     */       
/* 349 */       Resource resource = map.get(uri);
/* 350 */       if (resource != null) {
/*     */         
/* 352 */         if (loadOnDemand && !resource.isLoaded())
/*     */         {
/* 354 */           demandLoadHelper(resource);
/*     */         }
/* 356 */         return resource;
/*     */       } 
/*     */     } 
/*     */     
/* 360 */     URIConverter theURIConverter = getURIConverter();
/* 361 */     URI normalizedURI = theURIConverter.normalize(uri);
/* 362 */     for (Resource resource : getResources()) {
/*     */       
/* 364 */       if (theURIConverter.normalize(resource.getURI()).equals(normalizedURI)) {
/*     */         
/* 366 */         if (loadOnDemand && !resource.isLoaded())
/*     */         {
/* 368 */           demandLoadHelper(resource);
/*     */         }
/*     */         
/* 371 */         if (map != null)
/*     */         {
/* 373 */           map.put(uri, resource);
/*     */         }
/* 375 */         return resource;
/*     */       } 
/*     */     } 
/*     */     
/* 379 */     Resource delegatedResource = delegatedGetResource(uri, loadOnDemand);
/* 380 */     if (delegatedResource != null) {
/*     */       
/* 382 */       if (map != null)
/*     */       {
/* 384 */         map.put(uri, delegatedResource);
/*     */       }
/* 386 */       return delegatedResource;
/*     */     } 
/*     */     
/* 389 */     if (loadOnDemand) {
/*     */       
/* 391 */       Resource resource = demandCreateResource(uri);
/* 392 */       if (resource == null)
/*     */       {
/* 394 */         throw new RuntimeException("Cannot create a resource for '" + uri + "'; a registered resource factory is needed");
/*     */       }
/*     */       
/* 397 */       demandLoadHelper(resource);
/*     */       
/* 399 */       if (map != null)
/*     */       {
/* 401 */         map.put(uri, resource);
/*     */       }
/* 403 */       return resource;
/*     */     } 
/*     */     
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource createResource(URI uri) {
/* 414 */     return createResource(uri, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource createResource(URI uri, String contentType) {
/* 422 */     Resource.Factory resourceFactory = getResourceFactoryRegistry().getFactory(uri, contentType);
/* 423 */     if (resourceFactory != null) {
/*     */       
/* 425 */       Resource result = resourceFactory.createResource(uri);
/* 426 */       getResources().add(result);
/* 427 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 431 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource.Factory.Registry getResourceFactoryRegistry() {
/* 440 */     if (this.resourceFactoryRegistry == null)
/*     */     {
/* 442 */       this.resourceFactoryRegistry = 
/* 443 */         new ResourceFactoryRegistryImpl()
/*     */         {
/*     */           
/*     */           protected Resource.Factory delegatedGetFactory(URI uri, String contentTypeIdentifier)
/*     */           {
/* 448 */             return 
/* 449 */               convert(
/* 450 */                 getFactory(
/* 451 */                   uri, 
/* 452 */                   Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap(), 
/* 453 */                   Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap(), 
/* 454 */                   Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap(), 
/* 455 */                   contentTypeIdentifier, 
/* 456 */                   false));
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected URIConverter getURIConverter() {
/* 462 */             return ResourceSetImpl.this.getURIConverter();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected Map<?, ?> getContentDescriptionOptions() {
/* 468 */             return ResourceSetImpl.this.getLoadOptions();
/*     */           }
/*     */         };
/*     */     }
/* 472 */     return this.resourceFactoryRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry) {
/* 480 */     this.resourceFactoryRegistry = resourceFactoryRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIConverter getURIConverter() {
/* 488 */     if (this.uriConverter == null)
/*     */     {
/* 490 */       this.uriConverter = new ExtensibleURIConverterImpl();
/*     */     }
/* 492 */     return this.uriConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURIConverter(URIConverter uriConverter) {
/* 500 */     this.uriConverter = uriConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage.Registry getPackageRegistry() {
/* 508 */     if (this.packageRegistry == null)
/*     */     {
/* 510 */       this.packageRegistry = (EPackage.Registry)new EPackageRegistryImpl(EPackage.Registry.INSTANCE);
/*     */     }
/* 512 */     return this.packageRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPackageRegistry(EPackage.Registry packageRegistry) {
/* 520 */     this.packageRegistry = packageRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ResourcesEList<E extends Resource>
/*     */     extends NotifyingInternalEListImpl<E>
/*     */     implements InternalEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isNotificationRequired() {
/* 534 */       return ResourceSetImpl.this.eNotificationRequired();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object[] newData(int capacity) {
/* 540 */       return (Object[])new Resource[capacity];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getNotifier() {
/* 546 */       return ResourceSetImpl.this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getFeatureID() {
/* 552 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useEquals() {
/* 558 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasInverse() {
/* 564 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isUnique() {
/* 570 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected NotificationChain inverseAdd(E object, NotificationChain notifications) {
/* 576 */       Resource.Internal resource = (Resource.Internal)object;
/* 577 */       return resource.basicSetResourceSet(ResourceSetImpl.this, notifications);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected NotificationChain inverseRemove(E object, NotificationChain notifications) {
/* 583 */       Resource.Internal resource = (Resource.Internal)object;
/* 584 */       Map<URI, Resource> map = ResourceSetImpl.this.getURIResourceMap();
/* 585 */       if (map != null)
/*     */       {
/* 587 */         for (Iterator<Resource> i = map.values().iterator(); i.hasNext();) {
/*     */           
/* 589 */           if (resource == i.next())
/*     */           {
/* 591 */             i.remove();
/*     */           }
/*     */         } 
/*     */       }
/* 595 */       return resource.basicSetResourceSet(null, notifications);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean contains(Object object) {
/* 601 */       return (this.size <= 4) ? super.contains(object) : ((object instanceof Resource && ((Resource)object).getResourceSet() == ResourceSetImpl.this));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 612 */     return 
/* 613 */       String.valueOf(getClass().getName()) + '@' + Integer.toHexString(hashCode()) + 
/* 614 */       " resources=" + ((this.resources == null) ? "[]" : this.resources.toString());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ResourceSetImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */