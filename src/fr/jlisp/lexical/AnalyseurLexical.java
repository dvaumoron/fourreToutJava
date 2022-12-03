package fr.jlisp.lexical;

public class AnalyseurLexical {

	private final Etat start;

	public AnalyseurLexical(final Etat start) {
		this.start = start;
	}

	public Iterable<Lexeme> parse(final String phrase) {
		return new LexemeIterable(this.start, phrase);
	}

	public static void main(final String[] args) {
		final AnalyseurLexical analyseur = createAnalyseur();

		String phrase = " -37 -4. * +3 (x3.__y__ + 2.54) 5 0. ++ \"€\\\"\" null true false _false tr true0";
		System.out.println(phrase);
		for (final Lexeme lexeme : analyseur.parse(phrase)) {
			System.out.println(lexeme);
		}
	}

	public static AnalyseurLexical createAnalyseur() {
		final Etat poubelle = new Etat("poubelle", false);

		final Etat start = new Etat("start");
		start.setDefautTransition(poubelle);

		final Etat blank = new Etat("blank", Type.BLANK);
		blank.setDefautTransition(poubelle);
		final String blanks = " \t";
		start.addTransitions(blanks, blank);

		blank.addTransitions(blanks, blank);

		final Etat newLine = new Etat("newLine", Type.NEW_LINE);
		newLine.setDefautTransition(poubelle);
		final char newLineChar = '\n';
		start.addTransition(newLineChar, newLine);

		final Etat number = new Etat("number", Type.INTEGER_LITERAL);
		number.setDefautTransition(poubelle);
		final String digits = "0123456789";
		start.addTransitions(digits, number);

		number.addTransitions(digits, number);

		final Etat number2 = new Etat("number2", Type.FLOAT_LITERAL);
		number2.setDefautTransition(poubelle);
		number.addTransition('.', number2);

		number2.addTransitions(digits, number2);

		final Etat identifier = new Etat("identifier", Type.IDENTIFIER);
		identifier.setDefautTransition(poubelle);
		final String alphas = "_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		final String operateurs = "+-*/.,:;!=<^>%@$~?&|[]{}";
		start.addTransitions(alphas, identifier);
		start.addTransitions(operateurs, identifier);

		identifier.addTransitions(alphas, identifier);
		identifier.addTransitions(operateurs, identifier);
		identifier.addTransitions(digits, identifier);

		final Etat delimiteur = new Etat("delimiteur", Type.START_DELIMITER);
		delimiteur.setDefautTransition(poubelle);
		start.addTransition('(', delimiteur);

		final Etat delimiteur2 = new Etat("delimiteur2", Type.END_DELIMITER);
		delimiteur2.setDefautTransition(poubelle);
		start.addTransition(')', delimiteur2);

		final Etat string = new Etat("string");
		string.setDefautTransition(string);
		final char quoteChar = '"';
		start.addTransition(quoteChar, string);

		string.addTransition(newLineChar, poubelle);

		final Etat string2 = new Etat("string2", Type.STRING_LITERAL);
		string2.setDefautTransition(poubelle);
		string.addTransition(quoteChar, string2);

		final Etat string3 = new Etat("string3");
		string3.setDefautTransition(string);
		string.addTransition('\\', string3);

		string3.addTransition(newLineChar, poubelle);

		final Etat char1 = new Etat("char1");
		start.addTransition('\'', char1);

		char1.addTransition(newLineChar, poubelle);

		final Etat char2 = new Etat("char2");
		char2.setDefautTransition(poubelle);
		char1.setDefautTransition(char2);

		final Etat char3 = new Etat("char3", Type.CHAR_LITERAL);
		char3.setDefautTransition(poubelle);

		char2.addTransition('\'', char3);

		final Etat char4 = new Etat("char4");

		char1.addTransition('\\', char4);

		char4.addTransition(newLineChar, poubelle);

		final Etat char5 = new Etat("char5");
		char4.setDefautTransition(char5);
		char5.setDefautTransition(poubelle);

		char5.addTransition('\'', char3);

		final Etat number3 = new Etat("number3", Type.IDENTIFIER);
		number3.setDefautTransition(poubelle);
		start.addTransitions("+-", number3);
		number3.addTransitions(digits, number);
		number3.addTransitions(alphas, identifier);
		number3.addTransitions(operateurs, identifier);

		final Etat comment = new Etat("comment", Type.BLANK);
		comment.setDefautTransition(comment);
		comment.addTransition(newLineChar, poubelle);
		start.addTransition('#', comment);

		final Etat nullKeyword = new Etat("nullKeyword", Type.IDENTIFIER);
		nullKeyword.setDefautTransition(poubelle);
		start.addTransition('n', nullKeyword);

		nullKeyword.addTransitions(alphas, identifier);
		nullKeyword.addTransitions(operateurs, identifier);
		nullKeyword.addTransitions(digits, identifier);

		final Etat nullKeyword2 = new Etat("nullKeyword2", Type.IDENTIFIER);
		nullKeyword2.setDefautTransition(poubelle);
		nullKeyword.addTransition('u', nullKeyword2);

		nullKeyword2.addTransitions(alphas, identifier);
		nullKeyword2.addTransitions(operateurs, identifier);
		nullKeyword2.addTransitions(digits, identifier);

		final Etat nullKeyword3 = new Etat("nullKeyword3", Type.IDENTIFIER);
		nullKeyword3.setDefautTransition(poubelle);
		nullKeyword2.addTransition('l', nullKeyword3);

		nullKeyword3.addTransitions(alphas, identifier);
		nullKeyword3.addTransitions(operateurs, identifier);
		nullKeyword3.addTransitions(digits, identifier);

		final Etat nullKeyword4 = new Etat("nullKeyword4", Type.NULL_KEYWORD);
		nullKeyword4.setDefautTransition(poubelle);
		nullKeyword3.addTransition('l', nullKeyword4);

		nullKeyword4.addTransitions(alphas, identifier);
		nullKeyword4.addTransitions(operateurs, identifier);
		nullKeyword4.addTransitions(digits, identifier);

		final Etat trueKeyword = new Etat("trueKeyword", Type.IDENTIFIER);
		trueKeyword.setDefautTransition(poubelle);
		start.addTransition('t', trueKeyword);

		trueKeyword.addTransitions(alphas, identifier);
		trueKeyword.addTransitions(operateurs, identifier);
		trueKeyword.addTransitions(digits, identifier);

		final Etat trueKeyword2 = new Etat("trueKeyword2", Type.IDENTIFIER);
		trueKeyword2.setDefautTransition(poubelle);
		trueKeyword.addTransition('r', trueKeyword2);

		trueKeyword2.addTransitions(alphas, identifier);
		trueKeyword2.addTransitions(operateurs, identifier);
		trueKeyword2.addTransitions(digits, identifier);

		final Etat trueKeyword3 = new Etat("trueKeyword3", Type.IDENTIFIER);
		trueKeyword3.setDefautTransition(poubelle);
		trueKeyword2.addTransition('u', trueKeyword3);

		trueKeyword3.addTransitions(alphas, identifier);
		trueKeyword3.addTransitions(operateurs, identifier);
		trueKeyword3.addTransitions(digits, identifier);

		final Etat trueKeyword4 = new Etat("trueKeyword4", Type.TRUE_KEYWORD);
		trueKeyword4.setDefautTransition(poubelle);
		trueKeyword3.addTransition('e', trueKeyword4);

		trueKeyword4.addTransitions(alphas, identifier);
		trueKeyword4.addTransitions(operateurs, identifier);
		trueKeyword4.addTransitions(digits, identifier);

		final Etat falseKeyword = new Etat("falseKeyword", Type.IDENTIFIER);
		falseKeyword.setDefautTransition(poubelle);
		start.addTransition('f', falseKeyword);

		falseKeyword.addTransitions(alphas, identifier);
		falseKeyword.addTransitions(operateurs, identifier);
		falseKeyword.addTransitions(digits, identifier);

		final Etat falseKeyword2 = new Etat("falseKeyword2", Type.IDENTIFIER);
		falseKeyword2.setDefautTransition(poubelle);
		falseKeyword.addTransition('a', falseKeyword2);

		falseKeyword2.addTransitions(alphas, identifier);
		falseKeyword2.addTransitions(operateurs, identifier);
		falseKeyword2.addTransitions(digits, identifier);

		final Etat falseKeyword3 = new Etat("falseKeyword3", Type.IDENTIFIER);
		falseKeyword3.setDefautTransition(poubelle);
		falseKeyword2.addTransition('l', falseKeyword3);

		falseKeyword3.addTransitions(alphas, identifier);
		falseKeyword3.addTransitions(operateurs, identifier);
		falseKeyword3.addTransitions(digits, identifier);

		final Etat falseKeyword4 = new Etat("falseKeyword4", Type.IDENTIFIER);
		falseKeyword4.setDefautTransition(poubelle);
		falseKeyword3.addTransition('s', falseKeyword4);

		falseKeyword4.addTransitions(alphas, identifier);
		falseKeyword4.addTransitions(operateurs, identifier);
		falseKeyword4.addTransitions(digits, identifier);

		final Etat falseKeyword5 = new Etat("falseKeyword5", Type.FALSE_KEYWORD);
		falseKeyword5.setDefautTransition(poubelle);
		falseKeyword4.addTransition('e', falseKeyword5);

		falseKeyword5.addTransitions(alphas, identifier);
		falseKeyword5.addTransitions(operateurs, identifier);
		falseKeyword5.addTransitions(digits, identifier);

		return new AnalyseurLexical(start);
	}
}