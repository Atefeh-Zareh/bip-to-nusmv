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
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
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
/*     */ 
/*     */ 
/*     */ public class TokenRingProtocol
/*     */   extends ReservationProtocol
/*     */ {
/*     */   private Port SendTokenPort;
/*     */   private Port ReceiveTokenPort;
/*     */   private State Idle;
/*     */   private State Token;
/*     */   private State Token1TMP;
/*     */   private State Token1;
/*     */   private State Wait;
/*  35 */   private Map<Component, Variable> RestMap_Comp_NVar = new HashMap<Component, Variable>();
/*     */   
/*  37 */   private List<Variable> LNVar = new LinkedList<Variable>();
/*     */ 
/*     */   
/*     */   private Connector InteractionToReserve;
/*     */ 
/*     */   
/*     */   private Variable IndexCompFail;
/*     */ 
/*     */   
/*     */   public TokenRingProtocol(List<Connector> LInteraction, String IPName) {
/*  47 */     super(LInteraction, IPName);
/*  48 */     this.InteractionToReserve = LInteraction.get(0);
/*  49 */     this.IndexCompFail = this.Map_Conn_IndexCompFail.get(this.InteractionToReserve);
/*  50 */     CreateStates();
/*  51 */     setRestMap_Component_NVar();
/*  52 */     setLNVar();
/*  53 */     SetPorts();
/*  54 */     SetBehavior();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLNVar() {
/*  64 */     for (Component o : TOP.LComponentReserve) {
/*  65 */       Component component = o;
/*  66 */       Variable v1 = this.Map_Comp_NVar.get(component);
/*  67 */       Variable v2 = this.RestMap_Comp_NVar.get(component);
/*  68 */       if (v1 != null) {
/*  69 */         this.LNVar.add(v1); continue;
/*  70 */       }  if (v2 != null) {
/*  71 */         this.LNVar.add(v2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRestMap_Component_NVar() {
/*  78 */     for (Component o : TOP.LComponentReserve) {
/*  79 */       Component component = o;
/*  80 */       if (!this.Map_Comp_NVar.containsKey(component)) {
/*  81 */         Variable N_Var = TransformationFunction.CreateIntVariable("N_" + component.getName(), 0);
/*  82 */         this.AtomTypeRP.getVariable().add(N_Var);
/*  83 */         this.RestMap_Comp_NVar.put(component, N_Var);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void SetPorts() {
/*  90 */     PortType SRTokenPortType = TOP.getSRTokentPortType();
/*  91 */     this.SendTokenPort = TransformationFunction.CreatePort("SendToken", SRTokenPortType, this.AtomTypeRP, this.LNVar);
/*  92 */     this.ReceiveTokenPort = TransformationFunction.CreatePort("ReceiveToken", SRTokenPortType, this.AtomTypeRP, this.LNVar);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTransitionSRToken() {
/*  98 */     PortDefinition PDRecToken = TransformationFunction.getPortDefinition(this.ReceiveTokenPort);
/*  99 */     TransformationFunction.CreateTransition(PDRecToken, null, null, this.Idle, this.Token, this.PetriNetRP);
/*     */ 
/*     */     
/* 102 */     PortDefinition PDSendToken = TransformationFunction.getPortDefinition(this.SendTokenPort);
/* 103 */     TransformationFunction.CreateTransition(PDSendToken, null, null, this.Token, this.Idle, this.PetriNetRP);
/*     */ 
/*     */     
/* 106 */     TransformationFunction.CreateTransition(PDRecToken, null, null, this.Wait, this.Token1TMP, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTransitionsReserveOKFail() {
/* 113 */     List<IntegerLiteral> ActualData1 = new LinkedList();
/* 114 */     List<IntegerLiteral> ActualData2 = new LinkedList();
/* 115 */     List<Component> LComponentToReserve = getComponentToReserve(this.InteractionToReserve);
/* 116 */     List<Action> LActionOKTransition = new LinkedList<Action>();
/*     */     
/* 118 */     IntegerLiteral ILnArgs1 = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(LComponentToReserve.size()));
/* 119 */     ActualData1.add(ILnArgs1);
/* 120 */     IntegerLiteral ILnArgs2 = TransformationFunction.CreateIntegerLiteral(Integer.valueOf(LComponentToReserve.size()));
/* 121 */     ActualData2.add(ILnArgs2);
/*     */     
/* 123 */     for (Component o1 : LComponentToReserve) {
/* 124 */       Component component = o1;
/* 125 */       Variable nVar = getn_Var(this.InteractionToReserve, component);
/* 126 */       Variable NVar = this.Map_Comp_NVar.get(component);
/* 127 */       Integer ComponentID = TOP.getComponentID(component);
/* 128 */       VariableReference nVarRef1 = TransformationFunction.CreateVariableReference(nVar);
/* 129 */       VariableReference NVarRef1 = TransformationFunction.CreateVariableReference(NVar);
/* 130 */       IntegerLiteral IL1 = TransformationFunction.CreateIntegerLiteral(ComponentID);
/* 131 */       VariableReference nVarRef2 = TransformationFunction.CreateVariableReference(nVar);
/* 132 */       VariableReference NVarRef2 = TransformationFunction.CreateVariableReference(NVar);
/* 133 */       IntegerLiteral IL2 = TransformationFunction.CreateIntegerLiteral(ComponentID);
/* 134 */       ActualData1.add(nVarRef1);
/* 135 */       ActualData1.add(NVarRef1);
/* 136 */       ActualData1.add(IL1);
/* 137 */       ActualData2.add(nVarRef2);
/* 138 */       ActualData2.add(NVarRef2);
/* 139 */       ActualData2.add(IL2);
/*     */       
/* 141 */       LActionOKTransition.add(TransformationFunction.CreateAssignmentAction(NVar, nVar));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 146 */     setTransitionReserveFromIdleToWait(this.InteractionToReserve, ActualData1);
/*     */ 
/*     */     
/* 149 */     setTransitionReserveFromToken1TMPtoToken(this.InteractionToReserve, ActualData2);
/*     */ 
/*     */     
/* 152 */     setTransitionOkFromToken1ToToken(this.InteractionToReserve, LActionOKTransition);
/*     */ 
/*     */     
/* 155 */     setTransitionsFail(this.InteractionToReserve);
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
/*     */   private void setTransitionReserveFromIdleToWait(Connector connector, List ActualData) {
/* 174 */     AssignmentAction AA = TransformationFunction.CreateVarFunctionAssignmentAction(this.IndexCompFail, TOP.FunctionGetCompFail_res, ActualData);
/* 175 */     Port PortReserve = this.Map_Conn_Reserve.get(connector);
/* 176 */     PortDefinition PDReserve = TransformationFunction.getPortDefinition(PortReserve);
/* 177 */     TransformationFunction.CreateTransition(PDReserve, null, (Action)AA, this.Idle, this.Wait, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTransitionReserveFromToken1TMPtoToken(Connector connector, List ActualData) {
/* 182 */     AssignmentAction AA = TransformationFunction.CreateVarFunctionAssignmentAction(this.IndexCompFail, TOP.FunctionGetCompFail_res, ActualData);
/* 183 */     TransformationFunction.CreateTransition(this.InterPort, null, (Action)AA, this.Token1TMP, this.Token1, this.PetriNetRP);
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
/*     */   private void setTransitionOkFromToken1ToToken(Connector connector, List<Action> LActionOKTransition) {
/* 199 */     BinaryExpression GuardOK = TransformationFunction.CreateGuardEqual(this.IndexCompFail, Integer.valueOf(-1), true);
/* 200 */     CompositeAction ActionOK = TransformationFunction.CreateCompositeAction(LActionOKTransition);
/* 201 */     PortDefinition PDOk = TransformationFunction.getPortDefinition(this.Map_Conn_PortOk.get(connector));
/* 202 */     TransformationFunction.CreateTransition(PDOk, (Expression)GuardOK, (Action)ActionOK, this.Token1, this.Token, this.PetriNetRP);
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
/*     */   private void setTransitionsFail(Connector connector) {
/* 216 */     setTransitionFail(connector, this.Token1, this.Token);
/*     */     
/* 218 */     setTransitionFail(connector, this.Wait, this.Idle);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTransitionFail(Connector connector, State from, State to) {
/* 223 */     BinaryExpression GuardFail = TransformationFunction.CreateGuardEqual(this.IndexCompFail, Integer.valueOf(-1), false);
/* 224 */     PortDefinition PDFail = TransformationFunction.getPortDefinition(this.Map_Conn_PortFail.get(connector));
/* 225 */     TransformationFunction.CreateTransition(PDFail, (Expression)GuardFail, null, from, to, this.PetriNetRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetBehavior() {
/* 232 */     setTransitionsReserveOKFail();
/* 233 */     setTransitionSRToken();
/*     */   }
/*     */ 
/*     */   
/*     */   private void CreateStates() {
/* 238 */     this.Idle = TransformationFunction.CreateState("Idle", this.PetriNetRP);
/* 239 */     this.Token = TransformationFunction.CreateState("Token", this.PetriNetRP);
/* 240 */     this.Token1TMP = TransformationFunction.CreateState("Token1TMP", this.PetriNetRP);
/* 241 */     this.Token1 = TransformationFunction.CreateState("Token1", this.PetriNetRP);
/* 242 */     this.Wait = TransformationFunction.CreateState("Wait", this.PetriNetRP);
/* 243 */     this.PetriNetRP.getInitialState().add(this.Idle);
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getSendTokenPort() {
/* 248 */     return this.SendTokenPort;
/*     */   }
/*     */   
/*     */   public Port getReceiveTokenPort() {
/* 252 */     return this.ReceiveTokenPort;
/*     */   }
/*     */   
/*     */   public void setInitialHasToken() {
/* 256 */     this.PetriNetRP.getInitialState().add(this.Token);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\TokenRingProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */