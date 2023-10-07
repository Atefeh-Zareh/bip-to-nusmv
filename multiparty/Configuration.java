/*     */ package multiparty;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ 
/*     */ 
/*     */ public class Configuration
/*     */ {
/*     */   static CompoundType compoundType;
/*  21 */   private static Map<String, String> Map_Comp_IP = new HashMap<String, String>();
/*  22 */   private static Map<String, Integer> Map_Comp_Port = new HashMap<String, Integer>();
/*  23 */   private static Map<String, Integer> Map_Comp_ID = new HashMap<String, Integer>();
/*     */   
/*  25 */   private static List<String> LComponent = new LinkedList<String>();
/*  26 */   private static List<List<String>> CompRequest = new LinkedList<List<String>>();
/*  27 */   private static List<List<String>> CompListen = new LinkedList<List<String>>();
/*     */ 
/*     */ 
/*     */   
/*  31 */   static Integer id = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void Init(CompoundType compType, String IPPath) throws IOException {
/*  38 */     compoundType = compType;
/*  39 */     File file = new File(IPPath);
/*  40 */     FileInputStream fis = null;
/*  41 */     BufferedInputStream bis = null;
/*  42 */     DataInputStream dis = null;
/*     */ 
/*     */     
/*  45 */     fis = new FileInputStream(file);
/*     */     
/*  47 */     bis = new BufferedInputStream(fis);
/*  48 */     dis = new DataInputStream(bis);
/*  49 */     String[] IPPort = (String[])null;
/*  50 */     String Line = new String();
/*  51 */     String compName = new String();
/*     */     
/*  53 */     for (Object o : compoundType.getSubcomponent()) {
/*     */       
/*  55 */       Component comp = (Component)o;
/*  56 */       LComponent.add(comp.getName());
/*  57 */       List<String> Request = new LinkedList<String>();
/*  58 */       List<String> Listen = new LinkedList<String>();
/*  59 */       CompRequest.add(Request);
/*  60 */       CompListen.add(Listen);
/*  61 */       if ((Line = dis.readLine()) != null) {
/*     */         
/*  63 */         IPPort = Line.split(" ");
/*  64 */         compName = comp.getName();
/*  65 */         Map_Comp_IP.put(compName, IPPort[0]);
/*  66 */         Map_Comp_Port.put(compName, Integer.decode(IPPort[1]));
/*  67 */         id = Integer.valueOf(id.intValue() + 1); Map_Comp_ID.put(compName, id);
/*     */         continue;
/*     */       } 
/*  70 */       System.out.println("There is no enough of IP and port. See IP configuration file.");
/*     */     } 
/*     */     
/*  73 */     fis.close();
/*  74 */     bis.close();
/*  75 */     dis.close();
/*  76 */     Initialize_Request_Listen();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void Initialize_Request_Listen() {
/*  81 */     for (Object o : compoundType.getConnector()) {
/*     */       
/*  83 */       Connector conn = (Connector)o;
/*  84 */       InnerPortReference IPRSend = (InnerPortReference)conn.getActualPort().get(0);
/*  85 */       InnerPortReference IPRRec = (InnerPortReference)conn.getActualPort().get(1);
/*     */       
/*  87 */       String compName1 = IPRSend.getTargetInstance().getTargetPart().getName();
/*  88 */       String compName2 = IPRRec.getTargetInstance().getTargetPart().getName();
/*     */       
/*  90 */       int indexcompName1 = LComponent.indexOf(compName1);
/*  91 */       int indexcompName2 = LComponent.indexOf(compName2);
/*     */       
/*  93 */       Integer ID1 = getID(compName1);
/*  94 */       Integer ID2 = getID(compName2);
/*     */       
/*  96 */       if (ID1.intValue() < ID2.intValue()) {
/*     */         
/*  98 */         if (!((List)CompRequest.get(indexcompName1)).contains(compName2))
/*  99 */           ((List<String>)CompRequest.get(indexcompName1)).add(compName2); 
/* 100 */         if (!((List)CompListen.get(indexcompName2)).contains(compName1)) {
/* 101 */           ((List<String>)CompListen.get(indexcompName2)).add(compName1);
/*     */         }
/*     */         continue;
/*     */       } 
/* 105 */       if (!((List)CompListen.get(indexcompName1)).contains(compName2))
/* 106 */         ((List<String>)CompListen.get(indexcompName1)).add(compName2); 
/* 107 */       if (!((List)CompRequest.get(indexcompName2)).contains(compName1)) {
/* 108 */         ((List<String>)CompRequest.get(indexcompName2)).add(compName1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getListen(String CompName) {
/* 115 */     return CompListen.get(LComponent.indexOf(CompName));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getRequest(String CompName) {
/* 120 */     return CompRequest.get(LComponent.indexOf(CompName));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIP(String CompName) {
/* 126 */     return Map_Comp_IP.get(CompName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getPort(String CompName) {
/* 131 */     return Map_Comp_Port.get(CompName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getID(String CompName) {
/* 136 */     return Map_Comp_ID.get(CompName);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Configuration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */