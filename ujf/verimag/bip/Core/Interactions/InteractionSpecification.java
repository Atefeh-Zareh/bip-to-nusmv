package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface InteractionSpecification extends EObject {
  ConnectorType getConnectorType();
  
  void setConnectorType(ConnectorType paramConnectorType);
  
  Interaction getInteraction();
  
  void setInteraction(Interaction paramInteraction);
  
  Expression getGuard();
  
  void setGuard(Expression paramExpression);
  
  Action getDownAction();
  
  void setDownAction(Action paramAction);
  
  Action getUpAction();
  
  void setUpAction(Action paramAction);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\InteractionSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */