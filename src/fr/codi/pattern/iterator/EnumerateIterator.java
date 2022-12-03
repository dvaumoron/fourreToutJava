package fr.codi.pattern.iterator;

import java.util.Iterator;

import fr.codi.pattern.IndexedValue;

public class EnumerateIterator<T> implements Iterator<IndexedValue<T>> {

	private final Iterator<T> inner;
	private int current;

	public EnumerateIterator(final Iterator<T> inner, final int start) {
		this.inner = inner;
		this.current = start;
	}

	@Override
	public boolean hasNext() {
		return this.inner.hasNext();
	}

	@Override
	public IndexedValue<T> next() {
		return new IndexedValue<>(this.current++, this.inner.next());
	}
}
