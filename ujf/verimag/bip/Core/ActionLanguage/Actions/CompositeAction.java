package ujf.verimag.bip.Core.ActionLanguage.Actions;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.Action;

public interface CompositeAction extends Action {
  EList<Action> getContent();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\CompositeAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */