package jdr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class HybridClasse extends Classe {

	private DemiClasse classeOne;
	private DemiClasse classeTwo;

	public HybridClasse(DemiClasse classeOne, DemiClasse classeTwo) {
		this.classeOne = classeOne;
		this.classeTwo = classeTwo;
	}

	@Override
	public String getNom() {
		return new StringBuilder()
				.append(classeOne.getNom())
				.append("/").append(classeTwo.getNom()).toString();
	}

	@Override
	public Role getRole() {
		Role role;
		if (classeOne.getRole().equals(classeTwo.getRole())) {
			role = classeOne.getRole();
		} else {
			role = Role.HYBRID;
		}
		return role;
	}

	@Override
	public Source getSource() {
		Source source;
		if (classeOne.getSource().equals(classeTwo.getRole())) {
			source = classeOne.getSource();
		} else {
			source = Source.HYBRID;
		}
		return source;
	}

	@Override
	public List<ArmorType> getArmorProficiencies() {
		EnumSet<ArmorType> armorProficiencies = EnumSet.noneOf(ArmorType.class);
		armorProficiencies.addAll(EnumSet.complementOf(EnumSet.copyOf(classeOne.getArmorProficiencies())));
		armorProficiencies.addAll(EnumSet.complementOf(EnumSet.copyOf(classeTwo.getArmorProficiencies())));
		return new ArrayList<ArmorType>(EnumSet.complementOf(armorProficiencies));
	}

	@Override
	public List<WeaponType> getWeaponProficiencies() {
		EnumSet<WeaponType> weaponProficiencies = EnumSet.noneOf(WeaponType.class);
		weaponProficiencies.addAll(classeOne.getWeaponProficiencies());
		weaponProficiencies.addAll(classeTwo.getWeaponProficiencies());
		return new ArrayList<WeaponType>(weaponProficiencies);
	}

	@Override
	public List<Implement> getImplements() {
		EnumSet<Implement> implement =  EnumSet.noneOf(Implement.class);
		implement.addAll(classeOne.getImplements());
		implement.addAll(classeTwo.getImplements());
		return new ArrayList<Implement>(implement);
	}

	@Override
	public int[] getBonusToDefense() {
		int[] bonusToDefense = new int[3];
		int[] bonusToDefense1 = classeOne.getBonusToDefense();
		int[] bonusToDefense2 = classeTwo.getBonusToDefense();
		bonusToDefense[0] = bonusToDefense1[0] + bonusToDefense2[0];
		bonusToDefense[1] = bonusToDefense1[1] + bonusToDefense2[1];
		bonusToDefense[2] = bonusToDefense1[2] + bonusToDefense2[2];
		return bonusToDefense;
	}

	@Override
	public int getHitPointAtFirstLevel() {
		return (classeOne.getHitPointAtFirstLevel() + classeTwo.getHitPointAtFirstLevel()) / 2;
	}

	@Override
	public int getHitPointPerLevel() {
		return (classeOne.getHitPointPerLevel() + classeTwo.getHitPointPerLevel()) / 2;
	}

	@Override
	public int getHealingSurgesPerDay() {
		return (classeOne.getHealingSurgesPerDay() + classeTwo.getHealingSurgesPerDay()) / 2;
	}

	@Override
	public List<Skill> getTrainedSkills() {
		return Collections.emptyList();
	}

	@Override
	public int getSkillNumber() {
		return 3 + classeOne.getSkillNumber() + classeTwo.getSkillNumber();
	}

	@Override
	public List<Skill> getClassSkills() {
		EnumSet<Skill> skills = EnumSet.noneOf(Skill.class);
		skills.addAll(classeOne.getClassSkills());
		skills.addAll(classeTwo.getClassSkills());
		return new ArrayList<Skill>(skills);
	}

	@Override
	public List<Feature> getClassFeatures() {
		List<Feature> classFeatures = new ArrayList<Feature>();
		classFeatures.addAll(classeOne.getClassFeatures());
		classFeatures.addAll(classeTwo.getClassFeatures());
		return classFeatures;
	}
	
}
