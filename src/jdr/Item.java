package jdr;

public class Item {

	private ArmorType armorType;
	private WeaponType weaponType;
	private int armorBonus;
	private int reflexeBonus;

	private Item(ArmorType armorType, WeaponType weaponType, int armorBonus,
			int reflexeBonus) {
		this.armorType = armorType;
		this.weaponType = weaponType;
		this.armorBonus = armorBonus;
		this.reflexeBonus = reflexeBonus;
	}

	public boolean isHeavy() {
		return armorType.isHeavy();
	}

	public int getArmorBonus() {
		return armorBonus;
	}

	public int getReflexeBonus() {
		return reflexeBonus;
	}

}
