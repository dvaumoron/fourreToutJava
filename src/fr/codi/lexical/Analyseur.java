package fr.codi.lexical;

public class Analyseur {

	private final Etat start;
	private final String[] keywords;

	public Analyseur(final Etat start, final String... keywords) {
		this.start = start;
		this.keywords = keywords;
	}

	public Iterable<Lexeme> parse(final String phrase) {
		return new LexemeIterable(this.start, phrase, this.keywords);
	}

	public static void main(String[] args) {
		final Analyseur analyseur = createAnalyseur();

		String phrase = "  if0 if 37 * (x3.__y__ + 2.54) \"€\\\"\" else else_ el";
		System.out.println(phrase);
		for (Lexeme lexeme : analyseur.parse(phrase)) {
			System.out.println(lexeme);
		}

		System.out.println();

		phrase = "if (test1!=null)\n\ti=3\n\n\tj=4\nelse\n\tif (!test2)\n\t\ti=5\n\telse\n\t\ti=7";
		System.out.println(phrase);
		for (Lexeme lexeme : analyseur.parse(phrase)) {
			System.out.println(lexeme);
		}
	}

	public static Analyseur createAnalyseur() {
		final Etat poubelle = new Etat("poubelle", false);

		final Etat start = new Etat("start");
		start.setDefautTransition(poubelle);

		final Etat blank = new Etat("blank", Type.BLANK);
		blank.setDefautTransition(poubelle);
		final String blanks = " \t\n";
		start.addTransitions(blanks, blank);

		blank.addTransitions(blanks, blank);

		final Etat number = new Etat("number", Type.NUMBER_LITERAL);
		number.setDefautTransition(poubelle);
		final String digits = "0123456789";
		start.addTransitions(digits, number);

		number.addTransitions(digits, number);

		final Etat number2 = new Etat("number2");
		number2.setDefautTransition(poubelle);
		number.addTransition('.', number2);

		final Etat number3 = new Etat("number3", Type.NUMBER_LITERAL);
		number3.setDefautTransition(poubelle);
		number2.addTransitions(digits, number3);

		number3.addTransitions(digits, number3);

		final Etat identifier = new Etat("identifier", Type.IDENTIFIER);
		identifier.setDefautTransition(poubelle);
		final String alphas = "_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		start.addTransitions(alphas, identifier);

		identifier.addTransitions(alphas, identifier);
		identifier.addTransitions(digits, identifier);

		final Etat operateur = new Etat("operateur", Type.OPERATOR);
		operateur.setDefautTransition(poubelle);
		final String operateurs = "+-*/.!=<>%";
		start.addTransitions(operateurs, operateur);

		operateur.addTransitions(operateurs, operateur);

		final Etat delimiteur = new Etat("delimiteur", Type.DELIMITER);
		delimiteur.setDefautTransition(poubelle);
		start.addTransitions("(){}[],;:", delimiteur);

		final Etat string = new Etat("string");
		string.setDefautTransition(string);
		start.addTransition('"', string);

		final Etat string2 = new Etat("string2", Type.STRING_LITERAL);
		string2.setDefautTransition(poubelle);
		string.addTransition('"', string2);

		final Etat string3 = new Etat("string3");
		string3.setDefautTransition(string);
		string.addTransition('\\', string3);

		return new Analyseur(start, "if", "else", "while", "for", "break",
				"continue", "true", "false", "null");
	}
}