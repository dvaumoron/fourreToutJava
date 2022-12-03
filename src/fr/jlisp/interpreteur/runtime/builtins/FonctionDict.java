package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionDict implements Fonction {

	DICT;

	@Override
	public String getName() {
		return "dict";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Map<Object, Object> res;
		if (args.size() > 0) {
			final Object object = args.get(0);
			if (object instanceof Map) {
				res = new LinkedHashMap<>((Map<?, ?>) object);
			} else if (object instanceof Iterable) {
				res = new LinkedHashMap<>();
				for (final Object values : (Iterable<?>) object) {
					final Iterable<?> iterable = (Iterable<?>) values;
					final Iterator<?> it = iterable.iterator();
					res.put(it.next(), it.next());
				}
			} else {
				throw new IllegalArgumentException("argument non pris en compte");
			}
		} else {
			res = new LinkedHashMap<>();
		}
		return res;
	}
}