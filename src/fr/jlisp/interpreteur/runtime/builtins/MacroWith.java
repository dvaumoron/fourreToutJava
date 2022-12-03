package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroWith implements Macro {

	WITH;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final NodeList nodeList = (NodeList) args.get(0);
		final String name = ((NodeIdentifier) nodeList.get(0)).getIdentifier();
		Object object = null;
		try {
			object = nodeList.get(1).eval(environement);
			environement.set(name, object);
			return args.get(1).eval(environement);
		} finally {
			environement.del(name);
			if (object instanceof AutoCloseable) {
				final AutoCloseable closeable = (AutoCloseable) object;
				closeable.close();
			}
		}
	}
}