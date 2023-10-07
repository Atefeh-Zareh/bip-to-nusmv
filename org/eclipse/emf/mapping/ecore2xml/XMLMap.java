package org.eclipse.emf.mapping.ecore2xml;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;

public interface XMLMap extends EObject, XMLResource.XMLMap {
  String getIDAttributeName();
  
  void setIDAttributeName(String paramString);
  
  EMap<ENamedElement, XMLInfo> getEcoreToXMLInfo();
  
  EPackage getNoNamespacePackage();
  
  void setNoNamespacePackage(EPackage paramEPackage);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\XMLMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */