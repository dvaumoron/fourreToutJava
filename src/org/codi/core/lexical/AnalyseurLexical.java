package org.codi.core.lexical;

import java.util.Iterator;

import org.codi.core.lexical.Lexeme.Type;

public class AnalyseurLexical {

	private final Etat etatInitial;

	public AnalyseurLexical(Etat etatInitial) {
		this.etatInitial = etatInitial;
	}

	public Iterable<Lexeme> analise(String phrase) {
		return new ParserLexical(phrase);
	}

	private class ParserLexical implements Iterable<Lexeme> {

		private final String phrase;

		public ParserLexical(String phrase) {
			this.phrase = phrase;
		}

		@Override
		public Iterator<Lexeme> iterator() {
			return new ParsingLexical();
		}

		private class ParsingLexical implements Iterator<Lexeme> {

			private int position = 0;
			private boolean finAEnvoyer = true;

			@Override
			public boolean hasNext() {
				return position < phrase.length() || finAEnvoyer;
			}

			@Override
			public Lexeme next() {
				Lexeme result;
				if (position >= phrase.length()) {
					finAEnvoyer = false;
					result = Lexeme.create(Type.FIN);
				} else {
					Etat etatCourant = etatInitial;
					Type dernierTypeValide = null;
					int dernierePositionValide = 0;
					int positionCourante = position;
					while (etatCourant.isNotPoubelle() && positionCourante < phrase.length()) {
						etatCourant = etatCourant.transition(phrase.charAt(positionCourante));
						if (etatCourant.isValide()) {
							dernierTypeValide = etatCourant.getType();
							dernierePositionValide = positionCourante;
						}
						++positionCourante;
					}
					int anciennePosition = position;
					position = dernierePositionValide + 1;
					result = Lexeme.create(
							dernierTypeValide,
							phrase.substring(anciennePosition, position));
				}
				return result;
			}

			@Override
			public void remove() {
				// do nothing
			}

		}

	}

	public static void main(String[] args) {
		AnalyseurLexical analyseurLexical = buildAnalyseurExpression();
		for(Lexeme l : analyseurLexical.analise("35+4*6-25")) {
			System.out.println(l);
		}
	}

	public static AnalyseurLexical buildAnalyseurExpression() {
		Etat etatPoubelle = Etat.create();
		Etat etatNombre = Etat.create(Type.NOMBRE, etatPoubelle);
		etatNombre.addTransition('1', etatNombre);
		etatNombre.addTransition('2', etatNombre);
		etatNombre.addTransition('3', etatNombre);
		etatNombre.addTransition('4', etatNombre);
		etatNombre.addTransition('5', etatNombre);
		etatNombre.addTransition('6', etatNombre);
		etatNombre.addTransition('7', etatNombre);
		etatNombre.addTransition('8', etatNombre);
		etatNombre.addTransition('9', etatNombre);
		Etat etatOperateur = Etat.create(Type.OPERATEUR, etatPoubelle);
		Etat etatInitial = Etat.create(etatPoubelle);
		etatInitial.addTransition('1', etatNombre);
		etatInitial.addTransition('2', etatNombre);
		etatInitial.addTransition('3', etatNombre);
		etatInitial.addTransition('4', etatNombre);
		etatInitial.addTransition('5', etatNombre);
		etatInitial.addTransition('6', etatNombre);
		etatInitial.addTransition('7', etatNombre);
		etatInitial.addTransition('8', etatNombre);
		etatInitial.addTransition('9', etatNombre);
		etatInitial.addTransition('+', etatOperateur);
		etatInitial.addTransition('-', etatOperateur);
		etatInitial.addTransition('*', etatOperateur);
		etatInitial.addTransition('/', etatOperateur);
		
		AnalyseurLexical analyseurLexical = new AnalyseurLexical(etatInitial);
		return analyseurLexical;
	}

}
