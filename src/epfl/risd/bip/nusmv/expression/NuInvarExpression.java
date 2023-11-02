package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuInvarExpression implements NuExpression {

	private List<NuExpression> invars;

	// default constructor
	public NuInvarExpression() {
		invars = new LinkedList<NuExpression>();
	}

	// normal constructor
	public NuInvarExpression(List<NuExpression> exps) {
		invars = new LinkedList<NuExpression>();
		if(exps != null) invars.addAll(exps);
	}

	// copy constructor
	public NuInvarExpression(NuInvarExpression n) {
		invars = new LinkedList<NuExpression>();
		if(n.invars != null) invars.addAll(n.invars);
	}

	// setters
	public void setExpression(List<NuExpression> exps) {
		invars = exps;
	}

	// getters
	public List<NuExpression> getExpression() {
		return invars;
	}

	// add expression
	public void addExpression(NuExpression exp) {
		if(exp != null) invars.add(exp);
	}

	// add expressions
	public void addExpressions(List<NuExpression> exps) {
		if(exps != null) invars.addAll(exps);
	}

	// toString
	public String toString() {
		String s = "";
		boolean first = true;

		for(NuExpression invar : invars)
		{
			if(!first) s += " " + NuSyntax.LOGICALAND + "\n";
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += NuExpressionHelper.expressionToString(invar);
			first = false;
		}

		s += NuSyntax.SEMICOLON;

		return s;		
	}

}