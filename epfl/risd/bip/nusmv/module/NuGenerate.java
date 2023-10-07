/*    */ package epfl.risd.bip.nusmv.module;
/*    */ 
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collection;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuGenerate
/*    */ {
/*    */   public NuGenerate(String inputFile, String guideFile, File outputFile, NuVisitor v) {
/*    */     try {
/* 16 */       CompoundType ct = TransformationFunction.ParseBIPFile(inputFile);
/*    */       
/* 18 */       PrintStream output = new PrintStream(outputFile);
/*    */       
/* 20 */       v.execute(ct, guideFile);
/*    */       
/* 22 */       Collection<NuModule> modules = v.getModules();
/* 23 */       NuModule main = v.getMain();
/*    */       
/* 25 */       for (NuModule module : modules) {
/* 26 */         output.println(module.toString());
/*    */       }
/* 28 */       output.println(main.toString(true));
/*    */     }
/* 30 */     catch (FileNotFoundException e) {
/* 31 */       throw new Error("Output File Not Found");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\module\NuGenerate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */