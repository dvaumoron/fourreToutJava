package jdr.familier;

public enum Effet {

	AUCUN(Cible.AUCUNE);

	private Cible cible;

	private Effet(Cible cible) {
		this.cible = cible;
	}

}
