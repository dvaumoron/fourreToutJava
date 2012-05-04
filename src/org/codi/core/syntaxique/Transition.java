package org.codi.core.syntaxique;

import org.codi.interpreter.semantique.Semantique;

public class Transition {

	private final Action action;
	private final Etat etat;
	private final Semantique semantique;

	public Transition(Action action, Etat etat, Semantique semantique) {
		this.action = action;
		this.etat = etat;
		this.semantique = semantique;
	}

	public Action getAction() {
		return action;
	}

	public Etat getEtat() {
		return etat;
	}

	public Semantique getSemantique() {
		return semantique;
	}

	public static enum Action {
		SHIFT,
		REDUCE,
		EXPAND
	}
}
