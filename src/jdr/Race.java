package jdr;

import java.util.Arrays;
import java.util.List;

public class Race {

	public static final Race DRAGONBORN = new Race(
			"Drakéide", "1,85 m à 2 m", "110 à 160 kg", new int[]{2, 0, 0, 0, 0, 2},
			Size.MEDIUM, 6, Vision.NORMAL,
			Arrays.asList(Language.COMMON, Language.DRACONIC),
			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0}, // history, intimidate
			Arrays.asList(Feature.DRAGONBORN_FURY, Feature.DRACONIC_HERITAGE,
					Feature.DRAGON_BREATH));

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
