package ujf.verimag.bip.cmodel;

public interface CConditionalStm extends CStm {
  CExpression getCondition();
  
  void setCondition(CExpression paramCExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CConditionalStm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */