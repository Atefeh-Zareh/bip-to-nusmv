/*     */ package epfl.risd.bip.nusmv.api;
/*     */ 
/*     */ public enum NuBinaryOperator
/*     */ {
/*   5 */   ADDITION(0),
/*   6 */   SUBTRACTION(1),
/*   7 */   MULTIPLICATION(2),
/*   8 */   DIVISION(3),
/*   9 */   MODULUS(4),
/*  10 */   LOGICAL_AND(5),
/*  11 */   LOGICAL_OR(6),
/*  12 */   LOGICAL_XOR(7),
/*  13 */   EQUALITY(8),
/*  14 */   INEQUALITY(9),
/*  15 */   GREATER_THAN(10),
/*  16 */   GREATER_THAN_OR_EQUAL(11),
/*  17 */   LESS_THAN(12),
/*  18 */   LESS_THAN_OR_EQUAL(13),
/*  19 */   LEFT_SHIFT(14),
/*  20 */   RIGHT_SHIFT(15),
/*  21 */   IMPLIES(16),
/*  22 */   EQUIVELANT(17);
/*     */   
/*     */   private int value;
/*     */   
/*     */   public static final int ADDITION_VALUE = 0;
/*     */   
/*     */   public static final int SUBTRACTION_VALUE = 1;
/*     */   public static final int MULTIPLICATION_VALUE = 2;
/*     */   public static final int DIVISION_VALUE = 3;
/*     */   public static final int MODULUS_VALUE = 4;
/*     */   public static final int LOGICAL_AND_VALUE = 5;
/*     */   public static final int LOGICAL_OR_VALUE = 6;
/*     */   public static final int LOGICAL_XOR_VALUE = 7;
/*     */   public static final int EQUALITY_VALUE = 8;
/*     */   public static final int INEQUALITY_VALUE = 9;
/*     */   public static final int GREATER_THAN_VALUE = 10;
/*     */   public static final int GREATER_THAN_OR_EQUAL_VALUE = 11;
/*     */   public static final int LESS_THAN_VALUE = 12;
/*     */   public static final int LESS_THAN_OR_EQUAL_VALUE = 13;
/*     */   public static final int LEFT_SHIFT_VALUE = 14;
/*     */   public static final int RIGHT_SHIFT_VALUE = 15;
/*     */   public static final int IMPLIES_VALUE = 16;
/*     */   public static final int EQUIVELANT_VALUE = 17;
/*     */   
/*     */   NuBinaryOperator(int v) {
/*  47 */     this.value = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(int v) {
/*  52 */     this.value = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getValue() {
/*  57 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  62 */     String s = "";
/*     */     
/*  64 */     switch (this.value) {
/*     */       
/*     */       case 0:
/*  67 */         s = String.valueOf(s) + "+";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 124 */         return s;case 1: s = String.valueOf(s) + "-"; return s;case 2: s = String.valueOf(s) + "*"; return s;case 3: s = String.valueOf(s) + "/"; return s;case 4: s = String.valueOf(s) + "mod"; return s;case 5: s = String.valueOf(s) + "&"; return s;case 6: s = String.valueOf(s) + "|"; return s;case 7: s = String.valueOf(s) + "xor"; return s;case 8: s = String.valueOf(s) + "="; return s;case 9: s = String.valueOf(s) + "!="; return s;case 10: s = String.valueOf(s) + ">"; return s;case 11: s = String.valueOf(s) + ">="; return s;case 12: s = String.valueOf(s) + "<"; return s;case 13: s = String.valueOf(s) + "<="; return s;case 14: s = String.valueOf(s) + "<<"; return s;case 15: s = String.valueOf(s) + ">>"; return s;case 16: s = String.valueOf(s) + "->"; return s;case 17: s = String.valueOf(s) + "<->"; return s;
/*     */     } 
/*     */     throw new Error("Unimplemented NuBinary Operator");
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\api\NuBinaryOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */