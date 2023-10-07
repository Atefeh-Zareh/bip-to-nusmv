/*     */ package rv;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MAtomType
/*     */ {
/*     */   private Component ComponentInput;
/*  31 */   private List<Variable> LVariable = new LinkedList<Variable>();
/*     */   
/*     */   private AtomType AT;
/*     */   private AtomType MAT;
/*     */   private Port MPort;
/*     */   private PortType MPortType;
/*     */   private Variable PortNameVar;
/*     */   private Variable StateNameVar;
/*     */   
/*     */   MAtomType(Component ComponentInput, List<Variable> LVariable) {
/*  41 */     this.ComponentInput = ComponentInput;
/*  42 */     this.AT = (AtomType)ComponentInput.getType();
/*  43 */     this.LVariable = LVariable;
/*  44 */     this.PortNameVar = LVariable.get(LVariable.size() - 2);
/*  45 */     this.StateNameVar = LVariable.get(LVariable.size() - 1);
/*  46 */     setMAT();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMAT() {
/*  53 */     this.MAT = TransformationFunction.getCopy(this.AT);
/*  54 */     setMPort();
/*  55 */     PetriNet PN = (PetriNet)this.MAT.getBehavior();
/*  56 */     this.MAT.setName(String.valueOf(this.ComponentInput.getName()) + "Monitor");
/*  57 */     setPN(PN);
/*  58 */     BIPMonitor.BIPmodule.getBipType().add(this.MAT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPN(PetriNet PN) {
/*  68 */     int i = 0;
/*  69 */     List<Transition> LTransition = new LinkedList<Transition>((Collection<? extends Transition>)PN.getTransition());
/*  70 */     for (Transition o : LTransition) {
/*     */       
/*  72 */       Transition t = o;
/*  73 */       State MState = TransformationFunction.CreateState("MState_" + i++, PN);
/*  74 */       List<State> OutputState = new LinkedList<State>((Collection<? extends State>)t.getDestination());
/*  75 */       List<State> InputState = new LinkedList<State>();
/*  76 */       InputState.add(MState);
/*  77 */       t.getDestination().clear();
/*  78 */       t.getDestination().add(MState);
/*     */       
/*  80 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/*  81 */       String AssingmentStatePortNameAction = String.valueOf(this.PortNameVar.getName()) + " = \"" + pdr.getTarget().getName() + "\" ; \n";
/*     */       
/*  83 */       State toState = (State)t.getDestination().get(0);
/*  84 */       AssingmentStatePortNameAction = String.valueOf(AssingmentStatePortNameAction) + this.StateNameVar.getName() + "= \"" + toState.getName() + "\" ; ";
/*  85 */       OpaqueElement OEAction = TransformationFunction.CreateOpaqueElement(AssingmentStatePortNameAction);
/*     */       
/*  87 */       if (t.getAction() instanceof CompositeAction) {
/*  88 */         CompositeAction ca = (CompositeAction)t.getAction();
/*  89 */         ca.getContent().add(OEAction);
/*     */       }
/*  91 */       else if (t.getAction() == null) {
/*  92 */         t.setAction((Action)OEAction);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  97 */       TransformationFunction.CreateTransitionLState(TransformationFunction.getPortDefinition(this.MPort), null, null, InputState, OutputState, PN);
/*     */     } 
/*     */ 
/*     */     
/* 101 */     assert PN.getInitialState().size() == 1 : "At most one initial state";
/* 102 */     State initialstate = (State)PN.getInitialState().get(0);
/* 103 */     State newinitialstate = TransformationFunction.CreateState("newinitialstate", PN);
/* 104 */     TransformationFunction.CreateTransition(TransformationFunction.getPortDefinition(this.MPort), null, null, newinitialstate, initialstate, PN);
/* 105 */     PN.getInitialState().clear();
/* 106 */     PN.getInitialState().add(newinitialstate);
/*     */ 
/*     */     
/* 109 */     OpaqueElement OAction = TransformationFunction.CreateOpaqueElement(String.valueOf(this.StateNameVar.getName()) + "= \"" + initialstate.getName() + "\" ; ");
/* 110 */     if (PN.getInitialization() instanceof CompositeAction) {
/* 111 */       CompositeAction ca = (CompositeAction)PN.getInitialization();
/* 112 */       ca.getContent().add(OAction);
/*     */     }
/* 114 */     else if (PN.getInitialization() == null) {
/* 115 */       PN.setInitialization((Action)OAction);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMPort() {
/* 126 */     this.MAT.getVariable().add(this.PortNameVar);
/* 127 */     this.MAT.getVariable().add(this.StateNameVar);
/* 128 */     this.MPortType = TransformationFunction.CreatePortTypeFromVar(this.LVariable, "PMType_" + this.ComponentInput.getName(), BIPMonitor.BIPmodule);
/* 129 */     this.MPort = TransformationFunction.CreatePort("PM", this.MPortType, this.MAT, this.LVariable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType getAT() {
/* 137 */     return this.AT;
/*     */   }
/*     */   
/*     */   public AtomType getMAT() {
/* 141 */     return this.MAT;
/*     */   }
/*     */   
/*     */   public Port getMPort() {
/* 145 */     return this.MPort;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\rv\MAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */