package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.State;

public interface StateReference extends Expression {
  State getTargetState();
  
  void setTargetState(State paramState);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\StateReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */