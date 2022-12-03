package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.ToDoubleBiFunction;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionAddMultiply implements Fonction {

	ADD(0, 0, (a, b) -> a + b, (a, b) -> a + b, (a, b) -> a + b, "+"), MULTIPLY(
			1, 1, (a, b) -> a * b, (a, b) -> a * b, (a, b) -> a * b, "*");

	private final long initLong;
	private final double initDouble;
	private final LongBinaryOperator opLong;
	private final DoubleBinaryOperator opDouble;
	private final ToDoubleBiFunction<Long, Double> opLongDouble;
	private final String name;

	private FonctionAddMultiply(final long initLong, final double initDouble,
			final LongBinaryOperator opLong,
			final DoubleBinaryOperator opDouble,
			final ToDoubleBiFunction<Long, Double> opLongDouble,
			final String name) {
		this.initLong = initLong;
		this.initDouble = initDouble;
		this.opLong = opLong;
		this.opDouble = opDouble;
		this.opLongDouble = opLongDouble;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Object apply(final List<Object> args) {
		long resLong = this.initLong;
		double resDouble = this.initDouble;
		boolean hasDouble = false;
		for (Object arg : args) {
			Number argNumber = (Number) arg;
			if (hasDouble) {
				resDouble = this.opDouble.applyAsDouble(resDouble,
						argNumber.doubleValue());
			} else if (arg instanceof Integer || arg instanceof Long) {
				resLong = this.opLong.applyAsLong(resLong,
						argNumber.longValue());
			} else {
				hasDouble = true;
				resDouble = this.opLongDouble.applyAsDouble(resLong,
						argNumber.doubleValue());
			}
		}
		if (hasDouble) {
			return resDouble;
		} else {
			return resLong;
		}
	}
}