package fr.jlisp.lexical;

public class Lexeme {

	private final Type type;
	private final String value;

	public Lexeme(final Type type, final String value) {
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(this.type);
		buffer.append(", ");
		buffer.append(this.value);
		return buffer.toString();
	}
}