package fr.jlisp.interpreteur.runtime.builtins;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.MethodWrapper;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroGetAttr implements Macro {

	GET_ATTR;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object object = args.get(0).eval(environement);
		final String name = Util.getName(environement, args.get(1));
		if (object instanceof Environement) {
			final Environement objectEnv = (Environement) object;
			Object res = objectEnv.getInClass(name);
			if (res == null) {
				final Fonction getAttributeFonction = (Fonction) objectEnv
						.getInClass(Util.GET_ATTRIBUTE);
				if (getAttributeFonction != null) {
					res = getAttributeFonction.apply(Arrays.asList(objectEnv,
							name));
				}
			}
			if (res != null) {
				return res;
			}
		}
		final Class<?> clazz;
		if (object instanceof Class) {
			clazz = (Class<?>) object;
			object = null;
		} else {
			clazz = object.getClass();
		}

		try {
			return clazz.getField(name).get(object);
		} catch (final NoSuchFieldException nsfe) {
			if (hasMethod(clazz, name)) {
				return new MethodWrapper(environement, clazz, name);
			} else {
				return null;
			}
		}
	}

	private static boolean hasMethod(final Class<?> clazz,
			final String methodName) {
		for (final Method method : clazz.getMethods()) {
			if (methodName.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}
}