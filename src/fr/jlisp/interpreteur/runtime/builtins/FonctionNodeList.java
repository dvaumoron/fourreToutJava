package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public enum FonctionNodeList implements Fonction {

	NODE_LIST;

	@Override
	public String getName() {
		return "nodeList";
	}

	@Override
	public Object apply(final List<Object> args) {
		final NodeList res = new NodeList();
		for (Object arg : args) {
			res.add((Node) arg);
		}
		return res;
	}
}