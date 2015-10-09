package template;

class RopeString implements Writable {

	private String string;

	RopeString(String string) {
		this.string = string;
	}

	@Override
	public int length() {
		return string.length();
	}

	@Override
	public void write(StringBuilder buffer) {
		buffer.append(string);
	}

}
