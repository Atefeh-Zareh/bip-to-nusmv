package ujf.verimag.bip.cmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface CInitialization extends EObject {
  String getField();
  
  void setField(String paramString);
  
  EList<CInitParameter> getParameter();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CInitialization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */