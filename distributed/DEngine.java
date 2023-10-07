/*     */ package distributed;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ 
/*     */ public class DEngine {
/*  35 */   private List<List<Variable>> LVariableConnector = new LinkedList<List<Variable>>(); private List<DConnector> LDConnector;
/*  36 */   private List<List<Variable>> LVariableConnectorEng = new LinkedList<List<Variable>>();
/*  37 */   private List<DCompPort> LDCompPort = new LinkedList<DCompPort>();
/*  38 */   private List<Component> LComponent = new LinkedList<Component>();
/*     */   private AtomType Engine;
/*     */   private PortDefinition PDIntern;
/*  41 */   static int engineitt = 0;
/*  42 */   private List<Port> LPortInter = new LinkedList<Port>();
/*     */ 
/*     */ 
/*     */   
/*     */   public DEngine(List<DConnector> LDConnector) {
/*  47 */     this.LDConnector = LDConnector;
/*  48 */     InitEngine();
/*  49 */     setLVariableConnectorEng();
/*  50 */     setLComponent();
/*  51 */     setLDCompPort();
/*  52 */     setEngine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void InitEngine() {
/*  57 */     this.Engine = BehaviorsFactory.eINSTANCE.createAtomType();
/*     */     
/*  59 */     this.Engine.setName("Engine" + engineitt++);
/*  60 */     this.PDIntern = TransformationFunction.CreatePortDefinition("Intern_Initialize", DCompoundType.InternPortType, this.Engine);
/*  61 */     PetriNet PN = BehaviorsFactory.eINSTANCE.createPetriNet();
/*  62 */     this.Engine.setBehavior((Behavior)PN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLVariableConnectorEng() {
/*  69 */     for (DConnector o : this.LDConnector) {
/*     */       
/*  71 */       DConnector DConn = o;
/*  72 */       Connector Conn = DConn.getConnector();
/*  73 */       List<Variable> LVarEng = new LinkedList<Variable>();
/*  74 */       List<Variable> LVar = new LinkedList<Variable>();
/*  75 */       for (Object o1 : Conn.getType().getVariable()) {
/*     */         
/*  77 */         Variable Vconn = (Variable)o1;
/*  78 */         Variable Veng = (Variable)EcoreUtil.copy((EObject)Vconn);
/*  79 */         Veng.setName(String.valueOf(Vconn.getName()) + "_" + Conn.getName());
/*  80 */         this.Engine.getVariable().add(Veng);
/*  81 */         LVar.add(Vconn);
/*  82 */         LVarEng.add(Veng);
/*     */       } 
/*  84 */       this.LVariableConnector.add(LVar);
/*  85 */       this.LVariableConnectorEng.add(LVarEng);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setEngine() {
/*  95 */     Expression EguardValPort = null;
/*  96 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/*  97 */     for (DConnector o : this.LDConnector) {
/*     */       
/*  99 */       DConnector DConn = o;
/* 100 */       List<Port> LPortconn = DConn.getPort();
/* 101 */       List<Component> LComponent = DConn.getLCompenent();
/* 102 */       List<State> FromState = new LinkedList<State>();
/* 103 */       List<State> ToState = new LinkedList<State>();
/* 104 */       List<Variable> LBoolVar = new LinkedList<Variable>();
/* 105 */       int ittport = 0;
/* 106 */       for (Port o1 : LPortconn) {
/*     */         
/* 108 */         Port Pconn = o1;
/* 109 */         Component Comp = LComponent.get(ittport);
/* 110 */         ittport++;
/* 111 */         DCompPort CompPort = getCompPort(Pconn, Comp);
/* 112 */         State s1 = CompPort.getCorrespondClassStateEng(Pconn);
/* 113 */         State s2 = CompPort.getCorrespondStateEng(Pconn);
/* 114 */         Variable v = CompPort.getBooleanVar(Pconn);
/* 115 */         FromState.add(s1);
/* 116 */         ToState.add(s2);
/* 117 */         LBoolVar.add(v);
/*     */       } 
/*     */       
/* 120 */       EguardValPort = TransformationFunction.CreateAndExpression(LBoolVar);
/* 121 */       Expression connguard = getGuardConnector(DConn);
/* 122 */       Expression transconguard = TransformationFunction.AndOfTwoExpression(EguardValPort, connguard);
/* 123 */       Action connactionget = FunctionConnector(DConn);
/* 124 */       TransformationFunction.CreateTransitionLState(this.PDIntern, transconguard, connactionget, FromState, ToState, PN);
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
/*     */   private Expression getGuardConnector(DConnector conn) {
/* 142 */     Connector c = conn.getConnector();
/* 143 */     int indexDconn = this.LDConnector.indexOf(conn);
/* 144 */     List<Variable> VarConn = this.LVariableConnector.get(indexDconn);
/* 145 */     List<Variable> VarConnEng = this.LVariableConnectorEng.get(indexDconn);
/* 146 */     EList<InteractionSpecification> eList = c.getType().getInteractionSpecification();
/* 147 */     if (eList.size() != 0) {
/*     */       
/* 149 */       InteractionSpecification IS = eList.get(0);
/* 150 */       Expression guardbefore = IS.getGuard();
/* 151 */       if (guardbefore != null) {
/*     */         
/* 153 */         Expression guardafter = (Expression)EcoreUtil.copy((EObject)guardbefore);
/* 154 */         ReplaceExpression(c, guardafter, VarConn, VarConnEng, this.LDCompPort);
/* 155 */         return guardafter;
/*     */       } 
/*     */     } 
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Action FunctionConnector(DConnector conn) {
/* 163 */     CompositeAction CAUP = ActionsFactory.eINSTANCE.createCompositeAction();
/* 164 */     Connector c = conn.getConnector();
/* 165 */     int indexDconn = this.LDConnector.indexOf(conn);
/* 166 */     List<Variable> VarConn = this.LVariableConnector.get(indexDconn);
/* 167 */     List<Variable> VarConnEng = this.LVariableConnectorEng.get(indexDconn);
/* 168 */     EList<InteractionSpecification> eList = c.getType().getInteractionSpecification();
/* 169 */     if (eList.size() != 0) {
/*     */ 
/*     */       
/* 172 */       InteractionSpecification IS = eList.get(0);
/* 173 */       if (IS.getUpAction() instanceof CompositeAction) {
/*     */         
/* 175 */         CompositeAction UPA = (CompositeAction)EcoreUtil.copy((EObject)IS.getUpAction());
/* 176 */         if (UPA.getContent().size() > 0) {
/*     */           
/* 178 */           List LAction = new LinkedList((Collection<?>)UPA.getContent());
/* 179 */           for (Object o1 : LAction) {
/*     */             
/* 181 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 183 */               AssignmentAction AA = (AssignmentAction)o1;
/* 184 */               ReplaceExpression(c, AA.getAssignedValue(), VarConn, VarConnEng, this.LDCompPort);
/* 185 */               ReplaceExpression1(c, (Expression)AA.getAssignedTarget(), VarConn, VarConnEng, this.LDCompPort);
/* 186 */               CAUP.getContent().add(AA); continue;
/*     */             } 
/* 188 */             if (o1 instanceof FunctionCallExpression) {
/*     */               
/* 190 */               FunctionCallExpression Fcall = (FunctionCallExpression)EcoreUtil.copy((EObject)o1);
/* 191 */               for (Object o : Fcall.getActualData()) {
/*     */                 
/* 193 */                 Expression E1 = (Expression)o;
/* 194 */                 ReplaceExpression1(c, E1, VarConn, VarConnEng, this.LDCompPort);
/*     */               } 
/* 196 */               CAUP.getContent().add(Fcall);
/*     */               
/*     */               continue;
/*     */             } 
/* 200 */             System.out.println("To do Up");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 205 */       if (IS.getDownAction() instanceof CompositeAction) {
/*     */         
/* 207 */         Action DOA = (Action)EcoreUtil.copy((EObject)IS.getDownAction());
/* 208 */         CompositeAction CADO = (CompositeAction)DOA;
/* 209 */         if (CADO.getContent().size() > 0) {
/*     */           
/* 211 */           List LAction = new LinkedList((Collection<?>)CADO.getContent());
/* 212 */           for (Object o1 : LAction) {
/*     */ 
/*     */             
/* 215 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 217 */               AssignmentAction AA = (AssignmentAction)o1;
/* 218 */               ReplaceExpression(c, AA.getAssignedValue(), VarConn, VarConnEng, this.LDCompPort);
/* 219 */               ReplaceExpression1(c, (Expression)AA.getAssignedTarget(), VarConn, VarConnEng, this.LDCompPort);
/* 220 */               CAUP.getContent().add(AA); continue;
/*     */             } 
/* 222 */             if (o1 instanceof FunctionCallExpression) {
/*     */ 
/*     */               
/* 225 */               FunctionCallExpression Fcall = (FunctionCallExpression)EcoreUtil.copy((EObject)o1);
/* 226 */               for (Object o : Fcall.getActualData()) {
/*     */                 
/* 228 */                 Expression E1 = (Expression)o;
/* 229 */                 ReplaceExpression1(c, E1, VarConn, VarConnEng, this.LDCompPort);
/*     */               } 
/* 231 */               CAUP.getContent().add(Fcall);
/*     */               
/*     */               continue;
/*     */             } 
/* 235 */             System.out.println("To do Down");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 241 */     String s = "Execution of Interaction : " + c.getName() + "\\n";
/* 242 */     CAUP.getContent().add(TransformationFunction.CreateFucntionPrint(s));
/* 243 */     return (Action)CAUP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DCompPort getCompPort(Port pconn, Component comp) {
/* 250 */     for (DCompPort o : this.LDCompPort) {
/*     */       
/* 252 */       DCompPort CompPort = o;
/* 253 */       Component Comp = CompPort.getComponent();
/* 254 */       List<List<Port>> LPort = CompPort.getLPortCent();
/* 255 */       for (Object<Port> o1 : LPort) {
/*     */         
/* 257 */         List<Port> LP = (List<Port>)o1;
/* 258 */         for (Port o2 : LP) {
/*     */           
/* 260 */           Port p = o2;
/* 261 */           if (p.equals(pconn) && Comp.equals(comp)) {
/* 262 */             return CompPort;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getEngineName() {
/* 272 */     String engineName = new String("Engine");
/* 273 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 275 */       DConnector DConn = o;
/* 276 */       engineName = String.valueOf(engineName) + "_" + DConn.getConnector().getName();
/*     */     } 
/* 278 */     return engineName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLComponent() {
/* 284 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 286 */       DConnector DConn = o;
/* 287 */       DList.AddListUnique(DConn.getLCompenent(), this.LComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ReplaceExpression(Connector conn, Expression E, List<Variable> VarConn, List<Variable> VarConnEng, List<DCompPort> LcompPort) {
/* 294 */     Variable v = null;
/* 295 */     if (E instanceof RequiredDataParameterReference || E instanceof VariableReference) {
/*     */       
/* 297 */       if (E instanceof RequiredDataParameterReference) {
/*     */         
/* 299 */         RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/* 300 */         List<Integer> LIndexPortVar = TransformationFunction.getIndexPortVar(conn.getType(), rdr);
/* 301 */         int IndexPort = ((Integer)LIndexPortVar.get(0)).intValue();
/* 302 */         int IndexVar = ((Integer)LIndexPortVar.get(1)).intValue();
/*     */         
/* 304 */         InnerPortReference ipr = (InnerPortReference)conn.getActualPort().get(IndexPort);
/* 305 */         Component CompCent = (Component)ipr.getTargetInstance().getTargetPart();
/* 306 */         Port PortCent = ipr.getTargetPort();
/* 307 */         for (DCompPort o : LcompPort) {
/*     */           
/* 309 */           DCompPort compport = o;
/* 310 */           if (compport.getComponent().equals(CompCent)) {
/*     */             
/* 312 */             Port p = compport.getCorrespondPortEng(PortCent);
/* 313 */             if (p != null)
/*     */             {
/* 315 */               DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 316 */               PortDefinition pd = db.getDefinition();
/* 317 */               v = (Variable)pd.getExposedVariable().get(IndexVar);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 322 */       } else if (E instanceof VariableReference) {
/*     */         
/* 324 */         VariableReference vref = (VariableReference)E;
/* 325 */         Variable vconn = vref.getTargetVariable();
/* 326 */         v = VarConnEng.get(VarConn.indexOf(vconn));
/*     */       } 
/* 328 */       VariableReference vrengine = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 329 */       vrengine.setTargetVariable(v);
/* 330 */       Expression expression = E;
/*     */       
/* 332 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*     */         
/* 334 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/* 335 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), vrengine);
/*     */       }
/* 337 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*     */         
/* 339 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/* 340 */         if (bexp.getLeftOperand().equals(E))
/* 341 */           bexp.setLeftOperand((Expression)vrengine); 
/* 342 */         if (bexp.getRightOperand().equals(E)) {
/* 343 */           bexp.setRightOperand((Expression)vrengine);
/*     */         }
/* 345 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/* 351 */         if (aa.getAssignedTarget().equals(E))
/* 352 */           aa.setAssignedTarget((DataReference)vrengine); 
/* 353 */         if (aa.getAssignedValue().equals(E)) {
/* 354 */           aa.setAssignedValue((Expression)vrengine);
/*     */         }
/* 356 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*     */         
/* 358 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/* 359 */         UE.setOperand((Expression)vrengine);
/*     */       }
/* 361 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*     */         
/* 363 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/* 364 */         ispectmp.setGuard((Expression)vrengine);
/*     */       }
/*     */     
/* 367 */     } else if (E instanceof FunctionCallExpression) {
/*     */       
/* 369 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 370 */       for (Object o : Fcall.getActualData())
/*     */       {
/* 372 */         Expression E1 = (Expression)o;
/* 373 */         ReplaceExpression(conn, E1, VarConn, VarConnEng, LcompPort);
/*     */       }
/*     */     
/* 376 */     } else if (E instanceof BinaryExpression) {
/*     */       
/* 378 */       BinaryExpression BE = (BinaryExpression)E;
/* 379 */       ReplaceExpression(conn, BE.getRightOperand(), VarConn, VarConnEng, LcompPort);
/* 380 */       ReplaceExpression(conn, BE.getLeftOperand(), VarConn, VarConnEng, LcompPort);
/*     */     }
/* 382 */     else if (E instanceof UnaryExpression) {
/*     */       
/* 384 */       UnaryExpression UE = (UnaryExpression)E;
/* 385 */       ReplaceExpression(conn, UE.getOperand(), VarConn, VarConnEng, LcompPort);
/*     */     } else {
/* 387 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ReplaceExpression1(Connector conn, Expression E, List<Variable> VarConn, List<Variable> VarConnEng, List<DCompPort> LcompPort) {
/* 398 */     Variable v = null;
/* 399 */     if (E instanceof RequiredDataParameterReference || E instanceof VariableReference) {
/*     */       
/* 401 */       if (E instanceof RequiredDataParameterReference) {
/*     */         
/* 403 */         RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/* 404 */         List<Integer> LIndexPortVar = TransformationFunction.getIndexPortVar(conn.getType(), rdr);
/* 405 */         int IndexPort = ((Integer)LIndexPortVar.get(0)).intValue();
/* 406 */         int IndexVar = ((Integer)LIndexPortVar.get(1)).intValue();
/*     */         
/* 408 */         InnerPortReference ipr = (InnerPortReference)conn.getActualPort().get(IndexPort);
/* 409 */         Component CompCent = (Component)ipr.getTargetInstance().getTargetPart();
/* 410 */         Port PortCent = ipr.getTargetPort();
/* 411 */         for (DCompPort o : LcompPort) {
/*     */           
/* 413 */           DCompPort compport = o;
/* 414 */           if (compport.getComponent().equals(CompCent)) {
/*     */             
/* 416 */             Port p = compport.getCorrespondPortEng(PortCent);
/* 417 */             if (p != null)
/*     */             {
/* 419 */               DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 420 */               PortDefinition pd = db.getDefinition();
/* 421 */               v = (Variable)pd.getExposedVariable().get(IndexVar);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 426 */       } else if (E instanceof VariableReference) {
/*     */         
/* 428 */         VariableReference vref = (VariableReference)E;
/* 429 */         Variable vconn = vref.getTargetVariable();
/* 430 */         v = VarConnEng.get(VarConn.indexOf(vconn));
/*     */       } 
/*     */ 
/*     */       
/* 434 */       VariableReference vrengine = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 435 */       vrengine.setTargetVariable(v);
/* 436 */       Expression expression = E;
/* 437 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*     */         
/* 439 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/* 440 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), vrengine);
/*     */       }
/* 442 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*     */         
/* 444 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/* 445 */         if (bexp.getLeftOperand().equals(E))
/* 446 */           bexp.setLeftOperand((Expression)vrengine); 
/* 447 */         if (bexp.getRightOperand().equals(E)) {
/* 448 */           bexp.setRightOperand((Expression)vrengine);
/*     */         }
/* 450 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 455 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/* 456 */         if (aa.getAssignedTarget().equals(E))
/* 457 */           aa.setAssignedTarget((DataReference)vrengine); 
/* 458 */         if (aa.getAssignedValue().equals(E)) {
/* 459 */           aa.setAssignedValue((Expression)vrengine);
/*     */         }
/* 461 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*     */         
/* 463 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/* 464 */         UE.setOperand((Expression)vrengine);
/*     */       }
/* 466 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*     */         
/* 468 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/* 469 */         ispectmp.setGuard((Expression)vrengine);
/*     */       }
/*     */     
/* 472 */     } else if (E instanceof FunctionCallExpression) {
/*     */       
/* 474 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 475 */       for (Object o : Fcall.getActualData())
/*     */       {
/* 477 */         Expression E1 = (Expression)o;
/* 478 */         ReplaceExpression(conn, E1, VarConn, VarConnEng, LcompPort);
/*     */       }
/*     */     
/* 481 */     } else if (E instanceof BinaryExpression) {
/*     */       
/* 483 */       BinaryExpression BE = (BinaryExpression)E;
/* 484 */       ReplaceExpression(conn, BE.getRightOperand(), VarConn, VarConnEng, LcompPort);
/* 485 */       ReplaceExpression(conn, BE.getLeftOperand(), VarConn, VarConnEng, LcompPort);
/*     */     }
/* 487 */     else if (E instanceof UnaryExpression) {
/*     */       
/* 489 */       UnaryExpression UE = (UnaryExpression)E;
/* 490 */       ReplaceExpression(conn, UE.getOperand(), VarConn, VarConnEng, LcompPort);
/*     */     } else {
/* 492 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
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
/*     */   private List<List<Port>> getPortComp(Component C) {
/* 511 */     List<Port> LPort = new LinkedList<Port>();
/* 512 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 514 */       DConnector DConn = o;
/* 515 */       int index = DConn.getLCompenent().indexOf(C);
/* 516 */       if (index != -1) {
/*     */         
/* 518 */         Port port = DConn.getPort().get(index);
/* 519 */         DList.AddUnique(port, LPort);
/*     */       } 
/*     */     } 
/* 522 */     AtomType ATCent = (AtomType)C.getType();
/* 523 */     DAtomType DAT = getDAtomType(DCompoundType.LDAtomType, ATCent);
/* 524 */     return DAT.getSetClassofPorts(LPort);
/*     */   }
/*     */ 
/*     */   
/*     */   private DAtomType getDAtomType(List<DAtomType> LAT, AtomType CAT) {
/* 529 */     for (DAtomType o : LAT) {
/*     */       
/* 531 */       DAtomType DAT = o;
/* 532 */       if (DAT.getCAT().equals(CAT))
/* 533 */         return DAT; 
/*     */     } 
/* 535 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLDCompPort() {
/* 546 */     for (Component o : this.LComponent) {
/*     */       
/* 548 */       Component comp = o;
/* 549 */       List<List<Port>> LConfPort = getPortComp(comp);
/* 550 */       AtomType ATCent = (AtomType)comp.getType();
/* 551 */       DAtomType DAT = getDAtomType(DCompoundType.LDAtomType, ATCent);
/* 552 */       List<Port> LConflictPort = DAT.getClassSetPort(LConfPort);
/* 553 */       List<Variable> LVariable = DAT.getVariable(LConfPort);
/* 554 */       DCompPort compport = new DCompPort(comp, LConfPort, LConflictPort, LVariable, this.Engine);
/* 555 */       this.LDCompPort.add(compport);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DConnector> getLDConnector() {
/* 561 */     return this.LDConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DCompPort> getLDCompPort() {
/* 566 */     return this.LDCompPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getEngine() {
/* 571 */     return this.Engine;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLComponent() {
/* 576 */     return this.LComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLVariableConnector() {
/* 581 */     return this.LVariableConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLVariableConnectorEng() {
/* 586 */     return this.LVariableConnectorEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public PortDefinition getPortInter() {
/* 591 */     return this.PDIntern;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPortInter() {
/* 596 */     return this.LPortInter;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */