/*    */ package org.eclipse.emf.mapping.ecore2xml.util;
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
/*    */ public class Ecore2XMLResourceFactoryImpl
/*    */   extends ResourceFactoryImpl
/*    */   implements Ecore2XMLResource.Factory
/*    */ {
/*    */   public Resource createResource(URI uri) {
/* 56 */     Ecore2XMLResource result = new Ecore2XMLResourceImpl(uri);
/*    */     
/* 58 */     result.setEncoding("UTF-8");
/*    */     
/* 60 */     return (Resource)result;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xm\\util\Ecore2XMLResourceFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */