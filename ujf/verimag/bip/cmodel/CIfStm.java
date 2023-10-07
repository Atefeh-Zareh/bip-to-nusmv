package ujf.verimag.bip.cmodel;

public interface CIfStm extends CConditionalStm {
  CStm getIfCase();
  
  void setIfCase(CStm paramCStm);
  
  CStm getElseCase();
  
  void setElseCase(CStm paramCStm);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CIfStm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */