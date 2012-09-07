package jdr;

import java.util.Arrays;
import java.util.List;

public class StandardClasse extends Classe {

	public static final StandardClasse CLERIC = new StandardClasse(
			"Prêtre", Role.MENEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Implement.HOLY_SYMBOL, new int[]{0, 0, 2},
			12, 5, 7, Arrays.asList(Skill.RELIGION),
			Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.RELIGION),
			Arrays.asList(Feature.CHANEL_DIVINITY, Feature.HEALERS_LORE,
					Feature.HEALING_WORD, Feature.RITUAL_CASTING));

	private String nom;
	private Role role;
	private Source source;
	private List<ArmorType> armorProficiencies;
	private List<WeaponType> weaponProficiencies;
	private Implement implement;
	private int[] bonusToDefense;
	private int hitPointAtFirstLevel;
	private int hitPointPerLevel;
	private int healingSurgesPerDay;
	private List<Skill> trainedSkills;
	private List<Skill> classSkills;
	private List<Feature> classFeatures;

	private StandardClasse(String nom, Role role, Source source,
			List<ArmorType> armorProficiencies,
			List<WeaponType> weaponProficiencies,
			Implement implement, int[] bonusToDefense,
			int hitPointAtFirstLevel, int hitPointPerLevel,
			int healingSurgesPerDay, List<Skill> trainedSkills,
			List<Skill> classSkills, List<Feature> classFeatures) {
		this.nom = nom;
		this.role = role;
		this.source = source;
		this.armorProficiencies = armorProficiencies;
		this.weaponProficiencies = weaponProficiencies;
		this.implement = implement;
		this.bonusToDefense = bonusToDefense;
		this.hitPointAtFirstLevel = hitPointAtFirstLevel;
		this.hitPointPerLevel = hitPointPerLevel;
		this.healingSurgesPerDay = healingSurgesPerDay;
		this.trainedSkills = trainedSkills;
		this.classSkills = classSkills;
		this.classFeatures = classFeatures;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public Source getSource() {
		return source;
	}

	@Override
	public List<ArmorType> getArmorProficiencies() {
		return armorProficiencies;
	}

	@Override
	public List<WeaponType> getWeaponProficiencies() {
		return weaponProficiencies;
	}

	@Override
	public Implement getImplement() {
		return implement;
	}

	@Override
	public int[] getBonusToDefense() {
		return bonusToDefense;
	}

	@Override
	public int getHitPointAtFirstLevel() {
		return hitPointAtFirstLevel;
	}

	@Override
	public int getHitPointPerLevel() {
		return hitPointPerLevel;
	}

	@Override
	public int getHealingSurgesPerDay() {
		return healingSurgesPerDay;
	}

	@Override
	public List<Skill> getTrainedSkills() {
		return trainedSkills;
	}

	@Override
	public List<Skill> getClassSkills() {
		return classSkills;
	}

	@Override
	public List<Feature> getClassFeatures() {
		return classFeatures;
	}

}
