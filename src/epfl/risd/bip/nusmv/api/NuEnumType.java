package epfl.risd.bip.nusmv.api;

public enum NuEnumType {

	BOOLEAN(0, "boolean"),
	SYMBOLIC_ENUM(1, "symbolic enum"),
	UNSIGNED_WORD(2, "unsigned word"),
	SIGNED_WORD(3, "signed word"),
	MODULE_INSTANT(4, "module");

	private int value; 
	private String type;

	public static final int BOOLEAN_VALUE = 0;
	public static final int SYMBOLIC_ENUM_VALUE = 1;
	public static final int UNSIGNED_WORD_VALUE = 2;
	public static final int SIGNED_WORD_VALUE = 3;
	public static final int MODULE_INSTANT_VALUE = 4;

	// constructor
	NuEnumType(int v, String t) {
		value = v;
		type = t;
	}

	// setters
	public void setValue(int v) {
		value = v;
	}

	public void setType(String t) {
		type = t;
	}

	// getters
	public int getValue() {
		return value; 
	}

	public String getType() {
		return type; 
	}

	// toString
	public String toString() {
		return type;
	}

}