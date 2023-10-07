/*     */ package unifiedDistributed;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import distributed.DList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ 
/*     */ public class InteractionProtocol {
/*  33 */   private List<Connector> LInteraction = new LinkedList<Connector>();
/*  34 */   private List<List<Variable>> LVariableConnector = new LinkedList<List<Variable>>();
/*  35 */   private List<List<Variable>> LVariableIP = new LinkedList<List<Variable>>();
/*  36 */   private List<Component> LComponent = new LinkedList<Component>();
/*  37 */   private List<Component> LConflictComponent = new LinkedList<Component>();
/*  38 */   private List<List<Variable>> LLVariableOfferVar = new LinkedList<List<Variable>>();
/*  39 */   public Map<Component, Port> Map_Component_OfferPortIP = new HashMap<Component, Port>();
/*  40 */   public Map<Component, Port> Map_Component_OfferPortComp = new HashMap<Component, Port>();
/*  41 */   private Map<Component, Variable> Map_Component_RecOffer = new HashMap<Component, Variable>();
/*     */   
/*  43 */   private Map<Port, Port> Map_OfferPortIP_OfferPortComp = new HashMap<Port, Port>();
/*  44 */   private Map<Component, State> Map_Component_StateBefore = new HashMap<Component, State>();
/*  45 */   private Map<Component, State> Map_Component_StateAfter = new HashMap<Component, State>();
/*     */   
/*     */   private State ReceiveData;
/*  48 */   private List<List<Variable>> LLVariableComponent = new LinkedList<List<Variable>>();
/*  49 */   private List<List<Variable>> LLVariableCompIP = new LinkedList<List<Variable>>();
/*     */   
/*  51 */   public Map<Connector, Port> Map_Interaction_Reserve = new HashMap<Connector, Port>();
/*  52 */   private Map<Component, Variable> Map_Component_Nvar = new HashMap<Component, Variable>();
/*     */   
/*  54 */   private List<InnerPortReference> LCentIPR = new LinkedList<InnerPortReference>();
/*  55 */   private Map<Port, State> Map_Port_State = new HashMap<Port, State>();
/*  56 */   private Map<Port, Variable> Map_Port_GuardVar = new HashMap<Port, Variable>();
/*  57 */   public Map<InnerPortReference, Port> Map_CentIPR_IPPort = new HashMap<InnerPortReference, Port>();
/*  58 */   public Map<Port, InnerPortReference> Map_IPPort_CentIPR = new HashMap<Port, InnerPortReference>();
/*  59 */   public Map<Port, InnerPortReference> Map_IPDataPort_CentIPR = new HashMap<Port, InnerPortReference>();
/*  60 */   public Map<InnerPortReference, Port> Map_CentIPR_DistPort = new HashMap<InnerPortReference, Port>();
/*  61 */   public Map<InnerPortReference, Port> Map_CentIPR_DistDataPort = new HashMap<InnerPortReference, Port>();
/*  62 */   public Map<InnerPortReference, Port> Map_CentIPR_IPDataPort = new HashMap<InnerPortReference, Port>();
/*     */   
/*  64 */   private Map<Port, Port> Map_IPPort_DistPort = new HashMap<Port, Port>();
/*     */   private String IPName;
/*  66 */   private List<Port> LPort = new LinkedList<Port>();
/*  67 */   private AtomType AtomTypeIP = BehaviorsFactory.eINSTANCE.createAtomType();
/*     */   private PetriNet PetriNetIP;
/*     */   private PortDefinition InterPort;
/*  70 */   public Map<Connector, Port> Map_Interaction_OK = new HashMap<Connector, Port>();
/*  71 */   public Map<Connector, Port> Map_Interaction_FAIL = new HashMap<Connector, Port>();
/*     */ 
/*     */   
/*  74 */   private Map<Connector, Variable> Map_Interaction_IndexCompFail = new HashMap<Connector, Variable>();
/*     */ 
/*     */ 
/*     */   
/*     */   private Component ComponentIPInstance;
/*     */ 
/*     */   
/*  81 */   private Map<Connector, String> Map_Interaction_BlockTimeStart = new HashMap<Connector, String>();
/*  82 */   private Map<Connector, String> Map_Interaction_BlockTimeEnd = new HashMap<Connector, String>();
/*  83 */   private Map<Connector, String> Map_Interaction_BlockTimeSeconds = new HashMap<Connector, String>();
/*  84 */   private Map<Connector, String> Map_Interaction_NbOfBlock = new HashMap<Connector, String>();
/*  85 */   private String PrintDebugIP = new String("");
/*  86 */   private String PrintAssignment = new String("");
/*  87 */   private String PrintValues = new String("");
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
/*     */   public InteractionProtocol(List<Connector> LInteraction, String IPName) {
/* 103 */     setInternSynPort();
/* 104 */     this.LInteraction = LInteraction;
/* 105 */     this.IPName = IPName;
/* 106 */     this.AtomTypeIP.setName(IPName);
/* 107 */     this.LComponent = TOP.compoundTypeInfo.getComponent(LInteraction);
/* 108 */     this.LCentIPR = TOP.compoundTypeInfo.getLIPR(LInteraction);
/* 109 */     setConflictComponent();
/* 110 */     setVariableComponentIP();
/* 111 */     setMaps();
/* 112 */     setLVariableIP();
/* 113 */     setBehavior();
/* 114 */     TOP.module.getBipType().add(this.AtomTypeIP);
/*     */   }
/*     */   
/*     */   private void InitializeDebugInformation(Connector connector, String Name) {
/* 118 */     String BlockTimeStart = "IP_BTStart_" + Name + "_" + connector.getName();
/* 119 */     String BlockTimeEnd = "IP_BTEnd_" + Name + "_" + connector.getName();
/* 120 */     String BlockTimeSeconds = "IP_BTSeconds_" + Name + "_" + connector.getName();
/* 121 */     String NumberOfBlock = "IP_NumborOfBlock_" + Name + "_" + connector.getName();
/* 122 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n struct timespec " + BlockTimeStart + ";");
/* 123 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n struct timespec " + BlockTimeEnd + ";");
/* 124 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n double " + BlockTimeSeconds + " = 0 ;");
/* 125 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n int " + NumberOfBlock + " = 0 ;\n");
/* 126 */     this.Map_Interaction_BlockTimeEnd.put(connector, BlockTimeEnd);
/* 127 */     this.Map_Interaction_BlockTimeStart.put(connector, BlockTimeStart);
/* 128 */     this.Map_Interaction_BlockTimeSeconds.put(connector, BlockTimeSeconds);
/* 129 */     this.Map_Interaction_NbOfBlock.put(connector, NumberOfBlock);
/* 130 */     this.PrintAssignment = String.valueOf(this.PrintAssignment) + BlockTimeSeconds + " = %f \\n " + NumberOfBlock + " = %d \\n ";
/* 131 */     this.PrintValues = String.valueOf(this.PrintValues) + ", " + BlockTimeSeconds + "," + NumberOfBlock;
/*     */   }
/*     */   
/*     */   private void setConflictComponent() {
/* 135 */     for (Connector o : this.LInteraction) {
/* 136 */       Connector connector = o;
/* 137 */       DList.AddListUnique(TOP.compoundTypeInfo.getConnectorExternalConflict(connector, this.LInteraction), this.LConflictComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setInternSynPort() {
/* 147 */     PortType SynPortType = TOP.getInternPortType();
/* 148 */     this.InterPort = TransformationFunction.CreatePortDefinition("InternPortSyn", SynPortType, this.AtomTypeIP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBehavior() {
/* 155 */     this.AtomTypeIP.setBehavior((Behavior)BehaviorsFactory.eINSTANCE.createPetriNet());
/* 156 */     this.PetriNetIP = (PetriNet)this.AtomTypeIP.getBehavior();
/* 157 */     CreateReceiveDataTransition();
/* 158 */     CreateInitialOfferTransition();
/* 159 */     CreateTransitionForInteraction();
/*     */   }
/*     */ 
/*     */   
/*     */   private void CreateTransitionForInteraction() {
/* 164 */     SetPortState();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     for (Connector o : this.LInteraction) {
/* 171 */       Connector connector = o;
/* 172 */       List<Component> LConflictComponent = TOP.compoundTypeInfo.getConnectorExternalConflict(connector, this.LInteraction);
/* 173 */       if (LConflictComponent.size() == 0) {
/* 174 */         CreateInternalInteraction(connector);
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/* 181 */       Port OK = TransformationFunction.CreatePort("OK" + connector.getName(), TOP.getInternPortType(), this.AtomTypeIP);
/* 182 */       PortType FailPT = TOP.getReservePortType(Integer.valueOf(1));
/* 183 */       List<Variable> LVariable = new LinkedList<Variable>();
/* 184 */       Variable IndexCompFail = TransformationFunction.CreateIntVariable("IndexCompFail" + connector.getName(), -1);
/* 185 */       this.Map_Interaction_IndexCompFail.put(connector, IndexCompFail);
/* 186 */       LVariable.add(IndexCompFail);
/* 187 */       this.AtomTypeIP.getVariable().add(IndexCompFail);
/* 188 */       Port FAIL = TransformationFunction.CreatePort("FAIL" + connector.getName(), FailPT, this.AtomTypeIP, LVariable);
/* 189 */       this.Map_Interaction_OK.put(connector, OK);
/* 190 */       this.Map_Interaction_FAIL.put(connector, FAIL);
/* 191 */       CreateReservationInteraction(connector, LConflictComponent);
/*     */     } 
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
/*     */   private void CreateReservationInteraction(Connector connector, List<Component> conflictComponent) {
/* 210 */     Port ReservePort = CreateReservePort(connector, conflictComponent);
/* 211 */     PortDefinition PortDefReserve = TransformationFunction.getPortDefinition(ReservePort);
/* 212 */     EList eList = connector.getActualPort();
/* 213 */     List<State> LStateFrom = new LinkedList<State>();
/* 214 */     List<State> LStateOKTo = new LinkedList<State>();
/* 215 */     List<Variable> LGuardVariable = new LinkedList<Variable>();
/* 216 */     Expression EguardValPort = null;
/* 217 */     State StateReserve = TransformationFunction.CreateState("Reserve_" + connector.getName(), this.PetriNetIP);
/* 218 */     List<State> LStateReserve = new LinkedList<State>();
/* 219 */     LStateReserve.add(StateReserve);
/* 220 */     for (ActualPortParameter o : eList) {
/* 221 */       assert o instanceof InnerPortReference;
/* 222 */       InnerPortReference IPR = (InnerPortReference)o;
/* 223 */       Component component = (Component)IPR.getTargetInstance().getTargetPart();
/* 224 */       LStateFrom.add(this.Map_Component_StateAfter.get(component));
/* 225 */       Port IPPort = this.Map_CentIPR_IPPort.get(IPR);
/* 226 */       LStateOKTo.add(this.Map_Port_State.get(IPPort));
/* 227 */       Port PortIP = this.Map_CentIPR_IPPort.get(IPR);
/* 228 */       LGuardVariable.add(this.Map_Port_GuardVar.get(PortIP));
/*     */     } 
/* 230 */     ConnectorType connType = connector.getType();
/* 231 */     EguardValPort = TransformationFunction.CreateAndExpression(LGuardVariable);
/* 232 */     List<List<Variable>> LLVariable = getCorrespondingPortPara_Var(connector);
/* 233 */     Expression GuardConnector = TransformationFunction.getNewGuardConnector(connType, (List)connType.getPortParameter(), LLVariable);
/* 234 */     Expression FinalGuard = TransformationFunction.AndOfTwoExpression(EguardValPort, GuardConnector);
/* 235 */     int indexOfConnector = this.LInteraction.indexOf(connector);
/* 236 */     Action ActionOfConnector = TransformationFunction.getNewFunctionConnector(connType, (List)connType.getPortParameter(), LLVariable, this.LVariableConnector.get(indexOfConnector), this.LVariableIP.get(indexOfConnector));
/*     */ 
/*     */     
/* 239 */     Action getTime = null;
/*     */ 
/*     */     
/* 242 */     TransformationFunction.CreateTransitionLState(PortDefReserve, FinalGuard, getTime, LStateFrom, LStateReserve, this.PetriNetIP);
/*     */ 
/*     */ 
/*     */     
/* 246 */     for (Component o : conflictComponent) {
/* 247 */       Component ConfComponent = o;
/* 248 */       Variable TestOfferVar = getBoolRecOffer(ConfComponent);
/* 249 */       AssignmentAction AA = TransformationFunction.CreateAssignmentActionBoolVar(TestOfferVar, true);
/* 250 */       Port OfferPort = this.Map_Component_OfferPortIP.get(ConfComponent);
/* 251 */       PortDefinition PDOffer = TransformationFunction.getPortDefinition(OfferPort);
/* 252 */       TransformationFunction.CreateTransitionLState(PDOffer, null, (Action)AA, LStateReserve, LStateReserve, this.PetriNetIP);
/*     */     } 
/*     */ 
/*     */     
/* 256 */     Action CA = ActionOfConnector;
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
/* 268 */     PortDefinition PDOK = TransformationFunction.getPortDefinition(this.Map_Interaction_OK.get(connector));
/* 269 */     TransformationFunction.CreateTransitionLState(PDOK, null, CA, LStateReserve, LStateOKTo, this.PetriNetIP);
/*     */ 
/*     */ 
/*     */     
/* 273 */     Action setTime = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     State stateFail = TransformationFunction.CreateState("Fail_" + connector.getName(), this.PetriNetIP);
/* 280 */     List<State> LStateFail = new LinkedList<State>();
/* 281 */     LStateFail.add(stateFail);
/* 282 */     PortDefinition PDFAIL = TransformationFunction.getPortDefinition(this.Map_Interaction_FAIL.get(connector));
/* 283 */     TransformationFunction.CreateTransitionLState(PDFAIL, null, setTime, LStateReserve, LStateFail, this.PetriNetIP);
/*     */ 
/*     */     
/* 286 */     Expression GuardFailRest = null;
/* 287 */     List<Variable> LVariableBoolOffer = new LinkedList<Variable>();
/*     */ 
/*     */     
/* 290 */     for (Component o : conflictComponent) {
/* 291 */       Component ConfComponent = o;
/* 292 */       Variable BoolRecOffer = this.Map_Component_RecOffer.get(ConfComponent);
/* 293 */       LVariableBoolOffer.add(BoolRecOffer);
/*     */     } 
/*     */     
/* 296 */     for (Component o : conflictComponent) {
/*     */       
/* 298 */       Component ConfComponent = o;
/* 299 */       DAtomType dat = TOP.Map_Component_DAtomType.get(ConfComponent);
/* 300 */       Integer ID = dat.getComponentID();
/* 301 */       BinaryExpression binaryExpression = TransformationFunction.CreateGuardEqual(this.Map_Interaction_IndexCompFail.get(connector), ID, true);
/* 302 */       Variable BoolRecOffer = this.Map_Component_RecOffer.get(ConfComponent);
/* 303 */       Expression OfferTest = TransformationFunction.getInverseGuard(BoolRecOffer);
/* 304 */       Expression GuardFailComp = TransformationFunction.AndOfTwoExpression(OfferTest, (Expression)binaryExpression);
/*     */ 
/*     */       
/* 307 */       List<State> LStateFailTo = new LinkedList<State>(LStateFrom);
/* 308 */       State S_afterOffer = this.Map_Component_StateAfter.get(ConfComponent);
/* 309 */       State S_initial = this.Map_Component_StateBefore.get(ConfComponent);
/* 310 */       int indexSafterOffer = LStateFailTo.indexOf(S_afterOffer);
/* 311 */       LStateFailTo.set(indexSafterOffer, S_initial);
/* 312 */       CompositeAction ActionResetRecOffer = TransformationFunction.CreateAssignmentActions(LVariableBoolOffer, false);
/* 313 */       TransformationFunction.CreateTransitionLState(this.InterPort, GuardFailComp, (Action)ActionResetRecOffer, LStateFail, LStateFailTo, this.PetriNetIP);
/*     */ 
/*     */ 
/*     */       
/* 317 */       VariableReference vr = TransformationFunction.CreateVariableReference(BoolRecOffer);
/* 318 */       Expression GuardFailResti = TransformationFunction.AndOfTwoExpression((Expression)vr, (Expression)binaryExpression);
/* 319 */       if (GuardFailRest == null) {
/* 320 */         GuardFailRest = GuardFailResti; continue;
/*     */       } 
/* 322 */       GuardFailRest = TransformationFunction.OROfTwoExpression(GuardFailRest, GuardFailResti);
/*     */     } 
/*     */ 
/*     */     
/* 326 */     CompositeAction ActionRestFail = TransformationFunction.CreateAssignmentActions(LVariableBoolOffer, false);
/* 327 */     TransformationFunction.CreateTransitionLState(this.InterPort, GuardFailRest, (Action)ActionRestFail, LStateFail, LStateFrom, this.PetriNetIP);
/*     */   }
/*     */ 
/*     */   
/*     */   private Variable getBoolRecOffer(Component component) {
/* 332 */     if (this.Map_Component_RecOffer.containsKey(component)) {
/* 333 */       return this.Map_Component_RecOffer.get(component);
/*     */     }
/* 335 */     Variable TestOfferVar = TransformationFunction.CreateGuardVariable("RecOff_" + component.getName(), false);
/* 336 */     this.AtomTypeIP.getVariable().add(TestOfferVar);
/* 337 */     this.Map_Component_RecOffer.put(component, TestOfferVar);
/* 338 */     return TestOfferVar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Port CreateReservePort(Connector connector, List<Component> conflictComponent) {
/* 348 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 349 */     for (Component o : conflictComponent) {
/* 350 */       Component component = o;
/* 351 */       Variable NVar = this.Map_Component_Nvar.get(component);
/* 352 */       LVariable.add(NVar);
/*     */     } 
/* 354 */     PortType ReservePT = TOP.getReservePortType(Integer.valueOf(LVariable.size()));
/* 355 */     Port ReserveP = TransformationFunction.CreatePort("Reserve" + connector.getName(), ReservePT, this.AtomTypeIP, LVariable);
/* 356 */     this.Map_Interaction_Reserve.put(connector, ReserveP);
/* 357 */     return ReserveP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateInternalInteraction(Connector connector) {
/* 366 */     EList eList = connector.getActualPort();
/* 367 */     List<State> LStateFrom = new LinkedList<State>();
/* 368 */     List<State> LStateTo = new LinkedList<State>();
/* 369 */     List<Variable> LGuardVariable = new LinkedList<Variable>();
/* 370 */     Expression EguardValPort = null;
/* 371 */     for (ActualPortParameter o : eList) {
/* 372 */       assert o instanceof InnerPortReference;
/* 373 */       InnerPortReference IPR = (InnerPortReference)o;
/* 374 */       Component component = (Component)IPR.getTargetInstance().getTargetPart();
/* 375 */       LStateFrom.add(this.Map_Component_StateAfter.get(component));
/* 376 */       Port IPPort = this.Map_CentIPR_IPPort.get(IPR);
/* 377 */       LStateTo.add(this.Map_Port_State.get(IPPort));
/* 378 */       Port PortIP = this.Map_CentIPR_IPPort.get(IPR);
/* 379 */       LGuardVariable.add(this.Map_Port_GuardVar.get(PortIP));
/*     */     } 
/* 381 */     ConnectorType connType = connector.getType();
/* 382 */     EguardValPort = TransformationFunction.CreateAndExpression(LGuardVariable);
/* 383 */     List<List<Variable>> LLVariable = getCorrespondingPortPara_Var(connector);
/* 384 */     Expression GuardConnector = TransformationFunction.getNewGuardConnector(connType, (List)connType.getPortParameter(), LLVariable);
/* 385 */     Expression FinalGuard = TransformationFunction.AndOfTwoExpression(EguardValPort, GuardConnector);
/* 386 */     int indexOfConnector = this.LInteraction.indexOf(connector);
/* 387 */     Action ActionOfConnector = TransformationFunction.getNewFunctionConnector(connType, (List)connType.getPortParameter(), LLVariable, this.LVariableConnector.get(indexOfConnector), this.LVariableIP.get(indexOfConnector));
/* 388 */     TransformationFunction.CreateTransitionLState(this.InterPort, FinalGuard, ActionOfConnector, LStateFrom, LStateTo, this.PetriNetIP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<List<Variable>> getCorrespondingPortPara_Var(Connector connector) {
/* 399 */     List<List<Variable>> LLVariable = new LinkedList<List<Variable>>();
/* 400 */     EList eList = connector.getType().getPortParameter();
/* 401 */     for (Object o : eList) {
/* 402 */       List<Variable> LVariable = new LinkedList<Variable>();
/* 403 */       int indexPortParameter = eList.indexOf(o);
/* 404 */       InnerPortReference IPR = (InnerPortReference)connector.getActualPort().get(indexPortParameter);
/* 405 */       Port PortIP = this.Map_CentIPR_IPPort.get(IPR);
/* 406 */       LVariable.addAll((Collection<? extends Variable>)((DefinitionBinding)PortIP.getBinding()).getDefinition().getExposedVariable());
/* 407 */       LLVariable.add(LVariable);
/*     */     } 
/* 409 */     return LLVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetPortState() {
/* 418 */     for (Port o : this.Map_IPPort_CentIPR.keySet()) {
/* 419 */       Port port = o;
/* 420 */       State sport = TransformationFunction.CreateState("StateWSP" + port.getName(), this.PetriNetIP);
/* 421 */       this.Map_Port_State.put(port, sport);
/*     */ 
/*     */       
/* 424 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 425 */       PortDefinition pd = db.getDefinition();
/* 426 */       InnerPortReference IPRCent = this.Map_IPPort_CentIPR.get(port);
/* 427 */       Component component = (Component)IPRCent.getTargetInstance().getTargetPart();
/* 428 */       State InitialState = this.Map_Component_StateBefore.get(component);
/* 429 */       TransformationFunction.CreateTransition(pd, null, null, sport, InitialState, this.PetriNetIP);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateReceiveDataTransition() {
/* 438 */     this.ReceiveData = TransformationFunction.CreateState("StateReceiveData", this.PetriNetIP);
/* 439 */     for (Port o : this.Map_IPDataPort_CentIPR.keySet()) {
/* 440 */       Port dataPort = o;
/* 441 */       DefinitionBinding db = (DefinitionBinding)dataPort.getBinding();
/* 442 */       PortDefinition pd = db.getDefinition();
/* 443 */       TransformationFunction.CreateTransition(pd, null, null, this.ReceiveData, this.ReceiveData, this.PetriNetIP);
/* 444 */       this.PetriNetIP.getInitialState().add(this.ReceiveData);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateInitialOfferTransition() {
/* 452 */     for (Component o : this.Map_Component_OfferPortIP.keySet()) {
/* 453 */       Component component = o;
/* 454 */       Port offerPort = this.Map_Component_OfferPortIP.get(component);
/* 455 */       State s1 = TransformationFunction.CreateState("InitWaitOffer_" + component.getName(), this.PetriNetIP);
/* 456 */       State s2 = TransformationFunction.CreateState("S_RecOffer_" + component.getName(), this.PetriNetIP);
/* 457 */       DefinitionBinding db = (DefinitionBinding)offerPort.getBinding();
/* 458 */       PortDefinition pd = db.getDefinition();
/* 459 */       TransformationFunction.CreateTransition(pd, null, null, s1, s2, this.PetriNetIP);
/* 460 */       if (this.LConflictComponent.contains(component))
/* 461 */         TransformationFunction.CreateTransition(pd, null, null, s2, s2, this.PetriNetIP); 
/* 462 */       this.PetriNetIP.getInitialState().add(s1);
/* 463 */       this.Map_Component_StateBefore.put(component, s1);
/* 464 */       this.Map_Component_StateAfter.put(component, s2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLVariableIP() {
/* 472 */     for (Connector o : this.LInteraction) {
/* 473 */       List<Variable> LVariable = new LinkedList<Variable>();
/* 474 */       Connector connector = o;
/* 475 */       ConnectorType conntype = connector.getType();
/* 476 */       EList eList = conntype.getVariable();
/* 477 */       List<String> LVarName = TransformationFunction.getLVariableName((List)eList);
/* 478 */       List<String> LVariableNameIP = DList.ChangeNameAddString(LVarName, "_" + connector.getName());
/* 479 */       LVariable = TransformationFunction.CreateCopyVariables(LVariableNameIP, (List)eList);
/* 480 */       this.LVariableIP.add(LVariable);
/* 481 */       this.LVariableConnector.add(eList);
/* 482 */       this.AtomTypeIP.getVariable().addAll(LVariable);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setVariableComponentIP() {
/* 490 */     for (Component o : this.LComponent) {
/* 491 */       Component component = o;
/* 492 */       DAtomType dat = TOP.Map_Component_DAtomType.get(component);
/* 493 */       List<Variable> LVariableComponent = dat.getVariableForIP(this.IPName);
/* 494 */       List<String> LVariableName = TransformationFunction.getLVariableName(LVariableComponent);
/* 495 */       List<String> LVariableNameIP = DList.ChangeNameAddString(LVariableName, "_" + component.getName());
/* 496 */       List<Variable> LVariableCompIP = TransformationFunction.CreateCopyVariables(LVariableNameIP, LVariableComponent);
/* 497 */       this.LLVariableComponent.add(LVariableComponent);
/* 498 */       this.LLVariableCompIP.add(LVariableCompIP);
/* 499 */       this.AtomTypeIP.getVariable().addAll(LVariableCompIP);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMaps() {
/* 507 */     SetMapsOffer();
/* 508 */     setMapsCentIPR();
/* 509 */     setMap_Port_GuardVar();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMapsCentIPR() {
/* 518 */     for (InnerPortReference o : this.LCentIPR) {
/* 519 */       InnerPortReference CentIPR = o;
/* 520 */       InnerPortReference CentIPRParsed = TransformationFunction.ContainsInnerPortReference(this.Map_CentIPR_IPPort, CentIPR);
/*     */       
/* 522 */       if (CentIPRParsed != null) {
/*     */ 
/*     */         
/* 525 */         this.Map_CentIPR_IPPort.put(CentIPR, this.Map_CentIPR_IPPort.get(CentIPRParsed));
/* 526 */         this.Map_CentIPR_DistPort.put(CentIPR, this.Map_CentIPR_DistPort.get(CentIPRParsed));
/* 527 */         this.Map_CentIPR_DistDataPort.put(CentIPR, this.Map_CentIPR_DistDataPort.get(CentIPRParsed));
/* 528 */         this.Map_CentIPR_IPDataPort.put(CentIPR, this.Map_CentIPR_IPDataPort.get(CentIPRParsed));
/*     */         continue;
/*     */       } 
/* 531 */       Component component = (Component)CentIPR.getTargetInstance().getTargetPart();
/* 532 */       Port port = CentIPR.getTargetPort();
/* 533 */       String PortNameIP = String.valueOf(port.getName()) + "_" + component.getName();
/* 534 */       DAtomType dat = TOP.Map_Component_DAtomType.get(component);
/* 535 */       Port portdist = dat.getPortDist(port);
/* 536 */       int indexofComponent = this.LComponent.indexOf(component);
/* 537 */       List<Variable> LVarPortIP = TransformationFunction.getVarPortEng(portdist, this.LLVariableComponent.get(indexofComponent), this.LLVariableCompIP.get(indexofComponent));
/* 538 */       Port IP_Port = TransformationFunction.CreatePort(PortNameIP, portdist.getType(), this.AtomTypeIP, LVarPortIP);
/* 539 */       Port IP_DataPort = TransformationFunction.getCopy(IP_Port, String.valueOf(IP_Port.getName()) + "_Data");
/* 540 */       PortDefinition IP_PDDataPort = TransformationFunction.getPortDefinition(IP_DataPort);
/* 541 */       this.AtomTypeIP.getPortDefinition().add(IP_PDDataPort);
/* 542 */       this.AtomTypeIP.getPort().add(IP_DataPort);
/* 543 */       this.Map_CentIPR_IPPort.put(CentIPR, IP_Port);
/* 544 */       this.Map_IPPort_CentIPR.put(IP_Port, CentIPR);
/* 545 */       this.Map_IPDataPort_CentIPR.put(IP_DataPort, CentIPR);
/* 546 */       this.Map_CentIPR_DistPort.put(CentIPR, portdist);
/* 547 */       this.Map_CentIPR_DistDataPort.put(CentIPR, dat.getDataPort(this.IPName, portdist));
/* 548 */       this.Map_IPPort_DistPort.put(IP_Port, portdist);
/* 549 */       this.Map_CentIPR_IPDataPort.put(CentIPR, IP_DataPort);
/* 550 */       this.LPort.add(portdist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetMapsOffer() {
/* 560 */     for (Component o : this.LComponent) {
/* 561 */       Component component = o;
/* 562 */       DAtomType dat = TOP.Map_Component_DAtomType.get(component);
/* 563 */       Port OfferPort = dat.getOfferPort(this.IPName);
/*     */ 
/*     */       
/* 566 */       DefinitionBinding DB = (DefinitionBinding)OfferPort.getBinding();
/* 567 */       PortDefinition PD = DB.getDefinition();
/* 568 */       List<String> LVariableName = TransformationFunction.getLVariableName((List)PD.getExposedVariable());
/* 569 */       List<String> LVariableNameIP = DList.ChangeNameAddString(LVariableName, "_" + component.getName());
/* 570 */       List<Variable> LVariableIP = TransformationFunction.CreateCopyVariables(LVariableNameIP, (List)PD.getExposedVariable());
/* 571 */       Variable N_Variable = LVariableIP.get(0);
/* 572 */       this.Map_Component_Nvar.put(component, N_Variable);
/* 573 */       this.AtomTypeIP.getVariable().addAll(LVariableIP);
/* 574 */       this.LLVariableOfferVar.add(LVariableIP);
/*     */ 
/*     */       
/* 577 */       Port OfferPortIP = TransformationFunction.CreatePort("Offer_" + component.getName(), OfferPort.getType(), this.AtomTypeIP, LVariableIP);
/* 578 */       this.Map_Component_OfferPortIP.put(component, OfferPortIP);
/* 579 */       this.Map_Component_OfferPortComp.put(component, OfferPort);
/* 580 */       this.Map_OfferPortIP_OfferPortComp.put(OfferPort, OfferPortIP);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Port_GuardVar() {
/* 589 */     for (InnerPortReference o : this.Map_CentIPR_IPPort.keySet()) {
/* 590 */       InnerPortReference IPR = o;
/* 591 */       Component component = (Component)IPR.getTargetInstance().getTargetPart();
/* 592 */       DAtomType dat = TOP.Map_Component_DAtomType.get(component);
/* 593 */       Variable vdist = dat.getGuardVar(this.Map_CentIPR_DistPort.get(IPR));
/* 594 */       Port Offerdist = this.Map_Component_OfferPortComp.get(component);
/* 595 */       DefinitionBinding db = (DefinitionBinding)Offerdist.getBinding();
/* 596 */       int indexVariableDist = db.getDefinition().getExposedVariable().indexOf(vdist);
/* 597 */       int indexComponent = this.LComponent.indexOf(component);
/* 598 */       Variable vIP = ((List<Variable>)this.LLVariableOfferVar.get(indexComponent)).get(indexVariableDist);
/* 599 */       this.Map_Port_GuardVar.put(this.Map_CentIPR_IPPort.get(IPR), vIP);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType getAtomTypeIP() {
/* 606 */     return this.AtomTypeIP;
/*     */   }
/*     */   
/*     */   public void setComponentInstance(CompoundType compoundType) {
/* 610 */     this.ComponentIPInstance = TransformationFunction.CreateComponent(String.valueOf(this.AtomTypeIP.getName()) + "_Instance", (ComponentType)this.AtomTypeIP, compoundType, null);
/*     */   }
/*     */   
/*     */   public Component getComponentIPInstance() {
/* 614 */     return this.ComponentIPInstance;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\InteractionProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */