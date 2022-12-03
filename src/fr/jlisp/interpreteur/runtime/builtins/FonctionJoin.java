package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionJoin implements Fonction {

	JOIN;

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public Object apply(final List<Object> args) {
		final String sep = (String) args.get(0);
		final Iterable<?> iterable = (Iterable<?>) args.get(1);
		final StringBuilder buffer = new StringBuilder();
		final Iterator<?> it = iterable.iterator();
		if (it.hasNext()) {
			buffer.append(it.next());
			while (it.hasNext()) {
				buffer.append(sep);
				buffer.append(it.next());
			}
		}
		return buffer.toString();
	}
}