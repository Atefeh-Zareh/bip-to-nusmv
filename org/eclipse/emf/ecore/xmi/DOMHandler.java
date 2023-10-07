package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Node;

public interface DOMHandler {
  void recordValues(Node paramNode, EObject paramEObject, EStructuralFeature paramEStructuralFeature, Object paramObject);
  
  DOMHelper getDOMHelper();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\DOMHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */