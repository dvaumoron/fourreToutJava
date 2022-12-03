package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroBloc implements Macro {

	BLOC;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		Object res = null;
		for (final Node arg : args) {
			res = arg.eval(environement);
		}
		return res;
	}
}