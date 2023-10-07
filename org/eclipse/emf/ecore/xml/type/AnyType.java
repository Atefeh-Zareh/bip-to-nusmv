package org.eclipse.emf.ecore.xml.type;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

public interface AnyType extends EObject {
  FeatureMap getMixed();
  
  FeatureMap getAny();
  
  FeatureMap getAnyAttribute();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\AnyType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */