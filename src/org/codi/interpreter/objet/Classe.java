package org.codi.interpreter.objet;

import java.util.Arrays;
import java.util.List;

public class Classe {

	public static final Classe OBJET = new Classe("codi.lang.Objet", null);
	public static final Classe BOOLEEN = new Classe("codi.lang.Booleen", Arrays.asList(OBJET));
	public static final Classe FONCTION = new Classe("codi.lang.Fonction", Arrays.asList(OBJET));
	public static final Classe NOMBRE = new Classe("codi.lang.Nombre", Arrays.asList(OBJET));
	public static final Classe ENTIER = new Classe("codi.lang.Entier", Arrays.asList(NOMBRE));;
	public static final Classe REEL = new Classe("codi.lang.Reel", Arrays.asList(NOMBRE));

	private final String nom;
	private final List<Classe> parents;

	private Classe(String nom, List<Classe> parents) {
		this.nom = nom;
		this.parents = parents;
	}

	public boolean peutContenir(Classe classe) {
		boolean compatible = false;
		if (nom.equals(classe.nom)) {
			compatible = true;
		} else if (classe.parents != null) {
			for (Classe parent : classe.parents) {
				if (peutContenir(parent)) {
					compatible = true;
					break;
				}
			}
		}
		return compatible;
	}
	
}
