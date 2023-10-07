package ujf.verimag.bip.Core.ActionLanguage.Actions;

import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface AssignmentAction extends Action {
  DataReference getAssignedTarget();
  
  void setAssignedTarget(DataReference paramDataReference);
  
  Expression getAssignedValue();
  
  void setAssignedValue(Expression paramExpression);
  
  AssignType getType();
  
  void setType(AssignType paramAssignType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\AssignmentAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */