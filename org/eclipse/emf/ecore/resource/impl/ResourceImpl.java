/*      */ package org.eclipse.emf.ecore.resource.impl;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipInputStream;
/*      */ import java.util.zip.ZipOutputStream;
/*      */ import org.eclipse.emf.common.notify.Adapter;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.AdapterImpl;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*      */ import org.eclipse.emf.common.notify.impl.NotifierImpl;
/*      */ import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
/*      */ import org.eclipse.emf.common.util.AbstractTreeIterator;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.TreeIterator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.common.util.WrappedException;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*      */ import org.eclipse.emf.ecore.resource.URIConverter;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ResourceImpl
/*      */   extends NotifierImpl
/*      */   implements Resource, Resource.Internal
/*      */ {
/*      */   private static URIConverter defaultURIConverter;
/*      */   protected Map<Object, Object> defaultSaveOptions;
/*      */   protected Map<Object, Object> defaultLoadOptions;
/*      */   protected Map<Object, Object> defaultDeleteOptions;
/*      */   protected ResourceSet resourceSet;
/*      */   protected URI uri;
/*      */   protected long timeStamp;
/*      */   protected ContentsEList<EObject> contents;
/*      */   protected EList<Resource.Diagnostic> errors;
/*      */   protected EList<Resource.Diagnostic> warnings;
/*      */   protected boolean isModified;
/*      */   protected boolean isLoaded;
/*      */   protected boolean isLoading;
/*      */   protected List<EObject> unloadingContents;
/*      */   protected Adapter modificationTrackingAdapter;
/*      */   protected Map<String, EObject> intrinsicIDToEObjectMap;
/*      */   
/*      */   protected static URIConverter getDefaultURIConverter() {
/*  114 */     if (defaultURIConverter == null)
/*      */     {
/*  116 */       defaultURIConverter = new ExtensibleURIConverterImpl();
/*      */     }
/*  118 */     return defaultURIConverter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Map<?, ?> mergeMaps(Map<?, ?> map1, Map<?, ?> map2) {
/*  128 */     if (map1 == null || map1.isEmpty())
/*      */     {
/*  130 */       return map2;
/*      */     }
/*  132 */     if (map2 == null || map2.isEmpty())
/*      */     {
/*  134 */       return map1;
/*      */     }
/*      */ 
/*      */     
/*  138 */     Map<Object, Object> mergedMap = new HashMap<Object, Object>(map2);
/*  139 */     mergedMap.putAll(map1);
/*  140 */     return mergedMap;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceImpl() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceImpl(URI uri) {
/*  248 */     this();
/*  249 */     this.uri = uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceSet getResourceSet() {
/*  257 */     return this.resourceSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
/*      */     NotificationChainImpl notificationChainImpl;
/*  268 */     ResourceSet oldResourceSet = this.resourceSet;
/*  269 */     if (oldResourceSet != null)
/*      */     {
/*  271 */       notifications = ((InternalEList)oldResourceSet.getResources()).basicRemove(this, notifications);
/*      */     }
/*      */     
/*  274 */     this.resourceSet = resourceSet;
/*      */     
/*  276 */     if (eNotificationRequired()) {
/*      */       
/*  278 */       if (notifications == null)
/*      */       {
/*  280 */         notificationChainImpl = new NotificationChainImpl(2);
/*      */       }
/*  282 */       notificationChainImpl.add(
/*  283 */           (Notification)new NotificationImpl(1, oldResourceSet, resourceSet)
/*      */           {
/*      */             
/*      */             public Object getNotifier()
/*      */             {
/*  288 */               return ResourceImpl.this;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public int getFeatureID(Class<?> expectedClass) {
/*  294 */               return 0;
/*      */             }
/*      */           });
/*      */     } 
/*      */     
/*  299 */     return (NotificationChain)notificationChainImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI getURI() {
/*  307 */     return this.uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setURI(URI uri) {
/*  315 */     URI oldURI = this.uri;
/*  316 */     this.uri = uri;
/*  317 */     if (eNotificationRequired()) {
/*      */       
/*  319 */       NotificationImpl notificationImpl = 
/*  320 */         new NotificationImpl(1, oldURI, uri)
/*      */         {
/*      */           
/*      */           public Object getNotifier()
/*      */           {
/*  325 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID(Class<?> expectedClass) {
/*  331 */             return 1;
/*      */           }
/*      */         };
/*  334 */       eNotify((Notification)notificationImpl);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeStamp() {
/*  340 */     return this.timeStamp;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeStamp(long timeStamp) {
/*  345 */     long oldTimeStamp = this.timeStamp;
/*  346 */     this.timeStamp = timeStamp;
/*  347 */     if (eNotificationRequired()) {
/*      */       
/*  349 */       NotificationImpl notificationImpl = 
/*  350 */         new NotificationImpl(1, oldTimeStamp, timeStamp)
/*      */         {
/*      */           
/*      */           public Object getNotifier()
/*      */           {
/*  355 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID(Class<?> expectedClass) {
/*  361 */             return 8;
/*      */           }
/*      */         };
/*  364 */       eNotify((Notification)notificationImpl);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ContentsEList<E extends EObject>
/*      */     extends NotifyingInternalEListImpl<E>
/*      */     implements InternalEList<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     
/*      */     public Object getNotifier() {
/*  378 */       return ResourceImpl.this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getFeatureID() {
/*  384 */       return 2;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isNotificationRequired() {
/*  390 */       return ResourceImpl.this.eNotificationRequired();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean useEquals() {
/*  396 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean hasInverse() {
/*  402 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnique() {
/*  408 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain inverseAdd(E object, NotificationChain notifications) {
/*  414 */       InternalEObject eObject = (InternalEObject)object;
/*  415 */       notifications = eObject.eSetResource(ResourceImpl.this, notifications);
/*  416 */       ResourceImpl.this.attached((EObject)eObject);
/*  417 */       return notifications;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain inverseRemove(E object, NotificationChain notifications) {
/*  423 */       InternalEObject eObject = (InternalEObject)object;
/*  424 */       if (ResourceImpl.this.isLoaded)
/*      */       {
/*  426 */         ResourceImpl.this.detached((EObject)eObject);
/*      */       }
/*  428 */       return eObject.eSetResource(null, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object[] newData(int capacity) {
/*  434 */       return (Object[])new EObject[capacity];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void didAdd(int index, E object) {
/*  440 */       super.didAdd(index, object);
/*  441 */       if (index == this.size - 1)
/*      */       {
/*  443 */         loaded();
/*      */       }
/*  445 */       modified();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void didRemove(int index, E object) {
/*  451 */       super.didRemove(index, object);
/*  452 */       modified();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void didSet(int index, E newObject, E oldObject) {
/*  458 */       super.didSet(index, newObject, oldObject);
/*  459 */       modified();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void didClear(int oldSize, Object[] oldData) {
/*  465 */       if (oldSize == 0) {
/*      */         
/*  467 */         loaded();
/*      */       }
/*      */       else {
/*      */         
/*  471 */         super.didClear(oldSize, oldData);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void loaded() {
/*  477 */       if (!ResourceImpl.this.isLoaded()) {
/*      */         
/*  479 */         Notification notification = ResourceImpl.this.setLoaded(true);
/*  480 */         if (notification != null)
/*      */         {
/*  482 */           ResourceImpl.this.eNotify(notification);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void modified() {
/*  489 */       if (ResourceImpl.this.isTrackingModification())
/*      */       {
/*  491 */         ResourceImpl.this.setModified(true);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean contains(Object object) {
/*  498 */       return (this.size <= 4) ? super.contains(object) : ((object instanceof InternalEObject && ((InternalEObject)object).eDirectResource() == ResourceImpl.this));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EObject> getContents() {
/*  507 */     if (this.contents == null)
/*      */     {
/*  509 */       this.contents = new ContentsEList<EObject>();
/*      */     }
/*  511 */     return (EList<EObject>)this.contents;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TreeIterator<EObject> getAllContents() {
/*  519 */     return 
/*  520 */       (TreeIterator<EObject>)new AbstractTreeIterator<EObject>(this, false)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<EObject> getChildren(Object object) {
/*  527 */           return (object == ResourceImpl.this) ? ResourceImpl.this.getContents().iterator() : ((EObject)object).eContents().iterator();
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   protected TreeIterator<EObject> getAllProperContents(EObject eObject) {
/*  534 */     return EcoreUtil.getAllProperContents(eObject, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected TreeIterator<EObject> getAllProperContents(List<EObject> contents) {
/*  539 */     return 
/*  540 */       (TreeIterator<EObject>)new EcoreUtil.ContentTreeIterator<EObject>(contents, false)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<EObject> getChildren(Object object) {
/*  548 */           return 
/*  549 */             (object == this.object) ? (
/*  550 */             (List<EObject>)object).iterator() : 
/*  551 */             (Iterator<EObject>)new EcoreUtil.ProperContentIterator((EObject)object);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<Resource.Diagnostic> getErrors() {
/*  561 */     if (this.errors == null)
/*      */     {
/*  563 */       this.errors = 
/*  564 */         (EList<Resource.Diagnostic>)new NotifyingListImpl<Resource.Diagnostic>()
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isNotificationRequired() {
/*  571 */             return ResourceImpl.this.eNotificationRequired();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public Object getNotifier() {
/*  577 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID() {
/*  583 */             return 6;
/*      */           }
/*      */         };
/*      */     }
/*  587 */     return this.errors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<Resource.Diagnostic> getWarnings() {
/*  595 */     if (this.warnings == null)
/*      */     {
/*  597 */       this.warnings = 
/*  598 */         (EList<Resource.Diagnostic>)new NotifyingListImpl<Resource.Diagnostic>()
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isNotificationRequired() {
/*  605 */             return ResourceImpl.this.eNotificationRequired();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public Object getNotifier() {
/*  611 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID() {
/*  617 */             return 7;
/*      */           }
/*      */         };
/*      */     }
/*  621 */     return this.warnings;
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
/*      */   protected boolean useZip() {
/*  636 */     return false;
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
/*      */   protected String getURIFragmentRootSegment(EObject eObject) {
/*  649 */     List<EObject> contents = (this.unloadingContents != null) ? this.unloadingContents : (List<EObject>)getContents();
/*  650 */     return (contents.size() > 1) ? 
/*  651 */       Integer.toString(contents.indexOf(eObject)) : 
/*  652 */       "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getURIFragment(EObject eObject) {
/*  660 */     String id = EcoreUtil.getID(eObject);
/*  661 */     if (id != null)
/*      */     {
/*  663 */       return id;
/*      */     }
/*      */ 
/*      */     
/*  667 */     InternalEObject internalEObject = (InternalEObject)eObject;
/*  668 */     if (internalEObject.eDirectResource() == this || (this.unloadingContents != null && this.unloadingContents.contains(internalEObject)))
/*      */     {
/*  670 */       return "/" + getURIFragmentRootSegment(eObject);
/*      */     }
/*      */ 
/*      */     
/*  674 */     List<String> uriFragmentPath = new ArrayList<String>();
/*  675 */     boolean isContained = false;
/*  676 */     for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer()) {
/*      */       
/*  678 */       uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), (EObject)internalEObject));
/*  679 */       internalEObject = container;
/*  680 */       if (container.eDirectResource() == this || (this.unloadingContents != null && this.unloadingContents.contains(container))) {
/*      */         
/*  682 */         isContained = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  687 */     if (!isContained)
/*      */     {
/*  689 */       return "/-1";
/*      */     }
/*      */     
/*  692 */     StringBuffer result = new StringBuffer("/");
/*  693 */     result.append(getURIFragmentRootSegment((EObject)internalEObject));
/*      */     
/*  695 */     for (int i = uriFragmentPath.size() - 1; i >= 0; i--) {
/*      */       
/*  697 */       result.append('/');
/*  698 */       result.append(uriFragmentPath.get(i));
/*      */     } 
/*  700 */     return result.toString();
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
/*      */   protected EObject getEObjectForURIFragmentRootSegment(String uriFragmentRootSegment) {
/*  713 */     int position = 0;
/*  714 */     if (uriFragmentRootSegment.length() > 0) {
/*      */       
/*      */       try {
/*      */         
/*  718 */         position = Integer.parseInt(uriFragmentRootSegment);
/*      */       }
/*  720 */       catch (NumberFormatException exception) {
/*      */         
/*  722 */         throw new WrappedException(exception);
/*      */       } 
/*      */     }
/*      */     
/*  726 */     EList<EObject> eList = getContents();
/*  727 */     if (position < eList.size() && position >= 0)
/*      */     {
/*  729 */       return eList.get(position);
/*      */     }
/*      */ 
/*      */     
/*  733 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EObject getEObject(String uriFragment) {
/*  742 */     int length = uriFragment.length();
/*  743 */     if (length > 0) {
/*      */       
/*  745 */       if (uriFragment.charAt(0) == '/') {
/*      */         
/*  747 */         ArrayList<String> uriFragmentPath = new ArrayList<String>(4);
/*  748 */         int start = 1;
/*  749 */         for (int i = 1; i < length; i++) {
/*      */           
/*  751 */           if (uriFragment.charAt(i) == '/') {
/*      */             
/*  753 */             uriFragmentPath.add((start == i) ? "" : uriFragment.substring(start, i));
/*  754 */             start = i + 1;
/*      */           } 
/*      */         } 
/*  757 */         uriFragmentPath.add(uriFragment.substring(start));
/*  758 */         return getEObject(uriFragmentPath);
/*      */       } 
/*  760 */       if (uriFragment.charAt(length - 1) == '?') {
/*      */         
/*  762 */         int index = uriFragment.lastIndexOf('?', length - 2);
/*  763 */         if (index > 0)
/*      */         {
/*  765 */           uriFragment = uriFragment.substring(0, index);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  770 */     return getEObjectByID(uriFragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject getEObject(List<String> uriFragmentPath) {
/*  778 */     int size = uriFragmentPath.size();
/*  779 */     EObject eObject = getEObjectForURIFragmentRootSegment((size == 0) ? "" : uriFragmentPath.get(0));
/*  780 */     for (int i = 1; i < size && eObject != null; i++)
/*      */     {
/*  782 */       eObject = ((InternalEObject)eObject).eObjectForURIFragmentSegment(uriFragmentPath.get(i));
/*      */     }
/*      */     
/*  785 */     return eObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<String, EObject> getIntrinsicIDToEObjectMap() {
/*  796 */     return this.intrinsicIDToEObjectMap;
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
/*      */   public void setIntrinsicIDToEObjectMap(Map<String, EObject> intrinsicIDToEObjectMap) {
/*  810 */     this.intrinsicIDToEObjectMap = intrinsicIDToEObjectMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject getEObjectByID(String id) {
/*  819 */     Map<String, EObject> map = getIntrinsicIDToEObjectMap();
/*  820 */     if (map != null) {
/*      */       
/*  822 */       EObject eObject = map.get(id);
/*  823 */       if (eObject != null)
/*      */       {
/*  825 */         return eObject;
/*      */       }
/*      */     } 
/*      */     
/*  829 */     EObject result = null;
/*  830 */     for (TreeIterator<EObject> i = getAllProperContents((List<EObject>)getContents()); i.hasNext(); ) {
/*      */       
/*  832 */       EObject eObject = (EObject)i.next();
/*  833 */       String eObjectId = EcoreUtil.getID(eObject);
/*  834 */       if (eObjectId != null) {
/*      */         
/*  836 */         if (map != null)
/*      */         {
/*  838 */           map.put(eObjectId, eObject);
/*      */         }
/*      */         
/*  841 */         if (eObjectId.equals(id)) {
/*      */           
/*  843 */           result = eObject;
/*  844 */           if (map == null) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  852 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public void attached(EObject eObject) {
/*  857 */     if (isAttachedDetachedHelperRequired()) {
/*      */       
/*  859 */       attachedHelper(eObject);
/*  860 */       for (TreeIterator<EObject> tree = getAllProperContents(eObject); tree.hasNext();)
/*      */       {
/*  862 */         attachedHelper((EObject)tree.next());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isAttachedDetachedHelperRequired() {
/*  869 */     return !(!isTrackingModification() && getIntrinsicIDToEObjectMap() == null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void attachedHelper(EObject eObject) {
/*  874 */     if (isTrackingModification())
/*      */     {
/*  876 */       eObject.eAdapters().add(this.modificationTrackingAdapter);
/*      */     }
/*      */     
/*  879 */     Map<String, EObject> map = getIntrinsicIDToEObjectMap();
/*  880 */     if (map != null) {
/*      */       
/*  882 */       String id = EcoreUtil.getID(eObject);
/*  883 */       if (id != null)
/*      */       {
/*  885 */         map.put(id, eObject);
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
/*      */   @Deprecated
/*      */   protected final void addModificationTrackingAdapters(EObject eObject) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void detached(EObject eObject) {
/*  906 */     if (isAttachedDetachedHelperRequired()) {
/*      */       
/*  908 */       detachedHelper(eObject);
/*  909 */       for (TreeIterator<EObject> tree = getAllProperContents(eObject); tree.hasNext();)
/*      */       {
/*  911 */         detachedHelper((EObject)tree.next());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void detachedHelper(EObject eObject) {
/*  918 */     Map<String, EObject> map = getIntrinsicIDToEObjectMap();
/*  919 */     if (map != null) {
/*      */       
/*  921 */       String id = EcoreUtil.getID(eObject);
/*  922 */       if (id != null)
/*      */       {
/*  924 */         map.remove(id);
/*      */       }
/*      */     } 
/*      */     
/*  928 */     if (isTrackingModification())
/*      */     {
/*  930 */       eObject.eAdapters().remove(this.modificationTrackingAdapter);
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
/*      */   @Deprecated
/*      */   protected final void removeModificationTrackingAdapters(EObject eObject) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected URIConverter getURIConverter() {
/*  957 */     return 
/*  958 */       (getResourceSet() == null) ? 
/*  959 */       getDefaultURIConverter() : 
/*  960 */       getResourceSet().getURIConverter();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(Map<?, ?> options) throws IOException {
/*  968 */     Object saveOnlyIfChanged = 
/*  969 */       (options != null && options.containsKey("SAVE_ONLY_IF_CHANGED")) ? 
/*  970 */       options.get("SAVE_ONLY_IF_CHANGED") : (
/*  971 */       (this.defaultSaveOptions != null) ? 
/*  972 */       this.defaultSaveOptions.get("SAVE_ONLY_IF_CHANGED") : 
/*  973 */       null);
/*  974 */     if ("FILE_BUFFER".equals(saveOnlyIfChanged)) {
/*      */       
/*  976 */       saveOnlyIfChangedWithFileBuffer(options);
/*      */     }
/*  978 */     else if ("MEMORY_BUFFER".equals(saveOnlyIfChanged)) {
/*      */       
/*  980 */       saveOnlyIfChangedWithMemoryBuffer(options);
/*      */     } else {
/*      */       Long timeStamp;
/*      */       
/*  984 */       Map<?, ?> response = (options == null) ? null : (Map<?, ?>)options.get("RESPONSE");
/*  985 */       if (response == null)
/*      */       {
/*  987 */         response = new HashMap<Object, Object>();
/*      */       }
/*  989 */       URIConverter uriConverter = getURIConverter();
/*  990 */       OutputStream outputStream = uriConverter.createOutputStream(getURI(), new ExtensibleURIConverterImpl.OptionsMap("RESPONSE", response, options));
/*      */       
/*      */       try {
/*  993 */         save(outputStream, options);
/*      */       }
/*      */       finally {
/*      */         
/*  997 */         outputStream.close();
/*  998 */         Long long_ = (Long)response.get("TIME_STAMP");
/*  999 */         if (long_ != null)
/*      */         {
/* 1001 */           setTimeStamp(long_.longValue());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void saveOnlyIfChangedWithFileBuffer(Map<?, ?> options) throws IOException {
/* 1009 */     File temporaryFile = File.createTempFile("ResourceSaveHelper", null);
/*      */     
/*      */     try {
/* 1012 */       URI temporaryFileURI = URI.createFileURI(temporaryFile.getPath());
/* 1013 */       URIConverter uriConverter = getURIConverter();
/* 1014 */       OutputStream temporaryFileOutputStream = uriConverter.createOutputStream(temporaryFileURI, null);
/*      */       
/*      */       try {
/* 1017 */         save(temporaryFileOutputStream, options);
/*      */       }
/*      */       finally {
/*      */         
/* 1021 */         temporaryFileOutputStream.close();
/*      */       } 
/*      */       
/* 1024 */       boolean equal = true;
/* 1025 */       InputStream oldContents = null;
/*      */       
/*      */       try {
/* 1028 */         oldContents = getUnderlyingInputStream(uriConverter.createInputStream(getURI(), this.defaultLoadOptions), options);
/*      */       }
/* 1030 */       catch (IOException exception) {
/*      */         
/* 1032 */         equal = false;
/*      */       } 
/* 1034 */       byte[] newContentBuffer = new byte[4000];
/* 1035 */       if (oldContents != null) {
/*      */         
/*      */         try {
/*      */           
/* 1039 */           InputStream newContents = getUnderlyingInputStream(uriConverter.createInputStream(temporaryFileURI, null), options);
/*      */           
/*      */           try {
/* 1042 */             byte[] oldContentBuffer = new byte[4000];
/*      */             
/* 1044 */             int oldLength = oldContents.read(oldContentBuffer), newLength = newContents.read(newContentBuffer); while (true) {
/* 1045 */               if (!(equal = (oldLength == newLength)) || oldLength <= 0) {
/*      */                 break;
/*      */               }
/* 1048 */               for (int i = 0; i < oldLength; i++) {
/*      */                 
/* 1050 */                 if (oldContentBuffer[i] != newContentBuffer[i]) {
/*      */                   
/* 1052 */                   equal = false;
/*      */                   // Byte code: goto -> 239
/*      */                 } 
/*      */               } 
/*      */               oldLength = oldContents.read(oldContentBuffer);
/*      */               newLength = newContents.read(newContentBuffer);
/*      */             } 
/*      */           } finally {
/* 1060 */             newContents.close();
/*      */           }
/*      */         
/*      */         } finally {
/*      */           
/* 1065 */           oldContents.close();
/*      */         } 
/*      */       }
/*      */       
/* 1069 */       if (!equal) {
/*      */         Long timeStamp;
/* 1071 */         Map<?, ?> response = (options == null) ? null : (Map<?, ?>)options.get("RESPONSE");
/* 1072 */         if (response == null)
/*      */         {
/* 1074 */           response = new HashMap<Object, Object>();
/*      */         }
/* 1076 */         OutputStream newContents = uriConverter.createOutputStream(getURI(), new ExtensibleURIConverterImpl.OptionsMap("RESPONSE", response, options));
/*      */         
/*      */         try {
/* 1079 */           InputStream temporaryFileContents = uriConverter.createInputStream(temporaryFileURI, null);
/*      */           
/*      */           try {
/* 1082 */             for (int length = temporaryFileContents.read(newContentBuffer); length > 0; length = temporaryFileContents.read(newContentBuffer))
/*      */             {
/* 1084 */               newContents.write(newContentBuffer, 0, length);
/*      */             }
/*      */           }
/*      */           finally {
/*      */             
/* 1089 */             temporaryFileContents.close();
/*      */           }
/*      */         
/*      */         } finally {
/*      */           
/* 1094 */           newContents.close();
/* 1095 */           Long long_ = (Long)response.get("TIME_STAMP");
/* 1096 */           if (long_ != null)
/*      */           {
/* 1098 */             setTimeStamp(long_.longValue());
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } finally {
/*      */       
/* 1105 */       temporaryFile.delete();
/*      */     } 
/*      */   }
/*      */   protected void saveOnlyIfChangedWithMemoryBuffer(Map<?, ?> options) throws IOException {
/*      */     byte[] underlyingNewContentBuffer;
/*      */     int underlyingLength;
/* 1111 */     URIConverter uriConverter = getURIConverter();
/*      */     class MyByteArrayOutputStream
/*      */       extends ByteArrayOutputStream
/*      */     {
/*      */       public byte[] buffer() {
/* 1116 */         return this.buf;
/*      */       }
/*      */ 
/*      */       
/*      */       public int length() {
/* 1121 */         return this.count;
/*      */       }
/*      */     };
/* 1124 */     MyByteArrayOutputStream memoryBuffer = new MyByteArrayOutputStream();
/*      */     
/*      */     try {
/* 1127 */       save(memoryBuffer, options);
/*      */     }
/*      */     finally {
/*      */       
/* 1131 */       memoryBuffer.close();
/*      */     } 
/*      */     
/* 1134 */     byte[] newContentBuffer = memoryBuffer.buffer();
/* 1135 */     int length = memoryBuffer.length();
/* 1136 */     ByteArrayInputStream inputStream = new ByteArrayInputStream(newContentBuffer);
/* 1137 */     InputStream underlyingInputStream = getUnderlyingInputStream(inputStream, options);
/*      */ 
/*      */     
/* 1140 */     if (inputStream == underlyingInputStream) {
/*      */       
/* 1142 */       underlyingNewContentBuffer = newContentBuffer;
/* 1143 */       underlyingLength = length;
/*      */     }
/*      */     else {
/*      */       
/* 1147 */       ByteArrayOutputStream bytes = new ByteArrayOutputStream();
/* 1148 */       byte[] buffer = new byte[4000];
/* 1149 */       for (int count = underlyingInputStream.read(buffer); count > 0; count = underlyingInputStream.read(buffer))
/*      */       {
/* 1151 */         bytes.write(buffer, 0, count);
/*      */       }
/* 1153 */       bytes.close();
/* 1154 */       underlyingInputStream.close();
/* 1155 */       underlyingNewContentBuffer = bytes.toByteArray();
/* 1156 */       underlyingLength = underlyingNewContentBuffer.length;
/*      */     } 
/*      */     
/* 1159 */     boolean equal = true;
/* 1160 */     InputStream oldContents = null;
/*      */     
/*      */     try {
/* 1163 */       oldContents = getUnderlyingInputStream(uriConverter.createInputStream(getURI(), this.defaultLoadOptions), options);
/*      */     }
/* 1165 */     catch (IOException exception) {
/*      */       
/* 1167 */       equal = false;
/*      */     } 
/* 1169 */     if (oldContents != null) {
/*      */       
/*      */       try {
/*      */         
/* 1173 */         byte[] oldContentBuffer = new byte[underlyingLength];
/* 1174 */         int count = oldContents.read(oldContentBuffer);
/* 1175 */         while (count > 0 && count < underlyingLength) {
/*      */           
/* 1177 */           int more = oldContents.read(oldContentBuffer, count, oldContentBuffer.length - count);
/* 1178 */           if (more <= 0) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1184 */           count += more;
/*      */         } 
/*      */         
/* 1187 */         if (count == underlyingLength && oldContents.read() == -1) {
/*      */           
/* 1189 */           for (int i = 0; i < underlyingLength; i++) {
/*      */             
/* 1191 */             if (oldContentBuffer[i] != underlyingNewContentBuffer[i]) {
/*      */               
/* 1193 */               equal = false;
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1200 */           equal = false;
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 1205 */         oldContents.close();
/*      */       } 
/*      */     }
/*      */     
/* 1209 */     if (!equal) {
/*      */       Long timeStamp;
/* 1211 */       Map<?, ?> response = (options == null) ? null : (Map<?, ?>)options.get("RESPONSE");
/* 1212 */       if (response == null)
/*      */       {
/* 1214 */         response = new HashMap<Object, Object>();
/*      */       }
/* 1216 */       OutputStream newContents = uriConverter.createOutputStream(getURI(), new ExtensibleURIConverterImpl.OptionsMap("RESPONSE", response, options));
/*      */       
/*      */       try {
/* 1219 */         newContents.write(newContentBuffer, 0, length);
/*      */       }
/*      */       finally {
/*      */         
/* 1223 */         newContents.close();
/* 1224 */         Long long_ = (Long)response.get("TIME_STAMP");
/* 1225 */         if (long_ != null)
/*      */         {
/* 1227 */           setTimeStamp(long_.longValue());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void load(Map<?, ?> options) throws IOException {
/* 1238 */     if (!this.isLoaded) {
/*      */       Long timeStamp;
/* 1240 */       URIConverter uriConverter = getURIConverter();
/* 1241 */       Map<?, ?> response = (options == null) ? null : (Map<?, ?>)options.get("RESPONSE");
/* 1242 */       if (response == null)
/*      */       {
/* 1244 */         response = new HashMap<Object, Object>();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1250 */       InputStream inputStream = null;
/*      */       
/*      */       try {
/* 1253 */         inputStream = 
/* 1254 */           uriConverter.createInputStream(
/* 1255 */             getURI(), 
/* 1256 */             new ExtensibleURIConverterImpl.OptionsMap("RESPONSE", response, options));
/*      */       }
/* 1258 */       catch (IOException exception) {
/*      */         
/* 1260 */         Notification notification = setLoaded(true);
/* 1261 */         this.isLoading = true;
/* 1262 */         if (this.errors != null)
/*      */         {
/* 1264 */           this.errors.clear();
/*      */         }
/* 1266 */         if (this.warnings != null)
/*      */         {
/* 1268 */           this.warnings.clear();
/*      */         }
/* 1270 */         this.isLoading = false;
/* 1271 */         if (notification != null)
/*      */         {
/* 1273 */           eNotify(notification);
/*      */         }
/* 1275 */         setModified(false);
/*      */         
/* 1277 */         throw exception;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1282 */         load(inputStream, options);
/*      */       }
/*      */       finally {
/*      */         
/* 1286 */         inputStream.close();
/* 1287 */         Long long_ = (Long)response.get("TIME_STAMP");
/* 1288 */         if (long_ != null)
/*      */         {
/* 1290 */           setTimeStamp(long_.longValue());
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected ZipEntry newContentZipEntry() {
/* 1305 */     return new ZipEntry("ResourceContents");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream getUnderlyingInputStream(InputStream inputStream, Map<?, ?> options) throws IOException {
/* 1313 */     if (useZip() || (options != null && Boolean.TRUE.equals(options.get("ZIP")))) {
/*      */       
/* 1315 */       ZipInputStream zipInputStream = new ZipInputStream(inputStream);
/* 1316 */       while (zipInputStream.available() != 0) {
/*      */         
/* 1318 */         ZipEntry zipEntry = zipInputStream.getNextEntry();
/* 1319 */         if (isContentZipEntry(zipEntry))
/*      */         {
/* 1321 */           return zipInputStream;
/*      */         }
/*      */       } 
/*      */     } 
/* 1325 */     return inputStream;
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
/*      */   public final void save(OutputStream outputStream, Map<?, ?> options) throws IOException {
/* 1341 */     if (this.errors != null)
/*      */     {
/* 1343 */       this.errors.clear();
/*      */     }
/*      */     
/* 1346 */     if (this.warnings != null)
/*      */     {
/* 1348 */       this.warnings.clear();
/*      */     }
/*      */     
/* 1351 */     options = mergeMaps(options, this.defaultSaveOptions);
/* 1352 */     ZipOutputStream zipOutputStream = null;
/* 1353 */     if (useZip() || (options != null && Boolean.TRUE.equals(options.get("ZIP")))) {
/*      */       
/* 1355 */       zipOutputStream = 
/* 1356 */         new ZipOutputStream(outputStream)
/*      */         {
/*      */           
/*      */           public void finish() throws IOException
/*      */           {
/* 1361 */             super.finish();
/* 1362 */             this.def.end();
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void flush() {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void close() throws IOException {
/*      */             try {
/* 1376 */               super.flush();
/*      */             }
/* 1378 */             catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */             
/* 1382 */             super.close();
/*      */           }
/*      */         };
/* 1385 */       zipOutputStream.putNextEntry(newContentZipEntry());
/* 1386 */       outputStream = zipOutputStream;
/*      */     } 
/*      */     
/* 1389 */     URIConverter.Cipher cipher = (options != null) ? 
/* 1390 */       (URIConverter.Cipher)options.get("CIPHER") : 
/* 1391 */       null;
/*      */     
/* 1393 */     if (cipher != null) {
/*      */       
/*      */       try {
/*      */         
/* 1397 */         outputStream = cipher.encrypt(outputStream);
/*      */       }
/* 1399 */       catch (Exception e) {
/*      */         
/* 1401 */         throw new Resource.IOWrappedException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1406 */     doSave(outputStream, options);
/*      */     
/* 1408 */     if (cipher != null) {
/*      */       
/*      */       try {
/*      */         
/* 1412 */         cipher.finish(outputStream);
/*      */       }
/* 1414 */       catch (Exception e) {
/*      */         
/* 1416 */         throw new Resource.IOWrappedException(e);
/*      */       } 
/*      */     }
/*      */     
/* 1420 */     setModified(false);
/*      */     
/* 1422 */     if (zipOutputStream != null)
/*      */     {
/* 1424 */       zipOutputStream.finish();
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
/*      */   protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
/* 1438 */     throw new UnsupportedOperationException();
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
/*      */   protected boolean isContentZipEntry(ZipEntry zipEntry) {
/* 1451 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void load(InputStream inputStream, Map<?, ?> options) throws IOException {
/* 1459 */     if (!this.isLoaded) {
/*      */       
/* 1461 */       Notification notification = setLoaded(true);
/* 1462 */       this.isLoading = true;
/*      */       
/* 1464 */       if (this.errors != null)
/*      */       {
/* 1466 */         this.errors.clear();
/*      */       }
/*      */       
/* 1469 */       if (this.warnings != null)
/*      */       {
/* 1471 */         this.warnings.clear();
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1476 */         options = mergeMaps(options, this.defaultLoadOptions);
/* 1477 */         inputStream = getUnderlyingInputStream(inputStream, options);
/* 1478 */         URIConverter.Cipher cipher = (options != null) ? 
/* 1479 */           (URIConverter.Cipher)options.get("CIPHER") : 
/* 1480 */           null;
/*      */         
/* 1482 */         if (cipher != null) {
/*      */           
/*      */           try {
/*      */             
/* 1486 */             inputStream = cipher.decrypt(inputStream);
/*      */           }
/* 1488 */           catch (Exception e) {
/*      */             
/* 1490 */             throw new Resource.IOWrappedException(e);
/*      */           } 
/*      */         }
/*      */         
/* 1494 */         doLoad(inputStream, options);
/*      */         
/* 1496 */         if (cipher != null) {
/*      */           try
/*      */           {
/*      */             
/* 1500 */             cipher.finish(inputStream);
/*      */           }
/* 1502 */           catch (Exception e)
/*      */           {
/* 1504 */             throw new Resource.IOWrappedException(e);
/*      */           }
/*      */         
/*      */         }
/*      */       } finally {
/*      */         
/* 1510 */         this.isLoading = false;
/*      */         
/* 1512 */         if (notification != null)
/*      */         {
/* 1514 */           eNotify(notification);
/*      */         }
/*      */         
/* 1517 */         setModified(false);
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
/*      */   protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
/* 1532 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLoaded() {
/* 1540 */     return this.isLoaded;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLoading() {
/* 1548 */     return this.isLoading;
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
/*      */   protected void unloaded(InternalEObject internalEObject) {
/* 1561 */     if (!internalEObject.eIsProxy())
/*      */     {
/* 1563 */       internalEObject.eSetProxyURI(this.uri.appendFragment(getURIFragment((EObject)internalEObject)));
/*      */     }
/* 1565 */     internalEObject.eAdapters().clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Notification setLoaded(boolean isLoaded) {
/* 1576 */     boolean oldIsLoaded = this.isLoaded;
/* 1577 */     this.isLoaded = isLoaded;
/*      */     
/* 1579 */     if (eNotificationRequired())
/*      */     {
/*      */       
/* 1582 */       return (Notification)new NotificationImpl(1, oldIsLoaded, isLoaded)
/*      */         {
/*      */           
/*      */           public Object getNotifier()
/*      */           {
/* 1587 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID(Class<?> expectedClass) {
/* 1593 */             return 4;
/*      */           }
/*      */         };
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1600 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doUnload() {
/* 1611 */     TreeIterator<EObject> treeIterator = getAllProperContents(this.unloadingContents);
/*      */ 
/*      */ 
/*      */     
/* 1615 */     if (!getContents().isEmpty())
/*      */     {
/* 1617 */       getContents().clear();
/*      */     }
/* 1619 */     getErrors().clear();
/* 1620 */     getWarnings().clear();
/*      */     
/* 1622 */     while (treeIterator.hasNext())
/*      */     {
/* 1624 */       unloaded((InternalEObject)treeIterator.next());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void unload() {
/* 1633 */     if (this.isLoaded) {
/*      */       
/* 1635 */       this.unloadingContents = (List<EObject>)new BasicEList.FastCompare((Collection)getContents());
/* 1636 */       Notification notification = setLoaded(false);
/*      */       
/*      */       try {
/* 1639 */         doUnload();
/*      */       }
/*      */       finally {
/*      */         
/* 1643 */         this.unloadingContents = null;
/* 1644 */         if (notification != null)
/*      */         {
/* 1646 */           eNotify(notification);
/*      */         }
/* 1648 */         setTimeStamp(-1L);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void delete(Map<?, ?> options) throws IOException {
/* 1655 */     getURIConverter().delete(getURI(), mergeMaps(options, this.defaultDeleteOptions));
/* 1656 */     unload();
/* 1657 */     ResourceSet resourceSet = getResourceSet();
/* 1658 */     if (resourceSet != null)
/*      */     {
/* 1660 */       resourceSet.getResources().remove(this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ModificationTrackingAdapter
/*      */     extends AdapterImpl
/*      */   {
/*      */     public void notifyChanged(Notification notification) {
/* 1672 */       if (!notification.isTouch())
/*      */       {
/* 1674 */         ResourceImpl.this.setModified(true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTrackingModification() {
/* 1684 */     return (this.modificationTrackingAdapter != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTrackingModification(boolean isTrackingModification) {
/* 1692 */     boolean oldIsTrackingModification = (this.modificationTrackingAdapter != null);
/*      */     
/* 1694 */     if (oldIsTrackingModification != isTrackingModification)
/*      */     {
/* 1696 */       if (isTrackingModification) {
/*      */         
/* 1698 */         this.modificationTrackingAdapter = createModificationTrackingAdapter();
/*      */         
/* 1700 */         for (TreeIterator<EObject> i = getAllProperContents((List<EObject>)getContents()); i.hasNext(); )
/*      */         {
/* 1702 */           EObject eObject = (EObject)i.next();
/* 1703 */           eObject.eAdapters().add(this.modificationTrackingAdapter);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1708 */         Adapter oldModificationTrackingAdapter = this.modificationTrackingAdapter;
/* 1709 */         this.modificationTrackingAdapter = null;
/*      */         
/* 1711 */         for (TreeIterator<EObject> i = getAllProperContents((List<EObject>)getContents()); i.hasNext(); ) {
/*      */           
/* 1713 */           EObject eObject = (EObject)i.next();
/* 1714 */           eObject.eAdapters().remove(oldModificationTrackingAdapter);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1719 */     if (eNotificationRequired()) {
/*      */       
/* 1721 */       NotificationImpl notificationImpl = 
/* 1722 */         new NotificationImpl(1, oldIsTrackingModification, isTrackingModification)
/*      */         {
/*      */           
/*      */           public Object getNotifier()
/*      */           {
/* 1727 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID(Class<?> expectedClass) {
/* 1733 */             return 5;
/*      */           }
/*      */         };
/* 1736 */       eNotify((Notification)notificationImpl);
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
/*      */   protected Adapter createModificationTrackingAdapter() {
/* 1750 */     return (Adapter)new ModificationTrackingAdapter();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isModified() {
/* 1758 */     return this.isModified;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModified(boolean isModified) {
/* 1766 */     boolean oldIsModified = this.isModified;
/* 1767 */     this.isModified = isModified;
/* 1768 */     if (eNotificationRequired()) {
/*      */       
/* 1770 */       NotificationImpl notificationImpl = 
/* 1771 */         new NotificationImpl(1, oldIsModified, isModified)
/*      */         {
/*      */           
/*      */           public Object getNotifier()
/*      */           {
/* 1776 */             return ResourceImpl.this;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID(Class<?> expectedClass) {
/* 1782 */             return 3;
/*      */           }
/*      */         };
/* 1785 */       eNotify((Notification)notificationImpl);
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
/*      */   public String toKeyString() {
/* 1797 */     StringBuffer result = new StringBuffer("Key type: ");
/* 1798 */     result.append(getClass().toString());
/* 1799 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1805 */     return 
/* 1806 */       String.valueOf(getClass().getName()) + '@' + Integer.toHexString(hashCode()) + 
/* 1807 */       " uri='" + this.uri + "'";
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */