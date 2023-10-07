/*    */ package org.eclipse.emf.ecore.plugin;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.eclipse.core.runtime.IConfigurationElement;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.URIConverter;
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
/*    */ class URIMappingRegistryReader
/*    */   extends RegistryReader
/*    */ {
/*    */   static final String TAG_MAPPING = "mapping";
/*    */   static final String ATT_SOURCE = "source";
/*    */   static final String ATT_TARGET = "target";
/* 41 */   protected Map<URI, IConfigurationElement> map = new HashMap<URI, IConfigurationElement>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public URIMappingRegistryReader() {
/* 48 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "uri_mapping");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean readElement(IConfigurationElement element, boolean add) {
/* 54 */     if (element.getName().equals("mapping")) {
/*    */       
/* 56 */       String sourceURIValue = element.getAttribute("source");
/* 57 */       if (sourceURIValue == null) {
/*    */         
/* 59 */         logMissingAttribute(element, "source");
/*    */       }
/*    */       else {
/*    */         
/* 63 */         String targetURIValue = element.getAttribute("target");
/* 64 */         if (targetURIValue == null) {
/*    */           
/* 66 */           logMissingAttribute(element, "target");
/*    */         } else {
/* 68 */           if (add) {
/*    */             
/* 70 */             URI uRI1 = URI.createURI(sourceURIValue);
/* 71 */             URI targetURI = URI.createURI(targetURIValue);
/* 72 */             if (targetURI.isRelative() && targetURI.hasRelativePath())
/*    */             {
/* 74 */               targetURI = 
/* 75 */                 targetURI.resolve(
/* 76 */                   URI.createURI(
/* 77 */                     Platform.getBundle(element.getDeclaringExtension().getContributor().getName()).getEntry("/").toString()));
/*    */             }
/* 79 */             URIConverter.URI_MAP.put(uRI1, targetURI);
/* 80 */             IConfigurationElement previous = this.map.put(uRI1, element);
/* 81 */             if (previous != null)
/*    */             {
/* 83 */               EcorePlugin.INSTANCE.log(
/* 84 */                   "Both '" + previous.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a URI mapping for '" + uRI1 + "'");
/*    */             }
/* 86 */             return true;
/*    */           } 
/*    */ 
/*    */           
/* 90 */           URI sourceURI = URI.createURI(sourceURIValue);
/* 91 */           URIConverter.URI_MAP.remove(sourceURI);
/* 92 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/* 96 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\URIMappingRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */