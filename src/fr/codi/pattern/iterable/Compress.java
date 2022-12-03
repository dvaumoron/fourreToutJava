package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;

import fr.codi.pattern.iterator.CompressIterator;

public class Compress<T> implements Iterable<T> {

	private final Iterable<T> data;
	private final Iterable<Boolean> selector;

	public Compress(final Iterable<T> data, final Iterable<Boolean> selector) {
		this.data = data;
		this.selector = selector;
	}

	@Override
	public Iterator<T> iterator() {
		return new CompressIterator<>(this.data.iterator(),
				this.selector.iterator());
	}

	public static void main(final String[] args) {
		for (final String s : new Compress<>(Arrays.asList("A", "B", "C", "D",
				"E", "F"), new Compute<>(true, b -> !b))) {
			System.out.println(s);
		}
	}
}