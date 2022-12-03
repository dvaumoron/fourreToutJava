package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionType implements Fonction {

	TYPE;

	@Override
	public String getName() {
		return "type";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Object object = args.get(0);
		if (object instanceof EnvironementLocal) {
			final EnvironementLocal objectEnv = (EnvironementLocal) object;
			return objectEnv.getParent();
		} else {
			return object.getClass();
		}
	}
}