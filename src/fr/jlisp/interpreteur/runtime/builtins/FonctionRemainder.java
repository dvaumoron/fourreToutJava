package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionRemainder implements Fonction {

	REMAINDER;

	@Override
	public String getName() {
		return "%";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Number arg0 = (Number) args.get(0);
		final Number arg1 = (Number) args.get(1);
		return arg0.longValue() % arg1.longValue();
	}
}