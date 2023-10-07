/*     */ package ujf.verimag.bip.parser;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.antlr.runtime.CommonTokenStream;
/*     */ import org.antlr.runtime.tree.Tree;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BipTreeError
/*     */ {
/*  19 */   private Map<EObject, Tree> treeDictionnary = null;
/*     */   private ErrorMessage errorMsg;
/*     */   private CommonTokenStream tokenStream;
/*     */   
/*     */   public BipTreeError(ErrorMessage msg, CommonTokenStream theTokenStream) {
/*  24 */     this.errorMsg = msg;
/*  25 */     this.tokenStream = theTokenStream;
/*  26 */     this.treeDictionnary = new HashMap<EObject, Tree>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(EObject o, Tree t) {
/*  35 */     this.treeDictionnary.put(o, t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendError(int errNb, String[] params, EObject o) {
/*  46 */     if (params == null) {
/*  47 */       params = new String[0];
/*     */     }
/*  49 */     String msg = CompilerError.buildErrorMessage(errNb, params);
/*  50 */     Tree t = this.treeDictionnary.get(o);
/*  51 */     if (t == null) {
/*  52 */       this.errorMsg.sendErrorMessage(3, msg, 0, 0, "");
/*     */     } else {
/*     */       
/*  55 */       this.errorMsg.sendErrorMessage(3, msg, t.getLine(), t.getCharPositionInLine(), this.tokenStream.get(t.getTokenStartIndex()).getInputStream().getSourceName());
/*     */     } 
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
/*     */   public void sendError(int errNb, String[] params, Tree t) {
/*  68 */     if (params == null) {
/*  69 */       params = new String[0];
/*     */     }
/*  71 */     String msg = CompilerError.buildErrorMessage(errNb, params);
/*  72 */     if (t == null) {
/*     */       
/*  74 */       this.errorMsg.sendErrorMessage(3, msg, 0, 0, "<none>");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  79 */       this.errorMsg.sendErrorMessage(3, msg, t.getLine(), t.getCharPositionInLine(), this.tokenStream.get(t.getTokenStartIndex()).getInputStream().getSourceName());
/*     */     } 
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
/*     */   public void sendWarning(int errNb, String[] params, EObject o) {
/*  93 */     if (params == null) {
/*  94 */       params = new String[0];
/*     */     }
/*  96 */     String msg = CompilerError.buildErrorMessage(errNb, params);
/*  97 */     Tree t = this.treeDictionnary.get(o);
/*  98 */     if (t == null) {
/*  99 */       this.errorMsg.sendErrorMessage(2, msg, 0, 0, "");
/*     */     } else {
/*     */       
/* 102 */       this.errorMsg.sendErrorMessage(2, msg, t.getLine(), t.getCharPositionInLine(), this.tokenStream.get(t.getTokenStartIndex()).getInputStream().getSourceName());
/*     */     } 
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
/*     */   public void sendWarning(int errNb, String[] params, Tree t) {
/* 115 */     if (params == null) {
/* 116 */       params = new String[0];
/*     */     }
/* 118 */     String msg = CompilerError.buildErrorMessage(errNb, params);
/* 119 */     if (t == null) {
/* 120 */       this.errorMsg.sendErrorMessage(2, msg, 0, 0, "");
/*     */     } else {
/*     */       
/* 123 */       this.errorMsg.sendErrorMessage(2, msg, t.getLine(), t.getCharPositionInLine(), this.tokenStream.get(t.getTokenStartIndex()).getInputStream().getSourceName());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\BipTreeError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */