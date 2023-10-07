/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuTransExpression
/*    */   implements NuExpression
/*    */ {
/*    */   private List<NuExpression> trans;
/*    */   
/*    */   public NuTransExpression() {
/* 14 */     this.trans = new LinkedList<NuExpression>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuTransExpression(List<NuExpression> exps) {
/* 19 */     this.trans = new LinkedList<NuExpression>();
/* 20 */     if (exps != null) this.trans.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public NuTransExpression(NuTransExpression n) {
/* 25 */     this.trans = new LinkedList<NuExpression>();
/* 26 */     if (n.trans != null) this.trans.addAll(n.trans);
/*    */   
/*    */   }
/*    */   
/*    */   public void setExpression(List<NuExpression> exps) {
/* 31 */     this.trans = exps;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NuExpression> getExpression() {
/* 36 */     return this.trans;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addExpression(NuExpression exp) {
/* 41 */     if (exp != null) this.trans.add(exp);
/*    */   
/*    */   }
/*    */   
/*    */   public void addExpressions(List<NuExpression> exps) {
/* 46 */     if (exps != null) this.trans.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString(boolean main) {
/* 51 */     String s = "";
/* 52 */     String seperator = "|";
/* 53 */     if (main) seperator = "&"; 
/* 54 */     boolean first = true;
/*    */     
/* 56 */     for (NuExpression tran : this.trans) {
/*    */       
/* 58 */       if (!first) s = String.valueOf(s) + " " + seperator + "\n"; 
/* 59 */       s = String.valueOf(s) + "\t\t";
/* 60 */       s = String.valueOf(s) + NuExpressionHelper.expressionToString(tran);
/* 61 */       first = false;
/*    */     } 
/*    */     
/* 64 */     s = String.valueOf(s) + ";";
/*    */     
/* 66 */     return s;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     return toString(false);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuTransExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */