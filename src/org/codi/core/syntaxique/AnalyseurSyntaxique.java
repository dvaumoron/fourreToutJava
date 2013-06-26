package org.codi.core.syntaxique;

import java.util.ArrayList;
import java.util.List;

import org.codi.core.lexical.Lexeme;
import org.codi.core.lexical.Lexeme.Type;
import org.codi.core.syntaxique.Transition.Action;
import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Noeud;
import org.codi.interpreter.semantique.Fusion;
import org.codi.interpreter.semantique.Operation;

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
		for(Lexeme lexeme : lexemes) {
			while (true) {
				int index = pile.size() - 1;
				Transition t = pile.get(index).transition(lexeme);
				switch (t.getAction()) {
				case SHIFT_BLANK :
					pile.set(index, t.getEtat());
					break;
				case SHIFT :
					pile.set(index, t.getEtat());
					Expression expression = lexeme.toExpression();
					if (expression != null) {
						pileNoeud.get(index).add(expression);
					}
					continue lecture;
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
				default:
					throw new IllegalArgumentException();
				}
			}
		}
		return pileNoeud.get(0).get(0);
	}

	public static void main(String[] args) {
		List<Lexeme> listeLexeme = new ArrayList<Lexeme>();
		listeLexeme.add(Lexeme.create(Type.NOMBRE, "35"));
		listeLexeme.add(Lexeme.create(Type.OPERATEUR, "+"));
		listeLexeme.add(Lexeme.create(Type.NOMBRE, "4"));
		listeLexeme.add(Lexeme.create(Type.OPERATEUR, "*"));
		listeLexeme.add(Lexeme.create(Type.NOMBRE, "6"));
		listeLexeme.add(Lexeme.create(Type.OPERATEUR, "-"));
		listeLexeme.add(Lexeme.create(Type.NOMBRE, "25"));
		listeLexeme.add(Lexeme.create(Type.FIN, null));

		AnalyseurSyntaxique analyseurSyntaxique = buildAnalyseurExpression();
		Expression expression = analyseurSyntaxique.analyse(listeLexeme);
		System.out.print(expression.evaluer(new Environnement()));
	}

	public static AnalyseurSyntaxique buildAnalyseurExpression() {
		/* S -> E$
		 * E -> TT'
		 * T -> FF'
		 * T' -> +E
		 * T' -> -E
		 * T' -> epsilon
		 * F -> Nombre
		 * F' -> *T
		 * F' -> /T
		 * F' -> epsilon
		 */
		Transition error = Transition.create(Action.ERROR);
		Etat etatFinal = Etat.create(error, "S -> E$.");
		Etat etatPresqueFinal = Etat.create(error,"S -> E.$"); 
		Etat etatInitial = Etat.create(etatPresqueFinal, error, "S -> .E$");
		etatPresqueFinal.addTransition(
				Lexeme.create(Type.FIN),
				Transition.create(Action.SHIFT, etatFinal));

		Transition reduceFusion = Transition.create(Fusion.FUSION);
		Etat etat1 = Etat.create(reduceFusion, "E -> TT'.");
		Etat etat2 = Etat.create(etat1, error, "E -> T.T'");
		Etat etat3 = Etat.create(etat2, error, "E -> .TT'"); 

		Etat etat4 = Etat.create(reduceFusion, "T -> FF'.");
		Etat etat5 = Etat.create(etat4, error, "T -> F.F'");
		Etat etat6 = Etat.create(etat5, error, "T -> .FF'");

		Etat etat7 = Etat.create(Transition.create(Operation.ADDITION), "T' -> +E.");
		Etat etat8 = Etat.create(etat7, error, "T' -> +.E");
		Etat etat9 = Etat.create(error, "T' -> .+E");
		etat9.addTransition(
				Lexeme.create(Type.OPERATEUR, "+"),
				Transition.create(Action.SHIFT, etat8));

		Etat etat10 = Etat.create(Transition.create(Operation.SOUSTRACTION), "T' -> -E.");
		Etat etat11 = Etat.create(etat10, error, "T' -> -.E");
		Etat etat12 = Etat.create(error, "T' -> .-E");
		etat12.addTransition(
				Lexeme.create(Type.OPERATEUR, "-"),
				Transition.create(Action.SHIFT, etat11));

		Etat etat13 = Etat.create(Transition.create(Fusion.NEUTRE), "F -> Nombre.");
		Etat etat14 = Etat.create(error, "F -> .Nombre");
		etat14.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.SHIFT, etat13));

		Etat etat15 = Etat.create(Transition.create(Operation.MULTIPLICATION), "F' -> *T.");
		Etat etat16 = Etat.create(etat15, error, "F' -> *.T");
		Etat etat17 = Etat.create(error, "F' -> .*T"); 
		etat17.addTransition(
				Lexeme.create(Type.OPERATEUR, "*"),
				Transition.create(Action.SHIFT, etat16));

		Etat etat18 = Etat.create(Transition.create(Operation.DIVISION), "F' -> /T.");
		Etat etat19 = Etat.create(etat18, error, "F' -> /.T");
		Etat etat20 = Etat.create(error, "F' -> ./T");
		etat20.addTransition(
				Lexeme.create(Type.OPERATEUR, "/"),
				Transition.create(Action.SHIFT, etat19));
		
		etatInitial.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat3));
		etat2.addTransition(
				Lexeme.create(Type.FIN),
				Transition.create(Action.SHIFT_BLANK, etat1));
		etat2.addTransition(
				Lexeme.create(Type.OPERATEUR, "+"),
				Transition.create(Action.EXPAND, etat9));
		etat2.addTransition(
				Lexeme.create(Type.OPERATEUR, "-"),
				Transition.create(Action.EXPAND, etat12));
		etat3.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat6));
		etat5.addTransition(
				Lexeme.create(Type.FIN),
				Transition.create(Action.SHIFT_BLANK, etat4));
		etat5.addTransition(
				Lexeme.create(Type.OPERATEUR, "+"),
				Transition.create(Action.SHIFT_BLANK, etat4));
		etat5.addTransition(
				Lexeme.create(Type.OPERATEUR, "-"),
				Transition.create(Action.SHIFT_BLANK, etat4));
		etat5.addTransition(
				Lexeme.create(Type.OPERATEUR, "*"),
				Transition.create(Action.EXPAND, etat17));
		etat5.addTransition(
				Lexeme.create(Type.OPERATEUR, "/"),
				Transition.create(Action.EXPAND, etat20));
		etat6.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat14));
		etat8.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat3));
		etat11.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat3));
		etat16.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat6));
		etat19.addTransition(
				Lexeme.create(Type.NOMBRE),
				Transition.create(Action.EXPAND, etat6));
		
		AnalyseurSyntaxique analyseurSyntaxique = new AnalyseurSyntaxique(etatInitial);
		return analyseurSyntaxique;
	}

}
