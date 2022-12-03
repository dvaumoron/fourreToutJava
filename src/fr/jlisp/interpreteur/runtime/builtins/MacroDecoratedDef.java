package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.UserFonction;
import fr.jlisp.interpreteur.runtime.UserVarArgsFonction;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroDecoratedDef implements Macro {

	DECORATED_DEF;

	@Override
	public Object apply(Environement environement, List<Node> args)
			throws Exception {
		final int size = args.size();
		final String name = Util.getName(environement, args.get(size - 3));
		final Node functionArgs = args.get(size - 2);
		final Node body = args.get(size - 1);
		Object value;
		if (functionArgs instanceof NodeList) {
			value = new UserFonction(name, environement,
					(NodeList) functionArgs, body);
		} else {
			value = new UserVarArgsFonction(name, environement,
					((NodeIdentifier) functionArgs).getIdentifier(), body);
		}
		value = Util.decorate(environement, args, size - 4, value);
		environement.set(name, value);
		return value;
	}
}