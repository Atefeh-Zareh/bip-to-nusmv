package ujf.verimag.bip.Core.Interactions;

import ujf.verimag.bip.Core.Behaviors.Expression;

public interface ConditionalActualPortParameter extends ActualPortParameter {
  Expression getExpression();
  
  void setExpression(Expression paramExpression);
  
  ActualPortParameter getTrueCase();
  
  void setTrueCase(ActualPortParameter paramActualPortParameter);
  
  ActualPortParameter getFalseCase();
  
  void setFalseCase(ActualPortParameter paramActualPortParameter);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\ConditionalActualPortParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */