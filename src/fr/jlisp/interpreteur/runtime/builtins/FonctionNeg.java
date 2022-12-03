package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionNeg implements Fonction {

	NEG;

	@Override
	public String getName() {
		return "neg";
	}

	@Override
	public Object apply(final List<Object> args) {
		Number value = (Number) args.get(0);
		if (value instanceof Integer || value instanceof Long) {
			return -value.longValue();
		} else {
			return -value.doubleValue();
		}
	}
}