package org.eclipse.emf.mapping.ecore2xml;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;

public interface XMLInfo extends EObject, XMLResource.XMLInfo {
  String getName();
  
  void setName(String paramString);
  
  String getTargetNamespace();
  
  void setTargetNamespace(String paramString);
  
  int getXMLRepresentation();
  
  void setXMLRepresentation(int paramInt);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\XMLInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */