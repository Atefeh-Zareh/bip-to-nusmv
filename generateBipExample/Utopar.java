/*     */ package generateBipExample;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class Utopar
/*     */ {
/*   9 */   private static int NbCallingUnits = 7;
/*  10 */   private static int NbCars = 2;
/*     */   private static FileOutputStream BIPFile;
/*     */   
/*     */   public static void main(String[] s) throws IOException {
/*  14 */     String FileName = new String("/Users/mohamad/Desktop/utopar2_7.bip");
/*  15 */     BIPFile = new FileOutputStream(FileName);
/*  16 */     BIPFile.write("compound type Utopar\n".getBytes());
/*  17 */     CreateComponents();
/*  18 */     CreateConnectors();
/*  19 */     BIPFile.write("end\n".getBytes());
/*  20 */     String str = "component Utopar u\n";
/*  21 */     BIPFile.write(str.getBytes());
/*  22 */     BIPFile.write("end".getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void CreateComponents() throws IOException {
/*     */     int i;
/*  30 */     for (i = 0; i < NbCallingUnits; i++) {
/*  31 */       for (int j = 0; j < NbCallingUnits; j++) {
/*  32 */         String str1 = "\t component CallingUnit u" + i + j + "( " + (i + 1) + " , " + (j + 1) + " ) \n";
/*  33 */         BIPFile.write(str1.getBytes());
/*     */       } 
/*     */     } 
/*     */     
/*  37 */     for (i = 0; i < NbCars; i++) {
/*  38 */       String str1 = "\t component Car c" + i + "( " + NbCallingUnits + ",1,1 , " + i + " ) \n";
/*  39 */       BIPFile.write(str1.getBytes());
/*     */     } 
/*     */ 
/*     */     
/*  43 */     String str = "\t component CentralStation s( " + NbCars + ", " + NbCallingUnits + " ) \n";
/*  44 */     BIPFile.write(str.getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void CreateConnectors() throws IOException {
/*  50 */     BIPFile.write("\n\n// Connector".getBytes());
/*     */ 
/*     */     
/*  53 */     for (int i = 0; i < NbCallingUnits; i++) {
/*  54 */       for (int k = 0; k < NbCallingUnits; k++) {
/*  55 */         for (int m = 0; m < NbCars; m++) {
/*  56 */           String str = "\t connector EnterConnector enter" + i + k + m + "( u" + i + k + ".enter, c" + m + ".enter ) \n";
/*  57 */           BIPFile.write(str.getBytes());
/*  58 */           System.out.println("IP" + i + k + ".add(\"enter\"" + i + k + m + ");");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  64 */     String exit = "", awake = "", open = "", close = "", xpos = "", ypos = "", dep = "", arrival = "", destination = "", request = ""; int j;
/*  65 */     for (j = 0; j < NbCars; j++) {
/*  66 */       exit = String.valueOf(exit) + "\t connector ExitConnector exit" + j + "( c" + j + ".exitport) \n";
/*  67 */       awake = String.valueOf(awake) + "\t connector AwakeConnector awake" + j + "( c" + j + ".awake, s.awake) \n";
/*  68 */       open = String.valueOf(open) + "\t connector OpenConnector open" + j + "( c" + j + ".open, s.open) \n";
/*  69 */       close = String.valueOf(close) + "\t connector CloseConnector close" + j + "( c" + j + ".closeport, s.closeport) \n";
/*  70 */       xpos = String.valueOf(xpos) + "\t connector PosChangedConnector xposchange" + j + "( c" + j + ".xposChanged, s.xposChanged) \n";
/*  71 */       ypos = String.valueOf(ypos) + "\t connector PosChangedConnector yposchange" + j + "( c" + j + ".yposChanged, s.yposChanged) \n";
/*  72 */       dep = String.valueOf(dep) + "\t connector DepartureConnector departure" + j + "( c" + j + ".departure, s.departure) \n";
/*  73 */       arrival = String.valueOf(arrival) + "\t connector ArrivalConnector arrival" + j + "( c" + j + ".arrival, s.arrival) \n";
/*  74 */       destination = String.valueOf(destination) + "\t connector DestinationConnector destination" + j + "( c" + j + ".destination, s.destination) \n";
/*  75 */       System.out.println("IP" + j + ".add(\"exit\"" + j + ");");
/*  76 */       System.out.println("IP" + j + ".add(\"awake\"" + j + ");");
/*  77 */       System.out.println("IP" + j + ".add(\"open\"" + j + ");");
/*  78 */       System.out.println("IP" + j + ".add(\"close\"" + j + ");");
/*  79 */       System.out.println("IP" + j + ".add(\"xposchange\"" + j + ");");
/*  80 */       System.out.println("IP" + j + ".add(\"yposchange\"" + j + ");");
/*  81 */       System.out.println("IP" + j + ".add(\"departure\"" + j + ");");
/*  82 */       System.out.println("IP" + j + ".add(\"arrival\"" + j + ");");
/*  83 */       System.out.println("IP" + j + ".add(\"destination\"" + j + ");");
/*     */     } 
/*     */ 
/*     */     
/*  87 */     BIPFile.write(exit.getBytes());
/*  88 */     BIPFile.write(awake.getBytes());
/*  89 */     BIPFile.write(open.getBytes());
/*  90 */     BIPFile.write(close.getBytes());
/*  91 */     BIPFile.write(xpos.getBytes());
/*  92 */     BIPFile.write(ypos.getBytes());
/*  93 */     BIPFile.write(dep.getBytes());
/*  94 */     BIPFile.write(arrival.getBytes());
/*  95 */     BIPFile.write(destination.getBytes());
/*     */ 
/*     */     
/*  98 */     for (j = 0; j < NbCallingUnits; j++) {
/*  99 */       for (int k = 0; k < NbCallingUnits; k++) {
/* 100 */         request = "\t connector RequestConnector request" + j + k + "( u" + j + k + ".request, s.request) \n";
/* 101 */         BIPFile.write(request.getBytes());
/* 102 */         System.out.println("IP" + j + k + ".add(\"request\"" + j + k + ");");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\generateBipExample\Utopar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */