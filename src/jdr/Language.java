package jdr;

public enum Language {

	CHOICE("choix d'une autre", Script.NONE),

	COMMON("Commun", Script.COMMON),
	DEEP_SPEECH("Profond", Script.RELLANIC),
	DRACONIC("Draconique", Script.LOKHARIC),
	DWARVEN("Nain", Script.DAVEK),
	ELVEN("Elfique", Script.RELLANIC),
	GIANT("Géant", Script.DAVEK),
	GOBLIN("Gobelin", Script.COMMON),
	PRIMORDIAL("Originel", Script.BARAZHAD),
	SUPERNAL("Universel", Script.SUPERNAL),
	ABYSSAL("Abyssal", Script.BARAZHAD),

	// kalashtar
	TELEPATHY_5("Télépathie 5", Script.NONE);

	private String nom;
	private Script script;

	private Language(String nom, Script script) {
		this.nom = nom;
		this.script = script;
	}

	public String getNom() {
		return nom;
	}

	public Script getScript() {
		return script;
	}

}
