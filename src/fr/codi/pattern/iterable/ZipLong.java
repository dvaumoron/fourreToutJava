package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fr.codi.pattern.iterator.ZipLongIterator;

public class ZipLong<T> implements Iterable<List<T>> {

	private final T fillValue;
	private final Iterable<Iterable<? extends T>> inners;

	public ZipLong(final T fillValue, final Iterable<Iterable<? extends T>> iterables) {
		this.fillValue = fillValue;
		this.inners = iterables;
	}

	public ZipLong(final Iterable<Iterable<? extends T>> iterables) {
		this(null, iterables);
	}

	public ZipLong(final T fillValue, final Iterable<? extends T>... iterables) {
		this(fillValue, Arrays.asList(iterables));
	}

	public ZipLong(final Iterable<? extends T>... iterables) {
		this(null, iterables);
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new ZipLongIterator<>(this.fillValue, this.inners.iterator());
	}

	public static void main(final String[] args) {
		for (final List<String> l : new ZipLong<>("-", Arrays.asList("A", "B"),
				Arrays.asList("C", "D", "E"), Arrays.asList("F", "G", "H", "I"))) {
			System.out.println(l);
		}
	}
}