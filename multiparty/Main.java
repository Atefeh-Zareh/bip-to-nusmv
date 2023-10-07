/*    */ package multiparty;
/*    */ import BIP2BIP.Init;
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Root;
/*    */ import ujf.verimag.bip.Core.Modules.System;
/*    */ 
/*    */ public class Main {
/* 13 */   private static Root top = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] arg) throws IOException {
/* 18 */     String inputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/treeadd5flat.bip";
/* 19 */     String outputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/treeadd5multi.bip";
/*    */     
/* 21 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/* 22 */     Module M = CT.getModule();
/*    */     
/* 24 */     TransformationFunction.Initialize(M);
/* 25 */     Init.Initialize(M, TransformationFunction.PTSyn);
/*    */ 
/*    */ 
/*    */     
/* 29 */     Initialize.INIT(CT);
/* 30 */     Top TOP = new Top(CT);
/* 31 */     TOP.FlatTop();
/* 32 */     System sys = (System)M;
/* 33 */     top = sys.getRoot();
/* 34 */     top.setType((ComponentType)TOP.getTopSR());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     FileOutputStream outc = new FileOutputStream("/Users/mohamad/Documents/workspace/Transformations/src/multiparty/Protocol/Protocol.cpp");
/* 41 */     outc.write(Initialize.Function_C.getBytes());
/*    */     
/* 43 */     TransformationFunction.CreateBIPFile(outputfile, M);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */