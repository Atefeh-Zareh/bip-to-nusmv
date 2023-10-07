/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.emf.ecore.EPackage;
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
/*    */ class DynamicPackageRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_RESOURCE = "resource";
/*    */   static final String ATT_URI = "uri";
/*    */   static final String ATT_LOCATION = "location";
/*    */   
/*    */   public DynamicPackageRegistryReader() {
/* 41 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "dynamic_package");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 47 */     if (element.getName().equals("resource")) {
/*    */       
/* 49 */       String packageURI = element.getAttribute("uri");
/* 50 */       if (packageURI == null) {
/*    */         
/* 52 */         logMissingAttribute(element, "uri");
/*    */       }
/* 54 */       else if (element.getAttribute("location") == null) {
/*    */         
/* 56 */         logMissingAttribute(element, "location");
/*    */       } else {
/* 58 */         if (add) {
/*    */           
/* 60 */           Object previous = EPackage.Registry.INSTANCE.put(packageURI, new RegistryReader.EPackageDescriptor.Dynamic(element, "location"));
/* 61 */           if (previous instanceof RegistryReader.PluginClassDescriptor) {
/*    */             
/* 63 */             RegistryReader.PluginClassDescriptor descriptor = (RegistryReader.PluginClassDescriptor)previous;
/* 64 */             EcorePlugin.INSTANCE.log(
/* 65 */                 "Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package for '" + packageURI + "'");
/*    */           } 
/*    */           
/* 68 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 72 */         EPackage.Registry.INSTANCE.remove(packageURI);
/* 73 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\DynamicPackageRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */