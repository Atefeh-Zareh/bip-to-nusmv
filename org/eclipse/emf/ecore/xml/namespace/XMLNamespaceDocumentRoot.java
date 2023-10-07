package org.eclipse.emf.ecore.xml.namespace;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

public interface XMLNamespaceDocumentRoot extends EObject {
  FeatureMap getMixed();
  
  EMap<String, String> getXMLNSPrefixMap();
  
  EMap<String, String> getXSISchemaLocation();
  
  String getBase();
  
  void setBase(String paramString);
  
  String getId();
  
  void setId(String paramString);
  
  String getLang();
  
  void setLang(String paramString);
  
  SpaceType getSpace();
  
  void setSpace(SpaceType paramSpaceType);
  
  void unsetSpace();
  
  boolean isSetSpace();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\XMLNamespaceDocumentRoot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */