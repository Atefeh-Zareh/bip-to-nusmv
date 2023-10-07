/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.TreeIterator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.ResourceSet;
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
/*     */ public class ECrossReferenceAdapter
/*     */   implements Adapter.Internal
/*     */ {
/*     */   protected class InverseCrossReferencer
/*     */     extends EcoreUtil.CrossReferencer
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected Map<URI, List<EObject>> proxyMap;
/*     */     
/*     */     protected InverseCrossReferencer() {
/*  81 */       super((Collection<?>)null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected EContentsEList.FeatureIterator<EObject> getCrossReferences(EObject eObject) {
/*  87 */       return 
/*  88 */         new ECrossReferenceEList.FeatureIteratorImpl<EObject>(eObject)
/*     */         {
/*     */           
/*     */           protected boolean isIncluded(EStructuralFeature eStructuralFeature)
/*     */           {
/*  93 */             return !(!FeatureMapUtil.isFeatureMap(eStructuralFeature) && !ECrossReferenceAdapter.InverseCrossReferencer.access$0(ECrossReferenceAdapter.InverseCrossReferencer.this).isIncluded((EReference)eStructuralFeature));
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected boolean resolve() {
/*  99 */             return ECrossReferenceAdapter.InverseCrossReferencer.this.resolve();
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 107 */       return ECrossReferenceAdapter.this.isIncluded(eReference);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Collection<EStructuralFeature.Setting> newCollection() {
/* 113 */       return 
/* 114 */         (Collection<EStructuralFeature.Setting>)new BasicEList<EStructuralFeature.Setting>()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected Object[] newData(int capacity) {
/* 121 */             return (Object[])new EStructuralFeature.Setting[capacity];
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean add(EStructuralFeature.Setting setting) {
/* 127 */             EObject eObject = setting.getEObject();
/* 128 */             EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
/* 129 */             EStructuralFeature.Setting[] settingData = (EStructuralFeature.Setting[])this.data;
/* 130 */             for (int i = 0; i < this.size; i++) {
/*     */               
/* 132 */               EStructuralFeature.Setting containedSetting = settingData[i];
/* 133 */               if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature)
/*     */               {
/* 135 */                 return false;
/*     */               }
/*     */             } 
/* 138 */             addUnique(setting);
/* 139 */             return true;
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */     
/*     */     public void add(EObject eObject) {
/* 146 */       handleCrossReference(eObject);
/* 147 */       if (!resolve())
/*     */       {
/* 149 */         addProxy(eObject, eObject);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void add(InternalEObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 156 */       super.add(eObject, eReference, crossReferencedEObject);
/* 157 */       if (!resolve())
/*     */       {
/* 159 */         addProxy(crossReferencedEObject, (EObject)eObject);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void add(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 165 */       add((InternalEObject)eObject, eReference, crossReferencedEObject);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void addProxy(EObject proxy, EObject context) {
/* 170 */       if (proxy.eIsProxy()) {
/*     */         BasicEList.FastCompare<EObject> fastCompare;
/* 172 */         if (this.proxyMap == null)
/*     */         {
/* 174 */           this.proxyMap = new HashMap<URI, List<EObject>>();
/*     */         }
/* 176 */         URI uri = normalizeURI(((InternalEObject)proxy).eProxyURI(), context);
/* 177 */         List<EObject> proxies = this.proxyMap.get(uri);
/* 178 */         if (proxies == null)
/*     */         {
/* 180 */           this.proxyMap.put(uri, fastCompare = new BasicEList.FastCompare());
/*     */         }
/* 182 */         fastCompare.add(proxy);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Object remove(EObject eObject) {
/* 188 */       if (!resolve())
/*     */       {
/* 190 */         removeProxy(eObject, eObject);
/*     */       }
/* 192 */       return remove(eObject);
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 197 */       if (!resolve())
/*     */       {
/* 199 */         removeProxy(crossReferencedEObject, eObject);
/*     */       }
/* 201 */       BasicEList<EStructuralFeature.Setting> collection = (BasicEList<EStructuralFeature.Setting>)get(crossReferencedEObject);
/* 202 */       if (collection != null) {
/*     */         
/* 204 */         EStructuralFeature.Setting[] settingData = (EStructuralFeature.Setting[])collection.data();
/* 205 */         for (int i = 0, size = collection.size(); i < size; i++) {
/*     */           
/* 207 */           EStructuralFeature.Setting setting = settingData[i];
/* 208 */           if (setting.getEObject() == eObject && setting.getEStructuralFeature() == eReference) {
/*     */             
/* 210 */             if (collection.size() == 1) {
/*     */               
/* 212 */               remove(crossReferencedEObject);
/*     */               
/*     */               break;
/*     */             } 
/* 216 */             collection.remove(i);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void removeProxy(EObject proxy, EObject context) {
/* 226 */       if (this.proxyMap != null && proxy.eIsProxy()) {
/*     */         
/* 228 */         URI uri = normalizeURI(((InternalEObject)proxy).eProxyURI(), context);
/* 229 */         List<EObject> proxies = this.proxyMap.get(uri);
/* 230 */         if (proxies != null) {
/*     */           
/* 232 */           proxies.remove(proxy);
/* 233 */           if (proxies.isEmpty())
/*     */           {
/* 235 */             this.proxyMap.remove(uri);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected List<EObject> removeProxies(URI uri) {
/* 243 */       return (this.proxyMap != null) ? this.proxyMap.remove(uri) : null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected URI normalizeURI(URI uri, EObject objectContext) {
/* 250 */       String fragment = uri.fragment();
/* 251 */       if (fragment != null) {
/*     */         
/* 253 */         int length = fragment.length();
/* 254 */         if (length > 0 && fragment.charAt(0) != '/' && fragment.charAt(length - 1) == '?') {
/*     */           
/* 256 */           int index = fragment.lastIndexOf('?', length - 2);
/* 257 */           if (index > 0)
/*     */           {
/* 259 */             uri = uri.trimFragment().appendFragment(fragment.substring(0, index));
/*     */           }
/*     */         } 
/*     */       } 
/* 263 */       Resource resourceContext = objectContext.eResource();
/* 264 */       if (resourceContext != null) {
/*     */         
/* 266 */         ResourceSet resourceSetContext = resourceContext.getResourceSet();
/* 267 */         if (resourceSetContext != null)
/*     */         {
/* 269 */           return resourceSetContext.getURIConverter().normalize(uri);
/*     */         }
/*     */       } 
/* 272 */       return uri;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean resolve() {
/* 278 */       return ECrossReferenceAdapter.this.resolve();
/*     */     } } public static ECrossReferenceAdapter getCrossReferenceAdapter(Notifier notifier) { EList eList = notifier.eAdapters();
/*     */     for (int i = 0, size = eList.size(); i < size; i++) {
/*     */       Object adapter = eList.get(i);
/*     */       if (adapter instanceof ECrossReferenceAdapter)
/*     */         return (ECrossReferenceAdapter)adapter; 
/*     */     } 
/*     */     return null; } protected Set<Resource> unloadedResources = new HashSet<Resource>(); public ECrossReferenceAdapter() {
/* 286 */     this.inverseCrossReferencer = createInverseCrossReferencer();
/*     */   }
/*     */   protected Map<EObject, Resource> unloadedEObjects = new HashMap<EObject, Resource>(); protected InverseCrossReferencer inverseCrossReferencer;
/*     */   
/*     */   public Collection<EStructuralFeature.Setting> getNonNavigableInverseReferences(EObject eObject) {
/* 291 */     return getNonNavigableInverseReferences(eObject, !resolve());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<EStructuralFeature.Setting> getNonNavigableInverseReferences(EObject eObject, boolean resolve) {
/* 296 */     if (resolve)
/*     */     {
/* 298 */       resolveAll(eObject);
/*     */     }
/*     */     
/* 301 */     Collection<EStructuralFeature.Setting> result = this.inverseCrossReferencer.get(eObject);
/* 302 */     if (result == null)
/*     */     {
/* 304 */       result = Collections.emptyList();
/*     */     }
/* 306 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<EStructuralFeature.Setting> getInverseReferences(EObject eObject) {
/* 311 */     return getInverseReferences(eObject, !resolve());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<EStructuralFeature.Setting> getInverseReferences(EObject eObject, boolean resolve) {
/* 316 */     Collection<EStructuralFeature.Setting> result = new ArrayList<EStructuralFeature.Setting>();
/*     */     
/* 318 */     if (resolve)
/*     */     {
/* 320 */       resolveAll(eObject);
/*     */     }
/*     */     
/* 323 */     EObject eContainer = eObject.eContainer();
/* 324 */     if (eContainer != null)
/*     */     {
/* 326 */       result.add(((InternalEObject)eContainer).eSetting((EStructuralFeature)eObject.eContainmentFeature()));
/*     */     }
/*     */     
/* 329 */     Collection<EStructuralFeature.Setting> nonNavigableInverseReferences = this.inverseCrossReferencer.get(eObject);
/* 330 */     if (nonNavigableInverseReferences != null)
/*     */     {
/* 332 */       result.addAll(nonNavigableInverseReferences);
/*     */     }
/*     */     
/* 335 */     for (EReference eReference : eObject.eClass().getEAllReferences()) {
/*     */       
/* 337 */       EReference eOpposite = eReference.getEOpposite();
/* 338 */       if (eOpposite != null && !eReference.isContainer() && eObject.eIsSet((EStructuralFeature)eReference)) {
/*     */         
/* 340 */         if (eReference.isMany()) {
/*     */           
/* 342 */           Object collection = eObject.eGet((EStructuralFeature)eReference);
/* 343 */           Iterator<EObject> j = 
/* 344 */             resolve() ? (
/* 345 */             (Collection<EObject>)collection).iterator() : (
/* 346 */             (InternalEList<EObject>)collection).basicIterator();
/* 347 */           while (j.hasNext()) {
/*     */             
/* 349 */             InternalEObject referencingEObject = (InternalEObject)j.next();
/* 350 */             result.add(referencingEObject.eSetting((EStructuralFeature)eOpposite));
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 355 */         result.add(((InternalEObject)eObject.eGet((EStructuralFeature)eReference, resolve())).eSetting((EStructuralFeature)eOpposite));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 360 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void resolveAll(EObject eObject) {
/* 365 */     if (!eObject.eIsProxy()) {
/*     */       
/* 367 */       Resource resource = eObject.eResource();
/* 368 */       if (resource != null) {
/*     */         
/* 370 */         URI uri = resource.getURI();
/* 371 */         if (uri != null) {
/*     */           
/* 373 */           ResourceSet resourceSet = resource.getResourceSet();
/* 374 */           if (resourceSet != null)
/*     */           {
/* 376 */             uri = resourceSet.getURIConverter().normalize(uri);
/*     */           }
/* 378 */           uri = uri.appendFragment(resource.getURIFragment(eObject));
/*     */         }
/*     */         else {
/*     */           
/* 382 */           URI.createHierarchicalURI(null, null, resource.getURIFragment(eObject));
/*     */         } 
/* 384 */         List<EObject> proxies = this.inverseCrossReferencer.removeProxies(uri);
/* 385 */         if (proxies != null)
/*     */         {
/* 387 */           for (int i = 0, size = proxies.size(); i < size; i++) {
/*     */             
/* 389 */             EObject proxy = proxies.get(i);
/* 390 */             for (EStructuralFeature.Setting setting : getInverseReferences(proxy, false))
/*     */             {
/* 392 */               resolveProxy(resource, eObject, proxy, setting);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void resolveProxy(Resource resource, EObject eObject, EObject proxy, EStructuralFeature.Setting setting) {
/* 402 */     Object value = setting.get(true);
/* 403 */     if (setting.getEStructuralFeature().isMany()) {
/*     */       
/* 405 */       InternalEList<?> list = (InternalEList)value;
/* 406 */       List<?> basicList = list.basicList();
/* 407 */       int index = basicList.indexOf(proxy);
/* 408 */       if (index != -1)
/*     */       {
/* 410 */         list.get(index);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isIncluded(EReference eReference) {
/* 417 */     return (eReference.getEOpposite() == null && !eReference.isDerived());
/*     */   }
/*     */ 
/*     */   
/*     */   protected InverseCrossReferencer createInverseCrossReferencer() {
/* 422 */     return new InverseCrossReferencer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyChanged(Notification notification) {
/* 430 */     selfAdapt(notification);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void selfAdapt(Notification notification) {
/* 439 */     Object notifier = notification.getNotifier();
/* 440 */     if (notifier instanceof EObject) {
/*     */       
/* 442 */       Object feature = notification.getFeature();
/* 443 */       if (feature instanceof EReference) {
/*     */         
/* 445 */         EReference reference = (EReference)feature;
/* 446 */         if (reference.isContainment())
/*     */         {
/* 448 */           handleContainment(notification);
/*     */         }
/* 450 */         else if (isIncluded(reference))
/*     */         {
/* 452 */           handleCrossReference(reference, notification);
/*     */         }
/*     */       
/*     */       } 
/* 456 */     } else if (notifier instanceof Resource) {
/*     */       Iterator<Map.Entry<EObject, Resource>> i;
/* 458 */       switch (notification.getFeatureID(Resource.class)) {
/*     */ 
/*     */         
/*     */         case 2:
/* 462 */           if (!this.unloadedResources.contains(notifier)) {
/*     */             Resource resource;
/* 464 */             switch (notification.getEventType()) {
/*     */ 
/*     */               
/*     */               case 4:
/* 468 */                 resource = (Resource)notifier;
/* 469 */                 if (!resource.isLoaded()) {
/*     */                   
/* 471 */                   EObject eObject = (EObject)notification.getOldValue();
/* 472 */                   this.unloadedEObjects.put(eObject, resource);
/* 473 */                   for (TreeIterator<?> treeIterator = EcoreUtil.getAllProperContents(eObject, false); treeIterator.hasNext();)
/*     */                   {
/* 475 */                     this.unloadedEObjects.put((EObject)treeIterator.next(), resource);
/*     */                   }
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */               
/*     */               case 6:
/* 482 */                 resource = (Resource)notifier;
/* 483 */                 if (!resource.isLoaded()) {
/*     */ 
/*     */                   
/* 486 */                   List<EObject> eObjects = (List<EObject>)notification.getOldValue();
/* 487 */                   for (TreeIterator<?> treeIterator = EcoreUtil.getAllProperContents(eObjects, false); treeIterator.hasNext();)
/*     */                   {
/* 489 */                     this.unloadedEObjects.put((EObject)treeIterator.next(), resource);
/*     */                   }
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */ 
/*     */             
/* 496 */             handleContainment(notification);
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 505 */           if (notification.getNewBooleanValue()) {
/*     */             
/* 507 */             this.unloadedResources.remove(notifier);
/* 508 */             for (Notifier child : ((Resource)notifier).getContents())
/*     */             {
/* 510 */               addAdapter(child);
/*     */             }
/*     */             
/*     */             break;
/*     */           } 
/* 515 */           this.unloadedResources.add((Resource)notifier);
/* 516 */           for (i = this.unloadedEObjects.entrySet().iterator(); i.hasNext(); ) {
/*     */             
/* 518 */             Map.Entry<EObject, Resource> entry = i.next();
/* 519 */             if (entry.getValue() == notifier) {
/*     */               
/* 521 */               i.remove();
/* 522 */               EObject eObject = entry.getKey();
/* 523 */               Collection<EStructuralFeature.Setting> settings = this.inverseCrossReferencer.get(eObject);
/* 524 */               if (settings != null)
/*     */               {
/* 526 */                 for (EStructuralFeature.Setting setting : settings)
/*     */                 {
/* 528 */                   this.inverseCrossReferencer.addProxy(eObject, setting.getEObject());
/*     */                 }
/*     */               }
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/* 538 */     } else if (notifier instanceof ResourceSet) {
/*     */       
/* 540 */       if (notification.getFeatureID(ResourceSet.class) == 0)
/*     */       {
/* 542 */         handleContainment(notification);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleContainment(Notification notification) {
/*     */     Notifier oldValue;
/*     */     Object object;
/*     */     Notifier newValue;
/*     */     Notifier notifier1;
/* 552 */     switch (notification.getEventType()) {
/*     */ 
/*     */       
/*     */       case 9:
/* 556 */         oldValue = (Notifier)notification.getOldValue();
/* 557 */         removeAdapter(oldValue);
/* 558 */         notifier1 = (Notifier)notification.getNewValue();
/* 559 */         addAdapter(notifier1);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 564 */         object = notification.getNewValue();
/* 565 */         if (object != null && object != Boolean.TRUE && object != Boolean.FALSE)
/*     */         {
/* 567 */           addAdapter((Notifier)object);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 573 */         newValue = (Notifier)notification.getNewValue();
/* 574 */         if (newValue != null)
/*     */         {
/* 576 */           addAdapter(newValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 582 */         newValue = (Notifier)notification.getNewValue();
/* 583 */         if (newValue != null)
/*     */         {
/* 585 */           addAdapter(newValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 591 */         for (Object object1 : notification.getNewValue())
/*     */         {
/* 593 */           addAdapter((Notifier)object1); } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleCrossReference(EReference reference, Notification notification) {
/*     */     EObject eObject2;
/*     */     EObject newValue;
/*     */     EObject eObject1;
/*     */     EObject oldValue;
/*     */     EObject notifier;
/*     */     EReference feature;
/* 605 */     switch (notification.getEventType()) {
/*     */ 
/*     */       
/*     */       case 1:
/*     */       case 2:
/*     */       case 9:
/* 611 */         eObject2 = (EObject)notification.getNotifier();
/* 612 */         feature = (EReference)notification.getFeature();
/* 613 */         if (!feature.isMany() || notification.getPosition() != -1) {
/*     */           
/* 615 */           EObject eObject3 = (EObject)notification.getOldValue();
/* 616 */           if (eObject3 != null)
/*     */           {
/* 618 */             this.inverseCrossReferencer.remove(eObject2, feature, eObject3);
/*     */           }
/* 620 */           EObject eObject4 = (EObject)notification.getNewValue();
/* 621 */           if (eObject4 != null)
/*     */           {
/* 623 */             this.inverseCrossReferencer.add(eObject2, feature, eObject4);
/*     */           }
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 630 */         newValue = (EObject)notification.getNewValue();
/* 631 */         if (newValue != null)
/*     */         {
/* 633 */           this.inverseCrossReferencer.add((EObject)notification.getNotifier(), (EReference)notification.getFeature(), newValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 639 */         eObject1 = (EObject)notification.getNotifier();
/* 640 */         feature = (EReference)notification.getFeature();
/* 641 */         for (Object object : notification.getNewValue())
/*     */         {
/* 643 */           this.inverseCrossReferencer.add(eObject1, feature, (EObject)object);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 649 */         oldValue = (EObject)notification.getOldValue();
/* 650 */         if (oldValue != null)
/*     */         {
/* 652 */           this.inverseCrossReferencer.remove((EObject)notification.getNotifier(), (EReference)notification.getFeature(), oldValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 658 */         notifier = (EObject)notification.getNotifier();
/* 659 */         feature = (EReference)notification.getFeature();
/* 660 */         for (Object object : notification.getOldValue())
/*     */         {
/* 662 */           this.inverseCrossReferencer.remove(notifier, feature, (EObject)object);
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(Notifier target) {
/* 675 */     if (target instanceof EObject) {
/*     */       
/* 677 */       setTarget((EObject)target);
/*     */     }
/* 679 */     else if (target instanceof Resource) {
/*     */       
/* 681 */       setTarget((Resource)target);
/*     */     }
/* 683 */     else if (target instanceof ResourceSet) {
/*     */       
/* 685 */       setTarget((ResourceSet)target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(EObject target) {
/* 695 */     this.inverseCrossReferencer.add(target);
/* 696 */     Iterator<EObject> i = 
/* 697 */       resolve() ? 
/* 698 */       target.eContents().iterator() : (
/* 699 */       (InternalEList<EObject>)target.eContents()).basicIterator();
/* 700 */     while (i.hasNext()) {
/*     */       
/* 702 */       Notifier notifier = (Notifier)i.next();
/* 703 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(Resource target) {
/* 713 */     if (!target.isLoaded())
/*     */     {
/* 715 */       this.unloadedResources.add(target);
/*     */     }
/* 717 */     EList<Notifier> eList = target.getContents();
/* 718 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*     */       
/* 720 */       Notifier notifier = eList.get(i);
/* 721 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(ResourceSet target) {
/* 731 */     EList<Notifier> eList = target.getResources();
/* 732 */     for (int i = 0; i < eList.size(); i++) {
/*     */       
/* 734 */       Notifier notifier = eList.get(i);
/* 735 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetTarget(Notifier target) {
/* 745 */     if (target instanceof EObject) {
/*     */       
/* 747 */       unsetTarget((EObject)target);
/*     */     }
/* 749 */     else if (target instanceof Resource) {
/*     */       
/* 751 */       unsetTarget((Resource)target);
/*     */     }
/* 753 */     else if (target instanceof ResourceSet) {
/*     */       
/* 755 */       unsetTarget((ResourceSet)target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(EObject target) {
/* 765 */     for (EContentsEList.FeatureIterator<EObject> featureIterator = this.inverseCrossReferencer.getCrossReferences(target); featureIterator.hasNext(); ) {
/*     */       
/* 767 */       EObject crossReferencedEObject = featureIterator.next();
/* 768 */       this.inverseCrossReferencer.remove(target, (EReference)featureIterator.feature(), crossReferencedEObject);
/*     */     } 
/*     */     
/* 771 */     Iterator<InternalEObject> i = 
/* 772 */       resolve() ? 
/* 773 */       target.eContents().iterator() : (
/* 774 */       (InternalEList<InternalEObject>)target.eContents()).basicIterator();
/* 775 */     while (i.hasNext()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 780 */       InternalEObject internalEObject = i.next();
/* 781 */       Resource.Internal internal = internalEObject.eDirectResource();
/* 782 */       if (internal == null || !internal.eAdapters().contains(this))
/*     */       {
/* 784 */         removeAdapter((Notifier)internalEObject);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(Resource target) {
/* 795 */     EList<Notifier> eList = target.getContents();
/* 796 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*     */       
/* 798 */       Notifier notifier = eList.get(i);
/* 799 */       removeAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(ResourceSet target) {
/* 809 */     EList<Notifier> eList = target.getResources();
/* 810 */     for (int i = 0; i < eList.size(); i++) {
/*     */       
/* 812 */       Notifier notifier = eList.get(i);
/* 813 */       removeAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAdapter(Notifier notifier) {
/* 819 */     EList<ECrossReferenceAdapter> eList = notifier.eAdapters();
/* 820 */     if (!eList.contains(this))
/*     */     {
/* 822 */       eList.add(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeAdapter(Notifier notifier) {
/* 828 */     notifier.eAdapters().remove(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dump() {
/* 833 */     EcoreUtil.CrossReferencer.print(System.out, this.inverseCrossReferencer);
/*     */   }
/*     */ 
/*     */   
/*     */   public Notifier getTarget() {
/* 838 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdapterForType(Object type) {
/* 843 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean resolve() {
/* 848 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\ECrossReferenceAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */