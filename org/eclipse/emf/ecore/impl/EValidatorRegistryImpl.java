/*    */ package org.eclipse.emf.ecore.impl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.eclipse.emf.ecore.EPackage;
/*    */ import org.eclipse.emf.ecore.EValidator;
/*    */ import org.eclipse.emf.ecore.util.EObjectValidator;
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
/*    */ public class EValidatorRegistryImpl
/*    */   extends HashMap<EPackage, Object>
/*    */   implements EValidator.Registry
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected EValidator.Registry delegateRegistry;
/*    */   
/*    */   public EValidatorRegistryImpl() {}
/*    */   
/*    */   public EValidatorRegistryImpl(EValidator.Registry delegateRegistry) {
/* 44 */     this.delegateRegistry = delegateRegistry;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object get(Object key) {
/* 50 */     Object eValidator = super.get(key);
/* 51 */     if (eValidator instanceof EValidator.Descriptor) {
/*    */       
/* 53 */       EValidator.Descriptor eValidatorDescriptor = (EValidator.Descriptor)eValidator;
/* 54 */       eValidator = eValidatorDescriptor.getEValidator();
/* 55 */       put((EPackage)key, eValidator);
/* 56 */       return eValidator;
/*    */     } 
/* 58 */     if (eValidator != null)
/*    */     {
/* 60 */       return eValidator;
/*    */     }
/*    */ 
/*    */     
/* 64 */     return delegatedGet(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EValidator getEValidator(EPackage ePackage) {
/* 70 */     return (EValidator)get(ePackage);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Object delegatedGet(Object key) {
/* 75 */     if (this.delegateRegistry != null)
/*    */     {
/* 77 */       return this.delegateRegistry.get(key);
/*    */     }
/*    */     
/* 80 */     return (key == null) ? EObjectValidator.INSTANCE : null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object key) {
/* 86 */     return !(!super.containsKey(key) && (this.delegateRegistry == null || !this.delegateRegistry.containsKey(key)));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EValidatorRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */