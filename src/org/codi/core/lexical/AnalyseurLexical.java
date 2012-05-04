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

			@Override
			public boolean hasNext() {
				return position < phrase.length();
			}

			@Override
			public Lexeme next() {
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
				return new Lexeme(
						dernierTypeValide,
						phrase.substring(anciennePosition, position));
			}

			@Override
			public void remove() {
				// do nothing
			}

		}

	}

	public static void main(String[] args) {
		Etat etatPoubelle = new Etat(null, false, null);
		Etat etatNombre = new Etat(Type.NOMBRE, true, etatPoubelle);
		etatNombre.addTransition('1', etatNombre);
		etatNombre.addTransition('2', etatNombre);
		etatNombre.addTransition('3', etatNombre);
		etatNombre.addTransition('4', etatNombre);
		etatNombre.addTransition('5', etatNombre);
		etatNombre.addTransition('6', etatNombre);
		etatNombre.addTransition('7', etatNombre);
		etatNombre.addTransition('8', etatNombre);
		etatNombre.addTransition('9', etatNombre);
		Etat etatOperateur = new Etat(Type.OPERATEUR, true, etatPoubelle);
		Etat etatInitial = new Etat(null, true, etatPoubelle);
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
		for(Lexeme l : analyseurLexical.analise("35+4-25")) {
			System.out.println(l.getType());
			System.out.println(l.getValeur());
		}
	}

}
