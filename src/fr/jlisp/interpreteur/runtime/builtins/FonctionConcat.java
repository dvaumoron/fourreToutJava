package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionConcat implements Fonction {

	CONCAT;

	@Override
	public String getName() {
		return "concat";
	}

	@Override
	public Object apply(final List<Object> args) {
		final StringBuilder buffer = new StringBuilder();
		for (final Object arg : args) {
			buffer.append(arg);
		}
		return buffer.toString();
	}
}