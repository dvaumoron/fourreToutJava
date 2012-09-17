package jdr;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Ability {

	STRENGTH("Force") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return strength;
		}
	},
	CONSTITUTION("Constitution") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return constitution;
		}
	},
	DEXTERITY("Dextérité") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return  dexterity;
		}
	},
	INTELLIGENCE("Intelligence") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return intelligence;
		}
	},
	WISDOM("Sagesse") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return wisdom;
		}
	},
	CHARISMA("Charisme") {
		public int getAbility(
				int strength, int constitution, int dexterity, int intelligence, int wisdom,
				int charisma) {
			return charisma;
		}
	};

	private static final Map<String, Ability> abilityByName = new LinkedHashMap<String, Ability>();

	static {
		for(Ability ability : Ability.values()) {
			abilityByName.put(ability.getNom(), ability);
		}
	}

	public static Collection<String> getAbilityNameList() {
		return abilityByName.keySet();
	}

	public static Ability getAbilityByName(String nomCarac) {
		return abilityByName.get(nomCarac);
	}

	private String nom;

	private Ability(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public abstract int getAbility(
			int strength, int constitution, int dexterity, int intelligence, int wisdom,
			int charisma);

}
