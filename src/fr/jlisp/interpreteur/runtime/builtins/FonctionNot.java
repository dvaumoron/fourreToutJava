package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionNot implements Fonction {

	NOT;

	@Override
	public String getName() {
		return "not";
	}

	@Override
	public Object apply(final List<Object> args) {
		return !((Boolean) args.get(0));
	}
}