package ujf.verimag.bip.Core.Priorities;

import ujf.verimag.bip.Core.Interactions.ConnectorType;

public interface ConnectorTypeReference extends PriorityElement {
  ConnectorType getTarget();
  
  void setTarget(ConnectorType paramConnectorType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\ConnectorTypeReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */