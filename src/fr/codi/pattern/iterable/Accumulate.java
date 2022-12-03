package fr.codi.pattern.iterable;

import java.util.Iterator;
import java.util.function.BinaryOperator;

import fr.codi.pattern.iterator.AccumulateIterator;

public class Accumulate<T> implements Iterable<T> {

	private final Iterable<T> inner;
	private final BinaryOperator<T> function;

	public Accumulate(final Iterable<T> inner,
			final BinaryOperator<T> function) {
		this.inner = inner;
		this.function = function;
	}

	@Override
	public Iterator<T> iterator() {
		return new AccumulateIterator<>(this.inner.iterator(), this.function);
	}

	public static void main(final String[] args) {
		for (final int i : new Accumulate<>(
				new Range(1, 6), (a, b) -> a + b)) {
			System.out.println(i);
		}
	}
}