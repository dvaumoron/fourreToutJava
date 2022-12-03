package fr.jlisp.interpreteur.runtime.builtins;

import java.util.ArrayList;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroCallMethod implements Macro {

	CALL_METHOD;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object object = args.get(0).eval(environement);
		final String methodName = Util.getName(environement, args.get(1));

		if (object instanceof Environement) {
			final Environement objectEnv = (Environement) object;
			final Fonction method = objectEnv.getFonction(methodName);
			if (method != null) {
				final List<Object> newArgs = Util.buildNewArgs(environement,
						args.subList(2, args.size()), objectEnv);
				return method.apply(newArgs);
			}
		}
		final Class<?> clazz;
		if (object instanceof Class) {
			clazz = (Class<?>) object;
			object = null;
		} else {
			clazz = object.getClass();
		}

		final List<Object> newArgs = new ArrayList<>(args.size() - 2);
		for (Node node : args.subList(2, args.size())) {
			newArgs.add(node.eval(environement));
		}
		return Util
				.callMethod(environement, object, clazz, methodName, newArgs);
	}
}