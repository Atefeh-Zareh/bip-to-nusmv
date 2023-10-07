package ujf.verimag.bip.Core.Behaviors;

public interface DataParameter extends DataTypedElement {
  ParameterDirectionKind getDirection();
  
  void setDirection(ParameterDirectionKind paramParameterDirectionKind);
  
  ParameterizedElement getParameterizedElement();
  
  void setParameterizedElement(ParameterizedElement paramParameterizedElement);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\DataParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */