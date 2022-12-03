package fr.codi.pattern.iterable;

import java.util.Iterator;

import fr.codi.pattern.iterator.NegRangeIterator;
import fr.codi.pattern.iterator.RangeIterator;
import fr.codi.text.Rope;

public class Range implements Iterable<Integer> {

	private final int start;
	private final int end;
	private final int step;

	public Range(final int start, final int end, final int step) {
		this.start = start;
		this.end = end;
		this.step = step;
		if (step == 0) {
			throw new IllegalArgumentException("step must not be 0");
		}
	}

	public Range(final int end) {
		this(0, end, 1);
	}

	public Range(final int start, final int end) {
		this(start, end, 1);
	}

	@Override
	public Iterator<Integer> iterator() {
		if (this.step < 0) {
			return new NegRangeIterator(this.start, this.end, this.step);
		} else {
			return new RangeIterator(this.start, this.end, this.step);
		}
	}

	@Override
	public String toString() {
		Rope buffer = new Rope(7);
		buffer.append("Range(");
		buffer.append(Integer.toString(this.start));
		buffer.append(", ");
		buffer.append(Integer.toString(this.end));
		buffer.append(", ");
		buffer.append(Integer.toString(this.step));
		buffer.append(")");
		return buffer.toString();
	}

	public static void main(final String[] args) {
		final Range range = new Range(-2, -9, -3);
		System.out.println(range);
		for (int i : range) {
			System.out.println(i);
		}

		final Range range2 = new Range(5, 10);
		System.out.println(range2);
		for (int i : range2) {
			System.out.println(i);
		}

		final Range range3 = new Range(9);
		System.out.println(range3);
		for (int i : range3) {
			System.out.println(i);
		}
	}
}