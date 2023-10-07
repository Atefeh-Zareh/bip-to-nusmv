package ujf.verimag.bip.Core.Behaviors;

import ujf.verimag.bip.Core.Interactions.ConnectorType;

public interface Variable extends DataTypedElement {
  ConnectorType getConnectorType();
  
  void setConnectorType(ConnectorType paramConnectorType);
  
  Expression getInitialValue();
  
  void setInitialValue(Expression paramExpression);
  
  boolean isIsExternal();
  
  void setIsExternal(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\Variable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */