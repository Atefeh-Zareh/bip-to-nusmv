/*    */ package org.antlr.runtime.debug;
/*    */ 
/*    */ import org.antlr.runtime.IntStream;
/*    */ import org.antlr.runtime.TokenStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Tracer
/*    */   extends BlankDebugEventListener
/*    */ {
/*    */   public IntStream input;
/* 39 */   protected int level = 0;
/*    */   
/*    */   public Tracer(IntStream input) {
/* 42 */     this.input = input;
/*    */   }
/*    */   
/*    */   public void enterRule(String ruleName) {
/* 46 */     for (int i = 1; i <= this.level; ) { System.out.print(" "); i++; }
/* 47 */      System.out.println("> " + ruleName + " lookahead(1)=" + getInputSymbol(1));
/* 48 */     this.level++;
/*    */   }
/*    */   
/*    */   public void exitRule(String ruleName) {
/* 52 */     this.level--;
/* 53 */     for (int i = 1; i <= this.level; ) { System.out.print(" "); i++; }
/* 54 */      System.out.println("< " + ruleName + " lookahead(1)=" + getInputSymbol(1));
/*    */   }
/*    */   
/*    */   public Object getInputSymbol(int k) {
/* 58 */     if (this.input instanceof TokenStream) {
/* 59 */       return ((TokenStream)this.input).LT(k);
/*    */     }
/* 61 */     return new Character((char)this.input.LA(k));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\debug\Tracer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */