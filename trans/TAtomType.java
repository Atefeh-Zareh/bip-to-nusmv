/*     */ package trans;
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
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ 
/*     */ public class TAtomType {
/*  35 */   static int I = 0;
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
/*  47 */     this.AT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  48 */     this.AT.setBehavior((Behavior)BehaviorsFactory.eINSTANCE.createPetriNet());
/*  49 */     this.AT.setName("TOPBIP2");
/*  50 */     AddPVS(CT);
/*  51 */     AddTransition(CT);
/*  52 */     this.AT.getDataParameter().addAll(DP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddTransition(CompoundType ct) {
/*  58 */     EList Lconn = ct.getConnector();
/*  59 */     for (Object o : Lconn) {
/*     */       
/*  61 */       Connector C = (Connector)o;
/*  62 */       TConnectorType TCT = new TConnectorType(C.getType());
/*  63 */       this.AT.getVariable().addAll(TCT.GetNewVariable(C));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  69 */       CreateNewTransition(C, TCT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateNewTransition(Connector C, TConnectorType TCT) {
/*  77 */     String PortDefinitionName = new String();
/*  78 */     EList eList = C.getActualPort();
/*  79 */     List<List> LList = new LinkedList<List>();
/*  80 */     List<List> newLList = new LinkedList<List>();
/*  81 */     List<Transition> ltmp = new LinkedList<Transition>();
/*  82 */     List<Component> LComponent = new LinkedList<Component>();
/*     */     
/*  84 */     for (ActualPortParameter o : eList) {
/*     */       
/*  86 */       assert o instanceof InnerPortReference;
/*  87 */       InnerPortReference ipr = (InnerPortReference)o;
/*  88 */       DefinitionBinding db = (DefinitionBinding)ipr.getTargetPort().getBinding();
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
/* 104 */       String PortName = db.getDefinition().getName();
/* 105 */       String NewPortName = String.valueOf(ipr.getTargetInstance().getTargetPart().getName()) + db.getDefinition().getName();
/* 106 */       AtomType at = (AtomType)((Component)ipr.getTargetInstance().getTargetPart()).getType();
/* 107 */       LComponent.add((Component)ipr.getTargetInstance().getTargetPart());
/* 108 */       if (TCT.GetTransition(PortName, at).size() != 0)
/* 109 */         LList.add(TCT.GetTransition(PortName, at)); 
/* 110 */       PortDefinitionName = String.valueOf(PortDefinitionName) + NewPortName;
/*     */     } 
/*     */     
/* 113 */     if (LList.size() != 0) {
/*     */       
/* 115 */       ProductListTransition(LList, LList.get(0), newLList, ltmp);
/* 116 */       List<List> newLListtmp = new LinkedList<List>(newLList);
/* 117 */       for (List o : newLListtmp) {
/*     */         
/* 119 */         List LT = new LinkedList(o);
/* 120 */         CreateTransition(LT, LComponent, PortDefinitionName, C, TCT);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getCorrespondVariableName(String variableNameAtomNew) {
/* 129 */     for (Object o : this.AT.getVariable()) {
/*     */       
/* 131 */       Variable v = (Variable)o;
/* 132 */       if (v.getName() == variableNameAtomNew)
/* 133 */         return v; 
/*     */     } 
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateTransition(List LT, List<Component> LComponent, String PortDefinitionName, Connector C, TConnectorType TCT) {
/* 141 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/* 142 */     Expression guardconn = null;
/* 143 */     if (TCT.GetGuard(C, this.AT) != null) {
/* 144 */       guardconn = (Expression)EcoreUtil.copy((EObject)TCT.GetGuard(C, this.AT));
/*     */     }
/* 146 */     CompositeAction upconn = ActionsFactory.eINSTANCE.createCompositeAction();
/* 147 */     if (TCT.GetUp(C, this.AT) != null)
/* 148 */       upconn = (CompositeAction)EcoreUtil.copy((EObject)TCT.GetUp(C, this.AT)); 
/* 149 */     CompositeAction downconn = ActionsFactory.eINSTANCE.createCompositeAction();
/* 150 */     if (TCT.GetDown(C, this.AT) != null) {
/* 151 */       downconn = (CompositeAction)EcoreUtil.copy((EObject)TCT.GetDown(C, this.AT));
/* 152 */       upconn.getContent().addAll((Collection)downconn.getContent());
/*     */     } 
/*     */     
/* 155 */     Transition NewT = BehaviorsFactory.eINSTANCE.createTransition();
/* 156 */     if (guardconn != null) {
/*     */       
/* 158 */       Expression EG = (Expression)EcoreUtil.copy((EObject)guardconn);
/* 159 */       NewT.setGuard(EG);
/*     */     } 
/* 161 */     if (upconn != null)
/*     */     {
/*     */       
/* 164 */       NewT.setAction((Action)upconn);
/*     */     }
/* 166 */     for (Object o : LT) {
/*     */ 
/*     */       
/* 169 */       List<String> LStateName = new LinkedList<String>();
/* 170 */       Transition T = (Transition)o;
/* 171 */       List LOrigin = new LinkedList((Collection<?>)T.getOrigin());
/* 172 */       for (Object o3 : LOrigin) {
/*     */         
/* 174 */         State s = (State)o3;
/* 175 */         LStateName.add(String.valueOf(((Component)LComponent.get(LT.indexOf(o))).getName()) + s.getName());
/*     */       } 
/* 177 */       NewT.getOrigin().addAll(GetState(LStateName));
/* 178 */       LStateName.clear();
/* 179 */       List LDestination = new LinkedList((Collection<?>)T.getDestination());
/* 180 */       for (Object o3 : LDestination) {
/*     */         
/* 182 */         State s = (State)o3;
/* 183 */         LStateName.add(String.valueOf(((Component)LComponent.get(LT.indexOf(o))).getName()) + s.getName());
/*     */       } 
/*     */       
/* 186 */       NewT.getDestination().addAll(GetState(LStateName));
/* 187 */       LStateName.clear();
/*     */       
/* 189 */       List<CompositeAction> f = new LinkedList();
/* 190 */       List<Expression> g = new LinkedList();
/*     */       
/* 192 */       GetNewFG(LComponent.get(LT.indexOf(o)), T, f, g);
/*     */       
/* 194 */       if (f.size() != 0)
/*     */       {
/* 196 */         if (f.get(0) instanceof CompositeAction) {
/*     */           
/* 198 */           CompositeAction ca = f.get(0);
/* 199 */           ((CompositeAction)NewT.getAction()).getContent().addAll((Collection)ca.getContent());
/*     */         }
/* 201 */         else if (f.get(0) instanceof OpaqueElement) {
/*     */           
/* 203 */           OpaqueElement OE = (OpaqueElement)f.get(0);
/* 204 */           ((CompositeAction)NewT.getAction()).getContent().add(OE);
/*     */         }
/* 206 */         else if (f.get(0) instanceof Action) {
/* 207 */           ((CompositeAction)NewT.getAction()).getContent().add(f.get(0));
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 213 */       if (g.size() != 0) {
/* 214 */         if (NewT.getGuard() != null && g.get(0) != null) {
/* 215 */           BinaryExpression BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 216 */           BE.setLeftOperand(NewT.getGuard());
/* 217 */           BE.setRightOperand((Expression)EcoreUtil.copy((EObject)g.get(0)));
/* 218 */           BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/* 219 */           BE.setOperator(BO);
/* 220 */           NewT.setGuard((Expression)BE); continue;
/*     */         } 
/* 222 */         if (g.get(0) != null)
/*     */         {
/* 224 */           NewT.setGuard((Expression)EcoreUtil.copy((EObject)g.get(0)));
/*     */         }
/*     */       } 
/*     */     } 
/* 228 */     PN.getTransition().add(NewT);
/* 229 */     PortDefinition NewPd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 230 */     NewPd.setType(Init.PTSyn);
/*     */     
/* 232 */     NewPd.setName(String.valueOf(C.getName()) + I);
/* 233 */     I++;
/* 234 */     this.AT.getPortDefinition().add(NewPd);
/* 235 */     PortDefinitionReference pdr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 236 */     pdr.setTarget(NewPd);
/* 237 */     NewT.setTrigger((PortExpression)pdr);
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
/* 249 */     AtomType at = (AtomType)EcoreUtil.copy((EObject)C.getType());
/*     */     
/* 251 */     List<Variable> LVar = new LinkedList<Variable>((Collection<? extends Variable>)at.getVariable());
/* 252 */     for (Variable o : LVar) {
/*     */       
/* 254 */       Variable V = o;
/* 255 */       V.setName(String.valueOf(C.getName()) + V.getName());
/*     */     } 
/*     */ 
/*     */     
/* 259 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>((Collection<? extends DataParameter>)at.getDataParameter());
/* 260 */     for (DataParameter o : LDataParameter) {
/*     */       
/* 262 */       DataParameter dp = o;
/* 263 */       dp.setName(String.valueOf(C.getName()) + "_" + dp.getName());
/*     */     } 
/*     */ 
/*     */     
/* 267 */     PetriNet PN = (PetriNet)at.getBehavior();
/* 268 */     for (Object o : PN.getTransition()) {
/*     */       
/* 270 */       Transition T = (Transition)o;
/* 271 */       PortDefinitionReference pdr = (PortDefinitionReference)T.getTrigger();
/* 272 */       PortDefinitionReference pdr1 = (PortDefinitionReference)t.getTrigger();
/*     */       
/* 274 */       if (pdr.getTarget().getName().equals(pdr1.getTarget().getName()) && StateEquale(t, T)) {
/*     */         
/* 276 */         if (T.getAction() != null) {
/* 277 */           f.add(EcoreUtil.copy((EObject)T.getAction()));
/*     */         }
/* 279 */         if (T.getGuard() != null) {
/* 280 */           g.add(EcoreUtil.copy((EObject)T.getGuard()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean StateEquale(Transition t1, Transition t2) {
/* 288 */     EList eList1 = t1.getOrigin();
/* 289 */     EList eList2 = t1.getDestination();
/* 290 */     EList<State> eList3 = t2.getOrigin();
/* 291 */     EList<State> eList4 = t2.getDestination();
/* 292 */     for (Object o : eList1) {
/*     */ 
/*     */       
/* 295 */       State s1 = (State)o;
/* 296 */       State s2 = eList3.get(eList1.indexOf(o));
/* 297 */       if (s1.getName() != s2.getName()) {
/* 298 */         return false;
/*     */       }
/*     */     } 
/* 301 */     for (Object o : eList2) {
/*     */       
/* 303 */       State s1 = (State)o;
/* 304 */       State s2 = eList4.get(eList2.indexOf(o));
/* 305 */       if (s1.getName() != s2.getName())
/* 306 */         return false; 
/*     */     } 
/* 308 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private List GetState(List<String> stateName) {
/* 313 */     List<EObject> LS = new LinkedList();
/* 314 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/*     */     
/* 316 */     for (Object o : PN.getState()) {
/*     */       
/* 318 */       State S = (State)o;
/* 319 */       if (stateName.indexOf(S.getName()) != -1)
/*     */       {
/* 321 */         LS.add(EcoreUtil.copy((EObject)S));
/*     */       }
/*     */     } 
/* 324 */     return LS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddPVS(CompoundType ct) {
/* 330 */     EList Lsc = ct.getSubcomponent();
/* 331 */     List<Component> LSC = new LinkedList<Component>((Collection<? extends Component>)Lsc);
/* 332 */     List<State> LS = new LinkedList<State>();
/* 333 */     List<Variable> LV = new LinkedList<Variable>();
/* 334 */     List<PortDefinition> LPD = new LinkedList<PortDefinition>();
/* 335 */     List<State> IS = new LinkedList();
/* 336 */     List<Action> IA = new LinkedList();
/* 337 */     List<Transition> T = new LinkedList<Transition>();
/* 338 */     CompositeAction CA = ActionsFactory.eINSTANCE.createCompositeAction();
/* 339 */     PetriNet PN = (PetriNet)this.AT.getBehavior();
/* 340 */     PN.setInitialization((Action)CA);
/* 341 */     for (Component o : LSC) {
/*     */       
/* 343 */       TComponent TC = new TComponent(o);
/* 344 */       TC.GetNewPVS(LPD, LV, LS, IS, IA, T);
/* 345 */       this.AT.getPortDefinition().addAll(LPD);
/* 346 */       this.AT.getVariable().addAll(LV);
/* 347 */       PN.getState().addAll(LS);
/* 348 */       PN.getInitialState().addAll(IS);
/* 349 */       CA.getContent().addAll(IA);
/* 350 */       PN.getTransition().addAll(T);
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
/*     */   public void ProductListTransition(List<List> L, List<Transition> ElementList, List<List> NewList, List<Transition> LT) {
/* 367 */     for (Transition o : ElementList) {
/*     */       
/* 369 */       if (LT.size() == L.indexOf(ElementList)) {
/*     */         
/* 371 */         LT.add(L.indexOf(ElementList), o);
/* 372 */         if (L.size() - 1 == L.indexOf(ElementList)) {
/* 373 */           NewList.add(new LinkedList<Transition>(LT));
/*     */         }
/*     */       } else {
/*     */         
/* 377 */         LT.set(L.indexOf(ElementList), o);
/* 378 */         if (L.size() - 1 == L.indexOf(ElementList))
/* 379 */           NewList.add(new LinkedList<Transition>(LT)); 
/*     */       } 
/* 381 */       if (L.indexOf(ElementList) < L.size() - 1)
/* 382 */         ProductListTransition(L, L.get(L.indexOf(ElementList) + 1), NewList, LT); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\TAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */