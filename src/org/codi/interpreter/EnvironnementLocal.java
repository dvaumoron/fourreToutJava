package org.codi.interpreter;

public class EnvironnementLocal extends Environnement {

	private final Environnement surEnvironnement;

	public EnvironnementLocal(Environnement surEnvironnement) {
		this.surEnvironnement = surEnvironnement;
	}

	@Override
	public Object get(String nom) {
		Object valeur = super.get(nom);
		if (valeur == null) {
			valeur = surEnvironnement.get(nom);
		}
		return valeur;
	}

	@Override
	public void set(String nom, Object valeur) {
		if (super.declare(nom)) {
			super.set(nom, valeur);
		} else {
			surEnvironnement.set(nom, valeur);
		}
	}

	@Override
	public boolean declare(String nom) {
		return super.declare(nom) || surEnvironnement.declare(nom);
	}

}
