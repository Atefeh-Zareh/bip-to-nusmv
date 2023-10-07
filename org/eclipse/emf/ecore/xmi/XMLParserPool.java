package org.eclipse.emf.ecore.xmi;

import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;

public interface XMLParserPool {
  SAXParser get(Map<String, Boolean> paramMap, Map<String, ?> paramMap1, boolean paramBoolean) throws ParserConfigurationException, SAXException;
  
  void release(SAXParser paramSAXParser, Map<String, Boolean> paramMap, Map<String, ?> paramMap1, boolean paramBoolean);
  
  XMLDefaultHandler getDefaultHandler(XMLResource paramXMLResource, XMLLoad paramXMLLoad, XMLHelper paramXMLHelper, Map<?, ?> paramMap);
  
  void releaseDefaultHandler(XMLDefaultHandler paramXMLDefaultHandler, Map<?, ?> paramMap);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLParserPool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */