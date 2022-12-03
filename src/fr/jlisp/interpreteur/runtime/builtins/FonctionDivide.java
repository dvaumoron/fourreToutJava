package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionDivide implements Fonction {

	DIVIDE;

	@Override
	public String getName() {
		return "/";
	}

	@Override
	public Object apply(final List<Object> args) {
		double res = 1;
		final Iterator<Object> it = args.iterator();
		if (it.hasNext()) {
			Number next = (Number) it.next();
			res = next.doubleValue();
			while (it.hasNext()) {
				next = (Number) it.next();
				res /= next.doubleValue();
			}
		}
		return res;
	}
}