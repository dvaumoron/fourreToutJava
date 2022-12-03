package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.Predicate;

public class TakeUntilIterator<T> extends AbstractComputeNextIterator<T> {

	private final Predicate<T> predicate;
	private final Iterator<T> inner;
	private boolean take = true;

	public TakeUntilIterator(Predicate<T> predicate, Iterator<T> inner) {
		this.predicate = predicate;
		this.inner = inner;
	}

	protected void computeNext() {
		if (this.take && this.nextItem == null && this.inner.hasNext()) {
			this.nextItem = this.inner.next();
			if (this.predicate.test(this.nextItem)) {
				this.take = false;
				this.nextItem = null;
			}
		}
	}
}