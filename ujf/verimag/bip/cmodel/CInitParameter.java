package ujf.verimag.bip.cmodel;

import org.eclipse.emf.ecore.EObject;

public interface CInitParameter extends EObject {
  String getFieldName();
  
  void setFieldName(String paramString);
  
  CExpression getValue();
  
  void setValue(CExpression paramCExpression);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CInitParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */