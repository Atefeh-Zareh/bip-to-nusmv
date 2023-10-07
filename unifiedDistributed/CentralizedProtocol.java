/*     */ package unifiedDistributed;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ public class CentralizedProtocol extends ReservationProtocol {
/*  22 */   private Map<Connector, State> Map_Conn_InitialState = new HashMap<Connector, State>();
/*  23 */   private Map<Connector, State> Map_Conn_State = new HashMap<Connector, State>();
/*     */   public CentralizedProtocol(List<Connector> LInteraction, String IPName) {
/*  25 */     super(LInteraction, IPName);
/*  26 */     CreateStates();
/*  27 */     SetBehavior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void CreateStates() {
/*  32 */     for (Connector o : this.LInteraction) {
/*  33 */       Connector connector = o;
/*  34 */       State StateInitConn = TransformationFunction.CreateState("Idle_" + connector.getName(), this.PetriNetRP);
/*  35 */       State StateConn = TransformationFunction.CreateState("Rec_" + connector.getName(), this.PetriNetRP);
/*  36 */       this.Map_Conn_State.put(connector, StateConn);
/*  37 */       this.Map_Conn_InitialState.put(connector, StateInitConn);
/*  38 */       this.PetriNetRP.getInitialState().add(StateInitConn);
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
/*     */   private void SetBehavior() {
/*  51 */     for (Connector o : this.Map_Conn_State.keySet()) {
/*  52 */       Connector connector = o;
/*  53 */       State state = this.Map_Conn_State.get(connector);
/*  54 */       Port portReserve = this.Map_Conn_Reserve.get(connector);
/*  55 */       PortDefinition portdefReserve = TransformationFunction.getPortDefinition(portReserve);
/*     */       
/*  57 */       List<IntegerLiteral> ActualData = new LinkedList();
/*  58 */       List<Component> LComponentToReserve = getComponentToReserve(connector);
/*  59 */       IntegerLiteral ILnArgs = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(LComponentToReserve.size()));
/*  60 */       ActualData.add(ILnArgs);
/*     */       
/*  62 */       List<Action> LActionOKTransition = new LinkedList<Action>();
/*     */       
/*  64 */       for (Component o1 : LComponentToReserve) {
/*  65 */         Component component = o1;
/*  66 */         Variable nVar = getn_Var(connector, component);
/*  67 */         Variable NVar = this.Map_Comp_NVar.get(component);
/*  68 */         Integer ComponentID = TOP.getComponentID(component);
/*  69 */         VariableReference nVarRef = TransformationFunction.CreateVariableReference(nVar);
/*  70 */         VariableReference NVarRef = TransformationFunction.CreateVariableReference(NVar);
/*  71 */         IntegerLiteral IL = TransformationFunction.CreateIntegerLiteral(ComponentID);
/*  72 */         ActualData.add(nVarRef);
/*  73 */         ActualData.add(NVarRef);
/*  74 */         ActualData.add(IL);
/*     */         
/*  76 */         LActionOKTransition.add(TransformationFunction.CreateAssignmentAction(NVar, nVar));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       AssignmentAction AA = TransformationFunction.CreateVarFunctionAssignmentAction(this.Map_Conn_IndexCompFail.get(connector), TOP.FunctionGetCompFail_res, ActualData);
/*  83 */       CompositeAction UpdateNValues = TransformationFunction.CreateCompositeAction(LActionOKTransition);
/*  84 */       BinaryExpression GuardIndexFail = TransformationFunction.CreateGuardEqual(this.Map_Conn_IndexCompFail.get(connector), Integer.valueOf(-1), true);
/*  85 */       IfAction ifAction = TransformationFunction.CreateIfAction((Expression)GuardIndexFail, (Action)UpdateNValues, null);
/*  86 */       List<Action> LAction = new LinkedList<Action>();
/*  87 */       LAction.add(AA);
/*  88 */       LAction.add(ifAction);
/*  89 */       CompositeAction CAA = TransformationFunction.CreateCompositeAction(LAction);
/*  90 */       TransformationFunction.CreateTransition(portdefReserve, null, (Action)CAA, this.Map_Conn_InitialState.get(connector), state, this.PetriNetRP);
/*     */ 
/*     */       
/*  93 */       BinaryExpression GuardOK = TransformationFunction.CreateGuardEqual(this.Map_Conn_IndexCompFail.get(connector), Integer.valueOf(-1), true);
/*  94 */       PortDefinition PDOk = TransformationFunction.getPortDefinition(this.Map_Conn_PortOk.get(connector));
/*     */       
/*  96 */       TransformationFunction.CreateTransition(PDOk, (Expression)GuardOK, null, state, this.Map_Conn_InitialState.get(connector), this.PetriNetRP);
/*     */ 
/*     */       
/*  99 */       BinaryExpression GuardFail = TransformationFunction.CreateGuardEqual(this.Map_Conn_IndexCompFail.get(connector), Integer.valueOf(-1), false);
/* 100 */       PortDefinition PDFail = TransformationFunction.getPortDefinition(this.Map_Conn_PortFail.get(connector));
/* 101 */       TransformationFunction.CreateTransition(PDFail, (Expression)GuardFail, null, state, this.Map_Conn_InitialState.get(connector), this.PetriNetRP);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\CentralizedProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */