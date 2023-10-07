/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EEnumLiteral;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.EModelElement;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EParameter;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.ETypeParameter;
/*     */ import org.eclipse.emf.ecore.ETypedElement;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EcoreAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static EcorePackage modelPackage;
/*     */   protected EcoreSwitch<Adapter> modelSwitch;
/*     */   
/*     */   public EcoreAdapterFactory() {
/*  89 */     this
/*  90 */       .modelSwitch = new EcoreSwitch<Adapter>()
/*     */       {
/*     */         
/*     */         public Adapter caseEAttribute(EAttribute object)
/*     */         {
/*  95 */           return EcoreAdapterFactory.this.createEAttributeAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEAnnotation(EAnnotation object) {
/* 100 */           return EcoreAdapterFactory.this.createEAnnotationAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEClass(EClass object) {
/* 105 */           return EcoreAdapterFactory.this.createEClassAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEClassifier(EClassifier object) {
/* 110 */           return EcoreAdapterFactory.this.createEClassifierAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEDataType(EDataType object) {
/* 115 */           return EcoreAdapterFactory.this.createEDataTypeAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEEnum(EEnum object) {
/* 120 */           return EcoreAdapterFactory.this.createEEnumAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEEnumLiteral(EEnumLiteral object) {
/* 125 */           return EcoreAdapterFactory.this.createEEnumLiteralAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEFactory(EFactory object) {
/* 130 */           return EcoreAdapterFactory.this.createEFactoryAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEModelElement(EModelElement object) {
/* 135 */           return EcoreAdapterFactory.this.createEModelElementAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseENamedElement(ENamedElement object) {
/* 140 */           return EcoreAdapterFactory.this.createENamedElementAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEOperation(EOperation object) {
/* 145 */           return EcoreAdapterFactory.this.createEOperationAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEPackage(EPackage object) {
/* 150 */           return EcoreAdapterFactory.this.createEPackageAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEParameter(EParameter object) {
/* 155 */           return EcoreAdapterFactory.this.createEParameterAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEReference(EReference object) {
/* 160 */           return EcoreAdapterFactory.this.createEReferenceAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEStructuralFeature(EStructuralFeature object) {
/* 165 */           return EcoreAdapterFactory.this.createEStructuralFeatureAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseETypedElement(ETypedElement object) {
/* 170 */           return EcoreAdapterFactory.this.createETypedElementAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEStringToStringMapEntry(Map.Entry<String, String> object) {
/* 175 */           return EcoreAdapterFactory.this.createEStringToStringMapEntryAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseEGenericType(EGenericType object) {
/* 180 */           return EcoreAdapterFactory.this.createEGenericTypeAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseETypeParameter(ETypeParameter object) {
/* 185 */           return EcoreAdapterFactory.this.createETypeParameterAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter defaultCase(EObject object) {
/* 190 */           return EcoreAdapterFactory.this.createEObjectAdapter();
/*     */         }
/*     */       };
/*     */     if (modelPackage == null) {
/*     */       modelPackage = EcorePackage.eINSTANCE;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Adapter createAdapter(Notifier target) {
/* 205 */     return this.modelSwitch.doSwitch((EObject)target);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFactoryForType(Object object) {
/*     */     if (object == modelPackage) {
/*     */       return true;
/*     */     }
/*     */     if (object instanceof EObject) {
/*     */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*     */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Adapter createEModelElementAdapter() {
/* 221 */     return null;
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
/*     */   public Adapter createEStructuralFeatureAdapter() {
/* 236 */     return null;
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
/*     */   public Adapter createEAnnotationAdapter() {
/* 251 */     return null;
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
/*     */   public Adapter createEAttributeAdapter() {
/* 266 */     return null;
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
/*     */   public Adapter createEClassAdapter() {
/* 281 */     return null;
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
/*     */   public Adapter createEDataTypeAdapter() {
/* 296 */     return null;
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
/*     */   public Adapter createEClassifierAdapter() {
/* 311 */     return null;
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
/*     */   public Adapter createENamedElementAdapter() {
/* 326 */     return null;
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
/*     */   public Adapter createETypedElementAdapter() {
/* 341 */     return null;
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
/*     */   public Adapter createEParameterAdapter() {
/* 356 */     return null;
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
/*     */   public Adapter createEOperationAdapter() {
/* 371 */     return null;
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
/*     */   public Adapter createEPackageAdapter() {
/* 386 */     return null;
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
/*     */   public Adapter createEFactoryAdapter() {
/* 401 */     return null;
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
/*     */   public Adapter createEEnumLiteralAdapter() {
/* 416 */     return null;
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
/*     */   public Adapter createEEnumAdapter() {
/* 431 */     return null;
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
/*     */   public Adapter createEReferenceAdapter() {
/* 446 */     return null;
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
/*     */   public Adapter createEStringToStringMapEntryAdapter() {
/* 461 */     return null;
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
/*     */   public Adapter createEGenericTypeAdapter() {
/* 476 */     return null;
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
/*     */   public Adapter createETypeParameterAdapter() {
/* 491 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 504 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreAdapterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */