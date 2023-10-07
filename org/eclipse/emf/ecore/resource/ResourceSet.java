package org.eclipse.emf.ecore.resource;

import java.util.Map;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public interface ResourceSet extends Notifier {
  public static final int RESOURCE_SET__RESOURCES = 0;
  
  EList<Resource> getResources();
  
  TreeIterator<Notifier> getAllContents();
  
  EList<AdapterFactory> getAdapterFactories();
  
  Map<Object, Object> getLoadOptions();
  
  EObject getEObject(URI paramURI, boolean paramBoolean);
  
  Resource getResource(URI paramURI, boolean paramBoolean);
  
  Resource createResource(URI paramURI);
  
  Resource createResource(URI paramURI, String paramString);
  
  Resource.Factory.Registry getResourceFactoryRegistry();
  
  void setResourceFactoryRegistry(Resource.Factory.Registry paramRegistry);
  
  URIConverter getURIConverter();
  
  void setURIConverter(URIConverter paramURIConverter);
  
  EPackage.Registry getPackageRegistry();
  
  void setPackageRegistry(EPackage.Registry paramRegistry);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\ResourceSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */