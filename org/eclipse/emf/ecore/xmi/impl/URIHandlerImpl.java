/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.xmi.XMLResource;
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
/*    */ public class URIHandlerImpl
/*    */   implements XMLResource.URIHandler
/*    */ {
/*    */   protected URI baseURI;
/*    */   protected boolean resolve;
/*    */   
/*    */   public static class PlatformSchemeAware
/*    */     extends URIHandlerImpl
/*    */   {
/*    */     public URI deresolve(URI uri) {
/* 36 */       return (!uri.isPlatform() || (uri.segmentCount() > 0 && this.baseURI.segmentCount() > 0 && uri.segment(0).equals(this.baseURI.segment(0)))) ? 
/* 37 */         super.deresolve(uri) : uri;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBaseURI(URI uri) {
/* 46 */     this.baseURI = uri;
/* 47 */     this.resolve = (uri != null && uri.isHierarchical() && !uri.isRelative());
/*    */   }
/*    */ 
/*    */   
/*    */   public URI resolve(URI uri) {
/* 52 */     return (this.resolve && uri.isRelative() && uri.hasRelativePath()) ? uri.resolve(this.baseURI) : uri;
/*    */   }
/*    */ 
/*    */   
/*    */   public URI deresolve(URI uri) {
/* 57 */     if (this.resolve && !uri.isRelative()) {
/*    */       
/* 59 */       URI deresolvedURI = uri.deresolve(this.baseURI, true, true, false);
/* 60 */       if (deresolvedURI.hasRelativePath())
/*    */       {
/* 62 */         uri = deresolvedURI;
/*    */       }
/*    */     } 
/* 65 */     return uri;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\URIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */