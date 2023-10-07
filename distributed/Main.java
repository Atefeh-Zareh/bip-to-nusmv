/*    */ package distributed;
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.FileNotFoundException;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Root;
/*    */ import ujf.verimag.bip.Core.Modules.System;
/*    */ 
/*    */ public class Main {
/* 10 */   private static Root top = null;
/*    */ 
/*    */   
/*    */   public static void main(String[] arg) throws FileNotFoundException {
/* 14 */     String inputfile = "/home/jaberm/work/bipExamples/wajeb/test.bip";
/* 15 */     String outputfile = "/home/jaberm/work/bipExamples/wajeb/testsr.bip";
/*    */     
/* 17 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/* 18 */     Module M = CT.getModule();
/*    */ 
/*    */ 
/*    */     
/* 22 */     TransformationFunction.Initialize(M);
/*    */     
/* 24 */     DCompoundType DCT = new DCompoundType(CT);
/* 25 */     System sys = (System)M;
/* 26 */     top = sys.getRoot();
/* 27 */     top.setType((ComponentType)DCT.getCompTypeNew());
/*    */     
/* 29 */     TransformationFunction.CreateBIPFile(outputfile, M);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */