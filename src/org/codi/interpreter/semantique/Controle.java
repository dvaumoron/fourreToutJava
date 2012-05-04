package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.objet.Booleen;

public enum Controle implements Semantique {

	IF {
		@Override
		public Object evaluer(
				Environnement environnement, List<Expression> expressions) {
			Object valeur = null;
			if (((Booleen) expressions.get(0).evaluer(environnement)).isValeur()) {
				valeur = expressions.get(1).evaluer(environnement);
			} else if (expressions.size() > 2) {
				valeur = expressions.get(2).evaluer(environnement);
			}
			return valeur;
		}
	},

	WHILE {
		@Override
		public Object evaluer(
				Environnement environnement, List<Expression> expressions) {
			Object valeur = null;
			Expression expression0 = expressions.get(0);
			while (((Booleen) expression0.evaluer(environnement)).isValeur()) {
				valeur = expressions.get(1).evaluer(environnement);
			}
			return valeur;
		}
	};

}
