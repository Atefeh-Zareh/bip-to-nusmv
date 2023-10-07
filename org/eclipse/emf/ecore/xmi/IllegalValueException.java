/*    */ package org.eclipse.emf.ecore.xmi;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.EStructuralFeature;
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
/*    */ public class IllegalValueException
/*    */   extends XMIException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected transient EObject object;
/*    */   protected transient EStructuralFeature feature;
/*    */   protected transient Object value;
/*    */   
/*    */   public IllegalValueException(EObject object, EStructuralFeature feature, Object value, Exception emfException, String location, int line, int column) {
/* 33 */     super("Value '" + value + "' is not legal.", emfException, location, line, column);
/* 34 */     this.object = object;
/* 35 */     this.feature = feature;
/* 36 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public EObject getObject() {
/* 41 */     return this.object;
/*    */   }
/*    */ 
/*    */   
/*    */   public EStructuralFeature getFeature() {
/* 46 */     return this.feature;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 51 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\IllegalValueException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */