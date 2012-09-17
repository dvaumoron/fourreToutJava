package jdr;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Weapon {

	UNARMED_ATTACK (
			"Mains nues", false, 0, 1, 4, 0, 0, 0, "",
			Collections.<WeaponGroup>emptyList(),
			Arrays.asList(WeaponProperty.OFF_HAND),
			Collections.<WeaponType>emptyList()),

	MONK_UNARMED_STRIKE (
			"Mains nues (Moine)", false, 3, 1, 8, 0, 0, 0, "",
			Collections.<WeaponGroup>emptyList(),
			Arrays.asList(WeaponProperty.OFF_HAND),
			Arrays.asList(WeaponType.MONK_UNARMED_STRIKE)),

	CLUB (
			"Gourdin", false, 2, 1, 6, 0, 0, 1, "1,5 kg",
			Arrays.asList(WeaponGroup.MACE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.CLUB)),

	DAGGER (
			"Dague", false, 3, 1, 4, 5, 10, 1, "0,5 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Arrays.asList(WeaponProperty.OFF_HAND, WeaponProperty.LIGHT_THROWN),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.DAGGER)),

	JAVELIN (
			"Javeline", false, 2, 1, 6, 10, 20, 5, "1 kg",
			Arrays.asList(WeaponGroup.SPEAR),
			Arrays.asList(WeaponProperty.HEAVY_THROWN),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	MACE (
			"Masse d'armes", false, 2, 1, 8, 0, 0, 5, "3 kg",
			Arrays.asList(WeaponGroup.MACE),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	SICKLE (
			"Serpe", false, 2, 1, 6, 0, 0, 2, "1 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Arrays.asList(WeaponProperty.OFF_HAND),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	SPEAR (
			"Lance", false, 2, 1, 8, 0, 0, 5, "3 kg",
			Arrays.asList(WeaponGroup.SPEAR),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.SPEAR)),

	GREATCLUB (
			"Massue", true, 2, 2, 4, 0, 0, 1, "5 kg",
			Arrays.asList(WeaponGroup.MACE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	MORNINGSTAR (
			"Morgenstern", true, 2, 1, 10, 0, 0, 10, "4 kg",
			Arrays.asList(WeaponGroup.MACE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	QUARTERSTAFF (
			"Bâton", true, 2, 1, 8, 0, 0, 5, "2 kg",
			Arrays.asList(WeaponGroup.STAFF),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SIMPLE_MELEE, WeaponType.QUARTERSTAFF)),

	SCYTHE (
			"Faux", true, 2, 2, 4, 0, 0, 5, "5 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SIMPLE_MELEE)),

	BATTLEAXE (
			"Hache d'armes", false, 2, 1, 10, 0, 0, 15, "3 kg",
			Arrays.asList(WeaponGroup.AXE),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	FLAIL (
			"Fléau d'armes", false, 2, 1, 10, 0, 0, 10, "2,5 kg",
			Arrays.asList(WeaponGroup.FLAIL),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	HANDAXE (
			"Hache de lancer", false, 2, 1, 6, 5, 10, 5, "1,5 kg",
			Arrays.asList(WeaponGroup.AXE),
			Arrays.asList(WeaponProperty.OFF_HAND, WeaponProperty.HEAVY_THROWN),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	LONGSWORD (
			"Epée longue", false, 3, 1, 8, 0, 0, 15, "2 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.LONGSWORD, WeaponType.MILITARY_HEAVY_BLADES)),

	SCIMITAR (
			"Cimeterre", false, 2, 1, 8, 0, 0, 10, "2 kg",
					Arrays.asList(WeaponGroup.HEAVY_BLADE),
					Arrays.asList(WeaponProperty.HIGH_CRIT),
					Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.SCIMITAR, WeaponType.MILITARY_HEAVY_BLADES)),

	SHORT_SWORD (
			"Epée courte", false, 3, 1, 6, 0, 0, 10, "1 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Arrays.asList(WeaponProperty.OFF_HAND),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.SHORT_SWORD, WeaponType.MILITARY_LIGHT_BLADES)),

	THROWING_HAMMER (
			"Marteau de lancer", false, 2, 1, 6, 5, 10, 5, "1 kg",
			Arrays.asList(WeaponGroup.HAMMER),
			Arrays.asList(WeaponProperty.OFF_HAND, WeaponProperty.HEAVY_THROWN),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	WARHAMMER (
			"Marteau de guerre", false, 2, 1, 10, 0, 0, 15, "2,5 kg",
			Arrays.asList(WeaponGroup.HAMMER),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	WAR_PICK (
			"Pic de guerre", false, 2, 1, 8, 0, 0, 15, "3 kg",
			Arrays.asList(WeaponGroup.PICK),
			Arrays.asList(WeaponProperty.HIGH_CRIT, WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	FALCHION (
			"Cimeterre à deux mains", true, 3, 2, 4, 0, 0, 25, "3,5 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE),
			Arrays.asList(WeaponProperty.HIGH_CRIT),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.MILITARY_HEAVY_BLADES)),

	GLAIVE (
			"Coutille", true, 2, 2, 4, 0, 0, 25, "5 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE, WeaponGroup.POLEARM),
			Arrays.asList(WeaponProperty.REACH),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.MILITARY_HEAVY_BLADES)),

	GREATAXE (
			"Grande hache", true, 2, 1, 12, 0, 0, 30, "6 kg",
			Arrays.asList(WeaponGroup.AXE),
			Arrays.asList(WeaponProperty.HIGH_CRIT),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	GREATSWORD (
			"Epée à deux mains", true, 3, 1, 10, 0, 0, 30, "4 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.MILITARY_HEAVY_BLADES)),

	HALBERD (
			"Hallebarde", true, 2, 1, 10, 0, 0, 25, "6 kg",
			Arrays.asList(WeaponGroup.AXE, WeaponGroup.POLEARM),
			Arrays.asList(WeaponProperty.REACH),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	HEAVY_FLAIL (
			"Fléau d'armes lourd", true, 2, 2, 6, 0, 0, 25, "5 kg",
			Arrays.asList(WeaponGroup.FLAIL),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	LONGSPEAR (
			"Pique", true, 2, 1, 10, 0, 0, 10, "4,5 kg",
			Arrays.asList(WeaponGroup.POLEARM, WeaponGroup.SPEAR),
			Arrays.asList(WeaponProperty.REACH),
			Arrays.asList(WeaponType.MILITARY_MELEE, WeaponType.LONGSPEAR)),

	MAUL (
			"Maillet", true, 2, 2, 6, 0, 0, 30, "6 kg",
			Arrays.asList(WeaponGroup.HAMMER),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.MILITARY_MELEE)),

	BASTARD_SWORD (
			"Epée bâtarde", false, 3, 1, 10, 0, 0, 30, "3 kg",
			Arrays.asList(WeaponGroup.HEAVY_BLADE),
			Arrays.asList(WeaponProperty.VERSATILE),
			Arrays.asList(WeaponType.SUPERIOR_MELEE)),

	KATAR (
			"Katar", false, 3, 1, 6, 0, 0, 3, "0,5 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Arrays.asList(WeaponProperty.OFF_HAND, WeaponProperty.HIGH_CRIT),
			Arrays.asList(WeaponType.SUPERIOR_MELEE)),

	RAPIER (
			"Rapière", false, 3, 1, 8, 0, 0, 25, "1 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Collections.<WeaponProperty>emptyList(),
			Arrays.asList(WeaponType.SUPERIOR_MELEE)),

	SPIKED_CHAIN (
			"Chaîne cloutée", true, 3, 2, 4, 0, 0, 30, "5 kg",
			Arrays.asList(WeaponGroup.FLAIL),
			Arrays.asList(WeaponProperty.REACH),
			Arrays.asList(WeaponType.SUPERIOR_MELEE)),

	HAND_CROSSBOW (
			"Arbalète de poing", false, 2, 1, 6, 10, 20, 25, "1 kg",
			Arrays.asList(WeaponGroup.CROSSBOW),
			Arrays.asList(WeaponProperty.LOAD_FREE),
			Arrays.asList(WeaponType.SIMPLE_RANGED, WeaponType.HAND_CROSSBOW)),

	SLING (
			"Fronde", false, 2, 1, 6, 10, 20, 1, "0 kg",
			Arrays.asList(WeaponGroup.SLING),
			Arrays.asList(WeaponProperty.LOAD_FREE),
			Arrays.asList(WeaponType.SIMPLE_RANGED, WeaponType.SLING)),

	CROSSBOW (
			"Arbalète", true, 2, 1, 8, 15, 30, 25, "2 kg",
			Arrays.asList(WeaponGroup.CROSSBOW),
			Arrays.asList(WeaponProperty.LOAD_MINOR),
			Arrays.asList(WeaponType.SIMPLE_RANGED)),

	LONGBOW (
			"Arc long", true, 2, 1, 10, 20, 40, 30, "1,5 kg",
			Arrays.asList(WeaponGroup.BOW),
			Arrays.asList(WeaponProperty.LOAD_FREE),
			Arrays.asList(WeaponType.MILITARY_RANGED)),

	SHORTBOW (
			"Arc court", true, 2, 1, 8, 15, 30, 25, "1 kg",
			Arrays.asList(WeaponGroup.BOW),
			Arrays.asList(WeaponProperty.LOAD_FREE, WeaponProperty.SMALL),
			Arrays.asList(WeaponType.MILITARY_RANGED)),

	SHURIKEN (
			"Shuriken", false, 3, 1, 4, 6, 12, 1, "0,25 kg",
			Arrays.asList(WeaponGroup.LIGHT_BLADE),
			Arrays.asList(WeaponProperty.LIGHT_THROWN),
			Arrays.asList(WeaponType.SUPERIOR_RANGED, WeaponType.SHURIKEN));

	private static final Map<String, Weapon> weaponByName = new LinkedHashMap<String, Weapon>();

	static {
		for(Weapon weapon : Weapon.values()) {
			weaponByName.put(weapon.getNom(), weapon);
		}
	}

	public static Collection<String> getWeaponNameList() {
		return weaponByName.keySet();
	}

	public static Weapon getWeaponByName(String nomArme) {
		return weaponByName.get(nomArme);
	}

	private String nom;
	private boolean twoHanded;
	private int proficiency;
	private int diceNumber;
	private int diceSize;
	private int shortRange;
	private int longRange;
	private int price;
	private String weight;
	private List<WeaponGroup> group;
	private List<WeaponProperty> properties;
	private List<WeaponType> types;

	private Weapon(String nom, boolean twoHanded, int proficiency, int diceNumber,
			int diceSize, int shortRange, int longRange, int price, String weight,
			List<WeaponGroup> group, List<WeaponProperty> properties,
			List<WeaponType> types) {
		this.nom = nom;
		this.twoHanded = twoHanded;
		this.proficiency = proficiency;
		this.diceNumber = diceNumber;
		this.diceSize = diceSize;
		this.shortRange = shortRange;
		this.longRange = longRange;
		this.price = price;
		this.weight = weight;
		this.group = group;
		this.properties = properties;
		this.types = types;
	}

	public String getNom() {
		return nom;
	}

	public boolean isTwoHanded() {
		return twoHanded;
	}

	public int getProficiency() {
		return proficiency;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public int getDiceSize() {
		return diceSize;
	}

	public int getShortRange() {
		return shortRange;
	}

	public int getLongRange() {
		return longRange;
	}

	public int getPrice() {
		return price;
	}

	public String getWeight() {
		return weight;
	}

	public List<WeaponGroup> getGroup() {
		return group;
	}

	public List<WeaponProperty> getProperties() {
		return properties;
	}

	public List<WeaponType> getTypes() {
		return types;
	}

}
