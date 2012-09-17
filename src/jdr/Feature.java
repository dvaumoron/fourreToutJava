package jdr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Feature {

	// cleric feature
	CHANEL_DIVINITY,
	HEALERS_LORE,
	HEALING_WORD,
	RITUAL_CASTING,

	// fighter feature
	COMBAT_CHALLENGE,
	COMBAT_SUPERIORITY,
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

	// paladin feature
	DIVINE_CHALLENGE,
	LAY_ON_HANDS,

	// ranger feature
	FIGHTING_STYLE,
	ONE_HANDED_IN_OFFHAND,
	HUNTERS_QUARRY,
	PRIME_SHOT,

	// rogue feature
	FIRST_STRIKE,
	ROGUE_TACTICS,
	ROGUE_TACTICS_ARTFUL_DODGER,
	ROGUE_TACTICS_BRUTAL_SCOUNDREL,
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

		public int getDiceSizeBonus(Personnage personnage) {
			int bonus;
			if (personnage.getWeapon().equals(Weapon.SHURIKEN)) {
				bonus = 2;
			} else {
				bonus = 0;
			}
			return bonus;
		}
	},
	SNEAK_ATTACK,

	// warlock feature
	ELDRITCH_BLAST,
	ELDRITCH_PACT,
	FEY_PACT,
	INFERNAL_PACT,
	STAR_PACT,
	SHADOW_WALK,
	WARLOCKS_CURSE,

	// warlord feature
	COMBAT_LEADER {
		public int getInitiativeBonus(Personnage personnage) {
			return 2;
		}
	},
	COMMANDING_PRESENCE,
	INSPIRING_PRESENCE,
	TACTICAL_PRESENCE,
	INSPIRING_WORD,

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
	OATH_OF_ENMMITY,

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
	RAGE_STRIKE,
	RAMPAGE,

	// bard feature
	BARDIC_TRAINING,
	BARDIC_VIRTUE,
	VIRTUE_OF_CUNNING,
	VIRTUE_OF_VALOR,
	MAJESTIC_WORD,
	MULTICLASS_VERSATILITY,
	SKILL_VERSATILITY,
	SONG_OF_REST,
	WORDS_OF_FRIENDSHIP,

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
	WILD_SHAPE,

	// invoker feature
	DIVINE_COVENANT,
	COVENANT_OF_PRESERVATION,
	COVENANT_OF_WRATH,

	// shaman feature
	COMPANION_SPIRIT,
	PROTECTOR_SPIRIT,
	STALKER_SPIRIT,
	HEALING_SPIRIT,
	SPEAK_WITH_SPIRITS,

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
	},
	SPELL_SOURCE_WILD_MAGIC {
		public int getDegatBonus(Personnage personnage) {
			return personnage.getDexteriteMod()
					+ 2 * (getTiers(personnage.getNiveau()) - 1);
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
	NATURES_WRATH,

	// ardent feature
	ARDENT_MANTLE,
	MANTLE_OF_CLARITY,
	MANTLE_OF_ELATION,
	ARDENT_SURGE,
	PSIONIC_AUGMENTATION,

	// battlemind feature
	PSIONIC_DEFENSE,
	PSIONIC_STUDY,
	PSIONIC_STUDY_BATTLE_RESILIENCE,
	PSIONIC_STUDY_SPEED_OF_THOUGHT,

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

	// psion feature
	DISCIPLINE_FOCUS,
	TELEKINESIS_FOCUS,
	TELEPATHY_FOCUS,

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

	// artificier feature
	ARCANE_EMPOWERMENT,
	ARCANE_REJUVENATION,
	HEALING_INFUSION,

	// swordmage feature
	SWORDBOND,
	SWORDMAGE_AEGIS,
	AEGIS_OF_ASSAULT,
	AEGIS_OF_SHIELDING,
	SWORDMAGE_WARDING {
		public int getCABonus(Personnage personnage) {
			int bonus;
			if (personnage.getShield().equals(Shield.NONE)
					&& !personnage.getWeapon().isTwoHanded()) {
				// if one hand free
				bonus = 3;
			} else {
				bonus = 1;
			}
			return bonus;
		}
	},

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
	DEFENSIVE_MOBILITY;

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

	public static int getTiers(int niveau) {
		return (niveau + 9) / 10;
	}
}
