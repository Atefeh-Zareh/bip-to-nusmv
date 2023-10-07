package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;

public interface MultiTransition extends AbstractTransition {
  EList<TransitionAlternative> getAlternative();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\MultiTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */