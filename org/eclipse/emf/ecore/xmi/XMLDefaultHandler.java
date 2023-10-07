package org.eclipse.emf.ecore.xmi;

import java.util.Map;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ext.LexicalHandler;

public interface XMLDefaultHandler extends ContentHandler, EntityResolver, DTDHandler, ErrorHandler, LexicalHandler {
  void reset();
  
  void prepare(XMLResource paramXMLResource, XMLHelper paramXMLHelper, Map<?, ?> paramMap);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLDefaultHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */