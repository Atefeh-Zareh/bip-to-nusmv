/*    */ package unifiedDistributed;
/*    */ 
/*    */ import BIPTransformation.TransformationFunction;
/*    */ import java.io.IOException;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static OpaqueElement Header;
/*    */   public static final boolean debug = false;
/* 21 */   public static String AtomTypeName = "AtomTypeName";
/* 22 */   public static String FileName = "FileName";
/* 23 */   public static String PrintDebug = new String("void PrintDebug (String " + AtomTypeName + ", FILE * " + FileName + ") {");
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
/*    */   public static void main(String[] arg) throws IOException {
/* 40 */     String inputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/a.bip";
/* 41 */     String outputfile = "/Users/mohamad/Documents/workspace/Transformations/src/tmp/a1TR.bip";
/*    */     
/* 43 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     TOP top = new TOP(CT, InteractionRepartition(), 2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     TransformationFunction.CreateBIPFile(outputfile, CT.getModule());
/*    */   }
/*    */   
/*    */   private static List<List<String>> InteractionRepartition() {
/* 60 */     List<List<String>> LLInteractionName = new LinkedList<List<String>>();
/* 61 */     List<String> IP1 = new LinkedList<String>();
/* 62 */     List<String> IP2 = new LinkedList<String>();
/* 63 */     List<String> IP3 = new LinkedList<String>();
/*    */     
/* 65 */     IP1.add("t01");
/* 66 */     IP1.add("t08");
/* 67 */     IP1.add("t22");
/* 68 */     IP1.add("t20");
/* 69 */     IP1.add("t15");
/* 70 */     IP1.add("t12");
/* 71 */     IP1.add("t18");
/* 72 */     IP1.add("t07");
/*    */     
/* 74 */     IP2.add("t02");
/* 75 */     IP2.add("t21");
/* 76 */     IP2.add("t14");
/* 77 */     IP2.add("t24");
/* 78 */     IP2.add("t03");
/* 79 */     IP2.add("t19");
/* 80 */     IP2.add("t11");
/* 81 */     IP2.add("t06");
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 86 */     IP3.add("t09");
/* 87 */     IP3.add("t16");
/* 88 */     IP3.add("t04");
/* 89 */     IP3.add("t10");
/* 90 */     IP3.add("t23");
/* 91 */     IP3.add("t17");
/* 92 */     IP3.add("t13");
/* 93 */     IP3.add("t05");
/*    */     
/* 95 */     LLInteractionName.add(IP1);
/* 96 */     LLInteractionName.add(IP2);
/* 97 */     LLInteractionName.add(IP3);
/*    */     
/* 99 */     return LLInteractionName;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */