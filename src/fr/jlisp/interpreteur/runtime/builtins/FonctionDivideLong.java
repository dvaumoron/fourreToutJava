package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionDivideLong implements Fonction {

	DIVIDE_LONG;

	@Override
	public String getName() {
		return "//";
	}

	@Override
	public Object apply(final List<Object> args) {
		long res = 1;
		final Iterator<Object> it = args.iterator();
		if (it.hasNext()) {
			Number next = (Number) it.next();
			res = next.longValue();
			while (it.hasNext()) {
				next = (Number) it.next();
				res /= next.longValue();
			}
		}
		return res;
	}
}