package template;

class RopeChar implements Writable {

	private char c;

	RopeChar(char c) {
		this.c = c;
	}

	@Override
	public int length() {
		return 1;
	}

	@Override
	public void write(StringBuilder buffer) {
		buffer.append(c);
	}

}
