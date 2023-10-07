package ujf.verimag.bip.cmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface CModule extends EObject {
  EList<CInclude> getCImport();
  
  EList<CItem> getContent();
  
  String getNamespace();
  
  void setNamespace(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */