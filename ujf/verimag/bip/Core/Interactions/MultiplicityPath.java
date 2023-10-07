package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import ujf.verimag.bip.Core.Behaviors.Expression;

public interface MultiplicityPath extends EObject {
  EList<Expression> getIndex();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\MultiplicityPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */