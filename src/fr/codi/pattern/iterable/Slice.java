package fr.codi.pattern.iterable;

import java.util.Arrays;
import java.util.Iterator;

import fr.codi.pattern.iterator.InfiniteSliceIterator;
import fr.codi.pattern.iterator.SliceIterator;

public class Slice<T> implements Iterable<T> {

	private final Iterable<T> inner;
	private final int start;
	private final Integer end;
	private final int step;

	public Slice(final Iterable<T> inner, final int start, final Integer end,
			final int step) {
		this.inner = inner;
		this.start = start;
		this.end = end;
		this.step = step;
	}

	public Slice(final Iterable<T> inner, final int start, final Integer end) {
		this(inner, start, end, 1);
	}

	public Slice(final Iterable<T> inner, final Integer end) {
		this(inner, 0, end, 1);
	}

	@Override
	public Iterator<T> iterator() {
		if (end == null) {
			return new InfiniteSliceIterator<>(this.inner.iterator(),
					this.start, this.step);
		} else {
			return new SliceIterator<>(this.inner.iterator(), this.start,
					this.end, this.step);
		}
	}

	public static void main(String[] args) {
		for (int i : new Slice<>(new Count(10), 5)) {
			System.out.println(i);
		}

		for (String s : new Slice<>(Arrays.asList("A", "B", "C", "D", "E"), 2,
				null)) {
			System.out.println(s);
		}

		for (String s : new Slice<>(Arrays.asList("a", "b", "c", "d", "e"), 0,
				5, 2)) {
			System.out.println(s);
		}
	}
}