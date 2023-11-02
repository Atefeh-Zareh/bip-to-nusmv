package epfl.risd.bip.nusmv.module;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import epfl.risd.bip.nusmv.api.NuBinaryOperator;
import epfl.risd.bip.nusmv.api.NuEnumType;
import epfl.risd.bip.nusmv.api.NuPair;
import epfl.risd.bip.nusmv.api.NuSyntax;
import epfl.risd.bip.nusmv.api.NuUnaryOperator;
import epfl.risd.bip.nusmv.expression.NuAssignmentAction;
import epfl.risd.bip.nusmv.expression.NuBinaryExpression;
import epfl.risd.bip.nusmv.expression.NuConnector;
import epfl.risd.bip.nusmv.expression.NuConstant;
import epfl.risd.bip.nusmv.expression.NuDefineAction;
import epfl.risd.bip.nusmv.expression.NuExpression;
import epfl.risd.bip.nusmv.expression.NuExpressionHelper;
import epfl.risd.bip.nusmv.expression.NuInitExpression;
import epfl.risd.bip.nusmv.expression.NuInvarExpression;
import epfl.risd.bip.nusmv.expression.NuNamedElement;
import epfl.risd.bip.nusmv.expression.NuPort;
import epfl.risd.bip.nusmv.expression.NuSymbolicEnumVariable;
import epfl.risd.bip.nusmv.expression.NuTransExpression;
import epfl.risd.bip.nusmv.expression.NuUnaryExpression;
import epfl.risd.bip.nusmv.expression.NuUnsignedWordVariable;
import epfl.risd.bip.nusmv.expression.NuVarAction;
import epfl.risd.bip.nusmv.expression.NuVariable;

import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.AtomType;
import ujf.verimag.bip.Core.Behaviors.DataParameter;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.PetriNet;
import ujf.verimag.bip.Core.Behaviors.Port;
import ujf.verimag.bip.Core.Behaviors.PortDefinition;
import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
import ujf.verimag.bip.Core.Behaviors.PortType;
import ujf.verimag.bip.Core.Behaviors.State;
import ujf.verimag.bip.Core.Behaviors.Transition;
import ujf.verimag.bip.Core.Behaviors.Variable;
import ujf.verimag.bip.Core.Interactions.Component;
import ujf.verimag.bip.Core.Interactions.CompoundType;
import ujf.verimag.bip.Core.Interactions.Connector;
import ujf.verimag.bip.Core.Interactions.InnerPortReference;
import ujf.verimag.bip.Core.Modules.OpaqueElement;

public class NuVisitorImplementation1 implements NuVisitor {

	private Map<Component, NuModule> mapCompModule;
	private Map<Connector, NuConnector> mapConnectors;
	private Map<String, Map<String, Integer>> guide;
	private Map<String, Integer> tempGuide;
	private Map<String, NuPort> mapPort;

	private List<String> modifiedVariables;
	private List<String> tempInteractions;
	private List<String> tempAllVariables;
	private List<String> tempModified;

	private NuExpression tempExpression;
	private NuModule mainModule;
	private NuModule tempModule;

	private Integer defaultWordSize = 13;

	private static final String MAIN = "main";
	private static final String ACTIVEINTERACTION = "active_interaction";
	private static final String INTERACTIONPREFIX = "NuI";
	private static final String VARIABLEPREFIX = "NuV";
	private static final String DATAPARAMPREFIX = "NuDP";
	private static final String STATEPREFIX = "NuS";
	private static final String PORTPREFIX = "NuP";
	private static final String INTERACTIONVARIABLE = "NuInteraction";
	private static final String PLACEVARIABLE = "Nuplace";

	private static final NuBinaryOperator AND = NuBinaryOperator.LOGICAL_AND;
	private static final NuBinaryOperator EQUALITY = NuBinaryOperator.EQUALITY;
	private static final NuBinaryOperator INEQUALITY = NuBinaryOperator.INEQUALITY;
	private static final NuBinaryOperator IMPLIES = NuBinaryOperator.IMPLIES;
	private static final NuBinaryOperator EQUIVELANT = NuBinaryOperator.EQUIVELANT;

	// constructor
	public NuVisitorImplementation1() {
		mapCompModule = new HashMap<Component, NuModule>();
		mapConnectors = new HashMap<Connector, NuConnector>();
		guide = new HashMap<String, Map<String, Integer>>();
		tempGuide = new HashMap<String, Integer>();
		mapPort = new HashMap<String, NuPort>();

		modifiedVariables = new LinkedList<String>();
		tempInteractions = new LinkedList<String>();
		tempAllVariables = new LinkedList<String>();
		tempModified = new LinkedList<String>();

		mainModule = new NuModule();
		mainModule.setName(MAIN);
	}

	@Override
	public void execute(CompoundType ct, String guideFile) {
		if (guideFile != null)
			parseGuideFile(guideFile);
		visitCompound(ct);
	}

	@Override
	public Collection<NuModule> getModules() {
		return mapCompModule.values();
	}

	@Override
	public NuModule getMain() {
		return mainModule;
	}

	private void parseGuideFile(String guideFile) {

		try {
			Scanner in = new Scanner(new FileReader(guideFile));

			int numOfConnectors = in.nextInt();
			for (int i = 0; i < numOfConnectors; i++) {
				String connectorName = in.next();
				Map<String, Integer> mapVarSize = new HashMap<String, Integer>();
				int numOfVars = in.nextInt();
				for (int j = 0; j < numOfVars; j++) {
					String varName = in.next();
					Integer varSize = in.nextInt();
					mapVarSize.put(varName, varSize);
				}
				guide.put(connectorName, mapVarSize);
			}

			String def = in.next();
			if (def.equals("default"))
				defaultWordSize = in.nextInt();
			else {
				in.close();				
				throw new Error("default not specified in guide");
			}

			in.close();				
		} catch (FileNotFoundException e) {
			throw new Error("Guide File doesn't exist!");
		}
	}

	private void visitCompound(CompoundType ct) {

		// Connectors
		for (Connector cnct : ct.getConnector())
			visitConnector(cnct);

		// Subcomponents: Atomic Types
		for (Component comp : ct.getSubcomponent()) {
			if (comp.getType() instanceof AtomType) {
				AtomType at = (AtomType) comp.getType();
				initializeModule(comp);
				visitAtomic(at);
				addDefaultTransition();
				mapCompModule.put(comp, tempModule);
			} else
				throw new Error("Unimplemented Subcomponent Type");
		}

		clearTemps();
		constructMain();
	}

	private void visitConnector(Connector cnct) {

		NuConnector connector = new NuConnector();
		connector.setName(cnct.getName());

		int interactionNumber = mapConnectors.size() + 1;
		String interaction = INTERACTIONPREFIX + Integer.toString(interactionNumber);
		connector.setInteraction(interaction);

		assignConnectorPortInteractions(cnct, connector, interaction);

		if (!cnct.getType().getInteractionSpecification().isEmpty()) {
			Expression guard = cnct.getType().getInteractionSpecification().get(0).getGuard();
			connector.setGuard(guard);
			getModifiedVars(cnct, connector);
		}

		mapConnectors.put(cnct, connector);
	}

	private void assignConnectorPortInteractions(Connector cnct, NuConnector connector, String interaction) {

		List<NuPort> ports = new LinkedList<NuPort>();

		int numOfPorts = cnct.getActualPort().size();
		for (int i = 0; i < numOfPorts; i++) {
			InnerPortReference ipr = (InnerPortReference) cnct.getActualPort().get(i);
			Port p = ipr.getTargetPort();

			String componentName = ipr.getTargetInstance().getTargetPart().getName().toLowerCase();
			String portName = PORTPREFIX + p.getName();

			NuPort port = new NuPort(componentName, portName, null, null, null);
			if (mapPort.containsKey(port.toString()))
				port = mapPort.get(port.toString());
			port.addInteraction(interaction);
			ports.add(port);

			if (!mapPort.containsKey(port.toString()))
				mapPort.put(port.toString(), port);

			String formalPortName = cnct.getType().getPortParameter().get(i).getName();
			connector.addMapping(formalPortName, port);
		}

		connector.setPorts(ports);
	}

	private void getModifiedVars(Connector cnct, NuConnector connector) {

		Action down = cnct.getType().getInteractionSpecification().get(0).getDownAction();
		if (down instanceof CompositeAction) {
			CompositeAction downComp = (CompositeAction) down;
			for (Action act : downComp.getContent()) {
				getModifiedVar(act, connector);
			}
		} else if (down instanceof AssignmentAction) {
			getModifiedVar(down, connector);
		} else
			throw new Error("Unimplemented Action");
	}

	private void getModifiedVar(Action act, NuConnector connector) {

		AssignmentAction assignment = (AssignmentAction) act;
		RequiredDataParameterReference target = (RequiredDataParameterReference) assignment.getAssignedTarget();
		String targetPortName = target.getPortReference().getTarget().getName();
		String targetVarName = target.getTargetParameter().getName();

		NuPort port = connector.getMapPorts().get(targetPortName);
		NuPair modifiedVar = new NuPair(port, targetVarName);
		connector.addModifiedVar(modifiedVar);
	}

	private void initializeModule(Component comp) {
		tempModule = new NuModule();
		tempModule.setName(comp.getName().toUpperCase());
		tempModule.addParameter(ACTIVEINTERACTION);
		clearTemps();
	}

	private void clearTemps() {
		tempAllVariables.clear();
		tempInteractions.clear();
		tempGuide.clear();
	}

	private void visitAtomic(AtomType at) {

		for (PortDefinition portDef : at.getPortDefinition())
			visitPort(portDef);

		for (DataParameter dp : at.getDataParameter())
			visitAtomicDataParam(dp, at);

		for (Variable var : at.getVariable())
			visitAtomicVariable(var, at);

		PetriNet pn = (PetriNet) at.getBehavior();
		visitBehavior(pn);
	}

	private void visitPort(PortDefinition portDef) {

		String portName = PORTPREFIX + portDef.getName();
		String componentName = tempModule.getName().toLowerCase();
		String portNotation = componentName + "." + portName;
		NuPort port = mapPort.get(portNotation);

		// adding port booleans to VAR
		NuVariable variable = new NuVariable(portName, NuEnumType.BOOLEAN);
		addVariable(variable, tempModule);

		// mapping variables of each port
		PortType pt = portDef.getType();
		int numOfVars = pt.getDataParameter().size();

		for (int i = 0; i < numOfVars; i++) {
			String dataParam = pt.getDataParameter().get(i).getName();
			String varName = portDef.getExposedVariable().get(i).getName();
			port.addMapping(dataParam, varName);
		}
	}

	private void visitAtomicDataParam(DataParameter dp, AtomType at) {

		int size = getWordSize(dp.getName(), at, defaultWordSize);

		String parameterName = DATAPARAMPREFIX + dp.getName();
		tempModule.addParameter(parameterName);

		tempGuide.put(parameterName, size);
	}

	private void visitAtomicVariable(Variable var, AtomType at) {

		int size = getWordSize(var.getName(), at, defaultWordSize);

		OpaqueElement oe = (OpaqueElement) var.getType();
		NuVariable variable = getVarType(var, oe, at, size);

		tempGuide.put(variable.getName(), size);

		// adding variable to VAR
		addVariable(variable, tempModule);

		// adding initial value to INIT
		addInitialValue(var, variable, size);
	}

	private NuVariable getVarType(Variable var, OpaqueElement oe, AtomType at, int size) {

		String varName = VARIABLEPREFIX + var.getName();
		tempAllVariables.add(varName);

		if (oe.getBody().equals("int")) {
			return new NuUnsignedWordVariable(varName, size, 'd');
		} else if (oe.getBody().equals("bool")) {
			NuEnumType varType = NuEnumType.BOOLEAN;
			return new NuVariable(varName, varType);
		} else
			throw new Error("Unimplemented variable type");
	}

	private void addInitialValue(Variable var, NuVariable variable, int size) {

		Expression initialVal = var.getInitialValue();
		NuExpression initialNuVal = convertExpression(initialVal, null, size);

		if (initialNuVal != null) {
			NuBinaryExpression initial = new NuBinaryExpression(EQUALITY, variable, initialNuVal);
			addInitExpression(initial);
		}
	}

	private void visitBehavior(PetriNet pn) {

		NuSymbolicEnumVariable places = new NuSymbolicEnumVariable(PLACEVARIABLE, null);

		// getting all places
		for (State s : pn.getState())
			places.addEnumeration(STATEPREFIX + s.getName());

		// adding place variable to VAR
		addVariable(places, tempModule);

		// adding initial state to INIT
		addInitialState(pn, places);

		// adding initialization to INIT
		addInitialization(pn);

		for (Transition t : pn.getTransition())
			visitTransition(t, pn.getAtomType());

		// adding the INVAR expression
		addInvariation();
	}

	private void addInitialState(PetriNet pn, NuSymbolicEnumVariable places) {

		for (State s : pn.getInitialState()) {
			String stateName = STATEPREFIX + s.getName();
			NuNamedElement initialState = new NuNamedElement(stateName);
			NuBinaryExpression initial = new NuBinaryExpression(EQUALITY, places, initialState);
			addInitExpression(initial);
		}
	}

	private void addInitialization(PetriNet pn) {

		Action action = pn.getInitialization();
		if (action instanceof CompositeAction) {
			CompositeAction ca = (CompositeAction) action;

			for (Action act : ca.getContent()) {
				visitAction(act, null, true, pn.getAtomType());
				addInitExpression(tempExpression);
			}
		}
	}

	private void addInvariation() {

		for (NuPort port : mapPort.values()) {
			if (tempModule.getName().toLowerCase().equals(port.getComponent())) {
				NuNamedElement portName = new NuNamedElement(port.getPort());
				NuExpression enable = port.getEnablement();
				NuBinaryExpression invariation = new NuBinaryExpression(EQUIVELANT, portName, enable);
				addInvarExpression(invariation, tempModule);
			}
		}
	}

	private void visitAction(Action act, String interaction, boolean init, AtomType at) {

		if (act instanceof AssignmentAction) {
			int size = defaultWordSize;

			NuNamedElement target;
			AssignmentAction assignAct = (AssignmentAction) act;
			DataReference dataRef = assignAct.getAssignedTarget();
			if (dataRef instanceof VariableReference) {
				VariableReference varRef = (VariableReference) dataRef;
				Variable var = varRef.getTargetVariable();

				String varName = VARIABLEPREFIX + var.getName();
				if (init)
					target = new NuNamedElement(varName);
				else
					target = new NuNamedElement(next(varName));
				modifiedVariables.add(varName);

				size = getWordSize(var.getName(), at, size);
			} else
				throw new Error("Unimplemented Assignment Action");

			NuConnector connector = findConnector(interaction);

			Expression exp = assignAct.getAssignedValue();
			NuExpression assignedVal = null;
			if (interaction == null)
				assignedVal = convertExpression(exp, null, size);
			else
				assignedVal = convertExpression(exp, connector, true, size);
			tempExpression = new NuBinaryExpression(EQUALITY, target, assignedVal);
		} else
			throw new Error("Unimplemented Action");
	}

	private void visitTransition(Transition t, AtomType at) {

		// find the origin and destination places
		NuBinaryExpression origin = getOrigin(t);
		NuBinaryExpression destination = getDestination(t);
		NuBinaryExpression updatePlace = new NuBinaryExpression(AND, origin, destination);

		// find the corresponding interaction(s)
		NuPort port = getTransitionPort(t);
		List<String> interactionNames = new LinkedList<String>();
		interactionNames.addAll(port.getInteractions());

		NuNamedElement activeInteraction = new NuNamedElement(ACTIVEINTERACTION);

		for (String interactionName : interactionNames) {
			if (!tempInteractions.contains(interactionName))
				tempInteractions.add(interactionName);

			NuBinaryExpression transition = addInteractionName(activeInteraction, interactionName, updatePlace);
			transition = addTransitionAction(transition, interactionName, t, at);
			transition = addReduntantVars(transition, interactionName);

			addTransExpression(transition, false);
		}

		NuBinaryExpression portEnable = addGuard(origin, t);
		port.addEnablement(portEnable);
	}

	private NuBinaryExpression getOrigin(Transition t) {

		String originStateName = STATEPREFIX + t.getOrigin().get(0).getName();
		NuNamedElement originState = new NuNamedElement(originStateName);
		NuNamedElement place = new NuNamedElement(PLACEVARIABLE);

		return new NuBinaryExpression(EQUALITY, place, originState);
	}

	private NuBinaryExpression getDestination(Transition t) {

		String destStateName = STATEPREFIX + t.getDestination().get(0).getName();
		NuNamedElement destState = new NuNamedElement(destStateName);
		NuNamedElement nextPlace = new NuNamedElement(next(PLACEVARIABLE));

		return new NuBinaryExpression(EQUALITY, nextPlace, destState);
	}

	private NuPort getTransitionPort(Transition t) {

		PortDefinitionReference portDefRef = (PortDefinitionReference) t.getTrigger();
		String portName = PORTPREFIX + portDefRef.getTarget().getName();
		String portNotation = tempModule.getName().toLowerCase() + "." + portName;
		return mapPort.get(portNotation);
	}

	private NuBinaryExpression addInteractionName(NuExpression active, String interactionName, NuExpression place) {

		NuNamedElement chosenInteraction = new NuNamedElement(interactionName);
		NuBinaryExpression interaction = new NuBinaryExpression(EQUALITY, active, chosenInteraction);
		return new NuBinaryExpression(AND, place, interaction);
	}

	private NuBinaryExpression addTransitionAction(NuExpression transition, String interaction, Transition t,
			AtomType at) {

		modifiedVariables.clear();
		if (t.getAction() instanceof CompositeAction) {
			CompositeAction ca = (CompositeAction) t.getAction();
			for (Action act : ca.getContent()) {
				visitAction(act, interaction, false, at);
				transition = new NuBinaryExpression(AND, transition, tempExpression);
			}
		} else if (t.getAction() instanceof AssignmentAction) {
			visitAction(t.getAction(), interaction, false, at);
			transition = new NuBinaryExpression(AND, transition, tempExpression);
		} else if (t.getAction() != null)
			throw new Error("Unimplemented Action");

		return (NuBinaryExpression) transition;
	}

	private NuBinaryExpression addReduntantVars(NuBinaryExpression transition, String interaction) {

		NuConnector connector = findConnector(interaction);
		List<NuPair> interactionVars = connector.getModifiedVars();

		updateInteractionVars(interactionVars, connector);

		for (String variable : tempAllVariables) {
			if (!modifiedVariables.contains(variable) && !found(variable, interactionVars)) {
				NuNamedElement var = new NuNamedElement(variable);
				NuNamedElement nextVar = new NuNamedElement(next(variable));
				NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, nextVar, var);
				transition = new NuBinaryExpression(AND, transition, updateVar);
			}
		}

		return transition;
	}

	private void updateInteractionVars(List<NuPair> interactionVars, NuConnector connector) {

		for (NuPair pair : interactionVars) {
			if (pair.getOld() && pair.getPort().getComponent().equalsIgnoreCase(tempModule.getName())) {
				String varName = VARIABLEPREFIX;
				for (NuPort port : connector.getPorts()) {
					if (port.toString().equals(pair.getPort().toString()))
						varName += port.getMapVars().get(pair.getVariable());
				}
				pair.setVariable(varName);
				pair.setOld(false);
			}
		}
	}

	private boolean found(String variable, List<NuPair> interactionVars) {

		for (NuPair pair : interactionVars)
			if (pair.getPort().getComponent().equalsIgnoreCase(tempModule.getName()))
				if (pair.getVariable().equals(variable))
					return true;

		return false;
	}

	private NuBinaryExpression addGuard(NuBinaryExpression origin, Transition t) {

		Expression guard = t.getGuard();
		if (guard == null)
			return origin;

		NuExpression guardNu = convertExpression(guard, null);
		return new NuBinaryExpression(AND, origin, guardNu);
	}

	// updating VAR
	private void addVariable(NuVariable variable, NuModule module) {

		List<NuVarAction> vars = module.getVars();

		if (vars.isEmpty()) {
			NuVarAction varAct = new NuVarAction();
			varAct.addVariable(variable);
			module.addVar(varAct);
		} else {
			vars.get(0).addVariable(variable);
		}
	}

	// updating DEFINE
	private void addDefine(NuAssignmentAction assign) {

		List<NuDefineAction> defines = mainModule.getDefines();
		if (defines.isEmpty()) {
			NuDefineAction def = new NuDefineAction();
			def.addAction(assign);
			mainModule.addDefine(def);
		} else {
			defines.get(0).addAction(assign);
		}
	}

	// updating INIT
	private void addInitExpression(NuExpression initial) {

		List<NuInitExpression> inits = tempModule.getInits();

		if (inits.isEmpty()) {
			NuInitExpression init = new NuInitExpression();
			init.addExpression(initial);
			tempModule.addInit(init);
		} else {
			inits.get(0).addExpression(initial);
		}
	}

	// updating INVAR
	private void addInvarExpression(NuExpression invariation, NuModule module) {

		List<NuInvarExpression> invars = module.getInvars();

		if (invars.isEmpty()) {
			NuInvarExpression invar = new NuInvarExpression();
			invar.addExpression(invariation);
			module.addInvar(invar);
		} else {
			invars.get(0).addExpression(invariation);
		}
	}

	// updating TRANS
	private void addTransExpression(NuExpression transition, boolean main) {

		List<NuTransExpression> trans = null;
		if (main)
			trans = mainModule.getTrans();
		else
			trans = tempModule.getTrans();

		if (trans.isEmpty()) {
			NuTransExpression tran = new NuTransExpression();
			tran.addExpression(transition);
			if (main)
				mainModule.addTran(tran);
			else
				tempModule.addTran(tran);
		} else {
			trans.get(0).addExpression(transition);
		}
	}

	// adding default TRANS
	private void addDefaultTransition() {

		NuNamedElement place = new NuNamedElement(PLACEVARIABLE);
		NuNamedElement nextPlace = new NuNamedElement(next(PLACEVARIABLE));
		NuBinaryExpression transition = new NuBinaryExpression(EQUALITY, nextPlace, place);

		for (String variable : tempAllVariables) {
			NuNamedElement var = new NuNamedElement(variable);
			NuNamedElement nextVar = new NuNamedElement(next(variable));
			NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, nextVar, var);

			transition = new NuBinaryExpression(AND, transition, updateVar);
		}

		NuNamedElement activeInt = new NuNamedElement(ACTIVEINTERACTION);

		for (String interaction : tempInteractions) {
			NuNamedElement chosenInt = new NuNamedElement(interaction);
			NuBinaryExpression notInt = new NuBinaryExpression(INEQUALITY, activeInt, chosenInt);

			transition = new NuBinaryExpression(AND, notInt, transition);
		}

		addTransExpression(transition, false);
	}

	private void constructMain() {
		addInteractionsEnumerationMain();
		addModulesMain();
		addDefinesMain();
		addInvarsMain();
		addTransMain();
	}

	// adding interaction enumeration VAR in main
	private void addInteractionsEnumerationMain() {

		NuSymbolicEnumVariable variable = new NuSymbolicEnumVariable(INTERACTIONVARIABLE, null);

		int interactions = mapConnectors.size();
		for (int i = 1; i <= interactions; i++) {
			String interaction = INTERACTIONPREFIX + Integer.toString(i);
			variable.addEnumeration(interaction);
		}

		addVariable(variable, mainModule);
	}

	// adding module instants to VAR in main
	private void addModulesMain() {

		for (Component comp : mapCompModule.keySet()) {
			NuModule module = mapCompModule.get(comp);

			String instant = module.getName().toLowerCase() + NuSyntax.TABSPACE + NuSyntax.COLON;
			instant += NuSyntax.TABSPACE + module.getName().toUpperCase() + "(" + INTERACTIONVARIABLE;

			int numOfParameters = comp.getActualData().size();
			for (int i = 0; i < numOfParameters; i++) {
				String componentName = comp.getType().getName();
				String parameterName = comp.getType().getDataParameter().get(i).getName();
				int size = getWordSize(parameterName, componentName, defaultWordSize);

				Expression exp = comp.getActualData().get(i);
				NuExpression parameter = convertExpression(exp, null, size);
				instant += ", " + NuExpressionHelper.expressionToString(parameter);
			}

			instant += ")";

			NuVariable instantiation = new NuVariable(instant, NuEnumType.MODULE_INSTANT);
			addVariable(instantiation, mainModule);
		}
	}

	// adding interaction alias DEFINE in main
	private void addDefinesMain() {

		for (NuConnector connector : mapConnectors.values()) {
			// alias variable name
			NuVariable alias = new NuVariable();
			alias.setName(connector.getName());

			// ports expression
			NuExpression ports = getPorts(connector, null);

			// guard expression
			NuExpression guard = convertExpression(connector.getGuard(), connector);

			// merging them together
			NuAssignmentAction assign = merge(alias, ports, guard);

			addDefine(assign);
		}
	}

	private NuExpression getPorts(NuConnector connector, NuExpression ports) {

		for (NuPort port : connector.getPorts()) {
			if (ports == null)
				ports = new NuNamedElement(port.toString());
			else {
				NuNamedElement newPort = new NuNamedElement(port.toString());
				ports = new NuBinaryExpression(AND, ports, newPort);
			}
		}

		return ports;
	}

	private NuAssignmentAction merge(NuVariable alias, NuExpression ports, NuExpression guard) {

		if (ports != null && guard != null) {
			NuBinaryExpression value = new NuBinaryExpression(AND, ports, guard);
			return new NuAssignmentAction(alias, value);
		} else if (guard != null)
			return new NuAssignmentAction(alias, guard);
		else if (ports != null)
			return new NuAssignmentAction(alias, ports);
		else
			throw new Error("Unimplemented Alias Assignment Action");
	}

	// adding INVAR in main to select transitions
	private void addInvarsMain() {

		for (NuConnector connector : mapConnectors.values()) {
			NuNamedElement interaction = new NuNamedElement(INTERACTIONVARIABLE);
			NuNamedElement chosen = new NuNamedElement(connector.getInteraction());

			NuBinaryExpression exp = new NuBinaryExpression(EQUALITY, interaction, chosen);
			NuNamedElement connectorName = new NuNamedElement(connector.getName());
			NuBinaryExpression invariation = new NuBinaryExpression(IMPLIES, exp, connectorName);

			addInvarExpression(invariation, mainModule);
		}
	}

	// adding TRANS in main for data transfer
	private void addTransMain() {

		for (Connector cnct : mapConnectors.keySet()) {
			tempModified.clear();

			NuConnector connector = mapConnectors.get(cnct);

			NuNamedElement interaction = new NuNamedElement(connector.getInteraction());
			NuNamedElement chosenInteraction = new NuNamedElement(INTERACTIONVARIABLE);

			NuBinaryExpression selectInteraction = new NuBinaryExpression(EQUALITY, chosenInteraction, interaction);

			if (!cnct.getType().getInteractionSpecification().isEmpty()) {
				Action down = cnct.getType().getInteractionSpecification().get(0).getDownAction();
				NuBinaryExpression updateVars = getVarUpdate(down, connector, null);

				if (updateVars != null) {
					NuBinaryExpression transition = new NuBinaryExpression(IMPLIES, selectInteraction, updateVars);
					addTransExpression(transition, true);
				}
			}
		}
	}

	private NuBinaryExpression getVarUpdate(Action down, NuConnector connector, NuBinaryExpression updateVars) {

		if (down instanceof CompositeAction) {
			CompositeAction downComp = (CompositeAction) down;
			for (Action act : downComp.getContent()) {
				NuNamedElement targetVar = getTargetVar(act, connector);
				int size = getTargetVarSize(act, connector, defaultWordSize);
				NuExpression assignedExpression = getAssignedExpression(act, connector, size);

				NuBinaryExpression updateVar = new NuBinaryExpression(EQUALITY, targetVar, assignedExpression);

				if (updateVars == null)
					updateVars = updateVar;
				else
					updateVars = new NuBinaryExpression(AND, updateVars, updateVar);
			}
		} else if (down instanceof AssignmentAction) {
			NuNamedElement targetVar = getTargetVar(down, connector);
			int size = getTargetVarSize(down, connector, defaultWordSize);
			NuExpression assignedExpression = getAssignedExpression(down, connector, size);

			updateVars = new NuBinaryExpression(EQUALITY, targetVar, assignedExpression);
		} else
			throw new Error("Unimplemented Action");

		return updateVars;
	}

	private NuNamedElement getTargetVar(Action act, NuConnector connector) {

		AssignmentAction assignment = (AssignmentAction) act;
		RequiredDataParameterReference target = (RequiredDataParameterReference) assignment.getAssignedTarget();
		String portName = target.getPortReference().getTarget().getName();
		String varName = target.getTargetParameter().getName();

		NuPort port = connector.getMapPorts().get(portName);
		varName = port.getComponent().toLowerCase() + "." + VARIABLEPREFIX + port.getMapVars().get(varName);
		tempModified.add(varName);
		return new NuNamedElement(next(varName));
	}

	private int getTargetVarSize(Action act, NuConnector connector, int prevSize) {

		AssignmentAction assignment = (AssignmentAction) act;
		RequiredDataParameterReference target = (RequiredDataParameterReference) assignment.getAssignedTarget();
		String portName = target.getPortReference().getTarget().getName();
		String varName = target.getTargetParameter().getName();

		NuPort port = connector.getMapPorts().get(portName);
		varName = port.getMapVars().get(varName);
		String componentName = port.getComponent().toUpperCase();

		for (Component comp : mapCompModule.keySet()) {
			if (comp.getName().equals(componentName)) {
				String componentType = comp.getType().getName();
				return getWordSize(varName, componentType, prevSize);
			}
		}

		return prevSize;
	}

	private NuExpression getAssignedExpression(Action act, NuConnector connector, int size) {

		AssignmentAction assignment = (AssignmentAction) act;
		Expression assignedVal = assignment.getAssignedValue();
		NuExpression assignedExpression = convertExpression(assignedVal, connector, size);
		return assignedExpression;
	}

	private String next(String s) {
		return NuSyntax.NEXTBEGIN + s + NuSyntax.NEXTEND;
	}

	private String unsignedWordPrefix(int size) {
		return "0ud" + Integer.toString(size) + "_";
	}

	private int getWordSize(String var, AtomType at, int prevSize) {

		if (at != null) {
			String component = at.getName();
			return getWordSize(var, component, prevSize);
		}

		return prevSize;
	}

	private int getWordSize(String var, String component, int prevSize) {

		if (guide.containsKey(component) && guide.get(component).containsKey(var))
			return guide.get(component).get(var);

		return prevSize;
	}

	private NuConnector findConnector(String interaction) {

		for (NuConnector connector : mapConnectors.values())
			if (connector.getInteraction().equals(interaction))
				return connector;

		return null;
	}

	private NuExpression convertExpression(Expression exp, NuConnector connector) {
		return convertExpression(exp, connector, false);
	}

	private NuExpression convertExpression(Expression exp, NuConnector connector, int size) {
		return convertExpression(exp, connector, false, size);
	}

	private NuExpression convertExpression(Expression exp, NuConnector connector, boolean next) {
		return convertExpression(exp, connector, false, defaultWordSize);
	}

	private NuExpression convertExpression(Expression exp, NuConnector connector, boolean next, int size) {

		List<String> modified = getModified(connector, next);

		if (exp == null)
			return null;
		else if (exp instanceof RequiredDataParameterReference) {
			RequiredDataParameterReference rdpr = (RequiredDataParameterReference) exp;
			String portName = rdpr.getPortReference().getTarget().getName();
			String varName = rdpr.getTargetParameter().getName();

			NuPort port = connector.getMapPorts().get(portName);
			varName = port.getComponent().toLowerCase() + "." + VARIABLEPREFIX + port.getMapVars().get(varName);
			if (tempModified.contains(varName))
				varName = next(varName);
			return new NuNamedElement(varName);
		} else if (exp instanceof BinaryExpression) {
			BinaryExpression be = (BinaryExpression) exp;
			BinaryOperator operator = be.getOperator();
			NuBinaryOperator operatorNu = NuExpressionHelper.convertBinaryOperator(operator);
			Expression leftExp = be.getLeftOperand();
			Expression rightExp = be.getRightOperand();
			size = updateSize(leftExp, connector, size);
			NuExpression leftOperand = convertExpression(leftExp, connector, next, size);
			NuExpression rightOperand = convertExpression(rightExp, connector, next, size);
			NuBinaryExpression binaryExp = new NuBinaryExpression(operatorNu, leftOperand, rightOperand);
			return binaryExp;
		} else if (exp instanceof UnaryExpression) {
			UnaryExpression ue = (UnaryExpression) exp;
			UnaryOperator operator = ue.getOperator();
			NuUnaryOperator operatorNu = NuExpressionHelper.convertUnaryOperator(operator);
			Expression operand = ue.getOperand();
			NuExpression nuOperand = convertExpression(operand, connector, next, size);
			NuUnaryExpression unaryExp = new NuUnaryExpression(operatorNu, nuOperand);
			return unaryExp;
		} else if (exp instanceof IntegerLiteral) {
			String prefix = unsignedWordPrefix(size);
			IntegerLiteral il = (IntegerLiteral) exp;
			int value = il.getIValue();
			String val = prefix + Integer.toString(value);
			if (value < 0)
				val = NuSyntax.NEGATIVE + val;
			NuConstant constant = new NuConstant(NuEnumType.UNSIGNED_WORD, val);
			return constant;
		} else if (exp instanceof BooleanLiteral) {
			BooleanLiteral bl = (BooleanLiteral) exp;
			NuConstant constant = new NuConstant(NuEnumType.BOOLEAN, NuSyntax.FALSE);
			if (bl.isBValue())
				constant.setValue(NuSyntax.TRUE);
			return constant;
		} else if (exp instanceof VariableReference) {
			VariableReference vr = (VariableReference) exp;
			Variable var = vr.getTargetVariable();
			String varName = VARIABLEPREFIX + var.getName();
			if (next && modified.contains(varName))
				varName = next(varName);
			NuNamedElement variable = new NuNamedElement(varName);
			return variable;
		} else if (exp instanceof DataParameterReference) {
			DataParameterReference dpr = (DataParameterReference) exp;
			DataParameter dp = dpr.getTargetParameter();
			String dpName = DATAPARAMPREFIX + dp.getName();
			NuNamedElement variable = new NuNamedElement(dpName);
			return variable;
		} else
			throw new Error("Unimplemented Data Transfer Expression");
	}

	private List<String> getModified(NuConnector connector, boolean next) {

		List<String> modified = new LinkedList<String>();

		if (next) {
			for (NuPair pair : connector.getModifiedVars()) {
				if (pair.getPort().getComponent().equalsIgnoreCase(tempModule.getName())) {
					if (!pair.getOld())
						modified.add(pair.getVariable());
					else {
						String varName = VARIABLEPREFIX;
						for (NuPort port : connector.getPorts()) {
							if (port.toString().equals(pair.getPort().toString()))
								varName += port.getMapVars().get(pair.getVariable());
						}

						modified.add(varName);
					}
				}
			}
		}

		return modified;
	}

	private int updateSize(Expression exp, NuConnector connector, int prevSize) {

		if (exp instanceof VariableReference) {
			VariableReference vr = (VariableReference) exp;
			Variable var = vr.getTargetVariable();
			String varName = VARIABLEPREFIX + var.getName();
			if (tempGuide.containsKey(varName))
				return tempGuide.get(varName);
		} else if (exp instanceof DataParameterReference) {
			DataParameterReference dpr = (DataParameterReference) exp;
			DataParameter dp = dpr.getTargetParameter();
			String dpName = DATAPARAMPREFIX + dp.getName();
			if (tempGuide.containsKey(dpName))
				return tempGuide.get(dpName);
		} else if (exp instanceof RequiredDataParameterReference) {
			RequiredDataParameterReference rdpr = (RequiredDataParameterReference) exp;
			String portName = rdpr.getPortReference().getTarget().getName();
			String varName = rdpr.getTargetParameter().getName();

			NuPort port = connector.getMapPorts().get(portName);
			varName = port.getMapVars().get(varName);
			String componentName = port.getComponent().toUpperCase();

			for (Component comp : mapCompModule.keySet()) {
				if (comp.getName().equals(componentName)) {
					String componentType = comp.getType().getName();
					return getWordSize(varName, componentType, prevSize);
				}
			}
		}

		return prevSize;
	}
}