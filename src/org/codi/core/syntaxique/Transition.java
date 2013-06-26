package org.codi.core.syntaxique;

import org.codi.interpreter.semantique.Semantique;

public class Transition {

	private final Action action;
	private final Etat etat;
	private final Semantique semantique;

	private Transition(Action action, Etat etat, Semantique semantique) {
		this.action = action;
		this.etat = etat;
		this.semantique = semantique;
	}

	public static Transition create(Action action, Etat etat) {
		return new Transition(action, etat, null);
	}

	public static Transition create(Semantique semantique) {
		return new Transition(Action.REDUCE, null, semantique);
	}

	public static Transition create(Action action) {
		return new Transition(action, null, null);
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
		SHIFT_BLANK,
		SHIFT,
		REDUCE,
		EXPAND,
		ERROR
	}
}
