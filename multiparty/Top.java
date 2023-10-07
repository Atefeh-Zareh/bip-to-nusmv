/*     */ package multiparty;
/*     */ 
/*     */ import BIP2BIP.FlatBip2;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
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
/*     */ public class Top
/*     */ {
/*     */   private CompoundType Top;
/*     */   private CompoundType TopSR;
/*  28 */   private Map<Component, DistributedComponent> Map_Comp_DCompType = new HashMap<Component, DistributedComponent>();
/*  29 */   private Map<Connector, DistributedConnector> Map_Conn_DConnType = new HashMap<Connector, DistributedConnector>();
/*     */   
/*  31 */   private Map<Component, Component> Map_Comp_DComp = new HashMap<Component, Component>();
/*  32 */   private Map<Connector, Component> Map_Conn_DConn = new HashMap<Connector, Component>();
/*     */ 
/*     */ 
/*     */   
/*  36 */   static int itt = 0;
/*     */ 
/*     */   
/*     */   public Top(CompoundType compoundType) {
/*  40 */     this.Top = compoundType;
/*  41 */     setTopSR();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTopSR() {
/*  46 */     this.TopSR = InteractionsFactory.eINSTANCE.createCompoundType();
/*  47 */     this.TopSR.setName("Top_Send_Receive");
/*  48 */     setMap_Comp_DComp();
/*  49 */     setMap_Conn_DConn();
/*  50 */     setSR_Comp_Conn();
/*  51 */     setSR_Conn_Comp();
/*     */     
/*  53 */     this.Top.getModule().getBipType().add(this.TopSR);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSR_Comp_Conn() {
/*  58 */     List<Component> LComponent = new LinkedList<Component>();
/*  59 */     List<Port> LPort = new LinkedList<Port>();
/*  60 */     for (Component o : this.Map_Comp_DComp.keySet()) {
/*     */       
/*  62 */       Component component = o;
/*  63 */       Component Dcomponent = this.Map_Comp_DComp.get(component);
/*  64 */       DistributedComponent DistComponent = this.Map_Comp_DCompType.get(component);
/*     */ 
/*     */       
/*  67 */       for (Connector o1 : DistComponent.getMap_Connector_SCompPPort().keySet()) {
/*     */         
/*  69 */         Connector connector = o1;
/*  70 */         Component Dconnector = this.Map_Conn_DConn.get(connector);
/*  71 */         DistributedConnector DistConnector = this.Map_Conn_DConnType.get(connector);
/*     */         
/*  73 */         Port port_component = DistComponent.getMap_Connector_SCompPPort().get(connector);
/*  74 */         Port port_connector = DistConnector.getReceivePort();
/*     */         
/*  76 */         LComponent.clear();
/*  77 */         LPort.clear();
/*  78 */         LComponent.add(Dcomponent);
/*  79 */         LComponent.add(Dconnector);
/*  80 */         LPort.add(port_component);
/*  81 */         LPort.add(port_connector);
/*  82 */         TransformationFunction.CreateConnector("SR" + itt++, Initialize.SR_IDMessage, this.TopSR, LComponent, LPort);
/*     */       } 
/*     */ 
/*     */       
/*  86 */       for (Connector o1 : DistComponent.getMap_Connector_SDataCompPPort().keySet()) {
/*     */         
/*  88 */         Connector connector = o1;
/*  89 */         Component Dconnector = this.Map_Conn_DConn.get(connector);
/*  90 */         DistributedConnector DistConnector = this.Map_Conn_DConnType.get(connector);
/*     */         
/*  92 */         Port port_component = DistComponent.getMap_Connector_SDataCompPPort().get(connector);
/*  93 */         Port port_connector = DistConnector.getMap_Component_DBehaviorDataPort().get(component);
/*  94 */         PortType pt = port_component.getType();
/*  95 */         if (Initialize.Map_PT_ConnTSR.containsKey(pt)) {
/*     */           
/*  97 */           ConnectorType connectorType = Initialize.Map_PT_ConnTSR.get(pt);
/*  98 */           LComponent.clear();
/*  99 */           LPort.clear();
/* 100 */           LComponent.add(Dcomponent);
/* 101 */           LComponent.add(Dconnector);
/* 102 */           LPort.add(port_component);
/* 103 */           LPort.add(port_connector);
/* 104 */           TransformationFunction.CreateConnector("SR" + itt++, connectorType, this.TopSR, LComponent, LPort);
/*     */           
/*     */           continue;
/*     */         } 
/* 108 */         ConnectorType connType = TransformationFunction.CreateConnectorTypeSend(pt);
/* 109 */         Initialize.Map_PT_ConnTSR.put(pt, connType);
/* 110 */         LComponent.clear();
/* 111 */         LPort.clear();
/* 112 */         LComponent.add(Dcomponent);
/* 113 */         LComponent.add(Dconnector);
/* 114 */         LPort.add(port_component);
/* 115 */         LPort.add(port_connector);
/* 116 */         TransformationFunction.CreateConnector("SR" + itt++, connType, this.TopSR, LComponent, LPort);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSR_Conn_Comp() {
/* 125 */     List<Component> LComponent = new LinkedList<Component>();
/* 126 */     List<Port> LPort = new LinkedList<Port>();
/* 127 */     for (Connector o : this.Map_Conn_DConn.keySet()) {
/*     */       
/* 129 */       Connector connector = o;
/* 130 */       Component Dconnector = this.Map_Conn_DConn.get(connector);
/* 131 */       DistributedConnector DistConnector = this.Map_Conn_DConnType.get(connector);
/*     */ 
/*     */       
/* 134 */       for (Component o1 : DistConnector.getMap_Component_SConnPPort().keySet()) {
/*     */         
/* 136 */         Component component = o1;
/* 137 */         Component Dcomponent = this.Map_Comp_DComp.get(component);
/* 138 */         DistributedComponent DistComponent = this.Map_Comp_DCompType.get(component);
/*     */         
/* 140 */         Port port_component = DistComponent.getReceivePort();
/* 141 */         Port port_connector = DistConnector.getMap_Component_SConnPPort().get(component);
/*     */         
/* 143 */         LComponent.clear();
/* 144 */         LPort.clear();
/* 145 */         LComponent.add(Dconnector);
/* 146 */         LComponent.add(Dcomponent);
/* 147 */         LPort.add(port_connector);
/* 148 */         LPort.add(port_component);
/*     */         
/* 150 */         TransformationFunction.CreateConnector("SR" + itt++, Initialize.SR_IDMessage, this.TopSR, LComponent, LPort);
/*     */       } 
/*     */ 
/*     */       
/* 154 */       for (Component o1 : DistConnector.getMap_Component_DBehaviorPort().keySet()) {
/*     */         
/* 156 */         Component component = o1;
/* 157 */         Component Dcomponent = this.Map_Comp_DComp.get(component);
/* 158 */         DistributedComponent DistComponent = this.Map_Comp_DCompType.get(component);
/*     */         
/* 160 */         Port port_connector = DistConnector.getMap_Component_DBehaviorPort().get(component);
/* 161 */         Port port_component = DistComponent.getMap_Connector_DComponentPort().get(connector);
/*     */         
/* 163 */         PortType pt = port_component.getType();
/*     */         
/* 165 */         if (Initialize.Map_PT_ConnTSR.containsKey(pt)) {
/*     */           
/* 167 */           ConnectorType connectorType = Initialize.Map_PT_ConnTSR.get(pt);
/* 168 */           LComponent.clear();
/* 169 */           LPort.clear();
/*     */           
/* 171 */           LComponent.add(Dconnector);
/* 172 */           LComponent.add(Dcomponent);
/*     */           
/* 174 */           LPort.add(port_connector);
/* 175 */           LPort.add(port_component);
/*     */           
/* 177 */           TransformationFunction.CreateConnector("SR" + itt++, connectorType, this.TopSR, LComponent, LPort);
/*     */           
/*     */           continue;
/*     */         } 
/* 181 */         ConnectorType connType = TransformationFunction.CreateConnectorTypeSend(pt);
/* 182 */         Initialize.Map_PT_ConnTSR.put(pt, connType);
/* 183 */         LComponent.clear();
/* 184 */         LPort.clear();
/*     */         
/* 186 */         LComponent.add(Dconnector);
/* 187 */         LComponent.add(Dcomponent);
/*     */         
/* 189 */         LPort.add(port_connector);
/* 190 */         LPort.add(port_component);
/*     */         
/* 192 */         TransformationFunction.CreateConnector("SR" + itt++, connType, this.TopSR, LComponent, LPort);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void FlatTop() {
/* 200 */     for (Object o : this.TopSR.getSubcomponent()) {
/*     */       
/* 202 */       Component component = (Component)o;
/* 203 */       FlatBip2.Flat(component.getName(), this.TopSR);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Comp_DComp() {
/* 209 */     for (Object o : this.Top.getSubcomponent()) {
/*     */       
/* 211 */       Component component = (Component)o;
/* 212 */       DistributedComponent distComp = new DistributedComponent(component);
/* 213 */       Component Dcomponent = TransformationFunction.CreateComponent(String.valueOf(component.getName()) + "_SR", (ComponentType)distComp.getcompoundType(), this.TopSR, null);
/* 214 */       this.Map_Comp_DCompType.put(component, distComp);
/* 215 */       this.Map_Comp_DComp.put(component, Dcomponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Conn_DConn() {
/* 221 */     for (Object o : this.Top.getConnector()) {
/*     */       
/* 223 */       Connector connector = (Connector)o;
/* 224 */       DistributedConnector distConn = new DistributedConnector(connector);
/* 225 */       Component Dcomponent = TransformationFunction.CreateComponent(String.valueOf(connector.getName()) + "_SR", (ComponentType)distConn.getcompoundType(), this.TopSR, null);
/* 226 */       this.Map_Conn_DConnType.put(connector, distConn);
/* 227 */       this.Map_Conn_DConn.put(connector, Dcomponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getTop() {
/* 233 */     return this.Top;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getTopSR() {
/* 238 */     return this.TopSR;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Component, DistributedComponent> getMap_Comp_DCompType() {
/* 243 */     return this.Map_Comp_DCompType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Connector, DistributedConnector> getMap_Conn_DConnType() {
/* 248 */     return this.Map_Conn_DConnType;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Top.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */