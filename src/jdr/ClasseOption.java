package jdr;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ClasseOption {

	NONE("Aucune", Collections.<Feature>emptyList()),

	// fighter option
	FIGHTER_WEAPON_TALENT_ONE_HANDED("Arme à une main", Arrays.asList(Feature.FIGHTER_WEAPON_TALENT_ONE_HANDED)),
	FIGHTER_WEAPON_TALENT_TWO_HANDED("Arme à deux mains", Arrays.asList(Feature.FIGHTER_WEAPON_TALENT_TWO_HANDED)),

	// ranger option
	ARCHER_FIGHTING_STYLE("Combat à Distance", Arrays.asList(Feature.DEFENSIVE_MOBILITY)),
	TWO_BLADE_FIGHTING_STYLE("Combat à deux armes", Arrays.asList(Feature.TOUGHTNESS, Feature.ONE_HANDED_IN_OFFHAND)),

	// rogue option
	ROGUE_TACTICS_ARTFUL_DODGER("Esthète de l'esquive", Arrays.asList(Feature.ROGUE_TACTICS_ARTFUL_DODGER)),
	ROGUE_TACTICS_BRUTAL_SCOUNDREL("Brute des bas-fonds", Arrays.asList(Feature.ROGUE_TACTICS_BRUTAL_SCOUNDREL)),

	// warlock option
	FEY_PACT("Pacte féerique", Arrays.asList(Feature.FEY_PACT)),
	INFERNAL_PACT("Pacte infernal", Arrays.asList(Feature.INFERNAL_PACT)),
	STAR_PACT("Pacte stellaire", Arrays.asList(Feature.STAR_PACT)),

	// warlord option
	INSPIRING_PRESENCE("Présence inspiratrice", Arrays.asList(Feature.INSPIRING_PRESENCE)),
	TACTICAL_PRESENCE("Présence tactique",  Arrays.asList(Feature.TACTICAL_PRESENCE)),

	// wizard option
	ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION("Orbe du chatiment", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION)),
	ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE("Bâton de défense", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE)),
	ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY("Baguette de précision", Arrays.asList(Feature.ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY)),

	// avenger option
	CENSURE_OF_PURSUIT("Censure de poursuite", Arrays.asList(Feature.CENSURE_OF_PURSUIT)),
	CENSURE_OF_RETRIBUTION("Censure rétributive", Arrays.asList(Feature.CENSURE_OF_RETRIBUTION)),

	// barbarian option
	FERAL_MIGHT_RAGEBLOOD_VIGOR("Ardeur sanguinaire", Arrays.asList(Feature.FERAL_MIGHT_RAGEBLOOD_VIGOR)),
	FERAL_MIGHT_THANEBORN_TRIUMPH("Triomphe du champion", Arrays.asList(Feature.FERAL_MIGHT_THANEBORN_TRIUMPH)),
	
	// bard option
	VIRTUE_OF_CUNNING("Vertu de ruse", Arrays.asList(Feature.VIRTUE_OF_CUNNING)),
	VIRTUE_OF_VALOR("Vertu de vaillance", Arrays.asList(Feature.VIRTUE_OF_VALOR)),

	// druid option
	PRIMAL_GUARDIAN("Guardien primal", Arrays.asList(Feature.PRIMAL_GUARDIAN)), 
	PRIMAL_PREDATOR("Prédateur primal", Arrays.asList(Feature.PRIMAL_PREDATOR)),

	// invoker option
	COVENANT_OF_PRESERVATION("Alliance protectrice", Arrays.asList(Feature.COVENANT_OF_PRESERVATION)),
	COVENANT_OF_WRATH("Alliance de colère", Arrays.asList(Feature.COVENANT_OF_WRATH)),

	// shaman option
	PROTECTOR_SPIRIT("Esprit protecteur", Arrays.asList(Feature.PROTECTOR_SPIRIT)),
	STALKER_SPIRIT("Esprit prédateur", Arrays.asList(Feature.STALKER_SPIRIT)),

	// sorcerer option
	SPELL_SOURCE_DRAGON_MAGIC("Magie draconique", Arrays.asList(Feature.SPELL_SOURCE_DRAGON_MAGIC)),
	SPELL_SOURCE_WILD_MAGIC("Magie sauvage", Arrays.asList(Feature.SPELL_SOURCE_WILD_MAGIC)),

	// warden option
	GUARDIAN_MIGHT_EARTHSTRENGTH("Force de la terre",  Arrays.asList(Feature.GUARDIAN_MIGHT_EARTHSTRENGTH)),
	GUARDIAN_MIGHT_WILDBLOOD("Sang indompté",  Arrays.asList(Feature.GUARDIAN_MIGHT_WILDBLOOD)),

	// ardent option
	MANTLE_OF_CLARITY("Manteau de clarté", Arrays.asList(Feature.MANTLE_OF_CLARITY)),
	MANTLE_OF_ELATION("Manteau d'allégresse", Arrays.asList(Feature.MANTLE_OF_ELATION)),

	// battlemind option
	PSIONIC_STUDY_BATTLE_RESILIENCE("Résistance de combat", Arrays.asList(Feature.PSIONIC_STUDY_BATTLE_RESILIENCE)),
	PSIONIC_STUDY_SPEED_OF_THOUGHT("Vitesse de la pensée", Arrays.asList(Feature.PSIONIC_STUDY_SPEED_OF_THOUGHT)),

	// monk option
	MONASTIC_TRADITION_CENTERED_BREATH("Souffle centrée", Arrays.asList(Feature.MONASTIC_TRADITION_CENTERED_BREATH)),
	MONASTIC_TRADITION_STONE_FIST("Poing de pierre", Arrays.asList(Feature.MONASTIC_TRADITION_STONE_FIST)),

	// psion option
	TELEKINESIS_FOCUS("Spécialisation en télékinésie", Arrays.asList(Feature.TELEKINESIS_FOCUS)),
	TELEPATHY_FOCUS("Spécialisation en télépathie", Arrays.asList(Feature.TELEPATHY_FOCUS)),

	// runepriest option
	RUNIC_ARTISTRY_DEFIANT_WORD("Parole de défi", Arrays.asList(Feature.RUNIC_ARTISTRY_DEFIANT_WORD)),
	RUNIC_ARTISTRY_WRATHFUL_HAMMER("Marteau de l'ire", Arrays.asList(Feature.RUNIC_ARTISTRY_WRATHFUL_HAMMER)),

	// seeker option
	BLOODBOND("Lien du sang", Arrays.asList(Feature.BLOODBOND)),
	SPIRITBOND("Lien désincarné", Arrays.asList(Feature.SPIRITBOND)),

	// swordmage option
	AEGIS_OF_ASSAULT("Egide offensive", Arrays.asList(Feature.AEGIS_OF_ASSAULT)),
	AEGIS_OF_SHIELDING("Egide défensive", Arrays.asList(Feature.AEGIS_OF_SHIELDING));

	private static final Map<String, ClasseOption> optionByName = new LinkedHashMap<String, ClasseOption>();
	private static final Map<Classe, List<ClasseOption>> optionsByClasse = new LinkedHashMap<Classe, List<ClasseOption>>();

	static {
		for (ClasseOption option : ClasseOption.values()) {
			optionByName.put(option.getNom(), option);
		}

		optionsByClasse.put(StandardClasse.CLERIC, Arrays.asList(NONE));
		optionsByClasse.put(StandardClasse.FIGHTER, Arrays.asList(FIGHTER_WEAPON_TALENT_ONE_HANDED, FIGHTER_WEAPON_TALENT_TWO_HANDED));
		optionsByClasse.put(StandardClasse.PALADIN, Arrays.asList(NONE));
		List<ClasseOption> rangerOption = Arrays.asList(ARCHER_FIGHTING_STYLE, TWO_BLADE_FIGHTING_STYLE);
		optionsByClasse.put(StandardClasse.RANGER_DUNGEONEERING, rangerOption);
		optionsByClasse.put(StandardClasse.RANGER_NATURE, rangerOption);
		optionsByClasse.put(StandardClasse.ROGUE, Arrays.asList(ROGUE_TACTICS_ARTFUL_DODGER, ROGUE_TACTICS_BRUTAL_SCOUNDREL));
		optionsByClasse.put(StandardClasse.WARLOCK, Arrays.asList(FEY_PACT, INFERNAL_PACT, STAR_PACT));
		optionsByClasse.put(StandardClasse.WARLORD, Arrays.asList(INSPIRING_PRESENCE, TACTICAL_PRESENCE));
		optionsByClasse.put(StandardClasse.WIZARD, Arrays.asList(ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION, ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE, ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY));
		optionsByClasse.put(StandardClasse.AVENGER, Arrays.asList(CENSURE_OF_PURSUIT, CENSURE_OF_RETRIBUTION));
		optionsByClasse.put(StandardClasse.BARBARIAN, Arrays.asList(FERAL_MIGHT_RAGEBLOOD_VIGOR, FERAL_MIGHT_THANEBORN_TRIUMPH));
		optionsByClasse.put(StandardClasse.BARD, Arrays.asList(VIRTUE_OF_CUNNING, VIRTUE_OF_VALOR));
		optionsByClasse.put(StandardClasse.DRUID, Arrays.asList(PRIMAL_GUARDIAN, PRIMAL_PREDATOR));
		optionsByClasse.put(StandardClasse.INVOKER, Arrays.asList(COVENANT_OF_PRESERVATION, COVENANT_OF_WRATH));
		optionsByClasse.put(StandardClasse.SHAMAN, Arrays.asList(PROTECTOR_SPIRIT, STALKER_SPIRIT));
		optionsByClasse.put(StandardClasse.SORCERER, Arrays.asList(SPELL_SOURCE_DRAGON_MAGIC, SPELL_SOURCE_WILD_MAGIC));
		optionsByClasse.put(StandardClasse.WARDEN, Arrays.asList(GUARDIAN_MIGHT_EARTHSTRENGTH, GUARDIAN_MIGHT_WILDBLOOD));
		optionsByClasse.put(StandardClasse.ARDENT, Arrays.asList(MANTLE_OF_CLARITY, MANTLE_OF_ELATION));
		optionsByClasse.put(StandardClasse.BATTLEMIND, Arrays.asList(PSIONIC_STUDY_BATTLE_RESILIENCE, PSIONIC_STUDY_SPEED_OF_THOUGHT));
		optionsByClasse.put(StandardClasse.MONK, Arrays.asList(MONASTIC_TRADITION_CENTERED_BREATH, MONASTIC_TRADITION_STONE_FIST));
		optionsByClasse.put(StandardClasse.PSION, Arrays.asList(TELEKINESIS_FOCUS, TELEPATHY_FOCUS));
		optionsByClasse.put(StandardClasse.RUNEPRIEST, Arrays.asList(RUNIC_ARTISTRY_DEFIANT_WORD, RUNIC_ARTISTRY_WRATHFUL_HAMMER));
		optionsByClasse.put(StandardClasse.SEEKER, Arrays.asList(BLOODBOND, SPIRITBOND));
		optionsByClasse.put(StandardClasse.ARTIFICIER, Arrays.asList(NONE));
		optionsByClasse.put(StandardClasse.SWORDMAGE, Arrays.asList(AEGIS_OF_ASSAULT, AEGIS_OF_SHIELDING));		
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
