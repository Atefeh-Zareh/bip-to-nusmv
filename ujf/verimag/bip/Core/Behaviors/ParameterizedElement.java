package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface ParameterizedElement extends EObject {
  EList<DataParameter> getDataParameter();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\ParameterizedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */