package ujf.verimag.bip.Core.PortExpressions;

import org.eclipse.emf.common.util.EList;

public interface AINaryExpression extends AIExpression {
  EList<AIExpression> getOperand();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\AINaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */