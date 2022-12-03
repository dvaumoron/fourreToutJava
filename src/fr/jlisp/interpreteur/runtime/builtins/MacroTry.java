package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public enum MacroTry implements Macro {

	TRY;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		try {
			return args.get(0).eval(environement);
		} catch (final Exception ex) {
			final NodeList nodeList = (NodeList) args.get(1);
			final int size = nodeList.size();
			for (int i = 0; i < size; i++) {
				final NodeList exceptionList = (NodeList) nodeList.get(i);
				final Class<?> clazz = (Class<?>) exceptionList.get(0).eval(
						environement);
				if (clazz.isAssignableFrom(ex.getClass())) {
					final String name = Util.getName(environement,
							exceptionList.get(1));
					environement.set(name, ex);
					Object res = exceptionList.get(2).eval(environement);
					environement.del(name);
					return res;
				}
			}
			throw ex;
		} finally {
			if (args.size() > 2) {
				args.get(2).eval(environement);
			}
		}
	}
}