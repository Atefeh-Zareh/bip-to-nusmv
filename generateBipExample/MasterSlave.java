/*     */ package generateBipExample;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class MasterSlave
/*     */ {
/*     */   private static Integer Nb_Masters;
/*     */   private static Integer Nb_Iterations;
/*  10 */   private static Integer debug = Integer.valueOf(1);
/*     */   
/*     */   private static Integer Nb_Slaves;
/*     */   
/*     */   private static String FileName;
/*     */   
/*     */   private static FileOutputStream BIPFile;
/*     */   
/*     */   public static void main(String[] s) throws IOException {
/*  19 */     Nb_Masters = Integer.decode(s[0]);
/*  20 */     Nb_Iterations = Integer.decode(s[1]);
/*  21 */     debug = Integer.decode(s[2]);
/*     */     
/*  23 */     Nb_Slaves = Integer.valueOf(2 * Nb_Masters.intValue());
/*  24 */     FileName = "ms" + Nb_Masters + ".bip";
/*  25 */     BIPFile = new FileOutputStream(FileName);
/*     */     
/*  27 */     CreateHeader();
/*  28 */     CreateConnectorType();
/*  29 */     CreateAtomType();
/*  30 */     CreateCompoundType();
/*     */ 
/*     */     
/*  33 */     BIPFile.write("component Team t\n".getBytes());
/*  34 */     BIPFile.write("end\n".getBytes());
/*  35 */     BIPFile.close();
/*     */   }
/*     */   
/*     */   private static void CreateHeader() throws IOException {
/*  39 */     String header = "";
/*     */     
/*  41 */     header = String.valueOf(header) + "model ms\n";
/*     */     
/*  43 */     header = String.valueOf(header) + "header \n";
/*  44 */     header = String.valueOf(header) + "{#\n";
/*  45 */     header = String.valueOf(header) + "#define max_interaction  " + Nb_Iterations + "\n";
/*     */     
/*  47 */     header = String.valueOf(header) + "\tint getMax_Interaction() {return max_interaction; }\n";
/*  48 */     header = String.valueOf(header) + "\tint globalcounter = 0;\n";
/*  49 */     header = String.valueOf(header) + "#}\n";
/*  50 */     header = String.valueOf(header) + "\nport type intPort(int i)\n\n";
/*  51 */     BIPFile.write(header.getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateCompoundType() throws IOException {
/*  55 */     String compound = "";
/*  56 */     compound = String.valueOf(compound) + "compound type Team\n"; int i;
/*  57 */     for (i = 1; i <= Nb_Masters.intValue(); i++)
/*     */     {
/*  59 */       compound = String.valueOf(compound) + " component Master master" + i + "(" + i + ")\n";
/*     */     }
/*     */     
/*  62 */     for (i = 1; i <= Nb_Slaves.intValue(); i++)
/*     */     {
/*  64 */       compound = String.valueOf(compound) + " component Slave slave" + i + "(" + i + ")\n";
/*     */     }
/*     */     
/*  67 */     compound = String.valueOf(compound) + "\n\n";
/*     */     
/*  69 */     for (i = 1; i <= Nb_Masters.intValue(); i++) {
/*     */       
/*  71 */       for (int j = 1; j <= Nb_Slaves.intValue(); j++) {
/*     */         
/*  73 */         compound = String.valueOf(compound) + " connector Sync2 communicate_" + i + "_" + j + " (master" + i + ".req , slave" + j + ".get)\n";
/*  74 */         for (int k = 1; k <= Nb_Slaves.intValue(); k++) {
/*     */           
/*  76 */           if (k != j)
/*     */           {
/*  78 */             compound = String.valueOf(compound) + " connector Sync3 work" + i + "_" + j + "_" + k + "(master" + i + ".compute , slave" + j + ".work, slave" + k + ".work)\n";
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  83 */     compound = String.valueOf(compound) + "end\n";
/*  84 */     BIPFile.write(compound.getBytes());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void CreateConnectorType() throws IOException {
/*  89 */     BIPFile.write("\n\nconnector type Sync2(intPort p1, intPort p2)\n".getBytes());
/*  90 */     BIPFile.write("  define [p1 p2]\n".getBytes());
/*  91 */     BIPFile.write("  on p1 p2 provided true\n".getBytes());
/*  92 */     BIPFile.write("    up {}\n".getBytes());
/*  93 */     BIPFile.write("    down {p2.i = p1.i;}\n".getBytes());
/*  94 */     BIPFile.write("end\n\n\n".getBytes());
/*     */ 
/*     */     
/*  97 */     BIPFile.write("\n\nconnector type Sync3(intPort p1, intPort p2, intPort p3)\n".getBytes());
/*  98 */     BIPFile.write("  define [p1 p2 p3]\n".getBytes());
/*  99 */     BIPFile.write("  on p1 p2 p3 provided ((p1.i == p2.i) && (p1.i == p3.i))\n".getBytes());
/* 100 */     BIPFile.write("    up {}\n".getBytes());
/* 101 */     BIPFile.write("    down {}\n".getBytes());
/* 102 */     BIPFile.write("end\n\n\n".getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateAtomType() throws IOException {
/* 106 */     String Master = "";
/* 107 */     String Slave = "";
/*     */     
/* 109 */     Master = String.valueOf(Master) + "atomic type Master(int id)\n  data int myid = id\n  export port intPort req(myid)\n  export port intPort compute(myid)\n\n  place l0, l1, l2\n\n  initial to l0 do {}\n    on req from l0 to l1 provided true do { " + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       getString("printf(\"Master %d sent request\\n\", myid);") + " }\n" + 
/* 118 */       "    on req from l1 to l2 provided true do { " + getString("printf(\"Master %d sent request\\n\", myid);") + " }\n" + 
/* 119 */       "    on compute from l2 to l0 provided true do { " + getString("printf(\"Master %d start computing with the two slaves\\n\", myid); ") + "}\n" + 
/* 120 */       "end\n\n\n";
/*     */ 
/*     */     
/* 123 */     Slave = String.valueOf(Slave) + "atomic type Slave(int id)\n  data int myid = id\n  data int id_server\n  export port intPort get(id_server)\n  export port intPort work(id_server)\n\n  place l0, l1\n\n initial to l0 do {}\n   on get from l0 to l1 provided globalcounter <  getMax_Interaction() do { globalcounter = globalcounter + 1; " + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       getString("printf(\"Slave %d receive get\\n\", myid); ") + "}\n" + 
/* 134 */       "   on work from l1 to l0 provided globalcounter <  getMax_Interaction() do { globalcounter = globalcounter + 1; " + getString("printf(\"Slave %d work with the master %d\\n\", myid , id_server); ") + "}\n" + 
/* 135 */       "end\n\n\n";
/*     */ 
/*     */     
/* 138 */     BIPFile.write(Master.getBytes());
/* 139 */     BIPFile.write(Slave.getBytes());
/*     */   }
/*     */   
/*     */   private static String getString(String s) {
/* 143 */     if (debug.intValue() != 0)
/* 144 */       return s; 
/* 145 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\generateBipExample\MasterSlave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */