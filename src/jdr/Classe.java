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
