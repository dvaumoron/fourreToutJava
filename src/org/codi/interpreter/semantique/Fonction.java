package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Variable;

public enum Fonction implements Semantique {

	FONCTION {
		@Override
		public Object evaluer(Environnement environnement,
				List<Expression> expressions) {
			return new org.codi.interpreter.objet.Fonction(
					(List<Variable>) expressions.get(0).evaluer(environnement),
					(List<Expression>) expressions.get(1).evaluer(environnement));
		}
	},

	APPEL_FONCTION {
		@Override
		public Object evaluer(Environnement environnement,
				List<Expression> expressions) {
			return ((org.codi.interpreter.objet.Fonction) expressions.get(0).evaluer(environnement)).appliquer(
					environnement, expressions.subList(1, expressions.size()));
		}
	},
	
	LISTE {
		@Override
		public Object evaluer(Environnement environnement,
				List<Expression> expressions) {
			return expressions;
		}
		
	};
}
