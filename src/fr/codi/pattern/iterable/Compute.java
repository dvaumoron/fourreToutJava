package fr.codi.pattern.iterable;

import java.util.Iterator;
import java.util.function.UnaryOperator;

import fr.codi.pattern.iterator.ComputeIterator;

public class Compute<T> implements Iterable<T> {

	private final T value;
	private final UnaryOperator<T> operator;

	public Compute(final T value, final UnaryOperator<T> operator) {
		this.value = value;
		this.operator = operator;
	}

	@Override
	public Iterator<T> iterator() {
		return new ComputeIterator<>(this.value, this.operator);
	}

	public static void main(final String[] args) {
		for (final String s : new Limit<>(new Compute<>("a", e -> e + "a"), 10)) {
			System.out.println(s);
		}
	}
}