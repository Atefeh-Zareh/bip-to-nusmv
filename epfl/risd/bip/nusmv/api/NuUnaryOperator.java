/*    */ package epfl.risd.bip.nusmv.api;
/*    */ 
/*    */ public enum NuUnaryOperator
/*    */ {
/*  5 */   POSITIVE(0),
/*  6 */   NEGATIVE(1),
/*  7 */   LOGICAL_NOT(2);
/*    */   
/*    */   private int value;
/*    */   
/*    */   public static final int POSITIVE_VALUE = 0;
/*    */   
/*    */   public static final int NEGATIVE_VALUE = 1;
/*    */   public static final int LOGICAL_NOT_VALUE = 2;
/*    */   
/*    */   NuUnaryOperator(int v) {
/* 17 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(int v) {
/* 22 */     this.value = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getValue() {
/* 27 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     String s = "";
/*    */     
/* 34 */     switch (this.value) {
/*    */       
/*    */       case 0:
/* 37 */         s = String.valueOf(s) + "+";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 49 */         return s;case 1: s = String.valueOf(s) + "-"; return s;case 2: s = String.valueOf(s) + "!"; return s;
/*    */     } 
/*    */     throw new Error("Unimplemented NuUnary Operator");
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\api\NuUnaryOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */