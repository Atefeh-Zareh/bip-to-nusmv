package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EEnum extends EDataType {
  EList<EEnumLiteral> getELiterals();
  
  EEnumLiteral getEEnumLiteral(String paramString);
  
  EEnumLiteral getEEnumLiteral(int paramInt);
  
  EEnumLiteral getEEnumLiteralByLiteral(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EEnum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */