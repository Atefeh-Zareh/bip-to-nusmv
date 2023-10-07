/*    */ package multiparty;
/*    */ 
/*    */ import BIP2BIP.FlatBip2;
/*    */ import BIP2BIP.Init;
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.FileNotFoundException;
/*    */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*    */ import ujf.verimag.bip.Core.Interactions.Component;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Root;
/*    */ import ujf.verimag.bip.Core.Modules.System;
/*    */ 
/*    */ public class Mainflat
/*    */ {
/* 16 */   private static Root top = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] arg) throws FileNotFoundException {
/* 23 */     String inputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/x.bip";
/* 24 */     String outputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/y.bip";
/*    */ 
/*    */     
/* 27 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/* 28 */     Module M = CT.getModule();
/*    */ 
/*    */     
/* 31 */     TransformationFunction.Initialize(M);
/*    */     
/* 33 */     Init.Initialize(M, TransformationFunction.PTSyn);
/*    */ 
/*    */     
/* 36 */     for (Object o : CT.getSubcomponent()) {
/*    */       
/* 38 */       Component component = (Component)o;
/* 39 */       FlatBip2.Flat(component.getName(), CT);
/*    */     } 
/* 41 */     System sys = (System)M;
/* 42 */     top = sys.getRoot();
/* 43 */     top.setType((ComponentType)CT);
/*    */     
/* 45 */     TransformationFunction.CreateBIPFile(outputfile, M);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Mainflat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */