package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.syntaxique.NodeIdentifier;

public enum FonctionNode�dentifier implements Fonction {

	NODE_IDENTIFIER;

	@Override
	public String getName() {
		return "nodeIdentifier";
	}

	@Override
	public Object apply( final List<Object> args) {
		return new NodeIdentifier((String) args.get(0));
	}
}