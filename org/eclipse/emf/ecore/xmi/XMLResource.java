package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public interface XMLResource extends Resource {
  public static final String OPTION_USE_PARSER_POOL = "USE_PARSER_POOL";
  
  public static final String OPTION_USE_XML_NAME_TO_FEATURE_MAP = "USE_XML_NAME_TO_FEATURE_MAP";
  
  public static final String OPTION_USE_CACHED_LOOKUP_TABLE = "USE_CACHED_LOOKUP_TABLE";
  
  public static final String OPTION_USE_DEPRECATED_METHODS = "USE_DEPRECATED_METHODS";
  
  public static final String OPTION_CONFIGURATION_CACHE = "CONFIGURATION_CACHE";
  
  public static final String OPTION_SAVE_TYPE_INFORMATION = "SAVE_TYPE_INFORMATION";
  
  public static final String OPTION_PARSER_FEATURES = "PARSER_FEATURES";
  
  public static final String OPTION_PARSER_PROPERTIES = "PARSER_PROPERTIES";
  
  public static final String OPTION_USE_LEXICAL_HANDLER = "USE_LEXICAL_HANDLER";
  
  public static final String OPTION_DOM_USE_NAMESPACES_IN_SCOPE = "DOM_USE_NAMESPACES_IN_SCOPE";
  
  public static final String OPTION_EXTENDED_META_DATA = "EXTENDED_META_DATA";
  
  public static final String OPTION_ANY_TYPE = "ANY_TYPE";
  
  public static final String OPTION_ANY_SIMPLE_TYPE = "ANY_SIMPLE_TYPE";
  
  public static final String OPTION_XML_MAP = "XML_MAP";
  
  public static final String OPTION_USE_ENCODED_ATTRIBUTE_STYLE = "USE_ENCODED_ATTRIBUTE_STYLE";
  
  public static final String OPTION_FORMATTED = "FORMATTED";
  
  public static final String OPTION_LINE_WIDTH = "LINE_WIDTH";
  
  public static final String OPTION_DECLARE_XML = "DECLARE_XML";
  
  public static final String OPTION_KEEP_DEFAULT_CONTENT = "KEEP_DEFAULT_CONTENT";
  
  public static final String OPTION_SAVE_DOCTYPE = "SAVE_DOCTYPE";
  
  public static final String OPTION_RESOURCE_ENTITY_HANDLER = "RESOURCE_ENTITY_HANDLER";
  
  public static final String OPTION_SKIP_ESCAPE = "SKIP_ESCAPE";
  
  public static final String OPTION_SKIP_ESCAPE_URI = "SKIP_ESCAPE_URI";
  
  public static final String OPTION_PROCESS_DANGLING_HREF = "PROCESS_DANGLING_HREF";
  
  public static final String OPTION_PROCESS_DANGLING_HREF_THROW = "THROW";
  
  public static final String OPTION_PROCESS_DANGLING_HREF_DISCARD = "DISCARD";
  
  public static final String OPTION_PROCESS_DANGLING_HREF_RECORD = "RECORD";
  
  public static final String OPTION_RECORD_UNKNOWN_FEATURE = "RECORD_UNKNOWN_FEATURE";
  
  public static final String OPTION_LAX_FEATURE_PROCESSING = "LAX_FEATURE_PROCESSING";
  
  public static final String OPTION_LAX_WILDCARD_PROCESSING = "LAX_WILDCARD_PROCESSING";
  
  public static final String OPTION_XML_OPTIONS = "XML_OPTIONS";
  
  public static final String OPTION_DISABLE_NOTIFY = "DISABLE_NOTIFY";
  
  public static final String OPTION_SCHEMA_LOCATION = "SCHEMA_LOCATION";
  
  public static final String OPTION_SCHEMA_LOCATION_IMPLEMENTATION = "SCHEMA_LOCATION_IMPLEMENTATION";
  
  public static final String OPTION_ENCODING = "ENCODING";
  
  public static final String OPTION_XML_VERSION = "XML_VERSION";
  
  public static final String OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS = "RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS";
  
  public static final String OPTION_FLUSH_THRESHOLD = "FLUSH_THRESHOLD";
  
  public static final String OPTION_USE_FILE_BUFFER = "USE_FILE_BUFFER";
  
  public static final String OPTION_DEFER_IDREF_RESOLUTION = "DEFER_IDREF_RESOLUTION";
  
  public static final String OPTION_ROOT_OBJECTS = "ROOT_OBJECTS";
  
  public static final String OPTION_RESOURCE_HANDLER = "RESOURCE_HANDLER";
  
  public static final String OPTION_DEFER_ATTACHMENT = "DEFER_ATTACHMENT";
  
  public static final String OPTION_URI_HANDLER = "URI_HANDLER";
  
  public static final String OPTION_ELEMENT_HANDLER = "ELEMENT_HANDLER";
  
  public static final String OPTION_SUPPRESS_DOCUMENT_ROOT = "SUPPRESS_DOCUMENT_ROOT";
  
  public static final String OPTION_ESCAPE_USING_CDATA = "ESCAPE_USING_CDATA";
  
  public static final String HREF = "href";
  
  public static final String NIL = "nil";
  
  public static final String TYPE = "type";
  
  public static final String SCHEMA_LOCATION = "schemaLocation";
  
  public static final String NO_NAMESPACE_SCHEMA_LOCATION = "noNamespaceSchemaLocation";
  
  public static final String XML_NS = "xmlns";
  
  public static final String XSI_NS = "xsi";
  
  public static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";
  
  public static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";
  
  boolean useZip();
  
  void setUseZip(boolean paramBoolean);
  
  Map<Object, Object> getDefaultSaveOptions();
  
  Map<Object, Object> getDefaultLoadOptions();
  
  String getPublicId();
  
  String getSystemId();
  
  void setDoctypeInfo(String paramString1, String paramString2);
  
  String getEncoding();
  
  void setEncoding(String paramString);
  
  String getXMLVersion();
  
  void setXMLVersion(String paramString);
  
  @Deprecated
  Map<String, EObject> getIDToEObjectMap();
  
  @Deprecated
  Map<EObject, String> getEObjectToIDMap();
  
  String getID(EObject paramEObject);
  
  void setID(EObject paramEObject, String paramString);
  
  Map<EObject, AnyType> getEObjectToExtensionMap();
  
  Document save(Document paramDocument, Map<?, ?> paramMap, DOMHandler paramDOMHandler);
  
  void save(Writer paramWriter, Map<?, ?> paramMap) throws IOException;
  
  DOMHelper getDOMHelper();
  
  void load(Node paramNode, Map<?, ?> paramMap) throws IOException;
  
  void load(InputSource paramInputSource, Map<?, ?> paramMap) throws IOException;
  
  public static interface ElementHandler {
    EStructuralFeature getRoot(ExtendedMetaData param1ExtendedMetaData, EClassifier param1EClassifier);
    
    EStructuralFeature getSubstitutionGroup(ExtendedMetaData param1ExtendedMetaData, EStructuralFeature param1EStructuralFeature, EClassifier param1EClassifier);
  }
  
  public static interface ResourceEntityHandler {
    void reset();
    
    void handleEntity(String param1String1, String param1String2);
    
    String getEntityName(String param1String);
    
    Map<String, String> getNameToValueMap();
  }
  
  public static interface ResourceHandler {
    void preLoad(XMLResource param1XMLResource, InputStream param1InputStream, Map<?, ?> param1Map);
    
    void postLoad(XMLResource param1XMLResource, InputStream param1InputStream, Map<?, ?> param1Map);
    
    void preSave(XMLResource param1XMLResource, OutputStream param1OutputStream, Map<?, ?> param1Map);
    
    void postSave(XMLResource param1XMLResource, OutputStream param1OutputStream, Map<?, ?> param1Map);
  }
  
  public static interface URIHandler {
    void setBaseURI(URI param1URI);
    
    URI resolve(URI param1URI);
    
    URI deresolve(URI param1URI);
  }
  
  public static interface XMLInfo {
    public static final int UNSPECIFIED = -1;
    
    public static final int ELEMENT = 0;
    
    public static final int ATTRIBUTE = 1;
    
    public static final int CONTENT = 2;
    
    int getXMLRepresentation();
    
    void setXMLRepresentation(int param1Int);
    
    void setTargetNamespace(String param1String);
    
    String getTargetNamespace();
    
    String getName();
    
    void setName(String param1String);
  }
  
  public static interface XMLMap {
    void add(ENamedElement param1ENamedElement, XMLResource.XMLInfo param1XMLInfo);
    
    XMLResource.XMLInfo getInfo(ENamedElement param1ENamedElement);
    
    void setNoNamespacePackage(EPackage param1EPackage);
    
    EPackage getNoNamespacePackage();
    
    void setIDAttributeName(String param1String);
    
    String getIDAttributeName();
    
    EClassifier getClassifier(String param1String1, String param1String2);
    
    EStructuralFeature getFeature(EClass param1EClass, String param1String1, String param1String2);
    
    List<EStructuralFeature> getFeatures(EClass param1EClass);
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */