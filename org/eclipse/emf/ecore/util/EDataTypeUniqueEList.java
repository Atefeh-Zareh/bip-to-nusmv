/*    */ package org.eclipse.emf.ecore.util;
/*    */ 
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
/*    */ public class EDataTypeUniqueEList<E>
/*    */   extends EDataTypeEList<E>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public static class Unsettable<E>
/*    */     extends EDataTypeEList.Unsettable<E>
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID) {
/* 33 */       super(dataClass, owner, featureID);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected boolean isUnique() {
/* 39 */       return true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EDataTypeUniqueEList(Class<?> dataClass, InternalEObject owner, int featureID) {
/* 45 */     super(dataClass, owner, featureID);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isUnique() {
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EDataTypeUniqueEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */