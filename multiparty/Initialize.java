/*     */ package multiparty;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import trans.CmdLineError;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.parser.ErrorMessage;
/*     */ import ujf.verimag.bip.parser.actions.Parser;
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
/*     */ public class Initialize
/*     */ {
/*     */   static PortType Sync;
/*     */   static PortType IDMess;
/*     */   static PortType BufferIDMess;
/*     */   static ConnectorType Synchro2;
/*     */   static ConnectorType SR_IDMess;
/*     */   static ConnectorType SR_BufferIDMess;
/*     */   static ConnectorType SR_IDMessage;
/*     */   static AtomType ReceiveComponentProtocol;
/*     */   static AtomType SendComponentProtocol;
/*     */   static AtomType SendDataComponentProtocol;
/*     */   static AtomType ComponentProtocol;
/*     */   static AtomType ReceiveConnectorProtocol;
/*     */   static AtomType SendConnectorProtocol;
/*     */   static AtomType ConnectorProtocol;
/*     */   static AtomType ConnectorTypeBehavior;
/*     */   static CompoundType CompType;
/*  51 */   static Map<ConnectorType, DConnectorType> Map_ConnType_DConnType = new HashMap<ConnectorType, DConnectorType>(); static Port RCompPLock; static Port RCompPStart; static Port RCompPUnlock; static Port RCompPAckref; static Port RCompRecMSG; static Port RConnPOffer; static Port RConnPParticipate; static Port RConnPRefuse; static Port RConnPOk; static Port RConnPRecMSG; static Port ConnPOffer; static Port ConnPParticipate; static Port ConnPRefuse; static Port ConnPOk; static Port ConnPSendMSG; static Port ConnPStart; static Port ConnPGuard;
/*  52 */   static Map<AtomType, DAtomType> Map_AtomType_DAtomType = new HashMap<AtomType, DAtomType>();
/*  53 */   static Map<PortType, ConnectorType> Map_PT_ConnTSR = new HashMap<PortType, ConnectorType>();
/*  54 */   static Map<PortType, ConnectorType> Map_PT_ConnT = new HashMap<PortType, ConnectorType>();
/*     */   
/*  56 */   static String Function_C = new String();
/*     */ 
/*     */   
/*     */   static EList BipType;
/*     */ 
/*     */   
/*     */   static void INIT(CompoundType comptype) throws FileNotFoundException {
/*  63 */     CompType = comptype;
/*  64 */     BipType = CompType.getModule().getBipType();
/*  65 */     InitializeAtomProtocol();
/*  66 */     setMap_ConnType_DConnType();
/*  67 */     setMap_AtomType_DAtomType();
/*  68 */     setRCompPPorts();
/*  69 */     setRConnPPorts();
/*  70 */     setConnPPorts();
/*  71 */     BipType.add(0, Sync);
/*  72 */     BipType.add(BufferIDMess);
/*  73 */     BipType.add(IDMess);
/*  74 */     BipType.add(ReceiveComponentProtocol);
/*  75 */     BipType.add(SR_IDMess);
/*  76 */     BipType.add(SR_BufferIDMess);
/*  77 */     BipType.add(SR_IDMessage);
/*  78 */     BipType.add(ReceiveConnectorProtocol);
/*  79 */     BipType.add(Synchro2);
/*  80 */     BipType.add(ConnectorProtocol);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setConnPPorts() {
/*  86 */     for (Object o : ConnectorProtocol.getPort()) {
/*     */       
/*  88 */       Port p = (Port)o;
/*  89 */       if (p.getName().equals("OFFER")) {
/*  90 */         ConnPOffer = p; continue;
/*  91 */       }  if (p.getName().equals("PARTICIPATE")) {
/*  92 */         ConnPParticipate = p; continue;
/*  93 */       }  if (p.getName().equals("REFUSE")) {
/*  94 */         ConnPRefuse = p; continue;
/*  95 */       }  if (p.getName().equals("OK")) {
/*  96 */         ConnPOk = p; continue;
/*  97 */       }  if (p.getName().equals("START")) {
/*  98 */         ConnPStart = p; continue;
/*  99 */       }  if (p.getName().equals("sendMSG")) {
/* 100 */         ConnPSendMSG = p; continue;
/* 101 */       }  if (p.getName().equals("Guard")) {
/* 102 */         ConnPGuard = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setRConnPPorts() {
/* 108 */     for (Object o : ReceiveConnectorProtocol.getPort()) {
/*     */       
/* 110 */       Port p = (Port)o;
/* 111 */       if (p.getName().equals("OFFER")) {
/* 112 */         RConnPOffer = p; continue;
/* 113 */       }  if (p.getName().equals("PARTICIPATE")) {
/* 114 */         RConnPParticipate = p; continue;
/* 115 */       }  if (p.getName().equals("REFUSE")) {
/* 116 */         RConnPRefuse = p; continue;
/* 117 */       }  if (p.getName().equals("OK")) {
/* 118 */         RConnPOk = p; continue;
/* 119 */       }  if (p.getName().equals("recMSG")) {
/* 120 */         RConnPRecMSG = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static int getID(Component comp) {
/* 127 */     return CompType.getSubcomponent().indexOf(comp) + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getID(Connector conn) {
/* 132 */     return CompType.getConnector().indexOf(conn) + 100;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void InitializeAtomProtocol() throws FileNotFoundException {
/* 137 */     CmdLineError err = new CmdLineError();
/*     */     
/* 139 */     ArrayList<String> includeDirectories = new ArrayList();
/* 140 */     Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/* 141 */     String includeDir = "/";
/* 142 */     includeDirectories.add(String.valueOf(includeDir) + "/");
/* 143 */     Module[] bipModel = Parser.parse("/Users/mohamad/Documents/workspace/Transformations/src/multiparty/Protocol/Protocol.bip", includeDirectories, libFullNames, (ErrorMessage)err);
/* 144 */     Package sys = (Package)bipModel[0];
/*     */     
/* 146 */     Sync = (PortType)sys.getBipType().get(1);
/* 147 */     IDMess = (PortType)sys.getBipType().get(2);
/* 148 */     BufferIDMess = (PortType)sys.getBipType().get(3);
/*     */ 
/*     */     
/* 151 */     Synchro2 = (ConnectorType)sys.getBipType().get(4);
/* 152 */     SR_IDMess = (ConnectorType)sys.getBipType().get(5);
/* 153 */     SR_BufferIDMess = (ConnectorType)sys.getBipType().get(6);
/* 154 */     SR_IDMessage = (ConnectorType)sys.getBipType().get(17);
/*     */     
/* 156 */     ReceiveComponentProtocol = (AtomType)sys.getBipType().get(7);
/* 157 */     SendComponentProtocol = (AtomType)sys.getBipType().get(8);
/* 158 */     SendDataComponentProtocol = (AtomType)sys.getBipType().get(9);
/* 159 */     ComponentProtocol = (AtomType)sys.getBipType().get(10);
/*     */     
/* 161 */     ReceiveConnectorProtocol = (AtomType)sys.getBipType().get(11);
/* 162 */     SendConnectorProtocol = (AtomType)sys.getBipType().get(12);
/* 163 */     ConnectorProtocol = (AtomType)sys.getBipType().get(13);
/* 164 */     ConnectorTypeBehavior = (AtomType)sys.getBipType().get(16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setRCompPPorts() {
/* 170 */     for (Object o : ReceiveComponentProtocol.getPort()) {
/*     */       
/* 172 */       Port p = (Port)o;
/* 173 */       if (p.getName().equals("LOCK")) {
/* 174 */         RCompPLock = p; continue;
/* 175 */       }  if (p.getName().equals("UNLOCK")) {
/* 176 */         RCompPUnlock = p; continue;
/* 177 */       }  if (p.getName().equals("START")) {
/* 178 */         RCompPStart = p; continue;
/* 179 */       }  if (p.getName().equals("ACKREF")) {
/* 180 */         RCompPAckref = p; continue;
/* 181 */       }  if (p.getName().equals("recMSG")) {
/* 182 */         RCompRecMSG = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setMap_ConnType_DConnType() {
/* 189 */     for (Object o : CompType.getConnector()) {
/*     */       
/* 191 */       Connector conn = (Connector)o;
/* 192 */       ConnectorType connType = conn.getType();
/* 193 */       if (!Map_ConnType_DConnType.containsKey(connType)) {
/*     */         
/* 195 */         DConnectorType DconnType = new DConnectorType(connType);
/* 196 */         Map_ConnType_DConnType.put(connType, DconnType);
/* 197 */         BipType.add(DconnType.getDConnType());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setMap_AtomType_DAtomType() {
/* 205 */     for (Object o : CompType.getSubcomponent()) {
/*     */       
/* 207 */       Component component = (Component)o;
/* 208 */       AtomType atomType = (AtomType)component.getType();
/* 209 */       if (!Map_AtomType_DAtomType.containsKey(atomType)) {
/*     */         
/* 211 */         DAtomType DatomType = new DAtomType(atomType);
/* 212 */         Map_AtomType_DAtomType.put(atomType, DatomType);
/* 213 */         BipType.add(DatomType.getDAT());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Initialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */