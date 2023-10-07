package ujf.verimag.bip.Core.Behaviors;

import ujf.verimag.bip.Core.Interactions.ConnectorType;

public interface Port extends NamedElement {
  ComponentType getComponentType();
  
  void setComponentType(ComponentType paramComponentType);
  
  Binding getBinding();
  
  void setBinding(Binding paramBinding);
  
  PortType getType();
  
  void setType(PortType paramPortType);
  
  ConnectorType getConnectorType();
  
  void setConnectorType(ConnectorType paramConnectorType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\Port.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */