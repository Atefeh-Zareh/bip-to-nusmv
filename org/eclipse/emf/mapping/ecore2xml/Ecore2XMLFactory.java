/*    */ package org.eclipse.emf.mapping.ecore2xml;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLFactoryImpl;
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
/*    */ public interface Ecore2XMLFactory
/*    */   extends EFactory
/*    */ {
/* 36 */   public static final Ecore2XMLFactory eINSTANCE = Ecore2XMLFactoryImpl.init();
/*    */   
/*    */   XMLInfo createXMLInfo();
/*    */   
/*    */   XMLMap createXMLMap();
/*    */   
/*    */   Ecore2XMLPackage getEcore2XMLPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\Ecore2XMLFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */