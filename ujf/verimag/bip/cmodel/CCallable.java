package ujf.verimag.bip.cmodel;

import org.eclipse.emf.common.util.EList;

public interface CCallable extends CItem, CTypedElement, CBlock {
  EList<CArgument> getArgument();
  
  String getSpecifier();
  
  void setSpecifier(String paramString);
  
  String getQualifier();
  
  void setQualifier(String paramString);
  
  boolean isBodyInDecl();
  
  void setBodyInDecl(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CCallable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */