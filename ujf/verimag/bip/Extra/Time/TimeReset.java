package ujf.verimag.bip.Extra.Time;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;

public interface TimeReset extends EObject {
  EList<VariableReference> getClock();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\TimeReset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */