package fr.jlisp.lexical;

import java.util.Iterator;

public class LexemeIterable implements Iterable<Lexeme> {

	private final Etat start;
	private final String phrase;

	public LexemeIterable(final Etat start, final String phrase) {
		this.start = start;
		this.phrase = phrase;
	}

	@Override
	public Iterator<Lexeme> iterator() {
		return new LexemeIterator(this.start, this.phrase);
	}
}