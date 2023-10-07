package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EGenericType extends EObject {
  EGenericType getEUpperBound();
  
  void setEUpperBound(EGenericType paramEGenericType);
  
  EList<EGenericType> getETypeArguments();
  
  EClassifier getERawType();
  
  EGenericType getELowerBound();
  
  void setELowerBound(EGenericType paramEGenericType);
  
  ETypeParameter getETypeParameter();
  
  void setETypeParameter(ETypeParameter paramETypeParameter);
  
  EClassifier getEClassifier();
  
  void setEClassifier(EClassifier paramEClassifier);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EGenericType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */