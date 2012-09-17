package jdr;

public enum ArmorType {

	CLOTH(false, 0, 0),
	LEATHER(false, 0, 0),
	HIDE(false, -1, 0),
	CHAINMAIL(true, -1, -1),
	SCALE(true, 0, -1),
	PLATE(true, -2, -1),
	LIGHT_SHIELD(false, 0, 0),
	HEAVY_SHIELD(false, -2, 0);
	
	private boolean heavy;
	private int check;
	private int speed;
	
	private ArmorType(boolean heavy, int check, int speed) {
		this.heavy = heavy;
		this.check = check;
		this.speed = speed;
	}

	public boolean isHeavy() {
		return heavy;
	}

	public int getCheck() {
		return check;
	}

	public int getSpeed() {
		return speed;
	}

}
