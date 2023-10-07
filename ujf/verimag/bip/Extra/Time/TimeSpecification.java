package ujf.verimag.bip.Extra.Time;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.Behaviors.AbstractTransition;

public interface TimeSpecification extends EObject {
  UrgencyKind getUrgency();
  
  void setUrgency(UrgencyKind paramUrgencyKind);
  
  AbstractTransition getTransition();
  
  void setTransition(AbstractTransition paramAbstractTransition);
  
  EList<TimedConstraint> getTimedConstraint();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\TimeSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */