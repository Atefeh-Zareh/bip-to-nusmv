package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.Variable;

public interface VariableReference extends DataReference {
  Variable getTargetVariable();
  
  void setTargetVariable(Variable paramVariable);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\VariableReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */