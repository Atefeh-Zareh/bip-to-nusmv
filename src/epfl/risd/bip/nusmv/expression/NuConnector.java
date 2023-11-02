package epfl.risd.bip.nusmv.expression;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ujf.verimag.bip.Core.Behaviors.Expression;

import epfl.risd.bip.nusmv.api.NuPair;

public class NuConnector {

	private String name;
	private String interaction;
	private Expression guard;
	private NuExpression downAction;
	private List<NuPort> ports;
	private List<NuPair> modifiedVars;
	private Map<String, NuPort> mapPorts;

	// default constructor
	public NuConnector() {
		guard = null;
		downAction = null;
		ports = new LinkedList<NuPort>();
		modifiedVars = new LinkedList<NuPair>();
		mapPorts = new HashMap<String, NuPort>();
	}

	// normal constructor
	public NuConnector(String n, String i, List<NuPort> p, Expression g, List<NuPair> m, NuExpression d) {
		name = n;
		interaction = i;
		guard = g;
		downAction = d;
		ports = new LinkedList<NuPort>();
		modifiedVars = new LinkedList<NuPair>();
		if(p != null) ports.addAll(p);
		if(m != null) modifiedVars.addAll(m);
	}

	// copy constructor
	public NuConnector(NuConnector n) {
		name = n.name;
		interaction = n.interaction;
		guard = n.guard;
		downAction = n.downAction;
		ports = new LinkedList<NuPort>();
		modifiedVars = new LinkedList<NuPair>();
		mapPorts = new HashMap<String, NuPort>();
		if(n.ports != null) ports.addAll(n.ports);
		if(n.modifiedVars != null) modifiedVars.addAll(n.modifiedVars);
		if(n.mapPorts != null) mapPorts.putAll(n.mapPorts);
	}

	// setters
	public void setName(String n) {
		name = n;
	}

	public void setInteraction(String i) {
		interaction = i;
	}

	public void setPorts(List<NuPort> p) {
		ports = p;
	}

	public void setGuard(Expression g) {
		guard = g;
	}

	public void setModifiedVars(List<NuPair> m) {
		modifiedVars = m;
	}

	public void setDownAction(NuExpression d) {
		downAction = d;
	}

	public void setMapPorts(Map<String, NuPort> m) {
		mapPorts = m;
	}

	// getters
	public String getName() {
		return name;
	}

	public String getInteraction() {
		return interaction;
	}

	public List<NuPort> getPorts() {
		return ports;
	}

	public Expression getGuard() {
		return guard;
	}

	public List<NuPair> getModifiedVars() {
		return modifiedVars;
	}

	public NuExpression getDownAction() {
		return downAction;
	}

	public Map<String, NuPort> getMapPorts() {
		return mapPorts;
	}

	// add port
	public void addPort(NuPort p) {
		if(p != null) ports.add(p);
	}

	// add ports
	public void addPorts(List<NuPort> p) {
		if(p != null) ports.addAll(p);
	}

	// add modified variable
	public void addModifiedVar(NuPair var) {
		modifiedVars.add(var);
	}

	// add modified variables
	public void addModifiedVars(List<NuPair> vars) {
		if(vars != null) modifiedVars.addAll(vars);
	}

	// add mapping
	public void addMapping(String s, NuPort p) {
		mapPorts.put(s, p);
	}

	// add Mappings
	public void addMappings(Map<String, NuPort> m) {
		if(m != null) mapPorts.putAll(m);
	}

	// toString
	public String toString() {
		return name;
	}

}