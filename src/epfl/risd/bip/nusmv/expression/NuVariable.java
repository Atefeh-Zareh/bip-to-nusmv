package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuEnumType;

public class NuVariable extends NuNamedElement {

	protected NuEnumType type;

	// default constructor
	public NuVariable() {

	}

	// normal constructor
	public NuVariable(String name, NuEnumType t) {
		super(name);
		type = t;
	}

	// copy constructor
	public NuVariable(NuVariable n) {
		super(n.name);
		type = n.type;
	}

	// setters
	public void setType(NuEnumType t) {
		type = t;
	}

	// getters
	public NuEnumType getType() {
		return type;
	}

	// toString
	public String toString() {
		return NuExpressionHelper.variableToString(this);
	}

}