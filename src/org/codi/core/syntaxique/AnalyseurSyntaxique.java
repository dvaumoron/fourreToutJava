package org.codi.core.syntaxique;

import java.util.ArrayList;
import java.util.List;

import org.codi.core.lexical.Lexeme;
import org.codi.core.lexical.Lexeme.Type;
import org.codi.core.syntaxique.Transition.Action;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Noeud;

public class AnalyseurSyntaxique {

	private final Etat etatInitial;

	public AnalyseurSyntaxique(Etat etatInitial) {
		this.etatInitial = etatInitial;
	}

	public Expression analyse(Iterable<Lexeme> lexemes) {
		List<Etat> pile = new ArrayList<Etat>();
		pile.add(etatInitial);
		List<List<Expression>> pileNoeud = new ArrayList<List<Expression>>();
		pileNoeud.add(new ArrayList<Expression>());
		lecture:
		for(Lexeme l : lexemes) {
			while (true) {
				int index = pile.size() - 1;
				Transition t = pile.get(index).transition(l);
				switch (t.getAction()) {
				case SHIFT :
					pileNoeud.get(index).add(l.toExpression());
					break lecture;
				case REDUCE :
					int indexMoins1 = index - 1;
					pile.set(indexMoins1, pile.get(indexMoins1).getSuivant());
					pileNoeud.get(indexMoins1)
						.add(new Noeud(t.getSemantique(), pileNoeud.get(index)));
					pile.remove(index);
					pileNoeud.remove(index);
					break;
				case EXPAND :
					pile.add(t.getEtat());
					pileNoeud.add(new ArrayList<Expression>());
					break;
				}
			}
		}
		return pileNoeud.get(0).get(0);
	}

	public static void main(String[] args) {
		Etat etatFinal = new Etat(null); // S -> E.
		Etat etatInitial = new Etat(etatFinal); // S -> .E
		
		Etat etat2 = new Etat(null); // E -> E.+T
		Etat etat1 = new Etat(etat2); // E -> .E+T
		Etat etat3 = new Etat(null); // E -> E+.T
		// E -> E+T.
		// E -> T
		// T -> F
		// T -> T * F
		// F -> NOMBRE
		etatInitial.addTransition(
				new Lexeme(Type.NOMBRE, null),
				new Transition(Action.EXPAND, null, null));

		List<Lexeme> listeLexeme = new ArrayList<Lexeme>();
		listeLexeme.add(new Lexeme(Type.NOMBRE,"35"));
		listeLexeme.add(new Lexeme(Type.OPERATEUR,"+"));
		listeLexeme.add(new Lexeme(Type.NOMBRE,"4"));
		listeLexeme.add(new Lexeme(Type.OPERATEUR,"*"));
		listeLexeme.add(new Lexeme(Type.NOMBRE,"6"));
		listeLexeme.add(new Lexeme(Type.OPERATEUR,"-"));
		listeLexeme.add(new Lexeme(Type.NOMBRE,"25"));
	}

}
