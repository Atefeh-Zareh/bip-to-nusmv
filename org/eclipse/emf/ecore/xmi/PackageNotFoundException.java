/*    */ package org.eclipse.emf.ecore.xmi;
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
/*    */ public class PackageNotFoundException
/*    */   extends XMIException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String uri;
/*    */   
/*    */   public PackageNotFoundException(String uri, String location, int line, int column) {
/* 27 */     super("Package with uri '" + uri + "' not found.", location, line, column);
/* 28 */     this.uri = uri;
/*    */   }
/*    */ 
/*    */   
/*    */   public String uri() {
/* 33 */     return this.uri;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\PackageNotFoundException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */