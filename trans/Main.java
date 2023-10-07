/*    */ package trans;
/*    */ 
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.bip2src.Reverse;
/*    */ import ujf.verimag.bip.parser.actions.Parser;
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
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] arg) throws FileNotFoundException {
/* 30 */     assert arg.length == 2 || arg.length == 3 : "Error Using CmdLine: BIPFlat.jar [1,2,3] input.bip output.bip";
/* 31 */     int flatlevel = 3;
/* 32 */     int i = 0;
/* 33 */     if (arg.length == 3)
/*    */     {
/* 35 */       flatlevel = Integer.parseInt(arg[i++]);
/*    */     }
/* 37 */     String inputfile = arg[i++];
/* 38 */     String outputfile = arg[i++];
/*    */     
/* 40 */     Module m = ParseBIPFile(inputfile);
/*    */     
/* 42 */     FlatBip2.Flat(m, flatlevel);
/*    */     
/* 44 */     CreateBIPFile(outputfile, m);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Module ParseBIPFile(String Path) {
/*    */     try {
/* 54 */       CmdLineError err = new CmdLineError();
/*    */       
/* 56 */       ArrayList<String> includeDirectories = new ArrayList();
/* 57 */       Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/* 58 */       String includeDir = "";
/* 59 */       includeDirectories.add(String.valueOf(includeDir) + "/");
/* 60 */       Module[] bipModel = Parser.parse(Path, includeDirectories, libFullNames, err);
/*    */       
/* 62 */       return bipModel[0];
/*    */     }
/* 64 */     catch (Exception E) {
/*    */       
/* 66 */       System.out.println("Error Parsing BIP File");
/* 67 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void CreateBIPFile(String FileName, Module BIPSystem) throws FileNotFoundException {
/* 74 */     FileOutputStream out = new FileOutputStream(FileName);
/* 75 */     PrintStream X = new PrintStream(out);
/* 76 */     Reverse a = new Reverse(X);
/* 77 */     a.decompile(BIPSystem);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */