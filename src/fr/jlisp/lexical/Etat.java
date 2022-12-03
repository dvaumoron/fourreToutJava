package fr.jlisp.lexical;

import java.util.HashMap;
import java.util.Map;

public class Etat {

	private final String name;
	private final Map<Character, Etat> transitions = new HashMap<>();
	private Etat defautTransition;
	private final boolean notPoubelle;
	private final Type type;

	public Etat(final String name) {
		this(name, true, null);
	}

	public Etat(final String name, final boolean notPoubelle) {
		this(name, notPoubelle, null);
	}

	public Etat(final String name, final Type type) {
		this(name, true, type);
	}

	private Etat(final String name, final boolean notPoubelle, final Type type) {
		this.name = name;
		this.notPoubelle = notPoubelle;
		this.type = type;
	}

	public void addTransition(final char c, final Etat etat) {
		this.transitions.put(c, etat);
	}

	public void addTransitions(final String s, final Etat etat) {
		final int length = s.length();
		for (int i = 0; i < length; i++) {
			this.transitions.put(s.charAt(i), etat);
		}
	}

	public void setDefautTransition(final Etat defautTransition) {
		this.defautTransition = defautTransition;
	}

	public boolean isNotPoubelle() {
		return this.notPoubelle;
	}

	public Type getType() {
		return this.type;
	}

	public Etat next(final char c) {
		Etat res = this.transitions.get(c);
		if (res == null) {
			res = this.defautTransition;
		}
		return res;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(this.name.length() + 6);
		buffer.append("Etat[");
		buffer.append(this.name);
		buffer.append("]");
		return buffer.toString();
	}
}