package fr.codi.pattern.iterator;

import java.util.Iterator;

public class CompressIterator<T> extends AbstractComputeNextIterator<T> {

	private final Iterator<T> data;
	private final Iterator<Boolean> selector;

	public CompressIterator(final Iterator<T> data,
			final Iterator<Boolean> selector) {
		this.data = data;
		this.selector = selector;
	}

	protected void computeNext() {
		if (this.nextItem == null) {
			while (this.data.hasNext() && this.selector.hasNext()) {
				this.nextItem = this.data.next();
				if (this.selector.next()) {
					break;
				} else {
					this.nextItem = null;
				}
			}
		}
	}
}