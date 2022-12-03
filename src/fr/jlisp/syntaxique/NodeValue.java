package fr.jlisp.syntaxique;

import fr.jlisp.interpreteur.runtime.Environement;

public class NodeValue implements Node {

	private final Object value;

	public NodeValue(final Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public Object eval(final Environement environement) {
		return this.value;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("NodeValue(");
		buffer.append(this.value);
		buffer.append(")");
		return buffer.toString();
	}
}