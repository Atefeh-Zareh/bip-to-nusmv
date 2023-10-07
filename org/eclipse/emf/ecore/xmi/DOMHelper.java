package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Node;

public interface DOMHelper {
  EObject getContainer(Node paramNode);
  
  EStructuralFeature getEStructuralFeature(Node paramNode);
  
  Object getValue(Node paramNode);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\DOMHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */