package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.ecore.EObject;

public interface NamedElement extends EObject {
  String getName();
  
  void setName(String paramString);
  
  String getScope();
  
  void setScope(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\NamedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */