package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Variable;
import org.codi.interpreter.objet.Objet;

public enum Affectation implements Semantique {

	AFFECTATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Variable variable = (Variable) expressions.get(0);
			Objet valeur = (Objet) expressions.get(1).evaluer(environnement);
			if (variable.getClasse().peutContenir(valeur.getClasse())) {
				environnement.set(variable.getNom(), valeur);
			} else {
				throw new ClassCastException();
			}
			return valeur;
		}
	},

	DECLARATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Variable variable = (Variable) expressions.get(0);
			Objet valeur = (Objet) expressions.get(1).evaluer(environnement);
			if (variable.getClasse().peutContenir(valeur.getClasse())) {
				environnement.declare(variable.getNom(), valeur);
			} else {
				throw new ClassCastException();
			}
			return valeur;
		}
	};

}
