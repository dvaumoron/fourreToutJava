package fr.codi.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CycleIterator<T> implements Iterator<T> {

	private final Iterator<T> inner;
	private final List<T> list = new ArrayList<>();
	private Iterator<T> iter = null;

	public CycleIterator(final Iterator<T> inner) {
		this.inner = inner;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		final T res;
		if (this.inner.hasNext()) {
			res = this.inner.next();
			this.list.add(res);
		} else {
			if (this.iter == null || !this.iter.hasNext()) {
				this.iter = this.list.iterator();
			}
			res = this.iter.next();
		}
		return res;
	}
}