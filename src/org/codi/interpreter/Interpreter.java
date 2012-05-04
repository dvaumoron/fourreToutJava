package org.codi.interpreter;

import static org.codi.interpreter.objet.Fonction.RETURN;
import static org.codi.interpreter.semantique.Affectation.DECLARATION;
import static org.codi.interpreter.semantique.AppelFonction.APPEL_FONCTION;
import static org.codi.interpreter.semantique.Controle.IF;
import static org.codi.interpreter.semantique.Operation.EGALITE;
import static org.codi.interpreter.semantique.Operation.MULTIPLICATION;
import static org.codi.interpreter.semantique.Operation.SOUSTRACTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codi.interpreter.objet.Entier;
import org.codi.interpreter.objet.Fonction;

public class Interpreter {

	public static void main(String[] args) {

		Constante un = new Constante(new Entier(1));
		Variable variableN = new Variable("n");
		Variable variableReturn = new Variable(RETURN);
		Variable variableFactorielle = new Variable("factorielle");

		Expression noeudSoustraction = new Noeud(SOUSTRACTION, Arrays.asList(variableN, un));
		Expression appelRecursif = new Noeud(APPEL_FONCTION, Arrays.asList(variableFactorielle, noeudSoustraction));
		Expression noeudProduit = new Noeud(MULTIPLICATION, Arrays.asList(variableN, appelRecursif));

		List<Expression> listIf = new ArrayList<Expression>();
		listIf.add(new Noeud(EGALITE, Arrays.asList(variableN, new Constante(new Entier(0)))));
		listIf.add(new Noeud(DECLARATION, Arrays.asList(variableReturn, un)));
		listIf.add(new Noeud(DECLARATION, Arrays.asList(variableReturn, noeudProduit)));
		Expression noeudIf = new Noeud(IF, listIf);

		Fonction factorielle = new Fonction(
				Arrays.asList(variableN),
				Arrays.asList(noeudIf));

		Environnement environnement = new Environnement();
		environnement.set("factorielle", factorielle);

		Noeud appel = new Noeud(APPEL_FONCTION, Arrays.asList(variableFactorielle, new Constante(new Entier(5))));

		System.out.println(((Entier) appel.evaluer(environnement)).getValeur());
	}

}
