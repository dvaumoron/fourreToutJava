package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.BreakException;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroForEach implements Macro {

	FOR_EACH;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object res = null;
		final NodeList nodeList = (NodeList) args.get(0);
		final String name = ((NodeIdentifier) nodeList.get(0)).getIdentifier();
		final Node nodeBody = args.get(1);
		for (final Object object : (Iterable<?>) nodeList.get(1).eval(
				environement)) {
			environement.set(name, object);
			try {
				res = nodeBody.eval(environement);
			} catch (final BreakException be) {
				if (be.isBreak()) {
					break;
				}
			}
		}
		environement.del(name);
		return res;
	}
}