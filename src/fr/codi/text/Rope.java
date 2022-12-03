package fr.codi.text;

import java.util.ArrayList;
import java.util.List;

public class Rope {

	private final List<Object> items;

	public Rope() {
		this.items = new ArrayList<>();
	}

	public Rope(final int size) {
		this.items = new ArrayList<>(size);
	}

	public Rope append(final String string) {
		this.items.add(string);
		return this;
	}

	public Rope append(final Rope rope) {
		this.items.add(rope);
		return this;
	}

	public int length() {
		int res = 0;
		for (final Object item : this.items) {
			if (item instanceof Rope) {
				final Rope itemRope = (Rope) item;
				res += itemRope.length();
			} else {
				final String itemString = (String) item;
				res += itemString.length();
			}
		}
		return res;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder(this.length());
		this.printTo(buffer);
		return buffer.toString();
	}

	private void printTo(final StringBuilder buffer) {
		for (final Object item : this.items) {
			if (item instanceof Rope) {
				final Rope itemRope = (Rope) item;
				itemRope.printTo(buffer);
			} else {
				final String itemString = (String) item;
				buffer.append(itemString);
			}
		}
	}
}
