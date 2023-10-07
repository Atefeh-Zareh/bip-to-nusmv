package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

public interface EAnnotation extends EModelElement {
  String getSource();
  
  void setSource(String paramString);
  
  EMap<String, String> getDetails();
  
  EModelElement getEModelElement();
  
  void setEModelElement(EModelElement paramEModelElement);
  
  EList<EObject> getContents();
  
  EList<EObject> getReferences();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EAnnotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */