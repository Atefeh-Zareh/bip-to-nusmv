package ujf.verimag.bip.cmodel;

public interface COperation extends CExpression {
  CExpression getLeftOperand();
  
  void setLeftOperand(CExpression paramCExpression);
  
  CExpression getRightOperand();
  
  void setRightOperand(CExpression paramCExpression);
  
  String getOperator();
  
  void setOperator(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\COperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */