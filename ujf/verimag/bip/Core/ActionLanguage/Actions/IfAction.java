package ujf.verimag.bip.Core.ActionLanguage.Actions;

import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface IfAction extends Action {
  Action getIfCase();
  
  void setIfCase(Action paramAction);
  
  Action getElseCase();
  
  void setElseCase(Action paramAction);
  
  Expression getCondition();
  
  void setCondition(Expression paramExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\IfAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */