/*     */ package org.eclipse.emf.ecore;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.DiagnosticChain;
/*     */ import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
/*     */ import org.eclipse.emf.ecore.impl.ValidationDelegateRegistryImpl;
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
/*     */ public interface EValidator
/*     */ {
/*     */   public static final String MARKER = "org.eclipse.emf.ecore.diagnostic";
/*     */   public static final String URI_ATTRIBUTE = "uri";
/*     */   public static final String RELATED_URIS_ATTRIBUTE = "relatedURIs";
/*     */   
/*     */   boolean validate(EObject paramEObject, DiagnosticChain paramDiagnosticChain, Map<Object, Object> paramMap);
/*     */   
/*     */   boolean validate(EClass paramEClass, EObject paramEObject, DiagnosticChain paramDiagnosticChain, Map<Object, Object> paramMap);
/*     */   
/*     */   boolean validate(EDataType paramEDataType, Object paramObject, DiagnosticChain paramDiagnosticChain, Map<Object, Object> paramMap);
/*     */   
/*     */   public static interface Descriptor
/*     */   {
/*     */     EValidator getEValidator();
/*     */   }
/*     */   
/*     */   public static interface PatternMatcher
/*     */   {
/*     */     boolean matches(String param1String);
/*     */   }
/*     */   
/*     */   public static interface Registry
/*     */     extends Map<EPackage, Object>
/*     */   {
/*  84 */     public static final Registry INSTANCE = (Registry)new EValidatorRegistryImpl();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     EValidator getEValidator(EPackage param1EPackage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface SubstitutionLabelProvider
/*     */   {
/*     */     String getObjectLabel(EObject param1EObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getFeatureLabel(EStructuralFeature param1EStructuralFeature);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getValueLabel(EDataType param1EDataType, Object param1Object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ValidationDelegate
/*     */   {
/*     */     boolean validate(EClass param1EClass, EObject param1EObject, Map<Object, Object> param1Map, EOperation param1EOperation, String param1String);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean validate(EClass param1EClass, EObject param1EObject, Map<Object, Object> param1Map, String param1String1, String param1String2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean validate(EDataType param1EDataType, Object param1Object, Map<Object, Object> param1Map, String param1String1, String param1String2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static interface Descriptor
/*     */     {
/*     */       EValidator.ValidationDelegate getValidationDelegate();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static interface Registry
/*     */       extends Map<String, Object>
/*     */     {
/* 150 */       public static final Registry INSTANCE = (Registry)new ValidationDelegateRegistryImpl();
/*     */       
/*     */       EValidator.ValidationDelegate getValidationDelegate(String param2String);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */