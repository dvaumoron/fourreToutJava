package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionList implements Fonction {

	LIST;

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public Object apply(final List<Object> args) {
		return args;
	}
}