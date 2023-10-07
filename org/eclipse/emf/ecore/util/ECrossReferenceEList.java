/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.impl.EClassImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ECrossReferenceEList<E>
/*     */   extends EContentsEList<E>
/*     */ {
/*  35 */   public static final ECrossReferenceEList<?> EMPTY_CROSS_REFERENCE_ELIST = new ECrossReferenceEList<Object>(null, null)
/*     */     {
/*     */       
/*     */       public List<Object> basicList()
/*     */       {
/*  40 */         return this;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> ECrossReferenceEList<T> emptyCrossReferenceEList() {
/*  47 */     return (ECrossReferenceEList)EMPTY_CROSS_REFERENCE_ELIST;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> ECrossReferenceEList<T> createECrossReferenceEList(EObject eObject) {
/*  52 */     EStructuralFeature[] eStructuralFeatures = (
/*  53 */       (EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).crossReferences();
/*     */     
/*  55 */     return 
/*  56 */       (eStructuralFeatures == null) ? 
/*  57 */       emptyCrossReferenceEList() : 
/*  58 */       new ECrossReferenceEList<T>(eObject, eStructuralFeatures);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ECrossReferenceEList(EObject eObject) {
/*  65 */     super(eObject, ((EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).crossReferences());
/*     */   }
/*     */ 
/*     */   
/*     */   protected ECrossReferenceEList(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/*  70 */     super(eObject, eStructuralFeatures);
/*     */   }
/*     */   
/*     */   public static class FeatureIteratorImpl<E>
/*     */     extends EContentsEList.FeatureIteratorImpl<E> {
/*  75 */     protected static final EStructuralFeature[] NO_FEATURES = new EStructuralFeature[0];
/*     */ 
/*     */     
/*     */     public FeatureIteratorImpl(EObject eObject) {
/*  79 */       this(eObject, ((EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).crossReferences());
/*     */     }
/*     */ 
/*     */     
/*     */     public FeatureIteratorImpl(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/*  84 */       super(eObject, (eStructuralFeatures == null) ? NO_FEATURES : eStructuralFeatures);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature) {
/*  90 */       if (eStructuralFeature instanceof EReference) {
/*     */         
/*  92 */         EReference eReference = (EReference)eStructuralFeature;
/*  93 */         return (!eReference.isContainment() && !eReference.isContainer());
/*     */       } 
/*     */ 
/*     */       
/*  97 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ResolvingFeatureIteratorImpl<E>
/*     */     extends FeatureIteratorImpl<E>
/*     */   {
/*     */     public ResolvingFeatureIteratorImpl(EObject eObject) {
/* 106 */       super(eObject);
/*     */     }
/*     */ 
/*     */     
/*     */     public ResolvingFeatureIteratorImpl(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/* 111 */       super(eObject, eStructuralFeatures);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean resolve() {
/* 117 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isIncluded(EStructuralFeature eStructuralFeature) {
/* 124 */     if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
/*     */     {
/* 126 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 130 */     EReference eReference = (EReference)eStructuralFeature;
/* 131 */     return (!eReference.isContainment() && !eReference.isContainer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature) {
/* 138 */     if (eStructuralFeature instanceof EReference) {
/*     */       
/* 140 */       EReference eReference = (EReference)eStructuralFeature;
/* 141 */       return (!eReference.isContainment() && !eReference.isContainer());
/*     */     } 
/*     */ 
/*     */     
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ListIterator<E> newResolvingListIterator() {
/* 152 */     return new ResolvingFeatureIteratorImpl<E>(this.eObject, this.eStructuralFeatures);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ListIterator<E> newNonResolvingListIterator() {
/* 158 */     return new FeatureIteratorImpl<E>(this.eObject, this.eStructuralFeatures);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/* 164 */     return 
/* 165 */       new ECrossReferenceEList<E>(this.eObject, this.eStructuralFeatures)
/*     */       {
/*     */         
/*     */         protected boolean resolve()
/*     */         {
/* 170 */           return false;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\ECrossReferenceEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */