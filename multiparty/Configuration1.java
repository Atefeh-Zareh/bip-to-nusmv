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
/*     */ public class Configuration1
/*     */ {
/*     */   static CompoundType compoundType;
/*  21 */   private static Map<String, String> Map_Comp_IP = new HashMap<String, String>();
/*  22 */   private static Map<String, String> Map_IP_Comp = new HashMap<String, String>();
/*  23 */   private static Map<String, Integer> Map_Comp_Port = new HashMap<String, Integer>();
/*  24 */   private static Map<String, Integer> Map_Comp_ID = new HashMap<String, Integer>();
/*     */   
/*  26 */   private static List<String> LComponent = new LinkedList<String>();
/*  27 */   private static List<List<String>> CompRequest = new LinkedList<List<String>>();
/*  28 */   private static List<List<String>> CompListen = new LinkedList<List<String>>();
/*     */ 
/*     */ 
/*     */   
/*  32 */   static Integer id = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void Init(CompoundType compType, String IPPath) throws IOException {
/*  39 */     compoundType = compType;
/*  40 */     File file = new File(IPPath);
/*  41 */     FileInputStream fis = null;
/*  42 */     BufferedInputStream bis = null;
/*  43 */     DataInputStream dis = null;
/*     */     
/*  45 */     fis = new FileInputStream(file);
/*     */     
/*  47 */     bis = new BufferedInputStream(fis);
/*  48 */     dis = new DataInputStream(bis);
/*  49 */     String[] IPPortComp = (String[])null;
/*  50 */     String Line = new String();
/*     */     
/*  52 */     while ((Line = dis.readLine()) != null) {
/*     */       
/*  54 */       if (!Line.equals("")) {
/*     */         
/*  56 */         IPPortComp = Line.split(" ");
/*  57 */         Map_Comp_IP.put(IPPortComp[2], IPPortComp[0]);
/*  58 */         Map_IP_Comp.put(IPPortComp[0].concat("_").concat(IPPortComp[1]), IPPortComp[2]);
/*  59 */         Map_Comp_Port.put(IPPortComp[2], Integer.decode(IPPortComp[1]));
/*  60 */         id = Integer.valueOf(id.intValue() + 1); Map_Comp_ID.put(IPPortComp[2], id);
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     for (Object o : compoundType.getSubcomponent()) {
/*     */       
/*  66 */       Component comp = (Component)o;
/*  67 */       LComponent.add(comp.getName());
/*  68 */       List<String> Request = new LinkedList<String>();
/*  69 */       List<String> Listen = new LinkedList<String>();
/*  70 */       CompRequest.add(Request);
/*  71 */       CompListen.add(Listen);
/*     */     } 
/*     */ 
/*     */     
/*  75 */     fis.close();
/*  76 */     bis.close();
/*  77 */     dis.close();
/*  78 */     Initialize_Request_Listen();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void Initialize_Request_Listen() {
/*  83 */     for (Object o : compoundType.getConnector()) {
/*     */       
/*  85 */       Connector conn = (Connector)o;
/*  86 */       InnerPortReference IPRSend = (InnerPortReference)conn.getActualPort().get(0);
/*  87 */       InnerPortReference IPRRec = (InnerPortReference)conn.getActualPort().get(1);
/*     */       
/*  89 */       String compName1 = IPRSend.getTargetInstance().getTargetPart().getName();
/*  90 */       String compName2 = IPRRec.getTargetInstance().getTargetPart().getName();
/*     */       
/*  92 */       int indexcompName1 = LComponent.indexOf(compName1);
/*  93 */       int indexcompName2 = LComponent.indexOf(compName2);
/*     */       
/*  95 */       Integer ID1 = getID(compName1);
/*  96 */       Integer ID2 = getID(compName2);
/*     */       
/*  98 */       if (ID1.intValue() < ID2.intValue()) {
/*     */         
/* 100 */         if (!((List)CompRequest.get(indexcompName1)).contains(compName2))
/* 101 */           ((List<String>)CompRequest.get(indexcompName1)).add(compName2); 
/* 102 */         if (!((List)CompListen.get(indexcompName2)).contains(compName1)) {
/* 103 */           ((List<String>)CompListen.get(indexcompName2)).add(compName1);
/*     */         }
/*     */         continue;
/*     */       } 
/* 107 */       if (!((List)CompListen.get(indexcompName1)).contains(compName2))
/* 108 */         ((List<String>)CompListen.get(indexcompName1)).add(compName2); 
/* 109 */       if (!((List)CompRequest.get(indexcompName2)).contains(compName1)) {
/* 110 */         ((List<String>)CompRequest.get(indexcompName2)).add(compName1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getListen(String CompName) {
/* 117 */     return CompListen.get(LComponent.indexOf(CompName));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getRequest(String CompName) {
/* 122 */     return CompRequest.get(LComponent.indexOf(CompName));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIP(String CompName) {
/* 128 */     return Map_Comp_IP.get(CompName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getPort(String CompName) {
/* 133 */     return Map_Comp_Port.get(CompName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Integer getID(String CompName) {
/* 138 */     return Map_Comp_ID.get(CompName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getComponentFromIPPort(String IP) {
/* 143 */     return Map_IP_Comp.get(IP);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Configuration1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */