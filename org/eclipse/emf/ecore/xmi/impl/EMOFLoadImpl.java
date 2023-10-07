/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
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
/*    */ public class EMOFLoadImpl
/*    */   extends XMILoadImpl
/*    */ {
/*    */   public EMOFLoadImpl(EMOFHandler.Helper helper) {
/* 26 */     super(helper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected DefaultHandler makeDefaultHandler() {
/* 32 */     return new EMOFHandler(this.resource, (EMOFHandler.Helper)this.helper, this.options);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFLoadImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */