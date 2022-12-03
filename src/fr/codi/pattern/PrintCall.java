package fr.codi.pattern;

import java.io.PrintStream;
import java.util.function.Function;

public class PrintCall<T, R> implements Function<T, R> {

	private final String name;
	private final PrintStream out;
	private final Function<T, R> wrapped;

	public PrintCall(final String name, final PrintStream out,
			final Function<T, R> wrapped) {
		this.name = name;
		this.out = out;
		this.wrapped = wrapped;
	}

	@Override
	public R apply(final T value) {
		this.out.print(this.name);
		this.out.print("(");
		this.out.print(value);
		this.out.println(")");
		return this.wrapped.apply(value);
	}
}
