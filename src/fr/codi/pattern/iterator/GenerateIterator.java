package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.Supplier;

public class GenerateIterator<T> implements Iterator<T> {

	private final Supplier<T> supplier;

	public GenerateIterator(final Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		return this.supplier.get();
	}
}