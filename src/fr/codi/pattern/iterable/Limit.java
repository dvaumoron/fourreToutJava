package fr.codi.pattern.iterable;

import java.util.Iterator;

import fr.codi.pattern.iterator.LimitIterator;

public class Limit<T> implements Iterable<T> {

	private final Iterable<T> inner;
	private int time;

	public Limit(final Iterable<T> inner, final int time) {
		this.inner = inner;
		this.time = time;
	}

	@Override
	public Iterator<T> iterator() {
		return new LimitIterator<>(this.inner.iterator(), this.time);
	}

	public static void main(final String[] args) {
		for (final int i : new Limit<>(new Count(), 10)) {
			System.out.println(i);
		}
	}
}