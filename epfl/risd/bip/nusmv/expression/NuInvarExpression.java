/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuInvarExpression
/*    */   implements NuExpression
/*    */ {
/*    */   private List<NuExpression> invars;
/*    */   
/*    */   public NuInvarExpression() {
/* 14 */     this.invars = new LinkedList<NuExpression>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuInvarExpression(List<NuExpression> exps) {
/* 19 */     this.invars = new LinkedList<NuExpression>();
/* 20 */     if (exps != null) this.invars.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public NuInvarExpression(NuInvarExpression n) {
/* 25 */     this.invars = new LinkedList<NuExpression>();
/* 26 */     if (n.invars != null) this.invars.addAll(n.invars);
/*    */   
/*    */   }
/*    */   
/*    */   public void setExpression(List<NuExpression> exps) {
/* 31 */     this.invars = exps;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NuExpression> getExpression() {
/* 36 */     return this.invars;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addExpression(NuExpression exp) {
/* 41 */     if (exp != null) this.invars.add(exp);
/*    */   
/*    */   }
/*    */   
/*    */   public void addExpressions(List<NuExpression> exps) {
/* 46 */     if (exps != null) this.invars.addAll(exps);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     String s = "";
/* 52 */     boolean first = true;
/*    */     
/* 54 */     for (NuExpression invar : this.invars) {
/*    */       
/* 56 */       if (!first) s = String.valueOf(s) + " &\n"; 
/* 57 */       s = String.valueOf(s) + "\t\t";
/* 58 */       s = String.valueOf(s) + NuExpressionHelper.expressionToString(invar);
/* 59 */       first = false;
/*    */     } 
/*    */     
/* 62 */     s = String.valueOf(s) + ";";
/*    */     
/* 64 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuInvarExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */