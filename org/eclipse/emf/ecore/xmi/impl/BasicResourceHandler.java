package org.eclipse.emf.ecore.xmi.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class BasicResourceHandler implements XMLResource.ResourceHandler {
  public void preLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {}
  
  public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {}
  
  public void preSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {}
  
  public void postSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {}
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\BasicResourceHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */