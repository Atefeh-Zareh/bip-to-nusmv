/*    */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*    */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;
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
/*    */ public class Ecore2XMLRegistryImpl
/*    */   extends HashMap<String, Object>
/*    */   implements Ecore2XMLRegistry
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected Ecore2XMLRegistry delegateRegistry;
/*    */   
/*    */   public Ecore2XMLRegistryImpl() {}
/*    */   
/*    */   public Ecore2XMLRegistryImpl(Ecore2XMLRegistry delegateRegistry) {
/* 40 */     this();
/*    */     
/* 42 */     this.delegateRegistry = delegateRegistry;
/*    */   }
/*    */ 
/*    */   
/*    */   public XMLResource.XMLMap getXMLMap(String nsURI) {
/* 47 */     Object value = get(nsURI);
/*    */     
/* 49 */     if (value instanceof XMLResource.XMLMap)
/*    */     {
/* 51 */       return (XMLResource.XMLMap)value;
/*    */     }
/* 53 */     if (value instanceof Ecore2XMLRegistry.Descriptor) {
/*    */       
/* 55 */       XMLResource.XMLMap xmlMap = ((Ecore2XMLRegistry.Descriptor)value).getXMLMap();
/* 56 */       put(nsURI, xmlMap);
/*    */       
/* 58 */       return xmlMap;
/*    */     } 
/*    */ 
/*    */     
/* 62 */     return delegatedGetXMLMap(nsURI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected XMLResource.XMLMap delegatedGetXMLMap(String nsURI) {
/* 68 */     return (this.delegateRegistry == null) ? null : this.delegateRegistry.getXMLMap(nsURI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object key) {
/* 74 */     return !(!super.containsKey(key) && (this.delegateRegistry == null || !this.delegateRegistry.containsKey(key)));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\Ecore2XMLRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */