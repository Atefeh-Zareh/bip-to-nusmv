package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuUnaryOperator;

public class NuUnaryExpression implements NuExpression {

	private NuUnaryOperator operator;
	private NuExpression operand;

	// default constructor
	public NuUnaryExpression() {

	}

	// normal constructor
	public NuUnaryExpression(NuUnaryOperator op, NuExpression exp) {
		operator = op;
		operand = exp;
	}

	// copy constructor
	public NuUnaryExpression(NuUnaryExpression n) {
		operator = n.operator;
		operand = n.operand;
	}

	// setters
	public void setOperator(NuUnaryOperator op) {
		operator = op;
	}

	public void setOperand(NuExpression exp) {
		operand = exp;
	}

	// getters
	public NuUnaryOperator getOperator() {
		return operator;
	}

	public NuExpression getOperand() {
		return operand;
	}

	// toString
	public String toString() {
		String s = "";

		s += "( ";
		s += operator.toString();
		s += "(";
		s += NuExpressionHelper.expressionToString(operand);
		s += ") )";

		return s;
	}

}