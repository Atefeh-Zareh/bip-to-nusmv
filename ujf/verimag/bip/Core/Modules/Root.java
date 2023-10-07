package ujf.verimag.bip.Core.Modules;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.ComponentType;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.NamedElement;

public interface Root extends NamedElement {
  ComponentType getType();
  
  void setType(ComponentType paramComponentType);
  
  EList<Expression> getActualData();
  
  System getSystem();
  
  void setSystem(System paramSystem);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\Root.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */