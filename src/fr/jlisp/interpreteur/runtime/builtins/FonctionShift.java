package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;
import java.util.function.LongBinaryOperator;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionShift implements Fonction {

	LEFT((a, b) -> a << b, "<<"), RIGHT((a, b) -> a >> b, ">>");

	private final LongBinaryOperator op;
	private final String name;

	private FonctionShift(final LongBinaryOperator op, final String name) {
		this.op = op;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Object apply(final List<Object> args) {
		final Number arg0 = (Number) args.get(0);
		final Number arg1 = (Number) args.get(1);
		return this.op.applyAsLong(arg0.longValue(), arg1.longValue());
	}
}