/*     */ package unifiedDistributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import BipInfo.CompoundTypeInfo;
/*     */ import distributed.DList;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TOP
/*     */ {
/*  31 */   protected List<List<String>> LLInteractionName = new LinkedList<List<String>>();
/*  32 */   private static List<List<Connector>> LLInteraction = new LinkedList<List<Connector>>();
/*  33 */   private static List<String> LInteractionProtocolName = new LinkedList<String>();
/*  34 */   private Map<String, InteractionProtocol> Map_IPName_IP = new HashMap<String, InteractionProtocol>();
/*  35 */   private Map<String, Connector> Map_Name_Interaction = new HashMap<String, Connector>();
/*     */   private static PortType InternPortType;
/*  37 */   private static Map<Integer, PortType> Map_n_OfferPortType = new HashMap<Integer, PortType>();
/*  38 */   private static Map<Integer, PortType> Map_n_ReservePortType = new HashMap<Integer, PortType>();
/*     */   private CompoundType compoundType;
/*  40 */   public static Map<Component, DAtomType> Map_Component_DAtomType = new HashMap<Component, DAtomType>();
/*     */   
/*  42 */   private Map<Connector, DiningPhiloProtocol> Map_Conn_DPComponent = new HashMap<Connector, DiningPhiloProtocol>();
/*  43 */   private Map<Connector, TokenRingProtocol> Map_Conn_TRComponent = new HashMap<Connector, TokenRingProtocol>();
/*  44 */   List<Connector> LConnectorCentrilizedProtocol = new LinkedList<Connector>();
/*     */   CentralizedProtocol CentrilizedProtocolComp;
/*  46 */   private List<Connector> LInteractionToReserve = new LinkedList<Connector>();
/*     */   
/*     */   public static CompoundTypeInfo compoundTypeInfo;
/*     */   public static Module module;
/*  50 */   private int RPType = 0;
/*     */   
/*  52 */   public static List<Component> LComponentReserve = new LinkedList<Component>();
/*     */   private CompoundType DCompoundType;
/*  54 */   public static final String FunctionGetCompFail_res = new String("getCompFail_res");
/*  55 */   public static final String FunctionGetUpdateForkReceive = new String("UpdateForkReceive");
/*  56 */   public static final String FunctionGetUpdateForkSend = new String("UpdateForkSend");
/*  57 */   public static final String FunctionGetWhichFail = new String("GetWhichFail");
/*     */   
/*  59 */   private Map<PortType, ConnectorType> Map_PortType_ConnTypeSR = new HashMap<PortType, ConnectorType>();
/*     */   
/*  61 */   private static int IndexDataPortConn = 0;
/*  62 */   private static int IndexPortConn = 0;
/*  63 */   private static int IndexOfferConn = 0;
/*  64 */   private static int IndexReserveConn = 0;
/*  65 */   private static int IndexOKConn = 0;
/*  66 */   private static int IndexFailConn = 0;
/*  67 */   private static int IndexSRTokenConn = 0;
/*  68 */   private static int IndexSRForkConn = 0;
/*  69 */   private static int IndexSRReqConn = 0;
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
/*     */   public TOP(CompoundType compoundtype, List<List<String>> LLInteractionName, int RPType) throws IOException {
/*  81 */     this.compoundType = compoundtype;
/*  82 */     compoundTypeInfo = new CompoundTypeInfo(this.compoundType);
/*  83 */     module = this.compoundType.getModule();
/*  84 */     this.DCompoundType = TransformationFunction.CreateCompoundType("TOP");
/*  85 */     this.DCompoundType.getDataParameter().addAll(TransformationFunction.getCopyDataParameter((List)this.compoundType.getDataParameter()));
/*  86 */     this.RPType = RPType;
/*  87 */     this.LLInteractionName = LLInteractionName;
/*  88 */     setInternPortType();
/*  89 */     setMap_Name_Interaction();
/*  90 */     setIPRepartition();
/*  91 */     setDCompoundType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDCompoundType() {
/*  98 */     setMap_Component_AtomType();
/*  99 */     setLComponentReserve();
/* 100 */     setMap_IPName_IP();
/* 101 */     setConnectionInterfaceComponentIP();
/* 102 */     setLInteractionToReserve();
/* 103 */     setReservationProtocol();
/* 104 */     setCompoundTOP();
/*     */   }
/*     */   
/*     */   private void setCompoundTOP() {
/* 108 */     Root top = null;
/* 109 */     module.getBipType().add(this.DCompoundType);
/* 110 */     System sys = (System)module;
/* 111 */     top = sys.getRoot();
/* 112 */     top.setType((ComponentType)this.DCompoundType);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setReservationProtocol() {
/* 117 */     switch (this.RPType) { case 0:
/* 118 */         setCentrilizedProtocol(); return;
/* 119 */       case 1: setTokenRingProtocol(); return;
/* 120 */       case 2: setDiningPhiloProtocol(); return; }
/* 121 */      setCentrilizedProtocol();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDiningPhiloProtocol() {
/* 126 */     if (this.LInteractionToReserve.size() != 0) {
/* 127 */       setComponentInstanceDiningProtocol();
/* 128 */       setConnectionInterface_IP_RPDiningPhilo();
/* 129 */       setConnectionDiningPhiloProtocol();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setConnectionDiningPhiloProtocol() {
/* 135 */     for (Connector o : this.Map_Conn_DPComponent.keySet()) {
/* 136 */       Connector connector = o;
/* 137 */       DiningPhiloProtocol DPP = this.Map_Conn_DPComponent.get(connector);
/* 138 */       for (Connector o1 : DPP.LConflictInteraction) {
/* 139 */         Connector connector1 = o1;
/* 140 */         if (!DPP.SetLConflictInteraction.contains(o1)) {
/* 141 */           DiningPhiloProtocol DPP1 = this.Map_Conn_DPComponent.get(connector1);
/* 142 */           CreateConnectionDiningPhilo(DPP, DPP1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateConnectionDiningPhilo(DiningPhiloProtocol dpp, DiningPhiloProtocol dpp1) {
/* 153 */     List<Port> LPort = new LinkedList<Port>();
/* 154 */     List<Component> LComponent = new LinkedList<Component>();
/*     */     
/* 156 */     Connector conn_dpp = dpp.getInteractionOfRP();
/* 157 */     Connector conn_dpp1 = dpp1.getInteractionOfRP();
/*     */ 
/*     */     
/* 160 */     Port SendForkToconn_dpp1 = dpp.Map_Interaction_PortSendFork.get(conn_dpp1);
/* 161 */     Port ReceiveForkconn_dpp = dpp1.Map_Interaction_PortRecFork.get(conn_dpp);
/*     */     
/* 163 */     LPort.add(SendForkToconn_dpp1);
/* 164 */     LPort.add(ReceiveForkconn_dpp);
/* 165 */     LComponent.add(dpp.getComponentRPInstance());
/* 166 */     LComponent.add(dpp1.getComponentRPInstance());
/* 167 */     ConnectorType connTypeSRFork = getConnectorTypeSR(SendForkToconn_dpp1.getType());
/* 168 */     TransformationFunction.CreateConnector("Conn_SRFork" + IndexSRForkConn++, connTypeSRFork, this.DCompoundType, LComponent, LPort);
/*     */ 
/*     */     
/* 171 */     LPort.clear();
/* 172 */     LComponent.clear();
/* 173 */     Port SendForkToconn_dpp = dpp1.Map_Interaction_PortSendFork.get(conn_dpp);
/* 174 */     Port ReceiveForkconn_dpp1 = dpp.Map_Interaction_PortRecFork.get(conn_dpp1);
/* 175 */     LPort.add(SendForkToconn_dpp);
/* 176 */     LPort.add(ReceiveForkconn_dpp1);
/* 177 */     LComponent.add(dpp1.getComponentRPInstance());
/* 178 */     LComponent.add(dpp.getComponentRPInstance());
/* 179 */     TransformationFunction.CreateConnector("Conn_SRFork" + IndexSRForkConn++, connTypeSRFork, this.DCompoundType, LComponent, LPort);
/*     */ 
/*     */     
/* 182 */     LPort.clear();
/* 183 */     LComponent.clear();
/* 184 */     Port SendReqToconn_dpp1 = dpp.Map_Interaction_PortSendReq.get(conn_dpp1);
/* 185 */     Port ReceiveReqconn_dpp = dpp1.Map_Interaction_PortRecReq.get(conn_dpp);
/*     */     
/* 187 */     LPort.add(SendReqToconn_dpp1);
/* 188 */     LPort.add(ReceiveReqconn_dpp);
/* 189 */     LComponent.add(dpp.getComponentRPInstance());
/* 190 */     LComponent.add(dpp1.getComponentRPInstance());
/* 191 */     ConnectorType connTypeSRRequest = getConnectorTypeSR(SendReqToconn_dpp1.getType());
/* 192 */     TransformationFunction.CreateConnector("Conn_SRRequest" + IndexSRReqConn++, connTypeSRRequest, this.DCompoundType, LComponent, LPort);
/*     */ 
/*     */     
/* 195 */     LPort.clear();
/* 196 */     LComponent.clear();
/* 197 */     Port SendReqToconn_dpp = dpp1.Map_Interaction_PortSendReq.get(conn_dpp);
/* 198 */     Port ReceiveReqconn_dpp1 = dpp.Map_Interaction_PortRecReq.get(conn_dpp1);
/* 199 */     LPort.add(SendReqToconn_dpp);
/* 200 */     LPort.add(ReceiveReqconn_dpp1);
/* 201 */     LComponent.add(dpp1.getComponentRPInstance());
/* 202 */     LComponent.add(dpp.getComponentRPInstance());
/* 203 */     TransformationFunction.CreateConnector("Conn_SRRequest" + IndexSRReqConn++, connTypeSRRequest, this.DCompoundType, LComponent, LPort);
/*     */     
/* 205 */     dpp.SetLConflictInteraction.add(conn_dpp1);
/* 206 */     dpp1.SetLConflictInteraction.add(conn_dpp);
/*     */ 
/*     */     
/* 209 */     int indexofConnDpp = this.LInteractionToReserve.indexOf(conn_dpp);
/* 210 */     int indexofConnDpp1 = this.LInteractionToReserve.indexOf(conn_dpp1);
/* 211 */     if (indexofConnDpp > indexofConnDpp1) {
/* 212 */       dpp.setInititialHasFork(conn_dpp1);
/* 213 */       dpp1.setInititialNotHasFork(conn_dpp);
/*     */     } else {
/*     */       
/* 216 */       dpp1.setInititialHasFork(conn_dpp);
/* 217 */       dpp.setInititialNotHasFork(conn_dpp1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTokenRingProtocol() {
/* 224 */     if (this.LInteractionToReserve.size() != 0) {
/* 225 */       setComponentInstanceTokenRingProtocol();
/* 226 */       setConnectionInterface_IP_RPTokenRing();
/* 227 */       setConnectionTokenRingProtocol();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConnectionTokenRingProtocol() {
/* 235 */     List<Port> LPortToken = new LinkedList<Port>();
/* 236 */     List<Component> LComponent = new LinkedList<Component>();
/*     */     
/* 238 */     for (Connector o : this.LInteractionToReserve) {
/* 239 */       Connector connector = o;
/* 240 */       int indexOfConnector = this.LInteractionToReserve.indexOf(connector);
/* 241 */       if (indexOfConnector < this.LInteractionToReserve.size() - 1) {
/* 242 */         TokenRingProtocol TRP1 = this.Map_Conn_TRComponent.get(connector);
/* 243 */         TokenRingProtocol TRP2 = this.Map_Conn_TRComponent.get(this.LInteractionToReserve.get(indexOfConnector + 1));
/* 244 */         LPortToken.clear();
/* 245 */         LComponent.clear();
/* 246 */         LComponent.add(TRP1.getComponentRPInstance());
/* 247 */         LComponent.add(TRP2.getComponentRPInstance());
/* 248 */         LPortToken.add(TRP1.getSendTokenPort());
/* 249 */         LPortToken.add(TRP2.getReceiveTokenPort());
/* 250 */         ConnectorType connTypeSRToken = getConnectorTypeSR(TRP1.getSendTokenPort().getType());
/* 251 */         TransformationFunction.CreateConnector("Conn_SRToken" + IndexSRTokenConn++, connTypeSRToken, this.DCompoundType, LComponent, LPortToken);
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     Connector FirstConnector = this.LInteractionToReserve.get(0);
/* 256 */     Connector LastConnector = this.LInteractionToReserve.get(this.LInteractionToReserve.size() - 1);
/* 257 */     if (!FirstConnector.equals(LastConnector)) {
/* 258 */       TokenRingProtocol TRP1 = this.Map_Conn_TRComponent.get(FirstConnector);
/* 259 */       TokenRingProtocol TRP2 = this.Map_Conn_TRComponent.get(LastConnector);
/* 260 */       LPortToken.clear();
/* 261 */       LComponent.clear();
/* 262 */       LComponent.add(TRP2.getComponentRPInstance());
/* 263 */       LComponent.add(TRP1.getComponentRPInstance());
/* 264 */       LPortToken.add(TRP2.getSendTokenPort());
/* 265 */       LPortToken.add(TRP1.getReceiveTokenPort());
/* 266 */       ConnectorType connTypeSRToken = getConnectorTypeSR(TRP1.getSendTokenPort().getType());
/* 267 */       TransformationFunction.CreateConnector("Conn_SRToken" + IndexSRTokenConn++, connTypeSRToken, this.DCompoundType, LComponent, LPortToken);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setComponentInstanceDiningProtocol() {
/* 275 */     for (Connector o : this.LInteractionToReserve) {
/* 276 */       Connector connector = o;
/* 277 */       List<Connector> LConn = new LinkedList<Connector>();
/* 278 */       LConn.add(connector);
/* 279 */       DiningPhiloProtocol DPProtocol = new DiningPhiloProtocol(LConn, "DiningPhiloType_" + connector.getName());
/* 280 */       DPProtocol.setComponentInstance(this.DCompoundType);
/* 281 */       this.Map_Conn_DPComponent.put(connector, DPProtocol);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setComponentInstanceTokenRingProtocol() {
/* 286 */     for (Connector o : this.LInteractionToReserve) {
/* 287 */       Connector connector = o;
/* 288 */       List<Connector> LConn = new LinkedList<Connector>();
/* 289 */       LConn.add(connector);
/* 290 */       TokenRingProtocol TRProtocol = new TokenRingProtocol(LConn, "TokenRingType_" + connector.getName());
/* 291 */       TRProtocol.setComponentInstance(this.DCompoundType);
/* 292 */       this.Map_Conn_TRComponent.put(connector, TRProtocol);
/* 293 */       if (this.LInteractionToReserve.indexOf(o) == 0) {
/* 294 */         TRProtocol.setInitialHasToken();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCentrilizedProtocol() {
/* 302 */     if (this.LInteractionToReserve.size() != 0) {
/* 303 */       setComponentInstanceCentrilizedProtocol();
/* 304 */       setConnectionInterface_IP_RPCentrilized();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConnectionInterface_IP_RPCentrilized() {
/* 311 */     List<Component> LComponentReservePort = new LinkedList<Component>();
/* 312 */     List<Component> LComponentOKFAILPort = new LinkedList<Component>();
/*     */     
/* 314 */     List<Port> LOKPort = new LinkedList<Port>();
/* 315 */     List<Port> LFAILPort = new LinkedList<Port>();
/* 316 */     List<Port> LReservePort = new LinkedList<Port>();
/*     */     
/* 318 */     for (Connector o : this.LInteractionToReserve) {
/* 319 */       Connector connector = o;
/* 320 */       InteractionProtocol IP = getInteractionProtocol(connector);
/* 321 */       Component componentIP = IP.getComponentIPInstance();
/*     */ 
/*     */ 
/*     */       
/* 325 */       LReservePort.clear();
/* 326 */       LComponentReservePort.clear();
/* 327 */       Port CP_PortReserve = this.CentrilizedProtocolComp.Map_Conn_Reserve.get(connector);
/* 328 */       Port IP_PortReserve = IP.Map_Interaction_Reserve.get(connector);
/* 329 */       LReservePort.add(IP_PortReserve);
/* 330 */       LReservePort.add(IP_PortReserve);
/* 331 */       LComponentReservePort.add(componentIP);
/* 332 */       LComponentReservePort.add(this.CentrilizedProtocolComp.getComponentRPInstance());
/* 333 */       ConnectorType connTypeReserve = getConnectorTypeSR(CP_PortReserve.getType());
/* 334 */       TransformationFunction.CreateConnector("Conn_Reserve" + IndexReserveConn++, connTypeReserve, this.DCompoundType, LComponentReservePort, LReservePort);
/*     */ 
/*     */ 
/*     */       
/* 338 */       LComponentOKFAILPort.clear();
/* 339 */       LComponentOKFAILPort.add(this.CentrilizedProtocolComp.getComponentRPInstance());
/* 340 */       LComponentOKFAILPort.add(componentIP);
/*     */       
/* 342 */       LOKPort.clear();
/* 343 */       Port IP_OK = IP.Map_Interaction_OK.get(connector);
/* 344 */       Port CP_PortOK = this.CentrilizedProtocolComp.Map_Conn_PortOk.get(connector);
/* 345 */       LOKPort.add(CP_PortOK);
/* 346 */       LOKPort.add(IP_OK);
/* 347 */       ConnectorType connTypeOK = getConnectorTypeSR(IP_OK.getType());
/* 348 */       TransformationFunction.CreateConnector("Conn_OK" + IndexOKConn++, connTypeOK, this.DCompoundType, LComponentOKFAILPort, LOKPort);
/*     */ 
/*     */ 
/*     */       
/* 352 */       LFAILPort.clear();
/* 353 */       Port IP_FAIL = IP.Map_Interaction_FAIL.get(connector);
/* 354 */       Port CP_PortFAIL = this.CentrilizedProtocolComp.Map_Conn_PortFail.get(connector);
/* 355 */       LFAILPort.add(CP_PortFAIL);
/* 356 */       LFAILPort.add(IP_FAIL);
/* 357 */       ConnectorType connTypeFAIL = getConnectorTypeSR(IP_FAIL.getType());
/* 358 */       TransformationFunction.CreateConnector("Conn_FAIL" + IndexFailConn++, connTypeFAIL, this.DCompoundType, LComponentOKFAILPort, LFAILPort);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setConnectionInterface_IP_RPTokenRing() {
/* 364 */     List<Component> LComponentReservePort = new LinkedList<Component>();
/* 365 */     List<Component> LComponentOKFAILPort = new LinkedList<Component>();
/*     */     
/* 367 */     List<Port> LOKPort = new LinkedList<Port>();
/* 368 */     List<Port> LFAILPort = new LinkedList<Port>();
/* 369 */     List<Port> LReservePort = new LinkedList<Port>();
/* 370 */     for (Connector o : this.LInteractionToReserve) {
/* 371 */       Connector connector = o;
/* 372 */       InteractionProtocol IP = getInteractionProtocol(connector);
/* 373 */       Component componentIP = IP.getComponentIPInstance();
/* 374 */       TokenRingProtocol TRP = this.Map_Conn_TRComponent.get(connector);
/* 375 */       Component componentTRP = TRP.getComponentRPInstance();
/*     */ 
/*     */       
/* 378 */       LComponentReservePort.clear();
/* 379 */       LReservePort.clear();
/* 380 */       LComponentReservePort.add(componentIP);
/* 381 */       LComponentReservePort.add(componentTRP);
/* 382 */       Port TRP_ReservePort = TRP.Map_Conn_Reserve.get(connector);
/* 383 */       Port IP_ReservePort = IP.Map_Interaction_Reserve.get(connector);
/* 384 */       LReservePort.add(IP_ReservePort);
/* 385 */       LReservePort.add(TRP_ReservePort);
/* 386 */       ConnectorType connTypeReserve = getConnectorTypeSR(TRP_ReservePort.getType());
/* 387 */       TransformationFunction.CreateConnector("Conn_Reserve" + IndexReserveConn++, connTypeReserve, this.DCompoundType, LComponentReservePort, LReservePort);
/*     */ 
/*     */       
/* 390 */       LComponentOKFAILPort.clear();
/* 391 */       LComponentOKFAILPort.add(componentTRP);
/* 392 */       LComponentOKFAILPort.add(componentIP);
/*     */       
/* 394 */       LOKPort.clear();
/* 395 */       Port IP_OK = IP.Map_Interaction_OK.get(connector);
/* 396 */       Port TRP_PortOK = TRP.Map_Conn_PortOk.get(connector);
/* 397 */       LOKPort.add(TRP_PortOK);
/* 398 */       LOKPort.add(IP_OK);
/* 399 */       ConnectorType connTypeOK = getConnectorTypeSR(IP_OK.getType());
/* 400 */       TransformationFunction.CreateConnector("Conn_OK" + IndexOKConn++, connTypeOK, this.DCompoundType, LComponentOKFAILPort, LOKPort);
/*     */ 
/*     */ 
/*     */       
/* 404 */       LFAILPort.clear();
/* 405 */       Port IP_FAIL = IP.Map_Interaction_FAIL.get(connector);
/* 406 */       Port TRP_PortFAIL = TRP.Map_Conn_PortFail.get(connector);
/* 407 */       LFAILPort.add(TRP_PortFAIL);
/* 408 */       LFAILPort.add(IP_FAIL);
/* 409 */       ConnectorType connTypeFAIL = getConnectorTypeSR(IP_FAIL.getType());
/* 410 */       TransformationFunction.CreateConnector("Conn_FAIL" + IndexFailConn++, connTypeFAIL, this.DCompoundType, LComponentOKFAILPort, LFAILPort);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setConnectionInterface_IP_RPDiningPhilo() {
/* 415 */     List<Component> LComponentReservePort = new LinkedList<Component>();
/* 416 */     List<Component> LComponentOKFAILPort = new LinkedList<Component>();
/*     */     
/* 418 */     List<Port> LOKPort = new LinkedList<Port>();
/* 419 */     List<Port> LFAILPort = new LinkedList<Port>();
/* 420 */     List<Port> LReservePort = new LinkedList<Port>();
/* 421 */     for (Connector o : this.LInteractionToReserve) {
/* 422 */       Connector connector = o;
/* 423 */       InteractionProtocol IP = getInteractionProtocol(connector);
/* 424 */       Component componentIP = IP.getComponentIPInstance();
/* 425 */       DiningPhiloProtocol DPP = this.Map_Conn_DPComponent.get(connector);
/* 426 */       Component componentDPP = DPP.getComponentRPInstance();
/*     */ 
/*     */       
/* 429 */       LComponentReservePort.clear();
/* 430 */       LReservePort.clear();
/* 431 */       LComponentReservePort.add(componentIP);
/* 432 */       LComponentReservePort.add(componentDPP);
/* 433 */       Port DPP_ReservePort = DPP.Map_Conn_Reserve.get(connector);
/* 434 */       Port IP_ReservePort = IP.Map_Interaction_Reserve.get(connector);
/* 435 */       LReservePort.add(IP_ReservePort);
/* 436 */       LReservePort.add(DPP_ReservePort);
/* 437 */       ConnectorType connTypeReserve = getConnectorTypeSR(DPP_ReservePort.getType());
/* 438 */       TransformationFunction.CreateConnector("Conn_Reserve" + IndexReserveConn++, connTypeReserve, this.DCompoundType, LComponentReservePort, LReservePort);
/*     */ 
/*     */       
/* 441 */       LComponentOKFAILPort.clear();
/* 442 */       LComponentOKFAILPort.add(componentDPP);
/* 443 */       LComponentOKFAILPort.add(componentIP);
/*     */       
/* 445 */       LOKPort.clear();
/* 446 */       Port IP_OK = IP.Map_Interaction_OK.get(connector);
/* 447 */       Port DPP_PortOK = DPP.Map_Conn_PortOk.get(connector);
/* 448 */       LOKPort.add(DPP_PortOK);
/* 449 */       LOKPort.add(IP_OK);
/* 450 */       ConnectorType connTypeOK = getConnectorTypeSR(IP_OK.getType());
/* 451 */       TransformationFunction.CreateConnector("Conn_OK" + IndexOKConn++, connTypeOK, this.DCompoundType, LComponentOKFAILPort, LOKPort);
/*     */ 
/*     */ 
/*     */       
/* 455 */       LFAILPort.clear();
/* 456 */       Port IP_FAIL = IP.Map_Interaction_FAIL.get(connector);
/* 457 */       Port DPP_PortFAIL = DPP.Map_Conn_PortFail.get(connector);
/* 458 */       LFAILPort.add(DPP_PortFAIL);
/* 459 */       LFAILPort.add(IP_FAIL);
/* 460 */       ConnectorType connTypeFAIL = getConnectorTypeSR(IP_FAIL.getType());
/* 461 */       TransformationFunction.CreateConnector("Conn_FAIL" + IndexFailConn++, connTypeFAIL, this.DCompoundType, LComponentOKFAILPort, LFAILPort);
/*     */     } 
/*     */   }
/*     */   private void setLInteractionToReserve() {
/* 465 */     for (Object o : this.compoundType.getConnector()) {
/* 466 */       Connector connector = (Connector)o;
/* 467 */       List<Connector> LInteractionIP = getGroupInteraction(connector);
/* 468 */       List<Component> LComponent = compoundTypeInfo.getConnectorExternalConflict(connector, LInteractionIP);
/* 469 */       if (LComponent.size() > 0)
/* 470 */         this.LInteractionToReserve.add(connector); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setComponentInstanceCentrilizedProtocol() {
/* 475 */     this.CentrilizedProtocolComp = new CentralizedProtocol(this.LInteractionToReserve, "CentrilizedReservation");
/* 476 */     this.CentrilizedProtocolComp.setComponentInstance(this.DCompoundType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConnectionInterfaceComponentIP() {
/* 482 */     List<Component> LComponentDataPort = new LinkedList<Component>();
/* 483 */     List<Component> LComponentPort = new LinkedList<Component>();
/* 484 */     List<Component> LComponentOfferPort = new LinkedList<Component>();
/*     */     
/* 486 */     List<Port> LDataPort = new LinkedList<Port>();
/* 487 */     List<Port> LDPort = new LinkedList<Port>();
/* 488 */     List<Port> LOfferPort = new LinkedList<Port>();
/*     */     
/* 490 */     for (String o : this.Map_IPName_IP.keySet()) {
/* 491 */       InteractionProtocol IP = this.Map_IPName_IP.get(o);
/* 492 */       Component IPComponentInstance = IP.getComponentIPInstance();
/*     */       
/* 494 */       for (InnerPortReference o1 : IP.Map_CentIPR_DistDataPort.keySet()) {
/* 495 */         InnerPortReference IPR = o1;
/* 496 */         Port DataPortComp = IP.Map_CentIPR_DistDataPort.get(o1);
/* 497 */         Port DataPortIP = IP.Map_CentIPR_IPDataPort.get(o1);
/* 498 */         Component component = (Component)IPR.getTargetInstance().getTargetPart();
/* 499 */         DAtomType DAT = Map_Component_DAtomType.get(component);
/* 500 */         Component DComponent = ((DAtomType)Map_Component_DAtomType.get(component)).getDATInstance();
/* 501 */         Port PortComp = IP.Map_CentIPR_DistPort.get(o1);
/* 502 */         Port PortIP = IP.Map_CentIPR_IPPort.get(o1);
/* 503 */         ConnectorType connType = getConnectorTypeSR(DataPortComp.getType());
/*     */ 
/*     */         
/* 506 */         LComponentDataPort.clear();
/* 507 */         LDataPort.clear();
/* 508 */         LComponentDataPort.add(DComponent);
/* 509 */         LComponentDataPort.add(IPComponentInstance);
/* 510 */         LDataPort.add(DataPortComp);
/* 511 */         LDataPort.add(DataPortIP);
/* 512 */         TransformationFunction.CreateConnector("Conn_Data" + IndexDataPortConn++, connType, this.DCompoundType, LComponentDataPort, LDataPort);
/*     */ 
/*     */         
/* 515 */         LDPort.clear();
/* 516 */         LComponentPort.clear();
/* 517 */         LComponentPort.add(IPComponentInstance);
/* 518 */         LComponentPort.add(DComponent);
/* 519 */         LDPort.add(PortIP);
/* 520 */         LDPort.add(PortComp);
/* 521 */         TransformationFunction.CreateConnector("Conn_Port" + IndexPortConn++, connType, this.DCompoundType, LComponentPort, LDPort);
/*     */       } 
/*     */ 
/*     */       
/* 525 */       for (Component o1 : IP.Map_Component_OfferPortComp.keySet()) {
/* 526 */         Component component = o1;
/* 527 */         Component DComponent = ((DAtomType)Map_Component_DAtomType.get(component)).getDATInstance();
/* 528 */         Port CompOfferPort = IP.Map_Component_OfferPortComp.get(o1);
/* 529 */         Port IPOfferPort = IP.Map_Component_OfferPortIP.get(o1);
/* 530 */         LOfferPort.clear();
/* 531 */         LComponentOfferPort.clear();
/* 532 */         LOfferPort.add(CompOfferPort);
/* 533 */         LOfferPort.add(IPOfferPort);
/* 534 */         LComponentOfferPort.add(DComponent);
/* 535 */         LComponentOfferPort.add(IPComponentInstance);
/* 536 */         ConnectorType connType = getConnectorTypeSR(CompOfferPort.getType());
/* 537 */         TransformationFunction.CreateConnector("Conn_Port" + IndexPortConn++, connType, this.DCompoundType, LComponentOfferPort, LOfferPort);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_IPName_IP() {
/* 549 */     for (Object<Connector> o : LLInteraction) {
/* 550 */       List<Connector> LInteraction = (List<Connector>)o;
/* 551 */       String IPName = LInteractionProtocolName.get(LLInteraction.indexOf(o));
/* 552 */       InteractionProtocol IP = new InteractionProtocol(LInteraction, IPName);
/* 553 */       this.Map_IPName_IP.put(IPName, IP);
/* 554 */       IP.setComponentInstance(this.DCompoundType);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Component_AtomType() {
/* 564 */     int i = 1;
/* 565 */     for (Object o : this.compoundType.getSubcomponent()) {
/* 566 */       Component component = (Component)o;
/* 567 */       DAtomType DAT = new DAtomType(component, Integer.valueOf(i++));
/* 568 */       Map_Component_DAtomType.put(component, DAT);
/* 569 */       DAT.setComponentInstance(this.DCompoundType);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setInternPortType() {
/* 577 */     for (Object o : module.getBipType()) {
/* 578 */       if (o instanceof PortType) {
/* 579 */         PortType pt = (PortType)o;
/* 580 */         if (pt.getName().equals("Port")) {
/* 581 */           InternPortType = pt;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Name_Interaction() {
/* 592 */     for (Object o : this.compoundType.getConnector()) {
/* 593 */       Connector connector = (Connector)o;
/* 594 */       this.Map_Name_Interaction.put(connector.getName(), connector);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setIPRepartition() {
/* 602 */     setLLInteraction();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 608 */   private static int IPIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLLInteraction() {
/* 614 */     for (Object<String> o : this.LLInteractionName) {
/*     */       
/* 616 */       List<String> LInteractionName = (List<String>)o;
/* 617 */       List<Connector> LInteraction = new LinkedList<Connector>();
/* 618 */       String InteractionProtocolName = new String("IP" + IPIndex++);
/* 619 */       for (String o1 : LInteractionName) {
/*     */         
/* 621 */         String InteractionName = o1;
/* 622 */         Connector connector = this.Map_Name_Interaction.get(InteractionName);
/* 623 */         LInteraction.add(connector);
/*     */       } 
/*     */       
/* 626 */       LLInteraction.add(LInteraction);
/* 627 */       LInteractionProtocolName.add(InteractionProtocolName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType getOfferPortType(Integer n) {
/* 638 */     if (Map_n_OfferPortType.containsKey(n)) {
/* 639 */       return Map_n_OfferPortType.get(n);
/*     */     }
/* 641 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 642 */     Variable intVar = TransformationFunction.CreateIntVariable("n", -1);
/* 643 */     LVariable.add(intVar);
/* 644 */     for (int i = 0; i < n.intValue(); i++) {
/* 645 */       Variable boolVar = TransformationFunction.CreateGuardVariable("b" + i, true);
/* 646 */       LVariable.add(boolVar);
/*     */     } 
/* 648 */     List<DataParameter> LDataParameter = TransformationFunction.CreateDataParameter(LVariable);
/* 649 */     PortType pt = TransformationFunction.CreatePortType(LDataParameter, "PortType_Offer_" + n, module);
/* 650 */     Map_n_OfferPortType.put(n, pt);
/* 651 */     module.getBipType().add(pt);
/* 652 */     return pt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType getReservePortType(Integer n) {
/* 663 */     if (Map_n_ReservePortType.containsKey(n))
/* 664 */       return Map_n_ReservePortType.get(n); 
/* 665 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 666 */     for (int i = 0; i < n.intValue(); i++) {
/* 667 */       Variable intVar = TransformationFunction.CreateIntVariable("x" + i, -1);
/* 668 */       LVariable.add(intVar);
/*     */     } 
/* 670 */     List<DataParameter> LDataParameter = TransformationFunction.CreateDataParameter(LVariable);
/* 671 */     PortType pt = TransformationFunction.CreatePortType(LDataParameter, "PortType_Reserve_" + n, module);
/* 672 */     Map_n_ReservePortType.put(n, pt);
/* 673 */     module.getBipType().add(pt);
/* 674 */     return pt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType getInternPortType() {
/* 683 */     return InternPortType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getInteractionProtocolName(List<Connector> LConnector) {
/* 694 */     List<String> LString = new LinkedList<String>();
/* 695 */     if (LConnector == null) return LString; 
/* 696 */     for (Object<Connector> o : LLInteraction) {
/* 697 */       List<Connector> LConnector1 = (List<Connector>)o;
/* 698 */       if (DList.IsListIntersect(LConnector, LConnector1)) {
/* 699 */         String IPName = LInteractionProtocolName.get(LLInteraction.indexOf(o));
/* 700 */         LString.add(IPName);
/*     */       } 
/*     */     } 
/* 703 */     return LString;
/*     */   }
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
/*     */   public static Integer getComponentID(Component component) {
/* 735 */     DAtomType dat = Map_Component_DAtomType.get(component);
/* 736 */     return dat.getComponentID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List<Connector> getGroupInteraction(Connector connector) {
/* 746 */     for (Object<Connector> o : LLInteraction) {
/* 747 */       List<Connector> LConnector = (List<Connector>)o;
/* 748 */       if (LConnector.contains(connector))
/* 749 */         return LConnector; 
/*     */     } 
/* 751 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLComponentReserve() {
/* 761 */     for (Object<Connector> o : LLInteraction) {
/* 762 */       List<Connector> LInteraction = (List<Connector>)o;
/* 763 */       for (Connector o1 : LInteraction) {
/* 764 */         Connector connector = o1;
/* 765 */         List<Component> LComponent = new LinkedList<Component>();
/* 766 */         List<Connector> LInteractionIP = getGroupInteraction(connector);
/* 767 */         LComponent = compoundTypeInfo.getConnectorExternalConflict(connector, LInteractionIP);
/* 768 */         DList.AddListUnique(LComponent, LComponentReserve);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ConnectorType getConnectorTypeSR(PortType PT) {
/* 774 */     if (this.Map_PortType_ConnTypeSR.containsKey(PT))
/* 775 */       return this.Map_PortType_ConnTypeSR.get(PT); 
/* 776 */     ConnectorType connTypeSR = TransformationFunction.CreateConnectorTypeSendReceive(PT);
/* 777 */     this.Map_PortType_ConnTypeSR.put(PT, connTypeSR);
/* 778 */     return connTypeSR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType getSRTokentPortType() {
/* 787 */     return getReservePortType(Integer.valueOf(LComponentReserve.size()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private InteractionProtocol getInteractionProtocol(Connector connector) {
/* 793 */     for (Object<Connector> o : LLInteraction) {
/* 794 */       List<Connector> LConnector = (List<Connector>)o;
/* 795 */       if (LConnector.contains(connector)) {
/* 796 */         String IPName = LInteractionProtocolName.get(LLInteraction.indexOf(o));
/* 797 */         return this.Map_IPName_IP.get(IPName);
/*     */       } 
/*     */     } 
/* 800 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\TOP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */