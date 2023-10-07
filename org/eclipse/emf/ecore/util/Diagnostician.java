/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.BasicDiagnostic;
/*     */ import org.eclipse.emf.common.util.Diagnostic;
/*     */ import org.eclipse.emf.common.util.DiagnosticChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EValidator;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
/*     */ public class Diagnostician
/*     */   implements EValidator.SubstitutionLabelProvider, EValidator
/*     */ {
/*  45 */   public static final Diagnostician INSTANCE = new Diagnostician();
/*     */   
/*     */   protected EValidator.Registry eValidatorRegistry;
/*     */ 
/*     */   
/*     */   public Diagnostician(EValidator.Registry eValidatorRegistry) {
/*  51 */     this.eValidatorRegistry = eValidatorRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   public Diagnostician() {
/*  56 */     this(EValidator.Registry.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObjectLabel(EObject eObject) {
/*  61 */     return EcoreUtil.getIdentification(eObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFeatureLabel(EStructuralFeature eStructuralFeature) {
/*  66 */     return eStructuralFeature.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueLabel(EDataType eDataType, Object value) {
/*  71 */     return EcoreUtil.convertToString(eDataType, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Object, Object> createDefaultContext() {
/*  79 */     Map<Object, Object> context = new HashMap<Object, Object>();
/*  80 */     context.put(EValidator.SubstitutionLabelProvider.class, this);
/*  81 */     context.put(EValidator.class, this);
/*  82 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicDiagnostic createDefaultDiagnostic(EObject eObject) {
/*  90 */     return 
/*  91 */       new BasicDiagnostic(
/*  92 */         "org.eclipse.emf.ecore", 
/*  93 */         0, 
/*  94 */         EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { getObjectLabel(eObject)
/*  95 */           }), new Object[] { eObject });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicDiagnostic createDefaultDiagnostic(EDataType eDataType, Object value) {
/* 103 */     return 
/* 104 */       new BasicDiagnostic(
/* 105 */         "org.eclipse.emf.ecore", 
/* 106 */         0, 
/* 107 */         EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { getValueLabel(eDataType, value)
/* 108 */           }), new Object[] { value, eDataType });
/*     */   }
/*     */ 
/*     */   
/*     */   public Diagnostic validate(EObject eObject) {
/* 113 */     BasicDiagnostic diagnostics = createDefaultDiagnostic(eObject);
/* 114 */     validate(eObject, (DiagnosticChain)diagnostics, createDefaultContext());
/* 115 */     return (Diagnostic)diagnostics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Diagnostic validate(EObject eObject, Map<?, ?> contextEntries) {
/* 123 */     BasicDiagnostic diagnostics = createDefaultDiagnostic(eObject);
/* 124 */     Map<Object, Object> context = createDefaultContext();
/* 125 */     context.putAll(contextEntries);
/* 126 */     validate(eObject, (DiagnosticChain)diagnostics, context);
/* 127 */     return (Diagnostic)diagnostics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validate(EObject eObject, DiagnosticChain diagnostics) {
/* 138 */     return validate(eObject, diagnostics, createDefaultContext());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 143 */     return validate(eObject.eClass(), eObject, diagnostics, context);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 149 */     EClass eType = eClass; Object eValidator;
/* 150 */     while ((eValidator = this.eValidatorRegistry.get(eType.eContainer())) == null) {
/*     */       
/* 152 */       EList<EClass> eList = eType.getESuperTypes();
/* 153 */       if (eList.isEmpty()) {
/*     */         
/* 155 */         eValidator = this.eValidatorRegistry.get(null);
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 160 */       eType = eList.get(0);
/*     */     } 
/*     */     
/* 163 */     boolean circular = (context.get("org.eclipse.emf.ecore.EObject_NoCircularContainment") == eObject);
/*     */     
/* 165 */     boolean result = ((EValidator)eValidator).validate(eClass, eObject, diagnostics, context);
/* 166 */     if ((result || diagnostics != null) && !circular)
/*     */     {
/* 168 */       result &= doValidateContents(eObject, diagnostics, context);
/*     */     }
/* 170 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean doValidateContents(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 175 */     EList<EObject> eList = eObject.eContents();
/* 176 */     if (!eList.isEmpty()) {
/*     */       
/* 178 */       Iterator<EObject> i = eList.iterator();
/* 179 */       EObject child = i.next();
/* 180 */       boolean result = validate(child, diagnostics, context);
/* 181 */       while (i.hasNext() && (result || diagnostics != null)) {
/*     */         
/* 183 */         child = i.next();
/* 184 */         result &= validate(child, diagnostics, context);
/*     */       } 
/* 186 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Diagnostic validate(EDataType eDataType, Object value) {
/* 196 */     BasicDiagnostic diagnostics = createDefaultDiagnostic(eDataType, value);
/* 197 */     validate(eDataType, value, (DiagnosticChain)diagnostics, createDefaultContext());
/* 198 */     return (Diagnostic)diagnostics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 203 */     Object eValidator = this.eValidatorRegistry.get(eDataType.eContainer());
/* 204 */     if (eValidator == null)
/*     */     {
/* 206 */       eValidator = this.eValidatorRegistry.get(null);
/*     */     }
/*     */     
/* 209 */     return ((EValidator)eValidator).validate(eDataType, value, diagnostics, context);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\Diagnostician.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */