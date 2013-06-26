package org.codi.core.lexical;

import org.codi.interpreter.Constante;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Variable;
import org.codi.interpreter.objet.Classe;
import org.codi.interpreter.objet.Entier;
import org.codi.interpreter.objet.Reel;

public class Lexeme {

	private final Type type;
	private final String valeur;

	private Lexeme(Type type, String valeur) {
		this.type = type;
		this.valeur = valeur;
	}

	public static Lexeme create(Type type, String valeur) {
		return new Lexeme(type, valeur);
	}

	public static Lexeme create(Type type) {
		return new Lexeme(type, null);
	}

	public Type getType() {
		return type;
	}

	public String getValeur() {
		return valeur;
	}

	@Override
	public boolean equals(Object obj) {
		Lexeme lexeme = (Lexeme) obj;
		if (type == lexeme.type) {
			switch(type) {
				case OPERATEUR :
					return valeur.equals(lexeme.valeur);
				default :
					return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		switch(type) {
			case OPERATEUR :
				return valeur.hashCode();
			default :
				return type.hashCode();
		}
	}

	public Expression toExpression() {
		switch(type) {
			case NOMBRE :
				try {
					return new Constante(new Entier(Long.parseLong(valeur)));
				} catch (NumberFormatException nfe) {
					return new Constante(new Reel(Double.parseDouble(valeur)));
				}
			case NOM :
				return new Variable(valeur, Classe.OBJET);
			case CHAINE :
				return new Constante(valeur.substring(1, valeur.length() - 1));
			default:
				return null;
		}
	}

	public enum Type {
		OPERATEUR,
		NOMBRE,
		CHAINE,
		NOM,
		ESPACE,
		FIN
	}

	@Override
	public String toString() {
		return "Lexeme [type=" + type + ", valeur=" + valeur + "]";
	}

}
