/*     */ package BIP2BIP;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
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
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ 
/*     */ public class TAtomType {
/*  37 */   static int I = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType AT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TAtomType(CompoundType CT, List DP) {
/*  49 */     this.AT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  50 */     this.AT.setBehavior((Behavior)BehaviorsFactory.eINSTANCE.createPetriNet());
/*  51 */     this.AT.setName("TOPBIP2");
/*  52 */     AddPVS(CT);
/*  53 */     AddTransition(CT);
/*  54 */     this.AT.getDataParameter().addAll(DP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddTransition(CompoundType ct) {
/*  60 */     EList Lconn = ct.getConnector();
/*  61 */     for (Object o : Lconn) {
/*     */       
/*  63 */       Connector C = (Connector)o;
/*  64 */       TConnectorType TCT = new TConnectorType(C.getType());
/*  65 */       this.AT.getVariable().addAll(TCT.GetNewVariable(C));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       CompoundType compoundtype = C.getCompoundType();
/*  71 */       PortDefinition pd = null;
/*  72 */       for (Object p_i : compoundtype.getPort()) {
/*     */         
/*  74 */         Port p = (Port)p_i;
/*  75 */         ExportBinding eb = (ExportBinding)p.getBinding();
/*  76 */         if (eb.getTargetInstance().getTargetPart().getName().equals(C.getName())) {
/*     */           
/*  78 */           DefinitionBinding db = (DefinitionBinding)C.getType().getPort().getBinding();
/*  79 */           List<Variable> LVariable = new LinkedList<Variable>();
/*  80 */           PortDefinition pdtmp = db.getDefinition();
/*  81 */           for (Object v_i : pdtmp.getExposedVariable()) {
/*     */             
/*  83 */             Variable v = (Variable)v_i;
/*  84 */             Variable VariableAtomNew = getCorrespondVariableName(String.valueOf(C.getName()) + v.getName());
/*  85 */             LVariable.add(VariableAtomNew);
/*     */           } 
/*  87 */           pd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/*  88 */           pd.setType(pdtmp.getType());
/*  89 */           pd.setName(p.getName());
/*  90 */           pd.getExposedVariable().addAll(LVariable);
/*  91 */           Port port = BehaviorsFactory.eINSTANCE.createPort();
/*  92 */           port.setType(pd.getType());
/*  93 */           port.setName(pd.getName());
/*  94 */           port.setComponentType((ComponentType)this.AT);
/*  95 */           DefinitionBinding db1 = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  96 */           db1.setDefinition(pd);
/*  97 */           port.setBinding((Binding)db1);
/*  98 */           this.AT.getPort().add(port);
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 106 */       CreateNewTransition(C, TCT, pd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateNewTransition(Connector C, TConnectorType TCT, PortDefinition pd) {
/* 114 */     String PortDefinitionName = new String();
/* 115 */     EList eList = C.getActualPort();
/* 116 */     List<List> LList = new LinkedList<List>();
/* 117 */     List<List> newLList = new LinkedList<List>();
/* 118 */     List<Transition> ltmp = new LinkedList<Transition>();
/* 119 */     List<Component> LComponent = new LinkedList<Component>();
/* 120 */     for (ActualPortParameter o : eList) {
/* 121 */       assert o instanceof InnerPortReference;
/* 122 */       InnerPortReference ipr = (InnerPortReference)o;
/* 123 */       DefinitionBinding db = (DefinitionBinding)ipr.getTargetPort().getBinding();
/*     */       
/* 125 */       String PortName = db.getDefinition().getName();
/* 126 */       String NewPortName = String.valueOf(ipr.getTargetInstance().getTargetPart().getName()) + db.getDefinition().getName();
/* 127 */       AtomType at = (AtomType)((Component)ipr.getTargetInstance().getTargetPart()).getType();
/* 128 */       LComponent.add((Component)ipr.getTargetInstance().getTargetPart());
/* 129 */       if (TCT.GetTransition(PortName, at).size() != 0)
/* 130 */         LList.add(TCT.GetTransition(PortName, at)); 
/* 131 */       PortDefinitionName = String.valueOf(PortDefinitionName) + NewPortName;
/*     */     } 
/*     */     
/* 134 */     if (LList.size() != 0) {
/*     */       
/* 136 */       ProductListTransition(LList, LList.get(0), newLList, ltmp);
/* 137 */       List<List> newLListtmp = new LinkedList<List>(newLList);
/* 138 */       for (List o : newLListtmp) {
/*     */         
/* 140 */         List LT = new LinkedList(o);
/* 141 */         CreateTransition(LT, LComponent, PortDefinitionName, C, TCT, pd);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getCorrespondVariableName(String variableNameAtomNew) {
/* 150 */     for (Object o : this.AT.getVariable()) {
/*     */       
/* 152 */       Variable v = (Variable)o;
/* 153 */       if (v.getName().equals(variableNameAtomNew))
/* 154 */         return v; 
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateTransition(List LT, List<Component> LComponent, String PortDefinitionName, Connector C, TConnectorType TCT, PortDefinition pd) {
/* 162 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/* 163 */     Expression guardconn = null;
/* 164 */     if (TCT.GetGuard(C, this.AT) != null)
/*     */     {
/* 166 */       guardconn = (Expression)EcoreUtil.copy((EObject)TCT.GetGuard(C, this.AT));
/*     */     }
/* 168 */     CompositeAction upconn = ActionsFactory.eINSTANCE.createCompositeAction();
/* 169 */     if (TCT.GetUp(C, this.AT) != null)
/* 170 */       upconn = (CompositeAction)EcoreUtil.copy((EObject)TCT.GetUp(C, this.AT)); 
/* 171 */     CompositeAction downconn = ActionsFactory.eINSTANCE.createCompositeAction();
/* 172 */     if (TCT.GetDown(C, this.AT) != null) {
/* 173 */       downconn = (CompositeAction)EcoreUtil.copy((EObject)TCT.GetDown(C, this.AT));
/* 174 */       upconn.getContent().addAll((Collection)downconn.getContent());
/*     */     } 
/*     */     
/* 177 */     Transition NewT = BehaviorsFactory.eINSTANCE.createTransition();
/* 178 */     if (guardconn != null) {
/*     */       
/* 180 */       Expression EG = (Expression)EcoreUtil.copy((EObject)guardconn);
/* 181 */       NewT.setGuard(EG);
/*     */     } 
/* 183 */     if (upconn != null)
/*     */     {
/*     */       
/* 186 */       NewT.setAction((Action)upconn);
/*     */     }
/*     */ 
/*     */     
/* 190 */     for (Object o : LT) {
/*     */ 
/*     */       
/* 193 */       List<String> LStateName = new LinkedList<String>();
/* 194 */       Transition T = (Transition)o;
/* 195 */       List LOrigin = new LinkedList((Collection<?>)T.getOrigin());
/* 196 */       for (Object o3 : LOrigin) {
/*     */         
/* 198 */         State s = (State)o3;
/* 199 */         LStateName.add(String.valueOf(((Component)LComponent.get(LT.indexOf(o))).getName()) + s.getName());
/*     */       } 
/* 201 */       NewT.getOrigin().addAll(GetState(LStateName));
/* 202 */       LStateName.clear();
/* 203 */       List LDestination = new LinkedList((Collection<?>)T.getDestination());
/* 204 */       for (Object o3 : LDestination) {
/*     */         
/* 206 */         State s = (State)o3;
/* 207 */         LStateName.add(String.valueOf(((Component)LComponent.get(LT.indexOf(o))).getName()) + s.getName());
/*     */       } 
/*     */       
/* 210 */       NewT.getDestination().addAll(GetState(LStateName));
/* 211 */       LStateName.clear();
/*     */       
/* 213 */       List<CompositeAction> f = new LinkedList();
/* 214 */       List<Expression> g = new LinkedList();
/*     */       
/* 216 */       GetNewFG(LComponent.get(LT.indexOf(o)), T, f, g);
/*     */       
/* 218 */       if (f.size() != 0)
/*     */       {
/* 220 */         if (f.get(0) instanceof CompositeAction) {
/*     */           
/* 222 */           CompositeAction ca = f.get(0);
/* 223 */           ((CompositeAction)NewT.getAction()).getContent().addAll((Collection)ca.getContent());
/*     */         }
/* 225 */         else if (f.get(0) instanceof OpaqueElement) {
/*     */           
/* 227 */           OpaqueElement OE = (OpaqueElement)f.get(0);
/* 228 */           ((CompositeAction)NewT.getAction()).getContent().add(OE);
/*     */         }
/* 230 */         else if (f.get(0) instanceof Action) {
/* 231 */           ((CompositeAction)NewT.getAction()).getContent().add(f.get(0));
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 237 */       if (g.size() != 0) {
/* 238 */         if (NewT.getGuard() != null && g.get(0) != null) {
/* 239 */           BinaryExpression BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 240 */           BE.setLeftOperand(NewT.getGuard());
/* 241 */           BE.setRightOperand((Expression)EcoreUtil.copy((EObject)g.get(0)));
/* 242 */           BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/* 243 */           BE.setOperator(BO);
/* 244 */           NewT.setGuard((Expression)BE); continue;
/*     */         } 
/* 246 */         if (g.get(0) != null)
/*     */         {
/* 248 */           NewT.setGuard((Expression)EcoreUtil.copy((EObject)g.get(0)));
/*     */         }
/*     */       } 
/*     */     } 
/* 252 */     PN.getTransition().add(NewT);
/*     */     
/* 254 */     if (pd == null) {
/* 255 */       PortDefinition NewPd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 256 */       NewPd.setType(Init.PTSyn);
/*     */       
/* 258 */       NewPd.setName(String.valueOf(C.getName()) + I);
/* 259 */       I++;
/* 260 */       this.AT.getPortDefinition().add(NewPd);
/*     */       
/* 262 */       PortDefinitionReference pdr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 263 */       pdr.setTarget(NewPd);
/* 264 */       NewT.setTrigger((PortExpression)pdr);
/*     */     }
/*     */     else {
/*     */       
/* 268 */       this.AT.getPortDefinition().add(pd);
/*     */       
/* 270 */       PortDefinitionReference pdr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 271 */       pdr.setTarget(pd);
/* 272 */       NewT.setTrigger((PortExpression)pdr);
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
/*     */   private void GetNewFG(Component C, Transition t, List<EObject> f, List<EObject> g) {
/* 285 */     AtomType at = (AtomType)EcoreUtil.copy((EObject)C.getType());
/*     */     
/* 287 */     List<Variable> LVar = new LinkedList<Variable>((Collection<? extends Variable>)at.getVariable());
/* 288 */     for (Variable o : LVar) {
/*     */       
/* 290 */       Variable V = o;
/* 291 */       V.setName(String.valueOf(C.getName()) + V.getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 298 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>((Collection<? extends DataParameter>)at.getDataParameter());
/* 299 */     for (DataParameter o : LDataParameter) {
/*     */       
/* 301 */       DataParameter dp = o;
/* 302 */       dp.setName(String.valueOf(C.getName()) + "_" + dp.getName());
/*     */     } 
/*     */ 
/*     */     
/* 306 */     PetriNet PN = (PetriNet)at.getBehavior();
/* 307 */     for (Object o : PN.getTransition()) {
/*     */       
/* 309 */       Transition T = (Transition)o;
/* 310 */       PortDefinitionReference pdr = (PortDefinitionReference)T.getTrigger();
/* 311 */       PortDefinitionReference pdr1 = (PortDefinitionReference)t.getTrigger();
/*     */       
/* 313 */       if (pdr.getTarget().getName().equals(pdr1.getTarget().getName()) && StateEquale(t, T)) {
/*     */         
/* 315 */         if (T.getAction() != null)
/*     */         {
/* 317 */           f.add(EcoreUtil.copy((EObject)T.getAction()));
/*     */         }
/*     */         
/* 320 */         if (T.getGuard() != null) {
/* 321 */           g.add(EcoreUtil.copy((EObject)T.getGuard()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean StateEquale(Transition t1, Transition t2) {
/* 329 */     EList eList1 = t1.getOrigin();
/* 330 */     EList eList2 = t1.getDestination();
/* 331 */     EList<State> eList3 = t2.getOrigin();
/* 332 */     EList<State> eList4 = t2.getDestination();
/* 333 */     for (Object o : eList1) {
/*     */ 
/*     */       
/* 336 */       State s1 = (State)o;
/* 337 */       State s2 = eList3.get(eList1.indexOf(o));
/* 338 */       if (s1.getName() != s2.getName()) {
/* 339 */         return false;
/*     */       }
/*     */     } 
/* 342 */     for (Object o : eList2) {
/*     */       
/* 344 */       State s1 = (State)o;
/* 345 */       State s2 = eList4.get(eList2.indexOf(o));
/* 346 */       if (s1.getName() != s2.getName())
/* 347 */         return false; 
/*     */     } 
/* 349 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private List GetState(List<String> stateName) {
/* 354 */     List<EObject> LS = new LinkedList();
/* 355 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/*     */     
/* 357 */     for (Object o : PN.getState()) {
/*     */       
/* 359 */       State S = (State)o;
/* 360 */       if (stateName.indexOf(S.getName()) != -1)
/*     */       {
/* 362 */         LS.add(EcoreUtil.copy((EObject)S));
/*     */       }
/*     */     } 
/* 365 */     return LS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddPVS(CompoundType ct) {
/* 371 */     EList Lsc = ct.getSubcomponent();
/* 372 */     List<Component> LSC = new LinkedList<Component>((Collection<? extends Component>)Lsc);
/* 373 */     List<State> LS = new LinkedList<State>();
/* 374 */     List<Variable> LV = new LinkedList<Variable>();
/* 375 */     List<PortDefinition> LPD = new LinkedList<PortDefinition>();
/* 376 */     List<State> IS = new LinkedList();
/* 377 */     List<Action> IA = new LinkedList();
/* 378 */     List<Transition> T = new LinkedList<Transition>();
/* 379 */     List<Port> LPort = new LinkedList<Port>();
/* 380 */     CompositeAction CA = ActionsFactory.eINSTANCE.createCompositeAction();
/* 381 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/* 382 */     PN.setInitialization((Action)CA);
/* 383 */     for (Component o : LSC) {
/*     */       
/* 385 */       TComponent TC = new TComponent(o);
/* 386 */       TC.GetNewPVS(LPD, LV, LS, IS, IA, T, LPort);
/* 387 */       this.AT.getPortDefinition().addAll(LPD);
/* 388 */       this.AT.getVariable().addAll(LV);
/* 389 */       this.AT.getPort().addAll(LPort);
/* 390 */       PN.getState().addAll(LS);
/* 391 */       PN.getInitialState().addAll(IS);
/* 392 */       CA.getContent().addAll(IA);
/* 393 */       PN.getTransition().addAll(T);
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
/*     */   public void ProductListTransition(List<List> L, List<Transition> ElementList, List<List> NewList, List<Transition> LT) {
/* 412 */     for (Transition o : ElementList) {
/*     */       
/* 414 */       if (LT.size() == L.indexOf(ElementList)) {
/*     */         
/* 416 */         LT.add(L.indexOf(ElementList), o);
/* 417 */         if (L.size() - 1 == L.indexOf(ElementList)) {
/* 418 */           NewList.add(new LinkedList<Transition>(LT));
/*     */         }
/*     */       } else {
/*     */         
/* 422 */         LT.set(L.indexOf(ElementList), o);
/* 423 */         if (L.size() - 1 == L.indexOf(ElementList))
/* 424 */           NewList.add(new LinkedList<Transition>(LT)); 
/*     */       } 
/* 426 */       if (L.indexOf(ElementList) < L.size() - 1)
/* 427 */         ProductListTransition(L, L.get(L.indexOf(ElementList) + 1), NewList, LT); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\TAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */