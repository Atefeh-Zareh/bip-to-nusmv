package ujf.verimag.bip.cmodel;

public interface CConditionalExpression extends CExpression, CConditionalStm {
  CExpression getTrueCase();
  
  void setTrueCase(CExpression paramCExpression);
  
  CExpression getFalseCase();
  
  void setFalseCase(CExpression paramCExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CConditionalExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */