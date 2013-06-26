package org.codi.interpreter.semantique;

import java.util.ArrayList;
import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Noeud;

public enum Fusion implements Semantique {

	FUSION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			Object value;
			if (expressions.size() == 2) {
				List<Expression> newExpressions = new ArrayList<Expression>();
				newExpressions.add(expressions.get(0));
				Noeud noeud = (Noeud) expressions.get(1);
				newExpressions.addAll(noeud.getSousNoeud());
				value = noeud.getSemantique().evaluer(environnement, newExpressions);
			} else {
				value = expressions.get(0).evaluer(environnement);
			}
			return value;
		}
	},
	NEUTRE {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return expressions.get(0).evaluer(environnement);
		}
		
	};

}
