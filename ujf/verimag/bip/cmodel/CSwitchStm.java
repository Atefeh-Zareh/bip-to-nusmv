package ujf.verimag.bip.cmodel;

import org.eclipse.emf.common.util.EList;

public interface CSwitchStm extends CStm {
  EList<CCaseItem> getCaseAction();
  
  String getSelector();
  
  void setSelector(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CSwitchStm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */