package ujf.verimag.bip.Core.Modules;

import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.DataType;
import ujf.verimag.bip.Core.PortExpressions.PortExpression;

public interface OpaqueElement extends Action, DataReference, DataType, Declaration, PortExpression {
  String getBody();
  
  void setBody(String paramString);
  
  boolean isIsHeader();
  
  void setIsHeader(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\OpaqueElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */