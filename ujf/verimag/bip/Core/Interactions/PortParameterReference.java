package ujf.verimag.bip.Core.Interactions;

import ujf.verimag.bip.Core.PortExpressions.PortReference;

public interface PortParameterReference extends PortReference {
  PortParameter getTarget();
  
  void setTarget(PortParameter paramPortParameter);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\PortParameterReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */