/*    */ package org.eclipse.emf.ecore.xmi;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EObject;
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
/*    */ public class FeatureNotFoundException
/*    */   extends XMIException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String featureName;
/*    */   protected transient EObject object;
/*    */   
/*    */   public FeatureNotFoundException(String name, EObject object, String location, int line, int column) {
/* 30 */     super("Feature '" + name + "' not found.", location, line, column);
/* 31 */     this.featureName = name;
/* 32 */     this.object = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return this.featureName;
/*    */   }
/*    */ 
/*    */   
/*    */   public EObject getObject() {
/* 42 */     return this.object;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\FeatureNotFoundException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */