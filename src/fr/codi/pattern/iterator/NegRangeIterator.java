package fr.codi.pattern.iterator;

public class NegRangeIterator extends RangeIterator {

	public NegRangeIterator(final int start, final int end, final int step) {
		super(start, end, step);
	}

	@Override
	public boolean hasNext() {
		return this.current > this.end;
	}
}