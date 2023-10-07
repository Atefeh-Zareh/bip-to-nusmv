/*     */ package org.eclipse.emf.ecore.xmi;
/*     */ 
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMIException
/*     */   extends Exception
/*     */   implements Resource.Diagnostic
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String location;
/*     */   protected int line;
/*     */   protected int column;
/*     */   
/*     */   public XMIException(String message) {
/*  33 */     super(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMIException(Exception exception) {
/*  38 */     super(exception);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMIException(String message, Exception exception) {
/*  43 */     super(message, exception);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMIException(String message, String location, int line, int column) {
/*  48 */     super(message);
/*  49 */     this.location = location;
/*  50 */     this.line = line;
/*  51 */     this.column = column;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMIException(String message, Exception exception, String location, int line, int column) {
/*  56 */     super(message, exception);
/*  57 */     this.location = location;
/*  58 */     this.line = line;
/*  59 */     this.column = column;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMIException(Exception exception, String location, int line, int column) {
/*  64 */     super(exception);
/*  65 */     this.location = location;
/*  66 */     this.line = line;
/*  67 */     this.column = column;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  73 */     String result = super.getMessage();
/*  74 */     if (this.line != 0)
/*     */     {
/*  76 */       result = String.valueOf(result) + " (" + this.location + ", " + this.line + ", " + this.column + ")";
/*     */     }
/*  78 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocation() {
/*  83 */     return this.location;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLine() {
/*  88 */     return this.line;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumn() {
/*  93 */     return this.column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Exception getWrappedException() {
/* 104 */     return (Exception)getCause();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMIException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */