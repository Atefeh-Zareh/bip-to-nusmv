package ujf.verimag.bip.cmodel;

public interface CTypeConvertion extends CExpression, CTypedElement {
  CExpression getConvertedExpression();
  
  void setConvertedExpression(CExpression paramCExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CTypeConvertion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */