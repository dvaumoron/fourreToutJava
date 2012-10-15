package jdr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DemiClasse extends StandardClasse {

	public static final DemiClasse CLERIC_HYBRID = new DemiClasse(
			"Prêtre hybride", Role.MENEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.RELIGION),
			Arrays.asList(Feature.HEALERS_LORE, Feature.HEALING_WORD_HYBRID));

	public static final DemiClasse FIGHTER_HYBRID = new DemiClasse(
			"Guerrier hybride", Role.PROTECTEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 0, 0},
			15, 6, 9,
			0, Arrays.asList(Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL, Skill.INTIMIDATE,
					Skill.STREETWISE),
			Arrays.asList(Feature.COMBAT_CHALLENGE_HYBRID));

	public static final DemiClasse PALADIN_HYBRID_FORTITUDE = new DemiClasse(
			"Paladin hybride (Vigueur)", Role.PROTECTEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.PLATE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{1, 0, 0},
			15, 6, 10,
			0, Arrays.asList(Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.DIVINE_CHALLENGE_HYBRID));

	public static final DemiClasse PALADIN_HYBRID_REFLEX = new DemiClasse(
			"Paladin hybride (Réflexe)", Role.PROTECTEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.PLATE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 1, 0},
			15, 6, 10,
			0, Arrays.asList(Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.DIVINE_CHALLENGE_HYBRID));

	public static final DemiClasse PALADIN_HYBRID_WILL = new DemiClasse(
			"Paladin hybride (Volonté)", Role.PROTECTEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.PLATE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 0, 1},
			15, 6, 10,
			0, Arrays.asList(Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.DIVINE_CHALLENGE_HYBRID));

	public static final DemiClasse RANGER_HYBRID_FORTITUDE = new DemiClasse(
			"Rôdeur hybride (Vigueur)", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 0, 0},
			12, 5, 6,
			1, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DUNGEONEERING,
					Skill.ENDURANCE, Skill.HEAL, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.HUNTERS_QUARRY_HYBRID));
	
	public static final DemiClasse RANGER_HYBRID_REFLEX = new DemiClasse(
			"Rôdeur hybride (Réflexe)", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE,WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 1, 0},
			12, 5, 6,
			1, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DUNGEONEERING,
					Skill.ENDURANCE, Skill.HEAL, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.HUNTERS_QUARRY_HYBRID));

	public static final DemiClasse ROGUE_HYBRID = new DemiClasse(
			"Voleur hybride", Role.COGNEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.DAGGER,WeaponType.HAND_CROSSBOW, WeaponType.SHURIKEN,
					WeaponType.SLING, WeaponType.SHORT_SWORD),
			Collections.<Implement>emptyList(), new int[]{0, 1, 0},
			12, 5, 6,
			2, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.BLUFF,
					Skill.DUNGEONEERING, Skill.INSIGHT, Skill.INTIMIDATE, Skill.PERCEPTION,
					Skill.STEALTH, Skill.STREETWISE, Skill.THIEVERY),
			Arrays.asList(Feature.SNEAK_ATTACK_HYBRID));

	public static final DemiClasse WARLOCK_HYBRID_REFLEX = new DemiClasse(
			"Sorcier hybride (Réflexe)", Role.COGNEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{0, 1, 0},
			12, 5, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.BLUFF, Skill.HISTORY, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.RELIGION, Skill.STREETWISE, Skill.THIEVERY),
			Arrays.asList(Feature.ELDRITCH_PACT_HYBRID, Feature.WARLOCKS_CURSE_HYBRID));

	public static final DemiClasse WARLOCK_HYBRID_WILL = new DemiClasse(
			"Sorcier hybride (Volonté)", Role.COGNEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{0, 1, 1},
			12, 5, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.BLUFF, Skill.HISTORY, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.RELIGION, Skill.STREETWISE, Skill.THIEVERY),
			Arrays.asList(Feature.ELDRITCH_PACT_HYBRID, Feature.WARLOCKS_CURSE_HYBRID));

	public static final DemiClasse WARLORD_HYBRID_FORTITUDE = new DemiClasse(
			"Maître de guerre hybride (Vigueur)", Role.MENEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{1, 0, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ATHLETICS, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INTIMIDATE),
			Arrays.asList(Feature.INSPIRING_WORD_HYBRID, Feature.WARLORD_LEADERSHIP));

	public static final DemiClasse WARLORD_HYBRID_WILL = new DemiClasse(
			"Maître de guerre hybride (Volonté)", Role.MENEUR, Source.MARTIAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.WANDS), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ATHLETICS, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INTIMIDATE),
			Arrays.asList(Feature.INSPIRING_WORD_HYBRID, Feature.WARLORD_LEADERSHIP));

	public static final DemiClasse WIZARD_HYBRID = new DemiClasse(
			"Magicien hybride", Role.CONTROLLEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.DAGGER, WeaponType.QUARTERSTAFF),
			Arrays.asList(Implement.ORBS, Implement.STAFFS, Implement.WANDS), new int[]{0, 0, 1},
			10, 4, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.DUNGEONEERING,
					Skill.HISTORY, Skill.INSIGHT, Skill.NATURE),
			Arrays.asList(Feature.CANTRIPS));

	public static final DemiClasse AVENGER_HYBRID_FORTITUDE = new DemiClasse(
			"Vengeur hybride (Vigueur)", Role.COGNEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{1, 0, 0},
			14, 6, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.PERCEPTION, Skill.RELIGION,
					Skill.STEALTH, Skill.STREETWISE),
			Arrays.asList(Feature.OATH_OF_ENMMITY_HYBRID));

	public static final DemiClasse AVENGER_HYBRID_REFLEX = new DemiClasse(
			"Vengeur hybride (Réflexe)", Role.COGNEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 1, 0},
			14, 6, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.PERCEPTION, Skill.RELIGION,
					Skill.STEALTH, Skill.STREETWISE),
			Arrays.asList(Feature.OATH_OF_ENMMITY_HYBRID));

	public static final DemiClasse AVENGER_HYBRID_WILL = new DemiClasse(
			"Vengeur hybride", Role.COGNEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.HOLY_SYMBOL), new int[]{0, 0, 1},
			14, 6, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.PERCEPTION, Skill.RELIGION,
					Skill.STEALTH, Skill.STREETWISE),
			Arrays.asList(Feature.OATH_OF_ENMMITY_HYBRID));

	public static final DemiClasse BARBARIAN_HYBRID = new DemiClasse(
			"Barbare hybride", Role.COGNEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE),
			Collections.<Implement>emptyList(), new int[]{1, 0, 0},
			15, 6, 8,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.RAMPAGE));

	public static final DemiClasse BARD_HYBRID_REFLEX = new DemiClasse(
			"Barde hybride (Réflexe)", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSWORD, WeaponType.SCIMITAR, WeaponType.SHORT_SWORD, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Arrays.asList(Implement.WANDS), new int[]{0, 1, 1},
			12, 5, 7,
			1, Arrays.asList(Skill.ACROBATICS, Skill.ARCANA, Skill.ATHLETICS,
					Skill.BLUFF, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE,
					Skill.PERCEPTION, Skill.RELIGION, Skill.STREETWISE),
			Arrays.asList(Feature.MAJESTIC_WORD_HYBRID, Feature.SKILL_VERSATILITY));

	public static final DemiClasse BARD_HYBRID_WILL = new DemiClasse(
			"Barde hybride (Volonté)", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSWORD, WeaponType.SCIMITAR, WeaponType.SHORT_SWORD, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Arrays.asList(Implement.WANDS), new int[]{0, 1, 1},
			12, 5, 7,
			1, Arrays.asList(Skill.ACROBATICS, Skill.ARCANA, Skill.ATHLETICS,
					Skill.BLUFF, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE,
					Skill.PERCEPTION, Skill.RELIGION, Skill.STREETWISE),
			Arrays.asList(Feature.MAJESTIC_WORD_HYBRID, Feature.SKILL_VERSATILITY));

	public static final DemiClasse DRUID_HYBRID_REFLEX = new DemiClasse(
			"Druide hybride (Réflexe)", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.STAFFS, Implement.TOTEMS), new int[]{0, 1, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS,
					Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.WILD_SHAPE));

	public static final DemiClasse DRUID_HYBRID_WILL = new DemiClasse(
			"Druide hybride (Volonté)", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.STAFFS, Implement.TOTEMS), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS,
					Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HEAL, Skill.HISTORY,
					Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.WILD_SHAPE));

	public static final DemiClasse INVOKER_HYBRID_FORTITUDE = new DemiClasse(
			"Invocateur hybride (Vigueur)", Role.CONTROLLEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS), new int[]{1, 0, 0},
			10, 4, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.COVENANT_MANIFESTATION));

	public static final DemiClasse INVOKER_HYBRID_REFLEX = new DemiClasse(
			"Invocateur hybride (Réflexe)", Role.CONTROLLEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS), new int[]{0, 1, 0},
			10, 4, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.COVENANT_MANIFESTATION));

	public static final DemiClasse INVOKER_HYBRID_WILL = new DemiClasse(
			"Invocateur hybride (Volonté)", Role.CONTROLLEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS), new int[]{0, 0, 1},
			10, 4, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.ENDURANCE, Skill.HISTORY,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.RELIGION),
			Arrays.asList(Feature.COVENANT_MANIFESTATION));

	public static final DemiClasse SHAMAN_HYBRID_FORTITUDE = new DemiClasse(
			"Shaman hybride (Vigueur)", Role.MENEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSPEAR),
			Arrays.asList(Implement.TOTEMS), new int[]{1, 0, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION,
					Skill.RELIGION),
			Arrays.asList(Feature.COMPANION_SPIRIT_HYBRID, Feature.HEALING_SPIRIT_HYBRID,
					Feature.SPEAK_WITH_SPIRITS));

	public static final DemiClasse SHAMAN_HYBRID_WILL = new DemiClasse(
			"Shaman hybride (Volonté)", Role.MENEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.LONGSPEAR),
			Arrays.asList(Implement.TOTEMS), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.NATURE, Skill.PERCEPTION,
					Skill.RELIGION),
			Arrays.asList(Feature.COMPANION_SPIRIT_HYBRID, Feature.HEALING_SPIRIT_HYBRID,
					Feature.SPEAK_WITH_SPIRITS));

	public static final DemiClasse SORCERER_HYBRID = new DemiClasse(
			"Ensorceleur hybride", Role.COGNEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.DAGGERS, Implement.STAFFS), new int[]{0, 0, 1},
			12, 5, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.ENDURANCE, Skill.HISTORY, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.NATURE),
			Arrays.asList(Feature.SORCEROUS_POWER));

	public static final DemiClasse WARDEN_HYBRID_FORTITUDE = new DemiClasse(
			"Guardien hybride (Vigueur)", Role.PROTECTEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.DAGGERS, Implement.STAFFS), new int[]{1, 0, 0},
			17, 7, 9,
			0, Arrays.asList(Skill.ATHLETICS, Skill.DUNGEONEERING, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.NATURES_WRATH_HYBRID));

	public static final DemiClasse WARDEN_HYBRID_WILL = new DemiClasse(
			"Guardien hybride (Volonté)", Role.PROTECTEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.DAGGERS, Implement.STAFFS), new int[]{0, 0, 1},
			17, 7, 9,
			0, Arrays.asList(Skill.ATHLETICS, Skill.DUNGEONEERING, Skill.ENDURANCE,
					Skill.HEAL, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION),
			Arrays.asList(Feature.NATURES_WRATH_HYBRID));

	public static final DemiClasse ARDENT_HYBRID_FORTITUDE = new DemiClasse(
			"Flamboyant hybride (Vigueur)", Role.MENEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{1, 0, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.STREETWISE),
			Arrays.asList(Feature.ARDENT_MANTLE_HYBRID, Feature.ARDENT_SURGE_HYBRID,
					Feature.PSIONIC_AUGMENTATION_HYBRID));

	public static final DemiClasse ARDENT_HYBRID_WILL = new DemiClasse(
			"Flamboyant hybride (Volonté)", Role.MENEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT,
					Skill.INTIMIDATE, Skill.STREETWISE),
			Arrays.asList(Feature.ARDENT_MANTLE_HYBRID, Feature.ARDENT_SURGE_HYBRID,
					Feature.PSIONIC_AUGMENTATION_HYBRID));

	public static final DemiClasse BATTLEMIND_HYBRID = new DemiClasse(
			"Batailleur hybride", Role.PROTECTEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD, ArmorType.HEAVY_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 1},
			15, 6, 9,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.INTIMIDATE),
			Arrays.asList(Feature.PSIONIC_AUGMENTATION_HYBRID, Feature.PSIONIC_DEFENSE_HYBRID));

	public static final DemiClasse MONK_HYBRID_FORTITUDE = new DemiClasse(
			"Moine hybride (Vigueur)", Role.COGNEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.CLUB, WeaponType.DAGGER, WeaponType.MONK_UNARMED_STRIKE, WeaponType.QUARTERSTAFF, WeaponType.SHURIKEN, WeaponType.SLING, WeaponType.SPEAR),
			Arrays.asList(Implement.KI_FOCUSES, Implement.WEAPONS_WITH_WICH_YOU_HAVE_PROFICIENCY), new int[]{1, 0, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.PERCEPTION,
					Skill.RELIGION, Skill.STEALTH, Skill.THIEVERY),
			Arrays.asList(Feature.MONASTIC_TRADITION_HYBRID));

	public static final DemiClasse MONK_HYBRID_REFLEX = new DemiClasse(
			"Moine hybride (Réflexe)", Role.COGNEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.CLUB, WeaponType.DAGGER, WeaponType.MONK_UNARMED_STRIKE, WeaponType.QUARTERSTAFF, WeaponType.SHURIKEN, WeaponType.SLING, WeaponType.SPEAR),
			Arrays.asList(Implement.KI_FOCUSES, Implement.WEAPONS_WITH_WICH_YOU_HAVE_PROFICIENCY), new int[]{0, 1, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.PERCEPTION,
					Skill.RELIGION, Skill.STEALTH, Skill.THIEVERY),
			Arrays.asList(Feature.MONASTIC_TRADITION_HYBRID));

	public static final DemiClasse MONK_HYBRID_WILL = new DemiClasse(
			"Moine hybride (Volonté)", Role.COGNEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.CLUB, WeaponType.DAGGER, WeaponType.MONK_UNARMED_STRIKE, WeaponType.QUARTERSTAFF, WeaponType.SHURIKEN, WeaponType.SLING, WeaponType.SPEAR),
			Arrays.asList(Implement.KI_FOCUSES, Implement.WEAPONS_WITH_WICH_YOU_HAVE_PROFICIENCY), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HEAL, Skill.INSIGHT, Skill.PERCEPTION,
					Skill.RELIGION, Skill.STEALTH, Skill.THIEVERY),
			Arrays.asList(Feature.MONASTIC_TRADITION_HYBRID));

	public static final DemiClasse PSION_HYBRID = new DemiClasse(
			"Psion hybride", Role.CONTROLLEUR, Source.PSIONIC,
			Arrays.asList(ArmorType.CLOTH),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.ORBS, Implement.STAFFS), new int[]{0, 0, 1},
			12, 4, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.BLUFF, Skill.DIPLOMACY,
					Skill.DUNGEONEERING, Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE,
					Skill.PERCEPTION),
			Arrays.asList(Feature.DISCIPLINE_FOCUS_HYBRID, Feature.PSIONIC_AUGMENTATION_HYBRID));

	public static final DemiClasse RUNEPRIEST_HYBRID = new DemiClasse(
			"Prêtre des runes hybride", Role.MENEUR, Source.DIVINE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER, ArmorType.HIDE, ArmorType.CHAINMAIL, ArmorType.SCALE, ArmorType.LIGHT_SHIELD),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.HISTORY, Skill.INSIGHT, Skill.RELIGION, Skill.THIEVERY),
			Arrays.asList(Feature.RUNE_OF_MENDING_HYBRID, Feature.RUNIC_ARTISTRY));

	public static final DemiClasse SEEKER_HYBRID_REFLEX = new DemiClasse(
			"Limier hybride (Réflexe)", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 1, 0},
			12, 5, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.INEVITABLE_SHOT_HYBRID, Feature.SEEKERS_BOND_HYBRID));

	public static final DemiClasse SEEKER_HYBRID_WILL = new DemiClasse(
			"Limier hybride (Volonté)", Role.CONTROLLEUR, Source.PRIMAL,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED, WeaponType.MILITARY_RANGED),
			Collections.<Implement>emptyList(), new int[]{0, 0, 1},
			12, 5, 7,
			0, Arrays.asList(Skill.ACROBATICS, Skill.ATHLETICS, Skill.ENDURANCE, Skill.HEAL,
					Skill.INSIGHT, Skill.INTIMIDATE, Skill.NATURE, Skill.PERCEPTION,
					Skill.STEALTH),
			Arrays.asList(Feature.INEVITABLE_SHOT_HYBRID, Feature.SEEKERS_BOND_HYBRID));

	public static final DemiClasse ARTIFICIER_HYBRID_FORTITUDE = new DemiClasse(
			"Façonneur hybride (Vigueur)", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS, Implement.WANDS), new int[]{1, 0, 0},
			12, 5, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.PERCEPTION, Skill.THIEVERY),
			Arrays.asList(Feature.HEALING_INFUSION_HYBRID));

	public static final DemiClasse ARTIFICIER_HYBRID_WILL = new DemiClasse(
			"Façonneur hybride (Volonté)", Role.MENEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.RODS, Implement.STAFFS, Implement.WANDS), new int[]{0, 0, 1},
			12, 5, 6,
			0, Arrays.asList(Skill.ARCANA, Skill.DIPLOMACY, Skill.DUNGEONEERING, Skill.HEAL,
					Skill.HISTORY, Skill.PERCEPTION, Skill.THIEVERY),
			Arrays.asList(Feature.HEALING_INFUSION_HYBRID));

	public static final DemiClasse SWORDMAGE_HYBRID = new DemiClasse(
			"Mage d'arme hybride", Role.PROTECTEUR, Source.ARCANE,
			Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.MILITARY_LIGHT_BLADES, WeaponType.MILITARY_HEAVY_BLADES, WeaponType.SIMPLE_RANGED),
			Arrays.asList(Implement.ANY_LIGHT_OR_HEAVY_BLADE), new int[]{0, 0, 1},
			15, 6, 8,
			0, Arrays.asList(Skill.ARCANA, Skill.ATHLETICS, Skill.DIPLOMACY,
					Skill.ENDURANCE, Skill.HISTORY, Skill.INSIGHT, Skill.INTIMIDATE),
			Arrays.asList(Feature.SWORDBOND, Feature.SWORDMAGE_AEGIS_HYBRID));

	private static final List<DemiClasse> values = new ArrayList<DemiClasse>();

	static {
		values.add(CLERIC_HYBRID);
		values.add(FIGHTER_HYBRID);
		values.add(PALADIN_HYBRID_FORTITUDE);
		values.add(PALADIN_HYBRID_REFLEX);
		values.add(PALADIN_HYBRID_WILL);
		values.add(RANGER_HYBRID_FORTITUDE);
		values.add(RANGER_HYBRID_REFLEX);
		values.add(ROGUE_HYBRID);
		values.add(WARLOCK_HYBRID_REFLEX);
		values.add(WARLOCK_HYBRID_WILL);
		values.add(WARLORD_HYBRID_FORTITUDE);
		values.add(WARLORD_HYBRID_WILL);
		values.add(WIZARD_HYBRID);
		values.add(AVENGER_HYBRID_FORTITUDE);
		values.add(AVENGER_HYBRID_REFLEX);
		values.add(AVENGER_HYBRID_WILL);
		values.add(BARBARIAN_HYBRID);
		values.add(BARD_HYBRID_REFLEX);
		values.add(BARD_HYBRID_WILL);
		values.add(DRUID_HYBRID_REFLEX);
		values.add(DRUID_HYBRID_WILL);
		values.add(INVOKER_HYBRID_FORTITUDE);
		values.add(INVOKER_HYBRID_REFLEX);
		values.add(INVOKER_HYBRID_WILL);
		values.add(SHAMAN_HYBRID_FORTITUDE);
		values.add(SHAMAN_HYBRID_WILL);
		values.add(SORCERER_HYBRID);
		values.add(WARDEN_HYBRID_FORTITUDE);
		values.add(WARDEN_HYBRID_WILL);
		values.add(ARDENT_HYBRID_FORTITUDE);
		values.add(ARDENT_HYBRID_WILL);
		values.add(BATTLEMIND_HYBRID);
		values.add(MONK_HYBRID_FORTITUDE);
		values.add(MONK_HYBRID_REFLEX);
		values.add(MONK_HYBRID_WILL);
		values.add(PSION_HYBRID);
		values.add(RUNEPRIEST_HYBRID);
		values.add(SEEKER_HYBRID_REFLEX);
		values.add(SEEKER_HYBRID_WILL);
		values.add(ARTIFICIER_HYBRID_FORTITUDE);
		values.add(ARTIFICIER_HYBRID_WILL);
		values.add(SWORDMAGE_HYBRID);
	}

	public static List<DemiClasse> values() {
		return values;
	}

	private DemiClasse(String nom, Role role, Source source,
			List<ArmorType> armorProficiencies,
			List<WeaponType> weaponProficiencies,
			List<Implement> implementList, int[] bonusToDefense,
			int hitPointAtFirstLevel, int hitPointPerLevel,
			int healingSurgesPerDay, int skillNumber, List<Skill> classSkills,
			List<Feature> classFeatures) {
		super(nom, role, source, armorProficiencies, weaponProficiencies,
				implementList, bonusToDefense, hitPointAtFirstLevel, hitPointPerLevel,
				healingSurgesPerDay, Collections.<Skill>emptyList(), skillNumber, classSkills,
				classFeatures);
	}

}
