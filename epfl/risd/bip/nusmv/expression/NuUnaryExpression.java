/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuUnaryOperator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuUnaryExpression
/*    */   implements NuExpression
/*    */ {
/*    */   private NuUnaryOperator operator;
/*    */   private NuExpression operand;
/*    */   
/*    */   public NuUnaryExpression() {}
/*    */   
/*    */   public NuUnaryExpression(NuUnaryOperator op, NuExpression exp) {
/* 17 */     this.operator = op;
/* 18 */     this.operand = exp;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuUnaryExpression(NuUnaryExpression n) {
/* 23 */     this.operator = n.operator;
/* 24 */     this.operand = n.operand;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOperator(NuUnaryOperator op) {
/* 29 */     this.operator = op;
/*    */   }
/*    */   
/*    */   public void setOperand(NuExpression exp) {
/* 33 */     this.operand = exp;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuUnaryOperator getOperator() {
/* 38 */     return this.operator;
/*    */   }
/*    */   
/*    */   public NuExpression getOperand() {
/* 42 */     return this.operand;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String s = "";
/*    */     
/* 49 */     s = String.valueOf(s) + "( ";
/* 50 */     s = String.valueOf(s) + this.operator.toString();
/* 51 */     s = String.valueOf(s) + "(";
/* 52 */     s = String.valueOf(s) + NuExpressionHelper.expressionToString(this.operand);
/* 53 */     s = String.valueOf(s) + ") )";
/*    */     
/* 55 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuUnaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */