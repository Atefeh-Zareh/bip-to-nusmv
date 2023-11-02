package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuEnumType;

public class NuConstant implements NuExpression {

	private NuEnumType type;
	private String value;

	// default constructor
	public NuConstant() {

	}

	// normal constructor
	public NuConstant(NuEnumType t, String v) {
		type = t;
		value = v;
	}

	// copy constructor
	public NuConstant(NuConstant n) {
		type = n.type;
		value = n.value;
	}

	// setters
	public void setType(NuEnumType t) {
		type = t;
	}

	public void setValue(String v) {
		value = v;
	}

	// getters
	public NuEnumType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	// toString
	public String toString() {
		return value;
	}
}