package jdr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Feature {

	// cleric feature
	CHANEL_DIVINITY,
	CHANNEL_DIVINITY_TURN_UNDEAD,
	CHANNEL_DIVINITY_HEALERS_MERCY,
	HEALERS_LORE,
	HEALING_WORD,
	RITUAL_CASTING,
	HEALING_WORD_HYBRID,

	// fighter feature
	COMBAT_CHALLENGE,
	COMBAT_SUPERIORITY,
	COMBAT_AGILITY,
	FIGHTER_WEAPON_TALENT,
	FIGHTER_WEAPON_TALENT_ONE_HANDED {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().isTwoHanded()) {
				bonus = 0;
			} else {
				bonus = 1;
			}
			return bonus;
		}
		public int getAttaqueGaucheBonus(Personnage personnage) {
			return 1;
		}
	},
	FIGHTER_WEAPON_TALENT_TWO_HANDED {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().isTwoHanded()) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	BATTLERAGER_VIGOR,
	TEMPEST_TECHNIQUE,
	BRAWLER_STYLE {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.UNARMED_ATTACK)) {
				bonus = 2 * getTiers(personnage.getNiveau());
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getAttaqueGaucheBonus(Personnage personnage) {
			int bonus;
			if (personnage.getLeftWeapon().equals(Weapon.UNARMED_ATTACK)) {
				bonus = 2 * getTiers(personnage.getNiveau());
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (hasBrawlerStyleBonus(personnage)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getVigueurBonus(Personnage personnage) {
			int bonus;
			if (hasBrawlerStyleBonus(personnage)) {
				bonus = 2;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		private boolean hasBrawlerStyleBonus(Personnage personnage) {
			return !personnage.getWeapon().isTwoHanded()
					&& personnage.getLeftWeapon().equals(Weapon.UNARMED_ATTACK)
					&& personnage.getShield().equals(Shield.NONE);
		}
	},
	COMBAT_CHALLENGE_HYBRID,

	// paladin feature
	DIVINE_CHALLENGE,
	LAY_ON_HANDS,
	ARDENT_VOW,
	VIRTUES_TOUCH,
	DIVINE_CHALLENGE_HYBRID,

	// ranger feature
	FIGHTING_STYLE,
	ONE_HANDED_IN_OFFHAND {
		public boolean isOneHandedInOffhand() {
			return true;
		}
	},
	HUNTERS_QUARRY,
	PRIME_SHOT,
	BEAST_MASTERY,
	HUNTER_FIGHTING_STYLE,
	RUNNING_ATTACK,
	MARAUDER_FIGHTING_STYLE {
		@Override
		public int getSpeedBonus(Personnage personnage) {
			int bonus;
			if (personnage.getShield().equals(Shield.NONE)
					&& !personnage.getWeapon().isTwoHanded()) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	HUNTERS_QUARRY_HYBRID,

	// rogue feature
	FIRST_STRIKE,
	ROGUE_TACTICS,
	ROGUE_TACTICS_ARTFUL_DODGER,
	ROGUE_TACTICS_BRUTAL_SCOUNDREL,
	ROGUE_TACTICS_RUTHLESS_RUFFIAN {
		public List<Weapon> getWeaponProficiencies() {
			return Arrays.asList(Weapon.CLUB, Weapon.MACE);
		}
	},
	ROGUE_TACTICS_CUNNING_SNEAK,
	ROGUE_WEAPON_TALENT {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.DAGGER)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getAttaqueGaucheBonus(Personnage personnage) {
			int bonus;
			if (personnage.getLeftWeapon().equals(Weapon.DAGGER)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getDiceSizeBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.SHURIKEN)) {
				bonus = 2;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getDiceSizeGaucheBonus(Personnage personnage) {
			int bonus;
			if (personnage.getLeftWeapon().equals(Weapon.SHURIKEN)) {
				bonus = 2;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	SHARPSHOOTER_TALENT {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			List<WeaponGroup> group = personnage.getWeapon().getGroup();
			if (group.contains(WeaponGroup.CROSSBOW)
					|| group.contains(WeaponGroup.SLING)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getAttaqueGaucheBonus(Personnage personnage) {
			int bonus;
			List<WeaponGroup> group = personnage.getLeftWeapon().getGroup();
			if (group.contains(WeaponGroup.CROSSBOW)
					|| group.contains(WeaponGroup.SLING)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	SNEAK_ATTACK,
	SNEAK_ATTACK_HYBRID,

	// warlock feature
	ELDRITCH_BLAST,
	ELDRITCH_PACT,
	FEY_PACT,
	INFERNAL_PACT,
	STAR_PACT,
	VESTIGE_PACT,
	DARK_PACT,
	SHADOW_WALK,
	WARLOCKS_CURSE,
	ELDRITCH_PACT_HYBRID,
	WARLOCKS_CURSE_HYBRID,

	// warlord feature
	COMBAT_LEADER {
		public int getInitiativeBonus(Personnage personnage) {
			return 2;
		}
	},
	COMMANDING_PRESENCE,
	INSPIRING_PRESENCE,
	TACTICAL_PRESENCE,
	BRAVURA_PRESENCE,
	RESOURCEFUL_PRESENCE,
	INSIGHTFUL_PRESENCE,
	SKIRMISHING_PRESENCE,
	INSPIRING_WORD,
	INSPIRING_WORD_HYBRID,
	WARLORD_LEADERSHIP,

	// wizard feature
	ARCANE_IMPLEMENT_MASTERY,
	ARCANE_IMPLEMENT_MASTERY_ORB_OF_IMPOSITION,
	ARCANE_IMPLEMENT_MASTERY_STAFF_OF_DEFENSE {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.QUARTERSTAFF)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	ARCANE_IMPLEMENT_MASTERY_WAND_OF_ACCURACY,
	ARCANE_IMPLEMENT_MASTERY_ORB_OF_DECEPTION,
	ARCANE_IMPLEMENT_MASTERY_TOME_OF_BINDING,
	ARCANE_IMPLEMENT_MASTERY_TOME_OF_READINESS,
	CANTRIPS,
	SPELLBOOK,

	// avenger feature
	ARMOR_OF_FAITH {
		public int getCABonus(Personnage personnage) {
			int caBonus;
			if (personnage.getArmor().getArmorType().isHeavy()
					|| !personnage.getShield().equals(Shield.NONE)) {
				caBonus = 0;
			} else {
				// if not wearing heavy armor nor shield
				caBonus = 3;
			}
			return caBonus; 
		}
	},
	AVENGERS_CENSURE,
	CENSURE_OF_PURSUIT,
	CENSURE_OF_RETRIBUTION,
	CENSURE_OF_UNITY,
	OATH_OF_ENMMITY,
	OATH_OF_ENMMITY_HYBRID,

	// barbarian feature
	BARBARIAN_AGILITY{
		public int getCABonus(Personnage personnage) {
			return getBarbarianAgilityBonus(personnage);
		}

		public int getReflexeBonus(Personnage personnage) {
			return getBarbarianAgilityBonus(personnage);
		}

		private int getBarbarianAgilityBonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				 // if not wearing heavy armor
				bonus = getTiers(personnage.getNiveau());
			}
			return bonus;
		}
	},
	FERAL_MIGHT,
	FERAL_MIGHT_RAGEBLOOD_VIGOR,
	FERAL_MIGHT_THANEBORN_TRIUMPH,
	FERAL_MIGHT_THUNDERBORN_WRATH,
	FERAL_MIGHT_WHIRLWIND_SLAYER,
	RAGE_STRIKE,
	RAMPAGE,

	// bard feature
	BARDIC_TRAINING,
	BARDIC_VIRTUE,
	VIRTUE_OF_CUNNING,
	VIRTUE_OF_VALOR,
	VIRTUE_OF_PRESCIENCE,
	MAJESTIC_WORD,
	MULTICLASS_VERSATILITY,
	SKILL_VERSATILITY,
	SONG_OF_REST,
	WORDS_OF_FRIENDSHIP,
	MAJESTIC_WORD_HYBRID,

	// druid feature
	BALANCE_OF_NATURE,
	PRIMAL_ASPECT,
	PRIMAL_GUARDIAN {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getConstitutionMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}

	},
	PRIMAL_PREDATOR {
		public int getSpeedBonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				bonus = 1;
			}
			return bonus;
		}
	},
	PRIMAL_SWARM,
	WILD_SHAPE,

	// invoker feature
	DIVINE_COVENANT,
	COVENANT_OF_PRESERVATION,
	COVENANT_OF_WRATH,
	COVENANT_OF_MALEDICTION,
	COVENANT_MANIFESTATION,

	// shaman feature
	COMPANION_SPIRIT,
	PROTECTOR_SPIRIT,
	STALKER_SPIRIT,
	WATCHER_SPIRIT,
	WORLD_SPEAKER_SPIRIT,
	HEALING_SPIRIT,
	SPEAK_WITH_SPIRITS,
	COMPANION_SPIRIT_HYBRID,
	HEALING_SPIRIT_HYBRID,

	// sorcerer feature
	SPELL_SOURCE,
	SPELL_SOURCE_DRAGON_MAGIC {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getForceMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}

		public int getDegatBonus(Personnage personnage) {
			return personnage.getForceMod()
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
		}
		public int getDegatGaucheBonus(Personnage personnage) {
			return getDegatBonus(personnage);
		}
	},
	SPELL_SOURCE_WILD_MAGIC {
		public int getDegatBonus(Personnage personnage) {
			return personnage.getDexteriteMod()
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
		}
		public int getDegatGaucheBonus(Personnage personnage) {
			return getDegatBonus(personnage);
		}
	},
	SPELL_SOURCE_STORM_MAGIC {
		public int getDegatBonus(Personnage personnage) {
			return personnage.getDexteriteMod()
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
		}
		public int getDegatGaucheBonus(Personnage personnage) {
			return getDegatBonus(personnage);
		}
	},
	SPELL_SOURCE_COSMIC_MAGIC {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getForceMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}

		public int getDegatBonus(Personnage personnage) {
			return personnage.getForceMod()
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
		}
		public int getDegatGaucheBonus(Personnage personnage) {
			return getDegatBonus(personnage);
		}
	},
	SORCEROUS_POWER {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getForceMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}

		public int getDegatBonus(Personnage personnage) {
			return Math.max(personnage.getForceMod(), personnage.getDexteriteMod())
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
		}
		public int getDegatGaucheBonus(Personnage personnage) {
			return getDegatBonus(personnage);
		}
	},

	// warden feature
	FONT_OF_LIFE, 
	GUARDIAN_MIGHT,
	GUARDIAN_MIGHT_EARTHSTRENGTH {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getConstitutionMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}
	},
	GUARDIAN_MIGHT_WILDBLOOD {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getSagesseMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}
	},
	GUARDIAN_MIGHT_LIFESPIRIT {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getSagesseMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}
	},
	GUARDIAN_MIGHT_STORMHEART {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getConstitutionMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}
	},
	NATURES_WRATH,
	NATURES_WRATH_HYBRID,

	// ardent feature
	ARDENT_MANTLE,
	MANTLE_OF_CLARITY,
	MANTLE_OF_ELATION,
	MANTLE_OF_IMPULSIVENESS,
	ARDENT_SURGE,
	PSIONIC_AUGMENTATION,
	ARDENT_MANTLE_HYBRID,
	ARDENT_SURGE_HYBRID,
	PSIONIC_AUGMENTATION_HYBRID,

	// battlemind feature
	PSIONIC_DEFENSE,
	PSIONIC_STUDY,
	PSIONIC_STUDY_BATTLE_RESILIENCE,
	PSIONIC_STUDY_SPEED_OF_THOUGHT,
	PSIONIC_STUDY_PERSISTENT_HARRIER,
	PSIONIC_DEFENSE_HYBRID,

	// monk feature
	MONASTIC_TRADITION,
	MONASTIC_TRADITION_CENTERED_BREATH {
		public int getVigueurBonus(Personnage personnage) {
			return getTiers(personnage.getNiveau());
		}
	},
	MONASTIC_TRADITION_STONE_FIST {
		public int getVolonteBonus(Personnage personnage) {
			return getTiers(personnage.getNiveau());
		}
	},
	MONASTIC_TRADITION_IRON_SOUL {
		public int getCABonus(Personnage personnage) {
			int bonus;
			Weapon weapon = personnage.getWeapon();
			if (weapon.equals(Weapon.UNARMED_ATTACK)
					|| weapon.equals(Weapon.MONK_UNARMED_STRIKE)) {
				bonus = 0;
			} else {
				bonus = 1;
			}
			return bonus;
		}
	},
	UNARMED_COMBATANT,
	UNARMORED_DEFENSE {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().equals(ArmorType.CLOTH)
					&& personnage.getShield().equals(Shield.NONE)) {
				// if wearing cloth armor or no armor and no shield
				bonus = 2;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	MONASTIC_TRADITION_HYBRID,

	// psion feature
	DISCIPLINE_FOCUS,
	TELEKINESIS_FOCUS,
	TELEPATHY_FOCUS,
	SHAPER_FOCUS,
	DISCIPLINE_FOCUS_HYBRID,

	// runepriest feature
	RUNE_MASTER,
	RUNE_OF_MENDING,
	RUNIC_ARTISTRY,
	RUNIC_ARTISTRY_DEFIANT_WORD,
	RUNIC_ARTISTRY_WRATHFUL_HAMMER {
		public List<Weapon> getWeaponProficiencies() {
			return Arrays.asList(Weapon.THROWING_HAMMER, Weapon.WARHAMMER, Weapon.MAUL);
		}
	},
	RUNE_OF_MENDING_HYBRID,

	// seeker feature
	INEVITABLE_SHOT,
	SEEKERS_BOND,
	BLOODBOND,
	SPIRITBOND {
		public int getAttaqueBonus(Personnage personnage) {
			int bonus;
			List<WeaponProperty> properties = personnage.getWeapon().getProperties();
			if (properties.contains(WeaponProperty.HEAVY_THROWN)
					|| properties.contains(WeaponProperty.LIGHT_THROWN)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getAttaqueGaucheBonus(Personnage personnage) {
			int bonus;
			List<WeaponProperty> properties = personnage.getLeftWeapon().getProperties();
			if (properties.contains(WeaponProperty.HEAVY_THROWN)
					|| properties.contains(WeaponProperty.LIGHT_THROWN)) {
				bonus = 1;
			} else {
				bonus = 0;
			}
			return bonus;
		}
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getArmor().getArmorType().isHeavy()) {
				bonus = 0;
			} else {
				int maxDexInt = Math.max(personnage.getDexteriteMod(),
						personnage.getIntelligenceMod());
				bonus = Math.max(personnage.getForceMod(), maxDexInt)
						- maxDexInt;
			}
			return bonus;
		}
	},
	INEVITABLE_SHOT_HYBRID,
	SEEKERS_BOND_HYBRID,

	// artificier feature
	ARCANE_EMPOWERMENT,
	ARCANE_REJUVENATION,
	HEALING_INFUSION,
	HEALING_INFUSION_HYBRID,

	// swordmage feature
	SWORDBOND,
	SWORDMAGE_AEGIS,
	AEGIS_OF_ASSAULT,
	AEGIS_OF_SHIELDING,
	AEGIS_OF_ENSNAREMENT,
	SWORDMAGE_WARDING {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getShield().equals(Shield.NONE)
					&& !personnage.getWeapon().isTwoHanded()
					&& personnage.getLeftWeapon().equals(Weapon.UNARMED_ATTACK)) {
				// if one hand free
				bonus = 3;
			} else {
				bonus = 1;
			}
			return bonus;
		}
	},
	SWORDMAGE_AEGIS_HYBRID,

	// dragonborn feature
	DRAGONBORN_FURY,
	DRACONIC_HERITAGE {
		public int getSurgeValueBonus(Personnage personnage) {
			return personnage.getConstitutionMod();
		}
	},
	DRAGON_BREATH,

	// dwarf feature
	CAST_IRON_STOMACH,
	DWARVEN_RESILIENCE,
	DWARVEN_WEAPON_PROFICIENCY {
		public List<Weapon> getWeaponProficiencies() {
			return Arrays.asList(Weapon.THROWING_HAMMER, Weapon.WARHAMMER);
		}
	},
	ENCUMBERED_SPEED {
		public int getSpeedBonus(Personnage personnage) {
			int bonus;
			ArmorType armorType = personnage.getArmor().getArmorType();
			if (armorType.isHeavy()) {
				bonus = -armorType.getSpeed();
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	STAND_YOUR_GROUND,

	// eladrin feature
	ELADRIN_EDUCATION,
	ELADRIN_WEAPON_PROFICIENCY {
		public List<Weapon> getWeaponProficiencies() {
			return Arrays.asList(Weapon.LONGSWORD);
		}
	},
	ELADRIN_WILL {
		public int getVolonteBonus(Personnage personnage) {
			return 1;
		}
	},
	FEY_ORIGIN,
	TRANCE,
	FEY_STEP,

	// elf feature
	ELVEN_WEAPON_PROFICIENCY {
		public List<Weapon> getWeaponProficiencies() {
			return Arrays.asList(Weapon.LONGBOW, Weapon.SHORTBOW);
		}
	},
	GROUP_AWARENESS,
	WILD_STEP, 
	ELVEN_ACCURACY,

	// half-elf feature
	DILETTANTE,
	DUAL_HERITAGE,
	GROUP_DIPLOMACY,

	// halfling feature
	BOLD,
	NIMBLE_REACTION,
	SECOND_CHANCE,

	// human feature
	BONUS_AT_WIL_POWER,
	BONUS_FEAT,
	BONUS_SKILL,
	HUMAN_DEFENSE_BONUSES {
		public int getVigueurBonus(Personnage personnage) {
			return 1;
		}

		public int getReflexeBonus(Personnage personnage) {
			return 1;
		}

		public int getVolonteBonus(Personnage personnage) {
			return 1;
		}
	},

	// tiefling feature
	BLOODHUNT,
	FIRE_RESISTANCE,
	INFERNAL_WRATH,

	// deva feature
	ASTRAL_MAJESTY,
	ASTRAL_RESISTANCE,
	IMMORTAL_ORIGIN,
	MEMORY_OF_A_THOUSAND_LIFETIMES,

	// gnome feature
	MASTER_TRICKSTER,
	REACTIVE_STEALTH,
	TRICKSTERS_CUNNING,
	FADE_AWAY,

	// goliath feature
	MOUNTAINS_TENACITY,
	POWERFUL_ATHLETE,
	STONES_ENDURANCE,

	// half-orc feature
	HALF_ORC_RESILIENCE,
	SWIFT_CHARGE,
	FURIOUS_ASSAULT,

	// shifter feature
	LONGTOOTH_SHIFTING,
	RAZORCLAW_SHIFTING,

	// githzerai feature
	DANGER_SENSE {
		public int getInitiativeBonus(Personnage personnage) {
			return 2;
		}
	},
	DEFENDED_MIND,
	SHIFTING_FORTUNES,
	IRON_MIND,

	// minotaur feature
	VITALITY{
		public int getSurgeNumberBonus(Personnage personnage) {
			return 1;
		}
	},
	FEROCITY,
	HEEDLESS_CHARGE,
	GORING_CHARGE,

	// shardmind feature
	TELEPATHY,
	CRYSTALLINE_MIND,
	LIVING_CONSTRUCT,
	SHARD_SWARM,

	// wilden feature
	HARDY_FORM_FORTITUDE {
		public int getVigueurBonus(Personnage personnage) {
			return 1;
		}
	},
	HARDY_FORM_REFLEX {
		public int getReflexeBonus(Personnage personnage) {
			return 1;
		}
	},
	HARDY_FORM_WILL {
		public int getVolonteBonus(Personnage personnage) {
			return 1;
		}
	},
	NATURES_ASPECT,

	// changeling feature
	SHAPECHANGER,
	MENTAL_DEFENSE {
		public int getVolonteBonus(Personnage personnage) {
			return 1;
		}
	},
	CHANGE_SHAPE,
	CHANGELING_TRICK,

	// kalashtar feature
	DUAL_SOUL,
	BASTION_OF_MENTAL_CLARITY,

	// warforged feature
	UNSLEEPING_WATCHER,
	WARFORGED_RESILIENCE,
	WARFORGED_MIND {
		public int getVolonteBonus(Personnage personnage) {
			return 1;
		}
	},
	WARFORGED_RESOLVE,

	// drow feature
	LOLTHTOUCHED,

	// genasi feature
	ELEMENTAL_ORIGIN,
	ELEMENTAL_MANIFESTATION_EARTHSOUL {
		public int getVigueurBonus(Personnage personnage) {
			return 1;
		}
	},
	ELEMENTAL_MANIFESTATION_FIRESOUL {
		public int getReflexeBonus(Personnage personnage) {
			return 1;
		}
	},
	ELEMENTAL_MANIFESTATION_STORMSOUL {
		public int getVigueurBonus(Personnage personnage) {
			return 1;
		}
	},
	ELEMENTAL_MANIFESTATION_WATERSOUL,
	ELEMENTAL_MANIFESTATION_WINDSOUL,

	// feat
	IMPROVED_INITIATIVE {
		public int getInitiativeBonus(Personnage personnage) {
			return 4;
		}
	},
	TOUGHTNESS {
		public int getMaxHPBonus(Personnage personnage) {
			return getTiers(personnage.getNiveau()) * 5;
		}
	},
	DEFENSIVE_MOBILITY,
	QUICK_DRAW {
		public int getInitiativeBonus(Personnage personnage) {
			return 2;
		}
	},
	TWO_WEAPON_DEFENSE {
		public int getCABonus(Personnage personnage) {
			return getTwoWeaponDefenseBonus(personnage);
		}
		public int getReflexeBonus(Personnage personnage) {
			return getTwoWeaponDefenseBonus(personnage);
		}
		private int getTwoWeaponDefenseBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.UNARMED_ATTACK)
					|| personnage.getLeftWeapon().equals(Weapon.UNARMED_ATTACK)) {
				bonus = 0;
			} else {
				bonus = 1;
			}
			return bonus;
		}
	},
	FAR_SHOT;

	public int getSurgeValueBonus(Personnage personnage) {
		return 0;
	}

	public int getSurgeNumberBonus(Personnage personnage) {
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

	public int getInitiativeBonus(Personnage personnage) {
		return 0;
	}

	public int getSpeedBonus(Personnage personnage) {
		return 0;
	}

	public List<Weapon> getWeaponProficiencies() {
		return Collections.<Weapon>emptyList();
	}

	public int getAttaqueBonus(Personnage personnage) {
		return 0;
	}

	public int getDegatBonus(Personnage personnage) {
		return 0;
	}

	public int getDiceSizeBonus(Personnage personnage) {
		return 0;
	}

	public int getAttaqueGaucheBonus(Personnage personnage) {
		return 0;
	}

	public int getDegatGaucheBonus(Personnage personnage) {
		return 0;
	}

	public int getDiceSizeGaucheBonus(Personnage personnage) {
		return 0;
	}

	public boolean isOneHandedInOffhand() {
		return false;
	}

	public static int getTiers(int niveau) {
		return (niveau + 9) / 10;
	}
}
