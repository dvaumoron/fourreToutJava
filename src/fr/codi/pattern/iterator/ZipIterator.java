package fr.codi.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZipIterator<T> extends AbstractComputeNextIterator<List<T>> {

	private final List<Iterator<? extends T>> inners = new ArrayList<>();

	public ZipIterator(final Iterator<Iterable<? extends T>> iterators) {
		while (iterators.hasNext()) {
			this.inners.add(iterators.next().iterator());
		}
	}

	protected void computeNext() {
		if (this.nextItem == null) {
			final List<T> res = new ArrayList<>();
			for (final Iterator<? extends T> it : this.inners) {
				if (it.hasNext()) {
					res.add(it.next());
				} else {
					return;
				}
			}
			this.nextItem = res;
		}
	}
}