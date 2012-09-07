package org.codi.interpreter;

import org.codi.interpreter.objet.Classe;

public class Variable implements Expression {

	private final String nom;
	private final Classe classe;

	public Variable(String nom, Classe classe) {
		this.nom = nom;
		this.classe = classe;
	}

	@Override
	public Object evaluer(Environnement environnement) {
		return environnement.get(nom);
	}

	public String getNom() {
		return nom;
	}

	public Classe getClasse() {
		return classe;
	}

}
