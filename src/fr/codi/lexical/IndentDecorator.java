package fr.codi.lexical;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IndentDecorator implements Iterator<Lexeme> {

	public static final Lexeme INDENT = new Lexeme(Type.DELIMITER, "{");
	public static final Lexeme DEDENT = new Lexeme(Type.DELIMITER, "}");

	private final Iterator<Lexeme> inner;
	private final List<Integer> indentationLevels = new ArrayList<>();
	private final List<Lexeme> nextItems = new LinkedList<>();

	public IndentDecorator(Iterator<Lexeme> inner) {
		this.inner = inner;
	}

	private void computeNexts() {
		if (this.nextItems.isEmpty() && this.inner.hasNext()) {
			final Lexeme lexeme = this.inner.next();
			final Type type = lexeme.getType();
			if (Type.BLANK.equals(type)) {
				final String value = lexeme.getValue();
				final int index = value.lastIndexOf("\n");
				if (index != -1) {
					final int indentationLevel = value.length() - index - 1;
					if (this.indentationLevels.isEmpty()) {
						if (indentationLevel != 0) {
							this.indentationLevels.add(indentationLevel);
							this.nextItems.add(INDENT);
						}
					} else {
						int topIndentationLevel = this.indentationLevels
								.get(this.indentationLevels.size() - 1);
						if (indentationLevel > topIndentationLevel) {
							this.indentationLevels.add(indentationLevel);
							this.nextItems.add(INDENT);
						} else {
							while (indentationLevel < topIndentationLevel) {
								this.indentationLevels
										.remove(this.indentationLevels.size() - 1);
								final int lastIndex = this.indentationLevels
										.size() - 1;
								if (lastIndex == -1) {
									topIndentationLevel = 0;
								} else {
									topIndentationLevel = this.indentationLevels
											.get(lastIndex);
								}
								this.nextItems.add(DEDENT);
							}
						}
					}
				}
			} else if (Type.END.equals(type)) {
				final Iterator<Integer> it = this.indentationLevels.iterator(); 
				while (it.hasNext()) {
					it.next();
					this.nextItems.add(DEDENT);
				}
			}
			this.nextItems.add(lexeme);
		}
	}

	@Override
	public boolean hasNext() {
		this.computeNexts();
		return !this.nextItems.isEmpty();
	}

	@Override
	public Lexeme next() {
		this.computeNexts();
		return this.nextItems.remove(0);
	}
}