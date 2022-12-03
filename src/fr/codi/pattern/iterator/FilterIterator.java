package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.Predicate;

public class FilterIterator<T> extends AbstractComputeNextIterator<T> {

	private final Predicate<T> predicate;
	private final Iterator<T> inner;

	public FilterIterator(final Predicate<T> predicate, final Iterator<T> inner) {
		this.predicate = predicate;
		this.inner = inner;
	}

	protected void computeNext() {
		if (this.nextItem == null) {
			while (this.inner.hasNext()) {
				this.nextItem = this.inner.next();
				if (this.predicate.test(this.nextItem)) {
					break;
				} else {
					this.nextItem = null;
				}
			}
		}
	}
}