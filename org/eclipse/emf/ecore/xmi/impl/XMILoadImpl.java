/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*    */ import org.xml.sax.helpers.DefaultHandler;
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
/*    */ public class XMILoadImpl
/*    */   extends XMLLoadImpl
/*    */ {
/*    */   public XMILoadImpl(XMLHelper helper) {
/* 33 */     super(helper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected DefaultHandler makeDefaultHandler() {
/* 39 */     return new SAXXMIHandler(this.resource, this.helper, this.options);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMILoadImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */