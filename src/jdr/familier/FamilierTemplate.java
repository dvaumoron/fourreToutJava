package jdr.familier;

public enum FamilierTemplate {

	ELEMENTAIRE_DE_FEU("Elémentaire de feu", Type.FEU, 100, 10, 10, 10, Attaque.TRAIT_DE_FEU, Attaque.FLAMME_DE_SOINS, Attaque.BOULE_DE_FEU);

	private String nom;
	private Type type;
	private int vieNiveauUn;
	private int vieParNiveau;
	private int puissance;
	private int vitesse;
	private Attaque attaquePrincipal;
	private Attaque attaqueSecondaire;
	private Attaque attaqueTertaire;

	private FamilierTemplate(String nom, Type type, int vieNiveauUn,
			int vieParNiveau, int puissance, int vitesse,
			Attaque attaquePrincipal, Attaque attaqueSecondaire,
			Attaque attaqueTertaire) {
		this.nom = nom;
		this.type = type;
		this.vieNiveauUn = vieNiveauUn;
		this.vieParNiveau = vieParNiveau;
		this.puissance = puissance;
		this.vitesse = vitesse;
		this.attaquePrincipal = attaquePrincipal;
		this.attaqueSecondaire = attaqueSecondaire;
		this.attaqueTertaire = attaqueTertaire;
	}

	public String getNom() {
		return nom;
	}

	public Type getType() {
		return type;
	}

	public int getVieNiveauUn() {
		return vieNiveauUn;
	}

	public int getVieParNiveau() {
		return vieParNiveau;
	}

	public int getPuissance() {
		return puissance;
	}

	public int getVitesse() {
		return vitesse;
	}

	public Attaque getAttaquePrincipal() {
		return attaquePrincipal;
	}

	public Attaque getAttaqueSecondaire() {
		return attaqueSecondaire;
	}

	public Attaque getAttaqueTertaire() {
		return attaqueTertaire;
	}

}
