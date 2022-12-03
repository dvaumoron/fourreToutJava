package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroDelAttr implements Macro {

	DEL_ATTR;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final Environement object = (Environement) args.get(0).eval(
				environement);
		final String name = Util.getName(environement, args.get(1));
		object.del(name);
		return null;
	}
}