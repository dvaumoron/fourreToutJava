package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroEval implements Macro {

	EVAL;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final Object object = args.get(0).eval(environement);
		final Environement evalEnv;
		if (args.size() > 1) {
			evalEnv = (Environement) args.get(1).eval(environement);
		} else {
			evalEnv = environement;
		}
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		return interpreteur.eval((String) object, evalEnv);
	}
}