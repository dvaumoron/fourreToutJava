package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.BreakException;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroFor implements Macro {

	FOR;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object res = null;
		final NodeList nodeList = (NodeList) args.get(0);
		final String name = ((NodeIdentifier) nodeList.get(0)).getIdentifier();
		long index = ((Number) nodeList.get(1).eval(environement)).longValue();
		final long end = ((Number) nodeList.get(2).eval(environement))
				.longValue();
		final long step;
		if (nodeList.size() > 3) {
			step = ((Number) nodeList.get(3).eval(environement)).longValue();
		} else {
			step = 1;
		}
		final Node nodeBody = args.get(1);
		if (step > 0) {
			while (index < end) {
				environement.set(name, index);
				try {
					res = nodeBody.eval(environement);
				} catch (final BreakException be) {
					if (be.isBreak()) {
						break;
					}
				}
				index += step;
			}
		} else {
			while (index > end) {
				environement.set(name, index);
				try {
					res = nodeBody.eval(environement);
				} catch (final BreakException be) {
					if (be.isBreak()) {
						break;
					}
				}
				index += step;
			}
		}
		environement.del(name);
		return res;
	}
}