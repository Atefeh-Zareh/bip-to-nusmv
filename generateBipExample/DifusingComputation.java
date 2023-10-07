/*    */ package generateBipExample;
/*    */ 
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class DifusingComputation
/*    */ {
/*    */   private static Integer Rows;
/*    */   private static Integer Columns;
/*    */   private static FileOutputStream BIPFile;
/*    */   private static final String Separator = "_";
/*    */   
/*    */   public static void main(String[] s) throws IOException {
/* 14 */     Rows = Integer.decode(s[0]);
/* 15 */     Columns = Integer.decode(s[1]);
/* 16 */     String FileName = new String("DifusingComputation.bip");
/* 17 */     BIPFile = new FileOutputStream(FileName);
/* 18 */     BIPFile.write("compound type Grid\n".getBytes());
/* 19 */     CreateComponents();
/* 20 */     CreateConnectors();
/* 21 */     BIPFile.write("end\n".getBytes());
/* 22 */     String str = "component Grid g\n";
/* 23 */     BIPFile.write(str.getBytes());
/* 24 */     BIPFile.write("end".getBytes());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void CreateComponents() throws IOException {
/* 31 */     for (int i = 0; i < Rows.intValue(); i++) {
/* 32 */       for (int j = 0; j < Columns.intValue(); j++) {
/* 33 */         int index = Columns.intValue() * i + j + 1;
/* 34 */         int father = getFather(i, j);
/* 35 */         String str = "\t component Process process" + i + "_" + j + "( " + index + " , " + father + " ) \n";
/* 36 */         BIPFile.write(str.getBytes());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int getFather(int i, int j) {
/* 42 */     int index = Columns.intValue() * i + j + 1;
/* 43 */     if (i == 0)
/* 44 */       return index + Columns.intValue() * (Rows.intValue() - 1); 
/* 45 */     if (i < Rows.intValue() - 1)
/* 46 */       return index + Columns.intValue(); 
/* 47 */     if (index != Rows.intValue() * Columns.intValue()) {
/* 48 */       return index + 1;
/*    */     }
/* 50 */     return Rows.intValue() * Columns.intValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void CreateConnectors() throws IOException {
/* 56 */     BIPFile.write("\n\n// Connector".getBytes());
/* 57 */     int k = 1;
/* 58 */     for (int i = 0; i < Rows.intValue(); i++) {
/* 59 */       for (int j = 0; j < Columns.intValue(); j++) {
/* 60 */         BIPFile.write("\n".getBytes());
/*    */ 
/*    */         
/* 63 */         String connectorreset = "\t connector SynchReset reset" + i + "_" + j + "(process" + i + "_" + j + ".resetPort" + 
/* 64 */           " , process" + i + "_" + ((j + 1) % Columns.intValue()) + ".resetPort " + 
/* 65 */           " , process" + i + "_" + ((j - 1 + Columns.intValue()) % Columns.intValue()) + ".resetPort " + 
/* 66 */           " , process" + ((i - 1 + Rows.intValue()) % Rows.intValue()) + "_" + j + ".resetPort " + 
/* 67 */           ",  process" + ((i + 1) % Rows.intValue()) + "_" + j + ".resetPort)  \n";
/*    */         
/* 69 */         String connectorcompolete = "\t connector SynchComplete comp" + i + "_" + j + "(process" + i + "_" + j + ".completePort" + 
/* 70 */           " , process" + i + "_" + ((j + 1) % Columns.intValue()) + ".completePort " + 
/* 71 */           " , process" + i + "_" + ((j - 1 + Columns.intValue()) % Columns.intValue()) + ".completePort " + 
/* 72 */           " , process" + ((i - 1 + Rows.intValue()) % Rows.intValue()) + "_" + j + ".completePort " + 
/* 73 */           ",  process" + ((i + 1) % Rows.intValue()) + "_" + j + ".completePort)  \n";
/*    */         
/* 75 */         BIPFile.write(connectorreset.getBytes());
/* 76 */         BIPFile.write(connectorcompolete.getBytes());
/*    */ 
/*    */         
/* 79 */         System.out.println("List<String> IP" + k + " = new java.util.LinkedList<String>() ;");
/* 80 */         System.out.println("IP" + k + ".add(\"reset" + i + "_" + j + "\");");
/* 81 */         System.out.println("IP" + k + ".add(\"comp" + i + "_" + j + "\");");
/* 82 */         System.out.println("LLInteractionName.add(IP" + k + ");");
/*    */         
/* 84 */         k++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\generateBipExample\DifusingComputation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */