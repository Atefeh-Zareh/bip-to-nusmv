/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DistributedComponent
/*     */ {
/*     */   private Component component;
/*     */   private DAtomType DComponentType;
/*     */   private SendDataComponentProtocol SDataCompPType;
/*     */   private SendComponentProtocol SCompPType;
/*     */   private ComponentProtocol CompProtocolType;
/*     */   private Component DComponent;
/*     */   private Component SDataCompP;
/*     */   private Component SCompP;
/*     */   private Component RCompP;
/*     */   private Component CompProtocol;
/*     */   private CompoundType compoundType;
/*  38 */   private Map<Connector, Port> Map_Connector_SCompPPort = new HashMap<Connector, Port>();
/*  39 */   private Map<Connector, Port> Map_Connector_SDataCompPPort = new HashMap<Connector, Port>();
/*  40 */   private Map<Connector, Port> Map_Connector_DComponentPort = new HashMap<Connector, Port>();
/*  41 */   private List<Port> LDComponentPort = new LinkedList<Port>();
/*     */   
/*     */   private Port ReceivePort;
/*     */ 
/*     */   
/*     */   public DistributedComponent(Component component) {
/*  47 */     this.component = component;
/*     */     
/*  49 */     this.DComponentType = Initialize.Map_AtomType_DAtomType.get(component.getType());
/*  50 */     this.SDataCompPType = new SendDataComponentProtocol(this.DComponentType, component);
/*  51 */     this.SCompPType = new SendComponentProtocol(this.DComponentType, component, this.SDataCompPType);
/*  52 */     this.CompProtocolType = new ComponentProtocol(this.DComponentType, component);
/*     */     
/*  54 */     setcompoundType();
/*     */     
/*  56 */     EList BipType = component.getType().getModule().getBipType();
/*     */     
/*  58 */     BipType.add(this.SDataCompPType.getSDCPType());
/*  59 */     BipType.add(this.SCompPType.getSCPType());
/*  60 */     BipType.add(this.CompProtocolType.getCPType());
/*  61 */     BipType.add(this.compoundType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setcompoundType() {
/*  67 */     this.compoundType = InteractionsFactory.eINSTANCE.createCompoundType();
/*  68 */     this.compoundType.setName("Distriubted_" + this.component.getName() + "_Type");
/*  69 */     InitializeComponent();
/*  70 */     InitializeConnector();
/*  71 */     InitializePortMap();
/*     */   }
/*     */   
/*     */   private void InitializeComponent() {
/*  75 */     this.DComponent = TransformationFunction.CreateComponent("Distributed_Behavior", (ComponentType)this.DComponentType.getDAT(), this.compoundType, (List)this.component.getActualData());
/*  76 */     this.SDataCompP = TransformationFunction.CreateComponent("SendData", (ComponentType)this.SDataCompPType.getSDCPType(), this.compoundType, null);
/*  77 */     this.SCompP = TransformationFunction.CreateComponent("SendControl", (ComponentType)this.SCompPType.getSCPType(), this.compoundType, null);
/*  78 */     this.RCompP = TransformationFunction.CreateComponent("ReceiveControl", (ComponentType)Initialize.ReceiveComponentProtocol, this.compoundType, null);
/*  79 */     this.CompProtocol = TransformationFunction.CreateComponent("ComponentProtocol", (ComponentType)this.CompProtocolType.getCPType(), this.compoundType, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void InitializeConnector() {
/*  89 */     List<Component> LComponent = new LinkedList<Component>();
/*  90 */     List<Port> LPort = new LinkedList<Port>();
/*  91 */     LComponent.add(this.RCompP);
/*  92 */     LComponent.add(this.CompProtocol);
/*  93 */     LPort.add(Initialize.RCompPLock);
/*  94 */     LPort.add(this.CompProtocolType.getLock());
/*     */     
/*  96 */     TransformationFunction.CreateConnector("conn_lock", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/*  98 */     LComponent.clear();
/*  99 */     LPort.clear();
/* 100 */     LComponent.add(this.RCompP);
/* 101 */     LComponent.add(this.CompProtocol);
/* 102 */     LPort.add(Initialize.RCompPUnlock);
/* 103 */     LPort.add(this.CompProtocolType.getUnlock());
/*     */     
/* 105 */     TransformationFunction.CreateConnector("conn_unlock", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/* 107 */     LComponent.clear();
/* 108 */     LPort.clear();
/* 109 */     LComponent.add(this.RCompP);
/* 110 */     LComponent.add(this.CompProtocol);
/* 111 */     LPort.add(Initialize.RCompPStart);
/* 112 */     LPort.add(this.CompProtocolType.getStart());
/*     */     
/* 114 */     TransformationFunction.CreateConnector("conn_start", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/* 116 */     LComponent.clear();
/* 117 */     LPort.clear();
/* 118 */     LComponent.add(this.RCompP);
/* 119 */     LComponent.add(this.CompProtocol);
/* 120 */     LPort.add(Initialize.RCompPAckref);
/* 121 */     LPort.add(this.CompProtocolType.getAckref());
/*     */     
/* 123 */     TransformationFunction.CreateConnector("conn_ackref", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     LComponent.clear();
/* 129 */     LPort.clear();
/* 130 */     LComponent.add(this.CompProtocol);
/* 131 */     LComponent.add(this.SCompP);
/* 132 */     LPort.add(this.CompProtocolType.getSendMSG());
/* 133 */     LPort.add(this.SCompPType.getRecMSG());
/* 134 */     TransformationFunction.CreateConnector("conn_control_mess", Initialize.SR_BufferIDMess, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     Port Dcomp_SendPort = this.DComponentType.getSendPortGuard();
/* 142 */     Port compP_RecPort = this.CompProtocolType.getRecPortGuard();
/*     */ 
/*     */ 
/*     */     
/* 146 */     ConnectorType connTypebool = null;
/* 147 */     if (Initialize.Map_PT_ConnT.containsKey(Dcomp_SendPort.getType())) {
/*     */       
/* 149 */       connTypebool = Initialize.Map_PT_ConnT.get(Dcomp_SendPort.getType());
/*     */     }
/*     */     else {
/*     */       
/* 153 */       connTypebool = TransformationFunction.CreateConnectorTypeSendReceive(Dcomp_SendPort.getType());
/* 154 */       Initialize.Map_PT_ConnT.put(Dcomp_SendPort.getType(), connTypebool);
/*     */     } 
/*     */     
/* 157 */     LComponent.clear();
/* 158 */     LPort.clear();
/* 159 */     LPort.add(Dcomp_SendPort);
/* 160 */     LPort.add(compP_RecPort);
/* 161 */     LComponent.add(this.DComponent);
/* 162 */     LComponent.add(this.CompProtocol);
/* 163 */     TransformationFunction.CreateConnector("send_guards", connTypebool, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     Port Dcomp_SendDataPort = this.DComponentType.getSendPortGuardVar();
/* 172 */     Port SDataComp_RecDataPort = this.SDataCompPType.getReceiveGuardVar();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     ConnectorType connTypedata = null;
/* 178 */     if (Initialize.Map_PT_ConnT.containsKey(Dcomp_SendDataPort.getType())) {
/*     */       
/* 180 */       connTypedata = Initialize.Map_PT_ConnT.get(Dcomp_SendDataPort.getType());
/*     */     }
/*     */     else {
/*     */       
/* 184 */       connTypedata = TransformationFunction.CreateConnectorTypeSendReceive(Dcomp_SendDataPort.getType());
/* 185 */       Initialize.Map_PT_ConnT.put(Dcomp_SendDataPort.getType(), connTypedata);
/*     */     } 
/*     */ 
/*     */     
/* 189 */     LComponent.clear();
/* 190 */     LPort.clear();
/* 191 */     LPort.add(Dcomp_SendDataPort);
/* 192 */     LPort.add(SDataComp_RecDataPort);
/* 193 */     LComponent.add(this.DComponent);
/* 194 */     LComponent.add(this.SDataCompP);
/* 195 */     TransformationFunction.CreateConnector("send_guardsVariables", connTypedata, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     LComponent.clear();
/* 203 */     LPort.clear();
/* 204 */     LPort.add(this.DComponentType.getFinishSendData());
/* 205 */     LPort.add(this.SDataCompPType.getFinishSendData());
/* 206 */     LComponent.add(this.DComponent);
/* 207 */     LComponent.add(this.SDataCompP);
/* 208 */     TransformationFunction.CreateConnector("Finish_send_data", Initialize.Synchro2, this.compoundType, LComponent, LPort);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void InitializePortMap() {
/* 214 */     setMap_Connector_SCompPPort();
/* 215 */     setMap_Connector_SDataCompPPort();
/* 216 */     setMap_Connector_DComponentPort();
/* 217 */     setReceivePort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Connector_SCompPPort() {
/* 223 */     for (Connector o : this.SCompPType.getLConnector()) {
/*     */       
/* 225 */       Connector c = o;
/* 226 */       Port p1 = this.SCompPType.getLPort().get(this.SCompPType.getLConnector().indexOf(o));
/* 227 */       Port p = TransformationFunction.CreatePort("Mess_" + p1.getName(), this.SCompP, p1, this.compoundType);
/* 228 */       this.Map_Connector_SCompPPort.put(c, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Connector_SDataCompPPort() {
/* 234 */     for (Connector o : this.SDataCompPType.getLConnector()) {
/*     */       
/* 236 */       Connector c = o;
/* 237 */       Port p1 = this.SDataCompPType.getLPort().get(this.SDataCompPType.getLConnector().indexOf(o));
/* 238 */       Port p = TransformationFunction.CreatePort("Data_" + p1.getName(), this.SDataCompP, p1, this.compoundType);
/* 239 */       this.Map_Connector_SDataCompPPort.put(c, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Connector_DComponentPort() {
/* 245 */     for (Object o : this.DComponentType.getCAT().getPort()) {
/*     */       
/* 247 */       Port ptmp = (Port)o;
/* 248 */       Port p1 = this.DComponentType.getDPort(ptmp.getName());
/* 249 */       List<Connector> LConnector = this.SDataCompPType.getLConnectorfromPort(ptmp);
/* 250 */       Port p = TransformationFunction.CreatePort(p1.getName(), this.DComponent, p1, this.compoundType);
/* 251 */       for (Connector o1 : LConnector) {
/*     */         
/* 253 */         Connector c = o1;
/* 254 */         this.Map_Connector_DComponentPort.put(c, p);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setReceivePort() {
/* 261 */     this.ReceivePort = TransformationFunction.CreatePort("recMSG", this.RCompP, Initialize.RCompRecMSG, this.compoundType);
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 266 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public DAtomType getDComponentType() {
/* 271 */     return this.DComponentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public SendComponentProtocol getSCompPType() {
/* 276 */     return this.SCompPType;
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentProtocol getCompProtocolType() {
/* 281 */     return this.CompProtocolType;
/*     */   }
/*     */ 
/*     */   
/*     */   public SendDataComponentProtocol getSDataCompPType() {
/* 286 */     return this.SDataCompPType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getDComponent() {
/* 291 */     return this.DComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getSCompP() {
/* 296 */     return this.SCompP;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getRCompP() {
/* 301 */     return this.RCompP;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getCompProtocol() {
/* 306 */     return this.CompProtocol;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getSDataCompP() {
/* 311 */     return this.SDataCompP;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getcompoundType() {
/* 316 */     return this.compoundType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Connector, Port> getMap_Connector_SCompPPort() {
/* 321 */     return this.Map_Connector_SCompPPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Connector, Port> getMap_Connector_SDataCompPPort() {
/* 326 */     return this.Map_Connector_SDataCompPPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Connector, Port> getMap_Connector_DComponentPort() {
/* 331 */     return this.Map_Connector_DComponentPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLDComponentPort() {
/* 336 */     return this.LDComponentPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getReceivePort() {
/* 341 */     return this.ReceivePort;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\DistributedComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */