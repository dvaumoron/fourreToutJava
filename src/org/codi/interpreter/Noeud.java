package org.codi.interpreter;

import java.util.List;

import org.codi.interpreter.semantique.Semantique;

public class Noeud implements Expression {

	private final Semantique semantique;
	private final List<Expression> sousNoeud;

	public Noeud(Semantique semantique, List<Expression> list) {
		this.semantique = semantique;
		this.sousNoeud = list;
	}

	@Override
	public Object evaluer(Environnement environnement) {
		return semantique.evaluer(environnement, sousNoeud);
	}

}
