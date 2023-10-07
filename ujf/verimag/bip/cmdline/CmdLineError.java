/*    */ package ujf.verimag.bip.cmdline;
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
/* 19 */       if (fileName.equals("")) {
/* 20 */         msg = " " + lineNumber + ":" + columnNumber + " " + msg;
/*    */       } else {
/* 22 */         msg = " " + fileName + " " + lineNumber + ":" + columnNumber + " " + msg;
/*    */       } 
/*    */     }
/* 25 */     switch (level) {
/*    */       case 1:
/* 27 */         System.out.println(msg);
/* 28 */         return null;
/*    */       
/*    */       case 2:
/* 31 */         System.err.println("[WARNING]" + msg);
/* 32 */         return null;
/*    */       
/*    */       case 3:
/* 35 */         System.err.println("[ERROR]" + msg);
/* 36 */         this.errorNumber++;
/* 37 */         return null;
/*    */       
/*    */       case 4:
/* 40 */         System.err.println("[FATAL]" + msg);
/* 41 */         this.errorNumber++;
/* 42 */         return (Exception)new ParserException();
/*    */     } 
/*    */     
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getErrorNumber() {
/* 51 */     return this.errorNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWarningNumber() {
/* 56 */     return this.warningNumber;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmdline\CmdLineError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */