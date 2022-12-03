package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroIs implements Macro {

	IS;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		boolean res = true;
		final Iterator<Node> it = args.iterator();
		if (it.hasNext()) {
			final Object object = it.next().eval(environement);
			while (it.hasNext()) {
				if (object != it.next().eval(environement)) {
					res = false;
					break;
				}
			}
		}
		return res;
	}
}