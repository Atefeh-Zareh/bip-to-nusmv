/*     */ package epfl.risd.bip.nusmv.expression;
/*     */ 
/*     */ import epfl.risd.bip.nusmv.api.NuBinaryOperator;
/*     */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*     */ import epfl.risd.bip.nusmv.api.NuUnaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NuExpressionHelper
/*     */ {
/*     */   public static String expressionToString(NuExpression expression) {
/*  14 */     String s = "";
/*     */     
/*  16 */     if (expression instanceof NuBinaryExpression) {
/*     */       
/*  18 */       NuBinaryExpression exp = (NuBinaryExpression)expression;
/*  19 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  21 */     else if (expression instanceof NuConstant) {
/*     */       
/*  23 */       NuConstant exp = (NuConstant)expression;
/*  24 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  26 */     else if (expression instanceof NuInitExpression) {
/*     */       
/*  28 */       NuInitExpression exp = (NuInitExpression)expression;
/*  29 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  31 */     else if (expression instanceof NuInvarExpression) {
/*     */       
/*  33 */       NuInvarExpression exp = (NuInvarExpression)expression;
/*  34 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  36 */     else if (expression instanceof NuNamedElement) {
/*     */       
/*  38 */       NuNamedElement exp = (NuNamedElement)expression;
/*  39 */       s = String.valueOf(s) + exp.getName();
/*     */     }
/*  41 */     else if (expression instanceof NuTransExpression) {
/*     */       
/*  43 */       NuTransExpression exp = (NuTransExpression)expression;
/*  44 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  46 */     else if (expression instanceof NuUnaryExpression) {
/*     */       
/*  48 */       NuUnaryExpression exp = (NuUnaryExpression)expression;
/*  49 */       s = String.valueOf(s) + exp.toString();
/*     */     }
/*  51 */     else if (expression instanceof NuVariable) {
/*     */       
/*  53 */       NuVariable exp = (NuVariable)expression;
/*  54 */       s = String.valueOf(s) + exp.getName();
/*     */     } else {
/*  56 */       throw new Error("Unimplemented Expression");
/*     */     } 
/*  58 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String variableToString(NuVariable variable) {
/*  63 */     String s = "";
/*     */     
/*  65 */     if (variable instanceof NuSignedWordVariable) {
/*     */       
/*  67 */       NuSignedWordVariable var = (NuSignedWordVariable)variable;
/*  68 */       s = String.valueOf(s) + var.toString();
/*     */     }
/*  70 */     else if (variable instanceof NuSymbolicEnumVariable) {
/*     */       
/*  72 */       NuSymbolicEnumVariable var = (NuSymbolicEnumVariable)variable;
/*  73 */       s = String.valueOf(s) + var.toString();
/*     */     }
/*  75 */     else if (variable instanceof NuUnsignedWordVariable) {
/*     */       
/*  77 */       NuUnsignedWordVariable var = (NuUnsignedWordVariable)variable;
/*  78 */       s = String.valueOf(s) + var.toString();
/*     */     }
/*  80 */     else if (variable.getType().equals(NuEnumType.BOOLEAN)) {
/*     */       
/*  82 */       s = String.valueOf(s) + variable.getName();
/*  83 */       s = String.valueOf(s) + "\t\t";
/*  84 */       s = String.valueOf(s) + ":";
/*  85 */       s = String.valueOf(s) + "\t\t";
/*  86 */       s = String.valueOf(s) + variable.getType();
/*     */     }
/*  88 */     else if (variable.getType().equals(NuEnumType.MODULE_INSTANT)) {
/*     */       
/*  90 */       s = String.valueOf(s) + variable.getName();
/*     */     } else {
/*  92 */       throw new Error("Unimplemented Variable");
/*     */     } 
/*  94 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NuUnaryOperator convertUnaryOperator(UnaryOperator uo) {
/* 100 */     switch (uo.getValue()) {
/*     */       
/*     */       case 0:
/* 103 */         return NuUnaryOperator.POSITIVE;
/*     */       case 1:
/* 105 */         return NuUnaryOperator.NEGATIVE;
/*     */       case 2:
/* 107 */         return NuUnaryOperator.LOGICAL_NOT;
/*     */       case 5:
/* 109 */         return NuUnaryOperator.LOGICAL_NOT;
/*     */     } 
/* 111 */     throw new Error("Unimplemented Unary Operator");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NuBinaryOperator convertBinaryOperator(BinaryOperator bo) {
/* 118 */     switch (bo.getValue()) {
/*     */       
/*     */       case 0:
/* 121 */         return NuBinaryOperator.ADDITION;
/*     */       case 3:
/* 123 */         return NuBinaryOperator.DIVISION;
/*     */       case 5:
/* 125 */         return NuBinaryOperator.EQUALITY;
/*     */       case 8:
/* 127 */         return NuBinaryOperator.GREATER_THAN;
/*     */       case 10:
/* 129 */         return NuBinaryOperator.GREATER_THAN_OR_EQUAL;
/*     */       case 6:
/* 131 */         return NuBinaryOperator.INEQUALITY;
/*     */       case 7:
/* 133 */         return NuBinaryOperator.LESS_THAN;
/*     */       case 9:
/* 135 */         return NuBinaryOperator.LESS_THAN_OR_EQUAL;
/*     */       case 12:
/* 137 */         return NuBinaryOperator.LOGICAL_AND;
/*     */       case 11:
/* 139 */         return NuBinaryOperator.LOGICAL_OR;
/*     */       case 15:
/* 141 */         return NuBinaryOperator.LOGICAL_AND;
/*     */       case 13:
/* 143 */         return NuBinaryOperator.LOGICAL_OR;
/*     */       case 14:
/* 145 */         return NuBinaryOperator.LOGICAL_XOR;
/*     */       case 16:
/* 147 */         return NuBinaryOperator.LEFT_SHIFT;
/*     */       case 17:
/* 149 */         return NuBinaryOperator.RIGHT_SHIFT;
/*     */       case 4:
/* 151 */         return NuBinaryOperator.MODULUS;
/*     */       case 2:
/* 153 */         return NuBinaryOperator.MULTIPLICATION;
/*     */       case 1:
/* 155 */         return NuBinaryOperator.SUBTRACTION;
/*     */     } 
/* 157 */     throw new Error("Unimplemented Binary Operator");
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuExpressionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */