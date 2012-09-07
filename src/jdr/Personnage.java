package jdr;

import java.util.List;

public class Personnage {

	private String nom;
	private Race race;
	private Classe classe;
	private int niveau;

	// Ability score
	private int force;
	private int constitution;
	private int dexterite;
	private int intelligence;
	private int sagesse;
	private int charisme;

	private List<Feature> features;
	private List<Item> itemList;

	public String getNom() {
		return nom;
	}

	public String getRace() {
		return race.getNom();
	}

	public String getClasse() {
		return classe.getNom();
	}
	
	public int getNiveau() {
		return niveau;
	}

	public int getDemiNiveau() {
		return niveau / 2;
	}

	public int getCA() {
		int ca = 10 + getDemiNiveau();
		boolean lightArmor = true;
		for(Item item : itemList) {
			if (item.isHeavy()) {
				lightArmor = false;
				break;
			}
		}
		if (lightArmor) {
			ca += Math.max(getDexteriteMod(), getIntelligenceMod());
		}
		for(Item item : itemList) {
			ca += item.getArmorBonus();
		}
		for(Feature feature : features) {
			ca += feature.getCABonus(this);
		}
		return ca;
	}

	public int getForce() {
		return force;
	}

	public int getForceMod() {
		return getMod(force);
	}
	
	public int getConstitution() {
		return constitution;
	}

	public int getConstitutionMod() {
		return getMod(constitution);
	}

	public int getVigueur() {
		int vigueur = 10 + getDemiNiveau()
				+ Math.max(getForceMod(), getConstitutionMod());
		for(Feature feature : features) {
			vigueur += feature.getVigueurBonus(this);
		}
		vigueur += classe.getBonusToDefense()[0];
		return vigueur;
	}

	public int getDexterite() {
		return dexterite;
	}

	public int getDexteriteMod() {
		return getMod(dexterite);
	}
	
	public int getIntelligence() {
		return intelligence;
	}

	public int getIntelligenceMod() {
		return getMod(intelligence);
	}

	public int getReflexe() {
		int reflexe = 10 + getDemiNiveau()
				+ Math.max(getDexteriteMod(), getIntelligenceMod());
		for(Item item : itemList) {
			reflexe += item.getReflexeBonus();
		}
		for(Feature feature : features) {
			reflexe += feature.getReflexeBonus(this);
		}
		reflexe += classe.getBonusToDefense()[1];
		return reflexe;
	}

	public int getSagesse() {
		return sagesse;
	}

	public int getSagesseMod() {
		return getMod(sagesse);
	}
	
	public int getCharisme() {
		return charisme;
	}

	public int getCharismeMod() {
		return getMod(charisme);
	}

	public int getVolonte() {
		int volonte = 10 + getDemiNiveau()
				+ Math.max(getSagesseMod(), getCharismeMod());
		for(Feature feature : features) {
			volonte += feature.getVolonteBonus(this);
		}
		volonte += classe.getBonusToDefense()[2];
		return volonte;
	}

	public int getMaxHP() {
		int maxHP = classe.getHitPointAtFirstLevel() + getConstitution()
				+ (getNiveau() - 1) * classe.getHitPointPerLevel();
		for(Feature feature : features) {
			maxHP += feature.getMaxHPBonus(this);
		}
		return maxHP;
	}

	public int getBloodiedHP() {
		return getMaxHP() / 2; 
	}
	
	public int getSurgeValue() { 
		int surgeValue = getMaxHP() / 4;
		for(Feature feature : features) {
			surgeValue += feature.getSurgeValueBonus(this);
		}
		return surgeValue;
	}

	private static int getMod(int score) {
		return score / 2 - 5;
	}
}
