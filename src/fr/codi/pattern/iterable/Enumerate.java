package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;

import fr.codi.pattern.IndexedValue;
import fr.codi.pattern.iterator.EnumerateIterator;

public class Enumerate<T> implements Iterable<IndexedValue<T>> {

	private final Iterable<T> inner;
	private final int start;

	public Enumerate(final Iterable<T> inner, final int start) {
		this.inner = inner;
		this.start = start;
	}

	public Enumerate(final Iterable<T> inner) {
		this(inner, 0);
	}

	@Override
	public Iterator<IndexedValue<T>> iterator() {
		return new EnumerateIterator<>(this.inner.iterator(), this.start);
	}

	public static void main(final String[] args) {
		for (final IndexedValue<String> element : new Enumerate<>(
				Arrays.asList("A", "B", "C"))) {
			System.out.print(element.getIndex());
			System.out.print(", ");
			System.out.println(element.getValue());
		}
	}
}