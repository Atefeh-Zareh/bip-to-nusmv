package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuBinaryOperator;

public class NuBinaryExpression implements NuExpression {

	private NuBinaryOperator operator;
	private NuExpression leftOperand;
	private NuExpression rightOperand;

	// default constructor
	public NuBinaryExpression() {

	}

	// normal constructor
	public NuBinaryExpression(NuBinaryOperator op, NuExpression lo, NuExpression ro) {
		operator = op;
		leftOperand = lo;
		rightOperand = ro;
	}

	// copy constructor
	public NuBinaryExpression(NuBinaryExpression n) {
		operator = n.operator;
		leftOperand = n.leftOperand;
		rightOperand = n.rightOperand;
	}

	// setters
	public void setOperator(NuBinaryOperator op) {
		operator = op;
	}

	public void setLeftOperand(NuExpression lo) {
		leftOperand = lo;
	}

	public void setRightOperand(NuExpression ro) {
		rightOperand = ro;
	}

	// getters
	public NuBinaryOperator getOperator() {
		return operator;
	}

	public NuExpression getLeftOperand() {
		return leftOperand;
	}

	public NuExpression getRightOperand() {
		return rightOperand;
	}

	// toString
	public String toString() {
		String s = "";

		s += "( (";
		s += NuExpressionHelper.expressionToString(leftOperand);
		s += ") ";
		s += operator.toString();
		s += " (";
		s += NuExpressionHelper.expressionToString(rightOperand);
		s += ") )";

		return s;
	}

}