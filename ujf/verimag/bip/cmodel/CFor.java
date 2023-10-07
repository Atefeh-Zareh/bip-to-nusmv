package ujf.verimag.bip.cmodel;

public interface CFor extends CBlock, CStm {
  CBodyItem getInitialization();
  
  void setInitialization(CBodyItem paramCBodyItem);
  
  CExpression getCodition();
  
  void setCodition(CExpression paramCExpression);
  
  CStm getIteration();
  
  void setIteration(CStm paramCStm);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CFor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */