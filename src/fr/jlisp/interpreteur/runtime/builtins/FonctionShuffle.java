package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Collections;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionShuffle implements Fonction {

	SHUFFLE;

	@Override
	public String getName() {
		return "shuffle";
	}

	@Override
	public Object apply(final List<Object> args) {
		final List<?> list = (List<?>) args.get(0);
		Collections.shuffle(list);
		return null;
	}
}