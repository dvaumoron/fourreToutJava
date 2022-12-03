package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroDecoratedClass implements Macro {

	DECORATED_CLASS;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final int size = args.size();
		final String className = Util.getName(environement, args.get(size - 3));
		final String category = Util.getCategory(Util.CLASS, className);
		final Environement parentClass = (Environement) args.get(size - 2)
				.eval(environement);
		Object clazz = new EnvironementLocal(true, category, parentClass);
		final Node nodeBody = args.get(size - 1);
		nodeBody.eval((Environement) clazz);
		clazz = Util.decorate(environement, args, size - 4, clazz);
		environement.set(className, clazz);
		return clazz;
	}
}