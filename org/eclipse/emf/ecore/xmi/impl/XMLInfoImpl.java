/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLInfoImpl
/*    */   implements XMLResource.XMLInfo
/*    */ {
/* 39 */   protected int xmlRepresentation = -1;
/*    */ 
/*    */ 
/*    */   
/*    */   protected String targetNamespace;
/*    */ 
/*    */   
/*    */   protected String name;
/*    */ 
/*    */ 
/*    */   
/*    */   public int getXMLRepresentation() {
/* 51 */     return this.xmlRepresentation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setXMLRepresentation(int representation) {
/* 60 */     this.xmlRepresentation = representation;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTargetNamespace() {
/* 65 */     return this.targetNamespace;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTargetNamespace(String namespaceURI) {
/* 70 */     this.targetNamespace = namespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 79 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 87 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLInfoImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */