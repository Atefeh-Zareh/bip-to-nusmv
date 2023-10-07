package ujf.verimag.bip.Core.Modules;

import org.eclipse.emf.common.util.EList;
import ujf.verimag.bip.Core.Behaviors.BipType;
import ujf.verimag.bip.Core.Behaviors.DataType;
import ujf.verimag.bip.Core.Behaviors.NamedElement;

public interface Module extends NamedElement {
  EList<BipType> getBipType();
  
  EList<Package> getUsedPackage();
  
  EList<Declaration> getDeclaration();
  
  EList<DataType> getDataType();
  
  String getSrcFileName();
  
  void setSrcFileName(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\Module.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */