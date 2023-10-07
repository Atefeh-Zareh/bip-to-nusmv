/*    */ package org.eclipse.emf.mapping.ecore2xml.util;
/*    */ 
/*    */ import org.eclipse.emf.ecore.resource.Resource;
/*    */ import org.eclipse.emf.ecore.xmi.XMIResource;
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
/*    */ public interface Ecore2XMLResource
/*    */   extends XMIResource
/*    */ {
/*    */   public static final String FILE_EXTENSION = "ecore2xml";
/*    */   public static final String DEFAULT_ENCODING = "UTF-8";
/*    */   
/*    */   public static interface Factory
/*    */     extends Resource.Factory
/*    */   {
/* 34 */     public static final Factory INSTANCE = new Ecore2XMLResourceFactoryImpl();
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xm\\util\Ecore2XMLResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */