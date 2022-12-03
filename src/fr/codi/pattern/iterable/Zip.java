package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fr.codi.pattern.iterator.ZipIterator;

public class Zip<T> implements Iterable<List<T>> {

	private final Iterable<Iterable<? extends T>> inners;

	public Zip(final Iterable<Iterable<? extends T>> iterables) {
		this.inners = iterables;
	}

	public Zip(final Iterable<? extends T>... iterables) {
		this(Arrays.asList(iterables));
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new ZipIterator<>(this.inners.iterator());
	}

	public static void main(final String[] args) {
		for (final List<String> l : new Zip<>(Arrays.asList("A", "B"),
				Arrays.asList("C", "D"), Arrays.asList("E", "F", "G"))) {
			System.out.println(l);
		}
	}
}