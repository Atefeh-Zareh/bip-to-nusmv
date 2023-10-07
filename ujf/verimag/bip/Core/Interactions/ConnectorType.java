package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.PartType;
import ujf.verimag.bip.Core.Behaviors.Port;
import ujf.verimag.bip.Core.Behaviors.PortDefinition;
import ujf.verimag.bip.Core.Behaviors.Variable;
import ujf.verimag.bip.Core.PortExpressions.PortExpression;

public interface ConnectorType extends PartType {
  Port getPort();
  
  void setPort(Port paramPort);
  
  EList<InteractionSpecification> getInteractionSpecification();
  
  EList<PortParameter> getPortParameter();
  
  PortDefinition getPortDefinition();
  
  void setPortDefinition(PortDefinition paramPortDefinition);
  
  PortExpression getDefinition();
  
  void setDefinition(PortExpression paramPortExpression);
  
  EList<Variable> getVariable();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\ConnectorType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */