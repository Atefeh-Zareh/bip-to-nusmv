package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.Expression;

public interface ArrayNavigationExpression extends DataNavigationExpression {
  Expression getIndex();
  
  void setIndex(Expression paramExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\ArrayNavigationExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */