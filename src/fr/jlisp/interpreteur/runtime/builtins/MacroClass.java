package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroClass implements Macro {

	CLASS;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String className = Util.getName(environement, args.get(0));
		final String category = Util.getCategory(Util.CLASS, className);
		final Environement clazz;
		final Node nodeBody;
		if (args.size() > 2) {
			final Environement parentClass = (Environement) args.get(1).eval(
					environement);
			clazz = new EnvironementLocal(true, category, parentClass);
			nodeBody = args.get(2);
		} else {
			clazz = new EnvironementLocal(true, category, environement);
			nodeBody = args.get(1);
		}
		nodeBody.eval(clazz);
		environement.set(className, clazz);
		return clazz;
	}
}