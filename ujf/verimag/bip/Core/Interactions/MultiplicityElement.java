package ujf.verimag.bip.Core.Interactions;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.NamedElement;

public interface MultiplicityElement extends NamedElement {
  EList<Expression> getMultiplicitySpecification();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\MultiplicityElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */