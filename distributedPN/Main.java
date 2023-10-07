/*    */ package distributedPN;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import trans.CmdLineError;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Root;
/*    */ import ujf.verimag.bip.Core.Modules.System;
/*    */ import ujf.verimag.bip.bip2src.Reverse;
/*    */ import ujf.verimag.bip.parser.ErrorMessage;
/*    */ import ujf.verimag.bip.parser.actions.Parser;
/*    */ 
/*    */ public class Main {
/* 18 */   private static Root top = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] arg) throws FileNotFoundException {
/* 25 */     CmdLineError err = new CmdLineError();
/*    */     
/* 27 */     ArrayList<String> includeDirectories = new ArrayList();
/* 28 */     Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/* 29 */     String includeDir = "";
/* 30 */     includeDirectories.add(String.valueOf(includeDir) + "/");
/* 31 */     Module[] bipModel = Parser.parse("/Users/mohamad/Documents/workspace/Transformations/src/tmp/a.bip", includeDirectories, libFullNames, (ErrorMessage)err);
/*    */ 
/*    */ 
/*    */     
/* 35 */     TransformationFunction.Initialize(bipModel[0]);
/*    */ 
/*    */     
/* 38 */     System sys = (System)bipModel[0];
/* 39 */     top = sys.getRoot();
/* 40 */     CompoundType CT = (CompoundType)top.getType();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     DCompoundType DCT = new DCompoundType(CT);
/*    */ 
/*    */     
/* 49 */     FileOutputStream out = new FileOutputStream("/Users/mohamad/Documents/workspace/Transformations/src/tmp/adist.bip");
/* 50 */     PrintStream X = new PrintStream(out);
/* 51 */     Reverse a = new Reverse(X);
/* 52 */     a.decompile(bipModel[0]);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */