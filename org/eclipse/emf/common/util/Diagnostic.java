/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Diagnostic
/*     */ {
/*     */   public static final int OK = 0;
/*     */   public static final int INFO = 1;
/*     */   public static final int WARNING = 2;
/*     */   public static final int ERROR = 4;
/*     */   public static final int CANCEL = 8;
/*  95 */   public static final Diagnostic OK_INSTANCE = new BasicDiagnostic(
/*  96 */       0, "org.eclipse.emf.common", 0, CommonPlugin.INSTANCE.getString("_UI_OK_diagnostic_0"), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final Diagnostic CANCEL_INSTANCE = new BasicDiagnostic(
/* 103 */       8, "org.eclipse.emf.common", 0, CommonPlugin.INSTANCE.getString("_UI_Cancel_diagnostic_0"), null);
/*     */   
/*     */   int getSeverity();
/*     */   
/*     */   String getMessage();
/*     */   
/*     */   String getSource();
/*     */   
/*     */   int getCode();
/*     */   
/*     */   Throwable getException();
/*     */   
/*     */   List<?> getData();
/*     */   
/*     */   List<Diagnostic> getChildren();
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\Diagnostic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */