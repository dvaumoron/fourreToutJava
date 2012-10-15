package jdr;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public enum MagicalWeapon {

	NONE("Aucun", 0),
	_1("+1", 1),
	_2("+2", 2),
	_3("+3", 3),
	_4("+4", 4),
	_5("+5", 5),
	_6("+6", 6);

	private static final Map<String, MagicalWeapon> weaponByDisplay = new LinkedHashMap<String, MagicalWeapon>();

	static {
		for(MagicalWeapon weapon : MagicalWeapon.values()) {
			weaponByDisplay.put(weapon.getDisplay(), weapon);
		}
	}

	public static Collection<String> getWeaponDisplayList() {
		return weaponByDisplay.keySet();
	}

	public static MagicalWeapon getWeaponByName(String nomArmeMagique) {
		return weaponByDisplay.get(nomArmeMagique);
	}

	private String display;
	private int bonus;

	private MagicalWeapon(String display, int bonus) {
		this.display = display;
		this.bonus = bonus;
	}

	public String getDisplay() {
		return display;
	}

	public int getBonus() {
		return bonus;
	}
	
	
}
