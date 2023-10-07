package ujf.verimag.bip.cmodel;

public interface CAssignStm extends CStm {
  CExpression getSource();
  
  void setSource(CExpression paramCExpression);
  
  CExpression getTarget();
  
  void setTarget(CExpression paramCExpression);
  
  CAssignType getType();
  
  void setType(CAssignType paramCAssignType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CAssignStm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */