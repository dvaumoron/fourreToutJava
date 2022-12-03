package fr.codi.pattern.iterable;

import java.util.Iterator;

import fr.codi.pattern.iterator.CountIterator;

public class Count implements Iterable<Integer> {

	private final int start;
	private final int step;

	public Count(final int start, final int step) {
		this.start = start;
		this.step = step;
	}

	public Count(final int start) {
		this(start, 1);
	}

	public Count() {
		this(0, 1);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new CountIterator(this.start, this.step);
	}

	public static void main(final String[] args) {
		for (final int i : new Limit<>(new Count(10), 10)) {
			System.out.println(i);
		}
	}
}