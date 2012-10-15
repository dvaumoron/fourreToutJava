package jdr;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Race {

	DRAGONBORN (
			"Drakéide", "1,85 m à 2 m", "110 à 160 kg", new int[]{2, 0, 0, 0, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DRACONIC),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0}, // history, intimidate
			Arrays.asList(Feature.DRAGONBORN_FURY, Feature.DRACONIC_HERITAGE,
					Feature.DRAGON_BREATH)),

	DWARF (
			"Nain", "1,25 m à 1,45 m", "80 à 110 kg" , new int[]{0, 2, 0, 0, 2, 0},
			Size.MEDIUM, 5, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.DWARVEN),
			new int[]{0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // dungeoneering, endurance
			Arrays.asList(Feature.CAST_IRON_STOMACH, Feature.DWARVEN_RESILIENCE,
					Feature.DWARVEN_WEAPON_PROFICIENCY, Feature.ENCUMBERED_SPEED,
					Feature.STAND_YOUR_GROUND)),
	
	ELADRIN (
			"Eladrin", "1,65 m à 1,85 m", "65 à 90 kg" , new int[]{0, 0, 2, 2, 0, 0},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, history
			Arrays.asList(Feature.ELADRIN_EDUCATION, Feature.ELADRIN_WEAPON_PROFICIENCY,
					Feature.ELADRIN_WILL, Feature.FEY_ORIGIN, Feature.TRANCE, Feature.FEY_STEP)),

	 ELF (
			"Elfe", "1,60 m à 1,80 m", "65 à 85 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 7, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0}, // nature, perception
			Arrays.asList(Feature.ELVEN_WEAPON_PROFICIENCY, Feature.FEY_ORIGIN,
					Feature.GROUP_AWARENESS, Feature.WILD_STEP, Feature.ELVEN_ACCURACY)),

	HALF_ELF (
			"Demi-elfe", "1,65 m à 1,85 m", "65 à 85 kg" , new int[]{0, 2, 0, 0, 0, 2},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.ELVEN, Language.CHOICE),
			new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // diplomacy, insight
			Arrays.asList(Feature.DILETTANTE, Feature.DUAL_HERITAGE,
					Feature.GROUP_DIPLOMACY)),

	HALFLING (
			"Halfelin", "1,15 m à 1,25 m", "37 à 42 kg" , new int[]{0, 0, 2, 0, 0, 2},
			Size.SMALL, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}, // acrobatics, thievery
			Arrays.asList(Feature.BOLD, Feature.NIMBLE_REACTION,
					Feature.SECOND_CHANCE)),

	HUMAN_FORCE (
			"Humain(Force)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{2, 0, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),
	
	HUMAN_CONSTITUTION (
			"Humain(Constitution)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{0, 2, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),

	HUMAN_DEXTERITY (
			"Humain(Dextérité)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{0, 0, 2, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),

	HUMAN_INTELLIGENCE (
			"Humain(Intelligence)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),

	HUMAN_WISDOM (
			"Humain(Sagesse)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{0, 0, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),

	HUMAN_CHARISMA (
			"Humain(Charisme)", "1,65 m à 1,85 m", "65 à 110 kg" , new int[]{0, 0, 0, 0, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			Arrays.asList(Feature.BONUS_AT_WIL_POWER, Feature.BONUS_FEAT,
					Feature.BONUS_SKILL, Feature.HUMAN_DEFENSE_BONUSES)),

	TIEFLING (
			"Tieffelin", "1,65 m à 1,85 m", "70 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0}, // bluff, stealth
			Arrays.asList(Feature.BLOODHUNT, Feature.FIRE_RESISTANCE,
					Feature.INFERNAL_WRATH)),

	DEVA (
			"Déva", "2 m à 2,15 m", "87 à 140 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE, Language.CHOICE),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0}, // history, religion
			Arrays.asList(Feature.ASTRAL_MAJESTY, Feature.ASTRAL_RESISTANCE,
					Feature.IMMORTAL_ORIGIN, Feature.MEMORY_OF_A_THOUSAND_LIFETIMES)),

	GNOME (
			"Gnome", "1,1 m à 1,2 m", "25 à 37 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.SMALL, 5, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0}, // arcana, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.MASTER_TRICKSTER,
					Feature.REACTIVE_STEALTH, Feature.TRICKSTERS_CUNNING,
					Feature.FADE_AWAY)),

	GOLIATH_DWARVEN (
			"Goliath(Nain)", "2,4 m à 2,5 m", "140 à 170 kg" , new int[]{2, 2, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DWARVEN),
			new int[]{0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // athletics, nature
			Arrays.asList(Feature.MOUNTAINS_TENACITY, Feature.POWERFUL_ATHLETE,
					Feature.STONES_ENDURANCE)),

	GOLIATH_GIANT (
			"Goliath(Géant)", "2,4 m à 2,5 m", "140 à 170 kg" , new int[]{2, 2, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.GIANT),
			new int[]{0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // athletics, nature
			Arrays.asList(Feature.MOUNTAINS_TENACITY, Feature.POWERFUL_ATHLETE,
					Feature.STONES_ENDURANCE)),

	HALF_ORC (
			"Demi-orc", "1,9 m à 2,1 m", "77 à 112 kg" , new int[]{2, 0, 2, 0, 0, 0},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.GIANT),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // endurance, intimidate
			Arrays.asList(Feature.HALF_ORC_RESILIENCE, Feature.SWIFT_CHARGE,
					Feature.FURIOUS_ASSAULT)),

	LONGTOOTH_SHIFTER (
			"Féral longue-dent", "1,8 m à 2  m", "65 à 90 kg" , new int[]{2, 0, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // athletics, endurance
			Arrays.asList(Feature.LONGTOOTH_SHIFTING)),

	RAZORCLAW_SHIFTER (
			"Féral griffe-éfilée", "1,8 m à 2 m", "65 à 90 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 6, Vision.LOW_LIGHT,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0}, // acrobatics, stealth
			Arrays.asList(Feature.RAZORCLAW_SHIFTING)),
	
	GITHZERAI_DEXTERITY (
			"Githzerai(Dextérité)", "1,8 m à 1,95 m", "80 à 95 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH),
			new int[]{2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // acrobatics, athletics
			Arrays.asList(Feature.DANGER_SENSE, Feature.DEFENDED_MIND,
					Feature.SHIFTING_FORTUNES, Feature.IRON_MIND)),

	GITHZERAI_INTELLIGENCE (
			"Githzerai(Intelligence)", "1,8 m à 1,95 m", "80 à 95 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH),
			new int[]{2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // acrobatics, athletics
			Arrays.asList(Feature.DANGER_SENSE, Feature.DEFENDED_MIND,
					Feature.SHIFTING_FORTUNES, Feature.IRON_MIND)),

	MINOTAUR_CONSTITUTION (
			"Minotaure(Constitution)", "2,15 m à 2,3 m", "160 à 175 kg" , new int[]{2, 2, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0}, // nature, perception
			Arrays.asList(Feature.VITALITY, Feature.FEROCITY,
					Feature.HEEDLESS_CHARGE, Feature.GORING_CHARGE)),

	MINOTAUR_WISDOM (
			"Minotaure(Sagesse)", "2,15 m à 2,3 m", "160 à 175 kg" , new int[]{2, 0, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.CHOICE),
			new int[]{2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0}, // nature, perception
			Arrays.asList(Feature.VITALITY, Feature.FEROCITY,
					Feature.HEEDLESS_CHARGE, Feature.GORING_CHARGE)),

	SHARDMIND_WISDOM_ACROBATICS (
			"Cristallien(Sagesse, Acrobaties)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{2, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_ATHLETICS (
			"Cristallien(Sagesse, Athlétisme)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_BLUFF (
			"Cristallien(Sagesse, Bluff)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_DIPLOMACY (
			"Cristallien(Sagesse, Diplomatie)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_DUNGEONEERING (
			"Cristallien(Sagesse, Exploration)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_HEAL (
			"Cristallien(Sagesse, Soins)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_HISTORY (
			"Cristallien(Sagesse, Histoire)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_INSIGHT (
			"Cristallien(Sagesse, Intuition)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_INTIMIDATE (
			"Cristallien(Sagesse, Acrobaties)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_NATURE (
			"Cristallien(Sagesse, Nature)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_PERCEPTION (
			"Cristallien(Sagesse, Perception)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_RELIGION (
			"Cristallien(Sagesse, Religion)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_STEALTH (
			"Cristallien(Sagesse, Discrétion)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_STREETWISE (
			"Cristallien(Sagesse, Connais. de la rue)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_WISDOM_THIEVERY (
			"Cristallien(Sagesse, Larcin)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_ACROBATICS (
			"Cristallien(Charisme, Acrobaties)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{2, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_ATHLETICS (
			"Cristallien(Charisme, Athlétisme)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_BLUFF (
			"Cristallien(Charisme, Bluff)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_DIPLOMACY (
			"Cristallien(Charisme, Diplomatie)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_DUNGEONEERING (
			"Cristallien(Charisme, Exploration)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_HEAL (
			"Cristallien(Charisme, Soins)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_HISTORY (
			"Cristallien(Charisme, Histoire)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_INSIGHT (
			"Cristallien(Charisme, Intuition)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_INTIMIDATE (
			"Cristallien(Charisme, Intimidation)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_NATURE (
			"Cristallien(Charisme, Nature)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_PERCEPTION (
			"Cristallien(Charisme, Perception)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_RELIGION (
			"Cristallien(Charisme, Religion)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_STEALTH (
			"Cristallien(Charisme, Discretion)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_STREETWISE (
			"Cristallien(Charisme, Connais. de la rue)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	SHARDMIND_CHARISMA_THIEVERY (
			"Cristallien(Charisme, Larcin)", "1,7 m à 1,85 m", "90 à 115 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DEEP_SPEECH, Language.CHOICE),
			new int[]{0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}, // arcana, endurance, one other
			Arrays.asList(Feature.TELEPATHY, Feature.CRYSTALLINE_MIND,
					Feature.LIVING_CONSTRUCT, Feature.IMMORTAL_ORIGIN,
					Feature.SHARD_SWARM)),

	WILDEN_CONSTITUTION_FORTITUDE (
			"Sylvien(Constitution, Vigueur)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 2, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_FORTITUDE,
					Feature.NATURES_ASPECT)),

	WILDEN_CONSTITUTION_REFLEX (
			"Sylvien(Constitution, Reflexe)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 2, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_REFLEX,
					Feature.NATURES_ASPECT)),

	WILDEN_CONSTITUTION_WILL (
			"Sylvien(Constitution, Volonté)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 2, 0, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_WILL,
					Feature.NATURES_ASPECT)),

	WILDEN_DEXTERITY_FORTITUDE (
			"Sylvien(Dextérité, Vigueur)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_FORTITUDE,
					Feature.NATURES_ASPECT)),

	WILDEN_DEXTERITY_REFLEX (
			"Sylvien(Dextérité, Reflexe)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_REFLEX,
					Feature.NATURES_ASPECT)),

	WILDEN_DEXTERITY_WILL (
			"Sylvien(Dextérité, Volonté)", "1,8 m à 1,95 m", "70 à 85 kg" , new int[]{0, 0, 2, 0, 2, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0}, // nature, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.HARDY_FORM_WILL,
					Feature.NATURES_ASPECT)),

	CHANGELING_DEXTERITY (
			"Changelin (Dextérité)", "1,65 m à 1,8 m", "60 à 80 kg" , new int[]{0, 0, 2, 0, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON),
			new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // bluff, insight
			Arrays.asList(Feature.SHAPECHANGER, Feature.MENTAL_DEFENSE,
					Feature.CHANGE_SHAPE, Feature.CHANGELING_TRICK)),

	CHANGELING_INTELLIGENCE (
			"Changelin (Intelligence)", "1,65 m à 1,8 m", "60 à 80 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON),
			new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // bluff, insight
			Arrays.asList(Feature.SHAPECHANGER, Feature.MENTAL_DEFENSE,
					Feature.CHANGE_SHAPE, Feature.CHANGELING_TRICK)),

	KALASHTAR_ACROBATICS (
			"Kalashtar (Acrobaties)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_ARCANA (
			"Kalashtar (Arcanes)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_ATHLETICS (
			"Kalashtar (Athlétisme)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_BLUFF (
			"Kalashtar (Bluff)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_DIPLOMACY (
			"Kalashtar (Diplomatie)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_DUNGEONEERING (
			"Kalashtar (Exploration)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_ENDURANCE (
			"Kalashtar (Endurance)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_HEAL (
			"Kalashtar (Soins)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_HISTORY (
			"Kalashtar (Histoire)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_INTIMIDATE (
			"Kalashtar (Intimidation)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_NATURE (
			"Kalashtar (Nature)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_PERCEPTION (
			"Kalashtar (Perception)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_RELIGION (
			"Kalashtar (Religion)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_STEALTH (
			"Kalashtar (Discrétion)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_STREETWISE (
			"Kalashtar (Connais. de la rue)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	KALASHTAR_THIEVERY (
			"Kalashtar (Larcin)", "1,7 m à 1,9 m", "65 à 110 kg" , new int[]{0, 0, 0, 2, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.TELEPATHY_5),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2}, // insight, one other
			Arrays.asList(Feature.DUAL_SOUL, Feature.BASTION_OF_MENTAL_CLARITY)),

	WARFORGED (
			"Forgelier", "1,8 m à 1,95 m", "135 à 150 kg" , new int[]{2, 2, 0, 0, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // endurance, intimidate
			Arrays.asList(Feature.LIVING_CONSTRUCT, Feature.UNSLEEPING_WATCHER,
					Feature.WARFORGED_RESILIENCE, Feature.WARFORGED_MIND,
					Feature.WARFORGED_RESOLVE)),
	DROW (
			"Drow", "1,6 m à 1,8 m", "65 à 85 kg" , new int[]{0, 0, 2, 0, 0, 2},
			Size.MEDIUM, 6, Vision.DARKVISION,
			Arrays.asList(Language.COMMON, Language.ELVEN),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0}, // intimidate, stealth
			Arrays.asList(Feature.FEY_ORIGIN, Feature.TRANCE, Feature.LOLTHTOUCHED)),

	GENASI_EARTHSOUL (
			"Genasi (âme de terre)", "1,7 m à 1,85 m", "65 à 112 kg" , new int[]{2, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.PRIMORDIAL),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // endurance, nature
			Arrays.asList(Feature.ELEMENTAL_ORIGIN, Feature.ELEMENTAL_MANIFESTATION_EARTHSOUL)),

	GENASI_FIRESOUL (
			"Genasi (âme de feu)", "1,7 m à 1,85 m", "65 à 112 kg" , new int[]{2, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.PRIMORDIAL),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // endurance, nature
			Arrays.asList(Feature.ELEMENTAL_ORIGIN, Feature.ELEMENTAL_MANIFESTATION_FIRESOUL)),

	GENASI_STORMSOUL (
			"Genasi (âme d'orage)", "1,7 m à 1,85 m", "65 à 112 kg" , new int[]{2, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.PRIMORDIAL),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // endurance, nature
			Arrays.asList(Feature.ELEMENTAL_ORIGIN, Feature.ELEMENTAL_MANIFESTATION_STORMSOUL)),

	GENASI_WATERSOUL (
			"Genasi (âme d'eau)", "1,7 m à 1,85 m", "65 à 112 kg" , new int[]{2, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.PRIMORDIAL),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // endurance, nature
			Arrays.asList(Feature.ELEMENTAL_ORIGIN, Feature.ELEMENTAL_MANIFESTATION_WATERSOUL)),

	GENASI_WINDSOUL (
			"Genasi (âme de vent)", "1,7 m à 1,85 m", "65 à 112 kg" , new int[]{2, 0, 0, 2, 0, 0},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.PRIMORDIAL),
			new int[]{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0}, // endurance, nature
			Arrays.asList(Feature.ELEMENTAL_ORIGIN, Feature.ELEMENTAL_MANIFESTATION_WINDSOUL));

	private static final Map<String, Race> raceByName = new LinkedHashMap<String, Race>();

	static {
		for(Race race : Race.values()) {
			raceByName.put(race.getNom(), race);
		}
	}

	public static Collection<String> getRaceNameList() {
		return raceByName.keySet();
	}

	public static Race getRaceByName(String nomRace) {
		return raceByName.get(nomRace);
	}

	private String nom;
	private String averageHeight;
	private String averageWeight;
	private int[] abilityBonus;
	private Size size;
	private int speed;
	private Vision vision;
	private List<Language> languages;
	private int[] skillBonus;
	private List<Feature> raceFeatures;

	private Race(String nom, String averageHeight, String averageWeight,
			int[] abilityBonus, Size size, int speed, Vision vision,
			List<Language> languages, int[] skillBonus, List<Feature> raceFeatures) {
		this.nom = nom;
		this.averageHeight = averageHeight;
		this.averageWeight = averageWeight;
		this.abilityBonus = abilityBonus;
		this.size = size;
		this.speed = speed;
		this.vision = vision;
		this.languages = languages;
		this.skillBonus = skillBonus;
		this.raceFeatures = raceFeatures;
	}

	public String getNom() {
		return nom;
	}

	public String getAverageHeight() {
		return averageHeight;
	}

	public String getAverageWeight() {
		return averageWeight;
	}

	public int[] getAbilityBonus() {
		return abilityBonus;
	}

	public Size getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public Vision getVision() {
		return vision;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public int[] getSkillBonus() {
		return skillBonus;
	}

	public List<Feature> getRaceFeatures() {
		return raceFeatures;
	}

}
