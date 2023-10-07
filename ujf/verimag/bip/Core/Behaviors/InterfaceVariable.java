package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;

public interface InterfaceVariable extends DataTypedElement {
  ComponentType getComponentType();
  
  void setComponentType(ComponentType paramComponentType);
  
  EList<VariableBinding> getVariableBinding();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\InterfaceVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */