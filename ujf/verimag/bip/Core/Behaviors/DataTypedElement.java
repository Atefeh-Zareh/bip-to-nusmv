package ujf.verimag.bip.Core.Behaviors;

public interface DataTypedElement extends NamedElement {
  DataType getType();
  
  void setType(DataType paramDataType);
  
  String getOpaqueTypeName();
  
  void setOpaqueTypeName(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\DataTypedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */