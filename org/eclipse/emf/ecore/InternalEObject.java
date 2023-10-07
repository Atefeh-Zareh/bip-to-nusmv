package org.eclipse.emf.ecore;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public interface InternalEObject extends EObject {
  public static final int EOPPOSITE_FEATURE_BASE = -1;
  
  boolean eNotificationRequired();
  
  String eURIFragmentSegment(EStructuralFeature paramEStructuralFeature, EObject paramEObject);
  
  EObject eObjectForURIFragmentSegment(String paramString);
  
  void eSetClass(EClass paramEClass);
  
  EStructuralFeature.Setting eSetting(EStructuralFeature paramEStructuralFeature);
  
  int eBaseStructuralFeatureID(int paramInt, Class<?> paramClass);
  
  int eContainerFeatureID();
  
  int eDerivedStructuralFeatureID(int paramInt, Class<?> paramClass);
  
  int eDerivedOperationID(int paramInt, Class<?> paramClass);
  
  NotificationChain eSetResource(Resource.Internal paramInternal, NotificationChain paramNotificationChain);
  
  NotificationChain eInverseAdd(InternalEObject paramInternalEObject, int paramInt, Class<?> paramClass, NotificationChain paramNotificationChain);
  
  NotificationChain eInverseRemove(InternalEObject paramInternalEObject, int paramInt, Class<?> paramClass, NotificationChain paramNotificationChain);
  
  NotificationChain eBasicSetContainer(InternalEObject paramInternalEObject, int paramInt, NotificationChain paramNotificationChain);
  
  NotificationChain eBasicRemoveFromContainer(NotificationChain paramNotificationChain);
  
  URI eProxyURI();
  
  void eSetProxyURI(URI paramURI);
  
  EObject eResolveProxy(InternalEObject paramInternalEObject);
  
  InternalEObject eInternalContainer();
  
  Resource.Internal eInternalResource();
  
  Resource.Internal eDirectResource();
  
  EStore eStore();
  
  void eSetStore(EStore paramEStore);
  
  Object eGet(EStructuralFeature paramEStructuralFeature, boolean paramBoolean1, boolean paramBoolean2);
  
  Object eGet(int paramInt, boolean paramBoolean1, boolean paramBoolean2);
  
  void eSet(int paramInt, Object paramObject);
  
  void eUnset(int paramInt);
  
  boolean eIsSet(int paramInt);
  
  Object eInvoke(int paramInt, EList<?> paramEList) throws InvocationTargetException;
  
  public static interface EStore {
    public static final int NO_INDEX = -1;
    
    Object get(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, int param1Int);
    
    Object set(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    boolean isSet(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    void unset(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    boolean isEmpty(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    int size(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    boolean contains(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    int indexOf(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    int lastIndexOf(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    void add(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    Object remove(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, int param1Int);
    
    Object move(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, int param1Int1, int param1Int2);
    
    void clear(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    Object[] toArray(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    <T> T[] toArray(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature, T[] param1ArrayOfT);
    
    int hashCode(InternalEObject param1InternalEObject, EStructuralFeature param1EStructuralFeature);
    
    InternalEObject getContainer(InternalEObject param1InternalEObject);
    
    EStructuralFeature getContainingFeature(InternalEObject param1InternalEObject);
    
    EObject create(EClass param1EClass);
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\InternalEObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */