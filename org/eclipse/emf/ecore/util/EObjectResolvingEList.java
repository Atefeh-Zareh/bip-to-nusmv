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
/*    */ 
/*    */ public class EObjectResolvingEList<E>
/*    */   extends EObjectEList<E>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public static class Unsettable<E>
/*    */     extends EObjectEList.Unsettable<E>
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID) {
/* 34 */       super(dataClass, owner, featureID);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected boolean hasProxies() {
/* 40 */       return true;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     protected E resolve(int index, E object) {
/* 47 */       return (E)resolve(index, (EObject)object);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EObjectResolvingEList(Class<?> dataClass, InternalEObject owner, int featureID) {
/* 53 */     super(dataClass, owner, featureID);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean hasProxies() {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected E resolve(int index, E object) {
/* 66 */     return (E)resolve(index, (EObject)object);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectResolvingEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */