package jdr;

public enum Script {

	NONE(""),
	COMMON("Commune"),
	RELLANIC("Rellanique"),
	LOKHARIC("Lokharique"),
	DAVEK("Davek"),
	BARAZHAD("Barazhad"),
	SUPERNAL("Universelle");

	private String nom;

	private Script(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
