package ujf.verimag.bip.Core.ActionLanguage.Expressions;

public interface DataNavigationExpression extends DataReference {
  DataReference getNavigated();
  
  void setNavigated(DataReference paramDataReference);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\DataNavigationExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */