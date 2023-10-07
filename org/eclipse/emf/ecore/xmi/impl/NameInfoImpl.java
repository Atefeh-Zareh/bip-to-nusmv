/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.ecore.xmi.NameInfo;
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
/*    */ public class NameInfoImpl
/*    */   implements NameInfo
/*    */ {
/*    */   protected String localPart;
/*    */   protected String qualifiedName;
/*    */   protected String namespaceURI;
/*    */   
/*    */   public String getLocalPart() {
/* 33 */     return this.localPart;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 38 */     return this.namespaceURI;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getQualifiedName() {
/* 43 */     return this.qualifiedName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLocalPart(String name) {
/* 48 */     this.localPart = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNamespaceURI(String uri) {
/* 53 */     this.namespaceURI = uri;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setQualifiedName(String name) {
/* 58 */     this.qualifiedName = name;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\NameInfoImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */