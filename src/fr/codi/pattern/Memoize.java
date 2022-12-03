package fr.codi.pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoize<T, R> implements Function<T, R> {

	private Function<T, R> wrapped;
	private final Map<T, R> cache = new LinkedHashMap<>();

	public Memoize() {
	}

	public Memoize(final Function<T, R> wrapped) {
		this.wrapped = wrapped;
	}

	public Function<T, R> getWrapped() {
		return wrapped;
	}

	public void setWrapped(final Function<T, R> wrapped) {
		this.wrapped = wrapped;
	}

	public Map<T, R> getCache() {
		return cache;
	}

	@Override
	public R apply(final T value) {
		if (this.cache.containsKey(value)) {
			return this.cache.get(value);
		} else {
			final R res = this.wrapped.apply(value);
			this.cache.put(value, res);
			return res;
		}
	}

	public static void main(String[] args) {
		final Memoize<Integer, Integer> fact = new Memoize<>();
		final Function<Integer, Integer> factWrapped = new PrintCall<>("fact",
				System.out, n -> n * fact.apply(n - 1));
		fact.setWrapped(factWrapped);
		final Map<Integer, Integer> factCache = fact.getCache();
		factCache.put(0, 1);

		System.out.println(fact.apply(10));
		System.out.println(factCache);

		final Memoize<Integer, Integer> fib = new Memoize<>();
		final Function<Integer, Integer> fibWrapped = new PrintCall<>("fib",
				System.out, n -> fib.apply(n - 1) + fib.apply(n - 2));
		fib.setWrapped(fibWrapped);
		final Map<Integer, Integer> fibCache = fib.getCache();
		fibCache.put(0, 0);
		fibCache.put(1, 1);

		System.out.println(fib.apply(10));
		System.out.println(fib.apply(10));
		System.out.println(fibCache);
	}
}