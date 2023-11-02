package epfl.risd.bip.nusmv.expression;

import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
import epfl.risd.bip.nusmv.api.NuBinaryOperator;
import epfl.risd.bip.nusmv.api.NuEnumType;
import epfl.risd.bip.nusmv.api.NuSyntax;
import epfl.risd.bip.nusmv.api.NuUnaryOperator;

public class NuExpressionHelper {

	// NuExpression to String
	public static String expressionToString(NuExpression expression) {
		String s = "";

		if(expression instanceof NuBinaryExpression)
		{
			NuBinaryExpression exp = (NuBinaryExpression) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuConstant)
		{
			NuConstant exp = (NuConstant) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuInitExpression)
		{
			NuInitExpression exp = (NuInitExpression) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuInvarExpression)
		{
			NuInvarExpression exp = (NuInvarExpression) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuNamedElement)
		{
			NuNamedElement exp = (NuNamedElement) expression;
			s += exp.getName();
		}
		else if(expression instanceof NuTransExpression)
		{
			NuTransExpression exp = (NuTransExpression) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuUnaryExpression)
		{
			NuUnaryExpression exp = (NuUnaryExpression) expression;
			s += exp.toString();
		}
		else if(expression instanceof NuVariable)
		{
			NuVariable exp = (NuVariable) expression;
			s += exp.getName();
		}
		else throw new Error("Unimplemented Expression");

		return s;
	}

	// NuVariable to String
	public static String variableToString(NuVariable variable) {
		String s = "";

		if(variable instanceof NuSignedWordVariable)
		{
			NuSignedWordVariable var = (NuSignedWordVariable) variable;
			s += var.toString();
		}
		else if(variable instanceof NuSymbolicEnumVariable)
		{
			NuSymbolicEnumVariable var = (NuSymbolicEnumVariable) variable;
			s += var.toString();
		}
		else if(variable instanceof NuUnsignedWordVariable)
		{
			NuUnsignedWordVariable var = (NuUnsignedWordVariable) variable;
			s += var.toString();
		}
		else if(variable.getType().equals(NuEnumType.BOOLEAN))
		{
			s += variable.getName();
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += NuSyntax.COLON;
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += variable.getType();
		}
		else if(variable.getType().equals(NuEnumType.MODULE_INSTANT))
		{
			s += variable.getName();
		}
		else throw new Error("Unimplemented Variable");

		return s;
	}

	// UnaryOperator to NuUnaryOperator
	public static NuUnaryOperator convertUnaryOperator(UnaryOperator uo) {

		switch(uo.getValue()) {

			case UnaryOperator.POSITIVE_VALUE : 
				return NuUnaryOperator.POSITIVE;
			case UnaryOperator.NEGATIVE_VALUE : 
				return NuUnaryOperator.NEGATIVE;
			case UnaryOperator.LOGICAL_NOT_VALUE:
				return NuUnaryOperator.LOGICAL_NOT;
			case UnaryOperator.BITWISE_NOT_VALUE:
				return NuUnaryOperator.LOGICAL_NOT;
			default :
				throw new Error("Unimplemented Unary Operator");
		}
	}

	// BinaryOperator to NuBinaryOperator
	public static NuBinaryOperator convertBinaryOperator(BinaryOperator bo) {

		switch(bo.getValue()) {

			case BinaryOperator.ADDITION_VALUE : 
				return NuBinaryOperator.ADDITION;
			case BinaryOperator.DIVISION_VALUE : 
				return NuBinaryOperator.DIVISION;
			case BinaryOperator.EQUALITY_VALUE : 
				return NuBinaryOperator.EQUALITY;
			case BinaryOperator.GREATER_THAN_VALUE : 
				return NuBinaryOperator.GREATER_THAN;
			case BinaryOperator.GREATER_THAN_OR_EQUAL_VALUE : 
				return NuBinaryOperator.GREATER_THAN_OR_EQUAL;
			case BinaryOperator.INEQUALITY_VALUE : 
				return NuBinaryOperator.INEQUALITY;
			case BinaryOperator.LESS_THAN_VALUE : 
				return NuBinaryOperator.LESS_THAN;
			case BinaryOperator.LESS_THAN_OR_EQUAL_VALUE : 
				return NuBinaryOperator.LESS_THAN_OR_EQUAL;
			case BinaryOperator.LOGICAL_AND_VALUE : 
				return NuBinaryOperator.LOGICAL_AND;
			case BinaryOperator.LOGICAL_OR_VALUE : 
				return NuBinaryOperator.LOGICAL_OR;
			case BinaryOperator.BITWISE_AND_VALUE : 
				return NuBinaryOperator.LOGICAL_AND;
			case BinaryOperator.BITWISE_OR_VALUE:
				return NuBinaryOperator.LOGICAL_OR;
			case BinaryOperator.BITWISE_XOR_VALUE:
				return NuBinaryOperator.LOGICAL_XOR;
			case BinaryOperator.LEFT_SHIFT_VALUE:
				return NuBinaryOperator.LEFT_SHIFT;
			case BinaryOperator.RIGHT_SHIFT_VALUE:
				return NuBinaryOperator.RIGHT_SHIFT;
			case BinaryOperator.MODULUS_VALUE : 
				return NuBinaryOperator.MODULUS;
			case BinaryOperator.MULTIPLICATION_VALUE : 
				return NuBinaryOperator.MULTIPLICATION;
			case BinaryOperator.SUBSTRACTION_VALUE : 
				return NuBinaryOperator.SUBTRACTION;
			default :
				throw new Error("Unimplemented Binary Operator");
		}
	}

}