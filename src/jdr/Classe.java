package jdr;

import java.util.List;

public abstract class Classe {

	public abstract String getNom();
	public abstract Role getRole();
	public abstract Source getSource();
	public abstract List<ArmorType> getArmorProficiencies();
	public abstract List<WeaponType> getWeaponProficiencies();
	public abstract Implement getImplement();
	public abstract int[] getBonusToDefense();
	public abstract int getHitPointAtFirstLevel();
	public abstract int getHitPointPerLevel();
	public abstract int getHealingSurgesPerDay();
	public abstract List<Skill> getTrainedSkills();
	public abstract List<Skill> getClassSkills();
	public abstract List<Feature> getClassFeatures();
}
