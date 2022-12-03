package fr.codi.pattern.iterator;

import java.util.Iterator;

public class CountIterator implements Iterator<Integer> {

	private int current;
	private final int step;

	public CountIterator(final int start, final int step) {
		this.current = start;
		this.step = step;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Integer next() {
		final int res = this.current;
		this.current += this.step;
		return res;
	}
}