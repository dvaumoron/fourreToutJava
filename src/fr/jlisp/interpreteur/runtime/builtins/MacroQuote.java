package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroQuote implements Macro {

	QUOTE;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		return execUnquote(environement, args.get(0));
	}

	private static Node execUnquote(final Environement environement,
			final Node node) throws Exception {
		if (node instanceof NodeList) {
			final NodeList nodeList = (NodeList) node;
			final int size = nodeList.size();
			final NodeList res = new NodeList();

			if (size > 0) {
				final Node node0 = nodeList.get(0);
				if (node0 instanceof NodeIdentifier) {
					final NodeIdentifier nodeIdentifier = (NodeIdentifier) node0;
					if (Util.UNQUOTE.equals(nodeIdentifier.getIdentifier())) {
						final Node node1 = nodeList.get(1);
						return (Node) node1.eval(environement);
					}
				}
				res.add(execUnquote(environement, node0));
				for (int i = 1; i < size; i++) {
					res.add(execUnquote(environement, nodeList.get(i)));
				}
			}
			return res;
		}
		return node;
	}
}