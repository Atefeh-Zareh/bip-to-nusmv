package epfl.risd.bip.nusmv.expression;

public class NuNamedElement implements NuExpression {

	protected String name;

	// default constructor
	public NuNamedElement() {

	}

	// normal constructor
	public NuNamedElement(String name) {
		this.name = name;
	}

	// copy constructor
	public NuNamedElement(NuNamedElement n) {
		name = n.name;
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	// getters
	public String getName() {
		return name;
	}

	// toString
	public String toString() {
		return name;
	}

}