package fr.codi.text;

public class Indenter {

	private int level;

	public Indenter(final int level) {
		this.level = level;
	}

	public Indenter() {
		this(0);
	}

	public void increment() {
		this.level++;
	}

	public void decrement() {
		this.level--;
	}

	public void printIndentation(final Rope buffer) {
		for (int i = 0; i < this.level; i++) {
			buffer.append("\t");
		}
	}
}