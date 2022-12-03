package fr.codi.lexical;

import java.util.Iterator;

import fr.codi.pattern.iterator.AbstractComputeNextIterator;

public class SkipBlankDecorator extends AbstractComputeNextIterator<Lexeme> {

	private final Iterator<Lexeme> inner;

	public SkipBlankDecorator(final Iterator<Lexeme> inner) {
		this.inner = inner;
	}

	protected void computeNext() {
		if (this.nextItem == null) {
			while (this.inner.hasNext()) {
				this.nextItem = this.inner.next();
				if (Type.BLANK == this.nextItem.getType()) {
					this.nextItem = null;
				} else {
					break;
				}
			}
		}
	}
}