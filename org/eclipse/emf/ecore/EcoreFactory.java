/*    */ package org.eclipse.emf.ecore;
/*    */ 
/*    */ import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
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
/*    */ public interface EcoreFactory
/*    */   extends EFactory
/*    */ {
/* 35 */   public static final EcoreFactory eINSTANCE = EcoreFactoryImpl.init();
/*    */   
/*    */   EObject createEObject();
/*    */   
/*    */   EAttribute createEAttribute();
/*    */   
/*    */   EAnnotation createEAnnotation();
/*    */   
/*    */   EClass createEClass();
/*    */   
/*    */   EDataType createEDataType();
/*    */   
/*    */   EParameter createEParameter();
/*    */   
/*    */   EOperation createEOperation();
/*    */   
/*    */   EPackage createEPackage();
/*    */   
/*    */   EFactory createEFactory();
/*    */   
/*    */   EEnumLiteral createEEnumLiteral();
/*    */   
/*    */   EEnum createEEnum();
/*    */   
/*    */   EReference createEReference();
/*    */   
/*    */   EGenericType createEGenericType();
/*    */   
/*    */   ETypeParameter createETypeParameter();
/*    */   
/*    */   EcorePackage getEcorePackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EcoreFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */