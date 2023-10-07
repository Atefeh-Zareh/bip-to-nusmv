package ujf.verimag.bip.cmodel;

import org.eclipse.emf.common.util.EList;

public interface CClass extends CTypedElement, CItem {
  EList<CItem> getContent();
  
  String getName();
  
  void setName(String paramString);
  
  EList<String> getSuperClasses();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */