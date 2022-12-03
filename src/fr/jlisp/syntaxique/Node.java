package fr.jlisp.syntaxique;

import fr.jlisp.interpreteur.runtime.Environement;

public interface Node {

	public abstract Object eval(final Environement environement)
			throws Exception;
}