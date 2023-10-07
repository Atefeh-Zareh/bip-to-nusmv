/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.emf.ecore.resource.Resource;
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
/*    */ class ExtensionParserRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_PARSER = "parser";
/*    */   static final String ATT_TYPE = "type";
/*    */   static final String ATT_CLASS = "class";
/*    */   
/*    */   public ExtensionParserRegistryReader() {
/* 43 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "extension_parser");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 49 */     if (element.getName().equals("parser")) {
/*    */       
/* 51 */       String type = element.getAttribute("type");
/* 52 */       if (type == null) {
/*    */         
/* 54 */         logMissingAttribute(element, "type");
/*    */       }
/* 56 */       else if (element.getAttribute("class") == null) {
/*    */         
/* 58 */         logMissingAttribute(element, "class");
/*    */       } else {
/* 60 */         if (add) {
/*    */           
/* 62 */           Object previous = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(type, new RegistryReader.ResourceFactoryDescriptor(element, "class"));
/* 63 */           if (previous instanceof RegistryReader.ResourceFactoryDescriptor) {
/*    */             
/* 65 */             RegistryReader.ResourceFactoryDescriptor descriptor = (RegistryReader.ResourceFactoryDescriptor)previous;
/* 66 */             EcorePlugin.INSTANCE.log(
/* 67 */                 "Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an extension parser for '" + type + "'");
/*    */           } 
/* 69 */           return true;
/*    */         } 
/*    */ 
/*    */         
/* 73 */         Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().remove(type);
/* 74 */         return true;
/*    */       } 
/*    */     } 
/* 77 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\ExtensionParserRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */