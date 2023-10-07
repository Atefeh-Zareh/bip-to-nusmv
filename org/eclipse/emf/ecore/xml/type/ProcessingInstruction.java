package org.eclipse.emf.ecore.xml.type;

import org.eclipse.emf.ecore.EObject;

public interface ProcessingInstruction extends EObject {
  String getData();
  
  void setData(String paramString);
  
  String getTarget();
  
  void setTarget(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\ProcessingInstruction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */