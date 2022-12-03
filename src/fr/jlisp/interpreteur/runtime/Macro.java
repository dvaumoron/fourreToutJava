package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;

@FunctionalInterface
public interface Macro {

	public abstract Object apply(final Environement environement,
			final List<Node> args) throws Exception;
}