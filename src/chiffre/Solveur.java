package chiffre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Solveur {

	public static void main(String[] args) {
		if (args.length == 0) {
			Solveur solveur = new SolveurIt();
			recherche(solveur, 144, Arrays.asList(2, 3, 9, 10));

			recherche(solveur, 836, Arrays.asList(2, 3, 5, 8, 9, 10));

			recherche(solveur, 988, Arrays.asList(1, 25, 75, 4, 3, 50));
		} else if (args.length == 1) {
			if (args[0].equals("-r")) {
				Solveur solveur = new SolveurRec();
				recherche(solveur, 144, Arrays.asList(2, 3, 9, 10));

				recherche(solveur, 836, Arrays.asList(2, 3, 5, 8, 9, 10));

				recherche(solveur, 988, Arrays.asList(1, 25, 75, 4, 3, 50));
			} else if (args[0].equals("-h") || args[0].equals("--help")) {
				System.out.println("Utilisation : Solveur [options] valeurRecherchee nombre1 nombre2 ...");
				System.out.println("Liste des options disponibles pour le programme Solveur :");
				System.out.println("-h ou --help : affiche ce message d'aide");
				System.out.println("-r : algorithme recursif");
			} else {
				System.out.println("option non reconnue");
			}
		} else {
			int offset = 0;
			Solveur solveur;
			if (args[0].equals("-r")) {
				solveur = new SolveurRec();
				offset = 1;
			} else {
				solveur = new SolveurIt();
			}
			List<Integer> chiffres = new ArrayList<Integer>();
			for(int i = offset + 1; i < args.length; i++) {
				chiffres.add(Integer.parseInt(args[i]));
			}
			recherche(solveur, Integer.parseInt(args[offset]), chiffres);
		}
	}
	
	public static void recherche(
			Solveur solveur, int valeurRecherchee, List<Integer> chiffres) {

		List<Solution> solutions = solveur.recherche(chiffres);

		Collections.sort(solutions, new SolutionComparator(valeurRecherchee));

		System.out.println(solutions.get(0));
	}

	public abstract List<Solution> recherche(List<Integer> chiffres);
}
