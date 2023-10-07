package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;

public interface AtomType extends ComponentType {
  Behavior getBehavior();
  
  void setBehavior(Behavior paramBehavior);
  
  EList<Variable> getVariable();
  
  EList<PortDefinition> getPortDefinition();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\AtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */