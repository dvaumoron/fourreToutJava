package jdr;

public enum ArmorType {

	NONE(false),
	CLOTH(false),
	LEATHER(false),
	HIDE(false),
	CHAINMAIL(true),
	SCALE(true),
	PLATE(true);
	
	private boolean heavy;
	
	private ArmorType(boolean heavy) {
		this.heavy = heavy;
	}

	public boolean isHeavy() {
		return heavy;
	}

}
