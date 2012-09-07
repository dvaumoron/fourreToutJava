package org.codi.interpreter;

import static org.codi.interpreter.objet.Fonction.RETURN;
import static org.codi.interpreter.semantique.Affectation.DECLARATION;
import static org.codi.interpreter.semantique.Fonction.APPEL_FONCTION;
import static org.codi.interpreter.semantique.Fonction.FONCTION;
import static org.codi.interpreter.semantique.Fonction.LISTE;
import static org.codi.interpreter.semantique.Controle.IF;
import static org.codi.interpreter.semantique.Controle.BLOC;
import static org.codi.interpreter.semantique.Operation.EGALITE;
import static org.codi.interpreter.semantique.Operation.MULTIPLICATION;
import static org.codi.interpreter.semantique.Operation.SOUSTRACTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codi.interpreter.objet.Classe;
import org.codi.interpreter.objet.Entier;

public class Interpreter {

	public static void main(String[] args) {

		Expression un = new Constante(new Entier(1));
		Expression variableN = new Variable("n", Classe.ENTIER);
		Expression variableReturn = new Variable(RETURN, Classe.ENTIER);
		Expression variableFactorielle = new Variable("factorielle", Classe.FONCTION);

		Expression noeudSoustraction = new Noeud(SOUSTRACTION, Arrays.asList(variableN, un));
		Expression appelRecursif = new Noeud(APPEL_FONCTION, Arrays.asList(variableFactorielle, noeudSoustraction));
		Expression noeudProduit = new Noeud(MULTIPLICATION, Arrays.asList(variableN, appelRecursif));

		List<Expression> listIf = new ArrayList<Expression>();
		listIf.add(new Noeud(EGALITE, Arrays.asList(variableN, new Constante(new Entier(0)))));
		listIf.add(new Noeud(DECLARATION, Arrays.asList(variableReturn, un)));
		listIf.add(new Noeud(DECLARATION, Arrays.asList(variableReturn, noeudProduit)));
		Expression noeudIf = new Noeud(IF, listIf);

		Expression arguments = new Noeud(LISTE, Arrays.asList(variableN));
		Expression factorielle = new Noeud (FONCTION, Arrays.asList(
				arguments, new Noeud(LISTE, Arrays.asList(noeudIf))));

		Expression declarationFactorielle = new Noeud(DECLARATION, Arrays.asList(
				variableFactorielle, factorielle));

		Expression appel = new Noeud(APPEL_FONCTION, Arrays.asList(variableFactorielle, new Constante(new Entier(5))));

		Expression bloc = new Noeud(BLOC, Arrays.asList(declarationFactorielle, appel));

		Environnement environnement = new Environnement();
		System.out.println(((Entier) bloc.evaluer(environnement)).getValeur());
	}

}
