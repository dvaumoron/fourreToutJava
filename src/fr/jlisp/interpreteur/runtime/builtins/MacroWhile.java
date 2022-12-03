package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.BreakException;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroWhile implements Macro {

	WHILE;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object res = null;
		final Node nodeTest = args.get(0);
		final Node nodeBody = args.get(1);
		while ((Boolean) nodeTest.eval(environement)) {
			try {
				res = nodeBody.eval(environement);
			} catch (final BreakException be) {
				if (be.isBreak()) {
					break;
				}
			}
		}
		return res;
	}
}