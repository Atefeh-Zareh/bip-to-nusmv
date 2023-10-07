/*      */ package BIPTransformation;
/*      */ 
/*      */ import distributed.DList;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import trans.CmdLineError;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*      */ import ujf.verimag.bip.Core.Behaviors.State;
/*      */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*      */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*      */ import ujf.verimag.bip.Core.Interactions.Part;
/*      */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.Modules.ModulesFactory;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ import ujf.verimag.bip.Core.Modules.Root;
/*      */ import ujf.verimag.bip.Core.Modules.System;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*      */ import ujf.verimag.bip.bip2src.Reverse;
/*      */ import ujf.verimag.bip.parser.ErrorMessage;
/*      */ import ujf.verimag.bip.parser.actions.Parser;
/*      */ 
/*      */ 
/*      */ public class TransformationFunction
/*      */ {
/*      */   public static PortType PTSyn;
/*      */   public static ConnectorType ConnSyn;
/*   83 */   protected static BehaviorsFactory behavFactory = BehaviorsFactory.eINSTANCE;
/*   84 */   protected static InteractionsFactory interFactory = InteractionsFactory.eINSTANCE;
/*   85 */   protected static ActionsFactory actionFactory = ActionsFactory.eINSTANCE;
/*   86 */   protected static ExpressionsFactory expressionFactory = ExpressionsFactory.eINSTANCE;
/*   87 */   protected static PortExpressionsFactory portexpressionFactory = PortExpressionsFactory.eINSTANCE;
/*   88 */   protected static ModulesFactory moduleFactory = ModulesFactory.eINSTANCE;
/*   89 */   protected static Root top = null;
/*      */   
/*      */   public static void Initialize(Module m) {
/*   92 */     PTSyn = behavFactory.createPortType();
/*   93 */     setConnSynType();
/*   94 */     PTSyn.setName("SynEPort1");
/*   95 */     PTSyn.setModule(m);
/*   96 */     ConnSyn.setModule(m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setConnSynType() {
/*  104 */     ConnSyn = interFactory.createConnectorType();
/*  105 */     ConnSyn.setName("ConnSynPrint");
/*  106 */     PortParameter PortPara = interFactory.createPortParameter();
/*  107 */     PortPara.setType(PTSyn);
/*  108 */     PortPara.setName("p");
/*  109 */     ConnSyn.getPortParameter().add(PortPara);
/*  110 */     PortParameterReference PortParaRef = interFactory.createPortParameterReference();
/*  111 */     PortParaRef.setTarget(PortPara);
/*  112 */     ACFusion portdef = portexpressionFactory.createACFusion();
/*  113 */     portdef.getOperand().add(PortParaRef);
/*  114 */     ConnSyn.setDefinition((PortExpression)portdef);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Variable getCopyVariable(Variable v, String NewName) {
/*  124 */     Variable vcopy = (Variable)EcoreUtil.copy((EObject)v);
/*  125 */     vcopy.setName(NewName);
/*  126 */     return vcopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Variable> CreateGuardVariable(List<String> LName) {
/*  138 */     List<Variable> LVariable = new LinkedList<Variable>();
/*  139 */     for (String name_i : LName) {
/*      */       
/*  141 */       String Name = name_i;
/*  142 */       Variable BVariable = behavFactory.createVariable();
/*  143 */       OpaqueElement Oelem = moduleFactory.createOpaqueElement();
/*  144 */       BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/*  145 */       BL.setBValue(false);
/*  146 */       Oelem.setBody("bool");
/*  147 */       BVariable.setType((DataType)Oelem);
/*  148 */       BVariable.setName(Name);
/*  149 */       BVariable.setInitialValue((Expression)BL);
/*  150 */       LVariable.add(BVariable);
/*      */     } 
/*  152 */     return LVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static VariableReference CreateVariableReference(Variable v) {
/*  161 */     VariableReference vr = expressionFactory.createVariableReference();
/*  162 */     vr.setTargetVariable(v);
/*  163 */     return vr;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<DataParameter> CreateDataParamter(List<Variable> LVariable) {
/*  169 */     int i = 0;
/*  170 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>();
/*  171 */     for (Variable o : LVariable) {
/*      */       
/*  173 */       Variable v = o;
/*  174 */       DataParameter dp = BehaviorsFactory.eINSTANCE.createDataParameter();
/*  175 */       dp.setType(v.getType());
/*  176 */       dp.setName("x" + i);
/*  177 */       i++;
/*  178 */       LDataParameter.add(dp);
/*      */     } 
/*  180 */     return LDataParameter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void SetTransitionInitialState(List<Transition> LTransition, State s1) {
/*  187 */     for (Transition o : LTransition) {
/*      */       
/*  189 */       Transition t = o;
/*  190 */       t.getOrigin().clear();
/*  191 */       t.getOrigin().add(s1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Variable CreateGuardVariable(String Name, boolean initialvalue) {
/*  203 */     Variable BVariable = behavFactory.createVariable();
/*  204 */     OpaqueElement Oelem = moduleFactory.createOpaqueElement();
/*  205 */     BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/*  206 */     BL.setBValue(initialvalue);
/*  207 */     Oelem.setBody("bool");
/*  208 */     BVariable.setType((DataType)Oelem);
/*  209 */     BVariable.setName(Name);
/*  210 */     BVariable.setInitialValue((Expression)BL);
/*  211 */     return BVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void SetInitialValueBoolVar(Variable var, boolean value) {
/*  220 */     BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/*  221 */     BL.setBValue(value);
/*  222 */     var.setInitialValue((Expression)BL);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Variable CreateIntVariable(String Name, int initialvalue) {
/*  233 */     Variable BVariable = behavFactory.createVariable();
/*  234 */     OpaqueElement Oelem = moduleFactory.createOpaqueElement();
/*  235 */     IntegerLiteral IL = expressionFactory.createIntegerLiteral();
/*  236 */     IL.setIValue(initialvalue);
/*  237 */     Oelem.setBody("int");
/*  238 */     BVariable.setType((DataType)Oelem);
/*  239 */     BVariable.setName(Name);
/*  240 */     BVariable.setInitialValue((Expression)IL);
/*  241 */     return BVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Variable CreateStringVariable(String Name, String initialvalue) {
/*  252 */     Variable SVariable = behavFactory.createVariable();
/*  253 */     OpaqueElement Oelem = moduleFactory.createOpaqueElement();
/*  254 */     StringLiteral SL = expressionFactory.createStringLiteral();
/*  255 */     SL.setSValue(initialvalue);
/*  256 */     Oelem.setBody("String");
/*  257 */     SVariable.setType((DataType)Oelem);
/*  258 */     SVariable.setName(Name);
/*  259 */     SVariable.setInitialValue((Expression)SL);
/*  260 */     return SVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Variable CreateVariable(String VarName, DataType VarType) {
/*  271 */     Variable Var = behavFactory.createVariable();
/*  272 */     Var.setName(VarName);
/*  273 */     Var.setType(VarType);
/*  274 */     return Var;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Variable> CreateCopyVariables(List<String> LVarName, List<Variable> LVariable) {
/*  284 */     List<Variable> LOutputVariable = new LinkedList<Variable>();
/*  285 */     for (Variable o : LVariable) {
/*  286 */       Variable v = o;
/*  287 */       Variable Var = behavFactory.createVariable();
/*  288 */       Var.setName(LVarName.get(LVariable.indexOf(o)));
/*  289 */       Var.setType(v.getType());
/*  290 */       LOutputVariable.add(Var);
/*      */     } 
/*  292 */     return LOutputVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType CreatePortType(List<DataParameter> LDP, String Name, Module module) {
/*  303 */     PortType porttype = behavFactory.createPortType();
/*  304 */     for (DataParameter o : LDP) {
/*  305 */       porttype.getDataParameter().add(o);
/*      */     }
/*  307 */     porttype.setName(Name);
/*  308 */     porttype.setModule(module);
/*  309 */     return porttype;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType CreatePortTypeFromVar(List<Variable> LVariable, String Name, Module module) {
/*  321 */     PortType porttype = behavFactory.createPortType();
/*  322 */     for (Variable o : LVariable) {
/*      */       
/*  324 */       Variable var = o;
/*  325 */       DataType dt = var.getType();
/*  326 */       DataParameter dp = behavFactory.createDataParameter();
/*  327 */       dp.setName(var.getName());
/*  328 */       dp.setType(dt);
/*  329 */       porttype.getDataParameter().add(dp);
/*      */     } 
/*  331 */     porttype.setName(Name);
/*  332 */     porttype.setModule(module);
/*  333 */     return porttype;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt, AtomType at) {
/*  340 */     Port port = behavFactory.createPort();
/*  341 */     PortDefinition pd = CreatePortDefinition(Name, pt, at);
/*  342 */     DefinitionBinding db = behavFactory.createDefinitionBinding();
/*  343 */     db.setDefinition(pd);
/*  344 */     port.setBinding((Binding)db);
/*  345 */     port.setName(Name);
/*  346 */     port.setType(pt);
/*  347 */     port.setComponentType((ComponentType)at);
/*  348 */     at.getPort().add(port);
/*  349 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt) {
/*  354 */     Port port = behavFactory.createPort();
/*  355 */     port.setName(Name);
/*  356 */     port.setType(pt);
/*  357 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Port CreatePort(PortDefinition pd) {
/*  362 */     Port port = behavFactory.createPort();
/*  363 */     DefinitionBinding db = behavFactory.createDefinitionBinding();
/*  364 */     db.setDefinition(pd);
/*  365 */     port.setBinding((Binding)db);
/*  366 */     port.setName(pd.getName());
/*  367 */     port.setType(pd.getType());
/*  368 */     return port;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt, AtomType at, List<Variable> LVariable) {
/*  374 */     Port port = behavFactory.createPort();
/*  375 */     PortDefinition pd = CreatePortDefinition(Name, pt, at);
/*  376 */     pd.getExposedVariable().addAll(LVariable);
/*  377 */     DefinitionBinding db = behavFactory.createDefinitionBinding();
/*  378 */     db.setDefinition(pd);
/*  379 */     port.setBinding((Binding)db);
/*  380 */     port.setName(Name);
/*  381 */     port.setType(pt);
/*  382 */     port.setComponentType((ComponentType)at);
/*  383 */     at.getPort().add(port);
/*  384 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt, List<Variable> LVariable) {
/*  389 */     Port port = behavFactory.createPort();
/*  390 */     PortDefinition pd = CreatePortDefinition(Name, pt);
/*  391 */     pd.getExposedVariable().addAll(LVariable);
/*  392 */     DefinitionBinding db = behavFactory.createDefinitionBinding();
/*  393 */     db.setDefinition(pd);
/*  394 */     port.setBinding((Binding)db);
/*  395 */     port.setName(Name);
/*  396 */     port.setType(pt);
/*  397 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static State getState(PetriNet PN, String StateName) {
/*  402 */     for (Object o : PN.getState()) {
/*      */       
/*  404 */       State s = (State)o;
/*  405 */       if (s.getName().equals(StateName))
/*  406 */         return s; 
/*      */     } 
/*  408 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompositeAction CreateInitializeCAGuard(List<Variable> LVariable, List<Expression> LGuardExpression) {
/*  419 */     CompositeAction CompAction = ActionsFactory.eINSTANCE.createCompositeAction();
/*  420 */     for (Variable variable_i : LVariable) {
/*      */       
/*  422 */       Variable variable = variable_i;
/*  423 */       AssignmentAction AssAction = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  424 */       VariableReference VarRef = expressionFactory.createVariableReference();
/*  425 */       VarRef.setTargetVariable(variable);
/*  426 */       AssAction.setAssignedTarget((DataReference)VarRef);
/*  427 */       int index = LVariable.indexOf(variable_i);
/*  428 */       AssAction.setAssignedValue(LGuardExpression.get(index));
/*  429 */       CompAction.getContent().add(AssAction);
/*      */     } 
/*  431 */     return CompAction;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType CreatePortType(String Name) {
/*  437 */     PortType PT = behavFactory.createPortType();
/*  438 */     PT.setName(Name);
/*  439 */     return PT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortDefinition CreatePortDefinition(String Name, PortType PT, AtomType AT) {
/*  445 */     PortDefinition PD = behavFactory.createPortDefinition();
/*  446 */     PD.setAtomType(AT);
/*  447 */     PD.setType(PT);
/*  448 */     PD.setName(Name);
/*  449 */     AT.getPortDefinition().add(PD);
/*  450 */     return PD;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PortDefinition CreatePortDefinition(String Name, PortType PT) {
/*  455 */     PortDefinition PD = behavFactory.createPortDefinition();
/*  456 */     PD.setType(PT);
/*  457 */     PD.setName(Name);
/*  458 */     return PD;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PortDefinitionReference CreatePortDefinitionReference(PortDefinition PD) {
/*  463 */     PortDefinitionReference PDR = behavFactory.createPortDefinitionReference();
/*  464 */     PDR.setTarget(PD);
/*  465 */     return PDR;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static State CreateState(String Name, List<Transition> IN, List<Transition> OUT) {
/*  471 */     State S = behavFactory.createState();
/*  472 */     S.setName(Name);
/*  473 */     for (Transition o : IN)
/*      */     {
/*  475 */       S.getIncoming().add(o);
/*      */     }
/*  477 */     for (Transition o : OUT)
/*      */     {
/*  479 */       S.getOutgoing().add(o);
/*      */     }
/*  481 */     return S;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static State CreateState(String Name, PetriNet PN) {
/*  488 */     State S = behavFactory.createState();
/*  489 */     S.setName(Name);
/*  490 */     PN.getState().add(S);
/*  491 */     return S;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Transition CreateTransition(PortDefinition PD, Expression guard, Action A, State s1, State s2, PetriNet PN) {
/*  497 */     Transition t = behavFactory.createTransition();
/*  498 */     PortDefinitionReference PDR = behavFactory.createPortDefinitionReference();
/*  499 */     PDR.setTarget(PD);
/*  500 */     t.setTrigger((PortExpression)PDR);
/*  501 */     t.setAction(A);
/*  502 */     t.setGuard(guard);
/*  503 */     t.getOrigin().add(s1);
/*  504 */     t.getDestination().add(s2);
/*  505 */     PN.getTransition().add(t);
/*  506 */     s1.getOutgoing().add(t);
/*  507 */     s2.getIncoming().add(t);
/*  508 */     return t;
/*      */   }
/*      */   
/*      */   public static Transition getLTransition(PetriNet PN, State s1, State s2) {
/*  512 */     for (Object o : PN.getTransition()) {
/*      */       
/*  514 */       Transition t = (Transition)o;
/*  515 */       if (((State)t.getOrigin().get(0)).equals(s1) && ((State)t.getDestination().get(0)).equals(s2))
/*  516 */         return t; 
/*      */     } 
/*  518 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Transition CreateTransitionLState(PortDefinition PD, Expression guard, Action A, List<State> Ls1, List<State> Ls2, PetriNet PN) {
/*  523 */     Transition t = behavFactory.createTransition();
/*  524 */     PortDefinitionReference PDR = behavFactory.createPortDefinitionReference();
/*  525 */     PDR.setTarget(PD);
/*  526 */     t.setTrigger((PortExpression)PDR);
/*  527 */     t.setAction(A);
/*  528 */     t.setGuard(guard);
/*  529 */     for (State o : Ls1) {
/*  530 */       State s1 = o;
/*  531 */       t.getOrigin().add(s1);
/*  532 */       s1.getOutgoing().add(t);
/*      */     } 
/*  534 */     for (State o : Ls2) {
/*  535 */       State s2 = o;
/*  536 */       t.getDestination().add(s2);
/*  537 */       s2.getIncoming().add(t);
/*      */     } 
/*  539 */     PN.getTransition().add(t);
/*  540 */     return t;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void RemoveGuardTransition(List<Transition> LTransition) {
/*  546 */     for (Transition o : LTransition) {
/*      */       
/*  548 */       Transition t = o;
/*  549 */       t.setGuard(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void CopyTransitionFromState(List<Transition> LTransition, State s1, State s2) {
/*  556 */     for (Transition o : LTransition) {
/*      */       
/*  558 */       Transition t = o;
/*  559 */       t.getOrigin().clear();
/*  560 */       t.getOrigin().add(s2);
/*  561 */       s1.getOutgoing().remove(t);
/*  562 */       s2.getOutgoing().add(t);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Connector> getConnectorfromPortComp(Port p, Component C, CompoundType CT) {
/*  568 */     List<Connector> LConn = new LinkedList<Connector>();
/*  569 */     for (Object o : CT.getConnector()) {
/*      */       
/*  571 */       Connector conn = (Connector)o;
/*  572 */       for (Object o1 : conn.getActualPort()) {
/*      */         
/*  574 */         InnerPortReference IPR = (InnerPortReference)o1;
/*  575 */         if (IPR.getTargetPort().equals(p) && IPR.getTargetInstance().getTargetPart().equals(C)) {
/*      */           
/*  577 */           LConn.add(conn);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  582 */     return LConn;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Variable> getCopyLVariable(List<Variable> LVariable) {
/*  587 */     List<Variable> LCopyVariable = new LinkedList<Variable>();
/*  588 */     for (Variable o : LVariable) {
/*      */       
/*  590 */       Variable v = o;
/*  591 */       Variable vcopy = CreateVariable(v.getName(), v.getType());
/*  592 */       LCopyVariable.add(vcopy);
/*      */     } 
/*  594 */     return LCopyVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<DataParameter> CreateDataParameter(List<Variable> LVariable) {
/*  601 */     int i = 0;
/*  602 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>();
/*  603 */     for (Variable o : LVariable) {
/*      */       
/*  605 */       Variable v = o;
/*  606 */       DataParameter dp = behavFactory.createDataParameter();
/*  607 */       dp.setType(v.getType());
/*  608 */       dp.setName("x" + i);
/*  609 */       i++;
/*  610 */       LDataParameter.add(dp);
/*      */     } 
/*  612 */     return LDataParameter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Expression> CorrespondingGuard(List<Transition> LTransition, List<String> LPortName) {
/*  624 */     List<Expression> LExpression = new LinkedList<Expression>();
/*      */     
/*  626 */     for (String o : LPortName) {
/*      */       
/*  628 */       String portname = o;
/*  629 */       boolean exist = false;
/*  630 */       for (Transition o1 : LTransition) {
/*      */         
/*  632 */         Transition transition = o1;
/*  633 */         PortDefinitionReference pdr = (PortDefinitionReference)transition.getTrigger();
/*  634 */         if (pdr.getTarget().getName().equals(portname)) {
/*      */           
/*  636 */           exist = true;
/*  637 */           Expression guard = transition.getGuard();
/*  638 */           if (guard == null) {
/*      */             
/*  640 */             BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/*  641 */             BL.setBValue(true);
/*  642 */             LExpression.add(BL);
/*      */             
/*      */             continue;
/*      */           } 
/*  646 */           LExpression.add(guard);
/*      */         } 
/*      */       } 
/*      */       
/*  650 */       if (!exist) {
/*      */         
/*  652 */         BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/*  653 */         BL.setBValue(false);
/*  654 */         LExpression.add(BL);
/*      */       } 
/*      */     } 
/*  657 */     return LExpression;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<AtomType> getAtomType(Module module) {
/*  662 */     List<AtomType> LAtomType = new LinkedList<AtomType>();
/*  663 */     for (Object o : module.getBipType()) {
/*      */       
/*  665 */       if (o instanceof AtomType)
/*      */       {
/*  667 */         LAtomType.add((AtomType)o);
/*      */       }
/*      */     } 
/*  670 */     return LAtomType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static List<String> getLPortName(List<Port> LPort) {
/*  677 */     List<String> LPortName = new LinkedList<String>();
/*  678 */     for (Port o : LPort) {
/*      */       
/*  680 */       Port p = o;
/*  681 */       LPortName.add(p.getName());
/*      */     } 
/*  683 */     return LPortName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getLVariableName(List<Variable> LVariable) {
/*  693 */     List<String> LVariableName = new LinkedList<String>();
/*  694 */     for (Variable o : LVariable) {
/*      */       
/*  696 */       Variable v = o;
/*  697 */       LVariableName.add(v.getName());
/*      */     } 
/*  699 */     return LVariableName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Variable> getLVariable(AtomType at, List<String> LVariableName) {
/*  711 */     List<Variable> LVariable = new LinkedList<Variable>();
/*  712 */     for (Object o : at.getVariable()) {
/*  713 */       Variable v = (Variable)o;
/*  714 */       if (LVariableName.contains(v.getName()))
/*  715 */         LVariable.add(v); 
/*      */     } 
/*  717 */     return LVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Variable> getVarPortEng(Port portCent, List<Variable> LVarCent, List<Variable> LVarDist) {
/*  723 */     List<Variable> LVariable = new LinkedList<Variable>();
/*  724 */     DefinitionBinding db = (DefinitionBinding)portCent.getBinding();
/*  725 */     EList eList = db.getDefinition().getExposedVariable();
/*  726 */     for (Object o : eList)
/*      */     {
/*  728 */       LVariable.add(LVarDist.get(LVarCent.indexOf(o)));
/*      */     }
/*  730 */     return LVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression CreateAndExpression(List<Variable> LVariable) {
/*  742 */     boolean firststep = true;
/*  743 */     boolean test = false;
/*  744 */     BinaryExpression binaryExpression1 = expressionFactory.createBinaryExpression();
/*  745 */     BinaryExpression BE = null;
/*  746 */     for (Variable o : LVariable) {
/*      */       VariableReference variableReference1;
/*  748 */       Variable v = o;
/*  749 */       VariableReference VarRef = expressionFactory.createVariableReference();
/*  750 */       VarRef.setTargetVariable(v);
/*  751 */       if (firststep) {
/*      */         
/*  753 */         variableReference1 = VarRef;
/*  754 */         firststep = false;
/*      */         
/*      */         continue;
/*      */       } 
/*  758 */       test = true;
/*  759 */       BE = expressionFactory.createBinaryExpression();
/*  760 */       BE.setLeftOperand((Expression)variableReference1);
/*  761 */       BE.setRightOperand((Expression)VarRef);
/*  762 */       BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  763 */       BE.setOperator(BO);
/*  764 */       binaryExpression1 = BE;
/*      */     } 
/*      */     
/*  767 */     if (!test) return (Expression)binaryExpression1; 
/*  768 */     return (Expression)BE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression AndOfTwoExpression(Expression e1, Expression e2) {
/*  780 */     if (e2 != null) {
/*      */       
/*  782 */       BinaryExpression BE = expressionFactory.createBinaryExpression();
/*  783 */       BE.setLeftOperand((Expression)EcoreUtil.copy((EObject)e1));
/*  784 */       BE.setRightOperand((Expression)EcoreUtil.copy((EObject)e2));
/*  785 */       BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  786 */       BE.setOperator(BO);
/*  787 */       return (Expression)BE;
/*      */     } 
/*  789 */     return e1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression OROfTwoExpression(Expression e1, Expression e2) {
/*  800 */     if (e2 != null) {
/*      */       
/*  802 */       BinaryExpression BE = expressionFactory.createBinaryExpression();
/*  803 */       BE.setLeftOperand((Expression)EcoreUtil.copy((EObject)e1));
/*  804 */       BE.setRightOperand((Expression)EcoreUtil.copy((EObject)e2));
/*  805 */       BinaryOperator BO = BinaryOperator.LOGICAL_OR;
/*  806 */       BE.setOperator(BO);
/*  807 */       return (Expression)BE;
/*      */     } 
/*  809 */     return e1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeSend(PortType PT) {
/*  816 */     ConnectorType ConnT = interFactory.createConnectorType();
/*  817 */     ConnT.setName("ConnTransferSR_" + PT.getName());
/*  818 */     PortParameter PortParaFrom = interFactory.createPortParameter();
/*  819 */     PortParaFrom.setType(PT);
/*  820 */     PortParaFrom.setName("p1");
/*  821 */     PortParameter PortParaTo = interFactory.createPortParameter();
/*  822 */     PortParaTo.setType(PT);
/*  823 */     PortParaTo.setName("p2");
/*  824 */     ConnT.getPortParameter().add(PortParaFrom);
/*  825 */     ConnT.getPortParameter().add(PortParaTo);
/*  826 */     PortParameterReference PortParaRefFrom = interFactory.createPortParameterReference();
/*  827 */     PortParaRefFrom.setTarget(PortParaFrom);
/*  828 */     PortParameterReference PortParaRefTo = interFactory.createPortParameterReference();
/*  829 */     PortParaRefTo.setTarget(PortParaTo);
/*      */ 
/*      */     
/*  832 */     ACFusion portdef = portexpressionFactory.createACFusion();
/*  833 */     ACTyping prottrigfrom = portexpressionFactory.createACTyping();
/*  834 */     prottrigfrom.setOperand((ACExpression)PortParaRefFrom);
/*  835 */     prottrigfrom.setType(ACTypingKind.TRIG);
/*  836 */     portdef.getOperand().add(prottrigfrom);
/*  837 */     portdef.getOperand().add(PortParaRefTo);
/*  838 */     ConnT.setDefinition((PortExpression)portdef);
/*      */ 
/*      */     
/*  841 */     InteractionSpecification Ispec = interFactory.createInteractionSpecification();
/*  842 */     Interaction Inter = interFactory.createInteraction();
/*  843 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  844 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefTo));
/*  845 */     Ispec.setInteraction(Inter);
/*  846 */     ConnT.getInteractionSpecification().add(Ispec);
/*      */ 
/*      */     
/*  849 */     if (PT.getDataParameter().size() != 0) {
/*      */       
/*  851 */       CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/*  852 */       CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/*  853 */       for (Object o1 : PT.getDataParameter()) {
/*      */         
/*  855 */         DataParameter DP = (DataParameter)o1;
/*      */         
/*  857 */         RequiredDataParameterReference rdprfrom = expressionFactory.createRequiredDataParameterReference();
/*  858 */         PortParameterReference ppreffrom = interFactory.createPortParameterReference();
/*  859 */         ppreffrom.setTarget(PortParaFrom);
/*  860 */         rdprfrom.setTargetParameter(DP);
/*  861 */         rdprfrom.setPortReference(ppreffrom);
/*      */ 
/*      */         
/*  864 */         RequiredDataParameterReference rdprto = expressionFactory.createRequiredDataParameterReference();
/*  865 */         PortParameterReference pprefto = interFactory.createPortParameterReference();
/*  866 */         pprefto.setTarget(PortParaTo);
/*  867 */         rdprto.setTargetParameter(DP);
/*  868 */         rdprto.setPortReference(pprefto);
/*      */ 
/*      */         
/*  871 */         AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  872 */         AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdprfrom));
/*  873 */         AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdprto));
/*  874 */         CAD.getContent().add(AAD);
/*      */       } 
/*  876 */       Ispec.setUpAction((Action)CAU);
/*  877 */       Ispec.setDownAction((Action)CAD);
/*      */     } 
/*      */ 
/*      */     
/*  881 */     InteractionSpecification Ispec1 = interFactory.createInteractionSpecification();
/*  882 */     Interaction Inter1 = interFactory.createInteraction();
/*  883 */     Inter1.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  884 */     Ispec1.setInteraction(Inter1);
/*  885 */     ConnT.getInteractionSpecification().add(Ispec1);
/*  886 */     BooleanLiteral bl = expressionFactory.createBooleanLiteral();
/*  887 */     bl.setBValue(false);
/*  888 */     Ispec1.setGuard((Expression)bl);
/*      */     
/*  890 */     PT.getModule().getBipType().add(ConnT);
/*  891 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeSendReceive(PortType PT) {
/*  905 */     ConnectorType ConnT = interFactory.createConnectorType();
/*  906 */     ConnT.setName("ConnTransfer_" + PT.getName());
/*  907 */     PortParameter PortParaFrom = interFactory.createPortParameter();
/*  908 */     PortParaFrom.setType(PT);
/*  909 */     PortParaFrom.setName("p1");
/*  910 */     PortParameter PortParaTo = interFactory.createPortParameter();
/*  911 */     PortParaTo.setType(PT);
/*  912 */     PortParaTo.setName("p2");
/*  913 */     ConnT.getPortParameter().add(PortParaFrom);
/*  914 */     ConnT.getPortParameter().add(PortParaTo);
/*  915 */     PortParameterReference PortParaRefFrom = interFactory.createPortParameterReference();
/*  916 */     PortParaRefFrom.setTarget(PortParaFrom);
/*  917 */     PortParameterReference PortParaRefTo = interFactory.createPortParameterReference();
/*  918 */     PortParaRefTo.setTarget(PortParaTo);
/*      */ 
/*      */     
/*  921 */     ACFusion portdef = portexpressionFactory.createACFusion();
/*  922 */     portdef.getOperand().add(PortParaRefFrom);
/*  923 */     portdef.getOperand().add(PortParaRefTo);
/*  924 */     ConnT.setDefinition((PortExpression)portdef);
/*      */ 
/*      */     
/*  927 */     InteractionSpecification Ispec = interFactory.createInteractionSpecification();
/*  928 */     Interaction Inter = interFactory.createInteraction();
/*  929 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  930 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefTo));
/*  931 */     Ispec.setInteraction(Inter);
/*  932 */     ConnT.getInteractionSpecification().add(Ispec);
/*      */ 
/*      */     
/*  935 */     if (PT.getDataParameter().size() != 0) {
/*      */       
/*  937 */       CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/*  938 */       CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/*  939 */       for (Object o1 : PT.getDataParameter()) {
/*      */         
/*  941 */         DataParameter DP = (DataParameter)o1;
/*      */         
/*  943 */         RequiredDataParameterReference rdprfrom = expressionFactory.createRequiredDataParameterReference();
/*  944 */         PortParameterReference ppreffrom = interFactory.createPortParameterReference();
/*  945 */         ppreffrom.setTarget(PortParaFrom);
/*  946 */         rdprfrom.setTargetParameter(DP);
/*  947 */         rdprfrom.setPortReference(ppreffrom);
/*      */ 
/*      */         
/*  950 */         RequiredDataParameterReference rdprto = expressionFactory.createRequiredDataParameterReference();
/*  951 */         PortParameterReference pprefto = interFactory.createPortParameterReference();
/*  952 */         pprefto.setTarget(PortParaTo);
/*  953 */         rdprto.setTargetParameter(DP);
/*  954 */         rdprto.setPortReference(pprefto);
/*      */ 
/*      */         
/*  957 */         AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  958 */         AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdprfrom));
/*  959 */         AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdprto));
/*  960 */         CAD.getContent().add(AAD);
/*      */       } 
/*  962 */       Ispec.setUpAction((Action)CAU);
/*  963 */       Ispec.setDownAction((Action)CAD);
/*      */     } 
/*  965 */     PT.getModule().getBipType().add(ConnT);
/*  966 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeMultipleTrigger(List<PortType> LPortType, PortType porttypexport) {
/*  980 */     Map<PortType, PortParameterReference> Map_PT_PPR = new HashMap<PortType, PortParameterReference>();
/*  981 */     List<List<Variable>> LLVariable = new LinkedList<List<Variable>>();
/*  982 */     List<Variable> LAllVariable = new LinkedList<Variable>();
/*  983 */     ConnectorType ConnT = interFactory.createConnectorType();
/*  984 */     ConnT.setName("ConnTypeMultipleTrigger");
/*  985 */     int index = 0;
/*  986 */     int index1 = 0;
/*      */     
/*  988 */     ACFusion expressioninteraction = portexpressionFactory.createACFusion();
/*  989 */     for (PortType o : LPortType) {
/*      */       
/*  991 */       PortType PT = o;
/*  992 */       PortParameter PortPara = interFactory.createPortParameter();
/*  993 */       PortPara.setType(PT);
/*  994 */       PortPara.setName("p" + index++);
/*  995 */       ConnT.getPortParameter().add(PortPara);
/*  996 */       PortParameterReference PortParaRef = interFactory.createPortParameterReference();
/*  997 */       PortParaRef.setTarget(PortPara);
/*  998 */       Map_PT_PPR.put(PT, PortParaRef);
/*      */ 
/*      */       
/* 1001 */       ACTyping aCTyping = portexpressionFactory.createACTyping();
/* 1002 */       aCTyping.setOperand((ACExpression)PortParaRef);
/* 1003 */       aCTyping.setType(ACTypingKind.TRIG);
/* 1004 */       expressioninteraction.getOperand().add(aCTyping);
/*      */       
/* 1006 */       List<Variable> LVariable = new LinkedList<Variable>();
/* 1007 */       for (Object o2 : PT.getDataParameter()) {
/* 1008 */         DataParameter DP = (DataParameter)o2;
/* 1009 */         Variable connectorVar = CreateVariable("var" + index1++, DP.getType());
/* 1010 */         ConnT.getVariable().add(connectorVar);
/* 1011 */         LVariable.add(connectorVar);
/* 1012 */         LAllVariable.add(connectorVar);
/*      */       } 
/* 1014 */       LLVariable.add(LVariable);
/*      */     } 
/* 1016 */     ConnT.setDefinition((PortExpression)expressioninteraction);
/*      */     
/* 1018 */     List<List<PortType>> LCombinaisonPortType = new LinkedList<List<PortType>>();
/* 1019 */     LCombinaisonPortType = DList.getAllCombinaison(LPortType);
/* 1020 */     for (int i = LCombinaisonPortType.size() - 1; i >= 0; i--) {
/* 1021 */       List<PortType> LPortTypeInteraction = LCombinaisonPortType.get(i);
/* 1022 */       InteractionSpecification Ispec = interFactory.createInteractionSpecification();
/* 1023 */       Interaction Inter = interFactory.createInteraction();
/*      */       
/* 1025 */       CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/* 1026 */       CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/* 1027 */       for (PortType o1 : LPortTypeInteraction) {
/* 1028 */         PortType porttype = o1;
/* 1029 */         Inter.getPort().add(EcoreUtil.copy((EObject)Map_PT_PPR.get(porttype)));
/* 1030 */         Ispec.setInteraction(Inter);
/* 1031 */         ConnT.getInteractionSpecification().add(Ispec);
/*      */         
/* 1033 */         for (Object o2 : porttype.getDataParameter()) {
/*      */           
/* 1035 */           DataParameter DP = (DataParameter)o2;
/*      */           
/* 1037 */           RequiredDataParameterReference rdpr = expressionFactory.createRequiredDataParameterReference();
/* 1038 */           rdpr.setTargetParameter(DP);
/* 1039 */           rdpr.setPortReference(getCopy(Map_PT_PPR.get(porttype)));
/*      */ 
/*      */           
/* 1042 */           int indexport = LPortType.indexOf(porttype);
/* 1043 */           int indexdp = porttype.getDataParameter().indexOf(DP);
/* 1044 */           Variable var = ((List<Variable>)LLVariable.get(indexport)).get(indexdp);
/* 1045 */           VariableReference varref = CreateVariableReference(var);
/*      */ 
/*      */           
/* 1048 */           AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1049 */           AAD.setAssignedValue((Expression)rdpr);
/* 1050 */           AAD.setAssignedTarget((DataReference)varref);
/* 1051 */           CAU.getContent().add(AAD);
/*      */         } 
/* 1053 */         Ispec.setUpAction((Action)CAU);
/* 1054 */         Ispec.setDownAction((Action)CAD);
/*      */       } 
/*      */     } 
/*      */     
/* 1058 */     Port port = CreatePort("syncMonitor", porttypexport, LAllVariable);
/* 1059 */     ConnT.setPortDefinition(getPortDefinition(port));
/* 1060 */     ConnT.setPort(port);
/* 1061 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeMultipleSync(List<PortType> LPortType, PortType porttypexport) {
/* 1075 */     Map<PortType, PortParameterReference> Map_PT_PPR = new HashMap<PortType, PortParameterReference>();
/* 1076 */     List<List<Variable>> LLVariable = new LinkedList<List<Variable>>();
/* 1077 */     List<Variable> LAllVariable = new LinkedList<Variable>();
/* 1078 */     ConnectorType ConnT = interFactory.createConnectorType();
/* 1079 */     ConnT.setName("ConnTypeMultipleSync");
/* 1080 */     int index = 0;
/* 1081 */     int index1 = 0;
/*      */     
/* 1083 */     ACFusion expressioninteraction = portexpressionFactory.createACFusion();
/* 1084 */     for (PortType o : LPortType) {
/*      */       
/* 1086 */       PortType PT = o;
/* 1087 */       PortParameter PortPara = interFactory.createPortParameter();
/* 1088 */       PortPara.setType(PT);
/* 1089 */       PortPara.setName("p" + index++);
/* 1090 */       ConnT.getPortParameter().add(PortPara);
/* 1091 */       PortParameterReference PortParaRef = interFactory.createPortParameterReference();
/* 1092 */       PortParaRef.setTarget(PortPara);
/* 1093 */       Map_PT_PPR.put(PT, PortParaRef);
/*      */       
/* 1095 */       expressioninteraction.getOperand().add(PortParaRef);
/*      */       
/* 1097 */       List<Variable> LVariable = new LinkedList<Variable>();
/* 1098 */       for (Object o2 : PT.getDataParameter()) {
/* 1099 */         DataParameter DP = (DataParameter)o2;
/* 1100 */         Variable connectorVar = CreateVariable("var" + index1++, DP.getType());
/* 1101 */         ConnT.getVariable().add(connectorVar);
/* 1102 */         LVariable.add(connectorVar);
/* 1103 */         LAllVariable.add(connectorVar);
/*      */       } 
/* 1105 */       LLVariable.add(LVariable);
/*      */     } 
/* 1107 */     ConnT.setDefinition((PortExpression)expressioninteraction);
/*      */     
/* 1109 */     List<PortType> LPortTypeInteraction = LPortType;
/* 1110 */     InteractionSpecification Ispec = interFactory.createInteractionSpecification();
/* 1111 */     Interaction Inter = interFactory.createInteraction();
/*      */     
/* 1113 */     CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/* 1114 */     CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/* 1115 */     for (PortType o1 : LPortTypeInteraction) {
/* 1116 */       PortType porttype = o1;
/* 1117 */       Inter.getPort().add(EcoreUtil.copy((EObject)Map_PT_PPR.get(porttype)));
/* 1118 */       Ispec.setInteraction(Inter);
/* 1119 */       ConnT.getInteractionSpecification().add(Ispec);
/*      */       
/* 1121 */       for (Object o2 : porttype.getDataParameter()) {
/*      */         
/* 1123 */         DataParameter DP = (DataParameter)o2;
/*      */         
/* 1125 */         RequiredDataParameterReference rdpr = expressionFactory.createRequiredDataParameterReference();
/* 1126 */         rdpr.setTargetParameter(DP);
/* 1127 */         rdpr.setPortReference(getCopy(Map_PT_PPR.get(porttype)));
/*      */ 
/*      */         
/* 1130 */         int indexport = LPortType.indexOf(porttype);
/* 1131 */         int indexdp = porttype.getDataParameter().indexOf(DP);
/* 1132 */         Variable var = ((List<Variable>)LLVariable.get(indexport)).get(indexdp);
/* 1133 */         VariableReference varref = CreateVariableReference(var);
/*      */ 
/*      */         
/* 1136 */         AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1137 */         AAD.setAssignedValue((Expression)rdpr);
/* 1138 */         AAD.setAssignedTarget((DataReference)varref);
/* 1139 */         CAU.getContent().add(AAD);
/*      */       } 
/* 1141 */       Ispec.setUpAction((Action)CAU);
/* 1142 */       Ispec.setDownAction((Action)CAD);
/*      */     } 
/*      */     
/* 1145 */     Port port = CreatePort("syncMonitor", porttypexport, LAllVariable);
/* 1146 */     ConnT.setPortDefinition(getPortDefinition(port));
/* 1147 */     ConnT.setPort(port);
/* 1148 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Integer> getIndexPortVar(ConnectorType ct, RequiredDataParameterReference rdpr) {
/* 1160 */     List<Integer> LIndexPortVar = new LinkedList<Integer>();
/* 1161 */     DataParameter dp = rdpr.getTargetParameter();
/* 1162 */     PortParameter pp = rdpr.getPortReference().getTarget();
/* 1163 */     for (Object o : ct.getPortParameter()) {
/*      */       
/* 1165 */       PortParameter pptmp = (PortParameter)o;
/* 1166 */       if (pptmp.equals(pp))
/*      */       {
/* 1168 */         for (Object o1 : pp.getType().getDataParameter()) {
/*      */           
/* 1170 */           DataParameter dptmp = (DataParameter)o1;
/* 1171 */           if (dptmp.equals(dp)) {
/*      */             
/* 1173 */             LIndexPortVar.add(Integer.valueOf(ct.getPortParameter().indexOf(pp)));
/* 1174 */             LIndexPortVar.add(Integer.valueOf(pp.getType().getDataParameter().indexOf(dp)));
/* 1175 */             return LIndexPortVar;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1180 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Action CreateFucntionPrint(String s) {
/* 1188 */     FunctionCallExpression FCE = expressionFactory.createFunctionCallExpression();
/* 1189 */     StringLiteral SL = expressionFactory.createStringLiteral();
/* 1190 */     SL.setSValue("\"" + s + "\"");
/* 1191 */     FCE.setFunctionName("printf");
/* 1192 */     FCE.getActualData().add(SL);
/* 1193 */     return (Action)FCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FunctionCallExpression CreateFunctionCall(String FunctionName, List<Variable> LVariable) {
/* 1205 */     FunctionCallExpression FCE = expressionFactory.createFunctionCallExpression();
/* 1206 */     FCE.setFunctionName(FunctionName);
/* 1207 */     for (Variable o : LVariable) {
/*      */       
/* 1209 */       Variable v = o;
/* 1210 */       VariableReference VR = expressionFactory.createVariableReference();
/* 1211 */       VR.setTargetVariable(v);
/* 1212 */       FCE.getActualData().add(VR);
/*      */     } 
/* 1214 */     return FCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static IfAction CreateIfAction(Expression condition, Action ifcase, Action elsecase) {
/* 1225 */     IfAction ifaction = actionFactory.createIfAction();
/* 1226 */     ifaction.setCondition(condition);
/* 1227 */     ifaction.setIfCase(ifcase);
/* 1228 */     ifaction.setElseCase(elsecase);
/* 1229 */     return ifaction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Component> getLComponent(Connector c) {
/* 1236 */     List<Component> LComponent = new LinkedList<Component>();
/* 1237 */     for (Object o : c.getActualPort()) {
/*      */       
/* 1239 */       InnerPortReference ipr = (InnerPortReference)o;
/* 1240 */       LComponent.add((Component)ipr.getTargetInstance().getTargetPart());
/*      */     } 
/* 1242 */     return LComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void ExpressionReplace(Expression E, List<PortParameter> LPortParameter, List<List<Variable>> LLVariable) {
/* 1255 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/* 1257 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/* 1258 */       int indexPortPara = LPortParameter.indexOf(rdr.getPortReference().getTarget());
/* 1259 */       int indexVarPara = ((PortParameter)LPortParameter.get(indexPortPara)).getType().getDataParameter().indexOf(rdr.getTargetParameter());
/*      */       
/* 1261 */       VariableReference VarRef = expressionFactory.createVariableReference();
/* 1262 */       Variable Var = ((List<Variable>)LLVariable.get(indexPortPara)).get(indexVarPara);
/* 1263 */       VarRef.setTargetVariable(Var);
/* 1264 */       Expression expression = E;
/* 1265 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*      */         
/* 1267 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/* 1268 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), VarRef);
/*      */       }
/* 1270 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*      */         
/* 1272 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/* 1273 */         if (bexp.getLeftOperand().equals(E))
/* 1274 */           bexp.setLeftOperand((Expression)VarRef); 
/* 1275 */         if (bexp.getRightOperand().equals(E)) {
/* 1276 */           bexp.setRightOperand((Expression)VarRef);
/*      */         }
/* 1278 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*      */         
/* 1280 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/* 1281 */         if (aa.getAssignedTarget().equals(E))
/* 1282 */           aa.setAssignedTarget((DataReference)VarRef); 
/* 1283 */         if (aa.getAssignedValue().equals(E)) {
/* 1284 */           aa.setAssignedValue((Expression)VarRef);
/*      */         }
/* 1286 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*      */         
/* 1288 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/* 1289 */         UE.setOperand((Expression)VarRef);
/*      */       }
/* 1291 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*      */         
/* 1293 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/* 1294 */         ispectmp.setGuard((Expression)VarRef);
/*      */       }
/*      */     
/* 1297 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/* 1299 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 1300 */       for (Object o : Fcall.getActualData())
/*      */       {
/* 1302 */         Expression E1 = (Expression)o;
/* 1303 */         ExpressionReplace(E1, LPortParameter, LLVariable);
/*      */       }
/*      */     
/* 1306 */     } else if (E instanceof BinaryExpression) {
/*      */       
/* 1308 */       BinaryExpression BE = (BinaryExpression)E;
/* 1309 */       ExpressionReplace(BE.getRightOperand(), LPortParameter, LLVariable);
/* 1310 */       ExpressionReplace(BE.getLeftOperand(), LPortParameter, LLVariable);
/*      */     }
/* 1312 */     else if (E instanceof UnaryExpression) {
/*      */       
/* 1314 */       UnaryExpression UE = (UnaryExpression)E;
/* 1315 */       ExpressionReplace(UE.getOperand(), LPortParameter, LLVariable);
/*      */     } else {
/* 1317 */       E instanceof IfAction;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void ExpressionReplace(Expression E, List<PortParameter> LPortParameter, List<List<Variable>> LLVariable, List<Variable> LVarBefore, List<Variable> LVarAfter) {
/* 1334 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/* 1336 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/* 1337 */       int indexPortPara = LPortParameter.indexOf(rdr.getPortReference().getTarget());
/* 1338 */       int indexVarPara = ((PortParameter)LPortParameter.get(indexPortPara)).getType().getDataParameter().indexOf(rdr.getTargetParameter());
/*      */       
/* 1340 */       VariableReference VarRef = expressionFactory.createVariableReference();
/* 1341 */       Variable Var = ((List<Variable>)LLVariable.get(indexPortPara)).get(indexVarPara);
/* 1342 */       VarRef.setTargetVariable(Var);
/* 1343 */       Expression expression = E;
/* 1344 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*      */         
/* 1346 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/* 1347 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), VarRef);
/*      */       }
/* 1349 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*      */         
/* 1351 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/* 1352 */         if (bexp.getLeftOperand().equals(E))
/* 1353 */           bexp.setLeftOperand((Expression)VarRef); 
/* 1354 */         if (bexp.getRightOperand().equals(E)) {
/* 1355 */           bexp.setRightOperand((Expression)VarRef);
/*      */         }
/* 1357 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*      */         
/* 1359 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/* 1360 */         if (aa.getAssignedTarget().equals(E))
/* 1361 */           aa.setAssignedTarget((DataReference)VarRef); 
/* 1362 */         if (aa.getAssignedValue().equals(E)) {
/* 1363 */           aa.setAssignedValue((Expression)VarRef);
/*      */         }
/* 1365 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*      */         
/* 1367 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/* 1368 */         UE.setOperand((Expression)VarRef);
/*      */       }
/* 1370 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*      */         
/* 1372 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/* 1373 */         ispectmp.setGuard((Expression)VarRef);
/*      */       }
/*      */     
/* 1376 */     } else if (E instanceof VariableReference) {
/* 1377 */       VariableReference varRef = (VariableReference)E;
/* 1378 */       Variable var = varRef.getTargetVariable();
/* 1379 */       int indexOfVariable = LVarBefore.indexOf(var);
/* 1380 */       Variable varnew = LVarAfter.get(indexOfVariable);
/* 1381 */       varRef.setTargetVariable(varnew);
/*      */     }
/* 1383 */     else if (E instanceof FunctionCallExpression) {
/*      */       
/* 1385 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 1386 */       for (Object o : Fcall.getActualData())
/*      */       {
/* 1388 */         Expression E1 = (Expression)o;
/* 1389 */         ExpressionReplace(E1, LPortParameter, LLVariable);
/*      */       }
/*      */     
/* 1392 */     } else if (E instanceof BinaryExpression) {
/*      */       
/* 1394 */       BinaryExpression BE = (BinaryExpression)E;
/* 1395 */       ExpressionReplace(BE.getRightOperand(), LPortParameter, LLVariable);
/* 1396 */       ExpressionReplace(BE.getLeftOperand(), LPortParameter, LLVariable);
/*      */     }
/* 1398 */     else if (E instanceof UnaryExpression) {
/*      */       
/* 1400 */       UnaryExpression UE = (UnaryExpression)E;
/* 1401 */       ExpressionReplace(UE.getOperand(), LPortParameter, LLVariable);
/*      */     } else {
/* 1403 */       E instanceof IfAction;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Component CreateComponent(String name, ComponentType ct, CompoundType compoundType, List LParameter) {
/* 1413 */     Component comp = interFactory.createComponent();
/* 1414 */     comp.setName(name);
/* 1415 */     comp.setType(ct);
/* 1416 */     if (LParameter != null)
/*      */     {
/* 1418 */       for (Object o : LParameter) {
/*      */         
/* 1420 */         Expression parameter = (Expression)EcoreUtil.copy((EObject)o);
/* 1421 */         comp.getActualData().add(parameter);
/*      */       } 
/*      */     }
/* 1424 */     compoundType.getSubcomponent().add(comp);
/* 1425 */     return comp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Connector CreateConnector(String name, ConnectorType connType, CompoundType compoundType, List<Component> LComponent, List<Port> LPort) {
/* 1436 */     Connector connector = interFactory.createConnector();
/* 1437 */     connector.setName(name);
/* 1438 */     connector.setType(connType);
/* 1439 */     connector.setCompoundType(compoundType);
/*      */     
/* 1441 */     for (Component o : LComponent) {
/*      */       
/* 1443 */       Component comp = o;
/* 1444 */       Port p = LPort.get(LComponent.indexOf(o));
/* 1445 */       InnerPortReference ipr = interFactory.createInnerPortReference();
/* 1446 */       ipr.setTargetPort(p);
/* 1447 */       PartElementReference PE = interFactory.createPartElementReference();
/* 1448 */       PE.setTargetPart((Part)comp);
/* 1449 */       ipr.setTargetInstance(PE);
/* 1450 */       connector.getActualPort().add(ipr);
/*      */     } 
/* 1452 */     return connector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Connector createConnector(String name, ConnectorType connType, CompoundType compoundType, List<Part> LComponent, List<Port> LPort) {
/* 1463 */     Connector connector = interFactory.createConnector();
/* 1464 */     connector.setName(name);
/* 1465 */     connector.setType(connType);
/* 1466 */     connector.setCompoundType(compoundType);
/*      */     
/* 1468 */     for (Part o : LComponent) {
/*      */       
/* 1470 */       Port p = LPort.get(LComponent.indexOf(o));
/* 1471 */       InnerPortReference ipr = interFactory.createInnerPortReference();
/* 1472 */       ipr.setTargetPort(p);
/* 1473 */       PartElementReference PE = interFactory.createPartElementReference();
/* 1474 */       PE.setTargetPart(o);
/* 1475 */       ipr.setTargetInstance(PE);
/* 1476 */       connector.getActualPort().add(ipr);
/*      */     } 
/* 1478 */     return connector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String name, Component component, Port p1, CompoundType compoundType) {
/* 1489 */     Port p = behavFactory.createPort();
/* 1490 */     ExportBinding eb = interFactory.createExportBinding();
/* 1491 */     PartElementReference PER = interFactory.createPartElementReference();
/* 1492 */     PER.setTargetPart((Part)component);
/* 1493 */     eb.setTargetInstance(PER);
/* 1494 */     eb.setTargetPort(p1);
/* 1495 */     p.setBinding((Binding)eb);
/* 1496 */     p.setName(name);
/* 1497 */     p.setType(p1.getType());
/* 1498 */     p.setComponentType((ComponentType)compoundType);
/* 1499 */     compoundType.getPort().add(p);
/* 1500 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InteractionSpecification CreateInteractionSpecification(ConnectorType connType) {
/* 1510 */     InteractionSpecification intspec = interFactory.createInteractionSpecification();
/* 1511 */     Interaction inter = interFactory.createInteraction();
/* 1512 */     intspec.setInteraction(inter);
/* 1513 */     for (Object o : connType.getPortParameter()) {
/*      */       
/* 1515 */       PortParameter pp = (PortParameter)o;
/* 1516 */       PortParameterReference PortParaRef = interFactory.createPortParameterReference();
/* 1517 */       PortParaRef.setTarget(pp);
/* 1518 */       inter.getPort().add(PortParaRef);
/*      */     } 
/* 1520 */     intspec.setDownAction((Action)actionFactory.createCompositeAction());
/* 1521 */     intspec.setUpAction((Action)actionFactory.createCompositeAction());
/* 1522 */     return intspec;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AtomType getCopy(AtomType at) {
/* 1530 */     AtomType copyat = behavFactory.createAtomType();
/* 1531 */     copyat = (AtomType)EcoreUtil.copy((EObject)at);
/* 1532 */     return copyat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortParameterReference getCopy(PortParameterReference ppr) {
/* 1539 */     PortParameterReference pprcopty = interFactory.createPortParameterReference();
/* 1540 */     pprcopty = (PortParameterReference)EcoreUtil.copy((EObject)ppr);
/* 1541 */     return pprcopty;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AtomType CreateAtomType(String Name) {
/* 1549 */     AtomType atomtype = behavFactory.createAtomType();
/* 1550 */     atomtype.setName(Name);
/* 1551 */     atomtype.setBehavior((Behavior)behavFactory.createPetriNet());
/* 1552 */     return atomtype;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Component getCopy(Component component) {
/* 1560 */     Component componentcopy = interFactory.createComponent();
/* 1561 */     componentcopy = (Component)EcoreUtil.copy((EObject)component);
/* 1562 */     return componentcopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression getCopy(Expression exp) {
/* 1570 */     Expression expcopy = (Expression)EcoreUtil.copy((EObject)exp);
/* 1571 */     return expcopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompoundType getCopy(CompoundType compoundType) {
/* 1578 */     CompoundType compoundTypecopy = (CompoundType)EcoreUtil.copy((EObject)compoundType);
/* 1579 */     return compoundTypecopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Binding getCopy(Binding binding) {
/* 1586 */     Binding bindingcopy = (Binding)EcoreUtil.copy((EObject)binding);
/* 1587 */     return bindingcopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Action getCopy(AssignmentAction action) {
/* 1594 */     Action actioncopy = (Action)EcoreUtil.copy((EObject)action);
/* 1595 */     return actioncopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port getCopy(Port port, String NewName) {
/* 1605 */     Port copyport = behavFactory.createPort();
/* 1606 */     PortDefinition pd = ((DefinitionBinding)port.getBinding()).getDefinition();
/* 1607 */     PortDefinition copyportdefinition = getCopy(pd);
/* 1608 */     copyportdefinition.setName(NewName);
/* 1609 */     copyport = (Port)EcoreUtil.copy((EObject)port);
/* 1610 */     ((DefinitionBinding)copyport.getBinding()).setDefinition(copyportdefinition);
/* 1611 */     copyport.setName(NewName);
/* 1612 */     return copyport;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PortDefinition getCopy(PortDefinition portdefinition) {
/* 1617 */     PortDefinition copyportdefinition = behavFactory.createPortDefinition();
/* 1618 */     copyportdefinition = (PortDefinition)EcoreUtil.copy((EObject)portdefinition);
/* 1619 */     return copyportdefinition;
/*      */   }
/*      */ 
/*      */   
/*      */   public static DataParameter getCopy(DataParameter dataparameter) {
/* 1624 */     DataParameter copydataparameter = (DataParameter)EcoreUtil.copy((EObject)dataparameter);
/* 1625 */     return copydataparameter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean IsEquals(PortType p1, PortType p2) {
/* 1637 */     for (Object o : p1.getDataParameter()) {
/*      */       
/* 1639 */       DataParameter dp = (DataParameter)o;
/* 1640 */       if (dp.getType() instanceof OpaqueElement) {
/*      */         
/* 1642 */         OpaqueElement oe = (OpaqueElement)dp.getType();
/* 1643 */         int indexOfDataParameter = p1.getDataParameter().indexOf(o);
/* 1644 */         DataParameter dp1 = (DataParameter)p2.getDataParameter().get(indexOfDataParameter);
/* 1645 */         if (dp1.getType() instanceof OpaqueElement) {
/*      */           
/* 1647 */           OpaqueElement oe1 = (OpaqueElement)dp1.getType();
/* 1648 */           if (!oe.getBody().equals(oe1.getBody()))
/* 1649 */             return false; 
/*      */           continue;
/*      */         } 
/* 1652 */         return false;
/*      */       } 
/*      */       
/* 1655 */       return false;
/*      */     } 
/* 1657 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Transition> getTransitionExpPort(State s, List<String> LNameExportPort) {
/* 1667 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 1668 */     for (Object o : s.getOutgoing()) {
/*      */       
/* 1670 */       Transition t = (Transition)o;
/* 1671 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 1672 */       if (LNameExportPort.indexOf(pdr.getTarget().getName()) != -1)
/* 1673 */         LTransition.add(t); 
/*      */     } 
/* 1675 */     return LTransition;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Transition> getTransitionIntPort(State s, List<String> LNameExportPort) {
/* 1680 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 1681 */     for (Object o : s.getOutgoing()) {
/*      */       
/* 1683 */       Transition t = (Transition)o;
/* 1684 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 1685 */       if (LNameExportPort.indexOf(pdr.getTarget().getName()) == -1)
/* 1686 */         LTransition.add(t); 
/*      */     } 
/* 1688 */     return LTransition;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getOutStringExpPort(State s, List<String> LNameExportPort) {
/* 1696 */     List<String> SubLStringPort = new LinkedList<String>();
/* 1697 */     for (Object transition_i : s.getOutgoing()) {
/*      */       
/* 1699 */       Transition transition = (Transition)transition_i;
/* 1700 */       PortDefinitionReference PDR = (PortDefinitionReference)transition.getTrigger();
/* 1701 */       String NameofPDR = PDR.getTarget().getName();
/* 1702 */       if (LNameExportPort.contains(NameofPDR))
/* 1703 */         SubLStringPort.add(NameofPDR); 
/*      */     } 
/* 1705 */     return SubLStringPort;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression getInverseGuard(Variable v) {
/* 1715 */     UnaryExpression NotE = expressionFactory.createUnaryExpression();
/* 1716 */     UnaryOperator Not = UnaryOperator.LOGICAL_NOT;
/* 1717 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 1718 */     VarRef.setTargetVariable(v);
/* 1719 */     NotE.setOperator(Not);
/* 1720 */     NotE.setOperand((Expression)VarRef);
/* 1721 */     return (Expression)NotE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static UnaryExpression CreateUnaryExpressionReference(Variable v) {
/* 1730 */     UnaryExpression UE = expressionFactory.createUnaryExpression();
/* 1731 */     UnaryOperator RefOP = UnaryOperator.REFERENCE;
/* 1732 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 1733 */     VarRef.setTargetVariable(v);
/* 1734 */     UE.setOperand((Expression)VarRef);
/* 1735 */     UE.setOperator(RefOP);
/* 1736 */     return UE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression getOrExpression(List<Variable> LVariable) {
/* 1750 */     boolean firststep = true;
/* 1751 */     boolean test = false;
/* 1752 */     BinaryExpression binaryExpression1 = expressionFactory.createBinaryExpression();
/* 1753 */     BinaryExpression BE = null;
/* 1754 */     for (Variable o : LVariable) {
/*      */       VariableReference variableReference1;
/* 1756 */       Variable v = o;
/* 1757 */       VariableReference VarRef = expressionFactory.createVariableReference();
/* 1758 */       VarRef.setTargetVariable(v);
/* 1759 */       if (firststep) {
/*      */         
/* 1761 */         variableReference1 = VarRef;
/* 1762 */         firststep = false;
/*      */         
/*      */         continue;
/*      */       } 
/* 1766 */       test = true;
/* 1767 */       BE = expressionFactory.createBinaryExpression();
/* 1768 */       BE.setLeftOperand((Expression)variableReference1);
/* 1769 */       BE.setRightOperand((Expression)VarRef);
/* 1770 */       BinaryOperator BO = BinaryOperator.LOGICAL_OR;
/* 1771 */       BE.setOperator(BO);
/* 1772 */       binaryExpression1 = BE;
/*      */     } 
/*      */     
/* 1775 */     if (!test) return (Expression)binaryExpression1; 
/* 1776 */     return (Expression)BE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Expression getNewGuardConnector(ConnectorType connType, List<PortParameter> LPortParameter, List<List<Variable>> LLVariable) {
/* 1790 */     EList<InteractionSpecification> eList = connType.getInteractionSpecification();
/* 1791 */     if (eList.size() != 0) {
/* 1792 */       InteractionSpecification IS = eList.get(0);
/* 1793 */       Expression guardbefore = IS.getGuard();
/* 1794 */       if (guardbefore != null) {
/* 1795 */         Expression guardafter = (Expression)EcoreUtil.copy((EObject)guardbefore);
/* 1796 */         ExpressionReplace(guardafter, LPortParameter, LLVariable);
/* 1797 */         return guardafter;
/*      */       } 
/*      */     } 
/* 1800 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Action getNewFunctionConnector(ConnectorType connType, List<PortParameter> LPortParameter, List<List<Variable>> LLVariable, List<Variable> LVariableConnBefore, List<Variable> LVariableConnAfter) {
/* 1816 */     if (connType.getInteractionSpecification().size() != 0) {
/*      */       
/* 1818 */       InteractionSpecification IS = (InteractionSpecification)connType.getInteractionSpecification().get(0);
/* 1819 */       CompositeAction CompAction = actionFactory.createCompositeAction();
/*      */ 
/*      */       
/* 1822 */       if (IS.getUpAction() instanceof CompositeAction) {
/* 1823 */         CompositeAction CA = (CompositeAction)IS.getUpAction();
/* 1824 */         for (Object o : CA.getContent()) {
/* 1825 */           if (o instanceof AssignmentAction) {
/* 1826 */             AssignmentAction AA = (AssignmentAction)o;
/* 1827 */             AssignmentAction aacopy = (AssignmentAction)EcoreUtil.copy((EObject)AA);
/* 1828 */             ExpressionReplace(aacopy.getAssignedValue(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1829 */             ExpressionReplace((Expression)aacopy.getAssignedTarget(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1830 */             CompAction.getContent().add(aacopy); continue;
/*      */           } 
/* 1832 */           if (o instanceof FunctionCallExpression) {
/* 1833 */             FunctionCallExpression fce = (FunctionCallExpression)o;
/* 1834 */             FunctionCallExpression fcecopy = (FunctionCallExpression)EcoreUtil.copy((EObject)fce);
/* 1835 */             ExpressionReplace((Expression)fcecopy, LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1836 */             CompAction.getContent().add(fcecopy); continue;
/*      */           } 
/* 1838 */           if (o instanceof IfAction) {
/* 1839 */             IfAction ifaction = (IfAction)o;
/* 1840 */             IfAction ifactioncopy = (IfAction)EcoreUtil.copy((EObject)ifaction);
/* 1841 */             ExpressionReplace(ifactioncopy.getCondition(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1842 */             ExpressionReplace((Expression)ifactioncopy.getIfCase(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1843 */             ExpressionReplace((Expression)ifactioncopy.getElseCase(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1844 */             CompAction.getContent().add(ifactioncopy);
/*      */             continue;
/*      */           } 
/* 1847 */           System.out.println("Problem up action in the connector type:  " + connType.getName());
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1854 */       if (IS.getDownAction() instanceof CompositeAction) {
/* 1855 */         CompositeAction CA = (CompositeAction)IS.getDownAction();
/* 1856 */         for (Object o : CA.getContent()) {
/* 1857 */           if (o instanceof AssignmentAction) {
/* 1858 */             AssignmentAction AA = (AssignmentAction)o;
/* 1859 */             AssignmentAction aacopy = (AssignmentAction)EcoreUtil.copy((EObject)AA);
/* 1860 */             ExpressionReplace(aacopy.getAssignedValue(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1861 */             ExpressionReplace((Expression)aacopy.getAssignedTarget(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1862 */             CompAction.getContent().add(aacopy); continue;
/*      */           } 
/* 1864 */           if (o instanceof FunctionCallExpression) {
/* 1865 */             FunctionCallExpression fce = (FunctionCallExpression)o;
/* 1866 */             FunctionCallExpression fcecopy = (FunctionCallExpression)EcoreUtil.copy((EObject)fce);
/* 1867 */             ExpressionReplace((Expression)fcecopy, LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1868 */             CompAction.getContent().add(fcecopy); continue;
/*      */           } 
/* 1870 */           if (o instanceof IfAction) {
/* 1871 */             IfAction ifaction = (IfAction)o;
/* 1872 */             IfAction ifactioncopy = (IfAction)EcoreUtil.copy((EObject)ifaction);
/* 1873 */             ExpressionReplace(ifactioncopy.getCondition(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1874 */             ExpressionReplace((Expression)ifactioncopy.getIfCase(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1875 */             ExpressionReplace((Expression)ifactioncopy.getElseCase(), LPortParameter, LLVariable, LVariableConnBefore, LVariableConnAfter);
/* 1876 */             CompAction.getContent().add(ifactioncopy);
/*      */             continue;
/*      */           } 
/* 1879 */           System.out.println("Problem down action in the connector type:  " + connType.getName());
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1884 */       return (Action)CompAction;
/*      */     } 
/* 1886 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortDefinition getPortDefinition(Port port) {
/* 1895 */     if (port.getBinding() instanceof DefinitionBinding) {
/* 1896 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 1897 */       return db.getDefinition();
/*      */     } 
/* 1899 */     System.out.println("Problem in the port port" + port.getName());
/* 1900 */     System.out.println("Debug : TransformationFunction getPortDefinition Method");
/* 1901 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PortDefinitionReference createPortDefinitionReference(Port port) {
/* 1906 */     assert port.getBinding() instanceof DefinitionBinding : "error while create a new port definition reference";
/* 1907 */     DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 1908 */     PortDefinitionReference pdr = behavFactory.createPortDefinitionReference();
/* 1909 */     pdr.setTarget(db.getDefinition());
/* 1910 */     return pdr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction CreateAssignmentActionBoolVar(Variable var, boolean value) {
/* 1921 */     AssignmentAction AA = actionFactory.createAssignmentAction();
/* 1922 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 1923 */     BooleanLiteral BL = expressionFactory.createBooleanLiteral();
/* 1924 */     BL.setBValue(value);
/* 1925 */     VarRef.setTargetVariable(var);
/* 1926 */     AA.setAssignedTarget((DataReference)VarRef);
/* 1927 */     AA.setAssignedValue((Expression)BL);
/* 1928 */     return AA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompositeAction CreateAssignmentActions(List<Variable> LVariable, boolean value) {
/* 1939 */     CompositeAction CA = actionFactory.createCompositeAction();
/* 1940 */     for (Variable o : LVariable) {
/* 1941 */       Variable v = o;
/* 1942 */       CA.getContent().add(CreateAssignmentActionBoolVar(v, value));
/*      */     } 
/* 1944 */     return CA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BinaryExpression CreateGuardEqual(Variable v, Integer value, boolean Operator) {
/* 1955 */     BinaryExpression BE = expressionFactory.createBinaryExpression();
/* 1956 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 1957 */     VarRef.setTargetVariable(v);
/* 1958 */     IntegerLiteral IL = expressionFactory.createIntegerLiteral();
/* 1959 */     IL.setIValue(value.intValue());
/* 1960 */     BE.setLeftOperand((Expression)VarRef);
/* 1961 */     BE.setRightOperand((Expression)IL);
/* 1962 */     BinaryOperator BO = null;
/* 1963 */     if (Operator) {
/* 1964 */       BO = BinaryOperator.EQUALITY;
/*      */     } else {
/* 1966 */       BO = BinaryOperator.INEQUALITY;
/* 1967 */     }  BE.setOperator(BO);
/* 1968 */     return BE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction CreateAssignmentAction(Variable V_Target, Variable V_Value) {
/* 1983 */     AssignmentAction AA = actionFactory.createAssignmentAction();
/*      */     
/* 1985 */     VariableReference VarRefTarget = expressionFactory.createVariableReference();
/* 1986 */     VarRefTarget.setTargetVariable(V_Target);
/*      */     
/* 1988 */     VariableReference VarRefValue = expressionFactory.createVariableReference();
/* 1989 */     VarRefValue.setTargetVariable(V_Value);
/*      */     
/* 1991 */     AA.setAssignedTarget((DataReference)VarRefTarget);
/* 1992 */     AA.setAssignedValue((Expression)VarRefValue);
/* 1993 */     return AA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction CreateAddAssignmentAction(Variable v, Integer value) {
/* 2003 */     AssignmentAction AA = actionFactory.createAssignmentAction();
/*      */ 
/*      */     
/* 2006 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 2007 */     VarRef.setTargetVariable(v);
/*      */ 
/*      */     
/* 2010 */     VariableReference VarRef1 = expressionFactory.createVariableReference();
/* 2011 */     VarRef1.setTargetVariable(v);
/* 2012 */     IntegerLiteral IL = expressionFactory.createIntegerLiteral();
/* 2013 */     IL.setIValue(value.intValue());
/* 2014 */     BinaryOperator BA = BinaryOperator.ADDITION;
/* 2015 */     BinaryExpression BE = expressionFactory.createBinaryExpression();
/* 2016 */     BE.setLeftOperand((Expression)VarRef);
/* 2017 */     BE.setOperator(BA);
/* 2018 */     BE.setRightOperand((Expression)IL);
/*      */     
/* 2020 */     AA.setAssignedTarget((DataReference)VarRef1);
/* 2021 */     AA.setAssignedValue((Expression)BE);
/*      */     
/* 2023 */     return AA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction CreateVarFunctionAssignmentAction(Variable v, String FunctionName, List ActualData) {
/* 2037 */     FunctionCallExpression fce = expressionFactory.createFunctionCallExpression();
/* 2038 */     fce.setFunctionName(FunctionName);
/* 2039 */     fce.getActualData().addAll(ActualData);
/*      */     
/* 2041 */     AssignmentAction AA = actionFactory.createAssignmentAction();
/* 2042 */     VariableReference VarRef = expressionFactory.createVariableReference();
/* 2043 */     VarRef.setTargetVariable(v);
/*      */     
/* 2045 */     AA.setAssignedTarget((DataReference)VarRef);
/* 2046 */     AA.setAssignedValue((Expression)fce);
/* 2047 */     return AA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FunctionCallExpression CreateFunction(String FunctionName, List ActualData) {
/* 2058 */     FunctionCallExpression fce = expressionFactory.createFunctionCallExpression();
/* 2059 */     fce.setFunctionName(FunctionName);
/* 2060 */     fce.getActualData().addAll(ActualData);
/* 2061 */     return fce;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static IntegerLiteral CreateIntegerLiteral(Integer value) {
/* 2070 */     IntegerLiteral IL = expressionFactory.createIntegerLiteral();
/* 2071 */     IL.setIValue(value.intValue());
/* 2072 */     return IL;
/*      */   }
/*      */ 
/*      */   
/*      */   public static CompositeAction CreateCompositeAction(List<Action> LAction) {
/* 2077 */     CompositeAction CA = actionFactory.createCompositeAction();
/* 2078 */     CA.getContent().addAll(LAction);
/* 2079 */     return CA;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompoundType ParseBIPFile(String Path) {
/*      */     try {
/* 2091 */       CmdLineError err = new CmdLineError();
/*      */       
/* 2093 */       ArrayList<String> includeDirectories = new ArrayList();
/* 2094 */       Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/* 2095 */       String includeDir = "";
/* 2096 */       includeDirectories.add(String.valueOf(includeDir) + "/");
/* 2097 */       Module[] bipModel = Parser.parse(Path, includeDirectories, libFullNames, (ErrorMessage)err);
/* 2098 */       System sys = (System)bipModel[0];
/* 2099 */       top = sys.getRoot();
/* 2100 */       CompoundType CT = (CompoundType)top.getType();
/* 2101 */       return CT;
/*      */     }
/* 2103 */     catch (Exception E) {
/* 2104 */       System.out.println("Error Parsing BIP File");
/* 2105 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void CreateBIPFile(String FileName, Module BIPSystem) throws FileNotFoundException {
/* 2116 */     FileOutputStream out = new FileOutputStream(FileName);
/* 2117 */     PrintStream X = new PrintStream(out);
/* 2118 */     Reverse a = new Reverse(X);
/* 2119 */     a.decompile(BIPSystem);
/*      */   }
/*      */ 
/*      */   
/*      */   public static CompoundType CreateCompoundType(String Name) {
/* 2124 */     CompoundType CT = interFactory.createCompoundType();
/* 2125 */     CT.setName(Name);
/* 2126 */     return CT;
/*      */   }
/*      */   
/*      */   public static Component getComponent(CompoundType compoundType, String Name) {
/* 2130 */     for (Object o : compoundType.getSubcomponent()) {
/* 2131 */       Component component = (Component)o;
/* 2132 */       if (component.getName().equals(Name))
/* 2133 */         return component; 
/*      */     } 
/* 2135 */     return null;
/*      */   }
/*      */   
/*      */   public static Port getPort(ComponentType componentType, String PortName) {
/* 2139 */     for (Object o : componentType.getPort()) {
/* 2140 */       Port p = (Port)o;
/* 2141 */       if (p.getName().equals(PortName))
/* 2142 */         return p; 
/*      */     } 
/* 2144 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean IsAllAtomic(CompoundType compoundType) {
/* 2149 */     for (Object o : compoundType.getSubcomponent()) {
/* 2150 */       Component component = (Component)o;
/* 2151 */       if (!(component.getType() instanceof AtomType))
/* 2152 */         return false; 
/*      */     } 
/* 2154 */     return true;
/*      */   }
/*      */   
/*      */   public static Component getNonAtomicComponent(CompoundType compoundType) {
/* 2158 */     for (Object o : compoundType.getSubcomponent()) {
/* 2159 */       Component component = (Component)o;
/* 2160 */       if (component.getType() instanceof CompoundType)
/* 2161 */         return component; 
/*      */     } 
/* 2163 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InnerPortReference ContainsInnerPortReference(List<InnerPortReference> LIPR, InnerPortReference IPR) {
/* 2174 */     for (InnerPortReference o : LIPR) {
/* 2175 */       InnerPortReference ipr = o;
/* 2176 */       if (IPR.getTargetPort().getName().equals(ipr.getTargetPort().getName()) && 
/* 2177 */         IPR.getTargetInstance().getTargetPart().getName().equals(ipr.getTargetInstance().getTargetPart().getName()))
/* 2178 */         return ipr; 
/*      */     } 
/* 2180 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static InnerPortReference ContainsInnerPortReference(Map MapIPR, InnerPortReference IPR) {
/* 2185 */     for (Object o : MapIPR.keySet()) {
/* 2186 */       InnerPortReference ipr = (InnerPortReference)o;
/* 2187 */       if (IPR.getTargetPort().getName().equals(ipr.getTargetPort().getName()) && 
/* 2188 */         IPR.getTargetInstance().getTargetPart().getName().equals(ipr.getTargetInstance().getTargetPart().getName()))
/* 2189 */         return ipr; 
/*      */     } 
/* 2191 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean IsIntersectState(List<State> LState1, List<State> LState2) {
/* 2196 */     for (State o1 : LState1) {
/* 2197 */       for (State o2 : LState2) {
/* 2198 */         if (o1.equals(o2))
/* 2199 */           return true; 
/*      */       } 
/*      */     } 
/* 2202 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean IsConflictPetriNet(PetriNet PN) {
/* 2208 */     for (Object o1 : PN.getTransition()) {
/*      */       
/* 2210 */       Transition t1 = (Transition)o1;
/* 2211 */       for (Object o2 : PN.getTransition()) {
/*      */         
/* 2213 */         Transition t2 = (Transition)o2;
/* 2214 */         EList eList1 = t1.getOrigin();
/* 2215 */         EList eList2 = t2.getOrigin();
/* 2216 */         if (!t1.equals(t2) && IsIntersectState((List<State>)eList1, (List<State>)eList2) && (eList1.size() > 1 || eList2.size() > 1)) {
/*      */           
/* 2218 */           System.out.println("Error input : you do not have the right to use Petri Net with conflict.");
/* 2219 */           System.out.println("Verify atom type :  " + PN.getAtomType().getName() + "\nConflicts Transitions Labelled by the ports : \n" + ((PortDefinitionReference)t1.getTrigger()).getTarget().getName() + "\n" + ((PortDefinitionReference)t2.getTrigger()).getTarget().getName());
/* 2220 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2224 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static OpaqueElement CreateOpaqueElement(String OpaqueString) {
/* 2234 */     OpaqueElement Oelem = moduleFactory.createOpaqueElement();
/* 2235 */     Oelem.setBody(OpaqueString);
/* 2236 */     return Oelem;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<DataParameter> getCopyDataParameter(List<DataParameter> LDataParameter) {
/* 2241 */     List<DataParameter> LDataParameterCopy = new LinkedList<DataParameter>();
/* 2242 */     for (DataParameter o : LDataParameter) {
/* 2243 */       DataParameter dataparametercopy = getCopy(o);
/* 2244 */       LDataParameterCopy.add(dataparametercopy);
/*      */     } 
/* 2246 */     return LDataParameterCopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port getPort(PortExpression portExpression) {
/* 2254 */     PortDefinitionReference pdr = (PortDefinitionReference)portExpression;
/* 2255 */     PortDefinition pd = pdr.getTarget();
/* 2256 */     AtomType at = pd.getAtomType();
/* 2257 */     for (Port p : at.getPort()) {
/* 2258 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 2259 */       if (db.getDefinition() == pd)
/* 2260 */         return p; 
/*      */     } 
/* 2262 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Transition> getTransitions(Port p) {
/* 2271 */     List<Transition> transitions = new LinkedList<Transition>();
/* 2272 */     assert p.getComponentType() instanceof AtomType;
/* 2273 */     AtomType at = (AtomType)p.getComponentType();
/* 2274 */     PetriNet pn = (PetriNet)at.getBehavior();
/* 2275 */     DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 2276 */     for (Transition t : pn.getTransition()) {
/* 2277 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 2278 */       if (pdr.getTarget() == db.getDefinition())
/* 2279 */         transitions.add(t); 
/*      */     } 
/* 2281 */     return transitions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void getAssignedVariables(Action a, Map<Variable, AssignmentAction> variables) {
/* 2289 */     if (a instanceof AssignmentAction) {
/* 2290 */       variables.put((
/* 2291 */           (VariableReference)((AssignmentAction)a).getAssignedTarget()).getTargetVariable(), (AssignmentAction)a);
/*      */     
/*      */     }
/* 2294 */     else if (a instanceof CompositeAction) {
/* 2295 */       for (Action subAction : ((CompositeAction)a).getContent()) {
/* 2296 */         getAssignedVariables(subAction, variables);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<Variable, AssignmentAction> getAssignedVariables(Action a) {
/* 2310 */     Map<Variable, AssignmentAction> variables = new HashMap<Variable, AssignmentAction>();
/* 2311 */     getAssignedVariables(a, variables);
/* 2312 */     return variables;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Transition> getTransitionsUpdateVariable(Variable v) {
/* 2321 */     assert v.eContainer() instanceof AtomType;
/* 2322 */     AtomType at = (AtomType)v.eContainer();
/* 2323 */     PetriNet pn = (PetriNet)at.getBehavior();
/*      */     
/* 2325 */     List<Transition> transitions = new LinkedList<Transition>();
/*      */     
/* 2327 */     for (Transition t : pn.getTransition()) {
/* 2328 */       if (getAssignedVariables(t.getAction()).keySet().contains(v)) {
/* 2329 */         transitions.add(t);
/*      */       }
/*      */     } 
/*      */     
/* 2333 */     return transitions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void getAssignmentAction(Action a, Variable v, List<AssignmentAction> ac) {
/* 2345 */     if (a instanceof AssignmentAction) {
/* 2346 */       if (((VariableReference)((AssignmentAction)a).getAssignedTarget()).getTargetVariable().getName().equals(v.getName())) {
/* 2347 */         ac.add((AssignmentAction)a);
/*      */         
/*      */         return;
/*      */       } 
/* 2351 */     } else if (a instanceof CompositeAction) {
/* 2352 */       for (Action subAction : ((CompositeAction)a).getContent()) {
/* 2353 */         getAssignmentAction(subAction, v, ac);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AssignmentAction getAssignmentAction(Action a, Variable v) {
/* 2367 */     List<AssignmentAction> assignmentActions = new LinkedList<AssignmentAction>();
/* 2368 */     getAssignmentAction(a, v, assignmentActions);
/* 2369 */     if (assignmentActions.size() == 1)
/* 2370 */       return assignmentActions.get(0); 
/* 2371 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction getAssignmentAction(Transition t, Variable v) {
/* 2381 */     return getAssignmentAction(t.getAction(), v);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<Variable, Component> getVariable(Connector c, RequiredDataParameterReference rdpr) {
/* 2392 */     ConnectorType connType = c.getType();
/*      */     
/* 2394 */     int indexInnerPortReference = connType.getPortParameter().indexOf(rdpr.getPortReference().getTarget());
/*      */     
/* 2396 */     if (indexInnerPortReference < 0) return null;
/*      */     
/* 2398 */     PortParameter pp = (PortParameter)connType.getPortParameter().get(indexInnerPortReference);
/* 2399 */     int indexDataParameterPort = pp.getType().getDataParameter().indexOf(rdpr.getTargetParameter());
/* 2400 */     if (indexDataParameterPort < 0) return null;
/*      */     
/* 2402 */     InnerPortReference ipr = (InnerPortReference)c.getActualPort().get(indexInnerPortReference);
/* 2403 */     Map<Variable, Component> mapVarComp = new HashMap<Variable, Component>();
/* 2404 */     mapVarComp.put((Variable)((DefinitionBinding)ipr.getTargetPort().getBinding()).getDefinition().getExposedVariable().get(indexDataParameterPort), 
/* 2405 */         (Component)ipr.getTargetInstance().getTargetPart());
/* 2406 */     return mapVarComp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void getAssignedDataReferences(Action a, Map<DataReference, AssignmentAction> dataReferences) {
/* 2415 */     if (a instanceof AssignmentAction) {
/* 2416 */       dataReferences.put((
/* 2417 */           (AssignmentAction)a).getAssignedTarget(), (AssignmentAction)a);
/*      */     
/*      */     }
/* 2420 */     else if (a instanceof CompositeAction) {
/* 2421 */       for (Action subAction : ((CompositeAction)a).getContent()) {
/* 2422 */         getAssignedDataReferences(subAction, dataReferences);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<DataReference, AssignmentAction> getAssignedDataReferences(Action a) {
/* 2436 */     Map<DataReference, AssignmentAction> dataReferences = new HashMap<DataReference, AssignmentAction>();
/* 2437 */     getAssignedDataReferences(a, dataReferences);
/* 2438 */     return dataReferences;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<Connector, AssignmentAction> getAssignmentActions(Component c, Variable v) {
/* 2451 */     Map<Connector, AssignmentAction> mapConnAction = new HashMap<Connector, AssignmentAction>();
/* 2452 */     for (Connector connector : c.getCompoundType().getConnector()) {
/* 2453 */       InteractionSpecification interactionSpec = (InteractionSpecification)connector.getType().getInteractionSpecification().get(0);
/* 2454 */       Map<DataReference, AssignmentAction> mapDataRefAction = getAssignedDataReferences(interactionSpec.getDownAction());
/* 2455 */       for (DataReference dr : mapDataRefAction.keySet()) {
/* 2456 */         RequiredDataParameterReference rdpr = (RequiredDataParameterReference)dr;
/* 2457 */         Map<Variable, Component> mapVarComp = getVariable(connector, rdpr);
/* 2458 */         if (mapVarComp.containsKey(v)) {
/* 2459 */           mapConnAction.put(connector, mapDataRefAction.get(dr));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2464 */     return mapConnAction;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIPTransformation\TransformationFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */