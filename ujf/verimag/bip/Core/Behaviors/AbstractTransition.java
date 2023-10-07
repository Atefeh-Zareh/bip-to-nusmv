package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.PortExpressions.PortExpression;
import ujf.verimag.bip.Extra.Time.TimeReset;
import ujf.verimag.bip.Extra.Time.TimeSpecification;

public interface AbstractTransition extends NamedElement {
  EList<State> getOrigin();
  
  Expression getGuard();
  
  void setGuard(Expression paramExpression);
  
  Action getAction();
  
  void setAction(Action paramAction);
  
  PortExpression getTrigger();
  
  void setTrigger(PortExpression paramPortExpression);
  
  TimeReset getTimeReset();
  
  void setTimeReset(TimeReset paramTimeReset);
  
  TimeSpecification getTimeSpecification();
  
  void setTimeSpecification(TimeSpecification paramTimeSpecification);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\AbstractTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */