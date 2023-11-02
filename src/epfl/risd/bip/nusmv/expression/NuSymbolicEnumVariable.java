package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuEnumType;
import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuSymbolicEnumVariable extends NuVariable {

	private List<String> enumerations;

	// default constructor
	public NuSymbolicEnumVariable() {
		type = NuEnumType.SYMBOLIC_ENUM;
		enumerations = new LinkedList<String>();
	}

	// normal constructor
	public NuSymbolicEnumVariable(String n, List<String> enums) {
		super(n, NuEnumType.SYMBOLIC_ENUM);
		enumerations = new LinkedList<String>();
		if(enums != null) enumerations.addAll(enums);
	}

	// copy constructor
	public NuSymbolicEnumVariable(NuSymbolicEnumVariable n) {
		super(n.name, NuEnumType.SYMBOLIC_ENUM);
		enumerations = new LinkedList<String>();
		if(n.enumerations != null) enumerations.addAll(n.enumerations);
	}

	// setters
	public void setEnumerations(List<String> enums) {
		enumerations = enums;
	}

	// getters
	public List<String> getEnumerations() {
		return enumerations;
	}

	// add enumeration
	public void addEnumeration(String en) {
		if(en != null) enumerations.add(en);
	}

	// add enumerations
	public void addEnumerations(List<String> enums) {
		if(enums != null) enumerations.addAll(enums);
	}

	// toString
	public String toString() {
		String s = "";
		s += name;
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += NuSyntax.COLON;
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += NuSyntax.ENUMBEGIN;
		boolean first = true;

		for(String en : enumerations)
		{
			if(!first) s += NuSyntax.ENUMSEPERATE;
			s += en;
			first = false;
		}

		s += NuSyntax.ENUMEND;
		return s;
	}

}