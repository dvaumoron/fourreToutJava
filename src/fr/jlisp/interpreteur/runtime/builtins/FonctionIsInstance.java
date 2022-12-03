package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionIsInstance implements Fonction {

	IS_INSTANCE;

	@Override
	public String getName() {
		return "isInstance";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Object arg0 = args.get(0);
		final Object arg1 = args.get(1);
		if (arg1 instanceof Class) {
			final Class<?> clazz = (Class<?>) arg1;
			return clazz.isInstance(arg0);
		} else if (arg0 instanceof EnvironementLocal
				&& arg1 instanceof Environement) {
			final EnvironementLocal objectEnv = (EnvironementLocal) arg0;
			return isSubEnv(objectEnv, (Environement) arg1);
		}
		return false;
	}

	private boolean isSubEnv(final EnvironementLocal objectEnv,
			final Environement clazz) {
		final Environement parent = objectEnv.getParent();
		if (parent == clazz) {
			return true;
		} else if (parent instanceof EnvironementLocal) {
			return isSubEnv((EnvironementLocal) parent, clazz);
		}
		return false;
	}
}