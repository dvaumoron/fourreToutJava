package org.codi.interpreter;

public class Variable implements Expression {

	private final String nom;

	public Variable(String nom) {
		this.nom = nom;
	}

	@Override
	public Object evaluer(Environnement environnement) {
		return environnement.get(nom);
	}

	public String getNom() {
		return nom;
	}

}
