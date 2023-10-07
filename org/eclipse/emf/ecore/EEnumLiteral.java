package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.Enumerator;

public interface EEnumLiteral extends ENamedElement, Enumerator {
  int getValue();
  
  void setValue(int paramInt);
  
  Enumerator getInstance();
  
  void setInstance(Enumerator paramEnumerator);
  
  String getLiteral();
  
  void setLiteral(String paramString);
  
  EEnum getEEnum();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EEnumLiteral.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */