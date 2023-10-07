package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.Behaviors.Port;

public interface InnerPortSpecification extends EObject {
  Port getTargetPort();
  
  void setTargetPort(Port paramPort);
  
  PartElementReference getTargetInstance();
  
  void setTargetInstance(PartElementReference paramPartElementReference);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\InnerPortSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */