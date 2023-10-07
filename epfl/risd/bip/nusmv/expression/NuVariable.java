/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuVariable
/*    */   extends NuNamedElement
/*    */ {
/*    */   protected NuEnumType type;
/*    */   
/*    */   public NuVariable() {}
/*    */   
/*    */   public NuVariable(String name, NuEnumType t) {
/* 16 */     super(name);
/* 17 */     this.type = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuVariable(NuVariable n) {
/* 22 */     super(n.name);
/* 23 */     this.type = n.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(NuEnumType t) {
/* 28 */     this.type = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuEnumType getType() {
/* 33 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return NuExpressionHelper.variableToString(this);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */