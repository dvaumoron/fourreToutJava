package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import fr.codi.pattern.iterator.DropUntilIterator;

public class DropUntil<T> implements Iterable<T> {

	private final Predicate<T> predicate;
	private final Iterable<T> inner;

	public DropUntil(final Predicate<T> predicate, final Iterable<T> inner) {
		this.predicate = predicate;
		this.inner = inner;
	}

	@Override
	public Iterator<T> iterator() {
		return new DropUntilIterator<>(this.predicate, this.inner.iterator());
	}

	public static void main(final String[] args) {
		for (final String s : new DropUntil<>(a -> a.contains("i"), Arrays.asList("toto", "titi", "tata"))) {
			System.out.println(s);
		}
	}
}