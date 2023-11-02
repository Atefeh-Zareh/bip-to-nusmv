package epfl.risd.bip.nusmv.api;

public enum NuUnaryOperator {
	
	POSITIVE(0),
	NEGATIVE(1),
	LOGICAL_NOT(2);
	
	private int value;
	
	public static final int POSITIVE_VALUE = 0;
	public static final int NEGATIVE_VALUE = 1;
	public static final int LOGICAL_NOT_VALUE = 2;
	
	// constructor
	NuUnaryOperator(int v) {
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
		
			case POSITIVE_VALUE : 
				s += NuSyntax.POSTIVE;
				break;
			case NEGATIVE_VALUE : 
				s += NuSyntax.NEGATIVE;
				break;
			case LOGICAL_NOT_VALUE:
				s += NuSyntax.LOGICALNOT;
				break;
			default :
				throw new Error("Unimplemented NuUnary Operator");
		}
		
		return s;
	}

}