/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.Resource;
/*    */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
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
/*    */ 
/*    */ public class XMLResourceFactoryImpl
/*    */   extends ResourceFactoryImpl
/*    */ {
/*    */   public Resource createResource(URI uri) {
/* 43 */     return (Resource)new XMLResourceImpl(uri);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLResourceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */