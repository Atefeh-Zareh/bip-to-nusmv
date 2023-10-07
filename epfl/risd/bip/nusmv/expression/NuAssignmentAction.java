/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuAssignmentAction
/*    */   implements NuAction
/*    */ {
/*    */   private NuVariable target;
/*    */   private NuExpression value;
/*    */   
/*    */   public NuAssignmentAction() {
/* 12 */     this.target = new NuVariable();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuAssignmentAction(NuVariable t, NuExpression v) {
/* 17 */     this.target = new NuVariable(t);
/* 18 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuAssignmentAction(NuAssignmentAction n) {
/* 23 */     this.target = new NuVariable(n.target);
/* 24 */     this.value = n.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTarget(NuVariable t) {
/* 29 */     this.target = new NuVariable(t);
/*    */   }
/*    */   
/*    */   public void setValue(NuExpression v) {
/* 33 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuVariable getTarget() {
/* 38 */     return this.target;
/*    */   }
/*    */   
/*    */   public NuExpression getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String s = "";
/*    */     
/* 49 */     s = String.valueOf(s) + this.target.getName();
/* 50 */     s = String.valueOf(s) + "\t\t";
/* 51 */     s = String.valueOf(s) + ":=";
/* 52 */     s = String.valueOf(s) + "\t\t";
/* 53 */     s = String.valueOf(s) + NuExpressionHelper.expressionToString(this.value);
/*    */     
/* 55 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuAssignmentAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */