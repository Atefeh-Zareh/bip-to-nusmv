package ujf.verimag.bip.Extra.Time;

import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface TimedConstraint extends EObject {
  VariableReference getClock();
  
  void setClock(VariableReference paramVariableReference);
  
  Expression getLowBound();
  
  void setLowBound(Expression paramExpression);
  
  Expression getHighBound();
  
  void setHighBound(Expression paramExpression);
  
  TimeSpecification getTimeSpecification();
  
  void setTimeSpecification(TimeSpecification paramTimeSpecification);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\TimedConstraint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */