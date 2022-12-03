package fr.codi.pattern.iterable;

import java.util.Iterator;

import fr.codi.pattern.iterator.RepeatIterator;

public class Repeat<T> implements Iterable<T> {

	private final T value;

	public Repeat(final T value) {
		this.value = value;
	}

	@Override
	public Iterator<T> iterator() {
		return new RepeatIterator<T>(this.value);
	}

	public static void main(final String[] args) {
		for (final String s : new Limit<>(new Repeat<>("a"), 3)) {
			System.out.println(s);
		}
	}
}