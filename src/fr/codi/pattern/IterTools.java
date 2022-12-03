package fr.codi.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import fr.codi.pattern.iterable.Enumerate;
import fr.codi.pattern.iterable.Range;

public final class IterTools {

	private IterTools() {
	}

	public static boolean all(final Iterable<Boolean> iterable) {
		for (final boolean value : iterable) {
			if (!value) {
				return false;
			}
		}
		return true;
	}

	public static boolean any(final Iterable<Boolean> iterable) {
		for (final boolean value : iterable) {
			if (value) {
				return true;
			}
		}
		return false;
	}

	public static <T extends Comparable<T>> T max(final Iterable<T> iterable) {
		return reduce((a, b) -> {
			final T res;
			if (a.compareTo(b) > 0) {
				res = a;
			} else {
				res = b;
			}
			return res;
		}, iterable);
	}

	public static <T extends Comparable<T>> T min(final Iterable<T> iterable) {
		return reduce((a, b) -> {
			final T res;
			if (a.compareTo(b) < 0) {
				res = a;
			} else {
				res = b;
			}
			return res;
		}, iterable);
	}

	public static <T> T reduce(final BinaryOperator<T> operator,
			final Iterable<T> iterable, final T start) {
		T res = start;
		for (final T value : iterable) {
			res = operator.apply(res, value);
		}
		return res;
	}

	public static <T> T reduce(final BinaryOperator<T> operator,
			final Iterable<T> iterable) {
		T res = null;
		final Iterator<T> it = iterable.iterator();
		if (it.hasNext()) {
			res = it.next();
			while (it.hasNext()) {
				res = operator.apply(res, it.next());
			}
		}
		return res;
	}

	public static <T> List<T> toList(final Iterable<T> iterable) {
		final List<T> res = new ArrayList<>();
		toCollection(iterable, res);
		return res;
	}

	public static <T> Set<T> toSet(final Iterable<T> iterable) {
		final Set<T> res = new LinkedHashSet<>();
		toCollection(iterable, res);
		return res;
	}

	public static <T> void toCollection(final Iterable<T> iterable,
			final Collection<T> dest) {
		for (final T value : iterable) {
			dest.add(value);
		}
	}

	public static <K, V, T> Map<K, V> toMap(final Iterable<T> iterable,
			final Function<T, K> keyFunction, final Function<T, V> valueFunction) {
		final Map<K, V> res = new LinkedHashMap<>();
		for (final T value : iterable) {
			res.put(keyFunction.apply(value), valueFunction.apply(value));
		}
		return res;
	}

	public static void main(final String[] args) {
		final List<Boolean> l1 = Arrays.asList(false, true, false);
		final List<Boolean> l2 = Arrays.asList(false, false, false);
		final List<Boolean> l3 = Arrays.asList(true, true, true);
		System.out.println(any(l1));
		System.out.println(any(l2));
		System.out.println(any(l3));
		System.out.println(all(l1));
		System.out.println(all(l2));
		System.out.println(all(l3));

		System.out.println(max(new Range(1, 5)));
		System.out.println(min(new Range(1, 5)));

		final BinaryOperator<Integer> plusOperator = (a, b) -> a + b;
		final Integer sum0 = reduce(plusOperator, new Range(0));
		System.out.println(sum0);

		final int sum5 = reduce(plusOperator, new Range(6));
		System.out.println(sum5);

		final int fact10 = reduce((a, b) -> a * b, new Range(2, 11), 1);
		System.out.println(fact10);

		final List<IndexedValue<String>> enumerate = toList(new Enumerate<>(
				Arrays.asList("A", "B", "C"), 1));
		System.out.println(enumerate);

		final Map<String, Integer> map = toMap(enumerate, a -> a.getValue(),
				b -> b.getIndex());
		System.out.println(map);

		final Counter<String> counter = new Counter<>(Arrays.asList("a", "b",
				"b", "c", "c", "c"));
		System.out.println(counter);
		System.out.println(toList(counter.elements()));
		counter.iAdd(new Counter<>(Arrays.asList("c", "d", "d", "e", "e", "e")));
		System.out.println(counter.mostCommon());

		final DefaultMap<String, List<Integer>> dm = new DefaultMap<>(
				a -> new ArrayList<>());
		for (final IndexedValue<String> iValue : new Enumerate<>(Arrays.asList(
				"A", "B", "C", "A"))) {
			dm.get(iValue.getValue()).add(iValue.getIndex());
		}
		System.out.println(dm);
	}
}