package ujf.verimag.bip.Core.Priorities;

import ujf.verimag.bip.Core.Behaviors.ComponentType;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Interactions.MultiplicityElement;

public interface PriorityRule extends MultiplicityElement {
  ComponentType getCompoundType();
  
  void setCompoundType(ComponentType paramComponentType);
  
  Expression getGuard();
  
  void setGuard(Expression paramExpression);
  
  PriorityElement getLower();
  
  void setLower(PriorityElement paramPriorityElement);
  
  PriorityElement getGreater();
  
  void setGreater(PriorityElement paramPriorityElement);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\PriorityRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */