/*    */ package org.eclipse.emf.ecore.util;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import org.eclipse.emf.common.util.EList;
/*    */ import org.eclipse.emf.ecore.EOperation;
/*    */ import org.eclipse.emf.ecore.EStructuralFeature;
/*    */ import org.eclipse.emf.ecore.EcorePackage;
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
/*    */ public class BasicInvocationDelegate
/*    */   implements EOperation.Internal.InvocationDelegate
/*    */ {
/*    */   protected EOperation eOperation;
/*    */   
/*    */   public BasicInvocationDelegate(EOperation operation) {
/* 51 */     this.eOperation = operation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object dynamicInvoke(InternalEObject target, EList<?> arguments) throws InvocationTargetException {
/* 56 */     if (this.eOperation.getEContainingClass() == EcorePackage.Literals.EOBJECT)
/*    */     {
/*    */       
/* 59 */       switch (this.eOperation.getEContainingClass().getEAllOperations().indexOf(this.eOperation)) {
/*    */         
/*    */         case 0:
/* 62 */           return target.eClass();
/*    */         case 1:
/* 64 */           return Boolean.valueOf(target.eIsProxy());
/*    */         case 2:
/* 66 */           return target.eResource();
/*    */         case 3:
/* 68 */           return target.eContainer();
/*    */         case 4:
/* 70 */           return target.eContainingFeature();
/*    */         case 5:
/* 72 */           return target.eContainmentFeature();
/*    */         case 6:
/* 74 */           return target.eContents();
/*    */         case 7:
/* 76 */           return target.eAllContents();
/*    */         case 8:
/* 78 */           return target.eCrossReferences();
/*    */         case 9:
/* 80 */           return target.eGet((EStructuralFeature)arguments.get(0));
/*    */         case 10:
/* 82 */           return target.eGet((EStructuralFeature)arguments.get(0), ((Boolean)arguments.get(1)).booleanValue());
/*    */         case 11:
/* 84 */           target.eSet((EStructuralFeature)arguments.get(0), arguments.get(1));
/* 85 */           return null;
/*    */         case 12:
/* 87 */           return Boolean.valueOf(target.eIsSet((EStructuralFeature)arguments.get(0)));
/*    */         case 13:
/* 89 */           target.eUnset((EStructuralFeature)arguments.get(0));
/* 90 */           return null;
/*    */         case 14:
/* 92 */           return target.eInvoke((EOperation)arguments.get(0), (EList)arguments.get(1));
/*    */       } 
/*    */     
/*    */     }
/* 96 */     throw new UnsupportedOperationException("eInvoke not implemented for " + this.eOperation.getName());
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\BasicInvocationDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */