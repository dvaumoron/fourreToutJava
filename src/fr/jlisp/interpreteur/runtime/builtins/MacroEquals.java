package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroEquals implements Macro {

	EQUALS;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		boolean res = true;
		final Iterator<Node> it = args.iterator();
		if (it.hasNext()) {
			final Object object = it.next().eval(environement);
			if (object == null) {
				while (it.hasNext()) {
					if (it.next().eval(environement) != null) {
						res = false;
						break;
					}
				}
			} else {
				while (it.hasNext()) {
					if (!object.equals(it.next().eval(environement))) {
						res = false;
						break;
					}
				}
			}
		}
		return res;
	}
}