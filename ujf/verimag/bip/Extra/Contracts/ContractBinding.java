package ujf.verimag.bip.Extra.Contracts;

import ujf.verimag.bip.Core.Behaviors.Port;
import ujf.verimag.bip.Core.Interactions.ExportBinding;

public interface ContractBinding extends ExportBinding {
  Port getContractedPort();
  
  void setContractedPort(Port paramPort);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\ContractBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */