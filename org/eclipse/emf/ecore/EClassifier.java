package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EClassifier extends ENamedElement {
  String getInstanceClassName();
  
  void setInstanceClassName(String paramString);
  
  Class<?> getInstanceClass();
  
  void setInstanceClass(Class<?> paramClass);
  
  Object getDefaultValue();
  
  String getInstanceTypeName();
  
  void setInstanceTypeName(String paramString);
  
  EPackage getEPackage();
  
  EList<ETypeParameter> getETypeParameters();
  
  boolean isInstance(Object paramObject);
  
  int getClassifierID();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EClassifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */