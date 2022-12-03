package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionAll implements Fonction {

	ALL;

	@Override
	public String getName() {
		return "all";
	}

	@Override
	public Object apply(final List<Object> args)
			throws Exception {
		boolean res = true;
		for (final Object value : (Iterable<?>) args.get(0)) {
			if (!((Boolean) value)) {
				res = false;
				break;
			}
		}
		return res;
	}
}