package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Document;

public interface XMLSave {
  void save(XMLResource paramXMLResource, OutputStream paramOutputStream, Map<?, ?> paramMap) throws IOException;
  
  Document save(XMLResource paramXMLResource, Document paramDocument, Map<?, ?> paramMap, DOMHandler paramDOMHandler);
  
  void save(XMLResource paramXMLResource, Writer paramWriter, Map<?, ?> paramMap) throws IOException;
  
  public static interface XMLTypeInfo {
    boolean shouldSaveType(EClass param1EClass, EClassifier param1EClassifier, EStructuralFeature param1EStructuralFeature);
    
    boolean shouldSaveType(EClass param1EClass1, EClass param1EClass2, EStructuralFeature param1EStructuralFeature);
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLSave.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */