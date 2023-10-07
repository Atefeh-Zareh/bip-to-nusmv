/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuNamedElement
/*    */   implements NuExpression
/*    */ {
/*    */   protected String name;
/*    */   
/*    */   public NuNamedElement() {}
/*    */   
/*    */   public NuNamedElement(String name) {
/* 14 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuNamedElement(NuNamedElement n) {
/* 19 */     this.name = n.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 24 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 29 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuNamedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */