package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.common.util.EList;

public interface Connector extends Part {
  EList<ActualPortParameter> getActualPort();
  
  ConnectorType getType();
  
  void setType(ConnectorType paramConnectorType);
  
  CompoundType getCompoundType();
  
  void setCompoundType(CompoundType paramCompoundType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\Connector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */