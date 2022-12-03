package fr.codi.pattern.iterator;

import java.util.Iterator;

public class RepeatIterator<T> implements Iterator<T> {

	private final T value;

	public RepeatIterator(final T value) {
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		return this.value;
	}
}