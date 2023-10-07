/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuVarAction
/*    */   implements NuAction
/*    */ {
/*    */   private List<NuVariable> variables;
/*    */   
/*    */   public NuVarAction() {
/* 14 */     this.variables = new LinkedList<NuVariable>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuVarAction(List<NuVariable> v) {
/* 19 */     this.variables = new LinkedList<NuVariable>();
/* 20 */     if (v != null) this.variables.addAll(v);
/*    */   
/*    */   }
/*    */   
/*    */   public NuVarAction(NuVarAction n) {
/* 25 */     this.variables = new LinkedList<NuVariable>();
/* 26 */     if (n.variables != null) this.variables.addAll(n.variables);
/*    */   
/*    */   }
/*    */   
/*    */   public void setVariables(List<NuVariable> v) {
/* 31 */     this.variables = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NuVariable> getVariables() {
/* 36 */     return this.variables;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addVariable(NuVariable v) {
/* 41 */     if (v != null) this.variables.add(v);
/*    */   
/*    */   }
/*    */   
/*    */   public void addVariables(List<NuVariable> v) {
/* 46 */     if (v != null) this.variables.addAll(v);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     String s = "";
/*    */     
/* 53 */     for (NuVariable var : this.variables) {
/*    */       
/* 55 */       s = String.valueOf(s) + "\t\t";
/* 56 */       s = String.valueOf(s) + var.toString() + ";" + "\n";
/*    */     } 
/*    */     
/* 59 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuVarAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */