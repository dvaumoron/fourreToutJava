package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.syntaxique.Node;

public enum MacroGetEnv implements Macro {

	GET_ENV;

	@Override
	public Object apply(final Environement environement, final List<Node> args) {
		return environement;
	}
}