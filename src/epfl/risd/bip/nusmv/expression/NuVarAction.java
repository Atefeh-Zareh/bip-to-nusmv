package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuVarAction implements NuAction {

	private List<NuVariable> variables;

	// default constructor
	public NuVarAction() {
		variables =  new LinkedList<NuVariable>();
	}

	// normal constructor
	public NuVarAction(List<NuVariable> v) {
		variables =  new LinkedList<NuVariable>();
		if(v != null) variables.addAll(v);
	}

	// copy constructor
	public NuVarAction(NuVarAction n) {
		variables =  new LinkedList<NuVariable>();
		if(n.variables != null) variables.addAll(n.variables);
	}

	// setters
	public void setVariables(List<NuVariable> v) {
		variables = v;
	}

	// getters
	public List<NuVariable> getVariables() {
		return variables;
	}

	// add variable
	public void addVariable(NuVariable v) {
		if(v != null) variables.add(v);
	}

	// add variables
	public void addVariables(List<NuVariable> v) {
		if(v != null) variables.addAll(v);
	}

	// toString
	public String toString() {
		String s = "";

		for(NuVariable var : variables)
		{
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += var.toString() + NuSyntax.SEMICOLON + "\n";
		}

		return s;
	}

}