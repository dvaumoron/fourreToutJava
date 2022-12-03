package fr.codi.pattern.iterator;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {

	protected int current;
	protected final int end;
	private final int step;

	public RangeIterator(final int start, final int end, final int step) {
		this.current = start;
		this.end = end;
		this.step = step;
	}

	@Override
	public boolean hasNext() {
		return this.current < this.end;
	}

	@Override
	public Integer next() {
		final int res = this.current;
		this.current += this.step;
		return res;
	}
}