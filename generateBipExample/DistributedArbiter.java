/*     */ package generateBipExample;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class DistributedArbiter
/*     */ {
/*     */   private static Integer Nb_Processors;
/*     */   private static String FileName;
/*     */   private static FileOutputStream BIPFile;
/*     */   
/*     */   public static void main(String[] s) throws IOException {
/*  13 */     Nb_Processors = Integer.decode(s[0]);
/*  14 */     FileName = "distarbiter" + Nb_Processors + ".bip";
/*  15 */     BIPFile = new FileOutputStream(FileName);
/*     */     
/*  17 */     BIPFile.write("model arbiter\n".getBytes());
/*  18 */     BIPFile.write("\n\nconnector type Sync2(Port p1, Port p2)\n".getBytes());
/*  19 */     BIPFile.write("  define p1 p2\n".getBytes());
/*  20 */     BIPFile.write("end\n\n\n".getBytes());
/*     */     
/*  22 */     CreateConnectorType();
/*  23 */     CreateAtomType();
/*  24 */     CreateCompoundType();
/*     */ 
/*     */     
/*  27 */     BIPFile.write("component ModelFair m\n".getBytes());
/*  28 */     BIPFile.write("end\n".getBytes());
/*  29 */     BIPFile.close();
/*     */   }
/*     */   
/*     */   private static void CreateCompoundType() throws IOException {
/*  33 */     String compound = "";
/*  34 */     compound = String.valueOf(compound) + "compound type ModelFair\n"; int i;
/*  35 */     for (i = 1; i <= Nb_Processors.intValue(); i++) {
/*     */       
/*  37 */       compound = String.valueOf(compound) + " component ArbiterBase A" + i + "\n";
/*  38 */       compound = String.valueOf(compound) + " component Processor P" + i + "\n";
/*  39 */       compound = String.valueOf(compound) + " connector Sync2 req" + i + " (P" + i + ".request, A" + i + ".request)\n";
/*  40 */       compound = String.valueOf(compound) + " connector Sync2 rel" + i + " (P" + i + ".release, A" + i + ".release)\n";
/*     */     } 
/*     */     
/*  43 */     compound = String.valueOf(compound) + "\n\n";
/*     */     
/*  45 */     for (i = 1; i <= Nb_Processors.intValue(); i++) {
/*     */       
/*  47 */       compound = String.valueOf(compound) + " connector SyncN grant" + i + " (";
/*  48 */       for (int j = 1; j <= Nb_Processors.intValue(); j++) {
/*     */         
/*  50 */         if (j == i) {
/*     */           
/*  52 */           compound = String.valueOf(compound) + "A" + j + ".grant, ";
/*     */         }
/*     */         else {
/*     */           
/*  56 */           compound = String.valueOf(compound) + "A" + j + ".accept, ";
/*     */         } 
/*     */       } 
/*  59 */       compound = String.valueOf(compound) + "P" + i + ".grant)\n";
/*     */     } 
/*  61 */     compound = String.valueOf(compound) + "end\n";
/*  62 */     BIPFile.write(compound.getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateConnectorType() throws IOException {
/*  66 */     BIPFile.write("connector type SyncN(".getBytes());
/*  67 */     String Parameters = "";
/*  68 */     String Interaction = "\tdefine ";
/*  69 */     for (int i = 1; i <= Nb_Processors.intValue(); i++) {
/*     */       
/*  71 */       Parameters = String.valueOf(Parameters) + "Port p" + i + ", ";
/*  72 */       Interaction = String.valueOf(Interaction) + "p" + i + " ";
/*     */     } 
/*  74 */     int N = Nb_Processors.intValue() + 1;
/*  75 */     Parameters = String.valueOf(Parameters) + "Port p" + N + ") " + "\n";
/*  76 */     Interaction = String.valueOf(Interaction) + "p" + N + "\n";
/*  77 */     BIPFile.write(Parameters.getBytes());
/*  78 */     BIPFile.write(Interaction.getBytes());
/*  79 */     BIPFile.write("end\n\n".getBytes());
/*     */   }
/*     */   
/*     */   private static void CreateAtomType() throws IOException {
/*  83 */     String Arbiter = "";
/*  84 */     String Processor = "";
/*     */     
/*  86 */     Arbiter = String.valueOf(Arbiter) + "atomic type ArbiterBase\n export port Port request\n export port Port accept\n export port Port release\n export port Port grant\n\n place q0\n place q1\n place q2\n\n initial to q0 do {}\n   on accept from q0 to q0 provided true do {}\n   on accept from q1 to q1 provided true do {}\n   on request from q0 to q1 provided true do {}\n   on grant from q1 to q2 provided true do {}\n   on release from q2 to q0 provided true do {}\nend\n\n\n";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     Processor = String.valueOf(Processor) + "atomic type Processor\n export port Port request\n export port Port release\n export port Port grant\n\n place q0\n place q1\n place q2\n\n initial to q0 do {}\n   on request from q0 to q1 provided true do {}\n   on grant from q1 to q2 provided true do {}\n   on release from q2 to q0 provided true do {}\nend\n\n\n";
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
/*     */ 
/*     */     
/* 122 */     BIPFile.write(Arbiter.getBytes());
/* 123 */     BIPFile.write(Processor.getBytes());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\generateBipExample\DistributedArbiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */