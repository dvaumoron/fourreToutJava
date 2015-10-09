package template;

import java.util.LinkedList;
import java.util.List;

public class Rope implements Writable {

	private List<Writable> ropes = new LinkedList<Writable>();

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(length());
		write(buffer);
		return buffer.toString();
	}

	@Override
	public int length() {
		int total = 0;
		for (Writable rope : ropes) {
			total += rope.length();
		}
		return total;
	}

	@Override
	public void write(StringBuilder buffer) {
		for (Writable rope : ropes) {
			rope.write(buffer);
		}
	}

	public Rope append(Rope rope) {
		ropes.add(rope);
		return this;
	}

	public Rope append(String string) {
		ropes.add(new RopeString(string));
		return this;
	}
	
	public Rope append(String[] strings) {
		for(String string : strings) {
			append(string);
		}
		return this;
	}

	public Rope append(char c) {
		ropes.add(new RopeChar(c));
		return this;
	}

	public Rope append(char[] chars) {
		return append(new String(chars));
	}

	public Rope append(double d) {
		return append(Double.toString(d));
	}

	public Rope append(float f) {
		return append(Float.toString(f));
	}

	public Rope append(int i) {
		return append(Integer.toString(i));
	}

	public Rope append(long l) {
		return append(Long.toString(l));
	}

	public Rope append(Object obj) {
		return append(String.valueOf(obj));
	}
}
