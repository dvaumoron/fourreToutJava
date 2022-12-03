package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class AccumulateIterator<T> implements Iterator<T> {

	private final Iterator<T> inner;
	private final BinaryOperator<T> function;
	private T total = null;

	public AccumulateIterator(final Iterator<T> inner,
			final BinaryOperator<T> function) {
		this.inner = inner;
		this.function = function;
	}

	@Override
	public boolean hasNext() {
		return this.inner.hasNext();
	}

	@Override
	public T next() {
		final T res = this.inner.next();
		if (this.total == null) {
			this.total = res;
		} else {
			this.total = this.function.apply(this.total, res);
		}
		return this.total;
	}
}