package fr.codi.pattern.iterator;

import java.util.Iterator;

import fr.codi.pattern.IndexedValue;

public class InfiniteSliceIterator<T> extends AbstractComputeNextIterator<T> {

	private final EnumerateIterator<T> inner;
	protected int current;
	private final int step;

	public InfiniteSliceIterator(final Iterator<T> inner, final int start,
			final int step) {
		this.inner = new EnumerateIterator<>(inner, 0);
		this.current = start;
		this.step = step;
	}

	@Override
	protected void computeNext() {
		if (this.nextItem == null) {
			this.innerComputeNext();
		}
	}

	protected void innerComputeNext() {
		while (this.inner.hasNext()) {
			final IndexedValue<T> currentItem = this.inner.next();
			if (currentItem.getIndex() == this.current) {
				this.nextItem = currentItem.getValue();
				break;
			}
		}
		this.current += this.step;
	}
}