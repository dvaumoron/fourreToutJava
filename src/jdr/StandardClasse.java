package jdr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StandardClasse extends Classe {

	public static final StandardClasse CLERIC = new StandardClasse(
			"Prêtre", Role.MENEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 0, 2},
			12, 5, 7, Arrays.asList(Skill.RELIGION),
			3, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.RELIGION),
			Arrays.asList(Feature.CHANEL_DIVINITY, Feature.HEALERS_LORE,
					Feature.HEALING_WORD, Feature.RITUAL_CASTING));

	public static final StandardClasse FIGHTER = new StandardClasse(
			"Guerrier", Role.PROTECTEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{2, 0, 0},
			15, 6, 9, Collections.<Skill>emptyList(),
			3, Arrays.asList(Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL, Skill.INTIMIDATE,
					Skill.STREETWISE),
			Arrays.asList(Feature.COMBAT_CHALLENGE, Feature.FIGHTER_WEAPON_TALENT));

	public static final StandardClasse PALADIN = new StandardClasse(
			"Paladin", Role.PROTECTEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.PLATE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{1, 1, 1},
			15, 6, 10, Arrays.asList(Skill.RELIGION),
			3, Arrays.asList(Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.CHANEL_DIVINITY, Feature.DIVINE_CHALLENGE));

	public static final StandardClasse RANGER_DUNGEONEERING = new StandardClasse(
			"Rôdeur(Exploration)", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 1, 0},
			12, 5, 6, Arrays.asList(Skill.DUNGEONEERING),
			4, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DUNGEONEERING,
					Skill.ENDURANCE, Skill.HEAL, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.FIGHTING_STYLE, Feature.HUNTERS_QUARRY));

	public static final StandardClasse RANGER_NATURE = new StandardClasse(
			"Rôdeur(Nature)", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 1, 0},
			12, 5, 6, Arrays.asList(Skill.NATURE),
			4, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DUNGEONEERING,
					Skill.ENDURANCE, Skill.HEAL, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.FIGHTING_STYLE, Feature.HUNTERS_QUARRY));

	public static final StandardClasse ROGUE = new StandardClasse(
			"Voleur", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.DAGGER,WeaponType.HAND_CROSSBOW, WeaponType.SHURIKEN,
					WeaponType.SLING, WeaponType.SHORT_SWORD),
			Collections.<Implement>emptyList(), new int[]{0, 2, 0},
			12, 5, 6, Arrays.asList(Skill.STEALTH, Skill.THIEVERY),
			4, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.BLUFF,
					Skill.DUNGEONEERING, Skill.INSIGHT, Skill.INTIMIDATE, Skill.PERCEPTION,
					Skill.STEALTH, Skill.STREETWISE, Skill.THIEVERY),
			Arrays.asList(Feature.FIRST_STRIKE, Feature.ROGUE_TACTICS, Feature.SNEAK_ATTACK));

	public static final StandardClasse WARLOCK = new StandardClasse(
			"Sorcier", Role.COGNEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{0, 1, 1},
			12, 5, 6, Collections.<Skill>emptyList(),
			4, Arrays.asList(Skill.ARCANA, Skill.BLUFF, Skill.HISTORY, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.RELIGION, Skill.STREETWISE, Skill.THIEVERY),
			Arrays.asList(Feature.ELDRITCH_BLAST, Feature.ELDRITCH_PACT,
							Feature.PRIME_SHOT, Feature.SHADOW_WALK, Feature.WARLOCKS_CURSE));

	public static final StandardClasse WARLORD = new StandardClasse(
			"Maître de guerre", Role.MENEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{1, 0, 1},
			12, 5, 7, Collections.<Skill>emptyList(),
			4, Arrays.asList(Skill.ATHLETICS, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INTIMIDATE),
			Arrays.asList(Feature.COMBAT_LEADER, Feature.COMMANDING_PRESENCE,
							Feature.INSPIRING_WORD));

	public static final StandardClasse WIZARD = new StandardClasse(
			"Magicien", Role.CONTROLLEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.DAGGER, WeaponType.QUARTERSTAFF),
			Arrays.asList(Implement.ORBS, Implement.STAFFS, Implement.WANDS), new int[]{0, 0, 2},
			10, 4, 6, Arrays.asList(Skill.ARCANA),
			3, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.DUNGEONEERING,
					Skill.HISTORY, Skill.INSIGHT, Skill.NATURE),
			Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY, Feature.CANTRIPS,
							Feature.RITUAL_CASTING, Feature.SPELLBOOK));

	public static final StandardClasse AVENGER = new StandardClasse(
			"Vengeur", Role.COGNEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{1, 1, 1},
			14, 6, 7, Arrays.asList(Skill.RELIGION),
			3, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.PERCEPTION, Skill.RELIGION,
					Skill.STEALTH, Skill.STREETWISE),
			Arrays.asList(Feature.ARMOR_OF_FAITH, Feature.AVENGERS_CENSURE,
							Feature.CHANEL_DIVINITY, Feature.OATH_OF_ENMMITY));

	public static final StandardClasse BARBARIAN = new StandardClasse(
			"Barbare", Role.COGNEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE),
			Collections.<Implement>emptyList(), new int[]{2, 0, 0},
			15, 6, 8, Collections.<Skill>emptyList(),
			3, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.BARBARIAN_AGILITY, Feature.FERAL_MIGHT,
							Feature.RAGE_STRIKE, Feature.RAMPAGE));

	public static final StandardClasse BARD = new StandardClasse(
			"Barde", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSWORD, WeaponType.SCIMITAR, WeaponType.SHORT_SWORD, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Arrays.asList(Implement.WANDS), new int[]{0, 1, 1},
			12, 5, 7, Arrays.asList(Skill.ARCANA),
			4, Arrays.asList(Skill.ACROBATICS, Skill.ARCANA, Skill.ATHLETICS,
					Skill.BLUFF, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE,
					Skill.PERCEPTION, Skill.RELIGION, Skill.STREETWISE),
			Arrays.asList(Feature.BARDIC_TRAINING, Feature.BARDIC_VIRTUE,
							Feature.MAJESTIC_WORD, Feature.MULTICLASS_VERSATILITY,
							Feature.SKILL_VERSATILITY, Feature.SONG_OF_REST,
							Feature.WORDS_OF_FRIENDSHIP));

	public static final StandardClasse DRUID = new StandardClasse(
			"Druide", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.STAFFS, Implement.TOTEMS), new int[]{0, 1, 1},
			12, 5, 7, Arrays.asList(Skill.NATURE),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS,
					Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.BALANCE_OF_NATURE, Feature.PRIMAL_ASPECT,
							Feature.RITUAL_CASTING, Feature.WILD_SHAPE));

	public static final StandardClasse INVOKER = new StandardClasse(
			"Invocateur", Role.CONTROLLEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS), new int[]{1, 1, 1},
			10, 4, 6, Arrays.asList(Skill.RELIGION),
			3, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.CHANEL_DIVINITY, Feature.DIVINE_COVENANT,
					Feature.RITUAL_CASTING));

	public static final StandardClasse SHAMAN = new StandardClasse(
			"Shaman", Role.MENEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSPEAR),
			Arrays.asList(Implement.TOTEMS), new int[]{1, 0, 1},
			12, 5, 7, Arrays.asList(Skill.NATURE),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION,
					Skill.RELIGION),
			Arrays.asList(Feature.COMPANION_SPIRIT, Feature.HEALING_SPIRIT,
					Feature.SPEAK_WITH_SPIRITS));

	public static final StandardClasse SORCERER = new StandardClasse(
			"Ensorceleur", Role.COGNEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.DAGGERS, Implement.STAFFS), new int[]{0, 0, 2},
			12, 5, 6, Arrays.asList(Skill.ARCANA),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.ENDURANCE, Skill.HISTORY, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.NATURE),
			Arrays.asList(Feature.SPELL_SOURCE));

	public static final StandardClasse WARDEN = new StandardClasse(
			"Guardien", Role.PROTECTEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.DAGGERS, Implement.STAFFS), new int[]{1, 0, 1},
			17, 7, 9, Arrays.asList(Skill.NATURE),
			3, Arrays.asList(Skill.ATHLETICS, Skill.DUNGEONEERING, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.FONT_OF_LIFE, Feature.GUARDIAN_MIGHT,
					Feature.NATURES_WRATH));

	public static final StandardClasse ARDENT = new StandardClasse(
			"Flamboyant", Role.MENEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 0, 1},
			12, 5, 7, Collections.<Skill>emptyList(),
			4, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.STREETWISE),
			Arrays.asList(Feature.ARDENT_MANTLE, Feature.ARDENT_SURGE,
					Feature.PSIONIC_AUGMENTATION));

	public static final StandardClasse BATTLEMIND = new StandardClasse(
			"Batailleur", Role.PROTECTEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 2},
			15, 6, 9, Collections.<Skill>emptyList(),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.INTIMIDATE),
			Arrays.asList(Feature.PSIONIC_AUGMENTATION, Feature.PSIONIC_DEFENSE,
					Feature.PSIONIC_STUDY));

	public static final StandardClasse MONK = new StandardClasse(
			"Moine", Role.COGNEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.CLUB, WeaponType.DAGGER, WeaponType.MONK_UNARMED_STRIKE, WeaponType.QUARTERSTAFF, WeaponType.SHURIKEN, WeaponType.SLING, WeaponType.SPEAR),
			Arrays.asList(Implement.KI_FOCUSES, Implement.WEAPONS_WITH_WICH_YOU_HAVE_PROFICIENCY), new int[]{1, 1, 1},
			12, 5, 7, Collections.<Skill>emptyList(),
			4, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.PERCEPTION,
					Skill.RELIGION, Skill.STEALTH, Skill.THIEVERY),
			Arrays.asList(Feature.MONASTIC_TRADITION, Feature.UNARMED_COMBATANT,
					Feature.UNARMORED_DEFENSE));

	public static final StandardClasse PSION = new StandardClasse(
			"Psion", Role.CONTROLLEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.ORBS, Implement.STAFFS), new int[]{0, 0, 2},
			12, 4, 6, Collections.<Skill>emptyList(),
			4, Arrays.asList(Skill.ARCANA, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE,
					Skill.PERCEPTION),
			Arrays.asList(Feature.DISCIPLINE_FOCUS, Feature.PSIONIC_AUGMENTATION,
					Feature.RITUAL_CASTING));

	public static final StandardClasse RUNEPRIEST = new StandardClasse(
			"Prêtre des runes", Role.MENEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 2},
			12, 5, 7, Arrays.asList(Skill.RELIGION),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.RELIGION, Skill.THIEVERY),
			Arrays.asList(Feature.RUNE_MASTER, Feature.RUNE_OF_MENDING,
					Feature.RUNIC_ARTISTRY));

	public static final StandardClasse SEEKER = new StandardClasse(
			"Limier", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 1, 1},
			12, 5, 7, Arrays.asList(Skill.NATURE),
			3, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.INEVITABLE_SHOT, Feature.SEEKERS_BOND));

	public static final StandardClasse ARTIFICIER = new StandardClasse(
			"Façonneur", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS, Implement.WANDS), new int[]{1, 0, 1},
			12, 5, 6, Arrays.asList(Skill.ARCANA),
			4, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.PERCEPTION, Skill.THIEVERY),
			Arrays.asList(Feature.ARCANE_EMPOWERMENT, Feature.ARCANE_REJUVENATION,
					Feature.HEALING_INFUSION, Feature.RITUAL_CASTING));

	public static final StandardClasse SWORDMAGE = new StandardClasse(
			"Mage d'arme", Role.PROTECTEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_LIGHT_BLADES, WeaponType.MILITARY_HEAVY_BLADES, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.ANY_LIGHT_OR_HEAVY_BLADE), new int[]{0, 0, 2},
			15, 6, 8, Arrays.asList(Skill.ARCANA),
			3, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE),
			Arrays.asList(Feature.SWORDBOND, Feature.SWORDMAGE_AEGIS,
					Feature.SWORDMAGE_WARDING));

	private String nom;
	private Role role;
	private Source source;
	private List<ArmorType> armorProficiencies;
	private List<WeaponType> weaponProficiencies;
	private List<Implement> implementList;
	private int[] bonusToDefense;
	private int hitPointAtFirstLevel;
	private int hitPointPerLevel;
	private int healingSurgesPerDay;
	private List<Skill> trainedSkills;
	private int skillNumber;
	private List<Skill> classSkills;
	private List<Feature> classFeatures;

	public StandardClasse(String nom, Role role, Source source,
			List<ArmorType> armorProficiencies,
			List<WeaponType> weaponProficiencies,
			List<Implement> implementList, int[] bonusToDefense,
			int hitPointAtFirstLevel, int hitPointPerLevel,
			int healingSurgesPerDay, List<Skill> trainedSkills,
			int skillNumber, List<Skill> classSkills, List<Feature> classFeatures) {
		this.nom = nom;
		this.role = role;
		this.source = source;
		this.armorProficiencies = armorProficiencies;
		this.weaponProficiencies = weaponProficiencies;
		this.implementList = implementList;
		this.bonusToDefense = bonusToDefense;
		this.hitPointAtFirstLevel = hitPointAtFirstLevel;
		this.hitPointPerLevel = hitPointPerLevel;
		this.healingSurgesPerDay = healingSurgesPerDay;
		this.trainedSkills = trainedSkills;
		this.classSkills = classSkills;
		this.skillNumber = skillNumber;
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
	public List<Implement> getImplements() {
		return implementList;
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
	public int getSkillNumber() {
		return skillNumber;
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
