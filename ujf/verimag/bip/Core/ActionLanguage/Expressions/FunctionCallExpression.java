package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface FunctionCallExpression extends Expression {
  String getFunctionName();
  
  void setFunctionName(String paramString);
  
  boolean isIsOnRef();
  
  void setIsOnRef(boolean paramBoolean);
  
  EList<Expression> getActualData();
  
  Expression getNavigated();
  
  void setNavigated(Expression paramExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\FunctionCallExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */