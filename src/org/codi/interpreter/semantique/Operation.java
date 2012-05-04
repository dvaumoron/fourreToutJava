package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.objet.Nombre;

public enum Operation implements Semantique {

	ADDITION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return getNombre(environnement, expressions.get(0)).plus(
					getNombre(environnement, expressions.get(1)));
		}
	},

	SOUSTRACTION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return getNombre(environnement, expressions.get(0)).moins(
					getNombre(environnement, expressions.get(1)));
		}
	},


	MULTIPLICATION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return getNombre(environnement, expressions.get(0)).fois(
					getNombre(environnement, expressions.get(1)));
		}
	},

	DIVISION {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return getNombre(environnement, expressions.get(0)).sur(
					getNombre(environnement, expressions.get(1)));
		}
	},

	EGALITE {
		@Override
		public Object evaluer(Environnement environnement, List<Expression> expressions) {
			return getNombre(environnement, expressions.get(0)).egal(
					getNombre(environnement, expressions.get(1)));
		}
	};

	private static Nombre getNombre(Environnement environnement, Expression expression) {
		return (Nombre) expression.evaluer(environnement);
	}

}
