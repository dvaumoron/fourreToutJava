package fr.jlisp.interpreteur.runtime.builtins;

import java.util.ArrayList;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroCall implements Macro {

	CALL;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final Object object = args.get(0).eval(environement);
		final Object object2 = args.get(1).eval(environement);
		final List<Object> listArgs;
		if (object2 instanceof List) {
			listArgs = (List<Object>) object2;
		} else {
			listArgs = new ArrayList<>();
			for (final Object value : (Iterable<?>) object2) {
				listArgs.add(value);
			}
		}
		final int size = listArgs.size();

		final Object res;
		if (object instanceof Class) {
			final Class<?> clazz = (Class<?>) object;
			if (size == 0) {
				return clazz.newInstance();
			} else {
				final Object[] objects = new Object[size];
				final Class<?>[] classes = new Class[size];
				for (int i = 0; i < size; i++) {
					objects[i] = listArgs.get(i);
					classes[i] = objects[i].getClass();
				}

				res = Util.callConstructor(clazz, objects, classes);
			}
		} else if (object instanceof Environement) {
			final Environement objectEnv = (Environement) object;
			if (objectEnv.isClass()) {
				final String category = Util.getCategory(Util.OBJECT,
						objectEnv.getName());
				res = new EnvironementLocal(false, category, objectEnv);
				final Fonction initFonction = objectEnv.getFonction(Util.INIT);
				if (initFonction != null) {
					final List<Object> newArgs = new ArrayList<>(size + 1);
					newArgs.add(res);
					newArgs.addAll(listArgs);
					initFonction.apply(newArgs);
				}
			} else {
				res = objectEnv.apply(listArgs);
			}
		} else if (object instanceof Fonction) {
			final Fonction fonction = (Fonction) object;
			res = fonction.apply(listArgs);
		} else {
			res = Util.callMethod(environement, object, object.getClass(),
					Util.APPLY, listArgs);
		}
		return res;
	}
}