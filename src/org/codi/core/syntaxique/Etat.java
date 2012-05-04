package org.codi.core.syntaxique;

import java.util.HashMap;
import java.util.Map;

import org.codi.core.lexical.Lexeme;

public class Etat {

	private final Map<Lexeme, Transition> transitions = new HashMap<Lexeme, Transition>();
	private final Etat suivant;

	public Etat(Etat suivant) {
		this.suivant = suivant;
	}

	public void addTransition(Lexeme l, Transition t) {
		transitions.put(l, t);
	}

	public Transition transition(Lexeme l) {
		return transitions.get(l);
	}

	public Etat getSuivant() {
		return suivant;
	}

}
