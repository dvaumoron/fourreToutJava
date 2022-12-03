package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionSort implements Fonction {

	SORT;

	@Override
	public String getName() {
		return "sort";
	}

	@Override
	public Object apply(final List<Object> args) {
		final List list = (List) args.get(0);
		if (args.size() > 1) {
			final Comparator comparator = (Comparator) args.get(1);
			Collections.sort(list, comparator);
		} else {
			Collections.sort(list);
		}
		return null;
	}
}