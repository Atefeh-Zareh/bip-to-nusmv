/*    */ package org.eclipse.emf.ecore.xmi;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
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
/*    */ public class ClassNotFoundException
/*    */   extends XMIException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String className;
/*    */   protected transient EFactory factory;
/*    */   
/*    */   public ClassNotFoundException(String name, EFactory factory, String location, int line, int column) {
/* 30 */     super("Class '" + name + "' is not found or is abstract.", location, line, column);
/* 31 */     this.className = name;
/* 32 */     this.factory = factory;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return this.className;
/*    */   }
/*    */ 
/*    */   
/*    */   public EFactory getFactory() {
/* 42 */     return this.factory;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\ClassNotFoundException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */