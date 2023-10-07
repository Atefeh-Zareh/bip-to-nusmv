package org.eclipse.emf.ecore;

import org.eclipse.emf.common.util.EList;

public interface EModelElement extends EObject {
  EList<EAnnotation> getEAnnotations();
  
  EAnnotation getEAnnotation(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EModelElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */