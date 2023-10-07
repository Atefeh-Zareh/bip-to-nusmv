package org.eclipse.emf.ecore.xml.type;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

public interface XMLTypeDocumentRoot extends EObject {
  FeatureMap getMixed();
  
  EMap<String, String> getXMLNSPrefixMap();
  
  EMap<String, String> getXSISchemaLocation();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\XMLTypeDocumentRoot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */