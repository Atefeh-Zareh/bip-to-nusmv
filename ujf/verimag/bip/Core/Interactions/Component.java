package ujf.verimag.bip.Core.Interactions;

import ujf.verimag.bip.Core.Behaviors.ComponentType;

public interface Component extends Part {
  CompoundType getCompoundType();
  
  void setCompoundType(CompoundType paramCompoundType);
  
  ComponentType getType();
  
  void setType(ComponentType paramComponentType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\Component.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */