package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroAnd implements Macro {

	AND;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		boolean res = true;
		for (final Node arg : args) {
			if (!((Boolean) arg.eval(environement))) {
				res = false;
				break;
			}
		}
		return res;
	}
}