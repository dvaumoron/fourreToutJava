package fr.codi.pattern.iterable;

import java.util.Iterator;
import java.util.function.Predicate;

import fr.codi.pattern.iterator.FilterIterator;

public class Filter<T> implements Iterable<T> {

	private final Predicate<T> predicate;
	private final Iterable<T> inner;

	public Filter(final Predicate<T> predicate, final Iterable<T> inner) {
		this.predicate = predicate;
		this.inner = inner;
	}

	@Override
	public Iterator<T> iterator() {
		return new FilterIterator<>(this.predicate, this.inner.iterator());
	}

	public static void main(final String[] args) {
		for (final int i : new Filter<>(a -> a % 2 == 0, new Range(10))) {
			System.out.println(i);
		}
	}
}