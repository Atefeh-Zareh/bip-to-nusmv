package ujf.verimag.bip.Extra.Contracts;

import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.State;

public interface ContractState extends State {
  boolean isIsAccepting();
  
  void setIsAccepting(boolean paramBoolean);
  
  Expression getInvariant();
  
  void setInvariant(Expression paramExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\ContractState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */