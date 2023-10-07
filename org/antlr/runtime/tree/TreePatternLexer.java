/*     */ package org.antlr.runtime.tree;
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
/*     */ public class TreePatternLexer
/*     */ {
/*     */   public static final int EOF = -1;
/*     */   public static final int BEGIN = 1;
/*     */   public static final int END = 2;
/*     */   public static final int ID = 3;
/*     */   public static final int ARG = 4;
/*     */   public static final int PERCENT = 5;
/*     */   public static final int COLON = 6;
/*     */   public static final int DOT = 7;
/*     */   protected String pattern;
/*  44 */   protected int p = -1;
/*     */ 
/*     */   
/*     */   protected int c;
/*     */ 
/*     */   
/*     */   protected int n;
/*     */ 
/*     */   
/*  53 */   public StringBuffer sval = new StringBuffer();
/*     */   
/*     */   public boolean error = false;
/*     */   
/*     */   public TreePatternLexer(String pattern) {
/*  58 */     this.pattern = pattern;
/*  59 */     this.n = pattern.length();
/*  60 */     consume();
/*     */   }
/*     */   
/*     */   public int nextToken() {
/*  64 */     this.sval.setLength(0);
/*  65 */     while (this.c != -1) {
/*  66 */       if (this.c == 32 || this.c == 10 || this.c == 13 || this.c == 9) {
/*  67 */         consume();
/*     */         continue;
/*     */       } 
/*  70 */       if ((this.c >= 97 && this.c <= 122) || (this.c >= 65 && this.c <= 90) || this.c == 95) {
/*  71 */         this.sval.append((char)this.c);
/*  72 */         consume();
/*     */         
/*  74 */         while ((this.c >= 97 && this.c <= 122) || (this.c >= 65 && this.c <= 90) || (this.c >= 48 && this.c <= 57) || this.c == 95) {
/*     */           
/*  76 */           this.sval.append((char)this.c);
/*  77 */           consume();
/*     */         } 
/*  79 */         return 3;
/*     */       } 
/*  81 */       if (this.c == 40) {
/*  82 */         consume();
/*  83 */         return 1;
/*     */       } 
/*  85 */       if (this.c == 41) {
/*  86 */         consume();
/*  87 */         return 2;
/*     */       } 
/*  89 */       if (this.c == 37) {
/*  90 */         consume();
/*  91 */         return 5;
/*     */       } 
/*  93 */       if (this.c == 58) {
/*  94 */         consume();
/*  95 */         return 6;
/*     */       } 
/*  97 */       if (this.c == 46) {
/*  98 */         consume();
/*  99 */         return 7;
/*     */       } 
/* 101 */       if (this.c == 91) {
/* 102 */         consume();
/* 103 */         while (this.c != 93) {
/* 104 */           if (this.c == 92) {
/* 105 */             consume();
/* 106 */             if (this.c != 93) {
/* 107 */               this.sval.append('\\');
/*     */             }
/* 109 */             this.sval.append((char)this.c);
/*     */           } else {
/*     */             
/* 112 */             this.sval.append((char)this.c);
/*     */           } 
/* 114 */           consume();
/*     */         } 
/* 116 */         consume();
/* 117 */         return 4;
/*     */       } 
/* 119 */       consume();
/* 120 */       this.error = true;
/* 121 */       return -1;
/*     */     } 
/* 123 */     return -1;
/*     */   }
/*     */   
/*     */   protected void consume() {
/* 127 */     this.p++;
/* 128 */     if (this.p >= this.n) {
/* 129 */       this.c = -1;
/*     */     } else {
/*     */       
/* 132 */       this.c = this.pattern.charAt(this.p);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreePatternLexer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */