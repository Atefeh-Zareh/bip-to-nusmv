package ujf.verimag.bip.Core.Behaviors;

import ujf.verimag.bip.Core.Modules.Module;
import ujf.verimag.bip.Extra.Traceability.TraceableElement;

public interface BipType extends NamedElement, ParameterizedElement, TraceableElement {
  Module getModule();
  
  void setModule(Module paramModule);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\BipType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */