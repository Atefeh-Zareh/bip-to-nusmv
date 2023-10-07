/*     */ package ujf.verimag.bip.Core.Behaviors;
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
/*     */ public enum ParameterDirectionKind
/*     */   implements Enumerator
/*     */ {
/*  34 */   INOUT(0, "inout", "inout"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   IN(1, "in", "in"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   OUT(2, "out", "out");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int INOUT_VALUE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int IN_VALUE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OUT_VALUE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final ParameterDirectionKind[] VALUES_ARRAY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<ParameterDirectionKind> VALUES;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int value;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String literal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 107 */     VALUES_ARRAY = new ParameterDirectionKind[] { INOUT, IN, OUT };
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
/* 121 */     VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParameterDirectionKind get(String literal) {
/* 131 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 133 */       ParameterDirectionKind result = VALUES_ARRAY[i];
/* 134 */       if (result.toString().equals(literal))
/*     */       {
/* 136 */         return result;
/*     */       }
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParameterDirectionKind getByName(String name) {
/* 150 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 152 */       ParameterDirectionKind result = VALUES_ARRAY[i];
/* 153 */       if (result.getName().equals(name))
/*     */       {
/* 155 */         return result;
/*     */       }
/*     */     } 
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ParameterDirectionKind get(int value) {
/* 169 */     switch (value) {
/*     */       case 0:
/* 171 */         return INOUT;
/* 172 */       case 1: return IN;
/* 173 */       case 2: return OUT;
/*     */     } 
/* 175 */     return null;
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
/*     */   
/*     */   ParameterDirectionKind(int value, String name, String literal) {
/* 207 */     this.value = value;
/* 208 */     this.name = name;
/* 209 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 219 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 229 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLiteral() {
/* 239 */     return this.literal;
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
/*     */   public String toString() {
/* 251 */     return this.literal;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\ParameterDirectionKind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */