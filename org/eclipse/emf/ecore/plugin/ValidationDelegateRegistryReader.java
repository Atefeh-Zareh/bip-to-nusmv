/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
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
/*    */ class ValidationDelegateRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_DELEGATE = "delegate";
/*    */   static final String ATT_URI = "uri";
/*    */   static final String ATT_CLASS = "class";
/*    */   
/*    */   static class ValidationDelegateDescriptor
/*    */     extends RegistryReader.PluginClassDescriptor
/*    */     implements EValidator.ValidationDelegate.Descriptor
/*    */   {
/*    */     protected EValidator.ValidationDelegate validationDelegate;
/*    */     
/*    */     public ValidationDelegateDescriptor(IConfigurationElement e, String attrName) {
/* 38 */       super(e, attrName);
/*    */     }
/*    */ 
/*    */     
/*    */     public EValidator.ValidationDelegate getValidationDelegate() {
/* 43 */       if (this.validationDelegate == null)
/*    */       {
/* 45 */         this.validationDelegate = (EValidator.ValidationDelegate)createInstance();
/*    */       }
/* 47 */       return this.validationDelegate;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValidationDelegateRegistryReader() {
/* 57 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "validation_delegate");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 63 */     if (element.getName().equals("delegate")) {
/*    */       
/* 65 */       String uri = element.getAttribute("uri");
/* 66 */       if (uri == null) {
/*    */         
/* 68 */         logMissingAttribute(element, "uri");
/*    */       }
/* 70 */       else if (element.getAttribute("class") == null) {
/*    */         
/* 72 */         logMissingAttribute(element, "class");
/*    */       } else {
/* 74 */         if (add) {
/*    */           
/* 76 */           Object previous = EValidator.ValidationDelegate.Registry.INSTANCE.put(uri, new ValidationDelegateDescriptor(element, "class"));
/* 77 */           if (previous instanceof ValidationDelegateDescriptor) {
/*    */             
/* 79 */             ValidationDelegateDescriptor descriptor = (ValidationDelegateDescriptor)previous;
/* 80 */             EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a validation delegate for '" + uri + "'");
/*    */           } 
/* 82 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 86 */         EValidator.ValidationDelegate.Registry.INSTANCE.remove(uri);
/* 87 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\ValidationDelegateRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */