package fr.codi.pattern.iterable;

import java.util.Iterator;
import java.util.function.Function;

import fr.codi.pattern.iterator.ApplyIterator;

public class Apply<T, R> implements Iterable<R> {

	private final Function<T, R> function;
	private final Iterable<T> inner;

	public Apply(final Function<T, R> function, final Iterable<T> inner) {
		this.function = function;
		this.inner = inner;
	}

	@Override
	public Iterator<R> iterator() {
		return new ApplyIterator<>(this.function, this.inner.iterator());
	}

	public static void main(final String[] args) {
		for(final int i : new Apply<>(e -> e + 1, new Range(5))) {
			System.out.println(i);
		}
	}
}