package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionAny implements Fonction {

	ANY;

	@Override
	public String getName() {
		return "any";
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		boolean res = false;
		for (final Object value : (Iterable<?>) args.get(0)) {
			if ((Boolean) value) {
				res = true;
				break;
			}
		}
		return res;
	}
}