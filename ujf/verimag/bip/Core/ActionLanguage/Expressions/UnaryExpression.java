package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.Expression;

public interface UnaryExpression extends Expression {
  UnaryOperator getOperator();
  
  void setOperator(UnaryOperator paramUnaryOperator);
  
  Expression getOperand();
  
  void setOperand(Expression paramExpression);
  
  boolean isPostfix();
  
  void setPostfix(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\UnaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */