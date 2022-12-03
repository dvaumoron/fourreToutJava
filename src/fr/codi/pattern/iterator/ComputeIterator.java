package fr.codi.pattern.iterator;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public class ComputeIterator<T> implements Iterator<T> {

	private final UnaryOperator<T> operator;
	private T value;

	public ComputeIterator(final T value, final UnaryOperator<T> operator) {
		this.operator = operator;
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		T res = this.value;
		this.value = this.operator.apply(res);
		return res;
	}
}