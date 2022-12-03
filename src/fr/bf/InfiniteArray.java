package fr.bf;

import java.util.ArrayList;
import java.util.List;

public class InfiniteArray {

	private final List<Integer> positiveArray = new ArrayList<>();
	private final List<Integer> negativeArray = new ArrayList<>();
	private int index = 0;

	public InfiniteArray() {
		this.positiveArray.add(0);
		this.negativeArray.add(0);
	}

	public int get() {
		if (this.index < 0) {
			return this.negativeArray.get(-this.index - 1);
		} else {
			return this.positiveArray.get(this.index);
		}
	}
	
	public int set(final int value) {
		if (this.index < 0) {
			return this.negativeArray.set(-this.index - 1, value);
		} else {
			return this.positiveArray.set(this.index, value);
		}
	}

	public void increment() {
		if (this.index < 0) {
			final int negativeIndex = -this.index - 1;
			this.negativeArray.set(negativeIndex, this.negativeArray.get(negativeIndex) + 1);
		} else {
			this.positiveArray.set(this.index, this.positiveArray.get(this.index) + 1);
		}
	}

	public void decrement() {
		if (this.index < 0) {
			final int negativeIndex = -this.index - 1;
			this.negativeArray.set(negativeIndex, this.negativeArray.get(negativeIndex) - 1);
		} else {
			this.positiveArray.set(this.index, this.positiveArray.get(this.index) - 1);
		}
	}

	public void shiftRight() {
		this.index++;
		if (this.index == this.positiveArray.size()) {
			this.positiveArray.add(0);
		}
	}

	public void shiftLeft() {
		this.index--;
		if (-this.index - 1 == this.negativeArray.size()) {
			this.negativeArray.add(0);
		}
	}
}
