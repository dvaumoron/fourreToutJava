package fr.codi.pattern.iterator;

import java.util.Iterator;

public abstract class AbstractComputeNextIterator<T> implements Iterator<T> {

	protected T nextItem = null;

	protected abstract void computeNext();

	@Override
	public boolean hasNext() {
		this.computeNext();
		return this.nextItem != null;
	}

	@Override
	public T next() {
		this.computeNext();
		final T res = this.nextItem;
		this.nextItem = null;
		return res;
	}
}