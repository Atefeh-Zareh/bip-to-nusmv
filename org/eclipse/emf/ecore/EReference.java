package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EReference extends EStructuralFeature {
  boolean isContainment();
  
  void setContainment(boolean paramBoolean);
  
  boolean isContainer();
  
  boolean isResolveProxies();
  
  void setResolveProxies(boolean paramBoolean);
  
  EReference getEOpposite();
  
  void setEOpposite(EReference paramEReference);
  
  EClass getEReferenceType();
  
  EList<EAttribute> getEKeys();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EReference.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */