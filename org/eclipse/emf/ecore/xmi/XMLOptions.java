package org.eclipse.emf.ecore.xmi;

import java.util.Map;
import org.eclipse.emf.common.util.URI;

public interface XMLOptions {
  void setProcessAnyXML(boolean paramBoolean);
  
  boolean isProcessAnyXML();
  
  void setEcoreBuilder(EcoreBuilder paramEcoreBuilder);
  
  EcoreBuilder getEcoreBuilder();
  
  void setExternalSchemaLocations(Map<String, URI> paramMap);
  
  Map<String, URI> getExternalSchemaLocations();
  
  void setProcessSchemaLocations(boolean paramBoolean);
  
  boolean isProcessSchemaLocations();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLOptions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */