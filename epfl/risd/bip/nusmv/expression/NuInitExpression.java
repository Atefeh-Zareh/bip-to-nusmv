/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuInitExpression
/*    */   implements NuExpression
/*    */ {
/*    */   private List<NuExpression> inits;
/*    */   
/*    */   public NuInitExpression() {
/* 14 */     this.inits = new LinkedList<NuExpression>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuInitExpression(List<NuExpression> exps) {
/* 19 */     this.inits = new LinkedList<NuExpression>();
/* 20 */     if (exps != null) this.inits.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public NuInitExpression(NuInitExpression n) {
/* 25 */     this.inits = new LinkedList<NuExpression>();
/* 26 */     if (n.inits != null) this.inits.addAll(n.inits);
/*    */   
/*    */   }
/*    */   
/*    */   public void setExpression(List<NuExpression> exps) {
/* 31 */     this.inits = exps;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NuExpression> getExpression() {
/* 36 */     return this.inits;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addExpression(NuExpression exp) {
/* 41 */     if (exp != null) this.inits.add(exp);
/*    */   
/*    */   }
/*    */   
/*    */   public void addExpressions(List<NuExpression> exps) {
/* 46 */     if (exps != null) this.inits.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     String s = "";
/* 52 */     boolean first = true;
/*    */     
/* 54 */     for (NuExpression init : this.inits) {
/*    */       
/* 56 */       if (!first) s = String.valueOf(s) + " &\n"; 
/* 57 */       s = String.valueOf(s) + "\t\t";
/* 58 */       s = String.valueOf(s) + NuExpressionHelper.expressionToString(init);
/* 59 */       first = false;
/*    */     } 
/*    */     
/* 62 */     s = String.valueOf(s) + ";";
/*    */     
/* 64 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuInitExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */