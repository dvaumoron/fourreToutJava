package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Variable;

public enum Affectation implements Semantique {

	AFFECTATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Object valeur = expressions.get(1).evaluer(environnement);
			environnement.set(
					((Variable) expressions.get(0)).getNom(), valeur);
			return valeur;
		}
	},

	DECLARATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Object valeur = expressions.get(1).evaluer(environnement);
			environnement.declare(
					((Variable) expressions.get(0)).getNom(), valeur);
			return valeur;
		}
	};

}
