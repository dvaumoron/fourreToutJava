package fr.sudoku;

public class Position {

	private final int line;
	private final int column;

	public Position(final int line, final int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return this.line;
	}

	public int getColumn() {
		return this.column;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(5);
		buffer.append("(");
		buffer.append(this.line);
		buffer.append(",");
		buffer.append(this.column);
		buffer.append(")");
		return buffer.toString();
	}
}