package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public interface XMLLoad {
  void load(XMLResource paramXMLResource, InputStream paramInputStream, Map<?, ?> paramMap) throws IOException;
  
  void load(XMLResource paramXMLResource, InputSource paramInputSource, Map<?, ?> paramMap) throws IOException;
  
  void load(XMLResource paramXMLResource, Node paramNode, Map<?, ?> paramMap) throws IOException;
  
  XMLDefaultHandler createDefaultHandler();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLLoad.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */