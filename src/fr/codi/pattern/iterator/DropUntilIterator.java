package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.Predicate;

public class DropUntilIterator<T> extends AbstractComputeNextIterator<T> {

	private final Predicate<T> predicate;
	private final Iterator<T> inner;
	private boolean drop = true;

	public DropUntilIterator(final Predicate<T> predicate,
			final Iterator<T> inner) {
		this.predicate = predicate;
		this.inner = inner;
	}

	protected void computeNext() {
		if (this.drop) {
			while (this.inner.hasNext()) {
				this.nextItem = this.inner.next();
				if (this.predicate.test(this.nextItem)) {
					this.drop = false;
					break;
				} else {
					this.nextItem = null;
				}
			}
		} else if (this.nextItem == null && this.inner.hasNext()) {
			this.nextItem = this.inner.next();
		}
	}
}