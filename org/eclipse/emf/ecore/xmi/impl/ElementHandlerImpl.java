/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
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
/*     */ 
/*     */ public class ElementHandlerImpl
/*     */   implements XMLResource.ElementHandler
/*     */ {
/*     */   protected boolean considerSubtypes;
/*     */   protected Collection<? extends EPackage> ePackages;
/*     */   
/*     */   public ElementHandlerImpl(boolean considerSubtypes) {
/*  51 */     this.considerSubtypes = considerSubtypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementHandlerImpl(boolean considerSubtypes, Collection<? extends EPackage> ePackages) {
/*  62 */     this.considerSubtypes = considerSubtypes;
/*  63 */     this.ePackages = ePackages;
/*     */   }
/*     */ 
/*     */   
/*     */   public EStructuralFeature getRoot(ExtendedMetaData extendedMetaData, EClassifier eClassifier) {
/*  68 */     if (extendedMetaData == null)
/*     */     {
/*  70 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     while (eClassifier != null) {
/*     */ 
/*     */ 
/*     */       
/*  80 */       EClass eClass = extendedMetaData.getDocumentRoot(eClassifier.getEPackage());
/*  81 */       if (eClass != null && eClass != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT)
/*     */       {
/*  83 */         for (EStructuralFeature element : extendedMetaData.getElements(eClass)) {
/*     */           
/*  85 */           if (element.getEType() == eClassifier && element.isChangeable())
/*     */           {
/*  87 */             return element;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (this.ePackages != null)
/*     */       {
/*  96 */         for (EPackage ePackage : this.ePackages) {
/*     */           
/*  98 */           eClass = extendedMetaData.getDocumentRoot(ePackage);
/*  99 */           if (eClass != null)
/*     */           {
/* 101 */             for (EStructuralFeature element : extendedMetaData.getElements(eClass)) {
/*     */               
/* 103 */               if (element.getEType() == eClassifier && element.isChangeable())
/*     */               {
/* 105 */                 return element;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/* 111 */       eClassifier = getSuperType(extendedMetaData, eClassifier);
/*     */     } 
/* 113 */     return null;
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
/*     */   protected EClassifier getSuperType(ExtendedMetaData extendedMetaData, EClassifier eClassifier) {
/* 126 */     if (eClassifier instanceof EDataType)
/*     */     {
/* 128 */       return (EClassifier)extendedMetaData.getBaseType((EDataType)eClassifier);
/*     */     }
/*     */ 
/*     */     
/* 132 */     EList<EClassifier> eList = ((EClass)eClassifier).getESuperTypes();
/* 133 */     if (eList.isEmpty())
/*     */     {
/* 135 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 139 */     return eList.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EStructuralFeature getSubstitutionGroup(ExtendedMetaData extendedMetaData, EStructuralFeature eStructuralFeature, EClassifier eClassifier) {
/* 146 */     if (extendedMetaData == null)
/*     */     {
/* 148 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     EClass eContainingClass = eStructuralFeature.getEContainingClass();
/* 155 */     while (eClassifier != null) {
/*     */       
/* 157 */       EStructuralFeature result = getSubstitutionGroup(extendedMetaData, eContainingClass.getEPackage(), eContainingClass, eStructuralFeature, eClassifier);
/* 158 */       if (result != null)
/*     */       {
/* 160 */         return result;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       result = getSubstitutionGroup(extendedMetaData, eClassifier.getEPackage(), eContainingClass, eStructuralFeature, eClassifier);
/* 167 */       if (result != null)
/*     */       {
/* 169 */         return result;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 175 */       if (this.ePackages != null)
/*     */       {
/* 177 */         for (EPackage ePackage : this.ePackages) {
/*     */           
/* 179 */           result = getSubstitutionGroup(extendedMetaData, ePackage, eContainingClass, eStructuralFeature, eClassifier);
/* 180 */           if (result != null)
/*     */           {
/* 182 */             return result;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 189 */       if (this.considerSubtypes) {
/*     */         
/* 191 */         eClassifier = getSuperType(extendedMetaData, eClassifier);
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*     */       break;
/*     */     } 
/*     */     
/* 200 */     return null;
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
/*     */   protected EStructuralFeature getSubstitutionGroup(ExtendedMetaData extendedMetaData, EPackage ePackage, EClass eContainingClass, EStructuralFeature eStructuralFeature, EClassifier eClassifier) {
/* 218 */     EClass eClass = extendedMetaData.getDocumentRoot(ePackage);
/* 219 */     if (eClass != null)
/*     */     {
/* 221 */       for (EStructuralFeature element : extendedMetaData.getElements(eClass)) {
/*     */         
/* 223 */         if (element.getEType() == eClassifier && element.isChangeable() && extendedMetaData.getAffiliation(eContainingClass, element) == eStructuralFeature)
/*     */         {
/* 225 */           return element;
/*     */         }
/*     */       } 
/*     */     }
/* 229 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\ElementHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */