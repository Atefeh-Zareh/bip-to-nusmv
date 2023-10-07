package ujf.verimag.bip.cmodel;

public interface CData extends CTypedElement, CItem, CBodyItem {
  String getName();
  
  void setName(String paramString);
  
  CExpression getInitialValue();
  
  void setInitialValue(CExpression paramCExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */