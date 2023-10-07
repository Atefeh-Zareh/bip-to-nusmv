/*    */ package org.eclipse.emf.ecore.util;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.InternalEObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EObjectWithInverseResolvingEList<E>
/*    */   extends EObjectWithInverseEList<E>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public static class Unsettable<E>
/*    */     extends EObjectWithInverseEList.Unsettable<E>
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public static class ManyInverse<E>
/*    */       extends Unsettable<E>
/*    */     {
/*    */       private static final long serialVersionUID = 1L;
/*    */       
/*    */       public ManyInverse(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/* 38 */         super(dataClass, owner, featureID, inverseFeatureID);
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       protected boolean hasManyInverse() {
/* 44 */         return true;
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/* 50 */       super(dataClass, owner, featureID, inverseFeatureID);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected boolean hasProxies() {
/* 56 */       return true;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     protected E resolve(int index, E object) {
/* 63 */       return (E)resolve(index, (EObject)object);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ManyInverse<E>
/*    */     extends EObjectWithInverseResolvingEList<E>
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public ManyInverse(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/* 73 */       super(dataClass, owner, featureID, inverseFeatureID);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected boolean hasManyInverse() {
/* 79 */       return true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EObjectWithInverseResolvingEList(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/* 86 */     super(dataClass, owner, featureID, inverseFeatureID);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean hasProxies() {
/* 92 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected E resolve(int index, E object) {
/* 99 */     return (E)resolve(index, (EObject)object);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectWithInverseResolvingEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */