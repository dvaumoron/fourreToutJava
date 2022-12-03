package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;
import java.util.function.LongBinaryOperator;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionBitwise implements Fonction {

	AND((a, b) -> a & b, "&"), OR((a, b) -> a | b, "|"), XOR((a, b) -> a ^ b,
			"^");

	private final LongBinaryOperator op;
	private final String name;

	private FonctionBitwise(final LongBinaryOperator op, final String name) {
		this.op = op;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Object apply(List<Object> args) throws Exception {
		long res = 0;
		final Iterator<Object> it = args.iterator();
		if (it.hasNext()) {
			res = ((Number) it.next()).longValue();
			while (it.hasNext()) {
				res = this.op
						.applyAsLong(res, ((Number) it.next()).longValue());
			}
		}
		return res;
	}
}