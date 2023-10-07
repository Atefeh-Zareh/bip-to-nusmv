/*     */ package org.eclipse.emf.ecore.xml.namespace;
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
/*     */ public enum SpaceType
/*     */   implements Enumerator
/*     */ {
/*  44 */   DEFAULT_LITERAL(0, "default", "default"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   PRESERVE_LITERAL(1, "preserve", "preserve");
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int DEFAULT = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int PRESERVE = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final SpaceType[] VALUES_ARRAY;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<SpaceType> VALUES;
/*     */ 
/*     */   
/*     */   private final int value;
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */   
/*     */   private final String literal;
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  84 */     VALUES_ARRAY = 
/*     */       
/*  86 */       new SpaceType[] {
/*  87 */         DEFAULT_LITERAL, 
/*  88 */         PRESERVE_LITERAL
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SpaceType get(String literal) {
/* 107 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 109 */       SpaceType result = VALUES_ARRAY[i];
/* 110 */       if (result.toString().equals(literal))
/*     */       {
/* 112 */         return result;
/*     */       }
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SpaceType getByName(String name) {
/* 126 */     for (int i = 0; i < VALUES_ARRAY.length; i++) {
/*     */       
/* 128 */       SpaceType result = VALUES_ARRAY[i];
/* 129 */       if (result.getName().equals(name))
/*     */       {
/* 131 */         return result;
/*     */       }
/*     */     } 
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SpaceType get(int value) {
/* 145 */     switch (value) {
/*     */       case 0:
/* 147 */         return DEFAULT_LITERAL;
/* 148 */       case 1: return PRESERVE_LITERAL;
/*     */     } 
/* 150 */     return null;
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
/*     */   SpaceType(int value, String name, String literal) {
/* 182 */     this.value = value;
/* 183 */     this.name = name;
/* 184 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 194 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 204 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLiteral() {
/* 214 */     return this.literal;
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
/* 226 */     return this.literal;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\SpaceType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */