package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuAssignmentAction implements NuAction {

	private NuVariable target;
	private NuExpression value;

	// default constructor
	public NuAssignmentAction() {
		target = new NuVariable();
	}

	// normal constructor
	public NuAssignmentAction(NuVariable t, NuExpression v) {
		target = new NuVariable(t);
		value = v;
	}

	// copy constructor
	public NuAssignmentAction(NuAssignmentAction n) {
		target = new NuVariable(n.target);
		value = n.value;
	}

	// setters
	public void setTarget(NuVariable t) {
		target = new NuVariable(t);
	}

	public void setValue(NuExpression v) {
		value = v;
	}

	// getters
	public NuVariable getTarget() {
		return target;
	}

	public NuExpression getValue() {
		return value;
	}

	// toString
	public String toString() {
		String s = "";

		s += target.getName();
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += NuSyntax.ASSIGNMENT;
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += NuExpressionHelper.expressionToString(value);

		return s;
	}

}