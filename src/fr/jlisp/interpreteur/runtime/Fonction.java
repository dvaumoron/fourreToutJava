package fr.jlisp.interpreteur.runtime;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface Fonction extends UnaryOperator<Object>, Consumer<Object>,
		Predicate<Object>, Supplier<Object>, Comparator<Object>, Runnable,
		Callable<Object> {

	public abstract Object apply(final List<Object> args) throws Exception;

	public abstract String getName();

	@Override
	public default Object apply(final Object object) {
		try {
			return this.apply(Collections.singletonList(object));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default void accept(final Object object) {
		try {
			this.apply(Collections.singletonList(object));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default boolean test(final Object object) {
		try {
			return (Boolean) this.apply(Collections.singletonList(object));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default Object get() {
		try {
			return this.apply(Collections.emptyList());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default int compare(final Object arg0, final Object arg1) {
		try {
			final Number res = (Number) this.apply(Arrays.asList(arg0, arg1));
			return res.intValue();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default void run() {
		try {
			this.apply(Collections.emptyList());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public default Object call() {
		try {
			return this.apply(Collections.emptyList());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}