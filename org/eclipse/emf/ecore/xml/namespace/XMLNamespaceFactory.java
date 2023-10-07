/*    */ package org.eclipse.emf.ecore.xml.namespace;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import org.eclipse.emf.ecore.xml.namespace.impl.XMLNamespaceFactoryImpl;
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
/*    */ public interface XMLNamespaceFactory
/*    */   extends EFactory
/*    */ {
/* 36 */   public static final XMLNamespaceFactory eINSTANCE = XMLNamespaceFactoryImpl.init();
/*    */   
/*    */   XMLNamespaceDocumentRoot createXMLNamespaceDocumentRoot();
/*    */   
/*    */   XMLNamespacePackage getXMLNamespacePackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\XMLNamespaceFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */