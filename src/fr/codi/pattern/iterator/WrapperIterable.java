package fr.codi.pattern.iterator;

import java.util.Iterator;

public class WrapperIterable<T> implements Iterable<T> {

	private final Iterator<T> inner;
	
	public WrapperIterable(final Iterator<T> inner) {
		this.inner = inner;
	}

	@Override
	public Iterator<T> iterator() {
		return this.inner;
	}
}