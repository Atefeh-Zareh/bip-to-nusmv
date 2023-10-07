/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
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
/*     */ public class EcoreSwitch<T>
/*     */ {
/*     */   protected static EcorePackage modelPackage;
/*     */   
/*     */   public EcoreSwitch() {
/*  59 */     if (modelPackage == null)
/*     */     {
/*  61 */       modelPackage = EcorePackage.eINSTANCE;
/*     */     }
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
/*     */   public T doSwitch(EObject theEObject) {
/*  74 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*     */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*  86 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  88 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  92 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  93 */     return 
/*  94 */       eList.isEmpty() ? 
/*  95 */       defaultCase(theEObject) : 
/*  96 */       doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { EAttribute eAttribute; EAnnotation eAnnotation; EClass eClass; EClassifier eClassifier; EDataType eDataType; EEnum eEnum; EEnumLiteral eEnumLiteral; EFactory eFactory;
/*     */     EModelElement eModelElement;
/*     */     ENamedElement eNamedElement;
/*     */     EOperation eOperation;
/*     */     EPackage ePackage;
/*     */     EParameter eParameter;
/*     */     EReference eReference;
/*     */     EStructuralFeature eStructuralFeature;
/*     */     ETypedElement eTypedElement;
/*     */     Map.Entry<String, String> eStringToStringMapEntry;
/*     */     EGenericType eGenericType;
/*     */     ETypeParameter eTypeParameter;
/*     */     T result;
/* 109 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 113 */         eAttribute = (EAttribute)theEObject;
/* 114 */         result = caseEAttribute(eAttribute);
/* 115 */         if (result == null) result = caseEStructuralFeature((EStructuralFeature)eAttribute); 
/* 116 */         if (result == null) result = caseETypedElement((ETypedElement)eAttribute); 
/* 117 */         if (result == null) result = caseENamedElement((ENamedElement)eAttribute); 
/* 118 */         if (result == null) result = caseEModelElement((EModelElement)eAttribute); 
/* 119 */         if (result == null) result = defaultCase(theEObject); 
/* 120 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 124 */         eAnnotation = (EAnnotation)theEObject;
/* 125 */         result = caseEAnnotation(eAnnotation);
/* 126 */         if (result == null) result = caseEModelElement((EModelElement)eAnnotation); 
/* 127 */         if (result == null) result = defaultCase(theEObject); 
/* 128 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 132 */         eClass = (EClass)theEObject;
/* 133 */         result = caseEClass(eClass);
/* 134 */         if (result == null) result = caseEClassifier((EClassifier)eClass); 
/* 135 */         if (result == null) result = caseENamedElement((ENamedElement)eClass); 
/* 136 */         if (result == null) result = caseEModelElement((EModelElement)eClass); 
/* 137 */         if (result == null) result = defaultCase(theEObject); 
/* 138 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 142 */         eClassifier = (EClassifier)theEObject;
/* 143 */         result = caseEClassifier(eClassifier);
/* 144 */         if (result == null) result = caseENamedElement((ENamedElement)eClassifier); 
/* 145 */         if (result == null) result = caseEModelElement((EModelElement)eClassifier); 
/* 146 */         if (result == null) result = defaultCase(theEObject); 
/* 147 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 151 */         eDataType = (EDataType)theEObject;
/* 152 */         result = caseEDataType(eDataType);
/* 153 */         if (result == null) result = caseEClassifier((EClassifier)eDataType); 
/* 154 */         if (result == null) result = caseENamedElement((ENamedElement)eDataType); 
/* 155 */         if (result == null) result = caseEModelElement((EModelElement)eDataType); 
/* 156 */         if (result == null) result = defaultCase(theEObject); 
/* 157 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 161 */         eEnum = (EEnum)theEObject;
/* 162 */         result = caseEEnum(eEnum);
/* 163 */         if (result == null) result = caseEDataType((EDataType)eEnum); 
/* 164 */         if (result == null) result = caseEClassifier((EClassifier)eEnum); 
/* 165 */         if (result == null) result = caseENamedElement((ENamedElement)eEnum); 
/* 166 */         if (result == null) result = caseEModelElement((EModelElement)eEnum); 
/* 167 */         if (result == null) result = defaultCase(theEObject); 
/* 168 */         return result;
/*     */ 
/*     */       
/*     */       case 6:
/* 172 */         eEnumLiteral = (EEnumLiteral)theEObject;
/* 173 */         result = caseEEnumLiteral(eEnumLiteral);
/* 174 */         if (result == null) result = caseENamedElement((ENamedElement)eEnumLiteral); 
/* 175 */         if (result == null) result = caseEModelElement((EModelElement)eEnumLiteral); 
/* 176 */         if (result == null) result = defaultCase(theEObject); 
/* 177 */         return result;
/*     */ 
/*     */       
/*     */       case 7:
/* 181 */         eFactory = (EFactory)theEObject;
/* 182 */         result = caseEFactory(eFactory);
/* 183 */         if (result == null) result = caseEModelElement((EModelElement)eFactory); 
/* 184 */         if (result == null) result = defaultCase(theEObject); 
/* 185 */         return result;
/*     */ 
/*     */       
/*     */       case 8:
/* 189 */         eModelElement = (EModelElement)theEObject;
/* 190 */         result = caseEModelElement(eModelElement);
/* 191 */         if (result == null) result = defaultCase(theEObject); 
/* 192 */         return result;
/*     */ 
/*     */       
/*     */       case 9:
/* 196 */         eNamedElement = (ENamedElement)theEObject;
/* 197 */         result = caseENamedElement(eNamedElement);
/* 198 */         if (result == null) result = caseEModelElement((EModelElement)eNamedElement); 
/* 199 */         if (result == null) result = defaultCase(theEObject); 
/* 200 */         return result;
/*     */ 
/*     */       
/*     */       case 11:
/* 204 */         eOperation = (EOperation)theEObject;
/* 205 */         result = caseEOperation(eOperation);
/* 206 */         if (result == null) result = caseETypedElement((ETypedElement)eOperation); 
/* 207 */         if (result == null) result = caseENamedElement((ENamedElement)eOperation); 
/* 208 */         if (result == null) result = caseEModelElement((EModelElement)eOperation); 
/* 209 */         if (result == null) result = defaultCase(theEObject); 
/* 210 */         return result;
/*     */ 
/*     */       
/*     */       case 12:
/* 214 */         ePackage = (EPackage)theEObject;
/* 215 */         result = caseEPackage(ePackage);
/* 216 */         if (result == null) result = caseENamedElement((ENamedElement)ePackage); 
/* 217 */         if (result == null) result = caseEModelElement((EModelElement)ePackage); 
/* 218 */         if (result == null) result = defaultCase(theEObject); 
/* 219 */         return result;
/*     */ 
/*     */       
/*     */       case 13:
/* 223 */         eParameter = (EParameter)theEObject;
/* 224 */         result = caseEParameter(eParameter);
/* 225 */         if (result == null) result = caseETypedElement((ETypedElement)eParameter); 
/* 226 */         if (result == null) result = caseENamedElement((ENamedElement)eParameter); 
/* 227 */         if (result == null) result = caseEModelElement((EModelElement)eParameter); 
/* 228 */         if (result == null) result = defaultCase(theEObject); 
/* 229 */         return result;
/*     */ 
/*     */       
/*     */       case 14:
/* 233 */         eReference = (EReference)theEObject;
/* 234 */         result = caseEReference(eReference);
/* 235 */         if (result == null) result = caseEStructuralFeature((EStructuralFeature)eReference); 
/* 236 */         if (result == null) result = caseETypedElement((ETypedElement)eReference); 
/* 237 */         if (result == null) result = caseENamedElement((ENamedElement)eReference); 
/* 238 */         if (result == null) result = caseEModelElement((EModelElement)eReference); 
/* 239 */         if (result == null) result = defaultCase(theEObject); 
/* 240 */         return result;
/*     */ 
/*     */       
/*     */       case 15:
/* 244 */         eStructuralFeature = (EStructuralFeature)theEObject;
/* 245 */         result = caseEStructuralFeature(eStructuralFeature);
/* 246 */         if (result == null) result = caseETypedElement((ETypedElement)eStructuralFeature); 
/* 247 */         if (result == null) result = caseENamedElement((ENamedElement)eStructuralFeature); 
/* 248 */         if (result == null) result = caseEModelElement((EModelElement)eStructuralFeature); 
/* 249 */         if (result == null) result = defaultCase(theEObject); 
/* 250 */         return result;
/*     */ 
/*     */       
/*     */       case 16:
/* 254 */         eTypedElement = (ETypedElement)theEObject;
/* 255 */         result = caseETypedElement(eTypedElement);
/* 256 */         if (result == null) result = caseENamedElement((ENamedElement)eTypedElement); 
/* 257 */         if (result == null) result = caseEModelElement((EModelElement)eTypedElement); 
/* 258 */         if (result == null) result = defaultCase(theEObject); 
/* 259 */         return result;
/*     */ 
/*     */       
/*     */       case 17:
/* 263 */         eStringToStringMapEntry = (Map.Entry<String, String>)theEObject;
/* 264 */         result = caseEStringToStringMapEntry(eStringToStringMapEntry);
/* 265 */         if (result == null) result = defaultCase(theEObject); 
/* 266 */         return result;
/*     */ 
/*     */       
/*     */       case 18:
/* 270 */         eGenericType = (EGenericType)theEObject;
/* 271 */         result = caseEGenericType(eGenericType);
/* 272 */         if (result == null) result = defaultCase(theEObject); 
/* 273 */         return result;
/*     */ 
/*     */       
/*     */       case 19:
/* 277 */         eTypeParameter = (ETypeParameter)theEObject;
/* 278 */         result = caseETypeParameter(eTypeParameter);
/* 279 */         if (result == null) result = caseENamedElement((ENamedElement)eTypeParameter); 
/* 280 */         if (result == null) result = caseEModelElement((EModelElement)eTypeParameter); 
/* 281 */         if (result == null) result = defaultCase(theEObject); 
/* 282 */         return result;
/*     */     } 
/* 284 */     return defaultCase(theEObject); }
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
/*     */   public T caseEModelElement(EModelElement object) {
/* 301 */     return null;
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
/*     */   public T caseEStructuralFeature(EStructuralFeature object) {
/* 317 */     return null;
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
/*     */   public T caseEAnnotation(EAnnotation object) {
/* 333 */     return null;
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
/*     */   public T caseEAttribute(EAttribute object) {
/* 349 */     return null;
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
/*     */   public T caseEClass(EClass object) {
/* 365 */     return null;
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
/*     */   public T caseEDataType(EDataType object) {
/* 381 */     return null;
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
/*     */   public T caseEClassifier(EClassifier object) {
/* 397 */     return null;
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
/*     */   public T caseENamedElement(ENamedElement object) {
/* 413 */     return null;
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
/*     */   public T defaultCase(EObject object) {
/* 429 */     return null;
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
/*     */   public T caseETypedElement(ETypedElement object) {
/* 445 */     return null;
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
/*     */   public T caseEStringToStringMapEntry(Map.Entry<String, String> object) {
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
/*     */   
/*     */   public T caseEGenericType(EGenericType object) {
/* 477 */     return null;
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
/*     */   public T caseETypeParameter(ETypeParameter object) {
/* 493 */     return null;
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
/*     */   public T caseEParameter(EParameter object) {
/* 509 */     return null;
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
/*     */   public T caseEOperation(EOperation object) {
/* 525 */     return null;
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
/*     */   public T caseEPackage(EPackage object) {
/* 541 */     return null;
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
/*     */   public T caseEFactory(EFactory object) {
/* 557 */     return null;
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
/*     */   public T caseEEnumLiteral(EEnumLiteral object) {
/* 573 */     return null;
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
/*     */   public T caseEEnum(EEnum object) {
/* 589 */     return null;
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
/*     */   public T caseEReference(EReference object) {
/* 605 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreSwitch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */