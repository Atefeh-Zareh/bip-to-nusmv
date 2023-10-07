package ujf.verimag.bip.Core.ActionLanguage.Expressions;

public interface FieldNavigationExpression extends DataNavigationExpression {
  boolean isIsOnRef();
  
  void setIsOnRef(boolean paramBoolean);
  
  String getFieldName();
  
  void setFieldName(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\FieldNavigationExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */