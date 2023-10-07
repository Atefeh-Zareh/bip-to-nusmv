/*     */ package org.antlr.runtime.misc;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
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
/*     */ public class Stats
/*     */ {
/*     */   public static final String ANTLRWORKS_DIR = "antlrworks";
/*     */   
/*     */   public static double stddev(int[] X) {
/*  55 */     int m = X.length;
/*  56 */     if (m <= 1) {
/*  57 */       return 0.0D;
/*     */     }
/*  59 */     double xbar = avg(X);
/*  60 */     double s2 = 0.0D;
/*  61 */     for (int i = 0; i < m; i++) {
/*  62 */       s2 += (X[i] - xbar) * (X[i] - xbar);
/*     */     }
/*  64 */     s2 /= (m - 1);
/*  65 */     return Math.sqrt(s2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double avg(int[] X) {
/*  70 */     double xbar = 0.0D;
/*  71 */     int m = X.length;
/*  72 */     if (m == 0) {
/*  73 */       return 0.0D;
/*     */     }
/*  75 */     for (int i = 0; i < m; i++) {
/*  76 */       xbar += X[i];
/*     */     }
/*  78 */     if (xbar >= 0.0D) {
/*  79 */       return xbar / m;
/*     */     }
/*  81 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static int min(int[] X) {
/*  85 */     int min = Integer.MAX_VALUE;
/*  86 */     int m = X.length;
/*  87 */     if (m == 0) {
/*  88 */       return 0;
/*     */     }
/*  90 */     for (int i = 0; i < m; i++) {
/*  91 */       if (X[i] < min) {
/*  92 */         min = X[i];
/*     */       }
/*     */     } 
/*  95 */     return min;
/*     */   }
/*     */   
/*     */   public static int max(int[] X) {
/*  99 */     int max = Integer.MIN_VALUE;
/* 100 */     int m = X.length;
/* 101 */     if (m == 0) {
/* 102 */       return 0;
/*     */     }
/* 104 */     for (int i = 0; i < m; i++) {
/* 105 */       if (X[i] > max) {
/* 106 */         max = X[i];
/*     */       }
/*     */     } 
/* 109 */     return max;
/*     */   }
/*     */   
/*     */   public static int sum(int[] X) {
/* 113 */     int s = 0;
/* 114 */     int m = X.length;
/* 115 */     if (m == 0) {
/* 116 */       return 0;
/*     */     }
/* 118 */     for (int i = 0; i < m; i++) {
/* 119 */       s += X[i];
/*     */     }
/* 121 */     return s;
/*     */   }
/*     */   
/*     */   public static void writeReport(String filename, String data) throws IOException {
/* 125 */     String absoluteFilename = getAbsoluteFileName(filename);
/* 126 */     File f = new File(absoluteFilename);
/* 127 */     File parent = f.getParentFile();
/* 128 */     parent.mkdirs();
/*     */     
/* 130 */     FileOutputStream fos = new FileOutputStream(f, true);
/* 131 */     BufferedOutputStream bos = new BufferedOutputStream(fos);
/* 132 */     PrintStream ps = new PrintStream(bos);
/* 133 */     ps.println(data);
/* 134 */     ps.close();
/* 135 */     bos.close();
/* 136 */     fos.close();
/*     */   }
/*     */   
/*     */   public static String getAbsoluteFileName(String filename) {
/* 140 */     return System.getProperty("user.home") + File.separator + "antlrworks" + File.separator + filename;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\misc\Stats.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */