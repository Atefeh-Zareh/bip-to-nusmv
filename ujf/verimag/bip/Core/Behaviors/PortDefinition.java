package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Interactions.ConnectorType;

public interface PortDefinition extends NamedElement {
  AtomType getAtomType();
  
  void setAtomType(AtomType paramAtomType);
  
  ConnectorType getConnectorType();
  
  void setConnectorType(ConnectorType paramConnectorType);
  
  PortType getType();
  
  void setType(PortType paramPortType);
  
  EList<Variable> getExposedVariable();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\PortDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */