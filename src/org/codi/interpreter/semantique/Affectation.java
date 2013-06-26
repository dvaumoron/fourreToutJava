package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Noeud;
import org.codi.interpreter.Variable;
import org.codi.interpreter.objet.Objet;

public enum Affectation implements Semantique {

	AFFECTATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Variable variable = (Variable) expressions.get(0);
			String nom = variable.getNom();
			if (!environnement.declare(nom)) {
				throw new RuntimeException("Variable non déclaré");
			}
			Objet valeur = (Objet) expressions.get(1).evaluer(environnement);			
			if (variable.getClasse().peutContenir(valeur.getClasse())) {
				environnement.set(nom, valeur);
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
			Objet valeur;
			if (expressions.size() > 1) {
				valeur = (Objet) expressions.get(1).evaluer(environnement);
			} else {
				valeur = null;
			}
			if (variable.getClasse().peutContenir(valeur.getClasse())) {
				environnement.declare(variable.getNom(), valeur);
			} else {
				throw new ClassCastException();
			}
			return valeur;
		}
	};

}
