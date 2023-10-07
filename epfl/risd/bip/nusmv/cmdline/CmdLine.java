/*    */ package epfl.risd.bip.nusmv.cmdline;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.module.NuVisitorImplementation1;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CmdLine
/*    */ {
/* 11 */   private static String cmdLineHelp = "HELP: java -jar bip-to-nusmv input.bip output.smv [guide]";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 18 */     if (args.length < 2) {
/* 19 */       System.out.println(cmdLineHelp);
/* 20 */       System.exit(0);
/*    */     } 
/*    */     
/* 23 */     String inputFile = args[0];
/* 24 */     String outputFile = args[1];
/* 25 */     String guideFile = null;
/* 26 */     if (args.length == 3) guideFile = args[2];
/*    */     
/* 28 */     File outFile = new File(outputFile);
/*    */ 
/*    */     
/* 31 */     NuVisitorImplementation1 nuVisitorImplementation1 = new NuVisitorImplementation1();
/*    */ 
/*    */ 
/*    */     
/* 35 */     System.out.println("The " + args[1] + " has been generated!");
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\cmdline\CmdLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */