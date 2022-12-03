package fr.codi.pattern.iterator;

import java.util.Iterator;

public class LimitIterator<T> implements Iterator<T> {

	private final Iterator<T> inner;
	private int time;

	public LimitIterator(final Iterator<T> inner, final int time) {
		this.inner = inner;
		this.time = time;
	}

	@Override
	public boolean hasNext() {
		return this.time > 0 && this.inner.hasNext();
	}

	@Override
	public T next() {
		this.time -= 1;
		return this.inner.next();
	}
}