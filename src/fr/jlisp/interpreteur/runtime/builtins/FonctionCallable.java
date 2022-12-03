package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Util;

public enum FonctionCallable implements Fonction {

	CALLABLE;

	@Override
	public String getName() {
		return "callable";
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		return isCallable(args.get(0));
	}

	private static boolean isCallable(final Object object) throws Exception {
		if (object instanceof Class) {
			return true;
		} else if (object instanceof Environement) {
			final Environement objectEnv = (Environement) object;
			if (objectEnv.isClass()) {
				return true;
			} else {
				try {
					return isInnerCallable(objectEnv.getFonction(Util.APPLY));
				} catch (final ClassCastException cce) {
					return false;
				}
			}
		} else {
			return object instanceof Fonction;
		}
	}

	private static boolean isInnerCallable(final Fonction fonction)
			throws Exception {
		if (fonction instanceof Environement) {
			final Environement objectEnv = (Environement) fonction;
			return isInnerCallable(objectEnv.getFonction(Util.APPLY));
		}
		return true;
	}
}