/*     */ package unifiedDistributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ public class DiningPhiloProtocol
/*     */   extends ReservationProtocol
/*     */ {
/*     */   private Connector InteractionToReserve;
/*  29 */   public List<Connector> LConflictInteraction = new LinkedList<Connector>();
/*  30 */   public List<Connector> SetLConflictInteraction = new LinkedList<Connector>();
/*  31 */   private List<List<Component>> LLComponentToReserve = new LinkedList<List<Component>>();
/*  32 */   public Map<Connector, Port> Map_Interaction_PortSendFork = new HashMap<Connector, Port>();
/*  33 */   public Map<Connector, Port> Map_Interaction_PortRecFork = new HashMap<Connector, Port>();
/*  34 */   public Map<Connector, Port> Map_Interaction_PortSendReq = new HashMap<Connector, Port>();
/*  35 */   public Map<Connector, Port> Map_Interaction_PortRecReq = new HashMap<Connector, Port>();
/*  36 */   private Map<Connector, Variable> Map_Interaction_VarReqFork = new HashMap<Connector, Variable>();
/*  37 */   private Map<Connector, Variable> Map_Interaction_VarHasFork = new HashMap<Connector, Variable>();
/*  38 */   private Map<Connector, Variable> Map_Interaction_VarDirtyFork = new HashMap<Connector, Variable>();
/*     */   
/*  40 */   private Map<Connector, State> Map_Interaction_StateWaitFork = new HashMap<Connector, State>();
/*  41 */   private Map<Connector, State> Map_Interaction_StateHasFork = new HashMap<Connector, State>();
/*  42 */   private Map<Connector, Variable> Map_Connector_IndexCompFail = new HashMap<Connector, Variable>();
/*  43 */   protected Map<Component, Variable> Map_Comp_NVarTMP = new HashMap<Component, Variable>();
/*     */   
/*     */   private State Idle;
/*     */   private State IdleTMP;
/*     */   private State S_Fail_TMP;
/*     */   private Variable IndexCompFail;
/*     */   
/*     */   public DiningPhiloProtocol(List<Connector> LInteraction, String IPName) {
/*  51 */     super(LInteraction, IPName);
/*  52 */     this.InteractionToReserve = LInteraction.get(0);
/*  53 */     this.IndexCompFail = this.Map_Conn_IndexCompFail.get(this.InteractionToReserve);
/*  54 */     setLConflictInteraction();
/*  55 */     setMap_Connector_IndexCompFail();
/*  56 */     setLLComponentToReserve();
/*  57 */     setMaps();
/*  58 */     setBehavior();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Connector_IndexCompFail() {
/*  64 */     for (Connector o : this.LConflictInteraction) {
/*  65 */       Connector connector = o;
/*  66 */       Variable IndexCompFail = TransformationFunction.CreateIntVariable("IndexCompFailTMP_" + connector.getName(), -1);
/*  67 */       this.Map_Connector_IndexCompFail.put(connector, IndexCompFail);
/*  68 */       this.AtomTypeRP.getVariable().add(IndexCompFail);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLConflictInteraction() {
/*  74 */     this.LConflictInteraction = TOP.compoundTypeInfo.getConnectorDirectConflict(this.InteractionToReserve);
/*  75 */     List<Connector> LGroupInteractionProtocol = TOP.getGroupInteraction(this.InteractionToReserve);
/*  76 */     this.LConflictInteraction.removeAll(LGroupInteractionProtocol);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLLComponentToReserve() {
/*  81 */     for (Connector o : this.LConflictInteraction) {
/*  82 */       Connector connector = o;
/*  83 */       this.LLComponentToReserve.add(TOP.compoundTypeInfo.getIntersectConflictComponent(this.InteractionToReserve, connector));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Comp_NVarTMP() {
/*  92 */     for (Object<Component> o : this.LLComponentReserve) {
/*  93 */       List<Component> LComponent = (List<Component>)o;
/*  94 */       for (Component o1 : LComponent) {
/*  95 */         Component component = o1;
/*  96 */         if (!this.Map_Comp_NVarTMP.containsKey(component)) {
/*  97 */           Variable N_Var = TransformationFunction.CreateIntVariable("N_TMP_" + component.getName(), 0);
/*  98 */           this.Map_Comp_NVarTMP.put(component, N_Var);
/*  99 */           this.AtomTypeRP.getVariable().add(N_Var);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMaps() {
/* 107 */     setMap_Comp_NVarTMP();
/* 108 */     for (Connector o : this.LConflictInteraction) {
/* 109 */       Connector connector = o;
/* 110 */       List<Component> LComponentToReserve = this.LLComponentToReserve.get(this.LConflictInteraction.indexOf(o));
/* 111 */       List<Variable> LVarCorresondToComponent = getN_TMPVariableToComponent(LComponentToReserve);
/* 112 */       setMap_Interaction_PortSRFork(connector, LVarCorresondToComponent);
/* 113 */       setMap_Interaction_PortSRRequest(connector);
/* 114 */       setMap_Interaction_VarReqFork(connector);
/* 115 */       setMap_Interaction_VarHasFork(connector);
/* 116 */       setMap_Interaction_VarDirtyFork(connector);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private FunctionCallExpression getUpdateForkNValuesReceive(Connector connector) {
/* 123 */     List<Component> LComponentToReserve = this.LLComponentToReserve.get(this.LConflictInteraction.indexOf(connector));
/* 124 */     int size = LComponentToReserve.size();
/* 125 */     IntegerLiteral ILnArgsUpdateFork = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(size));
/* 126 */     List<IntegerLiteral> ActualDataUpdateFork = new LinkedList();
/* 127 */     ActualDataUpdateFork.add(ILnArgsUpdateFork);
/* 128 */     for (Component o : LComponentToReserve) {
/* 129 */       Component component = o;
/* 130 */       Variable NVarTMP = this.Map_Comp_NVarTMP.get(component);
/* 131 */       Variable NVar = this.Map_Comp_NVar.get(component);
/* 132 */       UnaryExpression NVarTMPRef = TransformationFunction.CreateUnaryExpressionReference(NVarTMP);
/* 133 */       UnaryExpression NVarRef = TransformationFunction.CreateUnaryExpressionReference(NVar);
/* 134 */       ActualDataUpdateFork.add(NVarRef);
/* 135 */       ActualDataUpdateFork.add(NVarTMPRef);
/*     */     } 
/* 137 */     FunctionCallExpression FCE = TransformationFunction.CreateFunction(TOP.FunctionGetUpdateForkReceive, ActualDataUpdateFork);
/* 138 */     return FCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private FunctionCallExpression getUpdateForkNValuesSend(List<Component> LComponent) {
/* 144 */     int size = LComponent.size();
/* 145 */     IntegerLiteral ILnArgsUpdateFork = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(size));
/* 146 */     List<IntegerLiteral> ActualDataUpdateFork = new LinkedList();
/* 147 */     ActualDataUpdateFork.add(ILnArgsUpdateFork);
/* 148 */     for (Component o : LComponent) {
/* 149 */       Component component = o;
/* 150 */       Variable NVarTMP = this.Map_Comp_NVarTMP.get(component);
/* 151 */       Variable NVar = this.Map_Comp_NVar.get(component);
/* 152 */       UnaryExpression NVarTMPRef = TransformationFunction.CreateUnaryExpressionReference(NVarTMP);
/* 153 */       VariableReference NVarRef = TransformationFunction.CreateVariableReference(NVar);
/* 154 */       ActualDataUpdateFork.add(NVarRef);
/* 155 */       ActualDataUpdateFork.add(NVarTMPRef);
/*     */     } 
/* 157 */     FunctionCallExpression FCE = TransformationFunction.CreateFunction(TOP.FunctionGetUpdateForkSend, ActualDataUpdateFork);
/* 158 */     return FCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Variable> getN_TMPVariableToComponent(List<Component> LComponent) {
/* 169 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 170 */     for (Component o : LComponent) {
/* 171 */       Component component = o;
/* 172 */       if (this.Map_Comp_NVarTMP.containsKey(component)) {
/* 173 */         LVariable.add(this.Map_Comp_NVarTMP.get(component));
/*     */       }
/*     */     } 
/* 176 */     return LVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Interaction_VarDirtyFork(Connector connector) {
/* 181 */     Variable VarDirtyFork = TransformationFunction.CreateGuardVariable("DirtyFork_" + connector.getName(), true);
/* 182 */     this.Map_Interaction_VarDirtyFork.put(connector, VarDirtyFork);
/* 183 */     this.AtomTypeRP.getVariable().add(VarDirtyFork);
/*     */   }
/*     */   
/*     */   private void setMap_Interaction_VarHasFork(Connector connector) {
/* 187 */     Variable VarHasFork = TransformationFunction.CreateGuardVariable("Fork_" + connector.getName(), false);
/* 188 */     this.Map_Interaction_VarHasFork.put(connector, VarHasFork);
/* 189 */     this.AtomTypeRP.getVariable().add(VarHasFork);
/*     */   }
/*     */   
/*     */   private void setMap_Interaction_VarReqFork(Connector connector) {
/* 193 */     Variable VarReqFork = TransformationFunction.CreateGuardVariable("ReqFork_" + connector.getName(), true);
/* 194 */     this.Map_Interaction_VarReqFork.put(connector, VarReqFork);
/* 195 */     this.AtomTypeRP.getVariable().add(VarReqFork);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMap_Interaction_PortSRFork(Connector connector, List<Variable> LVarCorresondToComponent) {
/* 200 */     PortType SRForkPortType = TOP.getReservePortType(Integer.valueOf(LVarCorresondToComponent.size()));
/* 201 */     Port SendForkPort = TransformationFunction.CreatePort("SendFork" + connector.getName(), SRForkPortType, this.AtomTypeRP, LVarCorresondToComponent);
/* 202 */     Port ReceiveForkPort = TransformationFunction.CreatePort("ReceiveFork" + connector.getName(), SRForkPortType, this.AtomTypeRP, LVarCorresondToComponent);
/* 203 */     this.Map_Interaction_PortSendFork.put(connector, SendForkPort);
/* 204 */     this.Map_Interaction_PortRecFork.put(connector, ReceiveForkPort);
/* 205 */     this.AtomTypeRP.getPort().add(ReceiveForkPort);
/* 206 */     this.AtomTypeRP.getPort().add(SendForkPort);
/* 207 */     this.AtomTypeRP.getPortDefinition().add(TransformationFunction.getPortDefinition(SendForkPort));
/* 208 */     this.AtomTypeRP.getPortDefinition().add(TransformationFunction.getPortDefinition(ReceiveForkPort));
/*     */   }
/*     */   
/*     */   private void setMap_Interaction_PortSRRequest(Connector connector) {
/* 212 */     PortType InternPortType = TOP.getInternPortType();
/* 213 */     Port SendReqPort = TransformationFunction.CreatePort("SendReq" + connector.getName(), InternPortType, this.AtomTypeRP);
/* 214 */     Port ReceiveReqPort = TransformationFunction.CreatePort("ReceiveReq" + connector.getName(), InternPortType, this.AtomTypeRP);
/* 215 */     this.Map_Interaction_PortSendReq.put(connector, SendReqPort);
/* 216 */     this.Map_Interaction_PortRecReq.put(connector, ReceiveReqPort);
/* 217 */     this.AtomTypeRP.getPort().add(ReceiveReqPort);
/* 218 */     this.AtomTypeRP.getPort().add(SendReqPort);
/* 219 */     this.AtomTypeRP.getPortDefinition().add(TransformationFunction.getPortDefinition(SendReqPort));
/* 220 */     this.AtomTypeRP.getPortDefinition().add(TransformationFunction.getPortDefinition(ReceiveReqPort));
/*     */   }
/*     */   
/*     */   private void setBehavior() {
/* 224 */     setStates();
/* 225 */     setTransitions();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTransitions() {
/* 230 */     List<State> LStateWait = new LinkedList<State>();
/* 231 */     List<State> LStateHasFork = new LinkedList<State>();
/* 232 */     List<Action> LActionOKTransition = new LinkedList<Action>();
/* 233 */     Expression GuardOKTransition = null;
/* 234 */     List<Action> LActionFAILTransition = new LinkedList<Action>();
/* 235 */     List<Action> LActionReserveTransition = new LinkedList<Action>();
/*     */     
/* 237 */     Expression GuardFAILTransition = null;
/* 238 */     List<Component> LComponentToReserveParse = new LinkedList<Component>();
/*     */ 
/*     */     
/* 241 */     for (Connector o : this.LConflictInteraction) {
/* 242 */       Connector connector = o;
/* 243 */       BinaryExpression binaryExpression1 = TransformationFunction.CreateGuardEqual(this.Map_Connector_IndexCompFail.get(connector), Integer.valueOf(-1), true);
/* 244 */       BinaryExpression binaryExpression2 = TransformationFunction.CreateGuardEqual(this.Map_Connector_IndexCompFail.get(connector), Integer.valueOf(-1), false);
/* 245 */       GuardOKTransition = TransformationFunction.AndOfTwoExpression((Expression)binaryExpression1, GuardOKTransition);
/* 246 */       GuardFAILTransition = TransformationFunction.OROfTwoExpression((Expression)binaryExpression2, GuardFAILTransition);
/*     */       
/* 248 */       State StateWait = this.Map_Interaction_StateWaitFork.get(connector);
/* 249 */       State StateHasFork = this.Map_Interaction_StateHasFork.get(connector);
/* 250 */       LStateWait.add(StateWait);
/* 251 */       LStateHasFork.add(StateHasFork);
/*     */       
/* 253 */       List<Component> LComponentToReserve = this.LLComponentToReserve.get(this.LConflictInteraction.indexOf(o));
/* 254 */       List<IntegerLiteral> ActualDataGetCompFail = new LinkedList();
/* 255 */       List<IntegerLiteral> ActualDataUpdateFork = new LinkedList();
/* 256 */       int size = LComponentToReserve.size();
/* 257 */       IntegerLiteral ILnArgsGetCompFail = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(size));
/* 258 */       ActualDataGetCompFail.add(ILnArgsGetCompFail);
/*     */       
/* 260 */       IntegerLiteral ILnArgsUpdateFork = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(size));
/* 261 */       ActualDataUpdateFork.add(ILnArgsUpdateFork);
/*     */       
/* 263 */       setActualDataForFunction(connector, LComponentToReserve, LComponentToReserveParse, ActualDataGetCompFail, ActualDataUpdateFork, LActionOKTransition, LActionFAILTransition);
/* 264 */       LActionOKTransition.add(TransformationFunction.CreateAssignmentActionBoolVar(this.Map_Interaction_VarDirtyFork.get(connector), true));
/* 265 */       LActionFAILTransition.add(TransformationFunction.CreateAssignmentActionBoolVar(this.Map_Interaction_VarDirtyFork.get(connector), true));
/*     */ 
/*     */       
/* 268 */       setTransitionRecReq(connector, this.Idle, this.Idle);
/* 269 */       setTransitionRecReq(connector, StateWait, StateWait);
/* 270 */       setTransitionRecReq(connector, StateHasFork, StateHasFork);
/* 271 */       setSendForkFromIdleToIdleTransition(connector);
/* 272 */       setSendRequestTransition(connector);
/*     */       
/* 274 */       AssignmentAction AAgetCompFail = TransformationFunction.CreateVarFunctionAssignmentAction(this.Map_Connector_IndexCompFail.get(connector), TOP.FunctionGetCompFail_res, ActualDataGetCompFail);
/* 275 */       setInternFromWaitToForkTransition(connector, (AssignmentAction)TransformationFunction.getCopy(AAgetCompFail));
/* 276 */       setRecForkFromWaitToForkTransition(connector, (AssignmentAction)TransformationFunction.getCopy(AAgetCompFail));
/* 277 */       setSendForkFromForkToWaitTransition(connector);
/*     */       
/* 279 */       LActionReserveTransition.add(AAgetCompFail);
/*     */     } 
/* 281 */     AssignmentAction AAGetWhichFail = CreateActionGetWhichFail();
/* 282 */     LActionReserveTransition.add(AAGetWhichFail);
/* 283 */     LActionFAILTransition.add(TransformationFunction.getCopy(AAGetWhichFail));
/*     */     
/* 285 */     setTransitionFail(LStateHasFork, LActionFAILTransition, GuardFAILTransition);
/* 286 */     setTransitionOk(LStateHasFork, LActionOKTransition, GuardOKTransition, LComponentToReserveParse);
/* 287 */     setReserveTransition(LStateWait, LActionReserveTransition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setActualDataForFunction(Connector connector, List<Component> LComponentToReserve, List<Component> LComponentToReserveParse, List<VariableReference> ActualDataGetCompFail, List<VariableReference> ActualDataUpdateFork, List<Action> LActionOKTransition, List<Action> LActionFailTransition) {
/* 293 */     for (Component o1 : LComponentToReserve) {
/* 294 */       Component component = o1;
/* 295 */       Variable nVar = getn_Var(this.InteractionToReserve, component);
/* 296 */       Variable NVar = this.Map_Comp_NVar.get(component);
/*     */       
/* 298 */       Integer ComponentID = TOP.getComponentID(component);
/* 299 */       VariableReference nVarRef = TransformationFunction.CreateVariableReference(nVar);
/* 300 */       VariableReference NVarRef = TransformationFunction.CreateVariableReference(NVar);
/* 301 */       IntegerLiteral IL = TransformationFunction.CreateIntegerLiteral(ComponentID);
/* 302 */       ActualDataGetCompFail.add(nVarRef);
/* 303 */       ActualDataGetCompFail.add(NVarRef);
/* 304 */       ActualDataGetCompFail.add(IL);
/* 305 */       VariableReference nVarRef2 = TransformationFunction.CreateVariableReference(nVar);
/* 306 */       UnaryExpression unaryExpression = TransformationFunction.CreateUnaryExpressionReference(NVar);
/* 307 */       ActualDataUpdateFork.add(nVarRef2);
/* 308 */       ActualDataUpdateFork.add(unaryExpression);
/* 309 */       if (!LComponentToReserveParse.contains(component)) {
/* 310 */         LActionOKTransition.add(TransformationFunction.CreateAssignmentAction(NVar, nVar));
/* 311 */         LComponentToReserveParse.add(component);
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
/*     */   private void setTransitionRecReq(Connector connector, State from, State to) {
/* 323 */     Variable ReqFork = this.Map_Interaction_VarReqFork.get(connector);
/* 324 */     Expression GuardRecReq = TransformationFunction.getInverseGuard(ReqFork);
/* 325 */     AssignmentAction AARecReq = TransformationFunction.CreateAssignmentActionBoolVar(ReqFork, true);
/* 326 */     PortDefinition PDRecReq = TransformationFunction.getPortDefinition(this.Map_Interaction_PortRecReq.get(connector));
/* 327 */     TransformationFunction.CreateTransition(PDRecReq, GuardRecReq, (Action)AARecReq, from, to, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTransitionFail(List<State> LStateHasFork, List<Action> LActionFAILTransition, Expression GuardFAILTransition) {
/* 335 */     CompositeAction ActionFAIL = TransformationFunction.CreateCompositeAction(LActionFAILTransition);
/* 336 */     List<State> LStateFailTMP = new LinkedList<State>();
/* 337 */     LStateFailTMP.add(this.S_Fail_TMP);
/*     */     
/* 339 */     TransformationFunction.CreateTransitionLState(this.InterPort, GuardFAILTransition, (Action)ActionFAIL, LStateHasFork, LStateFailTMP, this.PetriNetRP);
/*     */     
/* 341 */     PortDefinition PDFail = TransformationFunction.getPortDefinition(this.Map_Conn_PortFail.get(this.InteractionToReserve));
/* 342 */     TransformationFunction.CreateTransition(PDFail, null, null, this.S_Fail_TMP, this.Idle, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTransitionOk(List<State> LStateHasFork, List<Action> LActionOKTransition, Expression GuardOKTransition, List<Component> LComponent) {
/* 352 */     FunctionCallExpression FCE = getUpdateForkNValuesSend(LComponent);
/* 353 */     LActionOKTransition.add(FCE);
/* 354 */     CompositeAction ActionOK = TransformationFunction.CreateCompositeAction(LActionOKTransition);
/* 355 */     List<State> LStateIdle = new LinkedList<State>();
/* 356 */     LStateIdle.add(this.Idle);
/* 357 */     PortDefinition PDOK = TransformationFunction.getPortDefinition(this.Map_Conn_PortOk.get(this.InteractionToReserve));
/* 358 */     TransformationFunction.CreateTransitionLState(PDOK, GuardOKTransition, (Action)ActionOK, LStateHasFork, LStateIdle, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSendForkFromIdleToIdleTransition(Connector connector) {
/* 366 */     Variable ReqFork = this.Map_Interaction_VarReqFork.get(connector);
/* 367 */     Variable HasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 368 */     Variable DirtyFork = this.Map_Interaction_VarDirtyFork.get(connector);
/* 369 */     List<Variable> LBoolVar = new LinkedList<Variable>();
/* 370 */     LBoolVar.add(ReqFork);
/* 371 */     LBoolVar.add(HasFork);
/* 372 */     LBoolVar.add(DirtyFork);
/* 373 */     Expression GuardSFIdleToIdle = TransformationFunction.CreateAndExpression(LBoolVar);
/* 374 */     AssignmentAction assignmentAction = TransformationFunction.CreateAssignmentActionBoolVar(this.Map_Interaction_VarHasFork.get(connector), false);
/* 375 */     List<Action> LAction = new LinkedList<Action>();
/* 376 */     LAction.add(assignmentAction);
/* 377 */     CompositeAction CALossFork = TransformationFunction.CreateCompositeAction(LAction);
/* 378 */     PortDefinition PDSendFork = TransformationFunction.getPortDefinition(this.Map_Interaction_PortSendFork.get(connector));
/* 379 */     TransformationFunction.CreateTransition(PDSendFork, GuardSFIdleToIdle, (Action)CALossFork, this.Idle, this.Idle, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setReserveTransition(List<State> LStateWait, List<Action> LActionReserveTransition) {
/* 385 */     CompositeAction ActionReserve = TransformationFunction.CreateCompositeAction(LActionReserveTransition);
/* 386 */     Port PortReserve = this.Map_Conn_Reserve.get(this.InteractionToReserve);
/* 387 */     PortDefinition PDReserve = TransformationFunction.getPortDefinition(PortReserve);
/* 388 */     TransformationFunction.CreateTransition(PDReserve, null, (Action)ActionReserve, this.Idle, this.IdleTMP, this.PetriNetRP);
/*     */ 
/*     */ 
/*     */     
/* 392 */     BinaryExpression binaryExpression1 = TransformationFunction.CreateGuardEqual(this.IndexCompFail, Integer.valueOf(-1), false);
/* 393 */     PortDefinition PDFail = TransformationFunction.getPortDefinition(this.Map_Conn_PortFail.get(this.InteractionToReserve));
/* 394 */     TransformationFunction.CreateTransition(PDFail, (Expression)binaryExpression1, null, this.IdleTMP, this.Idle, this.PetriNetRP);
/*     */     
/* 396 */     List<State> LStateIdleTMP = new LinkedList<State>();
/* 397 */     LStateIdleTMP.add(this.IdleTMP);
/* 398 */     BinaryExpression binaryExpression2 = TransformationFunction.CreateGuardEqual(this.IndexCompFail, Integer.valueOf(-1), true);
/* 399 */     TransformationFunction.CreateTransitionLState(this.InterPort, (Expression)binaryExpression2, null, LStateIdleTMP, LStateWait, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSendRequestTransition(Connector connector) {
/* 404 */     Variable ReqFork = this.Map_Interaction_VarReqFork.get(connector);
/* 405 */     Variable HasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 406 */     Expression NotHasFork = TransformationFunction.getInverseGuard(HasFork);
/* 407 */     VariableReference variableReference = TransformationFunction.CreateVariableReference(ReqFork);
/* 408 */     Expression GuardSendRequest = TransformationFunction.AndOfTwoExpression((Expression)variableReference, NotHasFork);
/* 409 */     AssignmentAction assignmentAction = TransformationFunction.CreateAssignmentActionBoolVar(this.Map_Interaction_VarReqFork.get(connector), false);
/* 410 */     PortDefinition PDSendReq = TransformationFunction.getPortDefinition(this.Map_Interaction_PortSendReq.get(connector));
/* 411 */     State StateWait = this.Map_Interaction_StateWaitFork.get(connector);
/* 412 */     TransformationFunction.CreateTransition(PDSendReq, GuardSendRequest, (Action)assignmentAction, StateWait, StateWait, this.PetriNetRP);
/*     */   }
/*     */   
/*     */   private void setInternFromWaitToForkTransition(Connector connector, AssignmentAction getCompFail) {
/* 416 */     List<Action> LActionIndexCompFail = new LinkedList<Action>();
/* 417 */     LActionIndexCompFail.add(getCompFail);
/* 418 */     CompositeAction CAIntern = TransformationFunction.CreateCompositeAction(LActionIndexCompFail);
/* 419 */     Variable HasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 420 */     VariableReference variableReference = TransformationFunction.CreateVariableReference(HasFork);
/* 421 */     State StateWait = this.Map_Interaction_StateWaitFork.get(connector);
/* 422 */     State StateFork = this.Map_Interaction_StateHasFork.get(connector);
/* 423 */     TransformationFunction.CreateTransition(this.InterPort, (Expression)variableReference, (Action)CAIntern, StateWait, StateFork, this.PetriNetRP);
/*     */   }
/*     */   
/*     */   private void setRecForkFromWaitToForkTransition(Connector connector, AssignmentAction getCompFail) {
/* 427 */     Variable VarHasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 428 */     Variable VarDirtyFork = this.Map_Interaction_VarDirtyFork.get(connector);
/*     */     
/* 430 */     AssignmentAction assignmentAction1 = TransformationFunction.CreateAssignmentActionBoolVar(VarHasFork, true);
/* 431 */     AssignmentAction assignmentAction2 = TransformationFunction.CreateAssignmentActionBoolVar(VarDirtyFork, false);
/* 432 */     List<Action> LAction = new LinkedList<Action>();
/* 433 */     LAction.add(assignmentAction2);
/* 434 */     LAction.add(assignmentAction1);
/* 435 */     FunctionCallExpression FCEUpdateReceiveFork = getUpdateForkNValuesReceive(connector);
/* 436 */     LAction.add(FCEUpdateReceiveFork);
/* 437 */     LAction.add(getCompFail);
/* 438 */     CompositeAction ActionRecFork = TransformationFunction.CreateCompositeAction(LAction);
/*     */     
/* 440 */     Expression NotHasFork = TransformationFunction.getInverseGuard(VarHasFork);
/*     */ 
/*     */     
/* 443 */     State StateWait = this.Map_Interaction_StateWaitFork.get(connector);
/* 444 */     State StateFork = this.Map_Interaction_StateHasFork.get(connector);
/*     */ 
/*     */     
/* 447 */     PortDefinition PDRecFork = TransformationFunction.getPortDefinition(this.Map_Interaction_PortRecFork.get(connector));
/*     */     
/* 449 */     TransformationFunction.CreateTransition(PDRecFork, NotHasFork, (Action)ActionRecFork, StateWait, StateFork, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSendForkFromForkToWaitTransition(Connector connector) {
/* 455 */     Variable ReqFork = this.Map_Interaction_VarReqFork.get(connector);
/* 456 */     Variable HasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 457 */     Variable DirtyFork = this.Map_Interaction_VarDirtyFork.get(connector);
/* 458 */     List<Variable> LBoolVar = new LinkedList<Variable>();
/* 459 */     LBoolVar.add(ReqFork);
/* 460 */     LBoolVar.add(HasFork);
/* 461 */     LBoolVar.add(DirtyFork);
/* 462 */     Expression GuardSFFromForkToWait = TransformationFunction.CreateAndExpression(LBoolVar);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 471 */     AssignmentAction assignmentAction = TransformationFunction.CreateAssignmentActionBoolVar(this.Map_Interaction_VarHasFork.get(connector), false);
/* 472 */     List<Action> LAction = new LinkedList<Action>();
/* 473 */     LAction.add(assignmentAction);
/* 474 */     CompositeAction CALossFork = TransformationFunction.CreateCompositeAction(LAction);
/*     */ 
/*     */     
/* 477 */     State StateWait = this.Map_Interaction_StateWaitFork.get(connector);
/* 478 */     State StateFork = this.Map_Interaction_StateHasFork.get(connector);
/*     */     
/* 480 */     PortDefinition PDSendFork = TransformationFunction.getPortDefinition(this.Map_Interaction_PortSendFork.get(connector));
/*     */     
/* 482 */     TransformationFunction.CreateTransition(PDSendFork, GuardSFFromForkToWait, (Action)CALossFork, StateFork, StateWait, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AssignmentAction CreateActionGetWhichFail() {
/* 492 */     int size = this.LConflictInteraction.size();
/* 493 */     List<IntegerLiteral> ActualDataWhichFail = new LinkedList();
/* 494 */     IntegerLiteral ILnArgsGetCompFail = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(size));
/* 495 */     ActualDataWhichFail.add(ILnArgsGetCompFail);
/* 496 */     for (Connector o : this.Map_Connector_IndexCompFail.keySet()) {
/* 497 */       Connector connector = o;
/* 498 */       Variable VarIndexCompFail = this.Map_Connector_IndexCompFail.get(connector);
/* 499 */       VariableReference VarRef = TransformationFunction.CreateVariableReference(VarIndexCompFail);
/* 500 */       ActualDataWhichFail.add(VarRef);
/*     */     } 
/* 502 */     return TransformationFunction.CreateVarFunctionAssignmentAction(this.IndexCompFail, TOP.FunctionGetWhichFail, ActualDataWhichFail);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setStates() {
/* 510 */     this.Idle = TransformationFunction.CreateState("Idle", this.PetriNetRP);
/* 511 */     this.IdleTMP = TransformationFunction.CreateState("IdleTMP", this.PetriNetRP);
/* 512 */     this.S_Fail_TMP = TransformationFunction.CreateState("S_Fail_TMP", this.PetriNetRP);
/* 513 */     this.PetriNetRP.getInitialState().add(this.Idle);
/* 514 */     for (Connector o : this.LConflictInteraction) {
/* 515 */       Connector connector = o;
/* 516 */       State WaitFork = TransformationFunction.CreateState("WaitFork" + connector.getName(), this.PetriNetRP);
/* 517 */       State HasFork = TransformationFunction.CreateState("HasFork" + connector.getName(), this.PetriNetRP);
/* 518 */       this.Map_Interaction_StateWaitFork.put(connector, WaitFork);
/* 519 */       this.Map_Interaction_StateHasFork.put(connector, HasFork);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInititialHasFork(Connector connector) {
/* 524 */     Variable VarHasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 525 */     TransformationFunction.SetInitialValueBoolVar(VarHasFork, true);
/* 526 */     Variable VarHasReq = this.Map_Interaction_VarReqFork.get(connector);
/* 527 */     TransformationFunction.SetInitialValueBoolVar(VarHasReq, false);
/*     */   }
/*     */   
/*     */   public void setInititialNotHasFork(Connector connector) {
/* 531 */     Variable VarHasFork = this.Map_Interaction_VarHasFork.get(connector);
/* 532 */     TransformationFunction.SetInitialValueBoolVar(VarHasFork, false);
/* 533 */     Variable VarHasReq = this.Map_Interaction_VarReqFork.get(connector);
/* 534 */     TransformationFunction.SetInitialValueBoolVar(VarHasReq, true);
/*     */   }
/*     */   
/*     */   public Connector getInteractionOfRP() {
/* 538 */     return this.InteractionToReserve;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\DiningPhiloProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */