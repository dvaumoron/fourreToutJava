package jdr;

public enum Skill {

	ACROBATICS(true),
	ARCANA(false),
	ATHLETICS(true),
	BLUFF(false),
	DIPLOMACY(false),
	DUNGEONEERING(false),
	ENDURANCE(true),
	HEAL(false),
	HISTORY(false),
	INSIGHT(false),
	INTIMIDATE(false),
	NATURE(false),
	PERCEPTION(false),
	RELIGION(false),
	STEALTH(true),
	STREETWISE(false),
	THIEVERY(true);

	private boolean armorPenalty;

	private Skill(boolean armorPenalty) {
		this.armorPenalty = armorPenalty;
	}

	public boolean isArmorPenalty() {
		return armorPenalty;
	}

}
