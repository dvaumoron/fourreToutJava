package jdr.familier;

public enum Attaque {

	TRAIT_DE_FEU("Trait de feu", Type.FEU, Cible.ADVERSAIRE, 1, 0, Effet.AUCUN),
	FLAMME_DE_SOINS("Flamme de soins", Type.FEU, Cible.LANCEUR, 1, 3, Effet.AUCUN),
	BOULE_DE_FEU("Boule de feu",Type.FEU, Cible.EQUIPE_ADVERSE, 0.5, 3, Effet.AUCUN);
	
	private String nom;
	private Type type;
	private Cible cible;
	private double coeff;
	private int recharge;
	private Effet effet;

	private Attaque(String nom, Type type, Cible cible, double coeff,
			int recharge, Effet effet) {
		this.nom = nom;
		this.type = type;
		this.cible = cible;
		this.coeff = coeff;
		this.recharge = recharge;
		this.effet = effet;
	}

	public String getNom() {
		return nom;
	}

	public Type getType() {
		return type;
	}

	public Cible getCible() {
		return cible;
	}

	public double getCoeff() {
		return coeff;
	}

	public int getRecharge() {
		return recharge;
	}

	public Effet getEffet() {
		return effet;
	}

}
