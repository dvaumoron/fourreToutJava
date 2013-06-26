package org.codi.interpreter.objet;


public class Booleen implements Objet {

	private final boolean valeur;

	public Booleen(boolean valeur) {
		this.valeur = valeur;
	}

	public boolean isValeur() {
		return valeur;
	}

	@Override
	public Classe getClasse() {
		return Classe.BOOLEEN;
	}

}
