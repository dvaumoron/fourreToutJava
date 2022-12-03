package fr.codi.pattern.iterator;

import java.util.Iterator;

public class ChainIterator<T> implements Iterator<T> {

	private final Iterator<Iterable<? extends T>> inners;
	private Iterator<? extends T> currentIter = null;

	public ChainIterator(final Iterator<Iterable<? extends T>> inners) {
		this.inners = inners;
	}

	private void computeCurrentIter() {
		if (this.currentIter == null || !this.currentIter.hasNext()) {
			if (this.inners.hasNext()) {
				this.currentIter = this.inners.next().iterator();
				while (!this.currentIter.hasNext() && this.inners.hasNext()) {
					this.currentIter = this.inners.next().iterator();
				}
			} else {
				this.currentIter = null;
			}
		}
	}

	@Override
	public boolean hasNext() {
		this.computeCurrentIter();
		return this.currentIter != null && this.currentIter.hasNext();
	}

	@Override
	public T next() {
		this.computeCurrentIter();
		return this.currentIter.next();
	}
}