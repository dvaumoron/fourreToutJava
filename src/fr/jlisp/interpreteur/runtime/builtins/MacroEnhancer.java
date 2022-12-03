package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroEnhancer implements Macro {

	ENHANCER;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final Class<?> clazz = (Class<?>) args.get(0).eval(environement);
		final String methodName = Util.getName(environement, args.get(1));
		final Fonction fonction = (Fonction) args.get(2).eval(environement);
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		interpreteur.getEnhancers().put(clazz, methodName, fonction);
		return fonction;
	}
}