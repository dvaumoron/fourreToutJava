package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroSetAttr implements Macro {

	SET_ATTR;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object object = args.get(0).eval(environement);
		final String name = Util.getName(environement, args.get(1));
		final Object value = args.get(2).eval(environement);
		if (object instanceof Environement) {
			final Environement objectEnv = (Environement) object;
			objectEnv.set(name, value);
		} else {
			final Class<?> clazz;
			if (object instanceof Class) {
				clazz = (Class<?>) object;
				object = null;
			} else {
				clazz = object.getClass();
			}
			clazz.getField(name).set(object, value);
		}
		return value;
	}
}