/*     */ package org.eclipse.emf.ecore.xml.namespace.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.BasicDiagnostic;
/*     */ import org.eclipse.emf.common.util.Diagnostic;
/*     */ import org.eclipse.emf.common.util.DiagnosticChain;
/*     */ import org.eclipse.emf.common.util.ResourceLocator;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import org.eclipse.emf.ecore.util.EObjectValidator;
/*     */ import org.eclipse.emf.ecore.xml.namespace.SpaceType;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
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
/*     */ 
/*     */ 
/*     */ public class XMLNamespaceValidator
/*     */   extends EObjectValidator
/*     */ {
/*  52 */   public static final XMLNamespaceValidator INSTANCE = new XMLNamespaceValidator();
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
/*     */   public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.xml.namespace";
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
/*     */   private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
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
/*     */   protected static final int DIAGNOSTIC_CODE_COUNT = 0;
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
/*  97 */   protected XMLTypeValidator xmlTypeValidator = XMLTypeValidator.INSTANCE;
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
/*     */   protected EPackage getEPackage() {
/* 109 */     return (EPackage)XMLNamespacePackage.eINSTANCE;
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
/*     */   protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 121 */     switch (classifierID) {
/*     */       
/*     */       case 0:
/* 124 */         return validateXMLNamespaceDocumentRoot((XMLNamespaceDocumentRoot)value, diagnostics, context);
/*     */       case 1:
/* 126 */         return validateSpaceType((SpaceType)value, diagnostics, context);
/*     */       case 2:
/* 128 */         return validateLangType((String)value, diagnostics, context);
/*     */       case 3:
/* 130 */         return validateLangTypeNull((String)value, diagnostics, context);
/*     */       case 4:
/* 132 */         return validateSpaceTypeObject((SpaceType)value, diagnostics, context);
/*     */     } 
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateXMLNamespaceDocumentRoot(XMLNamespaceDocumentRoot xmlNamespaceDocumentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 145 */     return validate_EveryDefaultConstraint((EObject)xmlNamespaceDocumentRoot, diagnostics, context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateSpaceType(SpaceType spaceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateLangType(String langType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 165 */     boolean result = validateLangType_MemberTypes(langType, diagnostics, context);
/* 166 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateLangType_MemberTypes(String langType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 177 */     if (diagnostics != null) {
/*     */       
/* 179 */       BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
/* 180 */       if (XMLTypePackage.Literals.LANGUAGE.isInstance(langType))
/*     */       {
/* 182 */         if (this.xmlTypeValidator.validateLanguage(langType, (DiagnosticChain)tempDiagnostics, context)) return true; 
/*     */       }
/* 184 */       if (XMLNamespacePackage.Literals.LANG_TYPE_NULL.isInstance(langType))
/*     */       {
/* 186 */         if (validateLangTypeNull(langType, (DiagnosticChain)tempDiagnostics, context)) return true; 
/*     */       }
/* 188 */       for (Diagnostic diagnostic : tempDiagnostics.getChildren())
/*     */       {
/* 190 */         diagnostics.add(diagnostic);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 195 */       if (XMLTypePackage.Literals.LANGUAGE.isInstance(langType))
/*     */       {
/* 197 */         if (this.xmlTypeValidator.validateLanguage(langType, null, context)) return true; 
/*     */       }
/* 199 */       if (XMLNamespacePackage.Literals.LANG_TYPE_NULL.isInstance(langType))
/*     */       {
/* 201 */         if (validateLangTypeNull(langType, (DiagnosticChain)null, context)) return true; 
/*     */       }
/*     */     } 
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateLangTypeNull(String langTypeNull, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validateSpaceTypeObject(SpaceType spaceTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 224 */     return true;
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
/*     */   public ResourceLocator getResourceLocator() {
/* 236 */     return (ResourceLocator)EcorePlugin.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespac\\util\XMLNamespaceValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */