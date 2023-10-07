package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EClass extends EClassifier {
  boolean isAbstract();
  
  void setAbstract(boolean paramBoolean);
  
  boolean isInterface();
  
  void setInterface(boolean paramBoolean);
  
  EList<EClass> getESuperTypes();
  
  EList<EClass> getEAllSuperTypes();
  
  EAttribute getEIDAttribute();
  
  EList<EStructuralFeature> getEStructuralFeatures();
  
  EList<EGenericType> getEGenericSuperTypes();
  
  EList<EGenericType> getEAllGenericSuperTypes();
  
  EList<EAttribute> getEAttributes();
  
  EList<EAttribute> getEAllAttributes();
  
  EList<EReference> getEReferences();
  
  EList<EReference> getEAllReferences();
  
  EList<EReference> getEAllContainments();
  
  EList<EStructuralFeature> getEAllStructuralFeatures();
  
  EList<EOperation> getEOperations();
  
  EList<EOperation> getEAllOperations();
  
  boolean isSuperTypeOf(EClass paramEClass);
  
  int getFeatureCount();
  
  EStructuralFeature getEStructuralFeature(int paramInt);
  
  EStructuralFeature getEStructuralFeature(String paramString);
  
  int getOperationCount();
  
  EOperation getEOperation(int paramInt);
  
  int getOperationID(EOperation paramEOperation);
  
  EOperation getOverride(EOperation paramEOperation);
  
  int getFeatureID(EStructuralFeature paramEStructuralFeature);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */