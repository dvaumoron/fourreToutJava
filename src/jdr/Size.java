package jdr;

public enum Size {

	TINY("T"),
	SMALL("S"),
	MEDIUM("M"),
	LARGE("L"),
	HUGE("H"),
	GARGANTUAN("G");

	private String display;

	private Size(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

}
