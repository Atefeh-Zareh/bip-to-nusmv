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
/*    */ public class UnresolvedReferenceException
/*    */   extends XMIException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String reference;
/*    */   
/*    */   public UnresolvedReferenceException(String reference, String location, int line, int column) {
/* 27 */     super("Unresolved reference '" + reference + "'.", location, line, column);
/* 28 */     this.reference = reference;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getReference() {
/* 33 */     return this.reference;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\UnresolvedReferenceException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */