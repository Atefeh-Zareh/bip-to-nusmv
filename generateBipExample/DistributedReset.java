/*     */ package generateBipExample;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class DistributedReset
/*     */ {
/*     */   private static Integer Rows;
/*     */   private static Integer Columns;
/*     */   private static FileOutputStream BIPFile;
/*     */   
/*     */   public static void main(String[] s) throws IOException {
/*  14 */     Rows = Integer.decode(s[0]);
/*  15 */     Columns = Integer.decode(s[1]);
/*  16 */     String FileName = new String("DistributedReset.bip");
/*  17 */     BIPFile = new FileOutputStream(FileName);
/*  18 */     String EndConnectorType = CreateEndConnectorType();
/*  19 */     BIPFile.write(EndConnectorType.getBytes());
/*  20 */     BIPFile.write("compound type Grid(int R, int C)\n".getBytes());
/*  21 */     CreateComponents();
/*  22 */     CreateConnectorsForestiNeighborIc();
/*     */ 
/*     */ 
/*     */     
/*  26 */     CreateConnectorEnd();
/*  27 */     BIPFile.write("end\n".getBytes());
/*     */     
/*  29 */     String str = "component Grid g(" + Rows + ", " + Columns + ")\n";
/*  30 */     BIPFile.write(str.getBytes());
/*  31 */     BIPFile.write("end".getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void CreateConnectorEnd() throws IOException {
/*  38 */     String syncTOP = "\t connector EndConnectorType syncTop (";
/*  39 */     for (int i = 0; i < Rows.intValue(); i++) {
/*  40 */       for (int j = 0; j < Columns.intValue(); j++) {
/*  41 */         if (i == 0 && j == 0) {
/*  42 */           syncTOP = String.valueOf(syncTOP) + " tree" + i + j + ".steadyState";
/*     */         } else {
/*  44 */           syncTOP = String.valueOf(syncTOP) + " , tree" + i + j + ".steadyState";
/*     */         } 
/*     */       } 
/*  47 */     }  syncTOP = String.valueOf(syncTOP) + ")";
/*  48 */     BIPFile.write(syncTOP.getBytes());
/*  49 */     BIPFile.write("\n".getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateConnectorsSyncTreeSteadyStateTOP() throws IOException {
/*  53 */     String syncTOP = "\t connector syncTreeSteadyStateTop syncTop (syncCol" + (Rows.intValue() - 2) + ".xp)\n";
/*  54 */     BIPFile.write(syncTOP.getBytes());
/*  55 */     BIPFile.write("\n".getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateComponents() throws IOException {
/*  59 */     for (int i = 0; i < Rows.intValue(); i++) {
/*  60 */       for (int j = 0; j < Columns.intValue(); j++) {
/*  61 */         String str = "\t component Tree tree" + i + j + "(" + i + " , " + j + ", R, C) \n";
/*  62 */         BIPFile.write(str.getBytes());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void CreateConnectorsSyncTreeSteadyStateRow() throws IOException {
/*  70 */     for (int i = 0; i < Rows.intValue(); i++) {
/*  71 */       for (int j = 0; j < Columns.intValue() - 1; j++) {
/*  72 */         if (j != 0) {
/*  73 */           String syncRow = "\t connector syncTreeSteadyState syncRow" + i + j + "(syncRow" + i + (j - 1) + ".xp" + 
/*  74 */             " , SS" + i + (j + 1) + ".xp )  \n";
/*  75 */           BIPFile.write(syncRow.getBytes());
/*  76 */           BIPFile.write("\n".getBytes());
/*     */         } else {
/*     */           
/*  79 */           String syncRow = "\t connector syncTreeSteadyState syncRow" + i + j + "(SS" + i + Character.MIN_VALUE + ".xp" + 
/*  80 */             " , SS" + i + (j + 1) + ".xp )  \n";
/*  81 */           BIPFile.write(syncRow.getBytes());
/*  82 */           BIPFile.write("\n".getBytes());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void CreateConnectorsSyncTreeSteadyStateColumn() throws IOException {
/*  89 */     for (int i = 0; i < Rows.intValue() - 1; i++) {
/*  90 */       if (i != 0) {
/*  91 */         String syncCol = "\t connector syncTreeSteadyState syncCol" + i + "(syncCol" + (i - 1) + ".xp" + 
/*  92 */           " , syncRow" + (i + 1) + (Columns.intValue() - 2) + ".xp )  \n";
/*  93 */         BIPFile.write(syncCol.getBytes());
/*  94 */         BIPFile.write("\n".getBytes());
/*     */       } else {
/*     */         
/*  97 */         String syncCol = "\t connector syncTreeSteadyState syncCol" + i + "(syncRow" + Character.MIN_VALUE + (Columns.intValue() - 2) + ".xp" + 
/*  98 */           " , syncRow" + (i + 1) + (Columns.intValue() - 2) + ".xp )  \n";
/*  99 */         BIPFile.write(syncCol.getBytes());
/* 100 */         BIPFile.write("\n".getBytes());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void CreateConnectorsForestiNeighborIc() throws IOException {
/* 107 */     BIPFile.write("\n\n// Connector".getBytes());
/* 108 */     for (int i = 0; i < Rows.intValue(); i++) {
/* 109 */       for (int j = 0; j < Columns.intValue(); j++) {
/* 110 */         BIPFile.write("\n".getBytes());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         String forestHorz = "\t connector forestDetect forestHorz" + i + j + "(tree" + i + j + ".mForest , tree" + i + ((j + 1) % Columns.intValue()) + ".mForest) \n";
/* 118 */         String forestVert = "\t connector forestDetect forestVert" + i + j + "(tree" + i + j + ".mForest , tree" + ((i + 1) % Rows.intValue()) + j + ".mForest) \n";
/*     */ 
/*     */         
/* 121 */         String icNeighborHorz = "\t connector neighborIc icNeighborHorz" + i + j + "(tree" + i + j + ".icNeighbor , tree" + i + ((j + 1) % Columns.intValue()) + ".icNeighbor) \n";
/* 122 */         String icNeighborVert = "\t connector neighborIc icNeighborVert" + i + j + "(tree" + i + j + ".icNeighbor , tree" + ((i + 1) % Rows.intValue()) + j + ".icNeighbor) \n";
/*     */         
/* 124 */         System.out.println("IP1.add(\"forestHorz" + i + j + "\");");
/* 125 */         System.out.println("IP2.add(\"forestVert" + i + j + "\");");
/* 126 */         System.out.println("IP3.add(\"icNeighborHorz" + i + j + "\");");
/* 127 */         System.out.println("IP2.add(\"icNeighborVert" + i + j + "\");");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         BIPFile.write(forestHorz.getBytes());
/* 142 */         BIPFile.write(forestVert.getBytes());
/* 143 */         BIPFile.write(icNeighborHorz.getBytes());
/* 144 */         BIPFile.write(icNeighborVert.getBytes());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String CreateEndConnectorType() throws IOException {
/* 151 */     String EndConnectoryType = new String("connector type EndConnectorType ");
/* 152 */     String PortParameter = new String("(");
/* 153 */     String PortExpression = new String("define [ ");
/* 154 */     String Interaction = new String("on ");
/*     */     
/* 156 */     String Guard = new String("provided ( ");
/* 157 */     String ForestDetectFalseHorz = new String("");
/* 158 */     String ForestDetectFalseVer = new String("");
/* 159 */     String IcNeighborFalseHorz = new String("");
/* 160 */     String IcNeighborFalseVer = new String("");
/* 161 */     String Function = new String("");
/*     */     
/* 163 */     for (int i = 0; i < Rows.intValue(); i++) {
/* 164 */       for (int j = 0; j < Columns.intValue(); j++) {
/* 165 */         if (i == 0 && j == 0) {
/* 166 */           PortParameter = String.valueOf(PortParameter) + "intIntIntIntPort p" + i + j;
/*     */         } else {
/* 168 */           PortParameter = String.valueOf(PortParameter) + ", intIntIntIntPort p" + i + j;
/* 169 */         }  PortExpression = String.valueOf(PortExpression) + "p" + i + j + " ";
/* 170 */         Interaction = String.valueOf(Interaction) + "p" + i + j + " ";
/* 171 */         ForestDetectFalseHorz = String.valueOf(ForestDetectFalseHorz) + "!(( (p" + i + ((j + 1) % Columns.intValue()) + ".c2 < getKvalue()) && ((p" + i + j + ".c1 < p" + i + ((j + 1) % Columns.intValue()) + ".c1) || ((p" + i + j + ".c1 == p" + i + ((j + 1) % Columns.intValue()) + ".c1) && (p" + i + ((j + 1) % Columns.intValue()) + ".c2 + 1 < p" + i + j + ".c2))) ) || " + 
/* 172 */           "( (p" + i + j + ".c2 < getKvalue()) && ((p" + i + ((j + 1) % Columns.intValue()) + ".c1 < p" + i + j + ".c1) || ((p" + i + ((j + 1) % Columns.intValue()) + ".c1 == p" + i + j + ".c1) && (p" + i + j + ".c2 + 1 < p" + i + ((j + 1) % Columns.intValue()) + ".c2))) ) )  && \n";
/*     */         
/* 174 */         ForestDetectFalseVer = String.valueOf(ForestDetectFalseVer) + "!(( (p" + ((i + 1) % Rows.intValue()) + j + ".c2 < getKvalue()) && ((p" + i + j + ".c1 < p" + ((i + 1) % Rows.intValue()) + j + ".c1) || ((p" + i + j + ".c1 == p" + ((i + 1) % Rows.intValue()) + j + ".c1) && (p" + ((i + 1) % Rows.intValue()) + j + ".c2 + 1 < p" + i + j + ".c2))) ) || " + 
/* 175 */           " ( (p" + i + j + ".c2 < getKvalue()) && ((p" + ((i + 1) % Rows.intValue()) + j + ".c1 < p" + i + j + ".c1) || ((p" + ((i + 1) % Rows.intValue()) + j + ".c1 == p" + i + j + ".c1) && (p" + i + j + ".c2 + 1 < p" + ((i + 1) % Rows.intValue()) + j + ".c2))) )) && \n";
/*     */         
/* 177 */         IcNeighborFalseHorz = String.valueOf(IcNeighborFalseHorz) + "!(( (p" + i + j + ".c2 < getKvalue()) && (p" + i + j + ".c3 == p" + i + ((j + 1) % Columns.intValue()) + ".c4) && ((p" + i + j + ".c1 != p" + i + ((j + 1) % Columns.intValue()) + ".c1) || (p" + i + j + ".c2 != p" + i + ((j + 1) % Columns.intValue()) + ".c2 + 1)) ) || " + 
/* 178 */           " ( (p" + i + ((j + 1) % Columns.intValue()) + ".c2 < getKvalue()) && (p" + i + ((j + 1) % Columns.intValue()) + ".c3 == p" + i + j + ".c4) && ((p" + i + ((j + 1) % Columns.intValue()) + ".c1 != p" + i + j + ".c1) || (p" + i + ((j + 1) % Columns.intValue()) + ".c2 != p" + i + j + ".c2 + 1)) ) ) && \n";
/*     */         
/* 180 */         IcNeighborFalseVer = String.valueOf(IcNeighborFalseVer) + "!(( (p" + i + j + ".c2 < getKvalue()) && (p" + i + j + ".c3 == p" + ((i + 1) % Rows.intValue()) + j + ".c4) && ((p" + i + j + ".c1 != p" + ((i + 1) % Rows.intValue()) + j + ".c1) || (p" + i + j + ".c2 != p" + ((i + 1) % Rows.intValue()) + j + ".c2 + 1)) ) || " + 
/* 181 */           " ( (p" + ((i + 1) % Rows.intValue()) + j + ".c2 < getKvalue()) && (p" + ((i + 1) % Rows.intValue()) + j + ".c3 == p" + i + j + ".c4) && ((p" + ((i + 1) % Rows.intValue()) + j + ".c1 != p" + i + j + ".c1) || (p" + ((i + 1) % Rows.intValue()) + j + ".c2 != p" + i + j + ".c2 + 1)) ) ) && p" + i + j + ".c1 == " + (Rows.intValue() * Columns.intValue()) + " && \n";
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     PortParameter = String.valueOf(PortParameter) + ") \n";
/* 186 */     PortExpression = String.valueOf(PortExpression) + "] \n";
/* 187 */     Interaction = String.valueOf(Interaction) + "\n";
/* 188 */     Guard = String.valueOf(Guard) + ForestDetectFalseHorz + ForestDetectFalseVer + IcNeighborFalseHorz + IcNeighborFalseVer + " true ) \n ";
/* 189 */     Function = String.valueOf(Function) + "\t up {} \n \t down{printf(\"\\n\\nSPANNING TREE CREATED DONE\\n\\n\");exit(0);} \n end \n\n\n";
/* 190 */     EndConnectoryType = String.valueOf(EndConnectoryType) + PortParameter + PortExpression + Interaction + Guard + Function + "\n\n";
/* 191 */     return EndConnectoryType;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\generateBipExample\DistributedReset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */