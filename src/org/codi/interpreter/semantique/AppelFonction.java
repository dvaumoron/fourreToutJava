package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;
import org.codi.interpreter.objet.Fonction;

public class AppelFonction implements Semantique {

	public static final AppelFonction APPEL_FONCTION = new AppelFonction();

	private AppelFonction() {
	}

	@Override
	public Object evaluer(Environnement environnement,
			List<Expression> expressions) {
		return ((Fonction) expressions.get(0).evaluer(environnement)).appliquer(
				environnement, expressions.subList(1, expressions.size()));
	}

}
