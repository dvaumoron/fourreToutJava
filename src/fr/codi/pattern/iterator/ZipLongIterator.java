package fr.codi.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZipLongIterator<T> extends AbstractComputeNextIterator<List<T>> {

	private final List<Iterator<? extends T>> inners = new ArrayList<>();
	private final RepeatIterator<T> fillValueIterator;
	private int numberActive;

	public ZipLongIterator(final T fillValue,
			final Iterator<Iterable<? extends T>> iterators) {
		while (iterators.hasNext()) {
			this.inners.add(iterators.next().iterator());
		}
		this.numberActive = this.inners.size();
		this.fillValueIterator = new RepeatIterator<>(fillValue);
	}

	protected void computeNext() {
		if (this.nextItem == null && this.numberActive > 0) {
			final List<T> res = new ArrayList<>();
			final int size = this.inners.size();
			for (int i = 0; i < size; i++) {
				final Iterator<? extends T> it = this.inners.get(i);
				if (it.hasNext()) {
					res.add(it.next());
				} else {
					this.numberActive--;
					if (this.numberActive == 0) {
						return;
					}
					this.inners.set(i, this.fillValueIterator);
					res.add(this.fillValueIterator.next());
				}
			}
			this.nextItem = res;
		}
	}
}