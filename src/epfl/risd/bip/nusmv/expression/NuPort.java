package epfl.risd.bip.nusmv.expression;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import epfl.risd.bip.nusmv.api.NuBinaryOperator;

public class NuPort {

	private String component;
	private String port;
	private NuExpression enablement;
	private List<String> interactions;
	private Map<String, String> mapVars;

	// default constructor
	public NuPort() {
		enablement = null;
		interactions = new LinkedList<String>();
		mapVars = new HashMap<String, String>();
	}

	// normal constructor
	public NuPort(String c, String p, List<String> i, NuExpression e, Map<String, String> m) {
		component = c;
		port = p;
		enablement = e;
		interactions = new LinkedList<String>();
		mapVars = new HashMap<String, String>();
		if(i != null) interactions.addAll(i);
		if(m != null) mapVars.putAll(m);
	}

	// copy constructor
	public NuPort(NuPort n) {
		component = n.component;
		port = n.port;
		enablement = n.enablement;
		interactions = new LinkedList<String>();
		mapVars = new HashMap<String, String>();
		if(n.interactions != null) interactions.addAll(n.interactions);
		if(n.mapVars != null) mapVars.putAll(n.mapVars);
	}

	// setters
	public void setComponent(String c) {
		component = c;
	}

	public void setPort(String p) {
		port = p;
	}

	public void setInteractions(List<String> i) {
		interactions = i;
	}

	public void setEnablement(NuExpression e) {
		enablement = e;
	}

	public void setMapVars(Map<String, String> m) {
		mapVars = m;
	}

	// getters
	public String getComponent() {
		return component;
	}

	public String getPort() {
		return port;
	}

	public List<String> getInteractions() {
		return interactions;
	}

	public NuExpression getEnablement() {
		return enablement;
	}

	public Map<String, String> getMapVars() {
		return mapVars;
	}

	// add interaction
	public void addInteraction(String i) {
		if(i != null) interactions.add(i);
	}

	// add interactions
	public void addInteractions(List<String> i) {
		if(i != null) interactions.addAll(i);
	}

	// add mapping
	public void addMapping(String key, String val) {
		mapVars.put(key, val);
	}

	// add mappings
	public void addMappings(Map<String, String> m) {
		if(m != null) mapVars.putAll(m);
	}
	
	// add enablement
	public void addEnablement(NuExpression e) {
		if(enablement == null) enablement = e;
		else enablement = new NuBinaryExpression(NuBinaryOperator.LOGICAL_OR, enablement, e);
	}

	// toString
	public String toString() {
		return component + "." + port;
	}

}