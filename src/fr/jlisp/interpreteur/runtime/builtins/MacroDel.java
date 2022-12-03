package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroDel implements Macro {

	DEL;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String name = Util.getName(environement, args.get(0));
		environement.del(name);
		return null;
	}
}