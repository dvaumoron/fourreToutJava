package fr.codi.pattern.iterator;

import java.util.Iterator;

public class SliceIterator<T> extends InfiniteSliceIterator<T> {

	private final int end;

	public SliceIterator(final Iterator<T> inner, final int start,
			final int end, final int step) {
		super(inner, start, step);
		this.end = end;
	}

	@Override
	protected void computeNext() {
		if (this.nextItem == null && this.current < this.end) {
			this.innerComputeNext();
		}
	}
}