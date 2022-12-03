package fr.codi.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntBinaryOperator;

import fr.codi.pattern.iterable.Chain;
import fr.codi.pattern.iterable.Limit;
import fr.codi.pattern.iterable.Repeat;

public class Counter<T> implements Map<T, Integer> {

	private final Map<T, Integer> inner = new LinkedHashMap<>();

	public Counter() {
	}

	public Counter(final Map<? extends T, ? extends Integer> map) {
		this.inner.putAll(map);
	}

	public Counter(final Iterable<? extends T> iterable) {
		for (final T key : iterable) {
			this.put(key, this.get(key) + 1);
		}
	}

	private Counter<T> applyOp(final Counter<? extends T> other,
			final IntBinaryOperator op) {
		final Counter<T> res = new Counter<>(this);
		for (final Entry<? extends T, Integer> entry : other.entrySet()) {
			final T key = entry.getKey();
			res.put(key, op.applyAsInt(res.get(key), entry.getValue()));
		}
		return res;
	}

	private Counter<T> iApplyOp(final Counter<? extends T> other,
			final IntBinaryOperator op) {
		for (final Entry<? extends T, Integer> entry : other.entrySet()) {
			final T key = entry.getKey();
			this.put(key, op.applyAsInt(this.get(key), entry.getValue()));
		}
		return this;
	}

	public Counter<T> add(final Counter<? extends T> other) {
		return this.applyOp(other, (a, b) -> a + b);
	}

	public Counter<T> subtract(final Counter<? extends T> other) {
		return this.applyOp(other, (a, b) -> a - b);
	}

	public Counter<T> max(final Counter<? extends T> other) {
		return this.applyOp(other, (a, b) -> Math.max(a, b));
	}

	public Counter<T> min(final Counter<? extends T> other) {
		return this.applyOp(other, (a, b) -> Math.min(a, b));
	}

	public Counter<T> iAdd(final Counter<? extends T> other) {
		return this.iApplyOp(other, (a, b) -> a + b);
	}

	public Counter<T> iSubtract(final Counter<? extends T> other) {
		return this.iApplyOp(other, (a, b) -> a - b);
	}

	public Counter<T> iMax(final Counter<? extends T> other) {
		return this.iApplyOp(other, (a, b) -> Math.max(a, b));
	}

	public Counter<T> iMin(final Counter<? extends T> other) {
		return this.iApplyOp(other, (a, b) -> Math.min(a, b));
	}

	public Iterable<T> elements() {
		final List<Iterable<? extends T>> res = new ArrayList<>();
		for (final Entry<T, Integer> entry : this.entrySet()) {
			final int count = entry.getValue();
			if (count > 0) {
				res.add(new Limit<>(new Repeat<>(entry.getKey()), count));
			}
		}
		return new Chain<T>(res);
	}

	public List<Entry<T, Integer>> mostCommon() {
		final List<Entry<T, Integer>> res = new ArrayList<>(this.entrySet());
		Collections.sort(res, (a, b) -> b.getValue() - a.getValue());
		return res;
	}

	@Override
	public void clear() {
		this.inner.clear();
	}

	@Override
	public boolean containsKey(final Object key) {
		return this.inner.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) {
		return this.inner.containsValue(value);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Counter other = (Counter) obj;
		return this.inner.equals(other.inner);
	}

	@Override
	public Set<Entry<T, Integer>> entrySet() {
		return this.inner.entrySet();
	}

	@Override
	public Integer get(final Object key) {
		Integer res = this.inner.get(key);
		if (res == null) {
			res = 0;
		}
		return res;
	}

	@Override
	public int hashCode() {
		return this.inner.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return this.inner.isEmpty();
	}

	@Override
	public Set<T> keySet() {
		return this.inner.keySet();
	}

	@Override
	public Integer put(final T key, final Integer value) {
		return this.inner.put(key, value);
	}

	@Override
	public void putAll(final Map<? extends T, ? extends Integer> map) {
		this.inner.putAll(map);
	}

	@Override
	public Integer remove(final Object key) {
		return this.inner.remove(key);
	}

	@Override
	public int size() {
		return this.inner.size();
	}

	@Override
	public Collection<Integer> values() {
		return this.inner.values();
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("Counter[");
		buffer.append(this.inner);
		buffer.append("]");
		return buffer.toString();
	}
}