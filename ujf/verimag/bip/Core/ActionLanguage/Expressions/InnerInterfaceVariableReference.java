package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
import ujf.verimag.bip.Core.Interactions.PartElementReference;

public interface InnerInterfaceVariableReference extends DataReference {
  PartElementReference getPartElementReference();
  
  void setPartElementReference(PartElementReference paramPartElementReference);
  
  InterfaceVariable getTargetInterfaceVariable();
  
  void setTargetInterfaceVariable(InterfaceVariable paramInterfaceVariable);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\InnerInterfaceVariableReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */