package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuTransExpression implements NuExpression {

	private List<NuExpression> trans;

	// default constructor
	public NuTransExpression() {
		trans = new LinkedList<NuExpression>();
	}

	// normal constructor
	public NuTransExpression(List<NuExpression> exps) {
		trans = new LinkedList<NuExpression>();
		if(exps != null) trans.addAll(exps);
	}

	// copy constructor
	public NuTransExpression(NuTransExpression n) {
		trans = new LinkedList<NuExpression>();
		if(n.trans != null) trans.addAll(n.trans);
	}

	// setters
	public void setExpression(List<NuExpression> exps) {
		trans = exps;
	}

	// getters
	public List<NuExpression> getExpression() {
		return trans;
	}

	// add expression
	public void addExpression(NuExpression exp) {
		if(exp != null) trans.add(exp);
	}

	// add expressions
	public void addExpressions(List<NuExpression> exps) {
		if(exps != null) trans.addAll(exps);
	}

	// toString
	public String toString(boolean main) {
		String s = "";
		String seperator = NuSyntax.LOGICALOR;
		if(main) seperator = NuSyntax.LOGICALAND;
		boolean first = true;

		for(NuExpression tran : trans)
		{
			if(!first) s += " " + seperator + "\n";
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += NuExpressionHelper.expressionToString(tran);
			first = false;
		}

		s += NuSyntax.SEMICOLON;

		return s;		
	}

	public String toString() {
		return toString(false);
	}

}