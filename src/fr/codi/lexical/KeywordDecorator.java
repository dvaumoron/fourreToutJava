package fr.codi.lexical;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KeywordDecorator implements Iterator<Lexeme> {

	private final Iterator<Lexeme> inner;
	private final Set<String> keywords;

	public KeywordDecorator(Iterator<Lexeme> inner, String[] keywords) {
		this.inner = inner;
		this.keywords = new HashSet<>(Arrays.asList(keywords));
	}

	@Override
	public boolean hasNext() {
		return this.inner.hasNext();
	}

	@Override
	public Lexeme next() {
		Lexeme lexeme = this.inner.next();
		if (Type.IDENTIFIER.equals(lexeme.getType())) {
			final String value = lexeme.getValue();
			if (this.keywords.contains(value)) {
				lexeme = new Lexeme(Type.KEYWORD, value);
			}
		}
		return lexeme;
	}
}