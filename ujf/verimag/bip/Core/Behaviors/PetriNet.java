package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;

public interface PetriNet extends Behavior {
  EList<State> getState();
  
  EList<Transition> getTransition();
  
  EList<State> getInitialState();
  
  Action getInitialization();
  
  void setInitialization(Action paramAction);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\PetriNet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */