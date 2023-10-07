package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.Expression;

public interface BinaryExpression extends Expression {
  BinaryOperator getOperator();
  
  void setOperator(BinaryOperator paramBinaryOperator);
  
  Expression getLeftOperand();
  
  void setLeftOperand(Expression paramExpression);
  
  Expression getRightOperand();
  
  void setRightOperand(Expression paramExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\BinaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */