package jdr;

public enum Feature {

	// cleric feature
	CHANEL_DIVINITY,
	HEALERS_LORE,
	HEALING_WORD,
	RITUAL_CASTING,

	// dragonborn feature
	DRAGONBORN_FURY,
	DRACONIC_HERITAGE {
		public int getSurgeValueBonus(Personnage personnage) {
			return personnage.getConstitutionMod();
		}
	},
	DRAGON_BREATH,

	// feat
	TOUGHTNESS {
		public int getMaxHPBonus(Personnage personnage) {
			return getTiers(personnage.getNiveau()) * 5;
		}
	};

	public int getSurgeValueBonus(Personnage personnage) {
		return 0;
	}

	public int getMaxHPBonus(Personnage personnage) {
		return 0;
	}

	public int getCABonus(Personnage personnage) {
		return 0;
	}

	public int getVigueurBonus(Personnage personnage) {
		return 0;
	}

	public int getReflexeBonus(Personnage personnage) {
		return 0;
	}

	public int getVolonteBonus(Personnage personnage) {
		return 0;
	}

	public static int getTiers(int niveau) {
		return (niveau + 9) / 10;
	}
}
