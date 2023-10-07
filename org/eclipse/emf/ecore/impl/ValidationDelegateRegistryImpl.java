/*    */ package org.eclipse.emf.ecore.impl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.eclipse.emf.ecore.EValidator;
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
/*    */ public class ValidationDelegateRegistryImpl
/*    */   extends HashMap<String, Object>
/*    */   implements EValidator.ValidationDelegate.Registry
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected EValidator.ValidationDelegate.Registry delegateRegistry;
/*    */   
/*    */   public ValidationDelegateRegistryImpl() {}
/*    */   
/*    */   public ValidationDelegateRegistryImpl(EValidator.ValidationDelegate.Registry delegateRegistry) {
/* 40 */     this.delegateRegistry = delegateRegistry;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object get(Object key) {
/* 46 */     Object validationDelegate = super.get(key);
/* 47 */     if (validationDelegate instanceof EValidator.ValidationDelegate.Descriptor) {
/*    */       
/* 49 */       EValidator.ValidationDelegate.Descriptor validationDelegateDescriptor = (EValidator.ValidationDelegate.Descriptor)validationDelegate;
/* 50 */       validationDelegate = validationDelegateDescriptor.getValidationDelegate();
/* 51 */       put((String)key, validationDelegate);
/* 52 */       return validationDelegate;
/*    */     } 
/* 54 */     if (validationDelegate != null)
/*    */     {
/* 56 */       return validationDelegate;
/*    */     }
/*    */ 
/*    */     
/* 60 */     return delegatedGet(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EValidator.ValidationDelegate getValidationDelegate(String uri) {
/* 66 */     return (EValidator.ValidationDelegate)get(uri);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Object delegatedGet(Object key) {
/* 71 */     if (this.delegateRegistry != null)
/*    */     {
/* 73 */       return this.delegateRegistry.get(key);
/*    */     }
/*    */     
/* 76 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object key) {
/* 82 */     return !(!super.containsKey(key) && (this.delegateRegistry == null || !this.delegateRegistry.containsKey(key)));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ValidationDelegateRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */