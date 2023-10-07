/*     */ package org.eclipse.emf.ecore.xml.namespace.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import org.eclipse.emf.ecore.util.Diagnostician;
/*     */ import org.eclipse.emf.ecore.xml.namespace.SpaceType;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceFactory;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLNamespaceFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements XMLNamespaceFactory
/*     */ {
/*     */   public static XMLNamespaceFactory init() {
/*     */     try {
/*  52 */       XMLNamespaceFactory theXMLNamespaceFactory = (XMLNamespaceFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.w3.org/XML/1998/namespace");
/*  53 */       if (theXMLNamespaceFactory != null)
/*     */       {
/*  55 */         return theXMLNamespaceFactory;
/*     */       }
/*     */     }
/*  58 */     catch (Exception exception) {
/*     */       
/*  60 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  62 */     return new XMLNamespaceFactoryImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  84 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  86 */         return (EObject)createXMLNamespaceDocumentRoot();
/*     */     } 
/*  88 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/* 100 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 1:
/* 103 */         return createSpaceTypeFromString(eDataType, initialValue);
/*     */       case 2:
/* 105 */         return createLangTypeFromString(eDataType, initialValue);
/*     */       case 3:
/* 107 */         return createLangTypeNullFromString(eDataType, initialValue);
/*     */       case 4:
/* 109 */         return createSpaceTypeObjectFromString(eDataType, initialValue);
/*     */     } 
/* 111 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 123 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 1:
/* 126 */         return convertSpaceTypeToString(eDataType, instanceValue);
/*     */       case 2:
/* 128 */         return convertLangTypeToString(eDataType, instanceValue);
/*     */       case 3:
/* 130 */         return convertLangTypeNullToString(eDataType, instanceValue);
/*     */       case 4:
/* 132 */         return convertSpaceTypeObjectToString(eDataType, instanceValue);
/*     */     } 
/* 134 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLNamespaceDocumentRoot createXMLNamespaceDocumentRoot() {
/* 145 */     XMLNamespaceDocumentRootImpl xmlNamespaceDocumentRoot = new XMLNamespaceDocumentRootImpl();
/* 146 */     return xmlNamespaceDocumentRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpaceType createSpaceTypeFromString(EDataType eDataType, String initialValue) {
/* 156 */     SpaceType result = SpaceType.get(initialValue);
/* 157 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 158 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertSpaceTypeToString(EDataType eDataType, Object instanceValue) {
/* 168 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String createLangTypeFromString(EDataType eDataType, String initialValue) {
/* 178 */     if (initialValue == null) return null; 
/* 179 */     String result = null;
/* 180 */     RuntimeException exception = null;
/*     */     
/*     */     try {
/* 183 */       result = (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.LANGUAGE, initialValue);
/* 184 */       if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
/*     */       {
/* 186 */         return result;
/*     */       }
/*     */     }
/* 189 */     catch (RuntimeException e) {
/*     */       
/* 191 */       exception = e;
/*     */     } 
/*     */     
/*     */     try {
/* 195 */       result = createLangTypeNullFromString(XMLNamespacePackage.Literals.LANG_TYPE_NULL, initialValue);
/* 196 */       if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
/*     */       {
/* 198 */         return result;
/*     */       }
/*     */     }
/* 201 */     catch (RuntimeException e) {
/*     */       
/* 203 */       exception = e;
/*     */     } 
/* 205 */     if (result != null || exception == null) return result;
/*     */     
/* 207 */     throw exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertLangTypeToString(EDataType eDataType, Object instanceValue) {
/* 217 */     if (instanceValue == null) return null; 
/* 218 */     if (XMLTypePackage.Literals.LANGUAGE.isInstance(instanceValue)) {
/*     */       
/*     */       try {
/*     */         
/* 222 */         String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.LANGUAGE, instanceValue);
/* 223 */         if (value != null) return value;
/*     */       
/* 225 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 230 */     if (XMLNamespacePackage.Literals.LANG_TYPE_NULL.isInstance(instanceValue)) {
/*     */       
/*     */       try {
/*     */         
/* 234 */         String value = convertLangTypeNullToString(XMLNamespacePackage.Literals.LANG_TYPE_NULL, instanceValue);
/* 235 */         if (value != null) return value;
/*     */       
/* 237 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 242 */     throw new IllegalArgumentException("Invalid value: '" + instanceValue + "' for datatype :" + eDataType.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String createLangTypeNullFromString(EDataType eDataType, String initialValue) {
/* 252 */     return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertLangTypeNullToString(EDataType eDataType, Object instanceValue) {
/* 262 */     return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpaceType createSpaceTypeObjectFromString(EDataType eDataType, String initialValue) {
/* 272 */     return createSpaceTypeFromString((EDataType)XMLNamespacePackage.Literals.SPACE_TYPE, initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertSpaceTypeObjectToString(EDataType eDataType, Object instanceValue) {
/* 282 */     return convertSpaceTypeToString((EDataType)XMLNamespacePackage.Literals.SPACE_TYPE, instanceValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLNamespacePackage getXMLNamespacePackage() {
/* 292 */     return (XMLNamespacePackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static XMLNamespacePackage getPackage() {
/* 304 */     return XMLNamespacePackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\impl\XMLNamespaceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */