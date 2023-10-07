/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuBinaryOperator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuBinaryExpression
/*    */   implements NuExpression
/*    */ {
/*    */   private NuBinaryOperator operator;
/*    */   private NuExpression leftOperand;
/*    */   private NuExpression rightOperand;
/*    */   
/*    */   public NuBinaryExpression() {}
/*    */   
/*    */   public NuBinaryExpression(NuBinaryOperator op, NuExpression lo, NuExpression ro) {
/* 18 */     this.operator = op;
/* 19 */     this.leftOperand = lo;
/* 20 */     this.rightOperand = ro;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuBinaryExpression(NuBinaryExpression n) {
/* 25 */     this.operator = n.operator;
/* 26 */     this.leftOperand = n.leftOperand;
/* 27 */     this.rightOperand = n.rightOperand;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOperator(NuBinaryOperator op) {
/* 32 */     this.operator = op;
/*    */   }
/*    */   
/*    */   public void setLeftOperand(NuExpression lo) {
/* 36 */     this.leftOperand = lo;
/*    */   }
/*    */   
/*    */   public void setRightOperand(NuExpression ro) {
/* 40 */     this.rightOperand = ro;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuBinaryOperator getOperator() {
/* 45 */     return this.operator;
/*    */   }
/*    */   
/*    */   public NuExpression getLeftOperand() {
/* 49 */     return this.leftOperand;
/*    */   }
/*    */   
/*    */   public NuExpression getRightOperand() {
/* 53 */     return this.rightOperand;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String s = "";
/*    */     
/* 60 */     s = String.valueOf(s) + "( (";
/* 61 */     s = String.valueOf(s) + NuExpressionHelper.expressionToString(this.leftOperand);
/* 62 */     s = String.valueOf(s) + ") ";
/* 63 */     s = String.valueOf(s) + this.operator.toString();
/* 64 */     s = String.valueOf(s) + " (";
/* 65 */     s = String.valueOf(s) + NuExpressionHelper.expressionToString(this.rightOperand);
/* 66 */     s = String.valueOf(s) + ") )";
/*    */     
/* 68 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuBinaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */