/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
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
/*     */ public class DistributedConnector
/*     */ {
/*     */   private Connector connector;
/*     */   private DConnectorType DBehaviorConnectorType;
/*     */   private SendConnectorProtocol SConnPType;
/*     */   private Component DBehaviorConnector;
/*     */   private Component SConnP;
/*     */   private Component RConnP;
/*     */   private Component ConnProtocol;
/*     */   private CompoundType compoundType;
/*  36 */   private Map<Component, Port> Map_Component_SConnPPort = new HashMap<Component, Port>();
/*  37 */   private Map<Component, Port> Map_Component_DBehaviorPort = new HashMap<Component, Port>();
/*  38 */   private Map<Component, Port> Map_Component_DBehaviorDataPort = new HashMap<Component, Port>();
/*     */   
/*     */   private Port ReceivePort;
/*     */ 
/*     */   
/*     */   public DistributedConnector(Connector connector) {
/*  44 */     this.connector = connector;
/*  45 */     this.DBehaviorConnectorType = Initialize.Map_ConnType_DConnType.get(connector.getType());
/*  46 */     this.SConnPType = new SendConnectorProtocol(connector);
/*     */     
/*  48 */     setCompoundType();
/*  49 */     EList BipType = connector.getType().getModule().getBipType();
/*     */     
/*  51 */     BipType.add(this.SConnPType.getSConnP());
/*  52 */     BipType.add(this.compoundType);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setCompoundType() {
/*  57 */     this.compoundType = InteractionsFactory.eINSTANCE.createCompoundType();
/*  58 */     this.compoundType.setName("Distriubted_" + this.connector.getName() + "_Type");
/*  59 */     InitializeComponent();
/*  60 */     InitializeConnector();
/*  61 */     InitializePortMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void InitializeComponent() {
/*  69 */     IntegerLiteral IL = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/*  70 */     IL.setIValue(this.connector.getActualPort().size());
/*  71 */     IntegerLiteral IDConn = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/*  72 */     IDConn.setIValue(Initialize.getID(this.connector));
/*  73 */     List<IntegerLiteral> LParameter = new LinkedList();
/*  74 */     List<IntegerLiteral> LParameter1 = new LinkedList();
/*  75 */     LParameter.add(IL);
/*  76 */     LParameter1.add(IL);
/*  77 */     LParameter1.add(IDConn);
/*     */     
/*  79 */     this.DBehaviorConnector = TransformationFunction.CreateComponent("Distributed_Behavior", (ComponentType)this.DBehaviorConnectorType.getDConnType(), this.compoundType, LParameter);
/*  80 */     this.SConnP = TransformationFunction.CreateComponent("Send_Connector_Protocol", (ComponentType)this.SConnPType.getSConnP(), this.compoundType, null);
/*  81 */     this.RConnP = TransformationFunction.CreateComponent("Receive_Connector_Protocol", (ComponentType)Initialize.ReceiveConnectorProtocol, this.compoundType, null);
/*  82 */     this.ConnProtocol = TransformationFunction.CreateComponent("Connector_Protocol", (ComponentType)Initialize.ConnectorProtocol, this.compoundType, LParameter1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void InitializeConnector() {
/*  92 */     List<Component> LComponent = new LinkedList<Component>();
/*  93 */     List<Port> LPort = new LinkedList<Port>();
/*  94 */     LComponent.add(this.RConnP);
/*  95 */     LComponent.add(this.ConnProtocol);
/*  96 */     LPort.add(Initialize.RConnPOffer);
/*  97 */     LPort.add(Initialize.ConnPOffer);
/*     */     
/*  99 */     TransformationFunction.CreateConnector("conn_offer", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/* 101 */     LComponent.clear();
/* 102 */     LPort.clear();
/* 103 */     LComponent.add(this.RConnP);
/* 104 */     LComponent.add(this.ConnProtocol);
/* 105 */     LPort.add(Initialize.RConnPParticipate);
/* 106 */     LPort.add(Initialize.ConnPParticipate);
/*     */     
/* 108 */     TransformationFunction.CreateConnector("conn_participate", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/* 110 */     LComponent.clear();
/* 111 */     LPort.clear();
/* 112 */     LComponent.add(this.RConnP);
/* 113 */     LComponent.add(this.ConnProtocol);
/* 114 */     LPort.add(Initialize.RConnPRefuse);
/* 115 */     LPort.add(Initialize.ConnPRefuse);
/*     */     
/* 117 */     TransformationFunction.CreateConnector("conn_refuse", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */     
/* 119 */     LComponent.clear();
/* 120 */     LPort.clear();
/* 121 */     LComponent.add(this.RConnP);
/* 122 */     LComponent.add(this.ConnProtocol);
/* 123 */     LPort.add(Initialize.RConnPOk);
/* 124 */     LPort.add(Initialize.ConnPOk);
/*     */     
/* 126 */     TransformationFunction.CreateConnector("conn_ok", Initialize.SR_IDMess, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     LComponent.clear();
/* 132 */     LPort.clear();
/* 133 */     LComponent.add(this.ConnProtocol);
/* 134 */     LComponent.add(this.SConnP);
/* 135 */     LPort.add(Initialize.ConnPSendMSG);
/* 136 */     LPort.add(this.SConnPType.getRecMSG());
/* 137 */     TransformationFunction.CreateConnector("conn_control_mess", Initialize.SR_BufferIDMess, this.compoundType, LComponent, LPort);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     LComponent.clear();
/* 146 */     LPort.clear();
/* 147 */     LComponent.add(this.ConnProtocol);
/* 148 */     LComponent.add(this.DBehaviorConnector);
/* 149 */     LPort.add(Initialize.ConnPGuard);
/* 150 */     LPort.add(this.DBehaviorConnectorType.getGuard());
/* 151 */     TransformationFunction.CreateConnector("conn_guard_guard", Initialize.Synchro2, this.compoundType, LComponent, LPort);
/*     */     
/* 153 */     LComponent.clear();
/* 154 */     LPort.clear();
/* 155 */     LComponent.add(this.ConnProtocol);
/* 156 */     LComponent.add(this.DBehaviorConnector);
/* 157 */     LPort.add(Initialize.ConnPStart);
/* 158 */     LPort.add(this.DBehaviorConnectorType.getStart());
/* 159 */     TransformationFunction.CreateConnector("conn_start_start", Initialize.Synchro2, this.compoundType, LComponent, LPort);
/*     */   }
/*     */ 
/*     */   
/*     */   private void InitializePortMap() {
/* 164 */     setMap_Component_SConnPPort();
/* 165 */     setMap_Component_DBehaviorPort();
/* 166 */     setMap_Component_DBehaviorDataPort();
/* 167 */     setReceivePort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Component_SConnPPort() {
/* 173 */     for (Component o : this.SConnPType.getLComponent()) {
/*     */       
/* 175 */       Component component = o;
/* 176 */       Port p1 = this.SConnPType.getLPort().get(this.SConnPType.getLComponent().indexOf(o));
/* 177 */       Port p = TransformationFunction.CreatePort("Mess_" + p1.getName(), this.SConnP, p1, this.compoundType);
/* 178 */       this.Map_Component_SConnPPort.put(component, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Component_DBehaviorPort() {
/* 184 */     for (Component o : this.SConnPType.getLComponent()) {
/*     */       
/* 186 */       Component component = o;
/* 187 */       Port p1 = this.DBehaviorConnectorType.getLPort().get(this.SConnPType.getLComponent().indexOf(o));
/* 188 */       Port p = TransformationFunction.CreatePort("Control_" + p1.getName(), this.DBehaviorConnector, p1, this.compoundType);
/* 189 */       this.Map_Component_DBehaviorPort.put(component, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Component_DBehaviorDataPort() {
/* 195 */     for (Component o : this.SConnPType.getLComponent()) {
/*     */       
/* 197 */       Component component = o;
/* 198 */       Port p1 = this.DBehaviorConnectorType.getLPortData().get(this.SConnPType.getLComponent().indexOf(o));
/* 199 */       Port p = TransformationFunction.CreatePort("Data_" + p1.getName(), this.DBehaviorConnector, p1, this.compoundType);
/* 200 */       this.Map_Component_DBehaviorDataPort.put(component, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setReceivePort() {
/* 206 */     this.ReceivePort = TransformationFunction.CreatePort("recMSG", this.RConnP, Initialize.RConnPRecMSG, this.compoundType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getcompoundType() {
/* 212 */     return this.compoundType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Component, Port> getMap_Component_SConnPPort() {
/* 217 */     return this.Map_Component_SConnPPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Component, Port> getMap_Component_DBehaviorPort() {
/* 222 */     return this.Map_Component_DBehaviorPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Component, Port> getMap_Component_DBehaviorDataPort() {
/* 227 */     return this.Map_Component_DBehaviorDataPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getReceivePort() {
/* 233 */     return this.ReceivePort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getDBehaviorConnector() {
/* 238 */     return this.DBehaviorConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getSConnP() {
/* 243 */     return this.SConnP;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getRConnP() {
/* 248 */     return this.RConnP;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getConnProtocol() {
/* 253 */     return this.ConnProtocol;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connector getConnector() {
/* 258 */     return this.connector;
/*     */   }
/*     */ 
/*     */   
/*     */   public DConnectorType getDBehaviorConnectorType() {
/* 263 */     return this.DBehaviorConnectorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public SendConnectorProtocol SConnPType() {
/* 268 */     return this.SConnPType;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\DistributedConnector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */