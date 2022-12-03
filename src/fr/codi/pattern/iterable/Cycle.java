package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;

import fr.codi.pattern.iterator.CycleIterator;

public class Cycle<T> implements Iterable<T> {

	private final Iterable<T> inner;

	public Cycle(final Iterable<T> inner) {
		this.inner = inner;
	}

	@Override
	public Iterator<T> iterator() {
		return new CycleIterator<>(this.inner.iterator());
	}

	public static void main(final String[] args) {
		for (final String s : new Limit<>(new Cycle<>(
				Arrays.asList("A", "B", "C", "D")), 8)) {
			System.out.println(s);
		}
	}
}