package ujf.verimag.bip.cmodel;

public interface CText extends CStm, CItem {
  String getCCode();
  
  void setCCode(String paramString);
  
  String getPragma();
  
  void setPragma(String paramString);
  
  boolean isInBodyFile();
  
  void setInBodyFile(boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */