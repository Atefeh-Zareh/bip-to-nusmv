package ujf.verimag.bip.Core.Interactions;

public interface PartElementReference extends MultiplicityPath {
  InnerPortSpecification getExportBinding();
  
  void setExportBinding(InnerPortSpecification paramInnerPortSpecification);
  
  Part getTargetPart();
  
  void setTargetPart(Part paramPart);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\PartElementReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */