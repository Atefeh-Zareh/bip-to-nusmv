package ujf.verimag.bip.Core.Interactions;

import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
import ujf.verimag.bip.Core.Behaviors.VariableBinding;

public interface VariableExportBinding extends VariableBinding {
  PartElementReference getTargetInstance();
  
  void setTargetInstance(PartElementReference paramPartElementReference);
  
  InterfaceVariable getTargetInterfaceVariable();
  
  void setTargetInterfaceVariable(InterfaceVariable paramInterfaceVariable);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\VariableExportBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */