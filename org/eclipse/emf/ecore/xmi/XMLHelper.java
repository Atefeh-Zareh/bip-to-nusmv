package org.eclipse.emf.ecore.xmi;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

public interface XMLHelper {
  public static final int DATATYPE_SINGLE = 1;
  
  public static final int DATATYPE_IS_MANY = 2;
  
  public static final int IS_MANY_ADD = 3;
  
  public static final int IS_MANY_MOVE = 4;
  
  public static final int OTHER = 5;
  
  void setOptions(Map<?, ?> paramMap);
  
  void setNoNamespacePackage(EPackage paramEPackage);
  
  EPackage getNoNamespacePackage();
  
  void setAnySimpleType(EClass paramEClass);
  
  void setXMLMap(XMLResource.XMLMap paramXMLMap);
  
  XMLResource.XMLMap getXMLMap();
  
  void setExtendedMetaData(ExtendedMetaData paramExtendedMetaData);
  
  ExtendedMetaData getExtendedMetaData();
  
  XMLResource getResource();
  
  Object getValue(EObject paramEObject, EStructuralFeature paramEStructuralFeature);
  
  String getName(ENamedElement paramENamedElement);
  
  String getQName(EClass paramEClass);
  
  void populateNameInfo(NameInfo paramNameInfo, EClass paramEClass);
  
  String getQName(EDataType paramEDataType);
  
  void populateNameInfo(NameInfo paramNameInfo, EDataType paramEDataType);
  
  String getQName(EStructuralFeature paramEStructuralFeature);
  
  void populateNameInfo(NameInfo paramNameInfo, EStructuralFeature paramEStructuralFeature);
  
  String getPrefix(String paramString);
  
  String getPrefix(EPackage paramEPackage);
  
  String getNamespaceURI(String paramString);
  
  List<String> getPrefixes(EPackage paramEPackage);
  
  String getID(EObject paramEObject);
  
  String getIDREF(EObject paramEObject);
  
  String getHREF(EObject paramEObject);
  
  URI deresolve(URI paramURI);
  
  EPackage[] packages();
  
  @Deprecated
  EObject createObject(EFactory paramEFactory, String paramString);
  
  EObject createObject(EFactory paramEFactory, EClassifier paramEClassifier);
  
  EClassifier getType(EFactory paramEFactory, String paramString);
  
  void setValue(EObject paramEObject, EStructuralFeature paramEStructuralFeature, Object paramObject, int paramInt);
  
  EStructuralFeature getFeature(EClass paramEClass, String paramString1, String paramString2);
  
  EStructuralFeature getFeature(EClass paramEClass, String paramString1, String paramString2, boolean paramBoolean);
  
  int getFeatureKind(EStructuralFeature paramEStructuralFeature);
  
  String getXMLEncoding(String paramString);
  
  String getJavaEncoding(String paramString);
  
  List<XMIException> setManyReference(ManyReference paramManyReference, String paramString);
  
  void setCheckForDuplicates(boolean paramBoolean);
  
  void setProcessDanglingHREF(String paramString);
  
  DanglingHREFException getDanglingHREFException();
  
  URI resolve(URI paramURI1, URI paramURI2);
  
  void addPrefix(String paramString1, String paramString2);
  
  Map<String, String> getAnyContentPrefixToURIMapping();
  
  void recordPrefixToURIMapping();
  
  String getURI(String paramString);
  
  void pushContext();
  
  void popContext();
  
  void popContext(Map<String, EFactory> paramMap);
  
  String convertToString(EFactory paramEFactory, EDataType paramEDataType, Object paramObject);
  
  EMap<String, String> getPrefixToNamespaceMap();
  
  void setPrefixToNamespaceMap(EMap<String, String> paramEMap);
  
  void setMustHavePrefix(boolean paramBoolean);
  
  public static interface ManyReference {
    EObject getObject();
    
    EStructuralFeature getFeature();
    
    Object[] getValues();
    
    int[] getPositions();
    
    int getLineNumber();
    
    int getColumnNumber();
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMLHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */