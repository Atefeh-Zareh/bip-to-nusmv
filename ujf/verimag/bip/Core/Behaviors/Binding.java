package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.ecore.EObject;

public interface Binding extends EObject {
  Port getOuterPort();
  
  void setOuterPort(Port paramPort);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\Binding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */