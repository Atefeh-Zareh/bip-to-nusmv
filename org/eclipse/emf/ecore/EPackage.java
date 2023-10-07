/*    */ package org.eclipse.emf.ecore;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.eclipse.emf.common.util.EList;
/*    */ import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
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
/*    */ public interface EPackage
/*    */   extends ENamedElement
/*    */ {
/*    */   String getNsURI();
/*    */   
/*    */   void setNsURI(String paramString);
/*    */   
/*    */   String getNsPrefix();
/*    */   
/*    */   void setNsPrefix(String paramString);
/*    */   
/*    */   EFactory getEFactoryInstance();
/*    */   
/*    */   void setEFactoryInstance(EFactory paramEFactory);
/*    */   
/*    */   EList<EClassifier> getEClassifiers();
/*    */   
/*    */   EList<EPackage> getESubpackages();
/*    */   
/*    */   EPackage getESuperPackage();
/*    */   
/*    */   EClassifier getEClassifier(String paramString);
/*    */   
/*    */   public static interface Descriptor
/*    */   {
/*    */     EPackage getEPackage();
/*    */     
/*    */     EFactory getEFactory();
/*    */   }
/*    */   
/*    */   public static interface Registry
/*    */     extends Map<String, Object>
/*    */   {
/* 81 */     public static final Registry INSTANCE = EPackageRegistryImpl.createGlobalRegistry();
/*    */     
/*    */     EPackage getEPackage(String param1String);
/*    */     
/*    */     EFactory getEFactory(String param1String);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EPackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */