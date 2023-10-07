/*     */ package trans;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TComponent
/*     */ {
/*     */   private Component Comp;
/*     */   
/*     */   public TComponent(Component Comp) {
/*  30 */     this.Comp = Comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void GetNewPVS(List<PortDefinition> ListPort, List<Variable> ListVariable, List<State> ListState, List<State> InitState, List<Action> InitAction, List<Transition> ListTransition) {
/*  38 */     String CompName = this.Comp.getName();
/*  39 */     AtomType AT = (AtomType)EcoreUtil.copy((EObject)this.Comp.getType());
/*     */ 
/*     */     
/*  42 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>((Collection<? extends DataParameter>)AT.getDataParameter());
/*  43 */     for (DataParameter o : LDataParameter) {
/*     */       
/*  45 */       DataParameter dp = o;
/*  46 */       dp.setName(String.valueOf(this.Comp.getName()) + "_" + dp.getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  51 */     List Lport = new LinkedList((Collection<?>)AT.getPortDefinition());
/*  52 */     for (Object o : Lport) {
/*     */       
/*  54 */       PortDefinition PD = (PortDefinition)o;
/*  55 */       if (!IsExport(PD)) {
/*     */         
/*  57 */         PD.getExposedVariable().clear();
/*  58 */         PD.setName(String.valueOf(CompName) + PD.getName());
/*  59 */         PD.setType(Init.PTSyn);
/*  60 */         ListPort.add(PD);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  65 */     List<Variable> LVar = new LinkedList<Variable>((Collection<? extends Variable>)AT.getVariable());
/*  66 */     for (Variable o : LVar) {
/*     */       
/*  68 */       Variable V = o;
/*  69 */       V.setName(String.valueOf(CompName) + V.getName());
/*  70 */       ListVariable.add(V);
/*     */     } 
/*     */ 
/*     */     
/*  74 */     PetriNet PN = (PetriNet)AT.getBehavior();
/*  75 */     List<State> LState = new LinkedList<State>((Collection<? extends State>)PN.getState());
/*  76 */     for (State o : LState) {
/*     */       
/*  78 */       State S = o;
/*  79 */       S.setName(String.valueOf(CompName) + S.getName());
/*  80 */       ListState.add(S);
/*     */     } 
/*     */ 
/*     */     
/*  84 */     List<State> LInitState = new LinkedList<State>((Collection<? extends State>)PN.getInitialState());
/*  85 */     for (State o : LInitState) {
/*     */       
/*  87 */       State S = o;
/*  88 */       InitState.add(S);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     Action A = PN.getInitialization();
/*  93 */     if (A instanceof CompositeAction) {
/*     */       
/*  95 */       CompositeAction CA = (CompositeAction)A;
/*  96 */       List<Action> LInitAction = new LinkedList<Action>((Collection<? extends Action>)CA.getContent());
/*  97 */       for (Action o : LInitAction) {
/*     */         
/*  99 */         Action S = o;
/* 100 */         InitAction.add(S);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     List<State> LTransition = new LinkedList<State>((Collection<? extends State>)PN.getTransition());
/* 110 */     for (State o : LTransition) {
/*     */       
/* 112 */       Transition T = (Transition)o;
/* 113 */       if (T.getTrigger() instanceof PortDefinitionReference) {
/*     */         
/* 115 */         PortDefinitionReference PDR = (PortDefinitionReference)T.getTrigger();
/* 116 */         if (!IsExport(PDR.getTarget())) {
/* 117 */           ListTransition.add(T);
/*     */         }
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
/*     */ 
/*     */   
/*     */   public boolean IsExport(PortDefinition PD) {
/* 132 */     AtomType AT = (AtomType)this.Comp.getType();
/* 133 */     for (Object o : AT.getPort()) {
/*     */       
/* 135 */       Port P = (Port)o;
/* 136 */       DefinitionBinding DB = (DefinitionBinding)P.getBinding();
/* 137 */       if (DB.getDefinition().getName() == PD.getName())
/* 138 */         return true; 
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\TComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */