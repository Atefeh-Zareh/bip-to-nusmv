/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuConstant
/*    */   implements NuExpression
/*    */ {
/*    */   private NuEnumType type;
/*    */   private String value;
/*    */   
/*    */   public NuConstant() {}
/*    */   
/*    */   public NuConstant(NuEnumType t, String v) {
/* 17 */     this.type = t;
/* 18 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuConstant(NuConstant n) {
/* 23 */     this.type = n.type;
/* 24 */     this.value = n.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(NuEnumType t) {
/* 29 */     this.type = t;
/*    */   }
/*    */   
/*    */   public void setValue(String v) {
/* 33 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuEnumType getType() {
/* 38 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuConstant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */