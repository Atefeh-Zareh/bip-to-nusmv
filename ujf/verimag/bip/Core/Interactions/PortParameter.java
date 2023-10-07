package ujf.verimag.bip.Core.Interactions;

import ujf.verimag.bip.Core.Behaviors.NamedElement;
import ujf.verimag.bip.Core.Behaviors.PortType;

public interface PortParameter extends NamedElement {
  PortType getType();
  
  void setType(PortType paramPortType);
  
  ConnectorType getConnectorType();
  
  void setConnectorType(ConnectorType paramConnectorType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\PortParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */