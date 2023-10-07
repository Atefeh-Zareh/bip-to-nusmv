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
/*    */ 
/*    */ class FactoryOverrideRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_FACTORY = "factory";
/*    */   static final String ATT_URI = "uri";
/*    */   static final String ATT_CLASS = "class";
/*    */   
/*    */   public FactoryOverrideRegistryReader() {
/* 42 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "factory_override");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 48 */     if (element.getName().equals("factory")) {
/*    */       
/* 50 */       String packageURI = element.getAttribute("uri");
/* 51 */       if (packageURI == null) {
/*    */         
/* 53 */         logMissingAttribute(element, "uri");
/*    */       }
/* 55 */       else if (element.getAttribute("class") == null) {
/*    */         
/* 57 */         logMissingAttribute(element, "class");
/*    */       } else {
/* 59 */         if (add) {
/*    */           
/* 61 */           Object object = EPackage.Registry.INSTANCE.get(packageURI);
/* 62 */           if (object instanceof EPackage.Descriptor) {
/*    */             
/* 64 */             EPackage.Registry.INSTANCE.put(packageURI, new RegistryReader.EFactoryDescriptor(element, "class", (EPackage.Descriptor)object));
/* 65 */             if (object instanceof RegistryReader.EFactoryDescriptor) {
/*    */               
/* 67 */               RegistryReader.EFactoryDescriptor descriptor = (RegistryReader.EFactoryDescriptor)object;
/* 68 */               EcorePlugin.INSTANCE.log(
/* 69 */                   "Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a factory override for '" + packageURI + "'");
/*    */             } 
/*    */           } 
/* 72 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 76 */         Object ePackageDescriptor = EPackage.Registry.INSTANCE.get(packageURI);
/* 77 */         if (ePackageDescriptor instanceof RegistryReader.EFactoryDescriptor)
/*    */         {
/* 79 */           EPackage.Registry.INSTANCE.put(packageURI, ((RegistryReader.EFactoryDescriptor)ePackageDescriptor).getOverridenDescriptor());
/*    */         }
/* 81 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\FactoryOverrideRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */