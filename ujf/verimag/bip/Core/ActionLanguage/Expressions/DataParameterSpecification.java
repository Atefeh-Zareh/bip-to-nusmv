package ujf.verimag.bip.Core.ActionLanguage.Expressions;

import ujf.verimag.bip.Core.Behaviors.DataParameter;

public interface DataParameterSpecification extends DataReference {
  DataParameter getTargetParameter();
  
  void setTargetParameter(DataParameter paramDataParameter);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\DataParameterSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */