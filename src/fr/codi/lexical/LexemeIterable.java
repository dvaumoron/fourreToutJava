package fr.codi.lexical;

import java.util.Iterator;

public class LexemeIterable implements Iterable<Lexeme> {

	private final Etat start;
	private final String phrase;
	private final String[] keywords;

	public LexemeIterable(final Etat start, final String phrase,
			final String[] keywords) {
		this.start = start;
		this.phrase = phrase;
		this.keywords = keywords;
	}

	@Override
	public Iterator<Lexeme> iterator() {
		return new KeywordDecorator(
				new SkipBlankDecorator(new IndentDecorator(new EndDecorator(
						new LexemeIterator(this.start, this.phrase)))),
				this.keywords);
	}
}