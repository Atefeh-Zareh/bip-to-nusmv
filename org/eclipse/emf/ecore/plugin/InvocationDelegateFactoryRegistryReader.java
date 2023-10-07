/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.emf.ecore.EOperation;
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
/*    */ class InvocationDelegateFactoryRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_FACTORY = "factory";
/*    */   static final String ATT_URI = "uri";
/*    */   static final String ATT_CLASS = "class";
/*    */   
/*    */   static class InvocationDelegateFactoryDescriptor
/*    */     extends RegistryReader.PluginClassDescriptor
/*    */     implements EOperation.Internal.InvocationDelegate.Factory.Descriptor
/*    */   {
/*    */     protected EOperation.Internal.InvocationDelegate.Factory factory;
/*    */     
/*    */     public InvocationDelegateFactoryDescriptor(IConfigurationElement e, String attrName) {
/* 38 */       super(e, attrName);
/*    */     }
/*    */ 
/*    */     
/*    */     public EOperation.Internal.InvocationDelegate.Factory getFactory() {
/* 43 */       if (this.factory == null)
/*    */       {
/* 45 */         this.factory = (EOperation.Internal.InvocationDelegate.Factory)createInstance();
/*    */       }
/* 47 */       return this.factory;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvocationDelegateFactoryRegistryReader() {
/* 57 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "invocation_delegate");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 63 */     if (element.getName().equals("factory")) {
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
/* 76 */           Object previous = EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(uri, new InvocationDelegateFactoryDescriptor(element, "class"));
/* 77 */           if (previous instanceof InvocationDelegateFactoryDescriptor) {
/*    */             
/* 79 */             InvocationDelegateFactoryDescriptor descriptor = (InvocationDelegateFactoryDescriptor)previous;
/* 80 */             EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an invocation delegate factory for '" + uri + "'");
/*    */           } 
/* 82 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 86 */         EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.remove(uri);
/* 87 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\InvocationDelegateFactoryRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */