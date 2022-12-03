package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroCase implements Macro {

	CASE;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object res = null;
		final Iterator<Node> it = args.iterator();
		while (it.hasNext()) {
			final Node nodeTest = it.next();
			final Node nodeValue = it.next();
			if ((Boolean) nodeTest.eval(environement)) {
				res = nodeValue.eval(environement);
				break;
			}
		}
		return res;
	}
}