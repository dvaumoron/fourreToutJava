package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Arrays;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionSplit implements Fonction {

	SPLIT;

	@Override
	public String getName() {
		return "split";
	}

	@Override
	public Object apply(final List<Object> args) {
		final String str = (String) args.get(0);
		final String str2 = (String) args.get(1);
		return Arrays.asList(str.split(str2));
	}
}