package fr.codi.pattern.iterable;

import java.util.Iterator;
import java.util.function.Supplier;

import fr.codi.pattern.iterator.GenerateIterator;

public class Generate<T> implements Iterable<T> {

	private final Supplier<T> supplier;

	public Generate(final Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Override
	public Iterator<T> iterator() {
		return new GenerateIterator<>(this.supplier);
	}

	public static void main(final String[] args) {
		for (final String s : new Limit<>(new Generate<>(() -> "a"), 3)) {
			System.out.println(s);
		}
	}
}