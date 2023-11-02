package epfl.risd.bip.nusmv.expression;

import epfl.risd.bip.nusmv.api.NuEnumType;
import epfl.risd.bip.nusmv.api.NuSyntax;

public class NuSignedWordVariable extends NuVariable {

	private int size;
	private char notation;

	// default constructor
	public NuSignedWordVariable() {
		type = NuEnumType.SIGNED_WORD;
	}

	// normal constructor
	public NuSignedWordVariable(String n, int s, char not) {
		super(n, NuEnumType.SIGNED_WORD);
		size = s;
		notation = not;
	}

	// copy constructor
	public NuSignedWordVariable(NuSignedWordVariable n) {
		super(n.name, NuEnumType.SIGNED_WORD);
		size = n.size;
		notation = n.notation;
	}

	// setters
	public void setSize(int s) {
		size = s;
	}

	public void setNotation(char not) {
		notation = not;
	}

	// getters
	public int getSize() {
		return size;
	}

	public char getNotation() {
		return notation;
	}

	// toString
	public String toString() {
		String s = "";

		s += name;
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += NuSyntax.COLON;
		s += NuSyntax.TABSPACE + NuSyntax.TABSPACE;
		s += type.toString();
		s += NuSyntax.WORDSIZEBEGIN;
		s += size;
		s += NuSyntax.WORDSIZEEND;

		return s;
	}

}