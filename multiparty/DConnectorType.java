/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ 
/*     */ public class DConnectorType {
/*     */   private ConnectorType connectorType;
/*  33 */   private List<PortParameter> LPortParameter = new LinkedList<PortParameter>();
/*  34 */   private List<List<DataParameter>> LLDataParameterPort = new LinkedList<List<DataParameter>>();
/*  35 */   private List<List<Variable>> LLVariablePort = new LinkedList<List<Variable>>();
/*  36 */   private List<Port> LPort = new LinkedList<Port>();
/*  37 */   private List<PortDefinition> LPortDefinition = new LinkedList<PortDefinition>();
/*     */   
/*  39 */   private List<Port> LPortData = new LinkedList<Port>();
/*  40 */   private List<PortDefinition> LPortDefinitionData = new LinkedList<PortDefinition>();
/*     */   
/*  42 */   private List<State> fromState = new LinkedList<State>();
/*  43 */   private List<State> toState = new LinkedList<State>();
/*     */   
/*     */   private Port Guard;
/*     */   
/*     */   private Port Start;
/*     */   
/*     */   private AtomType DConnType;
/*     */   
/*     */   public DConnectorType(ConnectorType connectorType) {
/*  52 */     this.connectorType = connectorType;
/*  53 */     this.LPortParameter = (List<PortParameter>)connectorType.getPortParameter();
/*  54 */     setDConnType();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDConnType() {
/*  59 */     this.DConnType = (AtomType)EcoreUtil.copy((EObject)Initialize.ConnectorTypeBehavior);
/*  60 */     this.DConnType.setName(String.valueOf(this.connectorType.getName()) + "Behavior");
/*  61 */     setPort();
/*  62 */     setLLDP_VariablePort();
/*     */ 
/*     */     
/*  65 */     List<Variable> LVariable = TransformationFunction.getCopyLVariable((List)this.connectorType.getVariable());
/*  66 */     this.DConnType.getVariable().addAll((Collection)this.connectorType.getVariable());
/*  67 */     this.connectorType.getVariable().addAll(LVariable);
/*     */ 
/*     */ 
/*     */     
/*  71 */     setBehavior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPort() {
/*  76 */     for (Object o : this.DConnType.getPort()) {
/*     */       
/*  78 */       Port p = (Port)o;
/*  79 */       if (p.getName().equals("START")) {
/*  80 */         this.Start = p; continue;
/*  81 */       }  if (p.getName().equals("Guard")) {
/*  82 */         this.Guard = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBehavior() {
/*  88 */     PetriNet PN = (PetriNet)this.DConnType.getBehavior();
/*  89 */     AddfromtoState(PN);
/*  90 */     EditAddTransitionA0ToA0(PN);
/*  91 */     EditTransitionsGuardStart(PN);
/*  92 */     AddTransitionInternfromCtoA1(PN);
/*     */   }
/*     */   
/*     */   private void AddTransitionInternfromCtoA1(PetriNet PN) {
/*  96 */     State C1 = TransformationFunction.getState(PN, "C1");
/*  97 */     State A0 = TransformationFunction.getState(PN, "A0");
/*  98 */     Transition intern = TransformationFunction.getLTransition(PN, C1, A0);
/*  99 */     intern.getOrigin().clear();
/* 100 */     intern.getOrigin().addAll(this.toState);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void EditAddTransitionA0ToA0(PetriNet PN) {
/* 106 */     State A0 = TransformationFunction.getState(PN, "A0");
/* 107 */     Transition fromA0ToA0 = TransformationFunction.getLTransition(PN, A0, A0);
/* 108 */     for (PortDefinition o : this.LPortDefinitionData) {
/*     */       
/* 110 */       PortDefinition pd = o;
/* 111 */       Transition t = (Transition)EcoreUtil.copy((EObject)fromA0ToA0);
/* 112 */       t.getDestination().add(A0);
/* 113 */       t.getOrigin().add(A0);
/* 114 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 115 */       pdr.setTarget(pd);
/* 116 */       PN.getTransition().add(t);
/*     */     } 
/* 118 */     PN.getTransition().remove(fromA0ToA0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddfromtoState(PetriNet PN) {
/* 125 */     boolean first = true;
/* 126 */     for (PortDefinition o : this.LPortDefinition) {
/*     */       
/* 128 */       PortDefinition pd = o;
/* 129 */       if (first) {
/*     */         
/* 131 */         first = false;
/* 132 */         State stateB1 = TransformationFunction.getState(PN, "B1");
/* 133 */         State stateC1 = TransformationFunction.getState(PN, "C1");
/* 134 */         this.fromState.add(stateB1);
/* 135 */         this.toState.add(stateC1);
/* 136 */         TransformationFunction.CreateTransition(pd, null, null, stateB1, stateC1, PN);
/*     */         
/*     */         continue;
/*     */       } 
/* 140 */       int index = this.LPortDefinition.indexOf(o) + 1;
/* 141 */       State stateBindex = TransformationFunction.CreateState("B" + index, PN);
/* 142 */       State stateCindex = TransformationFunction.CreateState("C" + index, PN);
/* 143 */       this.fromState.add(stateBindex);
/* 144 */       this.toState.add(stateCindex);
/* 145 */       TransformationFunction.CreateTransition(pd, null, null, stateBindex, stateCindex, PN);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void EditTransitionsGuardStart(PetriNet PN) {
/* 152 */     if (this.connectorType.getInteractionSpecification().size() != 0) {
/*     */       
/* 154 */       InteractionSpecification InterSpec = (InteractionSpecification)this.connectorType.getInteractionSpecification().get(0);
/* 155 */       CompositeAction CompAction = ActionsFactory.eINSTANCE.createCompositeAction();
/*     */ 
/*     */       
/* 158 */       if (InterSpec.getUpAction() instanceof CompositeAction) {
/*     */         
/* 160 */         CompositeAction ca = (CompositeAction)InterSpec.getUpAction();
/* 161 */         for (Object o : ca.getContent()) {
/*     */           
/* 163 */           if (o instanceof AssignmentAction) {
/*     */             
/* 165 */             AssignmentAction aa = (AssignmentAction)o;
/* 166 */             AssignmentAction aacopy = (AssignmentAction)EcoreUtil.copy((EObject)aa);
/* 167 */             TransformationFunction.ExpressionReplace(aacopy.getAssignedValue(), this.LPortParameter, this.LLVariablePort);
/* 168 */             TransformationFunction.ExpressionReplace((Expression)aacopy.getAssignedTarget(), this.LPortParameter, this.LLVariablePort);
/* 169 */             CompAction.getContent().add(aacopy); continue;
/*     */           } 
/* 171 */           if (o instanceof FunctionCallExpression) {
/*     */             
/* 173 */             FunctionCallExpression fce = (FunctionCallExpression)o;
/* 174 */             FunctionCallExpression fcecopy = (FunctionCallExpression)EcoreUtil.copy((EObject)fce);
/* 175 */             TransformationFunction.ExpressionReplace((Expression)fcecopy, this.LPortParameter, this.LLVariablePort);
/* 176 */             CompAction.getContent().add(fcecopy);
/*     */             
/*     */             continue;
/*     */           } 
/* 180 */           System.out.println("To Add - in DConnectorType -- Up function don't support this type");
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       if (InterSpec.getDownAction() instanceof CompositeAction) {
/*     */         
/* 189 */         CompositeAction ca = (CompositeAction)InterSpec.getDownAction();
/* 190 */         for (Object o : ca.getContent()) {
/*     */           
/* 192 */           if (o instanceof AssignmentAction) {
/*     */             
/* 194 */             AssignmentAction aa = (AssignmentAction)o;
/* 195 */             AssignmentAction aacopy = (AssignmentAction)EcoreUtil.copy((EObject)aa);
/* 196 */             TransformationFunction.ExpressionReplace(aacopy.getAssignedValue(), this.LPortParameter, this.LLVariablePort);
/* 197 */             TransformationFunction.ExpressionReplace((Expression)aacopy.getAssignedTarget(), this.LPortParameter, this.LLVariablePort);
/* 198 */             CompAction.getContent().add(aacopy); continue;
/*     */           } 
/* 200 */           if (o instanceof FunctionCallExpression) {
/*     */             
/* 202 */             FunctionCallExpression fce = (FunctionCallExpression)o;
/* 203 */             FunctionCallExpression fcecopy = (FunctionCallExpression)EcoreUtil.copy((EObject)fce);
/* 204 */             TransformationFunction.ExpressionReplace((Expression)fcecopy, this.LPortParameter, this.LLVariablePort);
/* 205 */             CompAction.getContent().add(fcecopy);
/*     */             
/*     */             continue;
/*     */           } 
/* 209 */           System.out.println("To Add - in DConnectorType -- Down function don't support this type");
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       State A0 = TransformationFunction.getState(PN, "A0");
/* 217 */       State B1 = TransformationFunction.getState(PN, "B1");
/* 218 */       Transition Guard = TransformationFunction.getLTransition(PN, A0, A0);
/* 219 */       Transition Start = TransformationFunction.getLTransition(PN, A0, B1);
/*     */ 
/*     */       
/* 222 */       Start.getDestination().clear();
/* 223 */       Start.getDestination().addAll(this.fromState);
/* 224 */       Start.setAction((Action)CompAction);
/*     */ 
/*     */       
/* 227 */       Expression ExpGuard = null;
/* 228 */       if (InterSpec.getGuard() != null) {
/*     */         
/* 230 */         ExpGuard = (Expression)EcoreUtil.copy((EObject)InterSpec.getGuard());
/* 231 */         TransformationFunction.ExpressionReplace(ExpGuard, this.LPortParameter, this.LLVariablePort);
/* 232 */         Start.setGuard((Expression)EcoreUtil.copy((EObject)ExpGuard));
/* 233 */         Guard.setGuard((Expression)EcoreUtil.copy((EObject)ExpGuard));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLLDP_VariablePort() {
/* 240 */     int itt = 1;
/* 241 */     int itt1 = 1;
/* 242 */     for (PortParameter o : this.LPortParameter) {
/*     */       
/* 244 */       List<DataParameter> LDP = new LinkedList<DataParameter>();
/* 245 */       List<Variable> LV = new LinkedList<Variable>();
/* 246 */       PortParameter pp = o;
/* 247 */       for (Object o1 : pp.getType().getDataParameter()) {
/*     */         
/* 249 */         DataParameter dp = (DataParameter)o1;
/* 250 */         LDP.add(dp);
/* 251 */         Variable v = TransformationFunction.CreateVariable("v" + itt++, dp.getType());
/* 252 */         LV.add(v);
/*     */       } 
/* 254 */       this.LLDataParameterPort.add(LDP);
/* 255 */       this.LLVariablePort.add(LV);
/* 256 */       this.DConnType.getVariable().addAll(LV);
/* 257 */       Port p = TransformationFunction.CreatePort("portComponent_" + itt1++, pp.getType(), this.DConnType, LV);
/* 258 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 259 */       this.LPort.add(p);
/* 260 */       this.LPortDefinition.add(db.getDefinition());
/*     */ 
/*     */       
/* 263 */       Port pdata = (Port)EcoreUtil.copy((EObject)p);
/* 264 */       PortDefinition pdefinitiondata = (PortDefinition)EcoreUtil.copy((EObject)db.getDefinition());
/* 265 */       DefinitionBinding db1 = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 266 */       db1.setDefinition(pdefinitiondata);
/* 267 */       pdata.setBinding((Binding)db1);
/* 268 */       pdefinitiondata.setName(String.valueOf(pdefinitiondata.getName()) + "data");
/* 269 */       pdata.setName(pdefinitiondata.getName());
/* 270 */       this.LPortData.add(pdata);
/* 271 */       this.LPortDefinitionData.add(pdefinitiondata);
/* 272 */       this.DConnType.getPort().add(pdata);
/* 273 */       this.DConnType.getPortDefinition().add(pdefinitiondata);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<DataParameter>> getLLDataParameterPort() {
/* 279 */     return this.LLDataParameterPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getDConnType() {
/* 284 */     return this.DConnType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLLVariablePort() {
/* 289 */     return this.LLVariablePort;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 294 */     return this.connectorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortParameter> getLPortParameter() {
/* 299 */     return this.LPortParameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPort() {
/* 304 */     return this.LPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortDefinition> getLPortDefinition() {
/* 309 */     return this.LPortDefinition;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> getfromState() {
/* 314 */     return this.fromState;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> gettoState() {
/* 319 */     return this.toState;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getGuard() {
/* 324 */     return this.Guard;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getStart() {
/* 329 */     return this.Start;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPortData() {
/* 334 */     return this.LPortData;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortDefinition> getLPortDefinitionData() {
/* 339 */     return this.LPortDefinitionData;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\DConnectorType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */