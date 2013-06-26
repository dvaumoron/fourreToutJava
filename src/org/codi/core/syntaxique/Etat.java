package org.codi.core.syntaxique;

import java.util.HashMap;
import java.util.Map;

import org.codi.core.lexical.Lexeme;

public class Etat {

	private final Map<Lexeme, Transition> transitions = new HashMap<Lexeme, Transition>();
	private final Etat suivant;
	private final Transition defaultTransition;
	private final String label;

	private Etat(Etat suivant, Transition defaultTransition, String label) {
		this.suivant = suivant;
		this.defaultTransition = defaultTransition;
		this.label = label;
	}

	public static Etat create(Etat suivant, Transition defaultTransition, String label) {
		return new Etat(suivant, defaultTransition, label);
	}

	public static Etat create(Transition defaultTransition, String label) {
		return new Etat(null, defaultTransition, label);
	}

	public void addTransition(Lexeme l, Transition t) {
		transitions.put(l, t);
	}

	public Transition transition(Lexeme lexeme) {
		Transition transition = transitions.get(lexeme);
		if (transition == null) {
			transition = defaultTransition;
		}
		return transition;
	}

	public Etat getSuivant() {
		return suivant;
	}

	@Override
	public String toString() {
		return label;
	}
	
}
