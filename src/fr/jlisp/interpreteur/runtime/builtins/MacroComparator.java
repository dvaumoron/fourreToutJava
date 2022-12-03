package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroComparator implements Macro {

	GREATER_OR_EQUALS((a, b) -> a < b), GREATER_THAN((a, b) -> a <= b), LESSER_OR_EQUALS(
			(a, b) -> a > b), LESSER_THAN((a, b) -> a >= b);

	private final BiPredicate<Double, Double> testFalse;

	private MacroComparator(final BiPredicate<Double, Double> testFalse) {
		this.testFalse = testFalse;
	}

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		boolean res = true;
		final Iterator<Node> it = args.iterator();
		if (it.hasNext()) {
			final Number next = (Number) it.next().eval(environement);
			Double value = next.doubleValue();
			while (it.hasNext()) {
				final Number next2 = (Number) it.next().eval(environement);
				final Double value2 = next2.doubleValue();
				if (this.testFalse.test(value, value2)) {
					res = false;
					break;
				} else {
					value = value2;
				}
			}
		}
		return res;
	}
}