package fr.codi.lexical;

import java.util.Iterator;

public class EndDecorator implements Iterator<Lexeme> {

	private final Iterator<Lexeme> inner;
	private boolean endNext = true;

	public EndDecorator(final Iterator<Lexeme> inner) {
		this.inner = inner;
	}

	@Override
	public boolean hasNext() {
		return this.inner.hasNext() || this.endNext;
	}

	@Override
	public Lexeme next() {
		if (this.inner.hasNext()) {
			return this.inner.next();
		} else if (this.endNext) {
			this.endNext = false;
			return new Lexeme(Type.END, "");
		} else {
			return null;
		}
	}
}