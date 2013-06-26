package org.codi.core.lexical;

import java.util.HashMap;
import java.util.Map;

import org.codi.core.lexical.Lexeme.Type;

public class Etat {

	private final Type type;
	private final boolean notPoubelle;
	private final Etat defautTransition;
	private final Map<Character, Etat> transitions = new HashMap<Character, Etat>();

	private Etat(Type type, boolean notPoubelle, Etat defautTransition) {
		this.type = type;
		this.notPoubelle = notPoubelle;
		this.defautTransition = defautTransition;
	}

	public static Etat create(Type type, Etat defautTransition) {
		return new Etat(type, true, defautTransition);
	}

	public static Etat create(Etat defautTransition) {
		return new Etat(null, true, defautTransition);
	}

	public static Etat create() {
		return new Etat(null, false, null);
	}

	public void addTransition(char c, Etat e) {
		transitions.put(c, e);
	}

	public Etat transition(char c) {
		Etat resultat = transitions.get(c);
		if (resultat == null) {
			resultat = defautTransition;
		}
		return resultat;
	}

	public boolean isValide() {
		return type != null;
	}

	public boolean isNotPoubelle() {
		return notPoubelle;
	}

	public Type getType() {
		return type;
	}

}
