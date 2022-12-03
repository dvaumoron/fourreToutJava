package fr.codi.lexical;

import java.util.Iterator;

public class LexemeIterator implements Iterator<Lexeme> {

	private final Etat start;
	private String phrase;

	public LexemeIterator(final Etat start, final String phrase) {
		this.start = start;
		this.phrase = phrase;
	}

	@Override
	public boolean hasNext() {
		return !this.phrase.isEmpty();
	}

	@Override
	public Lexeme next() {
		Etat current = this.start;

		int i = 0;
		Integer resIndex = null;
		Type resType = null;
		final int length = this.phrase.length();
		while (current.isNotPoubelle() && i < length) {
			current = current.next(this.phrase.charAt(i));
			final Type currentType = current.getType();
			++i;
			if (current.isNotPoubelle() && currentType != null) {
				resIndex = i;
				resType = currentType;
			}
		}

		if (resIndex == null) {
			final int indexOf = this.phrase.indexOf("\n");
			final String token;
			if (indexOf == -1) {
				token = this.phrase;
			} else {
				token = this.phrase.substring(0, indexOf);
			}
			throw new IllegalStateException("token non reconnu : " + token);
		} else {
			final int resIndexInt = resIndex;
			final String res = this.phrase.substring(0, resIndexInt);
			this.phrase = this.phrase.substring(resIndexInt);
			return new Lexeme(resType, res);
		}
	}
}