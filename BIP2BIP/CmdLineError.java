/*    */ package BIP2BIP;
/*    */ 
/*    */ import ujf.verimag.bip.parser.ErrorMessage;
/*    */ import ujf.verimag.bip.parser.ParserException;
/*    */ 
/*    */ public class CmdLineError
/*    */   implements ErrorMessage {
/*  8 */   int errorNumber = 0;
/*  9 */   int warningNumber = 0;
/*    */   
/*    */   public boolean isCorrect() {
/* 12 */     return (this.errorNumber == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Exception sendErrorMessage(int level, String message, int lineNumber, int columnNumber, String fileName) {
/* 17 */     String msg = message;
/* 18 */     if (fileName != null && lineNumber != 0) {
/* 19 */       msg = String.valueOf(fileName) + " " + lineNumber + ":" + columnNumber + " " + msg;
/*    */     }
/* 21 */     switch (level) {
/*    */       case 1:
/* 23 */         System.out.println(msg);
/* 24 */         return null;
/*    */       
/*    */       case 2:
/* 27 */         System.err.println(msg);
/* 28 */         return null;
/*    */       
/*    */       case 3:
/* 31 */         System.err.println(msg);
/* 32 */         this.errorNumber++;
/* 33 */         return null;
/*    */       
/*    */       case 4:
/* 36 */         System.err.println(msg);
/* 37 */         this.errorNumber++;
/* 38 */         return (Exception)new ParserException();
/*    */     } 
/*    */     
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getErrorNumber() {
/* 47 */     return this.errorNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWarningNumber() {
/* 52 */     return this.warningNumber;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\CmdLineError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */