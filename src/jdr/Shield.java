package jdr;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Shield {

	NONE("Aucun", ArmorType.CLOTH, 0),
	LIGHT_SHIELD("Bouclier léger", ArmorType.LIGHT_SHIELD, 1),
	HEAVY_SHIELD("Bouclier lourd", ArmorType.HEAVY_SHIELD, 2);

	private static final Map<String, Shield> shieldByName = new LinkedHashMap<String, Shield>();

	static {
		for(Shield shield : Shield.values()) {
			shieldByName.put(shield.getShieldName(), shield);
		}
	}

	public static Collection<String> getShieldNameList() {
		return shieldByName.keySet();
	}

	public static Shield getShieldByName(String nomBouclier) {
		return shieldByName.get(nomBouclier);
	}

	private String shieldName;
	private ArmorType armorType;
	private int shieldBonus;

	private Shield(String shieldName, ArmorType armorType, int shieldBonus) {
		this.shieldName = shieldName;
		this.armorType = armorType;
		this.shieldBonus = shieldBonus;
	}

	public String getShieldName() {
		return shieldName;
	}

	public ArmorType getArmorType() {
		return armorType;
	}

	public int getShieldBonus() {
		return shieldBonus;
	}

}
