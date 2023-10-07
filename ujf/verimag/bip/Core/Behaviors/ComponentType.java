package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Priorities.PriorityRule;
import ujf.verimag.bip.Extra.Contracts.Contract;

public interface ComponentType extends PartType {
  EList<Port> getPort();
  
  EList<PriorityRule> getPriorityRule();
  
  EList<InterfaceVariable> getInterfaceVariable();
  
  EList<Contract> getContract();
  
  boolean isIsMultishot();
  
  void setIsMultishot(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\ComponentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */