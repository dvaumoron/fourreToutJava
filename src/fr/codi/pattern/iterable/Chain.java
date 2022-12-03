package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import fr.codi.pattern.iterator.ChainIterator;

public class Chain<T> implements Iterable<T> {

	private final Iterable<Iterable<? extends T>> inners;

	public Chain(final Iterable<Iterable<? extends T>> iterables) {
		this.inners = iterables;
	}

	public Chain(final Iterable<? extends T>... iterables) {
		this(Arrays.asList(iterables));
	}

	@Override
	public Iterator<T> iterator() {
		return new ChainIterator<>(this.inners.iterator());
	}

	public static void main(final String[] args) {
		for (final String s : new Chain<>(Arrays.asList("A", "B"),
				Arrays.asList("C", "D", "E"), Collections.emptyList(),
				Arrays.asList("F", "G"))) {
			System.out.println(s);
		}
	}
}