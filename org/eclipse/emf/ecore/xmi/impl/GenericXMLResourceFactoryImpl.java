/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.Resource;
/*    */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
/*    */ import org.eclipse.emf.ecore.xmi.XMLOptions;
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
/*    */ public class GenericXMLResourceFactoryImpl
/*    */   extends ResourceFactoryImpl
/*    */ {
/*    */   public Resource createResource(URI uri) {
/* 37 */     XMLResource result = new XMLResourceImpl(uri);
/* 38 */     result.setEncoding("UTF-8");
/*    */     
/* 40 */     result.getDefaultSaveOptions().put("EXTENDED_META_DATA", Boolean.TRUE);
/* 41 */     result.getDefaultLoadOptions().put("EXTENDED_META_DATA", Boolean.TRUE);
/*    */     
/* 43 */     result.getDefaultLoadOptions().put("USE_LEXICAL_HANDLER", Boolean.TRUE);
/*    */     
/* 45 */     result.getDefaultSaveOptions().put("LINE_WIDTH", Integer.valueOf(80));
/*    */     
/* 47 */     result.getDefaultSaveOptions().put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/* 48 */     result.getDefaultLoadOptions().put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/*    */     
/* 50 */     result.getDefaultSaveOptions().put("SCHEMA_LOCATION", Boolean.TRUE);
/*    */     
/* 52 */     XMLOptions xmlOptions = new XMLOptionsImpl();
/*    */     
/* 54 */     xmlOptions.setProcessAnyXML(true);
/*    */     
/* 56 */     xmlOptions.setProcessSchemaLocations(true);
/*    */     
/* 58 */     result.getDefaultLoadOptions().put("XML_OPTIONS", xmlOptions);
/*    */     
/* 60 */     return (Resource)result;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\GenericXMLResourceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */