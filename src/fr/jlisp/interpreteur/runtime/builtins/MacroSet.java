package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroSet implements Macro {

	SET;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String name = Util.getName(environement, args.get(0));
		final Object value = args.get(1).eval(environement);
		environement.set(name, value);
		return value;
	}
}