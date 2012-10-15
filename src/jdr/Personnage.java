package jdr;

import java.util.ArrayList;
import java.util.List;

public class Personnage {

	private String nom;
	private Race race;
	private Classe classe;
	private int niveau;

	private int demiNiveau;

	// Ability score
	private int force;
	private int constitution;
	private int dexterite;
	private int intelligence;
	private int sagesse;
	private int charisme;

	private int forceMod;
	private int constitutionMod;
	private int dexteriteMod;
	private int intelligenceMod;
	private int sagesseMod;
	private int charismeMod;

	private List<Feature> features;

	private Armor armor;
	private Shield shield;
	private Weapon weapon;
	private Weapon leftWeapon;

	// defenses
	private int ca;
	private int vigueur;
	private int reflexe;
	private int volonte;

	private int initiative;

	private int maxHP;
	private int surgeValue;
	private int surgeNumber;

	// skills
	private int acrobatics;
	private int arcana;
	private int athletics;
	private int bluff;
	private int diplomacy;
	private int dungeoneering;
	private int endurance;
	private int heal;
	private int history;
	private int insight;
	private int intimidate;
	private int nature;
	private int perception;
	private int religion;
	private int stealth;
	private int streetwise;
	private int thievery;

	private int skillNumber;

	private int speed;

	private int attaque;
	private String degats;
	private int attaqueGauche;
	private String degatsGauche;

	public Personnage(String nom, int niveau, int force, int constitution,
			int dexterite, int intelligence, int sagesse, int charisme, String nomClasse,
			String nomOption, String nomRace, String nomArmure, String nomArme,
			String nomMainGauche, String nomCarac, String nomArmeMagique,
			boolean acrobatics, boolean arcana, boolean athletics, boolean bluff,
			boolean diplomacy, boolean dungeoneering, boolean endurance, boolean heal,
			boolean history, boolean insight, boolean intimidate, boolean nature,
			boolean perception, boolean religion, boolean stealth, boolean streetwise,
			boolean thievery) {

		this.nom = nom;
		this.niveau = niveau;
		this.force = force;
		this.constitution = constitution;
		this.dexterite = dexterite;
		this.intelligence = intelligence;
		this.sagesse = sagesse;
		this.charisme = charisme;

		classe = Classe.getClasseByName(nomClasse);
		race = Race.getRaceByName(nomRace);
		
		int[] abilityBonus = race.getAbilityBonus();
		this.force += abilityBonus[0];
		this.constitution += abilityBonus[1];
		this.dexterite += abilityBonus[2];
		this.intelligence += abilityBonus[3];
		this.sagesse += abilityBonus[4];
		this.charisme += abilityBonus[5];

		features = new ArrayList<Feature>();

		if (classe instanceof DemiClasse) {
			classe = new HybridClasse((DemiClasse) classe,
					(DemiClasse) Classe.getClasseByName(nomOption));
		} else {
			ClasseOption classeOption = ClasseOption.getClasseOption(nomOption);
			features.addAll(classeOption.getFeatures());
		}

		features.addAll(classe.getClassFeatures());
		features.addAll(race.getRaceFeatures());
		
		armor = Armor.getArmorByName(nomArmure);
		shield = Shield.getShieldByName(nomMainGauche);
		if (shield == null) {
			shield = Shield.NONE;
		}
		weapon = Weapon.getWeaponByName(nomArme);
		leftWeapon = Weapon.getWeaponByName(nomMainGauche);
		if (leftWeapon == null) {
			leftWeapon = Weapon.UNARMED_ATTACK;
		}

		demiNiveau = niveau / 2;

		forceMod = getMod(this.force);
		constitutionMod = getMod(this.constitution);
		dexteriteMod = getMod(this.dexterite);
		intelligenceMod = getMod(this.intelligence);
		sagesseMod = getMod(this.sagesse);
		charismeMod = getMod(this.charisme);

		ca = 10 + demiNiveau;
		if (!armor.getArmorType().isHeavy()) {	
			ca += Math.max(dexteriteMod, intelligenceMod);
		}
		ca += armor.getArmorBonus();
		ca += shield.getShieldBonus();
		for(Feature feature : features) {
			ca += feature.getCABonus(this);
		}

		vigueur = 10 + demiNiveau + Math.max(forceMod, constitutionMod);
		for(Feature feature : features) {
			vigueur += feature.getVigueurBonus(this);
		}
		vigueur += classe.getBonusToDefense()[0];

		reflexe = 10 + demiNiveau+ Math.max(dexteriteMod, intelligenceMod);
		reflexe += shield.getShieldBonus();
		for(Feature feature : features) {
			reflexe += feature.getReflexeBonus(this);
		}
		reflexe += classe.getBonusToDefense()[1];

		
		volonte = 10 + demiNiveau + Math.max(sagesseMod, charismeMod);
		for(Feature feature : features) {
			volonte += feature.getVolonteBonus(this);
		}
		volonte += classe.getBonusToDefense()[2];

		initiative = demiNiveau + dexteriteMod;
		for(Feature feature : features) {
			initiative += feature.getInitiativeBonus(this);
		}

		maxHP = classe.getHitPointAtFirstLevel() + constitution
				+ (niveau - 1) * classe.getHitPointPerLevel();
		for(Feature feature : features) {
			maxHP += feature.getMaxHPBonus(this);
		}

		surgeValue = maxHP / 4;
		for(Feature feature : features) {
			surgeValue += feature.getSurgeValueBonus(this);
		}
		surgeNumber = classe.getHealingSurgesPerDay() + constitutionMod;
		for(Feature feature : features) {
			surgeNumber += feature.getSurgeNumberBonus(this);
		}

		int armorPenalty = armor.getArmorType().getCheck()
				+ shield.getArmorType().getCheck();

		skillNumber = classe.getSkillNumber() + classe.getTrainedSkills().size();

		int[] skillBonus = race.getSkillBonus();
		this.acrobatics = skillBonus[0];
		if (acrobatics) {
			this.acrobatics += 5;
			skillNumber -= 1;
		}
		this.acrobatics += getDexteriteModDemi() + armorPenalty;
		this.arcana = skillBonus[1];
		if (arcana) {
			this.arcana += 5;
			skillNumber -= 1;
		}
		this.arcana += getIntelligenceModDemi();
		this.athletics = skillBonus[2];
		if (athletics) {
			this.athletics += 5;
			skillNumber -= 1;
		}
		this.athletics += getForceModDemi() + armorPenalty;
		this.bluff = skillBonus[3];
		if (bluff) {
			this.bluff += 5;
			skillNumber -= 1;
		}
		this.bluff += getCharismeModDemi();
		this.diplomacy = skillBonus[4];
		if (diplomacy) {
			this.diplomacy += 5;
			skillNumber -= 1;
		}
		this.diplomacy += getCharismeModDemi();
		this.dungeoneering = skillBonus[5];
		if (dungeoneering) {
			this.dungeoneering += 5;
			skillNumber -= 1;
		}
		this.dungeoneering += getSagesseModDemi();
		this.endurance = skillBonus[6];
		if (endurance) {
			this.endurance += 5;
			skillNumber -= 1;
		}
		this.endurance += getConstitutionModDemi() + armorPenalty;
		this.heal = skillBonus[7];
		if (heal) {
			this.heal += 5;
			skillNumber -= 1;
		}
		this.heal += getSagesseModDemi();
		this.history = skillBonus[8];
		if (history) {
			this.history += 5;
			skillNumber -= 1;
		}
		this.history += getIntelligenceModDemi();
		this.insight = skillBonus[9];
		if (insight) {
			this.insight += 5;
			skillNumber -= 1;
		}
		this.insight += getSagesseModDemi();
		this.intimidate = skillBonus[10];
		if (intimidate) {
			this.intimidate += 5;
			skillNumber -= 1;
		}
		this.intimidate += getCharismeModDemi();
		this.nature = skillBonus[11];
		if (nature) {
			this.nature += 5;
			skillNumber -= 1;
		}
		this.nature += getSagesseModDemi();
		this.perception = skillBonus[12];
		if (perception) {
			this.perception += 5;
			skillNumber -= 1;
		}
		this.perception += getSagesseModDemi();
		this.religion = skillBonus[13];
		if (religion) {
			this.religion += 5;
			skillNumber -= 1;
		}
		this.religion += getIntelligenceModDemi();
		this.stealth = skillBonus[14];
		if (stealth) {
			this.stealth += 5;
			skillNumber -= 1;
		}
		this.stealth += getDexteriteModDemi() + armorPenalty;
		this.streetwise = skillBonus[15];
		if (streetwise) {
			this.streetwise += 5;
			skillNumber -= 1;
		}
		this.streetwise += getCharismeModDemi();
		this.thievery = skillBonus[16];
		if (thievery) {
			this.thievery += 5;
			skillNumber -= 1;
		}
		this.thievery += getDexteriteModDemi() + armorPenalty;

		speed = race.getSpeed() + armor.getArmorType().getSpeed();
		for(Feature feature : features) {
			speed += feature.getSpeedBonus(this);
		}

		Ability ability = Ability.getAbilityByName(nomCarac);
		int abilityMod = ability.getAbility(
				this.forceMod, this.constitutionMod, this.dexteriteMod,
				this.intelligenceMod, this.sagesseMod, this.charismeMod);

		int itemBonus = MagicalWeapon.getWeaponByName(nomArmeMagique).getBonus();

		attaque = demiNiveau + abilityMod + weapon.getProficiency() + itemBonus;
		int degatValue = abilityMod + itemBonus;
		int diceSize = weapon.getDiceSize();
		for (Feature feature : features) {
			attaque += feature.getAttaqueBonus(this);
			degatValue += feature.getDegatBonus(this);
			diceSize += feature.getDiceSizeBonus(this);
		}

		degats = new StringBuilder().append(weapon.getDiceNumber()).append("d")
				.append(diceSize).append(" + ").append(degatValue).toString();
		
		attaqueGauche = demiNiveau + abilityMod + leftWeapon.getProficiency() + itemBonus;
		degatValue = abilityMod + itemBonus;
		diceSize = leftWeapon.getDiceSize();
		for (Feature feature : features) {
			attaqueGauche += feature.getAttaqueGaucheBonus(this);
			degatValue += feature.getDegatGaucheBonus(this);
			diceSize += feature.getDiceSizeGaucheBonus(this);
		}

		degatsGauche = new StringBuilder().append(leftWeapon.getDiceNumber()).append("d")
				.append(diceSize).append(" + ").append(degatValue).toString();
	}

	public static int getMod(int score) {
		return score / 2 - 5;
	}

	public String getNom() {
		return nom;
	}

	public Race getRace() {
		return race;
	}

	public Classe getClasse() {
		return classe;
	}

	public int getNiveau() {
		return niveau;
	}

	public int getDemiNiveau() {
		return demiNiveau;
	}

	public int getCA() {
		return ca;
	}

	public int getForce() {
		return force;
	}

	public int getForceMod() {
		return forceMod;
	}

	public int getForceModDemi() {
		return forceMod + demiNiveau;
	}

	public int getConstitution() {
		return constitution;
	}

	public int getConstitutionMod() {
		return constitutionMod;
	}

	public int getConstitutionModDemi() {
		return constitutionMod + demiNiveau;
	}
	
	public int getVigueur() {
		return vigueur;
	}

	public int getDexterite() {
		return dexterite;
	}

	public int getDexteriteMod() {
		return dexteriteMod;
	}

	public int getDexteriteModDemi() {
		return dexteriteMod + demiNiveau;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getIntelligenceMod() {
		return intelligenceMod;
	}

	public int getIntelligenceModDemi() {
		return intelligenceMod + demiNiveau;
	}

	public int getReflexe() {
		return reflexe;
	}

	public int getSagesse() {
		return sagesse;
	}

	public int getSagesseMod() {
		return sagesseMod;
	}

	public int getSagesseModDemi() {
		return sagesseMod + demiNiveau;
	}

	public int getCharisme() {
		return charisme;
	}

	public int getCharismeMod() {
		return charismeMod;
	}

	public int getCharismeModDemi() {
		return charismeMod + demiNiveau;
	}

	public int getVolonte() {
		return volonte;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getBloodiedHP() {
		return maxHP / 2; 
	}
	
	public int getSurgeValue() { 
		return surgeValue;
	}

	public int getSurgesByDay() {
		return surgeNumber;
	}

	public int getInitiative() {
		return initiative;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public Armor getArmor() {
		return armor;
	}

	public Shield getShield() {
		return shield;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Weapon getLeftWeapon() {
		return leftWeapon;
	}

	public int getAcrobatics() {
		return acrobatics;
	}

	public int getArcana() {
		return arcana;
	}

	public int getAthletics() {
		return athletics;
	}

	public int getBluff() {
		return bluff;
	}

	public int getDiplomacy() {
		return diplomacy;
	}

	public int getDungeoneering() {
		return dungeoneering;
	}

	public int getEndurance() {
		return endurance;
	}

	public int getHeal() {
		return heal;
	}

	public int getHistory() {
		return history;
	}

	public int getInsight() {
		return insight;
	}

	public int getIntimidate() {
		return intimidate;
	}

	public int getNature() {
		return nature;
	}

	public int getPerception() {
		return perception;
	}

	public int getReligion() {
		return religion;
	}

	public int getStealth() {
		return stealth;
	}

	public int getStreetwise() {
		return streetwise;
	}

	public int getThievery() {
		return thievery;
	}

	public int getSkillNumber() {
		return skillNumber;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPassiveInsight() {
		return 10 + insight;
	}

	public int getPassivePerception() {
		return 10 + perception;
	}

	public List<Language> getLanguages() {
		return race.getLanguages();
	}

	public int getAttaque() {
		return attaque;
	}

	public String getDegats() {
		return degats;
	}

	public int getAttaqueGauche() {
		return attaqueGauche;
	}

	public String getDegatsGauche() {
		return degatsGauche;
	}

	public String getSize() {
		return race.getSize().getDisplay();
	}

	public String getPoids() {
		return race.getAverageWeight();
	}

	public String getTaille() {
		return race.getAverageHeight();
	}

}
