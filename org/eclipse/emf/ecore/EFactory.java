package org.eclipse.emf.ecore;

public interface EFactory extends EModelElement {
  EPackage getEPackage();
  
  void setEPackage(EPackage paramEPackage);
  
  EObject create(EClass paramEClass);
  
  Object createFromString(EDataType paramEDataType, String paramString);
  
  String convertToString(EDataType paramEDataType, Object paramObject);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */