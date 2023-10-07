/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.emf.ecore.EStructuralFeature;
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
/*    */ class SettingDelegateFactoryRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_FACTORY = "factory";
/*    */   static final String ATT_URI = "uri";
/*    */   static final String ATT_CLASS = "class";
/*    */   
/*    */   static class SettingDelegateFactoryDescriptor
/*    */     extends RegistryReader.PluginClassDescriptor
/*    */     implements EStructuralFeature.Internal.SettingDelegate.Factory.Descriptor
/*    */   {
/*    */     protected EStructuralFeature.Internal.SettingDelegate.Factory factory;
/*    */     
/*    */     public SettingDelegateFactoryDescriptor(IConfigurationElement e, String attrName) {
/* 38 */       super(e, attrName);
/*    */     }
/*    */ 
/*    */     
/*    */     public EStructuralFeature.Internal.SettingDelegate.Factory getFactory() {
/* 43 */       if (this.factory == null)
/*    */       {
/* 45 */         this.factory = (EStructuralFeature.Internal.SettingDelegate.Factory)createInstance();
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
/*    */   public SettingDelegateFactoryRegistryReader() {
/* 57 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "setting_delegate");
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
/* 76 */           Object previous = EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(uri, new SettingDelegateFactoryDescriptor(element, "class"));
/* 77 */           if (previous instanceof SettingDelegateFactoryDescriptor) {
/*    */             
/* 79 */             SettingDelegateFactoryDescriptor descriptor = (SettingDelegateFactoryDescriptor)previous;
/* 80 */             EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a setting delegate factory for '" + uri + "'");
/*    */           } 
/* 82 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 86 */         EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.remove(uri);
/* 87 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\SettingDelegateFactoryRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */