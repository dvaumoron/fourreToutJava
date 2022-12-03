package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.UserFonction;
import fr.jlisp.interpreteur.runtime.UserVarArgsFonction;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroLambda implements Macro {

	LAMBDA;

	@Override
	public Object apply(final Environement environement, final List<Node> args) {
		final Node functionArgs = args.get(0);
		final Node body = args.get(1);
		if (functionArgs instanceof NodeList) {
			return new UserFonction(environement, (NodeList) functionArgs, body);
		} else {
			return new UserVarArgsFonction(environement,
					((NodeIdentifier) functionArgs).getIdentifier(), body);
		}
	}
}