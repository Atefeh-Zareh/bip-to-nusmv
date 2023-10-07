/*     */ package BIP2BIP;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
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
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TComponent
/*     */ {
/*     */   private Component Comp;
/*     */   
/*     */   public TComponent(Component Comp) {
/*  32 */     this.Comp = Comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void GetNewPVS(List<PortDefinition> ListPort, List<Variable> ListVariable, List<State> ListState, List<State> InitState, List<Action> InitAction, List<Transition> ListTransition, List<Port> expPort) {
/*  40 */     String CompName = this.Comp.getName();
/*  41 */     AtomType AT = (AtomType)EcoreUtil.copy((EObject)this.Comp.getType());
/*     */ 
/*     */     
/*  44 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>((Collection<? extends DataParameter>)AT.getDataParameter());
/*  45 */     for (DataParameter o : LDataParameter) {
/*     */       
/*  47 */       DataParameter dp = o;
/*  48 */       dp.setName(String.valueOf(this.Comp.getName()) + "_" + dp.getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  53 */     List Lport = new LinkedList((Collection<?>)AT.getPortDefinition());
/*  54 */     for (Object o : Lport) {
/*     */       
/*  56 */       PortDefinition PD = (PortDefinition)o;
/*  57 */       if (!IsExport(PD)) {
/*     */         
/*  59 */         PD.getExposedVariable().clear();
/*  60 */         PD.setName(String.valueOf(CompName) + PD.getName());
/*  61 */         PD.setType(Init.PTSyn);
/*  62 */         ListPort.add(PD);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  67 */     List<Variable> LVar = new LinkedList<Variable>((Collection<? extends Variable>)AT.getVariable());
/*  68 */     for (Variable o : LVar) {
/*     */       
/*  70 */       Variable V = o;
/*  71 */       V.setName(String.valueOf(CompName) + V.getName());
/*  72 */       ListVariable.add(V);
/*     */     } 
/*     */ 
/*     */     
/*  76 */     PetriNet PN = (PetriNet)AT.getBehavior();
/*  77 */     List<State> LState = new LinkedList<State>((Collection<? extends State>)PN.getState());
/*  78 */     for (State o : LState) {
/*     */       
/*  80 */       State S = o;
/*  81 */       S.setName(String.valueOf(CompName) + S.getName());
/*  82 */       ListState.add(S);
/*     */     } 
/*     */ 
/*     */     
/*  86 */     List<State> LInitState = new LinkedList<State>((Collection<? extends State>)PN.getInitialState());
/*  87 */     for (State o : LInitState) {
/*     */       
/*  89 */       State S = o;
/*  90 */       InitState.add(S);
/*     */     } 
/*     */ 
/*     */     
/*  94 */     Action A = PN.getInitialization();
/*  95 */     if (A instanceof CompositeAction) {
/*     */       
/*  97 */       CompositeAction CA = (CompositeAction)A;
/*  98 */       List<Action> LInitAction = new LinkedList<Action>((Collection<? extends Action>)CA.getContent());
/*  99 */       for (Action o : LInitAction) {
/*     */         
/* 101 */         Action S = o;
/* 102 */         InitAction.add(S);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     List<State> LTransition = new LinkedList<State>((Collection<? extends State>)PN.getTransition());
/* 112 */     for (State o : LTransition) {
/*     */       
/* 114 */       Transition T = (Transition)o;
/* 115 */       if (T.getTrigger() instanceof PortDefinitionReference) {
/*     */         
/* 117 */         PortDefinitionReference PDR = (PortDefinitionReference)T.getTrigger();
/* 118 */         if (!IsExport(PDR.getTarget())) {
/* 119 */           ListTransition.add(T);
/*     */         }
/*     */       } 
/*     */     } 
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
/* 133 */     List<Port> LPort = new LinkedList<Port>((Collection<? extends Port>)this.Comp.getCompoundType().getPort());
/* 134 */     for (Port o : LPort) {
/*     */       
/* 136 */       Port p = o;
/* 137 */       ExportBinding eb = (ExportBinding)p.getBinding();
/* 138 */       if (eb.getTargetInstance().getTargetPart() instanceof Component) {
/*     */         
/* 140 */         Component comp = (Component)eb.getTargetInstance().getTargetPart();
/* 141 */         if (comp.equals(this.Comp)) {
/*     */           
/* 143 */           Port p1 = eb.getTargetPort();
/* 144 */           PortDefinition pdtmp = (PortDefinition)EcoreUtil.copy((EObject)((DefinitionBinding)p1.getBinding()).getDefinition());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 153 */           List<Variable> LVartmp = new LinkedList<Variable>();
/* 154 */           for (Object v_i : pdtmp.getExposedVariable()) {
/*     */             
/* 156 */             Variable v = (Variable)v_i;
/* 157 */             Variable VariableAtomNew = getCorrespondVariableName(String.valueOf(this.Comp.getName()) + v.getName(), AT);
/* 158 */             LVartmp.add(VariableAtomNew);
/*     */           } 
/* 160 */           PortDefinition pd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 161 */           List<Transition> LTransitiontmp = getTransitionPort(pdtmp, AT, pd);
/* 162 */           ListTransition.addAll(LTransitiontmp);
/* 163 */           pd.setType(pdtmp.getType());
/* 164 */           pd.setName(p.getName());
/* 165 */           pd.getExposedVariable().addAll(LVartmp);
/* 166 */           Port port = BehaviorsFactory.eINSTANCE.createPort();
/* 167 */           port.setType(pd.getType());
/* 168 */           port.setName(pd.getName());
/* 169 */           DefinitionBinding db1 = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 170 */           db1.setDefinition(pd);
/* 171 */           port.setBinding((Binding)db1);
/* 172 */           expPort.add(port);
/* 173 */           ListPort.add(pd);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Variable getCorrespondVariableName(String variableNameAtomNew, AtomType AT) {
/* 180 */     for (Object o : AT.getVariable()) {
/*     */       
/* 182 */       Variable v = (Variable)o;
/* 183 */       if (v.getName().equals(variableNameAtomNew))
/* 184 */         return v; 
/*     */     } 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Transition> getTransitionPort(PortDefinition pd, AtomType at, PortDefinition pdnew) {
/* 191 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 192 */     PetriNet pn = (PetriNet)at.getBehavior();
/* 193 */     for (Object o : pn.getTransition()) {
/*     */       
/* 195 */       Transition t = (Transition)o;
/* 196 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 197 */       if (pdr.getTarget().getName().equals(pd.getName())) {
/*     */         
/* 199 */         pdr.setTarget(pdnew);
/* 200 */         LTransition.add(t);
/*     */       } 
/*     */     } 
/* 203 */     return LTransition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean IsExport(PortDefinition PD) {
/* 210 */     AtomType AT = (AtomType)this.Comp.getType();
/* 211 */     for (Object o : AT.getPort()) {
/*     */       
/* 213 */       Port P = (Port)o;
/* 214 */       DefinitionBinding DB = (DefinitionBinding)P.getBinding();
/* 215 */       if (DB.getDefinition().getName() == PD.getName())
/* 216 */         return true; 
/*     */     } 
/* 218 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\TComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */