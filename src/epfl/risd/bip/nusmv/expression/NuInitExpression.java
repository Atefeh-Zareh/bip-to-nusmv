package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuInitExpression implements NuExpression {

	private List<NuExpression> inits;

	// default constructor
	public NuInitExpression() {
		inits = new LinkedList<NuExpression>();
	}

	// normal constructor
	public NuInitExpression(List<NuExpression> exps) {
		inits = new LinkedList<NuExpression>();
		if(exps != null) inits.addAll(exps);
	}

	// copy constructor
	public NuInitExpression(NuInitExpression n) {
		inits = new LinkedList<NuExpression>();
		if(n.inits != null) inits.addAll(n.inits);
	}

	// setters
	public void setExpression(List<NuExpression> exps) {
		inits = exps;
	}

	// getters
	public List<NuExpression> getExpression() {
		return inits;
	}

	// add expression
	public void addExpression(NuExpression exp) {
		if(exp != null) inits.add(exp);
	}

	// add expressions
	public void addExpressions(List<NuExpression> exps) {
		if(exps != null) inits.addAll(exps);
	}

	// toString
	public String toString() {
		String s = "";
		boolean first = true;

		for(NuExpression init : inits)
		{
			if(!first) s += " " + NuSyntax.LOGICALAND + "\n";
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += NuExpressionHelper.expressionToString(init);
			first = false;
		}

		s += NuSyntax.SEMICOLON;

		return s;		
	}

}