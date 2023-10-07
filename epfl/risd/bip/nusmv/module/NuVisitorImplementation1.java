/*      */ package epfl.risd.bip.nusmv.module;
/*      */ 
/*      */ import epfl.risd.bip.nusmv.api.NuBinaryOperator;
/*      */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*      */ import epfl.risd.bip.nusmv.api.NuPair;
/*      */ import epfl.risd.bip.nusmv.api.NuUnaryOperator;
/*      */ import epfl.risd.bip.nusmv.expression.NuAssignmentAction;
/*      */ import epfl.risd.bip.nusmv.expression.NuBinaryExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuConnector;
/*      */ import epfl.risd.bip.nusmv.expression.NuConstant;
/*      */ import epfl.risd.bip.nusmv.expression.NuDefineAction;
/*      */ import epfl.risd.bip.nusmv.expression.NuExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuExpressionHelper;
/*      */ import epfl.risd.bip.nusmv.expression.NuInitExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuInvarExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuNamedElement;
/*      */ import epfl.risd.bip.nusmv.expression.NuPort;
/*      */ import epfl.risd.bip.nusmv.expression.NuSymbolicEnumVariable;
/*      */ import epfl.risd.bip.nusmv.expression.NuTransExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuUnaryExpression;
/*      */ import epfl.risd.bip.nusmv.expression.NuUnsignedWordVariable;
/*      */ import epfl.risd.bip.nusmv.expression.NuVarAction;
/*      */ import epfl.risd.bip.nusmv.expression.NuVariable;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Scanner;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
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
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NuVisitorImplementation1
/*      */   implements NuVisitor
/*      */ {
/*      */   private Map<Component, NuModule> mapCompModule;
/*      */   private Map<Connector, NuConnector> mapConnectors;
/*      */   private Map<String, Map<String, Integer>> guide;
/*      */   private Map<String, Integer> tempGuide;
/*      */   private Map<String, NuPort> mapPort;
/*      */   private List<String> modifiedVariables;
/*      */   private List<String> tempInteractions;
/*      */   private List<String> tempAllVariables;
/*      */   private NuExpression tempExpression;
/*      */   private NuModule mainModule;
/*      */   private NuModule tempModule;
/*   81 */   private Integer defaultWordSize = Integer.valueOf(3);
/*      */   
/*      */   private static final String MAIN = "main";
/*      */   
/*      */   private static final String ACTIVEINTERACTION = "active_interaction";
/*      */   private static final String INTERACTIONPREFIX = "NuI";
/*      */   private static final String VARIABLEPREFIX = "NuV";
/*      */   private static final String DATAPARAMPREFIX = "NuDP";
/*      */   private static final String STATEPREFIX = "NuS";
/*      */   private static final String PORTPREFIX = "NuP";
/*      */   private static final String INTERACTIONVARIABLE = "NuInteraction";
/*      */   private static final String PLACEVARIABLE = "Nuplace";
/*   93 */   private static final NuBinaryOperator AND = NuBinaryOperator.LOGICAL_AND;
/*   94 */   private static final NuBinaryOperator EQUALITY = NuBinaryOperator.EQUALITY;
/*   95 */   private static final NuBinaryOperator INEQUALITY = NuBinaryOperator.INEQUALITY;
/*   96 */   private static final NuBinaryOperator IMPLIES = NuBinaryOperator.IMPLIES;
/*   97 */   private static final NuBinaryOperator EQUIVELANT = NuBinaryOperator.EQUIVELANT;
/*      */ 
/*      */   
/*      */   public NuVisitorImplementation1() {
/*  101 */     this.mapCompModule = new HashMap<Component, NuModule>();
/*  102 */     this.mapConnectors = new HashMap<Connector, NuConnector>();
/*  103 */     this.guide = new HashMap<String, Map<String, Integer>>();
/*  104 */     this.tempGuide = new HashMap<String, Integer>();
/*  105 */     this.mapPort = new HashMap<String, NuPort>();
/*      */     
/*  107 */     this.modifiedVariables = new LinkedList<String>();
/*  108 */     this.tempInteractions = new LinkedList<String>();
/*  109 */     this.tempAllVariables = new LinkedList<String>();
/*      */     
/*  111 */     this.mainModule = new NuModule();
/*  112 */     this.mainModule.setName("main");
/*      */   }
/*      */ 
/*      */   
/*      */   public void execute(CompoundType ct, String guideFile) {
/*  117 */     if (guideFile != null) parseGuideFile(guideFile); 
/*  118 */     visitCompound(ct);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<NuModule> getModules() {
/*  123 */     return this.mapCompModule.values();
/*      */   }
/*      */ 
/*      */   
/*      */   public NuModule getMain() {
/*  128 */     return this.mainModule;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void parseGuideFile(String guideFile) {
/*      */     try {
/*  135 */       Scanner in = new Scanner(new FileReader(guideFile));
/*      */       
/*  137 */       int numOfConnectors = in.nextInt();
/*  138 */       for (int i = 0; i < numOfConnectors; i++) {
/*      */         
/*  140 */         String connectorName = in.next();
/*  141 */         Map<String, Integer> mapVarSize = new HashMap<String, Integer>();
/*  142 */         int numOfVars = in.nextInt();
/*  143 */         for (int j = 0; j < numOfVars; j++) {
/*      */           
/*  145 */           String varName = in.next();
/*  146 */           Integer varSize = Integer.valueOf(in.nextInt());
/*  147 */           mapVarSize.put(varName, varSize);
/*      */         } 
/*  149 */         this.guide.put(connectorName, mapVarSize);
/*      */       } 
/*      */       
/*  152 */       String def = in.next();
/*  153 */       if (def.equals("default")) { this.defaultWordSize = Integer.valueOf(in.nextInt()); }
/*  154 */       else { throw new Error("default not specified in guide"); }
/*      */     
/*  156 */     } catch (FileNotFoundException e) {
/*  157 */       throw new Error("Guide File doesn't exist!");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void visitCompound(CompoundType ct) {
/*  164 */     for (Connector cnct : ct.getConnector()) {
/*  165 */       visitConnector(cnct);
/*      */     }
/*      */     
/*  168 */     for (Component comp : ct.getSubcomponent()) {
/*      */       
/*  170 */       if (comp.getType() instanceof AtomType) {
/*      */         
/*  172 */         AtomType at = (AtomType)comp.getType();
/*  173 */         initializeModule(comp);
/*  174 */         visitAtomic(at);
/*  175 */         addDefaultTransition();
/*  176 */         this.mapCompModule.put(comp, this.tempModule); continue;
/*      */       } 
/*  178 */       throw new Error("Unimplemented Subcomponent Type");
/*      */     } 
/*      */     
/*  181 */     clearTemps();
/*  182 */     constructMain();
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitConnector(Connector cnct) {
/*  187 */     NuConnector connector = new NuConnector();
/*  188 */     connector.setName(cnct.getName());
/*      */     
/*  190 */     int interactionNumber = this.mapConnectors.size() + 1;
/*  191 */     String interaction = "NuI" + Integer.toString(interactionNumber);
/*  192 */     connector.setInteraction(interaction);
/*      */     
/*  194 */     assignConnectorPortInteractions(cnct, connector, interaction);
/*      */     
/*  196 */     if (!cnct.getType().getInteractionSpecification().isEmpty()) {
/*      */       
/*  198 */       Expression guard = ((InteractionSpecification)cnct.getType().getInteractionSpecification().get(0)).getGuard();
/*  199 */       connector.setGuard(guard);
/*  200 */       getModifiedVars(cnct, connector);
/*      */     } 
/*      */     
/*  203 */     this.mapConnectors.put(cnct, connector);
/*      */   }
/*      */ 
/*      */   
/*      */   private void assignConnectorPortInteractions(Connector cnct, NuConnector connector, String interaction) {
/*  208 */     List<NuPort> ports = new LinkedList<NuPort>();
/*      */     
/*  210 */     int numOfPorts = cnct.getActualPort().size();
/*  211 */     for (int i = 0; i < numOfPorts; i++) {
/*      */       
/*  213 */       InnerPortReference ipr = (InnerPortReference)cnct.getActualPort().get(i);
/*  214 */       Port p = ipr.getTargetPort();
/*      */       
/*  216 */       String componentName = ipr.getTargetInstance().getTargetPart().getName().toLowerCase();
/*  217 */       String portName = "NuP" + p.getName();
/*      */       
/*  219 */       NuPort port = new NuPort(componentName, portName, null, null, null);
/*  220 */       if (this.mapPort.containsKey(port.toString())) port = this.mapPort.get(port.toString()); 
/*  221 */       port.addInteraction(interaction);
/*  222 */       ports.add(port);
/*      */       
/*  224 */       if (!this.mapPort.containsKey(port.toString())) this.mapPort.put(port.toString(), port);
/*      */       
/*  226 */       String formalPortName = ((PortParameter)cnct.getType().getPortParameter().get(i)).getName();
/*  227 */       connector.addMapping(formalPortName, port);
/*      */     } 
/*      */     
/*  230 */     connector.setPorts(ports);
/*      */   }
/*      */ 
/*      */   
/*      */   private void getModifiedVars(Connector cnct, NuConnector connector) {
/*  235 */     Action down = ((InteractionSpecification)cnct.getType().getInteractionSpecification().get(0)).getDownAction();
/*  236 */     if (down instanceof CompositeAction) {
/*      */       
/*  238 */       CompositeAction downComp = (CompositeAction)down;
/*  239 */       for (Action act : downComp.getContent())
/*      */       {
/*  241 */         getModifiedVar(act, connector);
/*      */       }
/*      */     }
/*  244 */     else if (down instanceof AssignmentAction) {
/*      */       
/*  246 */       getModifiedVar(down, connector);
/*      */     } else {
/*  248 */       throw new Error("Unimplemented Action");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void getModifiedVar(Action act, NuConnector connector) {
/*  253 */     AssignmentAction assignment = (AssignmentAction)act;
/*  254 */     RequiredDataParameterReference target = (RequiredDataParameterReference)assignment.getAssignedTarget();
/*  255 */     String targetPortName = target.getPortReference().getTarget().getName();
/*  256 */     String targetVarName = target.getTargetParameter().getName();
/*      */     
/*  258 */     NuPort port = (NuPort)connector.getMapPorts().get(targetPortName);
/*  259 */     NuPair modifiedVar = new NuPair(port, targetVarName);
/*  260 */     connector.addModifiedVar(modifiedVar);
/*      */   }
/*      */   
/*      */   private void initializeModule(Component comp) {
/*  264 */     this.tempModule = new NuModule();
/*  265 */     this.tempModule.setName(comp.getName().toUpperCase());
/*  266 */     this.tempModule.addParameter("active_interaction");
/*  267 */     clearTemps();
/*      */   }
/*      */   
/*      */   private void clearTemps() {
/*  271 */     this.tempAllVariables.clear();
/*  272 */     this.tempInteractions.clear();
/*  273 */     this.tempGuide.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitAtomic(AtomType at) {
/*  278 */     for (PortDefinition portDef : at.getPortDefinition()) {
/*  279 */       visitPort(portDef);
/*      */     }
/*  281 */     for (DataParameter dp : at.getDataParameter()) {
/*  282 */       visitAtomicDataParam(dp, at);
/*      */     }
/*  284 */     for (Variable var : at.getVariable()) {
/*  285 */       visitAtomicVariable(var, at);
/*      */     }
/*  287 */     PetriNet pn = (PetriNet)at.getBehavior();
/*  288 */     visitBehavior(pn);
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitPort(PortDefinition portDef) {
/*  293 */     String portName = "NuP" + portDef.getName();
/*  294 */     String componentName = this.tempModule.getName().toLowerCase();
/*  295 */     String portNotation = String.valueOf(componentName) + "." + portName;
/*  296 */     NuPort port = this.mapPort.get(portNotation);
/*      */ 
/*      */     
/*  299 */     NuVariable variable = new NuVariable(portName, NuEnumType.BOOLEAN);
/*  300 */     addVariable(variable, this.tempModule);
/*      */ 
/*      */     
/*  303 */     PortType pt = portDef.getType();
/*  304 */     int numOfVars = pt.getDataParameter().size();
/*      */     
/*  306 */     for (int i = 0; i < numOfVars; i++) {
/*      */       
/*  308 */       String dataParam = ((DataParameter)pt.getDataParameter().get(i)).getName();
/*  309 */       String varName = ((Variable)portDef.getExposedVariable().get(i)).getName();
/*  310 */       port.addMapping(dataParam, varName);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitAtomicDataParam(DataParameter dp, AtomType at) {
/*  316 */     int size = getWordSize(dp.getName(), at, this.defaultWordSize.intValue());
/*      */     
/*  318 */     String parameterName = "NuDP" + dp.getName();
/*  319 */     this.tempModule.addParameter(parameterName);
/*      */     
/*  321 */     this.tempGuide.put(parameterName, Integer.valueOf(size));
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitAtomicVariable(Variable var, AtomType at) {
/*  326 */     int size = getWordSize(var.getName(), at, this.defaultWordSize.intValue());
/*      */     
/*  328 */     OpaqueElement oe = (OpaqueElement)var.getType();
/*  329 */     NuVariable variable = getVarType(var, oe, at, size);
/*      */     
/*  331 */     this.tempGuide.put(variable.getName(), Integer.valueOf(size));
/*      */ 
/*      */     
/*  334 */     addVariable(variable, this.tempModule);
/*      */ 
/*      */     
/*  337 */     addInitialValue(var, variable, size);
/*      */   }
/*      */ 
/*      */   
/*      */   private NuVariable getVarType(Variable var, OpaqueElement oe, AtomType at, int size) {
/*  342 */     String varName = "NuV" + var.getName();
/*  343 */     this.tempAllVariables.add(varName);
/*      */     
/*  345 */     if (oe.getBody().equals("int"))
/*      */     {
/*  347 */       return (NuVariable)new NuUnsignedWordVariable(varName, size, 'd');
/*      */     }
/*  349 */     if (oe.getBody().equals("bool")) {
/*      */       
/*  351 */       NuEnumType varType = NuEnumType.BOOLEAN;
/*  352 */       return new NuVariable(varName, varType);
/*      */     } 
/*  354 */     throw new Error("Unimplemented variable type");
/*      */   }
/*      */ 
/*      */   
/*      */   private void addInitialValue(Variable var, NuVariable variable, int size) {
/*  359 */     Expression initialVal = var.getInitialValue();
/*  360 */     NuExpression initialNuVal = convertExpression(initialVal, (NuConnector)null, size);
/*      */     
/*  362 */     if (initialNuVal != null) {
/*      */       
/*  364 */       NuBinaryExpression initial = new NuBinaryExpression(EQUALITY, (NuExpression)variable, initialNuVal);
/*  365 */       addInitExpression((NuExpression)initial);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitBehavior(PetriNet pn) {
/*  371 */     NuSymbolicEnumVariable places = new NuSymbolicEnumVariable("Nuplace", null);
/*      */ 
/*      */     
/*  374 */     for (State s : pn.getState()) {
/*  375 */       places.addEnumeration("NuS" + s.getName());
/*      */     }
/*      */     
/*  378 */     addVariable((NuVariable)places, this.tempModule);
/*      */ 
/*      */     
/*  381 */     addInitialState(pn, places);
/*      */ 
/*      */     
/*  384 */     addInitialization(pn);
/*      */     
/*  386 */     for (Transition t : pn.getTransition()) {
/*  387 */       visitTransition(t, pn.getAtomType());
/*      */     }
/*      */     
/*  390 */     addInvariation();
/*      */   }
/*      */ 
/*      */   
/*      */   private void addInitialState(PetriNet pn, NuSymbolicEnumVariable places) {
/*  395 */     for (State s : pn.getInitialState()) {
/*      */       
/*  397 */       String stateName = "NuS" + s.getName();
/*  398 */       NuNamedElement initialState = new NuNamedElement(stateName);
/*  399 */       NuBinaryExpression initial = new NuBinaryExpression(EQUALITY, (NuExpression)places, (NuExpression)initialState);
/*  400 */       addInitExpression((NuExpression)initial);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addInitialization(PetriNet pn) {
/*  406 */     Action action = pn.getInitialization();
/*  407 */     if (action instanceof CompositeAction) {
/*      */       
/*  409 */       CompositeAction ca = (CompositeAction)action;
/*      */       
/*  411 */       for (Action act : ca.getContent()) {
/*      */         
/*  413 */         visitAction(act, null, true, pn.getAtomType());
/*  414 */         addInitExpression(this.tempExpression);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addInvariation() {
/*  421 */     for (NuPort port : this.mapPort.values()) {
/*      */       
/*  423 */       if (this.tempModule.getName().toLowerCase().equals(port.getComponent())) {
/*      */         
/*  425 */         NuNamedElement portName = new NuNamedElement(port.getPort());
/*  426 */         NuExpression enable = port.getEnablement();
/*  427 */         NuBinaryExpression invariation = new NuBinaryExpression(EQUIVELANT, (NuExpression)portName, enable);
/*  428 */         addInvarExpression((NuExpression)invariation, this.tempModule);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitAction(Action act, String interaction, boolean init, AtomType at) {
/*  435 */     if (act instanceof AssignmentAction) {
/*      */       NuNamedElement target;
/*  437 */       int size = this.defaultWordSize.intValue();
/*      */ 
/*      */       
/*  440 */       AssignmentAction assignAct = (AssignmentAction)act;
/*  441 */       DataReference dataRef = assignAct.getAssignedTarget();
/*  442 */       if (dataRef instanceof VariableReference) {
/*      */         
/*  444 */         VariableReference varRef = (VariableReference)dataRef;
/*  445 */         Variable var = varRef.getTargetVariable();
/*      */         
/*  447 */         String varName = "NuV" + var.getName();
/*  448 */         if (init) { target = new NuNamedElement(varName); }
/*  449 */         else { target = new NuNamedElement(next(varName)); }
/*  450 */          this.modifiedVariables.add(varName);
/*      */         
/*  452 */         size = getWordSize(var.getName(), at, size);
/*      */       } else {
/*  454 */         throw new Error("Unimplemented Assignment Action");
/*      */       } 
/*  456 */       NuConnector connector = findConnector(interaction);
/*      */       
/*  458 */       Expression exp = assignAct.getAssignedValue();
/*  459 */       NuExpression assignedVal = null;
/*  460 */       if (interaction == null) { assignedVal = convertExpression(exp, (NuConnector)null, size); }
/*  461 */       else { assignedVal = convertExpression(exp, connector, true, size); }
/*  462 */        this.tempExpression = (NuExpression)new NuBinaryExpression(EQUALITY, (NuExpression)target, assignedVal);
/*      */     } else {
/*  464 */       throw new Error("Unimplemented Action");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void visitTransition(Transition t, AtomType at) {
/*  470 */     NuBinaryExpression origin = getOrigin(t);
/*  471 */     NuBinaryExpression destination = getDestination(t);
/*  472 */     NuBinaryExpression updatePlace = new NuBinaryExpression(AND, (NuExpression)origin, (NuExpression)destination);
/*      */ 
/*      */     
/*  475 */     NuPort port = getTransitionPort(t);
/*  476 */     List<String> interactionNames = new LinkedList<String>();
/*  477 */     interactionNames.addAll(port.getInteractions());
/*      */     
/*  479 */     NuNamedElement activeInteraction = new NuNamedElement("active_interaction");
/*      */     
/*  481 */     for (String interactionName : interactionNames) {
/*      */       
/*  483 */       if (!this.tempInteractions.contains(interactionName)) this.tempInteractions.add(interactionName);
/*      */       
/*  485 */       NuBinaryExpression transition = addInteractionName((NuExpression)activeInteraction, interactionName, (NuExpression)updatePlace);
/*  486 */       transition = addTransitionAction((NuExpression)transition, interactionName, t, at);
/*  487 */       transition = addReduntantVars(transition, interactionName);
/*      */       
/*  489 */       addTransExpression((NuExpression)transition, false);
/*      */     } 
/*      */     
/*  492 */     NuBinaryExpression portEnable = addGuard(origin, t);
/*  493 */     port.addEnablement((NuExpression)portEnable);
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression getOrigin(Transition t) {
/*  498 */     String originStateName = "NuS" + ((State)t.getOrigin().get(0)).getName();
/*  499 */     NuNamedElement originState = new NuNamedElement(originStateName);
/*  500 */     NuNamedElement place = new NuNamedElement("Nuplace");
/*      */     
/*  502 */     return new NuBinaryExpression(EQUALITY, (NuExpression)place, (NuExpression)originState);
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression getDestination(Transition t) {
/*  507 */     String destStateName = "NuS" + ((State)t.getDestination().get(0)).getName();
/*  508 */     NuNamedElement destState = new NuNamedElement(destStateName);
/*  509 */     NuNamedElement nextPlace = new NuNamedElement(next("Nuplace"));
/*      */     
/*  511 */     return new NuBinaryExpression(EQUALITY, (NuExpression)nextPlace, (NuExpression)destState);
/*      */   }
/*      */ 
/*      */   
/*      */   private NuPort getTransitionPort(Transition t) {
/*  516 */     PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
/*  517 */     String portName = "NuP" + portDefRef.getTarget().getName();
/*  518 */     String portNotation = String.valueOf(this.tempModule.getName().toLowerCase()) + "." + portName;
/*  519 */     return this.mapPort.get(portNotation);
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression addInteractionName(NuExpression active, String interactionName, NuExpression place) {
/*  524 */     NuNamedElement chosenInteraction = new NuNamedElement(interactionName);
/*  525 */     NuBinaryExpression interaction = new NuBinaryExpression(EQUALITY, active, (NuExpression)chosenInteraction);
/*  526 */     return new NuBinaryExpression(AND, place, (NuExpression)interaction);
/*      */   }
/*      */   
/*      */   private NuBinaryExpression addTransitionAction(NuExpression transition, String interaction, Transition t, AtomType at) {
/*      */     NuBinaryExpression nuBinaryExpression;
/*  531 */     this.modifiedVariables.clear();
/*  532 */     if (t.getAction() instanceof CompositeAction)
/*      */     
/*  534 */     { CompositeAction ca = (CompositeAction)t.getAction();
/*  535 */       for (Action act : ca.getContent())
/*      */       {
/*  537 */         visitAction(act, interaction, false, at);
/*  538 */         nuBinaryExpression = new NuBinaryExpression(AND, transition, this.tempExpression);
/*      */       }
/*      */        }
/*  541 */     else if (t.getAction() instanceof AssignmentAction)
/*      */     
/*  543 */     { visitAction(t.getAction(), interaction, false, at);
/*  544 */       nuBinaryExpression = new NuBinaryExpression(AND, (NuExpression)nuBinaryExpression, this.tempExpression); }
/*      */     
/*  546 */     else if (t.getAction() != null) { throw new Error("Unimplemented Action"); }
/*      */     
/*  548 */     return nuBinaryExpression;
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression addReduntantVars(NuBinaryExpression transition, String interaction) {
/*  553 */     NuConnector connector = findConnector(interaction);
/*  554 */     List<NuPair> interactionVars = connector.getModifiedVars();
/*      */     
/*  556 */     updateInteractionVars(interactionVars, connector);
/*      */     
/*  558 */     for (String variable : this.tempAllVariables) {
/*      */       
/*  560 */       if (!this.modifiedVariables.contains(variable) && !found(variable, interactionVars)) {
/*      */         
/*  562 */         NuNamedElement var = new NuNamedElement(variable);
/*  563 */         NuNamedElement nextVar = new NuNamedElement(next(variable));
/*  564 */         NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, (NuExpression)nextVar, (NuExpression)var);
/*  565 */         transition = new NuBinaryExpression(AND, (NuExpression)transition, (NuExpression)updateVar);
/*      */       } 
/*      */     } 
/*      */     
/*  569 */     return transition;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateInteractionVars(List<NuPair> interactionVars, NuConnector connector) {
/*  574 */     for (NuPair pair : interactionVars) {
/*      */       
/*  576 */       if (pair.getOld() && pair.getPort().getComponent().equalsIgnoreCase(this.tempModule.getName())) {
/*      */         
/*  578 */         String varName = "NuV";
/*  579 */         for (NuPort port : connector.getPorts()) {
/*      */           
/*  581 */           if (port.toString().equals(pair.getPort().toString()))
/*  582 */             varName = String.valueOf(varName) + (String)port.getMapVars().get(pair.getVariable()); 
/*      */         } 
/*  584 */         pair.setVariable(varName);
/*  585 */         pair.setOld(false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean found(String variable, List<NuPair> interactionVars) {
/*  592 */     for (NuPair pair : interactionVars) {
/*  593 */       if (pair.getPort().getComponent().equalsIgnoreCase(this.tempModule.getName()) && 
/*  594 */         pair.getVariable().equals(variable)) return true; 
/*      */     } 
/*  596 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression addGuard(NuBinaryExpression origin, Transition t) {
/*  601 */     Expression guard = t.getGuard();
/*  602 */     if (guard == null) return origin;
/*      */     
/*  604 */     NuExpression guardNu = convertExpression(guard, null);
/*  605 */     return new NuBinaryExpression(AND, (NuExpression)origin, guardNu);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addVariable(NuVariable variable, NuModule module) {
/*  612 */     List<NuVarAction> vars = module.getVars();
/*      */     
/*  614 */     if (vars.isEmpty()) {
/*      */       
/*  616 */       NuVarAction varAct = new NuVarAction();
/*  617 */       varAct.addVariable(variable);
/*  618 */       module.addVar(varAct);
/*      */     }
/*      */     else {
/*      */       
/*  622 */       ((NuVarAction)vars.get(0)).addVariable(variable);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addDefine(NuAssignmentAction assign) {
/*  629 */     List<NuDefineAction> defines = this.mainModule.getDefines();
/*  630 */     if (defines.isEmpty()) {
/*      */       
/*  632 */       NuDefineAction def = new NuDefineAction();
/*  633 */       def.addAction(assign);
/*  634 */       this.mainModule.addDefine(def);
/*      */     }
/*      */     else {
/*      */       
/*  638 */       ((NuDefineAction)defines.get(0)).addAction(assign);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addInitExpression(NuExpression initial) {
/*  645 */     List<NuInitExpression> inits = this.tempModule.getInits();
/*      */     
/*  647 */     if (inits.isEmpty()) {
/*      */       
/*  649 */       NuInitExpression init = new NuInitExpression();
/*  650 */       init.addExpression(initial);
/*  651 */       this.tempModule.addInit(init);
/*      */     }
/*      */     else {
/*      */       
/*  655 */       ((NuInitExpression)inits.get(0)).addExpression(initial);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addInvarExpression(NuExpression invariation, NuModule module) {
/*  662 */     List<NuInvarExpression> invars = module.getInvars();
/*      */     
/*  664 */     if (invars.isEmpty()) {
/*      */       
/*  666 */       NuInvarExpression invar = new NuInvarExpression();
/*  667 */       invar.addExpression(invariation);
/*  668 */       module.addInvar(invar);
/*      */     }
/*      */     else {
/*      */       
/*  672 */       ((NuInvarExpression)invars.get(0)).addExpression(invariation);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addTransExpression(NuExpression transition, boolean main) {
/*  679 */     List<NuTransExpression> trans = null;
/*  680 */     if (main) { trans = this.mainModule.getTrans(); }
/*  681 */     else { trans = this.tempModule.getTrans(); }
/*      */     
/*  683 */     if (trans.isEmpty()) {
/*      */       
/*  685 */       NuTransExpression tran = new NuTransExpression();
/*  686 */       tran.addExpression(transition);
/*  687 */       if (main) { this.mainModule.addTran(tran); }
/*  688 */       else { this.tempModule.addTran(tran); }
/*      */     
/*      */     } else {
/*      */       
/*  692 */       ((NuTransExpression)trans.get(0)).addExpression(transition);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addDefaultTransition() {
/*  699 */     NuNamedElement place = new NuNamedElement("Nuplace");
/*  700 */     NuNamedElement nextPlace = new NuNamedElement(next("Nuplace"));
/*  701 */     NuBinaryExpression transition = new NuBinaryExpression(EQUALITY, (NuExpression)nextPlace, (NuExpression)place);
/*      */     
/*  703 */     for (String variable : this.tempAllVariables) {
/*      */       
/*  705 */       NuNamedElement var = new NuNamedElement(variable);
/*  706 */       NuNamedElement nextVar = new NuNamedElement(next(variable));
/*  707 */       NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, (NuExpression)nextVar, (NuExpression)var);
/*      */       
/*  709 */       transition = new NuBinaryExpression(AND, (NuExpression)transition, (NuExpression)updateVar);
/*      */     } 
/*      */     
/*  712 */     NuNamedElement activeInt = new NuNamedElement("active_interaction");
/*      */     
/*  714 */     for (String interaction : this.tempInteractions) {
/*      */       
/*  716 */       NuNamedElement chosenInt = new NuNamedElement(interaction);
/*  717 */       NuBinaryExpression notInt = new NuBinaryExpression(INEQUALITY, (NuExpression)activeInt, (NuExpression)chosenInt);
/*      */       
/*  719 */       transition = new NuBinaryExpression(AND, (NuExpression)notInt, (NuExpression)transition);
/*      */     } 
/*      */     
/*  722 */     addTransExpression((NuExpression)transition, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void constructMain() {
/*  727 */     addInteractionsEnumerationMain();
/*  728 */     addModulesMain();
/*  729 */     addDefinesMain();
/*  730 */     addInvarsMain();
/*  731 */     addTransMain();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addInteractionsEnumerationMain() {
/*  737 */     NuSymbolicEnumVariable variable = new NuSymbolicEnumVariable("NuInteraction", null);
/*      */     
/*  739 */     int interactions = this.mapConnectors.size();
/*  740 */     for (int i = 1; i <= interactions; i++) {
/*      */       
/*  742 */       String interaction = "NuI" + Integer.toString(i);
/*  743 */       variable.addEnumeration(interaction);
/*      */     } 
/*      */     
/*  746 */     addVariable((NuVariable)variable, this.mainModule);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addModulesMain() {
/*  752 */     for (Component comp : this.mapCompModule.keySet()) {
/*      */       
/*  754 */       NuModule module = this.mapCompModule.get(comp);
/*      */       
/*  756 */       String instant = String.valueOf(module.getName().toLowerCase()) + "\t" + ":";
/*  757 */       instant = String.valueOf(instant) + "\t" + module.getName().toUpperCase() + "(" + "NuInteraction";
/*      */       
/*  759 */       int numOfParameters = comp.getActualData().size();
/*  760 */       for (int i = 0; i < numOfParameters; i++) {
/*      */         
/*  762 */         String componentName = comp.getType().getName();
/*  763 */         String parameterName = ((DataParameter)comp.getType().getDataParameter().get(i)).getName();
/*  764 */         int size = getWordSize(parameterName, componentName, this.defaultWordSize.intValue());
/*      */         
/*  766 */         Expression exp = (Expression)comp.getActualData().get(i);
/*  767 */         NuExpression parameter = convertExpression(exp, (NuConnector)null, size);
/*  768 */         instant = String.valueOf(instant) + ", " + NuExpressionHelper.expressionToString(parameter);
/*      */       } 
/*      */       
/*  771 */       instant = String.valueOf(instant) + ")";
/*      */       
/*  773 */       NuVariable instantiation = new NuVariable(instant, NuEnumType.MODULE_INSTANT);
/*  774 */       addVariable(instantiation, this.mainModule);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addDefinesMain() {
/*  781 */     for (NuConnector connector : this.mapConnectors.values()) {
/*      */ 
/*      */       
/*  784 */       NuVariable alias = new NuVariable();
/*  785 */       alias.setName(connector.getName());
/*      */ 
/*      */       
/*  788 */       NuExpression ports = getPorts(connector, null);
/*      */ 
/*      */       
/*  791 */       NuExpression guard = convertExpression(connector.getGuard(), connector);
/*      */ 
/*      */       
/*  794 */       NuAssignmentAction assign = merge(alias, ports, guard);
/*      */       
/*  796 */       addDefine(assign);
/*      */     } 
/*      */   }
/*      */   
/*      */   private NuExpression getPorts(NuConnector connector, NuExpression ports) {
/*      */     NuBinaryExpression nuBinaryExpression;
/*  802 */     for (NuPort port : connector.getPorts()) {
/*      */       NuNamedElement nuNamedElement1;
/*  804 */       if (ports == null) { nuNamedElement1 = new NuNamedElement(port.toString());
/*      */         continue; }
/*      */       
/*  807 */       NuNamedElement newPort = new NuNamedElement(port.toString());
/*  808 */       nuBinaryExpression = new NuBinaryExpression(AND, (NuExpression)nuNamedElement1, (NuExpression)newPort);
/*      */     } 
/*      */ 
/*      */     
/*  812 */     return (NuExpression)nuBinaryExpression;
/*      */   }
/*      */ 
/*      */   
/*      */   private NuAssignmentAction merge(NuVariable alias, NuExpression ports, NuExpression guard) {
/*  817 */     if (ports != null && guard != null) {
/*      */       
/*  819 */       NuBinaryExpression value = new NuBinaryExpression(AND, ports, guard);
/*  820 */       return new NuAssignmentAction(alias, (NuExpression)value);
/*      */     } 
/*  822 */     if (guard != null) return new NuAssignmentAction(alias, guard); 
/*  823 */     if (ports != null) return new NuAssignmentAction(alias, ports); 
/*  824 */     throw new Error("Unimplemented Alias Assignment Action");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addInvarsMain() {
/*  830 */     for (NuConnector connector : this.mapConnectors.values()) {
/*      */       
/*  832 */       NuNamedElement interaction = new NuNamedElement("NuInteraction");
/*  833 */       NuNamedElement chosen = new NuNamedElement(connector.getInteraction());
/*      */       
/*  835 */       NuBinaryExpression exp = new NuBinaryExpression(EQUALITY, (NuExpression)interaction, (NuExpression)chosen);
/*  836 */       NuNamedElement connectorName = new NuNamedElement(connector.getName());
/*  837 */       NuBinaryExpression invariation = new NuBinaryExpression(IMPLIES, (NuExpression)exp, (NuExpression)connectorName);
/*      */       
/*  839 */       addInvarExpression((NuExpression)invariation, this.mainModule);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addTransMain() {
/*  846 */     for (Connector cnct : this.mapConnectors.keySet()) {
/*      */       
/*  848 */       NuConnector connector = this.mapConnectors.get(cnct);
/*      */       
/*  850 */       NuNamedElement interaction = new NuNamedElement(connector.getInteraction());
/*  851 */       NuNamedElement chosenInteraction = new NuNamedElement("NuInteraction");
/*      */       
/*  853 */       NuBinaryExpression selectInteraction = new NuBinaryExpression(EQUALITY, (NuExpression)chosenInteraction, (NuExpression)interaction);
/*      */       
/*  855 */       if (!cnct.getType().getInteractionSpecification().isEmpty()) {
/*      */         
/*  857 */         Action down = ((InteractionSpecification)cnct.getType().getInteractionSpecification().get(0)).getDownAction();
/*  858 */         NuBinaryExpression updateVars = getVarUpdate(down, connector, null);
/*      */         
/*  860 */         if (updateVars != null) {
/*      */           
/*  862 */           NuBinaryExpression transition = new NuBinaryExpression(IMPLIES, (NuExpression)selectInteraction, (NuExpression)updateVars);
/*  863 */           addTransExpression((NuExpression)transition, true);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private NuBinaryExpression getVarUpdate(Action down, NuConnector connector, NuBinaryExpression updateVars) {
/*  871 */     if (down instanceof CompositeAction) {
/*      */       
/*  873 */       CompositeAction downComp = (CompositeAction)down;
/*  874 */       for (Action act : downComp.getContent())
/*      */       {
/*  876 */         NuNamedElement targetVar = getTargetVar(act, connector);
/*  877 */         NuExpression assignedExpression = getAssignedExpression(act, connector);
/*      */         
/*  879 */         NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, (NuExpression)targetVar, assignedExpression);
/*      */         
/*  881 */         if (updateVars == null) { updateVars = updateVar; continue; }
/*  882 */          updateVars = new NuBinaryExpression(AND, (NuExpression)updateVars, (NuExpression)updateVar);
/*      */       }
/*      */     
/*  885 */     } else if (down instanceof AssignmentAction) {
/*      */       
/*  887 */       NuNamedElement targetVar = getTargetVar(down, connector);
/*  888 */       NuExpression assignedExpression = getAssignedExpression(down, connector);
/*      */       
/*  890 */       updateVars = new NuBinaryExpression(EQUALITY, (NuExpression)targetVar, assignedExpression);
/*      */     } else {
/*  892 */       throw new Error("Unimplemented Action");
/*      */     } 
/*  894 */     return updateVars;
/*      */   }
/*      */ 
/*      */   
/*      */   private NuNamedElement getTargetVar(Action act, NuConnector connector) {
/*  899 */     AssignmentAction assignment = (AssignmentAction)act;
/*  900 */     RequiredDataParameterReference target = (RequiredDataParameterReference)assignment.getAssignedTarget();
/*  901 */     String portName = target.getPortReference().getTarget().getName();
/*  902 */     String varName = target.getTargetParameter().getName();
/*      */     
/*  904 */     NuPort port = (NuPort)connector.getMapPorts().get(portName);
/*  905 */     varName = String.valueOf(port.getComponent().toLowerCase()) + "." + "NuV" + (String)port.getMapVars().get(varName);
/*  906 */     return new NuNamedElement(next(varName));
/*      */   }
/*      */ 
/*      */   
/*      */   private NuExpression getAssignedExpression(Action act, NuConnector connector) {
/*  911 */     AssignmentAction assignment = (AssignmentAction)act;
/*  912 */     Expression assignedVal = assignment.getAssignedValue();
/*  913 */     NuExpression assignedExpression = convertExpression(assignedVal, connector);
/*      */     
/*  915 */     return assignedExpression;
/*      */   }
/*      */ 
/*      */   
/*      */   private String next(String s) {
/*  920 */     return "next(" + s + ")";
/*      */   }
/*      */   
/*      */   private String unsignedWordPrefix(int size) {
/*  924 */     return "0ud" + Integer.toString(size) + "_";
/*      */   }
/*      */ 
/*      */   
/*      */   private int getWordSize(String var, AtomType at, int prevSize) {
/*  929 */     if (at != null) {
/*      */       
/*  931 */       String component = at.getName();
/*  932 */       return getWordSize(var, component, prevSize);
/*      */     } 
/*      */     
/*  935 */     return prevSize;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getWordSize(String var, String component, int prevSize) {
/*  940 */     if (this.guide.containsKey(component) && ((Map)this.guide.get(component)).containsKey(var)) {
/*  941 */       return ((Integer)((Map)this.guide.get(component)).get(var)).intValue();
/*      */     }
/*  943 */     return prevSize;
/*      */   }
/*      */ 
/*      */   
/*      */   private NuConnector findConnector(String interaction) {
/*  948 */     for (NuConnector connector : this.mapConnectors.values()) {
/*  949 */       if (connector.getInteraction().equals(interaction))
/*  950 */         return connector; 
/*      */     } 
/*  952 */     return null;
/*      */   }
/*      */   
/*      */   private NuExpression convertExpression(Expression exp, NuConnector connector) {
/*  956 */     return convertExpression(exp, connector, false);
/*      */   }
/*      */   
/*      */   private NuExpression convertExpression(Expression exp, NuConnector connector, int size) {
/*  960 */     return convertExpression(exp, connector, false, size);
/*      */   }
/*      */   
/*      */   private NuExpression convertExpression(Expression exp, NuConnector connector, boolean next) {
/*  964 */     return convertExpression(exp, connector, false, this.defaultWordSize.intValue());
/*      */   }
/*      */ 
/*      */   
/*      */   private NuExpression convertExpression(Expression exp, NuConnector connector, boolean next, int size) {
/*  969 */     List<String> modified = getModified(connector, next);
/*      */     
/*  971 */     if (exp == null) return null; 
/*  972 */     if (exp instanceof RequiredDataParameterReference) {
/*      */       
/*  974 */       RequiredDataParameterReference rdpr = (RequiredDataParameterReference)exp;
/*  975 */       String portName = rdpr.getPortReference().getTarget().getName();
/*  976 */       String varName = rdpr.getTargetParameter().getName();
/*      */       
/*  978 */       NuPort port = (NuPort)connector.getMapPorts().get(portName);
/*  979 */       varName = String.valueOf(port.getComponent().toLowerCase()) + "." + "NuV" + (String)port.getMapVars().get(varName);
/*  980 */       return (NuExpression)new NuNamedElement(varName);
/*      */     } 
/*  982 */     if (exp instanceof BinaryExpression) {
/*      */       
/*  984 */       BinaryExpression be = (BinaryExpression)exp;
/*  985 */       BinaryOperator operator = be.getOperator();
/*  986 */       NuBinaryOperator operatorNu = NuExpressionHelper.convertBinaryOperator(operator);
/*  987 */       Expression leftExp = be.getLeftOperand();
/*  988 */       Expression rightExp = be.getRightOperand();
/*  989 */       size = updateSize(leftExp, size);
/*  990 */       NuExpression leftOperand = convertExpression(leftExp, connector, next, size);
/*  991 */       NuExpression rightOperand = convertExpression(rightExp, connector, next, size);
/*  992 */       NuBinaryExpression binaryExp = new NuBinaryExpression(operatorNu, leftOperand, rightOperand);
/*  993 */       return (NuExpression)binaryExp;
/*      */     } 
/*  995 */     if (exp instanceof UnaryExpression) {
/*      */       
/*  997 */       UnaryExpression ue = (UnaryExpression)exp;
/*  998 */       UnaryOperator operator = ue.getOperator();
/*  999 */       NuUnaryOperator operatorNu = NuExpressionHelper.convertUnaryOperator(operator);
/* 1000 */       Expression operand = ue.getOperand();
/* 1001 */       NuExpression nuOperand = convertExpression(operand, connector, next, size);
/* 1002 */       NuUnaryExpression unaryExp = new NuUnaryExpression(operatorNu, nuOperand);
/* 1003 */       return (NuExpression)unaryExp;
/*      */     } 
/* 1005 */     if (exp instanceof IntegerLiteral) {
/*      */       
/* 1007 */       String prefix = unsignedWordPrefix(size);
/* 1008 */       IntegerLiteral il = (IntegerLiteral)exp;
/* 1009 */       int value = il.getIValue();
/* 1010 */       String val = String.valueOf(prefix) + Integer.toString(value);
/* 1011 */       if (value < 0) val = "-" + val; 
/* 1012 */       NuConstant constant = new NuConstant(NuEnumType.UNSIGNED_WORD, val);
/* 1013 */       return (NuExpression)constant;
/*      */     } 
/* 1015 */     if (exp instanceof BooleanLiteral) {
/*      */       
/* 1017 */       BooleanLiteral bl = (BooleanLiteral)exp;
/* 1018 */       NuConstant constant = new NuConstant(NuEnumType.BOOLEAN, "FALSE");
/* 1019 */       if (bl.isBValue()) constant.setValue("TRUE"); 
/* 1020 */       return (NuExpression)constant;
/*      */     } 
/* 1022 */     if (exp instanceof VariableReference) {
/*      */       
/* 1024 */       VariableReference vr = (VariableReference)exp;
/* 1025 */       Variable var = vr.getTargetVariable();
/* 1026 */       String varName = "NuV" + var.getName();
/* 1027 */       if (next && modified.contains(varName)) varName = next(varName); 
/* 1028 */       NuNamedElement variable = new NuNamedElement(varName);
/* 1029 */       return (NuExpression)variable;
/*      */     } 
/* 1031 */     if (exp instanceof DataParameterReference) {
/*      */       
/* 1033 */       DataParameterReference dpr = (DataParameterReference)exp;
/* 1034 */       DataParameter dp = dpr.getTargetParameter();
/* 1035 */       String dpName = "NuDP" + dp.getName();
/* 1036 */       NuNamedElement variable = new NuNamedElement(dpName);
/* 1037 */       return (NuExpression)variable;
/*      */     } 
/* 1039 */     throw new Error("Unimplemented Data Transfer Expression");
/*      */   }
/*      */ 
/*      */   
/*      */   private List<String> getModified(NuConnector connector, boolean next) {
/* 1044 */     List<String> modified = new LinkedList<String>();
/*      */     
/* 1046 */     if (next)
/*      */     {
/* 1048 */       for (NuPair pair : connector.getModifiedVars()) {
/*      */         
/* 1050 */         if (pair.getPort().getComponent().equalsIgnoreCase(this.tempModule.getName())) {
/*      */           
/* 1052 */           if (!pair.getOld()) { modified.add(pair.getVariable());
/*      */             continue; }
/*      */           
/* 1055 */           String varName = "NuV";
/* 1056 */           for (NuPort port : connector.getPorts()) {
/*      */             
/* 1058 */             if (port.toString().equals(pair.getPort().toString())) {
/* 1059 */               varName = String.valueOf(varName) + (String)port.getMapVars().get(pair.getVariable());
/*      */             }
/*      */           } 
/* 1062 */           modified.add(varName);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1068 */     return modified;
/*      */   }
/*      */ 
/*      */   
/*      */   private int updateSize(Expression exp, int prevSize) {
/* 1073 */     if (exp instanceof VariableReference) {
/*      */       
/* 1075 */       VariableReference vr = (VariableReference)exp;
/* 1076 */       Variable var = vr.getTargetVariable();
/* 1077 */       String varName = "NuV" + var.getName();
/* 1078 */       if (this.tempGuide.containsKey(varName)) return ((Integer)this.tempGuide.get(varName)).intValue();
/*      */     
/* 1080 */     } else if (exp instanceof DataParameterReference) {
/*      */       
/* 1082 */       DataParameterReference dpr = (DataParameterReference)exp;
/* 1083 */       DataParameter dp = dpr.getTargetParameter();
/* 1084 */       String dpName = "NuDP" + dp.getName();
/* 1085 */       if (this.tempGuide.containsKey(dpName)) return ((Integer)this.tempGuide.get(dpName)).intValue();
/*      */     
/*      */     } 
/* 1088 */     return prevSize;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\module\NuVisitorImplementation1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */