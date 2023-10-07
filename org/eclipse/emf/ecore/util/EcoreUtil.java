/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.security.SecureRandom;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import org.eclipse.emf.common.notify.Adapter;
/*      */ import org.eclipse.emf.common.notify.AdapterFactory;
/*      */ import org.eclipse.emf.common.notify.Notifier;
/*      */ import org.eclipse.emf.common.util.AbstractTreeIterator;
/*      */ import org.eclipse.emf.common.util.BasicDiagnostic;
/*      */ import org.eclipse.emf.common.util.Diagnostic;
/*      */ import org.eclipse.emf.common.util.ECollections;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.TreeIterator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.EAnnotation;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EGenericType;
/*      */ import org.eclipse.emf.ecore.EModelElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EParameter;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.ETypeParameter;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EcoreUtil
/*      */ {
/*      */   protected static final String GEN_MODEL_PACKAGE_NS_URI = "http://www.eclipse.org/emf/2002/GenModel";
/*      */   public static final int GET = 0;
/*      */   public static final int SET = 1;
/*      */   public static final int IS_SET = 2;
/*      */   public static final int UNSET = 3;
/*      */   
/*      */   public static Adapter getExistingAdapter(Notifier notifier, Object type) {
/*   85 */     return getAdapter((List<Adapter>)notifier.eAdapters(), type);
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
/*      */   public static Adapter getRegisteredAdapter(EObject eObject, Object type) {
/*   97 */     Adapter result = getExistingAdapter((Notifier)eObject, type);
/*   98 */     if (result == null) {
/*      */       
/*  100 */       Resource resource = eObject.eResource();
/*  101 */       if (resource != null) {
/*      */         
/*  103 */         ResourceSet resourceSet = resource.getResourceSet();
/*  104 */         if (resourceSet != null) {
/*      */           
/*  106 */           AdapterFactory factory = getAdapterFactory((List<AdapterFactory>)resourceSet.getAdapterFactories(), type);
/*  107 */           if (factory != null)
/*      */           {
/*  109 */             result = factory.adaptNew((Notifier)eObject, type);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  114 */     return result;
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
/*      */   public static Adapter getRegisteredAdapter(Resource resource, Object type) {
/*  126 */     Adapter result = getExistingAdapter((Notifier)resource, type);
/*  127 */     if (result == null) {
/*      */       
/*  129 */       ResourceSet resourceSet = resource.getResourceSet();
/*  130 */       if (resourceSet != null) {
/*      */         
/*  132 */         AdapterFactory factory = getAdapterFactory((List<AdapterFactory>)resourceSet.getAdapterFactories(), type);
/*  133 */         if (factory != null)
/*      */         {
/*  135 */           result = factory.adaptNew((Notifier)resource, type);
/*      */         }
/*      */       } 
/*      */     } 
/*  139 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Adapter getAdapter(List<Adapter> adapters, Object type) {
/*  150 */     for (int i = 0, size = adapters.size(); i < size; i++) {
/*      */       
/*  152 */       Adapter adapter = adapters.get(i);
/*  153 */       if (adapter.isAdapterForType(type))
/*      */       {
/*  155 */         return adapter;
/*      */       }
/*      */     } 
/*  158 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AdapterFactory getAdapterFactory(List<AdapterFactory> adapterFactories, Object type) {
/*  169 */     for (int i = 0, size = adapterFactories.size(); i < size; i++) {
/*      */       
/*  171 */       AdapterFactory factory = adapterFactories.get(i);
/*  172 */       if (factory.isFactoryForType(type))
/*      */       {
/*  174 */         return factory;
/*      */       }
/*      */     } 
/*  177 */     return null;
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
/*      */   public static EObject resolve(EObject proxy, ResourceSet resourceSet) {
/*  191 */     URI proxyURI = ((InternalEObject)proxy).eProxyURI();
/*  192 */     if (proxyURI != null) {
/*      */       
/*      */       try {
/*      */         
/*  196 */         EObject resolvedObject = null;
/*      */         
/*  198 */         if (resourceSet != null) {
/*      */           
/*      */           try
/*      */           {
/*  202 */             resolvedObject = resourceSet.getEObject(proxyURI, true);
/*      */           }
/*  204 */           catch (RuntimeException exception)
/*      */           {
/*  206 */             resolvedObject = resourceSet.getEObject(proxyURI, false);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  211 */           EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(proxyURI.trimFragment().toString());
/*  212 */           if (ePackage != null) {
/*      */             
/*  214 */             Resource resource = ePackage.eResource();
/*  215 */             if (resource != null)
/*      */             {
/*  217 */               resolvedObject = resource.getEObject(proxyURI.fragment().toString());
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  222 */         if (resolvedObject != null && resolvedObject != proxy)
/*      */         {
/*  224 */           return resolve(resolvedObject, resourceSet);
/*      */         }
/*      */       }
/*  227 */       catch (RuntimeException runtimeException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  232 */     return proxy;
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
/*      */   public static EObject resolve(EObject proxy, Resource resourceContext) {
/*  246 */     return resolve(proxy, (resourceContext != null) ? resourceContext.getResourceSet() : null);
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
/*      */   public static EObject resolve(EObject proxy, EObject objectContext) {
/*  261 */     Resource resourceContext = (objectContext != null) ? objectContext.eResource() : null;
/*  262 */     return resolve(proxy, (resourceContext != null) ? resourceContext.getResourceSet() : null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resolveAll(ResourceSet resourceSet) {
/*  271 */     EList<Resource> eList = resourceSet.getResources();
/*  272 */     for (int i = 0; i < eList.size(); i++)
/*      */     {
/*  274 */       resolveAll(eList.get(i));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resolveAll(Resource resource) {
/*  284 */     for (TreeIterator<EObject> treeIterator = resource.getAllContents(); treeIterator.hasNext(); ) {
/*      */       
/*  286 */       EObject eObject = treeIterator.next();
/*  287 */       resolveCrossReferences(eObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resolveAll(EObject eObject) {
/*  297 */     resolveCrossReferences(eObject);
/*  298 */     for (TreeIterator<EObject> treeIterator = eObject.eAllContents(); treeIterator.hasNext(); ) {
/*      */       
/*  300 */       EObject childEObject = treeIterator.next();
/*  301 */       resolveCrossReferences(childEObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void resolveCrossReferences(EObject eObject) {
/*  307 */     for (Iterator<EObject> i = eObject.eCrossReferences().iterator(); i.hasNext(); i.next());
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
/*      */   public static Object getObjectByType(Collection<?> objects, EClassifier type) {
/*  321 */     for (Object object : objects) {
/*      */       
/*  323 */       if (type.isInstance(object))
/*      */       {
/*  325 */         return object;
/*      */       }
/*      */     } 
/*  328 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> Collection<T> getObjectsByType(Collection<?> objects, EClassifier type) {
/*  339 */     Collection<T> result = new ArrayList<T>();
/*  340 */     for (Object object : objects) {
/*      */       
/*  342 */       if (type.isInstance(object)) {
/*      */         
/*  344 */         T t = (T)object;
/*  345 */         result.add(t);
/*      */       } 
/*      */     } 
/*  348 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends EObject> T copy(T eObject) {
/*  359 */     Copier copier = new Copier();
/*  360 */     EObject result = copier.copy((EObject)eObject);
/*  361 */     copier.copyReferences();
/*      */     
/*  363 */     return (T)result;
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
/*      */   public static <T> Collection<T> copyAll(Collection<? extends T> eObjects) {
/*  375 */     Copier copier = new Copier();
/*  376 */     Collection<T> result = copier.copyAll(eObjects);
/*  377 */     copier.copyReferences();
/*  378 */     return result;
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
/*      */   public static class Copier
/*      */     extends HashMap<EObject, EObject>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean resolveProxies = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean useOriginalReferences = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Copier() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Copier(boolean resolveProxies) {
/*  428 */       this.resolveProxies = resolveProxies;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Copier(boolean resolveProxies, boolean useOriginalReferences) {
/*  438 */       this.resolveProxies = resolveProxies;
/*  439 */       this.useOriginalReferences = useOriginalReferences;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T> Collection<T> copyAll(Collection<? extends T> eObjects) {
/*  449 */       Collection<T> result = new ArrayList<T>(eObjects.size());
/*  450 */       for (T object : eObjects) {
/*      */         
/*  452 */         EObject eObject = copy((EObject)object);
/*  453 */         result.add((T)eObject);
/*      */       } 
/*  455 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EObject copy(EObject eObject) {
/*  465 */       if (eObject == null)
/*      */       {
/*  467 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  471 */       EObject copyEObject = createCopy(eObject);
/*  472 */       put(eObject, copyEObject);
/*  473 */       EClass eClass = eObject.eClass();
/*  474 */       for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */         
/*  476 */         EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/*  477 */         if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
/*      */         {
/*  479 */           if (eStructuralFeature instanceof EAttribute) {
/*      */             
/*  481 */             copyAttribute((EAttribute)eStructuralFeature, eObject, copyEObject);
/*      */           }
/*      */           else {
/*      */             
/*  485 */             EReference eReference = (EReference)eStructuralFeature;
/*  486 */             if (eReference.isContainment())
/*      */             {
/*  488 */               copyContainment(eReference, eObject, copyEObject);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  494 */       copyProxyURI(eObject, copyEObject);
/*      */       
/*  496 */       return copyEObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void copyProxyURI(EObject eObject, EObject copyEObject) {
/*  507 */       if (eObject.eIsProxy())
/*      */       {
/*  509 */         ((InternalEObject)copyEObject).eSetProxyURI(((InternalEObject)eObject).eProxyURI());
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
/*      */     protected EObject createCopy(EObject eObject) {
/*  522 */       return EcoreUtil.create(getTarget(eObject.eClass()));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EClass getTarget(EClass eClass) {
/*  533 */       return eClass;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EStructuralFeature getTarget(EStructuralFeature eStructuralFeature) {
/*  544 */       return eStructuralFeature;
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
/*      */     protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
/*  556 */       if (eObject.eIsSet((EStructuralFeature)eReference))
/*      */       {
/*  558 */         if (eReference.isMany()) {
/*      */           
/*  560 */           List<EObject> source = (List<EObject>)eObject.eGet((EStructuralFeature)eReference);
/*  561 */           List<EObject> target = (List<EObject>)copyEObject.eGet(getTarget((EStructuralFeature)eReference));
/*  562 */           if (source.isEmpty())
/*      */           {
/*  564 */             target.clear();
/*      */           }
/*      */           else
/*      */           {
/*  568 */             target.addAll(copyAll(source));
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  573 */           EObject childEObject = (EObject)eObject.eGet((EStructuralFeature)eReference);
/*  574 */           copyEObject.eSet(getTarget((EStructuralFeature)eReference), (childEObject == null) ? null : copy(childEObject));
/*      */         } 
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
/*      */     protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
/*  588 */       if (eObject.eIsSet((EStructuralFeature)eAttribute))
/*      */       {
/*  590 */         if (FeatureMapUtil.isFeatureMap((EStructuralFeature)eAttribute)) {
/*      */           
/*  592 */           FeatureMap featureMap = (FeatureMap)eObject.eGet((EStructuralFeature)eAttribute);
/*  593 */           for (int i = 0, size = featureMap.size(); i < size; i++) {
/*      */             
/*  595 */             EStructuralFeature feature = featureMap.getEStructuralFeature(i);
/*  596 */             if (feature instanceof EReference && ((EReference)feature).isContainment())
/*      */             {
/*  598 */               Object value = featureMap.getValue(i);
/*  599 */               if (value != null)
/*      */               {
/*  601 */                 copy((EObject)value);
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*  606 */         } else if (eAttribute.isMany()) {
/*      */           
/*  608 */           List<?> source = (List)eObject.eGet((EStructuralFeature)eAttribute);
/*  609 */           List<Object> target = (List<Object>)copyEObject.eGet(getTarget((EStructuralFeature)eAttribute));
/*  610 */           if (source.isEmpty())
/*      */           {
/*  612 */             target.clear();
/*      */           }
/*      */           else
/*      */           {
/*  616 */             target.addAll(source);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  621 */           copyEObject.eSet(getTarget((EStructuralFeature)eAttribute), eObject.eGet((EStructuralFeature)eAttribute));
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void copyReferences() {
/*  631 */       for (Map.Entry<EObject, EObject> entry : entrySet()) {
/*      */         
/*  633 */         EObject eObject = entry.getKey();
/*  634 */         EObject copyEObject = entry.getValue();
/*  635 */         EClass eClass = eObject.eClass();
/*  636 */         for (int j = 0, size = eClass.getFeatureCount(); j < size; j++) {
/*      */           
/*  638 */           EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(j);
/*  639 */           if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
/*      */           {
/*  641 */             if (eStructuralFeature instanceof EReference) {
/*      */               
/*  643 */               EReference eReference = (EReference)eStructuralFeature;
/*  644 */               if (!eReference.isContainment() && !eReference.isContainer())
/*      */               {
/*  646 */                 copyReference(eReference, eObject, copyEObject);
/*      */               }
/*      */             }
/*  649 */             else if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
/*      */               
/*  651 */               FeatureMap featureMap = (FeatureMap)eObject.eGet(eStructuralFeature);
/*  652 */               FeatureMap copyFeatureMap = (FeatureMap)copyEObject.eGet(getTarget(eStructuralFeature));
/*  653 */               int copyFeatureMapSize = copyFeatureMap.size();
/*  654 */               for (int k = 0, featureMapSize = featureMap.size(); k < featureMapSize; k++) {
/*      */                 
/*  656 */                 EStructuralFeature feature = featureMap.getEStructuralFeature(k);
/*  657 */                 if (feature instanceof EReference) {
/*      */                   
/*  659 */                   Object referencedEObject = featureMap.getValue(k);
/*  660 */                   Object copyReferencedEObject = get(referencedEObject);
/*  661 */                   if (copyReferencedEObject == null && referencedEObject != null) {
/*      */                     
/*  663 */                     EReference reference = (EReference)feature;
/*  664 */                     if (!this.useOriginalReferences || reference.isContainment() || reference.getEOpposite() != null) {
/*      */                       continue;
/*      */                     }
/*      */                     
/*  668 */                     copyReferencedEObject = referencedEObject;
/*      */                   } 
/*      */ 
/*      */                   
/*  672 */                   if (!copyFeatureMap.add(feature, copyReferencedEObject))
/*      */                   {
/*  674 */                     for (int l = 0; l < copyFeatureMapSize; l++) {
/*      */                       
/*  676 */                       if (copyFeatureMap.getEStructuralFeature(l) == feature && copyFeatureMap.getValue(l) == copyReferencedEObject) {
/*      */                         
/*  678 */                         copyFeatureMap.move(copyFeatureMap.size() - 1, l);
/*  679 */                         copyFeatureMapSize--;
/*      */                         
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*      */                   }
/*      */                   continue;
/*      */                 } 
/*  687 */                 copyFeatureMap.add(featureMap.get(k));
/*      */                 continue;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
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
/*      */     protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
/*  706 */       if (eObject.eIsSet((EStructuralFeature)eReference))
/*      */       {
/*  708 */         if (eReference.isMany()) {
/*      */           
/*  710 */           InternalEList<EObject> source = (InternalEList<EObject>)eObject.eGet((EStructuralFeature)eReference);
/*  711 */           InternalEList<EObject> target = (InternalEList<EObject>)copyEObject.eGet(getTarget((EStructuralFeature)eReference));
/*  712 */           if (source.isEmpty())
/*      */           {
/*  714 */             target.clear();
/*      */           }
/*      */           else
/*      */           {
/*  718 */             boolean isBidirectional = (eReference.getEOpposite() != null);
/*  719 */             int index = 0;
/*  720 */             for (Iterator<EObject> k = this.resolveProxies ? source.iterator() : source.basicIterator(); k.hasNext(); )
/*      */             {
/*  722 */               EObject referencedEObject = k.next();
/*  723 */               EObject copyReferencedEObject = get(referencedEObject);
/*  724 */               if (copyReferencedEObject == null) {
/*      */                 
/*  726 */                 if (this.useOriginalReferences && !isBidirectional) {
/*      */                   
/*  728 */                   target.addUnique(index, referencedEObject);
/*  729 */                   index++;
/*      */                 } 
/*      */                 
/*      */                 continue;
/*      */               } 
/*  734 */               if (isBidirectional) {
/*      */                 
/*  736 */                 int position = target.indexOf(copyReferencedEObject);
/*  737 */                 if (position == -1)
/*      */                 {
/*  739 */                   target.addUnique(index, copyReferencedEObject);
/*      */                 }
/*  741 */                 else if (index != position)
/*      */                 {
/*  743 */                   target.move(index, copyReferencedEObject);
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  748 */                 target.addUnique(index, copyReferencedEObject);
/*      */               } 
/*  750 */               index++;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  757 */           Object referencedEObject = eObject.eGet((EStructuralFeature)eReference, this.resolveProxies);
/*  758 */           if (referencedEObject == null) {
/*      */             
/*  760 */             copyEObject.eSet(getTarget((EStructuralFeature)eReference), null);
/*      */           }
/*      */           else {
/*      */             
/*  764 */             Object copyReferencedEObject = get(referencedEObject);
/*  765 */             if (copyReferencedEObject == null) {
/*      */               
/*  767 */               if (this.useOriginalReferences && eReference.getEOpposite() == null)
/*      */               {
/*  769 */                 copyEObject.eSet(getTarget((EStructuralFeature)eReference), referencedEObject);
/*      */               }
/*      */             }
/*      */             else {
/*      */               
/*  774 */               copyEObject.eSet(getTarget((EStructuralFeature)eReference), copyReferencedEObject);
/*      */             } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EObject getRootContainer(EObject eObject) {
/*  798 */     EObject result = eObject;
/*  799 */     if (eObject != null) {
/*      */       
/*  801 */       int count = 0;
/*  802 */       for (EObject parent = eObject.eContainer(); parent != null; parent = parent.eContainer()) {
/*      */         
/*  804 */         if (++count > 100000)
/*      */         {
/*  806 */           return getRootContainer(parent);
/*      */         }
/*  808 */         result = parent;
/*  809 */         if (parent == eObject)
/*      */         {
/*  811 */           throw new IllegalStateException("There is a cycle in the containment hierarchy of " + eObject);
/*      */         }
/*      */       } 
/*      */     } 
/*  815 */     return result;
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
/*      */   public static EObject getRootContainer(EObject eObject, boolean resolve) {
/*      */     InternalEObject internalEObject;
/*  836 */     if (resolve)
/*      */     {
/*  838 */       return getRootContainer(eObject);
/*      */     }
/*      */ 
/*      */     
/*  842 */     EObject result = eObject;
/*  843 */     if (eObject != null) {
/*      */       
/*  845 */       int count = 0;
/*  846 */       for (InternalEObject parent = ((InternalEObject)eObject).eInternalContainer(); parent != null; parent = parent.eInternalContainer()) {
/*      */         
/*  848 */         if (++count > 100000)
/*      */         {
/*  850 */           return getRootContainer((EObject)parent, false);
/*      */         }
/*  852 */         internalEObject = parent;
/*  853 */         if (parent == eObject)
/*      */         {
/*  855 */           throw new IllegalStateException("There is a cycle in the containment hierarchy of " + eObject);
/*      */         }
/*      */       } 
/*      */     } 
/*  859 */     return (EObject)internalEObject;
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
/*      */   public static boolean isAncestor(EObject ancestorEObject, EObject eObject) {
/*  874 */     if (eObject != null) {
/*      */       
/*  876 */       if (eObject == ancestorEObject)
/*      */       {
/*  878 */         return true;
/*      */       }
/*      */       
/*  881 */       int count = 0;
/*  882 */       InternalEObject eContainer = ((InternalEObject)eObject).eInternalContainer();
/*  883 */       for (; eContainer != null && eContainer != eObject; 
/*  884 */         eContainer = eContainer.eInternalContainer()) {
/*      */         
/*  886 */         if (++count > 100000)
/*      */         {
/*  888 */           return isAncestor(ancestorEObject, (EObject)eContainer);
/*      */         }
/*  890 */         if (eContainer == ancestorEObject)
/*      */         {
/*  892 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*  896 */     return false;
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
/*      */   public static boolean isAncestor(Resource ancestorResource, EObject eObject) {
/*  910 */     for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer()) {
/*      */       
/*  912 */       if (parent.eDirectResource() == ancestorResource)
/*      */       {
/*  914 */         return true;
/*      */       }
/*      */     } 
/*  917 */     return false;
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
/*      */   public static boolean isAncestor(ResourceSet ancestorResourceSet, EObject eObject) {
/*  932 */     for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer()) {
/*      */       
/*  934 */       Resource.Internal internal = parent.eDirectResource();
/*  935 */       if (internal != null && internal.getResourceSet() == ancestorResourceSet)
/*      */       {
/*  937 */         return true;
/*      */       }
/*      */     } 
/*  940 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAncestor(Collection<?> ancestorEMFObjects, EObject eObject) {
/*  951 */     for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer()) {
/*      */       
/*  953 */       if (ancestorEMFObjects.contains(parent))
/*      */       {
/*  955 */         return true;
/*      */       }
/*  957 */       Resource.Internal internal = parent.eDirectResource();
/*  958 */       if (internal != null && (ancestorEMFObjects.contains(internal) || ancestorEMFObjects.contains(internal.getResourceSet())))
/*      */       {
/*  960 */         return true;
/*      */       }
/*      */     } 
/*  963 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<EObject> filterDescendants(Collection<? extends EObject> eObjects) {
/*  974 */     List<EObject> result = new ArrayList<EObject>(eObjects.size());
/*      */ 
/*      */     
/*  977 */     for (EObject eObject : eObjects) {
/*      */       
/*  979 */       int i = 0, size = result.size(); while (true) { if (i >= size)
/*      */         
/*      */         { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  997 */           result.add(eObject); break; }  EObject rootEObject = result.get(i); if (rootEObject == eObject || isAncestor(rootEObject, eObject))
/*      */           break;  if (isAncestor(eObject, rootEObject)) { result.remove(i); size--; continue; }  i++; }
/*      */     
/* 1000 */     }  return result;
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
/*      */   public static <T> TreeIterator<T> getAllContents(Collection<?> emfObjects) {
/* 1019 */     return (TreeIterator<T>)new ContentTreeIterator(emfObjects);
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
/*      */   public static <T> TreeIterator<T> getAllContents(Collection<?> emfObjects, boolean resolve) {
/* 1040 */     return (TreeIterator<T>)new ContentTreeIterator(emfObjects, resolve);
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
/*      */   public static <T> TreeIterator<T> getAllContents(EObject eObject, boolean resolve) {
/* 1053 */     return 
/* 1054 */       (TreeIterator<T>)new ContentTreeIterator<T>(eObject, resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<T> getChildren(Object object) {
/* 1062 */           return (Iterator)getEObjectChildren((EObject)object);
/*      */         }
/*      */       };
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
/*      */   public static <T> TreeIterator<T> getAllContents(Resource resource, boolean resolve) {
/* 1077 */     return 
/* 1078 */       (TreeIterator<T>)new ContentTreeIterator<T>(resource.getContents(), resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<T> getChildren(Object object) {
/* 1086 */           return (object == this.object) ? ((List<T>)this.object).iterator() : (Iterator)getEObjectChildren((EObject)object);
/*      */         }
/*      */       };
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
/*      */   public static <T> TreeIterator<T> getAllContents(ResourceSet resourceSet, boolean resolve) {
/* 1103 */     return (TreeIterator<T>)new ContentTreeIterator(resourceSet, resolve);
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
/*      */   public static <T> TreeIterator<T> getAllProperContents(Collection<?> emfObjects, boolean resolve) {
/* 1125 */     return 
/* 1126 */       (TreeIterator<T>)new ContentTreeIterator<T>(emfObjects, resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<EObject> getEObjectChildren(EObject eObject) {
/* 1133 */           return new EcoreUtil.ProperContentIterator<EObject>(eObject, isResolveProxies());
/*      */         }
/*      */       };
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
/*      */   public static <T> TreeIterator<T> getAllProperContents(EObject eObject, boolean resolve) {
/* 1149 */     return 
/* 1150 */       (TreeIterator<T>)new ContentTreeIterator<T>(eObject, resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<T> getChildren(Object object) {
/* 1157 */           return new EcoreUtil.ProperContentIterator<T>((EObject)object, isResolveProxies());
/*      */         }
/*      */       };
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
/*      */   public static <T> TreeIterator<T> getAllProperContents(Resource resource, boolean resolve) {
/* 1173 */     return 
/* 1174 */       (TreeIterator<T>)new ContentTreeIterator<T>(resource.getContents(), resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<T> getChildren(Object object) {
/* 1181 */           if (object == this.object) {
/*      */             
/* 1183 */             Iterator<T> result = ((Collection<T>)object).iterator();
/* 1184 */             return result;
/*      */           } 
/*      */ 
/*      */           
/* 1188 */           return new EcoreUtil.ProperContentIterator<T>((EObject)object, isResolveProxies());
/*      */         }
/*      */       };
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
/*      */   public static <T> TreeIterator<T> getAllProperContents(ResourceSet resourceSet, boolean resolve) {
/* 1207 */     return 
/* 1208 */       (TreeIterator<T>)new ContentTreeIterator<T>(resourceSet, resolve)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<EObject> getEObjectChildren(EObject eObject) {
/* 1215 */           return new EcoreUtil.ProperContentIterator<EObject>(eObject, isResolveProxies());
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   public static class ProperContentIterator<E>
/*      */     implements Iterator<E>
/*      */   {
/*      */     protected Iterator<? extends E> iterator;
/*      */     
/*      */     protected E preparedResult;
/*      */ 
/*      */     
/*      */     public ProperContentIterator(List<? extends E> contents) {
/* 1230 */       this.iterator = contents.iterator();
/*      */     }
/*      */ 
/*      */     
/*      */     public ProperContentIterator(InternalEList<? extends E> basicContents) {
/* 1235 */       this.iterator = basicContents.basicIterator();
/*      */     }
/*      */ 
/*      */     
/*      */     public ProperContentIterator(EObject eObject) {
/* 1240 */       this(eObject, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public ProperContentIterator(EObject eObject, boolean isResolveProxies) {
/* 1246 */       EList<EObject> contents = eObject.eContents();
/* 1247 */       this.iterator = (
/*      */         
/* 1249 */         !isResolveProxies && contents instanceof InternalEList) ? (
/* 1250 */         (InternalEList)contents).basicIterator() : 
/* 1251 */         contents.iterator();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 1256 */       if (this.preparedResult == null) {
/*      */         
/* 1258 */         while (this.iterator.hasNext()) {
/*      */           
/* 1260 */           this.preparedResult = this.iterator.next();
/* 1261 */           if (((InternalEObject)this.preparedResult).eDirectResource() == null)
/*      */           {
/* 1263 */             return true;
/*      */           }
/*      */         } 
/* 1266 */         this.preparedResult = null;
/* 1267 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 1271 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public E next() {
/* 1277 */       hasNext();
/* 1278 */       E result = this.preparedResult;
/* 1279 */       this.preparedResult = null;
/* 1280 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public void remove() {
/* 1285 */       this.iterator.remove();
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
/*      */   public static class ContentTreeIterator<E>
/*      */     extends AbstractTreeIterator<E>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ResourcesIterator resourceSetIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ContentTreeIterator(Collection<?> emfObjects) {
/* 1322 */       super(emfObjects, false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ContentTreeIterator(Object object, boolean isResolveProxies) {
/* 1332 */       super(object, false);
/* 1333 */       this.isResolveProxies = isResolveProxies;
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
/*      */     public Iterator<E> getChildren(Object object) {
/* 1345 */       if (object instanceof EObject)
/*      */       {
/* 1347 */         return (Iterator)getEObjectChildren((EObject)object);
/*      */       }
/* 1349 */       if (object instanceof Resource)
/*      */       {
/* 1351 */         return (Iterator)getResourceChildren((Resource)object);
/*      */       }
/* 1353 */       if (object instanceof ResourceSet)
/*      */       {
/* 1355 */         return (Iterator)getResourceSetChildren((ResourceSet)object);
/*      */       }
/* 1357 */       if (object == this.object)
/*      */       {
/* 1359 */         return ((Collection<E>)object).iterator();
/*      */       }
/*      */ 
/*      */       
/* 1363 */       return getObjectChildren(object);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Iterator<? extends EObject> getEObjectChildren(EObject eObject) {
/* 1374 */       return isResolveProxies() ? eObject.eContents().iterator() : ((InternalEList<? extends EObject>)eObject.eContents()).basicIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 1383 */       return this.isResolveProxies;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Iterator<EObject> getResourceChildren(Resource resource) {
/* 1393 */       return resource.getContents().iterator();
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
/*      */     public boolean hasNext() {
/*      */       Iterator<?> iterator;
/* 1406 */       if (!this.includeRoot && this.data == null) {
/*      */         
/* 1408 */         this.nextPruneIterator = getChildren(this.object);
/* 1409 */         add(this.nextPruneIterator);
/* 1410 */         iterator = this.nextPruneIterator;
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1417 */         if (this.data == null)
/*      */         {
/* 1419 */           return true;
/*      */         }
/* 1421 */         if (isEmpty())
/*      */         {
/* 1423 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 1427 */         iterator = (Iterator)this.data[this.size - 1];
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1433 */       if (iterator == this.resourceSetIterator && !this.resourceSetIterator.reallyHasNext()) {
/*      */ 
/*      */ 
/*      */         
/* 1437 */         next();
/* 1438 */         return hasNext();
/*      */       } 
/*      */ 
/*      */       
/* 1442 */       return iterator.hasNext();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static class ResourcesIterator
/*      */       implements Iterator<Resource>
/*      */     {
/*      */       protected List<? extends Resource> resources;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1460 */       protected int index = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public ResourcesIterator(List<? extends Resource> resources) {
/* 1468 */         this.resources = resources;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean reallyHasNext() {
/* 1477 */         return (this.index < this.resources.size());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean hasNext() {
/* 1489 */         return (this.index <= this.resources.size());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Resource next() {
/* 1498 */         if (this.index >= this.resources.size()) {
/*      */           
/* 1500 */           this.index++;
/* 1501 */           return null;
/*      */         } 
/*      */ 
/*      */         
/* 1505 */         return this.resources.get(this.index++);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void remove() {
/* 1514 */         throw new UnsupportedOperationException();
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
/*      */     protected Iterator<Resource> getResourceSetChildren(ResourceSet resourceSet) {
/* 1526 */       return this.resourceSetIterator = new ResourcesIterator((List<? extends Resource>)resourceSet.getResources());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Iterator<E> getObjectChildren(Object object) {
/* 1536 */       return ECollections.emptyEList().iterator();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class CrossReferencer
/*      */     extends HashMap<EObject, Collection<EStructuralFeature.Setting>>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Collection<?> emfObjects;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CrossReferencer(EObject eObject) {
/* 1563 */       this.emfObjects = Collections.singleton(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CrossReferencer(Resource resource) {
/* 1572 */       this.emfObjects = Collections.singleton(resource);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CrossReferencer(ResourceSet resourceSet) {
/* 1581 */       this.emfObjects = Collections.singleton(resourceSet);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CrossReferencer(Collection<?> emfObjects) {
/* 1590 */       this.emfObjects = emfObjects;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean containment(EObject eObject) {
/* 1600 */       return true;
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
/*      */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 1613 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean resolve() {
/* 1622 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Collection<EStructuralFeature.Setting> newCollection() {
/* 1631 */       return new ArrayList<EStructuralFeature.Setting>();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Collection<EStructuralFeature.Setting> getCollection(Object key) {
/* 1642 */       Collection<EStructuralFeature.Setting> result = get(key);
/* 1643 */       if (result == null)
/*      */       {
/* 1645 */         put((EObject)key, result = newCollection());
/*      */       }
/* 1647 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected TreeIterator<Notifier> newContentsIterator() {
/* 1656 */       return (TreeIterator<Notifier>)new EcoreUtil.ContentTreeIterator(this.emfObjects);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void crossReference() {
/* 1664 */       for (TreeIterator<Notifier> contents = newContentsIterator(); contents.hasNext(); ) {
/*      */         
/* 1666 */         Object content = contents.next();
/* 1667 */         if (content instanceof EObject) {
/*      */           
/* 1669 */           EObject eObject = (EObject)content;
/* 1670 */           if (containment(eObject)) {
/*      */             
/* 1672 */             handleCrossReference(eObject);
/*      */             
/*      */             continue;
/*      */           } 
/* 1676 */           contents.prune();
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected EContentsEList.FeatureIterator<EObject> getCrossReferences(EObject eObject) {
/* 1684 */       return 
/*      */         
/* 1686 */         resolve() ? 
/* 1687 */         (EContentsEList.FeatureIterator<EObject>)eObject.eCrossReferences().iterator() : 
/* 1688 */         (EContentsEList.FeatureIterator<EObject>)((InternalEList)eObject.eCrossReferences()).basicIterator();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void handleCrossReference(EObject eObject) {
/* 1693 */       InternalEObject internalEObject = (InternalEObject)eObject;
/* 1694 */       for (EContentsEList.FeatureIterator<EObject> crossReferences = getCrossReferences((EObject)internalEObject); crossReferences.hasNext(); ) {
/*      */         
/* 1696 */         EObject crossReferencedEObject = crossReferences.next();
/* 1697 */         if (crossReferencedEObject != null) {
/*      */           
/* 1699 */           EReference eReference = (EReference)crossReferences.feature();
/* 1700 */           if (crossReference((EObject)internalEObject, eReference, crossReferencedEObject))
/*      */           {
/* 1702 */             add(internalEObject, eReference, crossReferencedEObject);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void add(InternalEObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 1710 */       getCollection(crossReferencedEObject).add(eObject.eSetting((EStructuralFeature)eReference));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void done() {
/* 1718 */       this.emfObjects = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Collection<?> emfObjects) {
/* 1728 */       CrossReferencer result = new CrossReferencer(emfObjects);
/* 1729 */       result.crossReference();
/* 1730 */       result.done();
/* 1731 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1741 */       StringBuffer result = new StringBuffer("{");
/*      */       
/* 1743 */       for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : entrySet()) {
/*      */         
/* 1745 */         EObject eObject = entry.getKey();
/* 1746 */         result.append(EcoreUtil.getIdentification(eObject));
/* 1747 */         result.append("=[");
/* 1748 */         Collection<EStructuralFeature.Setting> collection = entry.getValue();
/* 1749 */         for (Iterator<EStructuralFeature.Setting> j = collection.iterator(); j.hasNext(); ) {
/*      */           
/* 1751 */           EStructuralFeature.Setting setting = j.next();
/* 1752 */           EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
/* 1753 */           result.append(eStructuralFeature.getName());
/* 1754 */           result.append("<-");
/* 1755 */           result.append(EcoreUtil.getIdentification(setting.getEObject()));
/* 1756 */           if (j.hasNext())
/*      */           {
/* 1758 */             result.append(", ");
/*      */           }
/*      */         } 
/* 1761 */         result.append(']');
/*      */       } 
/*      */ 
/*      */       
/* 1765 */       result.append('}');
/* 1766 */       return result.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static void print(PrintStream out, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferenceMap) {
/* 1776 */       out.println('{');
/*      */       
/* 1778 */       for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : crossReferenceMap.entrySet()) {
/*      */         
/* 1780 */         EObject eObject = entry.getKey();
/* 1781 */         out.print(" ");
/* 1782 */         out.print(EcoreUtil.getIdentification(eObject));
/* 1783 */         Collection<EStructuralFeature.Setting> collection = entry.getValue();
/* 1784 */         if (collection.isEmpty()) {
/*      */           
/* 1786 */           out.println(" =[]");
/*      */           
/*      */           continue;
/*      */         } 
/* 1790 */         out.println(" =[");
/* 1791 */         for (Iterator<EStructuralFeature.Setting> j = collection.iterator(); j.hasNext(); ) {
/*      */           
/* 1793 */           EStructuralFeature.Setting setting = j.next();
/* 1794 */           EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
/* 1795 */           out.print("   ");
/* 1796 */           out.print(eStructuralFeature.getName());
/* 1797 */           out.print("<-");
/* 1798 */           out.print(EcoreUtil.getIdentification(setting.getEObject()));
/* 1799 */           if (j.hasNext())
/*      */           {
/* 1801 */             out.println(",");
/*      */           }
/*      */         } 
/* 1804 */         out.println(']');
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1809 */       out.println('}');
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static void print(PrintStream out, Collection<EStructuralFeature.Setting> settings) {
/* 1820 */       if (settings.isEmpty()) {
/*      */         
/* 1822 */         out.println("[]");
/*      */       }
/*      */       else {
/*      */         
/* 1826 */         out.println("[");
/* 1827 */         for (Iterator<EStructuralFeature.Setting> j = settings.iterator(); j.hasNext(); ) {
/*      */           
/* 1829 */           EStructuralFeature.Setting setting = j.next();
/* 1830 */           EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
/* 1831 */           out.print(" ");
/* 1832 */           out.print(eStructuralFeature.getName());
/* 1833 */           out.print("<-");
/* 1834 */           out.print(EcoreUtil.getIdentification(setting.getEObject()));
/* 1835 */           if (j.hasNext())
/*      */           {
/* 1837 */             out.println(",");
/*      */           }
/*      */         } 
/* 1840 */         out.println(']');
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ExternalCrossReferencer
/*      */     extends CrossReferencer
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ExternalCrossReferencer(Collection<?> emfObjects) {
/* 1858 */       super(emfObjects);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ExternalCrossReferencer(EObject eObject) {
/* 1867 */       super(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ExternalCrossReferencer(Resource resource) {
/* 1876 */       super(Collections.singleton(resource));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ExternalCrossReferencer(ResourceSet resourceSet) {
/* 1885 */       super(Collections.singleton(resourceSet));
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
/*      */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 1899 */       return !EcoreUtil.isAncestor(this.emfObjects, crossReferencedEObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<EObject, Collection<EStructuralFeature.Setting>> findExternalCrossReferences() {
/* 1908 */       crossReference();
/* 1909 */       done();
/* 1910 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(EObject eObject) {
/* 1920 */       return (new ExternalCrossReferencer(eObject)).findExternalCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Resource resource) {
/* 1930 */       return (new ExternalCrossReferencer(resource)).findExternalCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(ResourceSet resourceSet) {
/* 1940 */       return (new ExternalCrossReferencer(resourceSet)).findExternalCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Collection<?> emfObjectsToSearch) {
/* 1950 */       return (new ExternalCrossReferencer(emfObjectsToSearch)).findExternalCrossReferences();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class AbstractFilteredSettingsIterator<E>
/*      */     implements Iterator<E>
/*      */   {
/*      */     protected List<EStructuralFeature.Setting> list;
/*      */ 
/*      */     
/*      */     protected int size;
/*      */     
/*      */     protected int index;
/*      */     
/*      */     protected Iterator<EStructuralFeature.Setting> iterator;
/*      */     
/*      */     protected EStructuralFeature.Setting preparedResult;
/*      */     
/*      */     protected EReference eReference;
/*      */     
/*      */     protected EClass eClass;
/*      */ 
/*      */     
/*      */     public AbstractFilteredSettingsIterator(List<EStructuralFeature.Setting> list, EReference eReference, EClass eClass) {
/* 1976 */       if (list instanceof java.util.RandomAccess) {
/*      */         
/* 1978 */         this.list = list;
/* 1979 */         this.size = list.size();
/*      */       }
/*      */       else {
/*      */         
/* 1983 */         this.iterator = list.iterator();
/*      */       } 
/* 1985 */       this.eReference = eReference;
/* 1986 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public AbstractFilteredSettingsIterator(Collection<EStructuralFeature.Setting> collection, EReference eReference, EClass eClass) {
/* 1991 */       if (collection instanceof java.util.RandomAccess) {
/*      */         
/* 1993 */         this.list = (List<EStructuralFeature.Setting>)collection;
/* 1994 */         this.size = this.list.size();
/*      */       }
/*      */       else {
/*      */         
/* 1998 */         this.iterator = collection.iterator();
/*      */       } 
/* 2000 */       this.eReference = eReference;
/* 2001 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public AbstractFilteredSettingsIterator(Iterator<EStructuralFeature.Setting> iterator, EReference eReference, EClass eClass) {
/* 2006 */       this.iterator = iterator;
/* 2007 */       this.eReference = eReference;
/* 2008 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isIncluded(EStructuralFeature.Setting setting) {
/* 2014 */       if ((this.eReference == null || setting.getEStructuralFeature() == this.eReference) && (
/* 2015 */         this.eClass == null || this.eClass.isInstance(setting.getEObject()))) return true; 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 2020 */       if (this.preparedResult == null) {
/*      */         
/* 2022 */         if (this.iterator == null) {
/*      */           
/* 2024 */           while (this.index < this.size) {
/*      */             
/* 2026 */             EStructuralFeature.Setting setting = this.list.get(this.index++);
/* 2027 */             if (isIncluded(setting))
/*      */             {
/* 2029 */               this.preparedResult = setting;
/* 2030 */               return true;
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2036 */           while (this.iterator.hasNext()) {
/*      */             
/* 2038 */             EStructuralFeature.Setting setting = this.iterator.next();
/* 2039 */             if (isIncluded(setting)) {
/*      */               
/* 2041 */               this.preparedResult = setting;
/* 2042 */               return true;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2046 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 2050 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public E next() {
/* 2056 */       if (hasNext()) {
/*      */         
/* 2058 */         E result = yield(this.preparedResult);
/* 2059 */         this.preparedResult = null;
/* 2060 */         return result;
/*      */       } 
/*      */ 
/*      */       
/* 2064 */       throw new NoSuchElementException();
/*      */     }
/*      */ 
/*      */     
/*      */     protected abstract E yield(EStructuralFeature.Setting param1Setting);
/*      */ 
/*      */     
/*      */     public void remove() {
/* 2072 */       if (this.iterator == null) {
/*      */         
/* 2074 */         this.list.remove(this.index - 1);
/*      */       }
/*      */       else {
/*      */         
/* 2078 */         this.iterator.remove();
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
/*      */   public static class FilteredSettingsIterator
/*      */     extends AbstractFilteredSettingsIterator<EStructuralFeature.Setting>
/*      */   {
/*      */     public FilteredSettingsIterator(List<EStructuralFeature.Setting> list, EReference eReference, EClass eClass) {
/* 2094 */       super(list, eReference, eClass);
/*      */     }
/*      */ 
/*      */     
/*      */     public FilteredSettingsIterator(Collection<EStructuralFeature.Setting> collection, EReference eReference, EClass eClass) {
/* 2099 */       super(collection, eReference, eClass);
/*      */     }
/*      */ 
/*      */     
/*      */     public FilteredSettingsIterator(Iterator<EStructuralFeature.Setting> iterator, EReference eReference, EClass eClass) {
/* 2104 */       super(iterator, eReference, eClass);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected EStructuralFeature.Setting yield(EStructuralFeature.Setting setting) {
/* 2110 */       return setting;
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
/*      */   public static boolean equals(EObject eObject1, EObject eObject2) {
/* 2123 */     EqualityHelper equalityHelper = new EqualityHelper();
/* 2124 */     return equalityHelper.equals(eObject1, eObject2);
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
/*      */   public static class EqualityHelper
/*      */     extends HashMap<EObject, EObject>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(EObject eObject1, EObject eObject2) {
/* 2179 */       if (eObject1 == null)
/*      */       {
/* 2181 */         return (eObject2 == null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2186 */       if (eObject2 == null)
/*      */       {
/* 2188 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2194 */       Object eObject1MappedValue = get(eObject1);
/* 2195 */       if (eObject1MappedValue != null)
/*      */       {
/*      */ 
/*      */         
/* 2199 */         return (eObject1MappedValue == eObject2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2204 */       Object eObject2MappedValue = get(eObject2);
/* 2205 */       if (eObject2MappedValue != null)
/*      */       {
/*      */ 
/*      */         
/* 2209 */         return (eObject2MappedValue == eObject1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2216 */       if (eObject1 == eObject2) {
/*      */ 
/*      */ 
/*      */         
/* 2220 */         put(eObject1, eObject2);
/* 2221 */         put(eObject2, eObject1);
/* 2222 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2227 */       EClass eClass = eObject1.eClass();
/* 2228 */       if (eClass != eObject2.eClass())
/*      */       {
/* 2230 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2235 */       put(eObject1, eObject2);
/* 2236 */       put(eObject2, eObject1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2241 */       for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */ 
/*      */ 
/*      */         
/* 2245 */         EStructuralFeature feature = eClass.getEStructuralFeature(i);
/* 2246 */         if (!feature.isDerived())
/*      */         {
/* 2248 */           if (!haveEqualFeature(eObject1, eObject2, feature))
/*      */           {
/* 2250 */             return false;
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2257 */       return true;
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
/*      */     public boolean equals(List<EObject> list1, List<EObject> list2) {
/* 2269 */       int size = list1.size();
/* 2270 */       if (size != list2.size())
/*      */       {
/* 2272 */         return false;
/*      */       }
/*      */       
/* 2275 */       for (int i = 0; i < size; i++) {
/*      */         
/* 2277 */         EObject eObject1 = list1.get(i);
/* 2278 */         EObject eObject2 = list2.get(i);
/* 2279 */         if (!equals(eObject1, eObject2))
/*      */         {
/* 2281 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 2285 */       return true;
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
/*      */ 
/*      */     
/*      */     protected boolean haveEqualFeature(EObject eObject1, EObject eObject2, EStructuralFeature feature) {
/* 2301 */       if (eObject1.eIsSet(feature) == eObject2.eIsSet(feature) && (
/* 2302 */         (feature instanceof EReference) ? 
/* 2303 */         haveEqualReference(eObject1, eObject2, (EReference)feature) : 
/* 2304 */         haveEqualAttribute(eObject1, eObject2, (EAttribute)feature))) return true;
/*      */       
/*      */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {
/* 2317 */       Object value1 = eObject1.eGet((EStructuralFeature)reference);
/* 2318 */       Object value2 = eObject2.eGet((EStructuralFeature)reference);
/*      */       
/* 2320 */       return 
/* 2321 */         reference.isMany() ? 
/* 2322 */         equals((List<EObject>)value1, (List<EObject>)value2) : 
/* 2323 */         equals((EObject)value1, (EObject)value2);
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
/*      */     protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute attribute) {
/* 2335 */       Object value1 = eObject1.eGet((EStructuralFeature)attribute);
/* 2336 */       Object value2 = eObject2.eGet((EStructuralFeature)attribute);
/*      */ 
/*      */ 
/*      */       
/* 2340 */       if (value1 == null)
/*      */       {
/* 2342 */         return (value2 == null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2347 */       if (value2 == null)
/*      */       {
/* 2349 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2354 */       if (FeatureMapUtil.isFeatureMap((EStructuralFeature)attribute)) {
/*      */ 
/*      */ 
/*      */         
/* 2358 */         FeatureMap featureMap1 = (FeatureMap)value1;
/* 2359 */         FeatureMap featureMap2 = (FeatureMap)value2;
/* 2360 */         return equalFeatureMaps(featureMap1, featureMap2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2366 */       return value1.equals(value2);
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
/*      */     protected boolean equalFeatureMaps(FeatureMap featureMap1, FeatureMap featureMap2) {
/* 2379 */       int size = featureMap1.size();
/* 2380 */       if (size != featureMap2.size())
/*      */       {
/* 2382 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2387 */       for (int i = 0; i < size; i++) {
/*      */ 
/*      */ 
/*      */         
/* 2391 */         EStructuralFeature feature = featureMap1.getEStructuralFeature(i);
/* 2392 */         if (feature != featureMap2.getEStructuralFeature(i))
/*      */         {
/* 2394 */           return false;
/*      */         }
/*      */         
/* 2397 */         Object value1 = featureMap1.getValue(i);
/* 2398 */         Object value2 = featureMap2.getValue(i);
/*      */         
/* 2400 */         if (!equalFeatureMapValues(value1, value2, feature))
/*      */         {
/* 2402 */           return false;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2408 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean equalFeatureMapValues(Object value1, Object value2, EStructuralFeature feature) {
/* 2418 */       if (feature instanceof EReference)
/*      */       {
/*      */ 
/*      */         
/* 2422 */         return equals((EObject)value1, (EObject)value2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2428 */       return (value1 == null) ? ((value2 == null)) : value1.equals(value2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UsageCrossReferencer
/*      */     extends CrossReferencer
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Collection<?> eObjectsOfInterest;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UsageCrossReferencer(EObject eObject) {
/* 2452 */       super(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UsageCrossReferencer(Resource resource) {
/* 2461 */       super(resource);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UsageCrossReferencer(ResourceSet resourceSet) {
/* 2470 */       super(resourceSet);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UsageCrossReferencer(Collection<?> emfObjects) {
/* 2479 */       super(emfObjects);
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
/*      */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 2493 */       return this.eObjectsOfInterest.contains(crossReferencedEObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Collection<EStructuralFeature.Setting> findUsage(EObject eObject) {
/* 2503 */       this.eObjectsOfInterest = Collections.singleton(eObject);
/* 2504 */       crossReference();
/* 2505 */       this.eObjectsOfInterest = null;
/* 2506 */       done();
/* 2507 */       return getCollection(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<EObject, Collection<EStructuralFeature.Setting>> findAllUsage(Collection<?> eObjectsOfInterest) {
/* 2517 */       this.eObjectsOfInterest = eObjectsOfInterest;
/* 2518 */       crossReference();
/* 2519 */       this.eObjectsOfInterest = null;
/* 2520 */       done();
/* 2521 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, EObject eObject) {
/* 2532 */       return (new UsageCrossReferencer(eObject)).findUsage(eObjectOfInterest);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Resource resource) {
/* 2543 */       return (new UsageCrossReferencer(resource)).findUsage(eObjectOfInterest);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, ResourceSet resourceSet) {
/* 2554 */       return (new UsageCrossReferencer(resourceSet)).findUsage(eObjectOfInterest);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Collection<?> emfObjectsToSearch) {
/* 2565 */       return (new UsageCrossReferencer(emfObjectsToSearch)).findUsage(eObjectOfInterest);
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
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, EObject eObject) {
/* 2577 */       return (new UsageCrossReferencer(eObject)).findAllUsage(eObjectsOfInterest);
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
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Resource resource) {
/* 2589 */       return (new UsageCrossReferencer(resource)).findAllUsage(eObjectsOfInterest);
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
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, ResourceSet resourceSet) {
/* 2601 */       return (new UsageCrossReferencer(resourceSet)).findAllUsage(eObjectsOfInterest);
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
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Collection<?> emfObjectsToSearch) {
/* 2613 */       return (new UsageCrossReferencer(emfObjectsToSearch)).findAllUsage(eObjectsOfInterest);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ProxyCrossReferencer
/*      */     extends CrossReferencer
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ProxyCrossReferencer(EObject eObject) {
/* 2630 */       super(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ProxyCrossReferencer(Resource resource) {
/* 2639 */       super(Collections.singleton(resource));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ProxyCrossReferencer(ResourceSet resourceSet) {
/* 2648 */       super(Collections.singleton(resourceSet));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ProxyCrossReferencer(Collection<?> emfObjects) {
/* 2657 */       super(emfObjects);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean resolve() {
/* 2667 */       return false;
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
/*      */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 2681 */       return crossReferencedEObject.eIsProxy();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<EObject, Collection<EStructuralFeature.Setting>> findProxyCrossReferences() {
/* 2690 */       crossReference();
/* 2691 */       done();
/* 2692 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(EObject eObject) {
/* 2702 */       return (new ProxyCrossReferencer(eObject)).findProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Resource resource) {
/* 2712 */       return (new ProxyCrossReferencer(resource)).findProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(ResourceSet resourceSet) {
/* 2722 */       return (new ProxyCrossReferencer(resourceSet)).findProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Collection<?> emfObjects) {
/* 2732 */       return (new ProxyCrossReferencer(emfObjects)).findProxyCrossReferences();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UnresolvedProxyCrossReferencer
/*      */     extends CrossReferencer
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UnresolvedProxyCrossReferencer(EObject eObject) {
/* 2749 */       super(eObject);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UnresolvedProxyCrossReferencer(Resource resource) {
/* 2758 */       super(Collections.singleton(resource));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UnresolvedProxyCrossReferencer(ResourceSet resourceSet) {
/* 2767 */       super(Collections.singleton(resourceSet));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected UnresolvedProxyCrossReferencer(Collection<?> emfObjects) {
/* 2776 */       super(emfObjects);
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
/*      */     protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
/* 2790 */       return crossReferencedEObject.eIsProxy();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<EObject, Collection<EStructuralFeature.Setting>> findUnresolvedProxyCrossReferences() {
/* 2799 */       crossReference();
/* 2800 */       done();
/* 2801 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(EObject eObject) {
/* 2811 */       return (new UnresolvedProxyCrossReferencer(eObject)).findUnresolvedProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Resource resource) {
/* 2821 */       return (new UnresolvedProxyCrossReferencer(resource)).findUnresolvedProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(ResourceSet resourceSet) {
/* 2831 */       return (new UnresolvedProxyCrossReferencer(resourceSet)).findUnresolvedProxyCrossReferences();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Collection<?> emfObjects) {
/* 2841 */       return (new UnresolvedProxyCrossReferencer(emfObjects)).findUnresolvedProxyCrossReferences();
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
/*      */   public static String getIdentification(EObject eObject) {
/* 2856 */     StringBuffer result = new StringBuffer(eObject.getClass().getName());
/* 2857 */     EClass eClass = eObject.eClass();
/* 2858 */     if (eClass.getInstanceClassName() == null) {
/*      */       
/* 2860 */       result.append('/');
/* 2861 */       result.append(eClass.getEPackage().getNsURI());
/* 2862 */       result.append('#');
/* 2863 */       result.append(eClass.getName());
/*      */     } 
/* 2865 */     result.append('@');
/* 2866 */     result.append(Integer.toHexString(eObject.hashCode()));
/*      */     
/* 2868 */     result.append('{');
/* 2869 */     result.append(getURI(eObject));
/* 2870 */     result.append('}');
/*      */     
/* 2872 */     return result.toString();
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
/*      */   public static URI getURI(EObject eObject) {
/* 2889 */     URI proxyURI = ((InternalEObject)eObject).eProxyURI();
/* 2890 */     if (proxyURI != null)
/*      */     {
/* 2892 */       return proxyURI;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2898 */     Resource resource = eObject.eResource();
/* 2899 */     if (resource != null)
/*      */     {
/* 2901 */       return resource.getURI().appendFragment(resource.getURIFragment(eObject));
/*      */     }
/*      */ 
/*      */     
/* 2905 */     String id = getID(eObject);
/* 2906 */     if (id != null)
/*      */     {
/* 2908 */       return URI.createURI("#" + id);
/*      */     }
/*      */ 
/*      */     
/* 2912 */     InternalEObject internalEObject = (InternalEObject)eObject;
/* 2913 */     List<String> uriFragmentPath = new ArrayList<String>();
/* 2914 */     HashSet<InternalEObject> visited = new HashSet<InternalEObject>();
/* 2915 */     for (InternalEObject container = internalEObject.eInternalContainer(); container != null && visited.add(container); container = internalEObject.eInternalContainer()) {
/*      */       
/* 2917 */       uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), (EObject)internalEObject));
/* 2918 */       internalEObject = container;
/*      */     } 
/*      */     
/* 2921 */     StringBuffer result = new StringBuffer("#//");
/*      */     
/* 2923 */     for (int i = uriFragmentPath.size() - 1; i >= 0; i--) {
/*      */       
/* 2925 */       result.append('/');
/* 2926 */       result.append(uriFragmentPath.get(i));
/*      */     } 
/* 2928 */     return URI.createURI(result.toString());
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
/*      */   @Deprecated
/*      */   public static int indexOf(List<?> list, Object o, int fromIndex) {
/* 2949 */     return ECollections.indexOf(list, o, fromIndex);
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
/*      */   @Deprecated
/*      */   public static <T> void setEList(EList<T> eList, Collection<? extends T> prototypeCollection) {
/* 2965 */     ECollections.setEList(eList, new ArrayList<T>(prototypeCollection));
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
/*      */   @Deprecated
/*      */   public static <T> void setEList(EList<T> eList, List<? extends T> prototypeList) {
/* 2981 */     ECollections.setEList(eList, prototypeList);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void remove(EStructuralFeature.Setting setting, Object value) {
/* 2991 */     if (FeatureMapUtil.isMany(setting.getEObject(), setting.getEStructuralFeature())) {
/*      */       
/* 2993 */       ((List)setting.get(false)).remove(value);
/*      */     }
/*      */     else {
/*      */       
/* 2997 */       setting.unset();
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
/*      */   public static void replace(EStructuralFeature.Setting setting, Object oldValue, Object newValue) {
/* 3009 */     if (FeatureMapUtil.isMany(setting.getEObject(), setting.getEStructuralFeature())) {
/*      */       
/* 3011 */       List<Object> list = (List<Object>)setting.get(false);
/* 3012 */       list.set(list.indexOf(oldValue), newValue);
/*      */     }
/*      */     else {
/*      */       
/* 3016 */       setting.set(newValue);
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
/*      */   public static void remove(EObject eObject, EStructuralFeature eStructuralFeature, Object value) {
/* 3028 */     if (FeatureMapUtil.isMany(eObject, eStructuralFeature)) {
/*      */       
/* 3030 */       ((List)eObject.eGet(eStructuralFeature)).remove(value);
/*      */     }
/*      */     else {
/*      */       
/* 3034 */       eObject.eUnset(eStructuralFeature);
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
/*      */   public static void replace(EObject eObject, EStructuralFeature eStructuralFeature, Object oldValue, Object newValue) {
/* 3047 */     if (FeatureMapUtil.isMany(eObject, eStructuralFeature)) {
/*      */       
/* 3049 */       List<Object> list = (List<Object>)eObject.eGet(eStructuralFeature);
/* 3050 */       list.set(list.indexOf(oldValue), newValue);
/*      */     }
/*      */     else {
/*      */       
/* 3054 */       eObject.eSet(eStructuralFeature, newValue);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void remove(EObject eObject) {
/* 3065 */     InternalEObject internalEObject = (InternalEObject)eObject;
/* 3066 */     InternalEObject internalEObject1 = internalEObject.eInternalContainer();
/* 3067 */     if (internalEObject1 != null) {
/*      */       
/* 3069 */       EReference feature = eObject.eContainmentFeature();
/* 3070 */       if (FeatureMapUtil.isMany((EObject)internalEObject1, (EStructuralFeature)feature)) {
/*      */         
/* 3072 */         ((EList)internalEObject1.eGet((EStructuralFeature)feature)).remove(eObject);
/*      */       }
/*      */       else {
/*      */         
/* 3076 */         internalEObject1.eUnset((EStructuralFeature)feature);
/*      */       } 
/*      */     } 
/*      */     
/* 3080 */     Resource.Internal internal = internalEObject.eDirectResource();
/* 3081 */     if (internal != null)
/*      */     {
/* 3083 */       internal.getContents().remove(eObject);
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
/*      */   public static void replace(EObject eObject, EObject replacementEObject) {
/* 3096 */     InternalEObject internalEObject = (InternalEObject)eObject;
/* 3097 */     InternalEObject internalEObject1 = internalEObject.eInternalContainer();
/* 3098 */     if (internalEObject1 != null) {
/*      */       
/* 3100 */       EReference feature = eObject.eContainmentFeature();
/* 3101 */       if (FeatureMapUtil.isMany((EObject)internalEObject1, (EStructuralFeature)feature)) {
/*      */         
/* 3103 */         List<Object> list = (List<Object>)internalEObject1.eGet((EStructuralFeature)feature);
/* 3104 */         list.set(list.indexOf(eObject), replacementEObject);
/*      */       }
/*      */       else {
/*      */         
/* 3108 */         internalEObject1.eSet((EStructuralFeature)feature, replacementEObject);
/*      */       } 
/*      */     } 
/*      */     
/* 3112 */     Resource.Internal internal = internalEObject.eDirectResource();
/* 3113 */     if (internal != null) {
/*      */       
/* 3115 */       EList<EObject> eList = internal.getContents();
/* 3116 */       eList.set(eList.indexOf(eObject), replacementEObject);
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
/*      */   public static void delete(EObject eObject) {
/*      */     Collection<EStructuralFeature.Setting> usages;
/* 3130 */     EObject rootEObject = getRootContainer(eObject);
/* 3131 */     Resource resource = rootEObject.eResource();
/*      */ 
/*      */     
/* 3134 */     if (resource == null) {
/*      */       
/* 3136 */       usages = UsageCrossReferencer.find(eObject, rootEObject);
/*      */     }
/*      */     else {
/*      */       
/* 3140 */       ResourceSet resourceSet = resource.getResourceSet();
/* 3141 */       if (resourceSet == null) {
/*      */         
/* 3143 */         usages = UsageCrossReferencer.find(eObject, resource);
/*      */       }
/*      */       else {
/*      */         
/* 3147 */         usages = UsageCrossReferencer.find(eObject, resourceSet);
/*      */       } 
/*      */     } 
/*      */     
/* 3151 */     for (EStructuralFeature.Setting setting : usages) {
/*      */       
/* 3153 */       if (setting.getEStructuralFeature().isChangeable())
/*      */       {
/* 3155 */         remove(setting, eObject);
/*      */       }
/*      */     } 
/*      */     
/* 3159 */     remove(eObject);
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
/*      */   public static void delete(EObject eObject, boolean recursive) {
/* 3175 */     if (recursive) {
/*      */       Map<EObject, Collection<EStructuralFeature.Setting>> usages;
/* 3177 */       EObject rootEObject = getRootContainer(eObject);
/* 3178 */       Resource resource = rootEObject.eResource();
/*      */       
/* 3180 */       Set<EObject> eObjects = new HashSet<EObject>();
/* 3181 */       Set<EObject> crossResourceEObjects = new HashSet<EObject>();
/* 3182 */       eObjects.add(eObject);
/* 3183 */       for (TreeIterator<InternalEObject> j = eObject.eAllContents(); j.hasNext(); ) {
/*      */         
/* 3185 */         InternalEObject childEObject = (InternalEObject)j.next();
/* 3186 */         if (childEObject.eDirectResource() != null) {
/*      */           
/* 3188 */           crossResourceEObjects.add(childEObject);
/*      */           
/*      */           continue;
/*      */         } 
/* 3192 */         eObjects.add(childEObject);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3197 */       if (resource == null) {
/*      */         
/* 3199 */         usages = UsageCrossReferencer.findAll(eObjects, rootEObject);
/*      */       }
/*      */       else {
/*      */         
/* 3203 */         ResourceSet resourceSet = resource.getResourceSet();
/* 3204 */         if (resourceSet == null) {
/*      */           
/* 3206 */           usages = UsageCrossReferencer.findAll(eObjects, resource);
/*      */         }
/*      */         else {
/*      */           
/* 3210 */           usages = UsageCrossReferencer.findAll(eObjects, resourceSet);
/*      */         } 
/*      */       } 
/*      */       
/* 3214 */       for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet()) {
/*      */         
/* 3216 */         EObject deletedEObject = entry.getKey();
/* 3217 */         Collection<EStructuralFeature.Setting> settings = entry.getValue();
/* 3218 */         for (EStructuralFeature.Setting setting : settings) {
/*      */           
/* 3220 */           if (!eObjects.contains(setting.getEObject()) && setting.getEStructuralFeature().isChangeable())
/*      */           {
/* 3222 */             remove(setting, deletedEObject);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 3227 */       remove(eObject);
/*      */       
/* 3229 */       for (EObject crossResourceEObject : crossResourceEObjects)
/*      */       {
/* 3231 */         remove(crossResourceEObject.eContainer(), (EStructuralFeature)crossResourceEObject.eContainmentFeature(), crossResourceEObject);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3236 */       delete(eObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EObject create(EClass eClass) {
/* 3247 */     return eClass.getEPackage().getEFactoryInstance().create(eClass);
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
/*      */   public static Object createFromString(EDataType eDataType, String literal) {
/* 3259 */     return eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, literal);
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
/*      */   public static String convertToString(EDataType eDataType, Object value) {
/* 3271 */     return eDataType.getEPackage().getEFactoryInstance().convertToString(eDataType, value);
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
/*      */   public static String getID(EObject eObject) {
/* 3285 */     EClass eClass = eObject.eClass();
/* 3286 */     EAttribute eIDAttribute = eClass.getEIDAttribute();
/* 3287 */     return (eIDAttribute == null || !eObject.eIsSet((EStructuralFeature)eIDAttribute)) ? null : convertToString(
/* 3288 */         eIDAttribute.getEAttributeType(), 
/* 3289 */         eObject.eGet((EStructuralFeature)eIDAttribute));
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
/*      */   public static void setID(EObject eObject, String id) {
/* 3304 */     EClass eClass = eObject.eClass();
/* 3305 */     EAttribute eIDAttribute = eClass.getEIDAttribute();
/* 3306 */     if (eIDAttribute == null)
/*      */     {
/* 3308 */       throw new IllegalArgumentException("The object doesn't have an ID feature.");
/*      */     }
/* 3310 */     if (id == null) {
/*      */       
/* 3312 */       eObject.eUnset((EStructuralFeature)eIDAttribute);
/*      */     }
/*      */     else {
/*      */       
/* 3316 */       eObject.eSet((EStructuralFeature)eIDAttribute, createFromString(eIDAttribute.getEAttributeType(), id));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Class<?> wrapperClassFor(Class<?> javaClass) {
/* 3326 */     if (javaClass == null)
/*      */     {
/* 3328 */       return null;
/*      */     }
/* 3330 */     if (javaClass.isPrimitive()) {
/*      */       
/* 3332 */       if (javaClass == boolean.class)
/*      */       {
/* 3334 */         return Boolean.class;
/*      */       }
/* 3336 */       if (javaClass == int.class)
/*      */       {
/* 3338 */         return Integer.class;
/*      */       }
/* 3340 */       if (javaClass == float.class)
/*      */       {
/* 3342 */         return Float.class;
/*      */       }
/* 3344 */       if (javaClass == double.class)
/*      */       {
/* 3346 */         return Double.class;
/*      */       }
/* 3348 */       if (javaClass == long.class)
/*      */       {
/* 3350 */         return Long.class;
/*      */       }
/* 3352 */       if (javaClass == short.class)
/*      */       {
/* 3354 */         return Short.class;
/*      */       }
/* 3356 */       if (javaClass == byte.class)
/*      */       {
/* 3358 */         return Byte.class;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3363 */       return Character.class;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3368 */     return javaClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getDocumentation(EModelElement eModelElement) {
/* 3376 */     EAnnotation eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3377 */     return (eAnnotation == null) ? null : (String)eAnnotation.getDetails().get("documentation");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setDocumentation(EModelElement eModelElement, String documentation) {
/* 3382 */     EAnnotation eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3383 */     if (documentation == null) {
/*      */       
/* 3385 */       if (eAnnotation != null)
/*      */       {
/* 3387 */         eAnnotation.getDetails().remove("documentation");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3392 */       if (eAnnotation == null) {
/*      */         
/* 3394 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 3395 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/GenModel");
/* 3396 */         eModelElement.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 3398 */       eAnnotation.getDetails().put("documentation", documentation);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<String> getConstraints(EModelElement eModelElement) {
/* 3404 */     EAnnotation eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 3405 */     if (eAnnotation != null) {
/*      */       
/* 3407 */       String constraints = (String)eAnnotation.getDetails().get("constraints");
/* 3408 */       if (constraints != null) {
/*      */         
/* 3410 */         List<String> result = new ArrayList<String>();
/* 3411 */         for (StringTokenizer stringTokenizer = new StringTokenizer(constraints); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 3413 */           String constraint = stringTokenizer.nextToken();
/* 3414 */           result.add(constraint);
/*      */         } 
/* 3416 */         return result;
/*      */       } 
/*      */     } 
/* 3419 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setConstraints(EModelElement eModelElement, List<String> constraints) {
/* 3424 */     EAnnotation eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 3425 */     if (constraints == null || constraints.isEmpty()) {
/*      */       
/* 3427 */       if (eAnnotation != null)
/*      */       {
/* 3429 */         eAnnotation.getDetails().remove("constraints");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3434 */       if (eAnnotation == null) {
/*      */         
/* 3436 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 3437 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/Ecore");
/* 3438 */         eModelElement.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 3440 */       StringBuffer value = new StringBuffer();
/* 3441 */       for (Iterator<String> i = constraints.iterator(); i.hasNext(); ) {
/*      */         
/* 3443 */         value.append(i.next());
/* 3444 */         if (i.hasNext())
/*      */         {
/* 3446 */           value.append(' ');
/*      */         }
/*      */       } 
/* 3449 */       eAnnotation.getDetails().put("constraints", value.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getAnnotation(EModelElement eModelElement, String sourceURI, String key) {
/* 3455 */     EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
/* 3456 */     return (eAnnotation == null) ? null : (String)eAnnotation.getDetails().get(key);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setAnnotation(EModelElement eModelElement, String sourceURI, String key, String value) {
/* 3461 */     EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
/* 3462 */     if (eAnnotation == null) {
/*      */       
/* 3464 */       eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 3465 */       eAnnotation.setSource(sourceURI);
/* 3466 */       eModelElement.getEAnnotations().add(eAnnotation);
/*      */     } 
/* 3468 */     eAnnotation.getDetails().put(key, value);
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
/* 3506 */   static final String[] ACCESSOR_KEYS = new String[] {
/* 3507 */       "suppressedGetVisibility", 
/* 3508 */       "suppressedSetVisibility", 
/* 3509 */       "suppressedIsSetVisibility", 
/* 3510 */       "suppressedUnsetVisibility"
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String TRUE = "true";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String OPERATION_VISIBILITY_KEY = "suppressedVisibility";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSuppressedVisibility(EStructuralFeature eStructuralFeature, int accessor) {
/* 3527 */     if (accessor < 0 || accessor > 3) throw new IllegalArgumentException("Invalid accessor identifier: " + accessor);
/*      */     
/* 3529 */     EAnnotation eAnnotation = eStructuralFeature.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3530 */     return (eAnnotation == null) ? false : "true".equalsIgnoreCase((String)eAnnotation.getDetails().get(ACCESSOR_KEYS[accessor]));
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
/*      */   public static void setSuppressedVisibility(EStructuralFeature eStructuralFeature, int accessor, boolean suppress) {
/* 3542 */     if (accessor < 0 || accessor > 3) throw new IllegalArgumentException("Invalid accessor identifier: " + accessor);
/*      */     
/* 3544 */     EAnnotation eAnnotation = eStructuralFeature.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3545 */     if (!suppress) {
/*      */       
/* 3547 */       if (eAnnotation != null)
/*      */       {
/* 3549 */         eAnnotation.getDetails().removeKey(ACCESSOR_KEYS[accessor]);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3554 */       if (eAnnotation == null) {
/*      */         
/* 3556 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 3557 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/GenModel");
/* 3558 */         eStructuralFeature.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 3560 */       eAnnotation.getDetails().put(ACCESSOR_KEYS[accessor], "true");
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
/*      */   public static boolean isSuppressedVisibility(EOperation eOperation) {
/* 3574 */     EAnnotation eAnnotation = eOperation.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3575 */     return (eAnnotation == null) ? false : "true".equalsIgnoreCase((String)eAnnotation.getDetails().get("suppressedVisibility"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSuppressedVisibility(EOperation eOperation, boolean suppress) {
/* 3586 */     EAnnotation eAnnotation = eOperation.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
/* 3587 */     if (!suppress) {
/*      */       
/* 3589 */       if (eAnnotation != null)
/*      */       {
/* 3591 */         eAnnotation.getDetails().removeKey("suppressedVisibility");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3596 */       if (eAnnotation == null) {
/*      */         
/* 3598 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 3599 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/GenModel");
/* 3600 */         eOperation.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 3602 */       eAnnotation.getDetails().put("suppressedVisibility", "true");
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
/*      */   public static String generateUUID() {
/* 3618 */     return UUID.generate();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateUUID(byte[] uuid) {
/* 3629 */     UUID.generate(uuid);
/*      */   }
/*      */ 
/*      */   
/*      */   private static final class UUID
/*      */   {
/*      */     public static synchronized String generate() {
/* 3636 */       updateCurrentTime();
/*      */ 
/*      */ 
/*      */       
/* 3640 */       for (int i = 0; i < 5; i++) {
/*      */         
/* 3642 */         buffer[4 * i + 1] = BASE64_DIGITS[uuid[i * 3] >> 2 & 0x3F];
/* 3643 */         buffer[4 * i + 2] = BASE64_DIGITS[uuid[i * 3] << 4 & 0x30 | uuid[i * 3 + 1] >> 4 & 0xF];
/* 3644 */         buffer[4 * i + 3] = BASE64_DIGITS[uuid[i * 3 + 1] << 2 & 0x3C | uuid[i * 3 + 2] >> 6 & 0x3];
/* 3645 */         buffer[4 * i + 4] = BASE64_DIGITS[uuid[i * 3 + 2] & 0x3F];
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3650 */       buffer[21] = BASE64_DIGITS[uuid[15] >> 2 & 0x3F];
/* 3651 */       buffer[22] = BASE64_DIGITS[uuid[15] << 4 & 0x30];
/*      */       
/* 3653 */       return new String(buffer);
/*      */     }
/*      */ 
/*      */     
/*      */     public static synchronized void generate(byte[] uuid) {
/* 3658 */       updateCurrentTime();
/*      */       
/* 3660 */       for (int i = 0; i < 16; i++)
/*      */       {
/* 3662 */         uuid[i] = UUID.uuid[i];
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3671 */     private static final char[] BASE64_DIGITS = new char[] { 
/* 3672 */         'A', 
/* 3673 */         'B', 
/* 3674 */         'C', 
/* 3675 */         'D', 
/* 3676 */         'E', 
/* 3677 */         'F', 
/* 3678 */         'G', 
/* 3679 */         'H', 
/* 3680 */         'I', 
/* 3681 */         'J', 
/* 3682 */         'K', 
/* 3683 */         'L', 
/* 3684 */         'M', 
/* 3685 */         'N', 
/* 3686 */         'O', 
/* 3687 */         'P', 
/* 3688 */         'Q', 
/* 3689 */         'R', 
/* 3690 */         'S', 
/* 3691 */         'T', 
/* 3692 */         'U', 
/* 3693 */         'V', 
/* 3694 */         'W', 
/* 3695 */         'X', 
/* 3696 */         'Y', 
/* 3697 */         'Z', 
/* 3698 */         'a', 
/* 3699 */         'b', 
/* 3700 */         'c', 
/* 3701 */         'd', 
/* 3702 */         'e', 
/* 3703 */         'f', 
/* 3704 */         'g', 
/* 3705 */         'h', 
/* 3706 */         'i', 
/* 3707 */         'j', 
/* 3708 */         'k', 
/* 3709 */         'l', 
/* 3710 */         'm', 
/* 3711 */         'n', 
/* 3712 */         'o', 
/* 3713 */         'p', 
/* 3714 */         'q', 
/* 3715 */         'r', 
/* 3716 */         's', 
/* 3717 */         't', 
/* 3718 */         'u', 
/* 3719 */         'v', 
/* 3720 */         'w', 
/* 3721 */         'x', 
/* 3722 */         'y', 
/* 3723 */         'z', 
/* 3724 */         '0', 
/* 3725 */         '1', 
/* 3726 */         '2', 
/* 3727 */         '3', 
/* 3728 */         '4', 
/* 3729 */         '5', 
/* 3730 */         '6', 
/* 3731 */         '7', 
/* 3732 */         '8', 
/* 3733 */         '9', 
/* 3734 */         '-', 
/* 3735 */         '_' };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3741 */     private static final long EPOCH_ADJUSTMENT = (new GregorianCalendar(1970, 0, 1, 0, 0, 0)).getTime().getTime() - (
/* 3742 */       new GregorianCalendar(1582, 9, 15, 0, 0, 0)).getTime().getTime();
/*      */     
/* 3744 */     private static long lastTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;
/*      */     
/*      */     private static short clockSequence;
/*      */     
/*      */     private static short timeAdjustment;
/*      */     
/* 3750 */     private static int sleepTime = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3756 */     private static final byte[] uuid = new byte[16];
/*      */     
/* 3758 */     private static final char[] buffer = new char[23];
/*      */ 
/*      */     
/*      */     static {
/* 3762 */       SecureRandom random = new SecureRandom();
/*      */       
/* 3764 */       clockSequence = (short)random.nextInt(16384);
/* 3765 */       updateClockSequence();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3770 */       byte[] nodeAddress = new byte[6];
/*      */       
/* 3772 */       random.nextBytes(nodeAddress);
/*      */ 
/*      */ 
/*      */       
/* 3776 */       nodeAddress[0] = (byte)(nodeAddress[0] | Byte.MIN_VALUE);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3781 */       for (int i = 0; i < 6; i++)
/*      */       {
/* 3783 */         uuid[i + 10] = nodeAddress[i];
/*      */       }
/*      */       
/* 3786 */       buffer[0] = '_';
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
/*      */     private static void updateClockSequence() {
/* 3798 */       uuid[8] = (byte)(clockSequence >> 8 & 0x3F | 0x80);
/*      */       
/* 3800 */       uuid[9] = (byte)(clockSequence & 0xFF);
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
/*      */     private static void updateCurrentTime() {
/* 3814 */       long currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;
/*      */       
/* 3816 */       if (lastTime > currentTime) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3821 */         clockSequence = (short)(clockSequence + 1);
/*      */         
/* 3823 */         if (16384 == clockSequence)
/*      */         {
/* 3825 */           clockSequence = 0;
/*      */         }
/*      */         
/* 3828 */         updateClockSequence();
/*      */       }
/* 3830 */       else if (lastTime == currentTime) {
/*      */ 
/*      */ 
/*      */         
/* 3834 */         timeAdjustment = (short)(timeAdjustment + 1);
/*      */         
/* 3836 */         if (timeAdjustment > 9999) {
/*      */ 
/*      */           
/*      */           try {
/*      */             
/* 3841 */             Thread.sleep(sleepTime);
/*      */           }
/* 3843 */           catch (InterruptedException interruptedException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3848 */           timeAdjustment = 0;
/* 3849 */           currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;
/*      */           
/* 3851 */           while (lastTime == currentTime)
/*      */           {
/*      */             
/*      */             try {
/* 3855 */               sleepTime++;
/* 3856 */               Thread.sleep(1L);
/*      */             }
/* 3858 */             catch (InterruptedException interruptedException) {}
/*      */ 
/*      */ 
/*      */             
/* 3862 */             currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 3868 */         timeAdjustment = 0;
/*      */       } 
/*      */       
/* 3871 */       lastTime = currentTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3877 */       currentTime *= 10000L;
/* 3878 */       currentTime += timeAdjustment;
/* 3879 */       currentTime |= 0x1000000000000000L;
/*      */       
/*      */       int i;
/*      */       
/* 3883 */       for (i = 0; i < 4; i++)
/*      */       {
/*      */ 
/*      */         
/* 3887 */         uuid[i] = (byte)(int)(currentTime >> 8 * (3 - i) & 0xFFL);
/*      */       }
/*      */       
/* 3890 */       for (i = 0; i < 2; i++)
/*      */       {
/*      */ 
/*      */         
/* 3894 */         uuid[i + 4] = (byte)(int)(currentTime >> 8 * (1 - i) + 32 & 0xFFL);
/*      */       }
/*      */       
/* 3897 */       for (i = 0; i < 2; i++)
/*      */       {
/*      */ 
/*      */         
/* 3901 */         uuid[i + 6] = (byte)(int)(currentTime >> 8 * (1 - i) + 48 & 0xFFL);
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
/*      */   public static void freeze(EPackage ePackage) {
/*      */     try {
/* 3914 */       ((EPackageImpl)ePackage).freeze();
/*      */     }
/* 3916 */     catch (ClassCastException classCastException) {}
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
/*      */   public static Diagnostic computeDiagnostic(Resource resource, boolean includeWarnings) {
/* 3930 */     if (resource.getErrors().isEmpty() && (!includeWarnings || resource.getWarnings().isEmpty()))
/*      */     {
/* 3932 */       return Diagnostic.OK_INSTANCE;
/*      */     }
/*      */ 
/*      */     
/* 3936 */     BasicDiagnostic basicDiagnostic = new BasicDiagnostic();
/* 3937 */     for (Resource.Diagnostic resourceDiagnostic : resource.getErrors()) {
/*      */       BasicDiagnostic basicDiagnostic1;
/* 3939 */       Diagnostic diagnostic = null;
/* 3940 */       if (resourceDiagnostic instanceof Throwable) {
/*      */         
/* 3942 */         diagnostic = BasicDiagnostic.toDiagnostic((Throwable)resourceDiagnostic);
/*      */       }
/*      */       else {
/*      */         
/* 3946 */         basicDiagnostic1 = new BasicDiagnostic(
/* 3947 */             4, 
/* 3948 */             "org.eclipse.emf.ecore.resource", 
/* 3949 */             0, 
/* 3950 */             resourceDiagnostic.getMessage(), 
/* 3951 */             new Object[] { resourceDiagnostic });
/*      */       } 
/* 3953 */       basicDiagnostic.add((Diagnostic)basicDiagnostic1);
/*      */     } 
/*      */     
/* 3956 */     if (includeWarnings)
/*      */     {
/* 3958 */       for (Resource.Diagnostic resourceDiagnostic : resource.getWarnings()) {
/*      */         BasicDiagnostic basicDiagnostic1;
/* 3960 */         Diagnostic diagnostic = null;
/* 3961 */         if (resourceDiagnostic instanceof Throwable) {
/*      */           
/* 3963 */           diagnostic = BasicDiagnostic.toDiagnostic((Throwable)resourceDiagnostic);
/*      */         }
/*      */         else {
/*      */           
/* 3967 */           basicDiagnostic1 = new BasicDiagnostic(
/* 3968 */               2, 
/* 3969 */               "org.eclipse.emf.ecore.resource", 
/* 3970 */               0, 
/* 3971 */               resourceDiagnostic.getMessage(), 
/* 3972 */               new Object[] { resourceDiagnostic });
/*      */         } 
/* 3974 */         basicDiagnostic.add((Diagnostic)basicDiagnostic1);
/*      */       } 
/*      */     }
/*      */     
/* 3978 */     return (Diagnostic)basicDiagnostic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toJavaInstanceTypeName(EGenericType eGenericType) {
/* 3989 */     return EGenericTypeConverter.INSTANCE.toJavaInstanceTypeName(eGenericType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EGenericTypeConverter
/*      */   {
/* 4000 */     public static EGenericTypeConverter INSTANCE = new EGenericTypeConverter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toJavaInstanceTypeName(EGenericType eGenericType) {
/* 4009 */       StringBuilder result = new StringBuilder();
/* 4010 */       convertJavaInstanceTypeName(result, eGenericType);
/* 4011 */       return result.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void convertJavaInstanceTypeName(StringBuilder result, EGenericType eGenericType) {
/* 4021 */       EClassifier eClassifier = eGenericType.getEClassifier();
/* 4022 */       if (eClassifier != null) {
/*      */         
/* 4024 */         String instanceTypeName = getInstanceTypeName(eClassifier);
/* 4025 */         EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
/* 4026 */         if (eTypeArguments.isEmpty())
/*      */         {
/* 4028 */           result.append(instanceTypeName);
/*      */         }
/*      */         else
/*      */         {
/* 4032 */           int index = instanceTypeName.indexOf('[');
/* 4033 */           result.append((index == -1) ? instanceTypeName : instanceTypeName.substring(0, index));
/* 4034 */           result.append('<');
/* 4035 */           for (int i = 0, size = eTypeArguments.size(); i < size; i++) {
/*      */             
/* 4037 */             if (i != 0)
/*      */             {
/* 4039 */               result.append(", ");
/*      */             }
/* 4041 */             convertJavaInstanceTypeName(result, (EGenericType)eTypeArguments.get(i));
/*      */           } 
/* 4043 */           result.append('>');
/* 4044 */           if (index != -1)
/*      */           {
/* 4046 */             result.append(instanceTypeName.substring(index));
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 4052 */         ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
/* 4053 */         if (eTypeParameter != null) {
/*      */           
/* 4055 */           result.append(eTypeParameter.getName());
/*      */         }
/*      */         else {
/*      */           
/* 4059 */           result.append('?');
/* 4060 */           EGenericType eUpperBound = eGenericType.getEUpperBound();
/* 4061 */           if (eUpperBound != null) {
/*      */             
/* 4063 */             result.append(" extends ");
/* 4064 */             convertJavaInstanceTypeName(result, eUpperBound);
/*      */           }
/*      */           else {
/*      */             
/* 4068 */             EGenericType eLowerBound = eGenericType.getELowerBound();
/* 4069 */             if (eLowerBound != null) {
/*      */               
/* 4071 */               result.append(" super ");
/* 4072 */               convertJavaInstanceTypeName(result, eLowerBound);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String getInstanceTypeName(EClassifier eClassifier) {
/* 4086 */       return eClassifier.getInstanceTypeName();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isInvariant(EOperation eOperation) {
/* 4095 */     if (eOperation.getEType() == EcorePackage.Literals.EBOOLEAN && 
/* 4096 */       eOperation.getEParameters().size() == 2 && (
/* 4097 */       (EParameter)eOperation.getEParameters().get(0)).getEType() == EcorePackage.Literals.EDIAGNOSTIC_CHAIN && (
/* 4098 */       (EParameter)eOperation.getEParameters().get(1)).getEType() == EcorePackage.Literals.EMAP) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getValidationDelegates(EPackage ePackage) {
/* 4106 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4107 */     if (eAnnotation != null) {
/*      */       
/* 4109 */       String validationDelegates = (String)eAnnotation.getDetails().get("validationDelegates");
/* 4110 */       if (validationDelegates != null) {
/*      */         
/* 4112 */         List<String> result = new ArrayList<String>();
/* 4113 */         for (StringTokenizer stringTokenizer = new StringTokenizer(validationDelegates); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 4115 */           String validationDelegate = stringTokenizer.nextToken();
/* 4116 */           result.add(validationDelegate);
/*      */         } 
/* 4118 */         return result;
/*      */       } 
/*      */     } 
/* 4121 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setValidationDelegates(EPackage ePackage, List<String> validationDelegates) {
/* 4129 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4130 */     if (validationDelegates == null || validationDelegates.isEmpty()) {
/*      */       
/* 4132 */       if (eAnnotation != null)
/*      */       {
/* 4134 */         eAnnotation.getDetails().remove("validationDelegates");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 4139 */       if (eAnnotation == null) {
/*      */         
/* 4141 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 4142 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/Ecore");
/* 4143 */         ePackage.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 4145 */       StringBuffer value = new StringBuffer();
/* 4146 */       for (Iterator<String> i = validationDelegates.iterator(); i.hasNext(); ) {
/*      */         
/* 4148 */         value.append(i.next());
/* 4149 */         if (i.hasNext())
/*      */         {
/* 4151 */           value.append(' ');
/*      */         }
/*      */       } 
/* 4154 */       eAnnotation.getDetails().put("validationDelegates", value.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getSettingDelegates(EPackage ePackage) {
/* 4163 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4164 */     if (eAnnotation != null) {
/*      */       
/* 4166 */       String settingDelegates = (String)eAnnotation.getDetails().get("settingDelegates");
/* 4167 */       if (settingDelegates != null) {
/*      */         
/* 4169 */         List<String> result = new ArrayList<String>();
/* 4170 */         for (StringTokenizer stringTokenizer = new StringTokenizer(settingDelegates); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 4172 */           String settingDelegate = stringTokenizer.nextToken();
/* 4173 */           result.add(settingDelegate);
/*      */         } 
/* 4175 */         return result;
/*      */       } 
/*      */     } 
/* 4178 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSettingDelegates(EPackage ePackage, List<String> settingDelegates) {
/* 4186 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4187 */     if (settingDelegates == null || settingDelegates.isEmpty()) {
/*      */       
/* 4189 */       if (eAnnotation != null)
/*      */       {
/* 4191 */         eAnnotation.getDetails().remove("settingDelegates");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 4196 */       if (eAnnotation == null) {
/*      */         
/* 4198 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 4199 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/Ecore");
/* 4200 */         ePackage.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 4202 */       StringBuffer value = new StringBuffer();
/* 4203 */       for (Iterator<String> i = settingDelegates.iterator(); i.hasNext(); ) {
/*      */         
/* 4205 */         value.append(i.next());
/* 4206 */         if (i.hasNext())
/*      */         {
/* 4208 */           value.append(' ');
/*      */         }
/*      */       } 
/* 4211 */       eAnnotation.getDetails().put("settingDelegates", value.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EStructuralFeature.Internal.SettingDelegate.Factory getSettingDelegateFactory(EStructuralFeature eStructuralFeature) {
/* 4220 */     for (String settingDelegate : getSettingDelegates(eStructuralFeature.getEContainingClass().getEPackage())) {
/*      */       
/* 4222 */       if (eStructuralFeature.getEAnnotation(settingDelegate) != null)
/* 4223 */         return EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.getFactory(settingDelegate); 
/*      */     } 
/* 4225 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getInvocationDelegates(EPackage ePackage) {
/* 4233 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4234 */     if (eAnnotation != null) {
/*      */       
/* 4236 */       String invocationDelegates = (String)eAnnotation.getDetails().get("invocationDelegates");
/* 4237 */       if (invocationDelegates != null) {
/*      */         
/* 4239 */         List<String> result = new ArrayList<String>();
/* 4240 */         for (StringTokenizer stringTokenizer = new StringTokenizer(invocationDelegates); stringTokenizer.hasMoreTokens(); ) {
/*      */           
/* 4242 */           String invocationDelegate = stringTokenizer.nextToken();
/* 4243 */           result.add(invocationDelegate);
/*      */         } 
/* 4245 */         return result;
/*      */       } 
/*      */     } 
/* 4248 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setInvocationDelegates(EPackage ePackage, List<String> invocationDelegates) {
/* 4256 */     EAnnotation eAnnotation = ePackage.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
/* 4257 */     if (invocationDelegates == null || invocationDelegates.isEmpty()) {
/*      */       
/* 4259 */       if (eAnnotation != null)
/*      */       {
/* 4261 */         eAnnotation.getDetails().remove("invocationDelegates");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 4266 */       if (eAnnotation == null) {
/*      */         
/* 4268 */         eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 4269 */         eAnnotation.setSource("http://www.eclipse.org/emf/2002/Ecore");
/* 4270 */         ePackage.getEAnnotations().add(eAnnotation);
/*      */       } 
/* 4272 */       StringBuffer value = new StringBuffer();
/* 4273 */       for (Iterator<String> i = invocationDelegates.iterator(); i.hasNext(); ) {
/*      */         
/* 4275 */         value.append(i.next());
/* 4276 */         if (i.hasNext())
/*      */         {
/* 4278 */           value.append(' ');
/*      */         }
/*      */       } 
/* 4281 */       eAnnotation.getDetails().put("invocationDelegates", value.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EOperation.Internal.InvocationDelegate.Factory getInvocationDelegateFactory(EOperation eOperation) {
/* 4290 */     for (String invocationDelegate : getInvocationDelegates(eOperation.getEContainingClass().getEPackage())) {
/*      */       
/* 4292 */       if (eOperation.getEAnnotation(invocationDelegate) != null)
/* 4293 */         return EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.getFactory(invocationDelegate); 
/*      */     } 
/* 4295 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */