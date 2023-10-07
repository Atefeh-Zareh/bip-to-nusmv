/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuDefineAction
/*    */   implements NuAction
/*    */ {
/*    */   private List<NuAssignmentAction> actions;
/*    */   
/*    */   public NuDefineAction() {
/* 14 */     this.actions = new LinkedList<NuAssignmentAction>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuDefineAction(List<NuAssignmentAction> a) {
/* 19 */     this.actions = new LinkedList<NuAssignmentAction>();
/* 20 */     if (a != null) this.actions.addAll(a);
/*    */   
/*    */   }
/*    */   
/*    */   public NuDefineAction(NuDefineAction n) {
/* 25 */     this.actions = new LinkedList<NuAssignmentAction>();
/* 26 */     if (n.actions != null) this.actions.addAll(n.actions);
/*    */   
/*    */   }
/*    */   
/*    */   public void setActions(List<NuAssignmentAction> a) {
/* 31 */     this.actions = a;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NuAssignmentAction> getActions() {
/* 36 */     return this.actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addAction(NuAssignmentAction a) {
/* 41 */     if (a != null) this.actions.add(a);
/*    */   
/*    */   }
/*    */   
/*    */   public void addActions(List<NuAssignmentAction> a) {
/* 46 */     if (a != null) this.actions.addAll(a);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     String s = "";
/*    */     
/* 53 */     for (NuAssignmentAction act : this.actions) {
/*    */       
/* 55 */       s = String.valueOf(s) + "\t\t";
/* 56 */       s = String.valueOf(s) + act.toString() + ";" + "\n";
/*    */     } 
/*    */     
/* 59 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuDefineAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */