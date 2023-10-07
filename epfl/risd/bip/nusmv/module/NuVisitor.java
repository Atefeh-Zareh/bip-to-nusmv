package epfl.risd.bip.nusmv.module;

import java.util.Collection;
import ujf.verimag.bip.Core.Interactions.CompoundType;

public interface NuVisitor {
  void execute(CompoundType paramCompoundType, String paramString);
  
  Collection<NuModule> getModules();
  
  NuModule getMain();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\module\NuVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */