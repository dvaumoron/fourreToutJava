package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public enum MacroSetMultiple implements Macro {

	SET_MULTIPLE;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		int i = 0;
		final NodeList nodeList = (NodeList) args.get(0);
		final int size = nodeList.size();
		for (final Object value : (Iterable<?>) args.get(1).eval(environement)) {
			if (i < size) {
				final String name = Util.getName(environement, nodeList.get(i));
				environement.set(name, value);
				i++;
			} else {
				break;
			}
		}
		return null;
	}
}