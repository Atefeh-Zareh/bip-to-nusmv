/*      */ package distributedPN;
/*      */ 
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
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
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*      */ 
/*      */ public class TransformationFunction
/*      */ {
/*      */   static Module module;
/*      */   
/*      */   public static void Initialize(Module m) {
/*   66 */     module = m;
/*   67 */     PTSyn = BehaviorsFactory.eINSTANCE.createPortType();
/*   68 */     setConnSynType();
/*   69 */     PTSyn.setName("SynEPort1");
/*   70 */     PTSyn.setModule(m);
/*   71 */     ConnSyn.setModule(m);
/*      */   }
/*      */   public static PortType PTSyn;
/*      */   static ConnectorType ConnSyn;
/*      */   
/*      */   private static void setConnSynType() {
/*   77 */     ConnSyn = InteractionsFactory.eINSTANCE.createConnectorType();
/*   78 */     ConnSyn.setName("ConnSynPrint");
/*   79 */     PortParameter PortPara = InteractionsFactory.eINSTANCE.createPortParameter();
/*   80 */     PortPara.setType(PTSyn);
/*   81 */     PortPara.setName("p");
/*   82 */     ConnSyn.getPortParameter().add(PortPara);
/*   83 */     PortParameterReference PortParaRef = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*   84 */     PortParaRef.setTarget(PortPara);
/*   85 */     ACFusion portdef = PortExpressionsFactory.eINSTANCE.createACFusion();
/*   86 */     portdef.getOperand().add(PortParaRef);
/*   87 */     ConnSyn.setDefinition((PortExpression)portdef);
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
/*      */   public static List<Variable> CreateGuardVariable(List<String> LName) {
/*  101 */     List<Variable> LVariable = new LinkedList<Variable>();
/*  102 */     for (String name_i : LName) {
/*      */       
/*  104 */       String Name = name_i;
/*  105 */       Variable BVariable = BehaviorsFactory.eINSTANCE.createVariable();
/*  106 */       OpaqueElement Oelem = ModulesFactory.eINSTANCE.createOpaqueElement();
/*  107 */       BooleanLiteral BL = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/*  108 */       BL.setBValue(false);
/*  109 */       Oelem.setBody("bool");
/*  110 */       BVariable.setType((DataType)Oelem);
/*  111 */       BVariable.setName(Name);
/*  112 */       BVariable.setInitialValue((Expression)BL);
/*  113 */       LVariable.add(BVariable);
/*      */     } 
/*  115 */     return LVariable;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Variable CreateGuardVariable(String Name) {
/*  120 */     Variable BVariable = BehaviorsFactory.eINSTANCE.createVariable();
/*  121 */     OpaqueElement Oelem = ModulesFactory.eINSTANCE.createOpaqueElement();
/*  122 */     BooleanLiteral BL = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/*  123 */     BL.setBValue(false);
/*  124 */     Oelem.setBody("bool");
/*  125 */     BVariable.setType((DataType)Oelem);
/*  126 */     BVariable.setName(Name);
/*  127 */     BVariable.setInitialValue((Expression)BL);
/*  128 */     return BVariable;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Variable CreateVariable(String VarName, DataType VarType) {
/*  133 */     Variable Var = BehaviorsFactory.eINSTANCE.createVariable();
/*  134 */     Var.setName(VarName);
/*  135 */     Var.setType(VarType);
/*  136 */     return Var;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType CreatePortType(List<DataParameter> LDP, String Name) {
/*  147 */     PortType porttype = BehaviorsFactory.eINSTANCE.createPortType();
/*  148 */     for (DataParameter o : LDP) {
/*  149 */       porttype.getDataParameter().add(o);
/*      */     }
/*  151 */     porttype.setName(Name);
/*  152 */     porttype.setModule(module);
/*  153 */     return porttype;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt, AtomType at) {
/*  159 */     Port port = BehaviorsFactory.eINSTANCE.createPort();
/*  160 */     PortDefinition pd = CreatePortDefinition(Name, pt, at);
/*  161 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  162 */     db.setDefinition(pd);
/*  163 */     port.setBinding((Binding)db);
/*  164 */     port.setName(Name);
/*  165 */     port.setType(pt);
/*  166 */     port.setComponentType((ComponentType)at);
/*  167 */     at.getPort().add(port);
/*  168 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt) {
/*  173 */     Port port = BehaviorsFactory.eINSTANCE.createPort();
/*  174 */     port.setName(Name);
/*  175 */     port.setType(pt);
/*  176 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Port CreatePort(PortDefinition pd) {
/*  181 */     Port port = BehaviorsFactory.eINSTANCE.createPort();
/*  182 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  183 */     db.setDefinition(pd);
/*  184 */     port.setBinding((Binding)db);
/*  185 */     port.setName(pd.getName());
/*  186 */     port.setType(pd.getType());
/*  187 */     return port;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String Name, PortType pt, AtomType at, List<Variable> LVariable) {
/*  193 */     Port port = BehaviorsFactory.eINSTANCE.createPort();
/*  194 */     PortDefinition pd = CreatePortDefinition(Name, pt, at);
/*  195 */     pd.getExposedVariable().addAll(LVariable);
/*  196 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  197 */     db.setDefinition(pd);
/*  198 */     port.setBinding((Binding)db);
/*  199 */     port.setName(Name);
/*  200 */     port.setType(pt);
/*  201 */     port.setComponentType((ComponentType)at);
/*  202 */     at.getPort().add(port);
/*  203 */     return port;
/*      */   }
/*      */ 
/*      */   
/*      */   public static State getState(PetriNet PN, String StateName) {
/*  208 */     for (Object o : PN.getState()) {
/*      */       
/*  210 */       State s = (State)o;
/*  211 */       if (s.getName().equals(StateName))
/*  212 */         return s; 
/*      */     } 
/*  214 */     return null;
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
/*  225 */     CompositeAction CompAction = ActionsFactory.eINSTANCE.createCompositeAction();
/*  226 */     for (Variable variable_i : LVariable) {
/*      */       
/*  228 */       Variable variable = variable_i;
/*  229 */       AssignmentAction AssAction = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  230 */       VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/*  231 */       VarRef.setTargetVariable(variable);
/*  232 */       AssAction.setAssignedTarget((DataReference)VarRef);
/*  233 */       int index = LVariable.indexOf(variable_i);
/*  234 */       AssAction.setAssignedValue(LGuardExpression.get(index));
/*  235 */       CompAction.getContent().add(AssAction);
/*      */     } 
/*  237 */     return CompAction;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType CreatePortType(String Name) {
/*  243 */     PortType PT = BehaviorsFactory.eINSTANCE.createPortType();
/*  244 */     PT.setName(Name);
/*  245 */     return PT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortDefinition CreatePortDefinition(String Name, PortType PT, AtomType AT) {
/*  251 */     PortDefinition PD = BehaviorsFactory.eINSTANCE.createPortDefinition();
/*  252 */     PD.setAtomType(AT);
/*  253 */     PD.setType(PT);
/*  254 */     PD.setName(Name);
/*  255 */     AT.getPortDefinition().add(PD);
/*  256 */     return PD;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PortDefinitionReference CreatePortDefinitionReference(PortDefinition PD) {
/*  261 */     PortDefinitionReference PDR = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/*  262 */     PDR.setTarget(PD);
/*  263 */     return PDR;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static State CreateState(String Name, List<Transition> IN, List<Transition> OUT) {
/*  269 */     State S = BehaviorsFactory.eINSTANCE.createState();
/*  270 */     S.setName(Name);
/*  271 */     for (Transition o : IN)
/*      */     {
/*  273 */       S.getIncoming().add(o);
/*      */     }
/*  275 */     for (Transition o : OUT)
/*      */     {
/*  277 */       S.getOutgoing().add(o);
/*      */     }
/*  279 */     return S;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static State CreateState(String Name, PetriNet PN) {
/*  286 */     State S = BehaviorsFactory.eINSTANCE.createState();
/*  287 */     S.setName(Name);
/*  288 */     PN.getState().add(S);
/*  289 */     return S;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Transition CreateTransition(PortDefinition PD, Expression guard, Action A, State s1, State s2, PetriNet PN) {
/*  295 */     Transition t = BehaviorsFactory.eINSTANCE.createTransition();
/*  296 */     PortDefinitionReference PDR = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/*  297 */     PDR.setTarget(PD);
/*  298 */     t.setTrigger((PortExpression)PDR);
/*  299 */     t.setAction(A);
/*  300 */     t.setGuard(guard);
/*  301 */     t.getOrigin().add(s1);
/*  302 */     t.getDestination().add(s2);
/*  303 */     PN.getTransition().add(t);
/*  304 */     s1.getOutgoing().add(t);
/*  305 */     s2.getIncoming().add(t);
/*  306 */     return t;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Transition getLTransition(PetriNet PN, State s1, State s2) {
/*  312 */     for (Object o : PN.getTransition()) {
/*      */       
/*  314 */       Transition t = (Transition)o;
/*  315 */       if (((State)t.getOrigin().get(0)).equals(s1) && ((State)t.getDestination().get(0)).equals(s2))
/*  316 */         return t; 
/*      */     } 
/*  318 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Transition CreateTransitionLState(PortDefinition PD, Expression guard, Action A, List<State> Ls1, List<State> Ls2, PetriNet PN) {
/*  325 */     Transition t = BehaviorsFactory.eINSTANCE.createTransition();
/*  326 */     PortDefinitionReference PDR = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/*  327 */     PDR.setTarget(PD);
/*  328 */     t.setTrigger((PortExpression)PDR);
/*  329 */     t.setAction(A);
/*  330 */     t.setGuard(guard);
/*  331 */     for (State o : Ls1) {
/*      */       
/*  333 */       State s1 = o;
/*  334 */       t.getOrigin().add(s1);
/*  335 */       s1.getOutgoing().add(t);
/*      */     } 
/*  337 */     for (State o : Ls2) {
/*      */       
/*  339 */       State s2 = o;
/*  340 */       t.getDestination().add(s2);
/*  341 */       s2.getIncoming().add(t);
/*      */     } 
/*  343 */     PN.getTransition().add(t);
/*  344 */     return t;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void RemoveGuardTransition(List<Transition> LTransition) {
/*  350 */     for (Transition o : LTransition) {
/*      */       
/*  352 */       Transition t = o;
/*  353 */       t.setGuard(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void CopyTransitionFromState(List<Transition> LTransition, State s1, State s2) {
/*  360 */     for (Transition o : LTransition) {
/*      */       
/*  362 */       Transition t = o;
/*  363 */       t.getOrigin().clear();
/*  364 */       t.getOrigin().add(s2);
/*  365 */       s1.getOutgoing().remove(t);
/*  366 */       s2.getOutgoing().add(t);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void SetTransitionInitialState(List<Transition> LTransition, State s1) {
/*  372 */     for (Transition o : LTransition) {
/*      */       
/*  374 */       Transition t = o;
/*  375 */       t.getOrigin().clear();
/*  376 */       t.getOrigin().add(s1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Connector> getConnectorfromPortComp(Port p, Component C, CompoundType CT) {
/*  382 */     List<Connector> LConn = new LinkedList<Connector>();
/*  383 */     for (Object o : CT.getConnector()) {
/*      */       
/*  385 */       Connector conn = (Connector)o;
/*  386 */       for (Object o1 : conn.getActualPort()) {
/*      */         
/*  388 */         InnerPortReference IPR = (InnerPortReference)o1;
/*  389 */         if (IPR.getTargetPort().equals(p) && IPR.getTargetInstance().getTargetPart().equals(C)) {
/*      */           
/*  391 */           LConn.add(conn);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  396 */     return LConn;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Variable> getCopyLVariable(List<Variable> LVariable) {
/*  401 */     List<Variable> LCopyVariable = new LinkedList<Variable>();
/*  402 */     for (Variable o : LVariable) {
/*      */       
/*  404 */       Variable v = o;
/*  405 */       Variable vcopy = CreateVariable(v.getName(), v.getType());
/*      */       
/*  407 */       LCopyVariable.add(vcopy);
/*      */     } 
/*  409 */     return LCopyVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<DataParameter> CreateDataParamter(List<Variable> LVariable) {
/*  416 */     int i = 0;
/*  417 */     List<DataParameter> LDataParameter = new LinkedList<DataParameter>();
/*  418 */     for (Variable o : LVariable) {
/*      */       
/*  420 */       Variable v = o;
/*  421 */       DataParameter dp = BehaviorsFactory.eINSTANCE.createDataParameter();
/*  422 */       dp.setType(v.getType());
/*  423 */       dp.setName("x" + i);
/*  424 */       i++;
/*  425 */       LDataParameter.add(dp);
/*      */     } 
/*  427 */     return LDataParameter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Expression> CorrespondingGuard(List<Transition> LTransition, List<String> LPortName) {
/*  434 */     List<Expression> LExpression = new LinkedList<Expression>();
/*      */     
/*  436 */     for (String o : LPortName) {
/*      */       
/*  438 */       String portname = o;
/*  439 */       boolean exist = false;
/*  440 */       for (Transition o1 : LTransition) {
/*      */         
/*  442 */         Transition transition = o1;
/*  443 */         PortDefinitionReference pdr = (PortDefinitionReference)transition.getTrigger();
/*  444 */         if (pdr.getTarget().getName().equals(portname)) {
/*      */           
/*  446 */           exist = true;
/*  447 */           Expression guard = transition.getGuard();
/*  448 */           if (guard == null) {
/*      */             
/*  450 */             BooleanLiteral BL = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/*  451 */             BL.setBValue(true);
/*  452 */             LExpression.add(BL);
/*      */             
/*      */             continue;
/*      */           } 
/*  456 */           LExpression.add(guard);
/*      */         } 
/*      */       } 
/*      */       
/*  460 */       if (!exist) {
/*      */         
/*  462 */         BooleanLiteral BL = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/*  463 */         BL.setBValue(false);
/*  464 */         LExpression.add(BL);
/*      */       } 
/*      */     } 
/*  467 */     return LExpression;
/*      */   }
/*      */ 
/*      */   
/*      */   static List<AtomType> getAtomType() {
/*  472 */     List<AtomType> LAtomType = new LinkedList<AtomType>();
/*  473 */     for (Object o : module.getBipType()) {
/*      */       
/*  475 */       if (o instanceof AtomType)
/*      */       {
/*  477 */         LAtomType.add((AtomType)o);
/*      */       }
/*      */     } 
/*  480 */     return LAtomType;
/*      */   }
/*      */ 
/*      */   
/*      */   static DAtomType getDAtomType(List<DAtomType> LAT, AtomType CAT) {
/*  485 */     for (DAtomType o : LAT) {
/*      */       
/*  487 */       DAtomType DAT = o;
/*  488 */       if (DAT.getCAT().equals(CAT))
/*  489 */         return DAT; 
/*      */     } 
/*  491 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   static List<String> getLPortName(List<Port> LPort) {
/*  496 */     List<String> LPortName = new LinkedList<String>();
/*  497 */     for (Port o : LPort) {
/*      */       
/*  499 */       Port p = o;
/*  500 */       LPortName.add(p.getName());
/*      */     } 
/*  502 */     return LPortName;
/*      */   }
/*      */ 
/*      */   
/*      */   static List<String> getLVariableName(List<Variable> LVariable) {
/*  507 */     List<String> LVariableName = new LinkedList<String>();
/*  508 */     for (Variable o : LVariable) {
/*      */       
/*  510 */       Variable v = o;
/*  511 */       LVariableName.add(v.getName());
/*      */     } 
/*  513 */     return LVariableName;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static List<Variable> getVarPortEng(Port portCent, List<Variable> LVarCent, List<Variable> LVarDist) {
/*  519 */     List<Variable> LVariable = new LinkedList<Variable>();
/*  520 */     DefinitionBinding db = (DefinitionBinding)portCent.getBinding();
/*  521 */     EList eList = db.getDefinition().getExposedVariable();
/*  522 */     for (Object o : eList)
/*      */     {
/*  524 */       LVariable.add(LVarDist.get(LVarCent.indexOf(o)));
/*      */     }
/*  526 */     return LVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static Expression CreateAndExpression(List<Variable> LVariable) {
/*  532 */     boolean firststep = true;
/*  533 */     boolean test = false;
/*  534 */     BinaryExpression binaryExpression1 = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/*  535 */     BinaryExpression BE = null;
/*  536 */     for (Variable o : LVariable) {
/*      */       VariableReference variableReference1;
/*  538 */       Variable v = o;
/*  539 */       VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/*  540 */       VarRef.setTargetVariable(v);
/*  541 */       if (firststep) {
/*      */         
/*  543 */         variableReference1 = VarRef;
/*  544 */         firststep = false;
/*      */         
/*      */         continue;
/*      */       } 
/*  548 */       test = true;
/*  549 */       BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/*  550 */       BE.setLeftOperand((Expression)variableReference1);
/*  551 */       BE.setRightOperand((Expression)VarRef);
/*  552 */       BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  553 */       BE.setOperator(BO);
/*  554 */       binaryExpression1 = BE;
/*      */     } 
/*      */     
/*  557 */     if (!test) return (Expression)binaryExpression1; 
/*  558 */     return (Expression)BE;
/*      */   }
/*      */ 
/*      */   
/*      */   static Expression AndOfToExpression(Expression e1, Expression e2) {
/*  563 */     if (e2 != null) {
/*      */       
/*  565 */       BinaryExpression BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/*  566 */       BE.setLeftOperand(e1);
/*  567 */       BE.setRightOperand(e2);
/*  568 */       BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  569 */       BE.setOperator(BO);
/*  570 */       return (Expression)BE;
/*      */     } 
/*  572 */     return e1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeSend(PortType PT) {
/*  578 */     ConnectorType ConnT = InteractionsFactory.eINSTANCE.createConnectorType();
/*  579 */     ConnT.setName("ConnTransferSR_" + PT.getName());
/*  580 */     PortParameter PortParaFrom = InteractionsFactory.eINSTANCE.createPortParameter();
/*  581 */     PortParaFrom.setType(PT);
/*  582 */     PortParaFrom.setName("p1");
/*  583 */     PortParameter PortParaTo = InteractionsFactory.eINSTANCE.createPortParameter();
/*  584 */     PortParaTo.setType(PT);
/*  585 */     PortParaTo.setName("p2");
/*  586 */     ConnT.getPortParameter().add(PortParaFrom);
/*  587 */     ConnT.getPortParameter().add(PortParaTo);
/*  588 */     PortParameterReference PortParaRefFrom = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  589 */     PortParaRefFrom.setTarget(PortParaFrom);
/*  590 */     PortParameterReference PortParaRefTo = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  591 */     PortParaRefTo.setTarget(PortParaTo);
/*      */ 
/*      */     
/*  594 */     ACFusion portdef = PortExpressionsFactory.eINSTANCE.createACFusion();
/*  595 */     ACTyping prottrigfrom = PortExpressionsFactory.eINSTANCE.createACTyping();
/*  596 */     prottrigfrom.setOperand((ACExpression)PortParaRefFrom);
/*  597 */     prottrigfrom.setType(ACTypingKind.TRIG);
/*  598 */     portdef.getOperand().add(prottrigfrom);
/*  599 */     portdef.getOperand().add(PortParaRefTo);
/*  600 */     ConnT.setDefinition((PortExpression)portdef);
/*      */ 
/*      */     
/*  603 */     InteractionSpecification Ispec = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/*  604 */     Interaction Inter = InteractionsFactory.eINSTANCE.createInteraction();
/*  605 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  606 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefTo));
/*  607 */     Ispec.setInteraction(Inter);
/*  608 */     ConnT.getInteractionSpecification().add(Ispec);
/*      */ 
/*      */     
/*  611 */     if (PT.getDataParameter().size() != 0) {
/*      */       
/*  613 */       CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/*  614 */       CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/*  615 */       for (Object o1 : PT.getDataParameter()) {
/*      */         
/*  617 */         DataParameter DP = (DataParameter)o1;
/*      */         
/*  619 */         RequiredDataParameterReference rdprfrom = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/*  620 */         PortParameterReference ppreffrom = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  621 */         ppreffrom.setTarget(PortParaFrom);
/*  622 */         rdprfrom.setTargetParameter(DP);
/*  623 */         rdprfrom.setPortReference(ppreffrom);
/*      */ 
/*      */         
/*  626 */         RequiredDataParameterReference rdprto = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/*  627 */         PortParameterReference pprefto = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  628 */         pprefto.setTarget(PortParaTo);
/*  629 */         rdprto.setTargetParameter(DP);
/*  630 */         rdprto.setPortReference(pprefto);
/*      */ 
/*      */         
/*  633 */         AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  634 */         AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdprfrom));
/*  635 */         AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdprto));
/*  636 */         CAD.getContent().add(AAD);
/*      */       } 
/*  638 */       Ispec.setUpAction((Action)CAU);
/*  639 */       Ispec.setDownAction((Action)CAD);
/*      */     } 
/*      */ 
/*      */     
/*  643 */     InteractionSpecification Ispec1 = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/*  644 */     Interaction Inter1 = InteractionsFactory.eINSTANCE.createInteraction();
/*  645 */     Inter1.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  646 */     Ispec1.setInteraction(Inter1);
/*  647 */     ConnT.getInteractionSpecification().add(Ispec1);
/*  648 */     BooleanLiteral bl = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/*  649 */     bl.setBValue(false);
/*  650 */     Ispec1.setGuard((Expression)bl);
/*      */     
/*  652 */     PT.getModule().getBipType().add(ConnT);
/*  653 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType CreateConnectorTypeSend(Port P) {
/*  660 */     PortType PT = P.getType();
/*  661 */     ConnectorType ConnT = InteractionsFactory.eINSTANCE.createConnectorType();
/*  662 */     ConnT.setName("ConnTransfer_" + PT.getName());
/*  663 */     PortParameter PortParaFrom = InteractionsFactory.eINSTANCE.createPortParameter();
/*  664 */     PortParaFrom.setType(PT);
/*  665 */     PortParaFrom.setName("p1");
/*  666 */     PortParameter PortParaTo = InteractionsFactory.eINSTANCE.createPortParameter();
/*  667 */     PortParaTo.setType(PT);
/*  668 */     PortParaTo.setName("p2");
/*  669 */     ConnT.getPortParameter().add(PortParaFrom);
/*  670 */     ConnT.getPortParameter().add(PortParaTo);
/*  671 */     PortParameterReference PortParaRefFrom = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  672 */     PortParaRefFrom.setTarget(PortParaFrom);
/*  673 */     PortParameterReference PortParaRefTo = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  674 */     PortParaRefTo.setTarget(PortParaTo);
/*      */ 
/*      */     
/*  677 */     ACFusion portdef = PortExpressionsFactory.eINSTANCE.createACFusion();
/*  678 */     portdef.getOperand().add(PortParaRefFrom);
/*  679 */     portdef.getOperand().add(PortParaRefTo);
/*  680 */     ConnT.setDefinition((PortExpression)portdef);
/*      */ 
/*      */     
/*  683 */     InteractionSpecification Ispec = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/*  684 */     Interaction Inter = InteractionsFactory.eINSTANCE.createInteraction();
/*  685 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefFrom));
/*  686 */     Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRefTo));
/*  687 */     Ispec.setInteraction(Inter);
/*  688 */     ConnT.getInteractionSpecification().add(Ispec);
/*      */ 
/*      */     
/*  691 */     if (PT.getDataParameter().size() != 0) {
/*      */       
/*  693 */       CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/*  694 */       CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/*  695 */       for (Object o1 : PT.getDataParameter()) {
/*      */         
/*  697 */         DataParameter DP = (DataParameter)o1;
/*      */         
/*  699 */         RequiredDataParameterReference rdprfrom = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/*  700 */         PortParameterReference ppreffrom = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  701 */         ppreffrom.setTarget(PortParaFrom);
/*  702 */         rdprfrom.setTargetParameter(DP);
/*  703 */         rdprfrom.setPortReference(ppreffrom);
/*      */ 
/*      */         
/*  706 */         RequiredDataParameterReference rdprto = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/*  707 */         PortParameterReference pprefto = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  708 */         pprefto.setTarget(PortParaTo);
/*  709 */         rdprto.setTargetParameter(DP);
/*  710 */         rdprto.setPortReference(pprefto);
/*      */ 
/*      */         
/*  713 */         AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/*  714 */         AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdprfrom));
/*  715 */         AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdprto));
/*  716 */         CAD.getContent().add(AAD);
/*      */       } 
/*  718 */       Ispec.setUpAction((Action)CAU);
/*  719 */       Ispec.setDownAction((Action)CAD);
/*      */     } 
/*  721 */     PT.getModule().getBipType().add(ConnT);
/*  722 */     return ConnT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static List<Integer> getIndexPortVar(ConnectorType ct, RequiredDataParameterReference rdpr) {
/*  732 */     List<Integer> LIndexPortVar = new LinkedList<Integer>();
/*  733 */     DataParameter dp = rdpr.getTargetParameter();
/*  734 */     PortParameter pp = rdpr.getPortReference().getTarget();
/*  735 */     for (Object o : ct.getPortParameter()) {
/*      */       
/*  737 */       PortParameter pptmp = (PortParameter)o;
/*  738 */       if (pptmp.equals(pp))
/*      */       {
/*  740 */         for (Object o1 : pp.getType().getDataParameter()) {
/*      */           
/*  742 */           DataParameter dptmp = (DataParameter)o1;
/*  743 */           if (dptmp.equals(dp)) {
/*      */             
/*  745 */             LIndexPortVar.add(Integer.valueOf(ct.getPortParameter().indexOf(pp)));
/*  746 */             LIndexPortVar.add(Integer.valueOf(pp.getType().getDataParameter().indexOf(dp)));
/*  747 */             return LIndexPortVar;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*  752 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void ReplaceExpression(Connector conn, Expression E, List<Variable> VarConn, List<Variable> VarConnEng, List<DCompPort> LcompPort) {
/*  758 */     Variable v = null;
/*  759 */     if (E instanceof RequiredDataParameterReference || E instanceof VariableReference) {
/*      */       
/*  761 */       if (E instanceof RequiredDataParameterReference) {
/*      */         
/*  763 */         RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  764 */         List<Integer> LIndexPortVar = getIndexPortVar(conn.getType(), rdr);
/*  765 */         int IndexPort = ((Integer)LIndexPortVar.get(0)).intValue();
/*  766 */         int IndexVar = ((Integer)LIndexPortVar.get(1)).intValue();
/*      */         
/*  768 */         InnerPortReference ipr = (InnerPortReference)conn.getActualPort().get(IndexPort);
/*  769 */         Component CompCent = (Component)ipr.getTargetInstance().getTargetPart();
/*  770 */         Port PortCent = ipr.getTargetPort();
/*  771 */         for (DCompPort o : LcompPort) {
/*      */           
/*  773 */           DCompPort compport = o;
/*  774 */           if (compport.getComponent().equals(CompCent)) {
/*      */             
/*  776 */             Port p = compport.getCorrespondPortEng(PortCent);
/*  777 */             if (p != null)
/*      */             {
/*  779 */               DefinitionBinding db = (DefinitionBinding)p.getBinding();
/*  780 */               PortDefinition pd = db.getDefinition();
/*  781 */               v = (Variable)pd.getExposedVariable().get(IndexVar);
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*  786 */       } else if (E instanceof VariableReference) {
/*      */         
/*  788 */         VariableReference vref = (VariableReference)E;
/*  789 */         Variable vconn = vref.getTargetVariable();
/*  790 */         v = VarConnEng.get(VarConn.indexOf(vconn));
/*      */       } 
/*      */ 
/*      */       
/*  794 */       VariableReference vrengine = ExpressionsFactory.eINSTANCE.createVariableReference();
/*  795 */       vrengine.setTargetVariable(v);
/*  796 */       Expression expression = E;
/*  797 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*      */         
/*  799 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/*  800 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), vrengine);
/*      */       }
/*  802 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*      */         
/*  804 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/*  805 */         if (bexp.getLeftOperand().equals(E))
/*  806 */           bexp.setLeftOperand((Expression)vrengine); 
/*  807 */         if (bexp.getRightOperand().equals(E)) {
/*  808 */           bexp.setRightOperand((Expression)vrengine);
/*      */         }
/*  810 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  815 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/*  816 */         if (aa.getAssignedTarget().equals(E))
/*  817 */           aa.setAssignedTarget((DataReference)vrengine); 
/*  818 */         if (aa.getAssignedValue().equals(E)) {
/*  819 */           aa.setAssignedValue((Expression)vrengine);
/*      */         }
/*  821 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*      */         
/*  823 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/*  824 */         UE.setOperand((Expression)vrengine);
/*      */       }
/*  826 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*      */         
/*  828 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/*  829 */         ispectmp.setGuard((Expression)vrengine);
/*      */       }
/*      */     
/*  832 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/*  834 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  835 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  837 */         Expression E1 = (Expression)o;
/*  838 */         ReplaceExpression(conn, E1, VarConn, VarConnEng, LcompPort);
/*      */       }
/*      */     
/*  841 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  843 */       BinaryExpression BE = (BinaryExpression)E;
/*  844 */       ReplaceExpression(conn, BE.getRightOperand(), VarConn, VarConnEng, LcompPort);
/*  845 */       ReplaceExpression(conn, BE.getLeftOperand(), VarConn, VarConnEng, LcompPort);
/*      */     }
/*  847 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  849 */       UnaryExpression UE = (UnaryExpression)E;
/*  850 */       ReplaceExpression(conn, UE.getOperand(), VarConn, VarConnEng, LcompPort);
/*      */     } else {
/*  852 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
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
/*      */   
/*      */   static void ReplaceExpression1(Connector conn, Expression E, List<Variable> VarConn, List<Variable> VarConnEng, List<DCompPort> LcompPort) {
/*  870 */     Variable v = null;
/*  871 */     if (E instanceof RequiredDataParameterReference || E instanceof VariableReference) {
/*      */       
/*  873 */       if (E instanceof RequiredDataParameterReference) {
/*      */         
/*  875 */         RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  876 */         List<Integer> LIndexPortVar = getIndexPortVar(conn.getType(), rdr);
/*  877 */         int IndexPort = ((Integer)LIndexPortVar.get(0)).intValue();
/*  878 */         int IndexVar = ((Integer)LIndexPortVar.get(1)).intValue();
/*      */         
/*  880 */         InnerPortReference ipr = (InnerPortReference)conn.getActualPort().get(IndexPort);
/*  881 */         Component CompCent = (Component)ipr.getTargetInstance().getTargetPart();
/*  882 */         Port PortCent = ipr.getTargetPort();
/*  883 */         for (DCompPort o : LcompPort) {
/*      */           
/*  885 */           DCompPort compport = o;
/*  886 */           if (compport.getComponent().equals(CompCent)) {
/*      */             
/*  888 */             Port p = compport.getCorrespondPortEng(PortCent);
/*  889 */             if (p != null)
/*      */             {
/*  891 */               DefinitionBinding db = (DefinitionBinding)p.getBinding();
/*  892 */               PortDefinition pd = db.getDefinition();
/*  893 */               v = (Variable)pd.getExposedVariable().get(IndexVar);
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*  898 */       } else if (E instanceof VariableReference) {
/*      */         
/*  900 */         VariableReference vref = (VariableReference)E;
/*  901 */         Variable vconn = vref.getTargetVariable();
/*  902 */         v = VarConnEng.get(VarConn.indexOf(vconn));
/*      */       } 
/*      */ 
/*      */       
/*  906 */       VariableReference vrengine = ExpressionsFactory.eINSTANCE.createVariableReference();
/*  907 */       vrengine.setTargetVariable(v);
/*  908 */       Expression expression = E;
/*  909 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*      */         
/*  911 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/*  912 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), vrengine);
/*      */       }
/*  914 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*      */         
/*  916 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/*  917 */         if (bexp.getLeftOperand().equals(E))
/*  918 */           bexp.setLeftOperand((Expression)vrengine); 
/*  919 */         if (bexp.getRightOperand().equals(E)) {
/*  920 */           bexp.setRightOperand((Expression)vrengine);
/*      */         }
/*  922 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  927 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/*  928 */         if (aa.getAssignedTarget().equals(E))
/*  929 */           aa.setAssignedTarget((DataReference)vrengine); 
/*  930 */         if (aa.getAssignedValue().equals(E)) {
/*  931 */           aa.setAssignedValue((Expression)vrengine);
/*      */         }
/*  933 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*      */         
/*  935 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/*  936 */         UE.setOperand((Expression)vrengine);
/*      */       }
/*  938 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*      */         
/*  940 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/*  941 */         ispectmp.setGuard((Expression)vrengine);
/*      */       }
/*      */     
/*  944 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/*  946 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  947 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  949 */         Expression E1 = (Expression)o;
/*  950 */         ReplaceExpression(conn, E1, VarConn, VarConnEng, LcompPort);
/*      */       }
/*      */     
/*  953 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  955 */       BinaryExpression BE = (BinaryExpression)E;
/*  956 */       ReplaceExpression(conn, BE.getRightOperand(), VarConn, VarConnEng, LcompPort);
/*  957 */       ReplaceExpression(conn, BE.getLeftOperand(), VarConn, VarConnEng, LcompPort);
/*      */     }
/*  959 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  961 */       UnaryExpression UE = (UnaryExpression)E;
/*  962 */       ReplaceExpression(conn, UE.getOperand(), VarConn, VarConnEng, LcompPort);
/*      */     } else {
/*  964 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Action CreateFucntionPrint(String s) {
/*  975 */     FunctionCallExpression FCE = ExpressionsFactory.eINSTANCE.createFunctionCallExpression();
/*  976 */     StringLiteral SL = ExpressionsFactory.eINSTANCE.createStringLiteral();
/*  977 */     SL.setSValue("\"" + s + "\"");
/*  978 */     FCE.setFunctionName("printf");
/*  979 */     FCE.getActualData().add(SL);
/*  980 */     return (Action)FCE;
/*      */   }
/*      */ 
/*      */   
/*      */   public static FunctionCallExpression CreateFunctionCall(String FunctionName, List<Variable> LVariable) {
/*  985 */     FunctionCallExpression FCE = ExpressionsFactory.eINSTANCE.createFunctionCallExpression();
/*  986 */     FCE.setFunctionName(FunctionName);
/*  987 */     for (Variable o : LVariable) {
/*      */       
/*  989 */       Variable v = o;
/*  990 */       VariableReference VR = ExpressionsFactory.eINSTANCE.createVariableReference();
/*  991 */       VR.setTargetVariable(v);
/*  992 */       FCE.getActualData().add(VR);
/*      */     } 
/*  994 */     return FCE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Component> getLComponent(Connector c) {
/* 1000 */     List<Component> LComponent = new LinkedList<Component>();
/* 1001 */     for (Object o : c.getActualPort()) {
/*      */       
/* 1003 */       InnerPortReference ipr = (InnerPortReference)o;
/* 1004 */       LComponent.add((Component)ipr.getTargetInstance().getTargetPart());
/*      */     } 
/* 1006 */     return LComponent;
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
/*      */   public static void ExpressionReplace(Expression E, List<PortParameter> LPortParameter, List<List<Variable>> LLVariable) {
/* 1018 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/* 1020 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/* 1021 */       int indexPortPara = LPortParameter.indexOf(rdr.getPortReference().getTarget());
/* 1022 */       int indexVarPara = ((PortParameter)LPortParameter.get(indexPortPara)).getType().getDataParameter().indexOf(rdr.getTargetParameter());
/*      */       
/* 1024 */       VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 1025 */       Variable Var = ((List<Variable>)LLVariable.get(indexPortPara)).get(indexVarPara);
/* 1026 */       VarRef.setTargetVariable(Var);
/* 1027 */       Expression expression = E;
/* 1028 */       if (expression.eContainer() instanceof FunctionCallExpression) {
/*      */         
/* 1030 */         FunctionCallExpression fcall = (FunctionCallExpression)expression.eContainer();
/* 1031 */         fcall.getActualData().set(fcall.getActualData().indexOf(E), VarRef);
/*      */       }
/* 1033 */       else if (expression.eContainer() instanceof BinaryExpression) {
/*      */         
/* 1035 */         BinaryExpression bexp = (BinaryExpression)expression.eContainer();
/* 1036 */         if (bexp.getLeftOperand().equals(E))
/* 1037 */           bexp.setLeftOperand((Expression)VarRef); 
/* 1038 */         if (bexp.getRightOperand().equals(E)) {
/* 1039 */           bexp.setRightOperand((Expression)VarRef);
/*      */         }
/* 1041 */       } else if (expression.eContainer() instanceof AssignmentAction) {
/*      */         
/* 1043 */         AssignmentAction aa = (AssignmentAction)expression.eContainer();
/* 1044 */         if (aa.getAssignedTarget().equals(E))
/* 1045 */           aa.setAssignedTarget((DataReference)VarRef); 
/* 1046 */         if (aa.getAssignedValue().equals(E)) {
/* 1047 */           aa.setAssignedValue((Expression)VarRef);
/*      */         }
/* 1049 */       } else if (expression.eContainer() instanceof UnaryExpression) {
/*      */         
/* 1051 */         UnaryExpression UE = (UnaryExpression)expression.eContainer();
/* 1052 */         UE.setOperand((Expression)VarRef);
/*      */       }
/* 1054 */       else if (expression.eContainer() instanceof InteractionSpecification) {
/*      */         
/* 1056 */         InteractionSpecification ispectmp = (InteractionSpecification)expression.eContainer();
/* 1057 */         ispectmp.setGuard((Expression)VarRef);
/*      */       }
/*      */     
/* 1060 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/* 1062 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 1063 */       for (Object o : Fcall.getActualData())
/*      */       {
/* 1065 */         Expression E1 = (Expression)o;
/* 1066 */         ExpressionReplace(E1, LPortParameter, LLVariable);
/*      */       }
/*      */     
/* 1069 */     } else if (E instanceof BinaryExpression) {
/*      */       
/* 1071 */       BinaryExpression BE = (BinaryExpression)E;
/* 1072 */       ExpressionReplace(BE.getRightOperand(), LPortParameter, LLVariable);
/* 1073 */       ExpressionReplace(BE.getLeftOperand(), LPortParameter, LLVariable);
/*      */     }
/* 1075 */     else if (E instanceof UnaryExpression) {
/*      */       
/* 1077 */       UnaryExpression UE = (UnaryExpression)E;
/* 1078 */       ExpressionReplace(UE.getOperand(), LPortParameter, LLVariable);
/*      */     } else {
/* 1080 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Component CreateComponent(String name, ComponentType ct, CompoundType compoundType, List LParameter) {
/* 1089 */     Component comp = InteractionsFactory.eINSTANCE.createComponent();
/* 1090 */     comp.setName(name);
/* 1091 */     comp.setType(ct);
/* 1092 */     if (LParameter != null)
/*      */     {
/* 1094 */       for (Object o : LParameter) {
/*      */         
/* 1096 */         Expression parameter = (Expression)EcoreUtil.copy((EObject)o);
/* 1097 */         comp.getActualData().add(parameter);
/*      */       } 
/*      */     }
/* 1100 */     compoundType.getSubcomponent().add(comp);
/* 1101 */     return comp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Connector CreateConnector(String name, ConnectorType connType, CompoundType compoundType, List<Component> LComponent, List<Port> LPort) {
/* 1111 */     Connector connector = InteractionsFactory.eINSTANCE.createConnector();
/* 1112 */     connector.setName(name);
/* 1113 */     connector.setType(connType);
/* 1114 */     connector.setCompoundType(compoundType);
/*      */     
/* 1116 */     for (Component o : LComponent) {
/*      */       
/* 1118 */       Component comp = o;
/* 1119 */       Port p = LPort.get(LComponent.indexOf(o));
/* 1120 */       InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 1121 */       ipr.setTargetPort(p);
/* 1122 */       PartElementReference PE = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 1123 */       PE.setTargetPart((Part)comp);
/* 1124 */       ipr.setTargetInstance(PE);
/* 1125 */       connector.getActualPort().add(ipr);
/*      */     } 
/* 1127 */     return connector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port CreatePort(String name, Component component, Port p1, CompoundType compoundType) {
/* 1137 */     Port p = BehaviorsFactory.eINSTANCE.createPort();
/* 1138 */     ExportBinding eb = InteractionsFactory.eINSTANCE.createExportBinding();
/* 1139 */     PartElementReference PER = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 1140 */     PER.setTargetPart((Part)component);
/* 1141 */     eb.setTargetInstance(PER);
/* 1142 */     eb.setTargetPort(p1);
/* 1143 */     p.setBinding((Binding)eb);
/* 1144 */     p.setName(name);
/* 1145 */     p.setType(p1.getType());
/* 1146 */     p.setComponentType((ComponentType)compoundType);
/* 1147 */     compoundType.getPort().add(p);
/* 1148 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InteractionSpecification CreateInteractionSpecification(ConnectorType connType) {
/* 1157 */     InteractionSpecification intspec = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/* 1158 */     Interaction inter = InteractionsFactory.eINSTANCE.createInteraction();
/* 1159 */     intspec.setInteraction(inter);
/* 1160 */     for (Object o : connType.getPortParameter()) {
/*      */       
/* 1162 */       PortParameter pp = (PortParameter)o;
/* 1163 */       PortParameterReference PortParaRef = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 1164 */       PortParaRef.setTarget(pp);
/* 1165 */       inter.getPort().add(PortParaRef);
/*      */     } 
/* 1167 */     intspec.setDownAction((Action)ActionsFactory.eINSTANCE.createCompositeAction());
/* 1168 */     intspec.setUpAction((Action)ActionsFactory.eINSTANCE.createCompositeAction());
/* 1169 */     return intspec;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static AtomType getCopy(AtomType at) {
/* 1175 */     AtomType copyat = BehaviorsFactory.eINSTANCE.createAtomType();
/* 1176 */     copyat = (AtomType)EcoreUtil.copy((EObject)at);
/* 1177 */     return copyat;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean IsIntersectState(List<State> LState1, List<State> LState2) {
/* 1182 */     for (State o1 : LState1) {
/* 1183 */       for (State o2 : LState2) {
/* 1184 */         if (o1.equals(o2))
/* 1185 */           return true; 
/*      */       } 
/*      */     } 
/* 1188 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean IsConflictPetriNet(PetriNet PN) {
/* 1193 */     for (Object o1 : PN.getTransition()) {
/*      */       
/* 1195 */       Transition t1 = (Transition)o1;
/* 1196 */       for (Object o2 : PN.getTransition()) {
/*      */         
/* 1198 */         Transition t2 = (Transition)o2;
/* 1199 */         EList eList1 = t1.getOrigin();
/* 1200 */         EList eList2 = t2.getOrigin();
/* 1201 */         if (!t1.equals(t2) && IsIntersectState((List<State>)eList1, (List<State>)eList2) && (eList1.size() > 1 || eList2.size() > 1)) {
/*      */           
/* 1203 */           System.out.println("Error input : you do not have the right to use Petri Net with conflict.");
/* 1204 */           System.out.println("Verify atom type :  " + PN.getAtomType().getName() + "\nConflicts Transitions Labelled by the ports : \n" + ((PortDefinitionReference)t1.getTrigger()).getTarget().getName() + "\n" + ((PortDefinitionReference)t2.getTrigger()).getTarget().getName());
/* 1205 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1209 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\TransformationFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */