package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionThrow implements Fonction {

	THROW;

	@Override
	public String getName() {
		return "throw";
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		throw (Exception) args.get(0);
	}
}