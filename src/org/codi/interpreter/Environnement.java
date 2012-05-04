package org.codi.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Environnement {

	private final Map<String, Object> variables = new HashMap<String, Object>();

	public Object get(String nom) {
		return variables.get(nom);
	}

	public void set(String nom, Object valeur) {
		variables.put(nom, valeur);
	}

	public void declare(String nom, Object valeur) {
		variables.put(nom, valeur);
	}

	public boolean declare(String nom) {
		return variables.containsKey(nom);
	}
}
