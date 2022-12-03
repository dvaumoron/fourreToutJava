package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionIsClass implements Fonction {

	IS_CLASS;

	@Override
	public String getName() {
		return "isClass";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Object arg0 = args.get(0);
		final boolean res;
		if (arg0 instanceof Environement) {
			res = ((Environement) arg0).isClass();
		} else {
			res = arg0 instanceof Class;
		}
		return res;
	}
}