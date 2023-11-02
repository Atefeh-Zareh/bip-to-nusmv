package epfl.risd.bip.nusmv.expression;

import java.util.LinkedList;
import java.util.List;

import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuDefineAction implements NuAction {

	private List<NuAssignmentAction> actions;

	// default constructor
	public NuDefineAction() {
		actions = new LinkedList<NuAssignmentAction>();
	}

	// normal constructor
	public NuDefineAction(List<NuAssignmentAction> a) {
		actions = new LinkedList<NuAssignmentAction>();
		if(a != null) actions.addAll(a);
	}

	// copy constructor
	public NuDefineAction(NuDefineAction n) {
		actions = new LinkedList<NuAssignmentAction>();
		if(n.actions != null) actions.addAll(n.actions);
	}

	// setters
	public void setActions(List<NuAssignmentAction> a) {
		actions = a;
	}

	// getters
	public List<NuAssignmentAction> getActions() {
		return actions;
	}

	// add action
	public void addAction(NuAssignmentAction a) {
		if(a != null) actions.add(a);
	}

	// add actions
	public void addActions(List<NuAssignmentAction> a) {
		if(a != null) actions.addAll(a);
	}

	// toString
	public String toString() {
		String s = "";

		for(NuAssignmentAction act : actions)
		{
			s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
			s += act.toString() + NuSyntax.SEMICOLON + "\n";
		}

		return s;
	}

}