package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.PortExpressions.PortReference;
import ujf.verimag.bip.Core.Priorities.PriorityElement;

public interface Interaction extends PriorityElement {
  EList<PortReference> getPort();
  
  PartElementReference getConnector();
  
  void setConnector(PartElementReference paramPartElementReference);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\Interaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */