package fr.jlisp.syntaxique;

import fr.jlisp.interpreteur.runtime.Environement;

public class NodeIdentifier implements Node {

	private final String identifier;

	public NodeIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Object eval(final Environement environement) {
		return environement.get(this.identifier);
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("NodeIdentifier(");
		buffer.append(this.identifier);
		buffer.append(")");
		return buffer.toString();
	}
}