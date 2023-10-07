/*     */ package ujf.verimag.bip.Core.PortExpressions;
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
/*     */ public enum ACTypingKind
/*     */   implements Enumerator
/*     */ {
/*  34 */   SYNC(0, "sync", "sync"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   TRIG(1, "trig", "trig");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SYNC_VALUE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TRIG_VALUE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final ACTypingKind[] VALUES_ARRAY;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<ACTypingKind> VALUES;
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
/*  82 */     VALUES_ARRAY = new ACTypingKind[] { SYNC, TRIG };
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
/*  95 */     VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ACTypingKind get(String literal) {
/* 105 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 107 */       ACTypingKind result = VALUES_ARRAY[i];
/* 108 */       if (result.toString().equals(literal))
/*     */       {
/* 110 */         return result;
/*     */       }
/*     */     } 
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ACTypingKind getByName(String name) {
/* 124 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 126 */       ACTypingKind result = VALUES_ARRAY[i];
/* 127 */       if (result.getName().equals(name))
/*     */       {
/* 129 */         return result;
/*     */       }
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ACTypingKind get(int value) {
/* 143 */     switch (value) {
/*     */       case 0:
/* 145 */         return SYNC;
/* 146 */       case 1: return TRIG;
/*     */     } 
/* 148 */     return null;
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
/*     */   ACTypingKind(int value, String name, String literal) {
/* 180 */     this.value = value;
/* 181 */     this.name = name;
/* 182 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 192 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 202 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLiteral() {
/* 212 */     return this.literal;
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
/* 224 */     return this.literal;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\ACTypingKind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */