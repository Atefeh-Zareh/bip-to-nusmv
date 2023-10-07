/*      */ package org.eclipse.emf.ecore.impl;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.BasicEMap;
/*      */ import org.eclipse.emf.common.util.ECollections;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
/*      */ import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
/*      */ import org.eclipse.emf.ecore.util.EcoreEList;
/*      */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.FeatureMapUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EStoreEObjectImpl
/*      */   extends EObjectImpl
/*      */   implements EStructuralFeature.Internal.DynamicValueHolder
/*      */ {
/*      */   protected static class EStoreEPropertiesHolderImpl
/*      */     implements BasicEObjectImpl.EPropertiesHolder
/*      */   {
/*      */     protected EClass eClass;
/*      */     protected URI eProxyURI;
/*      */     protected Resource.Internal eResource;
/*      */     protected EList<EObject> eContents;
/*      */     protected EList<EObject> eCrossReferences;
/*      */     
/*      */     public EClass getEClass() {
/*   66 */       return this.eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEClass(EClass eClass) {
/*   71 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public URI getEProxyURI() {
/*   76 */       return this.eProxyURI;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEProxyURI(URI eProxyURI) {
/*   81 */       this.eProxyURI = eProxyURI;
/*      */     }
/*      */ 
/*      */     
/*      */     public Resource.Internal getEResource() {
/*   86 */       return this.eResource;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEResource(Resource.Internal eResource) {
/*   91 */       this.eResource = eResource;
/*      */     }
/*      */ 
/*      */     
/*      */     public EList<EObject> getEContents() {
/*   96 */       return this.eContents;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEContents(EList<EObject> eContents) {
/*  101 */       this.eContents = eContents;
/*      */     }
/*      */ 
/*      */     
/*      */     public EList<EObject> getECrossReferences() {
/*  106 */       return this.eCrossReferences;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setECrossReferences(EList<EObject> eCrossReferences) {
/*  111 */       this.eCrossReferences = eCrossReferences;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasSettings() {
/*  116 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void allocateSettings(int maximumDynamicFeatureID) {
/*  121 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object dynamicGet(int dynamicFeatureID) {
/*  126 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicSet(int dynamicFeatureID, Object value) {
/*  131 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicUnset(int dynamicFeatureID) {
/*  136 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BasicEStoreEList<E>
/*      */     extends DelegatingEcoreEList.Dynamic<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */     
/*      */     public BasicEStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature) {
/*  150 */       super(owner, eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/*      */     protected InternalEObject.EStore eStore() {
/*  155 */       return this.owner.eStore();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isSet() {
/*  161 */       return eStore().isSet(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void unset() {
/*  167 */       if (isUnsettable() && isNotificationRequired()) {
/*      */         
/*  169 */         boolean oldIsSet = isSet();
/*  170 */         eStore().unset(this.owner, this.eStructuralFeature);
/*  171 */         dispatchNotification((Notification)createNotification(2, oldIsSet, false));
/*      */       }
/*      */       else {
/*      */         
/*  175 */         eStore().unset(this.owner, this.eStructuralFeature);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<E> delegateList() {
/*  182 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/*  188 */       return this.eStructuralFeature;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateAdd(int index, Object object) {
/*  194 */       eStore().add(this.owner, this.eStructuralFeature, index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateAdd(Object object) {
/*  200 */       delegateAdd(delegateSize(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<E> delegateBasicList() {
/*  206 */       int size = delegateSize();
/*  207 */       if (size == 0)
/*      */       {
/*  209 */         return (List<E>)ECollections.emptyEList();
/*      */       }
/*      */ 
/*      */       
/*  213 */       Object[] data = eStore().toArray(this.owner, this.eStructuralFeature);
/*  214 */       return (List<E>)new EcoreEList.UnmodifiableEList(this.owner, this.eStructuralFeature, data.length, data);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateClear() {
/*  221 */       eStore().clear(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateContains(Object object) {
/*  227 */       return eStore().contains(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateContainsAll(Collection<?> collection) {
/*  233 */       for (Object o : collection) {
/*      */         
/*  235 */         if (!delegateContains(o))
/*      */         {
/*  237 */           return false;
/*      */         }
/*      */       } 
/*  240 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E delegateGet(int index) {
/*  247 */       return (E)eStore().get(this.owner, this.eStructuralFeature, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateHashCode() {
/*  253 */       return eStore().hashCode(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateIndexOf(Object object) {
/*  259 */       return eStore().indexOf(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateIsEmpty() {
/*  265 */       return eStore().isEmpty(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Iterator<E> delegateIterator() {
/*  271 */       return iterator();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateLastIndexOf(Object object) {
/*  277 */       return eStore().lastIndexOf(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected ListIterator<E> delegateListIterator() {
/*  283 */       return listIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E delegateRemove(int index) {
/*  290 */       return (E)eStore().remove(this.owner, this.eStructuralFeature, index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E delegateSet(int index, E object) {
/*  297 */       return (E)eStore().set(this.owner, this.eStructuralFeature, index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateSize() {
/*  303 */       return eStore().size(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object[] delegateToArray() {
/*  309 */       return eStore().toArray(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected <T> T[] delegateToArray(Object[] array) {
/*  315 */       return (T[])eStore().toArray(this.owner, this.eStructuralFeature, array);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected E delegateMove(int targetIndex, int sourceIndex) {
/*  322 */       return (E)eStore().move(this.owner, this.eStructuralFeature, targetIndex, sourceIndex);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateEquals(Object object) {
/*  328 */       if (object == this)
/*      */       {
/*  330 */         return true;
/*      */       }
/*      */       
/*  333 */       if (!(object instanceof List))
/*      */       {
/*  335 */         return false;
/*      */       }
/*      */       
/*  338 */       List<?> list = (List)object;
/*  339 */       if (list.size() != delegateSize())
/*      */       {
/*  341 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  345 */       for (ListIterator<?> i = list.listIterator(); i.hasNext(); ) {
/*      */         
/*  347 */         Object element = i.next();
/*  348 */         if ((element == null) ? (get(i.previousIndex()) != null) : !element.equals(get(i.previousIndex())))
/*      */         {
/*  350 */           return false;
/*      */         }
/*      */       } 
/*      */       
/*  354 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected String delegateToString() {
/*  360 */       StringBuffer stringBuffer = new StringBuffer();
/*  361 */       stringBuffer.append("[");
/*  362 */       for (int i = 0, size = size(); i < size; ) {
/*      */         
/*  364 */         Object value = delegateGet(i);
/*  365 */         stringBuffer.append(String.valueOf(value));
/*  366 */         if (++i < size)
/*      */         {
/*  368 */           stringBuffer.append(", ");
/*      */         }
/*      */       } 
/*  371 */       stringBuffer.append("]");
/*  372 */       return stringBuffer.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EStoreEList<E>
/*      */     extends BasicEStoreEList<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     protected InternalEObject.EStore store;
/*      */ 
/*      */     
/*      */     public EStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature, InternalEObject.EStore store) {
/*  387 */       super(owner, eStructuralFeature);
/*  388 */       this.store = store;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected final InternalEObject.EStore eStore() {
/*  394 */       return this.store;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BasicEStoreFeatureMap
/*      */     extends DelegatingFeatureMap
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */     
/*      */     public BasicEStoreFeatureMap(InternalEObject owner, EStructuralFeature eStructuralFeature) {
/*  408 */       super(owner, eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/*      */     protected InternalEObject.EStore eStore() {
/*  413 */       return this.owner.eStore();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<FeatureMap.Entry> delegateList() {
/*  419 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/*  425 */       return this.eStructuralFeature;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateAdd(int index, FeatureMap.Entry object) {
/*  431 */       eStore().add(this.owner, this.eStructuralFeature, index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateAdd(FeatureMap.Entry object) {
/*  437 */       delegateAdd(delegateSize(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<FeatureMap.Entry> delegateBasicList() {
/*  443 */       int size = delegateSize();
/*  444 */       if (size == 0)
/*      */       {
/*  446 */         return (List<FeatureMap.Entry>)ECollections.emptyEList();
/*      */       }
/*      */ 
/*      */       
/*  450 */       Object[] data = eStore().toArray(this.owner, this.eStructuralFeature);
/*  451 */       return (List<FeatureMap.Entry>)new EcoreEList.UnmodifiableEList(this.owner, this.eStructuralFeature, data.length, data);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void delegateClear() {
/*  458 */       eStore().clear(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateContains(Object object) {
/*  464 */       return eStore().contains(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateContainsAll(Collection<?> collection) {
/*  470 */       for (Object o : collection) {
/*      */         
/*  472 */         if (!delegateContains(o))
/*      */         {
/*  474 */           return false;
/*      */         }
/*      */       } 
/*  477 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FeatureMap.Entry delegateGet(int index) {
/*  483 */       return (FeatureMap.Entry)eStore().get(this.owner, this.eStructuralFeature, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateHashCode() {
/*  489 */       return eStore().hashCode(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateIndexOf(Object object) {
/*  495 */       return eStore().indexOf(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean delegateIsEmpty() {
/*  501 */       return eStore().isEmpty(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Iterator<FeatureMap.Entry> delegateIterator() {
/*  507 */       return iterator();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateLastIndexOf(Object object) {
/*  513 */       return eStore().lastIndexOf(this.owner, this.eStructuralFeature, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected ListIterator<FeatureMap.Entry> delegateListIterator() {
/*  519 */       return listIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FeatureMap.Entry delegateRemove(int index) {
/*  525 */       return (FeatureMap.Entry)eStore().remove(this.owner, this.eStructuralFeature, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FeatureMap.Entry delegateSet(int index, FeatureMap.Entry object) {
/*  531 */       return (FeatureMap.Entry)eStore().set(this.owner, this.eStructuralFeature, index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int delegateSize() {
/*  537 */       return eStore().size(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object[] delegateToArray() {
/*  543 */       return eStore().toArray(this.owner, this.eStructuralFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected <T> T[] delegateToArray(Object[] array) {
/*  549 */       return (T[])eStore().toArray(this.owner, this.eStructuralFeature, array);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FeatureMap.Entry delegateMove(int targetIndex, int sourceIndex) {
/*  555 */       return (FeatureMap.Entry)eStore().move(this.owner, this.eStructuralFeature, targetIndex, sourceIndex);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected String delegateToString() {
/*  561 */       StringBuffer stringBuffer = new StringBuffer();
/*  562 */       stringBuffer.append("[");
/*  563 */       for (int i = 0, size = size(); i < size; ) {
/*      */         
/*  565 */         Object value = delegateGet(i);
/*  566 */         stringBuffer.append(String.valueOf(value));
/*  567 */         if (++i < size)
/*      */         {
/*  569 */           stringBuffer.append(", ");
/*      */         }
/*      */       } 
/*  572 */       stringBuffer.append("]");
/*  573 */       return stringBuffer.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EStoreFeatureMap
/*      */     extends BasicEStoreFeatureMap
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     protected final InternalEObject.EStore store;
/*      */ 
/*      */     
/*      */     public EStoreFeatureMap(InternalEObject owner, EStructuralFeature eStructuralFeature, InternalEObject.EStore store) {
/*  588 */       super(owner, eStructuralFeature);
/*  589 */       this.store = store;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected InternalEObject.EStore eStore() {
/*  595 */       return this.store;
/*      */     }
/*      */   }
/*      */   
/*  599 */   protected static final Object[] ENO_SETTINGS = new Object[0];
/*  600 */   protected static final InternalEObject EUNINITIALIZED_CONTAINER = new EObjectImpl();
/*      */ 
/*      */   
/*      */   protected Object[] eSettings;
/*      */ 
/*      */   
/*      */   protected InternalEObject.EStore eStore;
/*      */ 
/*      */ 
/*      */   
/*      */   public EStoreEObjectImpl() {
/*  611 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EStoreEObjectImpl(InternalEObject.EStore eStore) {
/*  620 */     eSetStore(eStore);
/*  621 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EStoreEObjectImpl(EClass eClass) {
/*  630 */     eSetClass(eClass);
/*  631 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EStoreEObjectImpl(EClass eClass, InternalEObject.EStore eStore) {
/*  640 */     eSetClass(eClass);
/*  641 */     eSetStore(eStore);
/*  642 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean eIsCaching() {
/*  647 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object dynamicGet(int dynamicFeatureID) {
/*  652 */     Object<?> result = (Object<?>)this.eSettings[dynamicFeatureID];
/*  653 */     if (result == null) {
/*      */       
/*  655 */       EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
/*  656 */       if (!eStructuralFeature.isTransient())
/*      */       {
/*  658 */         if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
/*      */           
/*  660 */           this.eSettings[dynamicFeatureID] = result = (Object<?>)createFeatureMap(eStructuralFeature);
/*      */         }
/*  662 */         else if (eStructuralFeature.isMany()) {
/*      */           
/*  664 */           this.eSettings[dynamicFeatureID] = result = (Object<?>)createList(eStructuralFeature);
/*      */         }
/*      */         else {
/*      */           
/*  668 */           result = (Object<?>)eStore().get(this, eStructuralFeature, -1);
/*  669 */           if (eIsCaching())
/*      */           {
/*  671 */             this.eSettings[dynamicFeatureID] = result;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*  676 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dynamicSet(int dynamicFeatureID, Object value) {
/*  681 */     EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
/*  682 */     if (eStructuralFeature.isTransient()) {
/*      */       
/*  684 */       this.eSettings[dynamicFeatureID] = value;
/*      */     }
/*      */     else {
/*      */       
/*  688 */       eStore().set(this, eStructuralFeature, -1, value);
/*  689 */       if (eIsCaching())
/*      */       {
/*  691 */         this.eSettings[dynamicFeatureID] = value;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void dynamicUnset(int dynamicFeatureID) {
/*  698 */     EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
/*  699 */     if (eStructuralFeature.isTransient()) {
/*      */       
/*  701 */       this.eSettings[dynamicFeatureID] = null;
/*      */     }
/*      */     else {
/*      */       
/*  705 */       eStore().unset(this, eStructuralFeature);
/*  706 */       this.eSettings[dynamicFeatureID] = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean eDynamicIsSet(int dynamicFeatureID, EStructuralFeature eFeature) {
/*  713 */     return 
/*  714 */       (dynamicFeatureID < 0) ? 
/*  715 */       eOpenIsSet(eFeature) : (
/*  716 */       eFeature.isTransient() ? 
/*  717 */       eSettingDelegate(eFeature).dynamicIsSet(this, eSettings(), dynamicFeatureID) : 
/*  718 */       eStore().isSet(this, eFeature));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EList<?> createList(EStructuralFeature eStructuralFeature) {
/*      */     class EStoreEcoreEMap
/*      */       extends EcoreEMap<Object, Object>
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public EStoreEcoreEMap(EClassifier param1EClassifier, EStructuralFeature param1EStructuralFeature) {
/*  735 */         super((EClass)param1EClassifier, BasicEMap.Entry.class, null);
/*  736 */         this.delegateEList = 
/*  737 */           (EList)new EStoreEObjectImpl.BasicEStoreEList<BasicEMap.Entry<Object, Object>>(EStoreEObjectImpl.this, param1EStructuralFeature)
/*      */           {
/*      */             private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */             
/*      */             protected void didAdd(int index, BasicEMap.Entry<Object, Object> newObject) {
/*  744 */               EStoreEObjectImpl.EStoreEcoreEMap.access$0(EStoreEObjectImpl.EStoreEcoreEMap.this, newObject);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             protected void didSet(int index, BasicEMap.Entry<Object, Object> newObject, BasicEMap.Entry<Object, Object> oldObject) {
/*  750 */               didRemove(index, oldObject);
/*  751 */               didAdd(index, newObject);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             protected void didRemove(int index, BasicEMap.Entry<Object, Object> oldObject) {
/*  757 */               EStoreEObjectImpl.EStoreEcoreEMap.access$1(EStoreEObjectImpl.EStoreEcoreEMap.this, oldObject);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             protected void didClear(int size, Object[] oldObjects) {
/*  763 */               EStoreEObjectImpl.EStoreEcoreEMap.access$2(EStoreEObjectImpl.EStoreEcoreEMap.this);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             protected void didMove(int index, BasicEMap.Entry<Object, Object> movedObject, int oldIndex) {
/*  769 */               EStoreEObjectImpl.EStoreEcoreEMap.access$3(EStoreEObjectImpl.EStoreEcoreEMap.this, movedObject);
/*      */             }
/*      */           };
/*  772 */         this.size = this.delegateEList.size(); } };
/*      */     EClassifier eType = eStructuralFeature.getEType();
/*      */     if (eType.getInstanceClassName() == "java.util.Map$Entry") {
/*  775 */       return (EList<?>)new EStoreEcoreEMap(eType, eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/*  779 */     return (EList<?>)new BasicEStoreEList(this, eStructuralFeature);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected FeatureMap createFeatureMap(EStructuralFeature eStructuralFeature) {
/*  785 */     return (FeatureMap)new EStoreFeatureMap(this, eStructuralFeature, eStore());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public InternalEObject eInternalContainer() {
/*  791 */     if (this.eContainer == EUNINITIALIZED_CONTAINER)
/*      */     {
/*  793 */       eInitializeContainer();
/*      */     }
/*      */     
/*  796 */     return this.eContainer;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int eContainerFeatureID() {
/*  802 */     if (this.eContainer == EUNINITIALIZED_CONTAINER)
/*      */     {
/*  804 */       eInitializeContainer();
/*      */     }
/*      */     
/*  807 */     return this.eContainerFeatureID;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void eInitializeContainer() {
/*  812 */     this.eContainer = eStore().getContainer(this);
/*  813 */     if (this.eContainer != null) {
/*      */       
/*  815 */       EStructuralFeature eContainingFeature = eStore().getContainingFeature(this);
/*  816 */       if (eContainingFeature instanceof EReference) {
/*      */         
/*  818 */         EReference eContainingReference = (EReference)eContainingFeature;
/*  819 */         EReference eOpposite = eContainingReference.getEOpposite();
/*  820 */         if (eOpposite != null) {
/*      */           
/*  822 */           this.eContainerFeatureID = eClass().getFeatureID((EStructuralFeature)eOpposite);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*  827 */       this.eContainerFeatureID = -1 - this.eContainer.eClass().getFeatureID(eContainingFeature);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public InternalEObject.EStore eStore() {
/*  834 */     return this.eStore;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void eSetStore(InternalEObject.EStore store) {
/*  840 */     this.eStore = store;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int eStaticFeatureCount() {
/*  846 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature) {
/*  852 */     return eClass().getFeatureID(eStructuralFeature);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected BasicEObjectImpl.EPropertiesHolder eProperties() {
/*  858 */     if (this.eProperties == null)
/*      */     {
/*  860 */       this.eProperties = new EStoreEPropertiesHolderImpl();
/*      */     }
/*  862 */     return this.eProperties;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean eHasSettings() {
/*  868 */     return (this.eSettings != null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EStructuralFeature.Internal.DynamicValueHolder eSettings() {
/*  874 */     if (this.eSettings == null) {
/*      */       
/*  876 */       int size = eClass().getFeatureCount() - eStaticFeatureCount();
/*  877 */       this.eSettings = (size == 0) ? ENO_SETTINGS : new Object[size];
/*      */     } 
/*      */     
/*  880 */     return this;
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
/*      */   public static class EStoreImpl
/*      */     implements InternalEObject.EStore
/*      */   {
/*  901 */     protected Map<Entry, Object> map = new HashMap<Entry, Object>();
/*      */ 
/*      */     
/*      */     public static class Entry
/*      */     {
/*      */       protected EObject eObject;
/*      */       protected EStructuralFeature eStructuralFeature;
/*      */       
/*      */       public Entry(InternalEObject eObject, EStructuralFeature eStructuralFeature) {
/*  910 */         this.eObject = (EObject)eObject;
/*  911 */         this.eStructuralFeature = eStructuralFeature;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean equals(Object that) {
/*  917 */         if (that instanceof Entry) {
/*      */           
/*  919 */           Entry entry = (Entry)that;
/*  920 */           return (this.eObject == entry.eObject && this.eStructuralFeature == entry.eStructuralFeature);
/*      */         } 
/*      */ 
/*      */         
/*  924 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int hashCode() {
/*  931 */         return this.eObject.hashCode() ^ this.eStructuralFeature.hashCode();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected EList<Object> getList(Entry entry) {
/*      */       BasicEList basicEList;
/*  938 */       EList<Object> result = (EList<Object>)this.map.get(entry);
/*  939 */       if (result == null) {
/*      */         
/*  941 */         basicEList = new BasicEList();
/*  942 */         this.map.put(entry, basicEList);
/*      */       } 
/*  944 */       return (EList<Object>)basicEList;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(InternalEObject eObject, EStructuralFeature feature, int index) {
/*  949 */       Entry entry = new Entry(eObject, feature);
/*  950 */       if (index == -1)
/*      */       {
/*  952 */         return this.map.get(entry);
/*      */       }
/*      */ 
/*      */       
/*  956 */       return getList(entry).get(index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Object set(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
/*  962 */       Entry entry = new Entry(eObject, feature);
/*  963 */       if (index == -1)
/*      */       {
/*  965 */         return this.map.put(entry, value);
/*      */       }
/*      */ 
/*      */       
/*  969 */       EList<Object> eList = getList(entry);
/*  970 */       return eList.set(index, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
/*  976 */       Entry entry = new Entry(eObject, feature);
/*  977 */       getList(entry).add(index, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object remove(InternalEObject eObject, EStructuralFeature feature, int index) {
/*  982 */       Entry entry = new Entry(eObject, feature);
/*  983 */       return getList(entry).remove(index);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object move(InternalEObject eObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
/*  988 */       Entry entry = new Entry(eObject, feature);
/*  989 */       return getList(entry).move(targetIndex, sourceIndex);
/*      */     }
/*      */ 
/*      */     
/*      */     public void clear(InternalEObject eObject, EStructuralFeature feature) {
/*  994 */       Entry entry = new Entry(eObject, feature);
/*  995 */       this.map.remove(entry);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isSet(InternalEObject eObject, EStructuralFeature feature) {
/* 1001 */       Entry entry = new Entry(eObject, feature);
/* 1002 */       return this.map.containsKey(entry);
/*      */     }
/*      */ 
/*      */     
/*      */     public void unset(InternalEObject eObject, EStructuralFeature feature) {
/* 1007 */       Entry entry = new Entry(eObject, feature);
/* 1008 */       this.map.remove(entry);
/*      */     }
/*      */ 
/*      */     
/*      */     public int size(InternalEObject eObject, EStructuralFeature feature) {
/* 1013 */       Entry entry = new Entry(eObject, feature);
/* 1014 */       return getList(entry).size();
/*      */     }
/*      */ 
/*      */     
/*      */     public int indexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
/* 1019 */       Entry entry = new Entry(eObject, feature);
/* 1020 */       return getList(entry).indexOf(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public int lastIndexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
/* 1025 */       Entry entry = new Entry(eObject, feature);
/* 1026 */       return getList(entry).lastIndexOf(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] toArray(InternalEObject eObject, EStructuralFeature feature) {
/* 1031 */       Entry entry = new Entry(eObject, feature);
/* 1032 */       return getList(entry).toArray();
/*      */     }
/*      */ 
/*      */     
/*      */     public <T> T[] toArray(InternalEObject eObject, EStructuralFeature feature, Object[] array) {
/* 1037 */       Entry entry = new Entry(eObject, feature);
/* 1038 */       return (T[])getList(entry).toArray(array);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isEmpty(InternalEObject eObject, EStructuralFeature feature) {
/* 1043 */       Entry entry = new Entry(eObject, feature);
/* 1044 */       return getList(entry).isEmpty();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean contains(InternalEObject eObject, EStructuralFeature feature, Object value) {
/* 1049 */       Entry entry = new Entry(eObject, feature);
/* 1050 */       return getList(entry).contains(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode(InternalEObject eObject, EStructuralFeature feature) {
/* 1055 */       Entry entry = new Entry(eObject, feature);
/* 1056 */       return getList(entry).hashCode();
/*      */     }
/*      */ 
/*      */     
/*      */     public InternalEObject getContainer(InternalEObject eObject) {
/* 1061 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EStructuralFeature getContainingFeature(InternalEObject eObject) {
/* 1071 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EObject create(EClass eClass) {
/* 1078 */       InternalEObject result = new EStoreEObjectImpl(eClass, this);
/* 1079 */       return (EObject)result;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EStoreEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */