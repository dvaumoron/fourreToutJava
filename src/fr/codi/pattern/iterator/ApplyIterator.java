package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.Function;

public class ApplyIterator<T, R> implements Iterator<R> {

	private final Function<T, R> function;
	private final Iterator<T> inner;

	public ApplyIterator(final Function<T, R> function, final Iterator<T> inner) {
		this.function = function;
		this.inner = inner;
	}

	@Override
	public boolean hasNext() {
		return this.inner.hasNext();
	}

	@Override
	public R next() {
		return this.function.apply(this.inner.next());
	}
}