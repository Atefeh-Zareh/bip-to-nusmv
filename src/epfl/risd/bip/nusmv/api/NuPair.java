package epfl.risd.bip.nusmv.api;

import epfl.risd.bip.nusmv.expression.NuPort;

public class NuPair {
	
	private NuPort port;
	private String variable;
	private boolean old;
	
	// default constructor
	public NuPair() {
		port = new NuPort();
		old = true;
	}
	
	// normal constructor
	public NuPair(NuPort p, String v) {
		port = new NuPort(p);
		variable = v;
		old = true;
	}
	
	// copy constructor
	public NuPair(NuPair n) {
		port = new NuPort(n.port);
		variable = n.variable;
		old = n.old;
	}
	
	// setters
	public void setPort(NuPort p) {
		port = new NuPort(p);
	}
	
	public void setVariable(String v) {
		variable = v;
	}
	
	public void setOld(boolean o) {
		old = o;
	}
	
	// getters
	public NuPort getPort() {
		return port;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public boolean getOld() {
		return old;
	}
	
	// toString
	public String toString() {
		return port.getComponent() + "." + variable;
	}

}