package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroIf implements Macro {

	IF;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final Node node;
		if ((Boolean) args.get(0).eval(environement)) {
			node = args.get(1);
		} else {
			node = args.get(2);
		}
		return node.eval(environement);
	}
}