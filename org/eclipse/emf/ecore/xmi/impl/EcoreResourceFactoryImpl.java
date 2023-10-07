/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.Resource;
/*    */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
/*    */ import org.eclipse.emf.ecore.xmi.XMLResource;
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
/*    */ public class EcoreResourceFactoryImpl
/*    */   extends ResourceFactoryImpl
/*    */ {
/*    */   public Resource createResource(URI uri) {
/* 39 */     XMLResource result = 
/* 40 */       new XMIResourceImpl(uri)
/*    */       {
/*    */         
/*    */         protected boolean useIDs()
/*    */         {
/* 45 */           return !(this.eObjectToIDMap == null && this.idToEObjectMap == null);
/*    */         }
/*    */       };
/* 48 */     result.setEncoding("UTF-8");
/*    */     
/* 50 */     result.getDefaultSaveOptions().put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/* 51 */     result.getDefaultSaveOptions().put("LINE_WIDTH", Integer.valueOf(80));
/* 52 */     result.getDefaultSaveOptions().put("URI_HANDLER", new URIHandlerImpl.PlatformSchemeAware());
/* 53 */     return (Resource)result;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EcoreResourceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */