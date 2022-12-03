package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionId implements Fonction {

	ID;

	@Override
	public String getName() {
		return "id";
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		return args.get(0);
	}
}