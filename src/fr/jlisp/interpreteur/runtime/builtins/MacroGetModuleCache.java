package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroGetModuleCache implements Macro {

	GET_MODULE_CACHE;

	@Override
	public Object apply(final Environement environement, final List<Node> args) {
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		return interpreteur.getModuleCache();
	}
}