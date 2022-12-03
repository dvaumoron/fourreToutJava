package fr.jlisp.interpreteur.runtime.builtins;

import java.util.LinkedHashSet;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionHashSet implements Fonction {

	HASH_SET;

	@Override
	public String getName() {
		return "hashSet";
	}

	@Override
	public Object apply(final List<Object> args) {
		return new LinkedHashSet<>(args);
	}
}