package org.eclipse.emf.ecore;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.Resource;

public interface EObject extends Notifier {
  EClass eClass();
  
  Resource eResource();
  
  EObject eContainer();
  
  EStructuralFeature eContainingFeature();
  
  EReference eContainmentFeature();
  
  EList<EObject> eContents();
  
  TreeIterator<EObject> eAllContents();
  
  boolean eIsProxy();
  
  EList<EObject> eCrossReferences();
  
  Object eGet(EStructuralFeature paramEStructuralFeature);
  
  Object eGet(EStructuralFeature paramEStructuralFeature, boolean paramBoolean);
  
  void eSet(EStructuralFeature paramEStructuralFeature, Object paramObject);
  
  boolean eIsSet(EStructuralFeature paramEStructuralFeature);
  
  void eUnset(EStructuralFeature paramEStructuralFeature);
  
  Object eInvoke(EOperation paramEOperation, EList<?> paramEList) throws InvocationTargetException;
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */