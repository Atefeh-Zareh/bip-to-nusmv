package ujf.verimag.bip.Core.Behaviors;

import ujf.verimag.bip.Core.PortExpressions.PortReference;

public interface PortDefinitionReference extends PortReference {
  PortDefinition getTarget();
  
  void setTarget(PortDefinition paramPortDefinition);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\PortDefinitionReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */