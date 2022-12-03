package fr.jlisp.interpreteur.runtime;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public class BiFonction implements BinaryOperator<Object> {

	private final Fonction inner;

	public BiFonction(final Fonction inner) {
		this.inner = inner;
	}

	@Override
	public Object apply(final Object arg0, final Object arg1) {
		try {
			return this.inner.apply(Arrays.asList(arg0, arg1));
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}