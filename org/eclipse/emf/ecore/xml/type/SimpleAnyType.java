package org.eclipse.emf.ecore.xml.type;

import org.eclipse.emf.ecore.EDataType;

public interface SimpleAnyType extends AnyType {
  String getRawValue();
  
  void setRawValue(String paramString);
  
  Object getValue();
  
  void setValue(Object paramObject);
  
  EDataType getInstanceType();
  
  void setInstanceType(EDataType paramEDataType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\SimpleAnyType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */