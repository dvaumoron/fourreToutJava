package fr.sudoku;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Case {

	private final Set<Integer> possibility = new LinkedHashSet<>();
	private boolean first = true;

	public Case() {
		this.reset();
	}

	public Case(final int value) {
		if (value < 1 || value > 9) {
			throw new IllegalArgumentException();
		}
		this.possibility.add(value);
	}

	public Case(final Case caseValue) {
		this.possibility.addAll(caseValue.possibility);
	}

	public boolean found() {
		return this.possibility.size() == 1;
	}

	public boolean foundFirst() {
		final boolean res;
		if (this.first && this.possibility.size() == 1) {
			this.first = false;
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	public int getValue() {
		return this.possibility.iterator().next();
	}

	public boolean impossible(final int value) {
		return this.possibility.remove(value);
	}

	public boolean isPossible(final int value) {
		return this.possibility.contains(value);
	}

	public boolean set(final int value) {
		if (value < 1 || value > 9) {
			throw new IllegalArgumentException();
		}
		final boolean res;
		if (this.possibility.size() == 1) {
			res = false;
		} else {
			this.possibility.clear();
			this.possibility.add(value);
			res = true;
		}
		return res;
	}

	public void reset() {
		this.possibility.add(1);
		this.possibility.add(2);
		this.possibility.add(3);
		this.possibility.add(4);
		this.possibility.add(5);
		this.possibility.add(6);
		this.possibility.add(7);
		this.possibility.add(8);
		this.possibility.add(9);
	}

	public boolean randomValue(final Random rand) {
		final boolean res;
		final int size = this.possibility.size();
		if (size > 1)  {
			final int nextInt = rand.nextInt(size);
			final Iterator<Integer> it = this.possibility.iterator();
			Integer value = it.next();
			for (int i = 0; i < nextInt; i++) {
				value = it.next();
			}
			this.possibility.clear();
			this.possibility.add(value);
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	@Override
	public String toString() {
		return this.possibility.toString();
	}

	public String toDisplayString() {
		final String res;
		if (this.found()) {
			res = Integer.toString(this.getValue());
		} else {
			res = " ";
		}
		return res;
	}
}