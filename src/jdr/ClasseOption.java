package jdr;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ClasseOption {

	NONE("Aucune", Collections.<Feature>emptyList()),

	// cleric option
	CHANNEL_DIVINITY_TURN_UNDEAD("Renvoi des morts-vivants", Arrays.asList(Feature.CHANNEL_DIVINITY_TURN_UNDEAD)),
	CHANNEL_DIVINITY_HEALERS_MERCY("Compassion du guérisseur", Arrays.asList(Feature.CHANNEL_DIVINITY_HEALERS_MERCY)),

	// fighter option
	FIGHTER_WEAPON_TALENT_ONE_HANDED("Arme à une main", Arrays.asList(Feature.COMBAT_SUPERIORITY, Feature.FIGHTER_WEAPON_TALENT_ONE_HANDED)),
	FIGHTER_WEAPON_TALENT_TWO_HANDED("Arme à deux mains", Arrays.asList(Feature.COMBAT_SUPERIORITY, Feature.FIGHTER_WEAPON_TALENT_TWO_HANDED)),
	BATTLERAGER_VIGOR("Vigueur du guerrier téméraire", Arrays.asList(Feature.COMBAT_SUPERIORITY, Feature.BATTLERAGER_VIGOR)),
	TEMPEST_TECHNIQUE("Technique de la tempête", Arrays.asList(Feature.COMBAT_SUPERIORITY, Feature.TEMPEST_TECHNIQUE)),
	BRAWLER_STYLE("Style du bagarreur", Arrays.asList(Feature.COMBAT_SUPERIORITY, Feature.BRAWLER_STYLE)),
	FIGHTER_WEAPON_TALENT_ONE_HANDED_AGILITY("Arme à une main (Agilité en combat)", Arrays.asList(Feature.COMBAT_AGILITY, Feature.FIGHTER_WEAPON_TALENT_ONE_HANDED)),
	FIGHTER_WEAPON_TALENT_TWO_HANDED_AGILITY("Arme à deux mains (Agilité en combat)", Arrays.asList(Feature.COMBAT_AGILITY, Feature.FIGHTER_WEAPON_TALENT_TWO_HANDED)),
	BATTLERAGER_VIGOR_AGILITY("Vigueur du guerrier téméraire (Agilité en combat)", Arrays.asList(Feature.COMBAT_AGILITY, Feature.BATTLERAGER_VIGOR)),
	TEMPEST_TECHNIQUE_AGILITY("Technique de la tempête (Agilité en combat)", Arrays.asList(Feature.COMBAT_AGILITY, Feature.TEMPEST_TECHNIQUE)),
	BRAWLER_STYLE_AGILITY("Style du bagarreur (Agilité en combat)", Arrays.asList(Feature.COMBAT_AGILITY, Feature.BRAWLER_STYLE)),

	// paladin
	LAY_ON_HANDS("Imposition des mains", Arrays.asList(Feature.LAY_ON_HANDS)),
	ARDENT_VOW("Serment ardent", Arrays.asList(Feature.ARDENT_VOW)),
	VIRTUES_TOUCH("Toucher de la vertu", Arrays.asList(Feature.VIRTUES_TOUCH)),

	// ranger option
	ARCHER_FIGHTING_STYLE("Combat à Distance", Arrays.asList(Feature.PRIME_SHOT, Feature.DEFENSIVE_MOBILITY)),
	TWO_BLADE_FIGHTING_STYLE("Combat à deux armes", Arrays.asList(Feature.PRIME_SHOT, Feature.TOUGHTNESS, Feature.ONE_HANDED_IN_OFFHAND)),
	BEAST_MASTERY("Maîtrise des bêtes", Arrays.asList(Feature.BEAST_MASTERY)),
	HUNTER_FIGHTING_STYLE("Style de combat du chasseur", Arrays.asList(Feature.PRIME_SHOT, Feature.QUICK_DRAW, Feature.HUNTER_FIGHTING_STYLE)),
	MARAUDER_FIGHTING_STYLE("Style de combat du maraudeur", Arrays.asList(Feature.PRIME_SHOT, Feature.TWO_WEAPON_DEFENSE, Feature.MARAUDER_FIGHTING_STYLE)),
	ARCHER_FIGHTING_STYLE_RUNNING("Combat à Distance (Course d'assaut)", Arrays.asList(Feature.RUNNING_ATTACK, Feature.DEFENSIVE_MOBILITY)),
	TWO_BLADE_FIGHTING_STYLE_RUNNING("Combat à deux armes (Course d'assaut)", Arrays.asList(Feature.RUNNING_ATTACK, Feature.TOUGHTNESS, Feature.ONE_HANDED_IN_OFFHAND)),
	HUNTER_FIGHTING_STYLE_RUNNING("Style de combat du chasseur (Course d'assaut)", Arrays.asList(Feature.RUNNING_ATTACK, Feature.QUICK_DRAW, Feature.HUNTER_FIGHTING_STYLE)),
	MARAUDER_FIGHTING_STYLE_RUNNING("Style de combat du maraudeur (Course d'assaut)", Arrays.asList(Feature.RUNNING_ATTACK, Feature.TWO_WEAPON_DEFENSE, Feature.MARAUDER_FIGHTING_STYLE)),

	// rogue option
	ROGUE_TACTICS_ARTFUL_DODGER("Esthète de l'esquive", Arrays.asList(Feature.ROGUE_WEAPON_TALENT, Feature.ROGUE_TACTICS_ARTFUL_DODGER)),
	ROGUE_TACTICS_BRUTAL_SCOUNDREL("Brute des bas-fonds", Arrays.asList(Feature.ROGUE_WEAPON_TALENT, Feature.ROGUE_TACTICS_BRUTAL_SCOUNDREL)),
	ROGUE_TACTICS_RUTHLESS_RUFFIAN("Ruffian impitoyable", Arrays.asList(Feature.ROGUE_WEAPON_TALENT, Feature.ROGUE_TACTICS_RUTHLESS_RUFFIAN)),
	ROGUE_TACTICS_CUNNING_SNEAK("Furtivité", Arrays.asList(Feature.ROGUE_WEAPON_TALENT, Feature.ROGUE_TACTICS_CUNNING_SNEAK)),
	ROGUE_TACTICS_ARTFUL_DODGER_SHARPSHOOTER("Esthète de l'esquive (Tireur d'élite)", Arrays.asList(Feature.SHARPSHOOTER_TALENT, Feature.FAR_SHOT, Feature.ROGUE_TACTICS_ARTFUL_DODGER)),
	ROGUE_TACTICS_BRUTAL_SCOUNDREL_SHARPSHOOTER("Brute des bas-fonds (Tireur d'élite)", Arrays.asList(Feature.SHARPSHOOTER_TALENT, Feature.FAR_SHOT, Feature.ROGUE_TACTICS_BRUTAL_SCOUNDREL)),
	ROGUE_TACTICS_RUTHLESS_RUFFIAN_SHARPSHOOTER("Ruffian impitoyable (Tireur d'élite)", Arrays.asList(Feature.SHARPSHOOTER_TALENT, Feature.FAR_SHOT, Feature.ROGUE_TACTICS_RUTHLESS_RUFFIAN)),
	ROGUE_TACTICS_CUNNING_SNEAK_SHARPSHOOTER("Furtivité (Tireur d'élite)", Arrays.asList(Feature.SHARPSHOOTER_TALENT, Feature.FAR_SHOT, Feature.ROGUE_TACTICS_CUNNING_SNEAK)),

	// warlock option
	FEY_PACT("Pacte féerique", Arrays.asList(Feature.FEY_PACT)),
	INFERNAL_PACT("Pacte infernal", Arrays.asList(Feature.INFERNAL_PACT)),
	STAR_PACT("Pacte stellaire", Arrays.asList(Feature.STAR_PACT)),
	VESTIGE_PACT("Pacte des vestiges", Arrays.asList(Feature.VESTIGE_PACT)),
	DARK_PACT("Pacte sombre", Arrays.asList(Feature.DARK_PACT)),

	// warlord option
	INSPIRING_PRESENCE("Présence inspiratrice", Arrays.asList(Feature.INSPIRING_PRESENCE)),
	TACTICAL_PRESENCE("Présence tactique",  Arrays.asList(Feature.TACTICAL_PRESENCE)),
	BRAVURA_PRESENCE("Présence intrépide", Arrays.asList(Feature.BRAVURA_PRESENCE)),
	RESOURCEFUL_PRESENCE("Présence ingénieuse", Arrays.asList(Feature.RESOURCEFUL_PRESENCE)),
	INSIGHTFUL_PRESENCE("Présence perspicace", Arrays.asList(Feature.INSIGHTFUL_PRESENCE)),
	SKIRMISHING_PRESENCE("Présence du tirailleur", Arrays.asList(Feature.SKIRMISHING_PRESENCE)),

	// wizard option
	ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION("Orbe du chatiment", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION)),
	ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE("Bâton de défense", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE)),
	ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY("Baguette de précision", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY)),
	ARCANE_IMPLEMENT_MASTERY_ORB_OF_DECEPTION("Orbe de tromperie", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_ORB_OF_DECEPTION)),
	ARCANE_IMPLEMENT_MASTERY_TOME_OF_BINDING("Tome de liage", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_TOME_OF_BINDING)),
	ARCANE_IMPLEMENT_MASTERY_TOME_OF_READINESS("Tome d'empressement", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_TOME_OF_READINESS)),

	// avenger option
	CENSURE_OF_PURSUIT("Censure de poursuite", Arrays.asList(Feature.CENSURE_OF_PURSUIT)),
	CENSURE_OF_RETRIBUTION("Censure rétributive", Arrays.asList(Feature.CENSURE_OF_RETRIBUTION)),
	CENSURE_OF_UNITY("Censure d'unité", Arrays.asList(Feature.CENSURE_OF_UNITY)),

	// barbarian option
	FERAL_MIGHT_RAGEBLOOD_VIGOR("Ardeur sanguinaire", Arrays.asList(Feature.FERAL_MIGHT_RAGEBLOOD_VIGOR)),
	FERAL_MIGHT_THANEBORN_TRIUMPH("Triomphe du champion", Arrays.asList(Feature.FERAL_MIGHT_THANEBORN_TRIUMPH)),
	FERAL_MIGHT_THUNDERBORN_WRATH("Colère du fils du tonnerre", Arrays.asList(Feature.FERAL_MIGHT_THUNDERBORN_WRATH)),
	FERAL_MIGHT_WHIRLWIND_SLAYER("Faucheur tournoyant", Arrays.asList(Feature.ONE_HANDED_IN_OFFHAND, Feature.FERAL_MIGHT_WHIRLWIND_SLAYER)),

	// bard option
	VIRTUE_OF_CUNNING("Vertu de ruse", Arrays.asList(Feature.VIRTUE_OF_CUNNING)),
	VIRTUE_OF_VALOR("Vertu de vaillance", Arrays.asList(Feature.VIRTUE_OF_VALOR)),
	VIRTUE_OF_PRESCIENCE("Vertu de préscience", Arrays.asList(Feature.VIRTUE_OF_PRESCIENCE)),

	// druid option
	PRIMAL_GUARDIAN("Guardien primal", Arrays.asList(Feature.PRIMAL_GUARDIAN)), 
	PRIMAL_PREDATOR("Prédateur primal", Arrays.asList(Feature.PRIMAL_PREDATOR)),
	PRIMAL_SWARM("Nuée primale", Arrays.asList(Feature.PRIMAL_SWARM)),

	// invoker option
	COVENANT_OF_PRESERVATION("Alliance protectrice", Arrays.asList(Feature.COVENANT_OF_PRESERVATION)),
	COVENANT_OF_WRATH("Alliance de colère", Arrays.asList(Feature.COVENANT_OF_WRATH)),
	COVENANT_OF_MALEDICTION("Alliance de malédiction", Arrays.asList(Feature.COVENANT_OF_MALEDICTION)),

	// shaman option
	PROTECTOR_SPIRIT("Esprit protecteur", Arrays.asList(Feature.PROTECTOR_SPIRIT)),
	STALKER_SPIRIT("Esprit prédateur", Arrays.asList(Feature.STALKER_SPIRIT)),
	WATCHER_SPIRIT("Esprit du guetteur", Arrays.asList(Feature.WATCHER_SPIRIT)),
	WORLD_SPEAKER_SPIRIT("Esprit du messager des mondes", Arrays.asList(Feature.WORLD_SPEAKER_SPIRIT)),

	// sorcerer option
	SPELL_SOURCE_DRAGON_MAGIC("Magie draconique", Arrays.asList(Feature.SPELL_SOURCE_DRAGON_MAGIC)),
	SPELL_SOURCE_WILD_MAGIC("Magie sauvage", Arrays.asList(Feature.SPELL_SOURCE_WILD_MAGIC)),
	SPELL_SOURCE_STORM_MAGIC("Magie de la tempête", Arrays.asList(Feature.SPELL_SOURCE_STORM_MAGIC)),
	SPELL_SOURCE_COSMIC_MAGIC("Magie cosmique", Arrays.asList(Feature.SPELL_SOURCE_COSMIC_MAGIC)),

	// warden option
	GUARDIAN_MIGHT_EARTHSTRENGTH("Force de la terre", Arrays.asList(Feature.GUARDIAN_MIGHT_EARTHSTRENGTH)),
	GUARDIAN_MIGHT_WILDBLOOD("Sang indompté", Arrays.asList(Feature.GUARDIAN_MIGHT_WILDBLOOD)),
	GUARDIAN_MIGHT_LIFESPIRIT("Esprit vital", Arrays.asList(Feature.GUARDIAN_MIGHT_LIFESPIRIT)),
	GUARDIAN_MIGHT_STORMHEART("Coeur de tempête", Arrays.asList(Feature.GUARDIAN_MIGHT_STORMHEART)),

	// ardent option
	MANTLE_OF_CLARITY("Manteau de clarté", Arrays.asList(Feature.MANTLE_OF_CLARITY)),
	MANTLE_OF_ELATION("Manteau d'allégresse", Arrays.asList(Feature.MANTLE_OF_ELATION)),
	MANTLE_OF_IMPULSIVENESS("Manteau d'impulsivité", Arrays.asList(Feature.MANTLE_OF_IMPULSIVENESS)),

	// battlemind option
	PSIONIC_STUDY_BATTLE_RESILIENCE("Résistance de combat", Arrays.asList(Feature.PSIONIC_STUDY_BATTLE_RESILIENCE)),
	PSIONIC_STUDY_SPEED_OF_THOUGHT("Vitesse de la pensée", Arrays.asList(Feature.PSIONIC_STUDY_SPEED_OF_THOUGHT)),
	PSIONIC_STUDY_PERSISTENT_HARRIER("Busard persistant", Arrays.asList(Feature.PSIONIC_STUDY_PERSISTENT_HARRIER)),

	// monk option
	MONASTIC_TRADITION_CENTERED_BREATH("Souffle centrée", Arrays.asList(Feature.MONASTIC_TRADITION_CENTERED_BREATH)),
	MONASTIC_TRADITION_STONE_FIST("Poing de pierre", Arrays.asList(Feature.MONASTIC_TRADITION_STONE_FIST)),
	MONASTIC_TRADITION_IRON_SOUL("Ame de fer", Arrays.asList(Feature.MONASTIC_TRADITION_IRON_SOUL)),

	// psion option
	TELEKINESIS_FOCUS("Spécialisation en télékinésie", Arrays.asList(Feature.TELEKINESIS_FOCUS)),
	TELEPATHY_FOCUS("Spécialisation en télépathie", Arrays.asList(Feature.TELEPATHY_FOCUS)),
	SHAPER_FOCUS("Spécialisation en création", Arrays.asList(Feature.SHAPER_FOCUS)),

	// runepriest option
	RUNIC_ARTISTRY_DEFIANT_WORD("Parole de défi", Arrays.asList(Feature.RUNIC_ARTISTRY_DEFIANT_WORD)),
	RUNIC_ARTISTRY_WRATHFUL_HAMMER("Marteau de l'ire", Arrays.asList(Feature.RUNIC_ARTISTRY_WRATHFUL_HAMMER)),

	// seeker option
	BLOODBOND("Lien du sang", Arrays.asList(Feature.BLOODBOND)),
	SPIRITBOND("Lien désincarné", Arrays.asList(Feature.SPIRITBOND)),

	// swordmage option
	AEGIS_OF_ASSAULT("Egide offensive", Arrays.asList(Feature.AEGIS_OF_ASSAULT)),
	AEGIS_OF_SHIELDING("Egide défensive", Arrays.asList(Feature.AEGIS_OF_SHIELDING)),
	AEGIS_OF_ENSNAREMENT("Egide piègeante", Arrays.asList(Feature.AEGIS_OF_ENSNAREMENT));

	private static final Map<String, ClasseOption> optionByName = new LinkedHashMap<String, ClasseOption>();
	private static final Map<Classe, List<ClasseOption>> optionsByClasse = new LinkedHashMap<Classe, List<ClasseOption>>();

	static {
		for (ClasseOption option : ClasseOption.values()) {
			optionByName.put(option.getNom(), option);
		}

		optionsByClasse.put(StandardClasse.CLERIC, Arrays.asList(CHANNEL_DIVINITY_TURN_UNDEAD, CHANNEL_DIVINITY_HEALERS_MERCY));
		optionsByClasse.put(StandardClasse.FIGHTER, Arrays.asList(FIGHTER_WEAPON_TALENT_ONE_HANDED, FIGHTER_WEAPON_TALENT_TWO_HANDED, BATTLERAGER_VIGOR, TEMPEST_TECHNIQUE, BRAWLER_STYLE, 
				FIGHTER_WEAPON_TALENT_ONE_HANDED_AGILITY, FIGHTER_WEAPON_TALENT_TWO_HANDED_AGILITY, BATTLERAGER_VIGOR_AGILITY, TEMPEST_TECHNIQUE_AGILITY, BRAWLER_STYLE_AGILITY));
		optionsByClasse.put(StandardClasse.PALADIN, Arrays.asList(LAY_ON_HANDS, ARDENT_VOW, VIRTUES_TOUCH));
		List<ClasseOption> rangerOption = Arrays.asList(ARCHER_FIGHTING_STYLE, TWO_BLADE_FIGHTING_STYLE, BEAST_MASTERY, HUNTER_FIGHTING_STYLE, MARAUDER_FIGHTING_STYLE,
				ARCHER_FIGHTING_STYLE_RUNNING, TWO_BLADE_FIGHTING_STYLE_RUNNING, HUNTER_FIGHTING_STYLE_RUNNING, MARAUDER_FIGHTING_STYLE_RUNNING);
		optionsByClasse.put(StandardClasse.RANGER_DUNGEONEERING, rangerOption);
		optionsByClasse.put(StandardClasse.RANGER_NATURE, rangerOption);
		optionsByClasse.put(StandardClasse.ROGUE, Arrays.asList(ROGUE_TACTICS_ARTFUL_DODGER, ROGUE_TACTICS_BRUTAL_SCOUNDREL, ROGUE_TACTICS_RUTHLESS_RUFFIAN, ROGUE_TACTICS_CUNNING_SNEAK,
				ROGUE_TACTICS_ARTFUL_DODGER_SHARPSHOOTER, ROGUE_TACTICS_BRUTAL_SCOUNDREL_SHARPSHOOTER, ROGUE_TACTICS_RUTHLESS_RUFFIAN_SHARPSHOOTER, ROGUE_TACTICS_CUNNING_SNEAK_SHARPSHOOTER));
		optionsByClasse.put(StandardClasse.WARLOCK, Arrays.asList(FEY_PACT, INFERNAL_PACT, STAR_PACT, VESTIGE_PACT, DARK_PACT));
		optionsByClasse.put(StandardClasse.WARLORD, Arrays.asList(INSPIRING_PRESENCE, TACTICAL_PRESENCE, BRAVURA_PRESENCE, RESOURCEFUL_PRESENCE, INSIGHTFUL_PRESENCE, SKIRMISHING_PRESENCE));
		optionsByClasse.put(StandardClasse.WIZARD, Arrays.asList(ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION, ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE, ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY, ARCANE_IMPLEMENT_MASTERY_ORB_OF_DECEPTION, ARCANE_IMPLEMENT_MASTERY_TOME_OF_BINDING, ARCANE_IMPLEMENT_MASTERY_TOME_OF_READINESS));
		optionsByClasse.put(StandardClasse.AVENGER, Arrays.asList(CENSURE_OF_PURSUIT, CENSURE_OF_RETRIBUTION, CENSURE_OF_UNITY));
		optionsByClasse.put(StandardClasse.BARBARIAN, Arrays.asList(FERAL_MIGHT_RAGEBLOOD_VIGOR, FERAL_MIGHT_THANEBORN_TRIUMPH, FERAL_MIGHT_THUNDERBORN_WRATH, FERAL_MIGHT_WHIRLWIND_SLAYER));
		optionsByClasse.put(StandardClasse.BARD, Arrays.asList(VIRTUE_OF_CUNNING, VIRTUE_OF_VALOR, VIRTUE_OF_PRESCIENCE));
		optionsByClasse.put(StandardClasse.DRUID, Arrays.asList(PRIMAL_GUARDIAN, PRIMAL_PREDATOR, PRIMAL_SWARM));
		optionsByClasse.put(StandardClasse.INVOKER, Arrays.asList(COVENANT_OF_PRESERVATION, COVENANT_OF_WRATH, COVENANT_OF_MALEDICTION));
		optionsByClasse.put(StandardClasse.SHAMAN, Arrays.asList(PROTECTOR_SPIRIT, STALKER_SPIRIT, WATCHER_SPIRIT, WORLD_SPEAKER_SPIRIT));
		optionsByClasse.put(StandardClasse.SORCERER, Arrays.asList(SPELL_SOURCE_DRAGON_MAGIC, SPELL_SOURCE_WILD_MAGIC, SPELL_SOURCE_STORM_MAGIC, SPELL_SOURCE_COSMIC_MAGIC));
		optionsByClasse.put(StandardClasse.WARDEN, Arrays.asList(GUARDIAN_MIGHT_EARTHSTRENGTH, GUARDIAN_MIGHT_WILDBLOOD, GUARDIAN_MIGHT_LIFESPIRIT, GUARDIAN_MIGHT_STORMHEART));
		optionsByClasse.put(StandardClasse.ARDENT, Arrays.asList(MANTLE_OF_CLARITY, MANTLE_OF_ELATION, MANTLE_OF_IMPULSIVENESS));
		optionsByClasse.put(StandardClasse.BATTLEMIND, Arrays.asList(PSIONIC_STUDY_BATTLE_RESILIENCE, PSIONIC_STUDY_SPEED_OF_THOUGHT, PSIONIC_STUDY_PERSISTENT_HARRIER));
		optionsByClasse.put(StandardClasse.MONK, Arrays.asList(MONASTIC_TRADITION_CENTERED_BREATH, MONASTIC_TRADITION_STONE_FIST, MONASTIC_TRADITION_IRON_SOUL));
		optionsByClasse.put(StandardClasse.PSION, Arrays.asList(TELEKINESIS_FOCUS, TELEPATHY_FOCUS, SHAPER_FOCUS));
		optionsByClasse.put(StandardClasse.RUNEPRIEST, Arrays.asList(RUNIC_ARTISTRY_DEFIANT_WORD, RUNIC_ARTISTRY_WRATHFUL_HAMMER));
		optionsByClasse.put(StandardClasse.SEEKER, Arrays.asList(BLOODBOND, SPIRITBOND));
		optionsByClasse.put(StandardClasse.ARTIFICIER, Arrays.asList(NONE));
		optionsByClasse.put(StandardClasse.SWORDMAGE, Arrays.asList(AEGIS_OF_ASSAULT, AEGIS_OF_SHIELDING, AEGIS_OF_ENSNAREMENT));
	}

	public static List<ClasseOption> getClasseOptions(Classe classe) {
		return optionsByClasse.get(classe);
	}

	public static ClasseOption getClasseOption(String nomOption) {
		return optionByName.get(nomOption);
	}

	private String nom;
	private List<Feature> features;

	private ClasseOption(String nom, List<Feature> features) {
		this.nom = nom;
		this.features = features;
	}

	public String getNom() {
		return nom;
	}

	public List<Feature> getFeatures() {
		return features;
	}

}
