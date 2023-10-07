package ujf.verimag.bip.Extra.Contracts;

import ujf.verimag.bip.Core.Behaviors.ComponentType;
import ujf.verimag.bip.Core.Interactions.Component;
import ujf.verimag.bip.Core.Interactions.CompoundType;

public interface Contract extends CompoundType {
  Component getPromise();
  
  void setPromise(Component paramComponent);
  
  Component getAssume();
  
  void setAssume(Component paramComponent);
  
  ComponentType getContracted();
  
  void setContracted(ComponentType paramComponentType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\Contract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */