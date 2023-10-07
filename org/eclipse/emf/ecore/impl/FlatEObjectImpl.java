/*    */ package org.eclipse.emf.ecore.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.util.EList;
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.util.EContentsEList;
/*    */ import org.eclipse.emf.ecore.util.ECrossReferenceEList;
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
/*    */ public class FlatEObjectImpl
/*    */   extends EObjectImpl
/*    */ {
/*    */   protected URI eProxyURI;
/*    */   protected EList<EObject> eContents;
/*    */   protected EList<EObject> eCrossReferences;
/*    */   
/*    */   protected BasicEObjectImpl.EPropertiesHolder eProperties() {
/* 53 */     if (this.eProperties == null)
/*    */     {
/* 55 */       this.eProperties = new BasicEObjectImpl.EPropertiesHolderBaseImpl();
/*    */     }
/* 57 */     return this.eProperties;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean eIsProxy() {
/* 63 */     return (this.eProxyURI != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public URI eProxyURI() {
/* 69 */     return this.eProxyURI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eSetProxyURI(URI uri) {
/* 75 */     this.eProxyURI = uri;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EList<EObject> eContents() {
/* 81 */     if (this.eContents == null)
/*    */     {
/* 83 */       this.eContents = (EList<EObject>)EContentsEList.createEContentsEList(this);
/*    */     }
/* 85 */     return this.eContents;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EList<EObject> eCrossReferences() {
/* 91 */     if (this.eCrossReferences == null)
/*    */     {
/* 93 */       this.eCrossReferences = (EList<EObject>)ECrossReferenceEList.createECrossReferenceEList(this);
/*    */     }
/* 95 */     return this.eCrossReferences;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\FlatEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */