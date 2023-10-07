/*    */ package BIP2BIP;
/*    */ 
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.IOException;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static void main(String[] arg) throws IOException {
/* 16 */     String inputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/treeadd5flat.bip";
/* 17 */     String outputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/treeadd5multi.bip";
/*    */     
/* 19 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/* 20 */     Module M = CT.getModule();
/* 21 */     TransformationFunction.CreateBIPFile(outputfile, M);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */