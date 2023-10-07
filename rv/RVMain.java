/*    */ package rv;
/*    */ 
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.FileNotFoundException;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RVMain
/*    */ {
/*    */   public static void main(String[] s) throws FileNotFoundException {
/* 12 */     String inputfile = s[0];
/* 13 */     String outputfile = s[1];
/* 14 */     String XMLMonitorPath = s[2];
/* 15 */     String Map_Event_Guard = s[3];
/* 16 */     String GuideMonitor = s[4];
/* 17 */     CompoundType ct = TransformationFunction.ParseBIPFile(inputfile);
/*    */     
/* 19 */     System.out.println(inputfile);
/*    */     
/* 21 */     BIPMonitor Bipmonitor = new BIPMonitor(ct, XMLMonitorPath, Map_Event_Guard, GuideMonitor);
/* 22 */     TransformationFunction.CreateBIPFile(outputfile, ct.getModule());
/* 23 */     System.out.println("The BIP monitor has been automatically generated -> " + outputfile);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\rv\RVMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */