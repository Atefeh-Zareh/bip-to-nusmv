package ujf.verimag.bip.Core.Behaviors;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Modules.Declaration;

public interface PartType extends BipType {
  EList<Constant> getConstant();
  
  EList<Declaration> getDeclaration();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\PartType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */