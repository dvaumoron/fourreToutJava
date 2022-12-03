package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.syntaxique.NodeValue;

public enum FonctionNodeValue implements Fonction {

	NODE_VALUE;

	@Override
	public String getName() {
		return "nodeValue";
	}

	@Override
	public Object apply(final List<Object> args) {
		return new NodeValue(args.get(0));
	}
}