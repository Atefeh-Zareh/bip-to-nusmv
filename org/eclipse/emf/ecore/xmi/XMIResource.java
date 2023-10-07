package org.eclipse.emf.ecore.xmi;

public interface XMIResource extends XMLResource {
  public static final String OPTION_USE_XMI_TYPE = "USE_XMI_TYPE";
  
  public static final String VERSION_NAME = "version";
  
  public static final String VERSION_VALUE = "2.0";
  
  public static final String XMI_NAMESPACE_PREFIX = "http://schema.omg.org/spec/XMI/";
  
  public static final String XMI_NS = "xmi";
  
  public static final String XMI_ID = "id";
  
  public static final String XMI_TAG_NAME = "XMI";
  
  public static final String XMI_URI = "http://www.omg.org/XMI";
  
  String getXMIVersion();
  
  void setXMIVersion(String paramString);
  
  String getXMINamespace();
  
  void setXMINamespace(String paramString);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMIResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */