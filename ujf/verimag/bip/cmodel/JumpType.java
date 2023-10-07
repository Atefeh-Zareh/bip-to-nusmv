/*     */ package ujf.verimag.bip.cmodel;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.Enumerator;
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
/*     */ public enum JumpType
/*     */   implements Enumerator
/*     */ {
/*  34 */   CBREAK(0, "cbreak", "cbreak"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   CCONTINUE(1, "ccontinue", "ccontinue");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CBREAK_VALUE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CCONTINUE_VALUE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final JumpType[] VALUES_ARRAY;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<JumpType> VALUES;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int value;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String literal;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  80 */     VALUES_ARRAY = new JumpType[] { CBREAK, CCONTINUE };
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
/*  92 */     VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JumpType get(String literal) {
/* 101 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/* 102 */       JumpType result = VALUES_ARRAY[i];
/* 103 */       if (result.toString().equals(literal)) {
/* 104 */         return result;
/*     */       }
/*     */     } 
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JumpType getByName(String name) {
/* 117 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/* 118 */       JumpType result = VALUES_ARRAY[i];
/* 119 */       if (result.getName().equals(name)) {
/* 120 */         return result;
/*     */       }
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JumpType get(int value) {
/* 133 */     switch (value) { case 0:
/* 134 */         return CBREAK;
/* 135 */       case 1: return CCONTINUE; }
/*     */     
/* 137 */     return null;
/*     */   }
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
/*     */   JumpType(int value, String name, String literal) {
/* 168 */     this.value = value;
/* 169 */     this.name = name;
/* 170 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 179 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 188 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLiteral() {
/* 197 */     return this.literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 208 */     return this.literal;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\JumpType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */