package org.codi.interpreter;

public class Constante implements Expression {

	private final Object valeur;

	public Constante(Object valeur) {
		this.valeur = valeur;
	}

	@Override
	public Object evaluer(Environnement environnement) {
		return valeur;
	}

}
