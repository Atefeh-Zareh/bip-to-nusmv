package epfl.risd.bip.nusmv.api;

public enum NuBinaryOperator {
	
	ADDITION(0),
	SUBTRACTION(1),
	MULTIPLICATION(2),
	DIVISION(3),
	MODULUS(4),
	LOGICAL_AND(5),
	LOGICAL_OR(6),
	LOGICAL_XOR(7),
	EQUALITY(8),
	INEQUALITY(9),
	GREATER_THAN(10),
	GREATER_THAN_OR_EQUAL(11),
	LESS_THAN(12),
	LESS_THAN_OR_EQUAL(13),
	LEFT_SHIFT(14),
	RIGHT_SHIFT(15),
	IMPLIES(16),
	EQUIVELANT(17);
	
	private int value;
	
	public static final int ADDITION_VALUE = 0;
	public static final int SUBTRACTION_VALUE = 1;
	public static final int MULTIPLICATION_VALUE = 2;
	public static final int DIVISION_VALUE = 3;
	public static final int MODULUS_VALUE = 4;
	public static final int LOGICAL_AND_VALUE = 5;
	public static final int LOGICAL_OR_VALUE = 6;
	public static final int LOGICAL_XOR_VALUE = 7;
	public static final int EQUALITY_VALUE = 8;
	public static final int INEQUALITY_VALUE = 9;
	public static final int GREATER_THAN_VALUE = 10;
	public static final int GREATER_THAN_OR_EQUAL_VALUE = 11;
	public static final int LESS_THAN_VALUE = 12;
	public static final int LESS_THAN_OR_EQUAL_VALUE = 13;
	public static final int LEFT_SHIFT_VALUE = 14;
	public static final int RIGHT_SHIFT_VALUE = 15;
	public static final int IMPLIES_VALUE = 16;
	public static final int EQUIVELANT_VALUE = 17;
	
	// constructor
	NuBinaryOperator(int v) {
		value = v;
	}
	
	// setters
	public void setValue(int v) {
		value = v;
	}
	
	// getters
	public int getValue() {
		return value; 
	}
	
	// toString
	public String toString() {
		String s = "";

		switch(value) {

			case ADDITION_VALUE : 
				s += NuSyntax.ADD;
				break;
			case SUBTRACTION_VALUE : 
				s += NuSyntax.SUBTRACT;
				break;
			case MULTIPLICATION_VALUE : 
				s += NuSyntax.MULTIPLY;
				break;
			case DIVISION_VALUE : 
				s += NuSyntax.DIVIDE;
				break;	
			case MODULUS_VALUE : 
				s += NuSyntax.MODULO;
				break;	
			case LOGICAL_AND_VALUE : 
				s += NuSyntax.LOGICALAND;
				break;
			case LOGICAL_OR_VALUE : 
				s += NuSyntax.LOGICALOR;
				break;
			case LOGICAL_XOR_VALUE : 
				s += NuSyntax.LOGICALXOR;
				break;
			case EQUALITY_VALUE : 
				s += NuSyntax.EQUAL;
				break;	
			case INEQUALITY_VALUE : 
				s += NuSyntax.NOTEQUAL;
				break;
			case GREATER_THAN_VALUE : 
				s += NuSyntax.GREATERTHAN;
				break;
			case GREATER_THAN_OR_EQUAL_VALUE : 
				s += NuSyntax.GREATERTHANOREQUAL;
				break;
			case LESS_THAN_VALUE : 
				s += NuSyntax.LESS;
				break;
			case LESS_THAN_OR_EQUAL_VALUE : 
				s += NuSyntax.LESSTHANOREQUAL;
				break;	
			case LEFT_SHIFT_VALUE:
				s += NuSyntax.SHIFTLEFT;
				break;
			case RIGHT_SHIFT_VALUE:
				s += NuSyntax.SHIFTRIGHT;
				break;
			case IMPLIES_VALUE:
				s += NuSyntax.IMPLIES;
				break;
			case EQUIVELANT_VALUE:
				s += NuSyntax.EQUIVELANT;
				break;
			default :
				throw new Error("Unimplemented NuBinary Operator");
		}

		return s;
	}
	
}