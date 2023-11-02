package epfl.risd.bip.nusmv.module;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;
import epfl.risd.bip.nusmv.expression.NuDefineAction;
import epfl.risd.bip.nusmv.expression.NuInitExpression;
import epfl.risd.bip.nusmv.expression.NuInvarExpression;
import epfl.risd.bip.nusmv.expression.NuNamedElement;
import epfl.risd.bip.nusmv.expression.NuTransExpression;
import epfl.risd.bip.nusmv.expression.NuVarAction;

public class NuModule extends NuNamedElement {

	private List<String> parameters;
	private List<NuVarAction> vars;
	private List<NuInitExpression> inits;
	private List<NuTransExpression> trans;
	private List<NuInvarExpression> invars;
	private List<NuDefineAction> defines;

	// default constructor
	public NuModule() {
		parameters = new LinkedList<String>();
		vars = new LinkedList<NuVarAction>();
		inits = new LinkedList<NuInitExpression>();
		trans = new LinkedList<NuTransExpression>();
		invars = new LinkedList<NuInvarExpression>();
		defines = new LinkedList<NuDefineAction>();
	}

	// normal constructor
	public NuModule(String name, List<String> p, List<NuVarAction> v, List<NuInitExpression> i,
			List<NuTransExpression> t, List<NuInvarExpression> inv, List<NuDefineAction> d) {
		super(name);
		parameters = new LinkedList<String>();
		vars = new LinkedList<NuVarAction>();
		inits = new LinkedList<NuInitExpression>();
		trans = new LinkedList<NuTransExpression>();
		invars = new LinkedList<NuInvarExpression>();
		defines = new LinkedList<NuDefineAction>();
		if(p != null) parameters.addAll(p);
		if(v != null) vars.addAll(v);
		if(i != null) inits.addAll(i);
		if(t != null) trans.addAll(t);
		if(inv != null) invars.addAll(inv);
		if(d != null) defines.addAll(d);
	}

	// copy constructor
	public NuModule(NuModule n) {
		super(n.name);
		parameters = new LinkedList<String>();
		vars = new LinkedList<NuVarAction>();
		inits = new LinkedList<NuInitExpression>();
		trans = new LinkedList<NuTransExpression>();
		invars = new LinkedList<NuInvarExpression>();
		defines = new LinkedList<NuDefineAction>();
		if(n.parameters != null) parameters .addAll(n.parameters);
		if(n.vars != null) vars.addAll(n.vars);
		if(n.inits != null) inits.addAll(n.inits);
		if(n.trans != null) trans.addAll(n.trans);
		if(n.invars != null) invars.addAll(n.invars);
		if(n.defines != null) defines.addAll(n.defines);
	}

	// setters
	public void setParameters(List<String> p) {
		parameters = p;
	}

	public void setVars(List<NuVarAction> v) {
		vars = v;
	}

	public void setInits(List<NuInitExpression> i) {
		inits = i;
	}

	public void setTrans(List<NuTransExpression> t) {
		trans = t;
	}

	public void setInvars(List<NuInvarExpression> inv) {
		invars = inv;
	}

	public void setDefines(List<NuDefineAction> d) {
		defines = d;
	}

	// getters
	public List<String> getParameters() {
		return parameters;
	}

	public List<NuVarAction> getVars() {
		return vars;
	}

	public List<NuInitExpression> getInits() {
		return inits;
	}

	public List<NuTransExpression> getTrans() {
		return trans;
	}

	public List<NuInvarExpression> getInvars() {
		return invars;
	}

	public List<NuDefineAction> getDefines() {
		return defines;
	}

	// add parameter
	public void addParameter(String p) {
		if(p != null) parameters.add(p);
	}

	// add parameters
	public void addParameters(List<String> p) {
		if(p != null) parameters.addAll(p);
	}

	// add VAR
	public void addVar(NuVarAction v) {
		if(v != null) vars.add(v);
	}

	// add VARS
	public void addVars(List<NuVarAction> v) {
		if(v != null) vars.addAll(v);
	}

	// add INIT
	public void addInit(NuInitExpression i) {
		if(i != null) inits.add(i);
	}

	// add INITS
	public void addInits(List<NuInitExpression> i) {
		if(i != null) inits.addAll(i);
	}

	// add TRAN
	public void addTran(NuTransExpression t) {
		if(t != null) trans.add(t);
	}

	// add TRANS
	public void addTrans(List<NuTransExpression> t) {
		if(t != null) trans.addAll(t);
	}

	// add INVAR
	public void addInvar(NuInvarExpression inv) {
		if(inv != null) invars.add(inv);
	}

	// add INVARS
	public void addInvars(List<NuInvarExpression> inv) {
		if(inv != null) invars.addAll(inv);
	}

	// add DEFINE
	public void addDefine(NuDefineAction d) {
		if(d != null) defines.add(d);
	}

	// add DEFINES
	public void addDefines(List<NuDefineAction> d) {
		if(d != null) defines.addAll(d);
	}

	// toString
	public String toString(boolean main) {
		String s = "";

		s += printModuleName();
		s += printParameters();
		s += "\n\n";
		s += printVars();
		s += "\n";
		s += printDefines();
		s += "\n";
		s += printInits();
		s += "\n";
		s += printInvars();
		s += "\n";
		s += printTrans(main);
		s += "\n";

		return s;
	}

	public String toString() {
		return toString(false);
	}

	public String printModuleName() {
		String s = "";

		s += NuSyntax.MODULE;
		s += " ";
		s += name;

		return s;
	}

	public String printParameters() {
		String s = "";

		if(!parameters.isEmpty())
		{
			s += "(";
			boolean first = true;

			for(String param : parameters)
			{ 
				if(!first) s += ", ";
				s += param;
				first = false;
			}

			s += ")";
		}

		return s;
	}

	public String printVars() {
		String s = "";

		for(NuVarAction varAct : vars)
		{
			s += NuSyntax.TABSPACE;
			s += NuSyntax.VAR;
			s += "\n";
			s += varAct.toString();
		}

		return s;
	}

	public String printDefines() {
		String s = "";

		for(NuDefineAction defAct : defines)
		{
			s += NuSyntax.TABSPACE;
			s += NuSyntax.DEFINE;
			s += "\n";
			s += defAct.toString();
		}

		return s;
	}

	public String printInits() {
		String s = "";

		for(NuInitExpression exp : inits)
		{
			s += NuSyntax.TABSPACE;
			s += NuSyntax.INIT;
			s += "\n";
			s += exp.toString();			
			s += "\n";
		}

		return s;
	}

	public String printInvars() {
		String s = "";

		for(NuInvarExpression exp : invars)
		{
			s += NuSyntax.TABSPACE;
			s += NuSyntax.INVAR;
			s += "\n";
			s += exp.toString();			
			s += "\n";
		}

		return s;
	}

	public String printTrans(boolean main) {
		String s = "";

		for(NuTransExpression exp : trans)
		{
			s += NuSyntax.TABSPACE;
			s += NuSyntax.TRANS;
			s += "\n";
			s += exp.toString(main);
			s += "\n";
		}

		return s;
	}
}