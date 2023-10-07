package org.eclipse.emf.ecore.xmi;

import java.util.Collection;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

public interface EcoreBuilder {
  Collection<? extends Resource> generate(URI paramURI) throws Exception;
  
  Collection<? extends Resource> generate(Collection<URI> paramCollection) throws Exception;
  
  Collection<? extends Resource> generate(Map<String, URI> paramMap) throws Exception;
  
  void setExtendedMetaData(ExtendedMetaData paramExtendedMetaData);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\EcoreBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */