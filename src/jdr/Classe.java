package jdr;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Classe {

	private static final Map<String, Classe> classeByName = new LinkedHashMap<String, Classe>();

	static {
		classeByName.put(StandardClasse.CLERIC.getNom(), StandardClasse.CLERIC);
		classeByName.put(StandardClasse.FIGHTER.getNom(), StandardClasse.FIGHTER);
		classeByName.put(StandardClasse.PALADIN.getNom(), StandardClasse.PALADIN);
		classeByName.put(StandardClasse.RANGER_DUNGEONEERING.getNom(), StandardClasse.RANGER_DUNGEONEERING);
		classeByName.put(StandardClasse.RANGER_NATURE.getNom(), StandardClasse.RANGER_NATURE);
		classeByName.put(StandardClasse.ROGUE.getNom(), StandardClasse.ROGUE);
		classeByName.put(StandardClasse.WARLOCK.getNom(), StandardClasse.WARLOCK);
		classeByName.put(StandardClasse.WARLORD.getNom(), StandardClasse.WARLORD);
		classeByName.put(StandardClasse.WIZARD.getNom(), StandardClasse.WIZARD);		
		classeByName.put(StandardClasse.AVENGER.getNom(), StandardClasse.AVENGER);
		classeByName.put(StandardClasse.BARBARIAN.getNom(), StandardClasse.BARBARIAN);
		classeByName.put(StandardClasse.BARD.getNom(), StandardClasse.BARD);
		classeByName.put(StandardClasse.DRUID.getNom(), StandardClasse.DRUID);
		classeByName.put(StandardClasse.INVOKER.getNom(), StandardClasse.INVOKER);
		classeByName.put(StandardClasse.SHAMAN.getNom(), StandardClasse.SHAMAN);
		classeByName.put(StandardClasse.SORCERER.getNom(), StandardClasse.SORCERER);
		classeByName.put(StandardClasse.WARDEN.getNom(), StandardClasse.WARDEN);
		classeByName.put(StandardClasse.ARDENT.getNom(), StandardClasse.ARDENT);
		classeByName.put(StandardClasse.BATTLEMIND.getNom(), StandardClasse.BATTLEMIND);
		classeByName.put(StandardClasse.MONK.getNom(), StandardClasse.MONK);
		classeByName.put(StandardClasse.PSION.getNom(), StandardClasse.PSION);
		classeByName.put(StandardClasse.RUNEPRIEST.getNom(), StandardClasse.RUNEPRIEST);
		classeByName.put(StandardClasse.SEEKER.getNom(), StandardClasse.SEEKER);
		classeByName.put(StandardClasse.ARTIFICIER.getNom(), StandardClasse.ARTIFICIER);
		classeByName.put(StandardClasse.SWORDMAGE.getNom(), StandardClasse.SWORDMAGE);

		classeByName.put(DemiClasse.CLERIC_HYBRID.getNom(), DemiClasse.CLERIC_HYBRID);
		classeByName.put(DemiClasse.FIGHTER_HYBRID.getNom(), DemiClasse.FIGHTER_HYBRID);
		classeByName.put(DemiClasse.PALADIN_HYBRID_FORTITUDE.getNom(), DemiClasse.PALADIN_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.PALADIN_HYBRID_REFLEX.getNom(), DemiClasse.PALADIN_HYBRID_REFLEX);
		classeByName.put(DemiClasse.PALADIN_HYBRID_WILL.getNom(), DemiClasse.PALADIN_HYBRID_WILL);
		classeByName.put(DemiClasse.RANGER_HYBRID_FORTITUDE.getNom(), DemiClasse.RANGER_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.RANGER_HYBRID_REFLEX.getNom(), DemiClasse.RANGER_HYBRID_REFLEX);
		classeByName.put(DemiClasse.ROGUE_HYBRID.getNom(), DemiClasse.ROGUE_HYBRID);
		classeByName.put(DemiClasse.WARLOCK_HYBRID_REFLEX.getNom(), DemiClasse.WARLOCK_HYBRID_REFLEX);
		classeByName.put(DemiClasse.WARLOCK_HYBRID_WILL.getNom(), DemiClasse.WARLOCK_HYBRID_WILL);
		classeByName.put(DemiClasse.WARLORD_HYBRID_FORTITUDE.getNom(), DemiClasse.WARLORD_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.WARLORD_HYBRID_WILL.getNom(), DemiClasse.WARLORD_HYBRID_WILL);
		classeByName.put(DemiClasse.WIZARD_HYBRID.getNom(), DemiClasse.WIZARD_HYBRID);
		classeByName.put(DemiClasse.AVENGER_HYBRID_FORTITUDE.getNom(), DemiClasse.AVENGER_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.AVENGER_HYBRID_REFLEX.getNom(), DemiClasse.AVENGER_HYBRID_REFLEX);
		classeByName.put(DemiClasse.AVENGER_HYBRID_WILL.getNom(), DemiClasse.AVENGER_HYBRID_WILL);
		classeByName.put(DemiClasse.BARBARIAN_HYBRID.getNom(), DemiClasse.BARBARIAN_HYBRID);
		classeByName.put(DemiClasse.BARD_HYBRID_REFLEX.getNom(), DemiClasse.BARD_HYBRID_REFLEX);
		classeByName.put(DemiClasse.BARD_HYBRID_WILL.getNom(), DemiClasse.BARD_HYBRID_WILL);
		classeByName.put(DemiClasse.DRUID_HYBRID_REFLEX.getNom(), DemiClasse.DRUID_HYBRID_REFLEX);
		classeByName.put(DemiClasse.DRUID_HYBRID_WILL.getNom(), DemiClasse.DRUID_HYBRID_WILL);
		classeByName.put(DemiClasse.INVOKER_HYBRID_FORTITUDE.getNom(), DemiClasse.INVOKER_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.INVOKER_HYBRID_REFLEX.getNom(), DemiClasse.INVOKER_HYBRID_REFLEX);
		classeByName.put(DemiClasse.INVOKER_HYBRID_WILL.getNom(), DemiClasse.INVOKER_HYBRID_WILL);
		classeByName.put(DemiClasse.SHAMAN_HYBRID_FORTITUDE.getNom(), DemiClasse.SHAMAN_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.SHAMAN_HYBRID_WILL.getNom(), DemiClasse.SHAMAN_HYBRID_WILL);
		classeByName.put(DemiClasse.SORCERER_HYBRID.getNom(), DemiClasse.SORCERER_HYBRID);
		classeByName.put(DemiClasse.WARDEN_HYBRID_FORTITUDE.getNom(), DemiClasse.WARDEN_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.WARDEN_HYBRID_WILL.getNom(), DemiClasse.WARDEN_HYBRID_WILL);
		classeByName.put(DemiClasse.ARDENT_HYBRID_FORTITUDE.getNom(), DemiClasse.ARDENT_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.ARDENT_HYBRID_WILL.getNom(), DemiClasse.ARDENT_HYBRID_WILL);
		classeByName.put(DemiClasse.BATTLEMIND_HYBRID.getNom(), DemiClasse.BATTLEMIND_HYBRID);
		classeByName.put(DemiClasse.MONK_HYBRID_FORTITUDE.getNom(), DemiClasse.MONK_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.MONK_HYBRID_REFLEX.getNom(), DemiClasse.MONK_HYBRID_REFLEX);
		classeByName.put(DemiClasse.MONK_HYBRID_WILL.getNom(), DemiClasse.MONK_HYBRID_WILL);
		classeByName.put(DemiClasse.PSION_HYBRID.getNom(), DemiClasse.PSION_HYBRID);
		classeByName.put(DemiClasse.RUNEPRIEST_HYBRID.getNom(), DemiClasse.RUNEPRIEST_HYBRID);
		classeByName.put(DemiClasse.SEEKER_HYBRID_REFLEX.getNom(), DemiClasse.SEEKER_HYBRID_REFLEX);
		classeByName.put(DemiClasse.SEEKER_HYBRID_WILL.getNom(), DemiClasse.SEEKER_HYBRID_WILL);
		classeByName.put(DemiClasse.ARTIFICIER_HYBRID_FORTITUDE.getNom(), DemiClasse.ARTIFICIER_HYBRID_FORTITUDE);
		classeByName.put(DemiClasse.ARTIFICIER_HYBRID_WILL.getNom(), DemiClasse.ARTIFICIER_HYBRID_WILL);
		classeByName.put(DemiClasse.SWORDMAGE_HYBRID.getNom(), DemiClasse.SWORDMAGE_HYBRID);
	}

	public static Collection<String> getClassNameList() {
		return classeByName.keySet();
	}

	public static Classe getClasseByName(String nomClasse) {
		return classeByName.get(nomClasse);
	}

	public abstract String getNom();
	public abstract Role getRole();
	public abstract Source getSource();
	public abstract List<ArmorType> getArmorProficiencies();
	public abstract List<WeaponType> getWeaponProficiencies();
	public abstract List<Implement> getImplements();
	public abstract int[] getBonusToDefense();
	public abstract int getHitPointAtFirstLevel();
	public abstract int getHitPointPerLevel();
	public abstract int getHealingSurgesPerDay();
	public abstract List<Skill> getTrainedSkills();
	public abstract int getSkillNumber();
	public abstract List<Skill> getClassSkills();
	public abstract List<Feature> getClassFeatures();
}
