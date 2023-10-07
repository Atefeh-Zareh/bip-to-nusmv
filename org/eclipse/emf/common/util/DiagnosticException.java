/*    */ package org.eclipse.emf.common.util;
/*    */ 
/*    */ import org.eclipse.core.runtime.CoreException;
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
/*    */ public class DiagnosticException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Diagnostic diagnostic;
/*    */   
/*    */   public DiagnosticException(Diagnostic diagnostic) {
/* 36 */     super(diagnostic.getMessage(), diagnostic.getException());
/* 37 */     this.diagnostic = diagnostic;
/*    */   }
/*    */ 
/*    */   
/*    */   public final Diagnostic getDiagnostic() {
/* 42 */     return this.diagnostic;
/*    */   }
/*    */ 
/*    */   
/*    */   public static CoreException toCoreException(DiagnosticException exception) {
/* 47 */     return new CoreException(BasicDiagnostic.toIStatus(exception));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\DiagnosticException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */