package jdr;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Armor {

	CLOTH_ARMOR ("Vêtements normaux", ArmorType.CLOTH, 0),
	CLOTH_ARMOR_1 ("Armure d'étoffe (+1)", ArmorType.CLOTH, 1),
	CLOTH_ARMOR_2 ("Armure d'étoffe (+2)", ArmorType.CLOTH, 2),
	CLOTH_ARMOR_3 ("Armure d'étoffe (+3)", ArmorType.CLOTH, 3),
	FEYWAVE_ARMOR ("Armure d'étoffe féerique", ArmorType.CLOTH, 5),
	FEYWAVE_ARMOR_5 ("Armure d'étoffe féerique (+5)", ArmorType.CLOTH, 6),
	STARWAVE_ARMOR ("Armure d'étoffe céleste", ArmorType.CLOTH, 8),

	LEATHER_ARMOR ("Armure de cuir", ArmorType.LEATHER, 2),
	LEATHER_ARMOR_1 ("Armure de cuir (+1)", ArmorType.LEATHER, 3),
	LEATHER_ARMOR_2 ("Armure de cuir (+2)", ArmorType.LEATHER, 4),
	LEATHER_ARMOR_3 ("Armure de cuir (+3)", ArmorType.LEATHER, 5),
	FEYLEATHER_ARMOR ("Armure de cuir féerique", ArmorType.LEATHER, 7),
	FEYLEATHER_ARMOR_5 ("Armure de cuir féerique (+5)", ArmorType.LEATHER, 8),
	STARLEATHER_ARMOR ("Armure de cuir céleste", ArmorType.LEATHER, 10),

	HIDE_ARMOR ("Armure de peau", ArmorType.HIDE, 3),
	HIDE_ARMOR_1 ("Armure de peau (+1)", ArmorType.HIDE, 4),
	HIDE_ARMOR_2 ("Armure de peau (+2)", ArmorType.HIDE, 5),
	HIDE_ARMOR_3 ("Armure de peau (+3)", ArmorType.HIDE, 6),
	DARKHIDE_ARMOR ("Armure de sombrepeau", ArmorType.HIDE, 8),
	DARKHIDE_ARMOR_5 ("Armure de sombrepeau (+5)", ArmorType.HIDE, 9),
	ELDERHIDE_ARMOR ("Armure de peau vénérable", ArmorType.HIDE, 11),
	
	CHAINMAIL ("Cotte de mailles", ArmorType.CHAINMAIL, 6),
	CHAINMAIL_1 ("Cotte de mailles (+1)", ArmorType.CHAINMAIL, 7),
	CHAINMAIL_2 ("Cotte de mailles (+2)", ArmorType.CHAINMAIL, 8),
	CHAINMAIL_3 ("Cotte de mailles (+3)", ArmorType.CHAINMAIL, 9),
	FORGEMAIL ("Cotte de forgemailles", ArmorType.CHAINMAIL, 13),
	FORGEMAIL_5 ("Cotte de forgemailles (+5)", ArmorType.CHAINMAIL, 14),
	SPIRITMAIL ("Cotte de mailles céleste", ArmorType.CHAINMAIL, 18),
	
	SCALE_ARMOR ("Armure d'écailles", ArmorType.SCALE, 7),
	SCALE_ARMOR_1 ("Armure d'écailles (+1)", ArmorType.SCALE, 8),
	SCALE_ARMOR_2 ("Armure d'écailles (+2)", ArmorType.SCALE, 9),
	SCALE_ARMOR_3 ("Armure d'écailles (+3)", ArmorType.SCALE, 10),
	WYRMSCALE_ARMOR ("Armure draconique", ArmorType.SCALE, 14),
	WYRMSCALE_ARMOR_5 ("Armure draconique (+5)", ArmorType.SCALE, 15),
	ELDERSCALE_ARMOR ("Armure d'écailles vénérables", ArmorType.SCALE, 19),
	
	PLATE_ARMOR ("Harnois", ArmorType.PLATE, 8),
	PLATE_ARMOR_1 ("Harnois (+1)", ArmorType.PLATE, 9),
	PLATE_ARMOR_2 ("Harnois (+2)", ArmorType.PLATE, 10),
	PLATE_ARMOR_3 ("Harnois (+3)", ArmorType.PLATE, 11),
	WARPLATE_ARMOR ("Harnois de guerre", ArmorType.PLATE, 15),
	WARPLATE_ARMOR_5 ("Harnois de guerre (+5)", ArmorType.PLATE, 16),
	GODPLATE_ARMOR ("Harnois divin", ArmorType.PLATE, 20);

	private static final Map<String, Armor> armorByName = new LinkedHashMap<String, Armor>();

	static {
		for (Armor armor : Armor.values()) {
			armorByName.put(armor.getArmorName(), armor);			
		}
	}

	public static Collection<String> getArmorNameList() {
		return armorByName.keySet();
	}

	public static Armor getArmorByName(String nomArmure) {
		return armorByName.get(nomArmure);
	}

	private String armorName;
	private ArmorType armorType;
	private int armorBonus;

	private Armor(String armorName, ArmorType armorType, int armorBonus) {
		this.armorName = armorName;
		this.armorType = armorType;
		this.armorBonus = armorBonus;
	}

	public String getArmorName() {
		return armorName;
	}

	public ArmorType getArmorType() {
		return armorType;
	}

	public int getArmorBonus() {
		return armorBonus;
	}

}
