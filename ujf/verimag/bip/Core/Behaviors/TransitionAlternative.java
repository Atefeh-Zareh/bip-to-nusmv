package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface TransitionAlternative extends EObject {
  Expression getCondition();
  
  void setCondition(Expression paramExpression);
  
  EList<State> getState();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\TransitionAlternative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */